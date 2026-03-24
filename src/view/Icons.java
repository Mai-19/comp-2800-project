package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * class with icons
 */
public class Icons {
    public static final ImageIcon PLACEHOLDER_ALBUM;
    public static final ImageIcon ADD_DIRECTORY;
    public static final ImageIcon BACK;
    public static final ImageIcon FORWARD;
    public static final ImageIcon MUTED_VOLUME;
    public static final ImageIcon NEXT;
    public static final ImageIcon PAUSE;
    public static final ImageIcon PLAY;
    public static final ImageIcon PREVIOUS;
    public static final ImageIcon REFRESH;
    public static final ImageIcon REPEAT;
    public static final ImageIcon REVERSE;
    public static final ImageIcon SEARCH;
    public static final ImageIcon SETTINGS_SLIDERS;
    public static final ImageIcon SHUFFLE;
    public static final ImageIcon TRASH;
    public static final ImageIcon VOLUME;

    public static final ImageIcon MUSIC_16;
    public static final ImageIcon MUSIC_25;
    public static final ImageIcon MUSIC_32;
    public static final ImageIcon MUSIC_64;
    public static final ImageIcon MUSIC_256;

    /**
     * Loads an image from the classpath and scales it to a given width and height.
     * 
     * @param path
     * @param width
     * @param height
     * @return
     */
    private static ImageIcon load(String path, int width, int height) {
        try {
            var url = Icons.class.getClassLoader().getResource(path);
            return new ImageIcon(ImageIO.read(url).getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Inverts the colors of an ImageIcon.
     * Preserves the alpha (transparency) channel.
     */
    public static ImageIcon colourIcons(ImageIcon icon, int r, int g, int b) {
        // Get the image as a BufferedImage with ARGB support
        BufferedImage original = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);

        // Draw the icon onto the BufferedImage
        Graphics2D g2d = original.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();

        // Invert each pixel's RGB, preserving alpha
        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                int argb = original.getRGB(x, y);
                int a = (argb >> 24) & 0xFF; // alpha unchanged
                original.setRGB(x, y, (a << 24) | (r << 16) | (g << 8) | b);
            }
        }

        return new ImageIcon(original);
    }
    /**
     * Inverts the colors of an ImageIcon.
     * Preserves the alpha (transparency) channel.
     */
    public static ImageIcon colourIcons(ImageIcon icon) {
        return colourIcons(icon, View.TEXT.getRed(), View.TEXT.getGreen(), View.TEXT.getBlue());
    }

    static {
        PLACEHOLDER_ALBUM = new ImageIcon(new BufferedImage(View.ALBUM_IMG_SIZE, View.ALBUM_IMG_SIZE, BufferedImage.TYPE_INT_ARGB));

        ADD_DIRECTORY = colourIcons(load("icons/add-directory.png", 25, 25));
        BACK = colourIcons(load("icons/back.png", 25, 25));
        FORWARD = colourIcons(load("icons/forward.png", 25, 25));
        MUTED_VOLUME = colourIcons(load("icons/muted-volume.png", 25, 25));
        NEXT = colourIcons(load("icons/next.png", 25, 25));
        PAUSE = colourIcons(load("icons/pause.png", 25, 25));
        PLAY = colourIcons(load("icons/play.png", 25, 25));
        PREVIOUS = colourIcons(load("icons/previous.png", 25, 25));
        REFRESH = colourIcons(load("icons/refresh.png", 25, 25));
        REPEAT = colourIcons(load("icons/repeat.png", 25, 25));
        REVERSE = colourIcons(load("icons/reverse.png", 25, 25));
        SEARCH = colourIcons(load("icons/search.png", 25, 25));
        SETTINGS_SLIDERS = colourIcons(load("icons/settings-sliders.png", 25, 25));
        SHUFFLE = colourIcons(load("icons/shuffle.png", 25, 25));
        TRASH = colourIcons(load("icons/trash.png", 25, 25));
        VOLUME = colourIcons(load("icons/volume.png", 25, 25));
        MUSIC_16 = colourIcons(load("icons/music-alt.png", 16, 16));
        MUSIC_25 = colourIcons(load("icons/music-alt.png", 25, 25));
        MUSIC_32 = colourIcons(load("icons/music-alt.png", 32, 32));
        MUSIC_64 = colourIcons(load("icons/music-alt.png", 64, 64));
        MUSIC_256 = colourIcons(load("icons/music-alt.png", 256, 256));
    }
}