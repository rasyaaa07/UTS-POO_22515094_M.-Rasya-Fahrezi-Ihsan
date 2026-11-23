package akademik;

/**
 * MataKuliah merepresentasikan satu mata kuliah beserta kuota pesertanya.
 * Aturan bisnis: kuota terbatas, peserta tidak boleh melebihi kuota.
 */
public class MataKuliah {
    private static final int SKS_MIN = 1;
    private static final int SKS_MAKS = 6;

    private String namaMataKuliah;
    private String kodeMataKuliah;
    private int sks;
    private int kuota;
    private int jumlahTerdaftar;

    /** Konstruktor default: nilai awal kosong, belum ada peserta. */
    public MataKuliah() {
        this.namaMataKuliah = "";
        this.kodeMataKuliah = "";
        this.sks = SKS_MIN;
        this.kuota = 1;
        this.jumlahTerdaftar = 0;
    }

    /** Konstruktor berparameter. Memanggil setter agar nilai awal tervalidasi. */
    public MataKuliah(String namaMataKuliah, String kodeMataKuliah, int sks, int kuota) {
        this.setNamaMataKuliah(namaMataKuliah);
        this.setKodeMataKuliah(kodeMataKuliah);
        this.setSks(sks);
        this.setKuota(kuota);
        this.jumlahTerdaftar = 0;
    }

    public String getNamaMataKuliah() {
        return namaMataKuliah;
    }

    public void setNamaMataKuliah(String namaMataKuliah) {
        // Validasi: nama tidak boleh null atau kosong
        if (namaMataKuliah == null || namaMataKuliah.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama mata kuliah tidak boleh kosong.");
        }
        this.namaMataKuliah = namaMataKuliah.trim();
    }

    public String getKodeMataKuliah() {
        return kodeMataKuliah;
    }

    public void setKodeMataKuliah(String kodeMataKuliah) {
        // Validasi: kode tidak boleh null atau kosong
        if (kodeMataKuliah == null || kodeMataKuliah.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode mata kuliah tidak boleh kosong.");
        }
        this.kodeMataKuliah = kodeMataKuliah.trim();
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        // Validasi: SKS harus 1 sampai 6
        if (sks < SKS_MIN || sks > SKS_MAKS) {
            throw new IllegalArgumentException("SKS harus antara " + SKS_MIN + " dan " + SKS_MAKS + ".");
        }
        this.sks = sks;
    }

    public int getKuota() {
        return kuota;
    }

    public void setKuota(int kuota) {
        // Validasi: kuota harus lebih dari 0 dan tidak boleh di bawah peserta yang sudah terdaftar
        if (kuota <= 0) {
            throw new IllegalArgumentException("Kuota harus lebih dari 0.");
        }
        if (kuota < this.jumlahTerdaftar) {
            throw new IllegalArgumentException("Kuota tidak boleh kurang dari jumlah peserta terdaftar.");
        }
        this.kuota = kuota;
    }

    public int getJumlahTerdaftar() {
        return jumlahTerdaftar;
    }

    /** Menampilkan data mata kuliah ke console. */
    public void tampilkanInfo() {
        System.out.printf("Kode: %s | Nama: %s | SKS: %d | Kuota: %d/%d%n",
                kodeMataKuliah, namaMataKuliah, sks, jumlahTerdaftar, kuota);
    }

    /** Mengembalikan true jika kuota masih tersedia. */
    public boolean cekKetersediaan() {
        return jumlahTerdaftar < kuota;
    }

    /**
     * Menambah satu peserta. Aturan bisnis: ditolak jika kuota sudah penuh.
     * @return true jika berhasil, false jika kuota penuh
     */
    public boolean tambahPeserta() {
        if (!cekKetersediaan()) {
            return false;
        }
        jumlahTerdaftar++;
        return true;
    }

    /** Mengurangi satu peserta (dipakai saat KRS dibatalkan). */
    public void kurangiPeserta() {
        if (jumlahTerdaftar > 0) {
            jumlahTerdaftar--;
        }
    }
}