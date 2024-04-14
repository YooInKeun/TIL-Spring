package rabbitmq

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.beans.factory.annotation.Value

@Configuration
class RabbitMQConfig {

    @Value("\${spring.rabbitmq.host}")
    private lateinit var rabbitMQHost: String

    @Value("\${spring.rabbitmq.port}")
    private lateinit var rabbitMQPort: String

    @Value("\${spring.rabbitmq.username}")
    private lateinit var rabbitMQUsername: String

    @Value("\${spring.rabbitmq.password}")
    private lateinit var rabbitMQPassword: String

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(EXCHANGE_NAME)
    }

    @Bean
    fun queue(): Queue {
        return Queue(QUEUE_NAME)
    }

    @Bean
    fun binding(queue: Queue?, exchange: TopicExchange?): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY)
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory, messageConverter: MessageConverter): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = Jackson2JsonMessageConverter()
        return rabbitTemplate
    }

    @Bean
    fun connectionFactory(): ConnectionFactory? {
        val connectionFactory = CachingConnectionFactory()
        connectionFactory.host = rabbitMQHost
        connectionFactory.port = rabbitMQPort.toInt()
        connectionFactory.username = rabbitMQUsername
        connectionFactory.setPassword(rabbitMQPassword)
        return connectionFactory
    }

    @Bean
    fun messageConverter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

    companion object {
        private const val EXCHANGE_NAME = "sample.exchange"
        private const val QUEUE_NAME = "sample"
        private const val ROUTING_KEY = "aiden"
    }
}