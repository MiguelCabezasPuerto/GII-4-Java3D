package geometriabasica;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.*;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.media.j3d.Node;

public class GeometriaBasica extends Applet {
    private SimpleUniverse u=null;
   
    //CREAMOS EL GRAFO DE ESCENA
    public BranchGroup CreateSceneGraph(){ 
        //Creamos la raiz del grafo de escena
        BranchGroup branch = new BranchGroup();

        /**************************************************************************/
        //Box
        //Node node = createBox();
        //branch.addChild(node);
        /**************************************************************************/
        //Cone
        //Node node = createCone();
        //branch.addChild(node);
        /**************************************************************************/
        //Cylinder
        //Node node = createCylinder();
        //branch.addChild(node);
        /**************************************************************************/
        //Sphere
        //Node node = createSphere();
        //branch.addChild(node);
        /**************************************************************************/
        //Sphere 3D
        //Node node = new Sphere(0.5f);
        //branch.addChild(node);
        //DirectionalLight sphereLight = createSphereLight();
        //branch.addChild(sphereLight);
        //branch.addChild(node);
        /**************************************************************************/
        //YoYo
        //Transform3D rotate = new Transform3D();
        //Transform3D translate = new Transform3D();
        //rotate.rotZ(Math.PI/2.0d);
        //TransformGroup yoyoTGR1 = new TransformGroup(rotate);
        //translate.set(new Vector3f(0.1f, 0.0f, 0.0f));
        //TransformGroup yoyoTGT1 = new TransformGroup(translate);
        //Cone cone1 = createCone();
        //branch.addChild(yoyoTGT1);
        //yoyoTGT1.addChild(yoyoTGR1);
        //yoyoTGR1.addChild(cone1);
        //rotate.rotZ(-Math.PI/2.0d);
        //TransformGroup yoyoTGR2 = new TransformGroup(rotate);
        //translate.set(new Vector3f(-0.1f, 0.0f, 0.0f));
        //TransformGroup yoyoTGT2 = new TransformGroup(translate);
        //Cone cone2 = createCone();
        //branch.addChild(yoyoTGT2);
        //yoyoTGT2.addChild(yoyoTGR2);
        //yoyoTGR2.addChild(cone2);
        /**************************************************************************/
        //GeometryArray
        //branch.addChild(new Shape3D(createAxisXLines()));
        //branch.addChild(new Shape3D(createAxisYLines()));
        //branch.addChild(new Shape3D(createAxisZLines()));
        /**************************************************************************/
        //PointArray
        //branch.addChild(new Shape3D(createPointArray()));
        /**************************************************************************/
        //LineArray
        //branch.addChild(new Shape3D(createLineArray()));
        /**************************************************************************/
        //TriangleArray
        //branch.addChild(new Shape3D(createTriangleArray()));
        /**************************************************************************/
        //QuadArray
        //branch.addChild(new Shape3D(createQuadArray()));
        /**************************************************************************/
        
        branch.compile();
        return branch;
    }
    
    public Box createBox() {
        Appearance apariencia = new Appearance();
        apariencia.setColoringAttributes(new ColoringAttributes(0.3f, 1.0f, 1.0f, ColoringAttributes.SHADE_GOURAUD));
        return new Box(0.5f, 0.3f, 0.5f, apariencia);
    }
    
    public Cone createCone() {
        Appearance apariencia = new Appearance();
        apariencia.setColoringAttributes(new ColoringAttributes(0.3f, 1.0f, 1.0f, ColoringAttributes.SHADE_GOURAUD));
        return new Cone(0.5f, 0.3f, apariencia);
    }
    
    public Cylinder createCylinder() {
        Appearance apariencia = new Appearance();
        apariencia.setColoringAttributes(new ColoringAttributes(0.3f, 1.0f, 1.0f, ColoringAttributes.SHADE_GOURAUD));
        return new Cylinder(0.5f, 0.3f, apariencia);
    }
    
    public Sphere createSphere() {
        Appearance apariencia = new Appearance();
        apariencia.setColoringAttributes(new ColoringAttributes(0.3f, 1.0f, 1.0f, ColoringAttributes.SHADE_GOURAUD));
        return new Sphere(0.5f,apariencia);
    }
    
    public DirectionalLight createSphereLight() {
        Color3f sphereLightColor = new Color3f(0.5f, 1.0f, 0.8f);
        Vector3f sphereLightDirection = new Vector3f(0, -5, -15);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0), 100);
        DirectionalLight sphereLight = new DirectionalLight(sphereLightColor, sphereLightDirection);
        sphereLight.setInfluencingBounds(bounds);
        return sphereLight;
    }
    
    public LineArray createAxisXLines(){
        Color3f red = new Color3f(1.0f, 0.0f, 0.0f);
        LineArray axisXLines = new LineArray(2, LineArray.COORDINATES | LineArray.COLOR_3);
        axisXLines.setCoordinate(0, new Point3f(-1.0f, 0.0f, 0.0f));
        axisXLines.setCoordinate(1, new Point3f(1.0f, 0.0f, 0.0f));
        axisXLines.setColor(0, red);
        axisXLines.setColor(1, red);
        return axisXLines;
    }
    
    public LineArray createAxisYLines(){
        Color3f green = new Color3f(0.0f, 1.0f, 0.0f);
        Color3f blue = new Color3f(0.0f, 0.0f, 1.0f);
        LineArray axisYLines = new LineArray(2, LineArray.COORDINATES | LineArray.COLOR_3);
        axisYLines.setCoordinate(0, new Point3f(0.0f, -1.0f, 0.0f));
        axisYLines.setCoordinate(1, new Point3f(0.0f, 1.0f, 0.0f));
        axisYLines.setColor(0, green);
        axisYLines.setColor(1, blue);
        return axisYLines;
    }
    
    public LineArray createAxisZLines(){
        Color3f blue = new Color3f(0.0f, 0.0f, 1.0f);
        LineArray axisZLines = new LineArray(10, LineArray.COORDINATES | LineArray.COLOR_3);
        axisZLines.setCoordinate(0, new Point3f(0.1f, 0.3f, 0.9f));
        axisZLines.setCoordinate(1, new Point3f(0.1f, 0.2f, 0.9f));
        axisZLines.setCoordinate(2, new Point3f(0.1f, 0.2f, 0.9f));
        axisZLines.setCoordinate(3, new Point3f(0.1f, 0.1f, 0.9f));
        axisZLines.setCoordinate(4, new Point3f(-0.1f, 0.2f, 0.9f));
        axisZLines.setCoordinate(5, new Point3f(-0.1f, 0.1f, 0.9f));
        axisZLines.setCoordinate(6, new Point3f(0.1f, -0.2f, 0.9f));
        axisZLines.setCoordinate(7, new Point3f(0.1f, -0.1f, 0.9f));
        axisZLines.setCoordinate(8, new Point3f(-0.1f, -0.2f, 0.9f));
        axisZLines.setCoordinate(9, new Point3f(-0.1f, -0.1f, 0.9f));
        Color3f colors[] = new Color3f[10];
        for(int v = 0; v < 10; v++){
            colors[v] = blue;
        }
        axisZLines.setColors(0, colors);
        return axisZLines;
    }
    
    public PointArray createPointArray(){
        Color3f red = new Color3f(1.0f, 0.0f, 0.0f);
        Color3f blue = new Color3f(0.0f, 0.0f, 1.0f);
        Color3f green = new Color3f(0.0f, 1.0f, 0.0f);
        PointArray puntos = new PointArray(3, PointArray.COORDINATES | PointArray.COLOR_3);
        puntos.setCoordinate(0, new Point3f(0f, 0f, 0f));
        puntos.setCoordinate(1, new Point3f(0.5f, 0f, 0f));
        puntos.setCoordinate(2, new Point3f(0f, 0.5f, 0f));
        puntos.setColor(0, red);
        puntos.setColor(1, blue);
        puntos.setColor(2, green);
        return puntos;
    }
    
    public LineArray createLineArray(){
        Color3f red = new Color3f(1.0f, 0.0f, 0.0f);
        Color3f blue = new Color3f(0.0f, 0.0f, 1.0f);
        LineArray puntos = new LineArray(2, LineArray.COORDINATES | LineArray.COLOR_3);
        puntos.setCoordinate(0, new Point3f(0f, 0f, 0f));
        puntos.setCoordinate(1, new Point3f(0.5f, 0f, 0f));
        puntos.setColor(0, red);
        puntos.setColor(1, blue);
        return puntos;
    }
    
    public TriangleArray createTriangleArray(){
        Color3f red = new Color3f(1.0f, 0.0f, 0.0f);
        TriangleArray puntos = new TriangleArray(3, TriangleArray.COORDINATES | TriangleArray.COLOR_3);
        puntos.setCoordinate(0, new Point3f(0f, 0.5f, 0f));
        puntos.setCoordinate(1, new Point3f(0f, -0.5f, 0f));
        puntos.setCoordinate(2, new Point3f(0.5f, 0f, 0f));
        puntos.setColor(0, red);
        puntos.setColor(1, red);
        puntos.setColor(2, red);
        return puntos;
    }
    
    public QuadArray createQuadArray(){
        Color3f blue = new Color3f(0.0f, 0.0f, 1.0f);
        QuadArray puntos = new QuadArray(4, QuadArray.COORDINATES | QuadArray.COLOR_3);
        puntos.setCoordinate(0, new Point3f(-0.5f, -0.5f, 0f));
        puntos.setCoordinate(1, new Point3f(0.5f, -0.5f, 0f));
        puntos.setCoordinate(2, new Point3f(0.5f, 0.5f, 0f));
        puntos.setCoordinate(3, new Point3f(-0.5f, 0.5f, 0f));
        puntos.setColor(0, blue);
        puntos.setColor(1, blue);
        puntos.setColor(2, blue);
        puntos.setColor(3, blue);
        return puntos;
    }
    
    public GeometriaBasica(){
    }
    
    //ESTO SE EJECUTA CUANDO SE INICIA LA APLICACION
    public void init(){
        //ESTABLECE EL TIPO DE LAYOUT
        setLayout(new BorderLayout());
        GraphicsConfiguration config=SimpleUniverse.getPreferredConfiguration();
        //CREAMOS EL ESPACIO DONDE SE DIBUJA
        Canvas3D c=new Canvas3D(config);
        //CENTRAMOS EL DIBUJADO
        add("Center",c);
        //LLAMAMOS A LA FUNCION PARA CREAR EL GRAFO DE ESCENA
        BranchGroup scene=CreateSceneGraph();
        //CREAMOS EL UNIVERSO
        u=new SimpleUniverse(c);
        u.getViewingPlatform().setNominalViewingTransform();
        //ANIDAMOS EL GRAFO DE ESCENA AL UNIVERSO
        u.addBranchGraph(scene);
    }

    public void destroy(){
        u.cleanup();
    }
  
    public static void main(String[] args) {
        Frame frame=new MainFrame(new GeometriaBasica(),256,256);
    }  

}
