package commentchecker;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckComment {

    //temporary read file method
    public static ArrayList<String> readActivity(String filename, String regex) {
        Pattern pattern = Pattern.compile(regex);
        ArrayList<String> buf = new ArrayList<>();
        String line;
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line=bufferedReader.readLine())!=null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find())
                {
                    buf.add(matcher.group(0));
                }
            }
            bufferedReader.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return buf;
    }

    //temporary read file method 2
    public static ArrayList<String> readAnswers(String filename) {
        ArrayList<String> buf = new ArrayList<>();
        ArrayList<String> buffer = new ArrayList<>();
        String line;
        String com="";
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line=bufferedReader.readLine())!=null) {
                com+="\n"+line;
            }
            String[] token = com.split("\"\"\"");
            buf.addAll(Arrays.asList(token));
            for(int i=1;i<buf.size();i+=2){
                buffer.add(buf.get(i));
            }
            /*
            for(int i=0;i<buffer.size();i++){
                System.out.println(buffer.get(i));
            }
            */
            bufferedReader.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return buffer;
    }


    public static void main(String[]args){
        //First check if they have commented on number of questions
        String filename="python_labs\\chual2242_lab04.py";
        String regex="^#.+"; //to get string that begin with #
        ArrayList<String> activityStr=readActivity(filename,regex);
        int countActivity=0; //to count on Activity number
        for (String s : activityStr) {
            if (s.toLowerCase().startsWith("#activity") || s.startsWith("# activity")) {
                countActivity += 1;
            }
        }
        System.out.println(countActivity);

        //Then check if they have answer questions using the comments
        ArrayList<String> quesStr=readAnswers(filename);
        for(int i=0;i<quesStr.size();i++){
            System.out.println(quesStr.get(i));
        }

    }
}
