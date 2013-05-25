
package GUI.Retiro;

import GUI.MainGui;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Clase que permite el ingreso del numero de recibo para comenzar el retiro. Esta
 * lleva a cabo los llamados a los metodos necesarios para el retiro.
 * 
 * @author Luis M Ponce de leon
 */
public class RetiroLoginFrame extends JFrame {

    JLabel lblTitulo;
    JTextField txtNumRecibo;
    JButton butCancelar;
    JButton butContinuar;
    
    public RetiroLoginFrame() {
        setTitle("Login Retiro");
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout((int) CENTER_ALIGNMENT, 30, 20));
        setSize(300, 200);
        
        lblTitulo = new JLabel("Ingrese Numero del recibo", (int) CENTER_ALIGNMENT);
        add(lblTitulo);
        lblTitulo.setPreferredSize(new Dimension(200, 25));
        
        txtNumRecibo = new JTextField();
        add(txtNumRecibo);
        txtNumRecibo.setPreferredSize(new Dimension(200, 25));
        
        butCancelar = new JButton("Cancelar");
        add(butCancelar);
        butCancelar.setPreferredSize(new Dimension(100, 25));
        butCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        butContinuar = new JButton("Continuar");
        add(butContinuar);
        butContinuar.setPreferredSize(new Dimension(100, 25));
        butContinuar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                continuar();
            }
        });
    }
    
    public void continuar() {
        try {
            MainGui.getInfoVehiculo(Integer.parseInt(txtNumRecibo.getText()));
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error en retiro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
