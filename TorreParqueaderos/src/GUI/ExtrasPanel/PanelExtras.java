
package GUI.ExtrasPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Luis M Ponce de leon
 */
public class PanelExtras extends JPanel{
    
    MainTimerPanel mainTimer;
    NextRetiroPanel countdownTimer;
    StatusSotanoPanel statusSotano;
    PanelBotones botones;
    
    public PanelExtras() {
        setLayout(new FlowLayout((int) CENTER_ALIGNMENT,75,60));
        setPreferredSize(new Dimension(290, 500));
        //panels of 250x
        
        TitledBorder border = BorderFactory.createTitledBorder("Opciones");
        this.setBorder(border);
        
        mainTimer = new MainTimerPanel();
        add(mainTimer);
        
        countdownTimer = new NextRetiroPanel();
        add(countdownTimer);
        
        statusSotano = new StatusSotanoPanel();
        add(statusSotano);
        
        botones = new PanelBotones();
        add(botones);
    }

    public MainTimerPanel getMainTimer() {
        return mainTimer;
    }

    public NextRetiroPanel getCountdownTimer() {
        return countdownTimer;
    }

    public StatusSotanoPanel getStatusSotano() {
        return statusSotano;
    }
    
}