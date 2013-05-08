
package GUI;

import GUI.Ingreso.IngresoFrame;
import GUI.Retiro.RetiroLoginFrame;
import GUI.ExtrasPanel.PanelExtras;
import GUI.Ingreso.ShowReciboFrame;
import GUI.Retiro.InfoRetiroFrame;
import GUI.StatusPanel.PanelStatus;
import Model.*;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis M Ponce de leon
 */
public class MainGui extends JFrame{
    private static Parqueadero parq = new Parqueadero();
    
    private static HoraDelDia nextRetiro = new HoraDelDia(0, 0);
    
    private static PanelExtras panelExtras = new PanelExtras();;
    private static PanelStatus panelStatus = new PanelStatus();;
    
    //private static 
    
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
        RelojInterno.getInstance().start();
        
//        //debug
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
        
        
        new MainGui().setVisible(true);
    }
    
    public static void showIngresoFrame(){
        new IngresoFrame().setVisible(true);
    }
    
    public static void showLoginRetiroFrame(){
        new RetiroLoginFrame().setVisible(true);
    }
    
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
                if(getNextRetiro().comparar(new HoraDelDia(0, 0))==0 || v.getHoraEstRetiro().comparar(nextRetiro)==1){
                    nextRetiro=v.getHoraEstRetiro();
                    setNextRetiro(nextRetiro.getHoraEn24());
                }
            } else {
                System.err.println("Ingreso Fallido");
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        
        
    }
    
    
    
    public static void getInfoVehiculo (int numRecibo) throws Exception{
        Recibo r = LogEventos.getInstance().buscarRecibo(numRecibo);
        
        new InfoRetiroFrame("" + numRecibo, r.getPlaca(), "" + r.getIdCliente(), r.getHoraEntrada(), r.getTipoVehiculo(), "" + r.generarCobro(RelojInterno.getInstance().getHoraActual())).setVisible(true);
        
    }
    
    public static void retirarVehiculo(int numRecibo) throws Exception{
        
        Recibo r = LogEventos.getInstance().buscarRecibo(numRecibo);
        r.pagarRecibo();
        parq.retirarVehiculo(r.getVehiculo().getPlaca());
        
       if(r.getVehiculo().getFilaParqueo()==-1){
            updateInfoSotano("" + parq.getCeldasSotano());
        } else {
            updateInfoMatrix(r.getVehiculo().getFilaParqueo(), r.getVehiculo().getColumnaParqueo());
        }
    }

    public static int getValMatrizDisp(int fila, int columna){
        return parq.getCeldasEntrada(fila, columna);
    }
    
    public static void setMainTimer(String time){
        panelExtras.getMainTimer().setTime(time);
    }

    public static HoraDelDia getNextRetiro() {
        return nextRetiro;
    }
    
    public static void setNextRetiro(String time){
        panelExtras.getCountdownTimer().setHoraProxRetiro(time);
    }
    
    public static void updateInfoMatrix(int fila, int columna){
        if(fila < 13){
            panelStatus.getLeftPanel().getMatriz().setDispMatrix(fila+1, columna, getValMatrizDisp(fila, columna));
        } else {
            panelStatus.getRigthPanel().getMatriz().setDispMatrix(fila/2, columna, getValMatrizDisp(fila, columna));
        }
    }
    
    public static void updateInfoSotano(String celdas){
        panelExtras.getStatusSotano().setCeldasSotano(celdas);
    }
    
    public static void chekInfo(String tipoV, String placa, String idCliente, int hora, int min) throws Exception{
        int horaEstRet = hora+RelojInterno.getInstance().getHoraActual().getHoras();
        int minEstRet = RelojInterno.getInstance().getHoraActual().getMinutos()+min;
        
        if(LogEventos.getInstance().buscarRecibo(placa)!=null) throw new Exception("Existe un vehiculo con la misma placa");
        
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
}