public class Pemeriksaan {
    private final String idPemeriksaan;
    private final Pasien pasien;
    private final Dokter dokter;
    private String keluhan;
    private String diagnosis;
    private String tindakan;

    public Pemeriksaan(String idPemeriksaan, Pasien pasien, Dokter dokter,
                       String keluhan, String diagnosis, String tindakan) {
        this.idPemeriksaan = idPemeriksaan;
        this.pasien = pasien;
        this.dokter = dokter;
        this.keluhan = keluhan;
        this.diagnosis = diagnosis;
        this.tindakan = tindakan;
    }

    public String getIdPemeriksaan() { return idPemeriksaan; }
    public Pasien getPasien() { return pasien; }
    public Dokter getDokter() { return dokter; }
    public String getKeluhan() { return keluhan; }
    public String getDiagnosis() { return diagnosis; }
    public String getTindakan() { return tindakan; }

    public void tampilkanDetail() {
        System.out.println("ID Pemeriksaan : " + idPemeriksaan);
        System.out.println("Pasien         : " + pasien.getNama());
        System.out.println("Dokter         : " + dokter.getNama());
        System.out.println("Keluhan        : " + keluhan);
        System.out.println("Diagnosis      : " + diagnosis);
        System.out.println("Tindakan       : " + tindakan);
    }
}
