package exercise1;

import java.util.HashMap;
import java.util.Map;

import exception.InsufficentFundException;
import exception.InvalidCoinException;
import exception.InvalidItemException;
import exception.ProductUnavailableException;

/**
 * This is a class which supports different operations of vending machine
 * 
 * Supported operations:
 * 1. Products available with Code and price : Coke(1)-25c  Pepsi(@)-35 Soda(3)-45
 * 2. Accepts coins 1c, 5c, 10c, 25c only
 * 3. Returns product and remaining change if any
 * 4. Refunds on cancelling the request
 * 5. Allows reset operation for vending machine supplier.
 * 
 * @author swetabhadani
 *
 */
public class VendingMachine {

    static String PASSCODE = "123456";
    Map<Integer, Integer> productToQuantityMap;

    int productCode = 0;
    int productAmount = 0;
    int paidAmount = 0;

    public VendingMachine() {
	// TODO Auto-generated constructor stub
	productToQuantityMap = new HashMap<Integer, Integer>();
	reset();
    }

    public void selectProduct(int productCode) throws InvalidItemException, ProductUnavailableException {

	// populate product amount based on product selection
	switch (productCode) {

	case 1:
	    productAmount = 25;
	    break;

	case 2:
	    productAmount = 35;
	    break;

	case 3:
	    productAmount = 45;
	    break;

	default:
	    throw new InvalidItemException();

	}
	Boolean productAvailable = isProductAvailable(productCode);
	if (!productAvailable) {
	    resetData();
	    throw new ProductUnavailableException();
	}
	this.productCode = productCode;
    }

    private void resetData() {
	productCode = 0;
	productAmount = 0;
	paidAmount = 0;

    }

    public void addCoin(int coin) throws InvalidCoinException {

	switch (coin) {

	case 1:
	case 5:
	case 10:
	case 25:
	    paidAmount = paidAmount + coin;
	    break;

	default:

	    throw new InvalidCoinException();
	}
    }

    /**
     * Returns the refund of extra amount paid
     * 
     */
    public int vendItemAndRefund() throws InsufficentFundException {

	if (paidAmount < productAmount) {
	    throw new InsufficentFundException("Insufficient fund provided");
	}
	updateProductQuantity(productCode);

//	   System.out.println("Please collect refund of " + returnAmount + "p");
	int refundAmount = paidAmount - productAmount;
	resetData();

	return refundAmount;

    }

    public int cancelAndRefund() {

	int refundAmount = paidAmount;
	resetData();
	return refundAmount;

    }

    private void reset() {
	productToQuantityMap.put(1, 2);
	productToQuantityMap.put(2, 2);
	productToQuantityMap.put(3, 2);
    }

    public boolean reset(String passcode) {
	if (!passcode.equals(PASSCODE)) {
//	    System.out.println("Wrong passcode!!");
	    return false;
	} else {
	    reset();
	    return true;
	}

    }

    public boolean isProductAvailable(int productCode) {

	if (productToQuantityMap.get(productCode) < 1) {
	    return false;
	} else
	    return true;

    }

    // Decreases the product quantity by 1
    public void updateProductQuantity(int productCode) {
	productToQuantityMap.put(productCode, productToQuantityMap.get(productCode) - 1);
    }

    public void printProductAndTheirQuantity() {
	System.out.println(productToQuantityMap);
    }
}
