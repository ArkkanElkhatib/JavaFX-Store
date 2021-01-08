public class ElectronicStore {
    private final int MAX_PRODUCTS = 10;
    private String name;
    private int curProduct;
    private int curCart;
    private int totalSales;
    private double revenue;
    private int totalStock;
    private int zeroProducts;
    private Product[] stock;
    private Product[] cart;
    private Product[] popularProducts;

    public ElectronicStore(String name) {
        this.name = name;
        stock = new Product[MAX_PRODUCTS];
    }

    //Creating cart based on total stock as that would be the maximum space in cart
    public void createCart() {
        cart = new Product[totalStock];
    }

    //Start -- Popular Products
    //Creating a popularProduct array to be either 3 objects or fewer based on curProducts
    public void createPopularProduct() {
        if (curProduct >=3 ){
            popularProducts = new Product[3];
        } else {
            popularProducts = new Product[curProduct];
        }
        setPopularProduct();
    }

    //Setting the first 3 (or less) objects from sorted stock to the popular products
    public void setPopularProduct() {
        Product[] stockTemp = new Product[curProduct];
        for (int i = 0; i < stockTemp.length; i++) {
            stockTemp[i] = stock[i];
        }
        bubbleSortProducts(stockTemp);
        for (int i = 0; i < popularProducts.length; i++) {
            popularProducts[i] = stockTemp[i];
        }
    }

    //Bubble sort algorithm to sort the stock inventory by their #sold
    public void bubbleSortProducts(Product[] tempStock) {
        boolean sorted = false;
        Product temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < curProduct - 1; i++) {
                if (tempStock[i].getSoldQuantity() < tempStock[i+1].getSoldQuantity()) {
                    temp = tempStock[i];
                    tempStock[i] = tempStock[i+1];
                    tempStock[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }
    //End -- Popular Products

    //Adding a product object to the stock
    public boolean addProduct(Product newProduct) {
        if (curProduct < MAX_PRODUCTS) {
            stock[curProduct] = newProduct;
            curProduct++;
            totalStock += newProduct.getStockQuantity();
            return true;
        }
        return false;
    }

    //Adding a product from stock to the cart
    public boolean addCart(int index) {
        if (index >= 0 && index < curProduct) {
            cart[curCart] = stock[index];
            cart[curCart].toCart();
            curCart++;
            if (stock[index].getStockQuantity() == 0) {
                adjustStock(index);
            }
            return true;
        }
        return false;
    }

    //Adjust Stock Inventory to push 0 stock item to back of the stock array
    public void adjustStock(int index) {
        Product temp = stock[index];
        int last = curProduct-1;
        for (int i = index; i < curProduct - 1; i++) {
            stock[i] = stock[i+1];
        }
        stock[last] = temp;
        zeroProducts++;
    }

    //Start -- Remove from cart
    public void removeCart(int index) {
        if (cart[index].getStockQuantity() == 0) {
            zeroProducts--;
        }
        cart[index].addUnits(1);
        cart[index] = null;
        adjustCart(index);
        curCart--;
    }

    //Adjust Cart such that all null values are at the end of the array
    public void adjustCart(int index) {
        for (int i = index; i < curCart; i++) {
            cart[i] = cart[i+1];
        }
        cart[curCart-1] = null;
    }
    //End -- Remove from cart

    //Selling from cart
    public void sellCart() {
        for (int i = 0; i < curCart; i++) {
            if (cart[i] != null) {
                cart[i].sellUnits();
                revenue += cart[i].getPrice();
                totalSales++;
                totalStock--;
            }
        }
        curCart = 0;
        createCart();
    }

    //Getter Methods
    public String getName() {
        return name;
    }

    public double getRevenue() {
        return revenue;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public String toString() {
        return name;
    }

    public Product[] getStock() {
        return stock;
    }

    public int getCurProduct() {
        return curProduct;
    }

    public Product[] getCart() {
        return cart;
    }

    public int getCurCart() {
        return curCart;
    }

    public Product[] getPopularProducts() {
        return popularProducts;
    }

    public int getZeroProducts() {
        return zeroProducts;
    }

    public double getRevenueCart() {
        double total = 0.00;
        for (int i = 0; i < curCart; i++) {
            total += cart[i].getPrice();
        }
        return total;
    }


    public static ElectronicStore createStore(){
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 3, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 3, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 3, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 3, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 3, 250, "White", "Sub Zero", 15.5, false);
        Fridge f2 = new Fridge(750, 3, 125, "Stainless Steel", "Sub Zero", 23, true);
        ToasterOven t1 = new ToasterOven(25, 3, 50, "Black", "Danby", 8, false);
        ToasterOven t2 = new ToasterOven(75, 3, 50, "Silver", "Toasty", 12, true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }
}
