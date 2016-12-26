import java.io.*;
import java.util.*;
import com.nokia.mid.ui.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;
//import com.nokia.mid.sound.Sound;
//import javax.microedition.media.control.VolumeControl;
//Download by http://www.codefans.net
import com.nokia.mid.ui.*;
import java.util.*;
import javax.microedition.media.*;

public class numencanva
    extends FullCanvas {
//  protected final int N3650 = 1;
  protected final int N7650 = 0;

//  protected final int platform
//      = ("nokia3650".equals(System.getProperty("microedition.platform").
//                            toLowerCase())) ? N3650 : N7650;

  //****************************************************************************
   //角色左右方向
   //****************************************************************************
    private final int DIRECT_LEFT = -1;
  private final int DIRECT_RIGH = 1;
  private final int DIRECT_OVER = 2;
  private final int DIRECT_DOWN = 3;
  private final int DIRECT_LEFTOVER = 4;
  private final int DIRECT_LEFTDOWN = 5;
  private final int DIRECT_RIGHOVER = 6;
  private final int DIRECT_RIGHDOWN = 7;

  //****************************************************************************
   //资源索引
   //****************************************************************************
    private final int FACE_MENUCOVER = 0;
  private final int FACE_MENUTITLE = 1; //主菜单中央标题文字
  private final int FACE_FONT_MIDDLE = 2; //数字字体－中等大小
  private final int FACE_FONT_GREATE = 3; //数字字体－最大

  private final int FACE_GAME_OVER = 4; //游戏任务失败的画面
  private final int FACE_TOU1 = 5;
  private final int FACE_TOU2 = 6;
  private final int FACE_ANIMASLOT = 7; //上面的头什么的
  private final int FACE_ANIMASLOT1 = 8; //上面的头2什么的
  private final int FACE_ANIMASLOT2 = 9; //血条
  private final int FACE_ANIMASLOT3 = 10; //血
  private final int FACE_KKX = 11; //卡卡西
  private final int FACE_KUAN = 12; //对话筐
  private final int FACE_ANIMASLOT4 = 13; //生命

//  private final int FACE = 8; //动画3

//
//  private final int FACE_4 = 9; //动画4

//  private final int FACE_MOV1 = 10; //动画1
//  private final int FACE_MOV2 = 11; //动画2

  private final int FACE_PROP_ICON = 14; //道具图标图形
  private final int FACE_JIAN1 = 15; //道具图标图形
  private final int FACE_JIAN2 = 16; //道具图标图形

  // private final int FACE_BIGCANNON = 16; //飞机坦克用的炮管
  // private final int FACE_ZIZOU_GUN = 17; //自走炮的图形

  //****************************************************************************
   /** @todo:构造函数 */
   //*******************************  *********************************************
    Image sp = null;
//  private boolean isEnd1 = false;
//  private EffectFunction effect;
  public numencanva() {
    try {
      logo = Image.createImage("/logo.png");
      sp = Image.createImage("/sp.png");
    }
    catch (IOException ex) {
    }
//    effect = new EffectFunction(176, 208);
    face_load();
    screen_index = -10;

  }

  //****************************************************************************
   /** @todo: 框架代码 */
   //****************************************************************************
    private int screen_index = 0;
  private boolean process_lock = false;
  private final int SCREEN_MAINMENU = 0; //主菜单页面
  private final int SCREEN_GAMEBODY = 1; //游戏页面
  private final int SCREEN_HELPINFO = 2; //帮助页面
  private final int SCREEN_COPYRIGH = 3; //版权信息
  private final int SCREEN_GAMEOVER = 4; //游戏结束
  private final int SCREEN_HIGHLIST = 5; //排行榜
  private final int SCREEN_SELEGATE = 6; //选择关卡
  private final int SCREEN_RESSLOAD = 7; //资源装载页面

  /**********************************************
   *
   **********************************************/
  protected final void process_set(int index) {
    process_lock = true;
    switch (screen_index) {
      case SCREEN_GAMEBODY:
        gamebody_free();
        break;
//      case SCREEN_MAINMENU: mainmenu_free(); break;
//      case SCREEN_HELPINFO: helpinfo_free(); break;
//      case SCREEN_COPYRIGH: copyrigh_free(); break;
//      case SCREEN_GAMEOVER: gameover_free(); break;
//      case SCREEN_HIGHLIST: highlist_free(); break;
//      case SCREEN_RESSLOAD: ressload_free(); break;
//      case SCREEN_SELEGATE: selegate_free(); break;
    }
    screen_index = index;
    switch (index) {
      case SCREEN_GAMEBODY:
        gamebody_init();
        break;
      case SCREEN_MAINMENU:
        mainmenu_init();
        break;
      case SCREEN_HELPINFO:
        helpinfo_init();
        break;
      case SCREEN_COPYRIGH:
        copyrigh_init();
        break;
      case SCREEN_HIGHLIST:
        highlist_init();
        break;
      case SCREEN_SELEGATE:
        selegate_init();
        break;
//      case SCREEN_GAMEOVER: gameover_init(); break;
//      case SCREEN_RESSLOAD: ressload_init(); break;
    }
    process_lock = false;
  }

  /**********************************************
   * 状态处理
   **********************************************/
  int loop = 0;
  Image logo = null;
  public final void process_tick() {
    if (process_lock) {
      return;
    }
    switch (screen_index) {
      case -10:
        if (loop > 20) {
          screen_index = -9;
          logo = null;
          System.gc();
          loop = 0;
          repaint();
        }
        else {
          loop++;
        }

        break;
      case -9:
        if (loop > 20) {
          process_set(SCREEN_MAINMENU);
          sp = null;
          System.gc();
          loop = 0;
        }
        else {
          loop++;
        }

        break;
      case -8:
        tttt = !tttt;
        refresh();
        break;
      case SCREEN_GAMEBODY:
        gamebody_run();
        break;
      case SCREEN_MAINMENU:
        mainmenu_run();
        break;
      case SCREEN_HELPINFO:
        helpinfo_run();
        break;
      case SCREEN_COPYRIGH:
        copyrigh_run();
        break;
      case SCREEN_GAMEOVER:
        gameover_run();
        break;
      case SCREEN_HIGHLIST:
        highlist_run();
        break;
      case SCREEN_RESSLOAD:
        ressload_exec();
        break;
      case SCREEN_SELEGATE:
        selegate_run();
        break;
    }
  }

  /**********************************************
   * 按键处理
   **********************************************/
  protected final void process_key(int keycode) {
    if (process_lock) {
      return;
    }
    switch (screen_index) {
      case -10:

//        process_set(SCREEN_RESSLOAD);
        screen_index = -9;
        repaint();
        logo = null;
        System.gc();
        loop = 0;

        break;
      case -9:

        process_set(SCREEN_MAINMENU);
//        screen_index = -8;
        repaint();
        sp = null;
        System.gc();
        loop = 0;

        break;
      case -8:
        switch (keycode) {
          case KEY_LEFT:

          case KEY_RIGH:
            if (id == 0) {
              id = 1;
            }
            else {
              id = 0;
            }
            refresh();
            break;
          case KEY_FIRE:
          case KEY_SOFT1:
            process_set(SCREEN_RESSLOAD);
            break;
        }

//  mainmenu_execute(mainmenu_index);
//         process_set(SCREEN_RESSLOAD);
        break;
      case SCREEN_GAMEBODY:
        gamebody_key(keycode);
        break;
      case SCREEN_MAINMENU:
        mainmenu_key(keycode);
        break;
      case SCREEN_HELPINFO:
        helpinfo_key(keycode);
        break;
      case SCREEN_COPYRIGH:
        copyrigh_key(keycode);
        break;
      case SCREEN_GAMEOVER:
        gameover_key(keycode);
        break;
      case SCREEN_HIGHLIST:
        highlist_key(keycode);
        break;
      case SCREEN_SELEGATE:
        selegate_key(keycode);
        break;
//      case SCREEN_RESSLOAD: ressload_key(keycode); break;
    }
  }

  /**********************************************
   * 画
   **********************************************/
  boolean tttt = true;
  protected void process_draw() {
    if (process_lock) {
      return;
    }
    switch (screen_index) {
      case -10:
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, 176, 208);
        g.drawImage(logo, 88, 104, g.HCENTER | g.VCENTER);
        // System.out.println("dddddd");
        break;
      case -9:
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, 176, 208);
        g.drawImage(sp, 88, 104, g.HCENTER | g.VCENTER);
        // System.out.println("dddddd");
        break;
      case -8:
        g.setColor(0, 0, 0);
        g.fillRect(0, 0, 176, 208);
        if (id == 0) {
          g.drawImage(faceres[FACE_TOU1], 88, 104, g.HCENTER | g.VCENTER);
        }
        else {
          g.drawImage(faceres[FACE_TOU2], 88, 104, g.HCENTER | g.VCENTER);
        }
        if (tttt) {
          g.drawImage(faceres[FACE_JIAN1], 15, 104, g.LEFT | g.VCENTER);
          g.drawImage(faceres[FACE_JIAN2], 176 - 15 - 16, 104,
                      g.LEFT | g.VCENTER);
        }
        break;
      case SCREEN_INFO:
        message_screen_show();
        break; //全屏信息

      case SCREEN_MAINMENU:
        mainmenu_draw();
        break;
      case SCREEN_HELPINFO:
        helpinfo_draw();
        break;
      case SCREEN_COPYRIGH:
        copyrigh_draw();
        break;
      case SCREEN_GAMEBODY:
        gamebody_draw();
        break;
      case SCREEN_GAMEOVER:
        gameover_draw();
        break;
      case SCREEN_HIGHLIST:
        highlist_draw();
        break;
      case SCREEN_RESSLOAD:
        ressload_draw();
        break;
      case SCREEN_SELEGATE:
        selegate_draw();
        break;
    }
    message_prompt_show();
  }

  //****************************************************************************
   /** @todo: 主要菜单 */
   //****************************************************************************
    private int mainmenu_index = 0; //菜单选择索引
  private int mainmenu_total = 6;
  private final int MAINMENU_STARGAME = 0; //开始新游戏
  private final int MAINMENU_CONTINUE = 1; //游戏设置
  private final int MAINMENU_HELPINFO = 2; //帮助信息
  private final int MAINMENU_COPYRIGH = 4; //版权信息
  private final int MAINMENU_HIGHLIST = 3; //高分榜
  private final int MAINMENU_EXITGAME = 5; //退出游戏

  private final void mainmenu_init() {
    mainmenu_index = 0;
  }

  private boolean plat_exist = false; //地图是否已经被装载
  private final void mainmenu_execute(int index) {
    switch (index) {
      case MAINMENU_STARGAME:
        message_screen("加载中。。。 ");
        process_set(SCREEN_GAMEBODY);
        script_execute(0);
        plat_exist = true;

//        process_set(SCREEN_GAMEBODY);
        playok();
        break;
      case MAINMENU_CONTINUE:
        process_set(SCREEN_SELEGATE);
        break;
      case MAINMENU_HELPINFO:
        process_set(SCREEN_HELPINFO);
        break;
      case MAINMENU_COPYRIGH:
        process_set(SCREEN_COPYRIGH);
        break;
      case MAINMENU_HIGHLIST:
        process_set(SCREEN_HIGHLIST);
        break;
      case MAINMENU_EXITGAME:
        exitapp();
        break;
    }
  }

  private final void mainmenu_run() {
    refresh();
  }

  private final void mainmenu_key(int keycode) {
    if (zi >= 10) {
      switch (keycode) {
        case KEY_DOWN:
          if (mainmenu_index < mainmenu_total - 1) {
            mainmenu_index++;
          }
          refresh();
          break;
        case KEY_OVER:
          if (mainmenu_index > 0) {
            mainmenu_index--;
          }
          refresh();
          break;
        case KEY_FIRE:
        case KEY_SOFT1:
          screen_index = -8;
          refresh();
//          mainmenu_execute(mainmenu_index);
          break;
      }
    }
  }

  int isEnd1 = 0;
  boolean fhui = true;
  int zi = 0;
  private final void mainmenu_draw() {
    String[] menu = {
        "开始游戏", "游戏设置", "游戏帮助", "游戏排行", "游戏关于", "退出游戏"};
    g.setClip(0, 0, screen_w, screen_h);
//    g.setColor(0, 0, 0);
//    g.fillRect(0, 0, 176, 208);
//     System.out.println(mainmenu_index);

    if (zi < 10) {
      g.drawImage(faceres[FACE_MENUCOVER], 0, 0, 0);
      g.drawImage(faceres[FACE_MENUTITLE], 15, 208 - 70, 0);

    }
    else {
      g.drawImage(faceres[FACE_MENUCOVER], 0, 0, 0);
      g.setColor(255, 255, 255);
      g.drawImage(faceres[FACE_JIAN2], 35, 21 + mainmenu_index * 30, 0);
      for (int i = 0; i < menu.length; i++) {
        g.setColor(0);
        g.drawString(menu[i], (screen_w >> 1) - 1,
                     21 + i * 30, 17);
        g.drawString(menu[i], (screen_w >> 1) + 1,
                     21 + i * 30, 17);
        g.drawString(menu[i], screen_w >> 1, 22 + i * 30,
                     17);
        g.drawString(menu[i], screen_w >> 1, 20 + i * 30,
                     17);
        g.setColor(0xFFFFFF);
        g.drawString(menu[i], screen_w >> 1, 21 + i * 30,
                     17);

      }

    }
    if (zi < 10) {
      zi++;
    }
  }

  //****************************************************************************
   /** @todo:游戏菜单 */
   //****************************************************************************
    private int gamemenu_index = 0;
  private boolean gamemenushow = false;
  private boolean gamebodyexit = false;
  private String[] gamemenu_text = {
      "返回游戏", "游戏帮助", "回主菜单"};

  private final void gamemenu_draw() {
    if (!gamemenushow) {
      return;
    }
    g.setClip(0, 0, screen_w, screen_h);
    int x = 30;
    int y = 52;
    int r = 146;
    int b = 100;
    drawframe(x, y, r, b);
    g.setColor(0, 0, 255);
    if ( (gamemenu_index >= 0) && (gamemenu_index < gamemenu_text.length)) {
      g.fillRect(x + 4, y + 4 + 13 * gamemenu_index, r - 8 - x, 13);
    }
    g.setColor(0xffffff);
    for (int i = 0; i < gamemenu_text.length; i++) {
      g.drawString(gamemenu_text[i], screen_w >> 1, y + 4 + 13 * i, 0x11);
    }
  }

  private final void gamemenu_key(int keycode) {
    switch (keycode) {
      case KEY_OVER:
        if (gamemenu_index > 0) {
          gamemenu_index--;
        }
        break;
      case KEY_DOWN:
        if (gamemenu_index < gamemenu_text.length - 1) {
          gamemenu_index++;
        }
        break;
      case KEY_SOFT1:
      case KEY_FIRE:
        switch (gamemenu_index) {
          case 0:
            gamemenushow = false;
            playok();
            break;
          case 1:
            screen_index = SCREEN_HELPINFO;
            break;

          case 2:
            gamebodyexit = true;
            gamemenushow = false;
            record_save_mark(hero_score);
            stop();
            break;
        }
        break;
      case KEY_SOFT2:
        gamemenushow = false;
        playok();
        break;
    }
  }

  //****************************************************************************
   /** @todo:游戏结束 */
   //****************************************************************************
    private final void gameover_run() {
      refresh();
    }

  private final void gameover_key(int keycode) {
    stop();
    process_set(SCREEN_MAINMENU);
  }

  private final void gameover_draw() {
    g.setColor(0);
    g.fillRect(0, 0, screen_w, screen_h);
    g.drawImage(faceres[FACE_GAME_OVER], screen_w >> 1, 45, 17);
  }

  //****************************************************************************
   /** @todo:积分排行 */
   //****************************************************************************
    private final int[] highlist = new int[8];
  private final void highlist_init() {
    System.arraycopy(record_load_mark(), 0, highlist, 0, 8);
  }

  private final void highlist_run() {
    refresh();
  }

  private final void highlist_key(int keycode) {
    switch (keycode) {
      case KEY_SOFT2:
        process_set(SCREEN_MAINMENU);
        break;
    }
  }

  private final void highlist_draw() {
    g.setClip(0, 0, screen_w, screen_h);
    g.drawImage(faceres[FACE_MENUCOVER], 0, 0, 0);
//    g.setColor(0, 0, 0);
//    g.fillRect(0, 0, 176, 208);
    draw_alpharect(0, 0, screen_w, screen_h, (short) 0x6000);
    g.setColor(0xF17D00);
    g.drawString("游戏积分榜", 88, 9, 17);
    g.setColor(0xFFFFFF);
    g.drawString("排名：", 6, 39, 0);
    g.drawString("分数", 133, 39, 0);
    g.setColor(0xFFFC00);
    g.drawLine(0, 57, screen_w, 57);
    g.drawLine(0, 180, screen_w, 180);
    for (int i = 0; i < 8; i++) {
      g.setClip(0, 0, screen_w, screen_h);
      g.setColor(0xF17D00);
      g.drawString( (i + 1) + ".", 6, 64 + i * 14, 0);
      drawString(highlist[i], 156, 66 + i * 14, FONT_ALIGN_RIGH, FONT_MIDDLE); //font_middle_draw(highlist[i], 156, 66 + i * 14, FONT_ALIGN_RIGH);
    }
  }

  //****************************************************************************
   /** @todo:版权信息 */
   //****************************************************************************
    private String[] copyrigh_text = {
        "游戏关于", "", "", "客服电话：", "",
        "客服信箱：", "", "", "内容提供：", "", "", "", ""

    };
  private int copyrigh_line = 0;
  private final void copyrigh_init() {
    copyrigh_line = 0;
  }

  private final void copyrigh_run() {
//    if ( (key_Statuse & KEY_OVER) != 0) {
//      if (copyrigh_line > 0) {
//        copyrigh_line--;
//      }
//    }
//    else if ( (key_Statuse & KEY_DOWN) != 0) {
//      if (copyrigh_line < copyrigh_text.length - 12) {
//        copyrigh_line++;
//      }
//    }
    refresh();
  }

  private final void copyrigh_key(int keycode) {
    switch (keycode) {
      case KEY_SOFT2:
        process_set(SCREEN_MAINMENU);
        break;
    }
  }

  private final void copyrigh_draw() {
    g.setClip(0, 0, screen_w, screen_h);
//    g.setColor(0, 0, 0);
//    g.fillRect(0, 0, 176, 208);
    g.drawImage(faceres[FACE_MENUCOVER], 0, 0, 0);
    g.setColor(0xFFFFFF);
    for (int i = 0; i < 12; i++) {
      g.setColor(0);
      g.drawString(copyrigh_text[copyrigh_line + i], (screen_w >> 1) - 1,
                   12 + 14 * i, 17);
      g.drawString(copyrigh_text[copyrigh_line + i], (screen_w >> 1) + 1,
                   12 + 14 * i, 17);
      g.drawString(copyrigh_text[copyrigh_line + i], screen_w >> 1, 13 + 14 * i,
                   17);
      g.drawString(copyrigh_text[copyrigh_line + i], screen_w >> 1, 11 + 14 * i,
                   17);
      g.setColor(0xFFFFFF);
      g.drawString(copyrigh_text[copyrigh_line + i], screen_w >> 1, 12 + 14 * i,
                   17);
    }
//    g.drawString("上/上翻 下/下翻", 14, 188, 0);
    g.drawString("返回", 134, 188, 0);
  }

  //****************************************************************************
   /** @todo:帮助信息 */
   //****************************************************************************

    private final String[] help_7650 = {
        "　　　　游戏描述",
        "很久以前地上有一个英雄勇",
        "敢面对复活的可怕破坏神。",
        "在漫长的战斗之后，他封印",
        "了破坏神，斗转星移……。",
        "距离那个英雄的时代已经很",
        "远。但是这时突然封印破坏",
        "神的封条毁坏，不祥的预感",
        "隆罩在人们心头。",
        "　　　操作指南",

        "向左移动：　　　 4/左",
        "向右移动：　　　 6/右",
        "出拳：　　　 5/导航键",
        "换子弹：　　　     1",
        "跳跃：　　　　　 */#",
        "发必杀：　　　     0",
    };
  private int helpinfo_line;
  private final void helpinfo_init() {
    helpinfo_line = 0;
  }

  private final void helpinfo_run() {
    String[] help = help_7650;
    if ( (key_Statuse & KEY_OVER) != 0) {
      if (helpinfo_line > 0) {
        helpinfo_line--;
      }
    }
    else if ( (key_Statuse & KEY_DOWN) != 0) {
      if (helpinfo_line < help.length - 12) {
        helpinfo_line++;
      }
    }
    refresh();
  }

  private final void helpinfo_key(int keycode) {
    switch (keycode) {
      case KEY_SOFT2:
        if (gamemenushow) {
          screen_index = SCREEN_GAMEBODY;
        }
        else {
          process_set(SCREEN_MAINMENU);
        }
        break;
    }
  }

  private final void helpinfo_draw() {
    g.setClip(0, 0, screen_w, screen_h);
//    g.setColor(0, 0, 0);
//    g.fillRect(0, 0, 176, 208);
    g.drawImage(faceres[FACE_MENUCOVER], 0, 0, 0);
    g.setColor(0xFFFFFF);
    String[] help = help_7650;
    for (int i = 0; i < 12; i++) {
      g.setColor(0);
      g.drawString(help[helpinfo_line + i], 15, 12 + 14 * i, 0);
      g.drawString(help[helpinfo_line + i], 17, 12 + 14 * i, 0);
      g.drawString(help[helpinfo_line + i], 16, 11 + 14 * i, 0);
      g.drawString(help[helpinfo_line + i], 16, 13 + 14 * i, 0);
      g.setColor(0xFFFFFF);
      g.drawString(help[helpinfo_line + i], 16, 12 + 14 * i, 0);
    }
    g.drawString("上/上翻 下/下翻", 14, 188, 0);
    g.drawString("返回", 134, 188, 0);
  }

  //****************************************************************************
   /** @todo:关卡选择 */
   //****************************************************************************
//    private int selegate_max = 1;
//  private int selegate_index = 1;

    private final void selegate_init() {
//    selegate_max = record_read_mission();
    }

  private final void selegate_draw() {
//    g.setColor(0);
    g.setClip(0, 0, screen_w, screen_h);
//    g.setColor(0, 0, 0);
//    g.fillRect(0, 0, 176, 208);
    g.fillRect(0, 0, screen_w, screen_h);
    g.drawImage(faceres[FACE_MENUCOVER], 0, 0, 0);
    g.setColor(0xFFFFFF);
    g.drawString("游戏设置", 88, 60, g.HCENTER | g.TOP);
    if (temp) {
      g.drawString("声音      开", 88, 90, g.HCENTER | g.TOP);
    }
    else {
      g.drawString("声音      关", 88, 90, g.HCENTER | g.TOP);
    }
    g.setColor(0xBDBDBD);
    g.drawString("确定", 4, 188, 0);
    g.drawString("返回", 140, 188, 0);
  }

  boolean temp = true;
  private final void selegate_key(int keycode) {
    switch (keycode) {
      case KEY_LEFT:
      case KEY_RIGH:
        temp = !temp;
        refresh();
        break;
      case KEY_SOFT2:
        temp = sound;
        process_set(SCREEN_MAINMENU);
        break;
      case KEY_FIRE:
      case KEY_SOFT1:

//        hero_anima = 10;
//        hero_bombcount = 10;
//        hero_being = 3;
//        hero_score = 0;
//        gamemenushow = false;
//        gamebodyexit = false;
//         mission_start(selegate_index);
        sound = temp;
        process_set(SCREEN_MAINMENU);
        //screen_index = SCREEN_GAMEBODY;
        break;
    }
  }

  private void selegate_run() {
    refresh();
  }

  //****************************************************************************
   /** @todo:地图数据 */
   //****************************************************************************
    private static Image[] platres; //图形资源列表
  private static Image[] faceres; //界面用的图形资源

  private final void face_load() {

    InputStream in = stream_create("/game.bin");

    try {

      faceres = new Image[in.read() + (in.read() << 8)];
      skip(in, faceres.length * 2);
      for (int i = 0; i < faceres.length; i++) {
        int len = in.read() + (in.read() << 8);
        byte[] gdata = new byte[len + 6];
        System.arraycopy("GIF89a".getBytes(), 0, gdata, 0, 6);
        in.read(gdata, 6, len);
//        System.out.println(faceres.length);
        faceres[i] = Image.createImage(gdata, 0, gdata.length);

      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //****************************************************************************
   /** @todo: 场景 */
   //****************************************************************************
    private static int platid = 1; //当前的地图索引
  private static int plat_w; //地图网格宽度
  private static int plat_h; //地图网格高度
  private static int plat_n; //右边出口

  private int[][] facts; //基本地物数据
  private int backIndex; //0层及后面的图形的个数

  private final void fact_draw_back() {
    g.setClip(0, 0, screen_w, screen_h);
    int[] fact;
    for (int i = 0; i < backIndex; i++) {
      fact = facts[i];
      if (visible(fact[0], fact[1], fact[2], fact[3])) {
        g.drawImage(platres[fact[5]], fact[0] - position_x,
                    fact[1] - position_y, 0);
      }
    }
  }

  private final void fact_draw_fore() { //地物
    g.setClip(0, 0, screen_w, screen_h);
    int[] fact;
    for (int i = backIndex; i < facts.length; i++) {
      fact = facts[i];
      if (visible(fact[0], fact[1], fact[2], fact[3])) {
        g.drawImage(platres[fact[5]], fact[0] - position_x,
                    fact[1] - position_y, 0);
      }
    }
  }

  private final void fact_load(InputStream in) throws IOException {
    backIndex = readWord(in);
    //  System.out.println(backIndex);
    int total = readWord(in);
    facts = new int[total][6];
    for (int i = 0; i < total; i++) {
      facts[i][0] = readByte(in) * 8;
      facts[i][1] = readByte(in) * 8;
      facts[i][4] = translateRotate(in.read());
      facts[i][5] = in.read();
      if ( (facts[i][4] == 90) || (facts[i][4] == 270)) {
        facts[i][2] = facts[i][0] + platres[facts[i][5]].getHeight();
        facts[i][3] = facts[i][1] + platres[facts[i][5]].getWidth();
      }
      else {
        facts[i][2] = facts[i][0] + platres[facts[i][5]].getWidth();
        facts[i][3] = facts[i][1] + platres[facts[i][5]].getHeight();
      }
    }
  }

  //****************************************************************************
   /** @todo: 地形 */
   //****************************************************************************
    private byte[] grids; //地形数组 每个方格占4BIT

//  private final int CELL_NONE = 0; //空白
  private final int CELL_WALL = 1; //墙壁

//  private final int CELL_ROOF = 2; //平台

  /**********************************************
   * 查询地图上某个格子的作用区属性
   * @param x int 场景上某格的X坐标
   * @param y int 场景上某格的Y坐标
   * @return int  (X,Y)所表示格子的作用区值 如果(X,Y)的位置超出地图范围-1
   **********************************************/
  private final int grid_cellvalue(int x, int y) {
    if ( (x < 0) || (x >= plat_w) || (y < 0) || (y >= plat_h)) {
      return -1;
    }
    int v = x + y * plat_w;
    return ( (v & 1) == 1) ? grids[v >> 1] >> 4 : grids[v >> 1] & 0x0f;
  }

  private final void grid_load(InputStream in) throws IOException {
    grids = new byte[ (plat_h * plat_w) >> 1];
    in.read(grids);
  }

  //****************************************************************************
   /** @todo: 背景 */
   //****************************************************************************
    private int background1_face_w;
  private int background1_face_h;
  private int background1_face_index = -1;

  private int background2_face_y;
  private int background2_face_w;
  private int background2_speed;
  private int background2_face_index = -1;
  private int background2_position_x;

  private int background3_face_y;
  private int background3_face_w;
  private int background3_speed;
  private int background3_face_index = -1;
  private int background3_position_x;

  /**********************************************
   * 初始化游戏背景
   * @param in InputStream 地图文件流
   * @throws IOException IO异常
   **********************************************/
  private final void back_load(InputStream in) throws IOException {
    int bg1 = readByte(in);
    int bg2 = readByte(in);
    int bg3 = readByte(in);
    int bg2_top = readWord(in);
    int bg3_top = readWord(in);
    int bg2_spd = readByte(in);
    int bg3_spd = readByte(in);
    if ( (bg1 >= 0) && (bg1 < platres.length)) {
      background1_face_index = bg1;
      background1_face_w = platres[bg1].getWidth();
      background1_face_h = platres[bg1].getHeight();
    }
    else {
      background1_face_index = -1;
    }
    if ( (bg2 >= 0) && (bg2 < platres.length)) {
      background2_face_index = bg2;
      background2_face_y = bg2_top;
      background2_face_w = platres[bg2].getWidth();
      background2_position_x = 0;
      background2_speed = bg2_spd;
    }
    else {
      background2_face_index = -1;
    }
    if ( (bg3 >= 0) && (bg3 < platres.length)) {
      background3_face_index = bg3;
      background3_face_y = bg3_top;
      background3_face_w = platres[bg3].getWidth();
      background3_position_x = 0;
      background3_speed = bg3_spd;
    }
    else {
      background3_face_index = -1;
    }
  }

  /**********************************************
   * 画背景
   **********************************************/
  private final void back_draw() {
    int x, y;
    g.setClip(0, 0, screen_w, screen_h);
    if (background1_face_index >= 0) {
      y = -position_y % background1_face_h;
      while (y < screen_h) {
        x = -position_x % background1_face_w;
        while (x < screen_w) {
          g.drawImage(platres[background1_face_index], x, y, 0);
          x += background1_face_w;
        }
        y += background1_face_h;
      }
    }
    else {
      if (background3_face_index >= 0) {
        x = -background3_position_x % background3_face_w;
        while (x < screen_w) {
          g.drawImage(platres[background3_face_index], x, background3_face_y, 0);
          x += background3_face_w;
        }
      }
      if (background2_face_index >= 0) {
        x = -background2_position_x % background2_face_w;
        while (x < screen_w) {
          g.drawImage(platres[background2_face_index], x, background2_face_y, 0);
          x += background2_face_w;
        }
      }
    }
  }

  /**********************************************
   * 运行背景
   **********************************************/
  private final void back_run() {
    if (background3_face_index >= 0) {
      if (background3_speed != 0) {
        background3_position_x += background3_speed;
      }
      else {
        background3_position_x = position_x >> 3;
      }
    }
    if (background2_face_index >= 0) {
      if (background2_speed != 0) {
        background2_position_x += background2_speed;
      }
      else {
        background2_position_x = position_x >> 2;
      }
    }
  }

  //****************************************************************************
   /** @todo: 道具 */
   //****************************************************************************
    private Vector props = new Vector();
  private final int PROP_POS_W = 16;
  private final int PROP_POS_H = 16;

  private final int PROP_COUNT = 7;
  private final int PROP_POS_X = 0;
  private final int PROP_POS_Y = 1;
  private final int PROP_INDEX = 2;
  private final int PROP_TOTAL = 3;

  private final void prop_create(int pos_x, int pos_y, int prop_index,
                                 int prop_total) {
    int[] prop = new int[PROP_COUNT];
    prop[PROP_POS_X] = pos_x;
    prop[PROP_POS_Y] = pos_y;
    prop[PROP_INDEX] = prop_index;
    prop[PROP_TOTAL] = prop_total;
    props.addElement(prop);
  }

  private final void prop_run() {
    for (int i = props.size() - 1; i >= 0; i--) {
      int[] prop = (int[]) props.elementAt(i);
      int v = grid_cellvalue(prop[PROP_POS_X] >> 3, prop[PROP_POS_Y] >> 3);
      if ( (v == 1) || (v == 2)) {
        prop[PROP_POS_Y] = (prop[PROP_POS_Y] >> 3) << 3;
      }
      else {
        prop[PROP_POS_Y] += 4;
      }
    }
  }

  private final void prop_draw() {
    for (int i = props.size() - 1; i >= 0; i--) {
      int[] prop = (int[]) props.elementAt(i);
      clipRect(prop[PROP_POS_X] - (PROP_POS_W >> 1),
               prop[PROP_POS_Y] - PROP_POS_H,
               PROP_POS_W, prop[PROP_POS_Y]);
      drawFace(faceres[FACE_PROP_ICON],
               prop[PROP_POS_X] - prop[PROP_INDEX] * PROP_POS_W -
               (PROP_POS_W >> 1),
               prop[PROP_POS_Y] - PROP_POS_H, 0);
    }
  }

  //****************************************************************************
   /** @todo:俘虏 */
   //****************************************************************************
//    private final int PAWN_FACEW = 25;
//  private final int PAWN_FACEH = 28;
    private final int PAWN_HALFW = 12;
  private final int PAWN_HALFH = 14;

  private Vector pawns = new Vector();
  private final int PAWN_COUNT = 10;
  private final int PAWN_RESID = 0;
  private final int PAWN_POS_X = 1;
  private final int PAWN_POS_Y = 2;
  private final int PAWN_INDEX = 3; //携带的道具的索引
  private final int PAWN_TOTAL = 4; //道具数目 对于加分道具这个是分数 对于弹药这个是弹药数量
  private final int PAWN_STATE = 5; //俘虏当前的状态
  private final int PAWN_DIREC = 6;
  private final int PAWN_FRAME = 7;
  private final int PAWN_FLASH = 8;
  private final int PAWN_TALLY = 9;

//  private final int PAWN_STATE_HOLD = 0; //绑缚状态
//  private final int PAWN_STATE_STAN = 1; //站立状态
//  private final int PAWN_STATE_FADE = 2; //消失状态

  private final void pawn_rescue(int[] pawn) {
    if (pawn[PAWN_STATE] == 0) {
      pawn[PAWN_STATE] = 1;
      pawn[PAWN_FRAME] = 1;
    }
  }

  private final void pawn_load(InputStream in) throws IOException {
    for (int i = in.read() - 1; i >= 0; i--) {
      int[] pawn = new int[PAWN_COUNT];
      pawn[PAWN_RESID] = in.read();
      pawn[PAWN_POS_X] = readByte(in) * 8;
      pawn[PAWN_POS_Y] = readByte(in) * 8;
      pawn[PAWN_INDEX] = in.read();
      pawn[PAWN_TOTAL] = in.read();
      pawn[PAWN_STATE] = 0;
      pawn[PAWN_DIREC] = -1;
      pawn[PAWN_FRAME] = 0;
      pawn[PAWN_FLASH] = 0;
      pawn[PAWN_TALLY] = 0;
      pawns.addElement(pawn);
    }
  }

//  private final void pawn_run() {
//    int v;
//    for (int i = pawns.size() - 1; i >= 0; i--) {
//      int[] pawn = (int[]) pawns.elementAt(i);
//      switch (pawn[PAWN_STATE]) {
//        case PAWN_STATE_HOLD:
//          v = grid_cellvalue(pawn[PAWN_POS_X] >> 3,
//                             (pawn[PAWN_POS_Y] + PAWN_HALFH) >> 3);
//          if ( (v != 1) && (v != 2)) {
//            pawn[PAWN_POS_Y] += 4;
//          }
//          pawn[PAWN_DIREC] = (pawn[PAWN_POS_X] > hero_pos_x) ? -1 : 1;
//          break;
//        case PAWN_STATE_STAN:
//          if (pawn[PAWN_FRAME] < 2) {
//            if (pawn[PAWN_TALLY]++ > 2) {
//              pawn[PAWN_FRAME]++;
//              pawn[PAWN_TALLY] = 0;
//              prop_create(pawn[PAWN_POS_X], pawn[PAWN_POS_Y], pawn[PAWN_INDEX],
//                          pawn[PAWN_TOTAL]);
//            }
//          }
//          else {
//            pawn[PAWN_STATE]++;
//          }
//          break;
//        case PAWN_STATE_FADE:
//          if (pawn[PAWN_FLASH]++ > 20) {
//            pawns.removeElementAt(i);
//          }
//          break;
//      }
//    }
//  }

//  private final void pawn_draw() {
//    for (int i = pawns.size() - 1; i >= 0; i--) {
//      int[] pawn = (int[]) pawns.elementAt(i);
//      if ( (pawn[PAWN_STATE] == PAWN_STATE_FADE) &&
//          ( (pawn[PAWN_FLASH] & 2) == 2)) {
//        continue;
//      }
//      clipRect(pawn[PAWN_POS_X] - PAWN_HALFW, pawn[PAWN_POS_Y] - PAWN_HALFH,
//               PAWN_FACEW, PAWN_FACEH);
//      switch (pawn[PAWN_DIREC]) {
//        case -1:
//          drawFace(platres[pawn[PAWN_RESID]],
//                   pawn[PAWN_POS_X] - PAWN_HALFW -
//                   pawn[PAWN_FRAME] * PAWN_FACEW,
//                   pawn[PAWN_POS_Y] - PAWN_HALFH, 0);
//          break;
//        case 1:
//          drawFace(platres[pawn[PAWN_RESID]],
//                   pawn[PAWN_POS_X] + PAWN_HALFW +
//                   pawn[PAWN_FRAME] * PAWN_FACEW - 3 * PAWN_FACEW,
//                   pawn[PAWN_POS_Y] - PAWN_HALFH, 0x2000);
//          break;
//      }
//    }
//  }

  //****************************************************************************
   /** @todo:箱子 */
   //****************************************************************************
    private Vector chest = new Vector();
  private final int CHEST_COUNT = 9;
  private final int CHEST_RESID = 0;
  private final int CHEST_POS_X = 1;
  private final int CHEST_POS_Y = 2;
  private final int CHEST_POS_W = 3;
  private final int CHEST_POS_H = 4;
  private final int CHEST_BLAST = 5; //爆炸效果
  private final int CHEST_PROPS = 6; //道具的类型
  private final int CHEST_TOTAL = 7; //道具加的分 或者 子弹个数
  private final int CHEST_ANIMA = 8; //箱子的类型

  private final boolean chet_include(int[] chet, int x, int y) {
    return
        (x >= chet[CHEST_POS_X]) &&
        (y >= chet[CHEST_POS_Y]) &&
        (x <= chet[CHEST_POS_X] + chet[CHEST_POS_W]) &&
        (y <= chet[CHEST_POS_Y] + chet[CHEST_POS_H]);
  }

  private final void chet_load(InputStream in) throws IOException {
    int size_chet = in.read();
    for (int i = 0; i < size_chet; i++) {
      int[] chet = new int[CHEST_COUNT];
      chet[CHEST_RESID] = in.read();
      chet[CHEST_POS_X] = readByte(in) * 8;
      chet[CHEST_POS_Y] = readByte(in) * 8;
      chet[CHEST_ANIMA] = in.read();
      chet[CHEST_PROPS] = readByte(in);
      chet[CHEST_TOTAL] = in.read();
      chet[CHEST_BLAST] = readByte(in);
      chet[CHEST_POS_W] = platres[chet[CHEST_RESID]].getWidth();
      chet[CHEST_POS_H] = platres[chet[CHEST_RESID]].getHeight();
      chest.addElement(chet);
    }
  }

  private final void chet_run() {
    boolean touch = false;
    for (int i = chest.size() - 1; i >= 0; i--) {
      touch = false;
      int[] chet = (int[]) chest.elementAt(i);
      int chet_l = chet[CHEST_POS_X] + 3;
      int chet_r = chet[CHEST_POS_X] + chet[CHEST_POS_W] - 3;
      int chet_y = chet[CHEST_POS_Y] + chet[CHEST_POS_H] + 4;
      int v1 = grid_cellvalue(chet_l >> 3, chet_y >> 3);
      int v2 = grid_cellvalue(chet_r >> 3, chet_y >> 3);
      if ( (v1 == 1) || (v2 == 1) || (v1 == 2) || (v2 == 2)) {
        chet[CHEST_POS_Y] = ( (chet_y >> 3) << 3) - chet[CHEST_POS_H];
        continue;
      }
      for (int j = chest.size() - 1; j >= 0; j--) {
        if (i == j) {
          continue;
        }
        int[] chet_v = (int[]) chest.elementAt(j);
        if (chet_include(chet_v, chet_l, chet_y) ||
            chet_include(chet_v, chet_r, chet_y) ||
            chet_include(chet_v, (chet_l + chet_r) >> 1, chet_y)) {
          chet[CHEST_POS_Y] = chet_v[CHEST_POS_Y] - chet[CHEST_POS_H];
          touch = true;
        }
      }
      if (!touch) {
        chet[CHEST_POS_Y] += 4;
      }
    }
  }

  private final void chet_hitted(int[] chet, int attack) {
    chet[CHEST_ANIMA] -= attack;
    int center_x = ( (chet[CHEST_POS_X] << 1) + chet[CHEST_POS_W]) >> 1;
    int center_y = ( (chet[CHEST_POS_Y] << 1) + chet[CHEST_POS_H]) >> 1;
    if (chet[CHEST_ANIMA] > 0) {
      return;
    }
    if (chet[CHEST_BLAST] >= 0) {
      movi_create(chet[CHEST_BLAST], center_x, center_y, 0, 0, 0, 0, -1,
                  MOVI_DEATH_ONCE);
    }
    if (chet[CHEST_PROPS] >= 0) {
      prop_create(center_x, center_y, chet[CHEST_PROPS], chet[CHEST_TOTAL]);
    }
    chest.removeElement(chet);
  }

  private final void chet_draw() {
    g.setClip(0, 0, screen_w, screen_h);
    for (int i = chest.size() - 1; i >= 0; i--) {
      int[] chet = (int[]) chest.elementAt(i);
      int x = chet[CHEST_POS_X];
      int y = chet[CHEST_POS_Y];
      if (visible(x, y, x + chet[CHEST_POS_W], y + chet[CHEST_POS_H])) {
        drawFace(platres[chet[CHEST_RESID]], chet[CHEST_POS_X],
                 chet[CHEST_POS_Y], 0);
      }
    }
  }

  //****************************************************************************
   /** @todo:动画 */
   //****************************************************************************
    private Vector movis = new Vector(); //动画元件数据
  private int[][] movis_defin;
  private Image[] movis_image;

  private final int MOVI_DEFIN_COUNT = 5;
  private final int MOVI_DEFIN_FRAME = 0;
  private final int MOVI_DEFIN_LINKX = 1;
  private final int MOVI_DEFIN_LINKY = 2;
  private final int MOVI_DEFIN_FRAMW = 3;
  private final int MOVI_DEFIN_FRAMH = 4;

  private final int MOVI_COUNT = 18;
  private final int MOVI_DEFID = 0; //图形索引
  private final int MOVI_POS_X = 1; //动画位置X
  private final int MOVI_POS_Y = 2; //动画位置Y
  private final int MOVI_FRAML = 3; //连接点到帧左边的距离
  private final int MOVI_FRAMT = 4; //连接点到帧上边的距离
  private final int MOVI_FRAMR = 5; //连接点到帧右边的距离
  private final int MOVI_FRAMB = 6; //连接点到帧下边的距离

  private final int MOVI_FACEW = 7;
  private final int MOVI_FRAMH = 8;
  private final int MOVI_FRAMW = 9;
  private final int MOVI_FRAME = 10; //动画帧数
  private final int MOVI_INDEX = 11; //当前帧索引
  private final int MOVI_SPD_X = 12;
  private final int MOVI_SPD_Y = 13;
  private final int MOVI_ACCEX = 14;
  private final int MOVI_ACCEY = 15;
  private final int MOVI_DIREC = 16;
  private final int MOVI_DEATH = 17;

  private final int MOVI_DEATH_ONCE = 0;
  private final int MOVI_DEATH_EVER = 1;
  private final int MOVI_DEATH_SIDE = 2;

  private final void movi_loaddefine() {
    InputStream in = stream_create("/movi.bin");
    try {
      movis_defin = new int[in.read()][MOVI_DEFIN_COUNT];
      movis_image = new Image[movis_defin.length];
      for (int i = 0; i < movis_defin.length; i++) {
        movis_image[i] = platres[in.read()];
        movis_defin[i][MOVI_DEFIN_FRAME] = in.read();
        movis_defin[i][MOVI_DEFIN_LINKX] = in.read();
        movis_defin[i][MOVI_DEFIN_LINKY] = in.read();
        movis_defin[i][MOVI_DEFIN_FRAMW] = movis_image[i].getWidth() /
            movis_defin[i][MOVI_DEFIN_FRAME];
        movis_defin[i][MOVI_DEFIN_FRAMH] = movis_image[i].getHeight();
      }
    }
    catch (Exception e) {}
  }

  private final void movi_load(InputStream in) throws IOException {
    for (int i = in.read() - 1; i >= 0; i--) {
      movi_create(in.read(), readByte(in) * 8, readByte(in) * 8, 0, 0, 0, 0, -1,
                  MOVI_DEATH_EVER);
    }
  }

  private final void movi_create(int movi_index, int pos_x, int pos_y,
                                 int spd_x,
                                 int spd_y, int accelx, int accely, int direct,
                                 int death) {
    int[] movi = new int[MOVI_COUNT];
    movi[MOVI_DEFID] = movi_index;
    movi[MOVI_POS_X] = pos_x;
    movi[MOVI_POS_Y] = pos_y;

    movi[MOVI_FRAML] = movis_defin[movi_index][MOVI_DEFIN_LINKX];
    movi[MOVI_FRAMT] = movis_defin[movi_index][MOVI_DEFIN_LINKY];
    movi[MOVI_FRAMR] = movis_defin[movi_index][MOVI_DEFIN_FRAMW] -
        movis_defin[movi_index][MOVI_DEFIN_LINKX];
    movi[MOVI_FRAMB] = movis_defin[movi_index][MOVI_DEFIN_FRAMH] -
        movis_defin[movi_index][MOVI_DEFIN_LINKY];

    movi[MOVI_FACEW] = movis_image[movi_index].getWidth();
    movi[MOVI_FRAMH] = movis_image[movi_index].getHeight();
    movi[MOVI_FRAME] = movis_defin[movi_index][MOVI_DEFIN_FRAME];
    movi[MOVI_FRAMW] = movis_defin[movi_index][MOVI_DEFIN_FRAMW];
    movi[MOVI_INDEX] = 0;
    movi[MOVI_SPD_X] = spd_x;
    movi[MOVI_SPD_Y] = spd_y;
    movi[MOVI_ACCEX] = accelx;
    movi[MOVI_ACCEY] = accely;
    movi[MOVI_DIREC] = direct;
    movi[MOVI_DEATH] = death;
    movis.addElement(movi);
  }

  private final void movi_run() {
    for (int i = movis.size() - 1; i >= 0; i--) {
      int[] movi = (int[]) movis.elementAt(i);
      int movi_l = movi[MOVI_POS_X] -
          ( (movi[MOVI_DIREC] == -1) ? movi[MOVI_FRAML] : movi[MOVI_FRAMR]);
      int movi_t = movi[MOVI_POS_Y] - movi[MOVI_FRAMT];
      int movi_r = movi[MOVI_POS_X] +
          ( (movi[MOVI_DIREC] == -1) ? movi[MOVI_FRAMR] : movi[MOVI_FRAML]);
      int movi_b = movi[MOVI_POS_Y] + movi[MOVI_FRAMB];
      switch (movi[MOVI_DEATH]) {
        case MOVI_DEATH_ONCE:
          if (movi[MOVI_INDEX] == movi[MOVI_FRAME] - 1) {
            movis.removeElementAt(i);
          }
          break;
        case MOVI_DEATH_SIDE:
          if (!visible(movi_l, movi_t, movi_r, movi_b)) {
            movis.removeElementAt(i);
          }
          break;
      }
      movi[MOVI_POS_X] += movi[MOVI_SPD_X] / 100;
      movi[MOVI_POS_Y] += movi[MOVI_SPD_Y] / 100;
      movi[MOVI_SPD_X] += movi[MOVI_ACCEX];
      movi[MOVI_SPD_Y] += movi[MOVI_ACCEY];
      movi[MOVI_INDEX] = (movi[MOVI_INDEX] < movi[MOVI_FRAME] - 1) ?
          movi[MOVI_INDEX] + 1 : 0;
    }
  }

  private final void movi_draw() {
    //画动画物体
    for (int i = movis.size() - 1; i >= 0; i--) {
      int[] movi = (int[]) movis.elementAt(i);
      int movi_l = movi[MOVI_POS_X] -
          ( (movi[MOVI_DIREC] == -1) ? movi[MOVI_FRAML] : movi[MOVI_FRAMR]);
      int movi_t = movi[MOVI_POS_Y] - movi[MOVI_FRAMT];
      int movi_r = movi[MOVI_POS_X] +
          ( (movi[MOVI_DIREC] == -1) ? movi[MOVI_FRAMR] : movi[MOVI_FRAML]);
      int movi_b = movi[MOVI_POS_Y] + movi[MOVI_FRAMB];
      int draw_x = (movi[MOVI_DIREC] == -1) ?
          movi_l - movi[MOVI_INDEX] * movi[MOVI_FRAMW] :
          movi_r + movi[MOVI_INDEX] * movi[MOVI_FRAMW] - movi[MOVI_FACEW];
      if (visible(movi_l, movi_t, movi_r, movi_b)) {
        clipRect(movi_l, movi_t, movi[MOVI_FRAMW], movi[MOVI_FRAMH]);
        g.drawImage(movis_image[movi[MOVI_DEFID]],
                    draw_x - position_x, movi_t - position_y,
                    (movi[MOVI_DIREC] == -1) ? 0 : 0x2000);
      }
    }
  }

  /**********************************************
   * 转换图形翻转属性
   * @param rotate int 地图中用到的翻转数据
   * @return int nokia j2me api中的图形翻转数据
   **********************************************/
  private int translateRotate(int rotate) {
    switch (rotate) {
      case 1:
        return 0x2000;
      case 2:
        return 0x4000;
      case 3:
        return 90;
      case 4:
        return 180;
      case 5:
        return 270;
      default:
        return 0;
    }
  }

  //****************************************************************************
   // 游戏中的文字提示信息
   //****************************************************************************
//    private String message_prompt = null;
//  private final void message_prompt(String msg) {
//    message_prompt = msg;
//    refresh();
//  }

//  private final void message_prompt_show() {
//    if (message_prompt == null) {
//      return;
//    }
//    drawframe(15, 54, screen_w - 15, 74);
//    g.setColor(0xFFFFFF);
//    g.drawString(message_prompt, screen_w >> 1, 57, 0x11);
//  }

    /**********************************************
     * 主角被怪物攻击
     * @param attack 怪物的攻击力
     **********************************************/
    private final void hero_hitted(int attack) {
      if (hero_cflash > 0) {
        return;
      }
      if (attack <= 0) {
        return;
      }
      hero_anima--;
      hero_cflash = 10;
      if (hero_anima <= 0) {
        hero_setfirestatus(STATUS_FIRE_DEATHS);
        hero_setaction(HERO_ACTION_SHOTDEAD);
      }
    }

  /**********************************************
   * 判断主角是否和一个矩形发生碰撞
   * @param x int
   * @param y int
   * @param r int
   * @param b int
   * @return boolean
   **********************************************/
  private final boolean hero_nexus(int x, int y, int r, int b) {
    int[] fram = heroaction[hero_action][hero_cframe];
    return! ( (x > hero_pos_x + fram[HERO_FRAME_RECTW]) ||
             (r < hero_pos_x - fram[HERO_FRAME_RECTW]) ||
             (y > hero_pos_y + hero_frectb) ||
             (b < hero_pos_y - fram[HERO_FRAME_RECTT]));
  }

  //****************************************************************************
   /** @todo: 主角 */
   //****************************************************************************
    private Image hero_face; //当前主角图形资源
  private int hero_pos_x; //主角位置X
  private int hero_pos_y; //主角位置Y
  private int hero_old_x; //主角上次站立位置X
  private int hero_old_y; //主角上次站立位置Y
  private int hero_anima = 32; //生命值
  private int hero_attak = 10;
  private int hero_being = 3; //生命次数
  private int hero_score = 0; //得分
  private int hero_spd_y = 0; //垂直速度 速度数值为实际位移量的10倍
  private int hero_style = 0; //主角的样式：人物，飞机，坦克

  private int hero_shot_type = 0; //子弹类型
  private int hero_shotcount = 0; //子弹数量
  private int hero_shottally = 0; //子弹延时计数
  private int hero_bombcount = 0; //炸弹数量
  private int hero_bombtally = 0; //炸弹投掷延时
  private int HERO_BOMBTALLY = 5; //投弹延迟常数

//  private int hero_add_being = 0; //根据分数添加的人数0
  private boolean hero_death = false; //如果主角完成了死亡动作 该标记被设为true 用于新出现主角

  private final int SHOT_TYPE_TOMM = 0; //机枪
  private final int SHOT_TYPE_MISS = 1; //导弹

  private int SPEED_HORZ_ACCEL = 60; //水平方向加速度
  private int SPEED_VERT_ACCEL = 50; //垂直方向加速度
  private int SPEED_VERT_START = -220; //起跳初速度
  private int SPEED_VERT_LIMIT = 200; //垂直方向最大速度

  private int hero_direct = 1; //主角动作的方向 -1:向左 1:向右
  private int hero_cflash;
  private int hero_status;
  private int hero_action;
  private int hero_cframe;
  private int hero_frectw;
  private int hero_frectt;

//  private int hero_fire_count = 0;
  private final int hero_frectb = 11;

  //主角状态常数 分两部分 字节低位大状态 高位字节小状态
  private final int STATUS_MAIN = 0xF000;
  private final int STATUS_MAIN_STAND = 0x0000;
  private final int STATUS_MAIN_SQUAT = 0x1000;
  private final int STATUS_MAIN_INAIR = 0x2000;

  private final int STATUS_FIRE = 0xF00;
  private final int STATUS_FIRE_NORMAL = 0x000;
  private final int STATUS_FIRE_ATTACK = 0x100;
  private final int STATUS_FIRE_SWORDS = 0x200;
  private final int STATUS_FIRE_THROWS = 0x300;
  private final int STATUS_FIRE_DEATHS = 0x400;

  private final int STATUS_MOVE = 0xF0;
  private final int STATUS_MOVE_REST = 0x00;
  private final int STATUS_MOVE_MOVE = 0x10;
  private final int STATUS_MOVE_RISE = 0x20;
  private final int STATUS_MOVE_LEAP = 0x30;
  private final int STATUS_MOVE_FALL = 0x40;

  private final int STATUS_PORT = 0xF;
  private final int STATUS_PORT_BASE = 0x0;
  private final int STATUS_PORT_OVER = 0x1;
  private final int STATUS_PORT_DOWN = 0x2;

  //主角动作数据 第一维是表示动作索引 第二维表示帧索引 第三维帧定义
  private int[][][] heroaction;
  private final int HERO_ACTION_STANREST = 0;
  private final int HERO_ACTION_STANMOVE = 1;
  private final int HERO_ACTION_STANFALCHION = 2;

  private final int HERO_ACTION_STANTOSSBOMB = 3;

  private final int HERO_ACTION_STANFIREBASE = 4;

//  private final int HERO_ACTION_STANFIREOVER = 5;
//  private final int HERO_ACTION_STANPORTOVER = 6;

//  private final int HERO_ACTION_MOVEFALCHION = 7;
//  private final int HERO_ACTION_MOVETOSSBOMB = 8;
//  private final int HERO_ACTION_MOVEFIREBASE = 9;
//  private final int HERO_ACTION_MOVEFIREOVER = 10;
//  private final int HERO_ACTION_MOVEPORTOVER = 11;

  private final int HERO_ACTION_SQUAREST = 5;

  private final int HERO_ACTION_SQUAMOVE = 6;
  private final int HERO_ACTION_SQUAFALCHION = 7;

//  private final int HERO_ACTION_SQUATOSSBOMB = 15;
//  private final int HERO_ACTION_SQUAFIRE = 8;

  private final int HERO_ACTION_JUMPRISE = 8;
  private final int HERO_ACTION_RISEFALCHION = 9;

//  private final int HERO_ACTION_RISETOSSBOMB = 19;
//  private final int HERO_ACTION_RISEFIREBASE = 20;
//  private final int HERO_ACTION_RISEFIREOVER = 21;
//  private final int HERO_ACTION_RISEFIREDOWN = 22;
//  private final int HERO_ACTION_RISEPORTOVER = 23;
//  private final int HERO_ACTION_RISEPORTDOWN = 24;

  private final int HERO_ACTION_JUMPLEAP = 10;

  private final int HERO_ACTION_LEAPFALCHION = 9;

//  private final int HERO_ACTION_LEAPTOSSBOMB = HERO_ACTION_RISETOSSBOMB;
//  private final int HERO_ACTION_LEAPFIREBASE = HERO_ACTION_RISEFIREBASE;
//  private final int HERO_ACTION_LEAPFIREOVER = HERO_ACTION_RISEFIREOVER;
//  private final int HERO_ACTION_LEAPFIREDOWN = HERO_ACTION_RISEFIREDOWN;
//  private final int HERO_ACTION_LEAPPORTOVER = HERO_ACTION_RISEPORTOVER;
//  private final int HERO_ACTION_LEAPPORTDOWN = HERO_ACTION_RISEPORTDOWN;

  private final int HERO_ACTION_JUMPFALL = 11;

  private final int HERO_ACTION_FALLFALCHION = 9;

//  private final int HERO_ACTION_FALLTOSSBOMB = HERO_ACTION_RISETOSSBOMB;
//  private final int HERO_ACTION_FALLFIREBASE = HERO_ACTION_RISEFIREBASE;
//  private final int HERO_ACTION_FALLFIREOVER = HERO_ACTION_RISEFIREOVER;
//  private final int HERO_ACTION_FALLFIREDOWN = HERO_ACTION_RISEFIREDOWN;
//  private final int HERO_ACTION_FALLPORTOVER = HERO_ACTION_RISEPORTOVER;
//  private final int HERO_ACTION_FALLPORTDOWN = HERO_ACTION_RISEPORTDOWN;

  private final int HERO_ACTION_SHOTDEAD = 12;

//  private final int HERO_ACTION_BOMBDEAD = 42;

//  private final int HERO_ACTION_VICTORY = 43;
//  private final int HERO_ACTION_SCARPSIDE = 44;

  //主角每一帧包括34个数据 前面6个是 帧的作用区和攻击区
  //后面的数据每7个一组 每一组表示一个图块数据
  private final int HERO_FRAME_COUNT = 27;
  private final int HERO_FRAME_RECTW = 0;
  private final int HERO_FRAME_RECTT = 1;
  private final int HERO_FRAME_FIREX = 2;
  private final int HERO_FRAME_FIREY = 3;

//  private final int HERO_FRAME_FIREW = 4;
//  private final int HERO_FRAME_FIREH = 5;

  private final int HERO_FRAME_PARTSTART = 6;
  private final int HERO_FRAME_PARTCOUNT = 7;

  private final int HERO_FRAME_FRAML = 0;
  private final int HERO_FRAME_FRAMT = 1;
  private final int HERO_FRAME_FRAMR = 2;
  private final int HERO_FRAME_FRAMB = 3;
  private final int HERO_FRAME_FACEL = 4;
  private final int HERO_FRAME_FACET = 5;
  private final int HERO_FRAME_FACER = 6;

  /**********************************************
   * 装载主角数据
   **********************************************/
  private final void hero_loaddefine() {

    InputStream in = stream_create("/" + (id + 1) + ".bin");
    try {
      byte[] gifdata = new byte[readWord(in)];
      in.read(gifdata, 6, gifdata.length - 6);
      System.arraycopy("GIF89a".getBytes(), 0, gifdata, 0, 6);
      hero_face = Image.createImage(gifdata, 0, gifdata.length);
      heroaction = new int[in.read()][][];
      for (int i = 0; i < heroaction.length; i++) {
        int framecount = in.read();
        heroaction[i] = new int[framecount][HERO_FRAME_COUNT];
        for (int j = 0; j < framecount; j++) {
          for (int k = 0; k < 6; k++) {
            heroaction[i][j][k] = (byte) in.read();
          }
          for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 7; l++) {
              heroaction[i][j][6 + k * 7 + l] =
                  (l < 4) ? (byte) in.read() : in.read();
            }
          }
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**********************************************
   * 设置主角当前状态
   * @param status_id int 主角状态索引
   **********************************************/
  private final void hero_setmainstatus(int status_id) {
    hero_status = (hero_status & ~STATUS_MAIN) | status_id;
  }

  private final void hero_setfirestatus(int status_id) {
    hero_status = (hero_status & ~STATUS_FIRE) | status_id;
  }

  private final void hero_setmovestatus(int status_id) {
    hero_status = (hero_status & ~STATUS_MOVE) | status_id;
  }

  private final void hero_setportstatus(int status_id) {
    hero_status = (hero_status & ~STATUS_PORT) | status_id;
  }

  /**********************************************
   * 设置主角当前的动作
   * @param action_id int 主角动作的索引
   **********************************************/
  private final void hero_setaction(int action_id) {
    if (hero_action == action_id) {
      return;
    }
    hero_action = action_id;
    hero_setcframe(0);
  }

  /**********************************************
   * 设置主角当前帧
   * @param cframe_id int 主角当前帧设置
   **********************************************/
  private final void hero_setcframe(int cframe_id) {
    hero_cframe = cframe_id;
    hero_frectw = heroaction[hero_action][hero_cframe][HERO_FRAME_RECTW];
    hero_frectt = heroaction[hero_action][hero_cframe][HERO_FRAME_RECTT];
  }

  private final void hero_action_play() {
    if ( ( (hero_status & STATUS_FIRE) == STATUS_FIRE_SWORDS) ||
        ( (hero_status & STATUS_FIRE) == STATUS_FIRE_THROWS)) {
      if (hero_cframe < heroaction[hero_action].length - 1) {
        hero_cframe++;
      }
    }
    else {
      hero_cframe = (hero_cframe < heroaction[hero_action].length - 1) ?
          hero_cframe + 1 : 0;
    }
  }

  private final boolean hero_action_finish() {
    return hero_cframe == heroaction[hero_action].length - 1;
  }

  /**********************************************
   * 移动主角位置 这个位置使用的是主角的直接坐标 不是网格坐标
   * @param x int 主角在地图上的绝对位置X
   * @param y int 主角在地图上的绝对位置Y
   **********************************************/
  private final void hero_move(int x, int y) {
    hero_pos_x = x;
    hero_pos_y = y;
    adScreen();
  }

  /**********************************************
   * 画出主角的动作的
   **********************************************/
  private final void hero_draw() {
    int[] fram = heroaction[hero_action][hero_cframe];
    if (hero_cflash > 0) {
      hero_cflash--;
    }
    if ( (hero_cflash & 1) == 1) {
      return;
    }
    int part_l, part_t, part_w, part_h, show_x, show_y;
    int rotate = (hero_direct == -1) ? 0 : 0x2000;
//    if(hero_action==HERO_ACTION_STANTOSSBOMB)
//    {
//      if(rotate==0)
//      {
//        rotate=0x2000;
//
//      }
//      else
//      {
//          rotate=0;
//      }
//    }
    for (int i = 0; i < 3; i++) { //画3层
      part_l = hero_pos_x - ( (hero_direct == -1) ?
                             fram[HERO_FRAME_PARTSTART +
                             i * HERO_FRAME_PARTCOUNT + HERO_FRAME_FRAML] :
                             fram[HERO_FRAME_PARTSTART +
                             i * HERO_FRAME_PARTCOUNT + HERO_FRAME_FRAMR]);
      part_t = hero_pos_y - fram[HERO_FRAME_PARTSTART +
          i * HERO_FRAME_PARTCOUNT + HERO_FRAME_FRAMT];
      part_w = fram[HERO_FRAME_PARTSTART + i * HERO_FRAME_PARTCOUNT +
          HERO_FRAME_FRAML] +
          fram[HERO_FRAME_PARTSTART + i * HERO_FRAME_PARTCOUNT +
          HERO_FRAME_FRAMR];
      part_h = fram[HERO_FRAME_PARTSTART + i * HERO_FRAME_PARTCOUNT +
          HERO_FRAME_FRAMT] +
          fram[HERO_FRAME_PARTSTART + i * HERO_FRAME_PARTCOUNT +
          HERO_FRAME_FRAMB];
      show_x = hero_pos_x - ( (hero_direct == -1) ? fram[HERO_FRAME_PARTSTART +
                             i * HERO_FRAME_PARTCOUNT + HERO_FRAME_FACEL] :
                             fram[HERO_FRAME_PARTSTART +
                             i * HERO_FRAME_PARTCOUNT + HERO_FRAME_FACER]);
      show_y = hero_pos_y - fram[HERO_FRAME_PARTSTART +
          i * HERO_FRAME_PARTCOUNT + HERO_FRAME_FACET];
      clipRect(part_l, part_t, part_w, part_h);
      drawFace(hero_face, show_x, show_y, rotate);
    }
  }

  /**********************************************
   * 主角接近了怪物 用于主角是否7用刀来攻击
   * @return boolean 当有怪物达到主角出到距离返回True
   **********************************************/
  private final boolean hero_near() { // w17, h11
    int sword_l = hero_pos_x - ( (hero_direct == -1) ? 25 : 0);
    int sword_r = hero_pos_x + ( (hero_direct == -1) ? 0 : 25);
    int sword_t = hero_pos_y - 11;
    int sword_b = hero_pos_y + 11;
    boolean rt = false;
    for (int i = ogres.size() - 1; i >= 0; i--) {
      int[] ogre = (int[]) ogres.elementAt(i);
      int[] defin = ogre_getdefin(ogre);
      if ( (ogre[OGRE_DEATH] > 0) || (defin[OGRE_DEFIN_STYLE] == 1)) {
        continue;
      }
      int[] frame = ogre_getframe(ogre);
      if ( (ogre[OGRE_POS_X] - frame[OGRE_FRAME_RECTW] < sword_r) &&
          (ogre[OGRE_POS_X] + frame[OGRE_FRAME_RECTW] > sword_l) &&
          (ogre[OGRE_POS_Y] - frame[OGRE_FRAME_RECTT] < sword_b) &&
          (ogre[OGRE_POS_Y] + 11 > sword_t)) {
        rt = true;
        break;
      }
    }
    for (int i = pawns.size() - 1; i >= 0; i--) {
      int[] pawn = (int[]) pawns.elementAt(i);
      if ( (pawn[PAWN_POS_X] - PAWN_HALFW < sword_r) &&
          (pawn[PAWN_POS_X] + PAWN_HALFW > sword_l) &&
          (pawn[PAWN_POS_Y] - PAWN_HALFH < sword_b) &&
          (pawn[PAWN_POS_Y] + PAWN_HALFH > sword_t) &&
          (pawn[PAWN_STATE] == 0)) {
        rt = true;
        break;
      }
    }
    return rt;
  }

  private final void hero_deadone() {
    if (hero_being <= 0) {
      hero_death = true;
      return;
    }
    time_init();
    //重新初始化主角参数
    hero_cflash = 20;

    hero_anima = 32;
    hero_bombcount = 8;
    hero_setfirestatus(STATUS_FIRE_NORMAL);
    hero_death = false;
    hero_being--;
    hero_spd_y = 0;
    //查找可以站立的位置
    if (hero_old_x < position_x) {
      for (int i = (position_x >> 3) + 1;
           i < ( (position_x + screen_w) >> 3) - 3; i++) {
        for (int j = position_y >> 3; j < (position_y + screen_h) >> 3; j++) {
          if (grid_cellvalue(i, j) > 0) {
            hero_pos_x = i << 3;
            hero_pos_y = (j << 3) - 100;
            return;
          }
        }
      }
    }
    else {
      hero_pos_x = hero_old_x;
      hero_pos_y = hero_old_y - 100;
    }
  }

  private final void hero_addscore(int score) {
    if (hero_score / 10000 < (hero_score + score) / 10000) {
      //  sound_play(SOUND_GETBEING);
      hero_being++;
    }
    if (score < 0) {
      score = -score;
    }
    hero_score += score;
//   System.out.println(score);
  }

  private final void hero_gainprop() {
    for (int i = props.size() - 1; i >= 0; i--) {
      int[] prop = (int[]) props.elementAt(i);
      if ( (prop[PROP_POS_X] + (PROP_POS_W >> 1) > hero_pos_x - hero_frectw) &&
          (prop[PROP_POS_Y] > hero_pos_y - hero_frectt) &&
          (hero_pos_x + hero_frectw > prop[PROP_POS_X] - (PROP_POS_W >> 1)) &&
          (hero_pos_y + 11 > prop[PROP_POS_Y] - PROP_POS_H)) {
        switch (prop[PROP_INDEX]) {
          case 0: //0 猪炸弹
            hero_addscore(50);
            break;
          case 1: //1 屎分
            hero_being++;
//            info_create(hero_pos_x, hero_pos_y - 10, prop[PROP_TOTAL]);
            break;
          case 2: //2 蘑菇血
            hero_anima = 32;
            break;
          case 3: //3 勋章生命

//            onezidan = 50;
            break;
          case 4:

//            twozidan = 80;
            break;
          case 5:
            hero_addscore(100);
            break;
//          case 4: //4 鱼
//          case 5: //5 烤鸡
//          case 6: //6 炸弹
//            hero_bombcount += prop[PROP_TOTAL];
//            break;
//          case 7: //7 机枪
//            if (hero_shot_type == SHOT_TYPE_TOMM) {
//              hero_shotcount += prop[PROP_TOTAL];
//            }
//            else {
//              hero_shotcount = prop[PROP_TOTAL];
//              hero_shot_type = SHOT_TYPE_TOMM;
//            }
//            break;
//          case 8: //8 导弹
//            if (hero_shot_type == SHOT_TYPE_MISS) {
//              hero_shotcount += prop[PROP_TOTAL];
//            }
//            else {
//              hero_shotcount = prop[PROP_TOTAL];
//              hero_shot_type = SHOT_TYPE_MISS;
//            }
//            break;
//          case 9: //9 火焰
//          case 10: //10 激光
//          case 11: //11 霰弹
//            break;
        }
        props.removeElementAt(i);
      }
    }
  }

  /**********************************************
   * 调整屏幕在地图上的位置
   **********************************************/
  private final void hero_adScene() {
    if ( (platid == MISSION_1_PLAT_FINAL)) {
      int x_pre = ( (hero_direct == -1) ? hero_pos_x - screen_w * 2 / 3 :
                   hero_pos_x + screen_w * 2 / 3 - screen_w) - position_x;
      if ( (x_pre > 100) || (x_pre < -100)) {
        position_x += (x_pre > 0) ? x_pre - 100 : x_pre + 100;
      }
      else {
        position_x += x_pre / 3;
      }
    }
    else {
      if (hero_pos_x - screen_w / 3 > position_x) {
        position_x = hero_pos_x - screen_w / 3;
      }
    }
    position_y += ( (hero_pos_y - screen_h / 2) - position_y) / 3;
    if (position_x < 0) {
      position_x = 0;
    }
    else if (position_x + screen_w > plat_w * 8) {
      position_x = plat_w * 8 - screen_w;
    }
    if (position_y < 0) {
      position_y = 0;
    }
    else if (position_y + screen_h > plat_h * 8) {
      position_y = plat_h * 8 - screen_h;
    }
  }

  /**********************************************
   * 主角RUN方法
   **********************************************/
  private final void hero_exec() {
    //读取按键信息
    int key_Press = key_Statuse;
    if (mission_final_tally > 0) {
      key_Press = 0;
    }
    if ( (hero_status & STATUS_FIRE) == STATUS_FIRE_DEATHS) {
      key_Press = 0;

    }
    if (hero_bombtally > 0) {
      hero_bombtally--;
    }
//    if (hero_shottally > 0) {
//      hero_shottally--;
//    }
//    if ( (hero_shot_type != 0) && (hero_shotcount <= 0)) {
//      hero_shot_type = 0;
//
//    }
    if (hero_pos_y > plat_h * 8) {
      hero_deadone();
    }
    else {
      //控制主角移动
      hero_gainprop();
      hero_frectw = heroaction[hero_action][hero_cframe][HERO_FRAME_RECTW];
      hero_frectt = heroaction[hero_action][hero_cframe][HERO_FRAME_RECTT];
      switch (hero_status & STATUS_MAIN) {
        case STATUS_MAIN_STAND:
          hero_stand(key_Press);
          break;
        case STATUS_MAIN_SQUAT:
          hero_squat(key_Press);
          break;
        case STATUS_MAIN_INAIR:
          hero_inair(key_Press);
          break;
      }
      hero_frectw = heroaction[hero_action][hero_cframe][HERO_FRAME_RECTW];
      hero_frectt = heroaction[hero_action][hero_cframe][HERO_FRAME_RECTT];
      hero_select_action();
      switch (hero_status & STATUS_FIRE) {
        case STATUS_FIRE_ATTACK:
          hero_fire();
          break;
        case STATUS_FIRE_THROWS:
          hero_toss();
          break;
        case STATUS_FIRE_SWORDS:
          hero_sword();
          break;
      }
    }
    hero_action_play();
    hero_adScene(); //随着主角的移动 调整屏幕在地图上的位置
  }

  /**********************************************
   * 主角站在平台上的动作处理
   * @param key_Press int 按键按下状态数据
   **********************************************/
  private final void hero_stand_key(int key_Press) {
    switch (key_Press) {
      case KEY_DOWN:
        hero_setmainstatus(STATUS_MAIN_SQUAT);
        hero_setportstatus(STATUS_PORT_BASE);
        return;
      case KEY_JUMP:
        hero_setmainstatus(STATUS_MAIN_INAIR);
        hero_spd_y = SPEED_VERT_START;
        return;
      case KEY_LEFT:
        hero_direct = -1;
        break;
      case KEY_RIGH:
        hero_direct = 1;
        break;
      case KEY_OVER:
        hero_setportstatus(STATUS_PORT_OVER);
        break;
    }
  }

  private final void hero_stand(int key_Press) {
    if (!hero_stan()) {
      hero_setmainstatus(STATUS_MAIN_INAIR);
      return;
    }
    else if ( (key_Press & KEY_LEFT) != 0) {
      hero_direct = -1;
      hero_setmovestatus(STATUS_MOVE_MOVE);
      hero_walk();
    }
    else if ( (key_Press & KEY_RIGH) != 0) {
      hero_direct = 1;
      hero_setmovestatus(STATUS_MOVE_MOVE);
      hero_walk();
    }
    else {
      hero_setmovestatus(STATUS_MOVE_REST);

    }
    if ( (key_Press & KEY_OVER) != 0) {
      hero_setportstatus(STATUS_PORT_OVER);
    }
    else {
      hero_setportstatus(STATUS_PORT_BASE);

    }
    switch (hero_status & STATUS_FIRE) {
      case STATUS_FIRE_NORMAL:
        if ( ( (key_Press & KEY_TOSS) != 0) && (hero_bombtally <= 0)) {
          hero_setfirestatus(STATUS_FIRE_THROWS);
        }
        else if ( (key_Press & KEY_FIRE) != 0) {
          if (hero_near()) {
            hero_setfirestatus(STATUS_FIRE_SWORDS);
          }
          else {
            hero_setfirestatus(STATUS_FIRE_ATTACK);
          }
        }
        break;
      case STATUS_FIRE_ATTACK:
        if ( ( (key_Press & KEY_TOSS) != 0) && (hero_bombtally <= 0)) {
          hero_setfirestatus(STATUS_FIRE_THROWS);
        }
        else if ( (key_Press & KEY_FIRE) == 0) {
          hero_setfirestatus(STATUS_FIRE_NORMAL);
        }
        else if (hero_near()) {
          hero_setfirestatus(STATUS_FIRE_SWORDS);
        }
        break;
      case STATUS_FIRE_THROWS:
        if (hero_action_finish()) {
          if ( (key_Press & KEY_FIRE) == 0) {
            hero_setfirestatus(STATUS_FIRE_NORMAL);
          }
          else if (hero_near()) {
            hero_setfirestatus(STATUS_FIRE_SWORDS);
          }
          else {
            hero_setfirestatus(STATUS_FIRE_ATTACK);
          }
        }
        break;
      case STATUS_FIRE_SWORDS:
        if (hero_action_finish()) {
          if ( (key_Press & KEY_FIRE) == 0) {
            hero_setfirestatus(STATUS_FIRE_NORMAL);
          }
          else if (hero_near()) {
            hero_setfirestatus(STATUS_FIRE_SWORDS);
          }
          else {
            hero_setfirestatus(STATUS_FIRE_ATTACK);
          }
        }
        break;
      case STATUS_FIRE_DEATHS:
        if (hero_action_finish()) {
          hero_deadone();
        }
        break;
    }
  }

  /**********************************************
   * 主角蹲在平台上的动作处理
   * @param key_Press int 按键按下状态数据
   **********************************************/
  private final void hero_squat_key(int key_Press) {
    switch (key_Press) {
      case KEY_JUMP:
        hero_spd_y = SPEED_VERT_START;
        hero_setmainstatus(STATUS_MAIN_INAIR);
        break;
      case KEY_LEFT:
        hero_direct = -1;
        hero_setmainstatus(STATUS_MAIN_STAND);
        break;
      case KEY_RIGH:
        hero_direct = 1;
        hero_setmainstatus(STATUS_MAIN_STAND);
        break;
      case KEY_OVER:
        hero_setmainstatus(STATUS_MAIN_STAND);
        break;
    }
  }

  private final void hero_squat(int key_Press) {
    if (!hero_stan()) {
      hero_setmainstatus(STATUS_MAIN_INAIR);
      hero_inair(key_Press);
      return;
    }
    switch (hero_status & STATUS_FIRE) {
      case STATUS_FIRE_NORMAL:
        if ( ( (key_Press & KEY_TOSS) != 0) && (hero_bombtally <= 0)) {
          hero_setfirestatus(STATUS_FIRE_THROWS);
        }
        else
        if ( (key_Press & KEY_FIRE) != 0) {
          if (hero_near()) {
            hero_setfirestatus(STATUS_FIRE_SWORDS);
          }
          else {
            hero_setfirestatus(STATUS_FIRE_ATTACK);
          }
        }
        break;
      case STATUS_FIRE_ATTACK:
        if ( ( (key_Press & KEY_TOSS) != 0) && (hero_bombtally <= 0)) {
          hero_setfirestatus(STATUS_FIRE_THROWS);
        }
        else if ( (key_Press & KEY_FIRE) == 0) {
          hero_setfirestatus(STATUS_FIRE_NORMAL);
        }
        else if (hero_near()) {
          hero_setfirestatus(STATUS_FIRE_SWORDS);
        }
        break;
      case STATUS_FIRE_THROWS:
        if (hero_action_finish()) {
          hero_setfirestatus(STATUS_FIRE_NORMAL);
        }
        break;
      case STATUS_FIRE_SWORDS:
        if (hero_action_finish()) {
          if ( (key_Press & KEY_FIRE) != 0) {
            hero_setfirestatus(STATUS_FIRE_ATTACK);
          }
          else {
            hero_setfirestatus(STATUS_FIRE_NORMAL);
          }
        }
        break;
      case STATUS_FIRE_DEATHS:
        if (hero_action_finish()) {
          hero_deadone();
        }
        break;
    }
  }

  /**********************************************
   * 主角飞在空中的动作处理
   * @param key_Press int 按键按下状态数据
   **********************************************/
  private final void hero_inair_key(int key_Press) {
  }

  private final void hero_inair(int key_Press) {
    if (hero_stan()) {
      hero_setmainstatus(STATUS_MAIN_STAND);
      hero_setmovestatus(STATUS_MOVE_REST);
      hero_stand(key_Press);
      return;
    }
    if ( (key_Press & KEY_LEFT) != 0) {
      hero_direct = -1;
      if (!hero_side()) {
        hero_pos_x -= SPEED_HORZ_ACCEL / 10;
      }
    }
    else if ( (key_Press & KEY_RIGH) != 0) {
      hero_direct = 1;
      if (!hero_side()) {
        hero_pos_x += SPEED_HORZ_ACCEL / 10;
      }
    }
//    if(hero_head()) hero_spd_y = 0;
    hero_pos_y += hero_spd_y / 10;
    hero_spd_y = (hero_spd_y < SPEED_VERT_LIMIT) ?
        hero_spd_y + SPEED_VERT_ACCEL : SPEED_VERT_LIMIT;
    if (Math.abs(hero_spd_y) < 10) {
      hero_setmovestatus(STATUS_MOVE_LEAP);
    }
    else if (hero_spd_y > 0) {
      hero_setmovestatus(STATUS_MOVE_FALL);
    }
    else if (hero_spd_y < 0) {
      hero_setmovestatus(STATUS_MOVE_RISE);

    }
    if ( (key_Press & KEY_OVER) != 0) {
      hero_setportstatus(STATUS_PORT_OVER);
    }
    else if ( (key_Press & KEY_DOWN) != 0) {
      hero_setportstatus(STATUS_PORT_DOWN);
    }
    else {
      hero_setportstatus(STATUS_PORT_BASE);

    }
    switch (hero_status & STATUS_FIRE) {
      case STATUS_FIRE_NORMAL:
        if ( ( (key_Press & KEY_TOSS) != 0) && (hero_bombtally <= 0)) {
          hero_setfirestatus(STATUS_FIRE_THROWS);
        }
        else if ( (key_Press & KEY_FIRE) != 0) {
          if (hero_near()) {
            hero_setfirestatus(STATUS_FIRE_SWORDS);
          }
          else {
            hero_setfirestatus(STATUS_FIRE_ATTACK);
          }
        }
        break;
      case STATUS_FIRE_ATTACK:
        if ( ( (key_Press & KEY_TOSS) != 0) && (hero_bombtally <= 0)) {
          hero_setfirestatus(STATUS_FIRE_THROWS);
        }
        else if ( (key_Press & KEY_FIRE) == 0) {
          hero_setfirestatus(STATUS_FIRE_NORMAL);
        }
        else if (hero_near()) {
          hero_setfirestatus(STATUS_FIRE_SWORDS);
        }
        break;
      case STATUS_FIRE_THROWS:
        if (hero_action_finish()) {
          hero_setfirestatus(STATUS_FIRE_NORMAL);
        }
        break;
      case STATUS_FIRE_SWORDS:
        if (hero_action_finish()) {
          if ( (key_Press & KEY_FIRE) != 0) {
            hero_setfirestatus(STATUS_FIRE_ATTACK);
          }
          else {
            hero_setfirestatus(STATUS_FIRE_NORMAL);
          }
        }
        break;
      case STATUS_FIRE_DEATHS:
        if (hero_action_finish()) {
          hero_deadone();
        }
        break;
    }
  }

  private final void hero_select_action() {
    switch (hero_status & STATUS_MAIN) {
      case STATUS_MAIN_STAND: //站立地面===========================================
        switch (hero_status & STATUS_MOVE) {
          case STATUS_MOVE_REST: //站立不走
            switch (hero_status & STATUS_FIRE) {
              case STATUS_FIRE_NORMAL:
                switch (hero_status & STATUS_PORT) {
                  case STATUS_PORT_BASE:
                    hero_setaction(HERO_ACTION_STANREST);
                    break;
//                  case STATUS_PORT_OVER:
//                    hero_setaction(HERO_ACTION_STANPORTOVER);
//                    break;
                }
                break;
              case STATUS_FIRE_ATTACK:
                switch (hero_status & STATUS_PORT) {
                  case STATUS_PORT_BASE:
                    hero_setaction(HERO_ACTION_STANFIREBASE);
                    break;
//                  case STATUS_PORT_OVER:
//                    hero_setaction(HERO_ACTION_STANFIREOVER);
//                    break;
                }
                break;
              case STATUS_FIRE_THROWS:
                if (hero_bombcount > 0) {
                  hero_setaction(HERO_ACTION_STANTOSSBOMB);
                }
                break;
              case STATUS_FIRE_SWORDS:
                hero_setaction(HERO_ACTION_STANFALCHION);
                break;
              case STATUS_FIRE_DEATHS:
                hero_setaction(HERO_ACTION_SHOTDEAD);
                break;
            }
            break;
          case STATUS_MOVE_MOVE: //站立移动
            switch (hero_status & STATUS_FIRE) {
              case STATUS_FIRE_NORMAL:
                switch (hero_status & STATUS_PORT) {
                  case STATUS_PORT_BASE:
                    hero_setaction(HERO_ACTION_STANMOVE);
                    break;
//                  case STATUS_PORT_OVER:
//                    hero_setaction(HERO_ACTION_MOVEPORTOVER);
//                    break;
                }
                break;
//              case STATUS_FIRE_ATTACK:
//                switch (hero_status & STATUS_PORT) {
//                  case STATUS_PORT_BASE:
//                    hero_setaction(HERO_ACTION_MOVEFIREBASE);
//                    break;
//                  case STATUS_PORT_OVER:
//                    hero_setaction(HERO_ACTION_MOVEFIREOVER);
//                    break;
//                }
//                break;
//              case STATUS_FIRE_THROWS:
//                hero_setaction(HERO_ACTION_MOVETOSSBOMB);
//                break;
//              case STATUS_FIRE_SWORDS:
//                hero_setaction(HERO_ACTION_MOVEFALCHION);
//                break;
              case STATUS_FIRE_DEATHS:
                hero_setaction(HERO_ACTION_SHOTDEAD);
                break;
            }
            break;
        }
        break;
      case STATUS_MAIN_SQUAT: //蹲在地上==========================================
        switch (hero_status & STATUS_FIRE) {
          case STATUS_FIRE_NORMAL:
            hero_setaction(HERO_ACTION_SQUAREST);
            break;
//          case STATUS_FIRE_ATTACK:
//            hero_setaction(HERO_ACTION_SQUAFIRE);
//            break;
//          case STATUS_FIRE_THROWS:
//            hero_setaction(HERO_ACTION_SQUATOSSBOMB);
//            break;
          case STATUS_FIRE_SWORDS:
            hero_setaction(HERO_ACTION_SQUAFALCHION);
            break;
          case STATUS_FIRE_DEATHS:
            hero_setaction(HERO_ACTION_SHOTDEAD);
            break;
        }
        break;
      case STATUS_MAIN_INAIR: //飞在空中==========================================
        switch (hero_status & STATUS_MOVE) {
          case STATUS_MOVE_RISE:
            switch (hero_status & STATUS_FIRE) {
              case STATUS_FIRE_NORMAL:
                switch (hero_status & STATUS_PORT) {
                  case STATUS_PORT_BASE:
                    hero_setaction(HERO_ACTION_JUMPRISE);
                    break;
//                  case STATUS_PORT_OVER:
//                    hero_setaction(HERO_ACTION_RISEPORTOVER);
//                    break;
//                  case STATUS_PORT_DOWN:
//                    hero_setaction(HERO_ACTION_RISEPORTDOWN);
//                    break;
                }
                break;
//              case STATUS_FIRE_ATTACK:
//                switch (hero_status & STATUS_PORT) {
//                  case STATUS_PORT_BASE:
//                    hero_setaction(HERO_ACTION_RISEFIREBASE);
//                    break;
//                  case STATUS_PORT_OVER:
//                    hero_setaction(HERO_ACTION_RISEFIREOVER);
//                    break;
//                  case STATUS_PORT_DOWN:
//                    hero_setaction(HERO_ACTION_RISEFIREDOWN);
//                    break;
//                }
//                break;
//              case STATUS_FIRE_THROWS:
//                hero_setaction(HERO_ACTION_RISETOSSBOMB);
//                break;
              case STATUS_FIRE_SWORDS:
                hero_setaction(HERO_ACTION_RISEFALCHION);
                break;
              case STATUS_FIRE_DEATHS:
                hero_setaction(HERO_ACTION_SHOTDEAD);
                break;
            }
            break;
          case STATUS_MOVE_LEAP:
            switch (hero_status & STATUS_FIRE) {
              case STATUS_FIRE_NORMAL:
                switch (hero_status & STATUS_PORT) {
                  case STATUS_PORT_BASE:
                    hero_setaction(HERO_ACTION_JUMPLEAP);
                    break;
//                  case STATUS_PORT_OVER:
//                    hero_setaction(HERO_ACTION_LEAPPORTOVER);
//                    break;
//                  case STATUS_PORT_DOWN:
//                    hero_setaction(HERO_ACTION_LEAPPORTDOWN);
//                    break;
                }
                break;
//              case STATUS_FIRE_ATTACK:
//                switch (hero_status & STATUS_PORT) {
//                  case STATUS_PORT_BASE:
//                    hero_setaction(HERO_ACTION_LEAPFIREBASE);
//                    break;
//                  case STATUS_PORT_OVER:
//                    hero_setaction(HERO_ACTION_LEAPFIREOVER);
//                    break;
//                  case STATUS_PORT_DOWN:
//                    hero_setaction(HERO_ACTION_LEAPFIREDOWN);
//                    break;
//                }
//                break;
//              case STATUS_FIRE_THROWS:
//                hero_setaction(HERO_ACTION_LEAPTOSSBOMB);
//                break;
              case STATUS_FIRE_SWORDS:
                hero_setaction(HERO_ACTION_LEAPFALCHION);
                break;
              case STATUS_FIRE_DEATHS:
                hero_setaction(HERO_ACTION_SHOTDEAD);
                break;
            }
            break;
          case STATUS_MOVE_FALL:
            switch (hero_status & STATUS_FIRE) {
//              case STATUS_FIRE_NORMAL:
//                switch (hero_status & STATUS_PORT) {
              case STATUS_PORT_BASE:
                hero_setaction(HERO_ACTION_JUMPFALL);
                break;
//                  case STATUS_PORT_OVER:
//                    hero_setaction(HERO_ACTION_FALLPORTOVER);
//                    break;
//                  case STATUS_PORT_DOWN:
//                    hero_setaction(HERO_ACTION_FALLPORTDOWN);
//                    break;
//                }
//                break;
//              case STATUS_FIRE_ATTACK:
//                switch (hero_status & STATUS_PORT) {
//                  case STATUS_PORT_BASE:
//                    hero_setaction(HERO_ACTION_FALLFIREBASE);
//                    break;
//                  case STATUS_PORT_OVER:
//                    hero_setaction(HERO_ACTION_FALLFIREOVER);
//                    break;
//                  case STATUS_PORT_DOWN:
//                    hero_setaction(HERO_ACTION_FALLFIREDOWN);
//                    break;
//                }
//                break;
//              case STATUS_FIRE_THROWS:
//                hero_setaction(HERO_ACTION_FALLTOSSBOMB);
//                break;
              case STATUS_FIRE_SWORDS:
                hero_setaction(HERO_ACTION_FALLFALCHION);
                break;
              case STATUS_FIRE_DEATHS:
                hero_setaction(HERO_ACTION_SHOTDEAD);
                break;
            }
            break;
        }
        break;
    }
  }

  int shotid = 0;

//  int onezidan = 0;
//  int twozidan = 0;
  private final void hero_fire() {
    if (hero_shottally > 0) {
      return;
    }
//    if (shotid == 1) {
//      if (onezidan > 0) {
//        onezidan--;
//      }
//      else {
//        shotid = 0;
//      }
//    }
//    if (shotid == 6) {
//      if (twozidan > 0) {
//        twozidan--;
//      }
//      else {
//        shotid = 0;
//      }
//    }
//    System.out.println(onezidan);
//System.out.println(twozidan+"twozidan");
//    switch (hero_shot_type) { //if((framenum & 1) == 1) return;
//      case SHOT_TYPE_TOMM:
//        hero_shottally = 0;
//        shotid = 0;
//        break;
//      case SHOT_TYPE_MISS:
//        hero_shottally = 6;
//        shotid = 3;
//        break;
//    }
    int[] frame = heroaction[hero_action][hero_cframe];
    int shot_pos_x = hero_pos_x + hero_direct * frame[HERO_FRAME_FIREX];
    int shot_pos_y = hero_pos_y;
//    System.out.println(hero_status);
    switch (hero_status) {
      case 256:

//      case 4352:
        shot_create(shotid, shot_pos_x + 5, shot_pos_y - 5, hero_direct * 2000,
                    0,
                    hero_direct, 1, 0);
        break;
//      case 4352:
//        shot_create(shotid, shot_pos_x + 5, shot_pos_y , hero_direct * 2000,
//                     0,
//                     hero_direct, 1, 0);
//
//        break;
//      case 257:
//        shot_create(shotid, shot_pos_x + 5, shot_pos_y - 5, 0, -2000,
//                    DIRECT_OVER, 1, 0);
//        break;
    }

  }

//  int pp = 0;
  byte id = 0;
  private final void hero_toss() { //手雷
    if ( (hero_cframe != 0) || (hero_bombcount <= 0) || (hero_bombtally > 0)) {
      return;
    }
    int shot_pos_x = hero_pos_x + hero_direct * 10;
    int shot_pos_y = hero_pos_y;
    shot_create(8 + id, shot_pos_x, shot_pos_y, hero_direct * 2000, 0,
                hero_direct,
                1, 0);
    hero_bombtally = HERO_BOMBTALLY;
    hero_bombcount--;

  }

//  * 子弹新建
//  * @param shot_defin int 子弹定义索引
//  * @param shotx int 子弹位置
//  * @param shoty int 子弹位置
//  * @param spd_x int 子弹水平速度
//  * @param spd_y int 子弹垂直速度
//  * @param direc int 子弹方向
//  * @param heros int 子弹所属

  private final void hero_sword() {
    if (hero_cframe != 2) {
      return;
    }
    int sword_l = hero_pos_x - ( (hero_direct == -1) ? 20 : 0);
    int sword_r = hero_pos_x + ( (hero_direct == -1) ? 0 : 20);
    int sword_t = hero_pos_y - 20;
    int sword_b = hero_pos_y + 20;
    for (int i = ogres.size() - 1; i >= 0; i--) {
      int[] ogre = (int[]) ogres.elementAt(i);
      int[] defin = ogre_getdefin(ogre);
      if ( (ogre[OGRE_DEATH] > 0) || (defin[OGRE_DEFIN_STYLE] == 1)) {
        continue;
      }
      int[] frame = ogre_getframe(ogre);
      if ( (ogre[OGRE_POS_X] - frame[OGRE_FRAME_RECTW] < sword_r) &&
          (ogre[OGRE_POS_X] + frame[OGRE_FRAME_RECTW] > sword_l) &&
          (ogre[OGRE_POS_Y] - frame[OGRE_FRAME_RECTT] < sword_b) &&
          (ogre[OGRE_POS_Y] + 11 > sword_t)) {
        ogre_hitted(ogre, hero_attak);
        hero_addscore(100);
      }
    }
    for (int i = pawns.size() - 1; i >= 0; i--) {
      int[] pawn = (int[]) pawns.elementAt(i);
      if ( (pawn[PAWN_POS_X] - PAWN_HALFW < sword_r) &&
          (pawn[PAWN_POS_X] + PAWN_HALFW > sword_l) &&
          (pawn[PAWN_POS_Y] - PAWN_HALFH < sword_b) &&
          (pawn[PAWN_POS_Y] + PAWN_HALFH > sword_t)) {
        pawn_rescue(pawn);
      }
    }
    for (int j = chest.size() - 1; j >= 0; j--) {
      int[] chet = (int[]) chest.elementAt(j);
      if ( (chet[CHEST_POS_X] < sword_r) &&
          (chet[CHEST_POS_Y] < sword_b) &&
          (chet[CHEST_POS_X] + chet[CHEST_POS_W] > sword_l) &&
          (chet[CHEST_POS_Y] + chet[CHEST_POS_H] > sword_t)) {
        chet_hitted(chet, hero_attak);
      }
    }
  }

  /**********************************************
   * 主角在步行处理函数 如果主角行走自动上下台阶 遇到障碍停下
   **********************************************/
  private final void hero_walk() {
    int x = (hero_pos_x + hero_direct * hero_frectw) >> 3;
    int y = (hero_pos_y + hero_frectb) >> 3;
    if ( ( (grid_cellvalue(x, y - 1) == 1) || (grid_cellvalue(x, y - 1) == 2)) &&
        (grid_cellvalue(x, y - 2) == 0)) {
      hero_pos_y -= 8;
    }
    else if ( (grid_cellvalue(x, y) == 0) && ( (grid_cellvalue(x, y + 1) == 1) ||
                                              (grid_cellvalue(x, y + 1) == 2))) {
      hero_pos_y += 8;
    }
    if (!hero_side()) {
      hero_pos_x += hero_direct * SPEED_HORZ_ACCEL / 10;
    }
  }

  /**********************************************
   * 判断主角是否碰到墙壁 如果主角碰到墙壁返回true 并修正主角坐标 否则false
   * @return boolean 主角碰到墙壁
   **********************************************/
  private final boolean hero_side() {
    int hero_close = hero_pos_x +
        hero_direct * (hero_frectw + SPEED_HORZ_ACCEL / 10);
    int hero_linet = hero_pos_y - hero_frectt + 2;
    int hero_lineb = hero_pos_y + hero_frectb - 2;
    for (int x = hero_pos_x + hero_direct * hero_frectw; x != hero_close;
         x += hero_direct) {
      if ( (grid_cellvalue(x >> 3, hero_linet >> 3) == 1) ||
          (grid_cellvalue(x >> 3, hero_lineb >> 3) == 1) ||
          (grid_cellvalue(x >> 3, hero_pos_y >> 3) == 1)) {
        hero_pos_x = x - hero_direct * hero_frectw;
        return true;
      }
      boolean rt = false;
      for (int j = chest.size() - 1; j >= 0; j--) {
        int[] chet = (int[]) chest.elementAt(j);
        int chet_l = chet[CHEST_POS_X];
        int chet_r = chet_l + chet[CHEST_POS_W];
        int chet_t = chet[CHEST_POS_Y];
        int chet_b = chet_t + chet[CHEST_POS_H];
        if ( (x > chet_l) && (x < chet_r)) {
          rt |= (hero_linet > chet_t) && (hero_linet < chet_b);
          rt |= (hero_lineb > chet_t) && (hero_lineb < chet_b);
          rt |= (hero_pos_y > chet_t) && (hero_pos_y < chet_b);
          if (rt) {
            hero_pos_x = x - hero_direct * hero_frectw;
            return true;
          }
        }
      }
    }
    if (hero_direct == DIRECT_LEFT) {
      if ( (hero_pos_x - hero_frectw + hero_direct * SPEED_HORZ_ACCEL / 10) <
          position_x) {
        return true;
      }
    }
    return false;
  }

  /***********************************************
   * 判断主角是否站在平台或者墙壁上
   * @return boolean false：超出地图范围或者没有遇到平台或者墙壁 true:遇到平台或者墙壁
   ***********************************************/
  private final boolean hero_stan() {
    if (hero_spd_y < 0) {
      return false;
    }
    int i, v;
    boolean rt = false, rt1 = false, rt2 = false;
    int vert_start = hero_pos_y + hero_frectb;
    int vert_close = hero_pos_y + hero_frectb + hero_spd_y / 10;
    int hero_linel = hero_pos_x - hero_frectw + 2;
    int hero_liner = hero_pos_x + hero_frectw - 2;
    for (i = vert_start; i <= vert_close; i++) {
      v = grid_cellvalue(hero_linel >> 3, i >> 3);
      rt1 |= (v == 1) || (v == 2);
      v = grid_cellvalue(hero_liner >> 3, i >> 3);
      rt2 |= (v == 1) || (v == 2);
      if (rt1 && rt2) {
        hero_old_x = hero_pos_x;
        hero_old_y = hero_pos_y;
      }
      if (rt1 || rt2) {
        hero_pos_y = ( (i >> 3) << 3) - hero_frectb;
        return true;
      }
      rt = false;
      for (int j = chest.size() - 1; j >= 0; j--) {
        int[] chet = (int[]) chest.elementAt(j);
        int chet_l = chet[CHEST_POS_X];
        int chet_r = chet_l + chet[CHEST_POS_W];
        int chet_t = chet[CHEST_POS_Y];
        int chet_b = chet_t + chet[CHEST_POS_H];
        if ( (i > chet_t) && (i < chet_b)) {
          rt |= (hero_linel > chet_l) && (hero_linel < chet_r);
          rt |= (hero_liner > chet_l) && (hero_liner < chet_r);
          rt |= (hero_pos_x > chet_l) && (hero_pos_x < chet_r);
          if (rt) {
            hero_pos_y = i - hero_frectb;
            return true;
          }
        }
      }
    }
    return false;
  }

//  /**********************************************
//   * 判断主角的头是否顶到了墙壁
//   * @return boolean 如果主角头顶到了墙壁返回true, 否则false
//   **********************************************/
//  private final boolean hero_head(){
//    if(hero_spd_y >= 0) return false;  //如果主角处在下落状态 返回false
//    int vert_start = (hero_pos_y - hero_frectt) >> 3;
//    int vert_close = (hero_pos_y - hero_frectt + hero_spd_y / 10) >> 3;
//    int hero_headl = (hero_pos_x - hero_frectw + 3) >> 3;
//    int hero_headr = (hero_pos_x + hero_frectw - 3) >> 3;
//    int x, y;
//    for(y = vert_start; y >= vert_close; y--){
//      for(x = hero_headl; x <= hero_headr; x++){
//        if(grid_cellvalue(x, y) == 1){
//          hero_pos_y = (y + 1 << 3) + hero_frectt;
//          hero_spd_y = 0;
//          return true;
//        }
//      }
//    }
//    return false;
//  }
//******************************************************************************
   /** @todo: 飞机 */
//******************************************************************************

//    private Image plan_face;
//  private int plan_cannon_angle = 0;
//  private boolean plan_cannon_fire = false;
//  private int plan_cannon_fire_frame = 0;
//  private int plan_cannon_fire_count = 0;
//  private int plan_action = 0;
//  private int plan_cframe = 0;
//  private int plan_cflash = 0;
//  private int plan_death_count = 0;
//
//  private final int PLAN_ACTION_COUNT = 6;
//  private final int PLAN_ACTION_REST = 0;
//  private final int PLAN_ACTION_FORE = 1;
//  private final int PLAN_ACTION_BACK = 2;
//  private final int PLAN_ACTION_DOWN = 3;
//  private final int PLAN_ACTION_OVER = 4;
//  private final int PLAN_ACTION_BRUI = 5;
//  private byte[][][] planaction;
//
//  //主角每一帧包括34个数据 前面6个是 帧的作用区和攻击区
//  //后面的数据每7个一组 每一组表示一个图块数据
//  private final int PLAN_FRAME_COUNT = 34;
//  private final int PLAN_FRAME_RECTW = 0;
//  private final int PLAN_FRAME_RECTT = 1;
//  private final int PLAN_FRAME_FIREX = 2;
//  private final int PLAN_FRAME_FIREY = 3;
//  private final int PLAN_FRAME_FIREW = 4;
//  private final int PLAN_FRAME_FIREH = 5;
//
//  private final int PLAN_FRAME_PARTSTART = 6;
//  private final int PLAN_FRAME_PARTCOUNT = 7;
//
//  private final int PLAN_FRAME_FRAML = 0;
//  private final int PLAN_FRAME_FRAMT = 1;
//  private final int PLAN_FRAME_FRAMR = 2;
//  private final int PLAN_FRAME_FRAMB = 3;
//  private final int PLAN_FRAME_FACEL = 4;
//  private final int PLAN_FRAME_FACET = 5;
//  private final int PLAN_FRAME_FACER = 6;
//
//  private final void plan_loaddefine() {
//    InputStream in = stream_create("/plan.bin");
//    try {
//      byte[] gifdata = new byte[readWord(in)];
//      in.read(gifdata, 6, gifdata.length - 6);
//      System.arraycopy("GIF89a".getBytes(), 0, gifdata, 0, 6);
//      plan_face = Image.createImage(gifdata, 0, gifdata.length);
//      planaction = new byte[in.read()][][];
//      for (int i = 0; i < planaction.length; i++) {
//        planaction[i] = new byte[in.read()][PLAN_FRAME_COUNT];
//        for (int j = 0; j < planaction[i].length; j++) {
//          in.read(planaction[i][j]);
//        }
//      }
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//
//  private final boolean plan_nexus(int x, int y, int r, int b) {
//    if (plan_death_count > 0) {
//      return false;
//    }
//    byte[] fram = planaction[plan_action][plan_cframe];
//    return! ( (x > hero_pos_x + fram[PLAN_FRAME_RECTW]) ||
//             (r < hero_pos_x - fram[PLAN_FRAME_RECTW]) ||
//             (y > hero_pos_y + 11) ||
//             (b < hero_pos_y - fram[PLAN_FRAME_RECTT]));
//  }
//
//  private final void plan_hitted(int attack) {
//    if (plan_cflash > 0) {
//      return;
//    }
//    if (attack <= 0) {
//      return;
//    }
//    hero_anima -= attack;
//    plan_cflash = 10;
//    plan_setaction(PLAN_ACTION_BRUI);
//    if (hero_anima <= 0) {
//      plan_death_count = 20;
//      movi_create(2, hero_pos_x, hero_pos_y, 400, -700, 0, 100, -1,
//                  MOVI_DEATH_SIDE);
//      movi_create(2, hero_pos_x, hero_pos_y, -200, -800, 0, 100, -1,
//                  MOVI_DEATH_SIDE);
//      movi_create(2, hero_pos_x, hero_pos_y, -500, -600, 0, 100, -1,
//                  MOVI_DEATH_SIDE);
//      movi_create(2, hero_pos_x, hero_pos_y, 700, -400, 0, 100, -1,
//                  MOVI_DEATH_SIDE);
//    }
//  }
//
//  private final void plan_action_play() {
//    plan_cframe = (plan_cframe < planaction[plan_action].length - 1) ?
//        plan_cframe + 1 : 0;
//  }
//
//  private final boolean plan_action_finish() {
//    return plan_cframe == planaction[plan_action].length - 1;
//  }
//
//  private final void plan_setaction(int action) {
//    if (action != plan_action) {
//      plan_action = action;
//      plan_cframe = 0;
//    }
//  }

//  private final void plan_deadone() {
//    if (hero_being <= 0) {
//      return;
//    }
//    time_init();
//    //重新初始化主角参数
//
//    hero_anima = 10;
//    hero_being--;
//    hero_cflash = 20;
//    hero_death = hero_being <= 0;
//    hero_bombcount = 10;
//
//    hero_pos_x = position_x + 20;
//    hero_pos_y = 20;
//  }
//
//  private final void plan_exec() {
//    //按键参数的控制
//    int key_Press = key_Statuse;
//    if (mission_final_tally > 0) {
//      key_Press = 0;
//      //飞机闪烁
//    }
//    plan_cflash = (plan_cflash > 0) ? plan_cflash - 1 : 0;
//
//    hero_gainprop();
//    if (hero_pos_x + 15 < position_x) {
//      time_init();
//      hero_anima = 10;
//      hero_being--;
//
//      hero_death = hero_being <= 0;
//      hero_cflash = 20;
//      hero_bombcount = 10;
//
//      hero_pos_x = position_x + 20;
//      hero_pos_y = 20;
//    }
//    if (plan_death_count > 0) {
//      if (plan_death_count == 1) {
//        time_init();
//        hero_anima = 10;
//        hero_death = hero_being <= 0;
//        if (hero_death) {
//          plan_death_count = 10;
//        }
//        hero_bombcount = 10;
//        plan_cflash = 20;
//        hero_being--;
//
//        hero_pos_y = 20;
//        hero_pos_x = position_x + 20;
//      }
//      plan_death_count--;
//    }
//    else {
//      //控制飞机的动作
//      if (plan_action != TANK_ACTION_BRUI) {
//        if ( (key_Press & KEY_LEFT) != 0) {
//          plan_setaction(PLAN_ACTION_BACK);
//        }
//        else if ( (key_Press & KEY_RIGH) != 0) {
//          plan_setaction(PLAN_ACTION_FORE);
//        }
//        else if ( (key_Press & KEY_OVER) != 0) {
//          plan_setaction(PLAN_ACTION_OVER);
//        }
//        else if ( (key_Press & KEY_DOWN) != 0) {
//          plan_setaction(PLAN_ACTION_DOWN);
//        }
//        else {
//          plan_setaction(PLAN_ACTION_REST);
//        }
//      }
//      if ( (key_Press & KEY_LEFT) != 0) {
//        if (position_x < hero_pos_x - 10) {
//          plan_backmove();
//        }
//      }
//      else if ( (key_Press & KEY_RIGH) != 0) {
//        if (position_x + screen_w > hero_pos_x + 10) {
//          plan_foremove();
//        }
//      }
//      else if ( (key_Press & KEY_OVER) != 0) {
//        switch (plan_cannon_angle) {
//          case 0:
//            plan_cannon_angle = 1;
//            break;
//          case 7:
//            plan_cannon_angle = 0;
//            break;
//        }
//        if (position_y + 10 < hero_pos_y) {
//          plan_overmove();
//        }
//      }
//      else if ( (key_Press & KEY_DOWN) != 0) {
//        switch (plan_cannon_angle) {
//          case 1:
//            plan_cannon_angle = 0;
//            break;
//          case 0:
//            plan_cannon_angle = 7;
//            break;
//        }
//        if (position_y + screen_h > hero_pos_y + 10) {
//          plan_downmove();
//        }
//      }
//      //控制飞机的攻击
//      if ( (key_Press & KEY_FIRE) != 0) {
//        plan_cannon_fire_count = 3;
//      }
//      if (plan_cannon_fire_count > 0) {
//        plan_cannon_fire = true;
//        plan_cannon_fire_count--;
//        plan_cannon_fire_frame = framenum & 1;
//        if (plan_cannon_fire_frame == 1) {
//          byte[] frame = planaction[plan_action][plan_cframe];
//          int firex = hero_pos_x - frame[PLAN_FRAME_FIREX];
//          int firey = hero_pos_y - frame[PLAN_FRAME_FIREY];
//          switch (plan_cannon_angle) {
//            case 0:
//              shot_create(2, firex + 10, firey, 2000, 0, DIRECT_RIGH, 1,
//                          (framenum >> 1) & 3);
//              break;
//            case 1:
//              shot_create(2, firex + 7, firey - 7, 1200, -1200, DIRECT_RIGHOVER,
//                          1, (framenum >> 1) & 3);
//              break;
//            case 7:
//              shot_create(2, firex + 7, firey + 7, 1200, 1200, DIRECT_RIGHDOWN,
//                          1, (framenum >> 1) & 3);
//              break;
//          }
//        }
//      }
//      else {
//        plan_cannon_fire = false;
//      }
//    }
//    plan_action_play();
//    plan_adScene();
//  }
//
//  private final void plan_foremove() {
//    int plan_side_t = (hero_pos_y - 3) >> 3;
//    int plan_side_b = (hero_pos_y + 9) >> 3;
//    int plan_close = (hero_pos_x + 15) >> 3;
//    boolean hold = false;
//    for (int i = plan_side_t; i <= plan_side_b; i++) {
//      if (grid_cellvalue(plan_close, i) == 1) {
//        hold = true;
//        break;
//      }
//    }
//    if (hold) {
//      hero_pos_x = (plan_close << 3) - 15;
//    }
//    else {
//      hero_pos_x = hero_pos_x + 6;
//    }
//  }
//
//  private final void plan_backmove() {
//    int plan_side_t = (hero_pos_y - 3) >> 3;
//    int plan_side_b = (hero_pos_y + 9) >> 3;
//    int plan_close = (hero_pos_x - 15) >> 3;
//    boolean hold = false;
//    for (int i = plan_side_t; i <= plan_side_b; i++) {
//      if (grid_cellvalue(plan_close, i) == 1) {
//        hold = true;
//        break;
//      }
//    }
//    if (hold) {
//      hero_pos_x = (plan_close << 3) + 23;
//    }
//    else {
//      hero_pos_x = hero_pos_x - 6;
//    }
//  }
//
//  private final void plan_overmove() {
//    int plan_side_l = (hero_pos_x - 13) >> 3;
//    int plan_side_r = (hero_pos_x + 13) >> 3;
//    int plan_close = (hero_pos_y - 5) >> 3;
//    boolean hold = false;
//    for (int i = plan_side_l; i <= plan_side_r; i++) {
//      if (grid_cellvalue(i, plan_close) == 1) {
//        hold = true;
//        break;
//      }
//    }
//    if (hold) {
//      hero_pos_y = (plan_close << 3) + 13;
//    }
//    else {
//      hero_pos_y = hero_pos_y - 6;
//    }
//  }
//
//  private final void plan_downmove() {
//    int plan_side_l = (hero_pos_x - 13) >> 3;
//    int plan_side_r = (hero_pos_x + 13) >> 3;
//    int plan_close = (hero_pos_y + 11) >> 3;
//    boolean hold = false;
//    for (int i = plan_side_l; i <= plan_side_r; i++) {
//      if (grid_cellvalue(i, plan_close) == 1) {
//        hold = true;
//        break;
//      }
//    }
//    if (hold) {
//      hero_pos_y = (plan_close << 3) - 11;
//    }
//    else {
//      hero_pos_y = hero_pos_y + 6;
//    }
//  }
//
//  private final void plan_adScene() {
//    if (plan_death_count > 0) {
//      return;
//    }
//    int plan_side_t = (hero_pos_y - 3) >> 3;
//    int plan_side_b = (hero_pos_y + 9) >> 3;
//    int plan_close = (hero_pos_x + 17) >> 3;
//    boolean hold = false;
//    for (int i = plan_side_t; i <= plan_side_b; i++) {
//      if (grid_cellvalue(plan_close, i) == 1) {
//        hold = true;
//        break;
//      }
//    }
//    if (hold) {
//      hero_pos_x = (plan_close << 3) - 15;
//    }
//    else {
//      hero_pos_x = hero_pos_x + 2;
//    }
//    position_x += 2;
//    position_y += ( (hero_pos_y - screen_h / 2) - position_y) / 3;
//    if (position_x < 0) {
//      position_x = 0;
//    }
//    else if (position_x + screen_w > plat_w * 8) {
//      position_x = plat_w * 8 - screen_w;
//    }
//    if (position_y < 0) {
//      position_y = 0;
//    }
//    else if (position_y + screen_h > plat_h * 8) {
//      position_y = plat_h * 8 - screen_h;
//    }
//  }
//
//  private final void plan_draw() {
//    try {
//      if (plan_death_count > 0) {
//        return;
//      }
//      byte[] fram = planaction[plan_action][plan_cframe];
//      int part_l, part_t, part_w, part_h, show_x, show_y;
//      for (int i = 0; i < 4; i++) {
//        part_l = hero_pos_x - fram[HERO_FRAME_PARTSTART +
//            i * HERO_FRAME_PARTCOUNT + HERO_FRAME_FRAML];
//        part_t = hero_pos_y - fram[HERO_FRAME_PARTSTART +
//            i * HERO_FRAME_PARTCOUNT + HERO_FRAME_FRAMT];
//        part_w = fram[HERO_FRAME_PARTSTART + i * HERO_FRAME_PARTCOUNT +
//            HERO_FRAME_FRAML] +
//            fram[HERO_FRAME_PARTSTART + i * HERO_FRAME_PARTCOUNT +
//            HERO_FRAME_FRAMR];
//        part_h = fram[HERO_FRAME_PARTSTART + i * HERO_FRAME_PARTCOUNT +
//            HERO_FRAME_FRAMT] +
//            fram[HERO_FRAME_PARTSTART + i * HERO_FRAME_PARTCOUNT +
//            HERO_FRAME_FRAMB];
//        show_x = hero_pos_x - fram[HERO_FRAME_PARTSTART +
//            i * HERO_FRAME_PARTCOUNT + HERO_FRAME_FACEL];
//        show_y = hero_pos_y - fram[HERO_FRAME_PARTSTART +
//            i * HERO_FRAME_PARTCOUNT + HERO_FRAME_FACET];
//        clipRect(part_l, part_t, part_w, part_h);
//        drawFace(plan_face, show_x, show_y, 0);
//      }
//      cann_draw(hero_pos_x - fram[PLAN_FRAME_FIREX],
//                hero_pos_y - fram[PLAN_FRAME_FIREY],
//                plan_cannon_angle,
//                plan_cannon_fire,
//                plan_cannon_fire_frame);
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//
//******************************************************************************
//   /** @todo: 坦克 */
//******************************************************************************
//    private Image tank_face;
//  private final int TANK_ACTION_COUNT = 9;
//  private final int TANK_ACTION_REST = 0;
//  private final int TANK_ACTION_FORE = 1;
//  private final int TANK_ACTION_BACK = 2;
//  private final int TANK_ACTION_JUMPOVER = 3;
//  private final int TANK_ACTION_JUMPLEAP = 4;
//  private final int TANK_ACTION_JUMPDOWN = 5;
//  private final int TANK_ACTION_SQUA = 6;
//  private final int TANK_ACTION_BRUI = 7;
//  private final int TANK_ACTION_JUMPBRUI = 8;
//
//  private byte[][][] tankaction;
//
//  //主角每一帧包括34个数据 前面6个是 帧的作用区和攻击区
//  //后面的数据每7个一组 每一组表示一个图块数据
//  private final int TANK_FRAME_COUNT = 34;
//  private final int TANK_FRAME_RECTW = 0;
//  private final int TANK_FRAME_RECTT = 1;
//  private final int TANK_FRAME_FIREX = 2;
//  private final int TANK_FRAME_FIREY = 3;
//  private final int TANK_FRAME_FIREW = 4;
//  private final int TANK_FRAME_FIREH = 5;
//
//  private final int TANK_FRAME_PARTSTART = 6;
//  private final int TANK_FRAME_PARTCOUNT = 7;
//
//  private final int TANK_FRAME_FRAML = 0;
//  private final int TANK_FRAME_FRAMT = 1;
//  private final int TANK_FRAME_FRAMR = 2;
//  private final int TANK_FRAME_FRAMB = 3;
//  private final int TANK_FRAME_FACEL = 4;
//  private final int TANK_FRAME_FACET = 5;
//  private final int TANK_FRAME_FACER = 6;
//
//  private final void tank_loaddefine() {
//    InputStream in = stream_create("/tank.bin");
//    try {
//      byte[] gifdata = new byte[readWord(in)];
//      in.read(gifdata, 6, gifdata.length - 6);
//      System.arraycopy("GIF89a".getBytes(), 0, gifdata, 0, 6);
//      tank_face = Image.createImage(gifdata, 0, gifdata.length);
//      tankaction = new byte[in.read()][][];
//      for (int i = 0; i < tankaction.length; i++) {
//        tankaction[i] = new byte[in.read()][TANK_FRAME_COUNT];
//        for (int j = 0; j < tankaction[i].length; j++) {
//          in.read(tankaction[i][j]);
//        }
//      }
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//
//  private final int TANK_STATUS_STAND = 0;
//  private final int TANK_STATUS_SQUAT = 1;
//  private final int TANK_STATUS_INAIR = 2;
//
//  private final int TANK_STATUS_NORMAL = 0x00;
//  private final int TANK_STATUS_HITTED = 0x10;
//
//  private final int TANK_SPEED_HORIZ = 60;
//  private final int TANK_SPEED_START = -180;
//  private final int TANK_SPEED_ACCEL = 50;
//  private final int TANK_SPEED_LIMIT = 200;
//
//  private int tank_status = 0;
//  private int tank_action = 0;
//  private int tank_cframe = 0;
//  private int tank_speedy = 0;
//  private int tank_speedx = 0;
//  private int tank_cflash = 0;
//  private int tank_death_count = 0;
//  private int tank_cannon_angle = 0;
//  private boolean tank_cannon_fire = false;
//  private int tank_cannon_fire_frame = 0;
//  private int tank_cannon_fire_count = 0;
//  private final int tank_frectt = 9;
//  private final int tank_frectw = 5;
//  private final int tank_frectb = 11;
//
//  private final void tank_setaction(int action) {
//    if (action != tank_action) {
//      tank_action = action;
//      tank_cframe = 0;
//    }
//    switch (action) {
//      case TANK_ACTION_REST:
//      case TANK_ACTION_FORE:
//      case TANK_ACTION_BACK:
//      case TANK_ACTION_BRUI:
//        tank_status = TANK_STATUS_STAND | (tank_status & 0xF0);
//        break;
//      case TANK_ACTION_JUMPOVER:
//      case TANK_ACTION_JUMPLEAP:
//      case TANK_ACTION_JUMPDOWN:
//      case TANK_ACTION_JUMPBRUI:
//        tank_status = TANK_STATUS_INAIR | (tank_status & 0xF0);
//        break;
//      case TANK_ACTION_SQUA:
//        tank_status = TANK_STATUS_SQUAT | (tank_status & 0xF0);
//        break;
//    }
//  }
//
//  private final void tank_setbasestatus(int status) {
//    tank_status = (tank_status & 0xF0) | status;
//  }
//
//  private final void tank_setfirestatus(int status) {
//    tank_status = (tank_status & 0xF) | status;
//  }
//
//  private final void tank_deadone() {
//    if (hero_being <= 0) {
//      return;
//    }
//    time_init();
//
//    hero_anima = 10;
//    hero_being--;
//    hero_cflash = 20;
//    hero_death = hero_being <= 0;
//    if (hero_death) {
//      tank_death_count = 10;
//    }
//    hero_bombcount = 10;
//
//    hero_pos_y = 100;
//  }
//
//  private final void tank_draw() {
//    if (tank_death_count > 0) {
//      return;
//    }
//    byte[] fram = tankaction[tank_action][tank_cframe];
//    int part_l, part_t, part_w, part_h, show_x, show_y;
//    for (int i = 0; i < 4; i++) {
//      part_l = hero_pos_x - fram[TANK_FRAME_PARTSTART +
//          i * TANK_FRAME_PARTCOUNT + TANK_FRAME_FRAML];
//      part_t = hero_pos_y - fram[TANK_FRAME_PARTSTART +
//          i * TANK_FRAME_PARTCOUNT + TANK_FRAME_FRAMT];
//      part_w = fram[TANK_FRAME_PARTSTART + i * TANK_FRAME_PARTCOUNT +
//          TANK_FRAME_FRAML] +
//          fram[TANK_FRAME_PARTSTART + i * TANK_FRAME_PARTCOUNT +
//          TANK_FRAME_FRAMR];
//      part_h = fram[TANK_FRAME_PARTSTART + i * TANK_FRAME_PARTCOUNT +
//          TANK_FRAME_FRAMT] +
//          fram[TANK_FRAME_PARTSTART + i * TANK_FRAME_PARTCOUNT +
//          TANK_FRAME_FRAMB];
//      show_x = hero_pos_x - fram[TANK_FRAME_PARTSTART +
//          i * TANK_FRAME_PARTCOUNT + TANK_FRAME_FACEL];
//      show_y = hero_pos_y - fram[TANK_FRAME_PARTSTART +
//          i * TANK_FRAME_PARTCOUNT + TANK_FRAME_FACET];
//      clipRect(part_l, part_t, part_w, part_h);
//      drawFace(tank_face, show_x, show_y, 0);
//    }
//    cann_draw(hero_pos_x - fram[PLAN_FRAME_FIREX],
//              hero_pos_y - fram[PLAN_FRAME_FIREY],
//              tank_cannon_angle,
//              tank_cannon_fire,
//              tank_cannon_fire_frame);
//  }
//
//  private final boolean tank_nexus(int x, int y, int r, int b) {
//    if (tank_death_count > 0) {
//      return false;
//    }
//    byte[] fram = tankaction[tank_action][tank_cframe];
//    return! ( (x > hero_pos_x + fram[TANK_FRAME_RECTW]) ||
//             (r < hero_pos_x - fram[TANK_FRAME_RECTW]) ||
//             (y > hero_pos_y + 11) ||
//             (b < hero_pos_y - fram[TANK_FRAME_RECTT]));
//  }
//
//  private final void tank_hitted(int attack) {
//    if (tank_cflash > 0) {
//      return;
//    }
//    if (attack <= 0) {
//      return;
//    }
//    hero_anima -= attack;
//    tank_cflash = 10;
//    tank_setfirestatus(TANK_STATUS_HITTED);
//    if (hero_anima <= 0) {
//      tank_death_count = 20;
//      hero_being--;
//      movi_create(2, hero_pos_x, hero_pos_y, 400, -700, 0, 100, -1,
//                  MOVI_DEATH_SIDE);
//      movi_create(2, hero_pos_x, hero_pos_y, -200, -800, 0, 100, -1,
//                  MOVI_DEATH_SIDE);
//      movi_create(2, hero_pos_x, hero_pos_y, -500, -600, 0, 100, -1,
//                  MOVI_DEATH_SIDE);
//      movi_create(2, hero_pos_x, hero_pos_y, 700, -400, 0, 100, -1,
//                  MOVI_DEATH_SIDE);
//    }
//  }
//
//  private final void tank_exec() {
//    //读取按键信息
//    int key_Press = key_Statuse;
//    if (mission_final_tally > 0) {
//      key_Press = 0;
//    }
//    if ( (tank_status & STATUS_FIRE) == STATUS_FIRE_DEATHS) {
//      key_Press = 0;
//
//    }
//    if (hero_bombtally > 0) {
//      hero_bombtally--;
//    }
//    if (hero_shottally > 0) {
//      hero_shottally--;
//    }
//    if ( (hero_shot_type != 0) && (hero_shotcount <= 0)) {
//      hero_shot_type = 0;
//    }
//    if (tank_cflash > 0) {
//      tank_cflash--;
//
//    }
//    if (tank_death_count > 0) {
//      if (tank_death_count == 1) {
//        hero_pos_y = 100;
//        hero_anima = 10;
//        hero_death = hero_being <= 0;
//        if (hero_death) {
//          tank_death_count = 10;
//        }
//        hero_bombcount = 10;
//        tank_cflash = 10;
//      }
//      tank_death_count--;
//    }
//    else {
//      //控制主角移动
//      hero_gainprop();
//      switch (tank_status & 0xf) {
//        case TANK_STATUS_STAND:
//          tank_stand(key_Press);
//          break;
//        case TANK_STATUS_SQUAT:
//          tank_squat(key_Press);
//          break;
//        case TANK_STATUS_INAIR:
//          tank_inair(key_Press);
//          break;
//      }
//      if (tank_cflash <= 0) {
//        tank_setfirestatus(TANK_STATUS_NORMAL);
//      }
//      tank_selectaction();
//      //转动坦克机炮管
//      if ( (key_Press & KEY_LEFT) != 0) {
//        switch (tank_cannon_angle) {
//          case 0:
//          case 1:
//          case 2:
//          case 3:
//            tank_cannon_angle++;
//            break;
//          case 5:
//          case 6:
//          case 7:
//            tank_cannon_angle--;
//            break;
//        }
//      }
//      else if ( (key_Press & KEY_RIGH) != 0) {
//        switch (tank_cannon_angle) {
//          case 4:
//          case 3:
//          case 2:
//          case 1:
//            tank_cannon_angle--;
//            break;
//          case 5:
//          case 6:
//            tank_cannon_angle++;
//            break;
//          case 7:
//            tank_cannon_angle = 0;
//            break;
//        }
//      }
//      else if ( (key_Press & KEY_OVER) != 0) {
//        switch (tank_cannon_angle) {
//          case 3:
//          case 4:
//          case 5:
//            tank_cannon_angle--;
//            break;
//          case 6:
//          case 0:
//          case 1:
//            tank_cannon_angle++;
//            break;
//          case 7:
//            tank_cannon_angle = 0;
//            break;
//        }
//      }
//      else if ( (key_Press & KEY_DOWN) != 0) {
//        switch (tank_cannon_angle) {
//          case 2:
//          case 1:
//          case 7:
//            tank_cannon_angle--;
//            break;
//          case 3:
//          case 4:
//          case 5:
//            tank_cannon_angle++;
//            break;
//          case 0:
//            tank_cannon_angle = 7;
//            break;
//        }
//      }
//      //开炮
//      if ( (key_Press & KEY_FIRE) != 0) {
//        tank_cannon_fire_count = 3;
//      }
//      if (tank_cannon_fire_count > 0) {
//        tank_cannon_fire_count--;
//        tank_cannon_fire = true;
//        tank_cannon_fire_frame = framenum & 1;
//        if (tank_cannon_fire_frame == 1) {
//          byte[] frame = tankaction[tank_action][tank_cframe];
//          int firex = hero_pos_x - frame[TANK_FRAME_FIREX];
//          int firey = hero_pos_y - frame[TANK_FRAME_FIREY];
//          switch (tank_cannon_angle) {
//            case 0:
//              shot_create(2, firex + 10, firey, 2000, 0, DIRECT_RIGH, 1,
//                          (framenum >> 1) & 3);
//              break;
//            case 1:
//              shot_create(2, firex + 7, firey - 7, 1200, -1200, DIRECT_RIGHOVER,
//                          1, (framenum >> 1) & 3);
//              break;
//            case 2:
//              shot_create(2, firex, firey - 10, 0, -2000, DIRECT_OVER, 1,
//                          (framenum >> 1) & 3);
//              break;
//            case 3:
//              shot_create(2, firex - 7, firey - 7, -1200, -1200,
//                          DIRECT_RIGHOVER, 1, (framenum >> 1) & 3);
//              break;
//            case 4:
//              shot_create(2, firex - 10, firey, -2000, 0, DIRECT_RIGH, 1,
//                          (framenum >> 1) & 3);
//              break;
//            case 5:
//              shot_create(2, firex - 7, firey + 7, -1200, 1200, DIRECT_RIGHDOWN,
//                          1, (framenum >> 1) & 3);
//              break;
//            case 6:
//              shot_create(2, firex, firey + 10, 0, 2000, DIRECT_DOWN, 1,
//                          (framenum >> 1) & 3);
//              break;
//            case 7:
//              shot_create(2, firex + 7, firey + 7, 1200, 1200, DIRECT_LEFTDOWN,
//                          1, (framenum >> 1) & 3);
//              break;
//          }
//        }
//      }
//      else {
//        tank_cannon_fire = false;
//      }
//      tank_cframe = (tank_cframe < tankaction[tank_action].length - 1) ?
//          tank_cframe + 1 : 0;
//    }
//    hero_adScene(); //随着主角的移动 调整屏幕在地图上的位置
//  }
//
//  private final void tank_stand_key(int key_Press) {
//    switch (key_Press) {
//      case KEY_DOWN:
//        tank_setbasestatus(TANK_STATUS_SQUAT);
//        break;
//      case KEY_JUMP:
//        tank_setbasestatus(TANK_STATUS_INAIR);
//        tank_speedy = TANK_SPEED_START;
//        break;
//    }
//  }
//
//  private final void tank_stand(int key_Press) {
//    if (!tank_stan()) {
//      tank_setbasestatus(TANK_STATUS_INAIR);
//    }
//    else if ( (key_Press & KEY_LEFT) != 0) {
//      tank_setbasestatus(TANK_STATUS_STAND);
//      tank_move(DIRECT_LEFT);
//      tank_speedx = -7;
//    }
//    else if ( (key_Press & KEY_RIGH) != 0) {
//      tank_setbasestatus(TANK_STATUS_STAND);
//      tank_move(DIRECT_RIGH);
//      tank_speedx = 7;
//    }
//    else {
//      tank_setbasestatus(TANK_STATUS_STAND);
//      tank_speedx = 0;
//    }
//  }
//
//  private final void tank_squat_key(int key_Press) {
//    switch (key_Press) {
//      case KEY_JUMP:
//        tank_setbasestatus(TANK_STATUS_INAIR);
//        tank_speedy = TANK_SPEED_START;
//        break;
//      case KEY_OVER:
//        tank_setbasestatus(TANK_STATUS_STAND);
//        tank_speedx = 0;
//        break;
//    }
//  }
//
//  private final void tank_squat(int key_Press) {
//    if (!tank_stan()) {
//      tank_setbasestatus(TANK_STATUS_INAIR);
//    }
//    else if ( (key_Press & KEY_LEFT) != 0) {
//      tank_setbasestatus(TANK_STATUS_STAND);
//      tank_move(DIRECT_LEFT);
//      tank_speedx = -7;
//    }
//    else if ( (key_Press & KEY_RIGH) != 0) {
//      tank_setbasestatus(TANK_STATUS_STAND);
//      tank_move(DIRECT_RIGH);
//      tank_speedx = 7;
//    }
//  }
//
//  private final void tank_inair(int key_Press) {
//    if (tank_stan()) {
//      tank_setbasestatus(TANK_STATUS_STAND);
//      tank_speedy = 0;
//      tank_speedx = 0;
//      return;
//    }
//    hero_pos_y += tank_speedy / 10;
//    tank_speedy += TANK_SPEED_ACCEL;
//
//    if ( (key_Press & KEY_LEFT) != 0) {
//      tank_move(DIRECT_LEFT);
//    }
//    else if ( (key_Press & KEY_RIGH) != 0) {
//      tank_move(DIRECT_RIGH);
//    }
//  }
//
//  private final void tank_selectaction() {
//    switch (tank_status & 0xf0) {
//      case TANK_STATUS_NORMAL:
//        switch (tank_status & 0xf) {
//          case TANK_STATUS_STAND:
//            if (tank_speedx > 0) {
//              tank_setaction(TANK_ACTION_FORE);
//            }
//            else if (tank_speedx < 0) {
//              tank_setaction(TANK_ACTION_BACK);
//            }
//            else {
//              tank_setaction(TANK_ACTION_REST);
//            }
//            break;
//          case TANK_STATUS_SQUAT:
//            tank_setaction(TANK_ACTION_SQUA);
//            break;
//          case TANK_STATUS_INAIR:
//            if (tank_speedy > 10) {
//              tank_setaction(TANK_ACTION_JUMPDOWN);
//            }
//            else if (tank_speedy < -10) {
//              tank_setaction(TANK_ACTION_JUMPOVER);
//            }
//            else {
//              tank_setaction(TANK_ACTION_JUMPLEAP);
//            }
//            break;
//        }
//        break;
//      case TANK_STATUS_HITTED:
//        switch (tank_status & 0xf) {
//          case TANK_STATUS_STAND:
//            tank_setaction(TANK_ACTION_BRUI);
//            break;
//          case TANK_STATUS_SQUAT:
//            tank_setaction(TANK_ACTION_BRUI);
//            break;
//          case TANK_STATUS_INAIR:
//            tank_setaction(TANK_ACTION_JUMPBRUI);
//            break;
//        }
//        break;
//    }
//  }
//
//  private final boolean tank_stan() {
//    if (tank_speedy < 0) {
//      return false;
//    }
//    int i, v;
//    boolean rt = false, rt1 = false, rt2 = false;
//    int vert_start = hero_pos_y + tank_frectb;
//    int vert_close = hero_pos_y + tank_frectb + tank_speedy / 10;
//    int tank_linel = hero_pos_x - tank_frectw + 2;
//    int tank_liner = hero_pos_x + tank_frectw - 2;
//    for (i = vert_start; i <= vert_close; i++) {
//      v = grid_cellvalue(tank_linel >> 3, i >> 3);
//      rt1 |= (v == 1) || (v == 2);
//      v = grid_cellvalue(tank_liner >> 3, i >> 3);
//      rt2 |= (v == 1) || (v == 2);
//      if (rt1 || rt2) {
//        hero_pos_y = ( (i >> 3) << 3) - 11;
//        return true;
//      }
//      rt = false;
//      for (int j = chest.size() - 1; j >= 0; j--) {
//        int[] chet = (int[]) chest.elementAt(j);
//        int chet_l = chet[CHEST_POS_X];
//        int chet_r = chet_l + chet[CHEST_POS_W];
//        int chet_t = chet[CHEST_POS_Y];
//        int chet_b = chet_t + chet[CHEST_POS_H];
//        if ( (i > chet_t) && (i < chet_b)) {
//          rt |= (tank_linel > chet_l) && (tank_linel < chet_r);
//          rt |= (tank_liner > chet_l) && (tank_liner < chet_r);
//          rt |= (hero_pos_x > chet_l) && (hero_pos_x < chet_r);
//          if (rt) {
//            hero_pos_y = i - 11;
//            return true;
//          }
//        }
//      }
//    }
//    return false;
//  }
//
//  private final void tank_move(int direct) {
//    int tank_start = hero_pos_x + direct * tank_frectw;
//    int tank_close = hero_pos_x + direct * (tank_frectw + TANK_SPEED_HORIZ / 10);
//    int tank_linet = hero_pos_y - tank_frectt + 3;
//    int tank_lineb = hero_pos_y + tank_frectb - 3;
//
//    if ( (direct == DIRECT_LEFT) &&
//        ( (hero_pos_x - tank_frectw - TANK_SPEED_HORIZ / 10) < position_x)) {
//      return;
//    }
//    int x = tank_start;
//    boolean rt = false;
//    for (x = tank_start; x != tank_close; x += direct) {
//      rt |= grid_cellvalue(x >> 3, tank_linet >> 3) == 1;
//      rt |= grid_cellvalue(x >> 3, tank_lineb >> 3) == 1;
//      rt |= grid_cellvalue(x >> 3, hero_pos_y >> 3) == 1;
//      if (rt) {
//        break;
//      }
//      for (int j = chest.size() - 1; j >= 0; j--) {
//        int[] chet = (int[]) chest.elementAt(j);
//        int chet_l = chet[CHEST_POS_X];
//        int chet_t = chet[CHEST_POS_Y];
//        int chet_r = chet_l + chet[CHEST_POS_W];
//        int chet_b = chet_t + chet[CHEST_POS_H];
//        if ( (x > chet_l) && (x < chet_r)) {
//          rt |= (tank_linet > chet_t) && (tank_linet < chet_b);
//          rt |= (tank_lineb > chet_t) && (tank_lineb < chet_b);
//          rt |= (hero_pos_y > chet_t) && (hero_pos_y < chet_b);
//          if (rt) {
//            break;
//          }
//        }
//      }
//      if (rt) {
//        break;
//      }
//    }
//    if (rt) {
//      hero_pos_x = x - direct * tank_frectw;
//    }
//    else {
//      hero_pos_x += direct * TANK_SPEED_HORIZ / 10;
//    }
//  }

      //****************************************************************************
       /** @todo:炮管 */
       //****************************************************************************
//    private void cann_draw(int x, int y, int angle, boolean fire, int frame) {
//      switch (angle) {
//        case 0: //0
//          clipRect(x, y - 1, 11, 4);
//          //   drawFace(faceres[FACE_BIGCANNON], x, y - 12, 0);
//          if (fire) {
//            x += 10;
//            if (frame == 0) {
//              clipRect(x - 4, y - 5, 15, 11);
//              // drawFace(faceres[FACE_BIGCANNON], x - 27, y - 5, 0);
//            }
//            else {
//              clipRect(x - 8, y - 2, 16, 6);
//              //  drawFace(faceres[FACE_BIGCANNON], x - 8, y - 17, 0);
//            }
//          }
//          break;
//        case 1: //45
//          clipRect(x - 1, y - 7, 9, 9);
//          //drawFace(faceres[FACE_BIGCANNON], x - 1, y - 7, 0);
//          if (fire) {
//            x += 7;
//            y -= 7;
//            if (frame == 0) {
//              clipRect(x - 2, y - 8, 11, 12);
//              //  drawFace(faceres[FACE_BIGCANNON], x - 15, y - 8, 0);
//            }
//            else {
//              clipRect(x - 5, y - 4, 12, 11);
//              //drawFace(faceres[FACE_BIGCANNON], x - 5, y - 25, 0);
//            }
//          }
//          break;
//        case 2: //90
//          clipRect(x - 1, y - 10, 4, 11);
//          //drawFace(faceres[FACE_BIGCANNON], x - 10, y - 10, 0);
//          if (fire) {
//            y -= 10;
//            if (frame == 0) {
//              clipRect(x - 4, y - 10, 11, 14);
//              // drawFace(faceres[FACE_BIGCANNON], x - 20, y - 25, 0);
//            }
//            else {
//              clipRect(x - 2, y - 7, 11, 4);
//              //  drawFace(faceres[FACE_BIGCANNON], x - 31, y - 21, 0);
//            }
//          }
//          break;
//        case 3: //135
//          clipRect(x - 7, y - 7, 9, 9);
//          //     drawFace(faceres[FACE_BIGCANNON], x - 7, y - 37, 90);
//          if (fire) {
//            x -= 7;
//            y -= 7;
//            if (frame == 0) {
//              clipRect(x - 8, y - 8, 12, 11);
//              //  drawFace(faceres[FACE_BIGCANNON], x - 8, y - 23, 90);
//            }
//            else {
//              clipRect(x - 4, y - 6, 11, 12);
//              // drawFace(faceres[FACE_BIGCANNON], x - 25, y - 33, 90);
//            }
//          }
//          break;
//        case 4: //180
//          clipRect(x - 10, y - 2, 11, 4);
//          // drawFace(faceres[FACE_BIGCANNON], x - 10, y - 28, 90);
//          if (fire) {
//            x -= 10;
//            if (frame == 0) {
//              clipRect(x - 10, y - 5, 14, 11);
//              //  drawFace(faceres[FACE_BIGCANNON], x - 25, y - 18, 90);
//            }
//            else {
//              clipRect(x - 7, y - 2, 16, 6);
//              // drawFace(faceres[FACE_BIGCANNON], x - 21, y - 7, 90);
//            }
//          }
//          break;
//        case 5: //225
//          clipRect(x - 7, y - 1, 9, 9);
//          //drawFace(faceres[FACE_BIGCANNON], x - 37, y - 24, 180);
//          if (fire) {
//            x -= 7;
//            y += 7;
//            if (frame == 0) {
//              clipRect(x - 8, y - 3, 11, 12);
//              //  drawFace(faceres[FACE_BIGCANNON], x - 23, y - 23, 180);
//            }
//            else {
//              clipRect(x - 6, y - 6, 12, 11);
//              //  drawFace(faceres[FACE_BIGCANNON], x - 33, y - 6, 180);
//            }
//          }
//          break;
//        case 6: //270
//          clipRect(x - 2, y, 4, 11);
//          //   drawFace(faceres[FACE_BIGCANNON], x - 28, y - 21, 180);
//          if (fire) {
//            y += 10;
//            if (frame == 0) {
//              clipRect(x - 6, y - 4, 11, 14);
//              // drawFace(faceres[FACE_BIGCANNON], x - 18, y - 6, 180);
//            }
//            else {
//              clipRect(x - 3, y - 8, 6, 16);
//              //   drawFace(faceres[FACE_BIGCANNON], x - 7, y - 10, 180);
//            }
//          }
//          break;
//        case 7: //315
//          clipRect(x - 1, y - 1, 9, 9);
//          //  drawFace(faceres[FACE_BIGCANNON], x - 24, y - 1, 270);
//          if (fire) {
//            x += 7;
//            y += 7;
//            if (frame == 0) {
//              clipRect(x - 3, y - 2, 12, 11);
//              // drawFace(faceres[FACE_BIGCANNON], x - 23, y - 15, 270);
//            }
//            else {
//              clipRect(x - 6, y - 5, 11, 12);
//              //   drawFace(faceres[FACE_BIGCANNON], x - 6, y - 5, 270);
//            }
//          }
//          break;
//      }
//    }

//******************************************************************************
         /** @todo:自走炮 */
//******************************************************************************
          private Vector zguns = new Vector();
  private final int ZGUN_POS_W = 21;
  private final int ZGUN_POS_H = 19;

  private final int ZGUN_COUNT = 7;
  private final int ZGUN_POS_X = 0;
  private final int ZGUN_POS_Y = 1;
  private final int ZGUN_ANGLE = 2;
  private final int ZGUN_TALLY = 3;
  private final int ZGUN_STATE = 4;
  private final int ZGUN_ROTAT = 5;
  private final int ZGUN_ANIMA = 6;
  private final void zgun_load(InputStream in) throws IOException {
    int total = in.read();
    for (int i = 0; i < total; i++) {
      int[] zgun = new int[ZGUN_COUNT];
      zgun[ZGUN_POS_X] = in.read() << 3;
      zgun[ZGUN_POS_Y] = in.read() << 3;
      zgun[ZGUN_ANGLE] = 0;
      zgun[ZGUN_TALLY] = 0;
      zgun[ZGUN_STATE] = 0;
      zgun[ZGUN_ROTAT] = (in.read() != 0) ? 0X4000 : 0;
      zgun[ZGUN_ANIMA] = 3;
      zguns.addElement(zgun);
    }
  }

  private final void zgun_exec() {
    for (int i = zguns.size() - 1; i >= 0; i--) {
      int[] zgun = (int[]) zguns.elementAt(i);
      int x = zgun[ZGUN_POS_X] + 11;
      int y = zgun[ZGUN_POS_Y] + 10;
      zgun[ZGUN_TALLY]--;
      if (zgun[ZGUN_TALLY] <= 0) {
        zgun[ZGUN_TALLY] = 20;
        zgun[ZGUN_STATE] = 3;
      }
      if (zgun[ZGUN_STATE] > 0) {
        if (zgun[ZGUN_ROTAT] == 0) {
          switch (zgun[ZGUN_ANGLE]) {
            case 0:
              shot_create(3, zgun[ZGUN_POS_X], zgun[ZGUN_POS_Y] + 8, -1000, 0,
                          DIRECT_LEFT, 0, 0);
              break;
            case 1:
              shot_create(3, zgun[ZGUN_POS_X] + 2, zgun[ZGUN_POS_Y] + 3, -600,
                          -600, DIRECT_LEFTOVER, 0, 0);
              break;
            case 3:
              shot_create(3, zgun[ZGUN_POS_X] + 10, zgun[ZGUN_POS_Y], 0, -1000,
                          DIRECT_OVER, 0, 0);
              break;
            case 5:
              shot_create(3, zgun[ZGUN_POS_X] + 19, zgun[ZGUN_POS_Y] + 3, 600,
                          -600, DIRECT_RIGHOVER, 0, 0);
              break;
            case 6:
              shot_create(3, zgun[ZGUN_POS_X] + 20, zgun[ZGUN_POS_Y] + 8, 1000,
                          0, DIRECT_RIGH, 0, 0);
              break;
          }
        }
        else {
          switch (zgun[ZGUN_ANGLE]) {
            case 0:
              shot_create(3, zgun[ZGUN_POS_X], zgun[ZGUN_POS_Y] + 10, -1000, 0,
                          DIRECT_LEFT, 0, 0);
              break;
            case 1:
              shot_create(3, zgun[ZGUN_POS_X] + 2, zgun[ZGUN_POS_Y] + 16, -600,
                          600, DIRECT_LEFTOVER, 0, 0);
              break;
            case 3:
              shot_create(3, zgun[ZGUN_POS_X] + 10, zgun[ZGUN_POS_Y] + 18, 0,
                          1000, DIRECT_OVER, 0, 0);
              break;
            case 5:
              shot_create(3, zgun[ZGUN_POS_X] + 19, zgun[ZGUN_POS_Y] + 16, 600,
                          600, DIRECT_RIGHOVER, 0, 0);
              break;
            case 6:
              shot_create(3, zgun[ZGUN_POS_X] + 20, zgun[ZGUN_POS_Y] + 10, 1000,
                          0, DIRECT_RIGH, 0, 0);
              break;
          }
        }
        zgun[ZGUN_STATE]--;
      }
      else {
        if (hero_pos_x == x) {
          zgun[ZGUN_ANGLE] = 3;
        }
        else {
          int tan = Math.abs(hero_pos_y - y) * 1000 / (hero_pos_x - x);
          if (tan < -2414) {
            zgun[ZGUN_ANGLE] = 3;
          }
          else
          if (tan < -414) {
            zgun[ZGUN_ANGLE] = 1;
          }
          else
          if (tan < 0) {
            zgun[ZGUN_ANGLE] = 0;
          }
          else
          if (tan < 414) {
            zgun[ZGUN_ANGLE] = 6;
          }
          else
          if (tan < 2414) {
            zgun[ZGUN_ANGLE] = 5;
          }
          else {
            zgun[ZGUN_ANGLE] = 3;
          }
        }
      }
    }
  }

  private final boolean zgun_nexus(int[] zgun, int x, int y, int r, int b) {
    return (zgun[ZGUN_ROTAT] == 0) ?
        ! ( (zgun[ZGUN_POS_X] > r) || (zgun[ZGUN_POS_Y] > b) ||
           (zgun[ZGUN_POS_X] + ZGUN_POS_W < x) ||
           (zgun[ZGUN_POS_Y] + ZGUN_POS_H < y)) :
        ! ( (zgun[ZGUN_POS_X] > r) || (zgun[ZGUN_POS_Y] > b) ||
           (zgun[ZGUN_POS_X] + ZGUN_POS_W < x) ||
           (zgun[ZGUN_POS_Y] + ZGUN_POS_H < y));
  }

  private final void zgun_hitted(int[] zgun, int attack) {
    zgun[ZGUN_ANIMA] -= attack;
    if (zgun[ZGUN_ANIMA] <= 0) {
      movi_create(4, zgun[ZGUN_POS_X] + 10, zgun[ZGUN_POS_Y] + 9, 0, 0, 0, 0,
                  DIRECT_LEFT, MOVI_DEATH_ONCE);
      zguns.removeElement(zgun);
    }
  }

  private final void zgun_draw() {
    for (int i = zguns.size() - 1; i >= 0; i--) {
      int[] zgun = (int[]) zguns.elementAt(i);
      if (visible(zgun[ZGUN_POS_X], zgun[ZGUN_POS_Y],
                  zgun[ZGUN_POS_X] + ZGUN_POS_W, zgun[ZGUN_POS_Y] + ZGUN_POS_H)) {
        clipRect(zgun[ZGUN_POS_X], zgun[ZGUN_POS_Y], ZGUN_POS_W, ZGUN_POS_H);
        switch (zgun[ZGUN_ANGLE]) {
          case 0:

            // drawFace(faceres[FACE_ZIZOU_GUN], zgun[ZGUN_POS_X] - 21,
            //         zgun[ZGUN_POS_Y], zgun[ZGUN_ROTAT]);
            break;
          case 1:

//            drawFace(faceres[FACE_ZIZOU_GUN], zgun[ZGUN_POS_X], zgun[ZGUN_POS_Y],
//                     zgun[ZGUN_ROTAT]);
            break;
          case 2:

//            drawFace(faceres[FACE_ZIZOU_GUN], zgun[ZGUN_POS_X] - 63,
//                     zgun[ZGUN_POS_Y], zgun[ZGUN_ROTAT]);
            break;
          case 3:

//            drawFace(faceres[FACE_ZIZOU_GUN], zgun[ZGUN_POS_X] - 42,
//                     zgun[ZGUN_POS_Y], zgun[ZGUN_ROTAT]);
            break;
          case 4:

//            drawFace(faceres[FACE_ZIZOU_GUN], zgun[ZGUN_POS_X], zgun[ZGUN_POS_Y],
//                     zgun[ZGUN_ROTAT] | 0x2000);
            break;
          case 5:

//            drawFace(faceres[FACE_ZIZOU_GUN], zgun[ZGUN_POS_X] - 63,
//                     zgun[ZGUN_POS_Y], zgun[ZGUN_ROTAT] | 0x2000);
            break;
          case 6:

//            drawFace(faceres[FACE_ZIZOU_GUN], zgun[ZGUN_POS_X] - 42,
//                     zgun[ZGUN_POS_Y], zgun[ZGUN_ROTAT] | 0x2000);
            break;
        }
      }
    }
  }

  //****************************************************************************
   /** @todo:子弹 */
   //****************************************************************************
//    private final int SHOT_STATE_MOVE = 0; //子弹移动状态
    private final int SHOT_STATE_BOMB = 1; //子弹爆开状态

//  private final int SHOT_MOVESTYLE_LINE = 0; //直线移动子弹
  private final int SHOT_MOVESTYLE_PARA = 1; //抛物运动子弹
  private Image[][] shotimages; //子弹用到的图形
  private int[][] shotdefine; //子弹定义数据
  private final int SHOT_DEFIN_COUNT = 19;
  private final int SHOT_DEFIN_MOVE_RESID = 0;
  private final int SHOT_DEFIN_BOMB_RESID = 1;
  private final int SHOT_DEFIN_MOVE_FRAME = 2;
  private final int SHOT_DEFIN_BOMB_FRAME = 3;

  private final int SHOT_DEFIN_MOVE_STYLE = 4;
  private final int SHOT_DEFIN_FIRE_STYLE = 5;
  private final int SHOT_DEFIN_FIRE_DAMAG = 6;

  private final int SHOT_DEFIN_MOVE_POS_X = 7;
  private final int SHOT_DEFIN_MOVE_POS_Y = 8;
  private final int SHOT_DEFIN_BOMB_POS_X = 9;
  private final int SHOT_DEFIN_BOMB_POS_Y = 10;

  private final int SHOT_DEFIN_MOVE_FRAMW = 11;
  private final int SHOT_DEFIN_MOVE_FRAMH = 12;
  private final int SHOT_DEFIN_MOVE_FACEW = 13;
  private final int SHOT_DEFIN_MOVE_FACEH = 14;

  private final int SHOT_DEFIN_BOMB_FRAMW = 15;
  private final int SHOT_DEFIN_BOMB_FRAMH = 16;
  private final int SHOT_DEFIN_BOMB_FACEW = 17;
  private final int SHOT_DEFIN_BOMB_FACEH = 18;

  private Vector shots = new Vector();
  private final int SHOT_COUNT = 11;
  private final int SHOT_DEFIN = 0; //定义索引
  private final int SHOT_POS_X = 1; //位置X
  private final int SHOT_POS_Y = 2; //位置Y
  private final int SHOT_DIREC = 3; //方向
  private final int SHOT_FRAME = 4; //帧当前
  private final int SHOT_STATE = 5; //状态
  private final int SHOT_SPD_H = 6; //水平速度
  private final int SHOT_SPD_V = 7; //竖直速度
  private final int SHOT_HEROS = 8; //是否主角的子弹
  private final int SHOT_ACCEL_X = 9;
  private final int SHOT_ACCEL_Y = 10;

  /**********************************************
   * 装载子弹定义数据
   **********************************************/
  private final void shot_loaddefine() {
    InputStream in = stream_create("/shot.bin");
    try {
      shotdefine = new int[in.read()][SHOT_DEFIN_COUNT];
      shotimages = new Image[shotdefine.length][2];
      for (int i = 0; i < shotdefine.length; i++) {
        shotdefine[i][SHOT_DEFIN_MOVE_RESID] = in.read();
        shotdefine[i][SHOT_DEFIN_BOMB_RESID] = in.read();
        shotdefine[i][SHOT_DEFIN_MOVE_FRAME] = in.read();
        shotdefine[i][SHOT_DEFIN_BOMB_FRAME] = in.read();

        shotdefine[i][SHOT_DEFIN_MOVE_STYLE] = readByte(in);
        shotdefine[i][SHOT_DEFIN_FIRE_STYLE] = readByte(in);
        shotdefine[i][SHOT_DEFIN_FIRE_DAMAG] = readByte(in);

        shotdefine[i][SHOT_DEFIN_MOVE_POS_X] = in.read();
        shotdefine[i][SHOT_DEFIN_MOVE_POS_Y] = in.read();
        shotdefine[i][SHOT_DEFIN_BOMB_POS_X] = in.read();
        shotdefine[i][SHOT_DEFIN_BOMB_POS_Y] = in.read();
        shotimages[i][0] = platres[shotdefine[i][SHOT_DEFIN_MOVE_RESID]];
        shotimages[i][1] = platres[shotdefine[i][SHOT_DEFIN_BOMB_RESID]];
        shotdefine[i][SHOT_DEFIN_MOVE_FACEW] = shotimages[i][0].getWidth();
        shotdefine[i][SHOT_DEFIN_MOVE_FACEH] = shotimages[i][0].getHeight();
        shotdefine[i][SHOT_DEFIN_MOVE_FRAMW] = shotimages[i][0].getWidth() /
            shotdefine[i][SHOT_DEFIN_MOVE_FRAME];
        shotdefine[i][SHOT_DEFIN_MOVE_FRAMH] = shotimages[i][0].getHeight();

        shotdefine[i][SHOT_DEFIN_BOMB_FACEW] = shotimages[i][1].getWidth();
        shotdefine[i][SHOT_DEFIN_BOMB_FACEH] = shotimages[i][1].getHeight();
        shotdefine[i][SHOT_DEFIN_BOMB_FRAMW] = shotimages[i][1].getWidth() /
            shotdefine[i][SHOT_DEFIN_BOMB_FRAME];
        shotdefine[i][SHOT_DEFIN_BOMB_FRAMH] = shotimages[i][1].getHeight();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**********************************************
   * 子弹新建
   * @param shot_defin int 子弹定义索引
   * @param shotx int 子弹位置
   * @param shoty int 子弹位置
   * @param spd_x int 子弹水平速度
   * @param spd_y int 子弹垂直速度
   * @param direc int 子弹方向
   * @param heros int 子弹所属
   **********************************************/
  private final void shot_create(int shot_defin, int shotx, int shoty,
                                 int spd_x,
                                 int spd_y, int direc, int heros, int frame) {
    int[] shot = new int[SHOT_COUNT];
    shot[SHOT_DEFIN] = shot_defin;
    shot[SHOT_POS_X] = shotx;
    shot[SHOT_POS_Y] = shoty;
    shot[SHOT_DIREC] = direc;
    shot[SHOT_FRAME] = frame;
    shot[SHOT_STATE] = 0;
    shot[SHOT_SPD_H] = spd_x;
    shot[SHOT_SPD_V] = spd_y;
    shot[SHOT_HEROS] = heros;
    shot[SHOT_ACCEL_X] = 100;
    shot[SHOT_ACCEL_Y] = 100;
    shots.addElement(shot);
  };

  /**********************************************
   * 子弹绘制
   **********************************************/
  private final void shot_draw() {
    for (int i = shots.size() - 1; i >= 0; i--) {
      int[] shot_object = (int[]) shots.elementAt(i);
      int[] shot_define = shotdefine[shot_object[SHOT_DEFIN]];
      int state = shot_object[SHOT_STATE];
      int framw = (state == 0) ? shot_define[SHOT_DEFIN_MOVE_FRAMW] :
          shot_define[SHOT_DEFIN_BOMB_FRAMW];
      int framh = (state == 0) ? shot_define[SHOT_DEFIN_MOVE_FRAMH] :
          shot_define[SHOT_DEFIN_BOMB_FRAMH];
      int facew = (state == 0) ? shot_define[SHOT_DEFIN_MOVE_FACEW] :
          shot_define[SHOT_DEFIN_BOMB_FACEW];
      int linkx = (state == 0) ? shot_define[SHOT_DEFIN_MOVE_POS_X] :
          shot_define[SHOT_DEFIN_BOMB_POS_X];
      int linky = (state == 0) ? shot_define[SHOT_DEFIN_MOVE_POS_Y] :
          shot_define[SHOT_DEFIN_BOMB_POS_Y];
      int shot_draw_x = 0, shot_draw_y = 0, shot_rotate = 0;
      switch (shot_object[SHOT_DIREC]) {
        case DIRECT_RIGHOVER:
        case DIRECT_RIGHDOWN:
        case DIRECT_LEFTOVER:
        case DIRECT_LEFTDOWN:
        case DIRECT_LEFT:
          shot_draw_x = shot_object[SHOT_POS_X] - linkx;
          shot_draw_y = shot_object[SHOT_POS_Y] - linky;
          shot_rotate = 0;
          clipRect(shot_draw_x, shot_draw_y, framw, framh);
          drawFace(shotimages[shot_object[SHOT_DEFIN]][state],
                   shot_draw_x - shot_object[SHOT_FRAME] * framw,
                   shot_draw_y,
                   shot_rotate);
          break;
        case DIRECT_RIGH:
          shot_draw_x = shot_object[SHOT_POS_X] + linkx - framw;
          shot_draw_y = shot_object[SHOT_POS_Y] - linky;
          shot_rotate = DirectGraphics.FLIP_HORIZONTAL;
          clipRect(shot_draw_x, shot_draw_y, framw, framh);
          drawFace(shotimages[shot_object[SHOT_DEFIN]][state],
                   shot_draw_x + shot_object[SHOT_FRAME] * framw + framw -
                   facew,
                   shot_draw_y,
                   shot_rotate);
          break;
        case DIRECT_OVER:
          shot_draw_x = shot_object[SHOT_POS_X] + linky - framh;
          shot_draw_y = shot_object[SHOT_POS_Y] - linkx;
          shot_rotate = DirectGraphics.ROTATE_270;
          clipRect(shot_draw_x, shot_draw_y, framh, framw);
          drawFace(shotimages[shot_object[SHOT_DEFIN]][state],
                   shot_draw_x,
                   shot_draw_y - shot_object[SHOT_FRAME] * framw,
                   shot_rotate);
          break;
        case DIRECT_DOWN:
          shot_draw_x = shot_object[SHOT_POS_X] - linky;
          shot_draw_y = shot_object[SHOT_POS_Y] + linkx - framh;
          shot_rotate = DirectGraphics.ROTATE_90;
          clipRect(shot_draw_x, shot_draw_y, framh, framw);
          drawFace(shotimages[shot_object[SHOT_DEFIN]][state],
                   shot_draw_x,
                   shot_draw_y + shot_object[SHOT_FRAME] * framw + framw -
                   facew,
                   shot_rotate);
          break;
      }
    }
  }

  /**********************************************
   * 子弹移动
   **********************************************/
  private final void shot_run() {
    for (int i = shots.size() - 1; i >= 0; i--) {
      int[] shot = (int[]) shots.elementAt(i);
      int[] defin = shotdefine[shot[SHOT_DEFIN]];
      //子弹处于爆炸状态
      if (shot[SHOT_STATE] == SHOT_STATE_BOMB) {
        if (shot[SHOT_FRAME] < defin[SHOT_DEFIN_BOMB_FRAME] - 1) {
          shot[SHOT_FRAME]++;
        }
        else {
          shots.removeElementAt(i);
        }
        continue;
      }
      //读取子弹的数据
      int framw = defin[SHOT_DEFIN_MOVE_FRAMW];
      int framh = defin[SHOT_DEFIN_MOVE_FRAMH];
      int linkx = defin[SHOT_DEFIN_MOVE_POS_X];
      int linky = defin[SHOT_DEFIN_MOVE_POS_Y];
      int shot_rect_x, shot_rect_y, shot_rect_r, shot_rect_b;
      switch (shot[SHOT_DIREC]) {
        case DIRECT_RIGHOVER:
        case DIRECT_RIGHDOWN:
        case DIRECT_LEFTOVER:
        case DIRECT_LEFTDOWN:
        case DIRECT_LEFT:
          shot_rect_x = shot[SHOT_POS_X] - linkx;
          shot_rect_y = shot[SHOT_POS_Y] - linky;
          shot_rect_r = shot_rect_x + framw;
          shot_rect_b = shot_rect_y + framh;
          break;
        case DIRECT_RIGH:
          shot_rect_x = shot[SHOT_POS_X] + linkx - framw;
          shot_rect_y = shot[SHOT_POS_Y] - linky;
          shot_rect_r = shot_rect_x + framw;
          shot_rect_b = shot_rect_y + framh;
          break;
        case DIRECT_OVER:
          shot_rect_x = shot[SHOT_POS_X] + linky - framh;
          shot_rect_y = shot[SHOT_POS_Y] - linkx;
          shot_rect_r = shot_rect_x + framh;
          shot_rect_b = shot_rect_y + framw;
          break;
        case DIRECT_DOWN:
          shot_rect_x = shot[SHOT_POS_X] - linky;
          shot_rect_y = shot[SHOT_POS_Y] + linkx - framh;
          shot_rect_r = shot_rect_x + framh;
          shot_rect_b = shot_rect_y + framw;
          break;
        default:
          shot_rect_x = 0;
          shot_rect_y = 0;
          shot_rect_r = 0;
          shot_rect_b = 0;
      }
      //如果子弹飞出了屏幕 删除这个子弹
      if (!visible(shot_rect_x, shot_rect_y, shot_rect_r, shot_rect_b)) {
        shots.removeElementAt(i);
        continue;
      }
      boolean touch = false;
      //判断子弹与主角或者敌军发生碰撞
      if (shot[SHOT_HEROS] == 1) { //主角的子弹
        for (int j = ogres.size() - 1; j >= 0; j--) { //判断攻击怪物
          int[] ogre = (int[]) ogres.elementAt(j);
          if (ogre[OGRE_DEATH] == 1) {
            continue;
          }
          int[] ogre_frame = ogre_getframe(ogre);
          int ogrew = ogre_frame[OGRE_FRAME_RECTW];
          int ogret = ogre_frame[OGRE_FRAME_RECTT];
          int ogrex = ogre[OGRE_POS_X] - ogrew;
          int ogrey = ogre[OGRE_POS_Y] - ogret;
          int ogrer = ogre[OGRE_POS_X] + ogrew;
          int ogreb = ogre[OGRE_POS_Y] + 11;
          if (!visible(ogrex, ogrey, ogrer, ogreb)) {
            continue;
          }
          if ( (ogrer > shot_rect_x) && (ogreb > shot_rect_y) &&
              (ogrex < shot_rect_r) && (ogrey < shot_rect_b)) {
            ogre_hitted(ogre, defin[SHOT_DEFIN_FIRE_DAMAG]);
            hero_addscore(10);
            touch = true;
          }
        }
        for (int j = pawns.size() - 1; j >= 0; j--) {
          int[] pawn = (int[]) pawns.elementAt(j);
          if (pawn[PAWN_STATE] == 0) {
            int pawn_l = pawn[PAWN_POS_X] - PAWN_HALFW;
            int pawn_t = pawn[PAWN_POS_Y] - PAWN_HALFH;
            int pawn_r = pawn[PAWN_POS_X] + PAWN_HALFW;
            int pawn_b = pawn[PAWN_POS_Y] + PAWN_HALFH;
            if ( (shot_rect_r > pawn_l) && (shot_rect_b > pawn_t) &&
                (shot_rect_x < pawn_r) && (shot_rect_y < pawn_b)) {
              pawn_rescue(pawn);
              touch = true;
            }
          }
        }
        for (int j = chest.size() - 1; j >= 0; j--) {
          int[] chet = (int[]) chest.elementAt(j);
          int chet_l = chet[CHEST_POS_X];
          int chet_t = chet[CHEST_POS_Y];
          int chet_r = chet_l + chet[CHEST_POS_W];
          int chet_b = chet_t + chet[CHEST_POS_H];
          if ( (shot_rect_r > chet_l) && (shot_rect_b > chet_t) &&
              (shot_rect_x < chet_r) && (shot_rect_y < chet_b)) {
            chet_hitted(chet, defin[SHOT_DEFIN_FIRE_DAMAG]);
            touch = true;
          }
        }
        for (int j = zguns.size() - 1; j >= 0; j--) {
          int[] zgun = (int[]) zguns.elementAt(j);
          if (zgun_nexus(zgun, shot_rect_x, shot_rect_y, shot_rect_r,
                         shot_rect_b)) {
            zgun_hitted(zgun, defin[SHOT_DEFIN_FIRE_DAMAG]);
            touch = true;
          }
        }
      }
      else {
        switch (hero_style) {
          case 0:
            touch = hero_nexus(shot_rect_x, shot_rect_y, shot_rect_r,
                               shot_rect_b);
            if (touch) {
              hero_hitted(defin[SHOT_DEFIN_FIRE_DAMAG]);
            }
            break;
//          case 1:
//            touch = plan_nexus(shot_rect_x, shot_rect_y, shot_rect_r,
//                               shot_rect_b);
//            if (touch) {
//              plan_hitted(defin[SHOT_DEFIN_FIRE_DAMAG]);
//            }
//            break;
//          case 2:
//            touch = tank_nexus(shot_rect_x, shot_rect_y, shot_rect_r,
//                               shot_rect_b);
//            if (touch) {
//              tank_hitted(defin[SHOT_DEFIN_FIRE_DAMAG]);
//            }
//            break;
        }
      }
      if (!touch) { //判断子弹是否和墙壁发生碰撞
        int x = 0, y = 0;
        int shot_pos_x = shot[SHOT_POS_X];
        int shot_pos_y = shot[SHOT_POS_Y];
        int shot_spd_x = shot[SHOT_SPD_H] / 100;
        int shot_spd_y = shot[SHOT_SPD_V] / 100;
        int shot_sign_x = (shot[SHOT_SPD_H] > 0) ? 1 :
            ( (shot[SHOT_SPD_H] < 0) ? -1 : 0);
        int shot_sign_y = (shot[SHOT_SPD_V] > 0) ? 1 :
            ( (shot[SHOT_SPD_V] < 0) ? -1 : 0);

        if ( (shot_sign_x != 0) && (shot_sign_y != 0)) {
          if (Math.abs(shot_spd_y) > Math.abs(shot_spd_x)) {
            for (y = 0; y != shot_spd_y; y += shot_sign_y) {
              x = y * shot_spd_x / shot_spd_y;
              if (grid_cellvalue( (shot_pos_x + x) >> 3, (shot_pos_y + y) >> 3) ==
                  CELL_WALL) {
                touch = true;
                shot[SHOT_POS_X] = shot_pos_x + x;
                shot[SHOT_POS_Y] = shot_pos_y + y;
                break;
              }
            }
          }
          else {
            for (x = 0; x != shot_spd_x; x += shot_sign_x) {
              y = x * shot_spd_y / shot_spd_x;
              if (grid_cellvalue( (shot_pos_x + x) >> 3, (shot_pos_y + y) >> 3) ==
                  CELL_WALL) {
                touch = true;
                shot[SHOT_POS_X] = shot_pos_x + x;
                shot[SHOT_POS_Y] = shot_pos_y + y;
                break;
              }
            }
          }
        }
        else if (shot_sign_x != 0) {
          y = 0;
          for (x = 0; x != shot_spd_x; x += shot_sign_x) {
            if (grid_cellvalue( (shot_pos_x + x) >> 3, (shot_pos_y + y) >> 3) ==
                CELL_WALL) {
              touch = true;
              shot[SHOT_POS_X] = shot_pos_x + x;
              shot[SHOT_POS_Y] = shot_pos_y + y;
              break;
            }
          }
        }
        else if (shot_sign_y != 0) {
          x = 0;
          for (y = 0; y != shot_spd_y; y += shot_sign_y) {
            if (grid_cellvalue( (shot_pos_x + x) >> 3, (shot_pos_y + y) >> 3) ==
                CELL_WALL) {
              touch = true;
              shot[SHOT_POS_X] = shot_pos_x + x;
              shot[SHOT_POS_Y] = shot_pos_y + y;
              break;
            }
          }
        }
      }
      if (touch) { //如果子弹发生碰撞 转换子弹状态
        shot[SHOT_STATE] = 1;
        shot[SHOT_FRAME] = 0;
      }
      else { //控制子弹移动
        shot[SHOT_POS_X] += shot[SHOT_SPD_H] / 100;
        shot[SHOT_POS_Y] += shot[SHOT_SPD_V] / 100;
        if (defin[SHOT_DEFIN_MOVE_STYLE] == SHOT_MOVESTYLE_PARA) {
          shot[SHOT_SPD_V] += shot[SHOT_ACCEL_Y];
        }
        if (shot[SHOT_FRAME] < defin[SHOT_DEFIN_MOVE_FRAME] - 1) {
          shot[SHOT_FRAME]++;
        }
        else {
          shot[SHOT_FRAME] = 0;
        }
      }
    }
  }

  //****************************************************************************
   /** @todo: 怪物 */
   //****************************************************************************
    private Vector ogres = new Vector(); //存储怪物对象的向量
  private final int OGRE_COUNT = 15; //怪物对象的数据的索引定义
  private final int OGRE_DEFIN = 0; //怪物定义的索引
  private final int OGRE_RESID = 1; //怪物图形资源索引
  private final int OGRE_POS_X = 2; //怪物位置X
  private final int OGRE_POS_Y = 3; //怪物位置Y
  private final int OGRE_FRAME = 4; //怪物当前帧
  private final int OGRE_DIREC = 5; //怪物当前方向
  private final int OGRE_STATE = 6; //怪物当前状态
  private final int OGRE_DEATH = 7; //怪物无敌状态计数
  private final int OGRE_ANIMA = 8; //怪物生命值
  private final int OGRE_TALLY = 9; //攻击计数器 用来控制攻击速度得计数器
  private final int OGRE_FIREE = 10; //BOSS用的火焰攻击计数器
  private final int OGRE_MOVED = 11; //BOSS用的移动量
  private final int OGRE_TEMP0 = 12; //临时变量
  private final int OGRE_SHOTT = 13; //BOSS子弹发出计数
  private final int OGRE_SHTLR = 14; //BOSS子弹发出位置

  //怪物状态常数
  private final int OGRE_ACTION_COUNT = 6;
  private final int OGRE_ACTION_STAN = 0; //站立
  private final int OGRE_ACTION_MOVE = 1; //移动
  private final int OGRE_ACTION_FIRE = 2; //攻击
  private final int OGRE_ACTION_HITD = 3; //受伤
  private final int OGRE_ACTION_SCAR = 4; //惊吓
  private final int OGRE_ACTION_DEAD = 5; //死亡

  //怪物的AI模式
  private final int OGRE_AIMODE_TURN_TAIL = 0; //模式0：主角靠近逃跑
  private final int OGRE_AIMODE_MOVE_FIRE = 1; //模式1：来回移动攻击靠近的主角
  private final int OGRE_AIMODE_CYCLE_FLY = 2; //模式2：来回飞行攻击靠近的株距
  private final int OGRE_AIMODE_BOSS = 3; //模式3：BOSS

  //怪物移动常数
//  private final int OGRE_MOVE_WALK = 0; //简单走动
//  private final int OGRE_MOVE_WING = 1; //曲线飞行
//  private final int OGRE_MOVE_CYCL = 2; //循环飞行

  //怪物攻击常数
//  private final int OGRE_FIRE_REMOT = 0; //远程攻击
//  private final int OGRE_FIRE_CLOSE = 1; //近身攻击
//
//  //怪物类型
//  private final int OGRE_SORT_SOLDIER = 0; //士兵
//  private final int OGRE_SORT_MACHINE = 1; //机械

  private int[][] ogredefine; //怪物基本设定信息
  private final int OGRE_DEFIN_COUNT = 16;
  private final int OGRE_DEFIN_RESID = 0; //怪物图形索引
  private final int OGRE_DEFIN_ANIMA = 1;
  private final int OGRE_DEFIN_ATTAK = 2;
  private final int OGRE_DEFIN_SCORE = 3;
  private final int OGRE_DEFIN_AIMOD = 4;
  private final int OGRE_DEFIN_MVSPD = 5;
  private final int OGRE_DEFIN_AKSPD = 6;
  private final int OGRE_DEFIN_AKFME = 7;
  private final int OGRE_DEFIN_FIREW = 8; //警戒范围W的一半
  private final int OGRE_DEFIN_FIREH = 9; //警戒范围H的一半
  private final int OGRE_DEFIN_STYLE = 10;
  private final int OGRE_DEFIN_SHOTX = 11; //子弹初始速度X
  private final int OGRE_DEFIN_SHOTY = 12; //子弹初始速度Y
  private final int OGRE_DEFIN_ARMID = 13;
  private final int OGRE_DEFIN_BLAST = 14;
  private final int OGRE_DEFIN_CLIPS = 15;

  private int[][][][] ogreaction; //怪物动作定义

  //怪物帧内数据索引常数
  private final int OGRE_FRAME_COUNT = 38;
  private final int OGRE_FRAME_RECTW = 0;
  private final int OGRE_FRAME_RECTT = 1;
  private final int OGRE_FRAME_FIREX = 2;
  private final int OGRE_FRAME_FIREY = 3;
  private final int OGRE_FRAME_FIREW = 4;
  private final int OGRE_FRAME_FIREH = 5;

  private final int OGRE_FRAME_PARTSTART = 6;
  private final int OGRE_FRAME_DATACOUNT = 8;
  private final int OGRE_FRAME_PARTCOUNT = 4;

  private final int OGRE_FRAME_ROTAT = 0;
  private final int OGRE_FRAME_FRAML = 1;
  private final int OGRE_FRAME_FRAMT = 2;
  private final int OGRE_FRAME_FRAMR = 3;
  private final int OGRE_FRAME_FRAMB = 4;
  private final int OGRE_FRAME_FACET = 5;
  private final int OGRE_FRAME_FACEL = 6;
  private final int OGRE_FRAME_FACER = 7;

  /**********************************************
   * 在地图上添加一个怪物 怪物添加的位置需要*8
   * @param ogre_defin int 怪物定义索引
   * @param ogre_pos_x int 怪物天骄到地图上位置的x坐标
   * @param ogre_pos_y int 怪物天骄到地图上位置的y坐标
   **********************************************/
  private final void ogre_create(int ogre_defin, int ogre_pos_x, int ogre_pos_y) {
    int[] ogre = new int[OGRE_COUNT];
    ogre[OGRE_DEFIN] = ogre_defin;
    ogre[OGRE_RESID] = ogredefine[ogre_defin][OGRE_DEFIN_RESID];
    ogre[OGRE_POS_X] = ogre_pos_x;
    ogre[OGRE_POS_Y] = ogre_pos_y;
    ogre[OGRE_FRAME] = 0;
    ogre[OGRE_DIREC] = 1;
    ogre[OGRE_STATE] = 0;
    ogre[OGRE_DEATH] = 0;
//    System.out.println(ogre[OGRE_RESID]);
//    if (ogre[OGRE_RESID] == 15) {
//      ogre[OGRE_ANIMA] = 80;
//    }
//    else if (ogre[OGRE_RESID] == 28) {
//      ogre[OGRE_ANIMA] = 110;
//    }
//    else {
    ogre[OGRE_ANIMA] = ogredefine[ogre[OGRE_DEFIN]][OGRE_DEFIN_ANIMA];
//    }

//    ogre[OGRE_ANIMA] = ogredefine[ogre[OGRE_DEFIN]][OGRE_DEFIN_ANIMA];
    ogre[OGRE_TALLY] = 0;
    ogre[OGRE_TEMP0] = 0;
    ogre[OGRE_SHOTT] = 0;
    ogre[OGRE_SHTLR] = 0;
    ogres.addElement(ogre);
  }

  private final void ogre_load(InputStream in) throws IOException {
    for (int i = in.read() - 1; i >= 0; i--) {
      ogre_create(in.read(), readWord(in), readWord(in));
    }
  }

  /**********************************************
   * 装载怪物数据
   **********************************************/
  private void ogre_loaddefine() {
    InputStream in = stream_create("/ogre.bin");
    try {
      int ogre_size = in.read();
      ogredefine = new int[ogre_size][OGRE_DEFIN_COUNT];
      ogreaction = new int[ogre_size][OGRE_ACTION_COUNT][][];
      for (int i = 0; i < ogre_size; i++) {
        for (int j = 0; j < OGRE_DEFIN_COUNT; j++) {
          ogredefine[i][j] = readByte(in);
        }
        for (int j = 0; j < OGRE_ACTION_COUNT; j++) { //action
          int framecount = in.read();
          ogreaction[i][j] = new int[framecount][OGRE_FRAME_COUNT];
          for (int k = 0; k < framecount; k++) { //frame
            for (int l = 0; l < 6; l++) {
              ogreaction[i][j][k][l] = (byte) in.read();
            }
            for (int l = 0; l < OGRE_FRAME_PARTCOUNT; l++) { //framepart
              for (int m = 0; m < 6; m++) {
                ogreaction[i][j][k][OGRE_FRAME_PARTSTART +
                    l * OGRE_FRAME_DATACOUNT + m] = readByte(in);
              }
              ogreaction[i][j][k][OGRE_FRAME_PARTSTART +
                  l * OGRE_FRAME_DATACOUNT + 6] = readWord(in);
              ogreaction[i][j][k][OGRE_FRAME_PARTSTART +
                  l * OGRE_FRAME_DATACOUNT + 7] = readWord(in);
            }
          }
        }
      }
      in.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    System.gc();
  }

  /**********************************************
   * 读取怪物的帧数据数组
   * @param ogre int[] 怪物对象数据数组
   * @return byte[] 帧数据数组
   **********************************************/
  private final int[] ogre_getframe(int[] ogre) {
    return ogreaction[ogre[OGRE_DEFIN]][ogre[OGRE_STATE]][ogre[OGRE_FRAME]];
  }

  private final int[] ogre_getdefin(int[] ogre) {
    return ogredefine[ogre[OGRE_DEFIN]];
  }

  private final boolean ogre_play(int[] ogre) {
    if (ogre[OGRE_FRAME] <
        ogreaction[ogre[OGRE_DEFIN]][ogre[OGRE_STATE]].length - 1) {
      ogre[OGRE_FRAME]++;
    }
    else {
      ogre[OGRE_FRAME] = 0;
    }
    return true;
  }

  private final boolean ogre_once(int[] ogre) {
    if (ogre[OGRE_FRAME] <
        ogreaction[ogre[OGRE_DEFIN]][ogre[OGRE_STATE]].length - 1) {
      ogre[OGRE_FRAME]++;
      return false;
    }
    else {
      ogre[OGRE_FRAME] = 0;
      return true;
    }
  }

  private final void ogre_walk(int[] ogre) {
    int[] frame = ogre_getframe(ogre);
    int x = (ogre[OGRE_POS_X] + ogre[OGRE_DIREC] * frame[OGRE_FRAME_RECTW]) >>
        3;
    int y = (ogre[OGRE_POS_Y] + 11) >> 3;
    if ( ( (grid_cellvalue(x, y - 1) == 1) || (grid_cellvalue(x, y - 1) == 2)) &&
        (grid_cellvalue(x, y - 2) == 0)) {
      ogre[OGRE_POS_Y] -= 8;
    }
    else
    if ( ( (grid_cellvalue(x, y + 1) == 1) || (grid_cellvalue(x, y + 1) == 2)) &&
        (grid_cellvalue(x, y) == 0)) {
      ogre[OGRE_POS_Y] += 8;
    }
    if (!ogre_side(ogre)) {
      ogre[OGRE_POS_X] += ogre[OGRE_DIREC] *
          ogredefine[ogre[OGRE_DEFIN]][OGRE_DEFIN_MVSPD];
    }
  }

  /**********************************************
   * 怪物是否走到了平台范围的边上
   * @param ogre int[] 怪物对象在ogres中的索引
   * @return boolean true 怪物到了平台边
   **********************************************/
  private final boolean ogre_side(int[] ogre) {
    int[] frame = ogre_getframe(ogre);
    int x = ogre[OGRE_POS_X] + ogre[OGRE_DIREC] * frame[OGRE_FRAME_RECTW];
    int ogre_b = ogre[OGRE_POS_Y] + 11;
    int ogre_t = ogre[OGRE_POS_Y] - frame[OGRE_FRAME_RECTT];
    int ogre_y = ogre[OGRE_POS_Y];
    boolean rt = false;
    rt |= grid_cellvalue(x >> 3, (ogre_b - 11) >> 3) == 1;
    rt |= grid_cellvalue(x >> 3, (ogre_b - 4) >> 3) == 1;
    rt |= grid_cellvalue(x >> 3, (ogre_b + 3) >> 3) == 0;
    rt |= grid_cellvalue(x >> 3, (ogre_b + 3) >> 3) == -1;
    if (rt) {
      return true;
    }
    for (int i = chest.size() - 1; i >= 0; i--) {
      int[] chet = (int[]) chest.elementAt(i);
      int chet_l = chet[CHEST_POS_X];
      int chet_r = chet_l + chet[CHEST_POS_W];
      int chet_t = chet[CHEST_POS_Y];
      int chet_b = chet_t + chet[CHEST_POS_H];
      if ( (x > chet_l) && (x < chet_r)) {
        rt |= (ogre_t > chet_t) && (ogre_t < chet_b);
        rt |= (ogre_b > chet_t) && (ogre_b < chet_b);
        rt |= (ogre_y > chet_t) && (ogre_y < chet_b);
        if (rt) {
          break;
        }
      }
    }
    return rt;
  }

  /**********************************************
   * 怪物是否站在平台上
   * @param ogre int[] 怪物对象在ogres中的索引
   * @return boolean true怪物站在平台上
   **********************************************/
  private final boolean ogre_stan(int[] ogre) {
    int[] frame = ogre_getframe(ogre);
    int ogre_y = ogre[OGRE_POS_Y] + 11;
    int ogre_x = ogre[OGRE_POS_X];
    int ogre_l = ogre_x - frame[OGRE_FRAME_RECTW] + 3;
    int ogre_r = ogre_x + frame[OGRE_FRAME_RECTW] - 3;
    boolean rt = false;
    int v;
    v = grid_cellvalue(ogre_x >> 3, ogre_y >> 3);
    rt |= ( (v == 1) || (v == 2));
    v = grid_cellvalue(ogre_l >> 3, ogre_y >> 3);
    rt |= ( (v == 1) || (v == 2));
    v = grid_cellvalue(ogre_r >> 3, ogre_y >> 3);
    rt |= ( (v == 1) || (v == 2));
    if (rt) {
      ogre[OGRE_POS_Y] = ( (ogre_y >> 3) << 3) - 11;
      return true;
    }
    for (int i = chest.size() - 1; i >= 0; i--) {
      int[] chet = (int[]) chest.elementAt(i);
      int chet_l = chet[CHEST_POS_X];
      int chet_r = chet_l + chet[CHEST_POS_W];
      int chet_t = chet[CHEST_POS_Y];
      int chet_b = chet_t + chet[CHEST_POS_H];
      if ( (ogre_y > chet_t) && (ogre_y < chet_b)) {
        rt |= (ogre_l > chet_l) && (ogre_l < chet_r);
        rt |= (ogre_r > chet_l) && (ogre_r < chet_r);
        rt |= (ogre_x > chet_l) && (ogre_x < chet_r);
        if (rt) {
          ogre[OGRE_POS_Y] = chet_t - 10;
          break;
        }
      }
    }
    return rt;
  }

  private final void ogre_fire(int[] ogre) {
    if (ogre[OGRE_TALLY] > 0) {
      return;
    }
    int[] defin = ogre_getdefin(ogre);
    int[] frame = ogre_getframe(ogre);
    //如果士兵当前不在攻击帧 直接返回
    if ( (defin[OGRE_DEFIN_STYLE] == 0) &&
        (ogre[OGRE_FRAME] != defin[OGRE_DEFIN_AKFME])) {
      return;
    }
    if (defin[OGRE_DEFIN_ARMID] < 0) {
      int x = ogre[OGRE_POS_X] + ogre[OGRE_DIREC] * frame[OGRE_FRAME_FIREX];
      int y = ogre[OGRE_POS_Y] + frame[OGRE_FRAME_FIREY];
      int w = frame[OGRE_FRAME_FIREW];
      int h = frame[OGRE_FRAME_FIREH];
      if (hero_nexus(x - w, y - h, x + w, y + h)) {
        hero_hitted(ogredefine[ogre[OGRE_DEFIN]][OGRE_DEFIN_ATTAK]);
      }
    }
    else {

      shot_create(defin[OGRE_DEFIN_ARMID],
                  ogre[OGRE_POS_X] + ogre[OGRE_DIREC] * frame[OGRE_FRAME_FIREX],
                  ogre[OGRE_POS_Y] - frame[OGRE_FRAME_FIREY],
                  ogre[OGRE_DIREC] * defin[OGRE_DEFIN_SHOTX] * 100,
                  defin[OGRE_DEFIN_SHOTY] * 100, ogre[OGRE_DIREC], 0, 0);
    }
    ogre[OGRE_TALLY] = defin[OGRE_DEFIN_AKSPD];
  }

  /**********************************************
   * 画怪物
   **********************************************/
  private final void ogre_draw() {
    for (int i = ogres.size() - 1; i >= 0; i--) {
      int[] ogre = (int[]) ogres.elementAt(i);
      int ressindex = ogre[OGRE_RESID];
      int ogre_x = ogre[OGRE_POS_X];
      int ogre_y = ogre[OGRE_POS_Y];
      int ogre_d = ogre[OGRE_DIREC];
      int[] frame = ogre_getframe(ogre);
      int partx, party, partw, parth, showx, showy, direc;
      for (int j = 0; j < 4; j++) {
        partx = ogre_x - ( (ogre_d == -1) ?
                          frame[OGRE_FRAME_PARTSTART + OGRE_FRAME_DATACOUNT * j +
                          OGRE_FRAME_FRAML] :
                          frame[OGRE_FRAME_PARTSTART + OGRE_FRAME_DATACOUNT * j +
                          OGRE_FRAME_FRAMR]);
        party = ogre_y - frame[OGRE_FRAME_PARTSTART + OGRE_FRAME_DATACOUNT * j +
            OGRE_FRAME_FRAMT];
        partw = frame[OGRE_FRAME_PARTSTART + OGRE_FRAME_DATACOUNT * j +
            OGRE_FRAME_FRAML] +
            frame[OGRE_FRAME_PARTSTART + OGRE_FRAME_DATACOUNT * j +
            OGRE_FRAME_FRAMR];
        parth = frame[OGRE_FRAME_PARTSTART + OGRE_FRAME_DATACOUNT * j +
            OGRE_FRAME_FRAMT] +
            frame[OGRE_FRAME_PARTSTART + OGRE_FRAME_DATACOUNT * j +
            OGRE_FRAME_FRAMB];
        showx = ogre_x - ( (ogre[OGRE_DIREC] == -1) ?
                          frame[OGRE_FRAME_PARTSTART + OGRE_FRAME_DATACOUNT * j +
                          OGRE_FRAME_FACEL] :
                          frame[OGRE_FRAME_PARTSTART + OGRE_FRAME_DATACOUNT * j +
                          OGRE_FRAME_FACER]);
        showy = ogre_y - frame[OGRE_FRAME_PARTSTART + OGRE_FRAME_DATACOUNT * j +
            OGRE_FRAME_FACET];
        direc = (frame[OGRE_FRAME_PARTSTART + OGRE_FRAME_DATACOUNT * j +
                 OGRE_FRAME_ROTAT] == 0) ? 1 : -1;
        direc = (ogre[OGRE_DIREC] * direc == -1) ? 0 : 0x2000;
        if (visible(partx, party, partx + partw, party + parth)) {
          clipRect(partx, party, partw, parth);
          drawFace(platres[ressindex], showx, showy, direc);
        }
      }
    }
  }

  /**********************************************
   * 设置怪物的状态
   * @param ogre int[] 怪物对象ogres中的索引
   * @param actionid int 怪物状态
   **********************************************/
  private final void ogre_setaction(int[] ogre, int actionid) {
    if (ogre[OGRE_STATE] != actionid) {
      ogre[OGRE_STATE] = actionid;
      ogre[OGRE_FRAME] = 0;
    }
  };

  private final void ogre_run() {
    if (mission_final_tally > 0) {
      return;
    }
    int[] defin, frame;
    for (int i = ogres.size() - 1; i >= 0; i--) {
      int[] ogre = (int[]) ogres.elementAt(i);
      defin = ogre_getdefin(ogre);
      frame = ogre_getframe(ogre);

      int ogre_x = ogre[OGRE_POS_X];
      int ogre_y = ogre[OGRE_POS_Y];
      int ogre_d = ogre[OGRE_DIREC];
      int ogre_l = ogre[OGRE_POS_X] - frame[OGRE_FRAME_RECTW];
      int ogre_t = ogre[OGRE_POS_Y] - frame[OGRE_FRAME_RECTT];
      int ogre_r = ogre[OGRE_POS_X] + frame[OGRE_FRAME_RECTW];
      int ogre_b = ogre[OGRE_POS_Y] + 11;
      int aimode = defin[OGRE_DEFIN_AIMOD];
      int space_x = Math.abs(hero_pos_x - ogre_x);
      int space_y = Math.abs(hero_pos_y - ogre_y);
      if (ogre[OGRE_TALLY] > 0) {
        ogre[OGRE_TALLY]--; // 减去怪物的攻击计数
      }
      switch (aimode) {
        case OGRE_AIMODE_TURN_TAIL: //模式0：主角靠近逃跑
          if (!ogre_stan(ogre)) {
            ogre[OGRE_POS_Y] += 4;
          }
          if (!visible(ogre_l, ogre_t, ogre_r, ogre_b)) {
            continue;
          }
          switch (ogre[OGRE_STATE]) {
            case OGRE_ACTION_STAN: //站立
              ogre[OGRE_TEMP0] = 0;
              if ( (space_x < 71) && (space_y < 46)) {
                ogre_setaction(ogre, OGRE_ACTION_SCAR);
              }
              else {
                ogre[OGRE_DIREC] = (hero_pos_x < ogre_x) ? 1 : -1;
                ogre_play(ogre);
              }
              break;
            case OGRE_ACTION_MOVE: //逃走
              if (!visible(ogre_l, ogre_t, ogre_r, ogre_b)) {
                ogres.removeElementAt(i);
              }
              else {
                ogre[OGRE_DIREC] = (hero_pos_x > ogre_x) ? -1 : 1;
                ogre_walk(ogre);
                ogre_play(ogre);
              }
              break;
            case OGRE_ACTION_SCAR: //惊吓
              ogre[OGRE_DIREC] = (hero_pos_x > ogre_x) ? 1 : -1;
              if (ogre_once(ogre)) {
                ogre_setaction(ogre, OGRE_ACTION_MOVE);
                ogre[OGRE_TEMP0] = 1;
              }
              break;
            case OGRE_ACTION_HITD:
              if (ogre_once(ogre)) {
                ogre_setaction(ogre, OGRE_ACTION_STAN);
              }
              break;
            case OGRE_ACTION_DEAD: //被打死
              if (ogre_once(ogre)) {
                ogres.removeElementAt(i);
              }
              break;
          }
          break;
        case OGRE_AIMODE_MOVE_FIRE: //模式1：来回移动等待攻击
          if (!ogre_stan(ogre)) {
            ogre[OGRE_POS_Y] += 4;
          }
          if (!visible(ogre_l, ogre_t, ogre_r, ogre_b)) {
            continue;
          }
          switch (ogre[OGRE_STATE]) {
            case OGRE_ACTION_STAN:
              ogre_setaction(ogre, OGRE_ACTION_MOVE);
              ogre[OGRE_DIREC] = (hero_pos_x > ogre_x) ? 1 : -1;
              break;
            case OGRE_ACTION_MOVE:
              if (hero_nexus(ogre_x - defin[OGRE_DEFIN_FIREW],
                             ogre_y - defin[OGRE_DEFIN_FIREH],
                             ogre_x + defin[OGRE_DEFIN_FIREW],
                             ogre_y + defin[OGRE_DEFIN_FIREH]) &&
                  (ogre[OGRE_TALLY] <= 0)) {
                ogre[OGRE_DIREC] = (hero_pos_x > ogre_x) ? 1 : -1;
                ogre_setaction(ogre, OGRE_ACTION_FIRE);
              }
              else {
                if (space_x >= defin[OGRE_DEFIN_MVSPD]) {
                  ogre[OGRE_DIREC] = (hero_pos_x + 35 > ogre_x) ? 1 : -1;
                  ogre_walk(ogre);
                }
                ogre_play(ogre);
              }
              break;
            case OGRE_ACTION_FIRE:
              ogre_fire(ogre);
              if (ogre_once(ogre)) {
                ogre_setaction(ogre, OGRE_ACTION_MOVE);
                ogre[OGRE_TALLY] = defin[OGRE_DEFIN_AKSPD];
              }
              break;
            case OGRE_ACTION_HITD:
              ogre_fire(ogre);
              if (ogre_once(ogre)) {
                ogre_setaction(ogre, OGRE_ACTION_MOVE);
              }
              break;
            case OGRE_ACTION_DEAD:
              if (ogre_once(ogre)) {
                ogres.removeElementAt(i);
              }
              break;
          }
          break;
        case OGRE_AIMODE_CYCLE_FLY: //模式2：来回飞行攻击
          switch (ogre[OGRE_STATE]) {
            case OGRE_ACTION_STAN: //站立
              if (visible(ogre_l, ogre_t, ogre_r, ogre_b)) {
                ogre[OGRE_DIREC] = (hero_pos_x > ogre_x) ? 1 : -1;
                ogre_setaction(ogre, OGRE_ACTION_MOVE);
              }
              break;
            case OGRE_ACTION_MOVE: //移动
              if ( (space_x < 35) && (ogre[OGRE_TALLY] <= 0)) {
                ogre_setaction(ogre, OGRE_ACTION_FIRE);
              }
              else {
                if (space_x >= defin[OGRE_DEFIN_MVSPD]) {
                  ogre[OGRE_DIREC] = (hero_pos_x > ogre_x) ? 1 : -1;
                  ogre[OGRE_POS_X] += ogre[OGRE_DIREC] * defin[OGRE_DEFIN_MVSPD];
                }
                ogre_play(ogre);
              }
              break;
            case OGRE_ACTION_FIRE: //攻击
              if (ogre[OGRE_FRAME] == defin[OGRE_DEFIN_AKFME]) {
                ogre_fire(ogre);
              }
              if (ogre_once(ogre)) {
                ogre_setaction(ogre, OGRE_ACTION_STAN);
                ogre[OGRE_TALLY] = defin[OGRE_DEFIN_AKSPD];
              }
              break;
            case OGRE_ACTION_HITD: //受伤
              ogre_fire(ogre);
              if (space_x >= defin[OGRE_DEFIN_MVSPD]) {
                ogre[OGRE_DIREC] = (hero_pos_x > ogre_x) ? 1 : -1;
                ogre[OGRE_POS_X] += ogre[OGRE_DIREC] * defin[OGRE_DEFIN_MVSPD];
              }
              if (ogre_once(ogre)) {
                ogre_setaction(ogre, OGRE_ACTION_MOVE);
              }
              break;
            case OGRE_ACTION_DEAD: //炸死
              if (ogre_once(ogre)) {
                ogres.removeElementAt(i);
              }
              break;
          }
          break;
        case OGRE_AIMODE_BOSS:
          switch (ogre[OGRE_STATE]) {
            case OGRE_ACTION_STAN:
              if (visible(ogre_l, ogre_t, ogre_r, ogre_b)) {
                ogre[OGRE_DIREC] = (hero_pos_x > ogre_x) ? 1 : -1;
                ogre_setaction(ogre, OGRE_ACTION_MOVE);
                ogre[OGRE_TALLY] = 50;
                ogre[OGRE_MOVED] = 0;
              }
              break;
            case OGRE_ACTION_MOVE:
            case OGRE_ACTION_FIRE:
            case OGRE_ACTION_HITD:
              if (ogre[OGRE_MOVED] < -50) {
                ogre[OGRE_DIREC] = 1;
              }
              else if (ogre[OGRE_MOVED] > 50) {
                ogre[OGRE_DIREC] = -1;
              }
              ogre[OGRE_POS_X] += ogre[OGRE_DIREC] * defin[OGRE_DEFIN_MVSPD];
              ogre[OGRE_MOVED] += ogre[OGRE_DIREC] * defin[OGRE_DEFIN_MVSPD];
              if (ogre[OGRE_TALLY] <= 0) {
                ogre[OGRE_FIREE] = 40;
                ogre[OGRE_TALLY] = 50;
              }
              if (ogre[OGRE_FIREE] > 0) {
                int y = ogre[OGRE_POS_Y] + frame[OGRE_FRAME_FIREY];
                int w = frame[OGRE_FRAME_FIREW];
                int h = frame[OGRE_FRAME_FIREH];
                int x = ogre[OGRE_POS_X] + frame[OGRE_FRAME_FIREX];
                if (hero_nexus(x - w, y - h, x + w, y + h)) {
                  hero_hitted(ogredefine[ogre[OGRE_DEFIN]][OGRE_DEFIN_ATTAK]);
                }
                x = ogre[OGRE_POS_X] + frame[OGRE_FRAME_FIREX];
                if (hero_nexus(x - w, y - h, x + w, y + h)) {
                  hero_hitted(ogredefine[ogre[OGRE_DEFIN]][OGRE_DEFIN_ATTAK]);
                }
                ogre[OGRE_STATE] = OGRE_ACTION_FIRE;
                ogre[OGRE_FIREE]--;
              }
              else {
                ogre[OGRE_STATE] = OGRE_ACTION_MOVE;
              }
              if (ogre[OGRE_SHOTT] <= 0) {
                int shot_y = ogre_y - 49;
                shot_create(defin[OGRE_DEFIN_ARMID], ogre_x + 62, shot_y, -700,
                            700, ogre_d, 0, 0);
                shot_create(defin[OGRE_DEFIN_ARMID], ogre_x - 62, shot_y, 700,
                            700, ogre_d, 0, 0);
                ogre[OGRE_SHOTT] = 14;
              }
              else {
                ogre[OGRE_SHOTT]--;
              }
              ogre_play(ogre);
              break;
            case OGRE_ACTION_DEAD:
              if (ogre_once(ogre)) {
                ogres.removeElementAt(i);
              }
              break;
          }
          break;
      }
    }
  }

  /***********************************************
   * 怪物被主角打中
   * @param ogre int[] 怪物索引
   * @param attack int 攻击伤害
   ***********************************************/
  private final void ogre_hitted(int[] ogre, int attack) {
    ogre[OGRE_ANIMA] -= attack;
//    System.out.println(attack);
    int[] defin = ogre_getdefin(ogre);

    if (ogre[OGRE_ANIMA] <= 0) {
      int ogre_x = ogre[OGRE_POS_X];
      int ogre_y = ogre[OGRE_POS_Y];
      hero_addscore(defin[OGRE_DEFIN_SCORE]);
      if (defin[OGRE_DEFIN_BLAST] >= 0) {
        movi_create(defin[OGRE_DEFIN_BLAST], ogre_x, ogre_y, 0, 0, 0, 0, -1,
                    MOVI_DEATH_ONCE);
      }
      if (defin[OGRE_DEFIN_CLIPS] >= 0) {
        if (defin[OGRE_DEFIN_STYLE] == 0) {
          movi_create(defin[OGRE_DEFIN_CLIPS], ogre_x, ogre_y,
                      hero_direct * 400, -300, 0, 100, -1, MOVI_DEATH_SIDE);
        }
        else {
          movi_create(defin[OGRE_DEFIN_CLIPS], ogre_x, ogre_y, 400, -700, 0,
                      100, -1, MOVI_DEATH_SIDE);
          movi_create(defin[OGRE_DEFIN_CLIPS], ogre_x, ogre_y, -200, -800, 0,
                      100, -1, MOVI_DEATH_SIDE);
          movi_create(defin[OGRE_DEFIN_CLIPS], ogre_x, ogre_y, -500, -600, 0,
                      100, -1, MOVI_DEATH_SIDE);
          movi_create(defin[OGRE_DEFIN_CLIPS], ogre_x, ogre_y, 700, -400, 0,
                      100, -1, MOVI_DEATH_SIDE);
        }
      }
      ogre[OGRE_DEATH] = 1;
      ogre_setaction(ogre, OGRE_ACTION_DEAD);
    }
    else {
      if (defin[OGRE_DEFIN_AIMOD] != OGRE_AIMODE_BOSS) {
        ogre_setaction(ogre, OGRE_ACTION_HITD);
      }
    }
  }

  //****************************************************************************
   /** @todo: 游戏时间 */
   //****************************************************************************
    private int game_time = 0; //游戏惊醒时间
  private int timecount = 0;

  /**********************************************
   * 运行游戏时间
   * @return boolean 如果游戏时间结束返回true 否则false
   **********************************************/
  private final void time_run() {
    if (game_time <= 0) {
      if (hero_being > 0) {
        switch (hero_style) {
          case 0:
            hero_deadone();
            break;
//          case 1:
//            plan_deadone();
//            break;
//          case 2:
//            tank_deadone();
//            break;
        }
      }
      else {
        hero_death = true;
      }
    }
    else
    if (timecount++ > 17) {
//      game_time--;
      timecount = 0;
    }
  }

  private final void time_init() {
    game_time = 99;
    timecount = 0;
  }

  private final void time_draw() {
//    if ( ( (game_time <= 10) && ( (framenum & 2) != 0)) || (game_time > 10)) {
//      drawString(game_time, 176 - 10, 208 - 12, FONT_ALIGN_RIGH, FONT_MIDDLE); //时间
//    }
  }

  //****************************************************************************
   /** @todo: 开始结束 */
   //****************************************************************************
    private int mission_final_tally = 0;
  private int mission_start_tally; //mission开始画面
  private final void mission_draw() {
//    int left = (screen_w - 100) >> 1;
//    if (mission_start_tally > 0) {
//      g.setClip(left, 30, 100, 24);
//      g.drawImage(faceres[FACE_GAMESTART], left, 30, 0);
//    }
//    else
//    if (mission_final_tally > 0) {
//      g.setClip(left, 30, 44, 24);
//      g.drawImage(faceres[FACE_GAMESTART], left, 30, 0);
//      g.setClip(left + 91, 30, 9, 24);
//      g.drawImage(faceres[FACE_GAMESTART], left, 30, 0);
//      g.setClip(left + 44, 30, 47, 24);
//      g.drawImage(faceres[FACE_GAMESTART], left + 44 - 101, 30, 0);
//    }
  }

  //****************************************************************************
   /** @todo: 游戏主体 */
   //****************************************************************************
    private final void gamebody_init() {
      hero_score = 0;
      hero_anima = 32;
      hero_being = 3;
      hero_bombcount = 8;
      gamemenushow = false;
      gamebodyexit = false;
//      onezidan = 0;
      shotid = 0;
//      twozidan = 0;
      plat_exist = false;
//      mission_start(MISSION_1);
    }

  private final void gamebody_free() {
    grids = null;
    facts = null;
//    onezidan = 0;
    shotid = 0;
//    twozidan = 0;

    infos.removeAllElements();
    chest.removeAllElements();
    movis.removeAllElements();
    props.removeAllElements();
    ogres.removeAllElements();
    shots.removeAllElements();
    pawns.removeAllElements();
  }

  private final void gamebody_over() {
    gamebody_free();
    record_save_mark(hero_score);
    record_save_mission( (mission_index < 3) ? mission_index + 1 : 3);
    screen_index = SCREEN_GAMEOVER;
  }

  private final void gamebody_selectrole() {
    //   switch (platid) {
//      case 4:
//      case 17:
//        hero_style = 2;
//        break;
//      case 5:
//      case 6:
//        hero_style = 1;
//        break;
//      default:
    hero_style = 0;
    //}
  }

  int sht[] = {
      0, 1, 4};

  int ttt = 0;
  private final void gamebody_key(int keycode) {

    if (gamemenushow) {
      gamemenu_key(keycode);
    }
    else
    if (keycode == KEY_SOFT1 || keycode == KEY_SOFT2) {
      gamemenu_index = 0;
      stop();
      gamemenushow = true;
    }
    else {
      if (keycode == KEY_HUAN) {
        ttt++;
        if (ttt > 5) {
          ttt = 0;
        }
        shotid = sht[ttt];
//         System.out.println(shotid+"pp");
//        if (onezidan <= 0 || twozidan <= 0) {
//          shotid = 0;
//        }
//        if (onezidan <= 0 && twozidan > 0) {
//          shotid = 4;
//        }
//        if (onezidan >= 0) {
//          shotid = 1;
//        }
//        if (twozidan <= 0) {
//          shotid = 0;
//        }

//        System.out.println(shotid+"ee");
//        System.out.println(onezidan);
      }
      switch (hero_style) {
        case 0:
          switch (hero_status & STATUS_MAIN) {
            case STATUS_MAIN_STAND:
              hero_stand_key(keycode);
              break;
            case STATUS_MAIN_SQUAT:
              hero_squat_key(keycode);
              break;
            case STATUS_MAIN_INAIR:
              hero_inair_key(keycode);
              break;
          }
          break;
//        case 2:
//          switch (tank_status & 0xf) {
//            case TANK_STATUS_STAND:
//              tank_stand_key(keycode);
//              break;
//            case TANK_STATUS_SQUAT:
//              tank_squat_key(keycode);
//              break;
//          }
//          break;
      }
    }
  }

  private final void gamebody_run() {
    if (script_execute_list.size() > 0) {
      script_execute( ( (Integer) script_execute_list.elementAt(0)).intValue());
      script_execute_list.removeElementAt(0);
    }

    if (hero_death) { //主角死亡
      //   sound_play(SOUND_DEFEATED);
      gamebody_over();
      return;
    }
    else
    if (mission_final()) { //这里写进入第几关
      //System.out.println(platid);
      // if(platid==1){
//      record_save_mark(hero_score);
//      gamebodyexit = true;
      // }
    }
    else
    if (mission_start_tally-- > 0) {
      ;
    }
    if (gamemenushow) {
      ;
    }
    else {
      script_run(); //脚本
      plat_next();
      time_run();
      back_run();
      zgun_exec();
      chet_run(); //箱子运行
      ogre_run(); //运行怪物

      switch (hero_style) {
        case 0:
          hero_exec();
          break;
//        case 1:
//          plan_exec();
//          break;
//        case 2:
//          tank_exec();
//          break;
      }

      //pawn_run(); //运行俘虏
      prop_run(); //运行道具
      shot_run(); //运行子弹
      info_run(); //得分数字
      movi_run(); //运行动画
    }
    refresh();
    if (gamebodyexit) {
      process_set(SCREEN_MAINMENU);
    }
  }

  private final void gamebody_draw() {
    g.setColor(0, 0, 0);
    g.fillRect(0, 0, screen_w, screen_h);
    if (plat_exist) {
      back_draw(); //画背景
      fact_draw_back(); //底层地物
      zgun_draw();
      chet_draw(); //箱子
      ogre_draw(); //怪物
//       switch (hero_style) {
//         case 0:
      hero_draw();
//           break;
//         case 1:
//           plan_draw();
//           break;
//         case 2:
//           tank_draw();
//           break;
//       }
//       pawn_draw(); //俘虏
      prop_draw(); //道具
      movi_draw(); //动画
      shot_draw(); //子弹
      info_draw(); //数字
      fact_draw_fore(); //顶层地物
      mission_draw();
      infoclip_draw(); //顶部信息
      time_draw(); //游戏时间
      alpha_draw();
    }
    message_dialog_show();
    gamemenu_draw(); //画游戏中的菜单

  }

  //****************************************************************************
   /** @todo: 关卡 */
   //****************************************************************************
    private int mission_index = 0;
  private final int MISSION_1 = 1;
  private final int MISSION_2 = 2;

//  private final int MISSION_3 = 3;
  private final int MISSION_1_PLAT_START = 1;

//  private final int MISSION_2_PLAT_START = 6;

//  private final int MISSION_3_PLAT_START = 11;

  private final int MISSION_1_PLAT_FINAL = 7;

//  private final int MISSION_2_PLAT_FINAL = 11;

//  private final int MISSION_3_PLAT_FINAL = 16;

  /**********************************************
   * 游戏开始的
   * 时候初始化参数
   **********************************************/
  private final void mission_init() {
    mission_start_tally = 10;
    mission_final_tally = 0;
    time_init();

    hero_anima = 32;
    hero_death = false;

    hero_status = 0;
    hero_action = 0;
    hero_cframe = 0;

    hero_shot_type = 0;
    hero_shotcount = 0;
    hero_shottally = 0;
    hero_bombtally = 0;

    hero_pos_x = 0;
    hero_pos_y = 0;
    hero_spd_y = 0;

    hero_direct = 1;
    hero_cflash = 0;
    hero_status = 0;
    hero_action = 0;
    hero_cframe = 0;
    hero_frectw = 0;
    hero_frectt = 0;

//    tank_status = 0;
//    tank_action = 0;
//    tank_cframe = 0;
//    tank_speedy = 0;
//    tank_speedx = 0;
//    tank_cflash = 0;
//    tank_death_count = 0;
//    tank_cannon_angle = 0;
//    tank_cannon_fire = false;
//    tank_cannon_fire_frame = 0;
//    tank_cannon_fire_count = 0;

//    plan_cannon_angle = 0;
//    plan_cannon_fire = false;
//    plan_cannon_fire_frame = 0;
//    plan_cannon_fire_count = 0;
//    plan_action = 0;
//    plan_cframe = 0;
//    plan_cflash = 0;
//    plan_death_count = 0;
  }

  private final boolean mission_start(int index) {
    if ( (index != MISSION_1) && (index != MISSION_2)) {
      return false;
    }
    mission_index = index;
    mission_init();
    switch (index) {
      case MISSION_1:
        plat_load(MISSION_1_PLAT_START);
        hero_move(10, 100);
        break;
//      case MISSION_2:
//        plat_load(MISSION_2_PLAT_START);
//        hero_move(10, 100);
//        break;

      default:
        return false;
    }
    return true;
  }

  private final boolean mission_final() { //修改过关BOSS。MISSION_1_PLAT_FINAL每小关的数字
    switch (mission_index) {
      case MISSION_1:
        if ( (platid != MISSION_1_PLAT_FINAL) || (ogres.size() != 0)) {
          return false;
        }
        break;
//      case MISSION_2:
//        if ( (platid != MISSION_2_PLAT_FINAL) || (ogres.size() != 0) ||
//            (ogres.size() != 0)) {
//          return false;
//        }
//        break;
//      case MISSION_3:
//        if ( (platid != MISSION_3_PLAT_FINAL) || (ogres.size() != 0)) {
//          return false;
//        }
//        break;
      default:
        return false;
    }
    mission_final_tally = 1;
    ogres.removeAllElements();
    shots.removeAllElements();
    while (!hero_stan()) {
      chet_run(); //箱子运行
      ogre_run(); //运行怪物
      hero_exec(); //运行主角
      // pawn_run(); //运行俘虏
      prop_run(); //运行道具
      shot_run(); //运行子弹
      info_run(); //得分数字
      movi_run(); //运行动画
      refresh();
    }
    // sound_play(SOUND_TOLLGATE);
//    hero_setaction(HERO_ACTION_VICTORY);
    while (mission_final_tally < 30) {
      mission_final_tally++;
      hero_action_play();
      chet_run(); //箱子运行
      //  pawn_run(); //运行俘虏
      prop_run(); //运行道具
      info_run(); //得分数字
      movi_run(); //运行动画
      refresh();
    }
    alpha_fade(0, 1);
    mission_final_tally = 0;
    switch (mission_index) {
      case MISSION_1:

//        hero_score=12345;

        record_save_mark(hero_score);
        gamebodyexit = true;
        return true;
//      case MISSION_2:
//        record_save_mark(hero_score);
//        gamebodyexit = true;
//        return true;
//      case MISSION_3:
//        record_save_mark(hero_score);
//        gamebodyexit = true;
//        return true;
      default:
        return true;
    }
  }

  private final boolean plat_next() {
    if ( (hero_pos_x - hero_frectw > plat_w * 8 - 8) && (plat_n > 0)) {
      plat_load(plat_n);
      hero_pos_x = 9;
      return true;
    }
    else {
      return false;
    }
  }

  /**********************************************
   * 装载地图数据
   * @param id int 地图索引
   **********************************************/
  byte guan = 1;
  private final void plat_load(int id) {
    if (platid != id) {
      guan++;
//      System.out.println(guan);
    }
    platid = id;
    time_init();
    InputStream in = stream_create("/plat.bin");
    try {
      //释放资源
      time_init();
      grids = null;
      facts = null;
      infos.removeAllElements();
      chest.removeAllElements();
      movis.removeAllElements();
      props.removeAllElements();
      ogres.removeAllElements();
      shots.removeAllElements();
      pawns.removeAllElements();
      zguns.removeAllElements();

      //定位地图数据位置
      skip(in, id * 2);
      skip(in, in.read() + (in.read() << 8));
      //释放无用的图形和装载新的图形
      boolean[] ressign = new boolean[platres.length];
      for (int i = in.read() - 1; i >= 0; i--) {
        ressign[in.read()] = true;
        //读取基本地图数据
      }
      plat_w = in.read();
      plat_h = in.read();
      // System.out.println(plat_w);
      in.read();
      plat_n = in.read();
      in.read();
      in.read();

      back_load(in); //读取背景
      grid_load(in); //读取地形数据
      fact_load(in); //读取场景数据
      movi_load(in); //读取动画
      chet_load(in); //读取箱子
      pawn_load(in); //读取俘虏
      zgun_load(in);
      ogre_load(in); //读取怪物位置
      position_x = 0;
      in.close();
      gamebody_selectrole();
      plat_exist = true;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      System.gc();
    }
  }

  //****************************************************************************
   /** @todo: 图形数字 */
   //****************************************************************************
    private final int FONT_ALIGN_LEFT = 0;
  private final int FONT_ALIGN_RIGH = 1;
  private final int FONT_ALIGN_MIDD = 2;

//  private final int FONT_LITTLE_WIDTH = 5;
//  private final int FONT_LITTLE_HEIGH = 6;
//  private final int FONT_MIDDLE_WIDTH = 8;
//  private final int FONT_MIDDLE_HEIGH = 8;
//  private final int FONT_GREATE_WIDTH = 13;
//  private final int FONT_GREATE_HEIGH = 17;
  private final int FONT_LITTLE = 0;
  private final int FONT_MIDDLE = 1;
  private final int FONT_GREATE = 2;

  private final void drawString(int num, int x, int y, int align, int font) {
    int font_w, font_h, font_face_index;
    switch (font) {
      case FONT_LITTLE:
        font_w = 8;
        font_h = 8;
        font_face_index = FACE_FONT_MIDDLE;
        break;
      case FONT_MIDDLE:
        font_w = 8;
        font_h = 8;
        font_face_index = FACE_FONT_MIDDLE;
        break;
      case FONT_GREATE:
        font_w = 13;
        font_h = 17;
        font_face_index = FACE_FONT_GREATE;
        break;
      default:
        return;
    }
    String num_string = String.valueOf(num);
    int len = num_string.length();
    int xx = 0;
    switch (align) {
      case FONT_ALIGN_LEFT:
        xx = x;
        break;
      case FONT_ALIGN_RIGH:
        xx = x - len * font_w;
        break;
      case FONT_ALIGN_MIDD:
        xx = ( (x << 1) - len * font_w) >> 1;
        break;
    }
    for (int i = 0; i < len; i++) {
      int char_x = xx + i * font_w;
      g.setClip(char_x, y, font_w, font_h);
      g.drawImage(faceres[font_face_index],
                  char_x - (num_string.charAt(i) - 48) * font_w, y, 0);
    }
  }

  //******************************************************************************
   /** @todo:淡入淡出 */
   //******************************************************************************
    private boolean alpha_show; //当前是否显示透明遮罩
  private short[] alpha_line; //透明遮罩线条
  private void alpha_fade(int color, int incre) {
    short alpha_color; //透明遮罩的颜色
    short alpha_value; //透明度的当前值
    alpha_line = new short[screen_w];
    alpha_show = true;
    alpha_value = (short) ( (incre > 0) ? 0 : 15);
    while ( (alpha_value <= 15) && (alpha_value >= 0)) {
      alpha_color = (short) ( (alpha_value << 12) | color);
      for (int i = 0; i < screen_w; i++) {
        alpha_line[i] = alpha_color;
      }
      refresh();
      alpha_value += incre;
    }
    alpha_show = false;
    alpha_line = null;
    System.gc();
  }

  private void alpha_draw() {
    if (alpha_show) {
      g.setClip(0, 0, screen_w, screen_h);
      for (int i = 0; i < screen_h; i++) {
        dg.drawPixels(alpha_line, true, 0, screen_w, 0, i, screen_w, 1, 0, 4444);
      }
    }
  }

  /**********************************************
   * 游戏信息显示
   **********************************************/
  private final void infoclip_draw() {
//    private final int FACE_ANIMASLOT = 6; //分数时间
//private final int FACE_GAMELIFE = 7; //主角生命和血
//private final int FACE_HEROBEING = 8; //生命
//
//private final int FACE_ARMS_BOMB = 9; //炸弹数

    g.setClip(0, 0, screen_w, screen_h);
    g.drawImage(faceres[FACE_ANIMASLOT + id], 0, 0, g.LEFT | g.TOP);
//    drawString(hero_bombcount, 74, 14, FONT_ALIGN_MIDD, FONT_LITTLE); //炸弹总数

//    g.drawImage(faceres[FACE_GAMELIFE], 0, 208 - 25, 0);
//    if(hero_anima>0){
//    g.setClip(36, 208 - 25 + 5, hero_anima * 2, 13);
//    g.drawImage(faceres[FACE_HEROBEING], 36, 208 - 25 + 5, 0); //血
//    g.setClip(0, 0, screen_w, screen_h);
//    g.setClip(27, 208 - 25 + 8, 4, hero_bombcount);
//    g.drawImage(faceres[FACE_ARMS_BOMB], 27, 208 - 25 + 8, 0); //炸弹总数
//    g.setClip(0, 0, screen_w, screen_h);
//    drawString(hero_score, 149, 9, FONT_ALIGN_LEFT, FONT_LITTLE); //得分
//    g.setClip(36, 208 - 25 + 5, hero_anima * 2, 13);
    g.setClip(0, 0, screen_w, screen_h);
    g.drawImage(faceres[FACE_ANIMASLOT4], 130, 7, 0); //炸弹总数
    drawString(hero_being, 150, 7, FONT_ALIGN_LEFT, FONT_LITTLE); //生命数
    g.setClip(0, 0, screen_w, screen_h);
//    g.setClip(52, 9, hero_anima * 2, 5);
    g.drawImage(faceres[FACE_ANIMASLOT2], 39, 7, 0);
    for (int p = 0; p < (hero_anima * 2); p++) {
      g.drawImage(faceres[FACE_ANIMASLOT3], 40 + p, 8, 0);
    }

//    g.setClip(52, 16, hero_bombcount * 6, 5);
//    g.drawImage(faceres[FACE_ANIMASLOT1], 52, 17, 0);
//    g.fillRect(70-(63-(hero_anima*3)) , 33,  63-(hero_anima*3), 6);
//    for (int i = 0; i < hero_anima; i++) {
//      g.drawImage(faceres[FACE_HEROBEING], 18 + 16 * i, 26, 0);
//    }
//    drawString(hero_bombcount, 153, 18, FONT_ALIGN_MIDD, FONT_LITTLE); //炸弹总数 //font_little_draw(hero_bombcount, 153, 18, FONT_ALIGN_MIDD);
//    drawString(hero_being, 34, 10, FONT_ALIGN_LEFT, FONT_LITTLE); //生命数 //font_little_draw(hero_being, 26, 28, FONT_ALIGN_LEFT);
    // font_middle_draw(hero_score, 60,  5, FONT_ALIGN_RIGH);
  }

  //****************************************************************************
   /** @todo: 游戏装载 */
   //****************************************************************************
    private int ressload_progress = 0;
  private final void ressload_draw() {
    g.setClip(0, 0, screen_w, screen_h);
//    g.drawImage(faceres[FACE_MENUCOVER], 0, 0, 0);
    g.setColor(0, 0, 0);
    g.fillRect(0, 0, 176, 208);
//    isEnd1 = effect.drawFlowImage(g, faceres[FACE_MENUTITLE], 2,
//                                  88, 5, 7);
    g.setColor(136, 119, 128);
    g.fillRect(24, 198, 88 + 24, 2);
    g.setColor(255, 0, 0);
    g.fillRect(24, 198, (ressload_progress / 2), 2);
  }

  private final void ressload_exec() {

    InputStream in = stream_create("/face.bin");
    hero_loaddefine();
    //plan_loaddefine();
    //   tank_loaddefine();
    try {
      platres = new Image[in.read() + (in.read() << 8)];
      skip(in, platres.length * 2);
      for (int i = 0; i < platres.length; i++) {
        int len = in.read() + (in.read() << 8);
        byte[] gdata = new byte[len + 6];
        System.arraycopy("GIF89a".getBytes(), 0, gdata, 0, 6);
        in.read(gdata, 6, len);
        platres[i] = Image.createImage(gdata, 0, gdata.length);
        ressload_progress = (i + 1) * 208 / platres.length;
        refresh();
      }
      ogre_loaddefine();
      shot_loaddefine();
      movi_loaddefine();
      mainmenu_execute(mainmenu_index);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //****************************************************************************
   /** @todo: 游戏存档 */
   //****************************************************************************
    /**********************************************
     * 把分数写入高分榜
     * @param mark int 分数
     **********************************************/
    private final void record_save_mark(int mark) {
      int[] highmark_old = record_load_mark();
      int[] highmark_new = new int[9];
      System.arraycopy(highmark_old, 0, highmark_new, 0, 8);
      highmark_new[8] = mark;
      int temp;
      for (int i = 0; i < 8; i++) {
        for (int j = i + 1; j < 9; j++) {
          if (highmark_new[i] < highmark_new[j]) {
            temp = highmark_new[i];
            highmark_new[i] = highmark_new[j];
            highmark_new[j] = temp;
          }
        }
      }
      try {
        RecordStore rs = RecordStore.openRecordStore("highmark", true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        for (int i = 0; i < 8; i++) {
          dos.writeInt(highmark_new[i]);
        }
        byte[] recorddata = baos.toByteArray();
        if (rs.getNumRecords() == 0) {
          rs.addRecord(recorddata, 0, recorddata.length);
        }
        else {
          rs.setRecord(1, recorddata, 0, recorddata.length);
        }
        rs.closeRecordStore();
        dos.close();
        baos.close();

      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }

  /**********************************************
   * 读出高分表
   **********************************************/
  private final int[] record_load_mark() {
    int[] rt = new int[8];
    try {
      RecordStore rs = RecordStore.openRecordStore("highmark", true);
      if (rs.getNumRecords() > 0) {
        byte[] recorddata = rs.getRecord(1);
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(
            recorddata));
        for (int i = 0; i < 8; i++) {
          rt[i] = dis.readInt();
        }
      }
      rs.closeRecordStore();
      return rt;
    }
    catch (Exception e) {
      e.printStackTrace();
      return rt;
    }
  }

  private final void record_save_mission(int mission_mark) {
    if (record_read_mission() > mission_mark) {
      return;
    }
    try {
      RecordStore rs = RecordStore.openRecordStore("mission_mark", true);
      byte[] recorddata = new byte[1];
      recorddata[0] = (byte) mission_mark;
      if (rs.getNumRecords() == 0) {
        rs.addRecord(recorddata, 0, recorddata.length);
      }
      else {
        rs.setRecord(1, recorddata, 0, recorddata.length);
      }
      rs.closeRecordStore();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private final int record_read_mission() {
    int rt = 1;
    try {
      RecordStore rs = RecordStore.openRecordStore("mission_mark", true);
      if (rs.getNumRecords() > 0) {
        byte[] recorddata = rs.getRecord(1);
        if (recorddata.length == 1) {
          rt = recorddata[0];
        }
      }
      rs.closeRecordStore();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return (rt > 1) ? rt : 1;
  }

  //****************************************************************************
   /** @todo: 图形函数 */
   //****************************************************************************

    /**********************************************
     * 根据主角位置调整屏幕位置 没有屏幕滑动
     **********************************************/
    private final void adScreen() {
      position_x = (hero_direct == -1) ? hero_pos_x - screen_w * 2 / 3 :
          hero_pos_x + screen_w * 2 / 3 - screen_w;
      position_y = hero_pos_y - screen_h / 2;
      if (position_x + screen_w > plat_w * 8) {
        position_x = plat_w * 8 - screen_w;
      }
      else if (position_x < 0) {
        position_x = 0;
      }
      if (position_y + screen_h > plat_h * 8) {
        position_y = plat_h * 8 - screen_h;
      }
      else if (position_y < 0) {
        position_y = 0;
      }
    }

  /**********************************************
   * 转换地图坐标到屏幕坐标
   * @param x 地图上元件的x坐标
   * @return 返回参数x对应的屏幕坐标
   **********************************************/
  private final int toScreenx(int x) {
    return x - position_x;
  }

  /**********************************************
   * 转换地图坐标到屏幕坐标
   * @param y 地图上元件的x坐标
   * @return 返回参数x对应的屏幕坐标
   **********************************************/
  private final int toScreeny(int y) {
    return y - position_y;
  }

  /**********************************************
   * 判断由参数表示的一个方框是否出现在屏幕上
   * @param x 方框的左上角在地图中的x坐标
   * @param y 方框的左上角在地图中的y坐标
   * @param r 方框的左上角在地图中的r坐标
   * @param b 方框的左上角在地图中的b坐标
   * @return 如果方框出现在屏幕上返回ture 否则false
   **********************************************/
  private final boolean visible(int x, int y, int r, int b) {
    return (r > position_x) && (x < position_x + screen_w) &&
        (b > position_y) && (y < position_y + screen_h);
  }

  /**********************************************
   * 在画布上指定剪切区域
   * @param x 方框左上角在地图上的x坐标
   * @param y 方框左上角在地图上的y坐标
   * @param w 方框宽度
   * @param h 方框高度
   **********************************************/
  private final void clipRect(int x, int y, int w, int h) {
    g.setClip(toScreenx(x), toScreeny(y), w, h);
  }

  /**********************************************
   * 画出图形的函数
   * @param face 需要画出的图形
   * @param x 图形左上角在地图上的x坐标
   * @param y 图形左上角在地图上的y坐标
   * @param manipulation DirectGraphics的图形翻转常数
   **********************************************/
  private final void drawFace(Image face, int x, int y, int manipulation) {
    dg.drawImage(face, toScreenx(x), toScreeny(y), 0, manipulation);
  }

  /**********************************************
   * 画制定颜色的半透明方块
   * @param x int 方块左上角x坐标
   * @param y int 方块左上角y坐标
   * @param r int 方块左上角r坐标
   * @param b int 方块左上角b坐标
   * @param color short 方块颜色
   **********************************************/
  private final void draw_alpharect(int x, int y, int r, int b, short color) {
    short[] frame_line = new short[r - x + 1];
    for (int i = r - x; i >= 0; i--) {
      frame_line[i] = color;
    }
    for (int i = 0; i < b - y + 1; i++) {
      dg.drawPixels(frame_line, true, 0, r - x + 1, x, y + i, r - x + 1, 1, 0,
                    4444);
    }
  }

  /**********************************************
   * 画对话框外框
   * @param x int
   * @param y int
   * @param r int
   * @param b int
   **********************************************/
  private final void draw_framerect(int x, int y, int r, int b) {
    g.setColor(0x002A14);
    g.drawLine(x + 3, y, r - 3, y); //上
    g.drawLine(r - 3, y, r, y + 3); //右上
    g.drawLine(r, y + 3, r, b - 3); //右
    g.drawLine(r, b - 3, r - 3, b); //右下
    g.drawLine(x + 3, b, r - 3, b); //下
    g.drawLine(x + 3, b, x, b - 3); //左下
    g.drawLine(x, y + 3, x, b - 3); //左
    g.drawLine(x, y + 3, x + 3, y); //左上
    g.setColor(0xEC8600);
    g.drawLine(x + 3, y + 1, r - 3, y + 1); //上
    g.drawLine(r - 3, y + 1, r - 1, y + 3); //右上
    g.drawLine(r - 1, y + 3, r - 1, b - 3); //右
    g.drawLine(r - 1, b - 3, r - 3, b - 1); //右下
    g.drawLine(x + 3, b - 1, r - 3, b - 1); //下
    g.drawLine(x + 3, b - 1, x + 1, b - 3); //左下
    g.drawLine(x + 1, y + 3, x + 1, b - 3); //左
    g.drawLine(x + 1, y + 3, x + 3, y + 1); //左上
    g.setColor(0x9C5A07);
    g.drawLine(x + 3, y + 2, r - 3, y + 2);
    g.drawLine(x + 3, b - 2, r - 3, b - 2);
    g.drawLine(x + 2, y + 3, x + 2, b - 3);
    g.drawLine(r - 2, y + 3, r - 2, b - 3);
  }

  /**********************************************
   * 画对话框方框
   * @param x int
   * @param y int
   * @param r int
   * @param b int
   **********************************************/
  private final void drawframe(int x, int y, int r, int b) {
    draw_alpharect(x, y, r, b, (short) 0x9210);
    draw_framerect(x, y, r, b);
  }

  //****************************************************************************
   /** @todo: 音乐播放 */
   //****************************************************************************
//    private final int SOUND_MISSTART = 0;
//  private final int SOUND_GET_PROP = 1;
//  private final int SOUND_GETBEING = 2;
//  private final int SOUND_TOLLGATE = 3;
//  private final int SOUND_DEFEATED = 4;
//  private byte[][] sounds;
//  private Sound sound = new Sound(1, 100);
//
//  private void sound_load() {
//    InputStream in = stream_create("/sound.bin");
//    try {
//      int len = in.read();
//      sounds = new byte[len][];
//      for (int i = 0; i < len; i++) {
//        sounds[i] = new byte[in.read()];
//        in.read(sounds[i]);
//      }
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//
//  private void sound_play(int sound_id) {
//    if ( (sound_id < 0) || (sound_id >= sounds.length)) {
//      return;
//    }
//    sound.stop();
//    sound.init(sounds[sound_id], 1);
//    sound.play(1);
//  }
    boolean sound = true;
  int delay = 0;

//  public void playerUpdate(Player player, String string, Object object) {
//    if (sound) {
//      if (string.equals(PlayerListener.END_OF_MEDIA)) { //该文件已经播放完
//        try {
//          player.start(); //实现循环播放
//        }
//        catch (Exception e) {}
//      }
//    }
//  }

//  private void checkSound() {
//
//    if (sound && player.getState() != Player.STARTED) {
//      if (screen_index == SCREEN_GAMEBODY) {
//        playok();
//      }
//    }
//
//    if (!sound && player.getState() == Player.STARTED) {
//      stop();
//    }
//    if (screen_index != SCREEN_GAMEBODY) {
//      stop();
//    }
//  }

  Player player;
  public void playok() {
    if (sound) {
      try {
        InputStream in = getClass().getResourceAsStream("/sound/1.mid");
        player = Manager.createPlayer(in, "audio/midi");
        player.setLoopCount( -1);
//        player.start(); //开始播放
      }
      catch (Exception e) {
        System.out.println(e.toString());
      }
    }

  }

  public void stop() {
    if (sound) {
      try {
        player.stop();
      }
      catch (Exception e) {}
    }
  }

  //****************************************************************************
   /** @todo:数字信息 */
   //****************************************************************************
    private Vector infos = new Vector();
  private final int INFO_COUNT = 5;
  private final int INFO_POS_X = 0; //X坐标
  private final int INFO_POS_Y = 1; //Y坐标
  private final int INFO_VALUE = 2; //被显示的数字值
  private final int INFO_MOVED = 3; //已经位移的距离
  private final int INFO_SPACE = 4; //位移距离

//  private final void info_create(int pos_x, int pos_y, int value) {
//    int[] info = new int[INFO_COUNT];
//    info[INFO_POS_X] = pos_x;
//    info[INFO_POS_Y] = pos_y;
//    info[INFO_VALUE] = value;
//    info[INFO_MOVED] = 0;
//    info[INFO_SPACE] = 15;
//    infos.addElement(info);
//  }

  private final void info_run() {
    for (int i = infos.size() - 1; i >= 0; i--) {
      int[] info = (int[]) infos.elementAt(i);
      info[INFO_POS_Y] -= 1;
      info[INFO_MOVED] += 1;
      if (info[INFO_MOVED] > info[INFO_SPACE]) {
        infos.removeElementAt(i);
      }
    }
  }

  private final void info_draw() {
    g.setClip(0, 0, screen_w, screen_h);
    for (int i = infos.size() - 1; i >= 0; i--) {
      int[] info = (int[]) infos.elementAt(i);
      drawString(info[INFO_VALUE], toScreenx(info[INFO_POS_X]),
                 toScreeny(info[INFO_POS_Y]), FONT_ALIGN_MIDD, FONT_LITTLE);
    }
  }

  //****************************************************************************
   //屏幕显示全局变量
   //****************************************************************************
    protected static Graphics g;
  protected static DirectGraphics dg;
  protected static Font font = Font.getFont(0, 0, 8);

  //****************************************************************************
   // 屏幕分割数据
   //****************************************************************************
    protected final int screen_w = 176; //屏幕宽度
  protected final int screen_h = 208; //屏幕高度
  protected int position_x = 0; //屏幕左上角在地图上的X坐标
  protected int position_y = 0; //屏幕左上角在地图上的Y坐标

  //****************************************************************************
   /** @todo: 游戏循环 */
   //****************************************************************************
//    private static int framesec = 0; //游戏每秒的刷新帧数
    private static int interval = 100; //游戏每帧的显示时间
  protected static int framenum = 0;
  private static boolean running = false; //游戏循环进行标志
  private static boolean exitapp = false; //游戏

  public final void refresh() {
    repaint();
    serviceRepaints();
  } //刷新屏幕

  public void hideNotify() {
    if (screen_index == SCREEN_GAMEBODY) {
      gamemenu_index = 0;
      gamemenushow = true;
      stop();
    }
  }

  public void showNotify() {
    stop();
  }

  public final void stayapp() {
    if (screen_index == SCREEN_GAMEBODY) {
      gamemenu_index = 0;
      gamemenushow = true;
      stop();
    }
    else {
      running = false;
    }
  } //退出循环

  public final void exitapp() {
    exitapp = true;
  } //退出程序

  //游戏主循环函数
  public final boolean process() {
    int count = 0;
    long secon = 0;
    long timeold = 0;
    long timenow = 0;

    running = true;
    while (running && !exitapp) {
      if (exitapp) {
        return true;
      }
//      checkSound();
      timenow = System.currentTimeMillis();
      if (timenow >= interval + timeold) {
        timeold = timenow;
        framenum = (framenum < 9999) ? framenum + 1 : 0;
//        for(int i = 0; i < KEY_COUNT; i++)
//          if(key_Count[i] > 0)
//            key_Count[i]--;
//          else
//            key_Statuse &= ~(1 << i);
        process_tick();

        //计算当前的刷新帧数
        if (secon < (timenow / 1000)) {
          secon = timenow / 1000;
//          framesec = count;
          count = 1;
        }
        else {
          count++;
        }
      }
    }
    return exitapp;
  }

  protected void paint(Graphics g) {
    if (process_lock) {
      return;
    }
    this.g = g;
    dg = DirectUtils.getDirectGraphics(g);
    g.setClip(0, 0, screen_w, screen_h);
    g.setFont(font);
    process_draw();
  }

  //****************************************************************************
   /** @todo: 框架代码 */
   //****************************************************************************
    //****************************************************************************
     /** @todo: 文件读取 */
     //****************************************************************************
      /***********************************************
       * 建文件流对象
       * @param filename String 需要建立流的文件的名称
       * @return InputStream 建立的文件输入流
       ***********************************************/
      protected final InputStream stream_create(String filename) {
        InputStream in = getClass().getResourceAsStream(filename);
        while (in == null) {
          in = getClass().getResourceAsStream(filename);
        }
        return in;
      }

  /**********************************************
   * 跳过流中制定数量的数据
   **********************************************/
  protected final void skip(InputStream in, int num) throws IOException {
    while (num != 0) {
      num -= in.skip(num);
    }
  }

  /**********************************************
   * 从流中读取word量
   * @param in InputStream
   * @throws IOException
   * @return int
   **********************************************/
  protected final int readWord(InputStream in) throws IOException {
    return (short) (in.read() + (in.read() << 8));
  }

  /**********************************************
   * 从流中读取byte量
   * @param in InputStream
   * @throws IOException
   * @return byte
   **********************************************/
  protected final byte readByte(InputStream in) throws IOException {
    return (byte) in.read();
  }

  //****************************************************************************
   /** @todo:按键处理  */
   //****************************************************************************

    protected final static int KEY_SOFT1 = 0x00000001; //功能键 左
  protected final static int KEY_SOFT2 = 0x00000002; //功能键 右
  protected final static int KEY_LEFT = 0x00000004;
  protected final static int KEY_RIGH = 0x00000008;
  protected final static int KEY_OVER = 0x00000010;
  protected final static int KEY_DOWN = 0x00000020;
  protected final static int KEY_FIRE = 0x00000040;
  protected final static int KEY_JUMP = 0x00000080;
  protected final static int KEY_TOSS = 0x00000100;
  protected final static int KEY_HUAN = 0x00000090;
  private final static int KEY_COUNT = 9;
  private final static int KEY_SOFT1_INDEX = 0;
  private final static int KEY_SOFT2_INDEX = 1;
  private final static int KEY_LEFT_INDEX = 2;
  private final static int KEY_RIGH_INDEX = 3;
  private final static int KEY_OVER_INDEX = 4;
  private final static int KEY_DOWN_INDEX = 5;
  private final static int KEY_FIRE_INDEX = 6;
  private final static int KEY_JUMP_INDEX = 7;
  private final static int KEY_TOSS_INDEX = 8;

  protected static int key_Statuse = 0; //手机当前按键状态
  private static int[] key_Count = new int[KEY_COUNT];

  private int keyConvert(int keycode) {
    switch (keycode) {
      case FullCanvas.KEY_SOFTKEY1:
        return KEY_SOFT1;
      case FullCanvas.KEY_SOFTKEY2:
        return KEY_SOFT2;
      case FullCanvas.KEY_SOFTKEY3:
      case Canvas.KEY_NUM5:
        return KEY_FIRE;
      case FullCanvas.KEY_DOWN_ARROW:
        return KEY_DOWN;
      case FullCanvas.KEY_LEFT_ARROW:
        return KEY_LEFT;
      case FullCanvas.KEY_RIGHT_ARROW:
        return KEY_RIGH;
      case FullCanvas.KEY_UP_ARROW:
        return KEY_OVER;
      case Canvas.KEY_NUM0:
        return KEY_TOSS;
      case Canvas.KEY_NUM2:
        return KEY_OVER;
      case Canvas.KEY_NUM4:
        return KEY_LEFT;
      case Canvas.KEY_NUM6:
        return KEY_RIGH;
      case Canvas.KEY_NUM8:
        return KEY_DOWN;
      case Canvas.KEY_NUM1:
        return KEY_HUAN;
      case Canvas.KEY_POUND:
        return KEY_JUMP;
      case Canvas.KEY_STAR:
        return KEY_JUMP;
    }
//    }
    return 0;
  }

  /**********************************************
   *
   **********************************************/
  private void setkeystate(int keycode, boolean press) {
    if (press) {
      key_Statuse |= keycode;
    }
    else {
      key_Statuse &= ~keycode;

    }
    switch (keycode) {
      case KEY_SOFT1:
        key_Count[KEY_SOFT1_INDEX] = (press) ? 3 : 0;
        break;
      case KEY_SOFT2:
        key_Count[KEY_SOFT2_INDEX] = (press) ? 3 : 0;
        break;
      case KEY_LEFT:
        key_Count[KEY_LEFT_INDEX] = (press) ? 3 : 0;
        break;
      case KEY_RIGH:
        key_Count[KEY_RIGH_INDEX] = (press) ? 3 : 0;
        break;
      case KEY_OVER:
        key_Count[KEY_OVER_INDEX] = (press) ? 3 : 0;
        break;
      case KEY_DOWN:
        key_Count[KEY_DOWN_INDEX] = (press) ? 3 : 0;
        break;
      case KEY_FIRE:
        key_Count[KEY_FIRE_INDEX] = (press) ? 3 : 0;
        break;
      case KEY_JUMP:
        key_Count[KEY_JUMP_INDEX] = (press) ? 3 : 0;
        break;
      case KEY_TOSS:
        key_Count[KEY_TOSS_INDEX] = (press) ? 3 : 0;
        break;
    }
  }

  /**********************************************
   * 重载的FullCanvas的按键触发的方法
   * @param keyCode int 系统传入的按键代码
   **********************************************/
  protected void keyPressed(int keyCode) {
    setkeystate(keyConvert(keyCode), true);
    process_key(keyConvert(keyCode));
  }

  /**********************************************
   * 重载的FullCanvas的释放一个按键触发的方法
   * @param keyCode 系统传入的按键代码
   **********************************************/
  protected void keyRepeated(int keyCode) {
    setkeystate(keyConvert(keyCode), true);
  }

  /**********************************************
   * 重载的FullCanvas的释放一个按键触发的方法
   * @param keyCode 系统传入的按键代码
   **********************************************/
  protected void keyReleased(int keyCode) {
    setkeystate(keyConvert(keyCode), false);
  }

//**********************************************************
   //****************************************************************************
    /** @todo: 脚本部分 */
    //脚本从0开始
    //****************************************************************************
     private final int SCRIPT_ACTIVESCRIPT = 2;

//*************************  private final int SCRIPT_CANCELSCRIPT = 2;
   private final int SCRIPT_LOADPLAT = 4;
  private final int SCRIPT_SHOWTALK = 5;
  private final int SCRIPT_SHOWINFO = 6;
  private final int SCRIPT_GAINPROP = 7;
  private final int SCRIPT_DROPPROP = 8;
  private final int SCRIPT_MOVEHERO = 9;
  private final int SCRIPT_WALKHERO = 10;
  private final int SCRIPT_WALKOGRE = 11;
  private final int SCRIPT_LOADGAME = 12;
  private final int SCRIPT_SAVEGAME = 13;
  private final int SCRIPT_CREATEPROP = 14;
  private final int SCRIPT_REMOVEPROP = 15;
  private final int SCRIPT_CREATEOGRE = 16;
  private final int SCRIPT_REMOVEOGRE = 17;
  private final int SCRIPT_MOVESCREEN = 18;
  private final int SCRIPT_SCREENFOCUS = 19;
  private final int SCRIPT_ADDITION = 20;
  private final int SCRIPT_SUBTRATE = 21;
  private final int SCRIPT_MULTIPLI = 22;
  private final int SCRIPT_DIVISION = 23;
  private final int SCRIPT_SETVALUE = 24;
  private final int SCRIPT_IFLESS = 25;
  private final int SCRIPT_IFMORE = 26;
  private final int SCRIPT_IFEQUAL = 27;
  private final int SCRIPT_IFNOTEQUAL = 28;
  private final int SCRIPT_ELSE = 29;
  private final int SCRIPT_TASKEND = 1;
  private final int SCRIPT_TYPE_HERO = 0;
  private final int SCRIPT_TYPE_OGRE = 1;
  private final int SCRIPT_TYPE_LIFT = 2;
  private final int SCRIPT_TYPE_ONOF = 3;

  private final int SCRIPT_CONDITION_COUNT = 6;
  private final int SCRIPT_CONDITION_DEFIN = 0;
  private final int SCRIPT_CONDITION_MAPID = 1;
  private final int SCRIPT_CONDITION_RECTX = 2;
  private final int SCRIPT_CONDITION_RECTY = 3;
  private final int SCRIPT_CONDITION_RECTR = 4;
  private final int SCRIPT_CONDITION_RECTB = 5;
  private Vector script_active_list = new Vector(); //当前处于活动状态的脚本
  private Vector script_execute_list = new Vector();

  /**********************************************
   * 激活脚本的入口
   * @param script_index int 需要激活的脚本的索引
   **********************************************/
  private final void script_active(int script_index) {
    InputStream in = getClass().getResourceAsStream("/script.bin");
    System.out.println("active script" + script_index);
    try {
      int total = in.read();
      if ( (script_index >= 0) || (script_index < total)) {
        boolean include = false;
        for (int i = script_active_list.size() - 1; i >= 0; i--) {
          include |= ( (int[]) script_active_list.elementAt(i))[
              SCRIPT_CONDITION_DEFIN] == script_index;
        }
        if (!include) {
          skip(in, script_index * 7);
          int[] script_condition = new int[SCRIPT_CONDITION_COUNT];
          script_condition[SCRIPT_CONDITION_DEFIN] = script_index;
          script_condition[SCRIPT_CONDITION_MAPID] = in.read();
          script_condition[SCRIPT_CONDITION_RECTX] = in.read() * 8;
          script_condition[SCRIPT_CONDITION_RECTY] = in.read() * 8;
          script_condition[SCRIPT_CONDITION_RECTR] = in.read() * 8 +
              script_condition[SCRIPT_CONDITION_RECTX];
          script_condition[SCRIPT_CONDITION_RECTB] = in.read() * 8 +
              script_condition[SCRIPT_CONDITION_RECTY];
          script_active_list.addElement(script_condition);
        }
      }
      in.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
//    printScript(script_active_list);
  }

  /**********************************************
   * 取消脚本入口的活动状态
   * @param script_index int 脚本索引
   **********************************************/
  private final void script_cancel(int script_index) {
    for (int i = script_active_list.size() - 1; i >= 0; i--) {
      if ( ( (int[]) script_active_list.elementAt(i))[SCRIPT_CONDITION_DEFIN] ==
          script_index) {
        script_active_list.removeElementAt(i);
      }
    }
  }

  private final void script_setvalue(int type, int object_index,
                                     int param_index, int value) {
    switch (type) {
      case SCRIPT_TYPE_HERO:
        switch (param_index) {
          case 16:
            hero_setstatus(value);
            break;
          case 17:
            hero_setaction(value);
            break;
          case 18:
            hero_setcframe(value);
            break;
          case 19:
            hero_setdirect(value);
            break;
            //*************************
//         case 20: hero_undee = value;    break; //刀光是否出现
        }
        break;
      case SCRIPT_TYPE_OGRE:
      case SCRIPT_TYPE_LIFT:
      case SCRIPT_TYPE_ONOF:
    }
  }

  private final void script_execute(int script_index) {
    InputStream in = getClass().getResourceAsStream("/script.bin");
    try {
      int script_total = in.read();
      if ( (script_index >= 0) && (script_index < script_total)) {
        skip(in, script_index * 7 + 5);
        int offset = readWord(in);
        System.out.println( (offset - script_index * 7 - 8));
        skip(in, offset - script_index * 7 - 8);
        DataInputStream scriptin = new DataInputStream(in);
        while (script_process(scriptin)) {
          ; //如果脚本在运行中
        }
        script_cancel(script_index);
      }
      in.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**********************************************
   * 如果满足脚本的入口条件 运行脚本
   **********************************************/
  private final void script_run() {
    for (int i = script_active_list.size() - 1; i >= 0; i--) {
      int[] script_condition = (int[]) (script_active_list.elementAt(i));
      int mapid = script_condition[SCRIPT_CONDITION_MAPID];
      int rectx = script_condition[SCRIPT_CONDITION_RECTX];
      int recty = script_condition[SCRIPT_CONDITION_RECTY];
      int rectr = script_condition[SCRIPT_CONDITION_RECTR];
      int rectb = script_condition[SCRIPT_CONDITION_RECTB];
      if ( (mapid == platid) &&
          (hero_pos_x > rectx) && (hero_pos_y > recty) &&
          (hero_pos_x < rectr) && (hero_pos_y < rectb)) {
        script_execute(script_condition[SCRIPT_CONDITION_DEFIN]);
        break;
      }
    }
  }

  /**********************************************
   * 单步执行脚本指令
   * @param script_in InputStream 存储脚本的流
   * @return boolean 返回该脚本需要继续运行
   **********************************************/
  private boolean script_process(DataInputStream script_in) {
    try {
      int temp = script_in.read();
      System.out.println(temp + "tt");
//          System.out.println("fdsfsfd");
      switch (temp) {
        case SCRIPT_TASKEND:
          return false;
        case SCRIPT_ACTIVESCRIPT:
          script_active(script_in.read());
          break;
//        case SCRIPT_CANCELSCRIPT: script_cancel(script_in.read()); break;
        case SCRIPT_LOADPLAT:
          plat_load(script_in.read());
          break;
        case SCRIPT_SHOWTALK:
          message_dialog_pause(readString(script_in));
          break;
        case SCRIPT_SHOWINFO:
          message_prompt_pause(readString(script_in));
          break;
        case SCRIPT_GAINPROP:
          break;
        case SCRIPT_DROPPROP:
          break;
        case SCRIPT_MOVEHERO:
          script_in.read();
          hero_move(script_in.read(), script_in.read());
          break;
        case SCRIPT_WALKHERO:
          break;
        case SCRIPT_WALKOGRE:
          break;
//       case SCRIPT_LOADGAME: game_record_load(); break;
//       case SCRIPT_SAVEGAME: game_record_save(); break;
        case SCRIPT_CREATEOGRE:
          break;
        case SCRIPT_REMOVEOGRE:
          break;
        case SCRIPT_CREATEPROP:
          alpha_fade(readWord(script_in), script_in.readByte() * 2);
          break;
        case SCRIPT_REMOVEPROP:
          break;
        case SCRIPT_MOVESCREEN:
          break;
        case SCRIPT_SCREENFOCUS: {
          position_x = readWord(script_in);
          position_y = readWord(script_in);
          System.out.println("focus x=" + position_x + " y=" + position_y);
          break;
        }
        case SCRIPT_IFLESS:
          break;
        case SCRIPT_IFMORE:
          break;
        case SCRIPT_IFEQUAL:
          break;
        case SCRIPT_IFNOTEQUAL:
          break;
        case SCRIPT_SETVALUE:
          script_setvalue(script_in.read(), script_in.read(), script_in.read(),
                          script_in.read());
          break;
        case SCRIPT_ELSE:
          break;
        case SCRIPT_ADDITION:
          break;
        case SCRIPT_SUBTRATE:
          break;
        case SCRIPT_MULTIPLI:
          break;
        case SCRIPT_DIVISION:
          break;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }

  /**********************************************
   * 从流中读取字符串
   * @param in InputStream
   * @throws IOException
   * @return String
   **********************************************/
  private final String readString(InputStream in) throws IOException {
    byte[] bytes = new byte[in.read()];
    in.read(bytes);
    return new String(bytes, "UTF-8");
  }

  /**********************************************
   * 设置主角当前状态
   * @param status_id int 主角状态索引
   **********************************************/
  private final void hero_setstatus(int status_id) {
    if (hero_status == status_id) {
      return;
    }
    hero_status = status_id;
  }

  private final void hero_setdirect(int direct) {
    hero_direct = direct;
  }

  private final int SCREEN_INFO = 9;

//****************************************************************************
   /** @todo: 对话框 */
//****************************************************************************
    private String message_prompt = null;
  private String message_dialog = null;
  private boolean dialog_position = false;
  private String message_screen = null;

  private final void message_screen(String msg) {
    message_screen = msg;
    screen_index = SCREEN_INFO;
    refresh();
  }

  private final void message_screen_show() {
    if (message_screen == null) {
      return;
    }
    g.setColor(0);
    g.fillRect(0, 0, screen_w, screen_h);
    g.setColor(0xec8600);
    g.drawString(message_screen, screen_w >> 1,
                 (screen_h - font.getHeight()) >> 1, 0x11);
  }

  private final void message_prompt(String msg) {
    message_prompt = msg;
    refresh();
  }

  private final void message_prompt_pause(String msg) {
    message_prompt = msg;
    refresh();
    tick_pause();
    message_prompt = null;
  }

  private final void message_prompt_show() {
    if (message_prompt == null) {
      return;
    }
    drawframe(10, 54, screen_w - 10, 74);
    g.setColor(0xFFFFFF);
    int length = message_prompt.length();
    for (int i = length; i > 0; i--) {
      if (font.substringWidth(message_prompt, 0, i) < 100) {
        g.drawSubstring(message_prompt, 0, i, screen_w >> 1, 58, 0x11);
        break;
      }
    }
  }

  private final void message_dialog_pause(String msg) {
    message_dialog = msg;
//    System.out.println("word:"+message_dialog);
    refresh();
    tick_pause();
    message_dialog = null;
  }

  private final void message_dialog_show() {
    System.out.println("word:" + message_dialog);
    if (message_dialog == null) {
      return;
    }
    int dialog_y = (dialog_position) ? 2 : screen_h - 46;
    g.setClip(0, 0, screen_w, screen_h);
    int offset = 0;
    int line = 0;
    drawframe(4, dialog_y, screen_w - 4, dialog_y + 45);
    g.setColor(0xFFFFFF);
    for (int i = 0; i < message_dialog.length(); i++) {
      if (line == 3) {
        break;
      }
      if ( (font.substringWidth(message_dialog, offset, i - offset) >
            (screen_w - 32)) || (i == message_dialog.length() - 1)) {
        g.drawSubstring(message_dialog, offset, i - offset, 10,
                        line * 13 + dialog_y + 4, 0);
        offset = i;
        line++;
      }
    }
  }

  /**********************************************
   * 暂停游戏循环 直到按下任意键
   **********************************************/
  private final void tick_pause() {
    refresh();
    key_Pressed = 0;
    System.gc();
    while (key_Pressed == 0) {
      try {
        Thread.sleep(100);
      }
      catch (Exception e) {}
    }
    key_Pressed = 0;
  }

  private static int key_Pressed = 0; //当前被按下的的键

//  private void changeCRC(byte[] ImagePix) {
//     int numOfbyte = (ImagePix[33] << 24) + (ImagePix[34] << 16) + (ImagePix[35] << 8) + ImagePix[36];
//     byte[] data = new byte[numOfbyte + 4];
//     System.arraycopy(ImagePix, 37, data, 0, data.length);
//     int[] crcTable = new int[256];
//     for (int n = 0; n < 256; n++) {
//       int c = n;
//       for (int k = 0; k < 8; k++) {
//         if ( (c & 1) == 1)
//           c = 0xedb88320 ^ (c >>> 1);
//         else
//           c >>>= 1;
//         crcTable[n] = c;
//       }
//     }
//     int crcValue = updateCRC(data, 0, data.length, crcTable);
//     ByteArrayOutputStream bout = new ByteArrayOutputStream();
//     DataOutputStream dout = new DataOutputStream(bout);
//     try {
//       dout.writeInt(crcValue);
//     }
//     catch (IOException ex) {
//     }
//     byte[] crc = bout.toByteArray();
//     System.arraycopy(crc, 0, ImagePix, 41 + numOfbyte, 4);
//   }
//
//   private int updateCRC(byte[] data, int off, int len, int[] crcTable) {
//     int crc = 0xffffffff;
//     for (int n = 0; n < len; n++) {
//       crc = crcTable[ (crc ^ data[off + n]) & 0xff] ^ (crc >>> 8);
//     }
//     return crc ^ 0xffffffff;
//   }

}
