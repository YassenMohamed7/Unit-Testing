package org.example.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountManagerTest {

    Customer customer = new Customer();
    AccountManager accountManager = new AccountManagerImpl();

    @Test void givenAmountBelowMaxCreditForNormalCustomerWhenWithdrawThenSubtractFromBalance() {
        // Arrange
        customer.setBalance(100);

        // Act
        String result = accountManager.withdraw(customer, 80);

        // Assert
        int expectedBalance = customer.getBalance();
        Assertions.assertEquals(20, expectedBalance);
        Assertions.assertEquals("success", result);
    }
    @Test
    public void givenAmountMoreThanMaxCreditForNotCreditAllowedCustomerWhenWithdrawingThenGetInsufficientAccountBalance() {
        customer.setBalance(100);
        String result = accountManager.withdraw(customer, 150);
        int expectedBalance = customer.getBalance();

        Assertions.assertNotEquals(-50, expectedBalance);
        Assertions.assertEquals("insufficient account balance", result);
    }
    @Test
    public void givenAmountMoreThanTheCustomerBalanceButLessThanMaxCreditForCreditAllowedCustomerWhenWithdrawingThenGetGetSuccess() {
        customer.setBalance(100);
        customer.setCreditAllowed(true);
        String result = accountManager.withdraw(customer, 150);
        int expectedBalance = customer.getBalance();

        Assertions.assertEquals(-50, expectedBalance);
        Assertions.assertEquals("success", result);
    }
    @Test
    public void givenAmountMoreThanTheCustomerBalanceAndMoreThanMaxCreditForCreditAllowedCustomerWhenWithdrawingThenGetGetMaximumLimitExceeded() {
        customer.setBalance(100);
        customer.setCreditAllowed(true);
        String result = accountManager.withdraw(customer, 1150);
        int expectedBalance = customer.getBalance();

        Assertions.assertEquals(100, expectedBalance);
        Assertions.assertEquals("maximum credit exceeded", result);
    }


    @Test
    public void givenAmountMoreThanCustomerBalanceButLessThanMaxCreditForCreditAllowedAndNotVipCustomer_whenWithdrawing_thenGetSuccess() {
        customer.setBalance(100);
        customer.setCreditAllowed(true);
        String result = accountManager.withdraw(customer, 150);
        int expectedBalance = customer.getBalance();

        Assertions.assertEquals("success", result);
        Assertions.assertEquals(-50, expectedBalance);
    }

    @Test
    public void givenAmountMoreThanCustomerBalanceForCreditAllowedAndVipCustomer_whenWithdrawing_thenGetSuccess() {
        customer.setBalance(100);
        customer.setCreditAllowed(true);
        customer.setVip(true);
        String result = accountManager.withdraw(customer, 150);
        int expectedBalance = customer.getBalance();

        Assertions.assertEquals("success", result);
        Assertions.assertEquals(-50, expectedBalance);
    }





}
