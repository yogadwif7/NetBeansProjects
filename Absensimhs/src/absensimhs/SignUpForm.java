import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpForm {

    public static void showSignUpForm() {
        // Frame Utama untuk Form Sign-Up
        JFrame signUpFrame = new JFrame("Sign-Up Form");
        signUpFrame.setSize(400, 500);
        signUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signUpFrame.setLayout(new GridBagLayout());
        signUpFrame.setLocationRelativeTo(null);

        // Layout GridBag
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Komponen Form
        JLabel nameLabel = new JLabel("Nama:");
        JTextField nameField = new JTextField(20);

        JLabel nimLabel = new JLabel("NIM/NIP:");
        JTextField nimField = new JTextField(20);

        JLabel professionLabel = new JLabel("Profesi:");
        String[] professions = {"Pilih Profesi", "Mahasiswa", "Dosen"};
        JComboBox<String> professionBox = new JComboBox<>(professions);

        // Komponen khusus Mahasiswa (Kelas dan Prodi)
        JLabel classLabel = new JLabel("Kelas:");
        String[] classes = {"Pagi", "Malam"};
        JComboBox<String> classBox = new JComboBox<>(classes);

        JLabel prodiLabel = new JLabel("Prodi:");
        JTextField prodiField = new JTextField(20);

        // Tombol Sign-Up
        JButton signUpButton = new JButton("Sign Up");

        // Panel Form
        JPanel formPanel = new JPanel(new GridBagLayout());

        // Menambahkan Komponen ke Panel
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(nimLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nimField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(professionLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(professionBox, gbc);

        // Komponen lanjutan: Kelas
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(classLabel, gbc);
        classLabel.setVisible(false); // Disembunyikan saat awal
        gbc.gridx = 1;
        formPanel.add(classBox, gbc);
        classBox.setVisible(false);

        // Komponen lanjutan: Prodi
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(prodiLabel, gbc);
        prodiLabel.setVisible(false); // Disembunyikan saat awal
        gbc.gridx = 1;
        formPanel.add(prodiField, gbc);
        prodiField.setVisible(false);

        // Tombol Sign-Up
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        formPanel.add(signUpButton, gbc);

        // Logika untuk menampilkan input lanjutan
        professionBox.addActionListener(e -> {
            if (professionBox.getSelectedItem().equals("Mahasiswa")) {
                classLabel.setVisible(true);
                classBox.setVisible(true);
                prodiLabel.setVisible(true);
                prodiField.setVisible(true);
            } else {
                classLabel.setVisible(false);
                classBox.setVisible(false);
                prodiLabel.setVisible(false);
                prodiField.setVisible(false);
            }
        });

        // Aksi tombol Sign-Up
        signUpButton.addActionListener(e -> {
            String name = nameField.getText();
            String nim = nimField.getText();
            String profession = (String) professionBox.getSelectedItem();
            String kelas = (String) classBox.getSelectedItem();
            String prodi = prodiField.getText();

            if (name.isEmpty() || nim.isEmpty() || profession.equals("Pilih Profesi")) {
                JOptionPane.showMessageDialog(signUpFrame, "Mohon lengkapi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else if (profession.equals("Mahasiswa") && (kelas == null || prodi.isEmpty())) {
                JOptionPane.showMessageDialog(signUpFrame, "Mohon lengkapi data kelas dan prodi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(signUpFrame, "Sign-Up Berhasil!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                signUpFrame.dispose(); // Menutup form setelah berhasil
            }
        });

        // Tambahkan Panel ke Frame
        signUpFrame.add(formPanel);
        signUpFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // Frame Utama untuk Link Sign-Up
        JFrame mainFrame = new JFrame("Login Page");
        mainFrame.setSize(300, 200);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setLocationRelativeTo(null);

        JLabel signUpLabel = new JLabel("<HTML><U>Don't have an account?</U></HTML>");
        signUpLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Event ketika label di-klik
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showSignUpForm();
            }
        });

        mainFrame.add(signUpLabel);
        mainFrame.setVisible(true);
    }
}

