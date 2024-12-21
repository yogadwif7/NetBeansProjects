import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loginform {

    public static void main(String[] args) {
        // Menampilkan halaman login
        showLoginPage();
    }

    // Method untuk menampilkan halaman login
    public static void showLoginPage() {
        // Frame utama untuk login
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel utama dengan GridBagLayout
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

        // Ikon untuk Show/Hide Password
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
            boolean isPasswordVisible = passwordField.getEchoChar() == '\u0000'; // Cek apakah password terlihat
            passwordField.setEchoChar(isPasswordVisible ? '\u25CF' : '\u0000'); // Jika terlihat, sembunyikan dengan karakter '●'
            togglePasswordButton.setIcon(isPasswordVisible ? eyeClosedIcon : eyeOpenIcon); // Ganti ikon sesuai status
        });

        // Tombol Login Semi Persegi Panjang
        JButton loginButton = new RoundedButton("Login");
        loginButton.setBounds(80, 230, 150, 40); // Ukuran dan posisi tombol
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(60, 179, 113)); // Warna hijau
        loginButton.setFocusPainted(false);
        loginPanel.add(loginButton);

        // Aksi Tombol Login
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim(); // Ambil input username
            String password = new String(passwordField.getPassword()).trim(); // Ambil input password

            // Validasi login
            if (username.equals("yoga") && password.equals("123")) {
                JOptionPane.showMessageDialog(frame, "Login Berhasil! Anda adalah Mahasiswa.");
                frame.dispose();
                Home.showHomePage("Mahasiswa"); // Panggil halaman home dengan parameter "Mahasiswa"
            } else if (username.equals("andre") && password.equals("321")) {
                JOptionPane.showMessageDialog(frame, "Login Berhasil! Anda adalah Dosen.");
                frame.dispose();
                Home.showHomePage("Dosen"); // Panggil halaman home dengan parameter "Dosen"
            } else if (!username.equals("yoga") && !username.equals("andre") && !password.equals("123") && !password.equals("321")) {
                JOptionPane.showMessageDialog(frame, "Username dan Password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            } else if (!username.equals("yoga") && !username.equals("andre")) {
                JOptionPane.showMessageDialog(frame, "Username salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Forgot Password Link
        JLabel forgotPasswordLabel = new JLabel("<HTML>Lupa Password?</HTML>");
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotPasswordLabel.setForeground(Color.BLUE);
        forgotPasswordLabel.setBounds(180, 185, 100, 20); // Below the password field
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                SignUpForm.showResetPasswordForm(); // Memanggil form Reset Password
            }
        });
        loginPanel.add(forgotPasswordLabel);

        // Link "Don't have an account?"
        JLabel signUpLabel = new JLabel("<HTML>Don't have an account?</HTML>");
        signUpLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setBounds(90, 280, 150, 20); // Letak link di bawah tombol login
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                SignUpForm.showSignUpForm(); // Panggil halaman Sign-Up
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
