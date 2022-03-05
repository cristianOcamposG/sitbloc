package Controladores;

import DAO.UsuarioDAO;
import DAOIMP.UsuarioDAOIMP;
import DTO.UsuarioDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidarCTR extends HttpServlet {

    UsuarioDAOIMP usudao = new UsuarioDAOIMP();
    public static UsuarioDTO  usudto = new UsuarioDTO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("Ingresar")) {
            String user = request.getParameter("txtuser");
            String pass = request.getParameter("txtpass");
            usudto = usudao.validar(user, pass);
            if (usudto.getUsu_nombre()!=null && usudto.getUsu_rol()==1 && usudto.getUsu_estado()==1 ) {
                        
                request.setAttribute("USU_NOMBRE", usudto);
              
                request.getRequestDispatcher("principalCTR?menu=principal").forward(request, response);
            } else  if (usudto.getUsu_nombre()!=null && usudto.getUsu_rol()==2 && usudto.getUsu_estado()==1 ){
                request.setAttribute("USU_NOMBRE", usudto);
                
                request.getRequestDispatcher("ganaderoCTR?menu=inicio").forward(request, response);
              
            }else if (usudto.getUsu_nombre()!=null && usudto.getUsu_rol()==3 && usudto.getUsu_estado()==1 ) {
                request.setAttribute("USU_NOMBRE", usudto);
                
                request.getRequestDispatcher("sanidadCTR?menu=inicio").forward(request, response);
              
            }else {
        request.getRequestDispatcher("index.jsp").forward(request, response);
            
         
        }
        }  if (accion.equalsIgnoreCase("Salir")) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
