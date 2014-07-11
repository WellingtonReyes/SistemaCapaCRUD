/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gestion;
import CapaDatos.Conexion;
import Clases.Cliente;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


/**
 * @author PROFESORES
 */
public class GestionCliente implements IGestion{

    
    private Cliente cliente= new Cliente ("","","",0);

    public GestionCliente() {
        Conexion.setCadena("jdbc:mysql://localhost/facturacion");
        Conexion.setUsuario("root");
        Conexion.setClave("");
        
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente (Cliente cliente){
        this.cliente=cliente;
    }
    
    
    @Override
    public void Grabar() throws SQLException {        
        try{
        Conexion.GetInstancia().Conectar();
        Conexion.GetInstancia().Ejecutar("insert into cliente (cedula, nombre, direccion, cupo) values ('"+cliente.getCedula()+"','"+cliente.getNombre()+"','"+cliente.getDireccion()+"', "+cliente.getCupo()+")");
        Conexion.GetInstancia().Desconectar();    
        }
        catch(SQLException ex){
            throw ex;
            
        }
        
    }

    @Override
    public void Modificar() throws SQLException {
    Conexion.GetInstancia().Conectar();
    Conexion.GetInstancia().Modificacion("update cliente set Nombre='"+cliente.getNombre()+"', Direccion='"+cliente.getDireccion()+"', Cupo='"+cliente.getCupo()+"' where Cedula="+cliente.getCedula());
    Conexion.GetInstancia().Desconectar();   
    }

    @Override
    public void Nuevo() throws SQLException {
        Interface.Cliente.txtcedula.setText("");
        Interface.Cliente.txtnombre.setText("");
        Interface.Cliente.txtdireccion.setText("");
        Interface.Cliente.txtcupo.setText("");
    }

    @Override
    public void Eliminar() throws SQLException {
        Conexion.GetInstancia().Conectar();
        Conexion.GetInstancia().Eliminacion("delete from Cliente where Cedula= '"+cliente.getCedula()+"'");
        Conexion.GetInstancia().Desconectar();    
    }

    @Override
    public void Consultar() throws SQLException {
        Conexion.GetInstancia().Conectar();
        Conexion.GetInstancia().Registro("select * from Cliente where Cedula= '"+cliente.getCedula()+"'");
        Conexion.GetInstancia().Desconectar();    
    }
    
    public static void mostrar(ResultSet registro)throws SQLException {
        Interface.Cliente.txtnombre.setText(registro.getString("Nombre"));
        Interface.Cliente.txtdireccion.setText(registro.getString("Direccion"));
        Interface.Cliente.txtcupo.setText(registro.getString("Cupo"));
    }
}
