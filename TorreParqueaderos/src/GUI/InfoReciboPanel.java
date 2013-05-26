
package GUI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel de la GUI que permite mostrar la informacion de un recibo ordenada como
 * una tabla. La tabla se construlle de manera que se puede reutilizar de ser necesario.
 * 
 * @author Luis M Ponce de leon
 */
public class InfoReciboPanel extends JPanel{

    JLabel lblReciboTitle;
    JLabel lblNumRecibo;
    
    JLabel lblPlacaTitle;
    JLabel lblPlaca;
    
    JLabel lblIdTitle;
    JLabel lblIdCliente;
    
    JLabel lblHoraTitle;
    JLabel lblHoraParqueo;
    
    JLabel lblTipoTitle;
    JLabel lblTipoVehiculo;
    
    JLabel lblMontoTitle;
    JLabel lblMontoPagar;
    
    /**
     * Constructor del panel de infomracion del recibo usado cuando se
     * planea retirar el vehiculo, mostrando el cobro del cliente.
     * 
     * @param numRecibo Numero del recibo a mostrar.
     * @param placa Placa del vehiculo relacionado con el recibo.
     * @param id ID del cliente dueño del vehiculo.
     * @param horaParq Hora de parqueo del vehiculo.
     * @param tipoVehiculo El tipo del vehiculo.
     * @param cobro El monto a pagar por el cliente.
     */
    public InfoReciboPanel(String numRecibo, String placa, String id, String horaParq, String tipoVehiculo, String cobro){
        setLayout(new GridLayout(6, 2, 20, 10));
        
        lblReciboTitle = new JLabel("Numero del Recibo");
        add(lblReciboTitle);
        
        lblNumRecibo = new JLabel("#" + numRecibo);
        add(lblNumRecibo);
        
        lblPlacaTitle = new JLabel("Placa del Vehiculo");
        add(lblPlacaTitle);
        
        lblPlaca = new JLabel(placa);
        add(lblPlaca);
        
        lblIdTitle = new JLabel("ID Cliente");
        add(lblIdTitle);
        
        lblIdCliente = new JLabel(id);
        add(lblIdCliente);
        
        lblHoraTitle = new JLabel("Hora de Parqueo");
        add(lblHoraTitle);
        
        lblHoraParqueo = new JLabel(horaParq);
        add(lblHoraParqueo);
        
        lblTipoTitle = new JLabel("Tipo del Vehiculo");
        add(lblTipoTitle);
        
        lblTipoVehiculo = new JLabel(tipoVehiculo);
        add(lblTipoVehiculo);
        
        lblMontoTitle = new JLabel("Monto a Pagar");
        add(lblMontoTitle);
        
        lblMontoPagar = new JLabel("$" + cobro);
        add(lblMontoPagar);
    }

    /**
     * Constructor del panel del informacion del recibo, usado cuando se quiere mostrar
     * solo la informacion relacionada con el recibo, sin el cobro.
     * 
     * @param placa Placa del vehiculo relacionado con el recibo.
     * @param id ID del cliente dueño del vehiculo.
     * @param horaParq Hora en que se parqueo el vehiculo.
     * @param tipoVehiculo El tipo de vehiculo.
     */
    public InfoReciboPanel(String placa, String id, String horaParq, String tipoVehiculo){
        setLayout(new GridLayout(9, 2, 20, 10));
        
        lblReciboTitle = new JLabel("Numero del Recibo");
        add(lblReciboTitle);
        
        lblNumRecibo = new JLabel("#" + MainGui.getNumRecibo(placa));
        add(lblNumRecibo);
        
        lblPlacaTitle = new JLabel("Placa del Vehiculo");
        add(lblPlacaTitle);
        
        lblPlaca = new JLabel(placa);
        add(lblPlaca);
        
        JLabel lblUbicacionTitle = new JLabel("Ubicacion:");
        add(lblUbicacionTitle);
        add(new JLabel());
        
        JLabel lblPisoTitle = new JLabel("    piso");
        add(lblPisoTitle);
        
        JLabel lblPiso = new JLabel(MainGui.getPisoParqueo(placa));
        add(lblPiso);
        
        JLabel lblEntradaTitle = new JLabel("    Entrada");
        add(lblEntradaTitle);
        
        JLabel lblEntrada = new JLabel(MainGui.getCeldaParqueo(placa));
        add(lblEntrada);
        
        lblIdTitle = new JLabel("ID Cliente");
        add(lblIdTitle);
        
        lblIdCliente = new JLabel(id);
        add(lblIdCliente);
        
        lblHoraTitle = new JLabel("Hora de Parqueo");
        add(lblHoraTitle);
        
        lblHoraParqueo = new JLabel(horaParq);
        add(lblHoraParqueo);
        
        JLabel lblRetiroTitle = new JLabel("Hora estimada de retiro");
        add(lblRetiroTitle);
        
        JLabel lblRetiro = new JLabel(MainGui.getHoraRetiroVehiculo(placa));
        add(lblRetiro);
        
        lblTipoTitle = new JLabel("Tipo del Vehiculo");
        add(lblTipoTitle);
        
        lblTipoVehiculo = new JLabel(tipoVehiculo);
        add(lblTipoVehiculo);
    }
}
