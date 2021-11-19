/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textos;

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
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.media.j3d.Node;
/**
 *
 * @author Miguel
 */
public class Textos extends Applet {

        //CREAMOS EL GRAFO DE ESCENA
    public BranchGroup crearEscena(){ 
        BranchGroup escena = new BranchGroup();
        //Establecemos las opciones del bit de capacidad
        //Indica que el nodo permite el acceso a la información de transformación de su objeto.
        escena.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        //Indica que el nodo permite escribir la información de transformación de su objeto
        escena.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        //Anidamos un BranchGroup a la raiz del grafo
        escena.addChild(crearTexto());
        return escena;
    }
    public Textos(){
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
    public Text2D crearTexto(){
        //Creamos un objeto Text2D ("mensaje",Color RGB, fuente,tamaño fuente, estilo de la fuente)
        Text2D texto=new Text2D("PRUEBA",new Color3f(1.0f,0.0f,0.0f),"arial",15,1);
        return texto;
    }
    public BranchGroup crearTexto3D(){
        BranchGroup text = new BranchGroup();
        //Creamos el objeto que albega las transformaciones de rotacion a realizar en el objeto
        TransformGroup tg=new TransformGroup();
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        //Creamos el objeto que albega las transformaciones de escalado a realizar en el objeto
        TransformGroup tg2=new TransformGroup();
        tg2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tg2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        //1ª transformacion: rotacion 
        Transform3D t3d2=new Transform3D();
        AxisAngle4d aa=new AxisAngle4d(0.1f,0.1f,0.1f,50);
        t3d2.setRotation(aa);
        
        //Creamos un objeto Font3D (fuente, fontextrusion)
        Font3D fuente= new Font3D(new Font("Serif",Font.BOLD,1),new FontExtrusion());
        Text3D texto=new Text3D(fuente,"PRUEBA");
        Appearance apariencia=new Appearance();
        //LE ASIGNAMOS UN COLOR ( R G B SHADOW )
        apariencia.setColoringAttributes(new ColoringAttributes(0,1,0,0));
        //Asignamos el punto de partida del texto
        OrientedShape3D tam=new OrientedShape3D(texto,apariencia,OrientedShape3D.ALLOW_MODE_WRITE,new Point3f(-1.0f,0.0f,0.0f));
        //2ª transformacion: escalado 
        Transform3D t3=new Transform3D();
        t3.setScale(0.2f);
        tg2.setTransform(t3d2);
        tg.setTransform(t3);
        
        tg.addChild(tam);
        tg2.addChild(tg);
        text.addChild(tg2);
        return text;
    }
    public static void main(String[] args) {
        Frame frame = new MainFrame(new Textos(),800,620);
    }
    
}
