package rs.dusk.cache.config.decoder

import rs.dusk.cache.Configs.CONTAINERS
import rs.dusk.cache.config.ConfigDecoder
import rs.dusk.cache.config.data.ItemContainerDefinition
import rs.dusk.core.io.read.Reader

/**
 * @author Greg Hibberd <greg@greghibberd.com>
 * @since April 08, 2020
 */
class ItemContainerDecoder : ConfigDecoder<ItemContainerDefinition>(CONTAINERS) {

    override fun create() = ItemContainerDefinition()

    override fun ItemContainerDefinition.read(opcode: Int, buffer: Reader) {
        when (opcode) {
            2 -> length = buffer.readUnsignedShort()
            4 -> {
                val size = buffer.readUnsignedByte()
                ids = IntArray(size)
                amounts = IntArray(size)
                repeat(size) { i ->
                    ids!![i] = buffer.readUnsignedShort()
                    amounts!![i] = buffer.readUnsignedShort()
                }
            }
        }
    }
}