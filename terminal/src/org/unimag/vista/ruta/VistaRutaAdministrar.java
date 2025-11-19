package org.unimag.vista.ruta;

import java.text.DecimalFormat;
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
import org.unimag.controlador.ruta.RutaControladorEliminar;
import org.unimag.controlador.ruta.RutaControladorListar;
import org.unimag.dto.RutaDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Icono;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaRutaAdministrar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<RutaDto> miTabla;
    private final Button btnEliminar;
    private final Button btnActualizar;
    private final Button btnCancelar;
    private Text titulo;
    private final ObservableList<RutaDto> datosTabla = FXCollections.observableArrayList();

    private static final String ESTILO_CENTRAR = "-fx-alignment: CENTER;";
    private static final String ESTILO_DERECHA = "-fx-alignment: CENTER-RIGHT;";
    private static final String ESTILO_IZQUIERDA = "-fx-alignment: CENTER-LEFT;";

    public VistaRutaAdministrar(Stage ventanaPadre, double ancho, double alto) {
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

        int cant = RutaControladorListar.obtenerCantidadRuta();
        titulo = new Text("ADMINISTRAR RUTAS (" + cant + ")");
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
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreImagenPrivadoRuta"));
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
        datosTabla.setAll(arrRuta);

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
    
    private void crearBotones() {
        btnCancelar.setOnAction(e -> {
            miTabla.getSelectionModel().clearSelection();
        });
        
        btnEliminar.setOnAction(e -> {
            if (miTabla.getSelectionModel().getSelectedItem() == null) {
                Mensaje.mostrar(Alert.AlertType.WARNING,
                        miEscenario, "Atención", "Debe seleccionar una ruta.");
            } else {
                RutaDto rutaSeleccionada = miTabla.getSelectionModel().getSelectedItem();

                if (rutaSeleccionada.getCantidadViajeRuta()> 0) {
                    Mensaje.mostrar(Alert.AlertType.WARNING,
                            miEscenario, "Advertencia", "No se puede eliminar la ruta porque tiene viajes asociados.");
                    return;
                }

                String mensaje = "¿Está seguro de que desea eliminar la ruta con ID: "
                        + rutaSeleccionada.getIdRuta() + "?";

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Eliminación");
                alert.setHeaderText(null);
                alert.setContentText(mensaje);
                alert.initOwner(miEscenario);

                if (alert.showAndWait().get() == ButtonType.OK) {
                    int posi = miTabla.getSelectionModel().getSelectedIndex();
                    if (RutaControladorEliminar.borrar(posi)) {
                        int cant = RutaControladorListar.obtenerCantidadRuta();
                        titulo.setText("ADMINISTRAR RUTAS (" + cant + ")");

                        datosTabla.setAll(RutaControladorListar.obtenerRutas());
                        miTabla.refresh();

                        Mensaje.mostrar(Alert.AlertType.INFORMATION,
                                miEscenario, "Éxito", "La ruta ha sido eliminada correctamente.");
                    } else {
                        Mensaje.mostrar(Alert.AlertType.ERROR,
                                miEscenario, "Error", "No se pudo eliminar la ruta.");
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
