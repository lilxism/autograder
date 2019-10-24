package checkers;
//Zac and Lily
//formatchecker, answerchecker, activitychecker
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import configsettings.*;

public class Checker {

    private static class SyntaxSpec {
        String regex;
        String name;
        SyntaxSpec(String regex, String name) {
            this.regex = regex;
            this.name = name;
        }
    }


    public static ArrayList<String> formatchecker(String infile) {
        ArrayList<String> strs=new ArrayList<>();
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
                    if (lines.get(i).matches(spec.regex)) {
                        strs.add("Line " + i + ": " + spec.name);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strs;
    }

    public static  ArrayList<String> answerchecker(String infile) {
        ArrayList<String> strs =new ArrayList<>();
        ArrayList<String> buf = new ArrayList<>();
        String line;
        String com="";
        try {
            FileReader fileReader = new FileReader(infile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line=bufferedReader.readLine())!=null) {
                com+="\n"+line;
            }
            String[] token = com.split("\"\"\"");
            buf.addAll(Arrays.asList(token));
            for(int i=1;i<buf.size();i+=2){
                strs.add(buf.get(i));
            }
            bufferedReader.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return strs;
    }

    public static void writeToFile(ArrayList<String> strs, String outfile) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outfile, true));
            for(int i=0;i<strs.size();i++) {
                writer.write(strs.get(i));
            }
            writer.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


    public static boolean activitychecker(String infile, int expected) {
        boolean checked=false;
        int countAct=0;
        SyntaxSpec spec = new SyntaxSpec("^#.+", "Activity name");
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(infile));
            List<Integer> matches = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).matches(spec.regex)) {
                    if (lines.get(i).toLowerCase().startsWith("#activity") || lines.get(i).startsWith("# activity")) {
                        countAct += 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(countAct==expected){
            checked=true;
        }
        return checked;
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
