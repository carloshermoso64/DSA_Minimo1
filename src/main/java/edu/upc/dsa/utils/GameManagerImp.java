package edu.upc.dsa.utils;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GameManagerImp implements GameManager{

    private HashMap<String, Usuario> allUsers;
    final static Logger logger = Logger.getLogger(GameManagerImp.class);

    private static GameManager instance;


    private GameManagerImp(){
        this.allUsers = new HashMap<String, Usuario>();
    }

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImp();
        return instance;
    }

    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarioList = new LinkedList<Usuario>(this.allUsers.values());
        Collections.sort(usuarioList);
        return usuarioList;
    }

    @Override
    public void addUsuario(String name, String lastname, String id) {
        Usuario u = new Usuario(name,lastname, id);
        this.allUsers.put(u.getId(),u);
        logger.info(u.getName() + " " + u.getLastname() + " registered with id: " + u.getId());
    }

    @Override
    public void updateUsuario(String name, String lastname, String mail, String id) {
        Usuario u = this.allUsers.get(id);
        this.allUsers.remove(id);
        u.setMail(mail);
        u.setName(name);
        u.setLastname(lastname);
        this.allUsers.put(id,u);
        logger.info("Usuario modified to: " + u.getName() + " " + u.getLastname() + " with id: " + u.getId() + " and mail: " + u.getMail());
    }

    @Override
    public int getNumUsuario() {
        return allUsers.size();
    }

    @Override
    public Usuario getUsuario(String iduser) {
        return allUsers.get(iduser);
    }

    @Override
    public void addObjetoUsuario(String iduser, String idobj, String descp) {
        Usuario u = this.allUsers.get(iduser);
        Objeto obj = new Objeto(idobj,descp);
        u.addObject(obj);
        this.allUsers.put(iduser,u);
        logger.info(u.getId() + " received: " + obj.getId());
    }

    @Override
    public int getNumObjetosbyUsuario(String iduser) {
        Usuario u = this.allUsers.get(iduser);
        return u.getObjects().size();
    }

    @Override
    public void clear() {
        instance = null;
        this.allUsers.clear();
        logger.info("Closing..........");
    }
}
