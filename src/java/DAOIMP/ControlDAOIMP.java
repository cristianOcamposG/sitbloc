package DAOIMP;

import Controladores.ValidarCTR;
import DAO.ControlDAO;
import DTO.AlimentoDTO;
import DTO.ControlDTO;
import DTO.GanadoDTO;
import DTO.UsuarioDTO;
import Genericos.ConexionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlDAOIMP implements ControlDAO {

    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConexionDB conexion;

    public ControlDAOIMP() {
        conexion = new ConexionDB();
    }

    public ControlDTO Consulta(String codigoganado) {
        ControlDTO controldto = new ControlDTO();
        conexion.Transaccion(ConexionDB.TR.INICIAR);
        String query = "SELECT \"CONT_ID_CONTROL\",\"GANA_ID_GANADO\", \"GANA_COD_GANADO\",\"GANA_ESTADO\" FROM public.\"CONTROL\", public.\"GANADOS\" WHERE \"CONT_ID_GANADO\" = \"GANA_ID_GANADO\" AND \"GANA_COD_GANADO\" = '" + codigoganado + "'";
        
        try {
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                GanadoDTO ganado = new GanadoDTO();
                ganado.setGana_id_ganado(rs.getInt("GANA_ID_GANADO"));
                ganado.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                ganado.setGana_estado(rs.getString("GANA_ESTADO"));
                controldto.setCont_ganado(ganado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControlDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return controldto;
    }
    
    @Override
    public Boolean agregar(ControlDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "INSERT INTO public.\"CONTROL\" ( \"CONT_FECHA\", \"CONT_PESO\", \"CONT_ID_GANADO\", \"CONT_ID_ALIMENTO\", \"CONT_ID_USUARIO\", \"CONT_OBSERVACION\") VALUES (?, ?, ?, ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setDate(1, (Date) dto.getCont_fecha());
            ps.setInt(2, dto.getCont_peso());
            ps.setInt(3, dto.getCont_ganado().getGana_id_ganado());
            ps.setInt(4, dto.getCont_alimento().getAlimento_id_alimento());
            ps.setInt(5, new ValidarCTR().usudto.getUsu_id_usuario());
            ps.setString(6, dto.getCont_observacion());

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
            Logger.getLogger(ControlDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

        }
    }

    @Override
    public Boolean modificar(ControlDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "UPDATE public.\"CONTROL\" SET \"CONT_FECHA\"=?, \"CONT_PESO\"=?,\"CONT_ID_ALIMENTO\"=?, \"CONT_ID_USUARIO\"=? ,\"CONT_OBSERVACION\"=? WHERE \"CONT_ID_CONTROL\"=?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setDate(1, (Date) dto.getCont_fecha());
            ps.setInt(2, dto.getCont_peso());
            ps.setInt(3, dto.getCont_alimento().getAlimento_id_alimento());
            ps.setInt(4, new ValidarCTR().usudto.getUsu_id_usuario());
            ps.setString(5, dto.getCont_observacion());
            ps.setInt(6, dto.getCont_id_control());
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
            Logger.getLogger(ControlDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

        }
    }

    @Override
    public Boolean eliminar(ControlDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Boolean eliminar(int dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "DELETE FROM public.\"CONTROL\" WHERE \"CONT_ID_CONTROL\"=" + dto;;
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
            Logger.getLogger(ControlDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

        }
    }

    @Override
    public List<ControlDTO> consultarTodos() {
        try {
             List<ControlDTO> lista = null;
            ControlDTO dto = null;
            query = "SELECT \"CONT_ID_CONTROL\", \"CONT_FECHA\", \"CONT_PESO\", \"GANA_COD_GANADO\", \"ALIM_DESCRIPCION\", \"USU_NOMBRE\", \"CONT_OBSERVACION\""
                    + "	FROM public.\"CONTROL\", public.\"GANADOS\",  public.\"ALIMENTOS\", public.\"USUARIOS\""
                    + "	WHERE \"CONT_ID_GANADO\" = \"GANA_ID_GANADO\" AND \"CONT_ID_ALIMENTO\" = \"ALIM_ID_ALIMENTOS\" AND \"CONT_ID_USUARIO\" = \"USU_ID_USUARIO\";";
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new ControlDTO();
                dto.setCont_id_control(rs.getInt("CONT_ID_CONTROL"));
                dto.setCont_fecha(rs.getDate("CONT_FECHA"));
                dto.setCont_peso(rs.getInt("CONT_PESO"));
                dto.setCont_observacion(rs.getString("CONT_OBSERVACION"));
                GanadoDTO ganado = new GanadoDTO();
                ganado.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                dto.setCont_ganado(ganado);
                AlimentoDTO alimento = new AlimentoDTO();
                alimento.setAlim_descripcion(rs.getString("ALIM_DESCRIPCION"));
                dto.setCont_alimento(alimento);
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setUsu_nombre(rs.getString("USU_NOMBRE"));
                dto.setCont_usuario(usuario);

                lista.add(dto);

            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ControlDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ControlDTO consultarSegunId(Integer id) {
        try {
            ControlDTO dto = new ControlDTO();
            query = "SELECT \"CONT_ID_CONTROL\", \"CONT_FECHA\", \"CONT_PESO\", \"GANA_COD_GANADO\", \"ALIM_DESCRIPCION\", \"USU_NOMBRE\", \"CONT_OBSERVACION\""
                    + "	FROM public.\"CONTROL\", public.\"GANADOS\",  public.\"ALIMENTOS\", public.\"USUARIOS\""
                    + "	WHERE \"CONT_ID_GANADO\" = \"GANA_ID_GANADO\" AND \"CONT_ID_ALIMENTO\" = \"ALIM_ID_ALIMENTOS\" AND \"CONT_ID_USUARIO\" = \"USU_ID_USUARIO\"\n"
                    + " AND \"CONT_ID_CONTROL\"=" + id;
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                dto = new ControlDTO();
                dto.setCont_id_control(rs.getInt("CONT_ID_CONTROL"));
                dto.setCont_fecha(rs.getDate("CONT_FECHA"));
                dto.setCont_peso(rs.getInt("CONT_PESO"));
                dto.setCont_observacion(rs.getString("CONT_OBSERVACION"));
                GanadoDTO ganado = new GanadoDTO();
                ganado.setGana_cod_ganado(rs.getString("GANA_COD_GANADO"));
                dto.setCont_ganado(ganado);
                  AlimentoDTO alimento = new AlimentoDTO();
                alimento.setAlim_descripcion(rs.getString("ALIM_DESCRIPCION"));
                dto.setCont_alimento(alimento);
                
              

            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(ControlDAOIMP.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        } finally {
            
        }
    }

    @Override
    public ControlDTO consultarSegunCadena(String cadena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMsj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
