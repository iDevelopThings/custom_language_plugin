package com.github.idevelopthings.arc.language.formatting

import com.github.idevelopthings.arc.ArcLanguage
import com.github.idevelopthings.arc.psi.ArcTypes.*

import com.intellij.formatting.*
import com.intellij.psi.codeStyle.CodeStyleSettings

class ArcFormattingModelBuilder : FormattingModelBuilder {
		object Util {
				@JvmStatic
				fun createSpaceBuilder(settings: CodeStyleSettings): SpacingBuilder {
						return SpacingBuilder(settings, ArcLanguage)
								.before(COMMA).spaceIf(false)
								.after(COMMA).spaceIf(true)
								.before(SEMICOLON).spaceIf(false)
								.after(SEMICOLON).spaceIf(true)
								.beforeInside(DOT, IMPORT_STATEMENT).none()
								.afterInside(DOT, IMPORT_STATEMENT).spaces(1)
								.beforeInside(LIST_ELEMENTS, LIST).spaces(1)
								.between(OBJECT_FIELD_DECLARATION, OBJECT_FIELD_DECLARATION).spacing(0, 0, 1, true, 1)
								.after(OBJECT_FIELD_DECLARATION).spacing(0, 0, 1, true, 1)
								.before(OBJECT_FIELD_DECLARATION).spacing(0, 0, 1, true, 1)
								.after(FOR_KW).spaces(1)
								.after(FOR_KW).spaces(1)
								.after(OBJECT_KW).spaces(1)
								.after(ENUM_FIELD_DECLARATION).spacing(0, 0, 1, true, 1)
//								.beforeInside(EQ, ENUM_VALUE_DECLARATION).spaces(1)
//								.afterInside(EQ, ENUM_VALUE_DECLARATION).spaces(1)
								.before(EQ).spaces(1)
								.after(EQ).spaces(1)
								.betweenInside(LCURLY, RCURLY, BLOCK_BODY).spacing(0, 0, 0, true, 1)
								.afterInside(LCURLY, BLOCK_BODY).spacing(0, 0, 1, true, 1)
								.beforeInside(RCURLY, BLOCK_BODY).spacing(0, 0, 1, true, 1)

				}
		}

		override fun createModel(formattingContext: FormattingContext): FormattingModel {
				val codeStyleSettings = formattingContext.codeStyleSettings
				val block = ArcBlock(
						formattingContext.node,
						null,
						Indent.getNoneIndent(),
						null,
						codeStyleSettings,
						Util.createSpaceBuilder(codeStyleSettings)
				)
				return FormattingModelProvider.createFormattingModelForPsiFile(formattingContext.containingFile, block, codeStyleSettings)
		}

}
