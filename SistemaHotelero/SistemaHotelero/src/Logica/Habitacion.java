
package Logica;

import Datos.DatosHabitacion;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

/**
 *
 * @author Leticia Mongel�s
 */
public class Habitacion {
    private ConexionBaseDeDatos mysql = new ConexionBaseDeDatos();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer total_registros;
    
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        
        String [] titulos = {"ID","N�mero","Piso","Descrici�n","Caracter�sticas","Precio","Estado","Tipo de habitaci�n"};
        
        String [] registro = new String [8];
        
        total_registros = 0;
        modelo = new DefaultTableModel(null,titulos);
        
        sSQL = "select * from habitacion where numero like '%" + buscar + "%' order by idhabitacion";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()) {
                registro [0] = rs.getString("idhabitacion");
                registro [1] = rs.getString("numero");
                registro [2] = rs.getString("piso");
                registro [3] = rs.getString("descripcion");
                registro [4] = rs.getString("caracteristicas");
                registro [5] = rs.getString("precio_diario");
                registro [6] = rs.getString("estado");
                registro [7] = rs.getString("tipo_habitacion");
                
                total_registros = total_registros + 1;
                modelo.addRow(registro);
            }
            return modelo;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            return null;
        }
    }
    
    public boolean insertar(DatosHabitacion dts) {
        sSQL = "insert into habitacion (numero,piso,descripcion,caracteristicas,precio_diario,estado,tipo_habitacion)" +
                "values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_habitacion());
            
            
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
    
    public boolean editar(DatosHabitacion dts) {
        sSQL = "update habitacion set numero=?, piso=?, descripcion=?, caracteristicas=?, precio_diario=?, estado=?, tipo_habitacion=?" +
                " where idhabitacion=?";
        
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_habitacion());
            pst.setInt(8, dts.getIdhabitacion());
            
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
    
    public boolean eliminar(DatosHabitacion dts) {
        sSQL = "delete from habitacion where idhabitacion=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdhabitacion());
            
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
