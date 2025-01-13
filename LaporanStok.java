public class LaporanStok {
    public void tampilkanLaporanStok(ManajemenBarangImpl manajemenBarang) {
        for (BarangElektronik barang : manajemenBarang.daftarBarang()) {
            System.out.println("Kode: " + barang.getKodeBarang() + ", Nama: " + barang.getNamaBarang() + ", Stok: " + barang.getStok());
            if (barang instanceof Komputer) {
                Komputer computer = (Komputer) barang;
                System.out.println("Processor: " + computer.getProcessor() + ", RAM: " + computer.getRamSize() + "GB, Storage: " + computer.getStorageSize() + "GB, OS: " + computer.getOs());
            } else if (barang instanceof Laptop) {
                Laptop laptop = (Laptop) barang;
                System.out.println("Processor: " + laptop.getProcessor() + ", RAM: " + laptop.getRamSize() + "GB, Storage: " + laptop.getStorageSize() + "GB, Screen: " + laptop.getScreenSize() + " inches, Touchscreen: " + laptop.isTouchscreen());
            }
        }
    }
}
