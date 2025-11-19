package org.unimag.vista.equipaje;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.unimag.controlador.equipaje.EquipajeControladorGrabar;
import org.unimag.controlador.pasajero.PasajeroControladorListar;
import org.unimag.dto.EquipajeDto;
import org.unimag.dto.PasajeroDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Formulario;
import org.unimag.recurso.utilidad.GestorImagen;
import org.unimag.recurso.utilidad.Icono;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaEquipajeCrear extends StackPane {

    private static final int H_GAP = 10;
    private static final int V_GAP = 20;
    private static final int ALTO_FILA = 40;
    private static final int ALTO_CAJA = 35;
    private static final int TAMANIO_FUENTE = 20;
    private static final double ANCHO = 0.8;

    private static final double AJUSTE_TITULO = 0.1;

    private final GridPane miGrilla;
    private final Rectangle miMarco;

    private TextField txtPesoEquipaje;    
    private ComboBox<PasajeroDto> cbmPasajero;

    // Propiedades para el manejo de la imagen
    private TextField cajaImagen;
    private ImageView imgPorDefecto;
    private ImageView imgPrevisualizar;
    private String rutaImagenSeleccionada;

    // *************************************************************************
    public VistaEquipajeCrear(Stage esce, double ancho, double alto) {
        rutaImagenSeleccionada = "";
        setAlignment(Pos.CENTER);

        miGrilla = new GridPane();

        miMarco = Marco.crear(esce, Configuracion.MARCO_ALTO_PORCENTAJE,
                Configuracion.MARCO_ANCHO_PORCENTAJE,
                Configuracion.DEGRADE_ARREGLO_GENERO,
                Configuracion.DEGRADE_BORDE);

        getChildren().add(miMarco);

        configurarMiGrilla(ancho, alto);
        crearTitulo();
        crearFormulario();
        colocarFrmElegante();
        getChildren().add(miGrilla);
    }

    private void configurarMiGrilla(double ancho, double alto) {
        double miAnchoGrilla = ancho * Configuracion.GRILLA_ANCHO_PORCENTAJE;
        miGrilla.setHgap(H_GAP);
        miGrilla.setVgap(V_GAP);
        miGrilla.setPrefSize(miAnchoGrilla, alto);
        miGrilla.setMinSize(miAnchoGrilla, alto);

        miGrilla.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        ColumnConstraints col0 = new ColumnConstraints();
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();  // <- Nueva !!!
        col0.setPrefWidth(200);
        col1.setPrefWidth(200);
        col2.setPrefWidth(200); // <- Nueva !!!

        col1.setHgrow(Priority.ALWAYS);
        miGrilla.getColumnConstraints().addAll(col0, col1, col2); // <- Nueva !!!

        for (int i = 0; i < 7; i++) {
            RowConstraints fila = new RowConstraints();
            fila.setMinHeight(ALTO_FILA);
            fila.setMaxHeight(ALTO_FILA);
            miGrilla.getRowConstraints().add(fila);
        }
    }

    private void crearTitulo() {
        Text miTitulo = new Text("FORMULARIO - CREAR EQUIPAJE");
        miTitulo.setFill(Color.web(Configuracion.MORADO_OSCURO));
        miTitulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));
        GridPane.setHalignment(miTitulo, HPos.CENTER);
        GridPane.setMargin(miTitulo, new Insets(30, 0, 0, 0));
        miGrilla.add(miTitulo, 0, 0, 3, 1);
    }

    private void crearFormulario() {
        Label lblPeso = new Label("Peso del Equipaje");
        lblPeso.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblPeso, 0, 2);

        txtPesoEquipaje = new TextField();
        txtPesoEquipaje.setPromptText("Digita el Peso");
        GridPane.setHgrow(txtPesoEquipaje, Priority.ALWAYS);
        txtPesoEquipaje.setPrefHeight(ALTO_CAJA);
        miGrilla.add(txtPesoEquipaje, 1, 2);

        Label lblDuenio = new Label("Propietario");
        lblDuenio.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblDuenio, 0, 3);

        List<PasajeroDto> arrPasajeros = PasajeroControladorListar.obtenerPasajeros();
        //que el código inicie en 0
        PasajeroDto opcionInicial = new PasajeroDto(0, "", null, false,"", "",0,0);
        arrPasajeros.add(0, opcionInicial);

        cbmPasajero = new ComboBox<>();
        cbmPasajero.setMaxWidth(Double.MAX_VALUE);
        cbmPasajero.setPrefHeight(ALTO_CAJA);

        //Listas que usa javafx para manipular la imformación
        ObservableList<PasajeroDto> items = FXCollections.observableArrayList(arrPasajeros);

        cbmPasajero.setItems(items);

        cbmPasajero.getSelectionModel().select(0);
        miGrilla.add(cbmPasajero, 1, 3);

        // La sección para la imagen
        // *********************************************************************
        Label lblImagen = new Label("Imagen del Equipaje");
        lblImagen.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblImagen, 0, 4);

        cajaImagen = new TextField();
        cajaImagen.setDisable(true);
        cajaImagen.setPrefHeight(ALTO_CAJA);
        String[] extensiones = {"*.png", "*.jpg", "*.jpeg"};
        FileChooser objSeleccionar = Formulario.selectorImagen(
                "Selecciona la foto", "Imagenes", extensiones);
        Button btnSeleccionarImagen = new Button("+");
        btnSeleccionarImagen.setPrefHeight(ALTO_CAJA);
        btnSeleccionarImagen.setOnAction((e) -> {
            rutaImagenSeleccionada = GestorImagen.obtenerRutaImagen(
                    cajaImagen, objSeleccionar);
            if (rutaImagenSeleccionada.isEmpty()) {
                // Solo es para crear NO para actualizar !!!!!!!
                // OJOOOOOOOOOOOOOOOOOOO, por el amor de Dios
                miGrilla.getChildren().remove(imgPorDefecto);
                miGrilla.getChildren().remove(imgPrevisualizar);
                miGrilla.add(imgPorDefecto, 2, 1, 1, 5);
            }else{
                miGrilla.getChildren().remove(imgPorDefecto);
                miGrilla.getChildren().remove(imgPrevisualizar);
                imgPrevisualizar = Icono.previsualizar(
                        rutaImagenSeleccionada, 150);
                GridPane.setHalignment(imgPrevisualizar, HPos.CENTER);
                miGrilla.add(imgPrevisualizar, 2, 1, 1, 5);
            }
        });

        HBox.setHgrow(cajaImagen, Priority.ALWAYS);
        HBox panelHorizontal = new HBox(2);
        panelHorizontal.setAlignment(Pos.BOTTOM_RIGHT);
        panelHorizontal.getChildren().addAll(cajaImagen, btnSeleccionarImagen);
        miGrilla.add(panelHorizontal, 1, 4);

        imgPorDefecto = Icono.obtenerIcono("imgNoDisponible.png", 150);
        GridPane.setHalignment(imgPorDefecto, HPos.CENTER);
        GridPane.setValignment(imgPorDefecto, VPos.CENTER);
        miGrilla.add(imgPorDefecto, 2, 1, 1, 5);
        // *********************************************************************

        Button btnGrabar = new Button("Guardar Equipaje");
        btnGrabar.setTextFill(Color.web(Configuracion.MORADO_OSCURO));
        btnGrabar.setMaxWidth(Double.MAX_VALUE);
        btnGrabar.setFont(Font.font("Times New Roman", TAMANIO_FUENTE));
        btnGrabar.setOnAction((e) -> {
            guardarEquipaje();
        });
        miGrilla.add(btnGrabar, 1, 5);
    }

    private void limpiarFormulario() {
        txtPesoEquipaje.setText("");
        cbmPasajero.getSelectionModel().select(0);
        txtPesoEquipaje.requestFocus();
        
        rutaImagenSeleccionada = "";
        cajaImagen.setText("");
        miGrilla.getChildren().remove(imgPrevisualizar);
        GridPane.setHalignment(imgPorDefecto, HPos.CENTER);
        miGrilla.add(imgPorDefecto, 2, 1, 1, 5);
    }

    private Boolean formularioCompleto() {
        if (txtPesoEquipaje.getText().isBlank()) {
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(), "Alerta", "Agrega un Peso");
            txtPesoEquipaje.requestFocus();
            return false;
        }

        if (cbmPasajero.getSelectionModel().getSelectedIndex() == 0) {
            Mensaje.mostrar(Alert.AlertType.WARNING, null, "Alerta", "Escoge un Pasajero");
            cbmPasajero.requestFocus();
            return false;
        }
        
        try {
            Double.parseDouble(txtPesoEquipaje.getText());
        } catch (NumberFormatException e) {
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(),
                    "Validación", "El peso del equipaje debe ser un número.");
            txtPesoEquipaje.requestFocus();
            return false;
        }
        
        if (rutaImagenSeleccionada.isBlank()) {
            Mensaje.mostrar(Alert.AlertType.WARNING, null, "Alerta", "Ajá, y la imagen?");
            return false;
        }

        return true;
    }
    
    private PasajeroDto obtenerPasajero() {
        PasajeroDto seleccionado = cbmPasajero.getSelectionModel().getSelectedItem();

        if (seleccionado != null && seleccionado.getIdPasajero()!= 0) {

            return seleccionado;
        }

        return null;
    }

    private void guardarEquipaje() {

        if (formularioCompleto()) {
            EquipajeDto dto = new EquipajeDto();
            dto.setPesoEquipaje(Double.parseDouble(txtPesoEquipaje.getText()));
            dto.setDuenioEquipaje(obtenerPasajero());
            dto.setNombreImagenPublicoEquipaje(cajaImagen.getText());

            if (EquipajeControladorGrabar.crearEquipaje(dto, rutaImagenSeleccionada)) {
                Mensaje.mostrar(Alert.AlertType.INFORMATION, null, "Exito", "La información ha sido guardada exitosamente");
                txtPesoEquipaje.requestFocus();
                limpiarFormulario();
            } else {
                Mensaje.mostrar(Alert.AlertType.ERROR, null, "Error", "La información no ha podido ser guardada");
                txtPesoEquipaje.requestFocus();
            }
        }
    }

    private void colocarFrmElegante() {
        Runnable calcular = () -> {

            double alturaMarco = miMarco.getHeight();

            if (alturaMarco > 0) {

                double desplazamiento = alturaMarco * AJUSTE_TITULO;
                miGrilla.setTranslateY(-alturaMarco / 2 + desplazamiento);
            }
        };

        calcular.run();

        miMarco.heightProperty().addListener((obs, antes, despues) -> {
            calcular.run();
        });

    }
}
