package service;

import dao.Idao;
import model.Veterinario;

import java.util.List;

public class VeterinarioService {
    private Idao<Veterinario> veterinarioIdao;

    public VeterinarioService(Idao<Veterinario> veterinarioIdao) {
        this.veterinarioIdao = veterinarioIdao;
    }

    public Veterinario guardarVeterinario (Veterinario veterinario){
        return veterinarioIdao.guardar(veterinario);
    }

    public List<Veterinario> buscarTodos(){
        return veterinarioIdao.listarTodos();
    }
}
