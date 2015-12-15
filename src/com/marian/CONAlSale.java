package com.marian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

/**
 * Created by marian on 12/14/2015.
 */
public class CONAlSale extends JPanel{
    private JPanel CONAlSale;
    private JTextField soldpricetextField1;
    private JTextField consignorPaytextField2;
    private JTextField consignorOwntextField3;
    private JButton addButton;
    private JButton quitButton;
    private JButton deleteButton;
    private JTable saletable1;
    private JPanel getJpanel;

    CONAlSale(final ablumDateModel ablumDateModel){
        saletable1.setGridColor(Color.black);
        saletable1.setModel(ablumDateModel);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double price = Double.parseDouble(soldpricetextField1.getText());
                if (price < 0){
                    JOptionPane.showMessageDialog(CONAlSale, "enter album sale price");
                    return;
                }

                double consignorpay = (price / 100) * 40;
                consignorPaytextField2.setText(Double.toString(consignorpay));

                double consignorOwn = (price / 100) * 60;
                consignorOwntextField3.setText(Double.toString(consignorOwn));


                System.out.println(" to add " + price + " " + consignorpay + " " + consignorOwn);

                boolean insertrow = ablumDateModel.inserSale(price, consignorpay, consignorOwn);
                if (insertrow){
                    Main.loadAllSales();
                }else {
                    JOptionPane.showMessageDialog(CONAlSale, "error adding information");
                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.shutdown();
                System.exit(0);

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int currentRow = saletable1.getSelectedRow();

                if (currentRow == -1) {      // -1 means no row is selected. Display error message.
                    //JOptionPane.showMessageDialog(rootPane, "Please choose a sale to delete");
                }
                boolean deleted = ablumDateModel.deleteRow(currentRow);
                if (deleted) {
                    Main.loadAllRecord();
                } else {

                }
            }
        });
    }
    public JPanel getJPanel(){
        return getJpanel;
    }
    }

