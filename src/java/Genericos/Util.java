package Genericos;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

public class Util {

     private static final SimpleDateFormat famd = new SimpleDateFormat("yyyy/MM/dd");
      private static final SimpleDateFormat famd2 = new SimpleDateFormat("yyyy-MM-dd");
    public static enum ACCION {
        agregar, modificar, eliminar, anular, recuperar, listar };

    public static ACCION getAccion(HttpServletRequest request) {
        ACCION accion;
        accion = (request.getParameter("esAgregar") != null ? ACCION.agregar
                : request.getParameter("esAgregar") != null ? ACCION.agregar
                : request.getParameter("esModficar") != null ? ACCION.modificar
                : request.getParameter("esEliminar") != null ? ACCION.eliminar
                : request.getParameter("esAnular") != null ? ACCION.anular
                : request.getParameter("esRecuperar") != null ? ACCION.recuperar
                : request.getParameter("esListar") != null ? ACCION.listar:null);
        return accion;
    }

    public static String getString(HttpServletRequest request, String name) {
        return (request.getParameter(name) == null ? "" : request.getParameter(name).trim());

    }

    public static Integer getInt(HttpServletRequest request, String name) {
        return ((request.getParameter(name) == null || request.getParameter(name).isEmpty()) ? 0 : Integer.parseInt(request.getParameter(name).trim()));
    }

    public static String getAvisoExitos() {
        return "Swal.fire({\n"
                + "  position: 'top-end',\n"
                + "  icon: 'success',\n"
                + "  title: 'Operacion Exitosa',\n"
                + "  showConfirmButton: false,\n"
                + "  timer: 1500\n"
                + "   })";
    }

    public static String getAvisoError() {
        return "Swal.fire({\n"
                + "  icon: 'error',\n"
                + "  title: 'Error...',\n"
                + "  text: 'Operacion fallida!',\n"
                + "  footer: '<a href=\"\">Why do I have this issue?</a>'\n"
                + "})";
    }

    public static String getAvisoMoficacion() {
        return "Swal.fire({\n"
                + "  title: 'Deseas realizar los cambios?',\n"
                + "  showDenyButton: true,\n"
                + "  showCancelButton: true,\n"
                + "  confirmButtonText: `Guardar`,\n"
                + "  denyButtonText: `No Guardar`,\n"
                + "}).then((result) => {\n"
                + "  /* Read more about isConfirmed, isDenied below */\n"
                + "  if (result.isConfirmed) {\n"
                + "    Swal.fire('Guadado!', '', 'success')\n"
                + "  } else if (result.isDenied) {\n"
                + "    Swal.fire('Cambios no realizado', '', 'info')\n"
                + "  }\n"
                + "})";

    }

    public static java.sql.Date getDateSQL(String fecha) {
        try {
            Date date = famd2.parse(fecha);
            java.sql.Date res = new java.sql.Date(date.getTime());
            return res;
        } catch (Exception ex) {
            return null;
        }
    }

}
