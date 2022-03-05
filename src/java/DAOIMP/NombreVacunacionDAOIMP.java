
package DAOIMP;

import DAO.NombreVacunacionDAO;
import DTO.NombreVacunacionDTO;
import Genericos.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NombreVacunacionDAOIMP implements NombreVacunacionDAO{
     private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConexionDB conexion;
    public NombreVacunacionDAOIMP() {
        conexion = new ConexionDB();
    }

    @Override
    public Boolean agregar(NombreVacunacionDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "INSERT INTO public.\"VACUNACION_NOM\"( \"NOMBRE_VACU\") VALUES ( ?);";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getNombre_vacu());
            
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionDB.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(ConexionDB.TR.CANCELAR);
                return false;
            }

        } catch (SQLException ex) {
            msj = "Error durante la inserción " + ex.getMessage();
            conexion.Transaccion(ConexionDB.TR.CANCELAR);
            Logger.getLogger(NombreVacunacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
           
        }
    }

    @Override
    public Boolean modificar(NombreVacunacionDTO dto) {
      try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "UPDATE public.\"VACUNACION_NOM\" SET  \"NOMBRE_VACU\"=? WHERE \"ID_VACU_NOMBRE\"=? ;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getNombre_vacu());
            ps.setInt(2, dto.getId_nom_vacu());

            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionDB.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(ConexionDB.TR.CANCELAR);
                return false;
            }

        } catch (SQLException ex) {
            msj = "Error durante la modificacion " + ex.getMessage();
            conexion.Transaccion(ConexionDB.TR.CANCELAR);
            Logger.getLogger(NombreVacunacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            
        }
    }

    @Override
    public Boolean eliminar(NombreVacunacionDTO dto) {
  
           throw new UnsupportedOperationException("Not supported yet.");
    }
    
     public Boolean eliminar(int dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "DELETE FROM public.\"VACUNACION_NOM\" WHERE \"ID_VACU_NOMBRE\"="+dto;;
            ps = conexion.obtenerConexion().prepareStatement(query);
            
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionDB.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(ConexionDB.TR.CANCELAR);
                return false;
            }

        } catch (SQLException ex) {
            msj = "Error durante la Eliminacion " + ex.getMessage();
            conexion.Transaccion(ConexionDB.TR.CANCELAR);
            Logger.getLogger(NombreVacunacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
           
        }
    }

    
    @Override
    public List<NombreVacunacionDTO> consultarTodos() {
        try {
            List<NombreVacunacionDTO> lista = null;
            NombreVacunacionDTO dto = null;
            query = "SELECT \"ID_VACU_NOMBRE\", \"NOMBRE_VACU\" FROM public.\"VACUNACION_NOM\";";
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new NombreVacunacionDTO();
                dto.setId_nom_vacu(rs.getInt("ID_VACU_NOMBRE"));
                dto.setNombre_vacu(rs.getString("NOMBRE_VACU"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(NombreVacunacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public NombreVacunacionDTO consultarSegunId(Integer id) {
      try {
            NombreVacunacionDTO dto = null;
            query = "SELECT \"ID_VACU_NOMBRE\", \"NOMBRE_VACU\" FROM public.\"VACUNACION_NOM\" WHERE \"ID_VACU_NOMBRE\"="+id;
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                dto = new NombreVacunacionDTO();
             
                dto.setNombre_vacu(rs.getString("NOMBRE_VACU"));
            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(NombreVacunacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        } finally {
            
        }
    }

    @Override
    public NombreVacunacionDTO consultarSegunCadena(String cadena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMsj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
