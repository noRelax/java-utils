/////////////////////////////////////////
//棋子类
class ChessMan
{
	private boolean m_chessMan;
	public ChessMan(boolean man)
	{
		m_chessMan = man;
	}
	public boolean toBool()
	{
		return m_chessMan;
	}
}

/////////////////////////////////////////
//棋盘类
class ChessBoard
{
	private ChessMan [][] m_board;
	/*数组各值现在都为null*/
	ChessBoard()
	{
		m_board = new ChessMan[1+8][1+8];
	}
	public void initChessBoard()
	{
		for(int x=1; x<=8; x++)
		{
			for(int y=1; y<=8; y++)
			{
				m_board[x][y] = null;
			}
		}
		
		m_board[4][4] = new ChessMan(AllDef.BLACK);
		m_board[5][5] = new ChessMan(AllDef.BLACK);
		m_board[4][5] = new ChessMan(AllDef.WHITE);
		m_board[5][4] = new ChessMan(AllDef.WHITE);
	}
	public ChessMan getChessMan(int x, int y)
	{
		return m_board[x][y];
	}
	public void changeBoard(int x, int y, boolean who)
	{
		m_board[x][y] = null;
		m_board[x][y] = new ChessMan(who);
	}
}

/////////////////////////////////////////
//玩家类
public class Player 
{
	private boolean m_turn;
	private static ChessBoard m_bod;
	
	public int m_scroe;
	
	Player(boolean who)
	{
		m_turn = who;
		m_bod = new ChessBoard();
		m_bod.initChessBoard();
		
		m_scroe = 0;
	}
	private boolean eat(int x, int y, int xChange, int yChange)
	{
		//如果放子位置坐标过大或过小,返回false
		if(x<1 || x>8 || y<1 || y>8)
		{
			return AllDef.ERROR;
		}
		if(m_bod.getChessMan(x,y) != null)
		{
			return AllDef.ERROR;
		}
		int xNext = x + xChange;
		int yNext = y + yChange;
		while(true)
		{
			//如果延着制定方向,下一个子的坐标过大或过小,及超出棋盘范围,返回false
			//如果下一个棋子和要下的棋子是同一种颜色,返回false
			if(xNext<1 || xNext>8 || yNext<1 || yNext>8|| 
				m_bod.getChessMan(xNext, yNext) == null || 
				m_bod.getChessMan(x+xChange, y+yChange).toBool() == m_turn)
			{
				return AllDef.ERROR;
			}
			//吃子
			if(m_bod.getChessMan(xNext, yNext).toBool() == m_turn)
			{
				do
				{
					x = x + xChange;
					y = y + yChange;
					m_bod.changeBoard(x,y,m_turn); //吃子
				}while(x!=xNext || y!=yNext);
				break;
			}
			xNext = xNext + xChange;
			yNext = yNext + yChange;
		}
		return AllDef.OK;
	}
	private boolean judge(int x, int y, int xChange, int yChange)
	{
		int xNext = x + xChange;
		int yNext = y + yChange;
		if(m_bod.getChessMan(x,y) != null)
		{
			return AllDef.ERROR;
		}
		while(true)
		{
			if(xNext<1 || xNext>8 || yNext<1 || yNext>8|| 
				m_bod.getChessMan(xNext, yNext) == null || 
				m_bod.getChessMan(x+xChange, y+yChange).toBool() == m_turn)
			{
				return AllDef.ERROR;
			}
			//判断
			if(m_bod.getChessMan(xNext, yNext).toBool() == m_turn)
			{
				break;
			}
			xNext = xNext + xChange;
			yNext = yNext + yChange;
		}
		return AllDef.OK;
	}
	public int result()
	{
		//返回结果 WIN NOWIN NOSET
		int x,y;
		boolean result = false;

		for(x=1; x<=8 && false==result; x++)	
		{
			for(y=1; y<=8 && false==result; y++)
			{
				result = judge(x,y,0,-1) || result;
				result = judge(x,y,0,1)  || result;
				result = judge(x,y,-1,-1)|| result;
				result = judge(x,y,-1,0) || result;
				result = judge(x,y,-1,1) || result;
				result = judge(x,y,1,-1) || result;
				result = judge(x,y,1,0)  || result;
				result = judge(x,y,1,1)  || result;
			}
		}
		if(false == result)
		{
			return AllDef.NOSET;
		}
		
		for(x=1; x<=8; x++)	
		{
			for(y=1; y<=8; y++)
			{
				if (m_bod.getChessMan(x, y) != null && 
						m_bod.getChessMan(x,y).toBool() != m_turn)
				{
					return AllDef.NOWIN;
				}
			}
		}
		return AllDef.WIN;
	}
	public int getNum()
	{
		int num = 0;
		for(int x=1; x<=8; x++)	
		{
			for(int y=1; y<=8; y++)
			{
				if (m_bod.getChessMan(x, y) != null && 
						m_bod.getChessMan(x,y).toBool() == m_turn)
				{
					num++;
				}
			}
		}
		return num;
	}
	public boolean setChess(int x, int y)
	{
		//判断能不能下函数
		//返回结果 OK ERROR
		boolean result = false;
		result = eat(x,y,0,-1) || result;
		result = eat(x,y,0,1)  || result;
		result = eat(x,y,-1,-1)|| result;
		result = eat(x,y,-1,0) || result;
		result = eat(x,y,-1,1) || result;
		result = eat(x,y,1,-1) || result;
		result = eat(x,y,1,0)  || result;
		result = eat(x,y,1,1)  || result;//分别在八个方向上吃子
		
		if(AllDef.OK == result)
		{
			m_bod.changeBoard(x, y, m_turn);
		}
		return result;
	}
	public ChessMan getChess(int x, int y)
	{
		return m_bod.getChessMan(x, y);
	}
	public void init()
	{
		m_bod.initChessBoard();
	}
	public int[] aiXY()
	{
		int x,y;
		int xy[] = new int[2];
		boolean temp[][] = new boolean[1+8][1+8];
		for(x=1; x<=8; x++)
		{
			for(y=1; y<=8; y++)
			{
				temp[x][y] = false;
				boolean result = false;
				result = judge(x,y,0,-1) || result;
				result = judge(x,y,0,1) || result;
				result = judge(x,y,-1,-1) || result;
				result = judge(x,y,-1,0) || result;
				result = judge(x,y,-1,1) || result;
				result = judge(x,y,1,-1) || result;
				result = judge(x,y,1,0) || result;
				result = judge(x,y,1,1) || result;
				if(result == true )
				{
					temp[x][y] = true;
				}
			}
		}
		for(x=1; x<=8; x++)
		{
			for(y=1; y<=8; y++)
			{
		
				if(true==temp[x][y])
				{
					if((1==x||3==x||6==x||8==x) && (1==y||3==y||6==y||8==y) && x!=y && x!=y+3 && y!=x+3)
					{
						xy[0]= x;
						xy[1] = y;
						return xy;
					}
				}
			}
		}
		for(x=1; x<=8; x++)
		{
			for(y=1; y<=8; y++)
			{
				if((2==x||7==x||2==y||7==y) && true==temp[x][y])
				{
					continue;
				}
				if(true==temp[x][y])
				{
					xy[0]= x;
					xy[1] = y;
					return xy;
				}
			}
		}
		for(x=1; x<=8; x++)
		{
			for(y=1; y<=8; y++)
			{
				if((2==x&&7==y) || (7==x&&2==y) || (2==x&&2==y) || (7==x&&7==y) ||
				   (1==x&&2==y) || (2==x&&1==y) || (1==x&&7==y) || (7==x&&1==y) ||
				   (8==x&&2==y) || (2==x&&8==y) || (8==x&&7==y) || (7==x&&8==y)
				   )
				{
					continue;
				}
				if(true==temp[x][y])
				{
					xy[0]= x;
					xy[1] = y;
					return xy;
				}
			}
		}
		for(x=1; x<=8; x++)
		{
			for(y=1; y<=8; y++)
			{
				if(true==temp[x][y])
				{
					xy[0]= x;
					xy[1] = y;
					return xy;
				}
			}
		}
		xy[0] = xy[1] = -2;
		return xy;
	}
}