package C3Boot.code.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configurable
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    /**
     * 开启STOMP协议 ,注册节点映射的url ,指定使用SOCKJS协议
     * -> 前端使用SockJS+STOMP协议 ,连接指定的endpoint ,形成webSocket
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //广播式
        stompEndpointRegistry.addEndpoint("/endpointEddy").withSockJS();
        //点对点
        stompEndpointRegistry.addEndpoint("/endpointP2P").withSockJS();
    }

    /**
     * 配置消息代理 ,用于广播式消息 ,配置路径前缀
     * -> 服务器需要发送消息时 ,会给订阅了/topic/*路径的所有人发送消息
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //对应上面两个endPoint的消息代理
        registry.enableSimpleBroker("/topic", "/queue");
    }


}
