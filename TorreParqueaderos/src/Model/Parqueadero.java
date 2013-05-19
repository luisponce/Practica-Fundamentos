
package Model;

/** 
 * Clase encargada de administrar los parqueaderos de la torre y el sotano.
 * 
 * @author Luis M Ponce de leon
 */
public class Parqueadero {

    /**
     * Constructor del parqueadero que inicia la matriz con sus entradas.
     */
    public Parqueadero() {
        this.matrizDeDisponibilidad = new int[26][10];
        this.torre = new EntradaParqueadero[26][10];
        
        for (int i = 0; i < matrizDeDisponibilidad.length; i++) {
            for (int j = 0; j < matrizDeDisponibilidad[i].length; j++) {
                matrizDeDisponibilidad[i][j]=0;
            }
        }
        
        for (int i = 0; i < torre.length; i++) {
            for (int j = 0; j < torre[i].length; j++) {
                torre[i][j]= new EntradaParqueadero();
            }
        }
    }
    
    
    /** 
     * Matriz de las entradas. Posee 26 filas (A-Z) y 10 columnas (0-9).
     */
    private EntradaParqueadero[][] torre;

    /** 
     * Arreglo que contiene los vehiculos almacenados en el sotano. Puede
     * almacenar hasta 100 vehiculos.
     */
    private Vehiculo[] sotano = new Vehiculo[100];
    
    /**
     * La hora del proximo retiro del sotano.
     */
    private HoraDelDia proximoRetSotano = new HoraDelDia(0, 0);
    
    /**
     * La hora del proximo retiro de la torre.
     */
    private HoraDelDia proximoRetTorre = new HoraDelDia(0, 0);

    /** 
     * Numero de celdas ocupadas en el sotano, para un mejor control de este.
     */
    private int celdasOcupadasSotano=0;

    /** 
     * Matriz de enteros con el numero de celdas ocupadas en cada entrada.
     */
    private int[][] matrizDeDisponibilidad;

    /** Metodo para ingresar, si es posible, un vehiculo. Retorna true si fue
     * ingresado y false si no lo fue. El vehiculo es ingresado en su respectiva
     * celda si hay espacio, de lo contrario es ingresado al sotano si se puede.
     * 
     * @param vIn El vehiculo que se quiere ingresar.
     * @return True si el ingreso fue exitoso, false si no fue ingresado.
     * @throws Exception en el caso de que el vehiculo no pueda ser ingresado.
     */
    public boolean ingresarVehiculo(Vehiculo vIn) throws Exception {        
        String placa= vIn.getPlaca();
        int fila = (placa.toUpperCase().charAt(0) - 'A');
        int columna = placa.charAt(placa.length()-1) - 48;
        if (matrizDeDisponibilidad[fila][columna] < 24){
            vIn.setFilaParqueo(fila);
            vIn.setColumnaParqueo(columna);
            torre[fila][columna].agregarVehiculo(vIn);
            matrizDeDisponibilidad[fila][columna]++;
            return true;
        } else {
            if (celdasOcupadasSotano<100){
                for (int i=0; i<100; i++){
                    if (sotano[i]==null){
                        sotano[i]=vIn;
                        vIn.setFilaParqueo(-1);
                        vIn.setColumnaParqueo(i);
                        celdasOcupadasSotano++;
                        if(proximoRetSotano.comparar(vIn.getHoraEstRetiro()) == -1 || (proximoRetSotano.getHoras()==0 && proximoRetSotano.getMinutos()==0)){
                            proximoRetSotano=vIn.getHoraEstRetiro();
                        }
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /** Metodo para retirar un vehiculo apartir de su placa. Busca el vehiculo 
     * en su entrada determinada (deacuerdo a la placa). Si no se encuentra en 
     * la entrada se busca en el sotano.
     * 
     * @param placa Placa de vehiculo que se busca retirar.
     * @throws Exception En el caso de que no se encuentre el vehiculo especificado. 
     */
    public void retirarVehiculo(String placa) throws Exception {
        int fila = (placa.toUpperCase().charAt(0) - 'A');
        int columna = placa.charAt(placa.length()-1) - 48;
        if (torre[fila][columna].retirarVehiculo(placa)){
            matrizDeDisponibilidad[fila][columna]--;
            updateHoraProxRetiro();
        } else {
            for (int i=0; i<celdasOcupadasSotano; i++){
                if (sotano[i].getPlaca().equals(placa)){
                    sotano[i]=null;
                    celdasOcupadasSotano--;
                    updateProxRetiroSotano();
                }
            }
            throw new Exception("No se encontro ningun vehiculo de placas: " + placa);
        }
    }
    
    /**
     * Metodo para verificar/actualizar cual es la proxima hora de retiro de entre todas las entradas.
     */
    public void updateHoraProxRetiro(){
        proximoRetTorre= new HoraDelDia(0, 0);
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 10; j++) {
                if(matrizDeDisponibilidad[i][j]!=0){
                    if(proximoRetTorre.comparar(torre[i][j].getNextRetiro()) == -1 || (proximoRetTorre.getHoras()==0 && proximoRetTorre.getMinutos()==0)){
                        proximoRetTorre=torre[i][j].getNextRetiro();
                    }
                }
            }
        }
    }
    
    /**
     * Metodo para verificar/actualizar la hora proxima de retiro del sotano.
     */
    public void updateProxRetiroSotano(){
        proximoRetSotano= new HoraDelDia(0, 0);
        for (int i = 0; i < sotano.length; i++) {
            if(sotano[i]!=null){
                if(proximoRetSotano.comparar(sotano[i].getHoraEstRetiro())==-1 || (proximoRetSotano.getHoras()==0 && proximoRetSotano.getMinutos()==0)){
                    proximoRetSotano=sotano[i].getHoraEstRetiro();
                }
            }
        }
    }

    /** Geter de las celdas ocupadas del sotano
     * 
     * @return El numero de celdas ocupadas en el sotano.
     */
    public int getCeldasSotano() {
        return celdasOcupadasSotano;
    }
    
    

    /** Metodo llamado para sacar todos los vehiculos almacenados en el 
     * parqueadero. Este llama el metodo Limpieza de celdas en cada entrada
     * y retira todos los vehiculos en el sotano, generando los cobros
     * acumulados a los clientes.
     */
    public void limpiezaParqueadero() {
        for (int i=0; i<26; i++){
            for (int j=0; j<10; j++){
                torre[i][j].limpiezaCeldas();
                matrizDeDisponibilidad[i][j]=0;
            }
        }
        
        for (int i=0; i<celdasOcupadasSotano; i++){
            LogEventos.getInstance().buscarRecibo(sotano[i].getPlaca()).generarDeudaAcumulada();
            sotano[i]=null;
        }
        celdasOcupadasSotano=0;
    }
    
    /**
     * geter de la informacion en la matriz de disponibilidad. Retorna el numero
     * de vehiculos almacenados en la posicion especificada de la matriz.
     * 
     * @param fila La fila de la entrada que se desea obtener la informacion.
     * @param columna La columna de la entrada de la cual se desea obtener la informacion.
     * @return El numero entero de vehiculos almacenados en la entrada.
     */
    public int getCeldasEntrada(int fila, int columna){
        return matrizDeDisponibilidad[fila][columna];
    }
    
    /**
     * geter del proximo retiro de todo el parqueadero.
     * 
     * @return la hora del proximo retiro.
     */
    public HoraDelDia getNextRetiro(){
        updateHoraProxRetiro();
        updateProxRetiroSotano();
        //si el proximo retiro se encuentra en la torre
        if(proximoRetSotano.comparar(proximoRetTorre)==1){
            if(proximoRetTorre.getHoras()!=0 || proximoRetTorre.getMinutos()!=0){
                return proximoRetTorre;
            }
            
        //si esta en el sotano
        } else {
            if(proximoRetSotano.getHoras()!=0 || proximoRetSotano.getMinutos()!=0){
                return proximoRetSotano;
            }
        }
        
        return new HoraDelDia(0, 0);
    }
}
