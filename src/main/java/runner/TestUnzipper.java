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
    //public static Scanner console = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String STUDENT_OUTPUT =  "./WorkFile/compare.txt";
    public static final String COMPARED_RESULT = "./WorkFile/compared.txt";
    private static final String CHECK_SUFFIX = "_check.txt";

    public static void main(String[] args) throws IOException {
        //user should input settings.cfg
        String cfgFile="settings.cfg";
        ConfigSettings test = new ConfigSettings(cfgFile);

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

            //ignore file that is not python
            if(!filePath.contains(".py")){
                System.out.println(ANSI_RED + "File '" + fileNames[fileCount] + "' skipped. Not a python file.\n" + ANSI_RESET);
                fileCount++;
                continue;
            }

            System.out.println("Python file '" + fileNames[fileCount] + "':");

            /*Run the student's script to produce answers*/
            String str=""; //for reading line by line
            String command="python " + filePath;

            System.out.println("    Executing: "+ command);
            Process process = Runtime.getRuntime().exec(command);

            //This is the file for printing output
            File file = new File("./WorkFile/compare.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            // Write results of script to console
            while ((str = in.readLine()) != null) {
                writer.write(str+"\n");
            }
            writer.close();
            System.out.println(ANSI_GREEN + "    Saved student output to " + STUDENT_OUTPUT + ANSI_RESET);

            if(new File(STUDENT_OUTPUT).length() != 0) {
                System.out.println("    Comparing output: ");
                //compare the output with given answers
                compareOutput(STUDENT_OUTPUT, test.getSetting("CHECK_FILE"));
                System.out.println(ANSI_GREEN + "    Saved compared output to " + COMPARED_RESULT + ANSI_RESET);
            }
            else{
                System.out.println(ANSI_RED + "    'compare.txt' was empty. No comparison was done." + ANSI_RESET);
            }

            String outfile= removeExtension(fileNames[fileCount]) + CHECK_SUFFIX;
            resetFile(new File(outfile));
            ArrayList<String> strs;

            //Checking script format
            if(test.getSetting("CHECK_FORMAT").compareTo("TRUE")==0){
                System.out.println("    Checking format");
                strs=formatchecker(filePath);
                //System.out.println("    " + strs);
                writeToFile(strs,outfile);

            }

            //Checking script activity
            if(test.getSetting("CHECK_ACTIVITY").compareTo("TRUE")==0){
                System.out.println("    Checking activity");
                int expected=5; //number of expected activities
                boolean ac= activitychecker(filePath,expected) ;
                if(ac){
                    System.out.println(ANSI_GREEN + "    Number of activity is same as expected." + ANSI_RESET);
                }else{
                    System.out.println(ANSI_RED + "    Number of activity is not same as expected." + ANSI_RESET);
                }

            }

            //Checking script answers
            if(test.getSetting("CHECK_COMMENT_ANSWERS").compareTo("TRUE")==0){
                System.out.println(ANSI_GREEN + "    Saving answers to " + outfile + ANSI_RESET);
                strs=answerchecker(filePath);
                if(strs.isEmpty()){
                    System.out.println(ANSI_RED + "    No answers to save." + ANSI_RESET);
                }
                else{
                    System.out.println(ANSI_GREEN + "    Answers saved." + ANSI_RESET);
                }
                writeToFile(strs,outfile);
            }
            System.out.println();
            fileCount++;
        }

        System.exit(0);
    }

}
