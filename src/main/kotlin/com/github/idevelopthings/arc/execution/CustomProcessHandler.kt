package com.github.idevelopthings.arc.execution

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.configurations.PtyCommandLine
import com.intellij.execution.process.AnsiEscapeDecoder
import com.intellij.execution.process.KillableProcessHandler
import com.intellij.openapi.util.Key
import com.intellij.util.io.BaseOutputReader
import com.pty4j.PtyProcess
import java.nio.charset.Charset

/**
 * Credits to IntelliJ Rust plugin for this class.
 */

/**
 * Same as [com.intellij.execution.process.KillableColoredProcessHandler], but uses [RsAnsiEscapeDecoder].
 */
class CustomProcessHandler : KillableProcessHandler, AnsiEscapeDecoder.ColoredTextAcceptor {
		private val decoder: AnsiEscapeDecoder?

		constructor(commandLine: GeneralCommandLine, processColors: Boolean = true) : super(commandLine) {
				setHasPty(commandLine is PtyCommandLine)
				setShouldDestroyProcessRecursively(!hasPty())
				decoder = if (processColors && !hasPty()) CustomAnsiEscapeDecoder() else null
		}

		constructor(
				process: Process,
				commandRepresentation: String,
				charset: Charset,
				processColors: Boolean = true
		) : super(process, commandRepresentation, charset) {
				setHasPty(process is PtyProcess)
				setShouldDestroyProcessRecursively(!hasPty())
				decoder = if (processColors && !hasPty()) CustomAnsiEscapeDecoder() else null
		}

		override fun notifyTextAvailable(text: String, outputType: Key<*>) {
				decoder?.escapeText(text, outputType, this) ?: super.notifyTextAvailable(text, outputType)
		}

		override fun coloredTextAvailable(text: String, attributes: Key<*>) {
				super.notifyTextAvailable(text, attributes)
		}

		override fun readerOptions(): BaseOutputReader.Options =
				if (hasPty()) {
						BaseOutputReader.Options.forTerminalPtyProcess()
				} else {
						BaseOutputReader.Options.forMostlySilentProcess()
				}
}
