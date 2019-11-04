package resultAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class resultAnalysis {

    private static List result;
    private static int amountCalled = 0;

    public static void main(String[] args) {

        //create list of all the files to analyze
        List<String> files = new ArrayList<>();
        String pathTo = "src/main/java/resultAnalysis/";
        files.add(pathTo + "test1.txt");
        files.add(pathTo + "test2.txt");
        files.add(pathTo + "test3.txt");
        files.add(pathTo + "test4.txt");

        //create list to store the amount of wrong answers per question
        result = new ArrayList<Integer>(Collections.nCopies(4, 0));

        //run through all files analyzing each one and putting resulting into the result list
        for(int i = 0; i < files.size(); i++){
            try {
                arrayitize(files.get(i));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        //print out the results
        displayResults(result);

    }

    public static void arrayitize(String path) throws FileNotFoundException {
        //increment used to find class total aka amount of assignments graded
        amountCalled++;
        Scanner in = new Scanner(new File(path));
        in.nextLine();
        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            String[] separated = line.split("\t");
            //Split each line into the following
            //0: number  1: student  2: instructor   3: answer
            int question = Integer.parseInt(separated[0].substring(0,1));
            //if wrong add to that questions total
            if(separated[3].equals("Wrong")) {
                result.set(question, (int) result.get(question)+1);
            }

        }
    }


    public static void displayResults(List results) {

        for (int i = 0; i < results.size(); i++) {
            System.out.println("Amount wrong for question " + i + ": " + result.get(i) + " ==> " + ((int) result.get(i) * 100) / amountCalled + "%");
        }
    }
}
