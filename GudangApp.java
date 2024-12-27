import javax.swing.*;
import java.awt.Color; // Import for Color
import java.awt.FlowLayout; // Import for FlowLayout
import java.util.ArrayList;
 
public class GudangApp {
    private ManajemenBarangImpl manajemenBarang = new ManajemenBarangImpl();
    private ManajemenPemasokImpl manajemenPemasok = new ManajemenPemasokImpl();
    private ArrayList<Transaksi> transaksiList = new ArrayList<>();
    private String subItemType; // Declare subItemType as a class-level variable

    /**
     * Konstruktor untuk kelas GudangApp.
     * Menginisialisasi GUI dan menambahkan ActionListener untuk setiap tombol.
     */
    public GudangApp() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistem Manajemen Inventaris Gudang");
            frame.getContentPane().setBackground(new Color(240, 240, 240)); // Set a light gray background color
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center layout with padding
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton btnTambahBarang = new JButton("Tambah Barang");
            JButton btnHapusBarang = new JButton("Hapus Barang");
            JButton btnPerbaruiBarang = new JButton("Perbarui Barang");
            JButton btnDaftarBarang = new JButton("Daftar Barang");

            JButton btnTambahPemasok = new JButton("Tambah Pemasok");
            JButton btnHapusPemasok = new JButton("Hapus Pemasok");
            JButton btnPerbaruiPemasok = new JButton("Perbarui Pemasok");
            JButton btnDaftarPemasok = new JButton("Daftar Pemasok");

            JButton btnBarangMasuk = new JButton("Barang Masuk");
            JButton btnBarangKeluar = new JButton("Barang Keluar");

            // Tombol untuk Laporan Inventaris
            JButton btnLaporanInventaris = new JButton("Laporan Inventaris");
            JButton btnLaporanStokHabis = new JButton("Laporan Stok Dibawah 3");

            frame.setLayout(new java.awt.GridLayout(7, 2)); // 7 baris, 2 kolom
            frame.add(btnTambahBarang);
            frame.add(btnDaftarBarang);
            frame.add(btnHapusBarang);
            frame.add(btnPerbaruiBarang);
            frame.add(btnTambahPemasok);
            frame.add(btnDaftarPemasok);
            frame.add(btnHapusPemasok);
            frame.add(btnPerbaruiPemasok);
            frame.add(btnBarangMasuk);
            frame.add(btnBarangKeluar);
            frame.add(btnLaporanInventaris);
            frame.add(btnLaporanStokHabis);

            // Listener untuk Tambah Barang
            btnTambahBarang.addActionListener(evt -> {
                try {
                    String[] itemTypes = {"Elektronik", "Makanan"};
                    String itemType = (String) JOptionPane.showInputDialog(frame,
                            "Pilih jenis barang:", "Jenis Barang", JOptionPane.QUESTION_MESSAGE, null, itemTypes, itemTypes[0]);

                    subItemType = null; // Reset subItemType
                    if ("Elektronik".equals(itemType)) {
                        String[] electronicTypes = {"Elektronik", "Tidak Elektronik"};
                        subItemType = (String) JOptionPane.showInputDialog(frame,
                                "Pilih sub-jenis barang elektronik:", "Sub-Jenis Barang Elektronik", JOptionPane.QUESTION_MESSAGE, null, electronicTypes, electronicTypes[0]);
                    } else if ("Makanan".equals(itemType)) {
                        String[] foodTypes = {"Makanan", "Minuman"};
                        subItemType = (String) JOptionPane.showInputDialog(frame,
                                "Pilih sub-jenis barang makanan:", "Sub-Jenis Barang Makanan", JOptionPane.QUESTION_MESSAGE, null, foodTypes, foodTypes[0]);
                    }

                    String kode = JOptionPane.showInputDialog("Kode Barang:");
                    String nama = JOptionPane.showInputDialog("Nama Barang:");

                    // Retrieve the list of suppliers
                    ArrayList<Pemasok> suppliersList = manajemenPemasok.daftarPemasok();
                    if (suppliersList.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Tidak ada pemasok yang tersedia. Silakan tambahkan pemasok terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Exit the method if no suppliers are available
                    }
                    String[] suppliers = suppliersList.stream()
                            .map(Pemasok::getNamaPemasok)
                            .toArray(String[]::new);
                    String selectedSupplier = (String) JOptionPane.showInputDialog(frame,
                            "Pilih Pemasok:", "Pemasok", JOptionPane.QUESTION_MESSAGE, null, suppliers, suppliers[0]);

                    String pemasok = selectedSupplier != null ? selectedSupplier : "Pemasok Default"; // Default if no selection
                    int stok = Integer.parseInt(JOptionPane.showInputDialog("Stok:"));
                    double harga = Double.parseDouble(JOptionPane.showInputDialog("Harga:"));
                    Barang barang;

                    if ("Elektronik".equalsIgnoreCase(itemType)) {
                        barang = new BarangElektronik(kode, nama, subItemType, stok, harga, pemasok); // 6 parameters
                    } else {
                        String tanggalKadaluarsa = JOptionPane.showInputDialog("Tanggal Kadaluarsa:"); // Additional parameter for Makanan
                        barang = new BarangMakanan(kode, nama, subItemType, stok, harga, pemasok, tanggalKadaluarsa); // 7 parameters
                    }

                    manajemenBarang.tambahBarang(barang); // Add the item to the management system
                    JOptionPane.showMessageDialog(frame, "Barang " + nama + " telah ditambahkan."); // Success message
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); // Print stack trace for debugging
                }
            });

            // Listener untuk Hapus Barang
            btnHapusBarang.addActionListener(evt -> {
                try {
                    String kodeBarang = JOptionPane.showInputDialog("Masukkan Kode Barang yang akan dihapus:");
                    manajemenBarang.hapusBarang(kodeBarang);
                    JOptionPane.showMessageDialog(frame, "Barang berhasil dihapus.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); // Print stack trace for debugging
                }
            });

            // Listener untuk Perbarui Barang
            btnPerbaruiBarang.addActionListener(evt -> {
                try {
                    String kodeBarang = JOptionPane.showInputDialog("Masukkan Kode Barang yang akan diperbarui:");
                    Barang barang = manajemenBarang.getBarangByKode(kodeBarang);
                    if (barang != null) {
                        String nama = JOptionPane.showInputDialog("Nama Barang Baru:", barang.getNamaBarang());
                        int stok = Integer.parseInt(JOptionPane.showInputDialog("Stok Baru:", barang.getStok()));
                        double harga = Double.parseDouble(JOptionPane.showInputDialog("Harga Baru:", barang.getHarga()));
                        barang.setNamaBarang(nama);
                        barang.setStok(stok);
                        barang.setHarga(harga);
                        manajemenBarang.perbaruiBarang(kodeBarang, barang);
                        JOptionPane.showMessageDialog(frame, "Barang " + nama + " telah diperbarui.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Barang tidak ditemukan.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); // Print stack trace for debugging
                }
            });

            // Listener untuk Daftar Barang
            btnDaftarBarang.addActionListener(evt -> {
                StringBuilder daftarBarang = new StringBuilder("Daftar Barang:\n");
                for (Barang barang : manajemenBarang.daftarBarang()) {
                    daftarBarang.append(barang.getNamaBarang()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, daftarBarang.toString());
            });

            // Listener untuk Tambah Pemasok
            btnTambahPemasok.addActionListener(evt -> {
                try {
                    String idPemasok = JOptionPane.showInputDialog("ID Pemasok:");
                    String namaPemasok = JOptionPane.showInputDialog("Nama Pemasok:");
                    String kontak = JOptionPane.showInputDialog("Kontak Pemasok:");
                    Pemasok pemasok = new Pemasok(idPemasok, namaPemasok, kontak);
                    manajemenPemasok.tambahPemasok(pemasok);
                    JOptionPane.showMessageDialog(frame, "Pemasok " + namaPemasok + " telah ditambahkan.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); // Print stack trace for debugging
                }
            });

            // Listener untuk Hapus Pemasok
            btnHapusPemasok.addActionListener(evt -> {
                try {
                    String idPemasok = JOptionPane.showInputDialog("Masukkan ID Pemasok yang akan dihapus:");
                    manajemenPemasok.hapusPemasok(idPemasok);
                    JOptionPane.showMessageDialog(frame, "Pemasok berhasil dihapus.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); // Print stack trace for debugging
                }
            });

            // Listener untuk Perbarui Pemasok
            btnPerbaruiPemasok.addActionListener(evt -> {
                try {
                    String idPemasok = JOptionPane.showInputDialog("Masukkan ID Pemasok yang akan diperbarui:");
                    Pemasok pemasok = manajemenPemasok.daftarPemasok().stream()
                            .filter(p -> p.getIdPemasok().equals(idPemasok))
                            .findFirst()
                            .orElse(null);
                    if (pemasok != null) {
                        String namaBaru = JOptionPane.showInputDialog("Nama Pemasok Baru:", pemasok.getNamaPemasok());
                        String kontakBaru = JOptionPane.showInputDialog("Kontak Baru:", pemasok.getKontak());
                        pemasok.setNamaPemasok(namaBaru);
                        pemasok.setKontak(kontakBaru);
                        manajemenPemasok.perbaruiPemasok(idPemasok, pemasok);
                        JOptionPane.showMessageDialog(frame, "Pemasok " + namaBaru + " telah diperbarui.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Pemasok tidak ditemukan.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); // Print stack trace for debugging
                }
            });

            // Listener untuk Daftar Pemasok
            btnDaftarPemasok.addActionListener(evt -> {
                StringBuilder daftarPemasok = new StringBuilder("Daftar Pemasok:\n");
                for (Pemasok pemasok : manajemenPemasok.daftarPemasok()) {
                    daftarPemasok.append(pemasok.getNamaPemasok()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, daftarPemasok.toString());
            });

            // Listener untuk Barang Masuk
            btnBarangMasuk.addActionListener(evt -> {
                try {
                    String kodeBarang = JOptionPane.showInputDialog("Kode Barang:");
                    int jumlah = Integer.parseInt(JOptionPane.showInputDialog("Jumlah Barang Masuk:"));
                    manajemenBarang.barangMasuk(kodeBarang, jumlah);
                    JOptionPane.showMessageDialog(frame, "Barang masuk berhasil.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); // Print stack trace for debugging
                }
            });

            // Listener untuk Barang Keluar
            btnBarangKeluar.addActionListener(evt -> {
                try {
                    String kodeBarang = JOptionPane.showInputDialog("Kode Barang:");
                    int jumlah = Integer.parseInt(JOptionPane.showInputDialog("Jumlah Barang Keluar:"));
                    manajemenBarang.barangKeluar(kodeBarang, jumlah);
                    JOptionPane.showMessageDialog(frame, "Barang keluar berhasil.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); // Print stack trace for debugging
                }
            });

            // Implement listeners for Laporan buttons
            btnLaporanInventaris.addActionListener(evt -> {
                // Logic for generating inventory report
                StringBuilder laporanInventaris = new StringBuilder("Laporan Inventaris:\n");
                for (Barang barang : manajemenBarang.daftarBarang()) {
                    laporanInventaris.append(barang.getNamaBarang()).append(" - Stok: ").append(barang.getStok()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, laporanInventaris.toString());
            });

            btnLaporanStokHabis.addActionListener(evt -> {
                // Logic for generating low stock report
                StringBuilder laporanStokHabis = new StringBuilder("Laporan Stok Dibawah 3:\n");
                for (Barang barang : manajemenBarang.daftarBarang()) {
                    if (barang.getStok() < 3) {
                        laporanStokHabis.append(barang.getNamaBarang()).append(" - Stok: ").append(barang.getStok()).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(frame, laporanStokHabis.toString());
            });

            frame.setVisible(true); // Make the frame visible
        });
    }

    public static void main(String[] args) {
        new GudangApp();
    }
}
