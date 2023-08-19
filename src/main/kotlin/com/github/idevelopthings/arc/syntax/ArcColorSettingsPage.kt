package com.github.idevelopthings.arc.syntax

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
				AttributesDescriptor("Type Reference", ArcSyntaxHighlighter.TYPE_REFERENCE),
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
func (u User) String() string {
    return u.name;
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
