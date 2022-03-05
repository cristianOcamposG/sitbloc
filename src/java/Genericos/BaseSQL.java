
package Genericos;


import java.util.List;


public interface BaseSQL <T> {
    public Boolean agregar(T dto); 
    public Boolean modificar(T dto); 
    public Boolean eliminar(T dto);
    public List<T> consultarTodos();
    public T consultarSegunId(Integer id);
    public T consultarSegunCadena(String cadena);
    public String getMsj (); 
}
