package diet.client.rwr;

import javax.swing.Icon;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        container.setCursor((getTargetCard(mouseEvent) != null) ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        Card sourceCard = (Card) mouseEvent.getSource();
        Card targetCard = getTargetCard(mouseEvent);
        if (targetCard != null) {
            Icon targetIcon = targetCard.getIcon();
            targetCard.setIcon(sourceCard.getIcon());
            sourceCard.setIcon(targetIcon);

            dragAction.run();
        }
        container.setCursor(Cursor.getDefaultCursor());
    }

    private Card getTargetCard(MouseEvent mouseEvent) {
        Card sourceCard = (Card) mouseEvent.getSource();
        Component targetComponent = container.getComponentAt(convertPoint(sourceCard, mouseEvent.getPoint(), container));
        if (targetComponent == null) return null;
        if (targetComponent instanceof JPanel) {
            targetComponent = targetComponent.getComponentAt(convertPoint(sourceCard, mouseEvent.getPoint(), targetComponent));
        }
        if (targetComponent instanceof Card && !targetComponent.equals(sourceCard)) {
            return (Card) targetComponent;
        }
        return null;
    }
}
