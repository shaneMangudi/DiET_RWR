package diet.client.rwr;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JLabel;
import static javax.swing.SwingUtilities.convertPoint;

class CardDragHandler extends MouseAdapter {
    private final Container container;
    private final Runnable dragAction;

    CardDragHandler(Container container, Runnable dragAction) {
        this.container = container;
        this.dragAction = dragAction;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        container.setCursor((getTargetJLabel(mouseEvent) != null) ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        JLabel jLabel = (JLabel) mouseEvent.getSource();
        JLabel targetJLabel = getTargetJLabel(mouseEvent);
        if (targetJLabel != null) {
            Icon targetIcon = targetJLabel.getIcon();
            targetJLabel.setIcon(jLabel.getIcon());
            jLabel.setIcon(targetIcon);

            dragAction.run();
        }
        container.setCursor(Cursor.getDefaultCursor());
    }

    private JLabel getTargetJLabel(MouseEvent mouseEvent) {
        JLabel jLabel = (JLabel) mouseEvent.getSource();
        Component targetComponent = container.getComponentAt(convertPoint(jLabel, mouseEvent.getPoint(), container));
        if (targetComponent instanceof JLabel && !targetComponent.equals(jLabel)) {
            return (JLabel) targetComponent;
        }
        return null;
    }
}
