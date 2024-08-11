package dao.impl;

import dao.Idao;
import model.Veterinario;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class VeterinarioEnMemoria implements Idao<Veterinario> {
    public static final Logger logger = Logger.getLogger(VeterinarioEnMemoria.class);
    private List<Veterinario> veterinarios = new ArrayList<>();
    @Override
    public Veterinario guardar(Veterinario veterinario) {
        veterinario.setId(veterinarios.size()+1);
        veterinarios.add(veterinario);
        logger.info("veterinario guardado" + veterinario);
        return veterinario;
    }

    @Override
    public List<Veterinario> listarTodos() {
        logger.info("los veterinarios que hay actualmente son ");

        for (Integer i = 0; i <veterinarios.size(); i++){
           logger.info( veterinarios.get(i));
        }
        return veterinarios;

    }
}
