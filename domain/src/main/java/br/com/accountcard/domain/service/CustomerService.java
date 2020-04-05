package br.com.accountcard.domain.service;

import br.com.accountcard.domain.customer.StatusProposal;
import br.com.accountcard.domain.exception.CustomerNotFound;
import br.com.accountcard.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(final String cpf, final StatusProposal statusProposal) {
        customerRepository.findByCpf(cpf)
                .map(customer -> {
                    customer.setStatusProposal(statusProposal);
                    customer = customerRepository.save(customer);
                    customerRepository.flush();
                    return customer;
                })
                .orElseThrow(() -> new CustomerNotFound("Customer not found. CPF: " + cpf));
    }
}
