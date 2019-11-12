package runner;

import configsettings.ConfigSettings;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static checkers.Checker.*;
import static ziptools.FolderUnzipper.*;

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
        String folderPath = "";
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            folderPath = unzipFolder(chooser.getSelectedFile().getAbsolutePath());
            System.out.println("Uncompressed folder path: " + folderPath);
//            break;
        }
        String[] fileNames = retrieveFileNames(folderPath);
        System.out.println("Files in folder: ");
        for(int i=0; i<fileNames.length; i++){
            System.out.println("File " + i + ": " + fileNames[i]);
        }

        String[] filePaths = retrieveRelativeFilePaths(folderPath);

        int fileCount = 0;
        for(String filePath : filePaths){
            //CHECK_FORMAT=TRUE
            //CHECK_ACTIVITY=TRUE
            //CHECK_COMMENT_ANSWERS=TRUE
            ConfigSettings test = new ConfigSettings("settings.cfg");
            //String infile="C:\\Users\\USER\\IdeaProjects\\autograder\\python_labs\\chual2242_lab04.py";
            String outfile="chual2242_lab04_check.txt";
            ArrayList<String> strs=new ArrayList<String>();

            if(test.getSetting("CHECK_FORMAT").compareTo("TRUE")==0){
                System.out.println("format");
                strs=formatchecker(filePath);
                System.out.println(strs.size());
                writeToFile(strs,outfile);

            }
            if(test.getSetting("CHECK_ACTIVITY").compareTo("TRUE")==0){
                System.out.println("activity");
                int expected=3; //number of expected activities
                boolean ac= activitychecker(filePath,expected) ;
                if(ac){
                    System.out.println("Number of activity is same as expected.");
                }else{
                    System.out.println("Number of activity is not same as expected.");
                }

            }
            if(test.getSetting("CHECK_COMMENT_ANSWERS").compareTo("TRUE")==0){
                System.out.println("answers");
                strs=answerchecker(filePath);
                writeToFile(strs,outfile);
            }

            fileCount++;
        }
        System.exit(0);
//        }
//        System.out.println("Enter the path of the zip folder: ");
//        String path = console.nextLine();
//        unzipFolder(path);

    }
}
