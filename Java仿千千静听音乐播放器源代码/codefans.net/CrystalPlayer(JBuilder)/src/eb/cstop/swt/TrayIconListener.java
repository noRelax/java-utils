package eb.cstop.swt;
//download:http://www.codefans.net
public interface TrayIconListener {
	public void trayIconPressed(TrayIconEvent e);
	public void trayIconReleased(TrayIconEvent e);
	public void trayIconClicked(TrayIconEvent e);
	public void trayIconDBLClicked(TrayIconEvent e);
}
