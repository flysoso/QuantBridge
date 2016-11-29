package com.quant.bridge;

import com.quant.bridge.system.MessageServerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by yuer on 2016/11/29.
 */
public class ApplicationSocketStartup implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger logger = LoggerFactory.getLogger(ApplicationSocketStartup.class);

    private int port;

    public ApplicationSocketStartup(int port) {
        this.port = port;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                EventLoopGroup bossGroup = new NioEventLoopGroup(1);
                EventLoopGroup workerGroup = new NioEventLoopGroup(16);
                try {
                    logger.debug("socketserver is listening on " + port);
                    ServerBootstrap b = new ServerBootstrap();
                    b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new MessageServerChannelInitializer());
                    b.bind(port).sync().channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    logger.error("InterruptedException", e);
                } finally {
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

    }
}
