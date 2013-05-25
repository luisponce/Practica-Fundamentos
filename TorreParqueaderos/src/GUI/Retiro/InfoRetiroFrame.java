
package GUI.Retiro;

import GUI.InfoReciboPanel;
import GUI.MainGui;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Clase que muestra la informacion del retiro con el valor a pagar. Permite
 * continuar con el retiro y pagar el recibo o cancelar el retiro en el caso de
 * que se desee asi.
 * 
 * @author Luis M Ponce de leon
 */
public class InfoRetiroFrame extends JFrame{

    InfoReciboPanel infoRecibo;
    JButton butCancelar;
    JButton butPagar;
    
    int numeroRecibo;
    
    public InfoRetiroFrame(String numRecibo, String placa, String id, String horaParq, String tipoVehiculo, String cobro) {
        numeroRecibo = Integer.parseInt(numRecibo);
        
        setSize(new Dimension(300, 250));
        setResizable(false);
        setTitle("Info Retiro");
        setLayout(new FlowLayout((int) CENTER_ALIGNMENT, 30, 20));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        infoRecibo = new InfoReciboPanel(numRecibo, placa, id, horaParq, tipoVehiculo, cobro);
        add(infoRecibo);
        
        butCancelar = new JButton("cancelar");
        butCancelar.setPreferredSize(new Dimension(100, 25));
        add(butCancelar);
        butCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        butPagar = new JButton("Pagar");
        butPagar.setPreferredSize(new Dimension(100, 25));
        add(butPagar);
        butPagar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                retirarVehiculo();
            }
        });
    }
    
    private void retirarVehiculo(){
        try {
            MainGui.retirarVehiculo(numeroRecibo);
            
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en retiro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
