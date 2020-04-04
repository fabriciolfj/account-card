package br.com.accountcard.domain.customer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum StatusProposal {

    PENDING("PENDING"),
    ANALYSE("ANALYSE"),
    ANALYSE_FAUDE("ANALYSE_FAUDE"),
    ANALYSE_CREDIT("ANALYSE_CREDIT");

    private final String value;

    public static Optional<StatusProposal> fromValue(final String operation) {
        return Arrays.stream(values()).filter(t -> t.value.equals(operation)).findFirst();
    }

}
