//Output can be of many types (graph...)
//Instructor's answer are given at each line for each question
//After comparing each output??

import java.io.*;
import java.util.Scanner;


public class compareOutput {
	public static void compareOutput(String studentFile, String answer) {

		try {
			Scanner sc1 = new Scanner(new File(studentFile));
			Scanner sc2 = new Scanner(new File(answer));
			
			String check = "";
			String studentAnswer,instructorAnswer;
			int count = 0; //question number
			int correct = 0, wrong=0;
			FileWriter fileWriter = new FileWriter("testtest.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			printWriter.printf("%-10s%-30s%-30s%-20s\n", "No","Student","Instructor","Comment"); 
			
			//read the file
			while(sc1.hasNext() && sc2.hasNext()) {
				studentAnswer = sc1.nextLine();
				instructorAnswer = sc2.nextLine();
				
				if(studentAnswer.equals(instructorAnswer)) {
					//do someting
					check = "Correct";
					correct++;
					
				} else {
					//do something 
					check = "Wrong";
					wrong++;
				}
				++count;
				printWriter.printf("%-10d%-30s%-30s%-20s\n",count,studentAnswer,instructorAnswer,check);		
			}

			//the student did not answer all the questions
			if(sc2.hasNext()) {
				int totalQuestion = count;
				while(sc2.hasNext()) {
					instructorAnswer = sc2.nextLine();
					totalQuestion++;
				}
				int didNotAnswer = totalQuestion - count;
				printWriter.println("\nThe student only answered " + count + "/" + totalQuestion + " questions.");
				
			} 
			
			printWriter.println("Correct: " + correct + "\nWrong: "+ wrong);	
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String student = args[0];
		String instructor = args[1];
		
		compareOutput(student, instructor);
	}
}
		