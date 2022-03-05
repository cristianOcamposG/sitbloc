
package DTO;


public class LoteAlimentacionDTO {
    private LoteDTO lote__alim;
    private AlimentoDTO lote_alimentacion;
    private String lote_alim_marca;
    
    public LoteAlimentacionDTO() {
    }

    public LoteDTO getLote__alim() {
        return lote__alim;
    }

    public void setLote__alim(LoteDTO lote__alim) {
        this.lote__alim = lote__alim;
    }

    public AlimentoDTO getLote_alimentacion() {
        return lote_alimentacion;
    }

    public void setLote_alimentacion(AlimentoDTO lote_alimentacion) {
        this.lote_alimentacion = lote_alimentacion;
    }

    public String getLote_alim_marca() {
        return lote_alim_marca;
    }

    public void setLote_alim_marca(String lote_alim_marca) {
        this.lote_alim_marca = lote_alim_marca;
    }
    
}
