public class Transaksi extends Barang {
    private int jumlahBeli;
    private int total;

    public Transaksi(String kodeBarang, String namaBarang, int hargaBarang, int jumlahBeli) {
        super(kodeBarang, namaBarang, hargaBarang);
        this.jumlahBeli = jumlahBeli;
        this.total = hargaBarang * jumlahBeli;
    }

    public int getjumlahBeli() {
        return jumlahBeli;
    }

    public int getTotal() {
        return total;
    }
}