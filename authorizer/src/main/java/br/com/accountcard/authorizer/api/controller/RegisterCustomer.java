package br.com.accountcard.authorizer.api.controller;

import br.com.accountcard.domain.customer.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class RegisterCustomer {

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void create(@Valid @RequestBody final CustomerDTO customerDTO) {
    }
}
