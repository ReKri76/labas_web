package lab3.hierarhy;

import java.util.ArrayList;
import java.util.List;

public class API {
	private List<Computer> computers;
    private double averagePrice = 0;

    public List<Computer> getComputers() {
        return List.copyOf(computers);
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    /**
     * основной конструктор
     * @param inList - входной лист компьютеров
     */
    public API(List<Computer> inList){
        if (inList == null) {
            throw new IllegalArgumentException("Computer list must not be null");
        }

        computers = new ArrayList<>(inList);

        computers.forEach(c -> averagePrice += c.getPrice());

        if (!computers.isEmpty()) {
            averagePrice /= computers.size();
        }
    }
    
    /**
     * Создание дефолтного экземпляра класса API
     * @return дефолтный апи
     */
    public static API defaultApi(){
        return new API(defaultListOfComputers());
    }

    private static List<Computer> defaultListOfComputers(){
        return List.of(
            new Desktop(1, "Office Desktop", "Intel Core i7", "Dell"),
            new MacBook(2, "MacBook Air 13", "Apple M2", "Apple"),
            new AcerAspire(3, "Aspire 5", "AMD Ryzen 5", "Acer"),
            new Desktop(4, "OptiPlex", "intel core i5", "Dell"),
            new MacBook(5, "MacBook Pro 14", "Apple M3", "Apple"),
            new AcerAspire(6, "Aspire 7", "intel core i7", "Acer"),
            new Desktop(7, "Pavilion", "AMD Ryzen 7", "HP"),
            new MacBook(8, "MacBook Pro 16", "Apple M3 Pro", "Apple"),
            new AcerAspire(9, "Swift 3", "intel core i5", "Acer"),
            new Desktop(10, "ThinkCentre", "intel core i5", "Lenovo")
        );
    }

    /**
     * @return список компьютеров, у которых цена выше средней
     */
    public List<Computer> listOfComputersWithHigherThanAveragePrice(){
        return computers.stream().filter( c -> {return c.price>averagePrice;} ).toList();
    }

    /**
     * @return список копьютеров, поставка которых сейчас невозможна
     */
    public List<Computer> listOfUnavailableComputer(){
        return computers.stream().filter(c -> c.getCountInWarehouse() == 0).toList();
    }

    /**
     * сортирует массив оборудования по алфавитному пордяку наименвоания
     */
    public void sortArrayByAlphabet(){
        computers.sort(Computer::compareTo);
    }

    public List<Computer> findByName(String name){
        return computers.stream().filter(c -> {return c.getName().equals(name);}).toList();
    }
}