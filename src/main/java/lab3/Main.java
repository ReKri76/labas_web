package lab3;

import java.util.Scanner;

import lab3.hierarhy.API;

public class Main {	

	private static final API api = API.defaultApi();

	public static void main(String[] args) {
		
		System.out.println("Чамкин Антон; 25ВП3; Лабораторная работа 3, вариант 15");

		var scanner = new Scanner(System.in);

		while (true) { 
			System.out.print("Введите команду : ");
			var command = scanner.nextLine().trim();

			if (command.equals("vw"))
				vw();
			else if (command.equals("avrg"))
				avrg();
			else if (command.equals("avrg -higher")) 
				avrgHigher();
			else if (command.equals("na")) 
				na();
			else if (command.equals("srtd")) 
				srtd();
			else if (command.startsWith("find ")) {
				var name = command.substring(5);
				find(name, scanner);
			}
			else if (command.equals("exit")) 
				break;
			else 
				help();
			
		}
	}

	static private void help(){
		System.out.println("""
			Список поддерживаемых команд:

				vw: показать все компьютеры и информацию о них
				avrg: вывести среднюю стоимость компьютеров
				avrg -higher: вывести список оборудования, стоимость которого выше средней
				na: вывести оборудование, поставка которого в данный момент невозможна
				srtd: отсортировать список оборудования по названию
				find <name>: найти оборудование с названием <name> и изменить его количество
				exit: выйти из программы
                                       """
									);
	}

	static private void vw() {
		System.out.println(api.getComputers().toString());
	}

	static private void avrg() {
		System.out.println(api.getAveragePrice());
	}

	static private void avrgHigher() {
		System.out.println(api.listOfComputersWithHigherThanAveragePrice().toString());
	}

	static private void na() {
		System.out.println(api.listOfUnavailableComputer().toString());
	}

	static private void srtd() {
		api.sortArrayByAlphabet();
		System.out.println("Успех");
	}

	static private void find(String name, Scanner scanner) {
		var elements = api.findByName(name);

		if (elements.isEmpty()) {
			System.out.println("Компьютер с таким названием не найден");
			return;
		}

		if (elements.size() > 1) {
			System.out.println("Существует несколько компьютеров с таким названием. Определить нужный невозможно");
			return;
		}

		var element = elements.getFirst();

		System.out.println(element.toString());

		System.out.println("Введите изменение количества на складе. Нажмите Enter для отмены.");

		var input = scanner.nextLine().trim();

		if (input.equals(""))
			return;
		
		try {
			var value = Integer.parseInt(input);

			if (value < 0)
				element.buy(-value);
			else
				element.bring(value);
		} catch (RuntimeException e) {
			System.out.println("Неверный формат ввода");
			return;
		}

		System.out.println("Успех");
	}
}