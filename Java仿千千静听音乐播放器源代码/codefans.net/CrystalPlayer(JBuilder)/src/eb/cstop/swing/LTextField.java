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
 * <p>Title: �ı���</p>
 * <p>Description: �ı���</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ���ۿ���</p>
 * @author not attributable
 * @version 1.0
 * @��������:CodeFans.net
 */
public class LTextField extends JTextField implements MouseListener,FocusListener,KeyListener,ActionListener{
  private JPopupMenu popup = new JPopupMenu();
  private JMenuItem copy = new JMenuItem(" ����  ");
  private JMenuItem cut = new JMenuItem(" ����  ");
  private JMenuItem paste = new JMenuItem(" ճ��  ");
  private JMenuItem selectAll = new JMenuItem(" ȫѡ  ");
  private JMenuItem delete = new JMenuItem(" ɾ��  ");
  private Font font = new Font("����",0,12);

  /**
   * ���캯��
   */
  public LTextField() {
    this("");
  }

  /**
   * ���캯��<br>
   * column - ռ���г���
   */
  public LTextField(int column){
    this(column,"");
  }

  /**
   * ���캯��<br>
   * column - ռ���г���<br>
   * text - ��ʼ����
   */
  public LTextField(int column, String text){
    this(text);
    this.setColumns(column);
  }

  /**
   * ���캯��<br>
   * text - ��ʼ����
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
