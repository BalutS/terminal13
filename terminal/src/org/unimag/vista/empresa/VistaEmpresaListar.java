
package org.unimag.vista.empresa;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
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
import org.unimag.controlador.empresa.EmpresaControladorListar;
import org.unimag.dto.EmpresaDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Marco;

public class VistaEmpresaListar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<EmpresaDto> miTabla;

    private static final String ESTILO_CENTRAR = "-fx-alignment: CENTER;";
    private static final String ESTILO_IZQUIERDA = "-fx-alignment: CENTER-LEFT;";

    public VistaEmpresaListar(Stage ventanaPadre, double ancho, double alto) {
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

        int cant = EmpresaControladorListar.obtenerCantidadEmpresa();
        Text titulo = new Text("LISTA EMPRESAS (" + cant + ")");
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
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreImagenPublicoEmpresa"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.4));
        columna.setStyle(ESTILO_IZQUIERDA);
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
        ObservableList<EmpresaDto> datosTabla = FXCollections.observableArrayList(arrEmpresa);

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
}
