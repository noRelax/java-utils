
public class Game 
{
	public Player m_black;
	public Player m_white;
	public boolean m_turn;
	
	public int m_state;
	
	public Game()
	{
		m_turn = AllDef.BLACK;
		
		m_state = AllDef.READY;
		
		m_black = new Player(AllDef.BLACK);
		m_white = new Player(AllDef.WHITE);
	}
}
