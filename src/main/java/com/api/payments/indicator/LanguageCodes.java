package com.api.payments.indicator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum LanguageCodes {

    PT_BR("pt_BR"),
    EN_US("en_US");

    private String value;

    @Override
    public String toString() {
        return this.value;
    }
}
