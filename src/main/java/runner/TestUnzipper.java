package runner;

import formatchecker.FormatChecker;

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

import static commentchecker.CheckComment.readActivity;
import static commentchecker.CheckComment.readAnswers;
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
            //Comment check files
            String regex="^#.+"; //to get string that begin with #
            ArrayList<String> activityStr=readActivity(filePath,regex);
            int countActivity=0; //to count on Activity number
            for (String s : activityStr) {
                if (s.toLowerCase().startsWith("#activity") || s.startsWith("# activity")) {
                    countActivity += 1;
                }
            }
            System.out.println("File " + fileCount + " had " + countActivity + " activities.");

            //Then check if they have answer questions using the comments
            ArrayList<String> quesStr=readAnswers(filePath);
            for(int i=0;i<quesStr.size();i++){
                System.out.println(quesStr.get(i));
            }

            //Format check files
            List<FormatChecker.SyntaxSpec> specs = Arrays.asList(
                    new FormatChecker.SyntaxSpec("def\\s+[a-zA-Z_-]*[A-Z-]+[a-zA-Z_-]*", "Improper function name"),
                    new FormatChecker.SyntaxSpec("(?:==|!=)\\s*(?:True|False)", "Redundant comparison"), // (ex. == True)
                    new FormatChecker.SyntaxSpec("if\\s+\\w+\\s*=\\s*\\w+", "Assignment within conditional") // if a = b
            );
            List<String> lines;
            try {
                lines = Files.readAllLines(Paths.get(filePath));
                List<Integer> matches = new ArrayList<>();
                for (int i = 0; i < lines.size(); i++) {
                    for (FormatChecker.SyntaxSpec spec : specs) {
                        if (lines.get(i).matches(spec.regex)) {
                            System.out.println("Line " + i + ": " + spec.name);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
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
