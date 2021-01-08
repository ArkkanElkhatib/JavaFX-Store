import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ElectronicStoreView extends Pane {
    ElectronicStore model;
    Label storeSumLabel;
    Label numSalesLabel;
    Label revLabel;
    Label revPerSaleLabel;
    Label popItemsLabel;
    Label stockItemsLabel;
    Label cartItemsLabel;
    TextField numSalesField;
    TextField revField;
    TextField revPerSaleField;
    ListView<String> popularItemsList;
    ListView<String> stockItemsList;
    ListView<String> cartItemsList;
    Button resetButton;
    Button addCartButton;
    Button removeCartButton;
    Button completeSaleButton;

    public ElectronicStoreView(ElectronicStore iModel) {
        model = iModel;

        //Store Summary Label
        storeSumLabel = new Label("Store Summary:");
        storeSumLabel.relocate(10, 5);
        storeSumLabel.setPrefSize(152, 25);
        storeSumLabel.setAlignment(Pos.CENTER);

        //Store Stock Label
        stockItemsLabel = new Label("Store Stock:");
        stockItemsLabel.relocate(172, 5);
        stockItemsLabel.setPrefSize(304, 25);
        stockItemsLabel.setAlignment(Pos.CENTER);

        //Current Cart Label
        cartItemsLabel = new Label(String.format("Current Cart (%.2f):", model.getRevenueCart()));
        cartItemsLabel.relocate(486, 5);
        cartItemsLabel.setPrefSize(304, 25);
        cartItemsLabel.setAlignment(Pos.CENTER);

        //Number of Sales Label
        numSalesLabel = new Label("# Sales:");
        numSalesLabel.relocate(10, 35);
        numSalesLabel.setPrefSize(76, 25);
        numSalesLabel.setAlignment(Pos.CENTER);

        //Revenue Label
        revLabel = new Label("Revenue:");
        revLabel.relocate(10, 65);
        revLabel.setPrefSize(76, 25);
        revLabel.setAlignment(Pos.CENTER);

        //Revenue per Sale Label
        revPerSaleLabel = new Label("$ / Sale:");
        revPerSaleLabel.relocate(10, 95);
        revPerSaleLabel.setPrefSize(76, 25);
        revPerSaleLabel.setAlignment(Pos.CENTER);

        //Most Popular Item Label
        popItemsLabel = new Label("Most Popular Items:");
        popItemsLabel.relocate(10, 125);
        popItemsLabel.setPrefSize(152, 25);
        popItemsLabel.setAlignment(Pos.CENTER);

        //Number of Sales Field
        numSalesField = new TextField("0");
        numSalesField.relocate(86, 35);
        numSalesField.setPrefSize(76, 25);

        //Revenue Field
        revField = new TextField("0.00");
        revField.relocate(86, 65);
        revField.setPrefSize(76, 25);

        //Revenue per Sale Field
        revPerSaleField = new TextField("N/A");
        revPerSaleField.relocate(86, 95);
        revPerSaleField.setPrefSize(76, 25);

        //Popular Items List View
        popularItemsList = new ListView<String>();
        popularItemsList.relocate(10, 155);
        popularItemsList.setPrefSize(152, 180);

        //Store Stock List View
        stockItemsList = new ListView<String>();
        stockItemsList.relocate(172, 35);
        stockItemsList.setPrefSize(300, 300);

        //Current Cart List View
        cartItemsList = new ListView<String>();
        cartItemsList.relocate(486, 35);
        cartItemsList.setPrefSize(300, 300);

        //Reset Store Button
        resetButton = new Button("Reset Store");
        resetButton.relocate(30, 340);
        resetButton.setPrefSize(112, 40);

        //Add to Cart Button
        addCartButton = new Button("Add to Cart");
        addCartButton.relocate(266, 340);
        addCartButton.setPrefSize(112, 40);

        //Remove From Cart Button
        removeCartButton = new Button("Remove from Cart");
        removeCartButton.relocate(486, 340);
        removeCartButton.setPrefSize(132, 40);

        //Complete Sale Button
        completeSaleButton = new Button("Complete Sale");
        completeSaleButton.relocate(654, 340);
        completeSaleButton.setPrefSize(132, 40);

        getChildren().addAll(storeSumLabel, stockItemsLabel, cartItemsLabel, numSalesLabel,
                revLabel, revPerSaleLabel, popItemsLabel, popularItemsList, stockItemsList,
                numSalesField, revField, revPerSaleField, cartItemsList, resetButton,
                addCartButton, removeCartButton, completeSaleButton);

    }

    public Button getResetButton() {
        return resetButton;
    }

    public Button getAddCartButton() {
        return addCartButton;
    }

    public Button getRemoveCartButton() {
        return removeCartButton;
    }

    public Button getCompleteSaleButton() {
        return completeSaleButton;
    }

    public ListView getStockItemsList() {
        return stockItemsList;
    }

    public ListView getCartItemsList() {
        return cartItemsList;
    }

    public void update() {
        updateStock();
        updateCart();
        updatePopularProducts();
        updateSummary();
        updateButtons();
    }

    public void updateStock() {
        Product[] stock = model.getStock();
        String[] observableStock = new String[model.getCurProduct() - model.getZeroProducts()];

        for (int i = 0; i < observableStock.length; i++) {
            if (stock[i].getStockQuantity() != 0) {
                observableStock[i] = stock[i].toString();
            }
        }

        stockItemsList.setItems(FXCollections.observableArrayList(observableStock));
    }

    public void updateCart() {
        Product[] cart = model.getCart();
        String[] observableCart = new String[model.getCurCart()];

        for (int i = 0; i < observableCart.length; i++) {
            observableCart[i] = cart[i].toString();
        }

        cartItemsLabel.setText(String.format("Current Cart (%.2f):", model.getRevenueCart()));

        cartItemsList.setItems(FXCollections.observableArrayList(observableCart));
    }

    public void updatePopularProducts() {
        model.setPopularProduct();

        Product[] popularProducts = model.getPopularProducts();
        String[] observablePopularProducts = new String[popularProducts.length];

        for (int i = 0; i < observablePopularProducts.length; i++) {
            observablePopularProducts[i] = popularProducts[i].toString();
        }

        popularItemsList.setItems(FXCollections.observableArrayList(observablePopularProducts));
    }

    public void updateSummary() {
        revField.setText(String.format("%.2f", model.getRevenue()));
        numSalesField.setText(String.format("%d", model.getTotalSales()));

        if (model.getRevenue() > 0 && model.getTotalSales() > 0){
            revPerSaleField.setText(String.format("%.2f", model.getRevenue() / model.getTotalSales()));
        }

    }

    public void updateButtons() {
        if (stockItemsList.getSelectionModel().isEmpty()) {
            addCartButton.setDisable(true);
        } else {
            addCartButton.setDisable(false);
        }

        if (cartItemsList.getSelectionModel().isEmpty()) {
            removeCartButton.setDisable(true);
        } else {
            removeCartButton.setDisable(false);
        }

        if (model.getCurCart() == 0) {
            completeSaleButton.setDisable(true);
        } else {
            completeSaleButton.setDisable(false);
        }
    }
}
