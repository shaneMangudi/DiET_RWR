package diet.client.rwr;


import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import javax.swing.JPanel;

class ControlsPanel extends JPanel {
    ControlsPanel(ItemListener readyStateListener) {
        super(new FlowLayout());

        this.add(new ReadyStateToggle(readyStateListener));
    }
}
