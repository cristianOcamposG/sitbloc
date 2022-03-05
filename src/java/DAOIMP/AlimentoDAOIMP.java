
package DAOIMP;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import DAO.AlimentoDAO;
import DTO.AlimentoDTO;
import Genericos.ConexionDB;
import java.util.List;

public class AlimentoDAOIMP implements AlimentoDAO {

    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConexionDB conexion;

    public AlimentoDAOIMP() {
        conexion = new ConexionDB();
    }

    @Override
    public Boolean agregar(AlimentoDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "INSERT INTO public.\"ALIMENTOS\"( \"ALIM_DESCRIPCION\")VALUES (?);";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getAlim_descripcion());

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
            Logger.getLogger(AlimentoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            
        }
    }

    @Override
    public Boolean modificar(AlimentoDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "UPDATE public.\"ALIMENTOS\" SET \"ALIM_DESCRIPCION\"=? WHERE \"ALIM_ID_ALIMENTOS\"=?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getAlim_descripcion());
            ps.setInt(2, dto.getAlimento_id_alimento());

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
            Logger.getLogger(AlimentoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            
        }
    }

         public Boolean eliminar(int dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "DELETE FROM public.\"ALIMENTOS\" WHERE \"ALIM_ID_ALIMENTOS\"="+dto;
            ps = conexion.obtenerConexion().prepareStatement(query);
            
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionDB.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(ConexionDB.TR.CANCELAR);
                return false;
            }

        } catch (SQLException ex) {
            msj = "Error durante la Eliminacion" + ex.getMessage();
            conexion.Transaccion(ConexionDB.TR.CANCELAR);
            Logger.getLogger(AlimentoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
           
        }
    }
    @Override
    public Boolean eliminar(AlimentoDTO dto) {
       throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<AlimentoDTO> consultarTodos() {
        try {
            List<AlimentoDTO> lista = null;
            AlimentoDTO dto = null;
            query = "SELECT \"ALIM_ID_ALIMENTOS\", \"ALIM_DESCRIPCION\" FROM public.\"ALIMENTOS\";";
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new AlimentoDTO();
                dto.setAlimento_id_alimento(rs.getInt("ALIM_ID_ALIMENTOS"));
                dto.setAlim_descripcion(rs.getString("ALIM_DESCRIPCION"));
                lista.add(dto);

            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(AlimentoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public AlimentoDTO consultarSegunId(Integer id) {
       try {
            AlimentoDTO dto = null;
            query = "SELECT \"ALIM_ID_ALIMENTOS\", \"ALIM_DESCRIPCION\"\n"
                    + "	FROM public.\"ALIMENTOS\" WHERE \"ALIM_ID_ALIMENTOS\"="+id;
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                dto = new AlimentoDTO();
               
                dto.setAlim_descripcion(rs.getString("ALIM_DESCRIPCION"));

            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(RazaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        } finally {
            
        }
    }

    @Override
    public AlimentoDTO consultarSegunCadena(String cadena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMsj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
