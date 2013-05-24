
package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Clase que permite, por medio de una pequeña interface grafica, adelantar el
 * tiempo del reloj interno.
 * 
 * @author Luis M Ponce de leon
 */
public class DevTimeManager extends JFrame{

    private JTextField txtHoras = new JTextField("0");
    private JTextField txtMinutos = new JTextField("0");
    private JButton butAdelantar = new JButton("Adelantar tiempo");
    
    public DevTimeManager() {
        setTitle("Time Manager (Testing only)");
        setSize(new Dimension(210, 130));
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout((int) CENTER_ALIGNMENT, 15, 20));
        
        txtHoras.setPreferredSize(new Dimension(80, 25));
        txtHoras.setToolTipText("Horas a adelantar");
        
        txtMinutos.setPreferredSize(new Dimension(80, 25));
        txtMinutos.setToolTipText("Minutos a adelantar");
        
        butAdelantar.setPreferredSize(new Dimension(180, 25));
        butAdelantar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int h=getHora();
                int m=getMinuto();
                MainGui.adelantarTiempo(h, m);
            }
        });
        
        add(txtHoras);
        add(txtMinutos);
        add(butAdelantar);
    }
    
    
    private int getHora(){
        return Integer.parseInt(txtHoras.getText());
    }
    
    private int getMinuto(){
        return Integer.parseInt(txtMinutos.getText());
    }
    
}
