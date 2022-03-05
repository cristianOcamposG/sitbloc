
package DTO;


public class RazaDTO {
    private Integer raza_id_raza;
    private String raza_descripcion;
    
    public  RazaDTO(){
        
    }

    public RazaDTO(Integer id_raza) {
        this.raza_id_raza = id_raza;
    }

    public Integer getRaza_id_raza() {
        return raza_id_raza;
    }

    public void setRaza_id_raza(Integer raza_id_raza) {
        this.raza_id_raza = raza_id_raza;
    }
//fas fa-clipboard-list
    public String getRaza_descripcion() {
        return raza_descripcion;
    }

    public void setRaza_descripcion(String raza_descripcion) {
        this.raza_descripcion = raza_descripcion;
    }
    
    
}
