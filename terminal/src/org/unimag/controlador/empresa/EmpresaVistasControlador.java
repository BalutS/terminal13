package org.unimag.controlador.empresa;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.unimag.dto.EmpresaDto;
import org.unimag.vista.empresa.VistaEmpresaAdministrar;
import org.unimag.vista.empresa.VistaEmpresaCrear;
import org.unimag.vista.empresa.VistaEmpresaEditar; // Asegúrate de importar esto
import org.unimag.vista.empresa.VistaEmpresaListar;

public class EmpresaVistasControlador {

    public static StackPane CrearEmpresa(Stage esce, double anchito, double altito) {
        return new VistaEmpresaCrear(esce, anchito, altito);
    }
    
    public static StackPane listarEmpresa(Stage esce, double anchito, double altito) {
        return new VistaEmpresaListar(esce, anchito, altito);
    }

    public static StackPane administrarEmpresa(Stage esce, double anchito, double altito) {
        return new VistaEmpresaAdministrar(esce, anchito, altito);
    }
    
    public static StackPane editarEmpresa(Stage esce, double ancho, double alto, EmpresaDto empresa, int posicion) {
        return new VistaEmpresaEditar(esce, ancho, alto, empresa, posicion);
    }
}