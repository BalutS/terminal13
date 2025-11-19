
package org.unimag.vista.ruta;

import java.text.DecimalFormat;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.unimag.controlador.ruta.RutaControladorListar;
import org.unimag.dto.RutaDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Marco;

public class VistaRutaListar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<RutaDto> miTabla;

    private static final String ESTILO_CENTRAR = "-fx-alignment: CENTER;";
    private static final String ESTILO_DERECHA = "-fx-alignment: CENTER-RIGHT;";
    private static final String ESTILO_IZQUIERDA = "-fx-alignment: CENTER-LEFT;";

    public VistaRutaListar(Stage ventanaPadre, double ancho, double alto) {
        setAlignment(Pos.CENTER);
        miEscenario = ventanaPadre;
        marco = Marco.crear(miEscenario,
                Configuracion.MARCO_ALTO_PORCENTAJE,
                Configuracion.MARCO_ANCHO_PORCENTAJE,
                Configuracion.DEGRADE_ARREGLO_GENERO,
                Configuracion.DEGRADE_BORDE
        );

        miTabla = new TableView<>();
        cajaVertical = new VBox(20);
        getChildren().add(marco);

        configurarCajaVertical();
        crearTitulo();
        crearTabla();

    }

    private void configurarCajaVertical() {
        cajaVertical.setAlignment(Pos.TOP_CENTER);
        cajaVertical.prefWidthProperty().bind(miEscenario.widthProperty());
        cajaVertical.prefHeightProperty().bind(miEscenario.heightProperty());
    }

    private void crearTitulo() {
        Region bloqueSeparador = new Region();
        bloqueSeparador.prefHeightProperty().bind(
                miEscenario.heightProperty().multiply(0.05));

        int cant = RutaControladorListar.obtenerCantidadRuta();
        Text titulo = new Text("LISTA RUTAS (" + cant + ")");
        titulo.setFill(Color.web(Configuracion.MORADO_OSCURO));
        titulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));

        cajaVertical.getChildren().addAll(bloqueSeparador, titulo);
    }

    private TableColumn<RutaDto, Integer> crearColumnaId() {
        TableColumn<RutaDto, Integer> columna = new TableColumn<>("ID");
        columna.setCellValueFactory(new PropertyValueFactory<>("idRuta"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.1));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<RutaDto, String> crearColumnaOrigen() {
        TableColumn<RutaDto, String> columna = new TableColumn<>("Origen");
        columna.setCellValueFactory(new PropertyValueFactory<>("ciudadOrigenRuta"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.25));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<RutaDto, String> crearColumnaDestino() {
        TableColumn<RutaDto, String> columna = new TableColumn<>("Destino");
        columna.setCellValueFactory(new PropertyValueFactory<>("ciudadDestinoRuta"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.25));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<RutaDto, Double> crearColumnaTarifa() {
        TableColumn<RutaDto, Double> columna = new TableColumn<>("Tarifa");
        columna.setCellValueFactory(new PropertyValueFactory<>("tarifaRuta"));

        columna.setCellFactory(col -> new TableCell<RutaDto, Double>() {
            private final DecimalFormat formato = new DecimalFormat("#.#############");

            @Override
            protected void updateItem(Double tarifa, boolean empty) {
                super.updateItem(tarifa, empty);
                if (empty || tarifa == null) {
                    setText(null);
                } else {
                    setText(formato.format(tarifa));
                }
            }
        });

        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_DERECHA);

        return columna;
    }

    private TableColumn<RutaDto, String> crearColumnaImagen() {
        TableColumn<RutaDto, String> columna = new TableColumn<>("Imagen");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreImagenPublicoRuta"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private void configurarColumnas() {
        miTabla.getColumns().addAll(
                List.of(
                        crearColumnaId(),
                        crearColumnaOrigen(),
                        crearColumnaDestino(),
                        crearColumnaTarifa(),
                        crearColumnaImagen()
                ));
    }

    private void crearTabla() {
        configurarColumnas();

        List<RutaDto> arrRuta = RutaControladorListar.obtenerRutas();
        ObservableList<RutaDto> datosTabla = FXCollections.observableArrayList(arrRuta);

        miTabla.setItems(datosTabla);
        miTabla.setPlaceholder(new Text("No hay rutas registradas"));

        miTabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        miTabla.maxWidthProperty().bind(miEscenario.widthProperty().multiply(0.60));
        miTabla.maxHeightProperty().bind(miEscenario.heightProperty().multiply(0.50));

        miEscenario.heightProperty().addListener((o, oldVal, newVal)
                -> miTabla.setPrefHeight(newVal.doubleValue()
                ));
        VBox.setVgrow(miTabla, Priority.ALWAYS);

        cajaVertical.getChildren().add(miTabla);
        getChildren().add(cajaVertical);
    }
}
