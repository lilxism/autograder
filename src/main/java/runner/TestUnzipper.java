package runner;

import configsettings.ConfigSettings;
import javax.swing.*;
//import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static checkers.Checker.*;
import static compareoutput.compareOutput.compareOutput;
import static javax.swing.JFileChooser.FILES_AND_DIRECTORIES;
import static ziptools.FolderUnzipper.*;

public class TestUnzipper {
    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        File file = new File("./compare.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        ConfigSettings test = new ConfigSettings("settings.cfg");

        /*Create and set up file chooser for selecting zip file*/
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(FILES_AND_DIRECTORIES);
//        /*Only allow zip files to be chosen*/
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("Zip Files Only","zip");
//        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setAcceptAllFileFilterUsed(false);
        int returnVal = chooser.showOpenDialog(null);
        String folderPath = "";
        boolean unzipped = false;

        /*If a zip file is selected, call unzipFolder method to unzip contents into a new folder, else exit*/
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            folderPath = chooser.getSelectedFile().getAbsolutePath();
            if(folderPath.contains(".zip")) {
                folderPath = unzipFolder(folderPath);
                unzipped = true;
            }
            chooser.removeAll();

            if(unzipped) {
                System.out.println("Uncompressed folder path: " + folderPath);
            }
            else{
                System.out.println("Folder/file path: " + folderPath);
            }
        }
        else{
            chooser.removeAll();
            System.out.println("Goodbye!");
            System.exit(0);
        }

        String[] fileNames = retrieveFileNames(folderPath);
        System.out.println("Files/Directories: ");

        /*Create an array of the file paths for use in running checks on each file in the unzipped folder*/
        String[] filePaths = retrieveRelativeFilePaths(folderPath);

        /*Loop through file/directory names and print them out*/
        for(int i=0; i<fileNames.length; i++){
            File current = new File(filePaths[i]);
            if(current.isDirectory()){
                System.out.println("    " + (i+1) + ". Directory: " + fileNames[i]);
            }
            else {
                System.out.println("    " + (i+1) + ". File: " + fileNames[i]);
            }
        }

        int fileCount = 0;

        System.out.println();
        /*Loop through the file paths, checking each file*/
        for(String filePath : filePaths){

            if(!filePath.contains(".py")){
                fileCount++;
                continue;
            }

            System.out.println("Python file '" + fileNames[fileCount] + "':");

            String python = filePath;
            String str;

            if(test.getSetting("CHECK_INPUT").compareTo("TRUE")==0) {

                System.out.print("Enter answer file for script: ");
                String answers = console.next();
                String command = "python " + python + " < " + answers;
                System.out.println(command);
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while((str = in.readLine()) != null) {
                    System.out.println(str);
                    writer.write(str);
                }
                writer.close();
            } else {
                String command = "python " + python;
                System.out.println(command);
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while((str = in.readLine()) != null) {
                    System.out.println(str);
                    writer.write(str + "\n");
                }
                writer.close();
            }

            compareOutput("./compare.txt", "./answer_lab6.txt");

            String outfile= removeExtension(fileNames[fileCount]) + "_check.txt";
            ArrayList<String> strs=new ArrayList<String>();

            /*Checking format for file*/
            if(test.getSetting("CHECK_FORMAT").compareTo("TRUE")==0){
                System.out.println("    Checking format");
                strs=formatchecker(filePath);
                //System.out.println("    " + strs.size());
                writeToFile(strs,outfile);

            }

            /*Checking activity for file*/
            if(test.getSetting("CHECK_ACTIVITY").compareTo("TRUE")==0){
                System.out.println("    Checking activity");
                int expected=5; //number of expected activities
                boolean ac= activitychecker(filePath,expected) ;
                if(ac){
                    System.out.println("        Number of activity is same as expected.");
                }else{
                    System.out.println("        Number of activity is not same as expected.");
                }

            }

            /*Checking answers for file*/
            if(test.getSetting("CHECK_COMMENT_ANSWERS").compareTo("TRUE")==0){
                System.out.println("    Saving answers to file");
                strs=answerchecker(filePath);
                writeToFile(strs,outfile);
            }
            System.out.println();
            fileCount++;
        }
        System.exit(0);
    }
}
