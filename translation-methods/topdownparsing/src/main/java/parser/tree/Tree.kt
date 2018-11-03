package parser.tree

open class Tree(val name: String) {

    val children = ArrayList<Tree>()

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

    fun toTree() {
        visualize(this)
    }
}