package dao;

import java.util.List;

public interface Idao <T>{
    T guardar(T t);
    List<T> listarTodos();
}
