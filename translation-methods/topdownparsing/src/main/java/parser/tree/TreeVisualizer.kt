package parser.tree

import java.io.BufferedWriter
import java.nio.file.Files
import java.nio.file.Paths

private val LINE_SEPARATOR = System.lineSeparator()
private lateinit var writer: BufferedWriter
private var leafCounter = 0

fun visualize(root: Tree) {
    leafCounter = 0
    val treeOutputFile = "tree.dot"

    writer = Files.newBufferedWriter(Paths.get(treeOutputFile))
    writer.write("digraph tree{$LINE_SEPARATOR")
    writer.write(" node [shape=plaintext]$LINE_SEPARATOR")

    recursive(root, root.name, toVertexName(root.name))
    writer.write("}")
    writer.close()
}

private fun toVertexName(name: String) = "\"{$name}\""

private fun recursive(curr: Tree, name: String, vertexName: String) {
    if (curr.children.isNotEmpty()) {
        for (child in curr.children) {
            if (!child.name[0].isUpperCase()) {
                writer.write("$vertexName -> ${toVertexName("leaf#${++leafCounter}: ${child.name}")}$LINE_SEPARATOR")
            } else {
                val childName = "$name, ${child.name}"
                val vertexChildName = toVertexName(childName)
                writer.write("$vertexName -> $vertexChildName$LINE_SEPARATOR")
                recursive(child, childName, vertexChildName)
            }
        }
    } else {
        writer.write("$vertexName -> ${toVertexName("leaf#${++leafCounter}: \u03B5")}$LINE_SEPARATOR")
    }
}
