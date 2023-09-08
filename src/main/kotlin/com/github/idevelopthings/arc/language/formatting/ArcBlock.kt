@file:Suppress("SameParameterValue")

package com.github.idevelopthings.arc.language.formatting

import com.github.idevelopthings.arc.psi.ArcEnumFieldDeclaration
import com.github.idevelopthings.arc.psi.ArcExpression
import com.github.idevelopthings.arc.psi.ArcObjectDeclaration
import com.github.idevelopthings.arc.psi.ArcTokenSets.Companion.COMMENTS
import com.github.idevelopthings.arc.psi.ArcType
import com.github.idevelopthings.arc.psi.ArcTypes.*
import com.github.idevelopthings.arc.psi.ArcValueExpr
import com.intellij.formatting.*
import com.intellij.formatting.alignment.AlignmentStrategy
import com.intellij.formatting.alignment.AlignmentStrategy.AlignmentPerTypeStrategy
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.Key
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.psi.TokenType
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet


class ArcBlock(
		private val myNode: ASTNode,
		private val myAlignment: Alignment?,
		private val myIndent: Indent?,
		private val myWrap: Wrap?,
		private val mySettings: CodeStyleSettings,
		private val mySpacingBuilder: SpacingBuilder
) : UserDataHolderBase(), ASTBlock {

		private var mySubBlocks: MutableList<Block>? = null

		companion object {
				private val BLOCKS_TOKEN_SET = TokenSet.create(
						BLOCK_BODY,
						// HTTP_BLOCK,
//						OBJECT_DECLARATION,
						OBJECT_INSTANTIATE_EXPR,
						DICTIONARY,
						LIST,
				)
				private val BRACES_TOKEN_SET = TokenSet.create(
						LCURLY,
						RCURLY,
						LBRACK,
						RBRACK,
						LPAREN,
						RPAREN
				)

				private val TYPE_ALIGNMENT_INSIDE_OBJECT = Key.create<Alignment>("TYPE_ALIGNMENT_INSIDE_OBJECT")
				private val TYPE_ALIGNMENT_INSIDE_ENUM = Key.create<Alignment>("TYPE_ALIGNMENT_INSIDE_ENUM")
				private fun indentIfNotBrace(child: ASTNode): Indent {
						return if (BRACES_TOKEN_SET.contains(child.elementType)) Indent.getNoneIndent() else Indent.getNormalIndent()
				}

				private fun none(): Spacing {
						return Spacing.createSpacing(0, 0, 0, false, 0)
				}

				private fun one(): Spacing {
						return Spacing.createSpacing(1, 1, 0, false, 0)
				}

				private fun lineBreak() = lineBreak(true)
				private fun lineBreak(keepLineBreaks: Boolean) = lineBreak(0, keepLineBreaks)
				private fun lineBreak(lineBreaks: Int, keepLineBreaks: Boolean) = Spacing.createSpacing(0, 0, lineBreaks + 1, keepLineBreaks, if (keepLineBreaks) 1 else 0)

		}

		override fun getSpacing(child1: Block?, child2: Block): Spacing? {
				if (child1 is ArcBlock && child2 is ArcBlock) {
						val n1 = child1.node
						val n2 = child2.node
						val psi1 = n1.psi
						val psi2 = n2.psi

						if (n1.elementType == OBJECT_FIELD_DECLARATION && psi2 is ArcType)
								return one()

						val parent = psi1?.parent
						if (parent is ArcEnumFieldDeclaration) {
								if (n1.elementType == EQ && psi2 is ArcValueExpr)
										return one()
						}

						if (parent is ArcObjectDeclaration) {
								val oneLineType = !parent.textContains('\n')

								if (n1.elementType == OBJECT_KW && n2.elementType == LCURLY) {
										return if (oneLineType) none() else one()
								}

								if (n1.elementType === LCURLY && n2.elementType === RCURLY) {
										return if (oneLineType) none() else lineBreak()
								}

								if (n1.elementType === LCURLY) {
										return if (oneLineType) one() else lineBreak(false)
								}
								if (n2.elementType === RCURLY) {
										return if (oneLineType) one() else lineBreak(false)
								}
						}

				}

				return mySpacingBuilder.getSpacing(this, child1, child2)
		}

		override fun getChildAttributes(newChildIndex: Int): ChildAttributes {
				var childIndent = Indent.getNoneIndent()
				val parentType = myNode.elementType
				if (BLOCKS_TOKEN_SET.contains(parentType)) {
						childIndent = Indent.getNormalIndent()
				}
				return ChildAttributes(childIndent, null)
		}

		private fun buildSubBlocks(): MutableList<Block> {
				var strategy: AlignmentPerTypeStrategy? = null
				val isObject = node.elementType === OBJECT_DECLARATION
				val isEnum = node.elementType === ENUM_DECLARATION
				var forType: Alignment? = null
				if (isObject) {
						strategy = AlignmentStrategy.createAlignmentPerTypeStrategy(listOf(OBJECT_FIELD_DECLARATION, LINE_COMMENT), OBJECT_DECLARATION, true)
						forType = Alignment.createAlignment(true)
				}
				if (isEnum) {
						strategy = AlignmentStrategy.createAlignmentPerTypeStrategy(listOf(ENUM_FIELD_DECLARATION, LINE_COMMENT), ENUM_DECLARATION, true)
						forType = Alignment.createAlignment(true, Alignment.Anchor.RIGHT)
				}
				val blocks: MutableList<Block> = mutableListOf()
				var child = myNode.firstChildNode
				while (child != null) {
						val childType = child.elementType
						if (child.textRange.length == 0) {
								child = child.treeNext
								continue
						}
						if (childType === TokenType.WHITE_SPACE) {
								child = child.treeNext
								continue
						}
						val substitutor = if (childType === BLOCK_COMMENT) LINE_COMMENT else childType
						val alignment = strategy?.getAlignment(substitutor)
						val e: ArcBlock = buildSubBlock(child, alignment)
						if (isObject && forType != null) {
								e.putUserDataIfAbsent<Alignment>(TYPE_ALIGNMENT_INSIDE_OBJECT, forType)
						}
						if (isEnum && forType != null) {
								e.putUserDataIfAbsent<Alignment>(TYPE_ALIGNMENT_INSIDE_ENUM, forType)
						}
						blocks.add(e)
						child = child.treeNext
				}
				return blocks
		}

		private fun buildSubBlock(child: ASTNode, alignment: Alignment?): ArcBlock {
				var align = alignment
				if (child.psi is ArcType && child.treeParent.elementType === OBJECT_FIELD_DECLARATION) {
						align = getUserData(TYPE_ALIGNMENT_INSIDE_OBJECT)
				}
				if (child.psi is ArcValueExpr && child.treeParent.elementType === ENUM_FIELD_DECLARATION) {
						align = getUserData(TYPE_ALIGNMENT_INSIDE_ENUM)
				}
				val indent: Indent = calcIndent(child)
				return ArcBlock(child, align, indent, null, mySettings, mySpacingBuilder)
		}

		private fun calcIndent(child: ASTNode): Indent {
				val parentType = myNode.elementType
				val type = child.elementType
				if (parentType === ARG_LIST && type !== LPAREN && type !== RPAREN) return Indent.getNormalIndent()
				if (parentType === ARGUMENT_DECLARATION_LIST && type !== LPAREN && type !== RPAREN) return Indent.getNormalIndent()
				if (BLOCKS_TOKEN_SET.contains(parentType)) return indentIfNotBrace(child)
//				if (parentType === FILE) return indentOfMultipleDeclarationChild(type, IMPORT_STATEMENT)
//				if (parentType === STATEMENT) return indentOfMultipleDeclarationChild(type, VARIABLE_DECLARATION)
				if (parentType === OBJECT_DECLARATION) return indentOfMultipleDeclarationChild(type, OBJECT_FIELD_DECLARATION)
				if (parentType === ENUM_DECLARATION) return indentOfMultipleDeclarationChild(type, ENUM_FIELD_DECLARATION)
				if (parentType === LIST_ELEMENTS) return indentOfMultipleDeclarationChild(type, EXPRESSION)
				return if (child.psi is ArcExpression) Indent.getContinuationWithoutFirstIndent() else Indent.getNoneIndent()
		}

		private fun indentOfMultipleDeclarationChild(childType: IElementType, specType: IElementType): Indent {
				if (childType === specType) return Indent.getNormalIndent()
				return if (COMMENTS.contains(childType) && myNode.findChildByType(specType) != null) Indent.getNormalIndent() else Indent.getNoneIndent()
		}

		override fun getSubBlocks(): MutableList<Block> {
				if (mySubBlocks == null) {
						mySubBlocks = buildSubBlocks()
				}
				return mySubBlocks as MutableList<Block>
		}

		override fun isIncomplete(): Boolean = false

		override fun getTextRange(): TextRange = myNode.textRange

		override fun getWrap(): Wrap? = myWrap

		override fun getIndent(): Indent? = myIndent

		override fun getAlignment(): Alignment? = myAlignment

		override fun isLeaf(): Boolean {
				return myNode.firstChildNode == null
		}

		override fun getNode(): ASTNode = myNode

}
