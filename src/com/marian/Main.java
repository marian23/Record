package com.marian;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Main {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";        //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/Record";     //Connection string – where's the database?
    static final String USER = "root";   //TODO replace with your username
    static final String PASSWORD = "maan2013";   //TODO replace with your password

    static Connection conn = null;
    static ResultSet rs = null;

    static Statement statement = null;
    public final static String Album = "Album_table";
    public final static String albumID = "albumID";
    public final static String Title = "title";
    public final static String Artist = "artist";
    public final static String category = "category";
    public final static String sellingPrice = "sellingPrice";

    public final static String Consignor = "Consignor_Table";
    public final static String PK_column = "consignorID";
    public final static String consignorName = "ConsignorName";
    public final static String consignorPhoneNumber = "PhoneNumber";
    public final static String consignorPay = "consignorPay";
    public final static String consignorOwn = "consignorOwn";

    public final static String Sale = "Sale_table";
    public final static String sale_date = "sale_date";
    public final static String amount = "amountSold";
    static LinkedList<Statement> allStatements = new LinkedList<Statement>();
    //public final static
    private static ablumDateModel ablumDateModel;
    //Statement statement = null;


    PreparedStatement psInsert = null;

    public static void main(String[] args) {
        //Statement statement = null;


        //PreparedStatement psInsert = null;
        ///try {
        if (!setup()) {
            System.exit(-1);
        }
        if (!loadAllRecord()) {
            System.exit(-1);
        }
        System.out.println();


        //}


        AlbumGUI albumGUI = new AlbumGUI(ablumDateModel);
        }

    public static boolean loadAllRecord() {
        try {
            if (rs != null) {
                rs.close();
            }
            String alldata = "SELECT * FROM  Album"; //+ Album;
            String adddat = "SELECT * FROM  Consignor";
            String adddatas = "SELECT * FROM Sale";
            rs = statement.executeQuery(alldata);
            rs = statement.executeQuery(adddat);
            rs = statement.executeQuery(adddatas);
            if (ablumDateModel == null) {
                ablumDateModel = new ablumDateModel(rs);
            } else {
                ablumDateModel.updateResultSet(rs);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error loading or reloading album, consignor, sale");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }

    }


    //String alldata = "SELECT * FROM" + Album;
    //String adddat = "SELECT * FROM" + Consignor;
    //String adddatas = "SELECT * FROM" + Sale;
    //rs = statement.executeQuery(alldata);
    //rs = statement.executeQuery(adddat);
    //rs = statement.executeQuery(adddatas);

    public static boolean setup() {
        try {


            try {
                //Instantiate the driver
                Class.forName(JDBC_DRIVER);

            } catch (ClassNotFoundException cnfe) {
                System.out.println("Can't instantiate driver class; check you have drives and classpath configured correctly?");
                cnfe.printStackTrace();
                System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
            }


            try {

                conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
                statement = conn.createStatement();
                allStatements.add(statement);
                System.out.println("my project of the  record store ");


//create table for album, consignor and sale
                String createTable = "CREATE TABLE if NOT EXISTS " + Album + "( albumID int NOT NULL AUTO_INCREMENT PRIMARY KEY , Title varchar(50), Artist varchar(60),  category varchar(40), sellingPrice DOUBLE, isBasement Bool)";

                String deletTable = "DROP TABLE album";
                String create = "CREATE TABLE if NOT EXISTS " + Consignor + " (consignorID INT NOT NULL AUTO_INCREMENT PRIMARY KEY , consignorName varchar(60), phoneNumber VARCHAR(11) NOT NULL , consignorPay DOUBLE, consignorOwn DOUBLE) ";
                String deletetabel = "DROP TABLE consignor";
                String createsaleTaple = "CREATE TABLE if NOT EXISTS " + Sale + " (saleID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, sale_date date, amountSold Double, albumID int, FOREIGN KEY (albumID) REFERENCES Album(albumID), consignorID INT, FOREIGN KEY (consignorID) REFERENCES consignor(consignorID))";
                String deletesql = "DROP TABLE " + Sale;
//create insert data to test the tables
                String addsql = "INSERT INTO " + Album + "(" + Title + ", " + Artist + ", " + category + "," + sellingPrice + ")" + " VALUEs ('deep do', 'jim', 'MPLS', 3.33  )";

                String addtable = "INSERT INTO " + Consignor + " (" + consignorName + ", " + consignorPhoneNumber + ", " + consignorPay + ", " + consignorOwn + ")" + " Values ('john', '6126449988', 4.50, 3.49)";

                String addSale = "INSERT INTO " + Sale + "(" + sale_date + ", " + amount + ")" + " VALUES ('2014-08-31', 12.33)";


                try {
                    statement.executeUpdate(createTable);
                    statement.executeUpdate(addsql);
                    System.out.println("CREATE TABLE Album ");

                    statement.executeUpdate(create);
                    System.out.println("CREAT TABLE consignor");
                    statement.executeUpdate(addtable);

                    statement.executeUpdate(createsaleTaple);
                    System.out.println("CREATE TABLE Sale");
                    statement.executeUpdate(addSale);


                } catch (SQLDataException sql) {
                    if (sql.getSQLState().startsWith("XO")) {
                        System.out.println("Album table appears to exist already, delete and recreate");
                        statement.executeUpdate(deletTable);
                        statement.executeUpdate(createTable);
                        statement.executeUpdate(deletetabel);
                        statement.executeUpdate(create);
                        statement.executeUpdate(deletesql);
                        statement.executeUpdate(createsaleTaple);
                    } else {
                        throw sql;
                    }


                    //} catch (SQLException sqle) {
                    // statement.executeUpdate(deletTable);
                    //statement.executeUpdate(createTable);
                    //statement.executeUpdate(deletetabel);
                    //statement.executeUpdate(createsql);
                    //statement.executeUpdate(deletesql);
                    //statement.executeUpdate(createsaleTaple);

                }


            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }


        } catch (Exception e) {

        }
        return true;


    }

    public static void addToAlbum(Album album) {
        String add = "INSERT INTO albums (Title, Artist,  category, sellingPrice) VALUES (?, ?, ?, ?)";
        try {


            PreparedStatement prep = conn.prepareStatement(add);
            prep.setString(1, album.getTitle());
            prep.setString(2, album.getArtist());
            prep.setString(3, album.getCategory());
            prep.setDouble(4, album.getSellingPrice());

            prep.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addtoConsignor(Consignor consignor) {
        String addconsignor = "INSERT INTO Consignor (consignorName, phoneNumber,consignorPay, consignorOwn) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement prep = conn.prepareStatement(addconsignor);
            prep.setString(1, consignor.getConsignorName());
            prep.setString(2, consignor.getPhoneNumber());
            prep.setDouble(3, consignor.getConsignorPay());
            prep.setDouble(4, consignor.getConsignorOwn());

            prep.executeUpdate();

            System.out.println("Successfully added");


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addSale(Sale sale) {
        String addSale = "INSERT INTO sale(sale_date, amountSold) VALUES (?, ?)";
        try {
            PreparedStatement prep = conn.prepareStatement(addSale);
            prep.setDate(1, (Date) sale.getSale_date());
            prep.setDouble(2, sale.getAmount());
            prep.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static void shutdown() {
        try {
            if (rs != null) {
                rs.close();  //Close result set
                System.out.println("ResultSet closed");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        //Close all of the statements. Stored a reference to each statement in allStatements so we can loop over all of them and close them all.
        for (Statement s : allStatements) {

            if (s != null) {
                try {
                    s.close();
                    System.out.println("Statement closed");
                } catch (SQLException se) {
                    System.out.println("Error closing statement");
                    se.printStackTrace();
                }
            }
        }

        try {
            if (conn != null) {
                conn.close();  //Close connection to database
                System.out.println("Database connection closed");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }
}
