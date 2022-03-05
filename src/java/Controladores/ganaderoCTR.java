
package Controladores;

import DAOIMP.AlimentoDAOIMP;
import DAOIMP.ControlDAOIMP;
import DAOIMP.GanadoDAOIMP;
import DAOIMP.LoteDAOIMP;
import DAOIMP.RazaDAOIMP;
import DAOIMP.UsuarioDAOIMP;
import DTO.AlimentoDTO;
import DTO.ControlDTO;
import DTO.GanadoDTO;
import DTO.LoteDTO;
import DTO.RazaDTO;
import DTO.UsuarioDTO;
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


@WebServlet(name = "ganaderoCTR", urlPatterns = {"/ganaderoCTR"})
public class ganaderoCTR extends HttpServlet {
//lotes 
    LoteDAOIMP lotedao = new LoteDAOIMP();
    LoteDTO lotedto = new LoteDTO();
    int idlote;
     //razas
    RazaDAOIMP razadao = new RazaDAOIMP();
    RazaDTO razadto = new RazaDTO();
    int idraza;
      GanadoDAOIMP ganadodao = new GanadoDAOIMP();
    GanadoDTO ganadodto = new GanadoDTO();
    int idganado;
      //aliemntos
    AlimentoDAOIMP alimentodao = new AlimentoDAOIMP();
    AlimentoDTO alimentodto = new AlimentoDTO();
    int idalimento;
      UsuarioDAOIMP usuariodao = new UsuarioDAOIMP();
    UsuarioDTO usuariodto = new UsuarioDTO();
    int idusuario;
    
    //controles
    ControlDAOIMP controldao = new ControlDAOIMP();
    ControlDTO controldto = new ControlDTO();
    int idcontrol;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
       
        if (menu.equals("inicio")) {
            request.getRequestDispatcher("ganadero.jsp").forward(request, response);
        }
        if (menu.equals("ganados")) {

            switch (accion) {

          

                case "Listar":
                    List lista = ganadodao.consultarTodos();
                    request.setAttribute("ganados", lista);
                    request.setAttribute("raza_id_gana", razadao.consultarTodos());
                    request.setAttribute("lote_id_gana", lotedao.consultarTodos());
                    request.setAttribute("usu_id_gana", usuariodao.consultarTodos());
                    break;
                case "Listar1":
                    List lista1 = ganadodao.consultarReportes();
                    request.setAttribute("ganados", lista1);

                    break;
                case "Validar Padre":

                    String codigopadre = String.format(request.getParameter("codigopadre"));
                    ganadodto = ganadodao.ConsultaPadre(codigopadre);
                    request.setAttribute("padre", ganadodto);


                    break;
                case "Validar Madre":
                    String codigomadre = String.format(request.getParameter("codigomadre"));
                    ganadodto = ganadodao.ConsultaPadre(codigomadre);
                  request.setAttribute("madre", ganadodto);


                    break;

                case "Guardar Nuevo Ganado":
                    String codgana = request.getParameter("codgana");
                    Date fecnac = Util.getDateSQL(request.getParameter("fecnac"));
                    String sexogana = request.getParameter("sexogana");
                    String estadogana = request.getParameter("estadogana");
                    int id_raza_gana = Integer.parseInt(request.getParameter("id_raza_gana"));
                    int pesogana = Integer.parseInt(request.getParameter("pesogana"));
                    int id_lote_gana = Integer.parseInt(request.getParameter("id_lote_gana"));
                    //int id_usu_gana = Integer.parseInt(request.getParameter("id_usu_gana"));
                    String codigopadre1 = request.getParameter("codigopadre");
                    String codimadre1 = request.getParameter("codigomadre");
                    ganadodto.setGana_padre_ganado(codigopadre1);
                    ganadodto.setGana_madre_ganado(codimadre1);
                    ganadodto.setGana_cod_ganado(codgana);
                    ganadodto.setGana_fec_nacimiento(fecnac);
                    ganadodto.setGana_sexo(sexogana);
                    ganadodto.setGana_estado(estadogana);
                    ganadodto.setRaza(new RazaDTO(id_raza_gana));
                    ganadodto.setGana_peso_ganado(pesogana);
                    ganadodto.setLote(new LoteDTO(id_lote_gana));
                    //ganadodto.setUsuario(new UsuarioDTO(id_usu_gana));

                    ganadodao.agregar(ganadodto);
                    request.getRequestDispatcher("ganaderoCTR?menu=ganados&accion=Listar").forward(request, response);
                    break;

                case "modificar":
                    idganado = Integer.parseInt(request.getParameter("id_ganado"));
                    GanadoDTO ganado = ganadodao.consultarSegunId(idganado);
                    request.setAttribute("ganado", ganado);
                    
                    request.getRequestDispatcher("ganaderoCTR?menu=ganados&accion=Listar").forward(request, response);

                    break;

                case "Actualizar":
                    String codgana1 = request.getParameter("codgana");
                    Date fecnac1 = Util.getDateSQL(request.getParameter("fecnac"));
                    String sexogana1 = request.getParameter("sexogana");
                    String estadogana1 = request.getParameter("estadogana");
                    int id_raza_gana1 = Integer.parseInt(request.getParameter("id_raza_gana"));
                    int pesogana1 = Integer.parseInt(request.getParameter("pesogana"));
                    int id_lote_gana1 = Integer.parseInt(request.getParameter("id_lote_gana"));
//                    int id_usu_gana1 = Integer.parseInt(request.getParameter("id_usu_gana"));
                    String codigopadre2 = request.getParameter("codigopadre");
                    String codimadre2 = request.getParameter("codigomadre");
                    ganadodto.setGana_padre_ganado(codigopadre2);
                    ganadodto.setGana_madre_ganado(codimadre2);
                    ganadodto.setGana_cod_ganado(codgana1);
                    ganadodto.setGana_fec_nacimiento(fecnac1);
                    ganadodto.setGana_sexo(sexogana1);
                    ganadodto.setGana_estado(estadogana1);
                    ganadodto.setRaza(new RazaDTO(id_raza_gana1));
                    ganadodto.setGana_peso_ganado(pesogana1);
                    ganadodto.setLote(new LoteDTO(id_lote_gana1));
               //     ganadodto.setUsuario(new UsuarioDTO(id_usu_gana1));
                    ganadodto.setGana_id_ganado(idganado);
                    ganadodao.modificar(ganadodto);
                    request.getRequestDispatcher("ganaderoCTR?menu=ganados&accion=Listar").forward(request, response);

                    break;
                case "eliminar":
                    idganado = Integer.parseInt(request.getParameter("id_ganado"));
                    ganadodao.eliminar(idganado);
                    request.getRequestDispatcher("ganaderoCTR?menu=ganados&accion=Listar").forward(request, response);
                    break;

//                case "Agregar origen":
//                    String codigopadre1 = request.getParameter("codigopadre");
//                    String codimadre1 = request.getParameter("codigomadre");
//                    ganadodto.setGana_padre_ganado(codigopadre1);
//                    ganadodto.setGana_madre_ganado(codimadre1);
//                    ganadodao.Actualizarorigen(ganadodto);
//                    request.getRequestDispatcher("principalCTR?menu=ganados&accion=Listar").forward(request, response);
//
//                    break;
                case "validar_padre":

                    break;

                default:

                    throw new AssertionError();
            }
     
        
            request.getRequestDispatcher("ganadoganadero.jsp").forward(request, response);
        }
         if (menu.equals("controles")) {
            switch (accion) {

                case "Listar":
                    List lista = controldao.consultarTodos();
                    request.setAttribute("controles", lista);
                    request.setAttribute("usu_id_cont", usuariodao.consultarTodos());
                    request.setAttribute("alim_id_cont", alimentodao.consultarTodos());

                    break;
                case "Consultar":

                    String ganacontrol = String.format(request.getParameter("ganacontrol"));
                    controldto = controldao.Consulta(ganacontrol);
                    request.setAttribute("control1", controldto);
                    request.getRequestDispatcher("ganaderoCTR?menu=ganados&accion=Listar").forward(request, response);

                    break;
                case "Guardar":
                    int ganacont = Integer.parseInt(request.getParameter("idganacont"));
                    Date fechcont = Util.getDateSQL(request.getParameter("fechcont"));
                    int pesogana = Integer.parseInt(request.getParameter("pesogana"));
                    int id_alim_cont = Integer.parseInt(request.getParameter("id_alim_cont"));
                    int id_usu_cont = Integer.parseInt(request.getParameter("id_usu_cont"));
                    String obsercontr = request.getParameter("obsercontr");
                    controldto.setCont_ganado(new GanadoDTO(ganacont));
                    controldto.setCont_fecha(fechcont);
                    controldto.setCont_peso(pesogana);
                    controldto.setCont_alimento(new AlimentoDTO(id_alim_cont));
                    controldto.setCont_usuario(new UsuarioDTO(id_usu_cont));
                    controldto.setCont_observacion(obsercontr);
                    controldao.agregar(controldto);
                    request.getRequestDispatcher("ganaderoCTR?menu=ganados&accion=Listar").forward(request, response);
                    break;
                case "modificar":
                    idcontrol = Integer.parseInt(request.getParameter("id_control"));
                    ControlDTO control = controldao.consultarSegunId(idcontrol);
                    request.setAttribute("control", control);
                    request.setAttribute("control1", control);
                    request.getRequestDispatcher("ganaderoCTR?menu=ganados&accion=Listar").forward(request, response);

                    break;
                case "Actualizar":

                    Date fechcon = Util.getDateSQL(request.getParameter("fechcont"));
                    int pesocont = Integer.parseInt(request.getParameter("pesogana"));
                    int id_alim_contr = Integer.parseInt(request.getParameter("id_alim_cont"));
                    int id_usu_cont1 = Integer.parseInt(request.getParameter("id_usu_cont"));
                    String obsercontr1 = request.getParameter("obsercontr");

                    controldto.setCont_fecha(fechcon);
                    controldto.setCont_peso(pesocont);
                    controldto.setCont_alimento(new AlimentoDTO(id_alim_contr));
                    controldto.setCont_usuario(new UsuarioDTO(id_usu_cont1));
                    controldto.setCont_id_control(idcontrol);
                    controldto.setCont_observacion(obsercontr1);
                    controldao.modificar(controldto);
                    request.getRequestDispatcher("ganaderoCTR?menu=ganados&accion=Listar").forward(request, response);

                    break;
                case "eliminar":
                    idcontrol = Integer.parseInt(request.getParameter("id_control"));
                    controldao.eliminar(idcontrol);
                    request.getRequestDispatcher("ganaderoCTR?menu=ganados&accion=Listar").forward(request, response);
                    break;
                default:

                    throw new AssertionError();
            }

            request.getRequestDispatcher("Control.jsp").forward(request, response);
        }
         if (menu.equals("Salir")) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        
        
    }


    
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
