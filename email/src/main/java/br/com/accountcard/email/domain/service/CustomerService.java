package br.com.accountcard.email.domain.service;

import br.com.accountcard.domain.customer.StatusProposal;
import br.com.accountcard.email.domain.exception.CustomerNotFound;
import br.com.accountcard.email.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void update(final String cpf, final StatusProposal statusProposal) {
        customerRepository.findByCpf(cpf)
                .map(customer -> {
                    customer.setStatusProposal(statusProposal);
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new CustomerNotFound("Customer not found. CPF: " + cpf));
    }
}
