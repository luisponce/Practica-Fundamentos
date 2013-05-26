
package GUI.ExtrasPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Clase que contiene la vista del reloj interno del sistema.
 * 
 * @author Luis M Ponce de leon
 */
public class MainTimerPanel extends JPanel{

    JLabel lblTitle;
    JTextField txtHora;
    
    public MainTimerPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 40));
        
        lblTitle = new JLabel("Hora Actual", (int) CENTER_ALIGNMENT);
        txtHora = new JTextField("00:00");
        txtHora.setEditable(false);
        txtHora.setBackground(Color.WHITE);
        txtHora.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        
        add(lblTitle, BorderLayout.NORTH);
        add(txtHora, BorderLayout.SOUTH);
    }
    
    /**
     * Cambia la informacion que se muestra en el reloj por el parametro suministrado.
     * 
     * @param horaEn24 La hora a mostrar en String.
     */
    public void setTime(final String horaEn24){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                  txtHora.setText(horaEn24);
            }
        });
    }

}
