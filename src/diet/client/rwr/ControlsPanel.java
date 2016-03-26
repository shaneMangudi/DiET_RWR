package diet.client.rwr;

import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import javax.swing.JPanel;

class ControlsPanel extends JPanel {
    private final ReadyStateToggle readyStateToggle;

    ControlsPanel(ItemListener readyStateListener) {
        super(new FlowLayout());

        readyStateToggle = new ReadyStateToggle(readyStateListener);
        this.add(readyStateToggle);
    }

    public void reset() {
        readyStateToggle.setSelected(false);
    }
}
