package akademik;

public class Mahasiswa {
    private static final int SEMESTER_MIN = 1;
    private static final int SEMESTER_MAKS = 14;

    private String nama;
    private String nim;
    private String programStudi;
    private int semester;
    private boolean statusAktif;

    public Mahasiswa() {
        this.nama = "";
        this.nim = "";
        this.programStudi = "";
        this.semester = SEMESTER_MIN;
        this.statusAktif = true;
    }

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
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }
        this.nama = nama.trim();
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM tidak boleh kosong.");
        }
        this.nim = nim.trim();
    }

    public String getProgramStudi() {
        return programStudi;
    }

    public void setProgramStudi(String programStudi) {
        if (programStudi == null || programStudi.trim().isEmpty()) {
            throw new IllegalArgumentException("Program Studi tidak boleh kosong");
        }
        this.programStudi = programStudi.trim();
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

    public void tampilkanInfo() {
        System.out.printf("Nama: %s | NIM: %s | Prodi: %s | Semester %d | Status: %s%n",
                nama, nim, programStudi, semester, statusAktif ? "Aktif" : "Nonaktif");
    }

    public void ubahStatusAktif(boolean status) {
        this.statusAktif = status;
    }

    public boolean cekStatusAktif() {
        return statusAktif;
    }
}

