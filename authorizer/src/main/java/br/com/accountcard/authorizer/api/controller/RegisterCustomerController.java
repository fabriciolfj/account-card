package br.com.accountcard.authorizer.api.controller;

import br.com.accountcard.authorizer.domain.facade.create.CustomerCreate;
import br.com.accountcard.domain.customer.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class RegisterCustomerController {

    private final CustomerCreate customerCreate;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void create(@Valid @RequestBody final CustomerDTO customerDTO) {
        customerCreate.process(customerDTO);
    }
}
