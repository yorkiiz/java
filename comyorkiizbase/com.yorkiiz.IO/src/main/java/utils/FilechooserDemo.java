package utils;

import javafx.stage.FileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class FilechooserDemo {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(chooser);
        System.out.println(chooser.getSelectedFile().getName());

    }

}
