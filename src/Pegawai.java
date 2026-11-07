public abstract class Pegawai extends Orang {
    private String spesialisasi;
    private final String idPegawai;

    public Pegawai(String idPegawai, String nama, String spesialisasi) {
        super(idPegawai, nama);
        this.idPegawai = idPegawai;
        this.spesialisasi = spesialisasi;
    }

    public String getIdPegawai() {
        return idPegawai;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }

    public abstract void bekerja();
}
