
package Model;

import java.util.ArrayList;

/** Clase encargada de mantener control de todos los eventos (ingresos, retiros,
 * etc..) los que son identificados por un recibo.
 * <p>
 * Esta clase sigue el patron de diseño singleton, buscando que solo exista una
 * instancia de esta clase.
 * 
 * @author luis M Ponce de leon
 */
public class LogEventos {
    /** 
     * Lista dinamica de recibos (eventos).
     */
    private ArrayList<Recibo> listaRecibos;

    /** 
     * El numero que se le debe asignar al siguiente recibo generado. Incrementa
     * en uno cada vez que se genera un recibo.
     */
    private int curNumeroRecibo;
    
    private LogEventos() {
        this.listaRecibos = new ArrayList<Recibo>();
        curNumeroRecibo=0;
    }
    
    /**
     * Geter de la instancia unica de la clase.
     * 
     * @return La unica instacia de la clase.
     */
    public static LogEventos getInstance() {
        return LogEventosHolder.INSTANCE;
    }
    
    private static class LogEventosHolder {

        private static final LogEventos INSTANCE = new LogEventos();
    }
    
    /** 
     * Metodo llamado al ingresar un vehiculo para registrar el evento
     * y generar el recibo.
     * 
     * @param vIn Vehiculo que es ingresado.
     * @param cIn Cliente dueño del vehiculo ingresado.
     */
    public void CrearEvento(Vehiculo vIn, Cliente cIn) {
        Recibo nuevo = new Recibo(vIn, cIn, this.curNumeroRecibo);
        this.curNumeroRecibo++;
        
        listaRecibos.add(nuevo);
    }

    /** 
     * Metodo para buscar un recibo por su numero de recibo.
     * 
     * @param numRecibo numero del recibo a buscar.
     * @return El recibo (si existe) o null si no se encuentra.
     */
    public Recibo BuscarRecibo(int numRecibo) {
        for (int i=0; i<listaRecibos.size(); i++){
            if(listaRecibos.get(i).getNumeroRecibo()==numRecibo) {
                return listaRecibos.get(i);
            }
        }
        return null;
    }
    
    /**
     * Metodo para buscar un recibo por la placa del vehiculo asociado al recibo.
     * 
     * @param placa Placa del vehiculo asociado al recibo que se busca.
     * @return El recibo (si existe) o null si no se encuentra.
     */
    public Recibo BuscarReciboPorPlaca(String placa){
        for (int i=0; i<listaRecibos.size(); i++){
            if(listaRecibos.get(i).getVehiculo().getPlaca().equals(placa)) {
                return listaRecibos.get(i);
            }
        }
        return null;
    }
}
