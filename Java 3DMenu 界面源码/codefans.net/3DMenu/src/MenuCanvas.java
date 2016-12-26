/*****************************************************************************

 Description: This is the canvas displaying the 3d menu.
 Download by http://www.codefans.net
 Created By: Oscar Vivall 2006-01-09
 @file        MenuCanvas.java

 COPYRIGHT All rights reserved Sony Ericsson Mobile Communications AB 2004.
 The software is the copyrighted work of Sony Ericsson Mobile Communications AB.
 The use of the software is subject to the terms of the end-user license 
 agreement which accompanies or is included with the software. The software is 
 provided "as is" and Sony Ericsson specifically disclaim any warranty or 
 condition whatsoever regarding merchantability or fitness for a specific 
 purpose, title or non-infringement. No warranty of any kind is made in 
 relation to the condition, suitability, availability, accuracy, reliability, 
 merchantability and/or non-infringement of the software provided herein.

 *****************************************************************************/

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import javax.microedition.m3g.*;
import javax.microedition.m3g.Camera;

public class MenuCanvas extends GameCanvas implements Runnable{
    private StarHScroller scroller; // scroll stars on the screen.
    private Alert alert; // display info text on the screen

    private Graphics3D g3d;
    private World world;
    private Mesh mesh;
    private Camera camera;

    // The appearances is used for each menu texture. 
    private Appearance appearance1;
    private Appearance appearance2;
    private Appearance appearance3;
    private Appearance appearance4;

    private final int WIDTH, HEIGHT; // the canvas size

    // the menue options
    private String []menu = new String[] {"Play game", "High scores...", "About...", "Help...", "Options...", "Quit"};
    private final int MENU_SIZE = menu.length;
    private Texture2D []texMenu = new Texture2D[MENU_SIZE];

    private int index = 0; // menu index
    private int face = 0; // cube face 0-3

    // the current angle of the menu.
    private int menu_angle = 0;
    private int menu_rot = 0; // -1 or 1

    private MIDlet midlet;
    
    public MenuCanvas(MIDlet m){
        super(false);
        
        midlet = m;
        
        setFullScreenMode(true);
        
        WIDTH = getWidth();
        HEIGHT = getHeight();

        g3d = Graphics3D.getInstance();

        world = new World();
        camera = new Camera();
        camera.setPerspective(60.0f, (float)WIDTH/(float)HEIGHT, 0.1f, 50.0f);
        world.addChild(camera);
        world.setActiveCamera(camera);

        mesh = MC.createItem(); // create the menu mesh
        mesh.translate(0.0f, -3.0f, -11.0f); // menu position
        world.addChild(mesh);
        appearance1 = mesh.getAppearance(0); // front
        appearance2 = mesh.getAppearance(1); // back
        appearance3 = mesh.getAppearance(4); // top
        appearance4 = mesh.getAppearance(5); // bottom
        
        // generate the menu textures to use.
        createTextures();

        scroller = new StarHScroller(WIDTH, HEIGHT);
        
        new Thread(this).start();
    }

    /*
     * Create one texture for each option in the nenu.
     */
    private void createTextures(){

        Image image;
        try{
            for(int i=0; i<menu.length; i++){
                image = createMenuImage(menu[i]); // create an image from text
                texMenu[i] = new Texture2D(new Image2D(Image2D.RGB, image));
                texMenu[i].setFiltering(Texture2D.FILTER_LINEAR, Texture2D.FILTER_LINEAR);
                texMenu[i].setWrapping(Texture2D.WRAP_CLAMP, Texture2D.WRAP_CLAMP);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        appearance1.setTexture(0, texMenu[0]);
        appearance3.setTexture(0, texMenu[1]);
        appearance4.setTexture(0, texMenu[5]);
    }
    
    /*
     * Create an image from the text sent to the method
     */
    private Image createMenuImage(String imgStr){
        int bgColor = 0xFFFFFF;
        int fgColor = 0x00;
        String menu;
        Font f = Font.getDefaultFont();
        Image image = null;
        
        try{
            Image bgImage = Image.createImage("/menubg.png");
            
            image = Image.createImage(128, 32);
            Graphics g = image.getGraphics();
            g.setFont(f);
            g.setColor(bgColor);
            g.fillRect(0, 0, image.getWidth(), image.getHeight());
            g.drawImage(bgImage, 0, 0, 0);
            g.setColor(fgColor);
            menu = imgStr;
            
            g.drawString(menu, image.getWidth()/2 - f.stringWidth(menu)/2, 7, Graphics.TOP | Graphics.LEFT);
        }catch(Exception e){
            e.printStackTrace();
        }

        return image;
    }
    
    /*
    * when the menu is rotating the textures on the cube is changing.
    */
    private void changeTextures(){
        int front, top, bottom;
        front = top = bottom = 0;
        Appearance f,t,b;

        f = appearance1;
        t = appearance3;
        b = appearance4;
        
        // keep track of which appearance is actually facing the screen.
        switch(face){
            case 0:
                f = appearance1;
                t = appearance3;
                b = appearance4;
                break;
            case 1:
                f = appearance3;
                t = appearance2;
                b = appearance1;
                break;
            case 2:
                f = appearance2;
                t = appearance4;
                b = appearance3;
                break;
            case 3:
                f = appearance4;
                t = appearance1;
                b = appearance2;
                break;
        }

        front = index;
        top = index<MENU_SIZE-1?index+1:0;
        bottom = index > 0? index-1:MENU_SIZE-1;
        
        // set the texture for an appearance
        f.setTexture(0, texMenu[front]);
        t.setTexture(0, texMenu[top]);
        b.setTexture(0, texMenu[bottom]);
    }

    /*
     * The rendering method.
     */
    public void draw3D(Graphics g){
        try{
            g3d.bindTarget(g);
            g3d.render(world);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            g3d.releaseTarget();
        }
    }
    
    /*
     * Used when the fire button is pressed
     */
    private void menuSelect(){
        switch(index){
            case 0:
                // display the text on the screen.
                alert.setText("Start Game");
                alert.start();
                break;
            case 1:
                alert.setText("High scores");
                alert.start();
                break;
            case 2:
                alert.setText("About");
                alert.start();
                break;
            case 3:
                alert.setText("Help");
                alert.start();
                break;
            case 4:
                alert.setText("Options");
                alert.start();
                break;
            case 5: // Exit
                midlet.notifyDestroyed();
                break;
        }
    }

    public void keyPressed(int key){
        System.out.println(key);
        switch(key){
            case -1: // UP
                if(menu_rot==0){
                    index = index > 0?index-1:MENU_SIZE-1;
                    face = face > 0 ? face-1:3;
                    menu_rot = -3;
                }
                break;
            case -2: // DOWN
                if(menu_rot==0){
                    index = index < MENU_SIZE-1 ? index+1:0;
                    face = face <3 ? face+1:0;
                    menu_rot = 3;
                }
                break;
            case -5: // FIRE
                menuSelect();
                break;
        }
        System.out.println("index:" + index);
    }

    public void keyRepeated(int key){
        keyPressed(key);
    }

    public void run(){
        Graphics graphics = this.getGraphics();
        alert = new Alert();

        int exitCounter = 0;
        float exitSpeed = 1.0f;
        while(true){

            // rotate the menu.
            if(menu_rot != 0){
                menu_angle += menu_rot;
                mesh.postRotate(menu_rot, 1.0f, 0.0f, 0.0f);
                if(menu_angle%90==0){
                    menu_rot=0;
                    changeTextures();
                }
            }

            draw3D(graphics);
            
            // draw the alert text
            if(alert.isStarted()){
                alert.draw(graphics);
            }

            scroller.draw(graphics);

            flushGraphics();
            try{
                Thread.sleep(10);
            }catch(Exception e){}
        }
        
    }
    
}
