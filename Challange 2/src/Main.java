import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Menu> listMenu = new ArrayList<>();
    static List<Pesanan> listPesanan = new ArrayList<>();
    static String line = "=======================================================";
    static boolean isBreak = false;


    public static void main(String[] args) {

        // menambahkan menu
        addMenu();
        while (!isBreak) {
            // menampilkan menu
            menu();
            // menginput menu
            System.out.print("==> ");
            int inputMenu = scanner.nextInt();
            scanner.nextLine();
            // mengecek apakah inputan tersedia
            int index = -1;
            for (int i = 0; i < listMenu.size(); i++){
                if (listMenu.get(i).getId() == inputMenu ){
                    index = i;
                    break;
                }
            }

            // filter pilihan menu
            if (index != -1){
                    for (Menu item:listMenu){
                       if (index+1 == item.getId()){
                           orderTrue(item.getFood(), item.getPrice());
                       }
                    }
            } else if (inputMenu == 99) {
                if (listPesanan.isEmpty()){
                    System.out.println("Tidak ada pesanan yang tercatat\nsilahkan pesan terlebih dahulu \n");
                } else {
                    konfirmasiPembayaran();
                }

            } else if (inputMenu == 0) {
                break;
            } else {
                System.out.println("Silahkan Masukkan Input Pilihan yang Benar");
                System.out.println(line);
                System.out.println("(Y) untuk Lanjut");
                System.out.println("(N) untuk keluar");
                String pilihan = scanner.next();
                if (!pilihan.equals("Y")){
                    break;
                }
            }
        }
    }

    public static void menu() {
        System.out.println("=======================================================");
        System.out.println("Selamat Datang di BinarFud");
        System.out.println("=======================================================\n");
        for (Menu item:listMenu){
            System.out.println(item.getId() + ". " + item.getFood() + "  | " + item.getPrice());
        }

        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar Aplikasi\n");
    }
    public static void addMenu() {
        listMenu.add(new Menu(1, "Nasi Goreng", 15000));
        listMenu.add(new Menu(2, "Mie Goreng", 13000));
        listMenu.add(new Menu(3, "Nasi + Ayam", 18000));
        listMenu.add(new Menu(4, "Es Teh Manis", 3000));
        listMenu.add(new Menu(5, "Es Jeruk", 5000));
    }
    public static void orderTrue(String nameFood, int price) {

        System.out.println(line);
        System.out.println("Berapa Pesanan Anda");
        System.out.println(line);
        System.out.println(nameFood + " | " + price);
        System.out.print("Qty => ");
        int inputQty = scanner.nextInt();
        scanner.nextLine();
        if (inputQty < 1) {
            System.out.println("Minimal 1 Jumlah Pesanan");
        } else {
            int subHarga = price * inputQty;
            Pesanan orders = new Pesanan(nameFood, inputQty, subHarga);
            listPesanan.add(orders);
        }
    }
    public static void konfirmasiPembayaran(){
        int totalPesanan = 0;
        int totalHarga = 0;
        System.out.println(line);
        System.out.println("Konfirmasi dan Pembayaran");
        System.out.println(line);
        for (Pesanan item: listPesanan){
            System.out.println(item.getMenuPesanan() + "    " + item.getQty() + "    "  + item.getHarga());
            totalPesanan += item.getQty();
            totalHarga += item.getHarga();
        }
        System.out.println("------------------------------------------------------");
        System.out.println("Jumlah Pesanan : " + totalPesanan);
        System.out.println("Total Harga   : " + totalHarga + "\n");
        System.out.println("1. Konfirmasi dan Pembayaran");
        System.out.println("2. Kembali ke Menu Utama");
        System.out.println("0. Keluar Aplikasi");
        System.out.print("=>");
        int konfirmasiPembayaran = scanner.nextInt();
        scanner.nextLine();
        if (konfirmasiPembayaran == 1){
            cetakInvoice();
            isBreak = true;
        } else if (konfirmasiPembayaran == 0) {
            isBreak = true;
        }

    }

    public static void cetakInvoice(){
        String filePath = "invoice_pembayaran.txt";
        String textOpenInvoice = "Terima kasih sudah memesan di BinarFud\nDibawah ini adalah pesanan anda";
        String textCloseInvoide = "\nPembayaran : Binar Cash\n" + line + "\n" + "Simpan struk ini sebagai bukti pembayaran" + "\n" + line;
        int totalPesanan = 0;
        int totalHarga = 0;

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(textOpenInvoice);
            bufferedWriter.newLine();
            System.out.println("\n\n" + textOpenInvoice);
            for (Pesanan item:listPesanan){
                bufferedWriter.write(item.getMenuPesanan() + "   " + item.getQty() + "    " + item.getHarga());
                bufferedWriter.newLine();
                System.out.println(item.getMenuPesanan() + "   " + item.getQty() + "    " + item.getHarga());
                totalPesanan += item.getQty();
                totalHarga += item.getHarga();
            }
            bufferedWriter.write("-----------------------------------------+");
            bufferedWriter.newLine();
            System.out.println("-----------------------------------------+\n");
            bufferedWriter.write("Total Pesanan : " + totalPesanan);
            bufferedWriter.newLine();
            bufferedWriter.write("Total: " + totalHarga);
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            System.out.println("Total Pesanan : " + totalPesanan);
            System.out.println("Total Harga   : " + totalHarga + "\n");
            bufferedWriter.write(textCloseInvoide);
            System.out.println(textCloseInvoide);
            bufferedWriter.close();
            isBreak = true;
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
