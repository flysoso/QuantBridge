package com.quant.bridge.system;

import com.quant.bridge.Application;
import com.quant.bridge.domain.Message;
import com.quant.bridge.utils.Constants;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageServerHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(MessageServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        try {
            if (Constants.COMMAND_REGISTER.equals(message.getCommand())) {
                Application.Connections.put(message.getContent(), ctx.channel());
                logger.debug("token " + message.getContent() + " ==> remote address " + ctx.channel().remoteAddress());
            } else if (!Application.clientIsOnline(ctx.channel())) {
                ctx.writeAndFlush(Constants.CONTENT_BYE);
                ctx.close();
            } else {
                if (Constants.COMMAND_PULSE.equals(message.getCommand())) {
                } else {
                    ctx.writeAndFlush(Constants.CONTENT_BYE);
                    ctx.close();
                }
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.debug("open a connection from remote address " + ctx.channel().remoteAddress());
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        for (String token : Application.Connections.keySet()) {
            if (Application.Connections.get(token) == ctx.channel()) {
                logger.debug("close a connection from remote address " + ctx.channel().remoteAddress());
                Application.Connections.remove(token);
            }
        }
        super.channelUnregistered(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.debug("exception occur , remote address " + ctx.channel().remoteAddress());
        super.exceptionCaught(ctx, cause);
    }
}
