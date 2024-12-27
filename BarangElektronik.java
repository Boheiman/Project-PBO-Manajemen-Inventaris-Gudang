/**
 * Kelas untuk barang elektronik yang mewarisi dari kelas Barang.
 * Konsep OOP:
 * - Inheritance: Kelas ini mewarisi dari kelas Barang.
 * - Overriding: Metode toString() di-override untuk representasi spesifik.
 * - Polymorphism: Kelas ini dapat digunakan sebagai tipe Barang.
 */
public class BarangElektronik extends Barang {
    public BarangElektronik(String kodeBarang, String namaBarang, String kategori, int stok, double harga, String pemasok) {
        super(kodeBarang, namaBarang, JenisBarang.ELECTRONIC, kategori, stok, harga, pemasok); // Set jenisBarang to ELECTRONIC
    }

    @Override
    public String toString() {
        return super.toString() + ", BarangElektronik";
    }
}
