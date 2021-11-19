package interpolators;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import com.sun.j3d.utils.applet.MainFrame; 
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Text2D;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Interpolators extends Applet {

    public BranchGroup createSceneGraph() {
	// Creamos la raíz del grafo de escena
	BranchGroup objRoot = new BranchGroup();
        
        Transform3D t3d = new Transform3D();
        BoundingSphere bounds = new BoundingSphere();

        /*********************************************************************/
        //Primer paso
        // Creamos el objeto fuente para el PositionInterpolator
        TransformGroup objMove = new TransformGroup();
        objMove.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        // Creamos el objeto fuente para el RotationInterpolator
        TransformGroup objRotate = new TransformGroup();
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        // Creamos el objeto fuente para el ScaleInterpolator
        TransformGroup objScale = new TransformGroup();
        objScale.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        // Creamos el objeto fuente para el ColorInterpolator
        Material objColor = new Material();
        objColor.setCapability(Material.ALLOW_COMPONENT_WRITE);

        // Creamos el objeto fuente para el TransparencyInterpolator
        TransparencyAttributes objTransp = new TransparencyAttributes();
        objTransp.setCapability(TransparencyAttributes.ALLOW_VALUE_WRITE);
        objTransp.setTransparencyMode(TransparencyAttributes.BLENDED);

        // Creamos el objeto fuente para el SwitchValueInterpolator
        Switch objSwitch = new Switch();
        objSwitch.setCapability(Switch.ALLOW_SWITCH_WRITE);
        /*********************************************************************/
        
        /*********************************************************************/
        //Paso 2
        // Creamos el objeto Alpha
        Alpha alpha = new Alpha (-1,
                                 Alpha.INCREASING_ENABLE + Alpha.DECREASING_ENABLE,
                                 0, 0, 2000, 0, 1000, 2000, 0, 1000);
        /*********************************************************************/
        
        /*********************************************************************/
        //Paso 3 y 4
        // Creamos el PositionInterpolator y establecemos los límites
        PositionInterpolator posInt = new PositionInterpolator (alpha, objMove);
        posInt.setSchedulingBounds(bounds);
        posInt.setStartPosition(-1.0f);

        // Creamos el RotationInterpolator y establecemos los límites
        RotationInterpolator rotInt = new RotationInterpolator (alpha, objRotate);
        rotInt.setSchedulingBounds(bounds);

        // Creamos el ScaleInterpolator y establecemos los límites
        ScaleInterpolator scaInt = new ScaleInterpolator (alpha, objScale);
        scaInt.setSchedulingBounds(bounds);

        // Creamos el ColorInterpolator y establecemos los límites
        ColorInterpolator colInt = new ColorInterpolator (alpha, objColor);
        colInt.setStartColor(new Color3f(1.0f, 0.0f, 0.0f));
        colInt.setEndColor(new Color3f(0.0f, 0.0f, 1.0f));
        colInt.setSchedulingBounds(bounds);

        // Creamos el TransparencyInterpolator y establecemos los límites
        TransparencyInterpolator traInt = new TransparencyInterpolator (alpha, objTransp);
        traInt.setSchedulingBounds(bounds);

        // Creamos el SwitchValueInterpolator y establecemos los límites
        SwitchValueInterpolator swiInt = new SwitchValueInterpolator (alpha, objSwitch);
        swiInt.setSchedulingBounds(bounds);
        /*********************************************************************/

        /*********************************************************************/
        //Paso 5
        //Añadimos los Interpolator al grafo de escena (objRoot.addChild(*Int))
        //Además creamos y anidamos la correspondiente transformación
        //Anidamos también el objeto al que se le aplica la nimación
        t3d.setTranslation(new Vector3f(0.0f, 0.8f, 0.0f));
        TransformGroup objMovePos = new TransformGroup(t3d);
        objRoot.addChild(objMovePos);
        objMovePos.addChild(objMove);
        objMove.addChild(createBox());
        objRoot.addChild(posInt);

        t3d.setTranslation(new Vector3f(0.0f, 0.5f, 0.0f));
        TransformGroup objRotPos = new TransformGroup(t3d);
        objRoot.addChild(objRotPos);
        objRotPos.addChild(objRotate);
        objRotate.addChild(createBox());
        objRoot.addChild(rotInt);

        t3d.setTranslation(new Vector3f(0.0f, 0.2f, 0.0f));
        TransformGroup objScalePos = new TransformGroup(t3d);
        objRoot.addChild(objScalePos);
        objScalePos.addChild(objScale);
        objScale.addChild(createBox());
        objRoot.addChild(scaInt);

        t3d.setTranslation(new Vector3f(0.0f, -0.2f, 0.0f));
        TransformGroup objColorPos = new TransformGroup(t3d);
        objRoot.addChild(objColorPos);
        Shape3D color = createCar(0.4f, 0.4f, true, false);
        Appearance materialAppear = new Appearance();
        materialAppear.setMaterial(objColor);
        color.setAppearance(materialAppear);
        objColorPos.addChild(color);
        objRoot.addChild(colInt);

        t3d.setTranslation(new Vector3f(0.0f, -0.5f, 0.0f));
        TransformGroup objTranspPos = new TransformGroup(t3d);
        objRoot.addChild(objTranspPos);
        Shape3D transp = createQuadArray(1);
        Appearance transpAppear = transp.getAppearance();
        transpAppear.setTransparencyAttributes(objTransp);
        objTranspPos.addChild(transp);
        objRoot.addChild(traInt);

        t3d.setTranslation(new Vector3f(0.0f, -0.8f, 0.0f));
        TransformGroup objSwitchPos = new TransformGroup(t3d);
        objRoot.addChild(objSwitchPos);
        objSwitch.addChild(createBox());
        objSwitch.addChild(new ColorCube(0.1f));
        objSwitchPos.addChild(objSwitch);
        objRoot.addChild(swiInt);
        swiInt.setLastChildIndex(2);
        /*********************************************************************/

        DirectionalLight lightD1 = new DirectionalLight();
        lightD1.setInfluencingBounds(bounds);
        objRoot.addChild(lightD1);

        objRoot.compile();

	return objRoot;
    }
    
     Shape3D createCar(float xScale, float yScale, boolean createNormals,
                        boolean assignColoring) {
        Shape3D car = new Shape3D();
        QuadArray carGeom = null;

        if (createNormals)
            carGeom = new QuadArray(16, GeometryArray.COORDINATES
                                        | GeometryArray.NORMALS);
        else
            carGeom = new QuadArray(16, GeometryArray.COORDINATES);

        carGeom.setCoordinate( 0, new Point3f(xScale*-0.25f, yScale*0.22f, 0.0f));
        carGeom.setCoordinate( 1, new Point3f(xScale* 0.20f, yScale*0.22f, 0.0f));
        carGeom.setCoordinate( 2, new Point3f(xScale* 0.10f, yScale*0.35f, 0.0f));
        carGeom.setCoordinate( 3, new Point3f(xScale*-0.20f, yScale*0.35f, 0.0f));
        carGeom.setCoordinate( 4, new Point3f(xScale*-0.50f, yScale*0.10f, 0.0f));
        carGeom.setCoordinate( 5, new Point3f(xScale* 0.50f, yScale*0.10f, 0.0f));
        carGeom.setCoordinate( 6, new Point3f(xScale* 0.45f, yScale*0.20f, 0.0f));
        carGeom.setCoordinate( 7, new Point3f(xScale*-0.48f, yScale*0.20f, 0.0f));
        carGeom.setCoordinate( 8, new Point3f(xScale*-0.26f, yScale*0.00f, 0.0f));
        carGeom.setCoordinate( 9, new Point3f(xScale*-0.18f, yScale*0.00f, 0.0f));
        carGeom.setCoordinate(10, new Point3f(xScale*-0.16f, yScale*0.12f, 0.0f));
        carGeom.setCoordinate(11, new Point3f(xScale*-0.28f, yScale*0.12f, 0.0f));
        carGeom.setCoordinate(12, new Point3f(xScale* 0.25f, yScale*0.00f, 0.0f));
        carGeom.setCoordinate(13, new Point3f(xScale* 0.33f, yScale*0.00f, 0.0f));
        carGeom.setCoordinate(14, new Point3f(xScale* 0.35f, yScale*0.12f, 0.0f));
        carGeom.setCoordinate(15, new Point3f(xScale* 0.23f, yScale*0.12f, 0.0f));

        if (createNormals){
            int i;
            Vector3f normal = new Vector3f(0.6f, 0.6f, 0.8f);
            for(i = 0; i < 8; i++)
                carGeom.setNormal(i, normal);
            normal.set(new Vector3f(0.5f, 0.5f, 0.5f));
            for(i = 8; i <16; i++)
                carGeom.setNormal(i, normal);
        }

        if (assignColoring){
            ColoringAttributes colorAttrib =
                  new ColoringAttributes(0.0f, 0.0f, 1.0f, ColoringAttributes.NICEST);
            Appearance carAppear = new Appearance();
            carAppear.setColoringAttributes(colorAttrib);
            car.setAppearance(carAppear);
        }

        car.setGeometry(carGeom);
        return car;
    }
     
    public Shape3D createQuadArray(int asignarColor){
        Shape3D shape = new Shape3D();
        QuadArray puntos = new QuadArray(4, QuadArray.COORDINATES);
        puntos.setCoordinate(0, new Point3f(-0.5f, -0.2f, 0f));
        puntos.setCoordinate(1, new Point3f(0.5f, -0.2f, 0f));
        puntos.setCoordinate(2, new Point3f(0.5f, 0.2f, 0f));
        puntos.setCoordinate(3, new Point3f(-0.5f, 0.2f, 0f));
        
        if(asignarColor == 1){
           ColoringAttributes colorAttrib =
                  new ColoringAttributes(0.0f, 0.0f, 1.0f, ColoringAttributes.NICEST);
            Appearance apariencia = new Appearance();
            apariencia.setColoringAttributes(colorAttrib);
            shape.setAppearance(apariencia);
        }
        
        shape.setGeometry(puntos);
        return shape;
    }
    
    public Box createBox() {
        Appearance apariencia = new Appearance();
        apariencia.setColoringAttributes(new ColoringAttributes(0.3f, 1.0f, 1.0f, ColoringAttributes.SHADE_GOURAUD));
        return new Box(0.1f, 0.1f, 0.1f, apariencia);
    }

    public Interpolators() {
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
        Frame frame = new MainFrame(new Interpolators(), 256, 256);
    }

}
