package com.quant.bridge.system;

import com.quant.bridge.domain.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDecoder extends DelimiterBasedFrameDecoder {
    private static Logger logger = LoggerFactory.getLogger(MessageDecoder.class);

    public MessageDecoder(int maxFrameLength, ByteBuf[] byteBufs) {
        super(maxFrameLength, byteBufs);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
        Object obj = super.decode(ctx, buffer);
        if (obj == null) {
            return null;
        }
        ByteBuf objB = (ByteBuf) obj;
        byte[] array = new byte[objB.readableBytes()];
        objB.getBytes(0, array);
        String message = new String(array);

        String[] frames = message.split("\\s+", 2);
        if (frames.length < 2) {
            ctx.close();
            return null;
        } else {
            return new Message(frames[0], frames[1]);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

}
