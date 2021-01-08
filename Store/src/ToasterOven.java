public class ToasterOven extends Appliance {
    private int width;
    private boolean convection;

    public ToasterOven(double price, int quantity, int wattage,
                  String color, String brand, int width, boolean convection) {
        super(price, quantity, wattage, color, brand);
        this.width = width;
        this.convection = convection;
    }

    public String toString() {
        if (convection) {
            return (String.format("%d inch %s Toaster with Convection (%s, %d watts)\n(%.2f dollars each, %d in stock, %d sold)",
                    width, getBrand(), getColor(), getWattage(), getPrice(), getStockQuantity(), getSoldQuantity()));
        } else {
            return (String.format("%d inch %s Toaster (%s, %d watts)\n(%.2f dollars each, %d in stock, %d sold)",
                    width, getBrand(), getColor(), getWattage(), getPrice(), getStockQuantity(), getSoldQuantity()));
        }
    }
}
