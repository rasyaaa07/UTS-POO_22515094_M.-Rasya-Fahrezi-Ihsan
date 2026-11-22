package akademik;

import java.util.ArrayList;

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

    public KRS() {
        this.kodeKRS = "";
        this.semester = SEMESTER_MIN;
        this.tahunAkademik = "";
        this.totalSKS = 0;
        this.statusKRS = STATUS_AKTIF;
        this.mahasiswa = null;
        this.daftarMataKuliah = new ArrayList<>();
    }

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
        if (kodeKRS == null || kodeKRS.trim().isEmpty()) {
            throw new IllegalArgumentException("KodeKRS tidak boleh kosong.");
        }
        this.kodeKRS = kodeKRS.trim();
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        if (semester < SEMESTER_MIN || semester > SEMESTER_MAKS) {
            throw new IllegalArgumentException("Semester harus antara " + SEMESTER_MIN + " dan " + SEMESTER_MAKS + ".");
        }
        this.semester = semester;
    }

    public String getTahunAkademik() {
        return tahunAkademik;
    }

    public void setTahunAkademik(String tahunAkademik) {
        if (tahunAkademik == null || tahunAkademik.trim().isEmpty()) {
            throw new IllegalArgumentException("Tahun Akademik tidak boleh kosong.");
        }
        this.tahunAkademik = tahunAkademik.trim();
    }

    public int getTotalSKS() {
        return totalSKS;
    }

    public String getStatusKRS() {
        return statusKRS;
    }

    public void setStatusKRS(String statusKRS) {
        if (statusKRS == null || statusKRS.trim().isEmpty()) {
            throw new IllegalArgumentException("Status KRS tidak boleh kosong.");
        }
        this.statusKRS = statusKRS;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        if (mahasiswa == null) {
            throw new IllegalArgumentException("Mahasiswa pada KRS tidak boleh kosong.");
        }
        this.mahasiswa = mahasiswa;
    }

    public ArrayList<MataKuliah> getDaftarMataKuliah() {
        return daftarMataKuliah;
    }

    public void tampilkanKRS() {
        String namaMhs = (mahasiswa == null) ? "-" : mahasiswa.getNama() + "("+ mahasiswa.getNim() + ")";
        System.out.printf("KRS %s | Mahasiswa: %s | Semester: %d | TA: %s | Total SKS: %d | Status: %s%n", kodeKRS, namaMhs, semester, tahunAkademik, totalSKS, statusKRS);
        if (daftarMataKuliah.isEmpty()) {
            System.out.println("  (belum ada mata kuliah)");
        } else {
            for (MataKuliah mk : daftarMataKuliah) {
                System.out.printf("  - %s | %s | %d SKS%n", mk.getKodeMataKuliah(), mk.getNamaMataKuliah(), mk.getSks());
            }
        }
    }

    public int hitungTotalSKS() {
        int total = 0;
        for (MataKuliah mk : daftarMataKuliah) {
            total += mk.getSks();
        }
        return total;
    }

    public boolean tambahMataKuliah(MataKuliah mk) {
        if (mk == null) {
            System.out.println("Gagal: mata kuliah tidak valid.");
            return false;
        }
        if (mahasiswa == null) {
            System.out.println("Gagal: KRS belum memiliki mahasiswa.");
            return false;
        }

        if (!mahasiswa.cekStatusAktif()) {
            System.out.println("Gagal: mahasiswa " + mahasiswa.getNama() + " berstatus nonaktif.");
            return false;
        }
        if (STATUS_DIBATALKAN.equals(statusKRS)) {
            System.out.println("Gagal: KRS " + kodeKRS + " sudah dibatalkan.");
            return false;
        }
        for (MataKuliah ada : daftarMataKuliah) {
            if (ada.getKodeMataKuliah().equalsIgnoreCase(mk.getKodeMataKuliah())) {
                System.out.println("Gagal: mata kuliah " + mk.getKodeMataKuliah() + " sudah ada di KRS ini.");
                return false;
            }
        }
        if (!mk.cekKetersediaan()) {
            System.out.println("Gagal: total SKS akan menjadi " + (totalSKS + mk.getSks()) + ", melebihi batas " + MAKS_SKS + " SKS.");
            return false;
        }
        mk.tambahPeserta();
        daftarMataKuliah.add(mk);
        this.totalSKS = hitungTotalSKS();
        return true;
    }

    public void batalkanKRS() {
        for (MataKuliah mk : daftarMataKuliah) {
            mk.kurangiPeserta();
        }
        daftarMataKuliah.clear();
        this.totalSKS = 0;
        this.statusKRS = STATUS_DIBATALKAN;
    }
}