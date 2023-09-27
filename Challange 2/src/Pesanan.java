public class Pesanan {

    private String menuPesanan;
    private int qty;
    private int harga;

    public Pesanan(){};
    public Pesanan(String menu, int qty, int subTotal){
        this.menuPesanan = menu;
        this.qty = qty;
        this.harga = subTotal;
    }

    public String getMenuPesanan() {
        return menuPesanan;
    }

    public int getQty() {
        return qty;
    }

    public int getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        return "Pesanan{" +
                "menuPesanan='" + menuPesanan + '\'' +
                ", qty=" + qty +
                ", harga=" + harga +
                '}';
    }
}
