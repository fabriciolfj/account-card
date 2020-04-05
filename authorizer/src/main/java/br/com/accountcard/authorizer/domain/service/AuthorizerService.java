package br.com.accountcard.authorizer.domain.service;

import br.com.accountcard.authorizer.api.assembler.CustomerMessageAssembler;
import br.com.accountcard.authorizer.domain.messages.RegisterProducer;
import br.com.accountcard.domain.customer.Customer;
import br.com.accountcard.domain.customer.StatusProposal;
import br.com.accountcard.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizerService {

    private final RegisterProducer producerMessaging;
    private final CustomerRepository customerRepository;
    private final CustomerMessageAssembler customerMessageAssembler;

    public Optional<Customer> findByCPF(final String cpf) {
        return customerRepository.findByCpf(cpf);
    }

    public void save(final Customer customer) {
        customer.setStatusProposal(StatusProposal.ANALYSE_DOC);
        producerMessaging.process(customerMessageAssembler.toMessageObject(customerRepository.save(customer)));
    }

}
