import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class home {

    // Method untuk menampilkan halaman home
    public static void showHomePage() {
        // Frame untuk halaman home
        JFrame homeFrame = new JFrame("Home Laman");
        homeFrame.setSize(800, 600); // Ukuran frame home
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setLayout(new BorderLayout()); // Layout BorderLayout

        // Panel Sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(50, 50, 50)); // Warna gelap
        sidebarPanel.setPreferredSize(new Dimension(200, homeFrame.getHeight())); // Lebar sidebar
        sidebarPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Tambahkan logo di sidebar
        JLabel logoLabel = new JLabel("Logo", JLabel.CENTER);
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(logoLabel);

        // Tambahkan menu di sidebar sebagai link (tanpa tombol, hitam saja)
        String[] menuItems = {"Home", "Cek Absen", "Izin Sakit", "Daftar Nama Mahasiswa"};
        for (String item : menuItems) {
            JLabel menuLink = new JLabel("<HTML><U>" + item + "</U></HTML>");
            menuLink.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuLink.setFont(new Font("Arial", Font.PLAIN, 16));
            menuLink.setForeground(Color.WHITE); // Menjadikan teks hitam
            menuLink.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Menambahkan efek kursor tangan saat hover
            menuLink.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Aksi saat link diklik
                    JOptionPane.showMessageDialog(homeFrame, "Menu '" + item + "' clicked.");
                }
            });
            sidebarPanel.add(Box.createVerticalStrut(10)); // Spasi antar link
            sidebarPanel.add(menuLink);
        }

        // Panel Konten Tengah
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);

        // Tambahkan konten tengah
        JLabel welcomeLabel = new JLabel("Selamat Datang");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        contentPanel.add(welcomeLabel);

        // Tambahkan panel ke frame
        homeFrame.add(sidebarPanel, BorderLayout.WEST);
        homeFrame.add(contentPanel, BorderLayout.CENTER);

        // Tampilkan frame
        homeFrame.setLocationRelativeTo(null); // Pusatkan frame
        homeFrame.setVisible(true);
    }

    // Fungsi utama untuk menjalankan program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(home::showHomePage);
    }
}
