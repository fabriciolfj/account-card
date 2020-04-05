package br.com.accountcard.domain.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerMessage {

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String statusProposal;
}
