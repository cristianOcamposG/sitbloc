package DAOIMP;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import DAO.LoteDAO;
import DTO.LoteDTO;
import Genericos.ConexionDB;
import Genericos.TipoMotorDB;
import java.util.List;

public class LoteDAOIMP implements LoteDAO {

    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConexionDB conexion;
    private TipoMotorDB cn;
    int r;

    public LoteDAOIMP() {
        conexion = new ConexionDB();
    }

    @Override
    public Boolean agregar(LoteDTO lotedto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "INSERT INTO public.\"LOTES\"( \"LOTE_DESCRIPCION\")VALUES (?);";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, lotedto.getLote_descripcion());
            
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
            Logger.getLogger(LoteDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            
        }

    }
    

    @Override
    public Boolean modificar(LoteDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "UPDATE public.\"LOTES\" SET \"LOTE_DESCRIPCION\"=? WHERE \"LOTE_ID_LOTE\"=?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getLote_descripcion());
            ps.setInt(2, dto.getLote_id_lote());

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
            Logger.getLogger(LoteDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
          
        }
    }

  
    public Boolean eliminar(int dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "DELETE FROM public.\"LOTES\" WHERE \"LOTE_ID_LOTE\"="+dto;;
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
    public List<LoteDTO> consultarTodos() {
        try {
            List<LoteDTO> lista = null;
            LoteDTO dto = null;
            query = "SELECT \"LOTE_ID_LOTE\", \"LOTE_DESCRIPCION\" FROM public.\"LOTES\";";
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new LoteDTO();
                dto.setLote_id_lote(rs.getInt("LOTE_ID_LOTE"));
                dto.setLote_descripcion(rs.getString("LOTE_DESCRIPCION"));
                lista.add(dto);

            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(LoteDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public LoteDTO consultarSegunId(Integer id) {
        try {
            LoteDTO dto = new LoteDTO();
            query = "SELECT \"LOTE_ID_LOTE\", \"LOTE_DESCRIPCION\"\n"
                    + "	FROM public.\"LOTES\" WHERE \"LOTE_ID_LOTE\"="+id;
            ps = conexion.obtenerConexion().prepareStatement(query);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                dto.setLote_descripcion(rs.getString("LOTE_DESCRIPCION"));
            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(RazaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        } finally {
           
        }
    }

    @Override
    public LoteDTO consultarSegunCadena(String cadena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMsj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean eliminar(LoteDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
