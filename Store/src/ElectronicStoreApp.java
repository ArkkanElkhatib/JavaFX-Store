import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ElectronicStoreApp extends Application {
    ElectronicStore model;
    ElectronicStoreView view;
    Stage baseStage;

    public void start(Stage primaryStage) throws Exception {
        baseStage = primaryStage;
        runStore(baseStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void handleAddCart() {
        int selectedIndex = view.getStockItemsList().getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            model.addCart(selectedIndex);
        }

        view.update();
    }

    public void handleRemoveCart() {
        int selectedIndex = view.getCartItemsList().getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            model.removeCart(selectedIndex);
        }

        view.update();
    }

    public void handleCompleteSale() {
        model.sellCart();
        view.update();
    }

    public void handleReset() {
        runStore(baseStage);
    }

    public void runStore (Stage primaryStage) {
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);
        model.createCart();
        model.createPopularProduct();

        view.getAddCartButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleAddCart();
            }
        });

        view.getRemoveCartButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleRemoveCart();
            }
        });

        view.getCompleteSaleButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleCompleteSale();
            }
        });

        view.getResetButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleReset();
            }
        });

        view.getStockItemsList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update();
            }
        });

        view.getCartItemsList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
              view.update();
            }
        });

        primaryStage.setTitle("Electronic Store Application - " + view.model.getName());
        primaryStage.setScene(new Scene(view, 800, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
        view.update();
    }

}


