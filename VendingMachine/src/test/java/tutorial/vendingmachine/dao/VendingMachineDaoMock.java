/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.vendingmachine.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import tutorial.vendingmachine.dto.VendingMachine;

/**
 *
 * @author Austin
 */
public class VendingMachineDaoMock implements VendingMachineDao {

    @Override
    public List<VendingMachine> getAllItems() throws VendingMachinePersistenceException {
        return new ArrayList<>();
    }

    @Override
    public VendingMachine getUserItem(String itemId) throws VendingMachinePersistenceException {
        return new VendingMachine(itemId);
    }

    @Override
    public VendingMachine removeOneItem(String itemId) throws VendingMachinePersistenceException {
        return new VendingMachine(itemId);
    }

    @Override
    public BigDecimal getItemPrice(String itemId) throws VendingMachinePersistenceException {
        return new BigDecimal(itemId);
    }
    
}
