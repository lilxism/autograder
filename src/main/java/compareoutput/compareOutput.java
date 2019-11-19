package compareoutput;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class compareOutput {
	public static void compareOutput(String studentFile, String answer, String outfile) throws IOException {

			Scanner sc1 = new Scanner(new File(studentFile));
			Scanner sc2 = new Scanner(new File(answer));

			String check = "";
			String studentAnswer,instructorAnswer;
			int count = 0; //line
			FileWriter fileWriter = new FileWriter(outfile);
			PrintWriter printWriter = new PrintWriter(fileWriter);

			printWriter.printf("%s\t\t\t%s\t\t\t%s\n", "No","Student's answer","Comment");

			//read the file
			while(sc1.hasNext() && sc2.hasNext()) {
                do {
                    studentAnswer = sc1.nextLine();
                } while(studentAnswer.equals("") || studentAnswer.matches(".+\\?\\s*") || studentAnswer.matches(".+:\\s*") || studentAnswer.matches(">.+"));
                //read in the next line if the line is empty - some students might add "\n" after each question()-1).equals(":") || studentAnswer.substring(studentAnswer.length()-1).equals("?")); //read in the next line if the line is empty - some students might add "\n" after each question

				instructorAnswer = sc2.nextLine();

				if(studentAnswer.matches(instructorAnswer)) {
					check = "Correct";
				} else {
					check = "Wrong";
				}

				++count;
				printWriter.printf("%d\t\t\t%s\t\t\t%s\n",count,studentAnswer,check);
			}

			//the student did not answer all the questions
			if(sc2.hasNext()) {
				while(sc2.hasNext()) {
					++count;
					instructorAnswer = sc2.nextLine();
					printWriter.printf("%d\t\t\t%s\t\t\t%s\n",count,"-","Wrong");
				}
			} else if(sc1.hasNext()) {
                while(sc1.hasNext()) {
                    ++count;
                    instructorAnswer = sc1.nextLine();
                    printWriter.printf("%d\t\t\t%s\t\t\t%s\n",count,"-","Wrong");
                }
            }
			printWriter.close();
	}

	public static void main(String[] args) throws IOException {
		String student = "src\\main\\java\\compareoutput\\student_lab4.txt";
		String instructor = "src\\main\\java\\compareoutput\\answer_lab4.txt";

		compareOutput(student, instructor, "./WorkFile/compared.txt");
	}
}
