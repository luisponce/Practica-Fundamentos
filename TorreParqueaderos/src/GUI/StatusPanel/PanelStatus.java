
package GUI.StatusPanel;

import GUI.StatusPanel.PanelStatusMatrix;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Clase que contiene la vista principal del estatus del parqueadero. Contiene
 * las dos matrices de disponibilidad de la torre.
 * 
 * @author Luis M Ponce de leon
 */
public class PanelStatus extends JPanel {
    
    private PanelStatusMatrix leftPanel;
    private PanelStatusMatrix rigthPanel;
    
    public PanelStatus() {
        setLayout(new FlowLayout((int) CENTER_ALIGNMENT, 50, 40));
        setPreferredSize(new Dimension(800, 0));
        
        
        
        TitledBorder border = BorderFactory.createTitledBorder("Status del parqueadero");
        this.setBorder(border);
        
        leftPanel = new PanelStatusMatrix(true);
        rigthPanel = new PanelStatusMatrix(false);
        add(leftPanel, BorderLayout.WEST);
        add(rigthPanel, BorderLayout.EAST);
    }

    public PanelStatusMatrix getLeftPanel() {
        return leftPanel;
    }

    public PanelStatusMatrix getRigthPanel() {
        return rigthPanel;
    }
    
    
}
