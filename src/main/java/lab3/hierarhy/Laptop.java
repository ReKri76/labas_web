package lab3.hierarhy;

public abstract class Laptop extends Computer {
    protected int batteryLevel;
    protected boolean isLidOpen;
    protected double screenSize;
    protected double weight;
    protected boolean isPluggedIn;

    protected Laptop(double price, String name, String cpu, String vendor) {
        super(price, name, cpu, vendor);
    }

    public void openLid() {
        isLidOpen = true;
        System.out.println("Крышка открыта, экран " + screenSize + "\" активирован.");
    }

    public void closeLid() {
        isLidOpen = false;
        System.out.println("Крышка закрыта, ноутбук переходит в спящий режим.");
    }

    public void chargeBattery(int minutes) {
        if (isPluggedIn) {
            batteryLevel = Math.min(100, batteryLevel + (minutes / 2));
            System.out.println("Зарядка... Текущий уровень: " + batteryLevel + "%");
        }
    }
}