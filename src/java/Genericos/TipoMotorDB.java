
package Genericos;

public enum TipoMotorDB {
    POSTGRESQL ("postgresql", "org.postgresql.Driver","sitbloc","postgres","criss1998a","5432","localhost");
    

    private final String motorBD;
    private final String controlador;
    private final String nombreBD;
    private final String usuarioBD;
    private final String claveBD;
    private final String puertoBD;
    private final String host;

    private TipoMotorDB(String motorBD, String controlador, String nombreBD, String usuarioBD, String claveBD, String puertoBD, String host) {
        this.motorBD = motorBD;
        this.controlador = controlador;
        this.nombreBD = nombreBD;
        this.usuarioBD = usuarioBD;
        this.claveBD = claveBD;
        this.puertoBD = puertoBD;
        this.host = host;
    }

    public String getMotorBD() {
        return motorBD;
    }

    public String getHost() {
        return host;
    }
    
    public String getControlador() {
        return controlador;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public String getUsuarioBD() {
        return usuarioBD;
    }

    public String getClaveBD() {
        return claveBD;
    }

    public String getPuertoBD() {
        return puertoBD;
    }
    
}
