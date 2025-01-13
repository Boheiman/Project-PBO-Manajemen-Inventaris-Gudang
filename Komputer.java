public class Komputer extends BarangElektronik {
    private String processor;
    private int ramSize;
    private int storageSize;
    private String os;
    private String jenisCasing;

    // Constructor
    public Komputer(String kodeBarang, String namaBarang, String kategori, int stok, double harga,
                    Pemasok pemasok, String processor, int ramSize, int storageSize, String os, String jenisCasing) {
        super(kodeBarang, namaBarang, kategori, stok, harga, pemasok);
        this.processor = processor;
        this.ramSize = ramSize;
        this.storageSize = storageSize;
        this.os = os;
        this.jenisCasing = jenisCasing;
    }

    // Getters and Setters
    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public int getRamSize() {
        return ramSize;
    }

    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(int storageSize) {
        this.storageSize = storageSize;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getJenisCasing() {
        return jenisCasing;
    }

    public void setJenisCasing(String jenisCasing) {
        this.jenisCasing = jenisCasing;
    }

    @Override
    public String toString() {
        return super.toString() + ", Processor=" + processor + ", RAM=" + ramSize + "GB, Storage=" + storageSize + 
                "GB, OS=" + os + ", Jenis Casing=" + jenisCasing;
    }
}
