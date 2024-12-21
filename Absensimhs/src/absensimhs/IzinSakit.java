import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IzinSakit {
    public static void showIzinSakitPage() {
        // Frame Utama
        JFrame izinFrame = new JFrame("Halaman Izin Sakit");
        izinFrame.setSize(600, 400); // Ukuran frame
        izinFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        izinFrame.setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(51, 51, 51));
        sidebarPanel.setPreferredSize(new Dimension(200, izinFrame.getHeight()));

        // Tambahkan menu ke sidebar
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
                    case "Home":
                        izinFrame.dispose();
                        JOptionPane.showMessageDialog(izinFrame, "Menu 'Home' diklik.");
                        Home.showHomePage("Mahasiswa");
                        break;
                    case "Ajukan Absen":
                        JOptionPane.showMessageDialog(izinFrame, "Menu 'Ajukan Absen' diklik.");
                        AjukanAbsen.showAjukanAbsenPage();
                        break;
                    case "Izin Sakit":
                        JOptionPane.showMessageDialog(izinFrame, "Anda sudah berada di halaman Izin Sakit.");
                        break;
                    case "Jadwal":
                        JOptionPane.showMessageDialog(izinFrame, "Menu 'Jadwal' diklik.");
                        Jadwal.showJadwalPage();
                        break;
                }
            });

            sidebarPanel.add(Box.createVerticalStrut(10)); // Spasi antar tombol
            sidebarPanel.add(menuButton);
        }

        // Panel Konten Utama
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel Form Kontainer dengan layout yang fleksibel
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS)); // Menggunakan BoxLayout untuk mengatur form secara vertikal
        formContainer.setBackground(new Color(240, 240, 240)); // Warna latar abu-abu terang
        formContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100), 2), // Bingkai luar
                BorderFactory.createEmptyBorder(15, 15, 15, 15) // Padding dalam
        ));

        // Ukuran kontainer akan mengikuti ukuran layar
        formContainer.setPreferredSize(new Dimension(50, 50)); // Ukuran minimum awal
        formContainer.setMinimumSize(new Dimension(50, 50)); // Ukuran minimum
        formContainer.setMaximumSize(new Dimension(50, 50)); // Ukuran maksimum yang diizinkan

        // Judul Halaman
        JLabel titleLabel = new JLabel("Izin Sakit");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Ukuran font lebih kecil
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formContainer.add(titleLabel);
        formContainer.add(Box.createVerticalStrut(20));

        // Form Input
        JTextField nameField = new JTextField();
        JTextField reasonField = new JTextField();

        // Field tanggal dengan opsi manual atau pilih kalender
        JPanel datePanel = new JPanel(new BorderLayout());
        JTextField dateField = new JTextField();
        dateField.setPreferredSize(new Dimension(200, 30));
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Yoga\\Documents\\NetBeansProjects\\Absensimhs\\src\\img\\Tanggal.png");
        JButton datePickerButton = new JButton(logoIcon);

        // Tampilkan dialog kalender saat tombol diklik
        datePickerButton.addActionListener(e -> {
            JDatePicker picker = new JDatePicker();
            int result = JOptionPane.showConfirmDialog(izinFrame, picker, "Pilih Tanggal", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                dateField.setText(dateFormat.format(picker.getSelectedDate()));
            }
        });

        datePanel.add(dateField, BorderLayout.CENTER);
        datePanel.add(datePickerButton, BorderLayout.EAST);

        formContainer.add(createFormRow("Nama:", nameField));
        formContainer.add(Box.createVerticalStrut(10));
        formContainer.add(createFormRow("Tanggal:", datePanel));
        formContainer.add(Box.createVerticalStrut(10));
        formContainer.add(createFormRow("Alasan:", reasonField));
        formContainer.add(Box.createVerticalStrut(20));

        // Tombol Kirim
        JButton submitButton = new JButton("Kirim Izin");
        submitButton.setBackground(new Color(0, 123, 255));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String date = dateField.getText();
            String reason = reasonField.getText();

            if (name.isEmpty() || date.isEmpty() || reason.isEmpty()) {
                JOptionPane.showMessageDialog(izinFrame, "Harap isi semua field!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(izinFrame, "Izin dikirim untuk " + name + " pada tanggal " + date + " dengan alasan: " + reason);
                izinFrame.dispose();
            }
        });

        formContainer.add(submitButton);

        // Tambahkan Kontainer ke Panel Utama
        contentPanel.add(formContainer, BorderLayout.CENTER);

        // Tambahkan Sidebar dan Konten ke Frame
        izinFrame.add(sidebarPanel, BorderLayout.WEST);
        izinFrame.add(contentPanel, BorderLayout.CENTER);

        // Tampilkan Frame
        izinFrame.setLocationRelativeTo(null);
        izinFrame.setVisible(true);
    }

    private static JPanel createFormRow(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setMaximumSize(new Dimension(400, 40));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(80, 30));
        panel.add(label, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(IzinSakit::showIzinSakitPage);
    }
}

class JDatePicker extends JPanel {
    private JSpinner spinner;

    public JDatePicker() {
        setLayout(new BorderLayout());
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd-MM-yyyy");
        spinner.setEditor(editor);
        add(spinner, BorderLayout.CENTER);
    }

    public Date getSelectedDate() {
        return (Date) spinner.getValue();
    }
}
