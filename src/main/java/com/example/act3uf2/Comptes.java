package com.example.act3uf2;

import jakarta.persistence.*;

@Entity
public class Comptes {

    @Id
    private String iban;

    private long saldo;

    @ManyToOne()
    private Clients client;

    /////////////////////////////////////////////////////////////////////////////////


    public Comptes(String iban, long saldo, Clients client) {
        this.iban = iban;
        this.saldo = saldo;
        this.client = client;
    }

    public Comptes() {
    }
    /////////////////////////////////////////////////////////////////////////////////


    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }


    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }
}
