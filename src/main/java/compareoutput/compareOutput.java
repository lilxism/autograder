//Output can be of many types (graph...)
//Instructor's answer are given at each line for each question
//After comparing each output??
package compareoutput;
import java.io.*;
import java.util.Scanner;

public class compareOutput {
	public static void compareOutput(String studentFile, String answer) throws IOException {

			Scanner sc1 = new Scanner(new File(studentFile));
			Scanner sc2 = new Scanner(new File(answer));

			String check = "";
			String studentAnswer,instructorAnswer;
			int count = 0; //line
			int correct = 0, wrong=0, noAnswer=0;
			FileWriter fileWriter = new FileWriter("src\\main\\java\\compareoutput\\compared.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);

			printWriter.printf("%-5s%-50s%-50s%-20s\n", "No","Student's answer","Instructor's answer","Comment");

			//read the file
			while(sc1.hasNext() && sc2.hasNext()) {
				studentAnswer = sc1.nextLine();
				instructorAnswer = sc2.nextLine();

				if(studentAnswer.equals(instructorAnswer)) {
					check = "Correct";
					correct++;

				} else {
					check = "Wrong";
					wrong++;
				}

				++count;
				if(studentAnswer.length()>50) {
					studentAnswer = studentAnswer.substring(0,40);
				}
				if(instructorAnswer.length()>50) {
					instructorAnswer = instructorAnswer.substring(0,40);
				}

				printWriter.printf("%-5d%-50s%-50s%-20s\n",count,studentAnswer,instructorAnswer,check);
			}


			//the student did not answer all the questions
			if(sc2.hasNext()) {
				while(sc2.hasNext()) {
					++count;
					noAnswer++;
					instructorAnswer = sc2.nextLine();
					printWriter.printf("%-5d%-50s%-50s%-20s\n",count,"",instructorAnswer,"No answer");
				}
			}

			printWriter.println("\nCorrect: " + correct + "\nWrong: "+ wrong);
			if(noAnswer!=0) {
				printWriter.println("No answer: "+ noAnswer);
			}
			printWriter.close();

	}

	public static void main(String[] args) throws IOException {
		String student = "src\\main\\java\\compareoutput\\student_lab4.txt";
		String instructor = "src\\main\\java\\compareoutput\\answer_lab4.txt";

		compareOutput(student, instructor);
	}
}
