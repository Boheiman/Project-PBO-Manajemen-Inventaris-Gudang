public class Laptop extends BarangElektronik {
    private String processor;
    private int ramSize;
    private int storageSize;
    private double screenSize;
    private boolean isTouchscreen;

    // Constructor
    public Laptop(String kodeBarang, String namaBarang, String kategori, int stok, double harga,
                  Pemasok pemasok, String processor, int ramSize, int storageSize, double screenSize, boolean isTouchscreen) {
        super(kodeBarang, namaBarang, kategori, stok, harga, pemasok);
        this.processor = processor;
        this.ramSize = ramSize;
        this.storageSize = storageSize;
        this.screenSize = screenSize;
        this.isTouchscreen = isTouchscreen;
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

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public boolean isTouchscreen() {
        return isTouchscreen;
    }

    public void setTouchscreen(boolean touchscreen) {
        isTouchscreen = touchscreen;
    }

    @Override
    public String toString() {
        return super.toString() + ", Processor=" + processor + ", RAM=" + ramSize + "GB, Storage=" + storageSize + 
                "GB, Screen=" + screenSize + " inches, Touchscreen=" + isTouchscreen;
    }
}
