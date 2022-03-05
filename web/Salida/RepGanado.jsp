<%@page import="Genericos.ConexionDB"%>
<%@page import="Genericos.TipoMotorDB"%>

<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="application/pdf"%>

<%
    String dirPath="/Reporte";
    String realPath=this.getServletContext().getRealPath(dirPath);
   
    String jasperReport="ganadorepo.jasper";
    JasperPrint print=null;
    Connection conn=null;
     ConexionDB conexion = new ConexionDB();
     conexion.Transaccion(ConexionDB.TR.INICIAR);
    
    //Clientes clienteLogueado=(Clientes) sesion.getAttribute("clienteLoagueado");
    
    try{
        
       conn = conexion.obtenerConexion();
        
        Map parameters=new HashMap();
       
        //parameters.put("USUARIO",clienteLogueado.getCliente_cliente());
        
        print =JasperFillManager.fillReport(realPath+"//"+jasperReport, parameters,conn);
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }catch(Exception ex){
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    }
    finally{
        if(conn!=null){
            conn.close();
        }
    }
%>