package runner;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

/*
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
        System.out.print("Enter path to python script to be executed: ");
        String python = scanner.next();
        System.out.print("Does this script require user input? (y/n) ");
        String haveInput = scanner.next();

        if(haveInput.toLowerCase().startsWith("y")) {
            System.out.print("Enter answer file for script: ");
            String answers = scanner.next();
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
    }
}
*/