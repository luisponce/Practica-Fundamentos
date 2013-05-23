
package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Luis M Ponce de leon
 */
public class DevTimeManager extends JFrame{

    private JTextField txtHoras = new JTextField();
    private JTextField txtMinutos = new JTextField();
    private JButton butAdelantar = new JButton("Adelantar tiempo");
    
    public DevTimeManager() {
        setTitle("Time Manager (Testing only)");
        setSize(new Dimension(200, 150));
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout((int) CENTER_ALIGNMENT, 15, 20));
        
        txtHoras.setPreferredSize(new Dimension(100, 50));
        txtHoras.setToolTipText("Horas a adelantar");
        
    }
    
    
    
}
