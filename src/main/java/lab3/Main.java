package lab3;

import java.util.Scanner;

import lab3.hierarhy.API;

public class Main {	

	static private API api = API.defaultApi();

	public static void main(String[] args) {
		
		System.out.println("Чамкин Антон; 25ВП3; Лабараторная 3, Вариант 15;");

		var scanner = new Scanner(System.in);

		while (true) { 
			System.out.print("Введите команду : ");
			var command = scanner.nextLine().trim();

			if (command.equals("vw")) {
				vw();
			} else if (command.equals("avrg")) {
				avrg();
			} else if (command.equals("avrg -higher")) {
				avrgHigher();
			} else if (command.equals("na")) {
				na();
			} else if (command.equals("srtd")) {
				srtd();
			} else if (command.startsWith("find ")) {
				var name = command.substring(5);
				find(name, scanner);
			} else if (command.equals("exit")) {
				break;
			} else {
				help();
			}
		}
	}

	static private void help(){
		System.out.println("""
			Список поддерживаемых команд:

				vw: показать все существующие на данный момент компьюетры, и данне о них
				avrg: вывести средную стоимость компьютеров
				avrg -higher: вывести список оборудования, стоимость которого выше средней
				na: вывести оборудование, поставка, котрого на данный момент невозможна
				srtd: отсортировать текущий список оборудования в алфавитном порядке названия
				find <name>: найти оборудование с названием <name> и начать его редактирование
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

		if (elements.size() == 0) {
			System.out.println("Компьютер с таким названием не найден");
			return;
		}

		if (elements.size()>1){
			System.out.println("Существует несколько компьютеров с таким названием. Определить нужный невозможно");
			return;
		}

		var element = elements.getFirst();

		System.out.println(element.toString());

		System.err.println("Вы можете изменить количество этого оборудования на складе. Нажмите энтер для пропуска.");

		var input = scanner.nextLine().trim();

		if (input.equals(""))
			return;
		
		try{
			if (input.length() == 2 && input.charAt(0) == '-'){
				var value = Integer.parseInt(input.substring(1));
				element.buy(value);
			}else if(input.length() == 1){
				var value = Integer.parseInt(input);
				element.bring(value);
			} else 
				throw new RuntimeException();
		} catch (Exception e){
			System.out.println("Неверный формат ввода");
			return;
		}

		System.out.println("Успех");
	}
}