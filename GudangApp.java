
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GudangApp {

    private ManajemenBarangImpl manajemenBarang = new ManajemenBarangImpl();

    public GudangApp() {
        JFrame frame = new JFrame("Sistem Manajemen Inventaris Gudang");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnTambahBarang = new JButton("Tambah Barang");
        JButton btnDaftarBarang = new JButton("Daftar Barang");

        frame.setLayout(new java.awt.GridLayout(2, 1));
        frame.add(btnTambahBarang);
        frame.add(btnDaftarBarang);

        btnTambahBarang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kode = JOptionPane.showInputDialog("Kode Barang:");
                String nama = JOptionPane.showInputDialog("Nama Barang:");
                int stok = Integer.parseInt(JOptionPane.showInputDialog("Stok:"));

                Barang barang = new Barang(kode, nama, "Umum", stok, 0.0, "Pemasok Default");
                manajemenBarang.tambahBarang(barang);
            }
        });

        btnDaftarBarang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder daftar = new StringBuilder("Daftar Barang:\n");
                for (Barang barang : manajemenBarang.daftarBarang()) {
                    daftar.append(barang.getKodeBarang()).append(" - ").append(barang.getNamaBarang()).append("\n");
                }
                JOptionPane.showMessageDialog(null, daftar.toString());
            }
        });

        frame.setVisible(true);
    }
}
