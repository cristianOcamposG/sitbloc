package DAOIMP;

import Controladores.ValidarCTR;
import DAO.VacunacionDAO;
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

public class VacunacionDAOIMP implements VacunacionDAO {

    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConexionDB conexion;
    UsuarioDTO usudto = new UsuarioDTO();

    public VacunacionDAOIMP() {
        conexion = new ConexionDB();
    }

     public VacunacionDTO Consultar(String codigoganado) {
        VacunacionDTO vacunaciondto = new VacunacionDTO();
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
                vacunaciondto.setVacu_ganado(ganado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GanadoDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vacunaciondto;
    }
    @Override
    public Boolean agregar(VacunacionDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "INSERT INTO public.\"VACUNACIONES\"( \"VACU_FECHA\", \"VACU_TIPO\", \"VACU_NOTA\", \"VACU_ID_GANADO\", \"VACU_ID_USUARIO\", \"VACU_NOMBRE\") VALUES ( ?, ?, ?, ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(query);
             ps.setDate(1, (Date)dto.getVacu_fecha());
            ps.setInt(2, dto.getVacu_tipo());
            ps.setString(3, dto.getVacu_nota());
            ps.setInt(4, dto.getVacu_ganado().getGana_id_ganado());
            ps.setInt(5, new ValidarCTR().usudto.getUsu_id_usuario());
            ps.setInt(6, dto.getVacu_nombre().getId_nom_vacu());

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
            Logger.getLogger(VacunacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
          
        }
    }

    @Override
    public Boolean modificar(VacunacionDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "UPDATE public.\"VACUNACIONES\" SET   \"VACU_TIPO\"=?, \"VACU_FECHA\"=?, \"VACU_NOTA\"=?, \"VACU_ID_USUARIO\"=?, \"VACU_NOMBRE\"=? WHERE \"VACU_ID_VACUNACION\"=?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setInt(1, dto.getVacu_tipo());
             ps.setDate(2, (Date)dto.getVacu_fecha());
            ps.setString(3, dto.getVacu_nota());
            ps.setInt(4, new ValidarCTR().usudto.getUsu_id_usuario());
            ps.setInt(5, dto.getVacu_nombre().getId_nom_vacu());
            ps.setInt(6, dto.getVacu_id_vacunacion());
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
    public Boolean eliminar(VacunacionDTO dto) {
        
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
      public Boolean eliminar(int dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "DELETE FROM public.\"VACUNACIONES\" WHERE \"VACU_ID_VACUNACION\"=" + dto;;
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
    public List<VacunacionDTO> consultarTodos() {
        try {
              List<VacunacionDTO> lista = null;
            VacunacionDTO dto = null;
            query = "SELECT \"VACU_ID_VACUNACION\", \"VACU_FECHA\", \"VACU_TIPO\", \"VACU_NOTA\", \"GANA_COD_GANADO\", \"USU_NOMBRE\", \"NOMBRE_VACU\"\n"
                    + "	FROM public.\"VACUNACIONES\", public.\"USUARIOS\", public.\"GANADOS\", public. \"VACUNACION_NOM\"\n"
                    + "	WHERE \"VACU_ID_USUARIO\"=\"USU_ID_USUARIO\" AND \"VACU_ID_GANADO\"= \"GANA_ID_GANADO\" AND \"VACU_NOMBRE\" = \"ID_VACU_NOMBRE\";";
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new VacunacionDTO();
                dto.setVacu_id_vacunacion(rs.getInt("VACU_ID_VACUNACION"));
                dto.setVacu_fecha(rs.getDate("VACU_FECHA"));
                dto.setVacu_tipo(rs.getInt("VACU_TIPO"));
                dto.setVacu_nota(rs.getString("VACU_NOTA"));
                GanadoDTO ganado = new GanadoDTO();
                ganado.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                dto.setVacu_ganado(ganado);
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setUsu_nombre(rs.getString("USU_NOMBRE"));
                dto.setVacu_usuario(usuario);
                NombreVacunacionDTO nombrevacunacion = new NombreVacunacionDTO();
                nombrevacunacion.setNombre_vacu(rs.getString("NOMBRE_VACU"));
                dto.setVacu_nombre(nombrevacunacion);

                lista.add(dto);

            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(VacunacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public VacunacionDTO consultarSegunId(Integer id) {
        try {
             VacunacionDTO dto = new VacunacionDTO();
            query = "SELECT \"VACU_ID_VACUNACION\", \"VACU_FECHA\", \"VACU_TIPO\", \"VACU_NOTA\", \"GANA_COD_GANADO\", \"USU_NOMBRE\", \"NOMBRE_VACU\"\n"
                    + " FROM public.\"VACUNACIONES\", public.\"USUARIOS\", public.\"GANADOS\", public. \"VACUNACION_NOM\"\n"
                    + " WHERE \"VACU_ID_USUARIO\"=\"USU_ID_USUARIO\" AND \"VACU_ID_GANADO\"= \"GANA_ID_GANADO\" AND \"VACU_NOMBRE\" = \"ID_VACU_NOMBRE\"\n"
                    + " AND \"VACU_ID_VACUNACION\"="  + id;
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                dto = new VacunacionDTO();
                dto.setVacu_id_vacunacion(rs.getInt("VACU_ID_VACUNACION"));
                dto.setVacu_fecha(rs.getDate("VACU_FECHA"));
                dto.setVacu_tipo(rs.getInt("VACU_TIPO"));
                dto.setVacu_nota(rs.getString("VACU_NOTA"));
                GanadoDTO ganado = new GanadoDTO();
                ganado.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                dto.setVacu_ganado(ganado);
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setUsu_nombre(rs.getString("USU_NOMBRE"));
                dto.setVacu_usuario(usuario);
                NombreVacunacionDTO nombrevacunacion = new NombreVacunacionDTO();
                nombrevacunacion.setNombre_vacu(rs.getString("NOMBRE_VACU"));
                dto.setVacu_nombre(nombrevacunacion);

            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(RazaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        } finally {
         
        }
    }

    @Override
    public VacunacionDTO consultarSegunCadena(String cadena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMsj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
