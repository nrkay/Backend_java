// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> menuList = new ArrayList<>();
        ArrayList<Integer> jumlahList = new ArrayList<>();
        ArrayList<Integer> hargaList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int totalHarga = 0;

        // Menampilkan daftar menu
        System.out.println("Daftar Menu:");
        System.out.println("1. Nasi Goreng | Rp 15.000");
        System.out.println("2. Mie Ayam | Rp 15.000");
        System.out.println("3. Nasi Ayam | Rp 15.000");
        System.out.println("4. Es Teh Manis | Rp 15.000");
        System.out.println("0. Keluar");
        System.out.println("99. Selesai Pemesanan");

        while (true) {
            System.out.print("Masukkan angka menu: ");
            int pilihanMenu = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            if (pilihanMenu == 0) {
                break;
            } else if (pilihanMenu == 99) {
                break;
            } else if (pilihanMenu < 1 || pilihanMenu > 4) {
                System.out.println("Menu tidak valid.");
                continue;
            }

            String menu = "";
            int harga = 0;

            switch (pilihanMenu) {
                case 1:
                    menu = "Nasi Goreng";
                    harga = 15000;
                    break;
                case 2:
                    menu = "Mie Ayam";
                    harga = 15000;
                    break;
                case 3:
                    menu = "Nasi Ayam";
                    harga = 15000;
                    break;
                case 4:
                    menu = "Es Teh Manis";
                    harga = 15000;
                    break;
            }

            System.out.print("Masukkan jumlah porsi: ");
            int jumlahPorsi = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            int totalHargaMenu = harga * jumlahPorsi;

            menuList.add(menu);
            jumlahList.add(jumlahPorsi);
            hargaList.add(totalHargaMenu);

            System.out.print("Apakah Anda ingin menambah pesanan lagi? (ya/tidak): ");
            String tambahPesanan = scanner.nextLine();

            if (tambahPesanan.equalsIgnoreCase("tidak")) {
                break;
            }
        }

        // Menampilkan pesanan
        System.out.println("Pesanan Anda:");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println(menuList.get(i) + " - " + jumlahList.get(i) + " porsi - Total Harga: Rp " + hargaList.get(i));
            totalHarga += hargaList.get(i);
        }

        System.out.println("Total Harga Keseluruhan: Rp " + totalHarga);

        scanner.close();
    }

//    private static void listMenu() {
//        System.out.println("====================");
//        System.out.println("Selamat Datang Binar Food");
//        System.out.println("====================\n");
//        System.out.println("1. Nasi Goreng | Rp 15.000");
//        System.out.println("2. Mie Ayam | Rp 15.000");
//        System.out.println("3. Nasi + Ayam | Rp 15.000");
//        System.out.println("4. Es Teh Manis | Rp 15.000");
//        System.out.println("5. Es Jeruk | Rp 15.000");
//        System.out.println("99. Pesan dan Bayar");
//        System.out.println("0. Keluar Aplikasi");
//
//
//
//
//    }
}
