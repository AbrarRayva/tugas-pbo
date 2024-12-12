import java.util.*;
import java.util.Date;
import java.text.*;
import java.sql.*;

public class App {
    static String kasir;

    static String savedUsername = "admin";
    static String savedPassword = "password";
    static String savedCaptcha;

    static String inputUsername;
    static String inputPassword;
    static String inputCaptcha;

    static Captcha captcha = new Captcha();

    static int noFaktur;
    static String kodeBarang;
    static String namaBarang;
    static int hargaBarang = 0;
    static int jumlahBeli = 0;

    private static void login(Scanner scanner) {
        while (true) {
            savedCaptcha = captcha.generate(5);

            System.out.println("Log In");
            System.out.println("+-----------------------------------------------------+");
            System.out.print("Username            : ");
            inputUsername = scanner.nextLine();
            System.out.print("Password            : ");
            inputPassword = scanner.nextLine();
            System.out.print("Captcha (" + savedCaptcha + ")     : ");
            inputCaptcha = scanner.nextLine().trim();

            // Contoh Method String
            if (!inputCaptcha.equalsIgnoreCase(savedCaptcha)) {
                System.out.println("\nCaptcha gagal.\n");
                continue;
            }

            if (!inputUsername.equals(savedUsername) || !inputPassword.equals(savedPassword)) {
                System.out.println("\nLogin gagal silakan diulangi.\n");
            } else {
                System.out.println("\nLogin berhasil.");
                break;
            }
        }

        System.out.print("Kasir               : ");
        kasir = scanner.nextLine().trim();
        System.out.println("+-----------------------------------------------------+\n");
    }

    private static void tambahTransaksi(Scanner scanner, Connection conn) {
        System.out.println("\nTambah Transaksi");
        System.out.println("+-----------------------------------------------------+");

        System.out.print("Masukkan Kode Barang: ");
        kodeBarang = scanner.nextLine().trim();

        System.out.print("Masukkan Nama Barang: ");
        namaBarang = scanner.nextLine().trim();

        while (true) {
            try {
                System.out.print("Masukkan Harga Barang: ");
                hargaBarang = Integer.parseInt(scanner.nextLine().trim());
                if (hargaBarang <= 0) {
                    System.out.println("Harga barang harus lebih dari 0.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka yang sesuai.");
            }
        }

        while (true) {
            try {
                System.out.print("Masukkan Jumlah Beli: ");
                jumlahBeli = Integer.parseInt(scanner.nextLine().trim());
                if (jumlahBeli <= 0) {
                    System.out.println("Jumlah beli harus lebih dari 0.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka yang sesuai.");
            }
        }

        Transaksi transaksi = new Transaksi(kodeBarang, namaBarang, hargaBarang, jumlahBeli);

        String sql = "INSERT INTO transaksi (kode_barang, nama_barang, harga_barang, jumlah_beli, total) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, transaksi.getKode());
            pstmt.setString(2, transaksi.getNama());
            pstmt.setInt(3, transaksi.getHarga());
            pstmt.setInt(4, transaksi.getjumlahBeli());
            pstmt.setInt(5, transaksi.getTotal());
            pstmt.executeUpdate();

            System.out.println("Transaksi berhasil ditambahkan!");
            System.out.println("+-----------------------------------------------------+");
            cetakStruk(transaksi);
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan transaksi: " + e.getMessage());
        }
    }

    private static void lihatTransaksi(Scanner scanner, Connection conn) {
        String sql = "SELECT * FROM transaksi";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\nDaftar Transaksi");
            System.out.println("+-----------------------------------------------------+");

            while (rs.next()) {
                System.out.println("No Faktur       : " + rs.getString("no_faktur"));
                System.out.println("Kode Barang     : " + rs.getString("kode_barang"));
                System.out.println("Nama Barang     : " + rs.getString("nama_barang"));
                System.out.println("Harga Barang    : " + rs.getDouble("harga_barang"));
                System.out.println("Jumlah Beli     : " + rs.getInt("jumlah_beli"));
                System.out.println("Total           : " + rs.getDouble("total"));
                System.out.println("+-----------------------------------------------------+");
            }
        } catch (SQLException e) {
            System.out.println("Gagal membaca transaksi: " + e.getMessage());
        }
    }

    private static void updateTransaksi(Scanner scanner, Connection conn) {
        System.out.println("\nUpdate Transaksi");
        System.out.println("+-----------------------------------------------------+");

        int noFaktur;
        while (true) {
            try {
                System.out.print("Masukkan No. Faktur: ");
                noFaktur = Integer.parseInt(scanner.nextLine().trim());
                if (noFaktur <= 0) {
                    System.out.println("No. Faktur harus lebih dari 0.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka untuk No. Faktur.");
            }
        }

        System.out.print("Masukkan Kode Barang baru: ");
        String kodeBarang = scanner.nextLine().trim();

        System.out.print("Masukkan Nama Barang baru: ");
        String namaBarang = scanner.nextLine().trim();

        int hargaBarang;
        while (true) {
            try {
                System.out.print("Masukkan Harga Barang baru: ");
                hargaBarang = Integer.parseInt(scanner.nextLine().trim());
                if (hargaBarang <= 0) {
                    System.out.println("Harga barang harus lebih dari 0.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka yang sesuai.");
            }
        }

        int jumlahBeli;
        while (true) {
            try {
                System.out.print("Masukkan Jumlah Beli baru: ");
                jumlahBeli = Integer.parseInt(scanner.nextLine().trim());
                if (jumlahBeli <= 0) {
                    System.out.println("Jumlah beli harus lebih dari 0.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka yang sesuai.");
            }
        }

        int total = hargaBarang * jumlahBeli;

        String sql = "UPDATE transaksi SET kode_barang = ?, nama_barang = ?, harga_barang = ?, jumlah_beli = ?, total = ? WHERE no_faktur = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kodeBarang);
            pstmt.setString(2, namaBarang);
            pstmt.setInt(3, hargaBarang);
            pstmt.setInt(4, jumlahBeli);
            pstmt.setInt(5, total);
            pstmt.setInt(6, noFaktur);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Transaksi berhasil diubah!");
            } else {
                System.out.println("No. Faktur tidak ditemukan.");
            }

            System.out.println("+-----------------------------------------------------+");
        } catch (SQLException e) {
            System.out.println("Gagal mengubah transaksi: " + e.getMessage());
        }
    }

    private static void hapusTransaksi(Scanner scanner, Connection conn) {
        System.out.println("\nHapus Transaksi");
        System.out.println("+-----------------------------------------------------+");

        while (true) {
            try {
                System.out.print("Masukkan No. Faktur: ");
                noFaktur = Integer.parseInt(scanner.nextLine().trim());
                if (noFaktur <= 0) {
                    System.out.println("No. Faktur harus lebih dari 0.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka untuk No. Faktur.");
            }
        }

        String sql = "DELETE FROM transaksi WHERE no_faktur = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, noFaktur);
            pstmt.executeUpdate();

            System.out.println("Transaksi berhasil dihapus!");
            System.out.println("+-----------------------------------------------------+");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus transaksi: " + e.getMessage());
        }
    }

    private static void cetakStruk(Transaksi transaksi) {
        SimpleDateFormat formatWaktu = new SimpleDateFormat("dd.MM.yyyy hh:mm::ss");

        System.out.println("\nSelamat Datang di Supermarket Allium");
        System.out.println("Tanggal dan Waktu : " + formatWaktu.format(new Date()));
        System.out.println("+-----------------------------------------------------+");
        System.out.println("No Faktur    : " + noFaktur);
        System.out.println("Kode Barang  : " + transaksi.getKode());
        System.out.println("Nama Barang  : " + transaksi.getNama());
        System.out.println("Harga Barang : " + transaksi.getHarga());
        System.out.println("Jumlah Beli  : " + transaksi.getjumlahBeli());
        System.out.println("TOTAL        : " + transaksi.getTotal());
        System.out.println("+-----------------------------------------------------+");
        System.out.println("Kasir        : " + kasir);
        System.out.println("+-----------------------------------------------------+");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;

        try {
            conn = KonektorDatabase.getConnection();
            login(scanner);

            boolean bukaMenu = true;

            while (bukaMenu) {
                System.out.println("\nMenu CRUD:");
                System.out.println("+-----------------------------------------------------+");
                System.out.println("1. Tambah Transaksi");
                System.out.println("2. Lihat Transaksi");
                System.out.println("3. Update Transaksi");
                System.out.println("4. Hapus Transaksi");
                System.out.println("5. Keluar");
                System.out.println("+-----------------------------------------------------+");
                System.out.print("Pilih menu: ");

                try {
                    int pilihan = scanner.nextInt();
                    scanner.nextLine();

                    switch (pilihan) {
                        case 1:
                            tambahTransaksi(scanner, conn);
                            break;
                        case 2:
                            lihatTransaksi(scanner, conn);
                            break;
                        case 3:
                            updateTransaksi(scanner, conn);
                            break;
                        case 4:
                            hapusTransaksi(scanner, conn);
                            break;
                        case 5:
                            System.out.println("Sampai jumpa!");
                            bukaMenu = false;
                            break;
                        default:
                            System.out.println("Pilihan tidak valid.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Terjadi kesalahan input: " + e.getMessage());
                    scanner.nextLine();
                } catch (NumberFormatException e) {
                    System.out.println("Terjadi kesalahan input: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat menghubungkan ke database: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Gagal menutup koneksi database: " + e.getMessage());
                }
            }
            scanner.close();
        }
    }

}