package com.api.payments.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum LocaleType {
    PORTUGUESE_BRAZIL("pt_BR", "Português (Brasil)"),
    SPANISH("es_ES", "Español"),
    ENGLISH("en_US", "English (United States)");

    private final String code;
    private final String displayName;

    private static final Set<String> VALID_CODES =
            Arrays.stream(values())
                    .map(LocaleType::getCode)
                    .collect(Collectors.toSet());

    LocaleType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public static boolean isValidCode(String code) {
        return code != null && VALID_CODES.contains(code);
    }
}
