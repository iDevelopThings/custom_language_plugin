package com.github.idevelopthings.arc.language

import com.github.idevelopthings.arc.psi.*
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile


class ArcFoldingBuilder : FoldingBuilderEx(), DumbAware {

		override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
				val descriptors = mutableListOf<FoldingDescriptor>()

				root.accept(object : ArcVisitor() {
						override fun visitFile(file: PsiFile) {
								super.visitFile(file)
								if (file is ArcFile) {
										val topLevelDeclarations = file.children
										for (topLevelDeclaration in topLevelDeclarations) {
												if (topLevelDeclaration is ArcTopLevelDeclaration)
														topLevelDeclaration.accept(this)
										}
								}
						}

						override fun visitTopLevelDeclaration(o: ArcTopLevelDeclaration) {
								super.visitTopLevelDeclaration(o)

								o.funcDeclaration?.accept(this)
								o.objectDeclaration?.accept(this)
						}

						override fun visitObjectDeclaration(o: ArcObjectDeclaration) {
								super.visitObjectDeclaration(o)
								descriptors.add(FoldingDescriptor(o.node, o.textRange))
						}

						override fun visitFuncDeclaration(o: ArcFuncDeclaration) {
								super.visitFuncDeclaration(o)
								o.blockBody?.accept(this)
						}

						override fun visitBlockBody(o: ArcBlockBody) {
								super.visitBlockBody(o)
								descriptors.add(FoldingDescriptor(o.node, o.textRange))

								o.statementList.forEach { it.accept(this) }
						}

						override fun visitStatement(o: ArcStatement) {
								super.visitStatement(o)

								o.variableDeclaration?.accept(this)
								o.forLoopStatement?.accept(this)
								o.ifStatement?.accept(this)
						}

						override fun visitIfStatement(o: ArcIfStatement) {
								super.visitIfStatement(o)

								o.blockBody.accept(this)
								o.elseStatement?.accept(this)
						}

						override fun visitElseStatement(o: ArcElseStatement) {
								super.visitElseStatement(o)

								o.blockBody?.accept(this)
								o.ifStatement?.accept(this)
						}

						override fun visitForLoopStatement(o: ArcForLoopStatement) {
								super.visitForLoopStatement(o)

								o.blockBody.accept(this)
						}

						override fun visitVariableDeclaration(o: ArcVariableDeclaration) {
								super.visitVariableDeclaration(o)

								when (o.expression) {
										is ArcValueExpr -> {
												val expr = o.expression as ArcValueExpr
												expr.dictionary?.accept(this)
												expr.list?.accept(this)
										}

										is ArcObjectInstantiateExpr -> {
												(o.expression as ArcObjectInstantiateExpr).accept(this)
										}
								}
						}

						override fun visitObjectInstantiateExpr(o: ArcObjectInstantiateExpr) {
								super.visitObjectInstantiateExpr(o)

								descriptors.add(FoldingDescriptor(o.node, o.textRange))
						}

						override fun visitList(o: ArcList) {
								super.visitList(o)

								descriptors.add(FoldingDescriptor(o.node, o.textRange))
						}

						override fun visitDictionary(o: ArcDictionary) {
								super.visitDictionary(o)

								descriptors.add(FoldingDescriptor(o.node, o.textRange))
						}
				})

				return descriptors.toTypedArray()
		}

		override fun getPlaceholderText(node: ASTNode): String? {
				return "{...}"
		}

		override fun isCollapsedByDefault(node: ASTNode): Boolean {
				return false
		}
}
