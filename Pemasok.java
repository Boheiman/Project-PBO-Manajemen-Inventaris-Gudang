public class Pemasok {
    private String idPemasok;
    private String namaPemasok;
    private String kontak;

    public Pemasok(String idPemasok, String namaPemasok, String kontak) {
        this.idPemasok = idPemasok;
        this.namaPemasok = namaPemasok;
        this.kontak = kontak;
    }

    public String getIdPemasok() {
        return idPemasok;
    }

    public void setIdPemasok(String idPemasok) {
        this.idPemasok = idPemasok;
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

    @Override
    public String toString() {
        return "ID Pemasok: " + idPemasok + ", Nama Pemasok: " + namaPemasok + ", Kontak: " + kontak;
    }
}
