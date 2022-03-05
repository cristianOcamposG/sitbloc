package DTO;

import java.sql.Date;

public class GestacionDTO {

    private Integer gest_id_gestacion;
    private Date gest_fecha_gestacion;
    private Integer gest_tipo;
    private Integer gest_estado;
    private GanadoDTO gest_ganado;
    private Date gest_fecha_fina;

    public GestacionDTO() {

    }
public GestacionDTO (Integer id_gesta){

    this.gest_id_gestacion=id_gesta;
}
        
    public Date getGest_fecha_fina() {
        return gest_fecha_fina;
    }

    public void setGest_fecha_fina(Date gest_fecha_fina) {
        this.gest_fecha_fina = gest_fecha_fina;
    }

    public Integer getGest_id_gestacion() {
        return gest_id_gestacion;
    }

    public void setGest_id_gestacion(Integer gest_id_gestacion) {
        this.gest_id_gestacion = gest_id_gestacion;
    }

    public Date getGest_fecha_gestacion() {
        return gest_fecha_gestacion;
    }

    public void setGest_fecha_gestacion(Date gest_fecha_gestacion) {
        this.gest_fecha_gestacion = gest_fecha_gestacion;
    }

    public Integer getGest_estado() {
        return gest_estado;
    }

    public void setGest_estado(Integer gest_estado) {
        this.gest_estado = gest_estado;
    }

    public GanadoDTO getGest_ganado() {
        return gest_ganado;
    }

    public void setGest_ganado(GanadoDTO gest_ganado) {
        this.gest_ganado = gest_ganado;
    }

    public Integer getGest_tipo() {
        return gest_tipo;
    }

    public void setGest_tipo(Integer gest_tipo) {
        this.gest_tipo = gest_tipo;
    }

}
