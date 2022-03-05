
package DTO;


public class PersonaDTO {
    private Integer pers_id_persona;
    private String pers_Ci;
    private String pers_nombre;
    private String pers_apellido;
    private Integer pers_tel;
    

    public PersonaDTO() {
    }
     public PersonaDTO(Integer id) {
        this.pers_id_persona = id;
    }
    public Integer getPers_id_persona() {
        return pers_id_persona;
    }

    public void setPers_id_persona(Integer pers_id_persona) {
        this.pers_id_persona = pers_id_persona;
    }

    public String getPers_Ci() {
        return pers_Ci;
    }

    public void setPers_Ci(String pers_Ci) {
        this.pers_Ci = pers_Ci;
    }

   

    public String getPers_nombre() {
        return pers_nombre;
    }

    public void setPers_nombre(String pers_nombre) {
        this.pers_nombre = pers_nombre;
    }

    public String getPers_apellido() {
        return pers_apellido;
    }

    public void setPers_apellido(String pers_apellido) {
        this.pers_apellido = pers_apellido;
    }

    public Integer getPers_tel() {
        return pers_tel;
    }

    public void setPers_tel(Integer pers_tel) {
        this.pers_tel = pers_tel;
    }
    
    
}