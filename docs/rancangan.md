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
