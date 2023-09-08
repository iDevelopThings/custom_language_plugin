package com.github.idevelopthings.arc.language.languageHost

import com.google.gson.annotations.SerializedName


data class DiagnosticPosition(
		val line: Int,
		val column: Int,
		val abs: Int,
		val width: Int,
)

data class DiagnosticMessage(
		val start: DiagnosticPosition,
		val end: DiagnosticPosition,
		val code: String? = null,
		val message: String,
		val severity: String,
		val meta: DiagnosticMeta = DiagnosticMeta(),
)

data class LintingDiagnosticsOutput(
		val errors: List<DiagnosticMessage>,
		val path: String,
) {
		companion object {
				fun mapByPath(diagnostics: List<LintingDiagnosticsOutput>): MutableMap<String, MutableList<DiagnosticMessage>> {
						val errorsMap = mutableMapOf<String, MutableList<DiagnosticMessage>>()
						for (diagnostic in diagnostics) {
								if (errorsMap.containsKey(diagnostic.path)) {
										errorsMap[diagnostic.path]?.addAll(diagnostic.errors)
								} else {
										errorsMap[diagnostic.path] = diagnostic.errors.toMutableList()
								}
						}
						return errorsMap
				}
		}
}

data class DiagnosticMeta(
		val argumentInfo: List<DiagnosticMetaArgumentInfo> = listOf(),
)

data class DiagnosticMetaArgumentInfo(
		val isDeclared: Boolean,
		val name: String,
		val type: String? = null,
)


// "meta": {
// 		"declaration_arg_suggestions": [
// 		"string"
// 		]
// },
