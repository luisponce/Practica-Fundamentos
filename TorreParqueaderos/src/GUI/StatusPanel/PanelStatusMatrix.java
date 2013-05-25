
package GUI.StatusPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase que organiza la matriz de disponibilidad con su titulo para un manejo mas facil de las vistas.
 * 
 * @author Luis M Ponce de leon
 */
public class PanelStatusMatrix extends JPanel{

    JLabel lblTitle;
    MatrizDispGrafica matriz;
    
    public PanelStatusMatrix(boolean isLeftPanel) {
        setLayout(new BorderLayout());
        
        setPreferredSize(new Dimension(300, 400));
        
        if (isLeftPanel){
            lblTitle = new JLabel("Seccion Superior", (int) CENTER_ALIGNMENT);
            
        } else {
            lblTitle = new JLabel("Seccion Inferior", (int) CENTER_ALIGNMENT);
            
        }
        
        add(lblTitle, BorderLayout.NORTH);
        
        matriz = new MatrizDispGrafica(isLeftPanel);
        add(matriz, BorderLayout.CENTER);
        
        
    }

    public MatrizDispGrafica getMatriz() {
        return matriz;
    }
    
    
    
    
}
