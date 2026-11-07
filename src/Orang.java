public abstract class Orang {
    private String id;
    private String nama;
    private String alamat;

    public Orang(String id, String nama) {
        this(id, nama, "-");
    }

    public Orang(String id, String nama, String alamat) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void tampilkanIdentitas() {
        System.out.println("ID       : " + id);
        System.out.println("Nama     : " + nama);
        System.out.println("Alamat   : " + alamat);
    }

    public abstract void tampilkanPeran();
}
