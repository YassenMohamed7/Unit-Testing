package org.example.store;

import org.example.account.AccountManager;
import org.example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoreTest {

    Store store;

    Product product = new Product();
    Customer customer = new Customer();

    static class AlwaysReturnSuccessAccountManager implements AccountManager {

        @Override
        public void deposit(Customer customer, int amount) {

        }

        @Override
        public String withdraw(Customer customer, int amount) {
            return "success";
        }
    }


    static class AlwaysReturnFailDueToMaximumCreditExceededOrInsufficientAccountBalanceAccountManager implements AccountManager {

        @Override
        public void deposit(Customer customer, int amount) {

        }

        @Override
        public String withdraw(Customer customer, int amount) {
            return "fail";
        }
    }



    @Test void givenProductQuantity8_whenBuyingThenGetTheQuantity7AfterBuyingSuccessfully() {
        // Arrange
        product.setQuantity(8);
        store = new StoreImpl(new AlwaysReturnSuccessAccountManager());

        // Act
        store.buy(product, customer);

        // Assert
        Assertions.assertEquals(7, product.getQuantity());
    }
    @Test void givenProductQuantity0_whenBuyingThenGetRuntimeExceptionOfMessageProductOutOfStock() {
        // Arrange
        product.setQuantity(0);
        store = new StoreImpl(new AlwaysReturnSuccessAccountManager());

        // Assert
        Assertions.assertEquals(0, product.getQuantity());
        Assertions.assertThrows(RuntimeException.class, () -> store.buy(product, customer));
    }

    @Test void givenProductQuantity7_whenBuyingThenGetRuntimeExceptionOfMessageInsufficientAccountBalance() {
        // Arrange
        product.setQuantity(7);
        store = new StoreImpl(new AlwaysReturnFailDueToMaximumCreditExceededOrInsufficientAccountBalanceAccountManager());

        // Assert
        Assertions.assertEquals(7, product.getQuantity());
        Assertions.assertThrows(RuntimeException.class, () -> store.buy(product, customer));
    }

    @Test void givenProductQuantity7_whenBuyingThenGetRuntimeExceptionOfMessageOfMaximumCreditExceeded() {
        // Arrange
        product.setQuantity(7);
        store = new StoreImpl(new AlwaysReturnFailDueToMaximumCreditExceededOrInsufficientAccountBalanceAccountManager());

        // Assert
        Assertions.assertEquals(7, product.getQuantity());
        Assertions.assertThrows(RuntimeException.class, () -> store.buy(product, customer));
    }


}
