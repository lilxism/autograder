package runner;

import java.io.*;


public class runProgram {
    public static void main(String args[]) throws IOException {
        String str = null;
        Process process = Runtime.getRuntime().exec("python python_labs/chual2242_lab04.py");
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while((str = in.readLine()) != null) {
            System.out.println(str);
        }
    }
}