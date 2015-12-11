package com.marian;

/**
 * Created by marian on 12/3/2015.
 */
public class Consignor {
    String consignorName;
    String phoneNumber;
    Double consignorPay;
    Double consignorOwn;
    public Consignor(String name, String phoneNumber, Double pay, Double own) {
        this.consignorName = name;
        this.phoneNumber = phoneNumber;
        this.consignorPay = pay;
        this.consignorOwn = own;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public Double getConsignorPay() {

        return consignorPay;
    }

    public Double getConsignorOwn() {

        return consignorOwn;
    }

    public String getConsignorName() {

        return consignorName;
    }

    public void setConsignorName(String consignorName) {

        this.consignorName = consignorName;
    }
}
