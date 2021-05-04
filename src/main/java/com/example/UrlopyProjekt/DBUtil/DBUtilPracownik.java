package com.example.UrlopyProjekt.DBUtil;

import com.example.UrlopyProjekt.Urlop;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtilPracownik extends DBUtil{

    private DataSource dataSource;
    private int id;
    private String nazwisko;
    private int rok_zatrudnienia;
    private String login;
    private String haslo;
    private int dni_uropu;
    private String url ="jdbc:mysql://localhost:3306/obsluga_urlopow?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET";


    public DBUtilPracownik(DataSource dataSource) throws Exception {

        this.dataSource = dataSource;
    }

    public boolean verify(String login, String haslo) {

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean flag = false;

        try {
            // polaczenie z BD
            conn = dataSource.getConnection();

            // wyrazenie SQL
            String sql = "SELECT * FROM pracownicy where login= \'"+login+"\'";

            statement = conn.prepareStatement(sql);

            statement.execute();
            // wykonanie wyrazenia SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                this.id = resultSet.getInt("id");
                this.haslo = resultSet.getString("haslo");
                System.out.println(this.haslo+" =? "+haslo);
                this.nazwisko = resultSet.getString("nazwisko");
                this.rok_zatrudnienia = resultSet.getInt("rok_zatrudnienia");
                this.dni_uropu = resultSet.getInt("dni_urlopu");

                this.login = login;
            }
            if(this.haslo.equals(haslo))
                flag=true;

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }finally {

                // zamkniecie obiektow JDBC
                close(conn, statement, resultSet);
        }

        return flag;
    }


    @Override
    List<Urlop> getUrlopy() throws Exception {

        List<Urlop> urlopy = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            // polaczenie z BD
            conn = dataSource.getConnection();

            // wyrazenie SQL
            String sql = "SELECT * FROM zarezerwowane";
            statement = conn.createStatement();

            // wykonanie wyrazenia SQL
            resultSet = statement.executeQuery(sql);

            // przetworzenie wyniku zapytania
            while (resultSet.next()) {

                // pobranie danych z rzedu
                int id = resultSet.getInt("id");
                int id_pracownicy = resultSet.getInt("id_pracownicy");
                String data_poczatek = (resultSet.getString("data_poczatek"));
                String data_koniec = (resultSet.getString("data_koniec"));
                String data_poczatek_new = (resultSet.getString("data_poczatek_new"));
                String data_koniec_new = (resultSet.getString("data_poczatek_new"));
                String stan = resultSet.getString("stan");
                boolean zaakceptowane = resultSet.getBoolean("zaakceptowane");


                // dodanie do listy nowego obiektu
                urlopy.add(new Urlop(id,id_pracownicy, data_poczatek, data_koniec, data_poczatek_new, data_koniec_new, stan, zaakceptowane));

            }

        } finally {

            // zamkniecie obiektow JDBC
            close(conn, statement, resultSet);
        }
        return urlopy;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public int getId() {
        return id;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getRok_zatrudnienia() {
        return rok_zatrudnienia;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public int getDni_uropu() {
        return dni_uropu;
    }
}
