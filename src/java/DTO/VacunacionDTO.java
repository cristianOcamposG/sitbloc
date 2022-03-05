
package DTO;

import java.sql.Date;


public class VacunacionDTO {
    private Integer vacu_id_vacunacion;
    private Date vacu_fecha;
    private Integer vacu_tipo;
    private String vacu_nota;
    private GanadoDTO vacu_ganado;
    private UsuarioDTO vacu_usuario;
    private NombreVacunacionDTO vacu_nombre;
    

    public VacunacionDTO () {
        
    }
    
     public VacunacionDTO (Integer id_vacu ) {
        this.vacu_id_vacunacion= id_vacu;
    }

    public Integer getVacu_id_vacunacion() {
        return vacu_id_vacunacion;
    }

    public void setVacu_id_vacunacion(Integer vacu_id_vacunacion) {
        this.vacu_id_vacunacion = vacu_id_vacunacion;
    }

  

    public Date getVacu_fecha() {
        return vacu_fecha;
    }

    public void setVacu_fecha(Date vacu_fecha) {
        this.vacu_fecha = vacu_fecha;
    }

    public Integer getVacu_tipo() {
        return vacu_tipo;
    }

    public void setVacu_tipo(Integer vacu_tipo) {
        this.vacu_tipo = vacu_tipo;
    }

    public String getVacu_nota() {
        return vacu_nota;
    }

    public void setVacu_nota(String vacu_nota) {
        this.vacu_nota = vacu_nota;
    }

    public GanadoDTO getVacu_ganado() {
        return vacu_ganado;
    }

    public void setVacu_ganado(GanadoDTO vacu_ganado) {
        this.vacu_ganado = vacu_ganado;
    }

    public UsuarioDTO getVacu_usuario() {
        return vacu_usuario;
    }

    public void setVacu_usuario(UsuarioDTO vacu_usuario) {
        this.vacu_usuario = vacu_usuario;
    }

    public NombreVacunacionDTO getVacu_nombre() {
        return vacu_nombre;
    }

    public void setVacu_nombre(NombreVacunacionDTO vacu_nombre) {
        this.vacu_nombre = vacu_nombre;
    }

   
    
}
