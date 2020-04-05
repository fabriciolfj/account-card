package br.com.accountcard.documents.util;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class MessageBuild {

    public static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}
