public class Laptop extends Computer {
    private double screenSize;

    public Laptop(double price, int quantity, double cpuSpeed,
                   int ram, boolean ssd, int storage, double screenSize) {
        super(price, quantity, cpuSpeed, ram, ssd, storage);
        this.screenSize = screenSize;
    }

    public String toString() {
        if (isSsd()) {
            return (String.format("%.1f inch Laptop PC with %.1fGHZ CPU, %dGB RAM, %dGB SSD drive.\n(%.2f dollars each, %d in stock, %d sold)",
                    screenSize, getCpuSpeed(), getRam(), getStorage(), getPrice(), getStockQuantity(), getSoldQuantity()));
        } else {
            return (String.format("%.1f inch Laptop PC with %.1fGHZ CPU, %dGB RAM, %dGB HDD drive.\n(%.2f dollars each, %d in stock, %d sold)",
                    screenSize, getCpuSpeed(), getRam(), getStorage(), getPrice(), getStockQuantity(), getSoldQuantity()));
        }

    }

}
