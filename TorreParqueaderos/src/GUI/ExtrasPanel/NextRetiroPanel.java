
package GUI.ExtrasPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Clase que contiene la vista de la informacion del proximo retiro del sistema.
 * 
 * @author Luis M Ponce de leon
 */
public class NextRetiroPanel extends JPanel{

    JLabel lblTitle;
    JTextField txtHora;

    public NextRetiroPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 40));
        
        lblTitle = new JLabel("Proximo Retiro", (int) CENTER_ALIGNMENT);
        txtHora = new JTextField(null);
        txtHora.setEditable(false);
        txtHora.setBackground(Color.WHITE);
        txtHora.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        
        add(lblTitle, BorderLayout.NORTH);
        add(txtHora, BorderLayout.SOUTH);
    }
    
    public void setHoraProxRetiro(final String hora){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                  txtHora.setText(hora);
            }
        });
    }
}
