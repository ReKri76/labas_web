package lab3.hierarhy;

public class Desktop extends Computer {
    private String formFactor = "Midi Tower";
    private boolean hasWaterCooling = false;
    private int monitorCount = 1;
    private int powerSupplyWattage = 650;
    private boolean isUpsConnected = false;

    public Desktop(double price, String name, String cpu, String vendor) {
        super(price, name, cpu, vendor);
    }

    public void connectPeripheral(String deviceName) {
        System.out.println("Устройство подключено к системному блоку: " + deviceName
                + ". Мониторов: " + monitorCount + ", блок питания: " + powerSupplyWattage + " Вт.");
    }

    public void cleanDustFilters() {
        System.out.println("Пылевые фильтры корпуса " + formFactor + " очищены. Водяное охлаждение: "
                + (hasWaterCooling ? "установлено" : "не установлено")
                + ". ИБП: " + (isUpsConnected ? "подключён" : "не подключён") + ".");
    }

    public void connectUps() {
        isUpsConnected = true;
        System.out.println("Источник бесперебойного питания подключён.");
    }
}