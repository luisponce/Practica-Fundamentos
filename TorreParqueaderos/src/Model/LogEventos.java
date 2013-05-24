
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
    
    /**
     * constructor de log eventos que sigue el patron de diseño de un singleton,
     * impidiendo que se cree mas de una instancia de la clase.
     */
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
    public void crearEvento(Vehiculo vIn, Cliente cIn) {
        Recibo nuevo = new Recibo(vIn, cIn, this.curNumeroRecibo);
        this.curNumeroRecibo++;
        
        listaRecibos.add(nuevo);
    }

    /** 
     * Metodo para buscar un recibo por su numero de recibo.
     * 
     * @param numRecibo numero del recibo a buscar.
     * @return El recibo (si existe) o null si no se encuentra.
     * @throws Exception cuando el vehiculo no se encuentra o el recibo ya fue pagado. 
     */
    public Recibo buscarRecibo(int numRecibo) throws Exception {
        for (int i=0; i<listaRecibos.size(); i++){
            if(listaRecibos.get(i).getNumeroRecibo()==numRecibo) {
                if(listaRecibos.get(i).isActive() == false) throw new Exception("Recibo inactivo");
                return listaRecibos.get(i);
            }
        }
        throw new Exception("Vehiculo no encontrado");
    }
    
    /**
     * Metodo para buscar un recibo por la placa del vehiculo asociado al recibo.
     * 
     * @param placa Placa del vehiculo asociado al recibo que se busca.
     * @return El recibo (si existe) o null si no se encuentra.
     */
    public Recibo buscarRecibo(String placa){
        for (int i=0; i<listaRecibos.size(); i++){
            if(listaRecibos.get(i).getVehiculo().getPlaca().equals(placa) && listaRecibos.get(i).isActive()==true) {
                return listaRecibos.get(i);
            }
        }
        return null;
    }
}
