import java.io.*;
import java.util.ArrayList;

public class ManajemenBarangImpl implements ManajemenBarang {
    private ArrayList<Barang> daftarBarang = new ArrayList<>();
    private final String filePath = "data_barang.csv";

    public ManajemenBarangImpl() {
        muatDataDariFile();
    }

    @Override
    public void tambahBarang(Barang barang) {
        daftarBarang.add(barang);
        simpanDataKeFile();
        System.out.println("Barang berhasil ditambahkan: " + barang.getNamaBarang());
    }

    @Override
    public void perbaruiBarang(String kodeBarang, Barang barangBaru) {
        for (int i = 0; i < daftarBarang.size(); i++) {
            if (daftarBarang.get(i).getKodeBarang().equals(kodeBarang)) {
                daftarBarang.set(i, barangBaru);
                simpanDataKeFile();
                System.out.println("Barang berhasil diperbarui: " + barangBaru.getNamaBarang());
                return;
            }
        }
        System.out.println("Barang tidak ditemukan.");
    }

    @Override
    public void hapusBarang(String kodeBarang) {
        daftarBarang.removeIf(barang -> barang.getKodeBarang().equals(kodeBarang));
        simpanDataKeFile();
        System.out.println("Barang berhasil dihapus.");
    }

    @Override
    public ArrayList<Barang> daftarBarang() {
        return daftarBarang;
    }

    private void simpanDataKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Barang barang : daftarBarang) {
                writer.write(barang.getKodeBarang() + ";" + barang.getNamaBarang() + ";" +
                        barang.getKategori() + ";" + barang.getStok() + ";" +
                        barang.getHarga() + ";" + barang.getPemasok());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data ke file: " + e.getMessage());
        }
    }

    private void muatDataDariFile() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                Barang barang = new Barang(data[0], data[1], data[2],
                        Integer.parseInt(data[3]), Double.parseDouble(data[4]), data[5]);
                daftarBarang.add(barang);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca data dari file: " + e.getMessage());
        }
    }
}
