package org.unimag.vista.gestor;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.FontWeight;
import javafx.scene.image.ImageView;
import org.unimag.recurso.utilidad.Icono;

public class VistaAcerca {

    public final static String LBL_TEXTO = "#2E2E2E";
	public final static String CABECERA_COLOR_FONDO = "-fx-background-color: #C3A7E8;"
            + "-fx-background-color:linear-gradient(#2E2E2E,#C3A7E8, #F7D6E0); ";

    public static void mostrar(Stage escenarioPadre, double anchoPanel, double altoPanel) {
        Stage escenarioModal = new Stage();

        VBox miPanel = new VBox(10);
        miPanel.setAlignment(Pos.CENTER);
        miPanel.setPadding(new Insets(20));
        miPanel.setStyle(CABECERA_COLOR_FONDO);

        // Martin Soto
        ImageView martin = Icono.obtenerIcono("DesarrolladorMartin.jpg", 100);
        Label martinNombre = new Label("Martin Soto");
        martinNombre.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        Label martinCorreo = new Label("mesoto@unimagdalena.edu.co");
        Label martinTelefono = new Label("3016291203");
        VBox martinBox = new VBox(5, martin, martinNombre, martinCorreo, martinTelefono);
        martinBox.setAlignment(Pos.CENTER);

        // Leonardo Melo
        ImageView leonardo = Icono.obtenerIcono("DesarrolladorLeonardo.jpg", 100);
        Label leonardoNombre = new Label("Leonardo Melo");
        leonardoNombre.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        Label leonardoCorreo = new Label("lamelo@unimagdalena.edu.co");
        Label leonardoTelefono = new Label("3206228060");
        VBox leonardoBox = new VBox(5, leonardo, leonardoNombre, leonardoCorreo, leonardoTelefono);
        leonardoBox.setAlignment(Pos.CENTER);

        // Juan Arias
        ImageView juan = Icono.obtenerIcono("DesarrolladorJuan.jpg", 100);
        Label juanNombre = new Label("Juan Arias");
        juanNombre.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        Label juanCorreo = new Label("jegarias@unimagdalena.edu.co");
        Label juanTelefono = new Label("3113775413");
        VBox juanBox = new VBox(5, juan, juanNombre, juanCorreo, juanTelefono);
        juanBox.setAlignment(Pos.CENTER);
        
        HBox developersBox = new HBox(20, martinBox, leonardoBox, juanBox);
        developersBox.setAlignment(Pos.CENTER);

        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setPrefWidth(160);
        btnCerrar.setTextFill(Color.web("#E82E68"));
        btnCerrar.setOnAction(event -> escenarioModal.close());

        miPanel.getChildren().addAll(developersBox, btnCerrar);

        Scene nuevaEscena = new Scene(miPanel, anchoPanel, altoPanel);
        escenarioModal.setScene(nuevaEscena);
        escenarioModal.initModality(Modality.APPLICATION_MODAL);
        escenarioModal.initStyle(StageStyle.UTILITY);
        escenarioModal.setTitle("Acerca de ...");
        escenarioModal.show();

        escenarioPadre.getScene().getRoot().setOpacity(0.2);
        escenarioModal.setOnHidden(event -> escenarioPadre.getScene().getRoot().setOpacity(1.0));
    }
}
