
package GUI.ExtrasPanel;

import GUI.MainGui;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Clase que contiene los botones de ingreso y retiro de la vista principal.
 * 
 * @author Luis M Ponce de leon
 */
public class PanelBotones extends JPanel{

    JButton butIngreso;
    JButton butRetiro;
    
    public PanelBotones() {
        setLayout(new GridLayout(2, 1));
        setPreferredSize(new Dimension(120, 50));
        
        butIngreso = new JButton("Ingreso");
        butIngreso.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainGui.showIngresoFrame();
            }
        });
        add(butIngreso);
        
        butRetiro = new JButton("Retiro");
        butRetiro.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainGui.showLoginRetiroFrame();
            }
        });
        add(butRetiro);
    }

    

    
    
    

}
