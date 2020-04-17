package edu.upc.dsa;

import edu.upc.dsa.utils.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
    GameManager gm;

    @Before
    public void setUp() {
        gm = GameManagerImp.getInstance();

        this.gm.addUsuario("Carlos", "Hermoso", "seko");
        this.gm.addUsuario("Anas","Amlou","3azzy");
        this.gm.addUsuario("Juninho", "Penambucano", "ORey");


    }

    @Test //Adding users + checking the number of users
    public void testAddUsers() {

        this.gm.addUsuario("Juan", "Palomo", "fenomeno");

        Assert.assertEquals(4, this.gm.getNumUsuario());
    }

    @Test
    public void testAddObjects(){

        this.gm.addObjetoUsuario("seko","coche", "rojo");
        this.gm.addObjetoUsuario("seko","moto", "amarillo");
        this.gm.addObjetoUsuario("3azzy", "avion", "verde");

        Assert.assertEquals(2, this.gm.getNumObjetosbyUsuario("seko"));
        Assert.assertEquals(1,this.gm.getNumObjetosbyUsuario("3azzy"));
    }

    @Test
    public void testModifyUser(){

        this.gm.addObjetoUsuario("seko", "coche", "amarillo");
        this.gm.updateUsuario("seko","hermoso","seko@gmail.com", "seko");
        Assert.assertEquals("seko@gmail.com",this.gm.getUsuario("seko").getMail());
        Assert.assertEquals(1,this.gm.getNumObjetosbyUsuario("seko"));
    }

    @After
    public void tearDown(){
        this.gm.clear();
    }
}
