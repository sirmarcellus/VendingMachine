/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.List;
import tutorial.vendingmachine.dto.VendingMachine;

/**
 *
 * @author Austin
 */
public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void printMenu() {
        io.print("Main Menu");
        io.print("1. Buy a Item ");
        io.print("2. Exit ");
    }

    public int getUserMenuChoice() {
        return io.readInt("Please select from above choices", 1, 2);
    }

    public void displayAllItems(List<VendingMachine> itemsList) {
        for (VendingMachine currentItem : itemsList) {
            String itemInfo = String.format("#%s : %s %s %s",
                    currentItem.getItemId(),
                    currentItem.getItemName(),
                    currentItem.getItemCost(),
                    currentItem.getNumItemsInInventory());
            io.print(itemInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayItemsBanner() {
        io.print("==== Displaying all snacks Banner ==== ");
    }

    public BigDecimal getUserMoney() {
        return io.readBigDecimal("Please enter total money you have");
    }
    
    public void displayChangeArr(BigDecimal[] changeArr) {
        io.print("Change is " + changeArr[0] + " quarters " + 
                changeArr[1] + " dimes " + changeArr[2] +
                " nickels " + changeArr[3] + " pennies");
    }

    public String dispenseItem() {
        return io.readString("Please enter itemId.");
    }

    public void displayRemovedItem(VendingMachine itemRecord) {
        if (itemRecord != null) {
            io.print("Item dispensed");
        } else {
            io.print("No item in machine ");
        }
        io.readString("Please hit enter to continue");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayItemDispenseBanner() {
        io.print("=== Item dispensary === !!!");
    }
    
    public void displayChangeBanner() {
        io.print("==== Displaying all change Banner ==== ");
    }

    public void displayChangeSuccessBanner() {
        io.print("==== Displaying change success Banner ==== ");
    }
}
