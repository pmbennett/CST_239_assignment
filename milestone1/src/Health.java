public class Health extends Product{
    private int healthRegen; //add setter and getter 
    public Health(){

    }

    public Health(String name, String desc, double price, int qty, int healthRegen){
        super(name, desc, price, qty);
        this.healthRegen = healthRegen;
    }
}
