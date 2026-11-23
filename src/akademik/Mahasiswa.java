package akademik;

/**
 * Mahasiswa merepresentasikan seorang mahasiswa di kampus.
 * Semua atribut bersifat private dan diakses lewat getter/setter
 * yang memuat validasi (enkapsulasi).
 */
public class Mahasiswa {
    private static final int SEMESTER_MIN = 1;
    private static final int SEMESTER_MAKS = 14;

    private String nama;
    private String nim;
    private String programStudi;
    private int semester;
    private boolean statusAktif;

    /**
     * Konstruktor default: membuat mahasiswa dengan nilai awal kosong,
     * semester 1, dan status aktif.
     */
    public Mahasiswa() {
        this.nama = "";
        this.nim = "";
        this.programStudi = "";
        this.semester = SEMESTER_MIN;
        this.statusAktif = true;
    }

    /**
     * Konstruktor berparameter. Memanggil setter agar nilai awal tervalidasi.
     */
    public Mahasiswa(String nama, String nim, String programStudi, int semester, boolean statusAktif) {
        this.setNama(nama);
        this.setNim(nim);
        this.setProgramStudi(programStudi);
        this.setSemester(semester);
        this.statusAktif = statusAktif;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        // Validasi: nama tidak boleh null atau kosong
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }
        this.nama = nama.trim();
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        // Validasi: NIM tidak boleh null atau kosong
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM tidak boleh kosong.");
        }
        this.nim = nim.trim();
    }

    public String getProgramStudi() {
        return programStudi;
    }

    public void setProgramStudi(String programStudi) {
        // Validasi: program studi tidak boleh null atau kosong
        if (programStudi == null || programStudi.trim().isEmpty()) {
            throw new IllegalArgumentException("Program studi tidak boleh kosong.");
        }
        this.programStudi = programStudi.trim();
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

    /** Menampilkan data mahasiswa ke console. */
    public void tampilkanInfo() {
        System.out.printf("NIM: %s | Nama: %s | Prodi: %s | Semester: %d | Status: %s%n",
                nim, nama, programStudi, semester, statusAktif ? "Aktif" : "Nonaktif");
    }

    /** Mengubah status keaktifan mahasiswa. */
    public void ubahStatusAktif(boolean status) {
        this.statusAktif = status;
    }

    /** Mengembalikan true jika mahasiswa berstatus aktif. */
    public boolean cekStatusAktif() {
        return statusAktif;
    }
}