# gamemessage
消息推送服务器
本项目用于websocket协议数据推送，基于spring boot和netty-socketio开发，可简化websocket推送系统开发难度。
### 工作原理
推送消息方将推送的主题和消息数据作为参数，调用推送服务器的推送接口（http接口）。然后推送服务器将主题和消息通过websocket协议推送到订阅了该主题的前端
项目中有服务器和前端的示例代码。
