
package GUI.Ingreso;

import GUI.InfoReciboPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 * Clase para mostrar la informacion del ingreso por medio de la gui, ademas del
 * recibo que se imprime.
 * 
 * @author Luis M Ponce de leon
 */
public class ShowReciboFrame extends JFrame{

    InfoReciboPanel infoRecibo;
    
    public ShowReciboFrame(String placa, String id, String horaParq, String tipoVehiculo) {
        setSize(new Dimension(270, 300));
        setResizable(false);
        setTitle("Recibo");
        setLayout(new FlowLayout((int) CENTER_ALIGNMENT, 30, 20));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        infoRecibo = new InfoReciboPanel(placa, id, horaParq, tipoVehiculo);
        add(infoRecibo);
    }

    
}
