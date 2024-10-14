package org.example.store;

import org.example.account.AccountManager;
import org.example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class StoreMockitoTest {

    Store store;
    Product product = new Product();
    Customer customer = new Customer();


    @Test
    public void SuccessfulBuying()
    {
        product.setQuantity(7);
        AccountManager accountManager = Mockito.mock(AccountManager.class);
        Mockito.when(accountManager.withdraw(any(), anyInt())).thenReturn("success");
        store = new StoreImpl(accountManager);

        store.buy(product, customer);
        Assertions.assertEquals(6, product.getQuantity());
    }

    @Test
    public void zeroQuantity()
    {
        product.setQuantity(0);
        AccountManager accountManager = Mockito.mock(AccountManager.class);
        store = new StoreImpl(accountManager);
        Assertions.assertThrows(RuntimeException.class, () -> store.buy(product, customer));
                }


    @Test
    public void FailureBuyingDueToPaymentErrors()
    {
        product.setQuantity(7);
        AccountManager accountManager = Mockito.mock(AccountManager.class);
        Mockito.when(accountManager.withdraw(any(), anyInt())).thenReturn("Insufficient account balance or maximum credit exceeded");
        store = new StoreImpl(accountManager);

        Assertions.assertThrows(RuntimeException.class, () -> store.buy(product, customer));
    }





}
