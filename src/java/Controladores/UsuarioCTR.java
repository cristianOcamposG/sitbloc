package Controladores;

import DAO.UsuarioDAO;
import DAOIMP.PersonaDAOIMP;
import DAOIMP.UsuarioDAOIMP;
import DTO.PersonaDTO;
import DTO.UsuarioDTO;
import Genericos.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UsuarioCTR", urlPatterns = {"/UsuarioCTR"})
public class UsuarioCTR extends HttpServlet {

    private UsuarioDAOIMP dao;
    private UsuarioDTO dto;
    private PersonaDTO personaDTO;
    private PersonaDAOIMP personaDAO;

    public static Integer id, rol, estado, ID_USU_PERSONA;
    private String nombre, contraseña;

    private Util.ACCION accion;
    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.request = request;
        this.response = response;
        session = request.getSession();
        accion = Util.getAccion(request);
        if (accion == null) {
            request.getRequestDispatcher("/Usuario.jsp").forward(request, response);
        }
        switch (accion) {
            case listar:
              
                break;

        }

        id = Util.getInt(request, "idusuario");
        nombre = Util.getString(request, "usunombre");
        contraseña = Util.getString(request, "USU_CONTRASE");
        rol = Util.getInt(request, "usurol");
        estado = Util.getInt(request, "usuestado");
        ID_USU_PERSONA = Util.getInt(request, "ID_USU_PERSONA");

        if (validar(accion) == true) {
            dto = new UsuarioDTO();
            dto.setUsu_id_usuario(id);
            dto.setUsu_nombre(nombre);
            dto.setUsu_contraseña(contraseña);
            dto.setUsu_estado(estado);
            dto.setUsu_rol(rol);
            dto.setPersona(new PersonaDTO(ID_USU_PERSONA));

            dao = new UsuarioDAOIMP();
            switch (accion) {
                case agregar:
                    if (dao.agregar(dto) == true) {
                        this.request.setAttribute("msg", Util.getAvisoExitos());
                    } else {
                        this.request.setAttribute("msg", Util.getAvisoError());
                    }
                    ;
                    break;
                case modificar:
                    if (dao.modificar(dto) == true) {
                        this.request.setAttribute("msg", Util.getAvisoMoficacion());

                    } else {
                        this.request.setAttribute("msg", Util.getAvisoError());

                    }
                    ;
                    break;
                case eliminar:
                    dao.eliminar(dto);
                    break;
            }
       

        } else {
            //avisooo mensaje 
        }

    }

   

    public boolean validar(Util.ACCION accion) {
        switch (accion) {
            case agregar:
                if (nombre.isEmpty()) {
                    return false;
                }
                break;
            case modificar:
            case anular:
                if (id == null || id == 0) {
                    return false;
                }

                if (nombre.isEmpty()) {
                    return false;
                }
                break;
            case eliminar:
            case recuperar:
                if (id == null || id == 0) {
                    return false;
                }
                break;

        }
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
