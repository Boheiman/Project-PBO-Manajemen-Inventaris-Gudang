import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.*;

public class GudangApp {
    private ManajemenBarangImpl manajemenBarang = new ManajemenBarangImpl();
    private ManajemenPemasokImpl manajemenPemasok = new ManajemenPemasokImpl();
    private String subItemType;

    public GudangApp() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistem Manajemen Inventaris Gudang");
            frame.getContentPane().setBackground(new Color(240, 240, 240));
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Inisialisasi tombol-tombol
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
            JButton btnLaporanInventaris = new JButton("Laporan Inventaris");
            JButton btnLaporanStokHabis = new JButton("Laporan Stok Dibawah 3");

            // Atur layout
            frame.setLayout(new java.awt.GridLayout(7, 2));
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

            // Action Listener untuk Tambah Barang
            btnTambahBarang.addActionListener(evt -> {
                try {
                    String[] electronicTypes = {"Laptop", "Komputer"};
                    subItemType = (String) JOptionPane.showInputDialog(frame,
                            "Pilih jenis barang:", "Jenis Barang",
                            JOptionPane.QUESTION_MESSAGE, null, electronicTypes, electronicTypes[0]);

                    if (subItemType == null) {
                        JOptionPane.showMessageDialog(frame, "Anda belum memilih jenis barang.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Menawarkan pilihan penginputan ganda
                    int pilihan = JOptionPane.showConfirmDialog(frame,
                            "Apakah Anda ingin menambahkan dua barang sekaligus?",
                            "Opsi Penginputan Barang",
                            JOptionPane.YES_NO_OPTION);

                    if (pilihan == JOptionPane.YES_OPTION) {
                        // Input untuk dua barang
                        BarangElektronik barang1 = inputBarang(frame, "Barang 1");
                        BarangElektronik barang2 = inputBarang(frame, "Barang 2");

                        // Memilih pemasok
                        ArrayList<Pemasok> suppliersList = manajemenPemasok.daftarPemasok();
                        if (suppliersList.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Tidak ada pemasok yang tersedia. Silakan tambahkan pemasok terlebih dahulu.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        String selectedSupplier = pilihPemasok(frame, suppliersList);
                        Pemasok pemasok = suppliersList.stream()
                                .filter(p -> p.getNamaPemasok().equals(selectedSupplier))
                                .findFirst()
                                .orElse(null);

                        if (pemasok != null) {
                            pemasok.addBarang(barang1, barang2);
                            // Tambahkan ke manajemen barang
                            manajemenBarang.tambahBarang(barang1);
                            manajemenBarang.tambahBarang(barang2);
                            JOptionPane.showMessageDialog(frame, "Dua barang berhasil ditambahkan ke pemasok " + pemasok.getNamaPemasok());
                        } else {
                            JOptionPane.showMessageDialog(frame, "Pemasok tidak ditemukan.");
                        }
                    } else {
                        // Input untuk satu barang
                        BarangElektronik barang = inputBarang(frame, "Barang");
                        // Memilih pemasok
                        ArrayList<Pemasok> suppliersList = manajemenPemasok.daftarPemasok();
                        if (suppliersList.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Tidak ada pemasok yang tersedia. Silakan tambahkan pemasok terlebih dahulu.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        String selectedSupplier = pilihPemasok(frame, suppliersList);
                        Pemasok pemasok = suppliersList.stream()
                                .filter(p -> p.getNamaPemasok().equals(selectedSupplier))
                                .findFirst()
                                .orElse(null);

                        if (pemasok != null) {
                            pemasok.addBarang(barang);
                            // Tambahkan ke manajemen barang
                            manajemenBarang.tambahBarang(barang);
                            JOptionPane.showMessageDialog(frame, "Barang berhasil ditambahkan ke pemasok " + pemasok.getNamaPemasok());
                        } else {
                            JOptionPane.showMessageDialog(frame, "Pemasok tidak ditemukan.");
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
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
                    e.printStackTrace();
                }
            });

            // Listener untuk Perbarui Barang
            btnPerbaruiBarang.addActionListener(evt -> {
                try {
                    String kodeBarang = JOptionPane.showInputDialog("Masukkan Kode Barang yang akan diperbarui:");
                    BarangElektronik barang = manajemenBarang.getBarangByKode(kodeBarang);
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
                    e.printStackTrace();
                }
            });

            btnDaftarBarang.addActionListener(evt -> {
                ArrayList<BarangElektronik> daftar = manajemenBarang.daftarBarang();
                if (daftar.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Tidak ada barang dalam daftar.");
                } else {
                    StringBuilder daftarBarang = new StringBuilder("Daftar Barang:\n\n");
                    for (BarangElektronik barang : daftar) {
                        daftarBarang.append(String.format("Kode: %s\nNama: %s\nKategori: %s\nStok: %d\nHarga: %.2f\nPemasok: %s\n\n",
                            barang.getKodeBarang(),
                            barang.getNamaBarang(),
                            barang.getKategori(),
                            barang.getStok(),
                            barang.getHarga(),
                            barang.getPemasok() != null ? barang.getPemasok().getNamaPemasok() : "Tidak Ada"));
                    }
                    JOptionPane.showMessageDialog(frame, daftarBarang.toString());
                }
            });

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
                    e.printStackTrace();
                }
            });

            btnHapusPemasok.addActionListener(evt -> {
                try {
                    String idPemasok = JOptionPane.showInputDialog("Masukkan ID Pemasok yang akan dihapus:");
                    manajemenPemasok.hapusPemasok(idPemasok);
                    JOptionPane.showMessageDialog(frame, "Pemasok berhasil dihapus.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            });

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
                    e.printStackTrace();
                }
            });

            btnDaftarPemasok.addActionListener(evt -> {
                ArrayList<Pemasok> daftar = manajemenPemasok.daftarPemasok();
                if (daftar.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Tidak ada pemasok dalam daftar.");
                } else {
                    StringBuilder daftarPemasok = new StringBuilder("Daftar Pemasok:\n\n");
                    for (Pemasok pemasok : daftar) {
                        daftarPemasok.append(String.format("ID: %s\nNama: %s\nKontak: %s\n\n",
                            pemasok.getIdPemasok(),
                            pemasok.getNamaPemasok(),
                            pemasok.getKontak()));
                    }
                    JOptionPane.showMessageDialog(frame, daftarPemasok.toString());
                }
            });

            btnBarangMasuk.addActionListener(evt -> {
                try {
                    String kodeBarang = JOptionPane.showInputDialog("Kode Barang:");
                    int jumlah = Integer.parseInt(JOptionPane.showInputDialog("Jumlah Barang Masuk:"));
                    manajemenBarang.barangMasuk(kodeBarang, jumlah);
                    JOptionPane.showMessageDialog(frame, "Barang masuk berhasil dicatat.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            });

            btnBarangKeluar.addActionListener(evt -> {
                try {
                    String kodeBarang = JOptionPane.showInputDialog("Kode Barang:");
                    int jumlah = Integer.parseInt(JOptionPane.showInputDialog("Jumlah Barang Keluar:"));
                    manajemenBarang.barangKeluar(kodeBarang, jumlah);
                    JOptionPane.showMessageDialog(frame, "Barang keluar berhasil dicatat.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            });

            btnLaporanInventaris.addActionListener(evt -> {
                ArrayList<BarangElektronik> daftar = manajemenBarang.daftarBarang();
                if (daftar.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Tidak ada barang dalam inventaris.");
                } else {
                    StringBuilder laporan = new StringBuilder("Laporan Inventaris:\n\n");
                    for (BarangElektronik barang : daftar) {
                        laporan.append(String.format("Kode: %s\nNama: %s\nKategori: %s\nStok: %d\nHarga: Rp%.2f\nNilai Total: Rp%.2f\n\n",
                            barang.getKodeBarang(),
                            barang.getNamaBarang(),
                            barang.getKategori(),
                            barang.getStok(),
                            barang.getHarga(),
                            barang.getHarga() * barang.getStok()));
                    }
                    JOptionPane.showMessageDialog(frame, laporan.toString());
                }
            });

            btnLaporanStokHabis.addActionListener(evt -> {
                ArrayList<BarangElektronik> daftar = manajemenBarang.daftarBarang();
                StringBuilder laporan = new StringBuilder("Laporan Stok Dibawah 3:\n\n");
                boolean adaStokRendah = false;
                
                for (BarangElektronik barang : daftar) {
                    if (barang.getStok() < 3) {
                        adaStokRendah = true;
                        laporan.append(String.format("Kode: %s\nNama: %s\nStok Saat Ini: %d\n\n",
                            barang.getKodeBarang(),
                            barang.getNamaBarang(),
                            barang.getStok()));
                    }
                }
                
                if (!adaStokRendah) {
                    JOptionPane.showMessageDialog(frame, "Tidak ada barang dengan stok di bawah 3.");
                } else {
                    JOptionPane.showMessageDialog(frame, laporan.toString());
                }
            });

            frame.setVisible(true);
        });
    }

    private BarangElektronik inputBarang(JFrame frame, String label) {
        String kode = JOptionPane.showInputDialog(frame, "Kode " + label + ":");
        String nama = JOptionPane.showInputDialog(frame, "Nama " + label + ":");
        int stok = Integer.parseInt(JOptionPane.showInputDialog(frame, "Stok " + label + ":"));
        double harga = Double.parseDouble(JOptionPane.showInputDialog(frame, "Harga " + label + ":"));

        if (subItemType.equals("Komputer")) {
            String processor = JOptionPane.showInputDialog(frame, "Processor:");
            int ramSize = Integer.parseInt(JOptionPane.showInputDialog(frame, "RAM Size (GB):"));
            int storageSize = Integer.parseInt(JOptionPane.showInputDialog(frame, "Storage Size (GB):"));
            String os = JOptionPane.showInputDialog(frame, "Operating System:");
            String jenisCasing = JOptionPane.showInputDialog(frame, "Jenis Casing:");
            return new Komputer(kode, nama, subItemType, stok, harga, null, processor, ramSize, storageSize, os, jenisCasing);
        } else {
            String processor = JOptionPane.showInputDialog(frame, "Processor:");
            int ramSize = Integer.parseInt(JOptionPane.showInputDialog(frame, "RAM Size (GB):"));
            int storageSize = Integer.parseInt(JOptionPane.showInputDialog(frame, "Storage Size (GB):"));
            double screenSize = Double.parseDouble(JOptionPane.showInputDialog(frame, "Screen Size (inches):"));
            boolean isTouchscreen = JOptionPane.showConfirmDialog(frame, "Apakah ini touchscreen?") == JOptionPane.YES_OPTION;
            return new Laptop(kode, nama, subItemType, stok, harga, null, processor, ramSize, storageSize, screenSize, isTouchscreen);
        }
    }

    private String pilihPemasok(JFrame frame, ArrayList<Pemasok> suppliersList) {
        String[] suppliers = suppliersList.stream()
                .map(Pemasok::getNamaPemasok)
                .toArray(String[]::new);
        return (String) JOptionPane.showInputDialog(frame,
                "Pilih Pemasok:", "Pemasok", JOptionPane.QUESTION_MESSAGE, null, suppliers, suppliers[0]);
    }

    public static void main(String[] args) {
        new GudangApp();
    }
}
