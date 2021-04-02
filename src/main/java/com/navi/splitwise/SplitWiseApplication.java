package com.navi.splitwise;

import com.navi.splitwise.enums.SplitType;
import com.navi.splitwise.models.Split;
import com.navi.splitwise.services.ExpenseService;
import com.navi.splitwise.models.User;

import java.util.Arrays;

/**
 * @author Abhinav Tripathi 15/03/21
 */
public class SplitWiseApplication {
    public static void main(String[] args) {
        User abhinav = new User("Abhinav");
        User rahul = new User("Rahul");
        User krishna = new User("Krishna");

        ExpenseService.registerUser(abhinav);
        ExpenseService.registerUser(rahul);
        ExpenseService.registerUser(krishna);

        Split splitAbhinav = new Split(abhinav, 40D);
        Split splitRahul = new Split(rahul, 40D);
        Split splitKrishna = new Split(krishna, 40D);

        ExpenseService.addExpense(abhinav, 120D, Arrays.asList(splitAbhinav, splitRahul, splitKrishna), SplitType.EQUAL);

        System.out.println("Abhinav's balances" + ExpenseService.showBalancesOfUser(abhinav.getName()));
        System.out.println("Rahul's balances" + ExpenseService.showBalancesOfUser(rahul.getName()));
        System.out.println("Krishna's balances" + ExpenseService.showBalancesOfUser(krishna.getName()));

        splitAbhinav = new Split(abhinav, 40D);
        splitKrishna = new Split(krishna, 30D);

        ExpenseService.addExpense(rahul, 70D, Arrays.asList(splitAbhinav, splitKrishna), SplitType.EXACT);

        System.out.println();

        System.out.println("Abhinav's balances" + ExpenseService.showBalancesOfUser(abhinav.getName()));
        System.out.println("Rahul's balances" + ExpenseService.showBalancesOfUser(rahul.getName()));
        System.out.println("Krishna's balances" + ExpenseService.showBalancesOfUser(krishna.getName()));
    }
}
