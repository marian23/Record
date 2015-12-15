package com.marian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * Created by marian on 12/14/2015.
 */
public class GUIOFAlbum {
    private JPanel GUIOFAlbum;
    private JTextField titletextField1;
    private JTextField artisttextField2;
    private JTextField categoryextField3;
    private JTextField sellingPricetextField4;
    private JButton addToAlbumButton;
    private JButton quitButton;
    private JButton deleteButton;
    private JTable albumtable1;
    private JComboBox searchescomboBox1;
    private JTextField searchtextField1;
    private JButton searchButton;


    GUIOFAlbum(final ablumDateModel ablumDateModel) {
        albumtable1.setGridColor(Color.black);
        albumtable1.setModel(ablumDateModel);

        String search = "Title";
        String serach2 = "Artist";
        String search3 = "category";
        String search4 = "sellingPrice";


        searchescomboBox1.addItem(search);
        searchescomboBox1.addItem(serach2);
        searchescomboBox1.addItem(search3);
        searchescomboBox1.addItem(search4);


        addToAlbumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titletextField1.getText();
                String artist = artisttextField2.getText();
                String category = categoryextField3.getText();
                Double sellPrice = Double.parseDouble(sellingPricetextField4.getText());

                Album albumToAdd = new Album(title, artist, category, sellPrice);

                Main.addToAlbum(albumToAdd);


            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.shutdown();
                System.exit(0);

            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchCriteria = searchescomboBox1.getSelectedItem().toString();
                String word = searchtextField1.getText();

                if (!word.equals("")){
                    Main.searchAlbum(searchCriteria, word);


                }

                else {
                    JOptionPane.showMessageDialog(GUIOFAlbum, "Please enter something to search for.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentRow = albumtable1.getSelectedRow();

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

    public GUIOFAlbum() {



    }


    public JPanel getPanel () { return GUIOFAlbum; }
}
