package com.github.idevelopthings.arc.language.formatting

import com.github.idevelopthings.arc.psi.ArcTypes
import com.intellij.formatting.*
import com.intellij.formatting.templateLanguages.BlockWithParent
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock


class ArcBlock(node: ASTNode, wrap: Wrap?, alignment: Alignment?, private val spacingBuilder: SpacingBuilder?) :
		AbstractBlock(node, wrap, alignment), BlockWithParent {

		private var objectFieldAlignment = Alignment.createAlignment(true, Alignment.Anchor.RIGHT)
		private fun resetObjectFieldAlignment() {

				objectFieldAlignment = Alignment.createAlignment(true, Alignment.Anchor.RIGHT)
		}

//		private val objectFieldAlignments = HashMap<ASTNode, Alignment>()
//		private fun getOrCreateAlignmentForParent(parent: ASTNode): Alignment {
//				return objectFieldAlignments.getOrPut(parent) {
//						Alignment.createAlignment(true, Alignment.Anchor.RIGHT)
//				}
//		}

		private var parent: BlockWithParent? = null


		override fun getSpacing(child1: Block?, child2: Block): Spacing? {
				return spacingBuilder?.getSpacing(this, child1, child2)
		}

		override fun getIndent(): Indent? {
				return when {
//						myNode.elementType is IFileElementType -> Indent.getAbsoluteLabelIndent()

//						myNode.elementType == ArcTypes.LCURLY &&
//						myNode.treeParent.elementType == ArcTypes.OBJECT_BODY
//						-> Indent.getNormalIndent()

						myNode.elementType == ArcTypes.OBJECT_FIELD_DECLARATION -> Indent.getNormalIndent()

//						myNode.elementType == ArcTypes.RCURLY &&
//						myNode.treeParent.elementType == ArcTypes.OBJECT_BODY
//						-> Indent.getAbsoluteNoneIndent()


//						myNode.elementType == ArcTypes.OBJECT_FIELD_DECLARATION -> Indent.getNormalIndent()
//						myNode.elementType == ArcTypes.BLOCK_BODY ||
//						(parent as ArcBlock).myNode.elementType == ArcTypes.BLOCK_BODY
//						-> {
//								if (myNode.elementType == ArcTypes.RCURLY &&
//										myNode == (parent as ArcBlock).myNode.lastChildNode
//								) {
//										Indent.getContinuationIndent()
//								} else
//										Indent.getContinuationWithoutFirstIndent()
//						}

						myNode.elementType == ArcTypes.STATEMENT
						-> Indent.getNormalIndent()

						(myNode.elementType == ArcTypes.BLOCK_BODY &&
						 myNode.treeParent?.elementType == ArcTypes.FUNC_DECLARATION)
						-> Indent.getNormalIndent()

						(myNode.treeParent?.elementType == ArcTypes.BLOCK_BODY &&
						 myNode.treeParent?.treeParent?.elementType == ArcTypes.FUNC_DECLARATION)
						-> Indent.getNoneIndent()

						else -> Indent.getNoneIndent()
				}
		}

		private fun shouldAlignTypeBasedOnContext(child: ASTNode): Boolean {
				val parentType = child.treeParent?.elementType
				return parentType == ArcTypes.OBJECT_FIELD_DECLARATION
		}

		override fun isLeaf(): Boolean {
				return myNode.firstChildNode == null;
		}

		override fun buildChildren(): MutableList<Block> {
				val blocks: MutableList<Block> = ArrayList()
				var child = myNode.firstChildNode


				while (child != null) {
						if (child.elementType == ArcTypes.OBJECT_BODY) {
								resetObjectFieldAlignment()
						}

						if (child.elementType != TokenType.WHITE_SPACE) {

								val alignment: Alignment? = when {
										child.elementType == ArcTypes.TYPE &&
										shouldAlignTypeBasedOnContext(child) -> objectFieldAlignment

										else -> null
								}

								val block = ArcBlock(
										child,
										Wrap.createWrap(WrapType.NORMAL, false),
										alignment,
										spacingBuilder
								)
								block.setParent(this)
								blocks.add(block)
						}
						child = child.treeNext
				}
				return blocks
		}


		override fun getChildAttributes(newChildIndex: Int): ChildAttributes {
				return super.getChildAttributes(newChildIndex)
		}

		override fun getParent(): BlockWithParent {
				return parent!!
		}

		override fun setParent(newParent: BlockWithParent?) {
				parent = newParent
		}


}


