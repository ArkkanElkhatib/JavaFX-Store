public abstract class Product {
    private double price;
    private int stockQuantity;
    private int soldQuantity = 0;

    public Product(double price, int quantity) {
        this.price = price;
        this.stockQuantity = quantity;
    }

    //Officially sell units equal to the input amount
    public double sellUnits() {
        soldQuantity++;
        return price;
    }

    public void addUnits(int units) {
        stockQuantity += units;
    }

    public void toCart() {
        stockQuantity--;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }
}
