/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.vendingmachine.controller;

import java.math.BigDecimal;
import java.util.List;
import tutorial.vendingmachine.dao.VendingMachineDao;
import tutorial.vendingmachine.dao.VendingMachineDaoFileImpl;
import tutorial.vendingmachine.dao.VendingMachinePersistenceException;
import tutorial.vendingmachine.dto.VendingMachine;
import tutorial.vendingmachine.service.VendingMachineInsufficientFundsException;
import tutorial.vendingmachine.service.VendingMachineNoItemInventoryException;
import tutorial.vendingmachine.service.VendingMachineServiceLayer;
import tutorial.vendingmachine.ui.UserIO;
import tutorial.vendingmachine.ui.UserIOConsoleImpl;
import tutorial.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Austin
 */
public class VendingMachineController {

    private UserIO io = new UserIOConsoleImpl();

    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws VendingMachineNoItemInventoryException, VendingMachineInsufficientFundsException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                listSnacks();
                printMenuHere();
                menuSelection = getMenuChoice();

                switch (menuSelection) {
                    case 1:
                        io.print("Buying an item");
                        getUserMoney();
                        String newItemId = removeItem();
                        getChange(newItemId);
                        // other data 
                        break;
                    case 2:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();

                }
            }

            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void printMenuHere() {
        view.printMenu();
    }

    private int getMenuChoice() {
        return view.getUserMenuChoice();
    }

    private void listSnacks() throws VendingMachinePersistenceException {
        view.displayItemsBanner();
        List<VendingMachine> itemsList = service.getAllItems();
        view.displayAllItems(itemsList);
    }

    private void getUserMoney() {

        BigDecimal money = view.getUserMoney();
        service.setUserMoney(money);
    }

    private String removeItem() throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        view.displayItemDispenseBanner();
        String itemId = view.dispenseItem();
        VendingMachine removedItem = service.removeOneItemCount(itemId);
        view.displayRemovedItem(removedItem);
        return itemId;
    }

    private void getChange(String itemId) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException, VendingMachineInsufficientFundsException {
        view.displayChangeBanner();
        BigDecimal[] changeArray = service.giveChange(itemId);
        view.displayChangeArr(changeArray);
        view.displayChangeSuccessBanner();

    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
