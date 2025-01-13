import java.io.*;
import java.util.ArrayList;

public class ManajemenBarangImpl implements ManajemenBarang {
    private ArrayList<BarangElektronik> daftarBarang = new ArrayList<>();
    private final String filePath = "src/data_barang.csv";

    public ManajemenBarangImpl() {
        muatDataDariFile();
    }

    @Override
    public void tambahBarang(BarangElektronik barang) {
        
        if (getBarangByKode(barang.getKodeBarang()) != null) {
            System.out.println("Error: Kode barang sudah ada");
            return;
        }
        
        daftarBarang.add(barang);
        simpanDataKeFile();
        System.out.println("Barang berhasil ditambahkan: " + barang.getNamaBarang());
    }

    @Override
    public void perbaruiBarang(String kodeBarang, BarangElektronik barangBaru) {
        for (int i = 0; i < daftarBarang.size(); i++) {
            if (daftarBarang.get(i).getKodeBarang().equals(kodeBarang)) {
                daftarBarang.set(i, barangBaru);
                simpanDataKeFile();
                System.out.println("Barang berhasil diperbarui: " + barangBaru.getNamaBarang());
                return;
            }
        }
        System.out.println("Error: Barang tidak ditemukan");
    }

    @Override
    public void hapusBarang(String kodeBarang) {
        if (daftarBarang.removeIf(barang -> barang.getKodeBarang().equals(kodeBarang))) {
            simpanDataKeFile();
            System.out.println("Barang berhasil dihapus");
        } else {
            System.out.println("Error: Barang tidak ditemukan");
        }
    }

    @Override
    public ArrayList<BarangElektronik> daftarBarang() {
        return new ArrayList<>(daftarBarang);
    }

    @Override
    public BarangElektronik getBarangByKode(String kodeBarang) {
        return daftarBarang.stream()
                .filter(barang -> barang.getKodeBarang().equals(kodeBarang))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void barangMasuk(String kodeBarang, int jumlah) {
        BarangElektronik barang = getBarangByKode(kodeBarang);
        if (barang != null) {
            barang.setStok(barang.getStok() + jumlah);
            simpanDataKeFile();
            System.out.println("Barang masuk berhasil: " + jumlah + " unit pada " + barang.getNamaBarang());
        } else {
            System.out.println("Error: Barang tidak ditemukan");
        }
    }

    @Override
    public void barangKeluar(String kodeBarang, int jumlah) {
        BarangElektronik barang = getBarangByKode(kodeBarang);
        if (barang != null) {
            if (barang.getStok() >= jumlah) {
                barang.setStok(barang.getStok() - jumlah);
                simpanDataKeFile();
                System.out.println("Barang keluar berhasil: " + jumlah + " unit dari " + barang.getNamaBarang());
            } else {
                System.out.println("Error: Stok tidak mencukupi untuk " + barang.getNamaBarang());
            }
        } else {
            System.out.println("Error: Barang tidak ditemukan");
        }
    }

    private void simpanDataKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            
            writer.write("KodeBarang;NamaBarang;Kategori;Stok;Harga;Pemasok;Jenis;Spesifikasi");
            writer.newLine();
            
            for (BarangElektronik barang : daftarBarang) {
                String spesifikasi = "";
                if (barang instanceof Komputer) {
                    Komputer komputer = (Komputer) barang;
                    spesifikasi = String.format("Processor:%s,RAM:%d,Storage:%d,OS:%s,JenisCasing:%s",
                            komputer.getProcessor(), komputer.getRamSize(), komputer.getStorageSize(), 
                            komputer.getOs(), komputer.getJenisCasing());
                } else if (barang instanceof Laptop) {
                    Laptop laptop = (Laptop) barang;
                    spesifikasi = String.format("Processor:%s,RAM:%d,Storage:%d,Screen:%.1f,Touchscreen:%b",
                            laptop.getProcessor(), laptop.getRamSize(), laptop.getStorageSize(), 
                            laptop.getScreenSize(), laptop.isTouchscreen());
                }
                
                writer.write(String.format("%s;%s;%s;%d;%.2f;%s;%s;%s",
                    barang.getKodeBarang(),
                    barang.getNamaBarang(),
                    barang.getKategori(),
                    barang.getStok(),
                    barang.getHarga(),
                    barang.getPemasok() != null ? barang.getPemasok().getNamaPemasok() : "",
                    barang instanceof Komputer ? "Komputer" : barang instanceof Laptop ? "Laptop" : "Elektronik",
                    spesifikasi));
                writer.newLine();
            }
            System.out.println("Data barang berhasil disimpan ke file CSV.");
        } catch (IOException e) {
            System.out.println("Error: Gagal menyimpan data ke file - " + e.getMessage());
        }
    }

    private void muatDataDariFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("File baru dibuat: " + filePath);
                
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("KodeBarang;NamaBarang;Kategori;Stok;Harga;Pemasok;Jenis;Spesifikasi");
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error: Gagal membuat file - " + e.getMessage());
                return;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); 
            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = line.split(";");
                    if (data.length >= 5) {
                        BarangElektronik barang;
                        String jenis = data.length > 6 ? data[6] : "Elektronik";
                        String spesifikasi = data.length > 7 ? data[7] : "";

                        if (jenis.equals("Komputer")) {
                            String[] specs = spesifikasi.split(",");
                            barang = new Komputer(
                                data[0], data[1], data[2],
                                Integer.parseInt(data[3]),
                                Double.parseDouble(data[4]),
                                null,
                                specs[0].split(":")[1],
                                Integer.parseInt(specs[1].split(":")[1]),
                                Integer.parseInt(specs[2].split(":")[1]),
                                specs[3].split(":")[1],
                                specs[4].split(":")[1]
                            );
                        } else if (jenis.equals("Laptop")) {
                            String[] specs = spesifikasi.split(",");
                            barang = new Laptop(
                                data[0], data[1], data[2],
                                Integer.parseInt(data[3]),
                                Double.parseDouble(data[4]),
                                null,
                                specs[0].split(":")[1],
                                Integer.parseInt(specs[1].split(":")[1]),
                                Integer.parseInt(specs[2].split(":")[1]),
                                Double.parseDouble(specs[3].split(":")[1]),
                                Boolean.parseBoolean(specs[4].split(":")[1])
                            );
                        } else {
                            barang = new BarangElektronik(
                                data[0], data[1], data[2],
                                Integer.parseInt(data[3]),
                                Double.parseDouble(data[4]),
                                null
                            );
                        }
                        daftarBarang.add(barang);
                        System.out.println("Berhasil memuat barang: " + barang.getNamaBarang());
                    }
                } catch (Exception e) {
                    System.out.println("Error: Gagal memproses baris - " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Gagal membaca data dari file - " + e.getMessage());
        }
    }
}
