
package Model;

/** Esta clase es la enargada de manejar las 24 celdas que posee, ordenando los 
 * vehiculos deacuerdo a su hora estimada de retiro.
 * 
 * @author Luis M Ponce de leon
 */
public class EntradaParqueadero {
    /** 
     * Arreglo con los vehiculos almacenados en la entrada.
     */
    private Vehiculo[] celda = new Vehiculo[24];

    /** 
     * Numero de vehiculos almacenados en la entrada. Usado para un control
     * mas facil de que celdas estan llenas.
     */
    private int celdasOcupadas = 0;

    /** Metodo para ingresar un vehiculo a la entrada. Si recibe mas
     * mas vehiculos de los que se pueden almacenar en la entrada, estos no
     * seran ingresados.
     * 
     * @param vIn Vehiculo que es ingresado.
     */
    public void AgregarVehiculo(Vehiculo vIn) {
        if (celdasOcupadas < celda.length) {
            for (int i=0; i<celda.length; i++){
                if (celda[i]==null){
                    celda[i]= vIn;
                    celdasOcupadas++;
                    return;
                } else {
                    if (celda[i].getHoraEstRetiro().Comparar(vIn.getHoraEstRetiro()) == -1){
                        for (int j=celda.length-1; j>i; j--){
                            celda[j]=celda[j-1];
                            celda[i]=vIn;
                            return;
                        }
                    }
                }
            }
        } else {
             System.out.println("El vehiculo no pudo ser ingresado");
        }
    }

    /** Metodo para retirar un vehiculo especifico. Se suministra la placa
     * del vehiculo y el sistema lo retira si se encuentra. Retorna true si el
     * vehiculo se retira exitosamente o false si no se encuentra.
     * 
     * @param placa La placa del vehiculo a retirar.
     * @return true si el vehiculo se encontro y fue retirado, false de otro modo.
     */
    public boolean RetirarVehiculo(String placa) {
        for (int i=0; i<celdasOcupadas; i++){
            if (celda[i].getPlaca().equals(placa)){
                int j;
                for (j=i+1; j<celdasOcupadas && celda[j]!=null; j++){
                    celda[j-1]=celda[j];
                }
                celda[j-1]=null;
                celdasOcupadas--;
                return true;
            }
        }
        return false;
    }

    /** Geter de las celdas ocupadas en la entrada.
     * 
     * @return El numero de celdas ocupadas.
     */
    public int getCeldasOcupadas() {
        return celdasOcupadas;
    }
    
    /** Metodo para retirar todos los vehiculos en la entrada y genera la deuda
     * acumulada al cliente propietario del vehiculo.
     */
    public void LimpiezaCeldas(){
        for (int i=0; i<celdasOcupadas; i++){
            LogEventos.getInstance().BuscarReciboPorPlaca(celda[i].getPlaca()).GenerarDeudaAcumulada();
            celda[i]=null;
        }
        celdasOcupadas=0;
    }
}

