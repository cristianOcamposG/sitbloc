
package Genericos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {

    public static Connection cn;
    public Statement st;
    public ResultSet rs;
    public enum TR {
        INICIAR, CONFIRMAR, CANCELAR
    };
    private Connection conexion;
    private String msj;

    public ConexionDB() {
        if (conexion == null) {
            establecerConexion(TipoMotorDB.POSTGRESQL);
        }
    }

    private void establecerConexion(TipoMotorDB tipoMotorBD) {
        String url = "jdbc:" + tipoMotorBD.getMotorBD() + "://" + tipoMotorBD.getHost() + ":" + tipoMotorBD.getPuertoBD()
                + "/" + tipoMotorBD.getNombreBD();
        System.out.println("url " + url);
        try {
            Class.forName(tipoMotorBD.getControlador());
            conexion = DriverManager.getConnection(url, tipoMotorBD.getUsuarioBD(),
                    tipoMotorBD.getClaveBD());
            if (conexion == null) {
                msj = " CONEXION NO ESTABLECIDA - Parametros de conexión no válidos ";
            } else {
                msj = " CONEXION ESTABLECIDA ";

            }
        } catch (ClassNotFoundException | SQLException e) {
            msj = "Error durante la conexión a la base de datos " + e.getMessage();
        }
    }

    public Connection obtenerConexion() {
        if (conexion == null) {
            establecerConexion(TipoMotorDB.POSTGRESQL);
        }
        return conexion;
    }

    public void Transaccion(TR accion) {
        try {
            switch (accion) {
                case INICIAR:
                    conexion.setTransactionIsolation(
                            Connection.TRANSACTION_SERIALIZABLE);
                    conexion.setAutoCommit(false);
                    break;
                case CONFIRMAR:
                    conexion.commit();
                    conexion.setAutoCommit(true);
                    break;
                case CANCELAR:
                    conexion.rollback();
                    conexion.setAutoCommit(true);
                    break;
            }
        } catch (SQLException ex) {
            msj = "Error al establecer estado de transacciones.";
        }
    }
    
    public ResultSet consultar(String sql) throws SQLException{
        st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery(sql);
        return rs;
    }

    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            msj = "No se pudo cerrar la conexion " + ex.getMessage();
        }
    }

    public String getMsj() {
        return msj;
    }
}

