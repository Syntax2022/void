package rs.dusk.network.codec.game.decode

import io.netty.channel.ChannelHandlerContext
import rs.dusk.buffer.Endian
import rs.dusk.buffer.Modifier
import rs.dusk.buffer.read.Reader
import rs.dusk.network.codec.Decoder

class InterfaceOptionDecoder(private val index: Int) : Decoder(8) {

    override fun decode(context: ChannelHandlerContext, packet: Reader) {
        handler?.interfaceOption(
            context,
            packet.readInt(Modifier.INVERSE, Endian.MIDDLE),
            packet.readShort(Modifier.ADD, Endian.LITTLE),
            packet.readShort(),
            index
        )
    }

}