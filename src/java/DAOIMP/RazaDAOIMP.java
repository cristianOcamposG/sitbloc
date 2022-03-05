package DAOIMP;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.RazaDAO;
import DTO.RazaDTO;
import Genericos.ConexionDB;
import java.util.ArrayList;
import java.util.List;

public class RazaDAOIMP implements RazaDAO {

    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConexionDB conexion;

    public RazaDAOIMP() {
        conexion = new ConexionDB();
    }

    @Override
    public Boolean agregar(RazaDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "INSERT INTO public.\"RAZAS\"( \"RAZA_DESCRIPCION\")VALUES (?);";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getRaza_descripcion());

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
            Logger.getLogger(RazaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

        }
    }

    @Override
    public Boolean modificar(RazaDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "UPDATE public.\"RAZAS\" SET \"RAZA_DESCRIPCION\" =? WHERE \"RAZA_ID_RAZA\"=?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getRaza_descripcion());
            ps.setInt(2, dto.getRaza_id_raza());

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
            Logger.getLogger(RazaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

        }
    }

    @Override
    public Boolean eliminar(RazaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Boolean eliminar(int dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "DELETE FROM public.\"RAZAS\" WHERE \"RAZA_ID_RAZA\"=" + dto;
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
            Logger.getLogger(LoteDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

        }
    }

    @Override
    public RazaDTO consultarSegunId(Integer id) {
       try {
            
             RazaDTO dto =new  RazaDTO();
            query = "SELECT \"RAZA_ID_RAZA\", \"RAZA_DESCRIPCION\" FROM public.\"RAZAS\" where \"RAZA_ID_RAZA\"="+id+";";
            ps = conexion.obtenerConexion().prepareStatement(query);
             rs = ps.executeQuery();
            while (rs.next()) {
                dto.setRaza_descripcion(rs.getString("RAZA_DESCRIPCION"));
                
            } return dto;

        } catch (SQLException ex) {
            Logger.getLogger(RazaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }finally {
            
        }
    }

    @Override
    public RazaDTO consultarSegunCadena(String cadena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMsj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RazaDTO> consultarTodos() {
        try {
            List<RazaDTO> lista = null;
            RazaDTO dto = null;
            query = "SELECT \"RAZA_ID_RAZA\", \"RAZA_DESCRIPCION\" FROM public.\"RAZAS\";";
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new RazaDTO();
                dto.setRaza_id_raza(rs.getInt("RAZA_ID_RAZA"));
                dto.setRaza_descripcion(rs.getString("RAZA_DESCRIPCION"));
                lista.add(dto);

            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(RazaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
