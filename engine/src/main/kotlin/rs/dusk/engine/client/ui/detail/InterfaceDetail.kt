package rs.dusk.engine.client.ui.detail

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import rs.dusk.engine.client.ui.InterfaceException

data class InterfaceDetail(
    val id: Int,
    val name: String = "",
    val type: String = "",
    val data: InterfaceData? = null,
    val components: BiMap<String, Int> = HashBiMap.create()
) {

    constructor(id: Int, name: String = "", type: String = "", data: InterfaceData? = null, components: Map<String, Int>) : this(id, name, type, data, HashBiMap.create(components))

    class InvalidInterfaceException : InterfaceException()

    fun getIndex(resizable: Boolean): Int {
        return data?.getIndex(resizable) ?: throw InvalidInterfaceException()
    }

    fun getParent(resizable: Boolean): Int {
        return data?.getParent(resizable) ?: throw InvalidInterfaceException()
    }

    fun containsComponent(component: Int): Boolean = components.inverse().containsKey(component)

    fun getComponent(name: String): Int? = components[name]

    fun getComponentName(id: Int): String = components.inverse()[id] ?: ""

}