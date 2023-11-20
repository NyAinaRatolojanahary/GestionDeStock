/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.ConnectBase;
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
public class SortieStock {
    
    private String idSortie;
    private Date dateSortie;
    private int idArticle;
    private double quantite;
    private String magasin;
    
    private Mouvement mvt;

    public Mouvement getMvt() {
        return mvt;
    }

    public void setMvt(Mouvement mvt) {
        this.mvt = mvt;
    }
    

    public String getIdSortie() {
        return idSortie;
    }

    public void setIdSortie(String idSortie) {
        this.idSortie = idSortie;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }
    
    public void setDateSortie(String dateSortie) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateSaisie = LocalDate.parse(dateSortie, formatter);
        LocalDate aujourdHui = LocalDate.now(); // Get current date

        if (dateSaisie.isBefore(aujourdHui)) {
            this.dateSortie = Date.valueOf(dateSaisie);
        } else {
            System.out.println("La date de sortie doit être antérieure à aujourd'hui.");
        }
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    
    public void setQuantite(String quantite) throws Exception{
        if(!quantite.isEmpty()){
            double qte =  Double.parseDouble(quantite);
                if(qte<0){ throw new Exception("La quantite doit etre positif");}
                else{ this.quantite = qte;}
        }
        else{throw new Exception("La quantite ne doit pas etre vide");}
    }

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public SortieStock() {}

    public SortieStock(String idSortie, Date dateSortie, int idArticle, double quantite, String magasin) {
        this.idSortie = idSortie;
        this.dateSortie = dateSortie;
        this.idArticle = idArticle;
        this.quantite = quantite;
        this.magasin = magasin;
    }

    public SortieStock(Date dateSortie, int idArticle, double quantite, String magasin) {
        this.dateSortie = dateSortie;
        this.idArticle = idArticle;
        this.quantite = quantite;
        this.magasin = magasin;
    }
    
    public void sortieStock(Date dt,int idart,double qte,String mg){
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        String sql1 = "Insert into sortieStock(idSortieStock,dateSortie,idArticle,quantite,idMagasin) values('SS'|| nextval('seqSortieStock'),'"+ dt+"',"+ idart +","+ qte +",'"+ mg +"');";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            st.execute(sql1);
        }
        catch(Exception e){
            
            e.printStackTrace();
        }
        finally{
            if(st!=null)try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(SortieStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(SortieStock.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }
    
    public String getIdSortieStock(int idArticle,Date dateSortie,String idMagasin){
        String id = new String();
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = " select idSortieStock from sortieStock where dateSortie='"+ dateSortie+"' AND idArticle ="+ idArticle +" AND idMagasin ='"+ idMagasin +"';";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                SortieStock ss = new SortieStock();
                ss.setIdSortie(rs.getString("idSortieStock"));
                id = ss.getIdSortie();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null)try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(SortieStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(st!=null)try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(SortieStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(SortieStock.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        return id;
    }
    
    public void etapeSortie(ArrayList<EntreeStock> lses,double qte,Date dateSortie, int idArticle,String idMagasin){
        double qtte = qte;
        for(int i=0; i< lses.size(); i++){           
            if(qtte<lses.get(i).getReste()){
                try{
                    SortieStock ss = new SortieStock();
                    ss.sortieStock(dateSortie, idArticle, qtte, idMagasin);
                    double reste = lses.get(i).getReste() - qtte;
                    EntreeStock es = new EntreeStock();
                    es.updateResteEntreeStock(reste, lses.get(i).getIdEntree());
                    String idSS = ss.getIdSortieStock(idArticle, dateSortie, idMagasin);
                    Mouvement mv = new Mouvement(lses.get(i).getIdEntree(),idSS,reste);
                    mv.insertMouvementStock(mv);
                    break;
                }
                catch(Exception e){ e.printStackTrace();}
            }
            else if(qtte>lses.get(i).getReste()){
                try{
//                    SortieStock ss = new SortieStock();
//                    double nvQteDispo = qtte - lses.get(i).getReste();
//                    ss.sortieStock(dateSortie, idArticle, lses.get(i).getReste(), idMagasin);
//                    double QteRestanteES = 0.0;
//                    EntreeStock es = new EntreeStock();
//                    es.updateResteEntreeStock(QteRestanteES, lses.get(i).getIdEntree());
//                    String idSS = ss.getIdSortieStock(idArticle, dateSortie, idMagasin);
//                    Mouvement mv = new Mouvement(lses.get(i+1).getIdEntree(),idSS,nvQteDispo);
//                    mv.insertMouvementStock(mv);
//                    i++;
//                    qtte = nvQteDispo;
    
                       SortieStock ss = new SortieStock();
                       ss.sortieStock(dateSortie, idArticle, lses.get(i).getReste(), idMagasin);
                       double qteRestante1 = qtte - lses.get(i).getReste();
                       double resteStock1 = qteRestante1 - qteRestante1;
                       EntreeStock es = new EntreeStock();
                       es.updateResteEntreeStock(resteStock1, lses.get(i).getIdEntree());
                       String idSS = ss.getIdSortieStock(idArticle, dateSortie, idMagasin);
                       es.updateResteEntreeStock(lses.get(i).getReste()- qteRestante1, lses.get(i+1).getIdEntree());
                       Mouvement mv = new Mouvement(lses.get(i).getIdEntree(),idSS,resteStock1);
                       mv.insertMouvementStock(mv);
                       qtte = lses.get(i).getReste()-qteRestante1;
                }
                catch(Exception e){e.printStackTrace();}
            }
        }
    }
    
    
    
    
}
