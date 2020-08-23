/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.vendingmachine.dao;

import java.math.BigDecimal;
import java.util.List;
import tutorial.vendingmachine.dto.VendingMachine;

/**
 *
 * @author Austin
 */
public interface VendingMachineDao {

    // lists all the snacks in the vending machine 
    List<VendingMachine> getAllItems() 
            throws VendingMachinePersistenceException;

    // grabs one snack from user input
    
    VendingMachine getUserItem(String itemId) 
            throws VendingMachinePersistenceException;


    VendingMachine removeOneItem(String itemId)
            throws VendingMachinePersistenceException;

    BigDecimal getItemPrice(String itemId)
            throws VendingMachinePersistenceException;

}
