/*
 * @(#)BuddyTree.java
 *
 * Copyright (c) 2002, Jang-Ho Hwang
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 	1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 	2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 	3. Neither the name of the Jang-Ho Hwang nor the names of its contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *    $Id: BuddyTree.java,v 1.21 2004/08/04 07:05:49 xrath Exp $
 */
package rath.jmsn;

import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Properties;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

import rath.msnm.*;
import rath.msnm.entity.MsnFriend;
import rath.msnm.entity.Group;
import rath.msnm.event.*;
import rath.msnm.msg.*;
import rath.msnm.ftp.VolatileDownloader;
import rath.msnm.ftp.VolatileTransferServer;

import rath.jmsn.ui.ChatDialog;
import rath.jmsn.entity.FileItem;
import rath.jmsn.util.Msg;
import rath.jmsn.util.MusicBox;
import rath.jmsn.util.BuddyComparator;
import rath.jmsn.util.UserStatusBox;
/**
 *
 * @author Jang-Ho Hwang, rath@linuxkorea.co.kr
 * @version $Id: BuddyTree.java,v 1.21 2004/08/04 07:05:49 xrath Exp $
 */
public class BuddyTree extends JTree implements DragSourceListener, DragGestureListener,
	DropTargetListener, TreeExpansionListener
{
	private MsnFriend me = null;
	private MSNMessenger msnm = null;
	private MainFrame main = null;
	private Image chatIcon = null;
	private DefaultTreeModel model = null;
	private DefaultMutableTreeNode root = null;
	private MouseAdapter treeMouseAdapter = new BuddyMouseAdapter();
	private int inboxUnread = -1;
	
	/**
	 * Determine offline buddy has shown.
	 */
	private boolean offView = true;

	/**
	 * key is Group Index, value is MutableTreeNode.
	 */
	private Hashtable groupMap = new Hashtable();

	/**
	 * User's loginName or connected session id is key,
	 * and related ChatDialog is value.
	 */
	private Hashtable sessionMap = new Hashtable();

	/**
	 * Pre-creation dialog table,
	 * Key is target user's LoginName,
	 * Value is specify ChatDialog instance.
	 * <p>
	 * When you attempt to connect other new user, the value will put.
	 * When who joined and exists in dialogMap, remove that and
	 * notify value(dialog).
	 * Then current session set to found dialog, maybe queue flushed!
	 */
	private Hashtable dialogMap = new Hashtable();

	private DragSource dragSource = new DragSource();
	/**
	 * buddyListModified �̺�Ʈ ������, syncAllUsers �޼ҵ带 ȣ������ ����
	 * Ƚ���� �����Ѵ�. �⺻���� 0 �̰�, ���� �ʿ��� ��� 1�� ����������,
	 * 2-3���� �޽����� ���ÿ� �������� 2�� 3�� �����ϸ� ȿ�����̴�.
	 */
	private int nonUpdateCount = 0;
	private Hashtable expandGroupMap = new Hashtable();
	private Image backImage = null;
	private BuddyComparator comparator = new BuddyComparator();
	public BuddyRenderer renderer = new BuddyRenderer();

	public BuddyTree( MainFrame m )
	{
		this.main = m;
		this.msnm = m.getMessenger();
		msnm.addMsnListener( new Listener() );

		ToolTipManager.sharedInstance().registerComponent(this);

		setBackground( Color.white );
		setCellRenderer( renderer );
		addTreeExpansionListener( this );
		addMouseListener( treeMouseAdapter );
		addKeyListener( new KeyAdapter() {
			public void keyPressed( KeyEvent e )
			{
				if( e.getKeyCode()==KeyEvent.VK_ENTER )
				{
					DefaultMutableTreeNode o =
						(DefaultMutableTreeNode)getLastSelectedPathComponent();
					if( o==null )
						return;
					Object uo = o.getUserObject();
					if( uo instanceof MsnFriend && o!=root )
					{
						MsnFriend friend = (MsnFriend)uo;
						createNewSession( friend );
					}
				}
			}
		});
		getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );

		dragSource.createDefaultDragGestureRecognizer( this, DnDConstants.ACTION_COPY_OR_MOVE, this );
		new DropTarget( this, this );
		chatIcon = new ImageIcon( getClass().getResource("/resources/icon/chat.jpg") ).getImage();
		setOpaque( false );
	}

	public void setMyPhoto( Image image )
	{
		for(Iterator i=dialogMap.values().iterator(); i.hasNext(); )
		{
			ChatDialog cd = (ChatDialog)i.next();
			cd.setMyPhoto(image);
		}
	}

	public void setBackgroundImage( Image image )
	{
		if( backImage!=null )
			backImage.flush();
		this.backImage = image;
		repaint();
	}

	public Image getBackgroundImage()
	{
	    return this.backImage;
	}

	/**
	 * Enable to view LoginName.
	 */
	public String getToolTipText( MouseEvent e )
	{
		Point p = e.getPoint();
		TreePath path  = getPathForLocation(p.x, p.y);

		if( path==null )
			return null;

		DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
		Object obj = node.getUserObject();
		if( obj!=null && obj instanceof MsnFriend )
		{
			MsnFriend friend = (MsnFriend)obj;
                        if( friend.getLoginName().equals( me.getLoginName() ) )
				return this.inboxUnread==-1?friend.getLoginName():Msg.get("label.inboxunread")+" : "+this.inboxUnread;
			else
				return friend.getLoginName();
		}
		return null;
	}

	/**
	 * �α����� �Ϸ�Ǿ����� �ؾߵ� �ϵ��� �����Ѵ�.
	 * �ڱ� �ڽ��� Root node�� �����ϰ�, Model�� ���� �����ϴ� ���� �۾��� �����Ѵ�.
	 *
	 */
	public void start( MsnFriend i )
	{
		this.me = i;
		this.me.setStatus( msnm.getInitialStatus() );
		this.me.setGroupIndex( -1 );

		this.root = new DefaultMutableTreeNode(me);
		this.model = new DefaultTreeModel( root );

		setModel( model );
		expandRow( 0 );

		renderer.forward = msnm.getBuddyGroup().getForwardList();
		renderer.block = msnm.getBuddyGroup().getBlockList();
		syncAllUsers();
	}

	/**
	 * �� �޼ҵ�� �� �׷��� Expand ���¸� Monitoring �ϴ� �̺�Ʈ �������̴�.
	 * Expandsion event�� �߻��� node�� Group �ν��Ͻ��̸� Map�� �־�д�.
	 *
	 * @param e
	 */
	public void treeCollapsed( TreeExpansionEvent e )
	{
		TreePath path = e.getPath();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
		Object o = node.getUserObject();
		if( o instanceof Group )
		{
			Group group = (Group)o;
		    expandGroupMap.remove( group.getIndex() );
		}
	}

	/**
	 * �� �޼ҵ�� �� �׷��� Expand ���¸� Monitoring �ϴ� �̺�Ʈ �������̴�.
	 * Expandsion event�� �߻��� node�� Group �ν��Ͻ��̸� Map���� �����Ѵ�.
	 *
	 * @param e
	 */
	public void treeExpanded( TreeExpansionEvent e )
	{
		TreePath path = e.getPath();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
		Object o = node.getUserObject();
		if( o instanceof Group )
		{
			Group group = (Group)o;
		    expandGroupMap.put( group.getIndex(), path );
		}
	}

	protected DefaultMutableTreeNode findGroupNode( Integer groupIndex )
	{
		return (DefaultMutableTreeNode)groupMap.get(groupIndex);
	}

	/**
	 * ��� child���� ��������, ForwardList�� �ִ� ��� ����ڵ���
	 * Root node�� �ϰ� �����Ѵ�.
	 *
	 * ��� �׷� ����Ʈ�� ��Ÿ���� �ȴ�. (������ �׷��̴�)
	 * �� �޼ҵ尡 �����ϴ� ���� ��Ÿ������.
	 * <p>
	 * <ul>
	 *   <li>Root node�� ��� �׷��� children�� �����Ѵ�.
	 *   <li>GroupList�κ��� �׷� TreeNode���� �߰��Ѵ�.
	 *   <li>�� Buddy���� �ùٸ� group treenode�� �߰��Ѵ�.
	 * </ul>
	 */
	void syncAllUsers()
	{
		if( root==null || model==null )
		    return;
		root.removeAllChildren();

		for(Iterator i=groupMap.values().iterator(); i.hasNext(); )
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)i.next();
			node.removeAllChildren();
		}
		groupMap.clear();

		GroupList gl = msnm.getBuddyGroup().getGroupList();
		gl.sort();
		for(Iterator i=gl.iterator(); i.hasNext(); )
		{
			Group group = (Group)i.next();
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(group);
			root.add( node );
			groupMap.put( group.getIndex(), node );
		}

		BuddyList bl = msnm.getBuddyGroup().getForwardList();
		bl.sort(comparator);
		for(Iterator i=bl.iterator(); i.hasNext(); )
		{
			MsnFriend friend = (MsnFriend)i.next();
			if( !offView && friend.getStatus().equals(UserStatus.OFFLINE) )
				continue;
			DefaultMutableTreeNode node = findGroupNode( friend.getGroupIndex() );
			DefaultMutableTreeNode fnode = new DefaultMutableTreeNode(friend);
			node.add( fnode );
		}
		model.reload();

		recoverExpandState();
	}

	/**
	 * ���������� Group node���� expand���¸� ��� ȸ���Ѵ�.
	 */
	private void recoverExpandState()
	{
		int rowIndex = 0;
		while( rowIndex < getRowCount() )
		{
		    TreePath path = getPathForRow(rowIndex++);
			if( path.getPathCount()==2 ) // Is Group Node Path?
			{
				Group group = (Group)((DefaultMutableTreeNode)
					path.getLastPathComponent()).getUserObject();
				if( expandGroupMap.containsKey(group.getIndex()) )
				    expandPath( path );
			}
		}
	}

	/**
	 * ���� Tree���� �־��� friend�� ��ġ�� Node�� ã�� ��ȯ�Ѵ�.
	 * ���� �������� �ʴ´ٸ� null�� ��ȯ�� ���̴�.
	 */
	protected DefaultMutableTreeNode findTreeNode( MsnFriend friend )
	{
		for(Enumeration e = root.children();e.hasMoreElements();)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)e.nextElement();
			for(Enumeration e1 = node.children(); e1.hasMoreElements(); )
			{
				DefaultMutableTreeNode fnode = (DefaultMutableTreeNode)e1.nextElement();
				if( fnode.getUserObject().equals(friend) )
					return fnode;
			}
		}
		return null;
	}

	/**
	 * toInsert ģ���� Group�� �߰��Ϸ��� �Ҷ�, ���ļ����� �ùٸ� GroupNode���� Index��
	 * ������ش�.
	 *
	 * @param groupNode ������ Group Node
	 * @param toInsert ���Ե� MsnFriend ��ü.
	 * @param node MsnFriend��ü�� UserObject�� ������ DefaultMutableTreeNode
	 * @return
	 */
	protected int insertPreferredIndex( DefaultMutableTreeNode groupNode, MsnFriend toInsert, DefaultMutableTreeNode toAdd )
	{
		int index = 0;
		for(Enumeration e=groupNode.children(); e.hasMoreElements(); )
		{
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)e.nextElement();
			MsnFriend friend = (MsnFriend)node.getUserObject();
			// ù��° �Ķ���Ͱ� ���� �ö󰡷��� ������ ��ȯ�Ǿ�� �Ѵ�.
			// �׸��� ���º����, ������ remove�ϰ�, �� �޼ҵ带 ���ؼ� �ٽ� add�Ǿ��� ���̴�.
			if( comparator.compare(friend, toInsert) > 0 )
			{
				groupNode.insert( toAdd, index );
				return index;
			}
			index++;
		}
		groupNode.add( toAdd );
		return index;
	}

	/**
	 * ����ڿ��� ���� �ɶ� ����Ѵ�.
	 *
	 * Old implementation: <p>
	 *   �ش� ģ���� ����� ������ �ִ��� ã�ƺ� ��,
	 *   �̹� �ξ��� ������ ���ٸ�, ����޼ҵ� doCallWait���� ������ �����Ѵ�.
	 *   ���� ������ ������� �ʾҴٸ�, ��ٷ� return�ϸ�,
	 *   ������ ã�Ұų� ���� �δµ� �����Ͽ��ٸ�, sessionMap(���̾�α� ������)
	 *   ���� �ش� sid�� ���� Dialog�� �ִ��� ã�ƺ� ��, ������ ���� ����� �ְ�,
	 *   ������ ������ show�Ѵ�.
	 * <p>
	 * Current implementation: <p>
	 *   Pre-dialog�� �ִ��� �����ϰ�, ���� ���ٸ�, ���̾�α� ���� ��,
	 *   PreDialog table�� put����, show �Ѵ�.
	 */
	void createNewSession( MsnFriend friend )
	{
		if( friend.getStatus()==UserStatus.OFFLINE )
			return;

		String loginName = friend.getLoginName();

		ChatDialog cd = (ChatDialog)dialogMap.get(loginName);
		if( cd!=null )
		{
			cd.setVisible(true);
			cd.requestFocus();
			return;
		}

		cd = new ChatDialog( main, msnm, loginName, me );
		cd.setIconImage( chatIcon );
		cd.addWindowListener( new DisposeAdapter() );
		dialogMap.put( loginName, cd );
		cd.setVisible(true);

		try
		{
			msnm.doCall( loginName );
		}
		catch( IOException e )
		{
			cd.dispose();
		}
	}

	/**
	 * �������κ��� �޽����� �������, ã���� �ϴ� ����ڰ� ������ ����Ǵ� �޼ҵ��̴�.
	 */
	protected void createNewSession(
		SwitchboardSession ss, final MsnFriend friend, final MimeMessage msg )
	{
		String loginName = friend.getLoginName();

		ChatDialog chat = null;
		if( (chat=(ChatDialog)dialogMap.get(loginName))==null )
		{
			chat = new ChatDialog(main, msnm, ss, me);
			chat.setIconImage( chatIcon );
			chat.addWindowListener( new DisposeAdapter() );
			dialogMap.put( loginName, chat );
		}
		else
		{
			chat.setSession( ss );
		}

		sessionMap.put( ss.getSessionId(), chat );
		final ChatDialog fChat = chat;
		chat.addComponentListener( new ComponentAdapter() {
			public void componentShown( ComponentEvent e )
			{
				fChat.appendMessage( friend, msg );
				fChat.removeComponentListener( this );
			}
		});
		SwingUtilities.invokeLater( new Runnable() {
			public void run()
			{
				fChat.setVisible(true);
				fChat.requestFocus();
			}
		});
	}

	public MsnFriend getSelectedFriend()
	{
		TreePath path = getSelectionPath();
		if( path==null || path.getPathCount()!=3 )
			return null;

		Object o = path.getLastPathComponent();
		if( o instanceof DefaultMutableTreeNode )
			o = ((DefaultMutableTreeNode)o).getUserObject();
		if( o instanceof MsnFriend )
			return (MsnFriend)o;
		return null;
	}

	public Group getSelectedGroup()
	{
	    TreePath path = getSelectionPath();
		if( path==null || path.getPathCount()!=2 )
			return null;

		Object o = path.getLastPathComponent();
		if( o instanceof DefaultMutableTreeNode )
			o = ((DefaultMutableTreeNode)o).getUserObject();
		if( o instanceof Group )
			return (Group)o; 
		return null;
	}

	public void setMyStatus( String mode )
	{
		MsnFriend me = (MsnFriend)root.getUserObject();
		me.setStatus( mode );
		repaint();
	}

	public void toggleOfflineView()
	{
	    setOfflineView( !this.offView );
	}

	/**
	 * �������� ����� ǥ�� ���θ� Toggle �Ѵ�.
	 * �׸��� ���� ���� ���δ� ���� ����� LocalCopy�� ����ȴ�.
	 */
	public void setOfflineView( boolean offView )
	{
		this.offView = offView;

		LocalCopy lc = main.getMessenger().getLocalCopy();
		lc.setProperty( "View.buddy.offline", String.valueOf(offView) );
		lc.storeInformation();

		if( msnm.isLoggedIn() && isVisible() )
			syncAllUsers();
	}

	/**
	 * ģ������ ��� mode�� �������� �����ϴ� ������,
	 * VIEW_FRIENDLY_NAME, VIEW_LOGIN_NAME�� ������ �� �ִ�.
	 */
	public void setBuddyView( int mode )
	{
		renderer.setBuddyView( mode );
		comparator.setBuddyView( mode );

		if( msnm.isLoggedIn() )
			syncAllUsers();
	}

	/* ----------------------------------------------------------------
	 *                     Inner classes definition
	 * ----------------------------------------------------------------
	 */
	private class BuddyMouseAdapter extends MouseAdapter
	{
		public void mousePressed( MouseEvent e )
		{
			TreePath rootPath = null;
			if( (rootPath=getPathForLocation(e.getX(), e.getY()))!=null )
			{
				int row = getRowForPath( rootPath );

				DefaultMutableTreeNode node =
					(DefaultMutableTreeNode)rootPath.getLastPathComponent();
				if( node==root )
				{
					JPopupMenu pop = main.menuBar.getStatusPopupMenu();
					pop.show( BuddyTree.this, e.getX(), e.getY() );
					return;
				}
			}
		}
		public void mouseClicked( MouseEvent e )
		{
			if( getRowForLocation( e.getX(), e.getY() )==-1 )
				return;	
			TreePath path = getPathForLocation(e.getX(), e.getY());
			if( path==null )
				return;

			if( e.getClickCount() > 1 )
			{
				DefaultMutableTreeNode o = (DefaultMutableTreeNode)path.getLastPathComponent();
				Object uo = o.getUserObject();
				if( uo instanceof MsnFriend && o!=root )
				{
					MsnFriend friend = (MsnFriend)uo;
					createNewSession( friend );
				}
			}
		}
	};

	private class Listener extends MsnAdapter
	{
		public void renameNotify( MsnFriend i )
		{
			if( i==null )
			{
			    JOptionPane.showMessageDialog( BuddyTree.this,
				Msg.get("err.209"),
				Msg.get("title.alarm"), JOptionPane.ERROR_MESSAGE );
				return;
			}

			// �ڱ� �ڽ��� �̸� ����� ���� �����Ѵ�.
			if( i.getLoginName().equals(me.getLoginName()) )
			{
				me.setFriendlyName( i.getFriendlyName() );
				SwingUtilities.invokeLater( new Runnable() {
					public void run()
					{
						syncAllUsers();
					}
				});
			}
		}

		public void loginComplete( final MsnFriend owner )
		{
			SwingUtilities.invokeLater( new Runnable() {
				public void run()
				{
					start( owner );
					main.menuBar.enableLogin();
				}
			});
		}
 
		public void notifyUnreadMail( Properties Prop, int unread )
		{
			inboxUnread = unread;	
			main.addEvent( Msg.get("label.inboxunread")+ " : " + unread, null );
		}

		private void refreshGroupNode()
		{
			for(Enumeration e = root.children();e.hasMoreElements();)
			{
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)e.nextElement();
				model.reload( node );
			}
		}

		public void listOnline( final MsnFriend friend )
		{
			SwingUtilities.invokeLater( new Runnable() {
				public void run()
				{
					DefaultMutableTreeNode node = findTreeNode(friend);
					if( node==null )
					{
						node = new DefaultMutableTreeNode(friend);
						insertPreferredIndex( findGroupNode(friend.getGroupIndex()), friend, node );
					}
					else
					{
						MsnFriend old = (MsnFriend)node.getUserObject();
						Integer oldGroupIndex = old.getGroupIndex();
						node.setUserObject( friend );

						// ���� Tree���� �����Ͽ��� ��
						DefaultMutableTreeNode oldGroup = findGroupNode( oldGroupIndex );
						DefaultMutableTreeNode newGroup = findGroupNode( friend.getGroupIndex() );

						oldGroup.remove( node );
						insertPreferredIndex( newGroup, friend, node );

						model.reload( oldGroup );
						model.reload( newGroup );
					}

					model.reload(node);
					refreshGroupNode();
				}
			});
		}

		public void instantMessageReceived( SwitchboardSession ss, MsnFriend friend,
			MimeMessage msg )
		{
			ChatDialog chat = (ChatDialog)sessionMap.get(ss.getSessionId());

			if( chat!=null ) // Existing session
			{
				chat.appendMessage( friend, msg );

				// To blink chat window
				if( !chat.isActive() )
					chat.toFront(); 
			}
			else             // New session
			{
				createNewSession(ss, friend, msg);
			}
		}

		/**
		 *
		 */
		public void userOnline( final MsnFriend friend )
		{
			final String loginName = friend.getLoginName();
			final String formatStatus = UserStatusBox.getFormattedUserStatus(friend.getStatus());
			final String formatName = friend.getFormattedFriendlyName();

			SwingUtilities.invokeLater( new Runnable() {
			public void run() {
			DefaultMutableTreeNode node = findTreeNode(friend);

			BuddyList list = msnm.getBuddyGroup().getForwardList();
			MsnFriend old = list.get( loginName );

			// ������ �־���, ���¸� �����Ͽ��� ���(�������°� OFFLINE�� �ƴѰ��)
			if( node!=null && !UserStatus.OFFLINE.equals(old.getOldStatus()) )
			{
				main.addEvent( formatStatus + " " + formatName + 
					" (" + loginName + ")", friend );
				old.setFriendlyName( friend.getFriendlyName() );
				old.setStatus( friend.getStatus() );
				node.setUserObject( old );

				node.removeFromParent();
				DefaultMutableTreeNode group = findGroupNode( old.getGroupIndex() );
				insertPreferredIndex( group, old, node );

				model.reload( group );
			}
			// ������ �����ų�, �־��µ� ���������̿��� ���
			else
			{
				if( node==null )
				{
					friend.setGroupIndex( old.getGroupIndex() );
					node = new DefaultMutableTreeNode(friend);

					DefaultMutableTreeNode group = findGroupNode(friend.getGroupIndex());
					insertPreferredIndex( group, friend, node );
					model.reload( group );
				}
				else
				{
					node.removeFromParent();
				    node.setUserObject( friend );

					DefaultMutableTreeNode group = findGroupNode(friend.getGroupIndex());
					insertPreferredIndex( group, friend, node );
					model.reload( group );
				}
				refreshGroupNode();

				main.addEvent( Msg.get("event.login", loginName, formatName), friend );
				MusicBox.play( MusicBox.SOUND_LOGIN );
			}
			}});
		}

		/**
		 * Tree���� �ش� ����ڸ� ã�Ƽ� ���¸� OFFLINE���� ������ ��,
		 * Model���� reload�Ѵ�.
		 */
		public void userOffline( final String loginName )
		{
			SwingUtilities.invokeLater( new Runnable() {
			public void run() {
			DefaultMutableTreeNode node = findTreeNode(new MsnFriend(loginName,""));
			if( node!=null )
			{
				MsnFriend friend = (MsnFriend)node.getUserObject();
				friend.setStatus( UserStatus.OFFLINE );
				if( offView )
				{
					node.removeFromParent();
				    DefaultMutableTreeNode group = findGroupNode( friend.getGroupIndex() );
					insertPreferredIndex( group, friend, node );
					model.reload( group );
				}
				else
				{
					DefaultMutableTreeNode parent =
						(DefaultMutableTreeNode)node.getParent();
					parent.remove( node );
					model.reload( parent );
					refreshGroupNode();
				}

				main.addEvent( Msg.get("event.logout.2", friend.getLoginName(),
					friend.getFormattedFriendlyName()), null );
			}
			else
				main.addEvent( Msg.get("event.logout.1", loginName), null );

			}});

			ChatDialog cd = (ChatDialog)dialogMap.get(loginName);
			if( cd!=null )
				cd.userOffline();
		}

		public void whoJoinSession( SwitchboardSession ss, final MsnFriend join )
		{
			String loginName = join.getLoginName();
			if( ss.getSessionId()==null )
				return;

			ChatDialog cd = null;
			if( (cd=(ChatDialog)sessionMap.get(ss.getSessionId()))==null )
			{
				if( (cd=(ChatDialog)dialogMap.get(loginName))==null )
					return;
				sessionMap.put( ss.getSessionId(), cd );
				cd.setSession( ss );
			}

			if( ss.getFriendCount()>1 )
			{
				MimeMessage msg = new MimeMessage();
				msg.setMessage( Msg.get("chatdlg.msg.whojoin", loginName,
					join.getFormattedFriendlyName()) );
				cd.appendMessage( msg );
			}
		}

		public void whoPartSession( SwitchboardSession ss, final MsnFriend part )
		{
		    String loginName = part.getLoginName();

			ChatDialog cd = null;
			if( (cd=(ChatDialog)sessionMap.get(ss.getSessionId()))!=null )
			{
				MimeMessage msg = new MimeMessage();

			    // ������ 5�� ����ϸ� SS���� �ڵ����� ������ ���������.
				// �Ʒ��� ������ ����ð����� 5�оȿ� �޽����� �������� �ʾ����� true�̴�.
				if( (System.currentTimeMillis() - 300000L) > cd.getLastMessagingTime() )
					msg.setMessage( Msg.get("chatdlg.msg.timeout", "5") );
				else
				    msg.setMessage( Msg.get("chatdlg.msg.whopart", loginName,
						part.getFormattedFriendlyName()) );

			    cd.appendMessage( msg );
			}
		}

		public void switchboardSessionStarted( SwitchboardSession ss )
		{
			ss.setTimeout( 0 );

			final MsnFriend friend = ss.getMsnFriend();
			if( friend!=null )
			{
				SwingUtilities.invokeLater( new Runnable() {
					public void run()
					{
						main.addEvent( Msg.get("event.ring", friend.getLoginName(),
							friend.getFormattedFriendlyName()), friend );
					}
				});
			}
		}

		public void switchboardSessionEnded( SwitchboardSession ss )
		{
			ChatDialog cd = (ChatDialog)sessionMap.remove(ss.getSessionId());
			if( cd!=null )
			{
				cd.sessionClosed();
				return;
			}
		}

		public void switchboardSessionAbandon( SwitchboardSession ss, String targetName )
		{
		    ChatDialog cd = (ChatDialog)dialogMap.get( targetName );
			if( cd!=null )
			{
				cd.sessionClosed();
				MimeMessage msg = new MimeMessage();
				msg.setMessage( Msg.get("chatdlg.msg.ssclose") );
				cd.appendMessage( msg );
			}
		}

		public void buddyListModified()
		{
			nonUpdateCount--;
			if( nonUpdateCount < 0 )
			{
				SwingUtilities.invokeLater( new Runnable() {
					public void run()
					{
						syncAllUsers();
					}
				});
			}
		    if( nonUpdateCount < 0 )
				nonUpdateCount = 0;
		}

		public void progressTyping( SwitchboardSession ss, MsnFriend friend, String view )
		{
			String sid = ss.getSessionId();
			ChatDialog cd = (ChatDialog)sessionMap.get(sid);
			if( cd!=null )
			{
				cd.whoTyping( view );
			}
		}

		public void filePosted( SwitchboardSession ss, int cookie, String filename,
			int filesize )
		{
			String sid = ss.getSessionId();
			ChatDialog cd = (ChatDialog)sessionMap.get(sid);
			if( cd==null )
			{
				cd = new ChatDialog(main, msnm, ss, me);
				cd.setIconImage( chatIcon );
				cd.addWindowListener( new DisposeAdapter() );

				sessionMap.put( sid, cd );
				String ln = ss.getMsnFriend().getLoginName();
				if( !dialogMap.containsKey(ln) )
					dialogMap.put( ln, cd );
				cd.setVisible(true);
				cd.requestFocus();
			}

			cd.addFileItem( new FileItem(ss,cookie,filename,filesize) );
		}

		public void allListUpdated()
		{
			SwingUtilities.invokeLater( new Runnable() {
				public void run()
				{
					syncAllUsers();
				}
			});
		}

		public void logoutNotify()
		{
		    SwingUtilities.invokeLater( new Runnable() {
				public void run()
				{
				    main.logout();
				}
			});
		}

		public void photoContextUpdated( MsnFriend friend, String ctx )
		{
			ChatDialog cd = (ChatDialog)dialogMap.get(friend.getLoginName());
			if( cd!=null )
			{
				try
				{
					cd.getSession().requestPhoto();
				}
				catch( IOException e )
				{
					e.printStackTrace();
				}
			}
			System.out.println( "* Photo Context Updated: " + friend.getLoginName() + ", ctx=" + ctx );
		}

		public void photoUpdated( SwitchboardSession ss, final MsnFriend friend, final Image img )
		{
			final ChatDialog cd = (ChatDialog)dialogMap.get(friend.getLoginName());
			
			SwingUtilities.invokeLater( new Runnable() {
				public void run()
				{
					if( cd!=null )
					{
						cd.setFriendPhoto( img );
					}
					else
					{
						friend.setPhoto( img );
					}
				}
			});
		}
	}

	private class DisposeAdapter extends WindowAdapter
	{
		/**
		 * sessionMap���� sid�� �ش��ϴ� Dialog�� �����ϰ�,
		 * dialogMap���� loginName�� �ش��ϴ� Dialog�� �����ϴ� ����
		 */
		public void windowClosing( WindowEvent e )
		{
			ChatDialog cd = (ChatDialog)e.getSource();
			SwitchboardSession ss = cd.getSession();
			if( ss!=null )
			{
				sessionMap.remove( ss.getSessionId() );
				try
				{
					ss.close();
				}
				catch( IOException ex ) {}
			}
			cd.close();
			dialogMap.remove( cd.getTitle() );
		}
	}

	public void updateAllDialogs()
	{
		for(Enumeration e=dialogMap.elements(); e.hasMoreElements(); )
		{
			ChatDialog cd = (ChatDialog)e.nextElement();
			SwingUtilities.updateComponentTreeUI( cd );
		}
	}

    public void dragEnter(DragSourceDragEvent dsde)
    {

    }

    public void dragOver(DragSourceDragEvent dsde)
    {

	}

    public void dropActionChanged(DragSourceDragEvent dsde)
    {

    }

    public void dragExit(DragSourceEvent dse)
    {

    }

    public void dragDropEnd(DragSourceDropEvent dsde)
    {

    }

	private int dragStartGroup = -1;
	/**
	 * �巡�׸� �����Ϸ��� �Ҷ�, �̰� dnd�� �ؾߵǴ°����� �ƴѰ�����
	 * �Ǻ��ϰ� ���� MsnFriend �ν��Ͻ��� �´ٸ�, startDrag �޼ҵ��
	 * �巡�׸� �����Ѵ�.
	 *
	 * @param dge �̺�Ʈ ��ü
	 */
    public void dragGestureRecognized(DragGestureEvent dge)
    {
		Point p = dge.getDragOrigin();
		TreePath path = getPathForLocation( p.x, p.y );
		if( path==null )
	   		return;
		Object o = ((DefaultMutableTreeNode)path.getLastPathComponent()).getUserObject();
		if( o instanceof MsnFriend )
		{
		    MsnFriend friend = (MsnFriend)o;
			dragStartGroup = friend.getGroupIndex().intValue();
			String ln = friend.getLoginName();
			Transferable t = new StringSelection(ln);
			dragSource.startDrag( dge, DragSource.DefaultCopyDrop, t, this );
		}
    }

	// ���⼭���ʹ� DragTargetListener interface �� implement �κ��̴�.

    public void dragEnter(DropTargetDragEvent e)
    {

    }

	private int oldDraggingIndex = -1;

    public void dragOver(DropTargetDragEvent e)
    {
		Point p = e.getLocation();
		TreePath path = getPathForLocation( p.x, p.y );
		if( path==null )
	   		return;
		Object o = ((DefaultMutableTreeNode)path.getLastPathComponent()).getUserObject();

		int groupIndex = -1;
		if( o instanceof MsnFriend )
		{
		    MsnFriend f = (MsnFriend)o;
		    groupIndex = f==this.me ? -1 : f.getGroupIndex().intValue();
		}
		else
		if( o instanceof Group )
		{
			Group g = (Group)o;
			groupIndex = g.getIndexInt();
		}
		if( dragStartGroup==groupIndex )
		{
			groupIndex = -1;
			e.rejectDrag();
		}

		renderer.setDraggingGroup(groupIndex);
		if( oldDraggingIndex!=groupIndex )
		{
			oldDraggingIndex = groupIndex;
		    repaint();
		}
    }

    public void dropActionChanged(DropTargetDragEvent e)
    {

    }

    public void dragExit(DropTargetEvent e)
    {
		oldDraggingIndex = -1;
		renderer.setDraggingGroup(-1);
		repaint();
    }

    public void drop(DropTargetDropEvent e)
    {
		Point p = e.getLocation();
		TreePath path = getPathForLocation( p.x, p.y );
		if( path==null )
	   		return;

		oldDraggingIndex = -1;
		renderer.setDraggingGroup(-1);
		repaint();

		Object o = ((DefaultMutableTreeNode)path.getLastPathComponent()).getUserObject();
		int groupIndex = -1;
		if( o instanceof MsnFriend )
			groupIndex = ((MsnFriend)o).getGroupIndex().intValue();
		else
		if( o instanceof Group )
			groupIndex = ((Group)o).getIndexInt();
		if( dragStartGroup==groupIndex )
			return;

		try
		{
			String loginName = (String)e.getTransferable().getTransferData( DataFlavor.stringFlavor );
			e.acceptDrop( DnDConstants.ACTION_MOVE );
			e.getDropTargetContext().dropComplete( true );

		    BuddyList list = msnm.getBuddyGroup().getForwardList();
			MsnFriend friend = list.get(loginName);
			friend.setGroupIndex(groupIndex);

			nonUpdateCount = 2;
			// ������ �����ϵ��� ����
		    msnm.moveGroupAsFriend( friend, dragStartGroup, groupIndex );
			// ���� Tree���� �����Ͽ��� ��
			DefaultMutableTreeNode oldGroup = findGroupNode( new Integer(dragStartGroup) );
			DefaultMutableTreeNode newGroup = findGroupNode( new Integer(groupIndex) );
			DefaultMutableTreeNode nodeFriend = this.findTreeNode(friend);

			oldGroup.remove( nodeFriend );
			insertPreferredIndex( newGroup, friend, nodeFriend );

			model.reload( oldGroup );
			model.reload( newGroup );
		}
		catch( Exception ex )
		{
			e.rejectDrop();
		}
    }

	public void paintComponent( Graphics g )
	{
		g.setColor( Color.white );
		g.fillRect( 0, 0, getWidth(), getHeight() );
		if( backImage!=null )
		{
			JViewport view = (JViewport)getParent();
			Point p = view.getViewPosition();
			int w = view.getWidth();
			int h = view.getHeight();
			int iw = backImage.getWidth(null);
			int ih = backImage.getHeight(null);
			g.drawImage( backImage, w-iw+p.x, h-ih+p.y, null );
		}
		super.paintComponent( g );
	}
}
