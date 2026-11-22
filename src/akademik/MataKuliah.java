package akademik;

public class MataKuliah {
    private static final int SKS_MIN = 1;
    private static final int SKS_MAKS = 6;

    private String namaMataKuliah;
    private String kodeMataKuliah;
    private int sks;
    private int kuota;
    private int jumlahTerdaftar;

    public MataKuliah() {
        this.namaMataKuliah = "";
        this.kodeMataKuliah = "";
        this.sks = SKS_MIN;
        this.kuota = 1;
        this.jumlahTerdaftar = 0;
    }

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
        if (namaMataKuliah == null || namaMataKuliah.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama Mata Kuliah tidak boleh kosong.");
        }
        this.namaMataKuliah = namaMataKuliah;
    }

    public String getKodeMataKuliah() {
        return kodeMataKuliah;
    }

    public void setKodeMataKuliah(String kodeMataKuliah) {
        if (kodeMataKuliah == null || kodeMataKuliah.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode Mata Kuliah tidak boleh kosong.");
        }
        this.kodeMataKuliah = kodeMataKuliah;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        if (sks < SKS_MIN || sks > SKS_MAKS) {
            throw new IllegalArgumentException("SKS harus antara " + SKS_MIN + " dan " + SKS_MAKS + ".");
        }
        this.sks = sks;
    }

    public int getKuota() {
        return kuota;
    }

    public void setKuota(int kuota) {
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

    public void tampilkanInfo() {
        System.out.printf("Nama: %s | Kode: %s | SKS: %s | Kuota: %d/%d%n",
                namaMataKuliah, kodeMataKuliah, sks, jumlahTerdaftar, kuota);
    }

    public boolean cekKetersediaan() {
        return jumlahTerdaftar < kuota;
    }

    public boolean tambahPeserta() {
        if (!cekKetersediaan()) {
            return false;
        }
        jumlahTerdaftar++;
        return true;
    }

    public void kurangiPeserta() {
        if (jumlahTerdaftar > 0) {
            jumlahTerdaftar--;
        }
    }
}