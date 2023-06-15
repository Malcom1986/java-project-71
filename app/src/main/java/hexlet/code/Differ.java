package hexlet.code;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.Map;
public class Differ {
    public static String generate(String file1, String file2, String format) throws Exception {

        Path fullPath1 = Paths.get(file1).toAbsolutePath().normalize();
        Path fullPath2 = Paths.get(file2).toAbsolutePath().normalize();

        String content1 = Files.readString(fullPath1);
        String content2 = Files.readString(fullPath2);

        String[] subStr;
        String delimeter = "\\.";
        subStr = file1.split(delimeter);
        String extension = subStr[subStr.length - 1];
        Map<String, Object> data1 = Parser.parse(extension, content1);
        Map<String, Object> data2 = Parser.parse(extension, content2);
        Map<String, KeyStatus> difference = Difference.makeDifference(data1, data2);
        return Formatter.choiceFormat(difference, format);
    }
    public static String generate(String filePath1, String filePath2) throws Exception {
        String format = "stylish";
        return generate(filePath1, filePath2, format);
    }
}
