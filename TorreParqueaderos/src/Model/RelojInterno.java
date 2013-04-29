
package Model;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada del mantenimiento y avance del tiempo dentro del sistema.
 * <P>
 * Esta clase sigue el patron de diseño singleton, buscando que solo exista una
 * instancia de esta clase.
 * 
 * @author Luis M Ponce de leon
 */
public class RelojInterno extends Thread{
    /** Las hora que marca el reloj, sin minutos. Toma valoress enteros entre
     * 0 y 24.
     * 
     */
    private int horas = 0;
    
    /** Los minutos de la hora en que esta el reloj. Toma valores enteros entre 
     * 0 y 59.
     * 
     */
    private int minutos = 0;
    
    
    private RelojInterno() {
    }
    
    /**
     * geter de la unica instancia de la clase.
     * 
     * @return La unica instancia de la clase.
     */
    public static RelojInterno getInstance() {
        return RelojInternoHolder.INSTANCE;
    }
    
    private static class RelojInternoHolder {

        private static final RelojInterno INSTANCE = new RelojInterno();
    }
    
    @Override
    public void run() {
        while(true){
            try {
                sleep(60000);
                this.AdelantarTiempo(0, 1);
            } catch (InterruptedException ex) {
                Logger.getLogger(RelojInterno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /** Geter de la hora actual generada por el reloj interno.
     * 
     * @return La hora actual del sistema.
     */
    public HoraDelDia getHoraActual (){
        HoraDelDia horaActual = new HoraDelDia(this.horas, this.minutos);
        return horaActual;
    }

    /**
     * @param horas
     * @param minutos  
     */
    public void AdelantarTiempo(int horas, int minutos) {
        this.minutos+=minutos;
        this.horas+=horas;
        
        if (this.minutos>=60){
            this.horas+= this.minutos/60;
            this.minutos= this.minutos%60;
        }
        if (this.horas>=24){
            //iniciar limpieza!!
            this.horas= this.horas%24;
        }
        
        MainGui;
    }
}
