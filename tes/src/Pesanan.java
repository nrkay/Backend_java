public class Pesanan {
    private String nameFood;
    private int Qty;
    private int subHarga;

    public Pesanan(){}
    public Pesanan(String nameFood, int Qty, int subHarga){
        this.nameFood = nameFood;
        this.Qty = Qty;
        this.subHarga = subHarga;
    }

    public String getNameFood() {
        return nameFood;
    }

    public int getQty() {
        return Qty;
    }

    public int getSubHarga() {
        return subHarga;
    }
}
