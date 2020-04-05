package br.com.accountcard.documents.domain.service;

import br.com.accountcard.documents.domain.dto.CustomerDto;
import br.com.accountcard.documents.domain.messaging.decline.ProducerDeclineDocuments;
import br.com.accountcard.documents.domain.messaging.fraud.ProducerAnalyzeFraud;
import br.com.accountcard.documents.domain.messaging.notify.ProducerNotifyMail;
import br.com.accountcard.domain.customer.StatusProposal;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AnalyseDocumentsService {

    private final ProducerDeclineDocuments producerDeclineDocuments;
    private final ProducerNotifyMail producerNotifyMail;
    private final ProducerAnalyzeFraud producerAnalyzeFraud;
    private static final Logger LOG = LoggerFactory.getLogger(AnalyseDocumentsService.class);

    public void process(final CustomerDto customerDto) {
        LOG.info("Starting document analysis process.");
        customerDto.setStatusProposal(StatusProposal.ANALYSE_DOC.toString());
        sendNotify(customerDto);
        evaluateData(customerDto);
    }

    private void sendNotify(CustomerDto customerDto) {
        producerNotifyMail.process(customerDto);
    }

    private void evaluateData(CustomerDto customerDto) {
        var random = new Random().nextInt();
        if(random %2 == 0){
            customerDto.setStatusProposal(StatusProposal.ANALYSE_FRAUD.toString());
            sendProcessFraudAnalyse(customerDto);
            sendNotify(customerDto);
            return;
        }

        decline(customerDto);
    }

    private void sendProcessFraudAnalyse(CustomerDto customerDto) {
        producerAnalyzeFraud.process(customerDto);
    }

    private void decline(final CustomerDto customerDto) {
        producerDeclineDocuments.process(customerDto);
    }

}
