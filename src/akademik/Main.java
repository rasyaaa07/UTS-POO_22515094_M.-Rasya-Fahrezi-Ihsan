package akademik;

import java.util.Scanner;

public class Main {
    private Kampus kampus;
    private Stack riwayat;
    private Scanner scanner;

    public Main() {
        this.kampus = new Kampus("Kampus Mini");
        this.riwayat = new Stack(10);
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new Main().jalankanProgram();
    }

    private void jalankanProgram() {
        int pilihan;
        do {
            tampilkanMenu();
            pilihan = bacaInt("Pilih menu: ");
            try {
                prosesMenu(pilihan);
            } catch (IllegalArgumentException e) {
                System.out.println("Input tidak valid: " + e.getMessage());
            }
        } while (pilihan != 0);
    }

    private void tampilkanMenu() {
        System.out.println();
        System.out.println("===== AKADEMIK MINI KAMPUS =====");
        System.out.println("1.  Tambah mahasiswa");
        System.out.println("2.  Tampilkan semua mahasiswa");
        System.out.println("3.  Tambah mata kuliah");
        System.out.println("4.  Tampilkan semua mata kuliah");
        System.out.println("5.  Buat KRS");
        System.out.println("6.  Tambah mata kuliah ke KRS");
        System.out.println("7.  Tampilkan semua KRS");
        System.out.println("8.  Cari mahasiswa / mata kuliah");
        System.out.println("9.  Batalkan KRS terakhir (pop Stack)");
        System.out.println("10. Lihat riwayat KRS (isi Stack)");
        System.out.println("11. Ubah status mahasiswa");
        System.out.println("12. Ringkasan");
        System.out.println("0.  Keluar");
    }

    private void prosesMenu(int pilihan) {
        switch (pilihan) {
            case 1:
                tambahMahasiswa();
                break;
            case 2:
                kampus.tampilkanSemuaMahasiswa();
                break;
            case 3:
                tambahMataKuliah();
                break;
            case 4:
                kampus.tampilkanSemuaMataKuliah();
                break;
            case 5:
                buatKRS();
                break;
            case 6:
                tambahMataKuliahKeKRS();
                break;
            case 7:
                kampus.tampilkanSemuaKRS();
                break;
            case 8:
                cariData();
                break;
            case 9:
                batalkanKRSTerakhir();
                break;
            case 10:
                lihatRiwayat();
                break;
            case 11:
                ubahStatusMahasiswa();
                break;
            case 12:
                kampus.tampilkanRingkasan();
                System.out.println("Isi riwayat Stack  : " + riwayat.size());
                break;
            case 0:
                System.out.println("Terima kasih. Program selesai.");
                break;
            default:
                System.out.println("Menu tidak tersedia. Pilih angka 0 sampai 12.");
        }
    }

    private void tambahMahasiswa() {
        String nim = bacaString("NIM: ");
        String nama = bacaString("Nama: ");
        String prodi = bacaString("Program studi: ");
        int semester = bacaInt("Semester (1-14): ");
        Mahasiswa m = new Mahasiswa(nama, nim, prodi, semester, true);
        if (kampus.tambahMahasiswa(m)) {
            System.out.println("Mahasiswa berhasil ditambahkan.");
        }
    }

    private void tambahMataKuliah() {
        String kode = bacaString("Kode mata kuliah: ");
        String nama = bacaString("Nama mata kuliah: ");
        int sks = bacaInt("SKS (1-6): ");
        int kuota = bacaInt("Kuota: ");
        MataKuliah mk = new MataKuliah(nama, kode, sks, kuota);
        if (kampus.tambahMataKuliah(mk)) {
            System.out.println("Mata kuliah berhasil ditambahkan.");
        }
    }

    private void buatKRS() {
        String kodeKrs = bacaString("Kode KRS: ");
        String nim = bacaString("NIM mahasiswa: ");
        String kodeMk = bacaString("Kode mata kuliah pertama: ");
        KRS krs = kampus.buatKRS(kodeKrs, nim, kodeMk);
        if (krs != null) {
            System.out.println("KRS " + krs.getKodeKRS() + " berhasil dibuat.");
            if (!riwayat.push(krs.getKodeKRS())) {
                System.out.println("Peringatan: riwayat penuh, KRS ini tidak tercatat untuk dibatalkan.");
            }
        }
    }

    private void tambahMataKuliahKeKRS() {
        String kodeKrs = bacaString("Kode KRS: ");
        String kodeMk = bacaString("Kode mata kuliah: ");
        if (kampus.tambahMataKuliahKeKRS(kodeKrs, kodeMk)) {
            System.out.println("Mata kuliah berhasil ditambahkan ke KRS.");
        }
    }

    private void cariData() {
        System.out.println("1. Cari mahasiswa (NIM)");
        System.out.println("2. Cari mata kuliah (kode)");
        int jenis = bacaInt("Pilih: ");
        if (jenis == 1) {
            Mahasiswa m = kampus.cariMahasiswa(bacaString("NIM: "));
            if (m == null) {
                System.out.println("Mahasiswa tidak ditemukan.");
            } else {
                m.tampilkanInfo();
            }
        } else if (jenis == 2) {
            MataKuliah mk = kampus.cariMataKuliah(bacaString("Kode: "));
            if (mk == null) {
                System.out.println("Mata kuliah tidak ditemukan.");
            } else {
                mk.tampilkanInfo();
            }
        } else {
            System.out.println("Pilihan harus 1 atau 2.");
        }
    }

    private void batalkanKRSTerakhir() {
        if (riwayat.isEmpty()) {
            System.out.println("Riwayat kosong, tidak ada KRS yang bisa dibatalkan.");
            return;
        }
        String kode = riwayat.pop();
        if (kampus.hapusKRS(kode)) {
            System.out.println("KRS terakhir (" + kode + ") berhasil dibatalkan.");
        }
    }

    private void lihatRiwayat() {
        riwayat.tampilkanIsi();
        System.out.println("Ukuran   : " + riwayat.size());
        System.out.println("Kosong   : " + riwayat.isEmpty());
        System.out.println("Penuh    : " + riwayat.isFull());
        System.out.println("Teratas  : " + (riwayat.isEmpty() ? "-" : riwayat.peek()));
    }

    private void ubahStatusMahasiswa() {
        Mahasiswa m = kampus.cariMahasiswa(bacaString("NIM: "));
        if (m == null) {
            System.out.println("Mahasiswa tidak ditemukan.");
            return;
        }
        int status = bacaInt("Status (1 = aktif, 0 = nonaktif): ");
        if (status != 0 && status != 1) {
            System.out.println("Status harus 1 atau 0.");
            return;
        }
        m.ubahStatusAktif(status == 1);
        System.out.println("Status mahasiswa diubah menjadi " + (status == 1 ? "aktif." : "nonaktif."));
    }

    private String bacaString(String pesan) {
        while (true) {
            System.out.print(pesan);
            if (!scanner.hasNextLine()) {
                System.out.println();
                System.out.println("Input berakhir. Program selesai.");
                System.exit(0);
            }
            String teks = scanner.nextLine().trim();
            if (!teks.isEmpty()) {
                return teks;
            }
            System.out.println("Input tidak boleh kosong.");
        }
    }

    private int bacaInt(String pesan) {
        while (true) {
            String teks = bacaString(pesan);
            try {
                return Integer.parseInt(teks);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat.");
            }
        }
    }
}
