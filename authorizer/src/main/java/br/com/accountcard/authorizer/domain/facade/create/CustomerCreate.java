package br.com.accountcard.authorizer.domain.facade.create;

import br.com.accountcard.authorizer.api.assembler.CustomerDtoDesassembler;
import br.com.accountcard.authorizer.api.assembler.CustomerMessageAssembler;
import br.com.accountcard.authorizer.domain.exception.CPFInvalidException;
import br.com.accountcard.authorizer.domain.service.CustomerService;
import br.com.accountcard.domain.customer.Customer;
import br.com.accountcard.domain.customer.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.accountcard.authorizer.infra.util.ValidCPF.validateCpf;

@Component
@RequiredArgsConstructor
public class CustomerCreate {

    private final CustomerService customerService;
    private final CustomerDtoDesassembler customerDtoDesassembler;

    public void process(CustomerDTO dto) {
        Optional.of(dto)
                .filter(customerDTO -> validateCpf(customerDTO.getCpf()))
                .ifPresentOrElse(this::create,
                        () -> {
                            throw new CPFInvalidException("CPF invalid.");
                        });
    }

    private void create(final CustomerDTO dto) {
        customerService.findByCPF(dto.getCpf())
                .ifPresentOrElse(customer -> {
                    customerDtoDesassembler.copyToDomainObject(dto, customer);
                    save(customer);
                }, () -> {
                    var customer = customerDtoDesassembler.toDomainObject(dto);
                    save(customer);
                });
    }

    private void save(final Customer customer) {
        customerService.save(customer);
    }

}
