package gui;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;

import tile.Marine;
import tile.Scv;
import tile.Sprite;
import tile.Tile;
import core.Control;
import core.GridMap;
import core.GridMapRender;
import core.ResourceManager;
import core.Control.DragListener;
import core.Control.KeyPressListener;
import core.Control.LeftPressListener;
import core.Control.MoveListener;
import core.Control.RightPressListener;

/**
 * 娓告垙闈㈡澘
 * @author jiangyp
 *
 */
public class GamePanel extends Abstractpanel{
	
	
	GridMap gridmap;

	GridMapRender gridMapRender;
	
	Control control;

	ConsolePanel controlPanel;
	
	Robot robot; 
	 
	public void init(){
		
		initControl();
		gridmap = ResourceManager.resourceManager.getGridMap();
		gridMapRender = gridmap.getTileMapRender();
		gridMapRender.screenWidth = getWidth();
		gridMapRender.screenHeight = getHeight();
		controlPanel = new ConsolePanel(gridMapRender);
		controlPanel.setLocation(getWidth() - controlPanel.getWidth(), 0);
		add(controlPanel);
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		gridMapRender.addMsg("娓╅Θ鎻愮ず锛氭寜鏁板瓧閿�閫変腑鎵�湁鍐滄皯");
		gridMapRender.addMsg("娓╅Θ鎻愮ず锛氭寜鏁板瓧閿�閫変腑鎵�湁鏈烘灙鍏�");
	}
	
	private void initControl(){
		
		GameGUI  gui = (GameGUI) this.getParent();
		control = new Control(this,	gui.frame);
		
		control.addKeyPressListener(new KeyPressListener(){

			public void press(int keyCode) {
				
				if(keyCode==KeyEvent.VK_1){
					gridMapRender.blur();
					for(Tile tile:gridmap.getTiles()){
						
						boolean isScv = (tile.getType()==gridMapRender.getCurrentType())
									&&(tile instanceof Scv);
						if(isScv){
							tile.focus();
							gridMapRender.addFocusSprite((Sprite)tile);
						}
						
					}
				}
				else if(keyCode==KeyEvent.VK_2){
					gridMapRender.blur();
					for(Tile tile:gridmap.getTiles()){
						
						boolean isMarine = tile.getType()==gridMapRender.getCurrentType()
									&&tile instanceof Marine;
						if(isMarine){
							tile.focus();
							gridMapRender.addFocusSprite((Sprite)tile);
						}
						
					}
				}
			}
			
		});
		control.addDragListener(new DragListener(){
			public void drag(int x, int y, int dx, int dy) {
				gridMapRender.focus(x, y, dx, dy);
			}
		});
		control.addLeftPressListener(new LeftPressListener(){
			public void press(int x, int y) {
				//gridMapRender.focus(x, y);
				gridMapRender.operate(x, y);
			}
			
		});
		control.addRightPressListener(new RightPressListener(){
			public void press(int x, int y) {
				gridMapRender.move(x, y);
			}
		});	
		
		control.addMoveListener(new MoveListener(){

			public void move(int x, int y) {
				 
				int offsetX = gridMapRender.offsetX;
				int screenX = getWidth()-GridMapRender.TILE_WIDTH;
				 
				//濡傛灉榧犳爣浣嶄簬灞忓箷瀹藉害鍓嶄竴涓猅ILE瀹藉害
				if(x>screenX&&offsetX<GridMapRender.tileXToPx(107)){
					//鍚戝彸绉诲姩涓�釜tile瀹藉害
					gridMapRender.offsetX=offsetX+GridMapRender.TILE_WIDTH;
					robot.mouseMove(screenX, y);
					++controlPanel.map_panel.x;
				}
				//濡傛灉榧犳爣鍦ㄤ竴涓猅ILE瀹藉害
				if(x<GridMapRender.TILE_WIDTH&&offsetX>0){
					gridMapRender.offsetX=offsetX-GridMapRender.TILE_WIDTH;
					robot.mouseMove(GridMapRender.TILE_WIDTH, y);
					--controlPanel.map_panel.x;
				}
				
				//绾靛潗鏍囧拰姘村钩鍧愭爣閫昏緫鐩稿悓
				int offsetY = gridMapRender.offsetY;
				int screenY = getHeight()-GridMapRender.TILE_HEIGHT;
				
				if(y>screenY&&offsetY<GridMapRender.tileXToPx(110)){
					//鍚戝彸绉诲姩涓�釜tile瀹藉害
					gridMapRender.offsetY=offsetY+GridMapRender.TILE_HEIGHT;
					robot.mouseMove(x, screenY);
					++controlPanel.map_panel.y;
				}
				
				if(y<GridMapRender.TILE_HEIGHT&&offsetY>0){
					gridMapRender.offsetY=offsetY-GridMapRender.TILE_WIDTH;
					robot.mouseMove(x, GridMapRender.TILE_HEIGHT);
					--controlPanel.map_panel.y;
				}
				
				gridMapRender.moveX = x;
				gridMapRender.moveY = y;
				
			}
			
		});
		
		
	}

	public GamePanel(GameGUI parent, String name) {
		super(parent, name);
	}
	
	public void paintComponent(Graphics g) {
		
		gridMapRender.draw((Graphics2D)g);
		//鐢绘帶鍒剁嚎
		control.drag(g);
		
	}
	
	public void update(long elapsedTime) {

		gridMapRender.update(elapsedTime);
		 
	}
}
