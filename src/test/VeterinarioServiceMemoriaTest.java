package test;

import dao.impl.VeterinarioEnMemoria;
import model.Veterinario;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.VeterinarioService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VeterinarioServiceMemoriaTest {
    static Logger logger = Logger.getLogger(VeterinarioServiceMemoriaTest.class);
    VeterinarioService veterinarioService = new VeterinarioService(new VeterinarioEnMemoria());
    @Test
    @DisplayName("Testear que un veterinario se guarde en la base de datos")
    void caso1(){
        //dado
        Veterinario veterinario = new Veterinario(628,"alberto","scarpetta", "loros");
        //cuando
        Veterinario veterinarioDesdeDB = veterinarioService.guardarVeterinario(veterinario);
        //entonces
        assertNotNull(veterinarioDesdeDB.getId());
    }
    @Test
    @DisplayName("Testear que se listen los veterinarios")
    void caso2() {
        //dado
        Veterinario veterinario = new Veterinario(628,"alberto","scarpetta", "loros");
        Veterinario veterinario2 = new Veterinario(628,"alberto","scarpetta", "loros");
        veterinarioService.guardarVeterinario(veterinario);
        veterinarioService.guardarVeterinario(veterinario2);
        //cuando
        veterinarioService.buscarTodos();
        //enconces

    }


}