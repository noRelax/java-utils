//Finishing www.codefans.net

import java.applet.Applet;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import java.awt.BorderLayout;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.behaviors.picking.*;

class box extends Applet
{
	

TransparencyAttributes aTransparencyAttributes;

Appearance createAppearance() {
    Appearance appear=new Appearance();
    //TransparencyAttributes aTransparencyAttributes;
    aTransparencyAttributes=new TransparencyAttributes(2,0.5f);
    aTransparencyAttributes.setCapability(TransparencyAttributes.ALLOW_VALUE_READ);
    //getTransparency(float);
    aTransparencyAttributes.setCapability(TransparencyAttributes.ALLOW_VALUE_WRITE);
    //setTransparency() 
    appear.setTransparencyAttributes(aTransparencyAttributes) ;
    //appear.setCapability(Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_READ);
//appear.getTransparencyAttributes();  
    //appear.setCapability(Appearance.ALLOW_TRANSPARENCY_ATTRIBUTES_WRITE);
//appear.setTransparencyAttributes(aTransparencyAttributes);  

    return appear ;
  }

	
	
	BranchGroup createSceneGraph()
{BranchGroup objRoot=new BranchGroup();
ColorCube cube=new ColorCube(0.4d);
cube.setAppearance(createAppearance());//
objRoot.addChild(cube);
return objRoot;
}


box()
{
setLayout(new BorderLayout());
GraphicsConfiguration config=SimpleUniverse.getPreferredConfiguration();
Canvas3D c=new Canvas3D(config);
add("Center",c);
SimpleUniverse u=new SimpleUniverse(c);
u.getViewingPlatform().setNominalViewingTransform();

u.addBranchGraph(createSceneGraph());
}


public static void main(String aregs[])

{box abox=new box();
	new MainFrame(abox,200,200);
	
	
	abox.aTransparencyAttributes.setTransparency(0.6f);
    
}

}

