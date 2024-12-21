import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpForm {

    // Halaman untuk Reset Password
    public static void showResetPasswordForm() {
        JFrame resetPasswordFrame = new JFrame("Reset Password");
        resetPasswordFrame.setSize(400, 300);
        resetPasswordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resetPasswordFrame.setLayout(new GridBagLayout());
        resetPasswordFrame.setLocationRelativeTo(null);

        // Layout GridBag
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Komponen Reset Password
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password Baru:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton resetButton = new JButton("Reset");
        resetButton.setSize(new Dimension(50, 25)); // Ukuran tombol dipendekkan

        JPanel formPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        formPanel.add(resetButton, gbc);

        // Aksi Tombol Reset Password
        resetButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(resetPasswordFrame, "Mohon lengkapi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(resetPasswordFrame, "Password berhasil direset!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                resetPasswordFrame.dispose();
                // Panggil halaman login jika ada
            }
        });

        resetPasswordFrame.add(formPanel);
        resetPasswordFrame.setVisible(true);
    }

    public static void showSignUpForm() {
        // Frame Utama untuk Form Sign-Up
        JFrame signUpFrame = new JFrame("Sign-Up Form");
        signUpFrame.setSize(400, 400);
        signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUpFrame.setLayout(new GridBagLayout());
        signUpFrame.setLocationRelativeTo(null);

        // Layout GridBag
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Komponen Form
        JLabel nameLabel = new JLabel("Nama:");
        JTextField nameField = new JTextField(20);

        JLabel nimNipLabel = new JLabel("NIM/NIP:");
        JTextField nimNipField = new JTextField(20);

        JLabel professionLabel = new JLabel("Profesi:");
        String[] professions = {"Pilih Profesi", "Mahasiswa", "Dosen"};
        JComboBox<String> professionBox = new JComboBox<>(professions);

        JLabel classLabel = new JLabel("Kelas:");
        String[] classes = {"Pilih Kelas", "Pagi", "Malam"};
        JComboBox<String> classBox = new JComboBox<>(classes);
        classBox.setEnabled(false); // Disabled by default

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        // Tombol Sign-Up
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(20, 30)); // Ukuran tombol dipendekkan

        // Panel Form
        JPanel formPanel = new JPanel(new GridBagLayout());

        // Menambahkan Komponen ke Panel
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(nimNipLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nimNipField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(professionLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(professionBox, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(classLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(classBox, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        formPanel.add(signUpButton, gbc);

        // Aksi tombol Sign-Up
        signUpButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String nimNip = nimNipField.getText().trim();
            String profession = (String) professionBox.getSelectedItem();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String selectedClass = (String) classBox.getSelectedItem();

            if (name.isEmpty() || nimNip.isEmpty() || username.isEmpty() || password.isEmpty() || profession.equals("Pilih Profesi") || (profession.equals("Mahasiswa") && selectedClass.equals("Pilih Kelas"))) {
                JOptionPane.showMessageDialog(signUpFrame, "Mohon lengkapi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(signUpFrame, "Sign-Up berhasil!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                signUpFrame.dispose();
                // Panggil halaman login jika ada
            }
        });

        // Menambahkan Listener untuk menangani perubahan pilihan profesi
        professionBox.addActionListener(e -> {
            if (professionBox.getSelectedItem().equals("Mahasiswa")) {
                classBox.setEnabled(true);  // Mengaktifkan pilihan kelas
            } else {
                classBox.setEnabled(false);  // Menonaktifkan pilihan kelas
            }
        });

        // Tambahkan Panel ke Frame
        signUpFrame.add(formPanel);
        signUpFrame.setVisible(true);
    }

    public static void main(String[] args) {
        showSignUpForm();
    }
}
