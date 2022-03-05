package DAOIMP;

import Genericos.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


import DAO.UsuarioDAO;
import DTO.PersonaDTO;
import DTO.UsuarioDTO;
import java.util.List;

public class UsuarioDAOIMP implements UsuarioDAO {

    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConexionDB conexion;

    public UsuarioDAOIMP() {
        conexion = new ConexionDB();
    }

    public UsuarioDTO validar(String usu_nombre, String usu_contraseña) {

        UsuarioDTO usu = new UsuarioDTO();
        String sql = "SELECT * FROM public.\"USUARIOS\" where  \"USU_NOMBRE\"= ? and  \"USU_CONTRASEÑA\" = ? ;";
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, usu_nombre);
            ps.setString(2, usu_contraseña);
            rs = ps.executeQuery();
            while (rs.next()) {
                usu.setUsu_nombre(rs.getString("USU_NOMBRE"));
                usu.setUsu_contraseña(rs.getString("USU_CONTRASEÑA"));
                usu.setUsu_rol(rs.getInt("USU_ROL"));
                usu.setUsu_id_usuario(rs.getInt("USU_ID_USUARIO"));
                usu.setUsu_estado(rs.getInt("USU_ESTADO"));
                  
            }
        } catch (Exception e) {
        }
        return usu;
    }

     public UsuarioDTO Consultanombre(String ci) {
        UsuarioDTO usuariodto = new UsuarioDTO();
        conexion.Transaccion(ConexionDB.TR.INICIAR);
        String query = "SELECT \"PERS_ID_PERSONA\", \"PERS_NOMBRE\", \"PERS_CI\" FROM public.\"PERSONAS\" where \"PERS_CI\"='"+ci+"';";

        try {
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                 PersonaDTO persona = new PersonaDTO();
                persona.setPers_id_persona(rs.getInt("PERS_ID_PERSONA"));
                persona.setPers_nombre(rs.getString("PERS_NOMBRE"));
                persona.setPers_Ci(rs.getString("PERS_CI"));
                usuariodto.setPersona(persona);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuariodto;
    }
    
  
     
     
     
     
     
     
     
     
     
     
     
     
    
    @Override
    public Boolean agregar(UsuarioDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "INSERT INTO public.\"USUARIOS\"( \"USU_NOMBRE\", \"USU_CONTRASEÑA\", \"USU_ROL\", \"USU_ESTADO\",  \"USU_ID_PERSONA\") VALUES ( ?, ?, ?, ?, ?);";

            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getUsu_nombre());
            ps.setString(2, dto.getUsu_contraseña());
            ps.setInt(3, dto.getUsu_rol());
            ps.setInt(4, dto.getUsu_estado());
            ps.setInt(5, dto.getPersona().getPers_id_persona());
            

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
            Logger.getLogger(UsuarioDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            
        }

    }

    @Override
    public Boolean modificar(UsuarioDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "UPDATE public.\"USUARIOS\" SET \"USU_NOMBRE\"=?, \"USU_CONTRASEÑA\"=?, \"USU_ROL\"=?, \"USU_ESTADO\"=? WHERE \"USU_ID_USUARIO\"=?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getUsu_nombre());
            ps.setString(2, dto.getUsu_contraseña());
            ps.setInt(3, dto.getUsu_rol());
            ps.setInt(4, dto.getUsu_estado());
            ps.setInt(5, dto.getUsu_id_usuario());
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
            Logger.getLogger(UsuarioDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
           
        }
    }

    
    
    public Boolean eliminar(int dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "DELETE FROM public.\"USUARIOS\" WHERE \"USU_ID_USUARIO\"="+dto;
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
    public Boolean eliminar(UsuarioDTO dto) {
         throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UsuarioDTO> consultarTodos() {
        try {
            List<UsuarioDTO> lista = null;
            UsuarioDTO dto = null;
            query = "SELECT \"USU_ID_USUARIO\", \"USU_NOMBRE\", \"USU_CONTRASEÑA\", \"USU_ROL\", \"USU_ESTADO\", \"PERS_NOMBRE\"\n"
                    + "	FROM public.\"USUARIOS\", public.\"PERSONAS\"\n"
                    + "	WHERE \"USU_ID_PERSONA\" = \"PERS_ID_PERSONA\";";
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new UsuarioDTO();
                dto.setUsu_id_usuario(rs.getInt("USU_ID_USUARIO"));
                dto.setUsu_nombre(rs.getString("USU_NOMBRE"));
                dto.setUsu_contraseña(rs.getString("USU_CONTRASEÑA"));
                dto.setUsu_rol(rs.getInt("USU_ROL"));
                dto.setUsu_estado(rs.getInt("USU_ESTADO"));
                PersonaDTO persona = new PersonaDTO();
                persona.setPers_nombre(rs.getString("PERS_NOMBRE"));
                dto.setPersona(persona);

                lista.add(dto);

            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public UsuarioDTO consultarSegunId(Integer id) {
         try {
            UsuarioDTO dto = null;
            query = "SELECT \"USU_ID_USUARIO\", \"USU_NOMBRE\", \"USU_CONTRASEÑA\", \"USU_ROL\", \"USU_ESTADO\", \"PERS_CI\"\n"
                    + "FROM public.\"USUARIOS\" , public.\"PERSONAS\" WHERE \"USU_ID_PERSONA\" = \"PERS_ID_PERSONA\" AND \"USU_ID_USUARIO\" ="+id;
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                dto = new UsuarioDTO();
                dto.setUsu_id_usuario(rs.getInt("USU_ID_USUARIO"));
                dto.setUsu_nombre(rs.getString("USU_NOMBRE"));
                dto.setUsu_contraseña(rs.getString("USU_CONTRASEÑA"));
                dto.setUsu_rol(rs.getInt("USU_ROL"));
                dto.setUsu_estado(rs.getInt("USU_ESTADO"));
                 PersonaDTO persona=new PersonaDTO();
                 persona.setPers_Ci(rs.getString("PERS_CI"));
                 dto.setPersona(persona);
                  
            }
            return dto;

        } catch (SQLException ex) {
            Logger.getLogger(RazaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        } finally {
            
        }
    }


@Override
        public UsuarioDTO consultarSegunCadena(String cadena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
        public String getMsj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
