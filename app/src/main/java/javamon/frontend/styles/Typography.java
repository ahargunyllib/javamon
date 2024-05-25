package javamon.frontend.styles;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

public class Typography {
    public static final Font TITLE = new Font("Arial", Font.BOLD, 24);
    public static final Font SUBTITLE = new Font("Arial", Font.BOLD, 18);
    public static final Font BODY = new Font("Arial", Font.PLAIN, 14);
    public static final Font BUTTON = new Font("Arial", Font.BOLD, 14);
    public static final Font LABEL = new Font("Arial", Font.BOLD, 14);
    public static final Font INPUT = new Font("Arial", Font.PLAIN, 14);
    public static final Font ERROR = new Font("Arial", Font.BOLD, 14);
    public static final Font LINK = new Font("Arial", Font.BOLD, 14);
    public static final Font SMALL = new Font("Arial", Font.PLAIN, 12);
    public static final Font TINY = new Font("Arial", Font.PLAIN, 10);

    public class Jua {
        private Font font;

        public Jua(float size) {
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/jua.ttf")).deriveFont(size);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Font getFont() {
            return font;
        }
    }
    
    public class Inter {
        private Font font;

        public Inter(String name,float size) {
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File(String.format("assets/fonts/inter/%s.ttf", name))).deriveFont(size);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Font getFont() {
            return font;
        }
    }
}
