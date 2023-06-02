package world.gregs.voidps.engine.data.definition.extra

import world.gregs.voidps.engine.data.FileStorage
import world.gregs.voidps.engine.data.definition.DefinitionsDecoder
import world.gregs.voidps.engine.data.definition.config.JingleDefinition
import world.gregs.voidps.engine.get
import world.gregs.voidps.engine.getProperty
import world.gregs.voidps.engine.timedLoad

class JingleDefinitions : DefinitionsDecoder<JingleDefinition> {

    override lateinit var definitions: Array<JingleDefinition>
    override lateinit var ids: Map<String, Int>

    fun load(storage: FileStorage = get(), path: String = getProperty("jingleDefinitionsPath")): JingleDefinitions {
        timedLoad("jingle definition") {
            val data = storage.loadMapIds(path)
            definitions = Array(data.maxOf { it.value["id"] as Int }) { JingleDefinition(id = it, stringId = it.toString()) }
            decode(data)
        }
        return this
    }

    override fun empty() = JingleDefinition.EMPTY

}