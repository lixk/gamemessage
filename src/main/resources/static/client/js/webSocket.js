/**
 * @author lixk
 */
if (!window.WebSocket) {
    window.WebSocket = {};
}

/**
 * 订阅主题
 * @param url 服务器地址
 * @param topic 主题
 * @param callback 回调函数，示例：function(data){...}
 */
window.WebSocket.subscribe = function (url, topic, callback) {
    var socket = io.connect(url);
    socket.on('connect', function () {
        socket.emit('subscribe', topic);
    });
    socket.on(topic, callback);
}

/**
 * 取消订阅主题
 * @param url 服务器地址
 * @param topic 主题
 */
window.WebSocket.unsubscribe = function (url, topic) {
    var socket = io.connect(url);
    socket.emit('unsubscribe', topic);
}