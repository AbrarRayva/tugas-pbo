import java.util.*;
import java.text.*;

public class App {
    static boolean tambahTransaksi = true;
    static String kasir;

    static String savedUsername = "admin";
    static String savedPassword = "password";
    static String savedCaptcha;

    static String inputUsername;
    static String inputPassword;
    static String inputCaptcha;

    static Captcha captcha = new Captcha();

    static int noFaktur = 1;
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

    private static void output(Transaksi transaksi) {
        // Contoh Method Date
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

    private static void program(Scanner scanner) {
        try {
            do {
                System.out.println("Transaksi Baru - No Faktur: " + noFaktur);
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
                output(transaksi);

                boolean validInput;
                do {
                    System.out.print("\nApakah anda ingin menambah transaksi baru? (y/n): ");
                    String input = scanner.nextLine().trim().toLowerCase();

                    if (input.equals("y")) {
                        tambahTransaksi = true;
                        validInput = true;
                    } else if (input.equals("n")) {
                        tambahTransaksi = false;
                        validInput = true;
                    } else {
                        System.out.println("Input tidak valid. Masukkan 'y' untuk ya atau 'n' untuk tidak.");
                        validInput = false;
                    }
                } while (!validInput);

                noFaktur++;
            } while (tambahTransaksi);
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            System.out.println("Sampai Jumpa!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        login(scanner);
        program(scanner);
        scanner.close();
    }
}