// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        diimprove dalam object of array
        ArrayList<String> menuList = new ArrayList<>();
        ArrayList<Integer> jumlahList = new ArrayList<>();
        ArrayList<Integer> hargaList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String menu = "";
        int harga = 0;
        int totalHarga = 0;

        // Menampilkan daftar menu



        while (true) {
            listMenu();
            System.out.print("=> ");
            int pilihanMenu = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            if (pilihanMenu == 0) {
                break;
            } else if (pilihanMenu == 99) {
                break;
            } else if (pilihanMenu < 1 || pilihanMenu > 5) {
                System.out.println("Menu tidak valid.");
                continue;
            }

            // menampilkan pesanan yg dipilih
            System.out.println("================================");
            System.out.println("PESANAN ANDA");
            System.out.println("================================");

            switch (pilihanMenu) {
                case 1 :
                    System.out.println("Nasi Goreng | 15.000");
                    menu = "Nasi Goreng";
                    harga = 15000;
                    break;
                case 2 :
                    System.out.println("Mie Goreng | 13.000");
                    menu = "Mie Goreng";
                    harga = 13000;
                    break;
                case 3 :
                    System.out.println("Nasi + Ayam  | 18.000");
                    menu = "Nasi + Ayam";
                    harga = 18000;
                    break;
                case 4 :
                    System.out.println("Es Teh Manis | 3.000");
                    menu = "Es Teh Manis";
                    harga = 3000;
                    break;
                case 5 :
                    System.out.println("Es Jeruk | 5.000");
                    menu = "Es Jeruk";
                    harga = 5000;
                    break;
            }

            // input banyaknya pesanan
            System.out.print("qty=> ");
            int jumlahPorsi = scanner.nextInt();
            scanner.nextLine();
            totalHarga = jumlahPorsi * harga;

            // memasukkan value yang ada di variabel menu, jumlahPorsi kedalam list untuk ditampilkan
            menuList.add(menu);
            jumlahList.add(jumlahPorsi);
            hargaList.add(totalHarga);
        }

        // Menampilkan pesanan
        System.out.println("==========================================");
        System.out.println("BINARFOOD");
        System.out.println("==========================================\n\n");
        System.out.println("Terimakasih karna sudah memesan di BinarFood");
        System.out.println("Di bawah ini adalah pesanan anda:\n");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println(menuList.get(i) + " | " + jumlahList.get(i) + " porsi | Total Harga: Rp " + hargaList.get(i));
            totalHarga += hargaList.get(i);
        }

        System.out.println("Total Harga Keseluruhan: Rp " + totalHarga);
        System.out.println("==========================================");
        System.out.println("Pembayaran : Binarcash ");
        System.out.println("==========================================");

        scanner.close();
    }

    private static void listMenu() {
        System.out.println("==========================================");
        System.out.println("Selamat Datang Binar Food");
        System.out.println("==========================================\n");
        System.out.println("1. Nasi Goreng | Rp 15.000");
        System.out.println("2. Mie Ayam | Rp 13.000");
        System.out.println("3. Nasi + Ayam | Rp 18.000");
        System.out.println("4. Es Teh Manis | Rp 3.000");
        System.out.println("5. Es Jeruk | Rp 5.000");
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar Aplikasi");
    }

    private static void printStruck() {
        System.out.println();
    }
}
