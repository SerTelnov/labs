package parser

import java.lang.StringBuilder
import kotlin.collections.ArrayList

open class Tree(val name: String) {

    private val children = ArrayList<Tree>()

    fun addChild(child: Tree) {
        children.add(child)
    }

    override fun toString(): String {
        val builder = StringBuilder()
        buildString(builder)
        return builder.toString()
    }

    private fun buildString(builder: StringBuilder) {
        if (name[0].isLetter()) {
            if (name[0].isLowerCase()) {
                builder.append(name)
            }
        } else {
            builder.append(name)
        }

        for (child in children) {
            child.buildString(builder)
        }
    }

    fun toTree(): String {
        val builder = StringBuilder()
        buildTreeStr(0, builder)
        return builder.toString()
    }

    private fun buildTreeStr(level: Int, builder: StringBuilder) {
        for (i in 0 until level * 2) {
            builder.append(' ')
        }
        builder
                .append(name)
                .append('\n')

        if (children.isNotEmpty()) {
            for (child in children) {
                child.buildTreeStr(level + 1, builder)
            }
        } else {
            for (i in 0 until (level + 1) * 2) {
                builder.append(' ')
            }
            builder
                    .append('\u03B5')
                    .append('\n')
        }
    }
}