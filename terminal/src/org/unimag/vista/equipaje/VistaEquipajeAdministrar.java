package org.unimag.vista.equipaje;

import java.text.DecimalFormat;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
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
import org.unimag.controlador.equipaje.EquipajeControladorEliminar;
import org.unimag.controlador.equipaje.EquipajeControladorListar;
import org.unimag.dto.EquipajeDto;
import org.unimag.dto.PasajeroDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Icono;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaEquipajeAdministrar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<EquipajeDto> miTabla;
    private final Button btnEliminar;
    private final Button btnActualizar;
    private final Button btnCancelar;
    private Text titulo;
    private final ObservableList<EquipajeDto> datosTabla = FXCollections.observableArrayList();

    private static final String ESTILO_CENTRAR = "-fx-alignment: CENTER;";
    private static final String ESTILO_DERECHA = "-fx-alignment: CENTER-RIGHT;";
    private static final String ESTILO_IZQUIERDA = "-fx-alignment: CENTER-LEFT;";

    public VistaEquipajeAdministrar(Stage ventanaPadre, double ancho, double alto) {
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

        int cant = EquipajeControladorListar.obtenerCantidadEquipaje();
        titulo = new Text("ADMINISTRAR EQUIPAJES (" + cant + ")");
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
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreImagenPrivadoEquipaje"));
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
                        crearColumnaPeso(),
                        crearColumnaDuenio(),
                        crearColumnaImagen()
                ));
    }

    private void crearTabla() {
        configurarColumnas();

        List<EquipajeDto> arrEquipaje = EquipajeControladorListar.obtenerEquipaje();
        datosTabla.setAll(arrEquipaje);

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

    private void crearBotones() {
        
        btnCancelar.setOnAction(e -> {
            miTabla.getSelectionModel().clearSelection();
        });

        btnEliminar.setOnAction(e -> {
            if (miTabla.getSelectionModel().getSelectedItem() == null) {
                Mensaje.mostrar(Alert.AlertType.WARNING,
                        miEscenario, "Atención", "Debe seleccionar un equipaje.");
            } else {
                EquipajeDto equipajeSeleccionado = miTabla.getSelectionModel().getSelectedItem();

                String mensaje = "¿Está seguro de que desea eliminar el equipaje con ID: "
                        + equipajeSeleccionado.getIdEquipaje() + "?";

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Eliminación");
                alert.setHeaderText(null);
                alert.setContentText(mensaje);
                alert.initOwner(miEscenario);

                if (alert.showAndWait().get() == ButtonType.OK) {
                    int posi = miTabla.getSelectionModel().getSelectedIndex();
                    if (EquipajeControladorEliminar.borrar(posi)) {
                        int cant = EquipajeControladorListar.obtenerCantidadEquipaje();
                        titulo.setText("ADMINISTRAR EQUIPAJES (" + cant + ")");

                        datosTabla.setAll(EquipajeControladorListar.obtenerEquipaje());
                        miTabla.refresh();

                        Mensaje.mostrar(Alert.AlertType.INFORMATION,
                                miEscenario, "Éxito", "El equipaje ha sido eliminado correctamente.");
                    } else {
                        Mensaje.mostrar(Alert.AlertType.ERROR,
                                miEscenario, "Error", "No se pudo eliminar el equipaje.");
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
