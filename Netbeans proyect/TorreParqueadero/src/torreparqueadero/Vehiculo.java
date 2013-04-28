package torreparqueadero;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : TorreParqueadero
//  @ File Name : Vehiculo.java
//  @ Date : 04/2013
//  @ Author : Luis M Ponce de leon
//
//




/** Vehiculo almacenado en la torre de parqueaderos.
 * 
 * @author luismiguel
 */
public class Vehiculo {
    /**
     * tipo del vehiculo ingresado. 
     * Puede ser campero, automovil o camioneta.
     * 
     * @see TipoDeVehiculo
     */
    private TipoDeVehiculo tipo;

    /** 
     * La placa del carro ingresado como String.
     */
    private String placa;

    /** 
     * La hora del dia a la que ingresa el vehiculo.
     * 
     * @see HoraDelDia
     */
    private HoraDelDia horaIngreso;

    /** 
     * La hora del dia estimada del retiro.
     * 
     * @see HoraDelDia
     */
    private HoraDelDia horaEstRetiro;
    
    /**
     * Fila donde fue parqueado el vehiculo. -1 si fue en el garage.
     */
    private int filaParqueo;
    
    /**
     * Columna donde fue parqueado el vehiculo.
     */
    private int columnaParqueo;
    
    

    /** Constructor de vehiculo, que recibe todos los parametros relacionados con este.
     * 
     * @param tipo El tipo de vehiculo (automovil, camioneta o campero).
     * @param placa La placa del vehiculo, usada para identificarlo.
     * @param horaIngreso Hora en que entra el vehiculo al sistema.
     * @param horaEstRetiro Hora estimada en que se retirara el vehiculo.
     */
    public Vehiculo(TipoDeVehiculo tipo, String placa, HoraDelDia horaIngreso, HoraDelDia horaEstRetiro) {
        this.tipo = tipo;
        this.placa = placa;
        this.horaIngreso = horaIngreso;
        this.horaEstRetiro = horaEstRetiro;
    }

    /** Geter de la hora estimada de retiro.
     * 
     * @return la hora estimada de retiro como HoraDelDia.
     */
    public HoraDelDia GetHoraEstRetiro() {
        return horaEstRetiro;
    }

    /** Geter de la placa del vehiculo.
     * 
     * @return La placa como string.
     */
    public String GetPlaca() {
        return placa;
    }

    /** 
     * Geter del tipo de vehiculo.
     * 
     * @return El tipo de vehiculo
     */
    public TipoDeVehiculo GetTipo() {
        return tipo;
    }

    /** 
     * Geter de la hora de ingreso.
     * 
     * @return La hora de ingreso al parqueadero.
     */
    public HoraDelDia GetHoraIngreso() {
        return horaIngreso;
    }
    
    /**
     * Geter de la fila de Parqueo.
     * 
     * @return la fila en que esta parqueado el vehiculo. 
     */
    public int GetFilaParqueo() {
        return filaParqueo;
    }

    /**
     * Seter de la fila de parqueo.
     * 
     * @param filaParqueo fila en que esta parqueado el vehiculo. -1 si esta en el sotano.
     */
    public void SetFilaParqueo(int filaParqueo) {
        this.filaParqueo = filaParqueo;
    }

    /**
     * Geter de la columna en que esta parqueado el vehiculo.
     * 
     * @return la columna donde esta parqueado el vehiculo.
     */
    public int GetColumnaParqueo() {
        return columnaParqueo;
    }

    /**
     * Seter de la columna en que esta parqueado el vehiculo.
     * 
     * @param columnaParqueo Columna en que esta parqueado el vehiculo.
     */
    public void SetColumnaParqueo(int columnaParqueo) {
        this.columnaParqueo = columnaParqueo;
    }
    
    
}