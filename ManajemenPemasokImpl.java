import java.io.*;
import java.util.ArrayList;

public class ManajemenPemasokImpl {
    private ArrayList<Pemasok> daftarPemasok = new ArrayList<>();
    private final String filePath = "datapemasok.csv"; 
    public ManajemenPemasokImpl() {
        muatDataDariFile();
    }

    public void tambahPemasok(Pemasok pemasok) {
        daftarPemasok.add(pemasok);
        simpanDataKeFile();
        System.out.println("Pemasok berhasil ditambahkan: " + pemasok.getNamaPemasok());
    }

    public void perbaruiPemasok(String idPemasok, Pemasok pemasokBaru) {
        for (int i = 0; i < daftarPemasok.size(); i++) {
            if (daftarPemasok.get(i).getIdPemasok().equals(idPemasok)) {
                daftarPemasok.set(i, pemasokBaru);
                simpanDataKeFile();
                System.out.println("Pemasok berhasil diperbarui: " + pemasokBaru.getNamaPemasok());
                return;
            }
        }
        System.out.println("Pemasok tidak ditemukan.");
    }

    public void hapusPemasok(String idPemasok) {
        daftarPemasok.removeIf(pemasok -> pemasok.getIdPemasok().equals(idPemasok));
        simpanDataKeFile();
        System.out.println("Pemasok berhasil dihapus.");
    }

    public ArrayList<Pemasok> daftarPemasok() {
        return daftarPemasok;
    }

    private void simpanDataKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            
            writer.write("ID Pemasok;Nama Pemasok;Kontak");
            writer.newLine();

            for (Pemasok pemasok : daftarPemasok) {
                writer.write(pemasok.getIdPemasok() + ";" + pemasok.getNamaPemasok() + ";" + pemasok.getKontak());
                writer.newLine();
            }

            System.out.println("Data pemasok berhasil disimpan ke file CSV.");

        } catch (IOException e) {
            System.out.println("Gagal menyimpan data ke file: " + e.getMessage());
        }
    }

    private void muatDataDariFile() { 
        System.out.println("Mencoba memuat data dari file: " + filePath); 
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile(); 
            } catch (IOException e) {
                System.out.println("Gagal membuat file: " + e.getMessage());
                return;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine(); 
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length < 3) {
                    System.out.println("Data tidak lengkap di baris: " + line);
                    continue; 
                }
                Pemasok pemasok = new Pemasok(data[0], data[1], data[2]);
                daftarPemasok.add(pemasok);
            }

        } catch (IOException e) {
            System.out.println("Gagal membaca data dari file: " + e.getMessage());
        }
    }
}
