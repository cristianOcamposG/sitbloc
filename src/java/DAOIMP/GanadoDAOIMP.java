package DAOIMP;

import Controladores.ValidarCTR;
import DAO.GanadoDAO;
import DTO.GanadoDTO;
import DTO.LoteDTO;
import DTO.RazaDTO;
import DTO.UsuarioDTO;
import DTO.ControlDTO;
import Genericos.ConexionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GanadoDAOIMP implements GanadoDAO {

    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConexionDB conexion;

    public GanadoDAOIMP() {
        conexion = new ConexionDB();
    }

    public GanadoDTO ConsultaPadre(String codigoPadre) {
        GanadoDTO ganadodto = new GanadoDTO();
        conexion.Transaccion(ConexionDB.TR.INICIAR);
        String query = "SELECT \"GANA_ID_GANADO\", \"GANA_COD_GANADO\", \"GANA_SEXO\", \"GANA_ESTADO\" FROM public.\"GANADOS\" Where \"GANA_COD_GANADO\" ='" + codigoPadre + "'";

        try {
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ganadodto.setGana_id_ganado(rs.getInt("GANA_ID_GANADO"));
                ganadodto.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                ganadodto.setGana_sexo(rs.getString("GANA_SEXO"));
                ganadodto.setGana_estado(rs.getString("GANA_ESTADO"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GanadoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ganadodto;
    }

////    public Boolean Actualizarorigen(GanadoDTO GanadoDTO) {
////        try {
////            conexion.Transaccion(ConexionDB.TR.INICIAR);
////            query = "UPDATE public.\"GANADOS\" SET  \"GANA_PADRE_GANADO\"=?, \"GANA_MADRE_GANADO\"=? WHERE \"GANA_ID_GANADO\"=(SELECT MAX(\"GANA_ID_GANADO\") FROM PUBLIC.\"GANADOS\");";
////            ps = conexion.obtenerConexion().prepareStatement(query);
////            ps.setString(1, GanadoDTO.getGana_padre_ganado());
////            ps.setString(2, GanadoDTO.getGana_madre_ganado());
////
////            if (ps.executeUpdate() > 0) {
////                conexion.Transaccion(ConexionDB.TR.CONFIRMAR);
////                return true;
////
////            } else {
////                conexion.Transaccion(ConexionDB.TR.CANCELAR);
////                return false;
////            }
////        } catch (SQLException ex) {
////            msj = "Error durante la modificación " + ex.getMessage();
////            conexion.Transaccion(ConexionDB.TR.CANCELAR);
////            Logger.getLogger(GanadoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
////            return false;
////        } finally {
////
////        }
//    }
    @Override
    public Boolean agregar(GanadoDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "INSERT INTO public.\"GANADOS\"( \"GANA_COD_GANADO\", \"GANA_FEC_NACIMIENTO\", \"GANA_FEC_INGRESO\", \"GANA_SEXO\", \"GANA_PESO_GANADO\", \"GANA_ESTADO\", \"GANA_ID_LOTE\", \"GANA_ID_RAZA\", \"GANA_ID_USUARIO\",  \"GANA_PADRE_GANADO\", \"GANA_MADRE_GANADO\")VALUES ( ?, ?,(select current_date) , ?, ?, ?, ?, ?, ?,?,?);";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getGana_cod_ganado());
            ps.setDate(2, dto.getGana_fec_nacimiento());
            ps.setString(3, dto.getGana_sexo());
            ps.setInt(4, dto.getGana_peso_ganado());
            ps.setString(5, dto.getGana_estado());
            ps.setInt(6, dto.getLote().getLote_id_lote());
            ps.setInt(7, dto.getRaza().getRaza_id_raza());
//            ps.setInt(8, 1);
            ps.setInt(8, new ValidarCTR().usudto.getUsu_id_usuario());
            ps.setString(9, dto.getGana_padre_ganado());
            ps.setString(10, dto.getGana_madre_ganado());

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
            Logger.getLogger(GanadoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

        }
    }

    @Override
    public Boolean modificar(GanadoDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "UPDATE public.\"GANADOS\" SET  \"GANA_COD_GANADO\"=?, \"GANA_FEC_NACIMIENTO\"=?, \"GANA_PESO_GANADO\"=?, \"GANA_ID_LOTE\"=?, \"GANA_ID_RAZA\"=?, \"GANA_SEXO\"=?, \"GANA_ESTADO\"=?, \"GANA_PADRE_GANADO\"=?, \"GANA_MADRE_GANADO\"=? WHERE \"GANA_ID_GANADO\"=?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getGana_cod_ganado());
            ps.setDate(2, (Date) dto.getGana_fec_nacimiento());
            ps.setInt(3, dto.getGana_peso_ganado());
            ps.setInt(4, dto.getLote().getLote_id_lote());
            ps.setInt(5, dto.getRaza().getRaza_id_raza());
            ps.setString(6, dto.getGana_sexo());
            ps.setString(7, dto.getGana_estado());
         
            ps.setString(8, dto.getGana_padre_ganado());
            ps.setString(9, dto.getGana_madre_ganado());
            ps.setInt(10, dto.getGana_id_ganado());
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
            Logger.getLogger(GanadoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

        }
    }

    @Override
    public Boolean eliminar(GanadoDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    public Boolean eliminar(int dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "DELETE FROM public.\"GANADOS\" WHERE \"GANA_ID_GANADO\"=" + dto;;
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
            Logger.getLogger(GanadoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

        }
    }

    @Override
    public List<GanadoDTO> consultarTodos() {
        try {

            List<GanadoDTO> lista = null;
            GanadoDTO dto = null;
            query = "SELECT \"GANA_ID_GANADO\", \"GANA_COD_GANADO\", \"GANA_FEC_NACIMIENTO\", \"GANA_SEXO\", \"GANA_PADRE_GANADO\", \"GANA_MADRE_GANADO\", \"GANA_PESO_GANADO\", \"GANA_ESTADO\", \"LOTE_DESCRIPCION\", \"RAZA_DESCRIPCION\", \"USU_NOMBRE\"\n"
                    + "	 FROM public.\"GANADOS\", public.\"LOTES\", public.\"RAZAS\", public.\"USUARIOS\"\n"
                    + "	  WHERE \"GANA_ID_LOTE\"=\"LOTE_ID_LOTE\"\n"
                    + "   AND \"GANA_ID_RAZA\"=\"RAZA_ID_RAZA\"\n"
                    + "   AND \"GANA_ID_USUARIO\"=\"USU_ID_USUARIO\";";

            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new GanadoDTO();
                dto.setGana_id_ganado(rs.getInt("GANA_ID_GANADO"));
                dto.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                dto.setGana_fec_nacimiento(rs.getDate("GANA_FEC_NACIMIENTO"));
                dto.setGana_sexo(rs.getString("GANA_SEXO"));
                dto.setGana_padre_ganado(rs.getString("GANA_PADRE_GANADO"));
                dto.setGana_madre_ganado(rs.getString("GANA_MADRE_GANADO"));
                dto.setGana_peso_ganado(rs.getInt("GANA_PESO_GANADO"));
                dto.setGana_estado(rs.getString("GANA_ESTADO"));
                LoteDTO lote = new LoteDTO();
                lote.setLote_descripcion(rs.getString("LOTE_DESCRIPCION"));
                dto.setLote(lote);
                RazaDTO raza = new RazaDTO();
                raza.setRaza_descripcion(rs.getString("RAZA_DESCRIPCION"));
                dto.setRaza(raza);
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setUsu_nombre(rs.getString("USU_NOMBRE"));
                dto.setUsuario(usuario);

                lista.add(dto);

            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(GanadoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public GanadoDTO consultarSegunId(Integer id) {
        try {
            GanadoDTO dto = new GanadoDTO();
            query = "SELECT \"GANA_ID_GANADO\", \"GANA_COD_GANADO\", \"GANA_FEC_NACIMIENTO\", \"GANA_SEXO\", \"GANA_PADRE_GANADO\", \"GANA_MADRE_GANADO\", \"GANA_PESO_GANADO\", \"GANA_ESTADO\", \"LOTE_DESCRIPCION\", \"RAZA_DESCRIPCION\", \"USU_NOMBRE\"\n"
                    + "	FROM public.\"GANADOS\", public.\"LOTES\", public.\"RAZAS\", public.\"USUARIOS\"\n"
                    + "	WHERE \"GANA_ID_LOTE\"=\"LOTE_ID_LOTE\"\n"
                    + " AND \"GANA_ID_RAZA\"=\"RAZA_ID_RAZA\"\n"
                    + " AND \"GANA_ID_USUARIO\"=\"USU_ID_USUARIO\"\n"
                    + "	AND \"GANA_ID_GANADO\"=" + id;
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                dto = new GanadoDTO();
                dto.setGana_id_ganado(rs.getInt("GANA_ID_GANADO"));
                dto.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                dto.setGana_fec_nacimiento(rs.getDate("GANA_FEC_NACIMIENTO"));
                dto.setGana_sexo(rs.getString("GANA_SEXO"));
                dto.setGana_padre_ganado(rs.getString("GANA_PADRE_GANADO"));
                dto.setGana_madre_ganado(rs.getString("GANA_MADRE_GANADO"));
                dto.setGana_peso_ganado(rs.getInt("GANA_PESO_GANADO"));
                dto.setGana_estado(rs.getString("GANA_ESTADO"));
                LoteDTO lote = new LoteDTO();
                lote.setLote_descripcion(rs.getString("LOTE_DESCRIPCION"));
                dto.setLote(lote);
                RazaDTO raza = new RazaDTO();
                raza.setRaza_descripcion(rs.getString("RAZA_DESCRIPCION"));
                dto.setRaza(raza);
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setUsu_nombre(rs.getString("USU_NOMBRE"));
                dto.setUsuario(usuario);

            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(GanadoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        } finally {

        }
    }

    public List<GanadoDTO> consultarReportes() {
        try {

            List<GanadoDTO> lista = null;
            GanadoDTO dto = null;
            query = "SELECT  \"GANA_COD_GANADO\", \"GANA_FEC_NACIMIENTO\", \"GANA_FEC_INGRESO\",\"GANA_SEXO\",MAX(\"CONT_PESO\"), \"LOTE_DESCRIPCION\", \"RAZA_DESCRIPCION\"\n"
                    + "	FROM public.\"GANADOS\",public.\"LOTES\",public.\"RAZAS\",public.\"CONTROL\"\n"
                    + "	WHERE \"GANA_ID_LOTE\"=\"LOTE_ID_LOTE\"\n"
                    + "	AND \"GANA_ID_RAZA\"=\"RAZA_ID_RAZA\"\n"
                    + "	AND \"GANA_ID_GANADO\"=\"CONT_ID_GANADO\"\n"
                    + "	GROUP BY \"GANA_COD_GANADO\", \"GANA_FEC_NACIMIENTO\", \"GANA_FEC_INGRESO\",\"LOTE_DESCRIPCION\", \"RAZA_DESCRIPCION\";";

            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new GanadoDTO();
                dto.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                dto.setGana_fec_nacimiento(rs.getDate("GANA_FEC_NACIMIENTO"));
                dto.setGana_sexo(rs.getString("GANA_SEXO"));

                ControlDTO control = new ControlDTO();
                control.setCont_peso(rs.getInt("CONT_PESO"));

                LoteDTO lote = new LoteDTO();
                lote.setLote_descripcion(rs.getString("LOTE_DESCRIPCION"));
                dto.setLote(lote);
                RazaDTO raza = new RazaDTO();
                raza.setRaza_descripcion(rs.getString("RAZA_DESCRIPCION"));
                dto.setRaza(raza);

                lista.add(dto);

            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(GanadoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public GanadoDTO consultarSegunCadena(String cadena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMsj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
