package br.com.accountcard.fraud.domain.service;

import br.com.accountcard.domain.customer.StatusProposal;
import br.com.accountcard.fraud.domain.dto.CustomerDto;
import br.com.accountcard.fraud.domain.messaging.credit.ProducerCreditCustomer;
import br.com.accountcard.fraud.domain.messaging.decline.ProducerDeclineDocuments;
import br.com.accountcard.fraud.domain.messaging.notify.ProducerNotifyMail;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AnalyseFraudService {

    private final ProducerDeclineDocuments producerDeclineDocuments;
    private final ProducerNotifyMail producerNotifyMail;
    private final ProducerCreditCustomer producerCreditCustomer;
    private static final Logger LOG = LoggerFactory.getLogger(AnalyseFraudService.class);

    public void process(final CustomerDto customerDto) {
        LOG.info("Starting fraud analysis process");
        customerDto.setStatusProposal(StatusProposal.ANALYSE_FRAUD.toString());
        sendNotify(customerDto);
        evaluateData(customerDto);
    }

    private void evaluateData(CustomerDto customerDto) {
        var random = new Random().nextInt();
        if(random %2 == 0){
            customerDto.setStatusProposal(StatusProposal.ANALYSE_CREDIT.toString());
            sendProcessCreditAnalyse(customerDto);
            sendNotify(customerDto);
            return;
        }

        decline(customerDto);
    }

    private void sendProcessCreditAnalyse(CustomerDto customerDto) {
        LOG.info("Starting credit proposal. CPF "  + customerDto.getCpf());
        producerCreditCustomer.process(customerDto);
    }

    private void sendNotify(CustomerDto customerDto) {
        producerNotifyMail.process(customerDto);
    }

    private void decline(final CustomerDto customerDto) {
        LOG.info("Decline proposal. CPF "  + customerDto.getCpf());
        producerDeclineDocuments.process(customerDto);
    }

}
