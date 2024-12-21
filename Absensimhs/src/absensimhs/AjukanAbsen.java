import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AjukanAbsen {

    // Menyimpan data absensi yang diajukan
    public static List<Object[]> absensiList = new ArrayList<>();

    public static void showAjukanAbsenPage() {
        // Frame untuk halaman Ajukan Absen
        JFrame frame = new JFrame("Ajukan Absen");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(51, 51, 51));
        sidebarPanel.setPreferredSize(new Dimension(200, 600)); // Ukuran sidebar

        // Menambahkan tombol menu ke sidebar
        String[] menuItems = {"Home", "Izin Sakit", "Jadwal", "Ajukan Absen", "Cek Absen"};
        for (String item : menuItems) {
            JButton menuButton = new JButton(item);
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuButton.setForeground(Color.WHITE);
            menuButton.setBackground(new Color(51, 51, 51));
            menuButton.setFocusPainted(false);
            menuButton.setBorderPainted(false);
            menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            menuButton.setMaximumSize(new Dimension(200, 40));

            menuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (item) {
                        case "Home":
                            frame.dispose();
                            JOptionPane.showMessageDialog(frame, "Menu 'Home' diklik.");
                            Home.showHomePage("Mahasiswa"); // Panggil halaman Home
                            break;
                        case "Izin Sakit":
                            frame.dispose();
                            JOptionPane.showMessageDialog(frame, "Menu 'Izin Sakit' diklik.");
                            IzinSakit.showIzinSakitPage(); // Panggil halaman Izin Sakit
                            break;
                        case "Jadwal":
                            frame.dispose();
                            JOptionPane.showMessageDialog(frame, "Menu 'Jadwal' diklik.");
                            Jadwal.showJadwalPage(); // Panggil halaman Jadwal
                            break;
                        case "Cek Absen":
                            frame.dispose();
                            JOptionPane.showMessageDialog(frame, "Menu 'Cek Absen' diklik.");
                            CekAbsen.showCekAbsenPage();
                        case "Ajukan Absen":
                            JOptionPane.showMessageDialog(frame, "Anda sudah berada di halaman Ajukan Absen.");
                            break;
                    }
                }
            });

            sidebarPanel.add(Box.createVerticalStrut(10)); // Spasi antar tombol
            sidebarPanel.add(menuButton);
        }

        // Label Judul Halaman
        JLabel titleLabel = new JLabel("Halaman Ajukan Absen", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Data tabel
        String[] columnNames = {"Tanggal", "Deskripsi", "Status", "Poin", "Catatan"};
        Object[][] data = {}; // Data kosong di awal

        // Membuat model tabel dan JTable
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setRowHeight(30); // Tinggi baris tabel
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Memusatkan isi kolom tertentu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Tanggal
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Deskripsi
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // Status
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Catatan

        // Scroll Pane untuk tabel
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();

        // Tombol Absen
        JButton absenButton = new JButton("Absen");
        absenButton.setFont(new Font("Arial", Font.PLAIN, 16));
        absenButton.setBackground(new Color(0, 122, 255));
        absenButton.setForeground(Color.WHITE);
        absenButton.setPreferredSize(new Dimension(120, 40));

        // Aksi tombol Absen
        absenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mendapatkan waktu sekarang
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy HH:mm");
                String currentDate = now.format(formatter);

                // Menambahkan data ke tabel
                Object[] newRow = {currentDate, "Kelas Pagi", "Mahasiswa", "", "Absensi Mandiri"};
                model.addRow(newRow);

                // Menambahkan data ke absensiList
                absensiList.add(newRow);

                // Pesan konfirmasi
                JOptionPane.showMessageDialog(frame, "Absen Anda Telah Diajukan!", "Informasi", JOptionPane.INFORMATION_MESSAGE);

                absenButton.setEnabled(false);
            }
        });

        // Tombol Hapus
        JButton hapusButton = new JButton("Hapus");
        hapusButton.setFont(new Font("Arial", Font.PLAIN, 16));
        hapusButton.setBackground(new Color(255, 50, 50));
        hapusButton.setForeground(Color.WHITE);
        hapusButton.setPreferredSize(new Dimension(120, 40));

        // Aksi tombol Hapus
        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(frame, "Pilih baris yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }

                absenButton.setEnabled(true);
            }
        });

        // Menambahkan tombol ke panel
        buttonPanel.add(absenButton);
        buttonPanel.add(hapusButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Menambahkan Sidebar dan Konten ke Frame
        frame.add(sidebarPanel, BorderLayout.WEST);

        // Tampilkan frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AjukanAbsen::showAjukanAbsenPage);
    }
}
