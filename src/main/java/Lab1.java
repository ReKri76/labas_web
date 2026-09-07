import java.util.Arrays;
import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args){

        var scanner = new Scanner(System.in);
        System.out.println("input list of numbers:");
        final var inputString = scanner.nextLine();

        var numbers = inputString.split(" ");
        var array = new Double[numbers.length];
        for (var i = 0; i < numbers.length; i++)
            array[i] = Double.parseDouble(numbers[i]);

        var output = new Double[array.length];
        output[0]  = array[0]+array[1];
        output[output.length-1]  = array[array.length-1]+array[array.length-2];
        var i = 1;
        while (i< array.length-1){
            output[i] = array[i]+array[i-1]+array[i+1];
            i++;
        }

        System.out.println(Arrays.toString(output));
    }
}
