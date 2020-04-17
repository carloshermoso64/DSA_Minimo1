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

        this.gm.addUsuario("Marc","Vila","marclays");
        this.gm.addUsuario("Toni", "Norton", "tonilivo");
        this.gm.addUsuario("Ferran", "Lopez", "elbicho");
    }

    @Test //Adding users + checking the number of users
    public void testAddUsers() {

        this.gm.addUsuario("Juan", "Magan", "lapulga");

        Assert.assertEquals(4, this.gm.getNumUsuario());
    }

    @Test
    public void testAddObjects(){

        this.gm.addObjetoUsuario("marclays","Espada", "Espada gigante");
        this.gm.addObjetoUsuario("marclays","Arco", "Arco magnifico");
        this.gm.addObjetoUsuario("tonilivo", "Escudo", "Escudo gigante");

        Assert.assertEquals(2, this.gm.getNumObjetosbyUsuario("marclays"));
        Assert.assertEquals(1,this.gm.getNumObjetosbyUsuario("tonilivo"));
    }

    @Test
    public void testModifyUser(){

        this.gm.addObjetoUsuario("elbicho", "moneda", "moneda brillante");
        this.gm.updateUsuario("Ferran","Lopez","fr98@yahoo.com", "elbicho");
        Assert.assertEquals("fr98@yahoo.com",this.gm.getUsuario("elbicho").getMail());
        Assert.assertEquals(1,this.gm.getNumObjetosbyUsuario("elbicho"));
    }

    @After
    public void tearDown(){
        this.gm.clear();
    }
}
