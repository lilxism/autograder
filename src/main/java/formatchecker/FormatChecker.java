package formatchecker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormatChecker {

    public static class SyntaxSpec {
        public String regex;
        public String name;
        public SyntaxSpec(String regex, String name) {
            this.regex = regex;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        String filePath="python_labs\\chual2242_lab04.py";
        List<SyntaxSpec> specs = Arrays.asList(
                new SyntaxSpec("def\\s+[a-zA-Z_-]*[A-Z-]+[a-zA-Z_-]*", "Improper function name"),
                new SyntaxSpec("(?:==|!=)\\s*(?:True|False)", "Redundant comparison"), // (ex. == True)
                new SyntaxSpec("if\\s+\\w+\\s*=\\s*\\w+", "Assignment within conditional") // if a = b
        );
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filePath));
            List<Integer> matches = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                for (SyntaxSpec spec : specs) {
                    if (lines.get(i).matches(spec.regex)) {
                        System.out.println("Line " + i + ": " + spec.name);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
