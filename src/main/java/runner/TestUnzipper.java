package runner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Scanner;

import static ziptools.FolderUnzipper.unzipFolder;

public class TestUnzipper {
    private static Scanner console = new Scanner(System.in);
    public static void main(String[] args){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Zip Files Only","zip");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setAcceptAllFileFilterUsed(false);
//        while(true) {
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String folderPath = unzipFolder(chooser.getSelectedFile().getAbsolutePath());
//            break;
        }
        System.exit(0);
//        }
//        System.out.println("Enter the path of the zip folder: ");
//        String path = console.nextLine();
//        unzipFolder(path);

    }
}
