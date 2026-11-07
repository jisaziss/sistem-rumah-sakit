public class Obat {
    private final String kode;
    private String nama;
    private int stok;
    private double harga;

    public Obat(String kode, String nama, int stok, double harga) {
        this.kode = kode;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public int getStok() { return stok; }
    public double getHarga() { return harga; }
    public void setHarga(double harga) {
        if (harga >= 0) this.harga = harga;
    }

    public boolean kurangiStok(int jumlah) {
        if (jumlah > 0 && stok >= jumlah) {
            stok -= jumlah;
            return true;
        }
        return false;
    }

    public void tampilkanData() {
        System.out.printf("%-8s %-22s stok: %-5d harga: Rp%,.0f%n",
                kode, nama, stok, harga);
    }
}
