public class Kamar {
    private final String nomorKamar;
    private String kelas;
    private double tarifPerHari;
    private boolean tersedia;

    public Kamar(String nomorKamar, String kelas, double tarifPerHari) {
        this.nomorKamar = nomorKamar;
        this.kelas = kelas;
        this.tarifPerHari = tarifPerHari;
        this.tersedia = true;
    }

    public String getNomorKamar() { return nomorKamar; }
    public String getKelas() { return kelas; }
    public double getTarifPerHari() { return tarifPerHari; }
    public boolean isTersedia() { return tersedia; }

    public void setKelas(String kelas) { this.kelas = kelas; }
    public void setTarifPerHari(double tarif) {
        if (tarif >= 0) this.tarifPerHari = tarif;
    }

    public boolean tempati() {
        if (tersedia) {
            tersedia = false;
            return true;
        }
        return false;
    }

    public void kosongkan() {
        tersedia = true;
    }
}
