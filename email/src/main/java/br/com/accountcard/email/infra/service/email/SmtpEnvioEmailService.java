package br.com.accountcard.email.infra.service.email;

import br.com.accountcard.email.core.email.EmailProperties;
import br.com.accountcard.email.domain.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class SmtpEnvioEmailService implements SendEmailService {

    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;
    private static final Logger LOG = LoggerFactory.getLogger(SmtpEnvioEmailService.class);

    @Override
    public void send(Message message) {
        LOG.info("Send email: " + message.getRecipients());
        /*try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailProperties.getRemetente());
            helper.setTo(message.getRecipients().toArray(new String[0]));
            helper.setSubject(message.getSubject());
            helper.setText(message.getBody(), true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar e-mail", e);
        }*/
    }
}
