import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class Lab2 {

    private static final String REGEX = "^2[0-6][А-Я]{2}[1-9]$";
    private static final Pattern REGEX_COMPILE = Pattern.compile(REGEX);

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println("Чамкин Антон 25ВП3; проверить валидность названия группы ПГУ.");

        Scanner scanner = new Scanner(System.in);
        boolean repeat;

        do {
            String inputString = getNameOfGroup(scanner);
            boolean correct = checkGroup(inputString);

            System.out.println(correct ? "валидный паттерн" : "инвалидный паттерн");

            repeat = args.length > 0 && Objects.equals(args[0], "-r");
        } while (repeat);
    }

    private static String getNameOfGroup(Scanner scanner) {
        System.out.println("введите название группы:");
        return scanner.nextLine().trim();
    }

    private static boolean checkGroup(String group) {
        return REGEX_COMPILE.matcher(group).matches();
    }
}