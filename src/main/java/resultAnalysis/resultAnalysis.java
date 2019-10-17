package resultAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class resultAnalysis {

    public static void main(String[] args) {

        String[][] arr;

        try {

            arr = arrayitize("src/test.txt");
            System.out.println(arr[0][0]);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static String[][] arrayitize(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/test.txt"));
        in.nextLine();
        List<String[]> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            line = line.substring(line.indexOf(')') + 1);
            String[] separated = line.split(", ");
            lines.add(separated);
        }


        String[][] result = new String[lines.size()][];

        for (int i = 0; i < result.length; i++) {
            result[i] = lines.get(i);
        }

        return result;
    }
}
