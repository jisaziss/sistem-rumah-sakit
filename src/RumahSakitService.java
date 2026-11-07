import java.util.ArrayList;

public class RumahSakitService {
    private final ArrayList<Pasien> daftarPasien = new ArrayList<>();
    private final ArrayList<Dokter> daftarDokter = new ArrayList<>();
    private final ArrayList<Perawat> daftarPerawat = new ArrayList<>();
    private final ArrayList<Obat> daftarObat = new ArrayList<>();
    private final ArrayList<Kamar> daftarKamar = new ArrayList<>();
    private final ArrayList<Pemeriksaan> daftarPemeriksaan = new ArrayList<>();
    private final ArrayList<RekamMedis> daftarRekamMedis = new ArrayList<>();
    private final ArrayList<Resep> daftarResep = new ArrayList<>();
    private final ArrayList<RawatInap> daftarRawatInap = new ArrayList<>();
    private final ArrayList<Tagihan> daftarTagihan = new ArrayList<>();

    public RumahSakitService() {
        isiDataAwal();
    }

    private void isiDataAwal() {
        daftarDokter.add(new Dokter("D001", "Andi Pratama", "Umum", 75000));
        daftarDokter.add(new Dokter("D002", "Siti Rahma", "Anak", 100000));
        daftarDokter.add(new Dokter("D003", "Bima Saputra", "Penyakit Dalam", 125000));
        daftarDokter.add(new Dokter("D004", "Dimas Suki", "Penyakit Jantung", 150000));
        daftarDokter.add(new Dokter("D005", "Nadine Wijaya", "Kulit dan Kelamin", 180000));
        daftarDokter.add(new Dokter("D006", "Hana Alisa", " Gigi dan mulut", 200000));

        daftarPerawat.add(new Perawat("P001", "Rina", "Poli Umum"));
        daftarPerawat.add(new Perawat("P002", "Dewi", "Rawat Inap"));
        daftarPerawat.add(new Perawat("P003", "Hilmi Gunawan", "Rawat Inap"));

        daftarObat.add(new Obat("O001", "Paracetamol", 100, 2000));
        daftarObat.add(new Obat("O002", "Vitamin C", 60, 3000));
        daftarObat.add(new Obat("O003", "Amoxicillin", 40, 5000));
        daftarObat.add(new Obat("O004", "Antasida", 50, 2500));
        daftarObat.add(new Obat("O005", "Metmorfin", 30, 4000));
        daftarObat.add(new Obat("O006", "anti biotik", 100, 2000));
        daftarObat.add(new Obat("O007", "Ope ope no mi", 1, 1000000));




        daftarKamar.add(new Kamar("A101", "Kelas 1", 350000));
        daftarKamar.add(new Kamar("A102", "Kelas 1", 350000));
        daftarKamar.add(new Kamar("B201", "Kelas 2", 250000));
        daftarKamar.add(new Kamar("C301", "Kelas 3", 150000));
    }

    public ArrayList<Pasien> getDaftarPasien() { return daftarPasien; }
    public ArrayList<Dokter> getDaftarDokter() { return daftarDokter; }
    public ArrayList<Perawat> getDaftarPerawat() { return daftarPerawat; }
    public ArrayList<Obat> getDaftarObat() { return daftarObat; }
    public ArrayList<Kamar> getDaftarKamar() { return daftarKamar; }
    public ArrayList<Pemeriksaan> getDaftarPemeriksaan() { return daftarPemeriksaan; }
    public ArrayList<Resep> getDaftarResep() { return daftarResep; }
    public ArrayList<RawatInap> getDaftarRawatInap() { return daftarRawatInap; }
    public ArrayList<Tagihan> getDaftarTagihan() { return daftarTagihan; }

    public Pasien cariPasien(String nomorRM) {
        for (Pasien p : daftarPasien) {
            if (p.getNomorRM().equalsIgnoreCase(nomorRM)) return p;
        }
        return null;
    }

    public Obat cariObat(String kode) {
        for (Obat o : daftarObat) {
            if (o.getKode().equalsIgnoreCase(kode)) return o;
        }
        return null;
    }

    public Kamar cariKamar(String nomor) {
        for (Kamar k : daftarKamar) {
            if (k.getNomorKamar().equalsIgnoreCase(nomor)) return k;
        }
        return null;
    }

    public Tagihan cariTagihan(String nomor) {
        for (Tagihan t : daftarTagihan) {
            if (t.getNomorTagihan().equalsIgnoreCase(nomor)) return t;
        }
        return null;
    }

    public boolean tambahPasien(Pasien pasien) {
        if (cariPasien(pasien.getNomorRM()) != null) return false;
        daftarPasien.add(pasien);
        daftarRekamMedis.add(new RekamMedis(pasien));
        return true;
    }

    public RekamMedis cariRekamMedis(Pasien pasien) {
        for (RekamMedis rm : daftarRekamMedis) {
            if (rm.getPasien() == pasien) return rm;
        }
        return null;
    }

    public Tagihan buatTagihan(Pasien pasien) {
        Tagihan tagihan = null;
        for (Tagihan t : daftarTagihan) {
            if (t.getPasien() == pasien && t.getStatusPembayaran().equals("BELUM LUNAS")) {
                tagihan = t;
                break;
            }
        }
        if (tagihan == null) {
            tagihan = new Tagihan("T-" + pasien.getNomorRM(), pasien);
            tagihan.tambahBiaya(Konfigurasi.BIAYA_PENDAFTARAN);
            daftarTagihan.add(tagihan);
        }
        return tagihan;
    }

    public void tampilkanLaporan() {
        System.out.println("\n=== RINGKASAN RUMAH SAKIT ===");
        System.out.println("Jumlah pasien       : " + daftarPasien.size());
        System.out.println("Jumlah dokter       : " + daftarDokter.size());
        System.out.println("Jumlah perawat      : " + daftarPerawat.size());
        System.out.println("Jumlah pemeriksaan  : " + daftarPemeriksaan.size());
        System.out.println("Jumlah resep        : " + daftarResep.size());
        System.out.println("Jumlah rawat inap   : " + daftarRawatInap.size());
        System.out.println("Jumlah tagihan      : " + daftarTagihan.size());
    }
}
