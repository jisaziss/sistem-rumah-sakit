public class Pembayaran {
    private final String idPembayaran;
    private final String metode;
    private final double jumlahBayar;

    public Pembayaran(String idPembayaran, String metode, double jumlahBayar) {
        this.idPembayaran = idPembayaran;
        this.metode = metode;
        this.jumlahBayar = jumlahBayar;
    }

    public String getIdPembayaran() { return idPembayaran; }
    public String getMetode() { return metode; }
    public double getJumlahBayar() { return jumlahBayar; }

    public boolean proses(Tagihan tagihan) {
        if (tagihan.getStatusPembayaran().equals("LUNAS")) {
            System.out.println("Tagihan ini sudah lunas.");
            return false;
        }
        if (jumlahBayar >= tagihan.getTotalBiaya()) {
            tagihan.tandaiLunas();
            System.out.println("Pembayaran berhasil. Kembalian: Rp"
                    + (jumlahBayar - tagihan.getTotalBiaya()));
            return true;
        }
        System.out.println("Pembayaran gagal: jumlah uang kurang.");
        return false;
    }
}
