
package org.unimag.vista.equipaje;

import java.text.DecimalFormat;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
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
import org.unimag.controlador.equipaje.EquipajeControladorListar;
import org.unimag.dto.EquipajeDto;
import org.unimag.dto.PasajeroDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Marco;

public class VistaEquipajeListar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<EquipajeDto> miTabla;

    private static final String ESTILO_CENTRAR = "-fx-alignment: CENTER;";
    private static final String ESTILO_DERECHA = "-fx-alignment: CENTER-RIGHT;";
    private static final String ESTILO_IZQUIERDA = "-fx-alignment: CENTER-LEFT;";

    public VistaEquipajeListar(Stage ventanaPadre, double ancho, double alto) {
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

        int cant = EquipajeControladorListar.obtenerCantidadEquipaje();
        Text titulo = new Text("LISTA EQUIPAJES (" + cant + ")");
        titulo.setFill(Color.web(Configuracion.MORADO_OSCURO));
        titulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));

        cajaVertical.getChildren().addAll(bloqueSeparador, titulo);
    }

    private TableColumn<EquipajeDto, Integer> crearColumnaId() {
        TableColumn<EquipajeDto, Integer> columna = new TableColumn<>("ID");
        columna.setCellValueFactory(new PropertyValueFactory<>("idEquipaje"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.1));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<EquipajeDto, Double> crearColumnaPeso() {
        TableColumn<EquipajeDto, Double> columna = new TableColumn<>("Peso");
        columna.setCellValueFactory(new PropertyValueFactory<>("pesoEquipaje"));

        columna.setCellFactory(col -> new TableCell<EquipajeDto, Double>() {
            private final DecimalFormat formato = new DecimalFormat("#.#############");

            @Override
            protected void updateItem(Double peso, boolean empty) {
                super.updateItem(peso, empty);
                if (empty || peso == null) {
                    setText(null);
                } else {
                    setText(formato.format(peso));
                }
            }
        });

        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_DERECHA);

        return columna;
    }

    private TableColumn<EquipajeDto, String> crearColumnaDuenio() {
        TableColumn<EquipajeDto, String> columna = new TableColumn<>("Dueño");
        columna.setCellValueFactory(cellData -> {
            PasajeroDto duenio = cellData.getValue().getDuenioEquipaje();
            if (duenio != null) {
                return new SimpleStringProperty(duenio.getNombrePasajero());
            } else {
                return new SimpleStringProperty("-");
            }
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.4));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<EquipajeDto, String> crearColumnaImagen() {
        TableColumn<EquipajeDto, String> columna = new TableColumn<>("Imagen");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreImagenPublicoEquipaje"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.3));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private void configurarColumnas() {
        miTabla.getColumns().addAll(
                List.of(
                        crearColumnaId(),
                        crearColumnaPeso(),
                        crearColumnaDuenio(),
                        crearColumnaImagen()
                ));
    }

    private void crearTabla() {
        configurarColumnas();

        List<EquipajeDto> arrEquipaje = EquipajeControladorListar.obtenerEquipaje();
        ObservableList<EquipajeDto> datosTabla = FXCollections.observableArrayList(arrEquipaje);

        miTabla.setItems(datosTabla);
        miTabla.setPlaceholder(new Text("No hay equipajes registrados"));

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
