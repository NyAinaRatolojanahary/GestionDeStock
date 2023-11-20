/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Ny Aina Ratolo
 */
public class Stock {
    
    private String reference;
    private String nomArticle;
    private double quantiteInitial;
    private double quantiteSortie;
    private double quantiteRestante;
    private double PU;
    private double Montant;
    public String nomMagasin;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public double getQuantiteInitial() {
        return quantiteInitial;
    }

    public void setQuantiteInitial(double quantiteInitial) {
        this.quantiteInitial = quantiteInitial;
    }

    public double getQuantiteSortie() {
        return quantiteSortie;
    }

    public void setQuantiteSortie(double quantiteSortie) {
        this.quantiteSortie = quantiteSortie;
    }

    public double getQuantiteRestante() {
        return quantiteRestante;
    }

    public void setQuantiteRestante(double quantiteRestante) {
        this.quantiteRestante = quantiteRestante;
    }

    public double getPU() {
        return PU;
    }

    public void setPU(double PU) {
        this.PU = PU;
    }

    public double getMontant() {
        return Montant;
    }

    public void setMontant(double Montant) {
        this.Montant = Montant;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

    public Stock() {}
    
    public Stock(String reference, String nomArticle, double quantiteInitial, double quantiteSortie, double quantiteRestante, double PU, double Montant, String nomMagasin) {
        this.reference = reference;
        this.nomArticle = nomArticle;
        this.quantiteInitial = quantiteInitial;
        this.quantiteSortie = quantiteSortie;
        this.quantiteRestante = quantiteRestante;
        this.PU = PU;
        this.Montant = Montant;
        this.nomMagasin = nomMagasin;
    }
    
    
    
}
