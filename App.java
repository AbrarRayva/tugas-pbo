import java.util.Scanner;

public class App {
    static boolean tambahTransaksi = true;
    static int noFaktur;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                try {
                    System.out.print("Masukkan Nilai Awal No Faktur: ");
                    noFaktur = Integer.parseInt(scanner.nextLine().trim());
                    if (noFaktur <= 0) {
                        System.out.println("No Faktur harus lebih besar dari 0.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid. Masukkan angka yang sesuai.");
                }
            }

            do {
                System.out.println("\nTransaksi Baru - No Faktur: " + noFaktur);

                System.out.print("Masukkan Kode Barang: ");
                String kodeBarang = scanner.nextLine().trim();

                System.out.print("Masukkan Nama Barang: ");
                String namaBarang = scanner.nextLine().trim();

                double hargaBarang = 0;
                while (true) {
                    try {
                        System.out.print("Masukkan Harga Barang: ");
                        hargaBarang = Double.parseDouble(scanner.nextLine().trim());
                        if (hargaBarang <= 0) {
                            System.out.println("Harga barang harus lebih dari 0.");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid. Masukkan angka yang sesuai.");
                    }
                }

                int jumlahBeli = 0;
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

                System.out.println("\n=== Detail Transaksi ===");
                System.out.println("No Faktur    : " + noFaktur);
                System.out.println("Kode Barang  : " + transaksi.getKode());
                System.out.println("Nama Barang  : " + transaksi.getNama());
                System.out.println("Harga Barang : " + transaksi.getHarga());
                System.out.println("Jumlah Beli  : " + transaksi.getjumlahBeli());
                System.out.println("Total        : " + transaksi.getTotal());

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
            scanner.close();
            System.out.println("Program selesai. Terima kasih!");
        }
    }
}
