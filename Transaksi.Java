/**
 * Kelas abstrak untuk transaksi.
 * Konsep OOP:
 * - Encapsulation: Menggunakan private fields dan public getters/setters untuk akses.
 * - Abstract Class: Kelas ini tidak dapat diinstansiasi dan harus diwarisi oleh kelas lain.
 */
public abstract class Transaksi {
    private String idTransaksi;
    private String tanggal;

    public Transaksi(String idTransaksi, String tanggal) {
        this.idTransaksi = idTransaksi;
        this.tanggal = tanggal;
    }

    public String getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(String idTransaksi) { this.idTransaksi = idTransaksi; }

    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }

    public abstract void prosesTransaksi();
}
