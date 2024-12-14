import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loginform {

    public static void main(String[] args) {
        // Show the login page initially
        showLoginPage();
    }

    // Method to display the login page
    public static void showLoginPage() {
        // Frame utama untuk login
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel utama dengan GridBagLayout untuk memastikan tabel login tetap di tengah
        JPanel panel = new JPanel(new GridBagLayout()); // Deklarasi dan inisialisasi panel
        panel.setBackground(new Color(144, 238, 144)); // Hijau muda
        frame.add(panel, BorderLayout.CENTER);

        // Panel login dengan ukuran tetap
        JPanel loginPanel = new JPanel(null); // Menggunakan null layout untuk posisi absolut
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setPreferredSize(new Dimension(300, 400)); // Ukuran tetap untuk tabel login

        // Label Login (Center title)
        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(120, 10, 100, 30); // Manually centering the title
        loginPanel.add(titleLabel);

        // Label Username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setBounds(20, 50, 100, 30);
        loginPanel.add(usernameLabel);

        // Field Username
        JTextField usernameField = new JTextField();
        usernameField.setBounds(20, 80, 260, 30);
        loginPanel.add(usernameField);

        // Label Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(20, 120, 100, 30);
        loginPanel.add(passwordLabel);

        // Field Password
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20, 150, 230, 30);
        loginPanel.add(passwordField);

        // Tombol Show/Hide Password dengan ikon mata
        ImageIcon eyeClosedIcon = new ImageIcon("C:\\Users\\Yoga\\Documents\\NetBeansProjects\\Absensimhs\\src\\img\\ClosedEye.png");
        ImageIcon eyeOpenIcon = new ImageIcon("C:\\Users\\Yoga\\Documents\\NetBeansProjects\\Absensimhs\\src\\img\\Eye.png");
        JButton togglePasswordButton = new JButton(eyeClosedIcon);
        togglePasswordButton.setFocusPainted(false);
        togglePasswordButton.setContentAreaFilled(false);
        togglePasswordButton.setBorderPainted(false);
        togglePasswordButton.setBounds(250, 150, 30, 30);
        loginPanel.add(togglePasswordButton);

        // Fitur Show/Hide Password
        togglePasswordButton.addActionListener(e -> {
            boolean isPasswordVisible = passwordField.getEchoChar() == 0;
            passwordField.setEchoChar(isPasswordVisible ? '\u25CF' : (char) 0);
            togglePasswordButton.setIcon(isPasswordVisible ? eyeClosedIcon : eyeOpenIcon);
        });

        // Tombol Login Semi Persegi Panjang
        JButton loginButton = new RoundedButton("Login");
        loginButton.setBounds(70, 220, 150, 50); // Ukuran dan posisi tombol
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(60, 179, 113)); // Warna hijau
        loginButton.setFocusPainted(false);
        loginPanel.add(loginButton);

        // Aksi Tombol Login
        loginButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Login Berhasil!");
            frame.dispose(); // Close login page

            // Pindahkan ke halaman home setelah login berhasil
            home.showHomePage();  // Panggil metode untuk menampilkan halaman home
        });

        // Forgot Password Link
        JLabel forgotPasswordLabel = new JLabel("<HTML><U>Lupa Password?</U></HTML>");
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotPasswordLabel.setForeground(Color.BLUE);
        forgotPasswordLabel.setBounds(180, 185, 100, 20); // Below the password field
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Redirecting to Forgot Password Page...");
            }
        });
        loginPanel.add(forgotPasswordLabel);

        // Link "Don't have an account?" (Center link)
        JLabel signUpLabel = new JLabel("<HTML><U>Don't have an account?</U></HTML>");
        signUpLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        signUpLabel.setForeground(Color.BLUE);
        
        // Dynamically center the "Don't have an account?" label
        Dimension size = signUpLabel.getPreferredSize();
        int signUpX = (loginPanel.getWidth() - size.width) / 2; // Center the label horizontally based on its width
        signUpLabel.setBounds(80, 280, 170, 40);
        
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Redirecting to Sign-Up Page...");
            }
        });
        loginPanel.add(signUpLabel);

        // Tambahkan loginPanel ke panel utama
        panel.add(loginPanel);

        // Tampilkan Frame
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Pusatkan jendela
        frame.setVisible(true);
    }

    // Class Custom Button Semi Persegi Panjang
    static class RoundedButton extends JButton {
        public RoundedButton(String label) {
            super(label);
            setContentAreaFilled(false); // Menghilangkan latar belakang default
            setFocusPainted(false);      // Menghilangkan efek fokus
            setBorderPainted(false);     // Menghilangkan border default
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Warna tombol dengan sudut membulat
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Sudut membulat 20 piksel

            // Warna teks
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            // Tidak menggambar border
        }
    }
}
