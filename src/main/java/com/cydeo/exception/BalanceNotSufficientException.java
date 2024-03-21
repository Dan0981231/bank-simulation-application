package com.cydeo.exception;

public class BalanceNotSufficientException extends Throwable {
    public BalanceNotSufficientException(String balanceIsNotEnough) {
        super(balanceIsNotEnough);
    }
}
