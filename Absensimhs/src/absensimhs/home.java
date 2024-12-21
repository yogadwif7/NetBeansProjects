import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home {

    // Method untuk menampilkan halaman home dengan tipe pengguna
    public static void showHomePage(String userType) {
        // Frame untuk halaman home
        JFrame homeFrame = new JFrame("Home Page");
        homeFrame.setSize(800, 600); // Ukuran frame home
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setLayout(new BorderLayout()); // Layout BorderLayout

        // Panel Sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(50, 50, 50)); // Warna gelap
        sidebarPanel.setPreferredSize(new Dimension(200, homeFrame.getHeight()));
        sidebarPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Menambahkan panel untuk logo dengan background lingkaran putih
        JPanel logoPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int circleDiameter = 140; // Ukuran lingkaran
                int x = (getWidth() - circleDiameter) / 2;
                int y = 20; // Posisi ke atas
                g.setColor(Color.WHITE);
                g.fillOval(x, y, circleDiameter, circleDiameter); // Lingkaran putih
            }
        };
        logoPanel.setPreferredSize(new Dimension(200, 160));
        logoPanel.setOpaque(false);

        // Gambar ikon di tengah lingkaran
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Yoga\\Documents\\NetBeansProjects\\Absensimhs\\src\\img\\man.png");
        JLabel logoLabel = new JLabel(logoIcon);
        int imageWidth = 80;
        int imageHeight = 80;
        logoLabel.setBounds(60, 50, imageWidth, imageHeight); // Pusatkan gambar
        logoPanel.add(logoLabel);

        // Menambahkan logo ke sidebar
        sidebarPanel.add(logoPanel);

        // Tambahkan menu di sidebar berdasarkan userType
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(10, 1, 10, 10)); // GridLayout dinamis
        menuPanel.setBackground(new Color(50, 50, 50)); // Warna latar menu sama dengan sidebar
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Atur jarak atas menu

        String[] menuItems;
        if (userType.equalsIgnoreCase("Dosen")) {
            menuItems = new String[]{"Cek Absen", "Daftar Nama", "Jadwal"};
        } else if (userType.equalsIgnoreCase("Mahasiswa")) {
            menuItems = new String[]{"Ajukan Absen", "Jadwal", "Izin Sakit"};
        } else {
            menuItems = new String[]{"Menu Tidak Tersedia"};
        }

        for (String item : menuItems) {
            JLabel menuLink = new JLabel(item);
            menuLink.setHorizontalAlignment(SwingConstants.CENTER);
            menuLink.setFont(new Font("Arial", Font.PLAIN, 16));
            menuLink.setForeground(Color.WHITE);
            menuLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

            menuLink.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    homeFrame.dispose(); // Tutup halaman home saat berpindah
                    switch (item) {
                        case "Ajukan Absen":
                            AjukanAbsen.showAjukanAbsenPage();
                            break;
                        case "Izin Sakit":
                            IzinSakit.showIzinSakitPage();
                            break;
                        case "Cek Absen":
                            CekAbsen.showCekAbsenPage();
                            break;
                        case "Daftar Nama":
                            DaftarNama.showDaftarNamaPage();
                            break;
                        case "Jadwal":
                            Jadwal.showJadwalPage();
                            break;
                        default:
                            JOptionPane.showMessageDialog(homeFrame, "Menu '" + item + "' diklik.");
                    }
                }
            });
            menuPanel.add(menuLink);
        }

        sidebarPanel.add(menuPanel);

        // Panel Konten Tengah
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);

        // Tambahkan konten tengah
        JLabel welcomeLabel = new JLabel("Selamat Datang, " + userType + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        contentPanel.add(welcomeLabel);

        // Log Out Button
        JButton logOutButton = new JButton("Log Out");
        logOutButton.setBackground(Color.WHITE);
        logOutButton.setForeground(Color.BLACK);
        logOutButton.setFont(new Font("Arial", Font.PLAIN, 14));
        logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logOutButton.setBorder(BorderFactory.createLineBorder(new Color(255, 69, 0), 2));
        logOutButton.addActionListener(e -> {
            homeFrame.dispose();
            JOptionPane.showMessageDialog(null, "Anda telah keluar.");
        });

        // Panel untuk tombol log out (menggunakan FlowLayout)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setOpaque(false);
        topPanel.add(logOutButton);

        // Menambahkan panel ke frame
        homeFrame.add(sidebarPanel, BorderLayout.WEST);
        homeFrame.add(contentPanel, BorderLayout.CENTER);
        homeFrame.add(topPanel, BorderLayout.NORTH);

        // Tampilkan frame
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showHomePage("Mahasiswa"));
    }

    // Method tambahan dari kode sebelumnya
    public static void showHomePageSimple(String userType) {
        // Kode untuk menampilkan halaman Home
        JFrame homeFrame = new JFrame("Halaman Home");
        homeFrame.setSize(600, 400);
        homeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel label = new JLabel("Halaman Home - User Type: " + userType);
        homeFrame.add(label);
        homeFrame.setVisible(true);
    }
}
