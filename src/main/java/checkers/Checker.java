package checkers;
//Zac and Lily
//formatchecker, answerchecker, activitychecker
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import configsettings.*;

public class Checker {

    // Helper class used to store regex of syntax regex of potential issues
    // and description of each problem
    private static class SyntaxSpec {
        String regex;
        String name;
        SyntaxSpec(String regex, String name) {
            this.regex = regex;
            this.name = name;
        }
    }

    // Create ArrayList with a description for each issue that matches a spec
    public static ArrayList<String> formatchecker(String infile) {
        ArrayList<String> strs=new ArrayList<>();
        // Define syntax specs to check for and problem descriptions
        List<SyntaxSpec> specs = Arrays.asList(
                new SyntaxSpec("def\\s+[a-zA-Z_-]*[A-Z-]+[a-zA-Z_-]*", "Improper function name"),
                new SyntaxSpec("(?:==|!=)\\s*(?:True|False)", "Redundant comparison"), // (ex. == True)
                new SyntaxSpec("if\\s+\\w+\\s*=\\s*\\w+", "Assignment within conditional") // if a = b
        );
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(infile));
            List<Integer> matches = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                for (SyntaxSpec spec : specs) {
                    // If the line has the given issue
                    if (lines.get(i).matches(spec.regex)) {
                        // Add the description to the output ArrayList
                        strs.add("Line " + i + ": " + spec.name);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strs;
    }

    // Get a list of answer comments from the given file
    public static ArrayList<String> answerchecker(String infile) {
        ArrayList<String> strs = new ArrayList<>();
        String line;
        StringBuilder com = new StringBuilder();
        try {
            // Read the file contents into a string
            FileReader fileReader = new FileReader(infile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                com.append("\n").append(line);
            }
            // Split by triple quotes (which denote block comments) and get
            // every second section (the parts within quotes)
            String[] token = com.toString().split("\"\"\"");
            for(int i = 1; i < token.length; i += 2){
                strs.add(token[i]);
            }
            bufferedReader.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return strs;
    }

    // Helper function to write a list of strings to a file
    public static void writeToFile(ArrayList<String> strs, String outfile) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outfile, true));
            for (String str : strs) {
                writer.write(str);
            }
            writer.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    // Checks to see if the expected number of activities is present in the file
    public static boolean activitychecker(String infile, int expected) {
        int countAct=0;
        SyntaxSpec spec = new SyntaxSpec("^# ?activity.*", "Activity name");
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(infile));
            List<Integer> matches = new ArrayList<>();
            // Count lines that start with "#activity" or "# activity"
            for (String line : lines) {
                if (line.toLowerCase().matches(spec.regex)) {
                    countAct += 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("    Number of activities: " + countAct);
        return (countAct == expected);
    }
/*
    public static void main(String[]args){
        //CHECK_FORMAT=TRUE
        //CHECK_ACTIVITY=TRUE
        //CHECK_COMMENT_ANSWERS=TRUE
        ConfigSettings test = new ConfigSettings("C:\\Users\\USER\\IdeaProjects\\autograder\\settings.cfg");
        String infile="C:\\Users\\USER\\IdeaProjects\\autograder\\python_labs\\chual2242_lab04.py";
        String outfile="C:\\Users\\USER\\IdeaProjects\\autograder\\python_labs\\chual2242_lab04_check.txt";
        ArrayList<String> strs=new ArrayList<String>();

        if(test.getSetting("CHECK_FORMAT").compareTo("TRUE")==0){
            System.out.println("format");
            strs=formatchecker(infile);
            System.out.println(strs.size());
            writeToFile(strs,outfile);

        }
        if(test.getSetting("CHECK_ACTIVITY").compareTo("TRUE")==0){
            System.out.println("activity");
            int expected=3; //number of expected activities
            boolean ac= activitychecker(infile,expected) ;
            if(ac){
                System.out.println("Number of activity is same as expected.");
            }else{
                System.out.println("Number of activity is not same as expected.");
            }

        }
        if(test.getSetting("CHECK_COMMENT_ANSWERS").compareTo("TRUE")==0){
            System.out.println("answers");
            strs=answerchecker(infile);
            writeToFile(strs,outfile);
        }
    }
    */
}
