/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.vendingmachine.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import tutorial.vendingmachine.dao.VendingMachineDao;
import tutorial.vendingmachine.dao.VendingMachinePersistenceException;
import tutorial.vendingmachine.dto.VendingMachine;

/**
 *
 * @author Austin
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;
    VendingMachine view;
    BigDecimal userMoney = new BigDecimal("0");
    BigDecimal diff;
    BigDecimal hundVal = new BigDecimal("100");
    BigDecimal quarterVal = new BigDecimal("25");
    BigDecimal dimeVal = new BigDecimal("10");
    BigDecimal nickelVal = new BigDecimal("5");
    BigDecimal pennyVal = new BigDecimal("1");
    BigDecimal zeroVal = new BigDecimal("0");
    BigDecimal numQuarters = new BigDecimal("0");
    BigDecimal numDimes = new BigDecimal("0");
    BigDecimal numNickles = new BigDecimal("0");
    BigDecimal numPennies = new BigDecimal("0");

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    // validate data  :: good 
    // remove one item count in inventory :: check
    // do change calculations compare user money and price
    // give back change amount to user
    private void validateItemId(VendingMachine vendingItem) throws
            VendingMachineNoItemInventoryException {

        if (vendingItem.getItemId() == null
                || vendingItem.getItemId().trim().length() == 0) {
            throw new VendingMachineNoItemInventoryException(
                    "Error id entered is not valid");
        }

    }

    @Override
    public BigDecimal[] giveChange(String itemId) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException, VendingMachineInsufficientFundsException {
        BigDecimal cost = dao.getItemPrice(itemId);
        diff = userMoney.subtract(cost);

        MathContext mc = new MathContext(2);

        userMoney.equals(diff);
        BigDecimal change = diff.multiply(hundVal);
        if (diff.compareTo(zeroVal) == -1) {
            throw new VendingMachineInsufficientFundsException("Not enough money to purchase");
        } else {
            numQuarters = change.divide(quarterVal);
            change = change.remainder(quarterVal, mc);

            numDimes = change.divide(dimeVal);
            change = change.remainder(dimeVal, mc);

            numNickles = change.divide(nickelVal);
            change = change.remainder(nickelVal, mc);

            numPennies = change.divide(pennyVal);

        }

        BigDecimal[] changeArr = {numQuarters, numDimes, numNickles, numPennies};
        return changeArr;

    }

    @Override
    public void setUserMoney(BigDecimal assignMoney) {
        userMoney = assignMoney;
    }

    @Override
    public List<VendingMachine> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    @Override
    public VendingMachine getUserItem(String itemId) throws
            VendingMachinePersistenceException,
            VendingMachineNoItemInventoryException {
        validateItemId(dao.getUserItem(itemId));
        return dao.getUserItem(itemId);
    }

    @Override
    public VendingMachine removeOneItemCount(String itemId) throws
            VendingMachinePersistenceException,
            VendingMachineNoItemInventoryException {
        return dao.removeOneItem(itemId);
    }

    @Override
    public BigDecimal getVendItemCost(String itemId) throws
            VendingMachinePersistenceException,
            VendingMachineNoItemInventoryException,
            VendingMachineInsufficientFundsException {
        return dao.getItemPrice(itemId);
    }

}
