package com.example.act3uf2;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Clients {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long idClient;

    private String nom;

    private String DNI;

    private String pais;

    private String email;

    @OneToMany(mappedBy = "client")
    private List<Comptes> comptes;


    //////////////////////////////////////////////////////////////////////////////////

    public Clients(String nom, String DNI, String pais, String email, List<Comptes> comptes) {
        this.nom = nom;
        this.DNI = DNI;
        this.pais = pais;
        this.email = email;
        this.comptes = comptes;
    }

    public Clients(long idClient, String nom, String DNI, String pais, String email, List<Comptes> comptes) {
        this.idClient = idClient;
        this.nom = nom;
        this.DNI = DNI;
        this.pais = pais;
        this.email = email;
        this.comptes = comptes;
    }

    public Clients(String nom, String DNI, String pais, String email) {
        this.nom = nom;
        this.DNI = DNI;
        this.pais = pais;
        this.email = email;
    }

    public Clients() {
    }

    /////////////////////////////////////////////////////////////////////////////////


    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Comptes> getComptes() {
        return comptes;
    }

    public void setComptes(List<Comptes> comptes) {
        this.comptes = comptes;
    }

    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Clients{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", DNI='" + DNI + '\'' +
                ", pais='" + pais + '\'' +
                ", comptes=" + comptes +
                '}';
    }
}
