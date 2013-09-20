package test.anapp.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class World {
	
	private final Numbers numbers = new Numbers();
	private final List<Integer> goalWorld = new ArrayList<Integer>();
	
	private final List<Integer> currentWorld = new ArrayList<Integer>();

	private int selectedNo = 0;
	private int startPosOfSelectedNo = 0;
	
	public World() {
		
	}
	
	private void setUp() {
		numbers.scramble();
		goalWorld.clear();
		goalWorld.addAll(numbers.cloneNumbersList());
		selectedNo = getRandomNumber();
		startPosOfSelectedNo = findGoalIndex(selectedNo);
	}
	
	private int findGoalIndex(int no) {
		return Collections.binarySearch(goalWorld, no);
	}
	
	private int findCurrentIndex(int no) {
		return Collections.binarySearch(currentWorld, no);
	}
	
	private int getRandomNumber() {
		return (int )(Math.random() * 16 + 1);
	}
	
	private void swap(int index1, int index2) {
		int val1 = currentWorld.get(index1);
		int val2 = currentWorld.get(index2);
		currentWorld.set(index1, val2);
		currentWorld.set(index2, val1);
	}
	
	private class Numbers {
		private final short[] INITIAL = new short[]{3,1,2,4,6,7,5,8,9,11,10,14,13,12,15,16};
		private List<Integer> current = new ArrayList<Integer>();
		
		private Numbers() {
			for (short no : INITIAL) {
				current.add((int) no);
			}
		}
		
		private void scramble() {
			 Collections.shuffle(current);
		}
		
		private int getNumber(int index) {
			return current.get(index);
		}
		
		@SuppressWarnings("unchecked")
		private List<Integer> cloneNumbersList() {
			return (List<Integer>) ((ArrayList<Integer>)current).clone();
		}
	}
}
