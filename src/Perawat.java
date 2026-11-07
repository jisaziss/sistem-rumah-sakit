public class Perawat extends Pegawai {
    private String unitKerja;

    public Perawat(String id, String nama, String unitKerja) {
        super(id, nama, "Keperawatan");
        this.unitKerja = unitKerja;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }

    @Override
    public void tampilkanPeran() {
        System.out.println("Peran: Perawat");
    }

    @Override
    public void bekerja() {
        System.out.println("Perawat " + getNama() + " membantu pelayanan di " + unitKerja + ".");
    }

    public void bantuPemeriksaan() {
        System.out.println("Perawat " + getNama() + " membantu pemeriksaan pasien.");
    }
}
