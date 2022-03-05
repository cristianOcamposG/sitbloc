
package DTO;


public class LoteDTO {
    private Integer lote_id_lote;
    private String lote_descripcion;
    
    public  LoteDTO () {
        
    }

    public LoteDTO(Integer id_lote) {
        this.lote_id_lote = id_lote;
    }

    public Integer getLote_id_lote() {
        return lote_id_lote;
    }

    public void setLote_id_lote(Integer lote_id_lote) {
        this.lote_id_lote = lote_id_lote;
    }

    public String getLote_descripcion() {
        return lote_descripcion;
    }

    public void setLote_descripcion(String lote_descripcion) {
        this.lote_descripcion = lote_descripcion;
    }
    
}
