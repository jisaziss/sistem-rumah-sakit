public final class Konfigurasi {
    public static final String NAMA_RUMAH_SAKIT = "RS SANTIAGO BERNABEU";
    public static final double BIAYA_PENDAFTARAN = 25000.0;

    public static final String[] DIAGNOSIS = {
        "Demam Tinggi", "Flu/Batuk", "Sakit Kepala Berulang", "Gastritis/Lambung", 
        "Cedera Otot Ringan", "Hipertensi", "Kelelahan Kronis", "Diabetes", "Gigi berlubang", "Jantung", "Alergi"
    };

    public static final String[] TINDAKAN = {
        "Periksa Pasien", "Terapi Fisik",
        "Rujukan Spesialis Lanjut", "Rujukan Rawat Inap"
    };

    private Konfigurasi() {
        // Mencegah objek Konfigurasi dibuat.
    }
}
