package DTO;

import java.sql.Date;

public class GanadoDTO {

    private Integer gana_id_ganado;
    private String gana_cod_ganado;
    private Date gana_fec_nacimiento;
    private Date gana_fec_ingreso;
    private String gana_padre_ganado;
    private String gana_madre_ganado;
    private Integer gana_peso_ganado;
    private String gana_sexo;
    private String gana_estado;
    private LoteDTO lote;
    private RazaDTO raza;
    private ControlDTO control;
    private String RazaString;
    private String LoteString;
    private Integer cont_peso;
     
    private UsuarioDTO usuario;

    public GanadoDTO() {
    }

    public GanadoDTO(Integer id_ganado) {
        this.gana_id_ganado = id_ganado;
    }

    public Integer getGana_id_ganado() {
        return gana_id_ganado;
    }

    public void setGana_id_ganado(Integer gana_id_ganado) {
        this.gana_id_ganado = gana_id_ganado;
    }

    public String getGana_cod_ganado() {
        return gana_cod_ganado;
    }

    public void setGana_cod_ganado(String gana_cod_ganado) {
        this.gana_cod_ganado = gana_cod_ganado;
    }

    public Date getGana_fec_nacimiento() {
        return gana_fec_nacimiento;
    }

    public void setGana_fec_nacimiento(Date gana_fec_nacimiento) {
        this.gana_fec_nacimiento = gana_fec_nacimiento;
    }

    public Date getGana_fec_ingreso() {
        return gana_fec_ingreso;
    }

    public void setGana_fec_ingreso(Date gana_fec_ingreso) {
        this.gana_fec_ingreso = gana_fec_ingreso;
    }

    public String getGana_padre_ganado() {
        return gana_padre_ganado;
    }

    public void setGana_padre_ganado(String gana_padre_ganado) {
        this.gana_padre_ganado = gana_padre_ganado;
    }

    public String getGana_madre_ganado() {
        return gana_madre_ganado;
    }

    public void setGana_madre_ganado(String gana_madre_ganado) {
        this.gana_madre_ganado = gana_madre_ganado;
    }

    public Integer getGana_peso_ganado() {
        return gana_peso_ganado;
    }

    public void setGana_peso_ganado(Integer gana_peso_ganado) {
        this.gana_peso_ganado = gana_peso_ganado;
    }

    public String getGana_sexo() {
        return gana_sexo;
    }

    public void setGana_sexo(String gana_sexo) {
        this.gana_sexo = gana_sexo;
    }

    public String getGana_estado() {
        return gana_estado;
    }

    public void setGana_estado(String gana_estado) {
        this.gana_estado = gana_estado;
    }

    public LoteDTO getLote() {
        return lote;
    }

    public void setLote(LoteDTO lote) {
        this.lote = lote;
    }

    public RazaDTO getRaza() {
        return raza;
    }

    public void setRaza(RazaDTO raza) {
        this.raza = raza;
    }

    public ControlDTO getControl() {
        return control;
    }

    public void setControl(ControlDTO control) {
        this.control = control;
    }
    
            
            
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public String getRazaString(){
    return this.raza != null ? this.raza.getRaza_descripcion() : "---";
    }
    
    public String getLoteString(){
    return this.lote != null ? this.lote.getLote_descripcion(): "---";
    }
    
    public Integer getCont_pes() {
        return this.control.getCont_peso();
    }
}
