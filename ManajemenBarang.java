import java.util.ArrayList;

public interface ManajemenBarang {
    void tambahBarang(Barang barang);
    void perbaruiBarang(String kodeBarang, Barang barangBaru);
    void hapusBarang(String kodeBarang);
    ArrayList<Barang> daftarBarang();
}
