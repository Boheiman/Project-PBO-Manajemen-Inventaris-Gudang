public class BarangElektronik {
    private String kodeBarang;
    private String namaBarang;
    private String kategori;
    private int stok;
    private double harga;
    private Pemasok pemasok;

    // Constructor
    public BarangElektronik(String kodeBarang, String namaBarang, String kategori, int stok, double harga, Pemasok pemasok) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.stok = stok;
        this.harga = harga;
        this.pemasok = pemasok;
    }

    // Getters and Setters
    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public Pemasok getPemasok() {
        return pemasok;
    }

    public void setPemasok(Pemasok pemasok) {
        // Hapus barang dari pemasok sebelumnya jika ada
        if (this.pemasok != null) {
            this.pemasok.removeBarang(this);
        }
        this.pemasok = pemasok;
        // Tambahkan barang ke pemasok baru jika tidak null
        if (pemasok != null) {
            pemasok.addBarang(this);
        }
    }

    @Override
    public String toString() {
        return "BarangElektronik [kodeBarang=" + kodeBarang + ", namaBarang=" + namaBarang +
                ", kategori=" + kategori + ", stok=" + stok + ", harga=" + harga +
                ", pemasok=" + (pemasok != null ? pemasok.getNamaPemasok() : "Tidak Ada") + "]";
    }
}
