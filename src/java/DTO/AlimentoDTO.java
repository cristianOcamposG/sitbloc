
package DTO;


public class AlimentoDTO {
    private Integer alimento_id_alimento;
    private String alim_descripcion;
    
    public AlimentoDTO () {
        
    }
  public AlimentoDTO (Integer id_alimento) {
        this.alimento_id_alimento=id_alimento;
    }
    public Integer getAlimento_id_alimento() {
        return alimento_id_alimento;
    }

    public void setAlimento_id_alimento(Integer alimento_id_alimento) {
        this.alimento_id_alimento = alimento_id_alimento;
    }

    public String getAlim_descripcion() {
        return alim_descripcion;
    }

    public void setAlim_descripcion(String alim_descripcion) {
        this.alim_descripcion = alim_descripcion;
    }
}
