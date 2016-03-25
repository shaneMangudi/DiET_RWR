package diet.client.rwr;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import static java.lang.ClassLoader.getSystemResource;

class Card extends JLabel {
    Card(CardDragHandler cardDragHandler, String cardClass, int id) {
        super(getCardImage(cardClass, id), JLabel.CENTER);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.addMouseListener(cardDragHandler);
        this.addMouseMotionListener(cardDragHandler);
    }

    private static ImageIcon getCardImage(String cardClass, int id) {
        return new ImageIcon(getSystemResource("rwr/" + cardClass + "/" + id + ".png"));
    }
}
