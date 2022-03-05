
package DTO;


public class NombreVacunacionDTO {
    private int id_nom_vacu;
    private String nombre_vacu;
    
    public NombreVacunacionDTO (){
        
    }
    public NombreVacunacionDTO (Integer id_nom_vacu){
        this.id_nom_vacu=id_nom_vacu;
    }
    
    

    public int getId_nom_vacu() {
        return id_nom_vacu;
    }

    public void setId_nom_vacu(int id_nom_vacu) {
        this.id_nom_vacu = id_nom_vacu;
    }

    public String getNombre_vacu() {
        return nombre_vacu;
    }

    public void setNombre_vacu(String nombre_vacu) {
        this.nombre_vacu = nombre_vacu;
    }
    
    
    
}
