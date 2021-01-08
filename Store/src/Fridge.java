public class Fridge extends Appliance {
    private double cubicFeet;
    private boolean hasFreezer;

    public Fridge(double price, int quantity, int wattage,
                   String color, String brand, double cubicFeet, boolean hasFreezer) {
        super(price, quantity, wattage, color, brand);
        this.cubicFeet = cubicFeet;
        this.hasFreezer = hasFreezer;
    }

    public String toString() {
        if (hasFreezer) {
            return (String.format("%.1f cu. ft. %s Fridge with Freezer (%s, %d watts)\n(%.2f dollars each, %d in stock, %d sold)",
                    cubicFeet, getBrand(), getColor(), getWattage(), getPrice(), getStockQuantity(), getSoldQuantity()));
        } else {
            return (String.format("%.1f cu. ft. %s Fridge (%s, %d watts)\n(%.2f dollars each, %d in stock, %d sold)",
                    cubicFeet, getBrand(), getColor(), getWattage(), getPrice(), getStockQuantity(), getSoldQuantity()));
        }
    }
}
