package org.unimag.vista.empresa;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene; 
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.unimag.controlador.empresa.EmpresaControladorEliminar;
import org.unimag.controlador.empresa.EmpresaControladorListar;
import org.unimag.controlador.empresa.EmpresaVistasControlador; 
import org.unimag.dto.EmpresaDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Icono;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaEmpresaAdministrar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<EmpresaDto> miTabla;
    private final Button btnEliminar;
    private final Button btnActualizar;
    private final Button btnCancelar;
    private Text titulo;
    private final ObservableList<EmpresaDto> datosTabla = FXCollections.observableArrayList();

    private static final String ESTILO_CENTRAR = "-fx-alignment: CENTER;";
    private static final String ESTILO_IZQUIERDA = "-fx-alignment: CENTER-LEFT;";

    public VistaEmpresaAdministrar(Stage ventanaPadre, double ancho, double alto) {
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

        int cant = EmpresaControladorListar.obtenerCantidadEmpresa();
        titulo = new Text("ADMINISTRAR EMPRESAS (" + cant + ")");
        titulo.setFill(Color.web(Configuracion.MORADO_OSCURO));
        titulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));

        cajaVertical.getChildren().addAll(bloqueSeparador, titulo);
    }

    private TableColumn<EmpresaDto, Integer> crearColumnaId() {
        TableColumn<EmpresaDto, Integer> columna = new TableColumn<>("ID");
        columna.setCellValueFactory(new PropertyValueFactory<>("idEmpresa"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.2));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<EmpresaDto, String> crearColumnaNombre() {
        TableColumn<EmpresaDto, String> columna = new TableColumn<>("Nombre");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreEmpresa"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.4));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<EmpresaDto, String> crearColumnaImagen() {
        TableColumn<EmpresaDto, String> columna = new TableColumn<>("Imagen");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreImagenPrivadoEmpresa"));
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
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.4));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private void configurarColumnas() {
        miTabla.getColumns().addAll(
                List.of(
                        crearColumnaId(),
                        crearColumnaNombre(),
                        crearColumnaImagen()
                ));
    }

    private void crearTabla() {
        configurarColumnas();

        List<EmpresaDto> arrEmpresa = EmpresaControladorListar.obtenerEmpresas();
        datosTabla.setAll(arrEmpresa);

        miTabla.setItems(datosTabla);
        miTabla.setPlaceholder(new Text("No hay empresas registradas"));

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
            if (miTabla.getSelectionModel().getSelectedItem() == null) {
                Mensaje.mostrar(Alert.AlertType.WARNING,
                        miEscenario, "Atención", "Debe seleccionar una empresa para editar.");
            } else {
                EmpresaDto empresaSeleccionada = miTabla.getSelectionModel().getSelectedItem();
                int posicionSeleccionada = miTabla.getSelectionModel().getSelectedIndex();

                Stage ventanaEditar = new Stage();
                ventanaEditar.setTitle("Editar Empresa");
                ventanaEditar.initOwner(miEscenario);
                ventanaEditar.initModality(Modality.WINDOW_MODAL); 

                StackPane panelEdicion = EmpresaVistasControlador.editarEmpresa(
                        ventanaEditar,
                        800, 
                        600, 
                        empresaSeleccionada,
                        posicionSeleccionada
                );

                Scene escena = new Scene(panelEdicion, 800, 600);
                ventanaEditar.setScene(escena);
                ventanaEditar.showAndWait();

                int cant = EmpresaControladorListar.obtenerCantidadEmpresa();
                titulo.setText("ADMINISTRAR EMPRESAS (" + cant + ")");
                datosTabla.setAll(EmpresaControladorListar.obtenerEmpresas());
                miTabla.refresh();
                miTabla.getSelectionModel().clearSelection();
            }
        });

        btnEliminar.setOnAction(e -> {
            if (miTabla.getSelectionModel().getSelectedItem() == null) {
                Mensaje.mostrar(Alert.AlertType.WARNING,
                        miEscenario, "Atención", "Debe seleccionar una empresa.");
            } else {
                EmpresaDto empresaSeleccionada = miTabla.getSelectionModel().getSelectedItem();

                if (empresaSeleccionada.getCantidadBusEmpresa() > 0) {
                    Mensaje.mostrar(Alert.AlertType.WARNING,
                            miEscenario, "Advertencia", "No se puede eliminar la empresa porque tiene buses asociados.");
                    return;
                }

                String mensaje = "¿Está seguro de que desea eliminar la empresa con ID: "
                        + empresaSeleccionada.getIdEmpresa() + "?";

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Eliminación");
                alert.setHeaderText(null);
                alert.setContentText(mensaje);
                alert.initOwner(miEscenario);

                if (alert.showAndWait().get() == ButtonType.OK) {
                    int posi = miTabla.getSelectionModel().getSelectedIndex();
                    if (EmpresaControladorEliminar.borrar(posi)) {
                        int cant = EmpresaControladorListar.obtenerCantidadEmpresa();
                        titulo.setText("ADMINISTRAR EMPRESAS (" + cant + ")");

                        datosTabla.setAll(EmpresaControladorListar.obtenerEmpresas());
                        miTabla.refresh();

                        Mensaje.mostrar(Alert.AlertType.INFORMATION,
                                miEscenario, "Éxito", "La empresa ha sido eliminada correctamente.");
                    } else {
                        Mensaje.mostrar(Alert.AlertType.ERROR,
                                miEscenario, "Error", "No se pudo eliminar la empresa.");
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