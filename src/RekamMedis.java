import java.util.ArrayList;

public class RekamMedis {
    private final Pasien pasien;
    private final ArrayList<Pemeriksaan> riwayat;

    public RekamMedis(Pasien pasien) {
        this.pasien = pasien;
        this.riwayat = new ArrayList<>();
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void tambahRiwayat(Pemeriksaan pemeriksaan) {
        riwayat.add(pemeriksaan);
    }

    public void tampilkanRiwayat() {
        System.out.println("Riwayat medis: " + pasien.getNama());
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada riwayat pemeriksaan.");
            return;
        }
        for (Pemeriksaan p : riwayat) {
            System.out.println("------------------------------");
            p.tampilkanDetail();
        }
    }
}
