public class Admin extends Pegawai {
    public Admin(String id, String nama) {
        super(id, nama, "Administrasi");
    }

    @Override
    public void tampilkanPeran() {
        System.out.println("Peran: Admin");
    }

    @Override
    public void bekerja() {
        System.out.println("Admin " + getNama() + " mengelola administrasi rumah sakit.");
    }
}
