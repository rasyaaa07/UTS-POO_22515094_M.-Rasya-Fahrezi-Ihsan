package akademik;

import java.util.ArrayList;

/**
 * Kampus mengelola kumpulan Mahasiswa, MataKuliah, dan KRS.
 * Semua data disimpan di memori selama program berjalan.
 */
public class Kampus {
    private static final String TAHUN_AKADEMIK_DEFAULT = "2026/2027";

    private String namaKampus;
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;
    private ArrayList<KRS> daftarKRS;
    private int jumlahMahasiswa;

    /** Konstruktor default. */
    public Kampus() {
        this.namaKampus = "Kampus";
        this.daftarMahasiswa = new ArrayList<>();
        this.daftarMataKuliah = new ArrayList<>();
        this.daftarKRS = new ArrayList<>();
        this.jumlahMahasiswa = 0;
    }

    /** Konstruktor berparameter. */
    public Kampus(String namaKampus) {
        this();
        this.setNamaKampus(namaKampus);
    }

    public String getNamaKampus() {
        return namaKampus;
    }

    public void setNamaKampus(String namaKampus) {
        // Validasi: nama kampus tidak boleh null atau kosong
        if (namaKampus == null || namaKampus.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama kampus tidak boleh kosong.");
        }
        this.namaKampus = namaKampus.trim();
    }

    /** Menambah mahasiswa. Aturan: NIM tidak boleh duplikat. */
    public boolean tambahMahasiswa(Mahasiswa m) {
        if (m == null) {
            System.out.println("Gagal: data mahasiswa tidak valid.");
            return false;
        }
        if (cariMahasiswa(m.getNim()) != null) {
            System.out.println("Gagal: NIM " + m.getNim() + " sudah terdaftar.");
            return false;
        }
        daftarMahasiswa.add(m);
        jumlahMahasiswa++;
        return true;
    }

    /** Menambah mata kuliah. Aturan: kode tidak boleh duplikat. */
    public boolean tambahMataKuliah(MataKuliah mk) {
        if (mk == null) {
            System.out.println("Gagal: data mata kuliah tidak valid.");
            return false;
        }
        if (cariMataKuliah(mk.getKodeMataKuliah()) != null) {
            System.out.println("Gagal: kode mata kuliah " + mk.getKodeMataKuliah() + " sudah terdaftar.");
            return false;
        }
        daftarMataKuliah.add(mk);
        return true;
    }

    /** Mencari mahasiswa berdasarkan NIM. Mengembalikan null jika tidak ditemukan. */
    public Mahasiswa cariMahasiswa(String nim) {
        if (nim == null) {
            return null;
        }
        for (Mahasiswa m : daftarMahasiswa) {
            if (m.getNim().equalsIgnoreCase(nim.trim())) {
                return m;
            }
        }
        return null;
    }

    /** Mencari mata kuliah berdasarkan kode. Mengembalikan null jika tidak ditemukan. */
    public MataKuliah cariMataKuliah(String kode) {
        if (kode == null) {
            return null;
        }
        for (MataKuliah mk : daftarMataKuliah) {
            if (mk.getKodeMataKuliah().equalsIgnoreCase(kode.trim())) {
                return mk;
            }
        }
        return null;
    }

    /** Mencari KRS berdasarkan kode. Mengembalikan null jika tidak ditemukan. */
    public KRS cariKRS(String kodeKRS) {
        if (kodeKRS == null) {
            return null;
        }
        for (KRS krs : daftarKRS) {
            if (krs.getKodeKRS().equalsIgnoreCase(kodeKRS.trim())) {
                return krs;
            }
        }
        return null;
    }

    /**
     * Membuat KRS baru untuk mahasiswa dengan satu mata kuliah awal.
     * Transaksi ini menghubungkan Mahasiswa, MataKuliah, dan KRS.
     * @return KRS yang dibuat, atau null jika gagal
     */
    public KRS buatKRS(String kodeKRS, String nim, String kodeMk) {
        if (cariKRS(kodeKRS) != null) {
            System.out.println("Gagal: kode KRS " + kodeKRS + " sudah dipakai.");
            return null;
        }
        Mahasiswa m = cariMahasiswa(nim);
        if (m == null) {
            System.out.println("Gagal: mahasiswa dengan NIM " + nim + " tidak ditemukan.");
            return null;
        }
        MataKuliah mk = cariMataKuliah(kodeMk);
        if (mk == null) {
            System.out.println("Gagal: mata kuliah " + kodeMk + " tidak ditemukan.");
            return null;
        }
        KRS krs = new KRS(kodeKRS, m, m.getSemester(), TAHUN_AKADEMIK_DEFAULT, "Aktif");
        if (!krs.tambahMataKuliah(mk)) {
            return null; // pesan kesalahan sudah ditampilkan oleh KRS
        }
        daftarKRS.add(krs);
        return krs;
    }

    /** Menambah mata kuliah ke KRS yang sudah ada. */
    public boolean tambahMataKuliahKeKRS(String kodeKRS, String kodeMk) {
        KRS krs = cariKRS(kodeKRS);
        if (krs == null) {
            System.out.println("Gagal: KRS " + kodeKRS + " tidak ditemukan.");
            return false;
        }
        MataKuliah mk = cariMataKuliah(kodeMk);
        if (mk == null) {
            System.out.println("Gagal: mata kuliah " + kodeMk + " tidak ditemukan.");
            return false;
        }
        return krs.tambahMataKuliah(mk);
    }

    /** Membatalkan lalu menghapus KRS dari daftar. */
    public boolean hapusKRS(String kodeKRS) {
        KRS krs = cariKRS(kodeKRS);
        if (krs == null) {
            System.out.println("Gagal: KRS " + kodeKRS + " tidak ditemukan.");
            return false;
        }
        krs.batalkanKRS();
        daftarKRS.remove(krs);
        return true;
    }

    public void tampilkanSemuaMahasiswa() {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
            return;
        }
        for (Mahasiswa m : daftarMahasiswa) {
            m.tampilkanInfo();
        }
    }

    public void tampilkanSemuaMataKuliah() {
        if (daftarMataKuliah.isEmpty()) {
            System.out.println("Belum ada data mata kuliah.");
            return;
        }
        for (MataKuliah mk : daftarMataKuliah) {
            mk.tampilkanInfo();
        }
    }

    public void tampilkanSemuaKRS() {
        if (daftarKRS.isEmpty()) {
            System.out.println("Belum ada data KRS.");
            return;
        }
        for (KRS krs : daftarKRS) {
            krs.tampilkanKRS();
        }
    }

    /** Menampilkan ringkasan jumlah data dan total SKS seluruh KRS. */
    public void tampilkanRingkasan() {
        int totalSks = 0;
        for (KRS krs : daftarKRS) {
            totalSks += krs.getTotalSKS();
        }
        System.out.println("=== Ringkasan " + namaKampus + " ===");
        System.out.println("Jumlah mahasiswa  : " + jumlahMahasiswa);
        System.out.println("Jumlah mata kuliah: " + daftarMataKuliah.size());
        System.out.println("Jumlah KRS        : " + daftarKRS.size());
        System.out.println("Total SKS di KRS  : " + totalSks);
    }
}