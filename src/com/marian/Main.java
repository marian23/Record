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
    static ResultSet comboResults;
    static ResultSet CombosResults;

    static Statement statement = null;
    static Statement queriey = null;
    static Statement queries = null;
    static  Statement statementconn = null;
    static Statement statementconsignor = null;

    public final static String Album = "Album_table";
    public final static String albumID = "albumID";
    public final static String Title = "title";
    public final static String Artist = "artist";
    public final static String category = "category";
    public final static String sellingPrice = "sellingPrice";
    public final static String isBasement = "isBasement";

    public final static String Consignor = "Consignor_Table";
    public final static String PK_column = "consignorID";
    public final static String consignorName = "ConsignorName";
    public final static String consignorPhoneNumber = "PhoneNumber";
    public final static String consignorPay = "consignorPay";
    public final static String consignorOwn = "consignorOwn";

    public final static String Sale = "Sale_table";
    public final static String sale_date = "sale_date";
    public final static String amount = "amountSold";

    static String saleprice = "salePrice";

    static LinkedList<Statement> allStatements = new LinkedList<Statement>();
    //public final static
    public static ablumDateModel ablumDateModel;
    public static ablumDateModel consignorDateModel;
    public static ablumDateModel salesDateModel;
    public static ablumDateModel salepriceDataModel;

    public static ResultSet consignerResult = null;
    public static Statement consState = null;
    public static ResultSet salesResult = null;
    public static Statement salesState = null;
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
        if (!loadAllConsignor()) {
            System.exit(-1);
        }
        if (!loadAllSales()) {
            System.exit(-1);
        }
        System.out.println();


        //}




        //AlbumGUI albumGUI = new AlbumGUI(ablumDateModel);
tabbedJframe tabbedJframe = new tabbedJframe();
        //GUIOFAlbum guiofAlbum = new GUIOFAlbum(ablumDateModel);
        //ConsignorGUI consignorGUI = new ConsignorGUI(ablumDateModel);
        //SaleGUI saleGUI = new SaleGUI(ablumDateModel);
        }

    public static boolean loadAllRecord() {
        try {
            if (rs != null) {
                rs.close();
            }
            String alldata = "SELECT * FROM " + Album;
            rs = statement.executeQuery(alldata);
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

    public static boolean loadAllConsignor() {
        try {
            if (consignerResult != null) {
                consignerResult.close();
            }
            String adddat = "SELECT * FROM  " + Consignor;
            consignerResult = consState.executeQuery(adddat);
            if (consignorDateModel == null) {
                consignorDateModel = new ablumDateModel(consignerResult);
            } else {
                consignorDateModel.updateResultSet(consignerResult);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error loading or reloading album, consignor, sale");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }

    }

    public static boolean loadAllSales() {
        try {
            if (salesResult != null) {
                salesResult.close();
            }
            String adddatas = "SELECT * FROM " + Sale;
            salesResult = salesState.executeQuery(adddatas);
            if (salesDateModel == null) {
                salesDateModel = new ablumDateModel(salesResult);
            } else {
                salesDateModel.updateResultSet(salesResult);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error loading or reloading album, consignor, sale");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }

    }
    public static boolean loadAllsaleprice() {
        try {
            if (rs != null) {
                rs.close();
            }
            String alldata = "SELECT * FROM " + saleprice;
            rs = statement.executeQuery(alldata);
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
                statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                consState = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                salesState = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                queriey = conn.createStatement();
                queries = conn.createStatement();
                allStatements.add(statement);
                statementconn = conn.createStatement();
                statementconsignor = conn.createStatement();
                System.out.println("my project of the  record store ");


//create table for album, consignor and sale
                String createTable = "CREATE TABLE IF NOT EXISTS " + Album + "( albumID int NOT NULL AUTO_INCREMENT PRIMARY KEY , Title varchar(50), Artist varchar(60),  category varchar(40), sellingPrice DOUBLE, isBasement Bool)";

                String deletTable = "DROP TABLE " + Album;
                String create = "CREATE TABLE IF NOT EXISTS " + Consignor + " (consignorID INT NOT NULL AUTO_INCREMENT PRIMARY KEY , consignorName varchar(60), phoneNumber VARCHAR(11) NOT NULL , consignorPay DOUBLE, consignorOwn DOUBLE) ";
                String deletetabel = "DROP TABLE " + Consignor;
                String createsaleTaple = "CREATE TABLE IF NOT EXISTS " + Sale + " (saleID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, sale_date date, amountSold Double, albumID int, FOREIGN KEY (albumID) REFERENCES " + Album + "(albumID), consignorID INT, FOREIGN KEY (consignorID) REFERENCES " + Consignor + " (consignorID))";
                String deletesql = "DROP TABLE " + Sale;
//create insert data to test the tables



                try {




                    statement.executeUpdate(createTable);
                    System.out.println("CREATE TABLE Album ");

                    statement.executeUpdate(create);
                    System.out.println("CREAT TABLE consignor");


                    statement.executeUpdate(createsaleTaple);
                    System.out.println("CREATE TABLE Sale");

                    //createTestData();

                } catch (SQLException sql) {
                    if (sql.getSQLState().startsWith("X0")) {
                        System.out.println("Album table appears to exist already, delete and recreate");
                        statement.executeUpdate(deletesql);
                        statement.executeUpdate(deletTable);
                        statement.executeUpdate(deletetabel);
                        //statement.executeUpdate(deletesql);
                        statement.executeUpdate(createTable);
                        //statement.executeUpdate(deletetabel);
                        statement.executeUpdate(create);
                        //statement.executeUpdate(deletesql);
                        statement.executeUpdate(createsaleTaple);
                    } else {
                        throw sql;


                    }


//                    } catch (SQLException sqle) {
//                    statement.executeUpdate(deletTable);
//                    statement.executeUpdate(deletetabel);
//                    statement.executeUpdate(deletesql);
//                    statement.executeUpdate(createsaleTaple);
//
//                    statement.executeUpdate(create);
//                    statement.executeUpdate(createTable);




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
        String add = "INSERT INTO " + Album + " (Title, Artist,  category, sellingPrice) VALUES (?, ?, ?, ?)";
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
        String addconsignor = "INSERT INTO " + Consignor + " (consignorName, phoneNumber,consignorPay, consignorOwn) VALUES (?, ?, ?, ?)";

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

    public static void createTestData() {
        String addsql = "INSERT INTO " + Album + "(" + Title + ", " + Artist + ", " + category + "," + sellingPrice +  "," + isBasement + ")" + " VALUEs ('deep do', 'jim', 'MPLS', 3.33, FALSE  )";

        String addtable = "INSERT INTO " + Consignor + " (" + consignorName + ", " + consignorPhoneNumber + ", " + consignorPay + ", " + consignorOwn + ")" + " Values ('john', '6126449988', 4.50, 3.49)";

        String addSale = "INSERT INTO " + Sale + "(" + sale_date + ", " + amount + ")" + " VALUES ('2014-08-31', 12.33)";



    try {
        statement.executeUpdate(addsql);
        statement.executeUpdate(addtable);
        statement.executeUpdate(addSale);

    }catch (SQLException se) {
        se.printStackTrace();

    }
    }
    public static void addSale(Sale sale) {
        String addSale = "INSERT INTO " + Sale + "(sale_date, amountSold , albumID, consignorID) VALUES (?, ?, ?,?)";
        Date sqlDate = new Date(sale.getSale_date().getTime());
        try {
            PreparedStatement prep = conn.prepareStatement(addSale);
            prep.setDate(1, sqlDate);
            prep.setDouble(2, sale.getAmount());
            prep.setInt(3, sale.getConsignorID());
            prep.setInt(4, sale.getAlbumID());
            prep.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static HashMap getConsignor() {
        String consignors = "SELECT consignorID , consignorName  FROM " + Consignor;
        HashMap<Integer, String> consignorHash = new HashMap<>();
        try {
            comboResults = queriey.executeQuery(consignors);
            while (comboResults.next()){
                String name = comboResults.getString("consignorName");
                int id = comboResults.getInt("consignorID");
                consignorHash.put(id, name);
            }


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return consignorHash;
    }
    public void selectitem(){
        try {

            String update = "UPDATE  Album set isBasement = true WHERE  recieveDate < CURRENT_DATE -30";
            statement.executeUpdate(update);
            String add = "SELECT consignorName, Title FROM Consignor, Album WHERE  Consignor.albumID = Album.ID AND isBasement = TRUE ";
            statement.execute(add);
        }
        catch (SQLException se) {
        se.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();

    }
    }
    public  static HashMap getAlbum() {
        String albums = "SELECT albumID, Title FROM " + Album;
        HashMap<Integer, String> albumHash = new HashMap<>();
        try {
            CombosResults = queries.executeQuery(albums);
            while (CombosResults.next()) {
                String names = CombosResults.getString("Title");
                int ID = CombosResults.getInt("albumID");
                albumHash.put(ID, names);

            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return albumHash;
    }

    public static void searchAlbum(String column, String search){
        String searchString = "Select * From " + Album + " Where " + column + " Like \"" + search + "%\"";
        try {
            rs = statement.executeQuery(searchString);
            if (ablumDateModel == null) {
                ablumDateModel = new ablumDateModel(rs);
            } else {
                ablumDateModel.updateResultSet(rs);
            }
        }catch (SQLException se) {
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
