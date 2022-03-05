
package DTO;
import java.sql.Date;


public class ControlDTO {
    private Integer cont_id_control;
    private Date cont_fecha;
    private Integer cont_peso;
    private GanadoDTO cont_ganado;
    private AlimentoDTO cont_alimento;
    private UsuarioDTO cont_usuario;
    private String cont_observacion;
    
   public  ControlDTO (){ 
      
    }

   public  ControlDTO (Integer id_control){ 
      this.cont_id_control=id_control;
    }
   
  

    public Integer getCont_id_control() {
        return cont_id_control;
    }

    public void setCont_id_control(Integer cont_id_control) {
        this.cont_id_control = cont_id_control;
    }

    public Date getCont_fecha() {
        return cont_fecha;
    }

    public void setCont_fecha(Date cont_fecha) {
        this.cont_fecha = cont_fecha;
    }

    public Integer getCont_peso() {
        return cont_peso;
    }

    public void setCont_peso(Integer cont_peso) {
        this.cont_peso = cont_peso;
    }

    public GanadoDTO getCont_ganado() {
        return cont_ganado;
    }

    public void setCont_ganado(GanadoDTO cont_ganado) {
        this.cont_ganado = cont_ganado;
    }

    public AlimentoDTO getCont_alimento() {
        return cont_alimento;
    }

    public void setCont_alimento(AlimentoDTO cont_alimento) {
        this.cont_alimento = cont_alimento;
    }

    public UsuarioDTO getCont_usuario() {
        return cont_usuario;
    }

    public void setCont_usuario(UsuarioDTO cont_usuario) {
        this.cont_usuario = cont_usuario;
    }
  public String getCont_observacion() {
        return cont_observacion;
    }

    public void setCont_observacion(String cont_observacion) {
        this.cont_observacion = cont_observacion;
    }
}
 