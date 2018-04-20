package com.hhly.game.web;

import com.corundumstudio.socketio.SocketIOServer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhly.game.util.AES;
import com.hhly.game.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lixk185 on 2018/4/18.
 */
@Controller
@RequestMapping("/webSocket")
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private SocketIOServer socketIOServer;
    @Value("${ws.server.password}")
    private String password;

    @RequestMapping("/pushMessage")
    @ResponseBody
    public Result pushMessage(String data) {
        try {
            data = AES.decrypt(data, password);
        } catch (Exception e) {
            logger.error("数据解密失败：" + data);
            return new Result(Result.FAILED, "数据解密失败：" + e.getMessage());
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(data, JsonNode.class);
            String topic = node.get("topic").textValue();
            String message = node.get("message").textValue();
            socketIOServer.getRoomOperations(topic).sendEvent(topic, message);
        } catch (Exception e) {
            logger.error("推送异常：", e);
            return new Result(Result.ERROR, "推送异常：" + e.getMessage());
        }

        return new Result(Result.SUCCESS, "推送成功！");
    }

}
