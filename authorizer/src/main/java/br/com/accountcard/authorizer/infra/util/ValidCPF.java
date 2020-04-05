package br.com.accountcard.authorizer.infra.util;

import br.com.caelum.stella.validation.CPFValidator;

public class ValidCPF {

    public static boolean validateCpf(final String cpf) {
        final CPFValidator validator = new CPFValidator();
        try {
            validator.assertValid(cpf);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
