/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.ConnectBase;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ny Aina Ratolo
 */
public class Mouvement {
    
    private String idMouvement;
    private String idES;
    private String idSS;
    private double quantiteDispo;

    public String getIdMouvement() {
        return idMouvement;
    }

    public void setIdMouvement(String idMouvement) {
        this.idMouvement = idMouvement;
    }

    public String getIdES() {
        return idES;
    }

    public void setIdES(String idES) {
        this.idES = idES;
    }

    public String getIdSS() {
        return idSS;
    }

    public void setIdSS(String idSS) {
        this.idSS = idSS;
    }

    public double getQuantiteDispo() {
        return quantiteDispo;
    }

    public void setQuantiteDispo(double quantiteDispo) {
        this.quantiteDispo = quantiteDispo;
    }
    
    
    public Mouvement(){}

    public Mouvement(String idMouvement, String idES, String idSS, double quantiteDispo) {
        this.idMouvement = idMouvement;
        this.idES = idES;
        this.idSS = idSS;
        this.quantiteDispo = quantiteDispo;
    }

    public Mouvement(String idES, String idSS, double quantiteDispo) {
        this.idES = idES;
        this.idSS = idSS;
        this.quantiteDispo = quantiteDispo;
    }
    
    
    public void insertMouvementStock(Mouvement mt){
        
        ConnectBase cb = new ConnectBase();
        Connection c = null;
        Statement st = null;
        String sql1 = "Insert into mouvement(idMouvement,idEntree,quantiteDisponible,idSortie) values ('Mvt'|| nextval('seqMouvement'),'"+ mt.getIdES()+"',"+ mt.getQuantiteDispo()+",'"+ mt.getIdSS()+"');";
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
                Logger.getLogger(Mouvement.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(c!=null)try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Mouvement.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }
    
    
}
