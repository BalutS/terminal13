package org.unimag.vista.asiento;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
import org.unimag.controlador.asiento.AsientoControladorEliminar;
import org.unimag.controlador.asiento.AsientoControladorListar;
import org.unimag.controlador.asiento.AsientoVistasControlador;
import org.unimag.dto.AsientoDto;
import org.unimag.dto.BusDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Icono;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaAsientoAdministrar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<AsientoDto> miTabla;
    private final Button btnEliminar;
    private final Button btnActualizar;
    private final Button btnCancelar;
    private Text titulo;
    private final ObservableList<AsientoDto> datosTabla = FXCollections.observableArrayList();

    private static final String ESTILO_CENTRAR = "-fx-alignment: CENTER;";
    private static final String ESTILO_IZQUIERDA = "-fx-alignment: CENTER-LEFT;";
    private static final String ESTILO_ROJO_CENTRADO = "-fx-text-fill: red;" + ESTILO_CENTRAR;
    private static final String ESTILO_VERDE_CENTRADO = "-fx-text-fill: green;" + ESTILO_CENTRAR;

    public VistaAsientoAdministrar(Stage ventanaPadre, double ancho, double alto) {
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
        btnEliminar = new Button();
        btnActualizar = new Button();
        btnCancelar = new Button();

        btnActualizar.setGraphic(Icono.obtenerIcono("iconoEditar.png", 20));
        btnEliminar.setGraphic(Icono.obtenerIcono("iconoBorrar.png", 20));
        btnCancelar.setGraphic(Icono.obtenerIcono("iconoCancelar.png", 20));

        getChildren().add(marco);

        configurarCajaVertical();
        crearTitulo();
        crearTabla();
        crearBotones();
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

        int cant = AsientoControladorListar.obtenerCantidadAsientos();
        titulo = new Text("ADMINISTRAR ASIENTOS (" + cant + ")");
        titulo.setFill(Color.web(Configuracion.MORADO_OSCURO));
        titulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));

        cajaVertical.getChildren().addAll(bloqueSeparador, titulo);
    }

    private TableColumn<AsientoDto, Integer> crearColumnaId() {
        TableColumn<AsientoDto, Integer> columna = new TableColumn<>("ID Asiento");
        columna.setCellValueFactory(new PropertyValueFactory<>("idAsiento"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<AsientoDto, Integer> crearColumnaBus() {
        TableColumn<AsientoDto, Integer> columna = new TableColumn<>("ID Bus");
        columna.setCellValueFactory(cellData -> {
            BusDto bus = cellData.getValue().getBusAsiento();
            if (bus != null) {
                return new javafx.beans.property.SimpleIntegerProperty(bus.getIdBus()).asObject();
            } else {
                return null;
            }
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<AsientoDto, Boolean> crearColumnaEstado() {
        TableColumn<AsientoDto, Boolean> columna = new TableColumn<>("Estado");
        columna.setCellValueFactory(new PropertyValueFactory<>("estadoAsiento"));

        columna.setCellFactory(col -> new TableCell<AsientoDto, Boolean>() {
            @Override
            protected void updateItem(Boolean valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(valor ? "Ocupado" : "Desocupado");
                    setStyle(valor ? ESTILO_VERDE_CENTRADO : ESTILO_ROJO_CENTRADO);
                }
            }
        });

        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.3));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<AsientoDto, String> crearColumnaImagen() {
        TableColumn<AsientoDto, String> columna = new TableColumn<>("Imagen");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreImagenPrivadoAsiento"));
        columna.setCellFactory(param -> new TableCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    try {
                        Image image = new Image("file:lasFotos/" + item, 50, 50, true, true);
                        imageView.setImage(image);
                        setGraphic(imageView);
                    } catch (Exception e) {
                        setGraphic(null);
                    }
                }
            }
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.3));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private void configurarColumnas() {
        miTabla.getColumns().addAll(
                List.of(
                        crearColumnaId(),
                        crearColumnaBus(),
                        crearColumnaEstado(),
                        crearColumnaImagen()
                ));
    }

    private void crearTabla() {
        configurarColumnas();

        List<AsientoDto> arrAsiento = AsientoControladorListar.obtenerAsientos();
        datosTabla.setAll(arrAsiento);

        miTabla.setItems(datosTabla);
        miTabla.setPlaceholder(new Text("No hay asientos registrados"));

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
    
    private void crearBotones() {
        btnCancelar.setOnAction(e -> {
            miTabla.getSelectionModel().clearSelection();
        });
        btnActualizar.setOnAction(e -> {
           throw new UnsupportedOperationException();
        });
        
        btnEliminar.setOnAction(e -> {
            if (miTabla.getSelectionModel().getSelectedItem() == null) {
                Mensaje.mostrar(Alert.AlertType.WARNING,
                        miEscenario, "Atención", "Debe seleccionar un asiento.");
            } else {
                AsientoDto asientoSeleccionado = miTabla.getSelectionModel().getSelectedItem();

                if (asientoSeleccionado.getCantidadTiqueteAsiento()> 0) {
                    Mensaje.mostrar(Alert.AlertType.WARNING,
                            miEscenario, "Advertencia", "No se puede eliminar el asiento porque tiene tiquetes asociados.");
                    return;
                }

                String mensaje = "¿Está seguro de que desea eliminar el asiento con ID: "
                        + asientoSeleccionado.getIdAsiento() + "?";

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Eliminación");
                alert.setHeaderText(null);
                alert.setContentText(mensaje);
                alert.initOwner(miEscenario);

                if (alert.showAndWait().get() == ButtonType.OK) {
                    int posi = miTabla.getSelectionModel().getSelectedIndex();
                    if (AsientoControladorEliminar.borrar(posi)) {
                        int cant = AsientoControladorListar.obtenerCantidadAsientos();
                        titulo.setText("ADMINISTRAR ASIENTOS (" + cant + ")");

                        datosTabla.setAll(AsientoControladorListar.obtenerAsientos());
                        miTabla.refresh();

                        Mensaje.mostrar(Alert.AlertType.INFORMATION,
                                miEscenario, "Éxito", "El asiento ha sido eliminado correctamente.");
                    } else {
                        Mensaje.mostrar(Alert.AlertType.ERROR,
                                miEscenario, "Error", "No se pudo eliminar el asiento.");
                    }
                } else {
                    miTabla.getSelectionModel().clearSelection();
                }
            }
        });
        
        HBox cajaBotones = new HBox(20);
        cajaBotones.setAlignment(Pos.CENTER);
        cajaBotones.getChildren().addAll(btnActualizar, btnEliminar, btnCancelar);
        cajaVertical.getChildren().add(cajaBotones);
    }
}
