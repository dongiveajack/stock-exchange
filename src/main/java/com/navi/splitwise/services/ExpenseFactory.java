package com.navi.splitwise.services;

import com.navi.splitwise.enums.SplitType;
import com.navi.splitwise.models.Expense;
import com.navi.splitwise.models.Split;
import com.navi.splitwise.models.User;

import java.util.List;

/**
 * @author Abhinav Tripathi 15/03/21
 */
public class ExpenseFactory {
    public static Expense createExpense(User paidByUser, double amount, List<Split> splits, SplitType splitType) {
        return new Expense(amount, paidByUser, splits);
    }
}
