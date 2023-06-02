package com.example.database_offline;

public class Contacts_Data
{
    private int id;
    private String name;
    private String number;

    public Contacts_Data(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public Contacts_Data() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
