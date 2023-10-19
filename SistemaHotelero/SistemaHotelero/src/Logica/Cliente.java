
package Logica;

import Datos.DatosCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cliente {
    private ConexionBaseDeDatos mysql = new ConexionBaseDeDatos();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer total_registros;
    
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        
        String [] titulos = {"ID","Nombre","Apellido","Tipo de documento","Número de documento","Dirección","Teléfono","Correo"};
        
        String [] registro = new String [8];
        
        total_registros = 0;
        modelo = new DefaultTableModel(null,titulos);
        
        sSQL = "select * from cliente where numero_documento like '%" + buscar + "%' order by idcliente";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()) {
                registro [0] = rs.getString("idcliente");
                registro [1] = rs.getString("nombre");
                registro [2] = rs.getString("apellido");
                registro [3] = rs.getString("tipo_documento");
                registro [4] = rs.getString("numero_documento");
                registro [5] = rs.getString("direccion");
                registro [6] = rs.getString("telefono");
                registro [7] = rs.getString("correo");
                
                total_registros = total_registros + 1;
                modelo.addRow(registro);
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            return null;
        }
    }
    
    public boolean insertar(DatosCliente dts) {
        sSQL = "insert into cliente (nombre,apellido,tipo_documento,numero_documento,direccion,telefono,correo)" +
                "values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido());
            pst.setString(3, dts.getTipo_documento());
            pst.setString(4, dts.getNumero_documento());
            pst.setString(5, dts.getDireccion());
            pst.setString(6, dts.getTelefono());
            pst.setString(7, dts.getCorreo());
            
            int n = pst.executeUpdate();
            
            if (n != 0){
                return true;
            } else {
                return false;
            }
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean editar(DatosCliente dts) {
        sSQL = "update cliente set nombre=?, apellido=?, tipo_documento=?, numero_documento=?, direccion=?, telefono=?, correo=? " +
                " where idcliente=?";
        
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido());
            pst.setString(3, dts.getTipo_documento());
            pst.setString(4, dts.getNumero_documento());
            pst.setString(5, dts.getDireccion());
            pst.setString(6, dts.getTelefono());
            pst.setString(7, dts.getCorreo());
            pst.setInt(8, dts.getIdcliente());
            
            int n = pst.executeUpdate();
            
            if (n != 0){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean eliminar(DatosCliente dts) {
        sSQL = "delete from cliente where idcliente=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdcliente());
            
            int n = pst.executeUpdate();
            
            if (n != 0){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}
