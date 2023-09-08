package com.github.idevelopthings.arc.completion.providers

import com.github.idevelopthings.arc.ArcFileType
import com.github.idevelopthings.arc.Icons
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.vfs.isFile
import com.intellij.util.ProcessingContext
import kotlin.io.path.Path
import kotlin.io.path.exists

class ImportStatementPathCompletionProvider : CompletionProvider<CompletionParameters?>() {

		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, originalResultSet: CompletionResultSet) {
				val offset = parameters.offset
				val charSeq = parameters.editor.document.immutableCharSequence
				val currentElement = parameters.originalFile.findElementAt(parameters.position.textOffset)
				if (currentElement == null) {
						thisLogger().warn("currentElement is null")
						return
				}

				val (currentInput, didMatch) = getCurrentInputStr(charSeq, offset)
				if (!didMatch) {
						thisLogger().warn("could not find correct starting pos")
						return
				}

				val currentFile = parameters.originalFile.virtualFile
				val currentDir = currentFile.path.split('/').dropLast(1).joinToString("/")

				val dir = Path(currentDir).resolve(currentInput.toString()).normalize()
				if (!dir.exists()) {
						thisLogger().warn("dir does not exist")
						return
				}


				val fileIndex = ProjectFileIndex.getInstance(parameters.originalFile.project)
				val currentVfsDir = VirtualFileManager.getInstance().findFileByUrl(dir.toUri().toString())

				val items = mutableListOf<LookupElementBuilder>()
				fileIndex.iterateContentUnderDirectory(currentVfsDir!!, {
						var str = currentInput.toString()
						if (!str.endsWith("/") && str != "") {
								str += "/"
						}
						str += it.name

						var b = LookupElementBuilder.create(str)
						b = b.withPresentableText(it.presentableName)

						if (it.isFile) {
								b = b.withIcon(Icons.LogoLight)
						} else if (it.isDirectory) {
								b = b.withIcon(AllIcons.Nodes.Folder)
						}

						// b = b.withInsertHandler { context, item ->
						// 		thisLogger().warn("..")
						// }


						items.add(b)

						true
				}, {
						if (fileIndex.isInContent(it) && it != currentVfsDir) {
								return@iterateContentUnderDirectory it.isDirectory || it.isFile && it.extension == ArcFileType.INSTANCE.defaultExtension
						}

						return@iterateContentUnderDirectory false
				})

				originalResultSet.addAllElements(items)

				thisLogger().warn("Current dir path: $dir")

		}

		private fun getCurrentInputStr(charSeq: CharSequence, offset: Int): Pair<CharSequence, Boolean> {

				// We need to look back in our charseq to find either `"` or `import`... if we hit `import` it's invalid
				// if we hit `"` we need to find the next `"` or `\n` and then we can use that as our current input

				var currOffset = offset - 1

				do {
						if (currOffset <= 0)
								return Pair("", false)

						val char = charSeq[currOffset]

						// A lot of the time our starting char is '\n'
						// if (currOffset == offset && (char == '\n')) {
						// 		currOffset--
						// 		continue
						// }

						if (char == '"') {
								break
						}

						if (char == '\n') {
								return Pair("", false)
						}

						currOffset--

				} while (currOffset > 0)

				val currentInput = charSeq.subSequence(currOffset + 1, offset)

				thisLogger().warn("Current input: $currentInput")

				return Pair(currentInput, true)
		}

}
