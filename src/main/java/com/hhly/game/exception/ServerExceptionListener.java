package com.hhly.game.exception;

import com.corundumstudio.socketio.listener.ExceptionListenerAdapter;
import com.hhly.game.web.WebSocketController;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异常处理类
 * @author lixk
 */
public class ServerExceptionListener extends ExceptionListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Override
    public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        ctx.close();
        logger.info(e.getMessage());
        return true;
    }
}