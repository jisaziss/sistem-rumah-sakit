import java.util.ArrayList;

public class Resep {
    private final String nomorResep;
    private final Pasien pasien;
    private final Dokter dokter;
    private final ArrayList<ItemResep> itemObat;
    private boolean sudahDiproses;

    public Resep(String nomorResep, Pasien pasien, Dokter dokter) {
        this.nomorResep = nomorResep;
        this.pasien = pasien;
        this.dokter = dokter;
        this.itemObat = new ArrayList<>();
        this.sudahDiproses = false;
    }

    public String getNomorResep() { return nomorResep; }
    public Pasien getPasien() { return pasien; }
    public ArrayList<ItemResep> getItemObat() { return itemObat; }
    public boolean isSudahDiproses() { return sudahDiproses; }

    public void tambahObat(Obat obat, int jumlah) {
        if (obat != null && jumlah > 0) {
            itemObat.add(new ItemResep(obat, jumlah));
        }
    }

    public double hitungTotalObat() {
        double total = 0;
        for (ItemResep item : itemObat) {
            total += item.hitungSubtotal();
        }
        return total;
    }

    public boolean prosesResep() {
        if (sudahDiproses) {
            System.out.println("Resep ini sudah pernah diproses.");
            return false;
        }
        if (itemObat.isEmpty()) {
            System.out.println("Resep belum memiliki obat.");
            return false;
        }
        for (ItemResep item : itemObat) {
            if (item.getObat().getStok() < item.getJumlah()) {
                System.out.println("Stok " + item.getObat().getNama() + " tidak mencukupi.");
                return false;
            }
        }
        for (ItemResep item : itemObat) {
            item.getObat().kurangiStok(item.getJumlah());
        }
        sudahDiproses = true;
        System.out.println("Resep berhasil diproses oleh farmasi.");
        return true;
    }

    public void tampilkanResep() {
        System.out.println("Nomor resep: " + nomorResep);
        System.out.println("Pasien     : " + pasien.getNama());
        System.out.println("Dokter     : " + dokter.getNama());
        for (ItemResep item : itemObat) {
            System.out.println("- " + item.getObat().getNama() + " x " + item.getJumlah()
                    + " = Rp" + item.hitungSubtotal());
        }
        System.out.println("Total obat : Rp" + hitungTotalObat());
    }
}
