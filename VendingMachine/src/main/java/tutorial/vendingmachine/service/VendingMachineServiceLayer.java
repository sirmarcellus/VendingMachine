/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.vendingmachine.service;

import java.math.BigDecimal;
import java.util.List;
import tutorial.vendingmachine.dao.VendingMachinePersistenceException;
import tutorial.vendingmachine.dto.VendingMachine;

/**
 *
 * @author Austin
 */
public interface VendingMachineServiceLayer {

    List<VendingMachine> getAllItems() throws
            VendingMachinePersistenceException;

    VendingMachine getUserItem(String itemId) throws
            VendingMachinePersistenceException,
            VendingMachineNoItemInventoryException;
/*
    VendingMachine removeUserItem(String itemId) throws
            VendingMachinePersistenceException;
*/
    VendingMachine removeOneItemCount(String itemId) throws
            VendingMachinePersistenceException,
            VendingMachineNoItemInventoryException;

    BigDecimal getVendItemCost(String itemId) throws
            VendingMachinePersistenceException,
            VendingMachineNoItemInventoryException,
            VendingMachineInsufficientFundsException;

    BigDecimal[] giveChange(String itemId) throws
            VendingMachinePersistenceException,
            VendingMachineNoItemInventoryException,
            VendingMachineInsufficientFundsException;
    
    void setUserMoney(BigDecimal assignMoney);
}
