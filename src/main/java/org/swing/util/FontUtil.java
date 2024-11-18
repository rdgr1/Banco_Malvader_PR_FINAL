package org.swing.util;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class FontUtil {

    public static Font loadFont(String path, float size) {
        try {
            InputStream fontStream = FontUtil.class.getResourceAsStream("/org/swing/assets/SFPRODISPLAYREGULAR.OTF");
            if (fontStream == null) {
                throw new RuntimeException("Fonte não encontrada no caminho: /org/swing/assets/SFPRODISPLAYREGULAR.OTF");
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(size);
            return customFont;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Aplicar fonte globalmente usando UIManager
    public static void applyGlobalFont(Font font) {
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("PasswordField.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("Menu.font", font);
    }
}
