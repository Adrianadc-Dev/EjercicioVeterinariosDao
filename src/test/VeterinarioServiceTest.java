package test;

import dao.impl.DaoH2Veterinario;
import model.Veterinario;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.VeterinarioService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VeterinarioServiceTest {
    static final Logger logger = Logger.getLogger(VeterinarioServiceTest.class);
    VeterinarioService veterinarioService = new VeterinarioService(new DaoH2Veterinario());
    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try{
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./veterinaria;INIT=RUNSCRIPT FROM 'create.sql'", "sa","sa");
        }catch (Exception e){
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

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
        List<Veterinario> veterinarios = new ArrayList<>();
        //cuando
        veterinarios = veterinarioService.buscarTodos();
        //enconces
        assertTrue(veterinarios.size()!=0);
    }


}