package image;

import javax.swing.*;
import java.awt.*;

public class ImageTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new ImageFrame();
                frame.setTitle("ImageTest");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * A frame with an image component
 */
class ImageFrame extends JFrame {
    public ImageFrame() {
        add(new ImageComponent());
        pack();
    }
}

/**
 * A component that displays a tiled image
 */
class ImageComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private Image img;

    public ImageComponent() {
        img = new ImageIcon(getClass().getResource("blue-ball.gif")).getImage();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (img == null) return;

        int imageWidth = img.getWidth(this);
        int imageHeight = img.getHeight(this);

        // draw the image in the upper-left corner
        g.drawImage(img,0,0,null);

        // tile the image across the component

        for (int i = 0; i*imageWidth <= getWidth(); i++) {
            for (int j = 0; j*imageHeight <= getHeight(); j++) {
                if (i + j > 0)
                    g.copyArea(0, 0, imageWidth, imageHeight, i*imageWidth, j * imageHeight);
            }
        }
    }
}
