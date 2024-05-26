package javamon.frontend.styles;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

public class Typography {
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
