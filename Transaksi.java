public class Transaksi extends Barang {
    private int jumlahBeli;
    private double total;

    public Transaksi(String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli) {
        super(kodeBarang, namaBarang, hargaBarang);
        this.jumlahBeli = jumlahBeli;
        this.total = hargaBarang * jumlahBeli;
    }

    public int getjumlahBeli() {
        return jumlahBeli;
    }

    public double getTotal() {
        return total;
    }
}