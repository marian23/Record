package com.marian;

import javax.swing.*;

/**
 * Created by marian on 12/14/2015.
 */
public class tabbedJframe extends JFrame{
    private JPanel rootPanel;

    private JTabbedPane tabbedPane;

    public void tabbedJframe () { }

    public tabbedJframe(){
        setContentPane(rootPanel);
        tabbedPane = new JTabbedPane();
        rootPanel.add(tabbedPane);
        tabbedPane.add("album GUI", new GUIOFAlbum(Main.ablumDateModel).getPanel());// the new has to be the object name - have to add getPanel to the reference GUI file
        tabbedPane.add("Consignor GUI", new ConsignorGUI(Main.consignorDateModel).getPanel());
        tabbedPane.add("Sale GUI", new SaleGUI(Main.salesDateModel).getPanle());
        //tabbedPane.add("sale price", new CONAlSale(Main.salepriceDataModel).getJPanel());

        setVisible(true);
        pack();
    }

}
