package casper;

public interface Physics //physics of some thing about Data and public method
{
	public final int acceleration = 1;//+speed
	
	public boolean isCollidingWithMap(int cellX,int cellY);
	public boolean isCollidingWithProduct();
}