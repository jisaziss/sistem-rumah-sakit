public class Dokter extends Pegawai implements Pemeriksa, PemberiResep {
    private double biayaKonsultasi;

    public Dokter(String id, String nama, String spesialisasi) {

        this(id, nama, spesialisasi, 75000.0);
    }

    public Dokter(String id, String nama, String spesialisasi, double biayaKonsultasi) {
        super(id, nama, spesialisasi);
        this.biayaKonsultasi = biayaKonsultasi;
    }

    public double getBiayaKonsultasi() {

        return biayaKonsultasi;
    }

    public void setBiayaKonsultasi(double biayaKonsultasi) {
        if (biayaKonsultasi >= 0) {
            this.biayaKonsultasi = biayaKonsultasi;
        }
    }

    @Override
    public void tampilkanIdentitas() {
        super.tampilkanIdentitas();
        System.out.println("Spesialis: " + getSpesialisasi());
    }

    @Override
    public void tampilkanPeran() {
        System.out.println("Peran: Dokter");
    }

    @Override
    public void bekerja() {
        System.out.println("Dokter " + getNama() + " sedang bertugas.");
    }

    @Override
    public void periksa(Pasien pasien, String keluhan, String diagnosis, String tindakan) {
        if (pasien.getStatus().equals("MENUNGGU_PEMERIKSAAN")) {
            pasien.setStatus("SELESAI_DIPERIKSA");
            System.out.println("Pemeriksaan pasien " + pasien.getNama() + " selesai.");
            System.out.println("Diagnosis: " + diagnosis);
        } else {
            System.out.println("Pemeriksaan gagal: status pasien tidak valid.");
        }
    }

    @Override
    public Resep buatResep(Pasien pasien) {
        return new Resep("R-" + pasien.getNomorRM(), pasien, this);
    }
}
