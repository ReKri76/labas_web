import java.util.Scanner;
import java.util.regex.Pattern;

public class Lab2 {
    public static void main(String[] args){

        var scanner = new Scanner(System.in);
        System.out.println("input group name:");
        var inputString = scanner.nextLine().trim();

        var regex = "2[0-6][А-Я]{2}[1-9]";
        var correct = Pattern.compile(regex).matcher(inputString).matches();
        System.out.println(correct ? "valid pattern" : "invalid pattern");
    }
}
