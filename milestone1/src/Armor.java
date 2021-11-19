public class Armor extends Product {

    private int protection;
    public Armor(){

    }

    public Armor(String name, String desc, double price, int qty, int protection){
        super(name, desc, price, qty);
        this.protection = protection;
    }
}