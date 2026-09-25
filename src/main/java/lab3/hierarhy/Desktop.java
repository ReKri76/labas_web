package lab3.hierarhy;

public class Desktop extends Computer {
    private String formFactor;
    private boolean hasWaterCooling;
    private int monitorCount;
    private int powerSupplyWattage;
    private boolean isUpsConnected;

    public Desktop(double price, String name, String cpu, String vendor) {
        super(price, name, cpu, vendor);
    }

    public void connectPeripheral(String deviceName) {
        System.out.println("Подключено новое устройство к системному блоку: " + deviceName);
    }

    public void cleanDustFilters() {
        System.out.println("Напоминание: пылевые фильтры корпуса " + formFactor + " очищены.");
    }
}