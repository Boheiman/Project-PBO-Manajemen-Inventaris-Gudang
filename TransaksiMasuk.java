/**
 * Kelas untuk transaksi masuk yang mewarisi dari kelas Transaksi.
 * Konsep OOP:
 * - Inheritance: Kelas ini mewarisi dari kelas Transaksi.
 * - Overriding: Metode prosesTransaksi() di-override untuk implementasi spesifik.
 * - Polymorphism: Kelas ini dapat digunakan sebagai tipe Transaksi.
 */
public class TransaksiMasuk extends Transaksi {
    private String kodeBarang;
    private int jumlah;

    public TransaksiMasuk(String idTransaksi, String tanggal, String kodeBarang, int jumlah) {
        super(idTransaksi, tanggal);
        this.kodeBarang = kodeBarang;
        this.jumlah = jumlah;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    @Override
    public void prosesTransaksi() {
        System.out.println("Barang masuk: " + kodeBarang + ", Jumlah: " + jumlah);
    }
}
