package org.unimag.vista.pasajero;

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
import org.unimag.controlador.pasajero.PasajeroControladorEliminar;
import org.unimag.controlador.pasajero.PasajeroControladorListar;
import org.unimag.dto.PasajeroDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Icono;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaPasajeroAdministrar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<PasajeroDto> miTabla;
    private final Button btnEliminar;
    private final Button btnActualizar;
    private final Button btnCancelar;
    private Text titulo;
    private final ObservableList<PasajeroDto> datosTabla = FXCollections.observableArrayList();

    private static final String ESTILO_CENTRAR = "-fx-alignment: CENTER;";
    private static final String ESTILO_IZQUIERDA = "-fx-alignment: CENTER-LEFT;";
    private static final String ESTILO_MAGENTA_CENTRADO = "-fx-text-fill: magenta;" + ESTILO_CENTRAR;
    private static final String ESTILO_AZUL_CENTRADO = "-fx-text-fill: blue;" + ESTILO_CENTRAR;

    public VistaPasajeroAdministrar(Stage ventanaPadre, double ancho, double alto) {
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

        int cant = PasajeroControladorListar.obtenerCantidadPasajero();
        titulo = new Text("ADMINISTRAR PASAJEROS (" + cant + ")");
        titulo.setFill(Color.web(Configuracion.MORADO_OSCURO));
        titulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));

        cajaVertical.getChildren().addAll(bloqueSeparador, titulo);
    }

    private TableColumn<PasajeroDto, Integer> crearColumnaCedula() {
        TableColumn<PasajeroDto, Integer> columna = new TableColumn<>("id");
        columna.setCellValueFactory(new PropertyValueFactory<>("idPasajero"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<PasajeroDto, String> crearColumnaNombre() {
        TableColumn<PasajeroDto, String> columna = new TableColumn<>("Nombre");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombrePasajero"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.3));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<PasajeroDto, Short> crearColumnaEdad() {
        TableColumn<PasajeroDto, Short> columna = new TableColumn<>("Edad");
        columna.setCellValueFactory(new PropertyValueFactory<>("edadPasajero"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.1));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<PasajeroDto, Boolean> crearColumnaGenero() {
        TableColumn<PasajeroDto, Boolean> columna = new TableColumn<>("Género");
        columna.setCellValueFactory(new PropertyValueFactory<>("generoPasajero"));

        columna.setCellFactory(col -> new TableCell<PasajeroDto, Boolean>() {
            @Override
            protected void updateItem(Boolean valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(valor ? "Masculino" : "Femenino");
                    setStyle(valor ? ESTILO_AZUL_CENTRADO : ESTILO_MAGENTA_CENTRADO);
                }
            }
        });

        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<PasajeroDto, String> crearColumnaImagen() {
        TableColumn<PasajeroDto, String> columna = new TableColumn<>("Imagen");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreImagenPrivadoPasajero"));
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
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private void configurarColumnas() {
        miTabla.getColumns().addAll(
                List.of(
                        crearColumnaCedula(),
                        crearColumnaNombre(),
                        crearColumnaEdad(),
                        crearColumnaGenero(),
                        crearColumnaImagen()
                ));
    }

    private void crearTabla() {
        configurarColumnas();

        List<PasajeroDto> arrPasajero = PasajeroControladorListar.obtenerPasajeros();
        datosTabla.setAll(arrPasajero);

        miTabla.setItems(datosTabla);
        miTabla.setPlaceholder(new Text("No hay pasajeros registrados"));

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
                        miEscenario, "Atención", "Debe seleccionar un pasajero.");
            } else {
                PasajeroDto pasajeroSeleccionado = miTabla.getSelectionModel().getSelectedItem();

                if (pasajeroSeleccionado.getCantidadEquipajePasajero() > 0 || pasajeroSeleccionado.getCantidadTiquetePasajero() > 0) {
                    Mensaje.mostrar(Alert.AlertType.WARNING,
                            miEscenario, "Advertencia", "No se puede eliminar el pasajero porque tiene equipajes o tiquetes asociados.");
                    return;
                }

                String mensaje = "¿Está seguro de que desea eliminar al pasajero con ID: "
                        + pasajeroSeleccionado.getIdPasajero() + "?";

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Eliminación");
                alert.setHeaderText(null);
                alert.setContentText(mensaje);
                alert.initOwner(miEscenario);

                if (alert.showAndWait().get() == ButtonType.OK) {
                    int posi = miTabla.getSelectionModel().getSelectedIndex();
                    if (PasajeroControladorEliminar.borrar(posi)) {
                        int cant = PasajeroControladorListar.obtenerCantidadPasajero();
                        titulo.setText("ADMINISTRAR PASAJEROS (" + cant + ")");

                        datosTabla.setAll(PasajeroControladorListar.obtenerPasajeros());
                        miTabla.refresh();

                        Mensaje.mostrar(Alert.AlertType.INFORMATION,
                                miEscenario, "Éxito", "El pasajero ha sido eliminado correctamente.");
                    } else {
                        Mensaje.mostrar(Alert.AlertType.ERROR,
                                miEscenario, "Error", "No se pudo eliminar el pasajero.");
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
