import java.util.List; // Importing List

public class LaporanStok {
    public void tampilkanLaporanStok(ManajemenBarangImpl manajemenBarang, List<TransaksiMasuk> transaksiMasuk, List<TransaksiKeluar> transaksiKeluar) {
        System.out.println("Laporan Stok:");
        for (Barang barang : manajemenBarang.daftarBarang()) {
            System.out.println("Kode: " + barang.getKodeBarang() + ", Nama: " + barang.getNamaBarang() + ", Stok: " + barang.getStok());
        }

        System.out.println("\nTransaksi Masuk:");
        for (TransaksiMasuk transaksi : transaksiMasuk) {
            System.out.println("ID: " + transaksi.getIdTransaksi() + ", Tanggal: " + transaksi.getTanggal() + ", Kode Barang: " + transaksi.getKodeBarang() + ", Jumlah: " + transaksi.getJumlah());
        }

        System.out.println("\nTransaksi Keluar:");
        for (TransaksiKeluar transaksi : transaksiKeluar) {
            System.out.println("ID: " + transaksi.getIdTransaksi() + ", Tanggal: " + transaksi.getTanggal() + ", Kode Barang: " + transaksi.getKodeBarang() + ", Jumlah: " + transaksi.getJumlah());
        }
    }
}
