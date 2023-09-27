public class Menu {
    private int id;
    private String food;
    private int price;

    public Menu(){};

    public Menu (int id, String nameFood, int price){
        this.id = id;
        this.food = nameFood;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getFood() {
        return food;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", food='" + food + '\'' +
                ", price=" + price +
                '}';
    }
}
