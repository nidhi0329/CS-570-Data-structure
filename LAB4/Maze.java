package Maze;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * 
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

	/** The maze */
	private TwoDimGrid maze;

	public Maze(TwoDimGrid m) {
		maze = m;
	}

	/** Wrapper method. */
	public boolean findMazePath() {

		return findMazePath(0, 0); // (0, 0) is the start point.
	}

	/**
	 * Attempts to find a path through point (x, y).
	 * 
	 * @pre Possible path cells are in BACKGROUND color; barrier cells are in
	 *      ABNORMAL color.
	 * @post If a path is found, all cells on it are set to the PATH color; all
	 *       cells that were visited but are not on the path are in the TEMPORARY
	 *       color.
	 * @param x The x-coordinate of current point
	 * @param y The y-coordinate of current point
	 * @return If a path through (x, y) is found, true; otherwise, false
	 */
	public boolean findMazePath(int x, int y) {
		// COMPLETE HERE FOR PROBLEM 1
		if ((x >= 0 && x < maze.getNCols()) && (y >= 0 && y < maze.getNRows())
				&& maze.getColor(x, y) == NON_BACKGROUND) {
			maze.recolor(x, y, PATH);
			if ((x == (maze.getNCols() - 1) && y == (maze.getNRows() - 1)) || findMazePath(x - 1, y)
					|| findMazePath(x, y + 1) || findMazePath(x + 1, y) || findMazePath(x, y - 1)) {
				return true;
			} else {
				maze.recolor(x, y, TEMPORARY);
				return false;
			}
		} else {
			return false;
		}
	}

	// ADD METHOD FOR PROBLEM 2 HERE
	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
		if (y < maze.getNRows() && y >= 0 && x < maze.getNCols() && x >= 0
				&& (maze.getColor(x, y) != BACKGROUND && maze.getColor(x, y) != TEMPORARY)) {
			if (y == maze.getNRows() - 1 && x == maze.getNCols() - 1) {
				PairInt hold1 = new PairInt(x, y);
				trace.push(hold1);
				ArrayList<PairInt> hold = new ArrayList<PairInt>();
				hold.addAll(trace);
				result.add(hold);
				trace.pop();
				maze.recolor(x, y, NON_BACKGROUND);
				return;
			} else {
				PairInt hold1 = new PairInt(x, y);
				trace.push(hold1);
				maze.recolor(x, y, TEMPORARY);
				this.findMazePathStackBased(x + 1, y, result, trace);
				this.findMazePathStackBased(x - 1, y, result, trace);
				this.findMazePathStackBased(x, y + 1, result, trace);
				this.findMazePathStackBased(x, y - 1, result, trace);
				maze.recolor(x, y, NON_BACKGROUND);
				trace.pop();
				return;
			}
		} else {
			return;
		}

	}

	public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
		ArrayList<ArrayList<PairInt>> output = new ArrayList<>();
		Stack<PairInt> trace = new Stack<>();
		findMazePathStackBased(0, 0, output, trace);
		if (output.size() == 0) {
			ArrayList<PairInt> temporary = new ArrayList<PairInt>();
			output.add(temporary);
		}
		return output;
	}

	// ADD METHOD FOR PROBLEM 3 HERE
	public ArrayList<PairInt> findMazePathMin(int x, int y) {
		ArrayList<ArrayList<PairInt>> output = new ArrayList<ArrayList<PairInt>>();
		output = findAllMazePaths(x, y);
		int shortPath = output.get(0).size();
		int indexOfShortRoute = 0;
		for (int i = 0; i < output.size(); i++) {
			if (output.get(i).size() <= shortPath) {
				shortPath = output.get(i).size();
				indexOfShortRoute = i;
			}
		}
		return output.get(indexOfShortRoute);
	}

	/* <exercise chapter="5" section="6" type="programming" number="2"> */
	public void resetTemp() {
		maze.recolor(TEMPORARY, BACKGROUND);
	}
	/* </exercise> */

	/* <exercise chapter="5" section="6" type="programming" number="3"> */
	public void restore() {
		resetTemp();
		maze.recolor(PATH, BACKGROUND);
		maze.recolor(NON_BACKGROUND, BACKGROUND);
	}
	/* </exercise> */
}
/* </listing> */