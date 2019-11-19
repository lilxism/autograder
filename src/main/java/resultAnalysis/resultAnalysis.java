package resultAnalysis;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class resultAnalysis {

//    private static List<Integer> result;
    private static int amountCalled = 0;
//    private static List<String> files;
    private static int numQ;

//    public static void main(String[] args){
//
//        //create list of all the files to analyze
//        files = new ArrayList<>();
//        String pathTo = "src/main/java/resultAnalysis/";
//        files.add(pathTo + "test1.txt");
//        files.add(pathTo + "test2.txt");
//        files.add(pathTo + "test3.txt");
//        files.add(pathTo + "test4.txt");
//
//        try {
//
//            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("filename.txt"), StandardCharsets.UTF_8));
//            runAnalysis(files, writer);
//            displayResults(writer, result);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    public static void runAnalysis(List<String> list, Writer writer, List<Integer> result) throws IOException {
        //create list to store the amount of wrong answers per question
        numQ = numQuestions(new File(list.get(0)));
        result = new ArrayList<>(Collections.nCopies(numQ, 0));

        writer.write("INDIVIDUAL SCORES\n");
        //run through all files analyzing each one and putting resulting into the result list
        for (String s : list) {
            try {
                splitResult(s, writer, result);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        writer.write("\n");

    }

    public static void splitResult(String path, Writer writer, List<Integer> result) throws IOException {
        //increment used to find class total aka amount of assignments graded
        amountCalled++;
        int amountWrong = 0;
        Scanner in = new Scanner(new File(path));
        in.nextLine();
        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            String[] separated = line.split("\t\t\t");
            //Split each line into the following
            //0: number  1: student  2: instructor   3: answer
            int question = Integer.parseInt(separated[0].substring(0,1));
            //if wrong add to that questions total
            if(separated[3].equals("Wrong")) {
                amountWrong++;
                result.set(question, result.get(question) +1);
            }
        }
        String name = "STUDENT";
        String grade = getGrade(amountWrong, result);

        writer.write(name + ": " + grade + "\n");

    }


    public static String getGrade(int amountWrong, List<Integer> result){
        return (numQ - amountWrong) + "/" + numQ;
    }

    public static void displayResults(Writer writer, List<Integer> result) throws IOException {

            writer.write("CLASS RESULTS\n");
            for (int i = 0; i < result.size(); i++) {
                writer.write( result.get(i)  + " students got question " + i + " wrong. That is " + (result.get(i) * 100) / amountCalled + "% of the class\n");
            }

    }

    public static int numQuestions(File file) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        lines -= 1;
        return lines;
    }
}
