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
public class Unite {
    
    private int idUnite;
    private String nom;
    private String abbreviation;

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Unite() {}

    public Unite(int idUnite, String nom, String abbreviation) {
        this.idUnite = idUnite;
        this.nom = nom;
        this.abbreviation = abbreviation;
    }

    public Unite(String nom, String abbreviation) {
        this.nom = nom;
        this.abbreviation = abbreviation;
    }
    
    public ArrayList<Unite> getAllUnite(){
        ArrayList<Unite> unit = new ArrayList<Unite>();
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = " select * from unite;";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Unite ut = new Unite();
                ut.setIdUnite(Integer.parseInt(rs.getString("idUnite")));
                ut.setNom(rs.getString("nomUnite"));
                ut.setAbbreviation(rs.getString("abbreviation"));
                unit.add(ut);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null)try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Unite.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(st!=null)try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(Unite.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Unite.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        return unit;
    }
    
}
