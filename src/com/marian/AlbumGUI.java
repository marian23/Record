package com.marian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by marian on 12/3/2015.
 */
public class AlbumGUI extends JFrame implements WindowListener {
    private JPanel rootPanel;
    private JTextField TitletextField1;
    private JTextField ArtisttextField1;
    private JTextField categorytextField1;
    private JTextField sellingpricetextField1;
    private JRadioButton albumRadioButton;
    private JRadioButton consignorRadioButton;
    private JRadioButton saleRadioButton;
    private JTextField consignornametextField1;
    private JTextField phoneNumbertextField2;
    private JTextField consignorPaytextField3;
    private JTextField consignorOwntextField4;
    private JTextField saledatetextField1;
    private JTextField amountSoldtextField2;
    private JButton addButton;
    private JButton quitButton;
    private JButton deleteButton;
    private JTable table1;



    AlbumGUI(final ablumDateModel ablumDateModel) {


        //public AlbumGUI() {
            setContentPane(rootPanel);
            pack();
            setTitle("Record Store Database application");
            setVisible(true);
            setPreferredSize(new Dimension(300, 300));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ButtonGroup radios = new ButtonGroup();
            radios.add(albumRadioButton);
            radios.add(consignorRadioButton);
            radios.add(saleRadioButton);
        table1.setGridColor(Color.black);
        table1.setModel(ablumDateModel);

            albumRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (albumRadioButton.isSelected()) {
                        TitletextField1.setVisible(true);
                        ArtisttextField1.setVisible(true);
                        sellingpricetextField1.setVisible(true);
                        categorytextField1.setVisible(true);

                        consignornametextField1.setVisible(false);
                        phoneNumbertextField2.setVisible(false);
                        consignorPaytextField3.setVisible(false);
                        consignorOwntextField4.setVisible(false);
                        saledatetextField1.setVisible(false);
                        amountSoldtextField2.setVisible(false);

                    }

                }
            });
            consignorRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (consignorRadioButton.isSelected()) {
                        consignornametextField1.setVisible(true);
                        phoneNumbertextField2.setVisible(true);
                        consignorPaytextField3.setVisible(true);
                        consignorOwntextField4.setVisible(true);

                        TitletextField1.setVisible(false);
                        ArtisttextField1.setVisible(false);
                        sellingpricetextField1.setVisible(false);
                        categorytextField1.setVisible(false);
                        saledatetextField1.setVisible(false);
                        amountSoldtextField2.setVisible(false);


                    }
                }
            });
            saleRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (saleRadioButton.isSelected()) {
                        saledatetextField1.setVisible(true);
                        amountSoldtextField2.setVisible(true);


                        TitletextField1.setVisible(false);
                        ArtisttextField1.setVisible(false);
                        sellingpricetextField1.setVisible(false);
                        categorytextField1.setVisible(false);

                        consignornametextField1.setVisible(false);
                        phoneNumbertextField2.setVisible(false);
                        consignorPaytextField3.setVisible(false);
                        consignorOwntextField4.setVisible(false);

                    }
                }
            });

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (albumRadioButton.isSelected()) {
                        String title = TitletextField1.getText();
                        String artist = ArtisttextField1.getText();
                        String category = categorytextField1.getText();
                        Double sellPrice = Double.parseDouble(sellingpricetextField1.getText());

                        Album albumToAdd = new Album(title, artist, category, sellPrice);

                        Main.addToAlbum(albumToAdd);


                    }
                    if (consignorRadioButton.isSelected()) {
                        String name = consignornametextField1.getText();
                        String phoneNumber = phoneNumbertextField2.getText();
                        Double pay = Double.parseDouble(consignorPaytextField3.getText());
                        Double own = Double.parseDouble(consignorOwntextField4.getText());

                        Consignor consignor = new Consignor(name, phoneNumber, pay, own);
                        Main.addtoConsignor(consignor);

                    }
                    if (saleRadioButton.isSelected()) {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String sale_dateString = saledatetextField1.getText();
                        try {
                            Date d = new Date();
                            d = df.parse(sale_dateString);
                            Double amount = Double.parseDouble(amountSoldtextField2.getText());
                            Sale sale = new Sale(d, amount);
                            Main.addSale(sale);
                        } catch (ParseException pe) {
                            System.out.println("Unable to parse " + sale_dateString);
                        }


                    }
                }
            });
            quitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);

                }
            });
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("closing");
                    Main.shutdown();


                }
            });
        }

        @Override
        public void windowOpened (WindowEvent e){

        }

        @Override
        public void windowClosing (WindowEvent e){

        }

        @Override
        public void windowClosed (WindowEvent e){

        }

        @Override
        public void windowIconified (WindowEvent e){

        }

        @Override
        public void windowDeiconified (WindowEvent e){

        }

        @Override
        public void windowActivated (WindowEvent e){

        }

        @Override
        public void windowDeactivated (WindowEvent e){

        }
    }
//}