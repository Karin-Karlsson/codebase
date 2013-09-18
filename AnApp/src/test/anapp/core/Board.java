package test.anapp.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

	private final int selectedNo;
	private final int initPosOfSelectedNo;
	private final Numbers numbers = new Numbers();
	private final List<Integer> goal;
	private final List<Integer> neighbours;
	
	private int col = 1;
	private int row = 1;

	private int pos = 0;

	public Board() {
		numbers.scramble();
		goal = Collections.unmodifiableList(numbers.cloneNumbersList());
		selectedNo = numbers.getRandomNumber();
		initPosOfSelectedNo = numbers.findIndex(selectedNo);
		neighbours =  Collections.unmodifiableList(getNeighbours(initPosOfSelectedNo));
		initPosColAndRow(initPosOfSelectedNo);
		numbers.scramble();
	}
	
	private void initPosColAndRow(int excludePos) {
		pos = excludePos;
		while (pos == excludePos) {
			pos = numbers.getRandomNumber();
		}
		col = getColForIndex(pos + 1);
		row = getRowForIndex(pos + 1);
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
		setPos(row,col);
		return true;
	}
	
	public boolean moveDown() {
		if (row == 4)
		{
			return false;
		}
		row++;
		setPos(row,col);
		return true;
	}
	
	public boolean moveLeft()
	{
		if (col == 1)
		{
			return false;
		}
		col--;
		setPos(row,col);
		return true;
	}
	
	public boolean moveRight()
	{
		if (col == 4)
		{
			return false;
		}
		col++;
		setPos(row,col);
		return true;
	}
	
	private void setPos(int row, int col) {
		switch (row) {
		case 1:
			pos = col - 1;
			break;
		case 2:
			pos =  4 + col - 1;
			break;
		case 3:
			pos =  8 + col - 1;
			break;
		case 4:
			pos =  12 + col - 1;
			break;
		default:
			throw new IllegalStateException("Illegal switch!");
		}
	}
	
	private int getRowForIndex(int index) {
		if (index < 4) {
			return 1;
		} else if (index > 3 && index < 8) {
			return 2;
		} else if (index > 7 && index < 12) {
			return 3;
		} else if (index >= 12) {
			return 4;
		}
		throw new IllegalStateException("Index out of range!");
	}
	
	private int getColForIndex(int index) {
		if (index == 0 || index == 4 || index == 8 || index == 12) {
			return 1;
		} else if (index == 1 || index == 5 || index == 9 || index == 13) {
			return 2;
		} else if (index == 2 || index == 6 || index == 10 || index == 14) {
			return 3;
		} else if (index == 3 || index == 7 || index == 11 || index == 15) {
			return 4;
		}
		throw new IllegalStateException("Index out of range!");
	}
	
	private List<Integer> getNeighbours(int index) {

		List<Integer> list = new ArrayList<Integer>();
		int row = getRowForIndex(index);
		int col = getColForIndex(index);
		
		int curIndex;
		
		int left = col - 1;
		int right = col + 1;
		int up = row - 1;
		int down = row + 1;
		
		if (left > 0) {
			curIndex = numbers.getIndex(row, left);
			list.add(numbers.getNumber(curIndex));
		}
		if (right < 5) {
			curIndex = numbers.getIndex(row, right);
			list.add(numbers.getNumber(curIndex));
		}
		if (up > 0) {
			curIndex = numbers.getIndex(up, col);
			list.add(numbers.getNumber(curIndex));
		}
		if (down < 5) {
			curIndex = numbers.getIndex(down, col);
			list.add(numbers.getNumber(curIndex));
		}
		
		
		return list;
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
		
		int getNumber(int index) {
			return current.get(index);
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
		
		int getIndex(int row, int col) {
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
		
		int findIndex(int no) {
			return Collections.binarySearch(current, no);
		}
		
		@SuppressWarnings("unchecked")
		List<Integer> cloneNumbersList() {
			return (List<Integer>) ((ArrayList<Integer>)current).clone();
		}
		
	}
	
}
