package org.unimag.vista.bus;

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
import org.unimag.controlador.bus.BusControladorEliminar;
import org.unimag.controlador.bus.BusControladorListar;
import org.unimag.dto.BusDto;
import org.unimag.dto.EmpresaDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Icono;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaBusAdministrar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<BusDto> miTabla;
    private final Button btnEliminar;
    private final Button btnActualizar;
    private final Button btnCancelar;
    private Text titulo;
    private final ObservableList<BusDto> datosTabla = FXCollections.observableArrayList();

    private static final String ESTILO_CENTRAR = "-fx-alignment: CENTER;";
    private static final String ESTILO_IZQUIERDA = "-fx-alignment: CENTER-LEFT;";

    public VistaBusAdministrar(Stage ventanaPadre, double ancho, double alto) {
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

        int cant = BusControladorListar.obtenerCantidadBus();
        titulo = new Text("ADMINISTRAR BUSES (" + cant + ")");
        titulo.setFill(Color.web(Configuracion.MORADO_OSCURO));
        titulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));

        cajaVertical.getChildren().addAll(bloqueSeparador, titulo);
    }

    private TableColumn<BusDto, Integer> crearColumnaId() {
        TableColumn<BusDto, Integer> columna = new TableColumn<>("ID");
        columna.setCellValueFactory(new PropertyValueFactory<>("idBus"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.1));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<BusDto, String> crearColumnaModelo() {
        TableColumn<BusDto, String> columna = new TableColumn<>("Modelo");
        columna.setCellValueFactory(new PropertyValueFactory<>("modeloBus"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.3));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<BusDto, String> crearColumnaEmpresa() {
        TableColumn<BusDto, String> columna = new TableColumn<>("Empresa");
        columna.setCellValueFactory(cellData -> {
            EmpresaDto empresa = cellData.getValue().getEmpresaBus();
            if (empresa != null) {
                return new SimpleStringProperty(empresa.getNombreEmpresa());
            } else {
                return new SimpleStringProperty("-");
            }
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.3));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<BusDto, String> crearColumnaImagen() {
        TableColumn<BusDto, String> columna = new TableColumn<>("Imagen");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreImagenPrivadoBus"));
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
                        crearColumnaModelo(),
                        crearColumnaEmpresa(),
                        crearColumnaImagen()
                ));
    }

    private void crearTabla() {
        configurarColumnas();

        List<BusDto> arrBus = BusControladorListar.obtenerBuses();
        datosTabla.setAll(arrBus);

        miTabla.setItems(datosTabla);
        miTabla.setPlaceholder(new Text("No hay buses registrados"));

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
                        miEscenario, "Atención", "Debe seleccionar un bus.");
            } else {
                BusDto busSeleccionado = miTabla.getSelectionModel().getSelectedItem();

                if (busSeleccionado.getCantidadViajeBus() > 0) {
                    Mensaje.mostrar(Alert.AlertType.WARNING,
                            miEscenario, "Advertencia",
                            "No se puede eliminar el bus porque tiene "
                            + busSeleccionado.getCantidadViajeBus() + " viaje(s) asociado(s).");
                    return;
                }

                if (busSeleccionado.getCantidadAsientoBus() > 0) {
                    Mensaje.mostrar(Alert.AlertType.WARNING,
                            miEscenario, "Advertencia",
                            "No se puede eliminar el bus porque tiene "
                            + busSeleccionado.getCantidadAsientoBus() + " asiento(s) asociado(s).");
                    return;
                }

                String mensaje = "¿Está seguro de que desea eliminar el bus con ID: "
                        + busSeleccionado.getIdBus() + "?";

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Eliminación");
                alert.setHeaderText(null);
                alert.setContentText(mensaje);
                alert.initOwner(miEscenario);

                if (alert.showAndWait().get() == ButtonType.OK) {
                    int posi = miTabla.getSelectionModel().getSelectedIndex();
                    if (BusControladorEliminar.borrar(posi)) {
                        int cant = BusControladorListar.obtenerCantidadBus();
                        titulo.setText("ADMINISTRAR BUSES (" + cant + ")");

                        datosTabla.setAll(BusControladorListar.obtenerBuses());
                        miTabla.refresh();

                        Mensaje.mostrar(Alert.AlertType.INFORMATION,
                                miEscenario, "Éxito", "El bus ha sido eliminado correctamente.");
                    } else {
                        Mensaje.mostrar(Alert.AlertType.ERROR,
                                miEscenario, "Error", "No se pudo eliminar el bus.");
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
