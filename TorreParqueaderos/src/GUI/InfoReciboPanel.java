
package GUI;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
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

    public InfoReciboPanel(String numRecibo, String placa, String id, String horaParq, String tipoVehiculo){
        setLayout(new GridLayout(5, 2, 20, 10));
        
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
    }
}
