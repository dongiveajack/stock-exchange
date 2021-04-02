package com.navi.stock_exchange.validators;

/**
 * @author Abhinav Tripathi 13/03/21
 */
public interface Validator<T> {
    boolean validate(T t);
}
