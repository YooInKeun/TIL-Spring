package com.example.publisher

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PublisherController(
    private val rabbitTemplate: RabbitTemplate
) {

    @GetMapping("/sample/queue")
    fun samplePublish(): String {
        rabbitTemplate.convertAndSend("sample.exchange", "aiden", "very tired")
        return "success"
    }
}
