package runner;

import java.io.*;
import java.util.Scanner;


public class runProgram {
    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String str;
        File file = new File("./compare.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        //System.out.println("Please enter text file with user input for program");
        //String txtFile = scanner.next();
        //if(txtFile == null) {
        //    System.out.println("Please enter a valid text file");
        //}
        Process process = Runtime.getRuntime().exec("python python_labs/chual2242_lab04_removeDuplicates.py" );
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while((str = in.readLine()) != null) {
            System.out.println(str);
            String fileContent = str;
            writer.write(fileContent);
        }
        writer.close();
    }
}