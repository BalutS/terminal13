
package org.unimag.vista.tiquete;

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
import org.unimag.controlador.tiquete.TiqueteControladorListar;
import org.unimag.dto.AsientoDto;
import org.unimag.dto.PasajeroDto;
import org.unimag.dto.RutaDto;
import org.unimag.dto.TiqueteDto;
import org.unimag.dto.ViajeDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Marco;

public class VistaTiqueteListar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<TiqueteDto> miTabla;

    private static final String ESTILO_CENTRAR = "-fx-alignment: CENTER;";
    private static final String ESTILO_IZQUIERDA = "-fx-alignment: CENTER-LEFT;";
    private static final String ESTILO_ROJO_CENTRADO = "-fx-text-fill: red;" + ESTILO_CENTRAR;
    private static final String ESTILO_VERDE_CENTRADO = "-fx-text-fill: green;" + ESTILO_CENTRAR;

    public VistaTiqueteListar(Stage ventanaPadre, double ancho, double alto) {
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

        int cant = TiqueteControladorListar.obtenerCantidadTiquete();
        Text titulo = new Text("LISTA TIQUETES (" + cant + ")");
        titulo.setFill(Color.web(Configuracion.MORADO_OSCURO));
        titulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));

        cajaVertical.getChildren().addAll(bloqueSeparador, titulo);
    }

    private TableColumn<TiqueteDto, Integer> crearColumnaId() {
        TableColumn<TiqueteDto, Integer> columna = new TableColumn<>("ID");
        columna.setCellValueFactory(new PropertyValueFactory<>("idTiquete"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.1));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<TiqueteDto, String> crearColumnaPasajero() {
        TableColumn<TiqueteDto, String> columna = new TableColumn<>("Pasajero");
        columna.setCellValueFactory(cellData -> {
            PasajeroDto pasajero = cellData.getValue().getPasajeroTiquete();
            if (pasajero != null) {
                return new SimpleStringProperty(pasajero.getNombrePasajero());
            } else {
                return new SimpleStringProperty("-");
            }
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.25));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<TiqueteDto, String> crearColumnaViaje() {
        TableColumn<TiqueteDto, String> columna = new TableColumn<>("Viaje");
        columna.setCellValueFactory(cellData -> {
            ViajeDto viaje = cellData.getValue().getViajeTiquete();
            if (viaje != null) {
                RutaDto ruta = viaje.getRutaViaje();
                if (ruta != null) {
                    return new SimpleStringProperty(ruta.getCiudadOrigenRuta() + "-" + ruta.getCiudadDestinoRuta());
                }
            }
            return new SimpleStringProperty("-");
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.25));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<TiqueteDto, Integer> crearColumnaAsiento() {
        TableColumn<TiqueteDto, Integer> columna = new TableColumn<>("Asiento");
        columna.setCellValueFactory(cellData -> {
            AsientoDto asiento = cellData.getValue().getAsientoTiquete();
            if (asiento != null) {
                return new javafx.beans.property.SimpleIntegerProperty(asiento.getIdAsiento()).asObject();
            } else {
                return null;
            }
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.1));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<TiqueteDto, Boolean> crearColumnaEstado() {
        TableColumn<TiqueteDto, Boolean> columna = new TableColumn<>("Estado");
        columna.setCellValueFactory(new PropertyValueFactory<>("estadoTiquete"));

        columna.setCellFactory(col -> new TableCell<TiqueteDto, Boolean>() {
            @Override
            protected void updateItem(Boolean valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(valor ? "Activo" : "Inactivo");
                    setStyle(valor ? ESTILO_VERDE_CENTRADO : ESTILO_ROJO_CENTRADO);
                }
            }
        });

        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.1));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<TiqueteDto, String> crearColumnaImagen() {
        TableColumn<TiqueteDto, String> columna = new TableColumn<>("Imagen");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreImagenPublicoTiquete"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private void configurarColumnas() {
        miTabla.getColumns().addAll(
                List.of(
                        crearColumnaId(),
                        crearColumnaPasajero(),
                        crearColumnaViaje(),
                        crearColumnaAsiento(),
                        crearColumnaEstado(),
                        crearColumnaImagen()
                ));
    }

    private void crearTabla() {
        configurarColumnas();

        List<TiqueteDto> arrTiquete = TiqueteControladorListar.obtenerTiquete();
        ObservableList<TiqueteDto> datosTabla = FXCollections.observableArrayList(arrTiquete);

        miTabla.setItems(datosTabla);
        miTabla.setPlaceholder(new Text("No hay tiquetes registrados"));

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
