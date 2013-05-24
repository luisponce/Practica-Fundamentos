
package GUI;

import GUI.Ingreso.IngresoFrame;
import GUI.Retiro.RetiroLoginFrame;
import GUI.ExtrasPanel.PanelExtras;
import GUI.Ingreso.ShowReciboFrame;
import GUI.Retiro.InfoRetiroFrame;
import GUI.StatusPanel.PanelStatus;
import Model.*;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sun.rmi.log.ReliableLog;

/**
 * Clase "Puente" o controlador que funciona como coneccion entre las vistas y los modelos.
 * Crea la ventana Principal y contiene el main del programa.
 * 
 * @author Luis M Ponce de leon
 */
public class MainGui extends JFrame{
    private static Parqueadero parq = new Parqueadero();
    
    private static HoraDelDia nextRetiro = null;
    
    private static PanelExtras panelExtras = new PanelExtras();;
    private static PanelStatus panelStatus = new PanelStatus();;
    
    public MainGui(){
        this.setTitle("Main GUI");
        this.setSize(1100, 550);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        
        this.add(panelExtras, BorderLayout.EAST);
        
        this.add(panelStatus, BorderLayout.WEST);
        
        
    }
    
    public static void main(String[] args) {
        //Inicia el reloj
        RelojInterno.getInstance().start();
        
        //debug
//        Vehiculo testV = new Vehiculo(TipoDeVehiculo.CAMPERO, "AAA000", RelojInterno.getInstance().getHoraActual(), new HoraDelDia(1, 20));
//        for (int i = 0; i < 24; i++) {
//            try {
//                parq.ingresarVehiculo(testV);
//            } catch (Exception ex) {
//                Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        updateInfoMatrix(0, 0);
//        //end debug
        
        //"pinta" las vistas
        new MainGui().setVisible(true);
        new DevTimeManager().setVisible(true);
    }
    
    /**
     * Metodo llamado al presionar el boton de ingreso. Musetra la ventana de ingreso de vehiculo.
     * 
     * @see IngresoFrame
     */
    public static void showIngresoFrame(){
        new IngresoFrame().setVisible(true);
    }
    
    /**
     * Metodo llamado al presionar el boton de retiro. Muestra la ventana de retiro de vehiculo.
     * 
     * @see RetiroLoginFrame
     */
    public static void showLoginRetiroFrame(){
        new RetiroLoginFrame().setVisible(true);
    }
    
    /**
     * Metodo llamado para ingresar un vehiculo en el modelo
     * 
     * @param placa placa del vehiculo a ingresar
     * @param tipo tipo del vehiculo
     * @param horaEstRet hora en que se estima retirar el vehiculo
     * @param horaIngreso hora en que ingresa el vehiculo (hora actual)
     * @param idCLiente id del cliente que hace el ingreso del vehiculo.
     */
    public static void ingresarVehiculo (String placa, TipoDeVehiculo tipo, HoraDelDia horaEstRet, HoraDelDia horaIngreso, int idCLiente){
        try {
            Vehiculo v = new Vehiculo(tipo, placa, horaIngreso, horaEstRet);
            if(parq.ingresarVehiculo(v)){
                LogEventos.getInstance().crearEvento(v, Cliente.ingresarCliente(idCLiente));
                if(v.getFilaParqueo()==-1){
                    updateInfoSotano("" + parq.getCeldasSotano());
                } else {
                    updateInfoMatrix(v.getFilaParqueo(), v.getColumnaParqueo());
                }
                setNextRetiro(parq.getNextRetiro().getHoraEn24());
            } else {
                System.err.println("Ingreso Fallido");
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        
        
    }
    
    
    
    /**
     * Metodo para obtener la informacion de un vehiculo por medio del modelo.
     * 
     * @param numRecibo numero del recibo relacionado con el vehiculo que se busca.
     * @throws Exception en caso de que no se encuentre un recibo con ese numero.
     */
    public static void getInfoVehiculo (int numRecibo) throws Exception{
        Recibo r = LogEventos.getInstance().buscarRecibo(numRecibo);
        
        new InfoRetiroFrame("" + numRecibo, r.getPlaca(), "" + r.getIdCliente(), r.getHoraEntrada(), r.getTipoVehiculo(), "" + r.generarCobro(RelojInterno.getInstance().getHoraActual())).setVisible(true);
        
    }
    
    /**
     * Metodo para retirar un vehiculo del modelo.
     * 
     * @param numRecibo el numero del recibo con el cual esta relacionado el vehiculo.
     * @throws Exception en el caso de que no se encuentre el recibo, o que el vehiculo ya haya sido retirado.
     */
    public static void retirarVehiculo(int numRecibo) throws Exception{
        
        Recibo r = LogEventos.getInstance().buscarRecibo(numRecibo);
        r.pagarRecibo();
        parq.retirarVehiculo(r.getVehiculo().getPlaca());
        
       if(r.getVehiculo().getFilaParqueo()==-1){
            updateInfoSotano("" + parq.getCeldasSotano());
        } else {
            updateInfoMatrix(r.getVehiculo().getFilaParqueo(), r.getVehiculo().getColumnaParqueo());
        }
       
       setNextRetiro(parq.getNextRetiro().getHoraEn24());
    }

    /**
     * Geter de la matriz de disponibilidad.
     * 
     * @param fila fila de la matriz que se desea acceder.
     * @param columna columna de la matriz que se desea acceder.
     * @return el valor de la matriz en esa posiscion. es decir el numero de vehiculos almacenados en dicha entrada.
     */
    public static int getValMatrizDisp(int fila, int columna){
        return parq.getCeldasEntrada(fila, columna);
    }
    
    /**
     * Seter del timer o reloj actual.
     * 
     * @param time hora a mostrar.
     */
    public static void setMainTimer(String time){
        panelExtras.getMainTimer().setTime(time);
    }

    /**
     * Geter de la hora del proximo retiro
     * 
     * @return La hora del proximo retiro.
     */
    public static HoraDelDia getNextRetiro() {
        return nextRetiro;
    }
    
    /**
     * Seter del reloj que muestra cuando se realizara el proximo retiro.
     * 
     * @param time Hora a mostrar.
     */
    public static void setNextRetiro(String time){
        panelExtras.getHoraNextRetiroPanel().setHoraProxRetiro(time);
    }
    
    /**
     * Metodo para cambiar el valor que se muestra en las matrizes de la vista principal.
     * 
     * @param fila fila de la entrada a refrescar.
     * @param columna Columna de la entrada a refrescar.
     */
    public static void updateInfoMatrix(int fila, int columna){
        if(fila < 13){
            panelStatus.getLeftPanel().getMatriz().setDispMatrix(fila+1, columna, getValMatrizDisp(fila, columna));
        } else {
            panelStatus.getRigthPanel().getMatriz().setDispMatrix(fila/2, columna, getValMatrizDisp(fila, columna));
        }
    }
    
    /**
     * Metodo para cambiar la informacion de disponibilidad del sotano en la vista principal.
     * 
     * @param celdas Numero de celdas pcupadas a mostrar en la vista.
     */
    public static void updateInfoSotano(String celdas){
        panelExtras.getStatusSotano().setCeldasSotano(celdas);
    }
    
    /**
     * Metodo para validar la informacion de un ingreso y continuar con el ingreso
     * si no se presenta ningun problema.
     * 
     * @param tipoV tipo del vehiculo ingresado.
     * @param placa Placa del vehiculo a ingresar.
     * @param idCliente Id del cliente que ingresa el vehiculo.
     * @param hora Hora(s) que se espera este parqueado el vehiculo.
     * @param min Minutos que se espera que este parqueado el vehiculo.
     * @throws Exception en el caso de que algun dato no cumpla con el formato esperado se genera una excepcion.
     */
    public static void chekInfo(String tipoV, String placa, String idCliente, int hora, int min) throws Exception{
        int horaEstRet = hora+RelojInterno.getInstance().getHoraActual().getHoras();
        int minEstRet = RelojInterno.getInstance().getHoraActual().getMinutos()+min;
        
        if(LogEventos.getInstance().buscarRecibo(placa)!=null) throw new Exception("Existe un vehiculo con la misma placa");
        
        if(placa.length()>6) throw new Exception("Formato placa invalido");
        
        if(minEstRet>60){
            horaEstRet++;
            minEstRet = minEstRet%60;
        }
        
        if(horaEstRet >= 24){
            throw new Exception("Hora invalida");
        }
        
        TipoDeVehiculo tipo = null;
        
        switch(tipoV){
            case "automovil": tipo= TipoDeVehiculo.AUTOMOVIL;
                break;
            case "camioneta": tipo = TipoDeVehiculo.CAMIONETA;
                break;
            case "campero": tipo = TipoDeVehiculo.CAMPERO;
                break;
        }
        
        int numIDCliente = Integer.parseInt(idCliente);
        
        ingresarVehiculo(placa, tipo, new HoraDelDia(horaEstRet, minEstRet), RelojInterno.getInstance().getHoraActual(), numIDCliente);
    }
    
    /**
     * metodo para adelantar el tiempo en el modelo.
     * 
     * @param h Horas a adelantar
     * @param m Minutos a adelantar
     */
    public static void adelantarTiempo(int h, int m){
        RelojInterno.getInstance().adelantarTiempo(h, m);
    }
    
    /**
     * Metodo para iniciar la limpieaza del parqueadero que ocurre a las 24:00 horas.
     * Esta limpieza retira todos los vehiculos y genera las deudas acumuludas a
     * los dueños de los vehiculos retirados.
     */
    public static void iniciarLimpieza(){
        parq.limpiezaParqueadero();
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 10; j++) {
                updateInfoMatrix(i, j);
            }
        }
        updateInfoSotano("0");
        setNextRetiro("");
    }
}
