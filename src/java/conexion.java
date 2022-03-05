import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
 
public class conexion {

    public static   void main(String[] args) {
        final String URL="jdbc:postgresql://localhost:5432/sitbloc";
        final String DRIVER="org.postgresql.Driver";
        final String USUARIO="postgres";
        final String CLAVE="criss1998a";

        Connection con = null;
        
        try {
            Class.forName(DRIVER);
            try {
                con = DriverManager.getConnection(URL, USUARIO, CLAVE);
                String sql="SELECT \"PERS_NOMBRE\", \"PERS_APELLIDO\"\n" +
"	FROM public.\"PERSONAS\";";      
                PreparedStatement pstn = con.prepareStatement(sql);
                ResultSet rst = pstn.executeQuery();
                while(rst.next()){
                        System.out.println(rst.getInt(1));
                        System.out.println(rst.getString("PERS_NOMBRE"));
                        System.out.println(rst.getString("PERS_APELLIDO"));
                }          
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                
            }finally{
                if (con != null){
                    try {
                        con.close();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    
        
    }
}


