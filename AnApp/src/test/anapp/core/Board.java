package test.anapp.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

	private int col = 1;
	private int row = 1;

	private int pos = 0;
	
	public Board() {
	}
	
	public int getCurrentPos()
	{
		return pos;
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
	
	class Numbers {
		
		private final short[] INITIAL = new short[]{3,1,2,4,6,7,5,8,9,11,10,14,13,12,15,16};
		
		private List<Integer> current = new ArrayList<Integer>();
		
		Numbers() {
			for (short no : INITIAL) {
				current.add((int) no);
			}
		}
		
		void scramble() {
			 Collections.shuffle(current);
		}
		
		void swap(int index1, int index2) {
			int val1 = current.get(index1);
			int val2 = current.get(index2);
			current.set(index1, val2);
			current.set(index2, val1);
		}
		
		int getRandomNumber() {
			return (int )(Math.random() * 16 + 1);
		}
		
		int getUpperLeftMiddle() {
			return current.get(5);
		}
		
		int getLowerLeftMiddle() {
			return current.get(9);
		}
		
		int getUpperRightMiddle() {
			return current.get(6);
		}
		
		int getLowerRightMiddle() {
			return current.get(10);
		}
		
		int get(int row, int col) {
			switch (row) {
			case 1:
				return col - 1;
			case 2:
				return 4 + col - 1;
			case 3:
				return 8 + col - 1;
			case 4:
				return 12 + col - 1;
			default:
				throw new IllegalStateException("Illegal switch!");
			}
			
			
		}
		
	}
	
}
