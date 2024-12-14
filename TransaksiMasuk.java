public class TransaksiMasuk extends Transaksi {
    private String kodeBarang;
    private int jumlah;

    public TransaksiMasuk(String idTransaksi, String tanggal, String kodeBarang, int jumlah) {
        super(idTransaksi, tanggal);
        this.kodeBarang = kodeBarang;
        this.jumlah = jumlah;
    }

    @Override
    public void prosesTransaksi() {
        System.out.println("Barang masuk: " + kodeBarang + ", Jumlah: " + jumlah);
    }
}
