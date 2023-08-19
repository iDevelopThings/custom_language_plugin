package com.github.idevelopthings.arc.language.formatting

import com.github.idevelopthings.arc.ArcLanguage
import com.intellij.formatting.*
import com.intellij.psi.codeStyle.CodeStyleSettings


class ArcFormattingModelBuilder : FormattingModelBuilder {
		object Util {
				@JvmStatic
				fun createSpaceBuilder(settings: CodeStyleSettings): SpacingBuilder {
						return SpacingBuilder(settings, ArcLanguage.INSTANCE)
//								.around(ArcTypes.EQ)
//								.spaceIf(settings.getCommonSettings(ArcLanguage.INSTANCE.id).SPACE_AROUND_ASSIGNMENT_OPERATORS)
//								.before(ArcTypes.EXPRESSION)
//								.none()
//								.around(ArcTypes.TYPE).spaces(1)
//								.before(ArcTypes.FUNC_DECLARATION).lineBreakInCode()

				}
		}

		override fun createModel(formattingContext: FormattingContext): FormattingModel {
				val codeStyleSettings = formattingContext.codeStyleSettings
				return FormattingModelProvider
						.createFormattingModelForPsiFile(
								formattingContext.containingFile,
								ArcBlock(
										formattingContext.node,
										Wrap.createWrap(WrapType.NONE, false),
										Alignment.createAlignment(),
										Util.createSpaceBuilder(codeStyleSettings)
								),
								codeStyleSettings
						)
		}

}
