import java.util.Scanner;
import java.util.regex.Pattern;

public final class Lab2 {

    final static private String REGEX = "2[0-6][А-Я]{2}[1-9]";

    public static void main(String[] args){

        System.out.println("=============================================================");
        System.out.println("Чамкин Антон 25ВП3; проверить валидность названия группы ПГУ.");

        var inputString = getNameOfGroup();
        var correct = checkGroup(inputString);

        System.out.println(correct ? "валидный паттерн" : "инвалидный паттерн");

        main(args);
    }

    private static String getNameOfGroup(){
        var scanner = new Scanner(System.in);
        System.out.println("введите название группы:");
        var inputString = scanner.nextLine().trim();
        return inputString;
    }

    private static boolean checkGroup(String group){
        var correct = Pattern.compile(REGEX).matcher(group).matches();
        return correct;
    }
}
