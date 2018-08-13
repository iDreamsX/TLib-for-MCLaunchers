package fr.trxyy.launcher.fxutil;

import fr.trxyy.launcher.constants.TConstants;
import javafx.scene.text.Font;

public class FontLoader
{
    public void loadFont(final TConstants res, final String s) {
        Font.loadFont(this.getClass().getResourceAsStream(String.valueOf(TConstants.getResourceLocation()) + s), 14.0);
    }
    
    public void setFont(final String fontName, final float size) {
        Font.font(fontName, (double)size);
    }
    
    public static Font loadFont(final TConstants res, final String fullFont, final String fontName, final float size) {
        Font.loadFont(FontLoader.class.getResourceAsStream(String.valueOf(TConstants.getResourceLocation()) + fullFont), 14.0);
        final Font cFont = Font.font(fontName, (double)size);
        return cFont;
    }
}