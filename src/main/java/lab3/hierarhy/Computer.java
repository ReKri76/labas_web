package lab3.hierarhy;

import lombok.Getter;

@Getter 
public abstract class Computer implements Comparable<Computer> {
    protected final double price;
    protected final String name;
    protected final String cpu;
    protected final String vendor;

    protected int countInWarehouse = 0;

    protected Computer(double price, String name, String cpu, String vendor) {
        this.price = price;
        this.name = name;
        this.cpu = cpu;
        this.vendor = vendor;
    }

    /**
     * Покупка компьютеров со склада
     * @param count количество моделей для покупки
     * 
    */
    public void buy(int count){
        if (count>countInWarehouse || count<1)
            throw new RuntimeException("on warehouse less this models than u want to buy;");

        countInWarehouse-=count;
    }

    /**
     * Привоз на склад компьютеров
     * @param count новых моделей
     * 
    */
    public void bring(int count){
        if (count<1)
            throw new RuntimeException("count of new computers must be 1 or more");

        countInWarehouse+=count;
    }

    @Override
    public int compareTo(Computer other) {
        var otherName = other.getName().toLowerCase();
        var thisName = name.toLowerCase();
        
        for (var i = 0; i<otherName.length() && i < thisName.length(); i++){
            if (otherName.charAt(i) == thisName.charAt(i))
                continue;
            
            if (thisName.charAt(i) > otherName.charAt(i))
                return 1;
            else 
                return -1;
        }

        if (otherName.length()>thisName.length())
            return -1;
        else if (otherName.length()<thisName.length())
            return 1;
        else return 0;
    }

    @Override 
    public String toString(){
    return "price: " + price + "; name: " + name + "; cpu: " + cpu + "; vendor: " + vendor + "; countInWarehouse: " + countInWarehouse + ";"; 
    }
}
