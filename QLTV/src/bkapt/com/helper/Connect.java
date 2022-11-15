/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.helper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author PC
 */
public class Connect {
    protected Connection con=null;
    public Connect(){
        try
        {
            
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:mysql://localhost:3306/QLTV2";
            con=DriverManager.getConnection(url,"root","26070833857552");
        }
           catch(Exception ex)
            {
                ex.printStackTrace();
            }   
    }
}
