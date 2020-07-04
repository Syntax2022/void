package rs.dusk.engine.model.engine.variable

data class StringVariable(override val id: Int, override val type: Variable.Type, override val persistent: Boolean = false, override val defaultValue: String = "") : Variable<String>