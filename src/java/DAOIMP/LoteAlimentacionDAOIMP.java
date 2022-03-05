
package DAOIMP;

import DAO.LoteAlimentacionDAO;
import DTO.AlimentoDTO;
import DTO.LoteAlimentacionDTO;
import DTO.LoteDTO;
import Genericos.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoteAlimentacionDAOIMP implements LoteAlimentacionDAO {

    private String query, msj;
    private PreparedStatement ps;
    private ResultSet rs;
    private ConexionDB conexion;

    public LoteAlimentacionDAOIMP() {
        conexion = new ConexionDB();
    }

    @Override
    public Boolean agregar(LoteAlimentacionDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "INSERT INTO public.\"LOTE_ALIMENTACION\"(\"LOTE_ID_LOTE_ALIM\", \"LOTE_ID_ALIMENTACION\", \"LOTE_ALIM_MARCA\") VALUES (?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setInt(1, dto.getLote__alim().getLote_id_lote());
            ps.setInt(2, dto.getLote_alimentacion().getAlimento_id_alimento());
            ps.setString(3, dto.getLote_alim_marca());

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
            Logger.getLogger(LoteAlimentacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            conexion.cerrarConexion();
        }
    }

    @Override
    public Boolean modificar(LoteAlimentacionDTO dto) {
        try {
            conexion.Transaccion(ConexionDB.TR.INICIAR);
            query = "UPDATE public.\"LOTE_ALIMENTACION\" SET \"LOTE_ID_ALIMENTACION\"=?, \"LOTE_ALIM_MARCA\"=? WHERE \"LOTE_ID_LOTE_ALIM\"=?;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setInt(1, dto.getLote_alimentacion().getAlimento_id_alimento());
            ps.setString(2, dto.getLote_alim_marca());
            ps.setInt(3, dto.getLote__alim().getLote_id_lote());
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
            Logger.getLogger(LoteAlimentacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            conexion.cerrarConexion();
        }
    }

    @Override
    public Boolean eliminar(LoteAlimentacionDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoteAlimentacionDTO> consultarTodos() {
        try {
            List<LoteAlimentacionDTO> lista;
            query = "SELECT \"LOTE_DESCRIPCION\", \"ALIM_DESCRIPCION\", \"LOTE_ALIM_MARCA\""
                    + "	FROM public.\"LOTE_ALIMENTACION\", public.\"LOTES\", public.\"ALIMENTOS\""
                    + "	WHERE \"LOTE_ID_LOTE_ALIM\" = \"LOTE_ID_LOTE\""
                    + "	AND \"LOTE_ID_ALIMENTACION\" = \"ALIM_ID_ALIMENTOS\";";
            ps = conexion.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                LoteAlimentacionDTO dto = new LoteAlimentacionDTO();
                dto.setLote_alim_marca(rs.getString("LOTE_ALIM_MARCA"));
                LoteDTO lote = new LoteDTO();
                lote.setLote_descripcion(rs.getString("LOTE_DESCRIPCION"));
                dto.setLote__alim(lote);
                AlimentoDTO alimento = new AlimentoDTO();
                alimento.setAlim_descripcion(rs.getString("ALIM_DESCRIPCION"));
                dto.setLote_alimentacion(alimento);

                lista.add(dto);

            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(LoteAlimentacionDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public LoteAlimentacionDTO consultarSegunId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoteAlimentacionDTO consultarSegunCadena(String cadena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMsj() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
