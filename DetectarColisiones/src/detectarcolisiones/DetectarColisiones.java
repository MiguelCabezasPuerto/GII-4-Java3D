/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detectarcolisiones;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.media.j3d.Node;
import detectarcolisiones.CollisionDetector;
import javax.swing.Box;


/**
 *
 * @author Miguel
 */
public class DetectarColisiones extends Applet {

    private SimpleUniverse u=null;
     //CREAMOS EL GRAFO DE ESCENA
    //CREAMOS EL GRAFO DE ESCENA
    public BranchGroup crearEscena(){ 
        BranchGroup escena = new BranchGroup();
        escena.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        escena.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        MouseRotate myMouseRotate = new MouseRotate();
        MouseTranslate myMouseTranslate = new MouseTranslate();
        MouseZoom myMouseZoom = new MouseZoom();
        escena.addChild(dibujarCubo(myMouseRotate,myMouseTranslate,myMouseZoom));
        escena.addChild(myMouseRotate);
        escena.addChild(myMouseTranslate);
        escena.addChild(myMouseZoom);
        Group cubo2=crearCubo();
        escena.addChild(cubo2);
        return escena;
    }
    private Group crearCubo(){
        //CREAMOS LA TRANSFORMACION PARA CAMBIAR LA POSICION DEL CUBO
        Transform3D t3=new Transform3D();
        Vector3f translate = new Vector3f();
        //ASIGNAMOS UNAS COORDENADAS
        translate.set(0.7f, 0.5f, 0.0f);
        //APLICAMOS LA TRASLACION A LA TRANSFORMACION CREADA AL EFECTO
        t3.setTranslation(translate);
        //CREAMOS EL GRUPO DE TRANSFORMACIONES
        TransformGroup tg=new TransformGroup(t3);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        //CREAMOS LA CAJA HACIENDO USO DE LA CLASE CAJA.JAVA
        Shape3D forma= new Caja(0.1f,0.1f,0.1f);
        //APLICAMOS LA TRANSFORMACION A LA CAJA
        tg.addChild(forma);
        //AÑADIMOS UN OBJETO PARA DETECTAR COLISIONES A LA CAJA HACIENDO USO DE LA CLASE CREADA AL EFECTO
        CollisionDetector cd = new CollisionDetector(forma);
        //ESTABLECEMOS LOS LIMITES DE DETECCION
        BoundingSphere bounds =new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        //APLICAMOS LOS LIMITES AL DETECTOR DE COLISIONES
	cd.setSchedulingBounds(bounds);
        //ANIDAMOS EL DETECTOR DE COLISIONES AL GRUPO DE TRANSFORMACIONES
        tg.addChild(cd);
        return tg;
    }
   private BranchGroup dibujarCubo(MouseRotate myMouseRotate,MouseTranslate myMouseTranslate,MouseZoom myMouseZoom) {
        BranchGroup cubo = new BranchGroup();
        //GENERATE_NORMALS especifica que las normales se generan junto con las posiciones
        //GENERATE_TEXTURE_COORDS specifica que las coordenadas de textura se generan junto con las posiciones
        int paratextura=Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS;
        //Creamos el Box (tamX,tamY,tamZ,flags, apariencia)
        com.sun.j3d.utils.geometry.Box forma = new com.sun.j3d.utils.geometry.Box(0.1f, 0.1f, 0.1f, paratextura, crearApariencia());
        
        //Creamos el objeto que albega todas las transformaciones a realizar en el objeto
        TransformGroup tg=new TransformGroup();
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        //Indicamos sobre que TransformGroup operara este comportamiento de rotacion que sera el que se aplicara al objeto Box
        myMouseRotate.setTransformGroup(tg);
        //Establecemos la región de programación del comportamiento en los límites especificados
        myMouseRotate.setSchedulingBounds(new BoundingSphere());
        //Indicamos sobre que TransformGroup operara este comportamiento de traslacion que sera el que se aplicara al objeto Box
        myMouseTranslate.setTransformGroup(tg);
        //Establecemos la región de programación del comportamiento en los límites especificados
        myMouseTranslate.setSchedulingBounds(new BoundingSphere());
        //Indicamos sobre que TransformGroup operara este comportamiento de zoom que sera el que se aplicara al objeto Box
        myMouseZoom.setTransformGroup(tg);
        //Establecemos la región de programación del comportamiento en los límites especificados
        myMouseZoom.setSchedulingBounds(new BoundingSphere());
        
        //1ª transformacion: rotacion del box
        Transform3D t3d2=new Transform3D();
        AxisAngle4d aa=new AxisAngle4d(0.3f,0.3f,0.3f,135);
        t3d2.setRotation(aa);
        //Añadimos la transformacion al objeto que alberga las transformaciones
        tg.setTransform(t3d2);
        //Aplicamos la transformacion a la forma Box
        tg.addChild(forma);
        //Anidamos el objeto Box con las transformaciones ya aplicadas al BranchGroup
        cubo.addChild(tg);
        return cubo;
    }
     public Appearance crearApariencia(){
        //Cargamos la textura (imagen, canal a usar, observador
        TextureLoader loader = new TextureLoader("C:\\Users\\Miguel\\Desktop\\imagen2.jpg","INTENSITY",new Container());
        //Obtenemos la textura cargada
        Texture texture = loader.getTexture();
        //CREAMOS EL OBJETO PARA LOS ATRIBUTOS DE LA TEXTURA (PROPIEDADES)
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.REPLACE);
        //Creamos el objeto que recoge la apariencia
        Appearance apariencia=new Appearance();
        //LE ASIGNAMOS UN COLOR ( R G B SHADOW )
        apariencia.setColoringAttributes(new ColoringAttributes(0,1,0,0));
        //Asignamos la textura cargada a la apariencia
        apariencia.setTexture(texture);
        //Material material=new Material(new Color3f(1,0,0),new Color3f(0,1,0),new Color3f(0,0,1),new Color3f(0,0,0),1);
        //apariencia.setMaterial(material);
        //Le asignamos los atributos de la textura
        apariencia.setTextureAttributes(texAttr);
        return apariencia;
    }
     public DetectarColisiones(){
        setLayout(new BorderLayout());
        Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        add("Center",canvas3D);
        //creamos la escena y compilamos
        BranchGroup escena=crearEscena();
        escena.compile();
        //creamos el universo y acomodamos la camara
        SimpleUniverse universo=new SimpleUniverse(canvas3D);
        universo.getViewingPlatform().setNominalViewingTransform();
        //movemos la camara
        //agregamos la scena a nuestro universo
        universo.addBranchGraph(escena);
    }
    public static void main(String[] args) {
        Frame frame = new MainFrame(new DetectarColisiones(),820,600);
    }
    
}
