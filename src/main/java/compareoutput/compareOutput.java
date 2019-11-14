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
			FileWriter fileWriter = new FileWriter("src\\main\\java\\compareoutput\\compared.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);

			printWriter.printf("%s\t\t\t%s\t\t\t%s\t\t\t%s\n", "No","Student's answer","Instructor's answer","Comment");

			//read the file
			while(sc1.hasNext() && sc2.hasNext()) {
			    do {
                    studentAnswer = sc1.nextLine();
                } while(studentAnswer.equals("") || studentAnswer.substring(0,1).equals(">")); //read in the next line if the line is empty - some students might add "\n" after each question

				instructorAnswer = sc2.nextLine();

				if(studentAnswer.equals(instructorAnswer)) {
					check = "Correct";
				} else {
					check = "Wrong";
				}

				++count;
				if(studentAnswer.length()>50) {
					studentAnswer = studentAnswer.substring(0,30);
				}
				if(instructorAnswer.length()>50) {
					instructorAnswer = instructorAnswer.substring(0,0);
				}
				printWriter.printf("%d\t\t\t%s\t\t\t%s\t\t\t%s\n",count,studentAnswer,instructorAnswer,check);
			}

			//the student did not answer all the questions
			if(sc2.hasNext()) {
				while(sc2.hasNext()) {
					++count;
					instructorAnswer = sc2.nextLine();
					printWriter.printf("%d\t\t\t%s\t\t\t%s\t\t\t%s\n",count,"-",instructorAnswer,"Wrong");
				}
			} else if(sc1.hasNext()) {
                while(sc1.hasNext()) {
                    ++count;
                    instructorAnswer = sc1.nextLine();
                    printWriter.printf("%d\t\t\t%s\t\t\t%s\t\t\t%s\n",count,"-",instructorAnswer,"Wrong");
                }
            }
			printWriter.close();
	}

	public static void main(String[] args) throws IOException {
		String student = "src\\main\\java\\compareoutput\\student_lab4.txt";
		String instructor = "src\\main\\java\\compareoutput\\answer_lab4.txt";

		compareOutput(student, instructor);
	}
}
