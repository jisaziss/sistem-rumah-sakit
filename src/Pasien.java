public class Pasien extends Orang {
    private final String nomorRM;
    private int umur;
    private String jenisKelamin;
    private String status;

    public Pasien(String nomorRM, String nama, int umur) {
        this(nomorRM, nama, umur, "-", "-");
    }

    public Pasien(String nomorRM, String nama, int umur,
                  String jenisKelamin, String alamat) {
        super(nomorRM, nama, alamat);
        this.nomorRM = nomorRM;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
        this.status = "TERDAFTAR";
    }

    public String getNomorRM() {
        return nomorRM;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        if (umur >= 0) {
            this.umur = umur;
        }
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void tampilkanPeran() {
        System.out.println("Peran: Pasien");
    }

    public void tampilkanData() {
        tampilkanIdentitas();
        System.out.println("No. RM   : " + nomorRM);
        System.out.println("Umur     : " + umur);
        System.out.println("Jenis    : " + jenisKelamin);
        System.out.println("Status   : " + status);
    }
}
