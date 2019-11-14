package runner;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class runProgram {
    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String str;
        File file = new File("./compare.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        System.out.print("Enter path to python script to be executed: ");
        String python = scanner.next();

        // Run the script without piping anything in
        String command = "python " + python;
        System.out.println(command);
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        // Write results of script to console
        while ((str = in.readLine()) != null) {
            System.out.println(str);
            writer.write(str+"\n");
        }
        writer.close();
    }
}
