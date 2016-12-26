package eb.cstop.swing;
//download:http://www.codefans.net
import javax.swing.JTextField;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;

/**
 *
 * <p>Title: 文本框</p>
 * <p>Description: 文本框</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: 冷眼看海</p>
 * @author not attributable
 * @version 1.0
 * @友情下载:CodeFans.net
 */
public class LTextField extends JTextField implements MouseListener,FocusListener,KeyListener,ActionListener{
  private JPopupMenu popup = new JPopupMenu();
  private JMenuItem copy = new JMenuItem(" 复制  ");
  private JMenuItem cut = new JMenuItem(" 剪切  ");
  private JMenuItem paste = new JMenuItem(" 粘贴  ");
  private JMenuItem selectAll = new JMenuItem(" 全选  ");
  private JMenuItem delete = new JMenuItem(" 删除  ");
  private Font font = new Font("宋体",0,12);

  /**
   * 构造函数
   */
  public LTextField() {
    this("");
  }

  /**
   * 构造函数<br>
   * column - 占用列长度
   */
  public LTextField(int column){
    this(column,"");
  }

  /**
   * 构造函数<br>
   * column - 占用列长度<br>
   * text - 初始本文
   */
  public LTextField(int column, String text){
    this(text);
    this.setColumns(column);
  }

  /**
   * 构造函数<br>
   * text - 初始本文
   */
  public LTextField(String text){
    this.setText(text);
    this.addFocusListener(this);
    this.addKeyListener(this);
    this.setSelectionColor(new Color(0,80,140));
    this.setSelectedTextColor(Color.white);
    this.addMouseListener(this);
    copy.setFont(font);
    cut.setFont(font);
    paste.setFont(font);
    selectAll.setFont(font);
    delete.setFont(font);
    copy.addActionListener(this);
    cut.addActionListener(this);
    paste.addActionListener(this);
    selectAll.addActionListener(this);
    delete.addActionListener(this);
    popup.add(cut);
    popup.add(copy);
    popup.add(paste);
    popup.add(delete);
    popup.addSeparator();
    popup.add(selectAll);
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
    if(source == cut)this.cut();
    else if(source == copy)this.copy();
    else if(source == paste)this.paste();
    else if(source == selectAll)this.selectAll();
    else if(source == delete)this.replaceSelection("");
  }

  public void mouseClicked(MouseEvent e) {
    if(e.getButton() == MouseEvent.BUTTON3){
      this.requestFocus();
      if(this.getText().length() < 1){
        copy.setEnabled(false);
        cut.setEnabled(false);
        selectAll.setEnabled(false);
        delete.setEnabled(false);
      }
      else{
        copy.setEnabled(true);
        cut.setEnabled(true);
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
