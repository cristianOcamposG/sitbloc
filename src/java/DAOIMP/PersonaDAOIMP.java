
package DAOIMP;

import DAO.PersonaDAO;
import DTO.PersonaDTO;
import Genericos.ConexionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDAOIMP implements PersonaDAO {

    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConexionDB conexion;

    public PersonaDAOIMP() {
        conexion = new ConexionDB();
    }

    @Override
    public Boolean agregar(PersonaDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "INSERT INTO public.\"PERSONAS\"( \"PERS_CI\", \"PERS_NOMBRE\", \"PERS_APELLIDO\", \"PERS_TEL\")VALUES ( ?, ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getPers_Ci());
            ps.setString(2, dto.getPers_nombre());
            ps.setString(3, dto.getPers_apellido());
            ps.setInt(4, dto.getPers_tel());

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
            Logger.getLogger(PersonaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            
        }
    }

    @Override
    public Boolean modificar(PersonaDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "UPDATE public.\"PERSONAS\" SET \"PERS_CI\"=?, \"PERS_NOMBRE\"=?, \"PERS_APELLIDO\"=?, \"PERS_TEL\"=? WHERE \"PERS_ID_PERSONA\"=?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, dto.getPers_Ci());
            ps.setString(2, dto.getPers_nombre());
            ps.setString(3, dto.getPers_apellido());
            ps.setInt(4, dto.getPers_tel());
            ps.setInt(5, dto.getPers_id_persona());

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
            Logger.getLogger(PersonaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            
        }

    }

    @Override
    public Boolean eliminar(PersonaDTO dto) {
         throw new UnsupportedOperationException("Not supported yet."); 
    }

     public Boolean eliminar(int dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "DELETE FROM public.\"PERSONAS\" WHERE \"PERS_ID_PERSONA\"="+dto;;
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
            Logger.getLogger(PersonaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
           
        }
    }
    @Override
    public List<PersonaDTO> consultarTodos() {
        try {
            List<PersonaDTO> lista = null;
            PersonaDTO dto = null;
            query = "SELECT \"PERS_ID_PERSONA\", \"PERS_CI\", \"PERS_NOMBRE\", \"PERS_APELLIDO\", \"PERS_TEL\" FROM public.\"PERSONAS\";";
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new PersonaDTO();
                dto.setPers_id_persona(rs.getInt("PERS_ID_PERSONA"));
                dto.setPers_Ci(rs.getString("PERS_CI"));
                dto.setPers_nombre(rs.getString("PERS_NOMBRE"));
                dto.setPers_apellido(rs.getString("PERS_APELLIDO"));
                dto.setPers_tel(rs.getInt("PERS_TEL"));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public PersonaDTO consultarSegunId(Integer id) {
         try {
             PersonaDTO dto =null;
            query = "SELECT \"PERS_ID_PERSONA\", \"PERS_CI\", \"PERS_NOMBRE\", \"PERS_APELLIDO\", \"PERS_TEL\"\n" +
                    "FROM public.\"PERSONAS\" WHERE \"PERS_ID_PERSONA\" = "+id;
            
            
            ps = conexion.obtenerConexion().prepareStatement(query);
            
             rs = ps.executeQuery();
             while (rs.next()) {
                dto= new PersonaDTO();
               
                dto.setPers_id_persona(rs.getInt("PERS_ID_PERSONA"));
                dto.setPers_Ci(rs.getString("PERS_CI"));
                dto.setPers_nombre(rs.getString("PERS_NOMBRE"));
                dto.setPers_apellido(rs.getString("PERS_APELLIDO"));
                dto.setPers_tel(rs.getInt("PERS_TEL"));
                
            } return dto;

        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }finally {
           
        }
    }

    @Override
    public PersonaDTO consultarSegunCadena(String cadena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMsj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
