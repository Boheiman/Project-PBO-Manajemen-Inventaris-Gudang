/**
 * Kelas untuk barang.
 * Konsep OOP:
 * - Encapsulation: Fields adalah private dan diakses melalui public getters dan setters.
 * - Constructor Overloading: Konstruktor mengambil parameter untuk menginisialisasi fields.
 * - Overriding: Metode toString() di-override untuk representasi objek.
 * - Aggregation: JenisBarang digunakan untuk mendefinisikan tipe barang.
 */

public class Barang {
    private String kodeBarang;
    private String namaBarang;
    private JenisBarang jenisBarang; // New field
    private String kategori;
    private int stok;
    private double harga;
    private String pemasok;

    public Barang(String kodeBarang, String namaBarang, JenisBarang jenisBarang, String kategori, int stok, double harga, String pemasok) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.jenisBarang = jenisBarang; // Initialize new field
        this.kategori = kategori;
        this.stok = stok;
        this.harga = harga;
        this.pemasok = pemasok;
    }

    public String getKodeBarang() { return kodeBarang; }
    public void setKodeBarang(String kodeBarang) { this.kodeBarang = kodeBarang; }

    public String getNamaBarang() { return namaBarang; }
    public void setNamaBarang(String namaBarang) { this.namaBarang = namaBarang; }

    public JenisBarang getJenisBarang() { return jenisBarang; } // Getter for jenisBarang
    public void setJenisBarang(JenisBarang jenisBarang) { this.jenisBarang = jenisBarang; } // Setter for jenisBarang

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public String getPemasok() { return pemasok; }
    public void setPemasok(String pemasok) { this.pemasok = pemasok; }

    @Override
    public String toString() {
        return "Barang [kodeBarang=" + kodeBarang + ", namaBarang=" + namaBarang + ", jenisBarang=" + jenisBarang + ", kategori=" + kategori + ", stok=" + stok + ", harga=" + harga + ", pemasok=" + pemasok + "]";
    }
}
