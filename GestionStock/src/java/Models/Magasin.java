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
public class Magasin {
    
    private String idMagasin;
    private String nomMagasin;
    private String Localisation;
    private String Contact;

    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

    public String getLocalisation() {
        return Localisation;
    }

    public void setLocalisation(String Localisation) {
        this.Localisation = Localisation;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public Magasin() {}

    public Magasin(String idMagasin, String nomMagasin, String Localisation, String Contact) {
        this.idMagasin = idMagasin;
        this.nomMagasin = nomMagasin;
        this.Localisation = Localisation;
        this.Contact = Contact;
    }

    public Magasin(String nomMagasin, String Localisation, String Contact) {
        this.nomMagasin = nomMagasin;
        this.Localisation = Localisation;
        this.Contact = Contact;
    }

    public Magasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }
    
    
    
    public ArrayList<Magasin> getAllMagasin(){
        ArrayList<Magasin> mgs = new ArrayList<Magasin>();
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = " select * from magasin;";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Magasin mg = new Magasin();
                mg.setIdMagasin(rs.getString("idMagasin"));
                mg.setNomMagasin(rs.getString("nomMagasin"));
                mg.setLocalisation(rs.getString("localisation"));
                mg.setContact(rs.getString("contact"));
                mgs.add(mg);
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
        
        return mgs;
    }
    
    
    public Magasin getMagasinById(String idm){
        Magasin mgs = new Magasin();
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = " select * from magasin where idMagasin="+ idm +";";
        try{
            c = cb.connectToDataBase();
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Magasin mg = new Magasin();
                mg.setIdMagasin(rs.getString("idMagasin"));
                mg.setNomMagasin(rs.getString("nomMagasin"));
                mg.setLocalisation(rs.getString("localisation"));
                mg.setContact(rs.getString("contact"));
                mgs = mg;
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
        
        return mgs;
    }
    
}
