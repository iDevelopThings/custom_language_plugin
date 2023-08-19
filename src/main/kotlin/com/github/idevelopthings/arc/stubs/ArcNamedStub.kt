package com.github.idevelopthings.arc.stubs

import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.NamedStubBase
import com.intellij.psi.stubs.StubElement
import com.github.idevelopthings.arc.psi.ArcNamedElement

abstract class ArcNamedStub<T : ArcNamedElement>(
		parent: StubElement<*>?,
		elementType: IStubElementType<*, *>,
		name: String?
) : NamedStubBase<T>(parent, elementType, name) {
		override fun toString(): String {
				val name = name
				val str = super.toString()
				return if (StringUtil.isEmpty(name)) str else "$str: $name"
		}
}
