package com.quant.bridge;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static ConcurrentHashMap<String, Channel> Connections = new ConcurrentHashMap<String, Channel>();

    public static boolean clientIsOnline(String token) {
        return Application.Connections.containsKey(token);
    }

    public static boolean clientIsOnline(Channel channel) {
        if (channel == null) return false;
        for (String token : Application.Connections.keySet()) {
            if (Application.Connections.get(token) == channel) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        logger.info("[启动服务]");
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 10086;
        SpringApplication context = new SpringApplication(Application.class);
        context.addListeners(new ApplicationSocketStartup(port));
        context.run(args);
    }
}