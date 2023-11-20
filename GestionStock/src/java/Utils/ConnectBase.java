/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ny Aina Ratolo
 */
public class ConnectBase {
    
    public Connection connectToDataBase() throws Exception,SQLException, ClassNotFoundException{
        Connection c= null;

            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/stock","postgres","root");
                 System.out.println("Connecting...");
            } catch (Exception e) {
                 System.out.println(e);
                 throw e;
            }  
        return c;
    }
    
}
