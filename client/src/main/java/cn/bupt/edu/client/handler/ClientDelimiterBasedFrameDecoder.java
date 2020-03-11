package cn.bupt.edu.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class ClientDelimiterBasedFrameDecoder extends DelimiterBasedFrameDecoder {
    public ClientDelimiterBasedFrameDecoder(int maxFrameLength, ByteBuf delimiter) {
        super(maxFrameLength, delimiter);
        ByteBuf ddelimiter = Unpooled.copiedBuffer("$".getBytes());
        System.out.println(ddelimiter);
    }

}
