package com.example.subscriber

import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class EventListener {

    @RabbitListener(queues = ["sample"])
    fun receiveMessage(message: Message) {
        println(message.toString())
    }
}