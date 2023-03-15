package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.sql.*;
import static org.junit.Assert.*;


public class Stepdefinition {

    // JDBC (DB) testi yapilmaya baslamadan önce Sistem yöneticisi ile görüsülüp Database
    // bilgileri alinir.
    /*

            Database baglantisi icin gerekli bilgiler.
            type: jdbc:mysql
            host/ip: 45.84.206.41
            port:3306
            database: u480337000_tlb_training
            username: u480337000_tbl_training_u
            password: pO9#4bmxU

     */
        String url="jdbc:mysql://45.84.206.41:3306/u480337000_tlb_training";

        String username="u480337000_tbl_training_u";

        String password="pO9#4bmxU";

        // DataBase Sistem Yoneticisinden alinan bilgiler ile bir Url olusturuldu
        // Username ve password String data type 'inde bir veirable atandi

    Connection connection;
    Statement statement;
    ResultSet resultSet;


    @Given("Database ile iletisimi baslat")
    public void database_ile_iletisimi_baslat() throws SQLException {
      connection= DriverManager.getConnection(url,username,password);
        statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        // connection objemizi olusturduk ve (url,username, ve datalarini connection objesinin icine koyduk.)

        // olusturdugumuz connection objesini kullanarak typleri belli bikr statement create ettik

    }
    @Then("Query statement araciligi ile database gonderilir")
    public void query_statement_araciligi_ile_database_gonderilir() throws SQLException {
        String query="SELECT * FROM u480337000_tlb_training.users;";

        resultSet=statement.executeQuery(query);
        //statement araciligi ile DataBase e gonderdigimiz


    }
    @Then("Databaseden donen resulset verisi test edilir")
    public void databaseden_donen_resulset_verisi_test_edilir() throws SQLException {
        resultSet.first();
        System.out.println(resultSet.getString("first_name"));

        String actualname=resultSet.getNString("first_name");
        String expectedname="Super";
        assertEquals(expectedname,actualname);

        resultSet.next();
        System.out.println(resultSet.getString("first_name"));

        resultSet.absolute(11);
        System.out.println(resultSet.getString("first_name"));

        resultSet.absolute(11);
        System.out.println(resultSet.getString("email"));

        System.out.println("************************");

        resultSet.absolute(0);


        int sira=1;
        while (resultSet.next()) {
        System.out.println(sira+"--"+resultSet.getString("first_name"));
        sira++;

        }




    }
    @Then("Database kapatilir")
    public void database_kapatilir() throws SQLException {
      connection.close();
    }
    





}
