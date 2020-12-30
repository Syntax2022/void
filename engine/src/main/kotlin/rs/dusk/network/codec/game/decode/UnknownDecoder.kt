package rs.dusk.network.codec.game.decode

import io.netty.channel.ChannelHandlerContext
import rs.dusk.buffer.read.Reader
import rs.dusk.network.codec.Decoder

class UnknownDecoder : Decoder(2) {

    override fun decode(context: ChannelHandlerContext, packet: Reader) {
        handler?.unknown(
            context = context,
            value = packet.readShort()
        )
    }

}