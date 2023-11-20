/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.ConnectBase;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ny Aina Ratolo
 */
public class EtatStock {
    
    private Date dateDebut;
    private Date dateFin;
//    private Stock[] listeStock;
    private ArrayList<Stock> listeStock;
    private double montant;
    
    public Stock stck;

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    public void setDateDebut(String dateDebut) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateSaisie = LocalDate.parse(dateDebut, formatter);
        LocalDate aujourdHui = LocalDate.now(); // Get current date

        if (dateSaisie.isBefore(aujourdHui)) {
            this.dateDebut = Date.valueOf(dateSaisie);
        } else {
            System.out.println("La date debut a une erreur.");
        }
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public void setDateFin(String dateFin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateSaisie = LocalDate.parse(dateFin, formatter);
        LocalDate aujourdHui = LocalDate.now(); // Get current date

        if (dateSaisie.isAfter(this.getDateDebut().toLocalDate())) {
            this.dateFin = Date.valueOf(dateSaisie);
        } else {
            System.out.println("La date fin a une erreur.");
        }
    }

//    public Stock[] getListeStock() {
//        return listeStock;
//    }

//    public void setListeStock(Stock[] listeStock) {
//        this.listeStock = listeStock;
//    }

    public ArrayList<Stock> getListeStock() {
        return listeStock;
    }

    public void setListeStock(ArrayList<Stock> listeStock) {
        this.listeStock = listeStock;
    }

    public Stock getStck() {
        return stck;
    }

    public void setStck(Stock stck) {
        this.stck = stck;
    }
    
    
    

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public EtatStock() {}

    public EtatStock(Date dateDebut, Date dateFin, ArrayList<Stock> listeStock, double montant) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.listeStock = listeStock;
        this.montant = montant;
    }

    public EtatStock getEtatStock(String d1,String d2,int idArticle,String idMagasin){
        ArrayList<Stock> lsets = new ArrayList<Stock>();
        EtatStock ets = new EtatStock();
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select art.reference,art.nomArticle,es.quantite as qi ,ss.quantite as qs,mv.quantiteDisponible,es.PU,(es.PU*mv.quantiteDisponible) as montant ,mg.nomMagasin\n" +
                    "from mouvement as mv\n" +
                    "join entreeStock as es on mv.idEntree = es.idEntreeStock\n" +
                    "join sortieStock as ss on mv.idSortie = ss.idSortieStock\n" +
                    "join article as art on es.idArticle = art.idArticle\n" +
                    "join magasin as mg on es.idMagasin = mg.idMagasin\n" +
                    "join unite as ut on art.idUnite = ut.idUnite\n" +
                    "where ss.idArticle ="+ idArticle+"\n" +
                    "AND es.idArticle ="+ idArticle+"\n" +
                    "AND es.idMagasin ='"+ idMagasin+"'\n" +
                    "AND ss.idMagasin ='"+ idMagasin+"' \n" +
                    "AND es.dateEntree BETWEEN '"+d1+"' AND '"+d2+"'\n" +
                    "AND ss.dateSortie BETWEEN '"+d1+"' AND '"+d2+"';";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            rs = st.executeQuery(sql);
            
            ets.setDateDebut(d1);
            ets.setDateFin(d2);
            while(rs.next()){
                Stock stc = new Stock();
                stc.setReference(rs.getString("reference"));
                stc.setNomArticle(rs.getString("nomArticle"));
                stc.setQuantiteInitial(rs.getDouble("qi"));
                stc.setQuantiteRestante(rs.getDouble("quantitedisponible"));
                stc.setQuantiteSortie(rs.getDouble("qs"));
                stc.setPU(rs.getDouble("PU"));
                stc.setMontant(rs.getDouble("montant"));
                stc.setNomMagasin(rs.getString("nomMagasin"));
                lsets.add(stc);
            }
            ets.setListeStock(lsets);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null)try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtatStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(st!=null)try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtatStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtatStock.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        return ets;
    }
    
    public  static void main(String[] args){
    
        EtatStock ts = new EtatStock();
        ts = ts.getEtatStock("2023-10-01", "2023-11-30", 2, "MG1");
        
//        for(int i=0; i< ts.getListeStock().size(); i++){
//           out.println(ts.getListeStock().get(i).getPU());
//        }
        
    }
    
    
}
