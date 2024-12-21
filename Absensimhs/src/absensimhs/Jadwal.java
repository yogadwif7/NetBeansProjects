import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Jadwal {
    public static void showJadwalPage() {
        // Frame untuk halaman Jadwal
        JFrame jadwalFrame = new JFrame("Halaman Jadwal");
        jadwalFrame.setSize(600, 400);
        jadwalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jadwalFrame.setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(51, 51, 51));
        sidebarPanel.setPreferredSize(new Dimension(200, 400)); // Ukuran sidebar

        // Menambahkan tombol menu ke sidebar
        String[] menuItems = {"Home", "Ajukan Absen", "Izin Sakit", "Jadwal"};
        for (String item : menuItems) {
            JButton menuButton = new JButton(item);
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuButton.setForeground(Color.WHITE);
            menuButton.setBackground(new Color(51, 51, 51));
            menuButton.setFocusPainted(false);
            menuButton.setBorderPainted(false);
            menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            menuButton.setMaximumSize(new Dimension(200, 40));

            menuButton.addActionListener(e -> {
                switch (item) {
                    case "Home": // Perbaiki nama menu
                        jadwalFrame.dispose();
                        JOptionPane.showMessageDialog(jadwalFrame, "Menu 'Home' diklik.");
                        Home.showHomePage("Mahasiswa"); // Panggil halaman Home
                        break;
                    case "Ajukan Absen":
                        JOptionPane.showMessageDialog(jadwalFrame, "Menu 'Ajukan Absen' diklik.");
                        AjukanAbsen.showAjukanAbsenPage(); // Panggil halaman Ajukan Absen
                        break;
                    case "Izin Sakit":
                        JOptionPane.showMessageDialog(jadwalFrame, "Menu 'Izin Sakit' diklik.");
                        IzinSakit.showIzinSakitPage(); // Panggil halaman Izin Sakit
                        break;
                    case "Jadwal":
                        JOptionPane.showMessageDialog(jadwalFrame, "Anda sudah berada di halaman Jadwal.");
                        break;
                }
            });

            sidebarPanel.add(Box.createVerticalStrut(10)); // Spasi antar tombol
            sidebarPanel.add(menuButton);
        }

        // Judul halaman
        JLabel titleLabel = new JLabel("Jadwal Anda", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Data untuk tabel jadwal (contoh data)
        String[] columnNames = {"Hari", "Waktu", "Mata Kuliah", "Ruang"};
        Object[][] data = {
                {"Senin", "08:00 - 10:00", "Algoritma Pemrograman 3", "Lab 1"},
                {"Selasa", "10:00 - 12:00", "Basis Data", "Lab 2"},
                {"Rabu", "13:00 - 15:00", "Struktur Data", "Ruang A3"},
                {"Kamis", "08:00 - 10:00", "Matematika Diskrit", "Ruang B1"},
                {"Jumat", "10:00 - 12:00", "Teori Bahasa & Otomata", "Ruang C2"}
        };

        // Model tabel untuk data
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable jadwalTable = new JTable(tableModel);

        // Scroll pane untuk tabel
        JScrollPane tableScrollPane = new JScrollPane(jadwalTable);

        // Panel konten utama
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Menambahkan Sidebar dan Konten ke Frame
        jadwalFrame.add(sidebarPanel, BorderLayout.WEST);
        jadwalFrame.add(contentPanel, BorderLayout.CENTER);

        // Menampilkan frame
        jadwalFrame.setLocationRelativeTo(null); // Tengah layar
        jadwalFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Jadwal::showJadwalPage);
    }
}
