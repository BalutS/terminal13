package org.unimag.vista.viaje;

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
import org.unimag.controlador.bus.BusControladorListar;
import org.unimag.controlador.conductor.ConductorControladorListar;
import org.unimag.controlador.ruta.RutaControladorListar;
import org.unimag.controlador.viaje.ViajeControladorGrabar;
import org.unimag.dto.BusDto;
import org.unimag.dto.ConductorDto;
import org.unimag.dto.RutaDto;
import org.unimag.dto.ViajeDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Formulario;
import org.unimag.recurso.utilidad.GestorImagen;
import org.unimag.recurso.utilidad.Icono;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaViajeCrear extends StackPane {

    private static final int H_GAP = 10;
    private static final int V_GAP = 20;
    private static final int ALTO_FILA = 40;
    private static final int ALTO_CAJA = 35;
    private static final int TAMANIO_FUENTE = 20;
    private static final double ANCHO = 0.8;

    private static final double AJUSTE_TITULO = 0.1;

    private final GridPane miGrilla;
    private final Rectangle miMarco;

    private TextField txtFechaViaje;
    private TextField txtHoraViaje;
    private ComboBox<RutaDto> cbmRutas;
    private ComboBox<ConductorDto> cbmConductores;
    private ComboBox<BusDto> cbmBuses;

    // Propiedades para el manejo de la imagen
    private TextField cajaImagen;
    private ImageView imgPorDefecto;
    private ImageView imgPrevisualizar;
    private String rutaImagenSeleccionada;

    // *************************************************************************
    public VistaViajeCrear(Stage esce, double ancho, double alto) {
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
        Text miTitulo = new Text("FORMULARIO - CREAR VIAJE");
        miTitulo.setFill(Color.web(Configuracion.MORADO_OSCURO));
        miTitulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));
        GridPane.setHalignment(miTitulo, HPos.CENTER);
        GridPane.setMargin(miTitulo, new Insets(30, 0, 0, 0));
        miGrilla.add(miTitulo, 0, 0, 3, 1);
    }

    private void crearFormulario() {
        Label lblFecha = new Label("Fecha del Viaje");
        lblFecha.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblFecha, 0, 2);

        txtFechaViaje = new TextField();
        txtFechaViaje.setPromptText("Digita la Fecha(dd/mm/aa)");
        GridPane.setHgrow(txtFechaViaje, Priority.ALWAYS);
        txtFechaViaje.setPrefHeight(ALTO_CAJA);
        miGrilla.add(txtFechaViaje, 1, 2);
        
        Label lblHora = new Label("Hora del Viaje");
        lblHora.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblHora, 0, 3);

        txtHoraViaje = new TextField();
        txtHoraViaje.setPromptText("Digita la Hora (Formato militar: 2:00=1400)");
        GridPane.setHgrow(txtHoraViaje, Priority.ALWAYS);
        txtHoraViaje.setPrefHeight(ALTO_CAJA);
        miGrilla.add(txtHoraViaje, 1, 3);

        Label lblRutas = new Label("Ruta del Viaje:");
        lblRutas.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblRutas, 0, 4);

        List<RutaDto> arrRutas = RutaControladorListar.obtenerRutas();
        //que el código inicie en 0
        RutaDto opcionInicial1 = new RutaDto(0, "Seleccione Ruta", "",0.0, "", "",0);
        arrRutas.add(0, opcionInicial1);

        cbmRutas = new ComboBox<>();
        cbmRutas.setMaxWidth(Double.MAX_VALUE);
        cbmRutas.setPrefHeight(ALTO_CAJA);

        //Listas que usa javafx para manipular la imformación
        ObservableList<RutaDto> items1 = FXCollections.observableArrayList(arrRutas);

        cbmRutas.setItems(items1);

        cbmRutas.getSelectionModel().select(0);
        miGrilla.add(cbmRutas, 1, 4);
        
        Label lblBus = new Label("Bus del Viaje:");
        lblBus.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblBus, 0, 5);

        List<BusDto> arrBuses = BusControladorListar.obtenerBuses();
        //que el código inicie en 0
        BusDto opcionInicial2 = new BusDto(0, "", null,"", "",0,0);
        arrBuses.add(0, opcionInicial2);

        cbmBuses = new ComboBox<>();
        cbmBuses.setMaxWidth(Double.MAX_VALUE);
        cbmBuses.setPrefHeight(ALTO_CAJA);

        //Listas que usa javafx para manipular la imformación
        ObservableList<BusDto> items2 = FXCollections.observableArrayList(arrBuses);

        cbmBuses.setItems(items2);

        cbmBuses.getSelectionModel().select(0);
        miGrilla.add(cbmBuses, 1, 5);
        
        Label lblConductor = new Label("Conductor del Viaje:");
        lblConductor.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblConductor, 0, 6);

        List<ConductorDto> arrConductor = ConductorControladorListar.obtenerConductores();
        //que el código inicie en 0
        ConductorDto opcionInicial3 = new ConductorDto(0, "Seleccione Empresa del Bus", (short) 0, false, "", "",0);
        arrConductor.add(0, opcionInicial3);

        cbmConductores = new ComboBox<>();
        cbmConductores.setMaxWidth(Double.MAX_VALUE);
        cbmConductores.setPrefHeight(ALTO_CAJA);

        //Listas que usa javafx para manipular la imformación
        ObservableList<ConductorDto> items3 = FXCollections.observableArrayList(arrConductor);

        cbmConductores.setItems(items3);

        cbmConductores.getSelectionModel().select(0);
        miGrilla.add(cbmConductores, 1, 6);

        // La sección para la imagen
        // *********************************************************************
        Label lblImagen = new Label("Imagen del Viaje");
        lblImagen.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblImagen, 0, 7);

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
        miGrilla.add(panelHorizontal, 1, 7);

        imgPorDefecto = Icono.obtenerIcono("imgNoDisponible.png", 150);
        GridPane.setHalignment(imgPorDefecto, HPos.CENTER);
        GridPane.setValignment(imgPorDefecto, VPos.CENTER);
        miGrilla.add(imgPorDefecto, 2, 1, 1, 5);
        // *********************************************************************

        Button btnGrabar = new Button("Guardar Viaje");
        btnGrabar.setTextFill(Color.web(Configuracion.MORADO_OSCURO));
        btnGrabar.setMaxWidth(Double.MAX_VALUE);
        btnGrabar.setFont(Font.font("Times New Roman", TAMANIO_FUENTE));
        btnGrabar.setOnAction((e) -> {
            guardarViaje();
        });
        miGrilla.add(btnGrabar, 1, 8);
    }

    private void limpiarFormulario() {
        txtFechaViaje.setText("");
        txtHoraViaje.setText("");
        cbmBuses.getSelectionModel().select(0);
        cbmConductores.getSelectionModel().select(0);
        cbmRutas.getSelectionModel().select(0);
        txtFechaViaje.requestFocus();
        
        rutaImagenSeleccionada = "";
        cajaImagen.setText("");
        miGrilla.getChildren().remove(imgPrevisualizar);
        GridPane.setHalignment(imgPorDefecto, HPos.CENTER);
        miGrilla.add(imgPorDefecto, 2, 1, 1, 5);
    }

    private Boolean formularioCompleto() {
        if (txtFechaViaje.getText().isBlank()) {
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(), "Alerta", "Agrega un fecha");
            txtFechaViaje.requestFocus();
            return false;
        }
        
        if (txtHoraViaje.getText().isBlank()) {
    Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(),
            "Validación", "Por favor ingrese una hora.");
    txtHoraViaje.requestFocus();
    return false;
    }
    try {
        Integer.parseInt(txtHoraViaje.getText());
    } catch (NumberFormatException e) {
        Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(),
                "Validación", "La hora debe ser un número entero.");
        txtHoraViaje.requestFocus();
        return false;
    }
    
    if (cbmBuses.getSelectionModel().getSelectedIndex() == 0) {
        Mensaje.mostrar(Alert.AlertType.WARNING, null, "Alerta", "Escoge un Bus");
        cbmBuses.requestFocus();
        return false;
    }
    
    if (cbmConductores.getSelectionModel().getSelectedIndex() == 0) {
        Mensaje.mostrar(Alert.AlertType.WARNING, null, "Alerta", "Escoge un Conductor");
        cbmConductores.requestFocus();
        return false;
    }
    
    if (cbmRutas.getSelectionModel().getSelectedIndex() == 0) {
        Mensaje.mostrar(Alert.AlertType.WARNING, null, "Alerta", "Escoge una Ruta");
        cbmRutas.requestFocus();
        return false;
    }
    
    if (rutaImagenSeleccionada.isBlank()) {
        Mensaje.mostrar(Alert.AlertType.WARNING, null, "Alerta", "Ajá, y la imagen?");
        return false;
    }

    return true;
    }
    
    private BusDto obtenerBus() {
        BusDto seleccionado = cbmBuses.getSelectionModel().getSelectedItem();

        if (seleccionado != null && seleccionado.getIdBus()!= 0) {

            return seleccionado;
        }

        return null;
    }
    
    private RutaDto obtenerRuta() {
        RutaDto seleccionado = cbmRutas.getSelectionModel().getSelectedItem();

        if (seleccionado != null && seleccionado.getIdRuta()!= 0) {

            return seleccionado;
        }

        return null;
    }
    
    private ConductorDto obtenerConductor() {
        ConductorDto seleccionado = cbmConductores.getSelectionModel().getSelectedItem();

        if (seleccionado != null && seleccionado.getIdConductor()!= 0) {

            return seleccionado;
        }

        return null;
    }

    private void guardarViaje() {

        if (formularioCompleto()) {
            ViajeDto dto = new ViajeDto();
            dto.setFechaViaje(txtFechaViaje.getText());
            dto.setHoraViaje(Integer.parseInt(txtHoraViaje.getText()));
            dto.setBusViaje(obtenerBus());
            dto.setConductorViaje(obtenerConductor());
            dto.setRutaViaje(obtenerRuta());
            dto.setEstadoViaje(Boolean.FALSE);
            dto.setNombreImagenPublicoViaje(cajaImagen.getText());

            if (ViajeControladorGrabar.crearViaje(dto, rutaImagenSeleccionada)) {
                Mensaje.mostrar(Alert.AlertType.INFORMATION, null, "Exito", "La información ha sido guardada exitosamente");
                txtFechaViaje.requestFocus();
                limpiarFormulario();
            } else {
                Mensaje.mostrar(Alert.AlertType.ERROR, null, "Error", "La información no ha podido ser guardada");
                txtFechaViaje.requestFocus();
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
