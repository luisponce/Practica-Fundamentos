
package GUI.StatusPanel;

import GUI.MainGui;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Clase para dibujar una matriz de disponibilidad del parqueadero teniendo en cuenta
 * si es la primera o la segunda matriz. Estas van de A-M, y de N-Z respectivamente.
 * 
 * @author Luis M Ponce de leon
 */
public class MatrizDispGrafica extends JPanel{

    JTextField[][] dispMatrix;
    
    /**
     * Constructor generico que crea la matriz de la derecha o la izquierda,
     * teniendo en cuenta el parametro suministrado.
     * 
     * @param isIzq True si es la matriz de la izquierda (A-M) o False si es la de la derecha (N-Z).
     */
    public MatrizDispGrafica(boolean isIzq) {
        dispMatrix = new JTextField[14][11];
        
        setLayout(new GridLayout(14, 11));
        
        dispMatrix[0][0] = new JTextField("");
        dispMatrix[0][0].setEditable(false);
        add(dispMatrix[0][0]);
        
        for (int i = 1; i < 11; i++) {
            dispMatrix[0][i] = new JTextField("" + (i-1));
            dispMatrix[0][i].setEditable(false);
            add(dispMatrix[0][i]);
        }
        
        if (isIzq){
            for (int i = 1; i < 14; i++) {
                char c = (char) (i-1 + 65);
                dispMatrix[i][0] = new JTextField("" +c);
                dispMatrix[i][0].setEditable(false);
                add(dispMatrix[i][0]);
                
                for (int j = 1; j < 11; j++) {
                    dispMatrix[i][j] = new JTextField("" + MainGui.getValMatrizDisp(i-1, j-1));
                    dispMatrix[i][j].setEditable(false);
                    add(dispMatrix[i][j]);
                }
            }
        } else {
            for (int i = 1; i < 14; i++) {
                char c = (char) (i-1 + 65 + 13);
                dispMatrix[i][0] = new JTextField("" + c);
                dispMatrix[i][0].setEditable(false);
                add(dispMatrix[i][0]);
                
                for (int j = 1; j < 11; j++) {
                    dispMatrix[i][j] = new JTextField("" + MainGui.getValMatrizDisp(i-1 +13, j-1));
                    dispMatrix[i][j].setEditable(false);
                    add(dispMatrix[i][j]);
                }
            }
        }
        
        for (int i = 0; i < dispMatrix.length; i++) {
            for (int j = 0; j < dispMatrix[i].length; j++) {
                dispMatrix[i][j].setHorizontalAlignment((int) CENTER_ALIGNMENT);
            }
        }
    }

    /**
     * Metodo para cambiar la informacion que se muestra en la celda de la matriz especificada.
     * 
     * @param fila Fila de la celda a modificar.
     * @param columna Columna de la celda a modificar.
     * @param val Valor a "escribir" en la celda.
     */
    public void setDispMatrix(final int fila, final int columna, final int val) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                  dispMatrix[fila][columna+1].setText("" + val);
            }
        });
        
        
    }
    
    

    
}
