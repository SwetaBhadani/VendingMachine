package exercise1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exception.InsufficentFundException;
import exception.InvalidCoinException;
import exception.InvalidItemException;
import exception.ProductUnavailableException;
import exercise1.VendingMachine;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @Before
    public void setUp() {
	vendingMachine = new VendingMachine();
    }

    @Test
    public void testVendItemAndrefund_sufficientAmountProvided() {

	vendingMachine.selectProduct(3); // Selecting product 3.Soda that costs 45c
	vendingMachine.addCoin(10);
	vendingMachine.addCoin(10);
	vendingMachine.addCoin(25); // Added exact fund that is 45c
	int refundAmount = vendingMachine.vendItemAndRefund();
	Assert.assertEquals(0, refundAmount);

    }
    
    @Test(expected = InvalidItemException.class)
    public void testSelectProduct_invalidProduct() {
	/**
	 * 1. Product selection - one integer input 2. Provide money - list of coins [1,
	 * 1, 1, 5, 10, 10] - 25 3. Return product and refund extra money
	 */

	int productCode = 4;
	vendingMachine.selectProduct(productCode);
    }

    @Test(expected = ProductUnavailableException.class)
    public void testSelectProduct_unavailableProduct() {
	int productCode = 1;

	// set quantity to 0 for productCode 1
	vendingMachine.productToQuantityMap.put(productCode, 0);

	vendingMachine.selectProduct(productCode);
    }

    @Test(expected = InvalidCoinException.class)
    public void testAddCoin_invalidCoin() {

	int coin = 2;
	vendingMachine.addCoin(coin);

    }

    @Test (expected = InsufficentFundException.class)
    public void testVendItemAndRefund_insufficientAmountProvided() {

	vendingMachine.selectProduct(1); // Selecting product 1.Coke that costs 25c
	vendingMachine.addCoin(5); // Price was 25c but Provided only 5c
	vendingMachine.vendItemAndRefund();

    }

  

    @Test()
    public void testVendItemAndrefund_extraAmountProvided() {

	vendingMachine.selectProduct(2); // Selecting product 2.Pepsi
	vendingMachine.addCoin(1);
	vendingMachine.addCoin(5);
	vendingMachine.addCoin(10); // Added 36c i.e extra 1c
	vendingMachine.addCoin(10);
	vendingMachine.addCoin(10);
	int refundAmount = vendingMachine.vendItemAndRefund();
	Assert.assertEquals(1, refundAmount);

    }

    @Test()
    public void testCancelAndRefund_someAmountProvided() {

	vendingMachine.selectProduct(3); // Selecting product 1.Coke
	vendingMachine.addCoin(10); // Added fund of 10c

	int refundAmount = vendingMachine.cancelAndRefund();
	Assert.assertEquals(10, refundAmount);
    }
    @Test()
    public void testCancelAndRefund_noAmountProvided() {

	vendingMachine.selectProduct(3); // Selecting product 1.Coke
	//vendingMachine.addCoin(10); // No amount provided

	int refundAmount = vendingMachine.cancelAndRefund();
	Assert.assertEquals(0, refundAmount);
    }

    @Test()
    public void testReset_bySupplier_wrongPasscode() {

	String code = "123ABCD"; // Incorrect pass code provided

	Assert.assertEquals(false, vendingMachine.reset(code));

    }

    @Test()
    public void testReset_bySupplier_correctPasscode() {

	String code = "123456"; // Correct pass code provided

	Assert.assertEquals(true, vendingMachine.reset(code));

    }
    
}
