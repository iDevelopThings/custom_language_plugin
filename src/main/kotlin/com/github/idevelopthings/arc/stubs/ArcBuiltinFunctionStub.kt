package com.github.idevelopthings.arc.stubs

import com.github.idevelopthings.arc.psi.ArcFunction
import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.*


interface ArcBuiltinFunctionStub
		: StubElement<ArcFunction> {
		val name: String?
}

class ArcBuiltinFunctionStubImpl(
		parent: StubElement<*>?,
		elementType: IStubElementType<out StubElement<*>, *>?
) :
		StubBase<ArcFunction>(parent, elementType),
		ArcBuiltinFunctionStub {

		constructor(
				parent: StubElement<*>?,
				elementType: IStubElementType<out StubElement<*>, *>?,
				name: String?
		) : this(parent, elementType) {
				this.name = name
		}

		override var name: String? = null

//		override fun getName(): String? = name
}

class FunctionStubIndex : StringStubIndexExtension<ArcFunction>() {
		companion object {
				val KEY: StubIndexKey<String, ArcFunction> = StubIndexKey.createIndexKey<String, ArcFunction>(
						"stubs.functions.index"
				)

				val INSTANCE = FunctionStubIndex()
		}

		fun getInstance() = INSTANCE

		override fun getKey(): StubIndexKey<String, ArcFunction> = KEY

		fun getFunctions(key: String?, project: Project?, scope: GlobalSearchScope?): Collection<ArcFunction> {
				return StubIndex.getElements(getKey(), key!!, project!!, scope, ArcFunction::class.java)
		}


}

/*
class ArcBuiltinFunctionStubElementType :
		ILightStubElementType<ArcBuiltinFunctionStub, ArcFunction>(
				"FUNCTION_STUB",
				ArcLanguage
		) {
		override fun getExternalId(): String {
				return "arc.function.stub"
		}

		override fun createStub(tree: LighterAST, node: LighterASTNode, parentStub: StubElement<*>): ArcBuiltinFunctionStub {
				val nameNode = LightTreeUtil.firstChildOfType(tree, node, ArcTypes.FUNC_ID)

				if (nameNode !is LighterASTTokenNode) {
						throw IllegalStateException("Function name node is not a token node")
				}

				return ArcBuiltinFunctionStubImpl(parentStub, null, tree.charTable.intern(nameNode.text).toString())
		}

		override fun createStub(psi: ArcFunction, parentStub: StubElement<out PsiElement>?): ArcBuiltinFunctionStub {
				return ArcBuiltinFunctionStubImpl(parentStub, null, psi.getName())
		}

		override fun createPsi(stub: ArcBuiltinFunctionStub): ArcFunction {
				return ArcFunctionImpl(stub, this)
		}

		override fun indexStub(stub: ArcBuiltinFunctionStub, sink: IndexSink) {
				sink.occurrence(FunctionStubIndex.KEY, stub.name!!)
		}

		override fun serialize(stub: ArcBuiltinFunctionStub, dataStream: StubOutputStream) {
				dataStream.writeName(stub.name)
		}

		override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): ArcBuiltinFunctionStub {
				return ArcBuiltinFunctionStubImpl(parentStub, this, dataStream.readName()?.string)
		}

}
*/
