/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAOIMP.EnfermedadDAOIMP;
import DAOIMP.GestacionDAOIMP;
import DAOIMP.NombreVacunacionDAOIMP;
import DAOIMP.UsuarioDAOIMP;
import DAOIMP.VacunacionDAOIMP;
import DTO.EnfermedadDTO;
import DTO.GanadoDTO;
import DTO.GestacionDTO;
import DTO.NombreVacunacionDTO;
import DTO.UsuarioDTO;
import DTO.VacunacionDTO;
import Genericos.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "sanidadCTR", urlPatterns = {"/sanidadCTR"})
public class sanidadCTR extends HttpServlet {
     //vacunaciones
    VacunacionDAOIMP vacunaciondao = new VacunacionDAOIMP();
    VacunacionDTO vacunaciondto = new VacunacionDTO();
    int idvacunacion;

    //nombvac
    NombreVacunacionDAOIMP nomvacdao = new NombreVacunacionDAOIMP();
    NombreVacunacionDTO nomvacdto = new NombreVacunacionDTO();
    int idnomvac;
     //enfermedades
    EnfermedadDAOIMP enfermedaddao = new EnfermedadDAOIMP();
    EnfermedadDTO enfermedaddto = new EnfermedadDTO();
    int idenfermedad;

    //gestaciones 
    GestacionDAOIMP gestaciondao = new GestacionDAOIMP();
    GestacionDTO gestaciondto = new GestacionDTO();
    int idgestacion;
    //usuarios
    UsuarioDAOIMP usuariodao = new UsuarioDAOIMP();
    UsuarioDTO usuariodto = new UsuarioDTO();
    int idusuario;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        
        if (menu.equals("inicio")) {
            request.getRequestDispatcher("sanidad.jsp").forward(request, response);
        }
         if (menu.equals("enfermedades")) {
            switch (accion) {
                case "Listar":
                    List lista = enfermedaddao.consultarTodos();
                    request.setAttribute("enfermedades", lista);
                    request.setAttribute("vac_id_enf", nomvacdao.consultarTodos());
                    request.setAttribute("usu_id_enf", usuariodao.consultarTodos());
                    break;
                case "Consultar":
                    String ganaenfe = String.format(request.getParameter("ganaenfe"));
                    enfermedaddto = enfermedaddao.Consultar(ganaenfe);
                    request.setAttribute("enfermedad2", enfermedaddto);
                    request.getRequestDispatcher("principalCTR?menu=enfermedades&accion=Listar").forward(request, response);
                    break;

                case "Guardar":
                    int idganaenf = Integer.parseInt(request.getParameter("idganaenf"));
                    Date fechenf = Util.getDateSQL(request.getParameter("fechenf"));
                    String nomenfe = request.getParameter("descenfe");
                    String notaenf = request.getParameter("notaenf");
                    int id_vac_enf = Integer.parseInt(request.getParameter("id_vac_enf"));
                    int id_usu_enf = Integer.parseInt(request.getParameter("id_usu_enf"));

                    enfermedaddto.setEnfe_ganado(new GanadoDTO(idganaenf));
                    enfermedaddto.setEnfe_fecha(fechenf);
                    enfermedaddto.setEnfe_descripcion(nomenfe);
                    enfermedaddto.setEnfe_notas(notaenf);
                    enfermedaddto.setEnfe_vacunacion(new NombreVacunacionDTO(id_vac_enf));
                    enfermedaddto.setEnfe_usuario(new UsuarioDTO(id_usu_enf));
                    enfermedaddao.agregar(enfermedaddto);
                    request.getRequestDispatcher("principalCTR?menu=enfermedades&accion=Listar").forward(request, response);
                    break;

                case "modificar":
                    idenfermedad = Integer.parseInt(request.getParameter("id_enfermedad"));
                    EnfermedadDTO enfermedad = enfermedaddao.consultarSegunId(idenfermedad);
                    request.setAttribute("enfermedad", enfermedad);
                    request.setAttribute("enfermedad2", enfermedad);
                    request.getRequestDispatcher("principalCTR?menu=enfermedades&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":

                    Date fechenf1 = Util.getDateSQL(request.getParameter("fechenf"));
                    String nomenfe1 = request.getParameter("descenfe");
                    String notaenf1 = request.getParameter("notaenf");
                    int id_vac_enf1 = Integer.parseInt(request.getParameter("id_vac_enf"));
                    int id_usu_enf1 = Integer.parseInt(request.getParameter("id_usu_enf"));

                    enfermedaddto.setEnfe_fecha(fechenf1);
                    enfermedaddto.setEnfe_descripcion(nomenfe1);
                    enfermedaddto.setEnfe_notas(notaenf1);
                    enfermedaddto.setEnfe_vacunacion(new NombreVacunacionDTO(id_vac_enf1));
                    enfermedaddto.setEnfe_usuario(new UsuarioDTO(id_usu_enf1));
                    enfermedaddto.setEnfe_id_enfermedades(idenfermedad);
                    enfermedaddao.modificar(enfermedaddto);
                    request.getRequestDispatcher("principalCTR?menu=enfermedades&accion=Listar").forward(request, response);
                    break;
                case "eliminar":
                    idenfermedad = Integer.parseInt(request.getParameter("id_enfermedad"));
                    enfermedaddao.eliminar(idenfermedad);
                    request.getRequestDispatcher("principalCTR?menu=enfermedades&accion=Listar").forward(request, response);
                    break;
                default:

                    throw new AssertionError();
            }
            request.getRequestDispatcher("Enfermedades.jsp").forward(request, response);
        }
        
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
