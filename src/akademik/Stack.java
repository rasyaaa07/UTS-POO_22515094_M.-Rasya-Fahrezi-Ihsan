package akademik;

public class Stack {
    private static final int KAPASITAS_DEFAULT = 10;

    private String[] data;
    private int kapasitas;
    private int top;

    public Stack() {
        this(KAPASITAS_DEFAULT);
    }

    public Stack(int kapasitas) {
        if (kapasitas <= 0) {
            throw new IllegalArgumentException("Kapasitas stack harus lebih dari 0.");
        }
        this.kapasitas = kapasitas;
        this.data = new String[kapasitas];
        this.top = -1; // -1 berarti stack kosong
    }

    public boolean push(String aktivitas) {
        if (isFull() || aktivitas == null || aktivitas.trim().isEmpty()) {
            return false;
        }
        top++;
        data[top] = aktivitas;
        return true;
    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }
        String hasil = data[top];
        data[top] = null;
        top--;
        return hasil;
    }

    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == kapasitas - 1;
    }

    public int size() {
        return top + 1;
    }

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