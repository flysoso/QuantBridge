package com.quant.bridge.system;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class MessageServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    public void initChannel(SocketChannel ch) throws Exception {
        ByteBuf[] delimiters = {Unpooled.wrappedBuffer(new byte[]{13, 10}), Unpooled.wrappedBuffer(new byte[]{10, 13}), Unpooled.wrappedBuffer(new byte[]{10})};

//        ch.pipeline().addLast("ReadTimeoutHandler", new ReadTimeoutHandler(5, TimeUnit.MINUTES));

        ch.pipeline().addLast("MessageDecoder", new MessageDecoder(Integer.MAX_VALUE, delimiters));
        ch.pipeline().addLast("MessageEncoder", new MessageEncoder());

        ch.pipeline().addLast("MessageServerHandler", new MessageServerHandler());
    }
}