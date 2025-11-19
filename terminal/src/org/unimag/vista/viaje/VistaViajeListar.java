
package org.unimag.vista.viaje;

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
import org.unimag.controlador.viaje.ViajeControladorListar;
import org.unimag.dto.BusDto;
import org.unimag.dto.ConductorDto;
import org.unimag.dto.RutaDto;
import org.unimag.dto.ViajeDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Marco;

public class VistaViajeListar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<ViajeDto> miTabla;

    private static final String ESTILO_CENTRAR = "-fx-alignment: CENTER;";
    private static final String ESTILO_IZQUIERDA = "-fx-alignment: CENTER-LEFT;";
    private static final String ESTILO_ROJO_CENTRADO = "-fx-text-fill: red;" + ESTILO_CENTRAR;
    private static final String ESTILO_VERDE_CENTRADO = "-fx-text-fill: green;" + ESTILO_CENTRAR;

    public VistaViajeListar(Stage ventanaPadre, double ancho, double alto) {
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

        int cant = ViajeControladorListar.obtenerCantidadViaje();
        Text titulo = new Text("LISTA VIAJES (" + cant + ")");
        titulo.setFill(Color.web(Configuracion.MORADO_OSCURO));
        titulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));

        cajaVertical.getChildren().addAll(bloqueSeparador, titulo);
    }

    private TableColumn<ViajeDto, Integer> crearColumnaId() {
        TableColumn<ViajeDto, Integer> columna = new TableColumn<>("ID");
        columna.setCellValueFactory(new PropertyValueFactory<>("idViaje"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.05));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<ViajeDto, String> crearColumnaRuta() {
        TableColumn<ViajeDto, String> columna = new TableColumn<>("Ruta");
        columna.setCellValueFactory(cellData -> {
            RutaDto ruta = cellData.getValue().getRutaViaje();
            if (ruta != null) {
                return new SimpleStringProperty(ruta.getCiudadOrigenRuta() + "-" + ruta.getCiudadDestinoRuta());
            } else {
                return new SimpleStringProperty("-");
            }
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<ViajeDto, String> crearColumnaConductor() {
        TableColumn<ViajeDto, String> columna = new TableColumn<>("Conductor");
        columna.setCellValueFactory(cellData -> {
            ConductorDto conductor = cellData.getValue().getConductorViaje();
            if (conductor != null) {
                return new SimpleStringProperty(conductor.getNombreConductor());
            } else {
                return new SimpleStringProperty("-");
            }
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }
    
    private TableColumn<ViajeDto, Integer> crearColumnaBus() {
        TableColumn<ViajeDto, Integer> columna = new TableColumn<>("Bus");
        columna.setCellValueFactory(cellData -> {
            BusDto bus = cellData.getValue().getBusViaje();
            if (bus != null) {
                return new javafx.beans.property.SimpleIntegerProperty(bus.getIdBus()).asObject();
            } else {
                return null;
            }
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.05));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<ViajeDto, String> crearColumnaFecha() {
        TableColumn<ViajeDto, String> columna = new TableColumn<>("Fecha");
        columna.setCellValueFactory(new PropertyValueFactory<>("fechaViaje"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.1));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<ViajeDto, Integer> crearColumnaHora() {
        TableColumn<ViajeDto, Integer> columna = new TableColumn<>("Hora");
        columna.setCellValueFactory(new PropertyValueFactory<>("horaViaje"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.1));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<ViajeDto, Boolean> crearColumnaEstado() {
        TableColumn<ViajeDto, Boolean> columna = new TableColumn<>("Estado");
        columna.setCellValueFactory(new PropertyValueFactory<>("estadoViaje"));

        columna.setCellFactory(col -> new TableCell<ViajeDto, Boolean>() {
            @Override
            protected void updateItem(Boolean valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(valor ? "Finalizado" : "En curso");
                    setStyle(valor ? ESTILO_ROJO_CENTRADO : ESTILO_VERDE_CENTRADO);
                }
            }
        });

        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.1));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<ViajeDto, String> crearColumnaImagen() {
        TableColumn<ViajeDto, String> columna = new TableColumn<>("Imagen");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreImagenPublicoViaje"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private void configurarColumnas() {
        miTabla.getColumns().addAll(
                List.of(
                        crearColumnaId(),
                        crearColumnaRuta(),
                        crearColumnaConductor(),
                        crearColumnaBus(),
                        crearColumnaFecha(),
                        crearColumnaHora(),
                        crearColumnaEstado(),
                        crearColumnaImagen()
                ));
    }

    private void crearTabla() {
        configurarColumnas();

        List<ViajeDto> arrViaje = ViajeControladorListar.obtenerViajes();
        ObservableList<ViajeDto> datosTabla = FXCollections.observableArrayList(arrViaje);

        miTabla.setItems(datosTabla);
        miTabla.setPlaceholder(new Text("No hay viajes registrados"));

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
