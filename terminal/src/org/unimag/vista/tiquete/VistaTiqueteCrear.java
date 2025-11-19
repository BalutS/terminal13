package org.unimag.vista.tiquete;

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
import org.unimag.controlador.pasajero.PasajeroControladorListar;
import org.unimag.controlador.tiquete.TiqueteControladorGrabar;
import org.unimag.controlador.viaje.ViajeControladorListar;
import org.unimag.controlador.asiento.AsientoControladorListar;
import org.unimag.dto.AsientoDto;
import org.unimag.dto.PasajeroDto;
import org.unimag.dto.TiqueteDto;
import org.unimag.dto.ViajeDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Formulario;
import org.unimag.recurso.utilidad.GestorImagen;
import org.unimag.recurso.utilidad.Icono;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaTiqueteCrear extends StackPane {

    private static final int H_GAP = 10;
    private static final int V_GAP = 20;
    private static final int ALTO_FILA = 40;
    private static final int ALTO_CAJA = 35;
    private static final int TAMANIO_FUENTE = 20;
    private static final double ANCHO = 0.8;

    private static final double AJUSTE_TITULO = 0.1;

    private final GridPane miGrilla;
    private final Rectangle miMarco;
    
    private ComboBox<PasajeroDto> cbmPasajeros;
    private ComboBox<AsientoDto> cbmAsientos;
    private ComboBox<ViajeDto> cbmViajes;

    // Propiedades para el manejo de la imagen
    private TextField cajaImagen;
    private ImageView imgPorDefecto;
    private ImageView imgPrevisualizar;
    private String rutaImagenSeleccionada;

    // *************************************************************************
    public VistaTiqueteCrear(Stage esce, double ancho, double alto) {
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
        Text miTitulo = new Text("FORMULARIO - CREAR TIQUETE");
        miTitulo.setFill(Color.web(Configuracion.MORADO_OSCURO));
        miTitulo.setFont(Font.font("Rockwell", FontWeight.BOLD, 28));
        GridPane.setHalignment(miTitulo, HPos.CENTER);
        GridPane.setMargin(miTitulo, new Insets(30, 0, 0, 0));
        miGrilla.add(miTitulo, 0, 0, 3, 1);
    }

    private void crearFormulario() {

        Label lblPasajeros = new Label("Pasajero del Tiquete:");
        lblPasajeros.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblPasajeros, 0, 2);

        List<PasajeroDto> arrPasajeros = PasajeroControladorListar.obtenerPasajeros();
        //que el código inicie en 0
        PasajeroDto opcionInicial1 = new PasajeroDto(0, "Seleccione el Pasajero", (short) 0, false, "", "",0,0);
        arrPasajeros.add(0, opcionInicial1);

        cbmPasajeros = new ComboBox<>();
        cbmPasajeros.setMaxWidth(Double.MAX_VALUE);
        cbmPasajeros.setPrefHeight(ALTO_CAJA);

        //Listas que usa javafx para manipular la imformación
        ObservableList<PasajeroDto> items1 = FXCollections.observableArrayList(arrPasajeros);

        cbmPasajeros.setItems(items1);

        cbmPasajeros.getSelectionModel().select(0);
        miGrilla.add(cbmPasajeros, 1, 2);
        
        Label lblViaje = new Label("Viaje del Tiquete:");
        lblViaje.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblViaje, 0, 3);

        List<ViajeDto> arrBuses = ViajeControladorListar.obtenerViajes();
        //que el código inicie en 0
        ViajeDto opcionInicial2 = new ViajeDto(0, null, null, null, "", 0, true, "", "",0);
        arrBuses.add(0, opcionInicial2);

        cbmViajes = new ComboBox<>();
        cbmViajes.setMaxWidth(Double.MAX_VALUE);
        cbmViajes.setPrefHeight(ALTO_CAJA);

        //Listas que usa javafx para manipular la imformación
        ObservableList<ViajeDto> items2 = FXCollections.observableArrayList(arrBuses);

        cbmViajes.setItems(items2);

        cbmViajes.getSelectionModel().select(0);
        miGrilla.add(cbmViajes, 1, 3);
        
        Label lblAsiento = new Label("Asiento del Tiquete:");
        lblAsiento.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblAsiento, 0, 4);

        List<AsientoDto> arrAsientos = AsientoControladorListar.obtenerAsientosDesocupados();
        //que el código inicie en 0
        AsientoDto opcionInicial3 = new AsientoDto(0, null, false, "", "",0);
        arrAsientos.add(0, opcionInicial3);

        cbmAsientos = new ComboBox<>();
        cbmAsientos.setMaxWidth(Double.MAX_VALUE);
        cbmAsientos.setPrefHeight(ALTO_CAJA);

        //Listas que usa javafx para manipular la imformación
        ObservableList<AsientoDto> items3 = FXCollections.observableArrayList(arrAsientos);

        cbmAsientos.setItems(items3);

        cbmAsientos.getSelectionModel().select(0);
        miGrilla.add(cbmAsientos, 1, 4);

        // La sección para la imagen
        // *********************************************************************
        Label lblImagen = new Label("Imagen del Tiquete");
        lblImagen.setFont(Font.font("Times New Roman", FontPosture.ITALIC, TAMANIO_FUENTE));
        miGrilla.add(lblImagen, 0, 5);

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
        miGrilla.add(panelHorizontal, 1, 5);

        imgPorDefecto = Icono.obtenerIcono("imgNoDisponible.png", 150);
        GridPane.setHalignment(imgPorDefecto, HPos.CENTER);
        GridPane.setValignment(imgPorDefecto, VPos.CENTER);
        miGrilla.add(imgPorDefecto, 2, 1, 1, 5);
        // *********************************************************************

        Button btnGrabar = new Button("Guardar Tiquete");
        btnGrabar.setTextFill(Color.web(Configuracion.MORADO_OSCURO));
        btnGrabar.setMaxWidth(Double.MAX_VALUE);
        btnGrabar.setFont(Font.font("Times New Roman", TAMANIO_FUENTE));
        btnGrabar.setOnAction((e) -> {
            guardarTiquete();
        });
        miGrilla.add(btnGrabar, 1, 6);
    }

    private void limpiarFormulario() {
        cbmAsientos.getSelectionModel().select(0);
        cbmPasajeros.getSelectionModel().select(0);
        cbmViajes.getSelectionModel().select(0);
        
        rutaImagenSeleccionada = "";
        cajaImagen.setText("");
        miGrilla.getChildren().remove(imgPrevisualizar);
        GridPane.setHalignment(imgPorDefecto, HPos.CENTER);
        miGrilla.add(imgPorDefecto, 2, 1, 1, 5);
    }

    private Boolean formularioCompleto() {
    
    if (cbmAsientos.getSelectionModel().getSelectedIndex() == 0) {
        Mensaje.mostrar(Alert.AlertType.WARNING, null, "Alerta", "Escoge un Asiento");
        cbmAsientos.requestFocus();
        return false;
    }
    
    if (cbmPasajeros.getSelectionModel().getSelectedIndex() == 0) {
        Mensaje.mostrar(Alert.AlertType.WARNING, null, "Alerta", "Escoge un(a) Pasajero(a)");
        cbmPasajeros.requestFocus();
        return false;
    }
    
    if (cbmViajes.getSelectionModel().getSelectedIndex() == 0) {
        Mensaje.mostrar(Alert.AlertType.WARNING, null, "Alerta", "Escoge un Viaje");
        cbmViajes.requestFocus();
        return false;
    }
    
    if (rutaImagenSeleccionada.isBlank()) {
        Mensaje.mostrar(Alert.AlertType.WARNING, null, "Alerta", "Ajá, y la imagen?");
        return false;
    }

    return true;
    }
    
    private AsientoDto obtenerAsiento() {
        AsientoDto seleccionado = cbmAsientos.getSelectionModel().getSelectedItem();

        if (seleccionado != null && seleccionado.getIdAsiento()!= 0) {

            return seleccionado;
        }

        return null;
    }
    
    private PasajeroDto obtenerPasajero() {
        PasajeroDto seleccionado = cbmPasajeros.getSelectionModel().getSelectedItem();

        if (seleccionado != null && seleccionado.getIdPasajero()!= 0) {

            return seleccionado;
        }

        return null;
    }
    
    private ViajeDto obtenerViaje() {
        ViajeDto seleccionado = cbmViajes.getSelectionModel().getSelectedItem();

        if (seleccionado != null && seleccionado.getIdViaje()!= 0) {

            return seleccionado;
        }

        return null;
    }

    private void guardarTiquete() {

        if (formularioCompleto()) {
            TiqueteDto dto = new TiqueteDto();
            dto.setPasajeroTiquete(obtenerPasajero());
            dto.setViajeTiquete(obtenerViaje());
            dto.setAsientoTiquete(obtenerAsiento());
            dto.setEstadoTiquete(Boolean.TRUE);
            dto.setNombreImagenPublicoTiquete(cajaImagen.getText());

            if (TiqueteControladorGrabar.crearTiquete(dto, rutaImagenSeleccionada)) {
                Mensaje.mostrar(Alert.AlertType.INFORMATION, null, "Exito", "La información ha sido guardada exitosamente");
                limpiarFormulario();
            } else {
                Mensaje.mostrar(Alert.AlertType.ERROR, null, "Error", "La información no ha podido ser guardada");
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
