package com.spring.websocket

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class SocketHandler : TextWebSocketHandler() {
    private val sessions = mutableListOf<WebSocketSession>()
    private val log: Logger = LoggerFactory.getLogger(SocketHandler::class.java)

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        log.info("전송 메세지: ${message.payload}, client: ${session.id}")
        sessions.forEach { it.sendMessage(message) }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        log.info("client 입장: ${session.id}")
        sessions.add(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        log.info("client 퇴장: ${session.id}")
        sessions.remove(session)
    }
}
