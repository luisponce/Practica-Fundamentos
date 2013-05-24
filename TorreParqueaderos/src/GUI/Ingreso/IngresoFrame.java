
package GUI.Ingreso;

import GUI.MainGui;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Luis M Ponce de leon
 */
public class IngresoFrame extends JFrame{

    JLabel lblTipoV;
    JLabel lblPlaca;
    JLabel lblIDCliente;
    JLabel lblTiempoEst;
    
    String[] tiposVehiculos = {"automovil","camioneta","campero"};
    JComboBox<String> comboTipoV;
    
    JPanel panelHoraMinutos;
    Integer[] horas = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
    JComboBox<Integer> comboHoras;
    Integer[] minutos = {00, 10, 20, 30, 40, 50};
    JComboBox<Integer> comboMin;
    
    JTextField txtPlaca;
    JTextField txtIDCLiente;
    
    JButton butCancelar;
    JButton butContinuar;
    
    public IngresoFrame() {
        setTitle("Ingreso");
        setSize(500, 300);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 50, 25));
        
        
        lblTipoV= new JLabel("Tipo de Vehiculo");
        add(lblTipoV);
        
        comboTipoV = new JComboBox<>(tiposVehiculos);
        add(comboTipoV);
        
        lblPlaca = new JLabel("Placa");
        add(lblPlaca);
        
        txtPlaca = new JTextField("");
        txtPlaca.setToolTipText("Ej: DFT345");
        add(txtPlaca);
        
        lblIDCliente = new JLabel("ID Cliente");
        add(lblIDCliente);
        
        txtIDCLiente = new JTextField("");
        txtIDCLiente.setToolTipText("Ej: 70559099");
        add(txtIDCLiente);
        
        lblTiempoEst = new JLabel("Tiempo estimado de parqueo");
        add(lblTiempoEst);
        
        panelHoraMinutos = new JPanel(new GridLayout(1, 4));
            comboHoras = new JComboBox<>(horas);
            comboHoras.setSelectedIndex(1);
            panelHoraMinutos.add(comboHoras);
            
            panelHoraMinutos.add(new JLabel("Hora(s) y "));
            
            comboMin = new JComboBox<>(minutos);
            panelHoraMinutos.add(comboMin);
            
            panelHoraMinutos.add(new JLabel("minutos"));
        add(panelHoraMinutos);
        
        butCancelar = new JButton("Cancelar");
        butCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        add(butCancelar);
        
        butContinuar = new JButton("Continuar");
        butContinuar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveInfo();
            }
        });
        add(butContinuar);
    }
    
    private void close(){
        this.dispose();
    }
    
    private void saveInfo(){
         String tipoV = comboTipoV.getItemAt(comboTipoV.getSelectedIndex());
         String placa = txtPlaca.getText().toUpperCase();
         String iDCliente = txtIDCLiente.getText();
         int horasSel = comboHoras.getItemAt(comboHoras.getSelectedIndex());
         int minSel = comboMin.getItemAt(comboMin.getSelectedIndex());
        try {
            MainGui.chekInfo(tipoV, placa, iDCliente, horasSel, minSel);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en Ingreso", JOptionPane.WARNING_MESSAGE);
        }
        
        close();
    }

}
