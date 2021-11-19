public class Weapon extends Product{
    private int damage = 0;

    public Weapon(){

    }
    public Weapon(String name, String desc, double price, int qty, int damage){
        super(name, desc, price, qty);
        this.damage = damage;
    }

    
}   