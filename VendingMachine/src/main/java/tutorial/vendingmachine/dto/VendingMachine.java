/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.vendingmachine.dto;

/**
 *
 * @author Austin
 */
public class VendingMachine {
    private String itemName;
    private String itemCost;
    private String numItemsInInventory;
    private String itemId;
    private String userMoney;
    
    public VendingMachine(String itemId){
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }
    
    

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCost() {
        return itemCost;
    }

    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }

    public String getNumItemsInInventory() {
        return numItemsInInventory;
    }

    public void setNumItemsInInventory(String numItemsInInventory) {
        this.numItemsInInventory = numItemsInInventory;
    }

    public String getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(String userMoney) {
        this.userMoney = userMoney;
    }


    
    
    
}
