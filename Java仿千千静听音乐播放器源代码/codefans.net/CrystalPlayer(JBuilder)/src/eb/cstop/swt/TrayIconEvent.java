package eb.cstop.swt;
//download:http://www.codefans.net
public class TrayIconEvent {
	
	private int button = -1;
	private Object source = null;
	private int x = 0;
	private int y = 0;
	
	public int getButton() {
		return button;
	}
	protected void setButton(int button) {
		this.button = button;
	}
	public Object getSource() {
		return source;
	}
	protected void setSource(Object source) {
		this.source = source;
	}
	public int getX() {
		return x;
	}
	protected void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	protected void setY(int y) {
		this.y = y;
	}
}