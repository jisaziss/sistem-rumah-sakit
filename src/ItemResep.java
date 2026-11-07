public class ItemResep {
    private final Obat obat;
    private final int jumlah;

    public ItemResep(Obat obat, int jumlah) {
        this.obat = obat;
        this.jumlah = jumlah;
    }

    public Obat getObat() { return obat; }
    public int getJumlah() { return jumlah; }
    public double hitungSubtotal() { return obat.getHarga() * jumlah; }
}
