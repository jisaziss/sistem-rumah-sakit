public class RawatInap {
    private final Pasien pasien;
    private final Kamar kamar;
    private int lamaHari;

    public RawatInap(Pasien pasien, Kamar kamar, int lamaHari) {
        this.pasien = pasien;
        this.kamar = kamar;
        this.lamaHari = lamaHari;
        pasien.setStatus("RAWAT_INAP");
    }

    public Pasien getPasien() { return pasien; }
    public Kamar getKamar() { return kamar; }
    public int getLamaHari() { return lamaHari; }

    public void setLamaHari(int lamaHari) {
        if (lamaHari > 0) this.lamaHari = lamaHari;
    }

    public double hitungBiayaKamar() {
        return kamar.getTarifPerHari() * lamaHari;
    }

    public void pulangkanPasien() {
        kamar.kosongkan();
        pasien.setStatus("SELESAI");
    }
}
