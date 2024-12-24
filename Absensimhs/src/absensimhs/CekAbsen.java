import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CekAbsen {
    public static void showCekAbsenPage() {
        // Frame untuk halaman Cek Absen
        JFrame frame = new JFrame("Cek Absen");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(51, 51, 51));
        sidebarPanel.setPreferredSize(new Dimension(200, 600)); // Ukuran sidebar

        // Menambahkan tombol menu ke sidebar
        String[] menuItems = {"Home", "Izin Sakit", "Jadwal", "Cek Absen", "Ajukan Absen"};
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
                    case "Home":
                        frame.dispose();
                        JOptionPane.showMessageDialog(frame, "Menu 'Home' diklik.");
                        Home.showHomePage("Dosen"); // Panggil halaman Home
                        break;
                    case "Daftar Nama":
                        frame.dispose();
                        JOptionPane.showMessageDialog(frame, "Menu 'Daftar Nama' diklik.");
                        DaftarNama.showDaftarNamaPage(); // Panggil halaman Izin Sakit
                        break;
                    case "Jadwal":
                        frame.dispose();
                        JOptionPane.showMessageDialog(frame, "Menu 'Jadwal' diklik.");
                        Jadwal.showJadwalPage(); // Panggil halaman Jadwal
                        break;
                    case "Ajukan Absen":
                        frame.dispose();
                        JOptionPane.showMessageDialog(frame, "Menu 'Ajukan Absen' diklik.");
                        AjukanAbsen.showAjukanAbsenPage();
                    case "Cek Absen":
                        JOptionPane.showMessageDialog(frame, "Anda sudah berada di halaman Cek Absen.");
                        break;
                }
            });

            sidebarPanel.add(Box.createVerticalStrut(10)); // Spasi antar tombol
            sidebarPanel.add(menuButton);
        }

        // Konten tengah
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);

        JLabel label = new JLabel("Halaman Cek Absen");
        label.setFont(new Font("Arial", Font.BOLD, 28));
        contentPanel.add(label);

        // Data tabel
        String[] columnNames = {"Nama", "Waktu", "Status", "Catatan"};

        // Mengambil data dari absensiList
        Object[][] data = new Object[0][5]; // Default empty data if no records are available
        
        // Assuming AjukanAbsen.absensiList is a List of Object arrays
        // Example: Add data if available.
        if (AjukanAbsen.absensiList != null && !AjukanAbsen.absensiList.isEmpty()) {
            data = new Object[AjukanAbsen.absensiList.size()][5];
            for (int i = 0; i < AjukanAbsen.absensiList.size(); i++) {
                data[i] = AjukanAbsen.absensiList.get(i);
            }
        }

        // Membuat model tabel dan JTable
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setRowHeight(30); // Tinggi baris tabel
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Memusatkan isi kolom tertentu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Apply center alignment for all columns
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Scroll Pane untuk tabel
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Menambahkan Sidebar dan Konten ke Frame
        frame.add(sidebarPanel, BorderLayout.WEST);
        frame.add(contentPanel, BorderLayout.NORTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CekAbsen::showCekAbsenPage);
    }
}
