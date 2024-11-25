public class Barang {
    protected String kodeBarang;
    protected String namaBarang;
    protected int hargaBarang;

    public Barang(String kodeBarang, String namaBarang, int hargaBarang) {
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

    public int getHarga() {
        return hargaBarang;
    }
}
