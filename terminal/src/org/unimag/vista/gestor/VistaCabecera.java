
package org.unimag.vista.gestor;

import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.SubScene;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.scene.layout.Region;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.MenuButton;
import org.unimag.controlador.bus.BusVistasControlador;
import org.unimag.controlador.conductor.ConductorVistasControlador;
import org.unimag.controlador.empresa.EmpresaVistasControlador;
import org.unimag.controlador.equipaje.EquipajeVistasControlador;
import org.unimag.controlador.pasajero.PasajeroVistasControlador;
import org.unimag.controlador.ruta.RutaVistasControlador;
import org.unimag.controlador.tiquete.TiqueteVistasControlador;
import org.unimag.controlador.viaje.ViajeVistasControlador;
import org.unimag.controlador.asiento.AsientoVistasControlador;
import org.unimag.recurso.utilidad.Icono;
import org.unimag.controlador.varios.ControladorSalida;
import org.unimag.recurso.constante.Configuracion;

public class VistaCabecera extends SubScene {

    private final int menuAncho = 160;
    private final int menuAlto = 35;
    private final int espacioX = 15;

    private Pane miPanelCuerpo;
    private final Stage miEscenario;
    private final HBox miPanelCabecera;
    private final BorderPane miPanelPrincipal;

    public VistaCabecera(
            Stage esce,
            BorderPane prin,
            Pane pane,
            double anchoPanel,
            double altoCabecera) {
        super(new HBox(), anchoPanel, altoCabecera);
        miPanelCabecera = (HBox) this.getRoot();
        miPanelCabecera.setAlignment(Pos.CENTER_LEFT);

        miPanelPrincipal = prin;
        miPanelCuerpo = pane;
        miEscenario = esce;

        miPanelCabecera.setSpacing(espacioX);
        miPanelCabecera.setPadding(new Insets(0, 30, 0, 30));
        miPanelCabecera.setStyle(Configuracion.CABECERA_ESTILO_FONDO);//se empezo a utilizar la configuracion

        crearOpciones();
    }

    public HBox getMiPanelCabecera() {
        return miPanelCabecera;
    }

    private void crearOpciones() {
        menuEmpresas();
        menuBuses();
        menuAsientos();
        menuRutas();
        menuConductores();
        menuPasajeros();
        menuEquipajes();
        menuViajes();
        menuTiquetes();
        btnSalir();
        btnAcerca(400, 450);
    }

    private void agregarMenu(MenuButton menu) {
        menu.setCursor(Cursor.HAND);
        menu.setPrefWidth(menuAncho);
        miPanelCabecera.getChildren().add(menu);
    }

    private void menuAsientos() {
        MenuButton menuButton = new MenuButton("Asientos");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoAsiento.png", 25));
        MenuItem opcion1 = new MenuItem("Crear Asientos");
        MenuItem opcion2 = new MenuItem("Listar Asientos");
        MenuItem opcion3 = new MenuItem("Administrar Asientos");
        MenuItem opcion4 = new MenuItem("Carrusel Asientos");
        opcion1.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    AsientoVistasControlador.crearAsiento(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion2.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    AsientoVistasControlador.listarAsiento(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion3.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    AsientoVistasControlador.administrarAsiento(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion4.setOnAction((e) -> {
            System.out.println("Abrir carrusel Buses");
        });
        menuButton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarMenu(menuButton);
    }

    private void menuBuses() {
        MenuButton menuButton = new MenuButton("Buses");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoBus.png", 25));
        MenuItem opcion1 = new MenuItem("Crear Buses");
        MenuItem opcion2 = new MenuItem("Listar Buses");
        MenuItem opcion3 = new MenuItem("Administrar Buses");
        MenuItem opcion4 = new MenuItem("Carrusel Buses");
        opcion1.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    BusVistasControlador.CrearBus(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion2.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    BusVistasControlador.listarBus(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion3.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    BusVistasControlador.administrarBus(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion4.setOnAction((e) -> {
            System.out.println("Abrir carrusel Buses");
        });
        menuButton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarMenu(menuButton);
    }

    private void menuConductores() {
        MenuButton menuButton = new MenuButton("Conductores");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoConductor.png", 25));
        MenuItem opcion1 = new MenuItem("Crear Conductores");
        MenuItem opcion2 = new MenuItem("Listar Conductores");
        MenuItem opcion3 = new MenuItem("Administrar Conductores");
        MenuItem opcion4 = new MenuItem("Carrusel Conductores");
        opcion1.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    ConductorVistasControlador.CrearConductor(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion2.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    ConductorVistasControlador.listarConductor(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion3.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    ConductorVistasControlador.administrarConductor(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion4.setOnAction((e) -> {
            System.out.println("Abrir carrusel Buses");
        });
        menuButton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarMenu(menuButton);
    }

    private void menuEmpresas() {
        MenuButton menuButton = new MenuButton("Empresas");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoEmpresa.png", 25));
        MenuItem opcion1 = new MenuItem("Crear Empresas");
        MenuItem opcion2 = new MenuItem("Listar Empresas");
        MenuItem opcion3 = new MenuItem("Administrar Empresas");
        MenuItem opcion4 = new MenuItem("Carrusel Empresas");
        opcion1.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    EmpresaVistasControlador.CrearEmpresa(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion2.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    EmpresaVistasControlador.listarEmpresa(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion3.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    EmpresaVistasControlador.administrarEmpresa(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion4.setOnAction((e) -> {
            System.out.println("Abrir carrusel empresa");
        });
        menuButton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarMenu(menuButton);
        
    }

    private void menuEquipajes() {
        MenuButton menuButton = new MenuButton("Equipajes");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoEquipaje.png", 25));
        MenuItem opcion1 = new MenuItem("Crear Equipajes");
        MenuItem opcion2 = new MenuItem("Listar Equipajes");
        MenuItem opcion3 = new MenuItem("Administrar Equipajes");
        MenuItem opcion4 = new MenuItem("Carrusel Equipajes");
        opcion1.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    EquipajeVistasControlador.crearEquipaje(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion2.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    EquipajeVistasControlador.listarEquipaje(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion3.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    EquipajeVistasControlador.administrarEquipaje(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion4.setOnAction((e) -> {
            System.out.println("Abrir carrusel empresa");
        });
        menuButton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarMenu(menuButton);
        
    }

    private void menuPasajeros() {
        MenuButton menuButton = new MenuButton("Pasajeros");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoPasajero.png", 25));
        MenuItem opcion1 = new MenuItem("Crear Pasajeros");
        MenuItem opcion2 = new MenuItem("Listar Pasajeros");
        MenuItem opcion3 = new MenuItem("Administrar Pasajeros");
        MenuItem opcion4 = new MenuItem("Carrusel Pasajeros");
        opcion1.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    PasajeroVistasControlador.CrearPasajero(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion2.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    PasajeroVistasControlador.listarPasajero(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion3.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    PasajeroVistasControlador.administrarPasajero(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion4.setOnAction((e) -> {
            System.out.println("Abrir carrusel Buses");
        });
        menuButton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarMenu(menuButton);
    }

    private void menuRutas() {
        MenuButton menuButton = new MenuButton("Rutas");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoRuta.png", 25));
        MenuItem opcion1 = new MenuItem("Crear Rutas");
        MenuItem opcion2 = new MenuItem("Listar Rutas");
        MenuItem opcion3 = new MenuItem("Administrar Rutas");
        MenuItem opcion4 = new MenuItem("Carrusel Rutas");
        opcion1.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    RutaVistasControlador.CrearRuta(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion2.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    RutaVistasControlador.listarRuta(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion3.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    RutaVistasControlador.administrarRuta(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion4.setOnAction((e) -> {
            System.out.println("Abrir carrusel Buses");
        });
        menuButton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarMenu(menuButton);
    }

    private void menuTiquetes() {
        MenuButton menuButton = new MenuButton("Tiquetes");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoTiquete.png", 25));
        MenuItem opcion1 = new MenuItem("Crear Tiquetes");
        MenuItem opcion2 = new MenuItem("Listar Tiquetes");
        MenuItem opcion3 = new MenuItem("Administrar Tiquetes");
        MenuItem opcion4 = new MenuItem("Carrusel Tiquetes");
        opcion1.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    TiqueteVistasControlador.crearTiquete(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion2.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    TiqueteVistasControlador.listarTiquete(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion3.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    TiqueteVistasControlador.administrarTiquete(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion4.setOnAction((e) -> {
            System.out.println("Abrir carrusel Buses");
        });
        menuButton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarMenu(menuButton);
    }

    private void menuViajes() {
        MenuButton menuButton = new MenuButton("Viajes");
        menuButton.setPrefWidth(menuAncho);
        menuButton.setPrefHeight(menuAlto);
        menuButton.setGraphic(Icono.obtenerIcono("IconoViaje.png", 25));
        MenuItem opcion1 = new MenuItem("Crear Viajes");
        MenuItem opcion2 = new MenuItem("Listar Viajes");
        MenuItem opcion3 = new MenuItem("Administrar Viajes");
        MenuItem opcion4 = new MenuItem("Carrusel Viajes");
        opcion1.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    ViajeVistasControlador.crearViaje(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion2.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    ViajeVistasControlador.listarViaje(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion3.setOnAction((e) -> {
            miPanelPrincipal.setCenter(
                    ViajeVistasControlador.administrarViaje(miEscenario,
                            Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA)
            );
        });

        opcion4.setOnAction((e) -> {
            System.out.println("Abrir carrusel Buses");
        });
        menuButton.getItems().addAll(opcion1, opcion2, opcion3, opcion4);
        agregarMenu(menuButton);
    }

    private void btnSalir() {
        Button btnSalir = new Button("Salir");
        btnSalir.setCursor(Cursor.HAND);
        btnSalir.setPrefWidth(menuAncho);
        btnSalir.setPrefHeight(menuAlto);
        btnSalir.setOnAction((ActionEvent event) -> {
            event.consume();
            ControladorSalida.verificar(miEscenario);
        });
        miPanelCabecera.getChildren().add(btnSalir);
    }

    private void btnAcerca(double anchoFlotante, double altoFlotante) {
        Button botonAyuda = new Button("?");
        botonAyuda.setOnAction((ActionEvent event) -> {
            VistaAcerca.mostrar(miEscenario, anchoFlotante, altoFlotante);
        });
        botonAyuda.setPrefWidth(30);
        botonAyuda.setId("btn-ayuda");
        botonAyuda.setCursor(Cursor.HAND);
        botonAyuda.getStylesheets().add(getClass().getResource("/org/unimag/recurso/estilo/BtnAcerca.css").toExternalForm());
        Region espacio = new Region();
        HBox.setHgrow(espacio, Priority.ALWAYS);
        miPanelCabecera.getChildren().add(espacio);
        miPanelCabecera.getChildren().add(botonAyuda);
    }
}
