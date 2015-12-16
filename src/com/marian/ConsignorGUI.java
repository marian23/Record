package com.marian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

/**
 * Created by marian on 12/14/2015.
 */
public class ConsignorGUI extends JPanel {
    private JPanel ConsignorGUI;
    private JTextField consignorNametextField1;
    private JTextField consignorPaytextField2;
    private JTextField consignorOwntextField3;
    private JTextField phoneNumbertextField2;
    private JButton addToConsignorButton;
    private JButton quitButton;
    private JButton deleteButton;
    private JTable consignortable1;
    private JComboBox searchcomboBox1;
    private JTextField seachnametextField1;
    private JTextField salepricetextField1;

    ConsignorGUI(final ablumDateModel ablumDateModel) {
        consignortable1.setGridColor(Color.black);
        consignortable1.setModel(ablumDateModel);

        String search = "Title";
        String serach2 = "Artist";
        String search3 = "category";
        String search4 = "SellingPrice";



        //public AlbumGUI() {
        //setContentPane(rootPanel);
        //pack();
        //setTitle("Record Store Database application");
        //setVisible(true);
        //setPreferredSize(new Dimension(300, 300));
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addToConsignorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = consignorNametextField1.getText();
                String phoneNumber = phoneNumbertextField2.getText();
               // Double consignorpay = Double.parseDouble(consignorPaytextField2.getText());
                //Double consignorown = Double.parseDouble(consignorOwntextField3.getText());
                Double sale = Double.parseDouble(salepricetextField1.getText());
                double pay = (sale / 100) * 40;
                double own = (sale / 100) * 60;

                Consignor consignor = new Consignor(name, phoneNumber, pay, own); //consignorpay, consignorown);
                Main.addtoConsignor(consignor);


                    double price = Double.parseDouble(salepricetextField1.getText());
                    if (price < 0) {
                        JOptionPane.showMessageDialog(ConsignorGUI, "enter album sale price");
                        return;
                    }

                    double consignorPay = (price / 100) * 40;
                    consignorPaytextField2.setText(Double.toString(consignorPay));

                    double consignorOwn = (price / 100) * 60;
                    consignorOwntextField3.setText(Double.toString(consignorOwn));


                    System.out.println(" to add " + price + " " + consignorPay + " " + consignorOwn);

                    boolean insertrow = ablumDateModel.inserSale(price, consignorPay, consignorOwn);
                    if (insertrow) {
                        Main.loadAllConsignor();
                    } else {
                        JOptionPane.showMessageDialog(ConsignorGUI, "error adding information");
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
                int currentRow = consignortable1.getSelectedRow();

                if (currentRow == -1) {      // -1 means no row is selected. Display error message.
                    //JOptionPane.showMessageDialog(rootPane, "Please choose a sale to delete");
                }
                boolean deleted = ablumDateModel.deleteRow(currentRow);
                if (deleted) {
                    Main.loadAllConsignor();
                } else {
                    //JOptionPane.showMessageDialog(rootPane, "Error deleting movie");
                }
            }
        });
    }

    public ConsignorGUI() {

    }

    public JPanel getPanel () {
            return ConsignorGUI;
        }
    }
