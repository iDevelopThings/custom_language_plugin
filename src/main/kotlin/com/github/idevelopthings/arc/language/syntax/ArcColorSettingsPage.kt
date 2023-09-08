package com.github.idevelopthings.arc.language.syntax

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.github.idevelopthings.arc.Icons
import javax.swing.Icon

class ArcColorSettingsPage : ColorSettingsPage {
		val DESCRIPTORS = arrayOf<AttributesDescriptor>(
				// AttributesDescriptor("Identifier", ArcSyntaxHighlighter.ID),
				AttributesDescriptor("Keyword", ArcSyntaxHighlighter.KEYWORD),
				AttributesDescriptor("String", ArcSyntaxHighlighter.STRING),
				AttributesDescriptor("Number", ArcSyntaxHighlighter.NUMBER),
				AttributesDescriptor("Line comment", ArcSyntaxHighlighter.LINE_COMMENT),
				AttributesDescriptor("Block comment", ArcSyntaxHighlighter.BLOCK_COMMENT),
				AttributesDescriptor("Type reference", ArcSyntaxHighlighter.TYPE_REFERENCE),
				AttributesDescriptor("Function call", ArcSyntaxHighlighter.FUNCTION_CALL),
				AttributesDescriptor("Function declaration", ArcSyntaxHighlighter.FUNCTION_DECLARATION),
				AttributesDescriptor("Receiver local ref", ArcSyntaxHighlighter.RECEIVER_LOCAL_REF),
				AttributesDescriptor("Local variable", ArcSyntaxHighlighter.LOCAL_VARIABLE),
				AttributesDescriptor("Static runtime call", ArcSyntaxHighlighter.STD_RUNTIME_LIB_NS),
		)

		override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? {
				return null
		}

		override fun getIcon(): Icon? {
				return Icons.LogoLight
		}

		override fun getHighlighter(): SyntaxHighlighter {
				return ArcSyntaxHighlighter()
		}

		override fun getDemoText(): String {
				return """
/* block comment */
object User {
   name string
	 age int
}

// instance method
func (u User) String() string {
    return u.name;
}

// static method
func (User) SayHello() {
		fmt::printf("Hello, World!\n");
}

func main() {
	var user = User{name: "John"};
	john.String()
	// fmt::printf("Name: %s\n", john.String());
}
"""
		}

		override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
				return DESCRIPTORS
		}

		override fun getColorDescriptors(): Array<ColorDescriptor> {
				return ColorDescriptor.EMPTY_ARRAY
		}

		override fun getDisplayName(): String {
				return "Arc"
		}


}
