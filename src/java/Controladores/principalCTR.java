package Controladores;

import DAO.UsuarioDAO;
import DAOIMP.AlimentoDAOIMP;
import DAOIMP.ControlDAOIMP;
import DAOIMP.EnfermedadDAOIMP;
import DAOIMP.GanadoDAOIMP;
import DAOIMP.GestacionDAOIMP;
import DAOIMP.LoteDAOIMP;
import DAOIMP.NombreVacunacionDAOIMP;
import DAOIMP.PersonaDAOIMP;
import DAOIMP.RazaDAOIMP;
import DAOIMP.UsuarioDAOIMP;
import DAOIMP.VacunacionDAOIMP;
import DTO.AlimentoDTO;
import DTO.ControlDTO;
import DTO.EnfermedadDTO;
import DTO.GanadoDTO;
import DTO.GestacionDTO;
import DTO.LoteDTO;
import DTO.NombreVacunacionDTO;
import DTO.PersonaDTO;
import DTO.RazaDTO;
import DTO.UsuarioDTO;
import DTO.VacunacionDTO;
import Genericos.Util;

import java.io.IOException;
import static java.lang.System.out;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class principalCTR extends HttpServlet {

    //lotes 
    LoteDAOIMP lotedao = new LoteDAOIMP();
    LoteDTO lotedto = new LoteDTO();
    int idlote;

    //ganados
    GanadoDAOIMP ganadodao = new GanadoDAOIMP();
    GanadoDTO ganadodto = new GanadoDTO();
    int idganado;

    //razas
    RazaDAOIMP razadao = new RazaDAOIMP();
    RazaDTO razadto = new RazaDTO();
    int idraza;

    //usuarios
    UsuarioDAOIMP usuariodao = new UsuarioDAOIMP();
    UsuarioDTO usuariodto = new UsuarioDTO();
    int idusuario;

    //controles
    ControlDAOIMP controldao = new ControlDAOIMP();
    ControlDTO controldto = new ControlDTO();
    int idcontrol;

    //aliemntos
    AlimentoDAOIMP alimentodao = new AlimentoDAOIMP();
    AlimentoDTO alimentodto = new AlimentoDTO();
    int idalimento;

    //vacunaciones
    VacunacionDAOIMP vacunaciondao = new VacunacionDAOIMP();
    VacunacionDTO vacunaciondto = new VacunacionDTO();
    int idvacunacion;

    //nombvac
    NombreVacunacionDAOIMP nomvacdao = new NombreVacunacionDAOIMP();
    NombreVacunacionDTO nomvacdto = new NombreVacunacionDTO();
    int idnomvac;

    //persona
    PersonaDAOIMP personadao = new PersonaDAOIMP();
    PersonaDTO personadto = new PersonaDTO();
    int idpersona;

    //enfermedades
    EnfermedadDAOIMP enfermedaddao = new EnfermedadDAOIMP();
    EnfermedadDTO enfermedaddto = new EnfermedadDTO();
    int idenfermedad;

    //gestaciones 
    GestacionDAOIMP gestaciondao = new GestacionDAOIMP();
    GestacionDTO gestaciondto = new GestacionDTO();
    int idgestacion;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("principal")) {
            request.getRequestDispatcher("principal.jsp").forward(request, response);
        }

        if (menu.equals("lotes")) {
            switch (accion) {
                case "Listar":
                    List lista = lotedao.consultarTodos();
                    request.setAttribute("lotes", lista);

                    break;

                case "Agregar":
                    String desclote = request.getParameter("deslote");
                    lotedto.setLote_descripcion(desclote);
                    lotedao.agregar(lotedto);
                    request.getRequestDispatcher("principalCTR?menu=lotes&accion=Listar").forward(request, response);
                    break;

                case "modificar":
                    idlote = Integer.parseInt(request.getParameter("id_lote"));
                    LoteDTO lote = lotedao.consultarSegunId(idlote);
                    request.setAttribute("lote", lote);
                    request.getRequestDispatcher("principalCTR?menu=lotes&accion=Listar").forward(request, response);

                    break;
                case "Actualizar":
                    String desclote1 = request.getParameter("deslote");
                    lotedto.setLote_descripcion(desclote1);
                    lotedto.setLote_id_lote(idlote);
                    lotedao.modificar(lotedto);
                    request.getRequestDispatcher("principalCTR?menu=lotes&accion=Listar").forward(request, response);

                    break;
                case "eliminar":
                    idlote = Integer.parseInt(request.getParameter("id_lote"));
                    lotedao.eliminar(idlote);
                    request.getRequestDispatcher("principalCTR?menu=lotes&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError("");
            }

            request.getRequestDispatcher("/admin/Lote.jsp").forward(request, response);
        }
        if (menu.equals("razas")) {

            switch (accion) {
                case "Listar":
                    List lista = razadao.consultarTodos();
                    request.setAttribute("razas", lista);

                    break;

                case "Agregar":
                    String descraza = request.getParameter("desraza");
                    razadto.setRaza_descripcion(descraza);
                    razadao.agregar(razadto);
                    request.getRequestDispatcher("principalCTR?menu=razas&accion=Listar").forward(request, response);
                    break;

                case "modificar":
                    idraza = Integer.parseInt(request.getParameter("id_raza"));
                    RazaDTO raza = razadao.consultarSegunId(idraza);
                    request.setAttribute("raza", raza);
                    request.getRequestDispatcher("principalCTR?menu=razas&accion=Listar").forward(request, response);

                    break;
                case "Actualizar":
                    String descraza1 = request.getParameter("desraza");
                    razadto.setRaza_descripcion(descraza1);
                    razadto.setRaza_id_raza(idraza);
                    razadao.modificar(razadto);
                    request.getRequestDispatcher("principalCTR?menu=razas&accion=Listar").forward(request, response);

                    break;
                case "eliminar":
                    idraza = Integer.parseInt(request.getParameter("id_raza"));
                    razadao.eliminar(idraza);
                    request.getRequestDispatcher("principalCTR?menu=razas&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError("");
            }
            request.getRequestDispatcher("/admin/Raza.jsp").forward(request, response);
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
                    request.getRequestDispatcher("principalCTR?menu=controles&accion=Listar").forward(request, response);

                    break;
                case "Guardar":
                    int ganacont = Integer.parseInt(request.getParameter("idganacont"));
                    Date fechcont = Util.getDateSQL(request.getParameter("fechcont"));
                    int pesogana = Integer.parseInt(request.getParameter("pesogana"));
                    int id_alim_cont = Integer.parseInt(request.getParameter("id_alim_cont"));
//                    int id_usu_cont = Integer.parseInt(request.getParameter("id_usu_cont"));
                    String obsercontr = request.getParameter("obsercontr");

                    controldto.setCont_ganado(new GanadoDTO(ganacont));
                    controldto.setCont_fecha(fechcont);
                    controldto.setCont_peso(pesogana);
                    controldto.setCont_alimento(new AlimentoDTO(id_alim_cont));
               //     controldto.setCont_usuario(new UsuarioDTO(id_usu_cont));
                    controldto.setCont_observacion(obsercontr);
                    controldao.agregar(controldto);
                    request.getRequestDispatcher("principalCTR?menu=controles&accion=Listar").forward(request, response);
                    break;
                case "modificar":
                    idcontrol = Integer.parseInt(request.getParameter("id_control"));
                    ControlDTO control = controldao.consultarSegunId(idcontrol);
                    request.setAttribute("control", control);
                    request.setAttribute("control1", control);
                    request.getRequestDispatcher("principalCTR?menu=controles&accion=Listar").forward(request, response);

                    break;
                case "Actualizar":

                    Date fechcon = Util.getDateSQL(request.getParameter("fechcont"));
                    int pesocont = Integer.parseInt(request.getParameter("pesogana"));
                    int id_alim_contr = Integer.parseInt(request.getParameter("id_alim_cont"));
               //     int id_usu_cont1 = Integer.parseInt(request.getParameter("id_usu_cont"));
                    String obsercontr1 = request.getParameter("obsercontr");

                    controldto.setCont_fecha(fechcon);
                    controldto.setCont_peso(pesocont);
                    controldto.setCont_alimento(new AlimentoDTO(id_alim_contr));
           //         controldto.setCont_usuario(new UsuarioDTO(id_usu_cont1));
                    controldto.setCont_id_control(idcontrol);
                    controldto.setCont_observacion(obsercontr1);
                    controldao.modificar(controldto);
                    request.getRequestDispatcher("principalCTR?menu=controles&accion=Listar").forward(request, response);

                    break;
                case "eliminar":
                    idcontrol = Integer.parseInt(request.getParameter("id_control"));
                    controldao.eliminar(idcontrol);
                    request.getRequestDispatcher("principalCTR?menu=controles&accion=Listar").forward(request, response);
                    break;
                default:

                    throw new AssertionError();
            }

            request.getRequestDispatcher("Control.jsp").forward(request, response);
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
              //      int id_usu_enf = Integer.parseInt(request.getParameter("id_usu_enf"));

                    enfermedaddto.setEnfe_ganado(new GanadoDTO(idganaenf));
                    enfermedaddto.setEnfe_fecha(fechenf);
                    enfermedaddto.setEnfe_descripcion(nomenfe);
                    enfermedaddto.setEnfe_notas(notaenf);
                    enfermedaddto.setEnfe_vacunacion(new NombreVacunacionDTO(id_vac_enf));
         //           enfermedaddto.setEnfe_usuario(new UsuarioDTO(id_usu_enf));
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
         //           int id_usu_enf1 = Integer.parseInt(request.getParameter("id_usu_enf"));

                    enfermedaddto.setEnfe_fecha(fechenf1);
                    enfermedaddto.setEnfe_descripcion(nomenfe1);
                    enfermedaddto.setEnfe_notas(notaenf1);
                    enfermedaddto.setEnfe_vacunacion(new NombreVacunacionDTO(id_vac_enf1));
          //          enfermedaddto.setEnfe_usuario(new UsuarioDTO(id_usu_enf1));
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
                    request.getRequestDispatcher("principalCTR?menu=ganados&accion=Listar").forward(request, response);
                    break;

                case "modificar":
                    idganado = Integer.parseInt(request.getParameter("id_ganado"));
                    GanadoDTO ganado = ganadodao.consultarSegunId(idganado);
                    request.setAttribute("ganado", ganado);
                    
                    request.getRequestDispatcher("principalCTR?menu=ganados&accion=Listar").forward(request, response);

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
                    request.getRequestDispatcher("principalCTR?menu=ganados&accion=Listar").forward(request, response);

                    break;
                case "eliminar":
                    idganado = Integer.parseInt(request.getParameter("id_ganado"));
                    ganadodao.eliminar(idganado);
                    request.getRequestDispatcher("principalCTR?menu=ganados&accion=Listar").forward(request, response);
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
            request.getRequestDispatcher("Ganado.jsp").forward(request, response);
        }
        if (menu.equals("gestaciones")) {
            switch (accion) {
                case "Listar":
                    List lista = gestaciondao.consultarTodos();
                    request.setAttribute("gestaciones", lista);

                    break;
                case "Consultar":
                    String ganagest = String.format(request.getParameter("ganagest"));
                    gestaciondto = gestaciondao.Consultar(ganagest);
                    request.setAttribute("gestacion2", gestaciondto);
                    request.getRequestDispatcher("principalCTR?menu=gestaciones&accion=Listar").forward(request, response);
                    break;

                case "Guardar":
                    int ganagesta = Integer.parseInt(request.getParameter("idganagest"));
                    Date fechgest = Util.getDateSQL(request.getParameter("fechgest"));
                    int tipogest = Integer.parseInt(request.getParameter("tipogest"));
                    int estadogest = Integer.parseInt(request.getParameter("estadogest"));
                    Date fechfina = Util.getDateSQL(request.getParameter("fechfina"));

                    gestaciondto.setGest_ganado(new GanadoDTO(ganagesta));
                    gestaciondto.setGest_fecha_gestacion(fechgest);
                    gestaciondto.setGest_tipo(tipogest);
                    gestaciondto.setGest_estado(estadogest);
                    gestaciondto.setGest_fecha_fina(fechfina);
                    gestaciondao.agregar(gestaciondto);
                    request.getRequestDispatcher("principalCTR?menu=gestaciones&accion=Listar").forward(request, response);
                    break;

                case "modificar":
                    idgestacion = Integer.parseInt(request.getParameter("id_gestacion"));
                    GestacionDTO gestacion = gestaciondao.consultarSegunId(idgestacion);
                    request.setAttribute("gestacion", gestacion);
                    request.setAttribute("gestacion2", gestacion);
                    request.getRequestDispatcher("principalCTR?menu=gestaciones&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":

                    Date fechgest1 = Util.getDateSQL(request.getParameter("fechgest"));
                    int tipogest1 = Integer.parseInt(request.getParameter("tipogest"));
                    int estadogest1 = Integer.parseInt(request.getParameter("estadogest"));
                    Date fechfina1 = Util.getDateSQL(request.getParameter("fechfina"));

                    gestaciondto.setGest_fecha_gestacion(fechgest1);
                    gestaciondto.setGest_tipo(tipogest1);
                    gestaciondto.setGest_estado(estadogest1);
                    gestaciondto.setGest_fecha_fina(fechfina1);
                    gestaciondto.setGest_id_gestacion(idgestacion);
                    gestaciondao.modificar(gestaciondto);
                    request.getRequestDispatcher("principalCTR?menu=gestaciones&accion=Listar").forward(request, response);
                    break;
                case "eliminar":
                    idgestacion = Integer.parseInt(request.getParameter("id_gestacion"));
                    gestaciondao.eliminar(idgestacion);
                    request.getRequestDispatcher("principalCTR?menu=gestaciones&accion=Listar").forward(request, response);
                    break;
                default:

                    throw new AssertionError();
            }
            request.getRequestDispatcher("Gestacion.jsp").forward(request, response);
        }
        if (menu.equals("nombresvacunaciones")) {
            switch (accion) {
                case "Listar":
                    List lista = nomvacdao.consultarTodos();
                    request.setAttribute("nombresvacunaciones", lista);

                    break;

                case "Agregar":
                    String descnombrevacunacion = request.getParameter("desnombrevacunacion");
                    nomvacdto.setNombre_vacu(descnombrevacunacion);
                    nomvacdao.agregar(nomvacdto);
                    request.getRequestDispatcher("principalCTR?menu=nombresvacunaciones&accion=Listar").forward(request, response);
                    break;

                case "modificar":
                    idnomvac = Integer.parseInt(request.getParameter("id_nombrevacunacion"));
                    NombreVacunacionDTO nombrevacunacion = nomvacdao.consultarSegunId(idnomvac);
                    request.setAttribute("nombrevacunacion", nombrevacunacion);
                    request.getRequestDispatcher("principalCTR?menu=nombresvacunaciones&accion=Listar").forward(request, response);

                    break;
                case "Actualizar":
                    String descnombrevacunacion1 = request.getParameter("desnombrevacunacion");
                    nomvacdto.setNombre_vacu(descnombrevacunacion1);
                    nomvacdto.setId_nom_vacu(idnomvac);
                    nomvacdao.modificar(nomvacdto);
                    request.getRequestDispatcher("principalCTR?menu=nombresvacunaciones&accion=Listar").forward(request, response);

                    break;
                case "eliminar":
                    idnomvac = Integer.parseInt(request.getParameter("id_nombrevacunacion"));
                    nomvacdao.eliminar(idnomvac);
                    request.getRequestDispatcher("principalCTR?menu=nombresvacunaciones&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError("");
            }
            request.getRequestDispatcher("/admin/NombreVacunacion.jsp").forward(request, response);
        }
        if (menu.equals("alimentos")) {
            switch (accion) {
                case "Listar":
                    List lista = alimentodao.consultarTodos();
                    request.setAttribute("alimentos", lista);

                    break;

                case "Agregar":
                    String descalimento = request.getParameter("desalimento");
                    alimentodto.setAlim_descripcion(descalimento);
                    alimentodao.agregar(alimentodto);
                    request.getRequestDispatcher("principalCTR?menu=alimentos&accion=Listar").forward(request, response);
                    break;

                case "modificar":
                    idalimento = Integer.parseInt(request.getParameter("id_alimento"));
                    AlimentoDTO alimento = alimentodao.consultarSegunId(idalimento);
                    request.setAttribute("alimento", alimento);
                    request.getRequestDispatcher("principalCTR?menu=alimentos&accion=Listar").forward(request, response);

                    break;
                case "Actualizar":
                    String descalimento1 = request.getParameter("desalimento");
                    alimentodto.setAlim_descripcion(descalimento1);
                    alimentodto.setAlimento_id_alimento(idalimento);
                    alimentodao.modificar(alimentodto);
                    request.getRequestDispatcher("principalCTR?menu=alimentos&accion=Listar").forward(request, response);

                    break;
                case "eliminar":
                    idalimento = Integer.parseInt(request.getParameter("id_alimento"));
                    alimentodao.eliminar(idalimento);
                    request.getRequestDispatcher("principalCTR?menu=alimentos&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError("");
            }

            request.getRequestDispatcher("/admin/Alimento.jsp").forward(request, response);
        }
        if (menu.equals("personas")) {
            switch (accion) {
                case "Listar":
                    List lista = personadao.consultarTodos();
                    request.setAttribute("personas", lista);

                    break;

                case "Agregar":
                    String nompersona = request.getParameter("nompersona");
                    String cipersona = request.getParameter("cipersona");
                    int telpersona = Integer.parseInt(request.getParameter("telpersona"));
                    String apepersona = request.getParameter("apepersona");
                    personadto.setPers_nombre(nompersona);
                    personadto.setPers_Ci(cipersona);
                    personadto.setPers_apellido(apepersona);
                    personadto.setPers_tel(telpersona);

                    personadao.agregar(personadto);
                    request.getRequestDispatcher("principalCTR?menu=personas&accion=Listar").forward(request, response);
                    break;

                case "modificar":
                    idpersona = Integer.parseInt(request.getParameter("id_persona"));
                    PersonaDTO persona = personadao.consultarSegunId(idpersona);
                    request.setAttribute("persona", persona);
                    request.getRequestDispatcher("principalCTR?menu=personas&accion=Listar").forward(request, response);

                    break;
                case "Actualizar":
                    String nmpersona = request.getParameter("nompersona");
                    String cedulapersona = (request.getParameter("cipersona"));
                    int telepersona = Integer.parseInt(request.getParameter("telpersona"));
                    String appersona = request.getParameter("apepersona");
                    personadto.setPers_nombre(nmpersona);
                    personadto.setPers_id_persona(idpersona);
                    personadto.setPers_Ci(cedulapersona);
                    personadto.setPers_tel(telepersona);
                    personadto.setPers_apellido(appersona);
                    personadao.modificar(personadto);
                    request.getRequestDispatcher("principalCTR?menu=personas&accion=Listar").forward(request, response);

                    break;
                case "eliminar":
                    idpersona = Integer.parseInt(request.getParameter("id_persona"));
                    personadao.eliminar(idpersona);
                    request.getRequestDispatcher("principalCTR?menu=personas&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError("");
            }

            request.getRequestDispatcher("/admin/Persona.jsp").forward(request, response);
        }
        if (menu.equals("usuarios")) {

            switch (accion) {
                case "Listar":
                    List lista = usuariodao.consultarTodos();
                    request.setAttribute("usuarios", lista);

                    break;
                case "Buscar":
                    String ciper = String.format(request.getParameter("ciper"));
                    usuariodto = usuariodao.Consultanombre(ciper);
                    request.setAttribute("usuario1", usuariodto);
                    request.getRequestDispatcher("principalCTR?menu=usuarios&accion=Listar").forward(request, response);

                    break;

                case "Agregar":
                    String nombre = request.getParameter("nombre");
                    String clave = request.getParameter("clave");
                    int rol = Integer.parseInt(request.getParameter("rol"));
                    int estado = Integer.parseInt(request.getParameter("estado"));
                    int idpersona = Integer.parseInt(request.getParameter("idper"));
                    usuariodto.setPersona(new PersonaDTO(idpersona));
                    usuariodto.setUsu_estado(estado);
                    usuariodto.setUsu_rol(rol);
                    usuariodto.setUsu_contraseña(clave);
                    usuariodto.setUsu_nombre(nombre);
                    usuariodao.agregar(usuariodto);

                    request.getRequestDispatcher("principalCTR?menu=usuarios&accion=Listar").forward(request, response);
                    break;

                case "modificar":
                    idusuario = Integer.parseInt(request.getParameter("id_usuario"));
                    UsuarioDTO usuario = usuariodao.consultarSegunId(idusuario);
                    request.setAttribute("usuario", usuario);
                    request.setAttribute("usuario1", usuario);
                    request.getRequestDispatcher("principalCTR?menu=usuarios&accion=Listar").forward(request, response);

                    break;
                case "Actualizar":
                    String nombreusu = request.getParameter("nombre");
                    String claveusu = request.getParameter("clave");
                    int rolusu = Integer.parseInt(request.getParameter("rol"));
                    int estadousu = Integer.parseInt(request.getParameter("estado"));
                    usuariodto.setUsu_estado(estadousu);
                    usuariodto.setUsu_rol(rolusu);
                    usuariodto.setUsu_contraseña(claveusu);
                    usuariodto.setUsu_nombre(nombreusu);
                    usuariodto.setUsu_id_usuario(idusuario);
                    usuariodao.modificar(usuariodto);
                    request.getRequestDispatcher("principalCTR?menu=usuarios&accion=Listar").forward(request, response);

                    break;
                case "eliminar":
                    idusuario = Integer.parseInt(request.getParameter("id_usuario"));
                    usuariodao.eliminar(idusuario);
                    request.getRequestDispatcher("principalCTR?menu=usuarios&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError("");
            }
            request.getRequestDispatcher("/admin/Usuario.jsp").forward(request, response);

        }

        if (menu.equals("vacunaciones")) {
            switch (accion) {
                case "Listar":
                    List lista = vacunaciondao.consultarTodos();
                    request.setAttribute("vacunaciones", lista);
                    request.setAttribute("nom_id_vac", nomvacdao.consultarTodos());
                    request.setAttribute("usu_id_vacu", usuariodao.consultarTodos());
                    break;
                case "Consultar":
                    String ganavacu = String.format(request.getParameter("ganavacu"));
                    vacunaciondto = vacunaciondao.Consultar(ganavacu);
                    request.setAttribute("ganado2", vacunaciondto);
                    request.getRequestDispatcher("principalCTR?menu=vacunaciones&accion=Listar").forward(request, response);
                    break;

                case "Guardar":
                    int ganavacun = Integer.parseInt(request.getParameter("idganavacu"));
                    Date fechvacu = Util.getDateSQL(request.getParameter("fechvacu"));
                    int tipovacu = Integer.parseInt(request.getParameter("tipovacu"));
                    String notavacu = request.getParameter("notavacu");
                    int id_nom_vacu = Integer.parseInt(request.getParameter("id_nom_vac"));
                  //  int id_usu_vacu = Integer.parseInt(request.getParameter("id_usu_vacu"));

                    vacunaciondto.setVacu_ganado(new GanadoDTO(ganavacun));
                    vacunaciondto.setVacu_fecha(fechvacu);
                    vacunaciondto.setVacu_tipo(tipovacu);
                    vacunaciondto.setVacu_nota(notavacu);
                    vacunaciondto.setVacu_nombre(new NombreVacunacionDTO(id_nom_vacu));
                 //   vacunaciondto.setVacu_usuario(new UsuarioDTO(id_usu_vacu));
                    vacunaciondao.agregar(vacunaciondto);
                    request.getRequestDispatcher("principalCTR?menu=vacunaciones&accion=Listar").forward(request, response);
                    break;

                case "modificar":
                    idvacunacion = Integer.parseInt(request.getParameter("id_vacunacion"));
                    VacunacionDTO vacunacion = vacunaciondao.consultarSegunId(idvacunacion);
                    request.setAttribute("vacunacion", vacunacion);
                    request.setAttribute("ganado2", vacunacion);
                    request.getRequestDispatcher("principalCTR?menu=vacunaciones&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    Date fechavacu = Util.getDateSQL(request.getParameter("fechvacu"));
                    int tipovacu1 = Integer.parseInt(request.getParameter("tipovacu"));
                    String notavacu1 = request.getParameter("notavacu");
                    int id_nom_vacu1 = Integer.parseInt(request.getParameter("id_nom_vac"));
              //      int id_usu_vacu1 = Integer.parseInt(request.getParameter("id_usu_vacu"));

                    vacunaciondto.setVacu_fecha(fechavacu);
                    vacunaciondto.setVacu_tipo(tipovacu1);
                    vacunaciondto.setVacu_nota(notavacu1);
                    vacunaciondto.setVacu_nombre(new NombreVacunacionDTO(id_nom_vacu1));
             //       vacunaciondto.setVacu_usuario(new UsuarioDTO(id_usu_vacu1));
                    vacunaciondto.setVacu_id_vacunacion(idvacunacion);
                    vacunaciondao.modificar(vacunaciondto);
                    request.getRequestDispatcher("principalCTR?menu=vacunaciones&accion=Listar").forward(request, response);
                    break;
                case "eliminar":
                    idvacunacion = Integer.parseInt(request.getParameter("id_vacunacion"));
                    vacunaciondao.eliminar(idvacunacion);
                    request.getRequestDispatcher("principalCTR?menu=vacunaciones&accion=Listar").forward(request, response);
                    break;
                default:

                    throw new AssertionError();
            }
            request.getRequestDispatcher("Vacunacion.jsp").forward(request, response);
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
