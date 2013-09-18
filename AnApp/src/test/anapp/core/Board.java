package test.anapp.core;

public class Board {

	private int col = 1;
	private int row = 1;

	
	public Board() {
	}
	
	public int getCurrent()
	{
		return col * row;
	}
	
	public boolean moveUp() {
		if (row == 1)
		{
			return false;
		}
		row--;
		return true;
	}
	
	public boolean moveDown() {
		if (row == 4)
		{
			return false;
		}
		row++;
		return true;
	}
	
	public boolean moveLeft()
	{
		if (col == 1)
		{
			return false;
		}
		col--;
		return true;
	}
	
	public boolean moveRight()
	{
		if (col == 4)
		{
			return false;
		}
		col++;
		return true;
	}
	
}
