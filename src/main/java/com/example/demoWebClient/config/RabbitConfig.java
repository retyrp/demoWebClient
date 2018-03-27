package com.example.demoWebClient.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    @Bean
    public Exchange helloExchange(){return new Exchange() {
        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getType() {
            return null;
        }

        @Override
        public boolean isDurable() {
            return false;
        }

        @Override
        public boolean isAutoDelete() {
            return false;
        }

        @Override
        public Map<String, Object> getArguments() {
            return null;
        }

        @Override
        public boolean isDelayed() {
            return false;
        }

        @Override
        public boolean isInternal() {
            return false;
        }

        @Override
        public boolean shouldDeclare() {
            return false;
        }

        @Override
        public Collection<?> getDeclaringAdmins() {
            return null;
        }

        @Override
        public boolean isIgnoreDeclarationExceptions() {
            return false;
        }
    };}
}
