public class Tagihan {
    private final String nomorTagihan;
    private final Pasien pasien;
    private double totalBiaya;
    private String statusPembayaran;

    public Tagihan(String nomorTagihan, Pasien pasien) {
        this.nomorTagihan = nomorTagihan;
        this.pasien = pasien;
        this.totalBiaya = 0;
        this.statusPembayaran = "BELUM LUNAS";
    }

    public String getNomorTagihan() { return nomorTagihan; }
    public Pasien getPasien() { return pasien; }
    public double getTotalBiaya() { return totalBiaya; }
    public String getStatusPembayaran() { return statusPembayaran; }

    public void tambahBiaya(double biaya) {
        if (biaya > 0) totalBiaya += biaya;
    }

    public void tandaiLunas() {
        statusPembayaran = "LUNAS";
    }

    public void tampilkanTagihan() {
        System.out.println("Nomor tagihan : " + nomorTagihan);
        System.out.println("Pasien        : " + pasien.getNama());
        System.out.printf("Total biaya   : Rp%,.0f%n", totalBiaya);
        System.out.println("Status        : " + statusPembayaran);
    }
}
