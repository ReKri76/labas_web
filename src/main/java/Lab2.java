import java.util.Scanner;
import java.util.regex.Pattern;

public class Lab2 {
    public static void main(String[] args){

        System.out.println("Чамкин Антон 25ВП3; проверить валидность названия группы ПГУ.");

        var scanner = new Scanner(System.in);
        System.out.println("введите название группы:");
        var inputString = scanner.nextLine().trim();

        var regex = "2[0-6][А-Я]{2}[1-9]";
        var correct = Pattern.compile(regex).matcher(inputString).matches();
        System.out.println(correct ? "valid pattern" : "invalid pattern");
    }
}
