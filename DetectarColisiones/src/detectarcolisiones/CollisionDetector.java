/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detectarcolisiones;

import java.util.Enumeration;
import javax.media.j3d.*;
import javax.vecmath.*;

public class CollisionDetector extends Behavior {
    
    private boolean inCollision = false;
    private Shape3D shape;
  
    //DISPARADOR DE LA DETECCION DEL INICIO DE COLISION
    private WakeupOnCollisionEntry wEnter;
    //DISPARADOR DE DETECCION DE FIN DE COLISION (NO COLISIONA CON NINGUN OTRO OBJETO)
    private WakeupOnCollisionExit wExit;


    public CollisionDetector(Shape3D s) {
	shape = s;
	inCollision = false;
    }

    public void initialize() {
        //ESPECIFICAMOS QUE OBJETO SERA CON EL QUE SE DISPAREN LOS METODOS(DETECTE LAS COLISIONES)
	wEnter = new WakeupOnCollisionEntry(shape);
	wExit = new WakeupOnCollisionExit(shape);
	wakeupOn(wEnter);
    }
    //METODO PARA RESPONDER A LOS EVENTOS DE INICIO Y FINAL DE COLISION
    public void processStimulus(Enumeration criteria) {
	inCollision = !inCollision;

	if (inCollision) {
	    
            System.out.println("Colision");
	    wakeupOn(wExit);
	}
	else {
	   
	    wakeupOn(wEnter);
	}
    }
}
