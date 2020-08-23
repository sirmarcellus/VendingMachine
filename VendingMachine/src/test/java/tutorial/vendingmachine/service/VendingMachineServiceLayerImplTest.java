/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.vendingmachine.service;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tutorial.vendingmachine.dao.VendingMachineDao;
import tutorial.vendingmachine.dao.VendingMachineDaoMock;
import tutorial.vendingmachine.dto.VendingMachine;

/**
 *
 * @author Austin
 */
public class VendingMachineServiceLayerImplTest {

    VendingMachineServiceLayerImpl testService;

    public VendingMachineServiceLayerImplTest() {
        testService = new VendingMachineServiceLayerImpl(new VendingMachineDaoMock());
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of giveChange method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGiveChange() throws Exception {
        String itemId = "0";
        BigDecimal numQuarters = new BigDecimal("0");
        BigDecimal numDimes = new BigDecimal("0");
        BigDecimal numNickles = new BigDecimal("0");
        BigDecimal numPennies = new BigDecimal("0");
        BigDecimal[] changeArr = {numQuarters,numDimes,numNickles,numPennies};
        
        BigDecimal[] otherArr = testService.giveChange(itemId);
        assertArrayEquals(changeArr, otherArr);
    }

    /**
     * Test of setUserMoney method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testSetUserMoney() {
        //do nothing
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetAllItems() throws Exception {
        List<VendingMachine> numItems = testService.getAllItems();
        assertEquals(2, numItems.size());
    }

    /**
     * Test of getUserItem method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetUserItem() throws Exception {
        VendingMachine m = new VendingMachine("0006");
        assertTrue(m.getItemId().contains("0006"));
    }

    /**
     * Test of removeOneItemCount method, of class
     * VendingMachineServiceLayerImpl.
     */
    @Test
    public void testRemoveOneItemCount() throws Exception {
        VendingMachine m = new VendingMachine("0001");
        int itemInvAmount = Integer.parseInt(m.getNumItemsInInventory());
        testService.removeOneItemCount(m.getItemId());

        int newItemInvAmt = Integer.parseInt(m.getNumItemsInInventory());

        assertEquals(itemInvAmount - 1, newItemInvAmt);
    }

    /**
     * Test of getVendItemCost method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetVendItemCost() throws Exception {
        VendingMachine m = new VendingMachine("0001");
        BigDecimal itemCost = testService.getVendItemCost(m.getItemId());
        BigDecimal testCost = new BigDecimal("1.25");
        assertEquals(testCost, itemCost);
    }

}
