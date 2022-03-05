
package DTO;


public class UsuarioDTO {
   private Integer usu_id_usuario;
   private String usu_nombre;
   private String usu_contraseña;
   private Integer usu_rol;
   private Integer usu_estado;
   private PersonaDTO persona;
   
   public UsuarioDTO (){
       
   }
    public UsuarioDTO(Integer id_usuario) {
        this.usu_id_usuario = id_usuario;
    }
    
    
    public Integer getUsu_id_usuario() {
        return usu_id_usuario;
    }

    public void setUsu_id_usuario(Integer usu_id_usuario) {
        this.usu_id_usuario = usu_id_usuario;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getUsu_contraseña() {
        return usu_contraseña;
    }

    public void setUsu_contraseña(String usu_contraseña) {
        this.usu_contraseña = usu_contraseña;
    }

    public Integer getUsu_rol() {
        return usu_rol;
    }

    public void setUsu_rol(Integer usu_rol) {
        this.usu_rol = usu_rol;
    }

    public Integer getUsu_estado() {
        return usu_estado;
    }

    public void setUsu_estado(Integer usu_estado) {
        this.usu_estado = usu_estado;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }
   
}
 

