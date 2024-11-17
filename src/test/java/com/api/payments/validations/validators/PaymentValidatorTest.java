package com.api.payments.validations.validators;

import com.api.payments.dto.TransactionDto;
import com.api.payments.entity.Payments;
import com.api.payments.mocks.PaymentsMocked;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static com.api.payments.validations.messages.PaymentValidatorMessages.currencyInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.debtorFullNameInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.debtorLastNameInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.descriptionInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.expirationDateInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.messageTextInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.paymentDateInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.paymentMethodInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.paymentStatusInvalid;
import static com.api.payments.validations.messages.PaymentValidatorMessages.userIdInvalid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PaymentValidatorTest {

    private TransactionDto paymentsData;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Payments payment = new PaymentsMocked().returnPaymentMocked();

        paymentsData = new ModelMapper().map(payment, TransactionDto.class);
    }

    @Test
    @DisplayName("Should validate payment method")
    public void testPaymentMethod() {
        List<String> invalidPaymentMethods = Arrays.asList(null, " ", "invalid");

        for (String invalidMethod : invalidPaymentMethods) {
            Exception e = assertThrows(Exception.class, () -> {
                paymentsData.setPaymentMethod(invalidMethod);
                PaymentValidator.paymentValidator(paymentsData);
            });
            assertEquals(paymentMethodInvalid, e.getMessage());
        }
    }

    @Test
    @DisplayName("Should validate debtor full name")
    public void testDebtorFullName() {
        paymentsData.setDebtorFullName(null);

        Exception e = assertThrows(Exception.class, () -> PaymentValidator.paymentValidator(paymentsData));
        assertEquals(debtorFullNameInvalid, e.getMessage());
    }

    @Test
    @DisplayName("Should validate debtor last name")
    public void testDebtorLastName() {
        paymentsData.setDebtorLastName(null);

        Exception e = assertThrows(Exception.class, () -> PaymentValidator.paymentValidator(paymentsData));
        assertEquals(debtorLastNameInvalid, e.getMessage());
    }

    @Test
    @DisplayName("Should validate user")
    public void testUser() {
        paymentsData.setUser(null);

        Exception e = assertThrows(Exception.class, () -> PaymentValidator.paymentValidator(paymentsData));
        assertEquals(userIdInvalid, e.getMessage());
    }

    @Test
    @DisplayName("Should validate user id")
    public void testUserId() {
        paymentsData.getUser().setId(null);

        Exception e = assertThrows(Exception.class, () -> PaymentValidator.paymentValidator(paymentsData));
        assertEquals(userIdInvalid, e.getMessage());
    }

    @Test
    @DisplayName("Should validate payment status")
    public void testPaymentStatus() {
        paymentsData.setPaymentStatus(null);

        Exception e = assertThrows(Exception.class, () -> PaymentValidator.paymentValidator(paymentsData));
        assertEquals(paymentStatusInvalid, e.getMessage());
    }

    @Test
    @DisplayName("Should validate currency")
    public void testCurrency() {
        paymentsData.setCurrency(null);

        Exception e = assertThrows(Exception.class, () -> PaymentValidator.paymentValidator(paymentsData));
        assertEquals(currencyInvalid, e.getMessage());
    }

    @Test
    @DisplayName("Should validate payment date")
    public void testPaymentDate() {
        paymentsData.setPaymentDate(null);

        Exception e = assertThrows(Exception.class, () -> PaymentValidator.paymentValidator(paymentsData));
        assertEquals(paymentDateInvalid, e.getMessage());
    }

    @Test
    @DisplayName("Should validate expiration date")
    public void testExpirationDate() {
        paymentsData.setExpirationDate(null);

        Exception e = assertThrows(Exception.class, () -> PaymentValidator.paymentValidator(paymentsData));
        assertEquals(expirationDateInvalid, e.getMessage());
    }

    @Test
    @DisplayName("Should validate description")
    public void testDescription() {
        paymentsData.setDescription(null);

        Exception e = assertThrows(Exception.class, () -> PaymentValidator.paymentValidator(paymentsData));
        assertEquals(descriptionInvalid, e.getMessage());
    }

    @Test
    @DisplayName("Should validate message text")
    public void testMessageText() {
        paymentsData.setMessageText(null);

        Exception e = assertThrows(Exception.class, () -> PaymentValidator.paymentValidator(paymentsData));
        assertEquals(messageTextInvalid, e.getMessage());
    }
}
