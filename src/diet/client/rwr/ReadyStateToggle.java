package diet.client.rwr;

import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import static java.lang.ClassLoader.getSystemResource;

class ReadyStateToggle extends JToggleButton {
    private static final ImageIcon CROSS_ICON = new ImageIcon(getSystemResource("rwr/fa-times-circle_16_0_000000_none.png"));
    private static final ImageIcon CHECK_ICON = new ImageIcon(getSystemResource("rwr/fa-check-circle_16_0_000000_none.png"));

    ReadyStateToggle(ItemListener itemListener) {
        super("Ready");

        this.setIcon(CROSS_ICON);
        this.setPressedIcon(CROSS_ICON);
        this.setRolloverIcon(CROSS_ICON);
        this.setSelectedIcon(CHECK_ICON);

        this.addItemListener(itemListener);
    }
}
