/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial.vendingmachine;

import tutorial.vendingmachine.controller.VendingMachineController;
import tutorial.vendingmachine.dao.VendingMachineDao;
import tutorial.vendingmachine.dao.VendingMachineDaoFileImpl;
import tutorial.vendingmachine.service.VendingMachineInsufficientFundsException;
import tutorial.vendingmachine.service.VendingMachineNoItemInventoryException;
import tutorial.vendingmachine.service.VendingMachineServiceLayer;
import tutorial.vendingmachine.service.VendingMachineServiceLayerImpl;
import tutorial.vendingmachine.ui.UserIO;
import tutorial.vendingmachine.ui.UserIOConsoleImpl;
import tutorial.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Austin
 */
public class App {
    public static void main(String[] args) throws VendingMachineNoItemInventoryException, VendingMachineInsufficientFundsException {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao);
        VendingMachineController controller = new VendingMachineController(myService, myView);
        controller.run();
    }
}

