/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colorcube;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.universe.*;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.media.j3d.Node;

/**
 *
 * @author Miguel
 */
public class GeometriaBasica extends Applet {
    private SimpleUniverse u=null;
   
    //CREAMOS EL GRAFO DE ESCENA
    public BranchGroup CreateSceneGraph(){ 
        //CREAMOS LA RAIZ DEL GRAFO DE ESCENA
        BranchGroup objRoot=new BranchGroup();
        //INSTANCIAMOS Y PERSONALIZAMOS LA APARIENCIA
        Appearance apariencia=new  Appearance();
        apariencia.setColoringAttributes(new ColoringAttributes(0.3f,1.0f,1.0f,ColoringAttributes.SHADE_GOURAUD));
        //CREAMOS EL NODO A ANIDAR DE LA RAIZ (BOX,CONE...)
        Node node=new Box(0.5f,0.3f,0.5f,apariencia);
        //ANIDAMOS EL NODO AL RAIZ
        objRoot.addChild(node);
        //COMPILAMOS PARA UN MEJOR RENDIMIENTO
        objRoot.compile();
        //DEVOLVEMOS EL GRAFO DE ESCENA
        return objRoot;
    }
    
    //CONSTRUCTOR DE LA CLASE HELLOCONE
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
 

