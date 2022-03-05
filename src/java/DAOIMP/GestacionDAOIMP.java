package DAOIMP;

import DAO.GestacionDAO;
import DTO.GanadoDTO;
import DTO.GestacionDTO;
import java.util.List;
import Genericos.ConexionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestacionDAOIMP implements GestacionDAO {

    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConexionDB conexion;

    public GestacionDAOIMP() {
        conexion = new ConexionDB();
    }
 public GestacionDTO Consultar(String codigoganado) {
        GestacionDTO gestaciondto = new GestacionDTO();
        conexion.Transaccion(ConexionDB.TR.INICIAR);
        String query = "SELECT \"GANA_ID_GANADO\", \"GANA_COD_GANADO\", \"GANA_SEXO\", \"GANA_ESTADO\" FROM public.\"GANADOS\" Where \"GANA_COD_GANADO\" ='" + codigoganado + "'";

        try {
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                 GanadoDTO ganado = new GanadoDTO();
                ganado.setGana_id_ganado(rs.getInt("GANA_ID_GANADO"));
                ganado.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                ganado.setGana_sexo(rs.getString("GANA_SEXO"));
                ganado.setGana_estado(rs.getString("GANA_ESTADO"));
                gestaciondto.setGest_ganado(ganado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GanadoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gestaciondto;
    }
    @Override
    public Boolean agregar(GestacionDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "INSERT INTO public.\"GESTACION\"( \"GEST_FECHA_GESTACION\", \"GEST_ESTADO\", \"GEST_ID_GANADO\", \"GEST_TIPO\", \"GEST_FECHA_FINA\") VALUES (  ?, ?, ?, ?,?);";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setDate(1, (Date) dto.getGest_fecha_gestacion());
            ps.setInt(2, dto.getGest_estado());
            ps.setInt(3, dto.getGest_ganado().getGana_id_ganado());
            ps.setInt(4, dto.getGest_tipo());
             ps.setDate(5, (Date) dto.getGest_fecha_fina());

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
            Logger.getLogger(GestacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
           
        }
    }

    @Override
    public Boolean modificar(GestacionDTO dto) {
         try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "UPDATE public.\"GESTACION\" SET   \"GEST_FECHA_GESTACION\"=?,\"GEST_ESTADO\"=?,  \"GEST_TIPO\"=?,\"GEST_FECHA_FINA\"=?  WHERE \"GEST_ID_GESTACION\"=?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
          
            ps.setDate(1, (Date) dto.getGest_fecha_gestacion());
            ps.setInt(2, dto.getGest_estado());
           
            ps.setInt(3, dto.getGest_tipo());
            ps.setDate(4, (Date) dto.getGest_fecha_fina());

            ps.setInt(5, dto.getGest_id_gestacion());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(ConexionDB.TR.CONFIRMAR);
                return true;

            } else {
                conexion.Transaccion(ConexionDB.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            msj = "Error durante la modificación " + ex.getMessage();
            conexion.Transaccion(ConexionDB.TR.CANCELAR);
            Logger.getLogger(GestacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            
        }
    }

     public Boolean eliminar(int dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "DELETE FROM public.\"GESTACION\" WHERE \"GEST_ID_GESTACION\"=" + dto;;
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
            Logger.getLogger(GestacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

        }
    }
    @Override
    public Boolean eliminar(GestacionDTO dto) {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<GestacionDTO> consultarTodos() {
        try {
             List<GestacionDTO> lista = null;
            GestacionDTO dto = null;
            query = "SELECT \"GEST_ID_GESTACION\", \"GEST_FECHA_GESTACION\", \"GEST_TIPO\", \"GEST_ESTADO\",\"GEST_FECHA_FINA\", \"GANA_COD_GANADO\"\n"
                    + "	FROM public.\"GESTACION\", public.\"GANADOS\""
                    + "	WHERE \"GEST_ID_GANADO\"=\"GANA_ID_GANADO\";";
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new GestacionDTO();
                dto.setGest_id_gestacion(rs.getInt("GEST_ID_GESTACION"));
                dto.setGest_fecha_gestacion(rs.getDate("GEST_FECHA_GESTACION"));
                dto.setGest_tipo(rs.getInt("GEST_TIPO"));
                dto.setGest_estado(rs.getInt("GEST_ESTADO"));
                dto.setGest_fecha_fina(rs.getDate("GEST_FECHA_FINA"));
                GanadoDTO ganado = new GanadoDTO();
                ganado.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                dto.setGest_ganado(ganado);
                lista.add(dto);

            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(GestacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public GestacionDTO consultarSegunId(Integer id) {
        try {
            GestacionDTO dto = new GestacionDTO ();
            query = "SELECT \"GEST_ID_GESTACION\", \"GEST_FECHA_GESTACION\", \"GEST_TIPO\", \"GEST_ESTADO\",\"GEST_FECHA_FINA\", \"GANA_COD_GANADO\"\n"
                    + "	FROM public.\"GESTACION\", public.\"GANADOS\""
                    + "	WHERE \"GEST_ID_GANADO\"=\"GANA_ID_GANADO\""
                    + "	AND \"GEST_ID_GESTACION\"= "+ id;
            ps = conexion.obtenerConexion().prepareStatement(query);
           
            rs = ps.executeQuery();
            while (rs.next()) {
                dto = new GestacionDTO();
                dto.setGest_id_gestacion(rs.getInt("GEST_ID_GESTACION"));
                dto.setGest_fecha_gestacion(rs.getDate("GEST_FECHA_GESTACION"));
                dto.setGest_tipo(rs.getInt("GEST_TIPO"));
                dto.setGest_estado(rs.getInt("GEST_ESTADO"));
                dto.setGest_fecha_fina(rs.getDate("GEST_FECHA_FINA"));
                GanadoDTO ganado = new GanadoDTO();
                ganado.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                dto.setGest_ganado(ganado);

            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(GestacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        } finally {
            
        }
    }

    @Override
    public GestacionDTO consultarSegunCadena(String cadena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMsj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
