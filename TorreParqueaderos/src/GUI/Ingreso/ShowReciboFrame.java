
package GUI.Ingreso;

import GUI.InfoReciboPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Luis M Ponce de leon
 */
public class ShowReciboFrame extends JFrame{

    InfoReciboPanel infoRecibo;
    JButton cancelar;
    JButton pagar;
    
    public ShowReciboFrame(String numRecibo, String placa, String id, String horaParq, String tipoVehiculo) {
        setSize(new Dimension(300, 300));
        setTitle("Recibo");
        setLayout(new FlowLayout((int) CENTER_ALIGNMENT, 30, 20));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        infoRecibo = new InfoReciboPanel(numRecibo, placa, id, horaParq, tipoVehiculo);
        add(infoRecibo);
    }

    
}
