public class LaporanStok {
    public void tampilkanLaporanStok(ManajemenBarangImpl manajemenBarang) {
        for (Barang barang : manajemenBarang.daftarBarang()) {
            System.out.println("Kode: " + barang.getKodeBarang() + ", Nama: " + barang.getNamaBarang() + ", Stok: " + barang.getStok());
        }
    }
}
