package com.navi.splitwise.services;

import com.navi.splitwise.enums.SplitType;
import com.navi.splitwise.exceptions.ValidationException;
import com.navi.splitwise.models.Expense;
import com.navi.splitwise.models.Split;
import com.navi.splitwise.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Abhinav Tripathi 15/03/21
 */
public class ExpenseService {
    private Map<String, User> userMap;
    private List<Expense> expenseList;
    private Map<String, Map<String, Double>> balances;

    private ExpenseService() {
        this.userMap = new HashMap<>();
        this.expenseList = new ArrayList<>();
        this.balances = new HashMap<>();
    }

    private static ExpenseService instance;

    public static ExpenseService getInstance() {
        if (instance == null) {
            instance = new ExpenseService();
        }
        return instance;
    }

    public static User registerUser(User user) {
        getInstance().balances.put(user.getName(), new HashMap<>());
        return getInstance().userMap.put(user.getName(), user);
    }

    public static User getUser(String name) {
        return getInstance().userMap.get(name);
    }

    public static Expense addExpense(User paidByUser, double amount, List<Split> splits, SplitType splitType) {
        getInstance().validateExpenseRequest(amount, splits, splitType);
        Expense expense = ExpenseFactory.createExpense(paidByUser, amount, splits, splitType);

        Map<String, Map<String, Double>> balancesSheet = getInstance().balances;

        String paidByUserId = paidByUser.getName();
        Map<String, Double> paidByuserBalanceSheet = balancesSheet.get(paidByUserId);

        for (Split split : splits) {
            String paidToUserId = split.getPaidToUser().getName();
            if (!paidByuserBalanceSheet.containsKey(paidToUserId))
                paidByuserBalanceSheet.put(paidToUserId, 0D);
            paidByuserBalanceSheet.put(paidToUserId, paidByuserBalanceSheet.get(paidToUserId) + split.getAmount());

            Map<String, Double> paidToUserBalanceSheet = balancesSheet.get(paidToUserId);
            if (!paidToUserBalanceSheet.containsKey(paidByUserId))
                paidToUserBalanceSheet.put(paidByUserId, 0D);
            paidToUserBalanceSheet.put(paidByUserId, paidToUserBalanceSheet.get(paidByUserId) - split.getAmount());
        }
        getInstance().expenseList.add(expense);
        return expense;
    }

    public static Map<String, Double> showBalancesOfUser(String name) {
        return getInstance().balances.get(name);
    }

    private void validateExpenseRequest(double amount, List<Split> splits, SplitType splitType) {
        Double totalAmount = 0D;
        switch (splitType) {
            case EQUAL:
                Double equalShareAmount = null;
                for (Split split : splits) {
                    if (equalShareAmount == null)
                        equalShareAmount = split.getAmount();
                    if (equalShareAmount != split.getAmount()) {
                        throw new ValidationException("Amount in split should be equal for all splits");
                    }
                    totalAmount += split.getAmount();
                }
                if (totalAmount != amount) {
                    throw new ValidationException("Total Amount in splits is not equal to paid by amount");
                }
                break;
            case EXACT:
                totalAmount = 0D;
                for (Split split : splits) {
                    totalAmount += split.getAmount();
                }
                if (totalAmount != amount) {
                    throw new ValidationException("Total Amount in splits is not equal to paid by amount");
                }
                break;
        }
    }
}
