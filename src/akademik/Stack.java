package akademik;

/**
 * Stack adalah ADT buatan sendiri dengan prinsip LIFO (Last In First Out).
 * Dipakai untuk menyimpan riwayat kode KRS yang baru dibuat, sehingga
 * KRS terakhir dapat dibatalkan. Data disimpan dalam array berkapasitas tetap.
 * Atribut internal private; antarmuka berupa operasi publik.
 */
public class Stack {
    private static final int KAPASITAS_DEFAULT = 10;

    private String[] data;
    private int kapasitas;
    private int top;

    /** Konstruktor default: kapasitas 10. */
    public Stack() {
        this(KAPASITAS_DEFAULT);
    }

    /** Konstruktor berparameter: kapasitas harus lebih dari 0. */
    public Stack(int kapasitas) {
        if (kapasitas <= 0) {
            throw new IllegalArgumentException("Kapasitas stack harus lebih dari 0.");
        }
        this.kapasitas = kapasitas;
        this.data = new String[kapasitas];
        this.top = -1; // -1 berarti stack kosong
    }

    /**
     * Menaruh data di puncak stack.
     * Prasyarat: stack tidak penuh. Efek: size bertambah 1.
     * @return true jika berhasil, false jika stack penuh atau data kosong
     */
    public boolean push(String aktivitas) {
        if (isFull() || aktivitas == null || aktivitas.trim().isEmpty()) {
            return false;
        }
        top++;
        data[top] = aktivitas;
        return true;
    }

    /**
     * Mengambil dan menghapus data di puncak stack.
     * Prasyarat: stack tidak kosong. Efek: size berkurang 1.
     * @return data teratas, atau null jika stack kosong
     */
    public String pop() {
        if (isEmpty()) {
            return null;
        }
        String hasil = data[top];
        data[top] = null;
        top--;
        return hasil;
    }

    /**
     * Melihat data di puncak stack tanpa menghapusnya.
     * @return data teratas, atau null jika stack kosong
     */
    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return data[top];
    }

    /** Mengembalikan true jika stack kosong. */
    public boolean isEmpty() {
        return top == -1;
    }

    /** Mengembalikan true jika stack penuh. */
    public boolean isFull() {
        return top == kapasitas - 1;
    }

    /** Mengembalikan jumlah data di dalam stack. */
    public int size() {
        return top + 1;
    }

    /** Menampilkan isi stack dari puncak ke dasar. */
    public void tampilkanIsi() {
        if (isEmpty()) {
            System.out.println("Riwayat kosong.");
            return;
        }
        System.out.println("Isi riwayat (atas ke bawah), kapasitas " + kapasitas + ":");
        for (int i = top; i >= 0; i--) {
            System.out.println("   " + (top - i + 1) + ". " + data[i]);
        }
    }
}