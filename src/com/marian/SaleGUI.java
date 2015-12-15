package com.marian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by marian on 12/14/2015.
 */
public class SaleGUI {
    private JPanel SaleGUI;
    private JTextField sale_datetextField1;
    private JTextField amounttextField2;
    private JButton addToSaleButton;
    private JButton quitButton;
    private JButton deleteButton;
    private JComboBox albumcomboBox1;
    private JComboBox consignorcomboBox2;
    private JTable table1;


    SaleGUI(final ablumDateModel ablumDateModel) {
        fillCombo(Main.getConsignor());
        fillcom(Main.getAlbum());
        table1.setGridColor(Color.black);
        table1.setModel(ablumDateModel);

        addToSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String sale_dateString = sale_datetextField1.getText();
                String [] comboString = consignorcomboBox2.getSelectedItem().toString().split(" ");
                int consignorid = Integer.parseInt(comboString[0]);
                String [] comb = albumcomboBox1.getSelectedItem().toString().split(" ");
                int albumID = Integer.parseInt(comb[0]);
                try {
                    Date d = new Date();
                    d = df.parse(sale_dateString);
                    Double amount = Double.parseDouble(amounttextField2.getText());
                    Sale sale = new Sale(d, amount, 1, consignorid);
                    Main.addSale(sale);
                    Main.loadAllSales();
                } catch (ParseException pe) {
                    System.out.println("Unable to parse " + sale_dateString);
                    fillCombo(Main.getConsignor());
                    fillcom(Main.getAlbum());


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
                int currentRow = table1.getSelectedRow();

                if (currentRow == -1) {      // -1 means no row is selected. Display error message.
                    //JOptionPane.showMessageDialog(rootPane, "Please choose a sale to delete");
                }
                boolean deleted = ablumDateModel.deleteRow(currentRow);
                if (deleted) {
                    Main.loadAllRecord();
                } else {
                    // JOptionPane.showMessageDialog(rootPane, "Error deleting movie");
                }
            }
        });
    }

    public SaleGUI() {

    }

    private void fillCombo(HashMap consignor) {

        Set keySet = consignor.keySet(); //Creates a set of the keys, and iterate over that
        for (Object consignorID : keySet) {
//Use the key to get each value. Repeat for each key.
            consignorcomboBox2.addItem(consignorID + " - " + consignor.get(consignorID));
        }
    }
    private void fillcom(HashMap album){
        Set keySet = album.keySet();
        for (Object albumID : keySet){
            albumcomboBox1.addItem(albumID + " - " + album.get(albumID));
        }
    }
    public JPanel getPanle(){
        return SaleGUI;
    }
}
