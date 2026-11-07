import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final RumahSakitService rs = new RumahSakitService();

    public static void main(String[] args) {
        int pilihan;
        do {
            tampilkanMenuUtama();
            pilihan = bacaInt("Pilih menu: ");
            switch (pilihan) {
                case 1: layananBerobat(); break;
                case 2: menuPasien(); break;
                case 3: menuRawatInap(); break;
                case 4: menuInformasi(); break;
                case 5: rs.tampilkanLaporan(); break;
                case 6: ujiSepuluhRequirement();break;
                case 0: System.out.println("Terima kasih."); break;
                default: System.out.println("Pilihan tidak tersedia.");
            }
            if (pilihan != 0) tekanEnter();
        } while (pilihan != 0);
    }

    private static void tampilkanMenuUtama() {
        System.out.println("\n==========================================");
        System.out.println("       " + Konfigurasi.NAMA_RUMAH_SAKIT);
        System.out.println("==========================================");
        System.out.println("1. Layanan Berobat (Daftar-Periksa-Bayar)");
        System.out.println("2. Manajemen Pasien");
        System.out.println("3. Rawat Inap");
        System.out.println("4. Informasi RS");
        System.out.println("5. Laporan");
        System.out.println("6. UjiSepuluh requirement");
        System.out.println("0. Keluar");
    }

    private static void layananBerobat() {
        System.out.println("\n=== PENDAFTARAN ===");
        String rm = bacaString("Nomor rekam medis: ");
        Pasien pasien = rs.cariPasien(rm);
        if (pasien == null) {
            String nama = bacaString("Nama: ");
            int umur = bacaInt("Umur: ");
            int jkChoice = bacaInt("Jenis kelamin (1. Laki-laki 2. Perempuan): ");
            String alamat = bacaString("Alamat: ");
            pasien = new Pasien(rm, nama, umur, jkChoice == 1 ? "Laki-laki" : "Perempuan", alamat);
            rs.tambahPasien(pasien);
            System.out.println("Pasien baru didaftarkan.");
        } else {
            System.out.println("Pasien lama ditemukan: " + pasien.getNama());
        }

        Tagihan tagihan = rs.buatTagihan(pasien);
        System.out.println("\nPilih Dokter:");
        ArrayList<Dokter> dokterList = rs.getDaftarDokter();
        for (int i = 0; i < dokterList.size(); i++) {
            System.out.printf("%d. %s (%s) - Biaya: Rp%,.0f%n", i + 1, dokterList.get(i).getNama(), dokterList.get(i).getSpesialisasi(), dokterList.get(i).getBiayaKonsultasi());
        }
        int dIdx = bacaInt("Pilih dokter (1-" + dokterList.size() + "): ");
        Dokter dokter = dokterList.get(dIdx - 1);

        System.out.println("\n=== PEMERIKSAAN ===");
        String keluhan = bacaString("Keluhan pasien: ");
        
        System.out.println("Pilih Diagnosis:");
        for(int i = 0; i < Konfigurasi.DIAGNOSIS.length; i++) {
            System.out.println((i+1) + ". " + Konfigurasi.DIAGNOSIS[i]);
        }
        String diagnosis = Konfigurasi.DIAGNOSIS[bacaInt("Diagnosis (no): ") - 1];

        System.out.println("Pilih Tindakan:");
        for(int i = 0; i < Konfigurasi.TINDAKAN.length; i++) {
            System.out.println((i+1) + ". " + Konfigurasi.TINDAKAN[i]);
        }
        String tindakan = Konfigurasi.TINDAKAN[bacaInt("Tindakan (no): ") - 1];

        pasien.setStatus("MENUNGGU_PEMERIKSAAN");
        Pemeriksa pemeriksa = dokter;
        pemeriksa.periksa(pasien, keluhan, diagnosis, tindakan);
        
        Pemeriksaan pm = new Pemeriksaan("PM-" + System.currentTimeMillis() % 1000, pasien, dokter, keluhan, diagnosis, tindakan);
        rs.getDaftarPemeriksaan().add(pm);
        rs.cariRekamMedis(pasien).tambahRiwayat(pm);
        tagihan.tambahBiaya(dokter.getBiayaKonsultasi());

        if (bacaString("Berikan resep obat? (y/n): ").equalsIgnoreCase("y")) {
            Resep resep = dokter.buatResep(pasien);
            ArrayList<Obat> daftarObat = rs.getDaftarObat();
            while (true) {
                System.out.println("\nDaftar Obat:");
                for (int i = 0; i < daftarObat.size(); i++) {
                    System.out.print((i + 1) + ". ");
                    daftarObat.get(i).tampilkanData();
                }
                int oi = bacaInt("Pilih obat (1-" + daftarObat.size() + ", 0 jika selesai): ");
                if (oi == 0) break;
                int jml = bacaInt("Jumlah: ");
                resep.tambahObat(daftarObat.get(oi - 1), jml);
            }
            if (resep.prosesResep()) {
                rs.getDaftarResep().add(resep);
                tagihan.tambahBiaya(resep.hitungTotalObat());
                System.out.println("Obat ditambahkan ke tagihan.");
            }
        }

        System.out.println("\n=== PEMBAYARAN ===");
        tagihan.tampilkanTagihan();
        int met = bacaInt("Metode Pembayaran (1. Tunai 2. Transfer): ");
        double bayarNominal = bacaDouble("Jumlah uang: ");
        Pembayaran bayar = new Pembayaran("B-" + rm, met == 1 ? "Tunai" : "Transfer", bayarNominal);
        bayar.proses(tagihan);
    }

    private static void menuPasien() {
        System.out.println("\n=== DATA PASIEN ===");
        if (rs.getDaftarPasien().isEmpty()) {
            System.out.println("Belum ada pasien terdaftar.");
            return;
        }
        for (Pasien p : rs.getDaftarPasien()) {
            p.tampilkanData();
            System.out.println("------------------------------");
        }
        String rm = bacaString("Masukkan Nomor RM untuk lihat riwayat (atau 0 untuk kembali): ");
        if (!rm.equals("0")) {
            Pasien p = rs.cariPasien(rm);
            if (p != null) rs.cariRekamMedis(p).tampilkanRiwayat();
            else System.out.println("Pasien tidak ditemukan.");
        }
    }

    private static void menuRawatInap() {
        System.out.println("\n=== MANAJEMEN RAWAT INAP ===");
        System.out.println("1. Pendaftaran Rawat Inap");
        System.out.println("2. Check-out Pasien");
        System.out.println("3. Daftar Kamar");
        int pilih = bacaInt("Pilih: ");
        switch (pilih) {
            case 1:
                String rm = bacaString("Nomor RM pasien: ");
                Pasien p = rs.cariPasien(rm);
                if (p == null) { System.out.println("Pasien tidak ditemukan."); break; }
                tampilkanDaftarKamar();
                String noK = bacaString("Pilih Nomor Kamar: ");
                Kamar k = rs.cariKamar(noK);
                if (k == null || !k.isTersedia()) { System.out.println("Kamar tidak ada atau penuh."); break; }
                int hari = bacaInt("Lama inap (hari): ");
                if (k.tempati()) {
                    RawatInap ri = new RawatInap(p, k, hari);
                    rs.getDaftarRawatInap().add(ri);
                    rs.buatTagihan(p).tambahBiaya(ri.hitungBiayaKamar());
                    System.out.println("Rawat inap berhasil dicatat.");
                }
                break;
            case 2:
                String rmc = bacaString("Nomor RM pasien: ");
                RawatInap target = null;
                for (RawatInap ri : rs.getDaftarRawatInap()) {
                    if (ri.getPasien().getNomorRM().equalsIgnoreCase(rmc) && !ri.getKamar().isTersedia()) {
                        target = ri;
                        break;
                    }
                }
                if (target != null) {
                    target.pulangkanPasien();
                    System.out.println("Pasien " + target.getPasien().getNama() + " telah check-out.");
                } else {
                    System.out.println("Data rawat inap tidak ditemukan.");
                }
                break;
            case 3:
                tampilkanDaftarKamar();
                break;
        }
    }

    private static void tampilkanDaftarKamar() {
        System.out.println("\n=== DAFTAR KAMAR ===");
        for (Kamar k : rs.getDaftarKamar()) {
            System.out.printf("No: %s | Kelas: %-10s | Tarif: Rp%,.0f | Status: %s%n",
                    k.getNomorKamar(), k.getKelas(), k.getTarifPerHari(), k.isTersedia() ? "Tersedia" : "Terisi");
        }
    }

    private static void menuInformasi() {
        System.out.println("\n=== INFORMASI TENAGA MEDIS ===");
        System.out.println("--- DOKTER ---");
        for (Dokter d : rs.getDaftarDokter()) {
            d.tampilkanIdentitas();
            d.bekerja();
            System.out.println("------------------------------");
        }
        System.out.println("--- PERAWAT ---");
        for (Perawat per : rs.getDaftarPerawat()) {
            per.tampilkanIdentitas();
            per.bekerja();
            System.out.println("------------------------------");
        }
    }

    private static void ujiSepuluhRequirement() {
        System.out.println("=== PENGUJIAN REQUIREMENT PBO ===");
        System.out.println("[1] Static Final: " + Konfigurasi.NAMA_RUMAH_SAKIT);
        Pasien pUji = new Pasien("TEST", "Uji", 20);
        System.out.println("[2] Atribut Final: " + pUji.getNomorRM());
        pUji.setUmur(-1); pUji.setUmur(25);
        System.out.println("[3] Enkapsulasi: umur=" + pUji.getUmur());
        Dokter dUji = new Dokter("DU", "Dr. Uji", "Umum");
        System.out.println("[4] Inheritance & [5] Polymorphism:");
        Orang[] oArr = { dUji, pUji, new Perawat("PU", "Per. Uji", "IGD") };
        for (Orang o : oArr) o.tampilkanPeran();
        System.out.println("[6] Interface Pemeriksa:");
        Pemeriksa pem = dUji;
        pUji.setStatus("MENUNGGU_PEMERIKSAAN");
        pem.periksa(pUji, "Keluhan", "Diagnosis", "Tindakan");
        System.out.println("[7] Multiple Interface (Dokter): Pemeriksa, PemberiResep.");
        System.out.println("[8] Constructor Overloading: Pasien(\"ID\", \"Nama\", 20) chain to full constructor.");
        System.out.println("[9] Abstraksi: class Orang tidak bisa di-instantiate.");
        System.out.println("[10] Collections: rs.getDaftarPasien() menggunakan ArrayList.");
        System.out.println("=== PENGUJIAN SELESAI ===\n");
    }

    private static String bacaString(String pesan) {
        System.out.print(pesan);
        String line = input.nextLine();
        return line == null ? "" : line.trim();
    }

    private static int bacaInt(String pesan) {
        while (true) {
            try {
                String s = bacaString(pesan);
                if (s.isEmpty()) continue;
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka.");
            }
        }
    }

    private static double bacaDouble(String pesan) {
        while (true) {
            try {
                String s = bacaString(pesan);
                if (s.isEmpty()) continue;
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka.");
            }
        }
    }

    private static void tekanEnter() {
        System.out.print("Tekan Enter untuk kembali...");
        input.nextLine();
    }
}
