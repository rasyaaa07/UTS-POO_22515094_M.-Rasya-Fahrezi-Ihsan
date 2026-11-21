# Daftar Class

1. Mahasiswa
2. MataKuliah
3. KRS
4. Kampus
5. Stack
6. Main

# Mahasiswa

Atribut:
- nama: String
- nim: String
- programStudi: String
- semester: int
- statusAktif: boolean

Konstruktor:
- Mahasiswa()
- Mahasiswa(nama: String, nim: String, programStudi: String, semester: int, statusAktif: boolean)

Method:
- getNama()
- setNama(nama: String): void
- getNim(): String
- setNim(nim: String): void
- getProgramStudi(): String
- getProgramStudi(programStudi: String): void
- getSemester(): int
- setSemester(semester: int): void
- tampilkanInfo(): void
- ubahStatusAktif(status: boolean): void
- cekStatusAktif(): boolean

Validasi setter:
- nama, nim, programStudi tidak boleh null atau kosong
- semester harus 1 sampai 14

# MataKuliah

Atribut:
- namaMataKuliah: String
- kodeMataKuliah: String
- sks: int
- kuota: int
- jumlahTerdaftar: int

Konstruktor:
-MataKuliah()
-MataKuliah(namaMataKuliah: String, kodeMataKuliah: String, sks: int, kuota: int)

Method:
- getNamaMataKuliah(): String
- setNamaMataKuliah(namaMataKuliah: String): void
- getKodeMataKuliah(): String
- setKodeMataKuliah(kodeMataKuliah: String): void
- getSKS(): int
- setSKS(sks: int): void
- getKuota(): int
- setKuota(kuota: int): void
- getJumlahTerdaftar(): int
- tampilkanInfo(): void
- cekKetersediaan(): boolean
- tambahPeserta(): boolean
- kurangiPeserta(): void

Validasi setter:
- namaMataKuliah dan kodeMataKuliah tidak boleh kosong
- sks harus 1 sampai 6
- kuota harus lebih dari 0

Aturan bisnis:
- tambahPeserta() menolak kalau jumlahTerdaftar sudah sama dengan kuota

# KRS

Atribut:
- kodeKRS: String
- semester: int
- tahunAkademik: String
- totalSKS: int
- statusKRS: String
- mahasiswa: Mahasiswa
- daftarMataKuliah: ArrayList<MataKuliah>

Konstruktor:
- KRS()
- KRS(kodeKRS: String, mahasiswa: Mahasiswa, semester: int, tahunAkademik: String, statusKRS: String)

Method:
- getKodeKRS(): String
- setKodeKRS(kodeKRS: String): void
- getSemester(): int
- setSemester(semester: int): void
- getTahunAkademik(): String
- setTahunAkademik(tahunAkademik: String): void
- getTotalSKS(): int
- getStatusKRS(): String
- setStatusKRS(statusKRS: String): void
- getMahasiswa(): Mahasiswa
- setMahasiswa(mahasiswa: Mahasiswa): void
- getDaftarMataKuliah(): ArrayList<MataKuliah>
- tampilkanKRS(): void
- hitungTotalSKS(): int
- tambahMataKuliah(mk: MataKuliah): boolean
- batalkanKRS(): void

Validasi setter:
- kodeKRS dan tahunAkademik tidak boleh kosong
- semester harus 1 sampai 14
- mahasiswa tidak boleh null

Aturan bisnis
tambahMataKuliah():
- ditolak kalau mahasiswa tidak aktif
- ditolak kalau mata kuliah sudah ada di KRS
- ditolak kalau kuota mata kuliah penuh
- ditolak kalau totalSKS + sks mata kuliah lebih dari 24
- kalau lolos semua, panggil tambahPeserta() lalu perbarui totalSKS

batalkanKRS():
- memanggil kurangiPeserta() pada setiap mata kuliah, lalu mengosongkan daftarMataKuliah dan totalSKS

# Kampus
Atribut:
- namaKampus: String
- daftarMahasiswa: ArrayList<Mahasiswa>
- daftarMataKuliah: ArrayList<MataKuliah>
- daftarKRS: ArrayList<KRS>
- jumlahMahasiswa: int

Konstruktor:
- Kampus()
- Kampus(namaKampus: String)

Method:
- getNamaKampus(): String
- setNamaKampus(namaKampus: String): void
- tambahMahasiswa(m: Mahasiswa): boolean
- tambahMataKuliah(mk: MataKuliah): boolean
- cariMahasiswa(nim: String): Mahasiswa
- cariMataKuliah(kode: String): MataKuliah
- cariKRS(kodeKRS: String): KRS
- buatKRS(kodeKRS: String, nim: String, kodeMk: String): KRS
- hapusKRS(kodeKRS: String): boolean
- tampilkanSemuaMahasiswa(): void
- tampilkanSemuaMataKuliah(): void
- tampilkanSemuaKRS(): void
- tampilkanRingkasan(): void

Aturan bisnis:
- tambahMahasiswa() menolak NIM duplikat
- tambahMataKuliah() menolak kode duplikat
- buatKRS() menolak kalau mahasiswa atau mata kuliah tidak ditemukan, lalu memanggil tambahMataKuliah() milik KRS
- hapusKRS() memanggil batalkanKRS() lalu menghapus KRS dari daftarKRS 