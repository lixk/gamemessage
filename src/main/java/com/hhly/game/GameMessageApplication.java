package com.hhly.game;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.hhly.game.exception.ServerExceptionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GameMessageApplication {

    @Value("${ws.server.host}")
    private String host;
    @Value("${ws.server.port}")
    private int port;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname(host);
        config.setPort(port);
        config.setWorkerThreads(100);
        config.setExceptionListener(new ServerExceptionListener());
        SocketIOServer server = new SocketIOServer(config);
        //订阅监听器
        server.addEventListener("subscribe", String.class, (socketIOClient, topic, ackRequest) -> socketIOClient.joinRoom(topic));
        //取消订阅监听器
        server.addEventListener("unsubscribe", String.class, (socketIOClient, topic, ackRequest) -> socketIOClient.leaveRoom(topic));
        server.start();
        return server;
    }

    public static void main(String[] args) {
        SpringApplication.run(GameMessageApplication.class, args);
    }
}
