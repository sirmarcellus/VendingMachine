/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.vendingmachine.dao;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tutorial.vendingmachine.dto.VendingMachine;

/**
 *
 * @author Austin
 */
public class VendingMachineDaoFileImplTest {
    
    VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        testDao = new VendingMachineDaoFileImpl();
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDaoFileImpl.
     */
    @Test
    public void testGetAllItems() throws Exception {
        List<VendingMachine> numItems = testDao.getAllItems();
        
        assertEquals(2, numItems.size());
    }

    /**
     * Test of getUserItem method, of class VendingMachineDaoFileImpl.
     */
    @Test
    public void testGetUserItem() throws Exception {
        VendingMachine m = new VendingMachine("0006");
        
        //List<VendingMachine> numItems = testDao.getAllItems();
        assertTrue(m.getItemId().contains("0006"));
    }


    /**
     * Test of removeOneItem method, of class VendingMachineDaoFileImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveOneItem() throws Exception {
        VendingMachine m = new VendingMachine("0001");
        int itemInvAmount = Integer.parseInt(m.getNumItemsInInventory());
        testDao.removeOneItem(m.getItemId());
       
       
        int newItemInvAmt = Integer.parseInt(m.getNumItemsInInventory());
        
        assertEquals(itemInvAmount-1, newItemInvAmt);
    }

    /**
     * Test of getItemPrice method, of class VendingMachineDaoFileImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetItemPrice() throws Exception {
        VendingMachine m = new VendingMachine("0001");
        BigDecimal itemCost = testDao.getItemPrice(m.getItemId());
        BigDecimal testCost = new BigDecimal("1.25");
        assertEquals(testCost, itemCost);
        
    }
    
}
