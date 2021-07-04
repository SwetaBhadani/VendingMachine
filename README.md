# VendingMachine

This Vending machine application:
  1. Takes input for the product to buy
  2. Accepts valid coin i.e, 1c, 5c, 10c and 25c.
  3. Returns the product and the remaining change if any, if there is no exceptions found
  4. Cancels the request and refunds the money if provided
  5. Resets for the supplier. Assuming reset refills the vending machine with product to the maximum value
  6. Throws exceptions wherever required


The unit tests are written on JUnit. In order to run the tests, execute the below command:

* mvn clean test
    
