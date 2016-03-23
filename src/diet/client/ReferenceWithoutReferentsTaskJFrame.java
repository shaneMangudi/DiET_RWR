package diet.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReferenceWithoutReferentsTaskJFrame extends JFrame {
    public ReferenceWithoutReferentsTaskJFrame() {
        setVisible(true);
    }
}

class DragMouseAdapter extends MouseAdapter {
    private JFrame frame;

    public DragMouseAdapter(JFrame frame) {
        this.frame = frame;
    }

    private static JLabel getTargetJLabel(MouseEvent mouseEvent) {
        JLabel jLabel = (JLabel) mouseEvent.getSource();
        Component targetComponent = jLabel.getParent().getComponentAt(mouseEvent.getLocationOnScreen());
        if (targetComponent instanceof JLabel && !targetComponent.equals(jLabel)) {
            return (JLabel) targetComponent;
        }
        return null;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (getTargetJLabel(mouseEvent) != null) {
            frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        } else {
            frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        JLabel jLabel = (JLabel) mouseEvent.getSource();
        JLabel targetJLabel = getTargetJLabel(mouseEvent);
        if (targetJLabel != null) {
            Icon targetIcon = targetJLabel.getIcon();
            targetJLabel.setIcon(jLabel.getIcon());
            jLabel.setIcon(targetIcon);
        }
        frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

}

class IconDnD {
    public static void main(String[] args) {
        JFrame f = new JFrame("Icon Drag & Drop");
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("rwr/director/1.png"));
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("rwr/director/2.png"));
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("rwr/director/3.png"));

        JLabel label1 = new JLabel(icon1, JLabel.CENTER);
        JLabel label2 = new JLabel(icon2, JLabel.CENTER);
        JLabel label3 = new JLabel(icon3, JLabel.CENTER);

        DragMouseAdapter listener = new DragMouseAdapter(f);
        label1.addMouseListener(listener);
        label1.addMouseMotionListener(listener);
        label2.addMouseListener(listener);
        label2.addMouseMotionListener(listener);
        label3.addMouseListener(listener);
        label3.addMouseMotionListener(listener);

        f.setLayout(new FlowLayout());
        f.add(label1);
        f.add(label2);
        f.add(label3);
        f.pack();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        f.setVisible(true);
    }
}