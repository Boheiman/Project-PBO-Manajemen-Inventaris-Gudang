/**
 * Kelas untuk barang makanan yang mewarisi dari kelas Barang.
 * Konsep OOP:
 * - Inheritance: Kelas ini mewarisi dari kelas Barang.
 * - Encapsulation: Field tanggalKadaluarsa adalah private, diakses melalui public getters dan setters.
 * - Constructor Overloading: Konstruktor mengambil parameter untuk menginisialisasi fields.
 * - Overriding: Metode toString() di-override untuk representasi spesifik.
 * - Polymorphism: Kelas ini dapat digunakan sebagai tipe Barang.
 */
public class BarangMakanan extends Barang {
    private String tanggalKadaluarsa;

    public BarangMakanan(String kodeBarang, String namaBarang, String kategori, int stok, double harga, String pemasok, String tanggalKadaluarsa) {
        super(kodeBarang, namaBarang, JenisBarang.FOOD, kategori, stok, harga, pemasok); // Set jenisBarang to FOOD
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    public String getTanggalKadaluarsa() { return tanggalKadaluarsa; }
    public void setTanggalKadaluarsa(String tanggalKadaluarsa) { this.tanggalKadaluarsa = tanggalKadaluarsa; }

    @Override
    public String toString() {
        return super.toString() + ", BarangMakanan [tanggalKadaluarsa=" + tanggalKadaluarsa + "]";
    }
}
