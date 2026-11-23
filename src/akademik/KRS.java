package akademik;

import java.util.ArrayList;

/**
 * KRS (Kartu Rencana Studi) menghubungkan satu Mahasiswa dengan beberapa MataKuliah.
 * Aturan bisnis: total SKS per KRS maksimal 24 dan kuota mata kuliah terbatas.
 */
public class KRS {
    private static final int MAKS_SKS = 24;
    private static final int SEMESTER_MIN = 1;
    private static final int SEMESTER_MAKS = 14;
    private static final String STATUS_AKTIF = "Aktif";
    private static final String STATUS_DIBATALKAN = "Dibatalkan";

    private String kodeKRS;
    private int semester;
    private String tahunAkademik;
    private int totalSKS;
    private String statusKRS;
    private Mahasiswa mahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;

    /** Konstruktor default: KRS kosong tanpa mahasiswa. */
    public KRS() {
        this.kodeKRS = "";
        this.semester = SEMESTER_MIN;
        this.tahunAkademik = "";
        this.totalSKS = 0;
        this.statusKRS = STATUS_AKTIF;
        this.mahasiswa = null;
        this.daftarMataKuliah = new ArrayList<>();
    }

    /** Konstruktor berparameter. Memanggil setter agar nilai awal tervalidasi. */
    public KRS(String kodeKRS, Mahasiswa mahasiswa, int semester, String tahunAkademik, String statusKRS) {
        this.setKodeKRS(kodeKRS);
        this.setMahasiswa(mahasiswa);
        this.setSemester(semester);
        this.setTahunAkademik(tahunAkademik);
        this.setStatusKRS(statusKRS);
        this.totalSKS = 0;
        this.daftarMataKuliah = new ArrayList<>();
    }

    public String getKodeKRS() {
        return kodeKRS;
    }

    public void setKodeKRS(String kodeKRS) {
        // Validasi: kode tidak boleh null atau kosong
        if (kodeKRS == null || kodeKRS.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode KRS tidak boleh kosong.");
        }
        this.kodeKRS = kodeKRS.trim();
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        // Validasi: semester harus 1 sampai 14
        if (semester < SEMESTER_MIN || semester > SEMESTER_MAKS) {
            throw new IllegalArgumentException("Semester harus antara " + SEMESTER_MIN + " dan " + SEMESTER_MAKS + ".");
        }
        this.semester = semester;
    }

    public String getTahunAkademik() {
        return tahunAkademik;
    }

    public void setTahunAkademik(String tahunAkademik) {
        // Validasi: tahun akademik tidak boleh null atau kosong
        if (tahunAkademik == null || tahunAkademik.trim().isEmpty()) {
            throw new IllegalArgumentException("Tahun akademik tidak boleh kosong.");
        }
        this.tahunAkademik = tahunAkademik.trim();
    }

    /** totalSKS hanya punya getter karena nilainya dihitung dari mata kuliah. */
    public int getTotalSKS() {
        return totalSKS;
    }

    public String getStatusKRS() {
        return statusKRS;
    }

    public void setStatusKRS(String statusKRS) {
        // Validasi: status tidak boleh null atau kosong
        if (statusKRS == null || statusKRS.trim().isEmpty()) {
            throw new IllegalArgumentException("Status KRS tidak boleh kosong.");
        }
        this.statusKRS = statusKRS.trim();
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        // Validasi: mahasiswa tidak boleh null
        if (mahasiswa == null) {
            throw new IllegalArgumentException("Mahasiswa pada KRS tidak boleh kosong.");
        }
        this.mahasiswa = mahasiswa;
    }

    public ArrayList<MataKuliah> getDaftarMataKuliah() {
        return daftarMataKuliah;
    }

    /** Menampilkan isi KRS beserta daftar mata kuliahnya. */
    public void tampilkanKRS() {
        String namaMhs = (mahasiswa == null) ? "-" : mahasiswa.getNama() + " (" + mahasiswa.getNim() + ")";
        System.out.printf("KRS %s | Mahasiswa: %s | Semester: %d | TA: %s | Total SKS: %d | Status: %s%n",
                kodeKRS, namaMhs, semester, tahunAkademik, totalSKS, statusKRS);
        if (daftarMataKuliah.isEmpty()) {
            System.out.println("   (belum ada mata kuliah)");
        } else {
            for (MataKuliah mk : daftarMataKuliah) {
                System.out.printf("   - %s | %s | %d SKS%n", mk.getKodeMataKuliah(), mk.getNamaMataKuliah(), mk.getSks());
            }
        }
    }

    /** Menghitung total SKS dari seluruh mata kuliah di KRS. */
    public int hitungTotalSKS() {
        int total = 0;
        for (MataKuliah mk : daftarMataKuliah) {
            total += mk.getSks();
        }
        return total;
    }

    /**
     * Menambah mata kuliah ke KRS dengan memeriksa aturan bisnis.
     * Pelanggaran aturan menampilkan pesan kesalahan dan mengembalikan false.
     */
    public boolean tambahMataKuliah(MataKuliah mk) {
        if (mk == null) {
            System.out.println("Gagal: mata kuliah tidak valid.");
            return false;
        }
        if (mahasiswa == null) {
            System.out.println("Gagal: KRS belum memiliki mahasiswa.");
            return false;
        }
        // Aturan: mahasiswa nonaktif tidak boleh mengisi KRS
        if (!mahasiswa.cekStatusAktif()) {
            System.out.println("Gagal: mahasiswa " + mahasiswa.getNama() + " berstatus nonaktif.");
            return false;
        }
        if (STATUS_DIBATALKAN.equals(statusKRS)) {
            System.out.println("Gagal: KRS " + kodeKRS + " sudah dibatalkan.");
            return false;
        }
        // Aturan: mata kuliah tidak boleh dobel dalam satu KRS
        for (MataKuliah ada : daftarMataKuliah) {
            if (ada.getKodeMataKuliah().equalsIgnoreCase(mk.getKodeMataKuliah())) {
                System.out.println("Gagal: mata kuliah " + mk.getKodeMataKuliah() + " sudah ada di KRS ini.");
                return false;
            }
        }
        // Aturan: kuota mata kuliah terbatas
        if (!mk.cekKetersediaan()) {
            System.out.println("Gagal: kuota mata kuliah " + mk.getKodeMataKuliah() + " sudah penuh.");
            return false;
        }
        // Aturan: total SKS per KRS maksimal 24
        if (totalSKS + mk.getSks() > MAKS_SKS) {
            System.out.println("Gagal: total SKS akan menjadi " + (totalSKS + mk.getSks())
                    + ", melebihi batas " + MAKS_SKS + " SKS.");
            return false;
        }
        mk.tambahPeserta();
        daftarMataKuliah.add(mk);
        this.totalSKS = hitungTotalSKS();
        return true;
    }

    /** Membatalkan KRS: mengembalikan kuota setiap mata kuliah lalu mengosongkan KRS. */
    public void batalkanKRS() {
        for (MataKuliah mk : daftarMataKuliah) {
            mk.kurangiPeserta();
        }
        daftarMataKuliah.clear();
        this.totalSKS = 0;
        this.statusKRS = STATUS_DIBATALKAN;
    }
}