package animacion;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import com.sun.j3d.utils.applet.MainFrame; 
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.*;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;

public class Animacion extends Applet {

    public BranchGroup createSceneGraph() {
	// Creamos la raíz el grafo de escena
	BranchGroup objRoot = new BranchGroup();

        // Creamos un Transform group estableciendo sus capacidades
        TransformGroup objSpin = new TransformGroup();
        objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        // Creamos el objeto Alpha par que gire continuamente(-1) y con un periodo de 1 minuto(60000ms)
        Alpha alpha = new Alpha (-1, 60000);

        // Creamos la rotación sobre el ejeY utilizando un Objeto Interpolator
        RotationInterpolator rotInt = new RotationInterpolator (alpha, objSpin);
        //Establecemos los límites
        rotInt.setSchedulingBounds(new BoundingSphere());

        // Anidamos todo a la raíz del grafo
        objRoot.addChild(objSpin);
        objSpin.addChild(createBox());
        objRoot.addChild(rotInt);

        objRoot.compile();

	return objRoot;
    }

    public Box createBox() {
        Appearance apariencia = new Appearance();
        apariencia.setColoringAttributes(new ColoringAttributes(0.3f, 1.0f, 1.0f, ColoringAttributes.SHADE_GOURAUD));
        return new Box(0.5f, 0.3f, 0.5f, apariencia);
    }
    
    public Animacion() {
        setLayout(new BorderLayout());
        GraphicsConfiguration config=SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);

        BranchGroup scene = createSceneGraph();

        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

        simpleU.getViewingPlatform().setNominalViewingTransform();

        simpleU.addBranchGraph(scene);
    } 

    public static void main(String[] args) {
        Frame frame = new MainFrame(new Animacion(), 256, 256);
    }

}
