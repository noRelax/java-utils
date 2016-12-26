package eb.cstop.swing;
//download:http://www.codefans.net
import javax.swing.JPasswordField;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class LPasswordField extends JPasswordField implements ActionListener,FocusListener,KeyListener,MouseListener{
  private JPopupMenu popup = new JPopupMenu();
  private JMenuItem paste = new JMenuItem(" 粘贴  ");
  private JMenuItem selectAll = new JMenuItem(" 全选  ");
  private JMenuItem delete = new JMenuItem(" 删除  ");
  private Font font = new Font("宋体", 0, 12);


  /**
   * 构造函数<br>
   */
  public LPasswordField() {
    this("");
  }

  /**
   * 构造函数<br>
   * column - 占用列长度<br>
   */
  public LPasswordField(int column){
    this(column,"");
  }

  /**
   * 构造函数<br>
   * column - 占用列长度<br>
   * text - 初始本文
   */
  public LPasswordField(int column, String text){
    this(text);
    this.setColumns(column);
  }

  /**
   * 构造函数<br>
   * text - 初始本文
   */
  public LPasswordField(String text){
    this.setText(text);
    this.addFocusListener(this);
    this.addKeyListener(this);
    this.addMouseListener(this);
    paste.setFont(font);
    selectAll.setFont(font);
    delete.setFont(font);
    paste.addActionListener(this);
    selectAll.addActionListener(this);
    delete.addActionListener(this);
    popup.add(paste);
    popup.add(delete);
    popup.addSeparator();
    popup.add(selectAll);
    this.setSelectionColor(new Color(0,80,140));
    this.setSelectedTextColor(Color.white);
  }

  public void focusGained(FocusEvent e) {
    this.selectAll();
  }
  public void focusLost(FocusEvent e) {}
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_ENTER){
      this.transferFocus();
    }
  }
  public void keyReleased(KeyEvent e) {}
  public void keyTyped(KeyEvent e) {}
  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if(source == paste)this.paste();
    else if(source == selectAll)this.selectAll();
    else if(source == delete)this.replaceSelection("");
  }

  public void mouseClicked(MouseEvent e) {
    if(e.getButton() == MouseEvent.BUTTON3){
      this.requestFocus();
      if(this.getPassword().length < 1){
        selectAll.setEnabled(false);
        delete.setEnabled(false);
      }
      else{
        selectAll.setEnabled(true);
        if(this.getSelectedText() != null)delete.setEnabled(true);
        else delete.setEnabled(false);
      }
      popup.show(this,e.getX(),e.getY());
    }
  }
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
}
