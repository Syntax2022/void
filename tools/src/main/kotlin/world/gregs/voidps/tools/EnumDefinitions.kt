package world.gregs.voidps.tools

import world.gregs.voidps.cache.Cache
import world.gregs.voidps.cache.CacheDelegate
import world.gregs.voidps.cache.definition.decoder.EnumDecoder

object EnumDefinitions {

    @JvmStatic
    fun main(args: Array<String>) {
        val cache: Cache = CacheDelegate(property("storage.cache.path"))
        val decoder = EnumDecoder().load(cache)
        for (i in decoder.indices) {
            val def = decoder.getOrNull(i) ?: continue
            println("$i $def")
        }
    }
}