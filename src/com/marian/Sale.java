package com.marian;

import java.util.Date;

/**
 * Created by marian on 12/3/2015.
 */
public class Sale {
    Date sale_date;
    double amount;
    public Sale(Date sale_date, Double amount){

    }
    public double getAmount()
    {
        return amount;
    }

    public Date getSale_date() {
        return sale_date;
    }



}
