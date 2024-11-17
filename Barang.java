public class Barang {
    protected String kodeBarang;
    protected String namaBarang;
    protected double hargaBarang;

    public Barang(String kodeBarang, String namaBarang, double hargaBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }

    public String getKode() {
        return kodeBarang;
    }

    public String getNama() {
        return namaBarang;
    }

    public double getHarga() {
        return hargaBarang;
    }
}
