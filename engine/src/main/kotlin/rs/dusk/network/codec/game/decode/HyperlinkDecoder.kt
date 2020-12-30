package rs.dusk.network.codec.game.decode

import io.netty.channel.ChannelHandlerContext
import rs.dusk.buffer.read.Reader
import rs.dusk.network.codec.Decoder
import rs.dusk.network.packet.PacketSize.BYTE

class HyperlinkDecoder : Decoder(BYTE) {

    override fun decode(context: ChannelHandlerContext, packet: Reader) {
        handler?.hyperlink(
            context,
            packet.readString(),
            packet.readString(),
            packet.readByte()
        )
    }

}