import java.util.Arrays;
import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args){

        var scanner = new Scanner(System.in);
        System.out.println("Чамкин Антон; 25ВП3; " +
                "Полусумма всех соседних чисел из переданного в стандартном потоке ввода массива чисел.");
        System.out.println("введите лист элементов:");
        final var inputString = scanner.nextLine();

        try {
            var array = parse(inputString);

            if (array.length==1) {
                System.out.println(array[0]);
                return;
            }

            var output = sum(array);

            System.out.println("Результат :"+ Arrays.toString(output));
        }
        catch(RuntimeException e){
            System.out.println("Некорректный ввод");
            main(args);
        }
    }

    /**
     * Преобразует строку чисел в массив
     *
     * @param inputString  входная строка
     * @return массив double
     * */
    public static Double[] parse(String inputString){
        var numbers = inputString.split(" ");
        var array = new Double[numbers.length];
        for (var i = 0; i < numbers.length; i++)
            array[i] = Double.parseDouble(numbers[i]);

        return array;
    }

    /**
     * Преобразует массив в массив полусумм соседних элементов
     * @param array входной массив
     *
     * @return массив палусумм
     * */
    public static Double[] sum(Double[] array){
        var output = new Double[array.length];
        output[0] = (array[0] + array[1])/2;
        output[output.length - 1] = (array[array.length - 1] + array[array.length - 2]) / 2;
        var i = 1;
        while (i < array.length - 1) {
            output[i] = array[i] + array[i - 1] + array[i + 1];
            i++;
        }
        return output;
    }
}
