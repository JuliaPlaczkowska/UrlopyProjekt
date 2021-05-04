package com.example.UrlopyProjekt;

import java.time.LocalDate;

public class Urlop {

    private int id;
    private int id_pracownicy;
    private String data_poczatek;
    private String data_koniec;
    private String data_poczatek_new;
    private String data_koniec_new;
    private String stan;
    private boolean zaakceptowane;

    public Urlop(int id_pracownicy, String data_poczatek, String data_koniec, String data_poczatek_new, String data_koniec_new, String stan, boolean zaakceptowane) {
        this.id_pracownicy = id_pracownicy;
        this.data_poczatek = data_poczatek;
        this.data_koniec = data_koniec;
        this.data_poczatek_new = data_poczatek_new;
        this.data_koniec_new = data_koniec_new;
        this.stan = stan;
        this.zaakceptowane = zaakceptowane;
    }

    public Urlop(int id, int id_pracownicy, String data_poczatek, String data_koniec, String data_poczatek_new, String data_koniec_new, String stan, boolean zaakceptowane) {
        this.id = id;
        this.id_pracownicy = id_pracownicy;
        this.data_poczatek = data_poczatek;
        this.data_koniec = data_koniec;
        this.data_poczatek_new = data_poczatek_new;
        this.data_koniec_new = data_koniec_new;
        this.stan = stan;
        this.zaakceptowane = zaakceptowane;
    }

    public Urlop() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pracownicy() {
        return id_pracownicy;
    }

    public void setId_pracownicy(int id_pracownicy) {
        this.id_pracownicy = id_pracownicy;
    }

    public String getData_poczatek() {
        return data_poczatek;
    }

    public void setData_poczatek(String data_poczatek) {
        this.data_poczatek = data_poczatek;
    }

    public String getData_koniec() {
        return data_koniec;
    }

    public void setData_koniec(String data_koniec) {
        this.data_koniec = data_koniec;
    }

    public String getData_poczatek_new() {
        return data_poczatek_new;
    }

    public void setData_poczatek_new(String data_poczatek_new) {
        this.data_poczatek_new = data_poczatek_new;
    }

    public String getData_koniec_new() {
        return data_koniec_new;
    }

    public void setData_koniec_new(String data_koniec_new) {
        this.data_koniec_new = data_koniec_new;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public boolean isZaakceptowane() {
        return zaakceptowane;
    }

    public void setZaakceptowane(boolean zaakceptowane) {
        this.zaakceptowane = zaakceptowane;
    }
}

