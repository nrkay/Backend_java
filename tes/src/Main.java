import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static String line ="============================";
    static List<Pesanan> orders = new ArrayList<>();
    static List<Menu> menuList = new ArrayList<>();
    public static void main(String[] args) {
        while (true){
            menu();
        }
    }

    public static void menu() {
        System.out.println(line);
        System.out.println("Selamat Datang di BinarFud");
        System.out.println(line);
        menuList.add(new Menu(1, "Nasi Goreng", 15000));
        menuList.add(new Menu(2, "Mie Goreng", 13000));
        menuList.add(new Menu(3, "Nasi + Ayam", 18000));
        menuList.add(new Menu(4, "Es Teh Manis", 3000));
        menuList.add(new Menu(5, "Es Jeruk", 5000));

    }
}