package br.com.accountcard.email.domain.facade.create;

import br.com.accountcard.email.domain.service.SendEmailService;
import org.springframework.stereotype.Component;

@Component
public class CreateMessageMail {

    public SendEmailService.Message create(final String mail, final String body) {
        return SendEmailService.Message.builder()
                .subject("Proposal")
                .body(body)
                .recipient(body)
                .build();
    }
}
