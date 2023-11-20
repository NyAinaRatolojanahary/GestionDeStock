/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.ConnectBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ny Aina Ratolo
 */
public class Article {
    
    private int idArticle;
    private String nom;
    private String reference;
    private int unite;
    private int typeSortie;

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws Exception {
        if(!nom.isEmpty()){
            this.nom = nom;
        }
        else{ throw new Exception("Erreur , le nom ne doit pas etre vide");}
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) throws Exception{
        if(!reference.isEmpty()){
            this.reference = reference;
        }
        else{ throw new Exception("Erreur , la reference ne doit pas etre vide");}
    }

    public int getUnite() {
        return unite;
    }

    public void setUnite(int unite) {
        this.unite = unite;
    }
    
    public void setUnite(String unite) throws Exception{
        if(!unite.isEmpty()){
            int unit = Integer.parseInt(unite);
            this.unite = unit;
        }
        else{ throw new Exception("Erreur, l'unite ne doit pas etre nulle");}
    }

    public int getTypeSortie() {
        return typeSortie;
    }

    public void setTypeSortie(int typeSortie) {
        this.typeSortie = typeSortie;
    }
    
    public void setTypeSortie(String typeSortie) throws Exception {
        if(!typeSortie.isEmpty()){
            int TS = Integer.parseInt(typeSortie);
            this.typeSortie = TS;
        }
        else{ throw new Exception("Erreur, le type de sortie ne doit pas etre vide");}
    }

    public Article() {}

    public Article(int idArticle, String nom, String reference, int unite, int typeSortie) {
        this.idArticle = idArticle;
        this.nom = nom;
        this.reference = reference;
        this.unite = unite;
        this.typeSortie = typeSortie;
    }

    public Article(String nom, String reference, int unite, int typeSortie) {
        this.nom = nom;
        this.reference = reference;
        this.unite = unite;
        this.typeSortie = typeSortie;
    }

    public Article(String nom, String reference) {
        this.nom = nom;
        this.reference = reference;
    }
    
    
    public ArrayList<Article> getAllArticle(){
        ArrayList<Article> art = new ArrayList<Article>();
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = " select * from article;";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Article ar = new Article();
                ar.setIdArticle(Integer.parseInt(rs.getString("idArticle")));
                ar.setNom(rs.getString("nomArticle"));
                ar.setReference(rs.getString("reference"));
                ar.setTypeSortie(rs.getString("modeSortie"));
                ar.setUnite(rs.getString("idUnite"));
                art.add(ar);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null)try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(st!=null)try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        return art;
    }
    
    public Article getArticleById(String ida){
        Article art = new Article();
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = " select * from article where idArticle="+ ida +";";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Article ar = new Article();
                ar.setIdArticle(Integer.parseInt(rs.getString("idArticle")));
                ar.setNom(rs.getString("nomArticle"));
                ar.setReference(rs.getString("reference"));
                ar.setTypeSortie(rs.getString("modeSortie"));
                ar.setUnite(rs.getString("idUnite"));
                art = ar;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null)try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Magasin.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(st!=null)try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(Magasin.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Magasin.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        return art;
    }
    
    
    public int estFIFOouLIFO(int id){
    
        int modesortie = 0;
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = " select modeSortie from article where idArticle="+ id +";";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Article ar = new Article();
                ar.setTypeSortie(Integer.parseInt(rs.getString("modeSortie")));
                modesortie = ar.getTypeSortie();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null)try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(st!=null)try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        return modesortie;
    }
    
    
    
}
