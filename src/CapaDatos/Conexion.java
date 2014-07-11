/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CapaDatos;

import Gestion.GestionCliente;
import Interface.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * @author PROFESORES
 */
public class Conexion {
    Connection ConexionDatos= null;
    PreparedStatement Sentencia= null;
    ResultSet ConjuntoDatos=null;
    static String Usuario = null;
    static String Cadena = null;
    static String Clave =null;

    public static void setUsuario(String Usuario) {
        Conexion.Usuario = Usuario;
    }
    
    public static void setCadena(String Cadena) {
        Conexion.Cadena = Cadena;
    } 
    
    public static void setClave(String Clave) {
        Conexion.Clave = Clave;
    } 
    
    private static Conexion Instancia=null;
    
    public static Conexion GetInstancia (){
        if (Instancia == null){
        Instancia=new Conexion( );       
        }
        return Instancia;
    }    
    
   public void Conectar (){
       try {
       ConexionDatos= DriverManager.getConnection(Cadena, Usuario, Clave);    
       }
       catch(SQLException ex){
         ex.getStackTrace();
       }
               
    }
     public void Desconectar ()       {
         try{
         ConexionDatos.close();    
         }
         catch(SQLException ex){
             ex.getStackTrace();
         }
     }
     
     public void Ejecutar (String Cadena) throws SQLException{
         try{
             Sentencia= ConexionDatos.prepareStatement(Cadena);
         Sentencia.executeUpdate();
         }
         catch(SQLException ex){
             throw ex;
         }
         }
     
     public void Registro (String Cadena) throws SQLException{
         try{
             Sentencia= ConexionDatos.prepareStatement(Cadena);
          ResultSet registro = Sentencia.executeQuery();
         if(registro.next()==true){
             GestionCliente.mostrar(registro);
         }
         else{
             JOptionPane.showMessageDialog(null, "El cliente no se encuentra en la base de datos");
         }
         }
         catch(SQLException ex){
             throw ex;
         }
     }
     
     public void Modificacion (String Cadena) throws SQLException{
         try{
             Sentencia= ConexionDatos.prepareStatement(Cadena);
             int registro = Sentencia.executeUpdate();
            if (registro==1) {
            JOptionPane.showMessageDialog(null, "El cliente ha sido modificado con éxito");
          } else {
            JOptionPane.showMessageDialog(null, "El cliente no se encuentra en la base de datos");
          }
         }
         catch(SQLException ex){
             throw ex;
         }
     }
     public void Eliminacion (String Cadena) throws SQLException{
         try{
             Sentencia= ConexionDatos.prepareStatement(Cadena);
             int registro = Sentencia.executeUpdate();
            if (registro==1) {
            JOptionPane.showMessageDialog(null, "El cliente ha sido eliminado con éxito");
          } else {
            JOptionPane.showMessageDialog(null, "El cliente no se encuentra en la base de datos");
          }
         }
         catch(SQLException ex){
             throw ex;
         }
     }
}
