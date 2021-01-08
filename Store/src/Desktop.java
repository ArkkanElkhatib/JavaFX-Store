public class Desktop extends Computer{
    private String profile;

    public Desktop(double price, int quantity, double cpuSpeed,
                   int ram, boolean ssd, int storage, String profile) {
        super(price, quantity, cpuSpeed, ram, ssd, storage);
        this.profile = profile;
    }

    public String toString() {
        if (isSsd()) {
            return (String.format("%s Desktop PC with %.1fGHZ CPU, %dGB RAM, %dGB SSD drive.\n(%.2f dollars each, %d in stock, %d sold)",
                    profile, getCpuSpeed(), getRam(), getStorage(), getPrice(), getStockQuantity(), getSoldQuantity()));
        } else {
            return (String.format("%s Desktop PC with %.1fGHZ CPU, %dGB RAM, %dGB HDD drive.\n(%.2f dollars each, %d in stock, %d sold)",
                    profile, getCpuSpeed(), getRam(), getStorage(), getPrice(), getStockQuantity(), getSoldQuantity()));
        }

    }

}
