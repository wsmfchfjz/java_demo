package C3Boot.code.controller;

import C3Boot.code.beans.ChatRequest;
import C3Boot.code.beans.TransRequest;
import C3Boot.code.beans.TransResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by edliao on 2017/5/8.
 */
@Controller
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate msgTemplate;

    /**
     * 浏览器通过@MessageMapping访问控制器
     * 当控制器有信息时会发送给订阅了@SendTo的所有客户端(订阅使用js脚本)
     * <p>
     * -> 发布者使用/welcome向服务器发布信息
     * -> 订阅者先通过STOMP协议注册到相应'订阅'路径(/topic/getResponse) ,当发布者发布消息被spring处理后 ,通过SendTo返回给这些订阅者
     */
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public TransResponse say(TransRequest msg) throws Exception {
        Thread.sleep(3 * 1000);
        return new TransResponse("Welcome," + msg.getName() + "!");
    }

    /**
     * 点对点
     */
    @MessageMapping("/chat")
    public void handleChat(ChatRequest msg) throws Exception {
        msgTemplate.convertAndSendToUser(msg.getReceiver(), "/queue/notifications",
                msg.getSender() + "-send:" + msg.getMsg());
    }
}
