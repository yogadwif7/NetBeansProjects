import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DaftarNama {
    public static void showDaftarNamaPage() {
        // Frame utama
        JFrame frame = new JFrame("Daftar Nama");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(51, 51, 51));
        sidebarPanel.setPreferredSize(new Dimension(200, 600)); // Ukuran sidebar

        // Menambahkan tombol menu ke sidebar
        String[] menuItems = {"Home", "Daftar Nama", "Jadwal", "Cek Absen"};
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
                        break;
                    case "Daftar Nama":
                        JOptionPane.showMessageDialog(frame, "Anda sudah berada di halaman Daftar Nama.");
                        break;
                    case "Jadwal":
                        frame.dispose();
                        JOptionPane.showMessageDialog(frame, "Menu 'Jadwal' diklik.");
                        break;
                    case "Cek Absen":
                        frame.dispose();
                        JOptionPane.showMessageDialog(frame, "Menu 'Cek Absen' diklik.");
                        break;
                }
            });

            sidebarPanel.add(Box.createVerticalStrut(10)); // Spasi antar tombol
            sidebarPanel.add(menuButton);
        }

        // Konten tengah dengan tabel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        // Label header
        JLabel headerLabel = new JLabel("Daftar Nama", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(headerLabel, BorderLayout.NORTH);

        // Data tabel menggunakan DefaultTableModel
        String[] columnNames = {"Nama", "NIM", "Prodi"};
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{
                {"Ali", "123456", "Informatika"},
                {"Budi", "654321", "Sistem Informasi"},
                {"Citra", "789123", "Teknik Elektro"},
                {"Dewi", "321987", "Manajemen"}
        }, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel untuk tombol aksi
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Tombol tambah (+)
        JButton addButton = new JButton(new ImageIcon("C:\\Users\\Yoga\\Documents\\NetBeansProjects\\Absensimhs\\src\\img\\Buat.png"));
        addButton.setToolTipText("Tambah Data");
        addButton.setPreferredSize(new Dimension(100, 30));
        addButton.addActionListener(e -> {
            JTextField namaField = new JTextField(15);
            JTextField nimField = new JTextField(15);
            JTextField prodiField = new JTextField(15);

            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(3, 2));
            formPanel.add(new JLabel("Nama: "));
            formPanel.add(namaField);
            formPanel.add(new JLabel("NIM: "));
            formPanel.add(nimField);
            formPanel.add(new JLabel("Prodi: "));
            formPanel.add(prodiField);

            int result = JOptionPane.showConfirmDialog(frame, formPanel, "Tambah Data", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String nama = namaField.getText();
                String nim = nimField.getText();
                String prodi = prodiField.getText();

                if (!nama.isEmpty() && !nim.isEmpty() && !prodi.isEmpty()) {
                    // Tambahkan data ke tabel
                    tableModel.addRow(new Object[]{nama, nim, prodi});
                    JOptionPane.showMessageDialog(frame, "Data berhasil ditambahkan!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Semua kolom harus diisi.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Tombol hapus
        JButton deleteButton = new JButton(new ImageIcon("C:\\Users\\Yoga\\Documents\\NetBeansProjects\\Absensimhs\\src\\img\\Hapus.png"));
        deleteButton.setToolTipText("Hapus Data");
        deleteButton.setPreferredSize(new Dimension(100, 30));
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(frame, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(frame, "Pilih baris yang ingin dihapus.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Tombol update
        JButton updateButton = new JButton(new ImageIcon("C:\\Users\\Yoga\\Documents\\NetBeansProjects\\Absensimhs\\src\\img\\Update.png"));
        updateButton.setToolTipText("Update Data");
        updateButton.setPreferredSize(new Dimension(100, 30));
        updateButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String currentNama = (String) tableModel.getValueAt(selectedRow, 0);
                String currentNim = (String) tableModel.getValueAt(selectedRow, 1);
                String currentProdi = (String) tableModel.getValueAt(selectedRow, 2);

                JTextField namaField = new JTextField(currentNama, 15);
                JTextField nimField = new JTextField(currentNim, 15);
                JTextField prodiField = new JTextField(currentProdi, 15);

                JPanel formPanel = new JPanel();
                formPanel.setLayout(new GridLayout(3, 2));
                formPanel.add(new JLabel("Nama: "));
                formPanel.add(namaField);
                formPanel.add(new JLabel("NIM: "));
                formPanel.add(nimField);
                formPanel.add(new JLabel("Prodi: "));
                formPanel.add(prodiField);

                int result = JOptionPane.showConfirmDialog(frame, formPanel, "Update Data", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    tableModel.setValueAt(namaField.getText(), selectedRow, 0);
                    tableModel.setValueAt(nimField.getText(), selectedRow, 1);
                    tableModel.setValueAt(prodiField.getText(), selectedRow, 2);
                    JOptionPane.showMessageDialog(frame, "Data berhasil diperbarui!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Pilih baris yang ingin diperbarui.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        });

        actionPanel.add(addButton);
        actionPanel.add(deleteButton);
        actionPanel.add(updateButton);

        contentPanel.add(actionPanel, BorderLayout.SOUTH);

        // Menambahkan Sidebar dan Konten ke Frame
        frame.add(sidebarPanel, BorderLayout.WEST);
        frame.add(contentPanel, BorderLayout.CENTER);

        // Menampilkan frame di tengah layar
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DaftarNama::showDaftarNamaPage);
    }
}
