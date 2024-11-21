package org.swing.util;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class SwingStyleUtil {

    // Define se todas as fontes devem ser negrito
    private static final boolean USE_BOLD_FONT = true;

    public static void applyGlobalStyles() {
        // Configurar Look and Feel
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Carregar fonte personalizada
        Font customFont = FontUtil.loadFont("/assets/SFPRODISPLAYREGULAR.OTF", 15f);
        if (customFont != null) {
            // Aplicar estilo BOLD se configurado
            if (USE_BOLD_FONT) {
                customFont = customFont.deriveFont(Font.BOLD, customFont.getSize());
            }
            applyGlobalFont(customFont);
        }

        UIManager.put("Button.background", new Color(0, 0, 0, 0)); // Fundo transparente
        UIManager.put("Button.foreground", new Color(46, 202, 226)); // Cor do texto
        UIManager.put("Button.border", new RoundedBorder(20)); // Borda arredondada
        UIManager.put("Button.focusPainted", false); // Remove a pintura do foco
        UIManager.put("Button.contentAreaFilled", false); // Remove alteração visual ao pressionar
        UIManager.put("Button.opaque", true); // Mantém a opacidade
    }

    public static void applyGlobalFont(Font font) {
        // Aplicar fonte a todos os componentes suportados
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("PasswordField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
    }
}
