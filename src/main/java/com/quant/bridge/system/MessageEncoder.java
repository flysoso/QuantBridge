package com.quant.bridge.system;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageEncoder extends ChannelOutboundHandlerAdapter {
    public static Logger logger = LoggerFactory.getLogger(MessageEncoder.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        String sent = msg.toString().trim() + "\n";
        ByteBuf encoded = ctx.alloc().buffer(sent.length());
        encoded.writeBytes(sent.getBytes());
        ctx.write(encoded, promise);
    }
}
