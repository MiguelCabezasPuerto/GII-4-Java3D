/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texturizado;

import com.sun.j3d.utils.image.TextureLoader;
import java.applet.Applet;
import javax.media.j3d.Appearance;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Texture2D;

/**
 *
 * @author Miguel
 */
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.*;
import javax.vecmath.*;

/**
 * SimpleTextureApp creates a single plane with texture mapping.
 */
public class Texturizado extends Applet {

    BranchGroup crearGrafoEscena() { 
      BranchGroup raiz = new BranchGroup();

      Transform3D transformacion = new Transform3D();

      QuadArray plano = new QuadArray(4, GeometryArray.COORDINATES
                                        | GeometryArray.TEXTURE_COORDINATE_2);

      Point3f p = new Point3f(-1.0f,  1.0f,  0.0f);
      plano.setCoordinate(0, p);
      p.set(-1.0f, -1.0f,  0.0f);
      plano.setCoordinate(1, p);
      p.set(1.0f, -1.0f,  0.0f);
      plano.setCoordinate(2, p);
      p.set(1.0f,  1.0f,  0.0f);
      plano.setCoordinate(3, p);

      Point2f q = new Point2f( 0.0f,  1.0f);
      plano.setTextureCoordinate(0, q);
      q.set(0.0f, 0.0f);
      plano.setTextureCoordinate(1, q);
      q.set(1.0f, 0.0f);
      plano.setTextureCoordinate(2, q);
      q.set(1.0f, 1.0f);
      plano.setTextureCoordinate(3, q);

      Appearance apariencia = new Appearance();

      String imagenTextura = "C:\\Users\\Miguel\\Desktop\\imagen.gif";  
      TextureLoader loader = new TextureLoader(imagenTextura, null);
      ImageComponent2D imagen = loader.getImage();

      if(imagen == null) {
            System.out.println("Carga fallida de la textura: "+imagenTextura);
      }

      Texture2D textura = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA,
                                        imagen.getWidth(), imagen.getHeight());
      textura.setImage(0, imagen);
      //texture.setEnable(false);

      apariencia.setTexture(textura);

      apariencia.setTransparencyAttributes(
           new TransparencyAttributes(TransparencyAttributes.FASTEST, 0.1f));

      Shape3D planoObjeto = new Shape3D(plano, apariencia);
      Sphere esfera=new Sphere();
      raiz.addChild(planoObjeto);

      Background fondo = new Background();
      fondo.setColor(1.0f, 1.0f, 1.0f);
      fondo.setApplicationBounds(new BoundingSphere());
      raiz.addChild(fondo);

      return raiz;
  }

  public Texturizado (){
    setLayout(new BorderLayout());
    GraphicsConfiguration config =
       SimpleUniverse.getPreferredConfiguration();

    Canvas3D canvas3D = new Canvas3D(config);
    add("Center", canvas3D);

    canvas3D.setStereoEnable(false);

    SimpleUniverse u = new SimpleUniverse(canvas3D);

    // This will move the ViewPlatform back a bit so the
    // objects in the scene can be viewed.
    u.getViewingPlatform().setNominalViewingTransform();

    u.addBranchGraph(crearGrafoEscena());
  }
  
  public static void main(String argv[])
  {
      new MainFrame(new Texturizado(), 256, 256);
  }
}


