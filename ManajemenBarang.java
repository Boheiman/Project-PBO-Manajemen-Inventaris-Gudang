import java.util.ArrayList;

public interface ManajemenBarang {
    void tambahBarang(BarangElektronik barang);
    void perbaruiBarang(String kodeBarang, BarangElektronik barangBaru);
    void hapusBarang(String kodeBarang);
    ArrayList<BarangElektronik> daftarBarang();
    BarangElektronik getBarangByKode(String kodeBarang);
    void barangMasuk(String kodeBarang, int jumlah);
    void barangKeluar(String kodeBarang, int jumlah);
}
