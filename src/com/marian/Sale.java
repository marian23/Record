package com.marian;

import java.util.Date;

/**
 * Created by marian on 12/3/2015.
 */
public class Sale {
    Date sale_date;
    double amount;
    int albumID;
    int consignorID;

    public Sale(Date sale_date, Double amount, int albumID, int consignorID){
        this.amount = amount;
        this.sale_date = sale_date;
        this.albumID = albumID;
        this.consignorID = consignorID;

    }

    public int getAlbumID() {
        return albumID;
    }

    public int getConsignorID() {
        return consignorID;
    }

    public double getAmount()
    {
        return amount;
    }

    public Date getSale_date() {
        return sale_date;
    }



}
