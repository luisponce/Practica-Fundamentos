/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package torreparqueadero;

/** Enumerador para el tipo de vehiculo que ingresa al parqueadero
 *
 * @author luismiguel
 */
public enum TipoDeVehiculo {
    AUTOMOVIL (6000),
    CAMPERO (7000),
    CAMIONETA (6500);
    
    /** Costo del parqueadero por la hora. */
    private final int costoPorHora;

    TipoDeVehiculo(int costoPorHora) {
        this.costoPorHora = costoPorHora;
    }
    
    /** retorna el costo por hora del tipo de vehiculo.
     * 
     * @return el costo de la hora como entero.
     */
    public int GetCosto(){
        return costoPorHora;
    }
}
