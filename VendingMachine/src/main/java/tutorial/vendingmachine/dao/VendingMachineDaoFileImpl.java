/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.vendingmachine.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import static java.math.BigDecimal.ONE;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import tutorial.vendingmachine.dto.VendingMachine;

/**
 *
 * @author Austin
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    private Map<String, VendingMachine> itemsInMach = new HashMap<>();

    @Override
    public List<VendingMachine> getAllItems() throws VendingMachinePersistenceException {
        loadInv();
        return new ArrayList<VendingMachine>(itemsInMach.values());
    }

    @Override
    public VendingMachine getUserItem(String itemId) throws VendingMachinePersistenceException {
        loadInv();
        return itemsInMach.get(itemId);
    }

    @Override
    public VendingMachine removeOneItem(String itemId) throws VendingMachinePersistenceException {
        loadInv();
        VendingMachine removedOne = itemsInMach.get(itemId);
        removeOneItemInv(removedOne);
        writeInv();
        return removedOne;
    }

    @Override
    public BigDecimal getItemPrice(String itemId) throws VendingMachinePersistenceException {
        loadInv();
        VendingMachine price = itemsInMach.get(itemId);
        getItemPriceHere(price);
        return new BigDecimal(price.getItemCost());
        
    }

    
    private double getItemPriceHere(VendingMachine itemFromFile) {

        double itemPrice = Double.parseDouble(itemFromFile.getItemCost());
        return itemPrice;

    }

    
    

    private VendingMachine unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        String itemId = itemTokens[0];
        VendingMachine itemFromFile = new VendingMachine(itemId);
        itemFromFile.setItemName(itemTokens[1]);
        itemFromFile.setItemCost(itemTokens[2]);
        itemFromFile.setNumItemsInInventory(itemTokens[3]);

        return itemFromFile;
    }

    private void loadInv() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "Could not load inventory data.", e);
        }

        String currentLine;

        VendingMachine currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            itemsInMach.put(currentItem.getItemId(), currentItem);
        }
        scanner.close();

    }

    private String marshallItem(VendingMachine aItem) {
        String itemAsText = aItem.getItemId() + DELIMITER;

        itemAsText += aItem.getItemName() + DELIMITER;

        itemAsText += aItem.getItemCost() + DELIMITER;

        itemAsText += aItem.getNumItemsInInventory();

        return itemAsText;

    }

    private VendingMachine removeOneItemInv(VendingMachine itemFromFile) {

        int itemInvAmount = Integer.parseInt(itemFromFile.getNumItemsInInventory());
        itemInvAmount--;
        String itemInvStrAmount = Integer.toString(itemInvAmount);
        itemFromFile.setNumItemsInInventory(itemInvStrAmount);
        return itemFromFile;

    }

    
    

    private void writeInv() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save vending data.", e);
        }

        String itemAsText;

        List<VendingMachine> itemList = new ArrayList(itemsInMach.values());

        for (VendingMachine currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();

    }

}
