import java.util.ArrayList;
import java.util.List;

public class Pemasok {
    private String idPemasok;
    private String namaPemasok;
    private String kontak;
    private List<BarangElektronik> daftarBarang = new ArrayList<>();

    public Pemasok(String idPemasok, String namaPemasok, String kontak) {
        this.idPemasok = idPemasok;
        this.namaPemasok = namaPemasok;
        this.kontak = kontak;
    }

    public void addBarang(BarangElektronik barang1, BarangElektronik barang2) {
        addBarang(barang1);
        addBarang(barang2);
        System.out.println("Menambahkan 2 barang sekaligus ke pemasok: " + this.namaPemasok);
    }

    public String getIdPemasok() {
        return idPemasok;
    }

    public String getNamaPemasok() {
        return namaPemasok;
    }

    public void setNamaPemasok(String namaPemasok) {
        this.namaPemasok = namaPemasok;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public void addBarang(BarangElektronik barang) {
        if (!daftarBarang.contains(barang)) {
            daftarBarang.add(barang);
            barang.setPemasok(this);
        }
    }

    public void removeBarang(BarangElektronik barang) {
        if (daftarBarang.remove(barang)) {
            barang.setPemasok(null);
        }
    }

    @Override
    public String toString() {
        return "Pemasok [idPemasok=" + idPemasok + ", namaPemasok=" + namaPemasok +
                ", kontak=" + kontak + ", daftarBarang=" + daftarBarang + "]";
    }
}
