public class Menu {
    private int id;
    private String nameFood;
    private int price;

    public Menu(int id, String nameFood, int price){
        this.id = id;
        this.nameFood = nameFood;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getNameFood() {
        return nameFood;
    }

    public int getPrice() {
        return price;
    }
}
