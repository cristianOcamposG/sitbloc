
package DAOIMP;

import Controladores.ValidarCTR;
import DAO.EnfermedadDAO;
import DTO.EnfermedadDTO;
import DTO.GanadoDTO;
import DTO.NombreVacunacionDTO;
import DTO.UsuarioDTO;
import DTO.VacunacionDTO;
import java.util.List;
import Genericos.ConexionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnfermedadDAOIMP implements EnfermedadDAO {

    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConexionDB conexion;

    public EnfermedadDAOIMP() {
        conexion = new ConexionDB();
    }
  public EnfermedadDTO Consultar(String codigoganado) {
        EnfermedadDTO enfermedaddto = new EnfermedadDTO();
        conexion.Transaccion(ConexionDB.TR.INICIAR);
        String query = "SELECT \"GANA_ID_GANADO\", \"GANA_COD_GANADO\", \"GANA_SEXO\", \"GANA_ESTADO\" FROM public.\"GANADOS\" Where \"GANA_COD_GANADO\" ='" + codigoganado + "'";

        try {
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                 GanadoDTO ganado1 = new GanadoDTO();
                ganado1.setGana_id_ganado(rs.getInt("GANA_ID_GANADO"));
                ganado1.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                ganado1.setGana_sexo(rs.getString("GANA_SEXO"));
                ganado1.setGana_estado(rs.getString("GANA_ESTADO"));
                enfermedaddto.setEnfe_ganado(ganado1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnfermedadDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enfermedaddto;
    }
    @Override
    public Boolean agregar(EnfermedadDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "INSERT INTO public.\"ENFERMEDADES\"( \"ENFE_DESCRIPCION\", \"ENFE_FECHA\", \"ENFE_NOTAS\", \"ENFE_ID_GANADO\", \"ENF_ID_VACUNACION\", \"ENF_ID_USUARIO\") VALUES ( ?, ?, ?, ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getEnfe_descripcion());
            ps.setDate(2, (Date)dto.getEnfe_fecha());
            ps.setString(3, dto.getEnfe_notas());
            ps.setInt(4, dto.getEnfe_ganado().getGana_id_ganado());
            ps.setInt(5, dto.getEnfe_vacunacion().getId_nom_vacu());
            ps.setInt(6, new ValidarCTR().usudto.getUsu_id_usuario());

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
            Logger.getLogger(EnfermedadDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            
        }
    }

    @Override
    public Boolean modificar(EnfermedadDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "UPDATE public.\"ENFERMEDADES\" SET  \"ENFE_DESCRIPCION\"=?, \"ENFE_FECHA\"=?,  \"ENFE_NOTAS\"=?, \"ENF_ID_VACUNACION\"=?, \"ENF_ID_USUARIO\"=? WHERE \"ENFE_ID_ENFERMEDADES\"=?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getEnfe_descripcion());
              ps.setDate(2, (Date)dto.getEnfe_fecha());
            ps.setString(3, dto.getEnfe_notas());
         
            ps.setInt(4, dto.getEnfe_vacunacion().getId_nom_vacu());
            ps.setInt(5, new ValidarCTR().usudto.getUsu_id_usuario());
            ps.setInt(6, dto.getEnfe_id_enfermedades());
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
            Logger.getLogger(EnfermedadDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
        
        }
    }

    @Override
    public Boolean eliminar(EnfermedadDTO dto) {
      throw new UnsupportedOperationException("Not supported yet.");
    }

     public Boolean eliminar(int dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "DELETE FROM public.\"ENFERMEDADES\" WHERE \"ENFE_ID_ENFERMEDADES\"=" + dto;;
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
            Logger.getLogger(EnfermedadDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

        }
    }
    @Override
    public List<EnfermedadDTO> consultarTodos() {
        try {
            List<EnfermedadDTO> lista;
            EnfermedadDTO dto = null;
            query = "SELECT \"ENFE_ID_ENFERMEDADES\", \"ENFE_DESCRIPCION\", \"ENFE_FECHA\", \"ENFE_NOTAS\", \"GANA_COD_GANADO\", \"NOMBRE_VACU\", \"USU_NOMBRE\""
                    + "	FROM public.\"ENFERMEDADES\", public.\"GANADOS\", public.\"VACUNACION_NOM\", public.\"USUARIOS\""
                    + "	WHERE \"ENFE_ID_GANADO\"=\"GANA_ID_GANADO\""
                    + "	AND \"ENF_ID_VACUNACION\"=\"ID_VACU_NOMBRE\""
                    + "	AND \"ENF_ID_USUARIO\"=\"USU_ID_USUARIO\";";
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                 dto = new EnfermedadDTO();
                dto.setEnfe_id_enfermedades(rs.getInt("ENFE_ID_ENFERMEDADES"));
                dto.setEnfe_descripcion(rs.getString("ENFE_DESCRIPCION"));
                dto.setEnfe_fecha(rs.getDate("ENFE_FECHA"));
                dto.setEnfe_notas(rs.getString("ENFE_NOTAS"));
                GanadoDTO ganado = new GanadoDTO();
                ganado.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                dto.setEnfe_ganado(ganado);
                NombreVacunacionDTO vacunacion = new NombreVacunacionDTO();
                vacunacion.setNombre_vacu(rs.getString("NOMBRE_VACU"));
                dto.setEnfe_vacunacion(vacunacion);
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setUsu_nombre(rs.getString("USU_NOMBRE"));
                dto.setEnfe_vacunacion(vacunacion);

                lista.add(dto);

            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EnfermedadDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public EnfermedadDTO consultarSegunId(Integer id) {
        try {
            EnfermedadDTO dto = null;
            query = "SELECT \"ENFE_ID_ENFERMEDADES\", \"ENFE_DESCRIPCION\", \"ENFE_FECHA\", \"ENFE_NOTAS\", \"GANA_COD_GANADO\", \"NOMBRE_VACU\", \"USU_NOMBRE\""
                    + "	FROM public.\"ENFERMEDADES\", public.\"GANADOS\", public.\"VACUNACION_NOM\", public.\"USUARIOS\""
                    + "	WHERE \"ENFE_ID_GANADO\"=\"GANA_ID_GANADO\""
                    + "	AND \"ENF_ID_VACUNACION\"=\"ID_VACU_NOMBRE\""
                    + "	AND \"ENF_ID_USUARIO\"=\"USU_ID_USUARIO\""
                    + "	AND \"ENFE_ID_ENFERMEDADES\"= "+ id;
            ps = conexion.obtenerConexion().prepareStatement(query);
           
            rs = ps.executeQuery();
            while (rs.next()) {
                dto = new EnfermedadDTO();
                 dto.setEnfe_id_enfermedades(rs.getInt("ENFE_ID_ENFERMEDADES"));
                dto.setEnfe_descripcion(rs.getString("ENFE_DESCRIPCION"));
                dto.setEnfe_fecha(rs.getDate("ENFE_FECHA"));
                dto.setEnfe_notas(rs.getString("ENFE_NOTAS"));
                 GanadoDTO ganado = new GanadoDTO();
                ganado.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                dto.setEnfe_ganado(ganado);
                NombreVacunacionDTO vacunacion = new NombreVacunacionDTO();
                vacunacion.setNombre_vacu(rs.getString("NOMBRE_VACU"));
                dto.setEnfe_vacunacion(vacunacion);
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setUsu_nombre(rs.getString("USU_NOMBRE"));
                dto.setEnfe_vacunacion(vacunacion);
               

            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(RazaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        } finally {
           
        }
    }
    

    @Override
    public EnfermedadDTO consultarSegunCadena(String cadena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMsj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
