import dit954lab.world.CarRepairShop;
import dit954lab.world.vehicles.*;
import dit954lab.world.vehicles.addons.BooleanFlak;
import dit954lab.world.vehicles.addons.Flak;
import dit954lab.world.vehicles.addons.GradualFlak;
import org.junit.Test;
import util.Container;
import util.Unit;
import vector2d.Coord;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.Assert.*;

public class Transporters {
    <U> void flakTest(Flak<U> flak){
        flak.closeFlak();

        assertTrue(flak.openFlak());
        assertTrue(flak.isFlakOpen());
        assertFalse(flak.openFlak());
        assertTrue(flak.isFlakOpen());

        assertTrue(flak.closeFlak());
        assertTrue(flak.isFlakClosed());
        assertFalse(flak.closeFlak());
        assertTrue(flak.isFlakClosed());
    }

    void unitFlakTest(Flak<Unit> flak){
        flak.closeFlak();

        assertTrue(flak.openFlak(Unit.unit));
        assertTrue(flak.isFlakOpen());
        assertFalse(flak.openFlak(Unit.unit));
        assertTrue(flak.isFlakOpen());

        assertTrue(flak.closeFlak(Unit.unit));
        assertTrue(flak.isFlakClosed());
        assertFalse(flak.closeFlak(Unit.unit));
        assertTrue(flak.isFlakClosed());
    }

    void doubleFlakTest(Flak<Double> flak,double range){
        flak.closeFlak();

        {
            //Not open while opening slightly.
            for(int i=0; i<3; i++){
                assertTrue(flak.openFlak(range/3.2));
                assertFalse(flak.isFlakOpen()); assertFalse(flak.isFlakClosed());
            }
            //Open when overflowing range.
            assertTrue(flak.openFlak(range/3.2));
            assertTrue(flak.isFlakOpen()); assertFalse(flak.isFlakClosed());

            //Still open after open.
            assertFalse(flak.openFlak(range/3.2));
            assertTrue(flak.isFlakOpen()); assertFalse(flak.isFlakClosed());

            //Nothing happening for negative angles.
            assertFalse(flak.openFlak(-1.0));
            assertTrue(flak.isFlakOpen()); assertFalse(flak.isFlakClosed());
        }

        {
            //Not closed while closing slightly.
            for(int i=0; i<3; i++){
                assertTrue(flak.closeFlak(range/3.2));
                assertFalse(flak.isFlakOpen()); assertFalse(flak.isFlakClosed());
            }
            //Closed when overflowing range.
            assertTrue(flak.closeFlak(range/3.2));
            assertFalse(flak.isFlakOpen()); assertTrue(flak.isFlakClosed());

            //Still closed after close.
            assertFalse(flak.closeFlak(range/3.2));
            assertFalse(flak.isFlakOpen()); assertTrue(flak.isFlakClosed());

            //Nothing happening for negative angles.
            assertFalse(flak.closeFlak(-1.0));
            assertFalse(flak.isFlakOpen()); assertTrue(flak.isFlakClosed());
        }
    }

    boolean diffEq(double x,double y,double d){
        if(Double.compare(x,y) == 0){
            return true;
        }
        return Math.abs(x - y) <= d;
    }

    <C extends Vehicle & Flak.Has<?>> void vehicleFlakTest(C car){
        assertFalse(car.isMoving());
        assertTrue(car.getFlak().isFlakClosed());

        {
            //Movable when closed.
            assertFalse(car.getFlak().closeFlak());
            assertTrue(car.getFlak().isFlakClosed());
            var spd = car.getCurrentSpeed();
            var pos = new Coord(car.getPosition());
            car.gas(20.0);
            car.move();
            assertTrue(car.isMoving());
            assertTrue(car.getCurrentSpeed() > spd);
            assertFalse(diffEq(car.getPosition().getX(),pos.getX(),0.01) && diffEq(car.getPosition().getY(),pos.getY(),0.01));

            //Not openable when moving.
            assertFalse(car.getFlak().openFlak());
            assertFalse(car.getFlak().isFlakOpen());
        }

        car.stopEngine();

        {
            //Immovable when opened.
            assertTrue(car.getFlak().openFlak());
            assertTrue(car.getFlak().isFlakOpen());
            var spd = car.getCurrentSpeed();
            var pos = new Coord(car.getPosition());
            car.gas(20.0);
            car.move();
            assertFalse(car.isMoving());
            assertEquals(car.getCurrentSpeed(),spd,0.1);
            assertTrue(diffEq(car.getPosition().getX(),pos.getX(),0.01) && diffEq(car.getPosition().getY(),pos.getY(),0.01));
        }
    }

    <T> void containerAddTest(Container<T> c,T[] t){
        assertTrue(c.isEmpty());
        assertEquals(0, c.size());
        assertNull(c.remove());
        for(int i=0; i<t.length; i++){
            assertTrue(c.add(t[i]));
            assertEquals(c.size(), i + 1);
            assertFalse(c.isEmpty());
            for(int j=0; j<t.length; j++){
                assertEquals((j <= i), c.contains(t[j]));
            }
        }
    }

    <T> void containerCannotAddTest(Container<T> c,T t){
        var size = c.size();
        assertFalse(c.add(t));
        assertFalse(c.contains(t));
        assertEquals(size,c.size());
    }


    <T> void containerRemoveTest(Container<T> c,T[] t){
        var size = c.size();
        for(int i=0; i<t.length; i++){
            assertSame(c.remove(), t[i]);
            assertEquals(c.size(), size - i - 1);
            for(int j=0; j<=i; j++){
                assertFalse(c.contains(t[j]));
            }
        }
    }

    @Test
    public void arrayContainer(){
        var c = new Container.Array<>(4);
        Integer[] cs = {0,1,2,3};
        containerAddTest(c,cs);
        containerCannotAddTest(c,4);
        Collections.reverse(Arrays.asList(cs));
        containerRemoveTest(c,cs);
    }

    @Test
    public void queueContainer(){
        var c = new Container.Queue<>(new ArrayBlockingQueue<>(4));
        Integer[] cs = {0,1,2,3};
        containerAddTest(c,cs);
        containerCannotAddTest(c,4);
        containerRemoveTest(c,cs);
    }

    @Test
    public void booleanFlak(){
        var flak = new BooleanFlak();
        flakTest(flak);
        unitFlakTest(flak);
    }

    @Test
    public void gradualFlak(){
        var flak = new GradualFlak(-2.0,2.0);
        flakTest(flak);
        doubleFlakTest(flak,4.0);
    }

    public <C extends Vehicle & Flak.Has<Unit> & Container.Has<Volvo240>> void containerFlakTest(C car,Volvo240[] cs,boolean reversed){
        flakTest(car.getFlak());
        unitFlakTest(car.getFlak());
        vehicleFlakTest(car);

        //Cannot add when flak is closed.
        car.stopEngine();
        car.getFlak().closeFlak();
        containerCannotAddTest(car.getContainer(),new Volvo240(new Coord(0,0),0));

        //Cannot add when moving.
        car.startEngine();
        car.gas(1.0);
        containerCannotAddTest(car.getContainer(),new Volvo240(new Coord(0,0),0));

        car.stopEngine();
        car.getFlak().openFlak();
        containerAddTest(car.getContainer(),cs);
        containerCannotAddTest(car.getContainer(),new Volvo240(new Coord(0,0),0));

        //Move around a bit.
        car.gas(1.0);
        car.move();
        car.stopEngine();

        if(reversed) Collections.reverse(Arrays.asList(cs));
        containerRemoveTest(car.getContainer(),cs);

        //Removed at the same location.
        for(var c : cs) {
            assertEquals(c.getPosition().getX(), car.getPosition().getX(), 0.000001);
            assertEquals(c.getPosition().getY(), car.getPosition().getY(), 0.000001);
        }

        //Can add after removals.
        Volvo240[] cs2 = {
                new Volvo240(new Coord(car.getPosition()), 0.2),
        };
        containerAddTest(car.getContainer(),cs2);

        //Cannot add too far away
        containerCannotAddTest(car.getContainer(),new Volvo240(new Coord(100000,100000),50));

        //Cannot remove when flak is closed.
        car.getFlak().closeFlak();
        assertNull(car.getContainer().remove());

        //Cannot remove when moving.
        car.gas(1.0);
        assertNull(car.getContainer().remove());
    }

    @Test
    public void carFerryFlak(){
        Volvo240[] cs = {
                new Volvo240(new Coord(0.00001,-1.0),0.2),
                new Volvo240(new Coord(0.0001,-0.01),0.4),
                new Volvo240(new Coord(0.001,-0.001),0.6),
                new Volvo240(new Coord(0.01,-0.0001),0.8),
                new Volvo240(new Coord(0.1,-0.00001),1.0),
        };
        containerFlakTest(new CarFerry<Volvo240>(new Coord(0.0,0.0),0),cs,false);
    }

    @Test
    public void carTransporterFlak(){
        Volvo240[] cs = {
                new Volvo240(new Coord(0.00001,-1.0),0.2),
                new Volvo240(new Coord(0.0001,-0.01),0.4),
                new Volvo240(new Coord(0.001,-0.001),0.6),
        };
        containerFlakTest(new CarTransporter<Volvo240>(new Coord(0.0,0.0),0),cs,true);
    }

    @Test
    public void scaniaFlak(){
        var car = new Scania(new Coord(0.0,0.0),0);
        flakTest(car.getFlak());
        doubleFlakTest(car.getFlak(),70);
        vehicleFlakTest(car);
    }

    @Test
    public void carRepairShopFlak(){
        var car = new CarRepairShop<Saab95>(2,new Coord(0.0,0.0));
        assertEquals(car.getVelocity().getX(), 0.0, 0.0);
        assertEquals(car.getVelocity().getY(), 0.0, 0.0);

        flakTest(car.getFlak());
        unitFlakTest(car.getFlak());

        car.getFlak().openFlak();

        Saab95[] cs = {
                new Saab95(new Coord(0.00001,-1.0),0.2),
                new Saab95(new Coord(0.0001,-0.01),0.4),
        };
        containerAddTest(car.getContainer(),cs);
        containerCannotAddTest(car.getContainer(),new Saab95(new Coord(car.getPosition()),0.0));
        Collections.reverse(Arrays.asList(cs));
        containerRemoveTest(car.getContainer(),cs);
    }
}
