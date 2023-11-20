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
public class EntreeStock {
    
    private Article art;
    private Magasin mg;
    private String idEntree;
    private Date dateEntree;
    private int idArticle;
    private double prixUnitaire;
    private double quantite;
    private String magasin;
    private double reste;
    
    private String nomArticle;

    public Article getArt() {
        return art;
    }

    public void setArt(Article art) {
        this.art = art;
    }

    public Magasin getMg() {
        return mg;
    }

    public void setMg(Magasin mg) {
        this.mg = mg;
    }
    
    

    public String getIdEntree() {
        return idEntree;
    }

    public void setIdEntree(String idEntree) {
        this.idEntree = idEntree;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }
    
    public void setDateEntree(String dateEntree) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateSaisie = LocalDate.parse(dateEntree, formatter);
        LocalDate aujourdHui = LocalDate.now(); // Get current date

        if (dateSaisie.isBefore(aujourdHui)) {
            this.dateEntree = Date.valueOf(dateSaisie);
        } else {
            System.out.println("La date d'entree doit être antérieure à aujourd'hui.");
        }
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    
    public void setPrixUnitaire(String prixUnitaire) throws Exception{
        if(!prixUnitaire.isEmpty()){
            double PU =  Double.parseDouble(prixUnitaire);
                if(PU<0){ throw new Exception("Le prix unitaire doit etre positif");}
                else{ this.prixUnitaire = PU;}
        }
        else{throw new Exception("Le prix unitaire ne doit pas etre vide");}
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

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public double getReste() {
        return reste;
    }

    public void setReste(double reste) throws Exception {
        if(reste== 0 || reste > 0){
            this.reste = reste;
        }
        else{ throw new Exception("Le reste ne doit pas etre en dessous de 0");}
    }
    
    

    public EntreeStock() {}

    public EntreeStock(String idEntree, Date dateEntree, int idArticle, double prixUnitaire, double quantite, String magasin,double reste) {
        this.idEntree = idEntree;
        this.dateEntree = dateEntree;
        this.idArticle = idArticle;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.magasin = magasin;
        this.reste = reste;
    }

    public EntreeStock(Date dateEntree, int idArticle, double prixUnitaire, double quantite, String magasin, double reste) {
        this.dateEntree = dateEntree;
        this.idArticle = idArticle;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.magasin = magasin;
        this.reste = reste;
    }

    public EntreeStock(String idEntree, Date dateEntree, int idArticle, double prixUnitaire, double quantite, String magasin, String nomArticle,double reste) {
        this.idEntree = idEntree;
        this.dateEntree = dateEntree;
        this.idArticle = idArticle;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.magasin = magasin;
        this.nomArticle = nomArticle;
        this.reste = reste;
    }

    public EntreeStock(Article art, Magasin mg, String idEntree, Date dateEntree, double prixUnitaire, double quantite, double reste) {
        this.art = art;
        this.mg = mg;
        this.idEntree = idEntree;
        this.dateEntree = dateEntree;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.reste = reste;
    }
    
    
    
    
    
    public ArrayList<EntreeStock> getEntreeStockByArticleEntre2Dates(int idArticle, Date d1, Date d2){
        ArrayList<EntreeStock> ets = new ArrayList<EntreeStock>();
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "Select es.idEntreeStock,es.dateEntree,art.idArticle,art.nomArticle,es.quantite,es.PU,es.reste,art.modeSortie,es.idMagasin from entreeStock as es join article as art on art.idArticle = es.idArticle where es.dateEntree='"+d1+"';";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                EntreeStock es = new EntreeStock();
                es.setIdEntree(rs.getString("idEntree"));
                es.setDateEntree(rs.getDate("dateEntree"));
                es.getArt().setReference(rs.getString("reference"));
                es.getArt().setNom(rs.getString("nomArticle"));
                es.setPrixUnitaire(rs.getString("PU"));
                es.setQuantite(rs.getDouble("quantite"));
                es.setReste(rs.getDouble("reste"));
                es.getMg().setNomMagasin(rs.getString("nomMagasin"));
                ets.add(es);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null)try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(st!=null)try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        return ets;
    }
    
    public void entreeStock(EntreeStock es){
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        String sql1 = "Insert into entreeStock(idEntreeStock,dateEntree,idArticle,PU,quantite,idMagasin,reste) values('ES'||nextval('seqEntreeStock'),'"+ es.getDateEntree()+"',"+ es.getIdArticle() +","+ es.getPrixUnitaire()+","+ es.getQuantite() +",'"+ es.getMagasin() +"',"+es.getReste() +");";
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
    
    public void updateResteEntreeStock(double reste,String idEntreeStock){
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        String sql1 = "Update entreeStock set reste="+reste+" where idEntreeStock = '"+ idEntreeStock+"';";
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
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }
    
    public ArrayList<EntreeStock> getListeEntreeStockParArticleFIFOEntre2Date(int idArticle,Date d1,Date d2){
        ArrayList<EntreeStock> lses = new ArrayList<EntreeStock>();
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select es.idEntreeStock,es.dateEntree,es.idArticle,art.reference,art.nomArticle,es.PU,es.quantite,es.reste,es.idMagasin,mg.nomMagasin from entreeStock as es join article as art on art.idArticle = es.idArticle join magasin as mg on mg.idMagasin=es.idMagasin where es.idArticle="+ idArticle +" AND art.modeSortie=1  AND es.reste>0 AND es.dateEntree BETWEEN '"+ d1 +"' AND '"+ d2 +"' order by es.dateEntree ASC;";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                EntreeStock es = new EntreeStock();
                es.setIdEntree(rs.getString("idEntree"));
                es.setDateEntree(rs.getDate("dateEntree"));
                es.setIdArticle(Integer.parseInt(rs.getString("idArticle")));
                es.setPrixUnitaire(rs.getString("PU"));
                es.setQuantite(rs.getString("quantite"));
                es.setMagasin(rs.getString("idMagasin"));
                es.setNomArticle(rs.getString("nomArticle"));
                lses.add(es);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null)try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(st!=null)try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        return lses;
    }
    
    public ArrayList<EntreeStock> getListeEntreeStockParArticleFIFODispo(int idArticle){
        ArrayList<EntreeStock> lses = new ArrayList<EntreeStock>();
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select es.idEntreeStock,es.dateEntree,es.idArticle,art.reference,art.nomArticle,es.PU,es.quantite,es.reste,es.idMagasin,mg.nomMagasin from entreeStock as es join article as art on art.idArticle = es.idArticle join magasin as mg on mg.idMagasin=es.idMagasin where es.idArticle="+ idArticle +" AND art.modeSortie=1  AND es.reste>0 order by es.dateEntree ASC;";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                EntreeStock es = new EntreeStock();
                es.setIdEntree(rs.getString("idEntreeStock"));
                es.setDateEntree(rs.getDate("dateEntree"));
                es.setIdArticle(Integer.parseInt(rs.getString("idArticle")));
                es.setPrixUnitaire(rs.getString("PU"));
                es.setQuantite(rs.getString("quantite"));
                es.setMagasin(rs.getString("idMagasin"));
                es.setReste(Double.parseDouble(rs.getString("reste")));
                es.setNomArticle(rs.getString("nomArticle"));
                lses.add(es);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null)try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(st!=null)try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        return lses;
    }
    
    public ArrayList<EntreeStock> getListeEntreeStockParArticleLIFOEntre2Date(int idArticle,Date d1,Date d2){
        ArrayList<EntreeStock> lses = new ArrayList<EntreeStock>();
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select es.idEntreeStock,es.dateEntree,es.idArticle,art.reference,art.nomArticle,es.PU,es.quantite,es.reste,es.idMagasin,mg.nomMagasin from entreeStock as es join article as art on art.idArticle = es.idArticle join magasin as mg on mg.idMagasin=es.idMagasin where es.idArticle="+ idArticle +" AND art.modeSortie=-1  AND es.reste>0 '"+ d1 +"' AND '"+ d2 +"' order by es.dateEntree DESC;";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                EntreeStock es = new EntreeStock();
                es.setIdEntree(rs.getString("idEntree"));
                es.setDateEntree(rs.getDate("dateEntree"));
                es.setIdArticle(Integer.parseInt(rs.getString("idArticle")));
                es.setPrixUnitaire(rs.getString("PU"));
                es.setQuantite(rs.getString("quantite"));
                es.setMagasin(rs.getString("idMagasin"));
                es.setNomArticle(rs.getString("nomArticle"));
                lses.add(es);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null)try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(st!=null)try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        return lses;
    }
    
    public ArrayList<EntreeStock> getListeEntreeStockParArticleLIFODispo(int idArticle){
        ArrayList<EntreeStock> lses = new ArrayList<EntreeStock>();
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select es.idEntreeStock,es.dateEntree,es.idArticle,art.reference,art.nomArticle,es.PU,es.quantite,es.reste,es.idMagasin,mg.nomMagasin from entreeStock as es join article as art on art.idArticle = es.idArticle join magasin as mg on mg.idMagasin=es.idMagasin where es.idArticle="+ idArticle +" AND art.modeSortie=-1  AND es.reste>0 order by es.dateEntree DESC;";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                EntreeStock es = new EntreeStock();
                es.setIdEntree(rs.getString("idEntree"));
                es.setDateEntree(rs.getDate("dateEntree"));
                es.setIdArticle(Integer.parseInt(rs.getString("idArticle")));
                es.setPrixUnitaire(rs.getString("PU"));
                es.setQuantite(rs.getString("quantite"));
                es.setMagasin(rs.getString("idMagasin"));
                es.setNomArticle(rs.getString("nomArticle"));
                lses.add(es);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null)try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(st!=null)try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(EntreeStock.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        return lses;
    }
    
    
}
