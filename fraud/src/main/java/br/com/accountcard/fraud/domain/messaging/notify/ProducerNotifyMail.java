package br.com.accountcard.fraud.domain.messaging.notify;

import br.com.accountcard.domain.util.JsonUtil;
import br.com.accountcard.domain.util.MessageBuild;
import br.com.accountcard.fraud.domain.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableBinding(NotifyMail.class)
public class ProducerNotifyMail {

    private final NotifyMail notifyMail;

    public void process(final CustomerDto customerDto) {
        var json = JsonUtil.getJson(customerDto);
        var message = MessageBuild.message(json);
        notifyMail.output().send(message);
    }
}
