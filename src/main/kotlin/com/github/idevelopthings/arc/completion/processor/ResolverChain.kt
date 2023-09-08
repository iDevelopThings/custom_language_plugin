@file:OptIn(ExperimentalTime::class, ExperimentalTime::class, ExperimentalTime::class)

package com.github.idevelopthings.arc.completion.processor

import com.github.idevelopthings.arc.completion.collector.TypeCollector
import com.github.idevelopthings.arc.completion.data.TypeInfo
import com.github.idevelopthings.arc.completion.resolution.TypeResolverBase
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.psi.PsiElement
import kotlin.system.measureNanoTime
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class ResolverChainContext {

		var shouldStopLoading: Boolean = false

		var didCallOnEntry: Boolean = false

		var resolvedTypes: HashMap<PsiElement, TypeInfo> = hashMapOf()

		var onEntryHook: ((TypeInfo) -> Unit)? = null
		var onShouldContinueHook: ((TypeInfo) -> Boolean)? = null
		var onTypeCreatedHook: ((TypeInfo) -> Unit)? = null

		var deepLoad = false

		fun isLoaded(e: PsiElement): Boolean {
				return resolvedTypes.containsKey(e)
		}

		fun markLoaded(e: PsiElement, type: TypeInfo) {
				resolvedTypes[e] = type
		}

		private fun runProcessorHook(type: TypeInfo): Boolean {
				return onShouldContinueHook?.invoke(type) ?: true
		}

		fun beginLoad(
				e: PsiElement,
				loadFunc: () -> TypeInfo
		): TypeInfo {
				if (isLoaded(e)) {
						if (!runProcessorHook(resolvedTypes[e]!!))
								this.shouldStopLoading = true

						return resolvedTypes[e]!!
				}

				val type = loadFunc()

				onTypeCreatedHook?.invoke(type)

				markLoaded(e, type)

				if (!runProcessorHook(resolvedTypes[e]!!))
						this.shouldStopLoading = true

				return type
		}

		fun loadNested(loadFunc: () -> Unit) {
				if (!deepLoad) return
				if (shouldStopLoading) return

				loadFunc()
		}

}


class ResolverChain(
		private var element: PsiElement
) {

		private val resolvers = mutableListOf<TypeResolverBase>()
		private val collectors = mutableListOf<TypeCollector>()

		private val filterFirsts = mutableListOf<(TypeInfo) -> Boolean>()
		private val filters = mutableListOf<(TypeInfo) -> Boolean>()

		val ctx = ResolverChainContext()
		val results = mutableListOf<TypeInfo>()

		fun shouldContinue(hook: (TypeInfo) -> Boolean) {
				ctx.onShouldContinueHook = hook
		}

		fun onTypeCreated(hook: (TypeInfo) -> Unit) {
				ctx.onTypeCreatedHook = hook
		}

		fun withDeepLoading(value: Boolean = true) {
				ctx.deepLoad = value
		}

		fun resolver(resolver: TypeResolverBase) {
				resolvers.add(resolver)
		}

		fun collector(collector: TypeCollector) {
				collectors.add(collector)
		}

		fun filterFirst(predicate: (TypeInfo) -> Boolean) {
				filterFirsts.add(predicate)
		}

		// This is called once when we hit our first type
		fun onEntry(func: (TypeInfo) -> Unit) {
				ctx.onEntryHook = func
		}

		fun filter(predicate: (TypeInfo) -> Boolean) {
				filters.add(predicate)
		}

		fun setElement(e: PsiElement) {
				element = e
		}

		private fun doResolve() {
				collectors.forEach {
						it.preCollect(element)
				}

				collectors.forEach {
						it.collect(element)
				}

				val resolvables = collectors.flatMap { it.getResults() }

				fun addResult(type: TypeInfo) {
						if (ctx.deepLoad) {
								type.loadDeeper(ctx)
						}

						if (!ctx.didCallOnEntry) {
								ctx.onEntryHook?.invoke(type)
								ctx.didCallOnEntry = true
						}

						results.add(type)
				}

				for (it in resolvables) {

						var type: TypeInfo? = null
						for (resolver in resolvers) {
								if (!resolver.canAccept(it)) continue
								type = resolver.resolveType(it) ?: continue
						}

						if (type == null) continue

						// Filter first essentially needs to be run, and if it returns
						// true, then we stop iterating and return our result
						// Mainly to stop iterating way too deep

						// thisLogger().warn("Resolved Type: $type")

						if (filterFirsts.isNotEmpty()) {
								if (filterFirsts.all { predicate -> predicate(type) }) {
										// thisLogger().warn("Filter first returned true for: $type")
										addResult(type)
										break
								}
								continue
						}

						// Essentially just a normal filter, but we don't stop iterating
						// return true = add the result to the list
						if (filters.isNotEmpty()) {
								if (filters.all { predicate -> predicate(type) }) {
										addResult(type)
								}
								continue
						}

						addResult(type)

				}
		}

		fun resolve(): ResolverChain {
				// doResolve()

				measureTime {
						doResolve()
				}.let {
						thisLogger().warn("Resolved in: $it")
				}

				return this
		}

		fun first(): TypeInfo? {
				return results.firstOrNull()
		}

}
