package edu.upc.dsa.utils;

import edu.upc.dsa.models.Usuario;

import java.util.List;

public interface GameManager {
    public List<Usuario> getUsuarios();
    public void addUsuario(String name, String lastname, String id);
    public void updateUsuario(String name, String lastname, String mail, String id);
    public int getNumUsuario();
    public Usuario getUsuario(String iduser);
    public void addObjetoUsuario(String iduser, String idobj, String desc);
    public int getNumObjetosbyUsuario(String iduser);
    public void clear();
}
