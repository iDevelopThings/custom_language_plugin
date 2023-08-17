package com.github.idevelopthings.customlanguageplugin.syntax

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.github.idevelopthings.customlanguageplugin.Icons
import javax.swing.Icon

class DataFusionColorSettingsPage : ColorSettingsPage {
		val DESCRIPTORS = arrayOf<AttributesDescriptor>(
				// AttributesDescriptor("Identifier", DataFusionSyntaxHighlighter.ID),
				AttributesDescriptor("Keyword", DataFusionSyntaxHighlighter.KEYWORD),
				AttributesDescriptor("String", DataFusionSyntaxHighlighter.STRING),
				AttributesDescriptor("Number", DataFusionSyntaxHighlighter.NUMBER),
				AttributesDescriptor("Line comment", DataFusionSyntaxHighlighter.LINE_COMMENT),
				AttributesDescriptor("Block comment", DataFusionSyntaxHighlighter.BLOCK_COMMENT),
				AttributesDescriptor("Type Reference", DataFusionSyntaxHighlighter.TYPE_REFERENCE),
		)

		override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? {
				return null
		}

		override fun getIcon(): Icon? {
				return Icons.LogoLight
		}

		override fun getHighlighter(): SyntaxHighlighter {
				return DataFusionSyntaxHighlighter()
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
				return "DataFusion"
		}


}
