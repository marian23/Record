package com.marian;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by marian on 12/3/2015.
 */
public class ablumDateModel extends AbstractTableModel {
    int rowCount = 0;
    int columnCount = 0;
    ResultSet resultSet;

    public ablumDateModel(ResultSet rs) {
        this.resultSet = rs;
        setup();

    }

    private void setup() {
        countRows();
        try {
            columnCount = resultSet.getMetaData().getColumnCount();
        } catch (SQLException se) {
            System.out.println("error count column" + se);
        }

    }

    public void updateResultSet(ResultSet newRS) {
        resultSet = newRS;
        setup();
    }

    @Override
    public int getRowCount() {

        countRows();
        return rowCount;
    }

    public void countRows() {
        rowCount = 0;
        try {


            resultSet.beforeFirst();
            while (resultSet.next()) {
                rowCount++;
            }
            resultSet.beforeFirst();
        } catch (SQLException se) {

            System.out.println("error counting rows " + se);
        }

    }


    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int row, int column) {
       try {
            resultSet.absolute(row + 1);
            Object O = resultSet.getObject(column + 1);
           return O;
                   //O.toString();
        } catch (SQLException se) {
            System.out.println(se);
            return se.toString();

        }


    }


    public boolean isCellEditable(int row, int column) {
        if (column == 3) {
            return true;
        }
        return false;
    }

    public boolean deleteRow(int row) {
        try {
            resultSet.absolute(row + 1);
            resultSet.deleteRow();
            //tell table to redraw itself
            fireTableDataChanged();
            return true;
        } catch (SQLException se) {
            System.out.println("Delete row error " + se);
            return false;
        }
    }

    public boolean inserRow(String Title, String Artist, String category, int sellingPrice) {
        try {
            resultSet.moveToInsertRow();
            resultSet.updateString(Main.Title, Title);
            resultSet.updateString(Main.Artist, Artist);
            resultSet.updateString(Main.category, category);
            resultSet.updateInt(Main.sellingPrice, sellingPrice);
            resultSet.insertRow();
            resultSet.moveToCurrentRow();
            fireTableDataChanged();
            return true;
        } catch (SQLException e) {
            System.out.println("error adding row");
            System.out.println(e);
            return false;
        }

    }

    public boolean inserRow(String consignorName, String PhoneNumber, int consignorPay, int consignorOwn) {
        try {
            resultSet.moveToInsertRow();
            resultSet.updateString(Main.consignorName, consignorName);
            resultSet.updateString(Main.consignorPhoneNumber, PhoneNumber);
            resultSet.updateInt(Main.consignorPay, consignorPay);
            resultSet.updateInt(Main.consignorOwn, consignorOwn);
            resultSet.insertRow();
            resultSet.moveToCurrentRow();
            fireTableDataChanged();
            return true;
        } catch (SQLException e) {
            System.out.println("error adding row");
            System.out.println(e);
            return false;
        }
    }

    public boolean inserRow(Date sale_date, Double amount) {
        try {
            resultSet.moveToInsertRow();
            resultSet.updateDate(Main.sale_date, (java.sql.Date) sale_date);
            resultSet.updateDouble(Main.amount, amount);
            resultSet.insertRow();
            resultSet.moveToCurrentRow();
            fireTableDataChanged();
            return true;
        } catch (SQLException e) {
            System.out.println("error adding row");
            System.out.println(e);
            return false;
        }
    }
    @Override
    public String getColumnName(int column){
        try {
            return resultSet.getMetaData().getColumnName(column + 1);

        }catch (SQLException se){
            System.out.println("error feching column names" +se);
            return "?";
        }
    }
}