package edu.upc.dsa;

import edu.upc.dsa.utils.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Covid19ManagerTest {
    Covid19Manager gm;

    @Before
    public void setUp() {
        gm = Covid19ManagerImp.getInstance();


        this.gm.addBrote("Barcelona",20200417,"4");
        this.gm.addBrote("Madrid",20200413,"2");



    }

    @Test //Adding users + checking the number of users
    public void testAddBrotes() {

        this.gm.addBrote("Valencia", 20200229, "3");

    }

    @Test
    public void testAddCasos() {
        this.gm.addBrote("Murcia",20200315,"2");
        this.gm.addCasoToBrote("2","Carlos","Hermoso","64",19980920,20200327,"A","M","carlos@gmail.com","680420180","Gava","R");
        this.gm.addCasoToBrote("2","Juninho","Pernambucano","69",19990220,20200421,"A","M","juni@gmail.com","642246242","Brasil","A");
    }

    @After
    public void tearDown(){
        this.gm.clear();
    }
}
