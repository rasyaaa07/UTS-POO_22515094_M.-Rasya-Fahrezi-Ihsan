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