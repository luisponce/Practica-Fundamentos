
package Model;

/** Enumerador para el tipo de vehiculo que ingresa al parqueadero
 *
 * @author Luis M Ponce de leon
 */
public enum TipoDeVehiculo {
    /**
     * Tipo de vehiculo automovil, la tarifa de este es 6000 la hora.
     */
    AUTOMOVIL (6000),
    /**
     * Tipo de vehiculo campero, la tarifa de este es 7000 la hora.
     */
    CAMPERO (7000),
    /**
     * Tipo de vehiculo camioneta, la tarifa de este es 6500 la hora.
     */
    CAMIONETA (6500);

    /** Costo del parqueadero por la hora. */
    private final int costoPorHora;

    /**
     * Constructor del enum tipo de vehiculo.
     * 
     * @param costoPorHora El costo por hora del tipo de vehiculo.
     */
    TipoDeVehiculo(int costoPorHora) {
        this.costoPorHora = costoPorHora;
    }

    /** retorna el costo por hora del tipo de vehiculo.
     * 
     * @return el costo de la hora como entero.
     */
    public int getCosto(){
        return costoPorHora;
    }
}
