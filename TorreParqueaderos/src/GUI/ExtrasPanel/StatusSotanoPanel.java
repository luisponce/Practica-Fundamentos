
package GUI.ExtrasPanel;

import java.awt.BorderLayout;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Luis M Ponce de leon
 */
public class StatusSotanoPanel extends JPanel{

    JLabel lblTitle;
    JTextField celdasSotano;
    
    public StatusSotanoPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 40));
        
        lblTitle = new JLabel("Sotano", (int) CENTER_ALIGNMENT);
        celdasSotano = new JTextField("0");
        celdasSotano.setEditable(false);
        celdasSotano.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        
        add(lblTitle, BorderLayout.NORTH);
        add(celdasSotano, BorderLayout.SOUTH);
    }

    public void setCeldasSotano(final String celdas){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                  celdasSotano.setText(celdas);
            }
        });
    }
}