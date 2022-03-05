
package DTO;

import java.sql.Date;


public class EnfermedadDTO {
    private Integer enfe_id_enfermedades;
    private String enfe_descripcion;
    private Date enfe_fecha;
    private String enfe_notas;
    private GanadoDTO enfe_ganado;
    private NombreVacunacionDTO enfe_vacunacion;
    private UsuarioDTO enfe_usuario;

   
    public EnfermedadDTO(){
    
    }
    
     public EnfermedadDTO(Integer id_enfermedad){
         this.enfe_id_enfermedades= id_enfermedad;
    
    }
            
    public String getEnfe_notas() {
        return enfe_notas;
    }

    public void setEnfe_notas(String enfe_notas) {
        this.enfe_notas = enfe_notas;
    }
    
   

    public Integer getEnfe_id_enfermedades() {
        return enfe_id_enfermedades;
    }

    public void setEnfe_id_enfermedades(Integer enfe_id_enfermedades) {
        this.enfe_id_enfermedades = enfe_id_enfermedades;
    }

    public String getEnfe_descripcion() {
        return enfe_descripcion;
    }

    public void setEnfe_descripcion(String enfe_descripcion) {
        this.enfe_descripcion = enfe_descripcion;
    }

    public Date getEnfe_fecha() {
        return enfe_fecha;
    }

    public void setEnfe_fecha(Date enfe_fecha) {
        this.enfe_fecha = enfe_fecha;
    }

    public GanadoDTO getEnfe_ganado() {
        return enfe_ganado;
    }

    public void setEnfe_ganado(GanadoDTO enfe_ganado) {
        this.enfe_ganado = enfe_ganado;
    }

    public NombreVacunacionDTO getEnfe_vacunacion() {
        return enfe_vacunacion;
    }

    public void setEnfe_vacunacion(NombreVacunacionDTO enfe_vacunacion) {
        this.enfe_vacunacion = enfe_vacunacion;
    }

    public UsuarioDTO getEnfe_usuario() {
        return enfe_usuario;
    }

    public void setEnfe_usuario(UsuarioDTO enfe_usuario) {
        this.enfe_usuario = enfe_usuario;
    }


    
    
}
 