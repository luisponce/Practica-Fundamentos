 
package Model;

/** Representa la hora en horas y minutos como un reloj militar,
 * es decir, siguiendo el formato de 24 horas.
 * 
 * @author Luis M Ponce de leon
 */
public class HoraDelDia {
    /** La hora representada, ignorando los minutos. 
     * Toma valores enteros entre 0 y 23.
     */
    private int horas;

    /** Los minutos de la hora representada.
     * Toma valores enteros entre 0 y 59
     */
    private int minutos;

    /** Constructor que recibe la hora y minutos en formato 24horas.
     * 
     * @param h hora a representar, sin minutos.
     * @param min minutos de la hora a representar.
     */
    public HoraDelDia(int h, int min){
        horas = h;
        minutos = min;
    }

    /** Retorna la hora como un string con formato: hora:minuto.
     * 
     * @return La hora almacenada como un string de la forma: 12:03
     */
    public String getHoraEn24() {
        return "" + horas/10 + horas%10 + ":" + minutos/10 + minutos%10;
    }

    /** Compara la hora del dia almacenada con la que se da como parametro.
     * Retorna 1 si la recibida es mayor, 0 si es igual y -1 si es menor.
     * 
     * @param aComparar La hora recibida para comparar.
     * @return 1 si la recibida es mayor, 0 si es igual y -1 si es menor.
     */
    public int comparar(HoraDelDia aComparar) {
        if (aComparar.horas > this.horas){
            return 1;
        } else {
            if (aComparar.horas < this.horas){
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Geter de la hora representada por el objeto. Toma valores enteros
     * entre 0 y 23.
     * 
     * @return La hora como entero, sin tener en cuenta los minutos.
     */
    public int getHoras() {
        return horas;
    }

    
    /**
     * Geter de los minutos de la hora representada por el objeto. Toma valores
     * enteros entre 0 y 59.
     * 
     * @return Los minutos de la hora como enteros.
     */
    public int getMinutos() {
        return minutos;
    }
        
}
