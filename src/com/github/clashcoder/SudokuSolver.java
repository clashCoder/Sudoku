package com.github.clashcoder;

import java.util.HashMap;

public class SudokuSolver {
	
	private final String UNASSIGNED_ROW_INDEX = "com.github.clashcoder.unassigned_row_index";
	private final String UNASSIGNED_COL_INDEX = "com.github.clashcoder.unassigned_col_index";
	private final String NUM_ROWS = "com.github.clashcoder.num_rows";
	private final String NUM_COLS = "com.github.clashcoder.num_cols";
	private final String NUM_GRID_ROWS = "com.github.clashcoder.num_grid_rows";
	private final String NUM_GRID_COLS = "com.github.clashcoder.num_grid_cols";
	private final int UNASSIGNED = 0;
	
	private HashMap<String, Integer> sudokuProperties = new HashMap<>();
	
	private int[][] solutionPuzzle;
	
	
	SudokuSolver(int[][] sudokuPuzzle, int numCol, int numRows) {
		
		if (initializeSudokuProperties(numCol, numRows)) {
			
			solutionPuzzle = new int[numRows][numCol];
			
			for (int row = 0; row < numRows; row++) {
				for (int col = 0; col < numCol; col++) {
					solutionPuzzle[row][col] = sudokuPuzzle[row][col];
				}
			}
		} else {
			solutionPuzzle = null;
			sudokuProperties.put(UNASSIGNED_ROW_INDEX, 0);
			sudokuProperties.put(UNASSIGNED_COL_INDEX, 0);
			sudokuProperties.put(NUM_ROWS, 0);
			sudokuProperties.put(NUM_COLS, 0);
			sudokuProperties.put(NUM_GRID_ROWS, 0);
			sudokuProperties.put(NUM_GRID_COLS, 0);
		}
	}
	
	/**
	 * Initializes the properties of the Sudoku Puzzle such as number of Rows and Columns
	 * 
	 * @param numCol		Number of columns present in Sudoku puzzle.
	 * @param numRows	Number of rows present in Sudoku puzzle.
	 * @return			True on proper initialization; False otherwise.
	 */
	private boolean initializeSudokuProperties(int numCol, int numRows) {
		if ( (numCol != numRows) || (!isPerfectSquare(numCol)) )
			return false;
		
		sudokuProperties.put(UNASSIGNED_ROW_INDEX, 0);
		sudokuProperties.put(UNASSIGNED_COL_INDEX, 0);
		sudokuProperties.put(NUM_ROWS, numRows);
		sudokuProperties.put(NUM_COLS, numCol);
		sudokuProperties.put(NUM_GRID_ROWS, (int) Math.sqrt(numRows));
		sudokuProperties.put(NUM_GRID_COLS, (int) Math.sqrt(numCol));
		
		return true;
	}
	
	/**
	 * Determines whether a specific value is in the given row.
	 * 
	 * @param row		Row index to be searched
	 * @param value		Value to look for in given row
	 * @return			True if value found in row; False otherwise.
	 */
	private boolean foundInRow(int row, int value) {
		int numCols = sudokuProperties.get(NUM_COLS);
		for (int col = 0; col < numCols; col++) {
			if (solutionPuzzle[row][col] == value)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Determines whether a specific value is in the given column.
	 * 
	 * @param col		Column index to be searched
	 * @param value		Value to look for in given column
	 * @return			True if value found in column; False otherwise
	 */
	private boolean foundInCol(int col, int value) {
		int numRows = sudokuProperties.get(NUM_ROWS);
		for (int row = 0; row < numRows; row++) {
			if (solutionPuzzle[row][col] == value)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Determines whether a specific value is within a specific grid.
	 * 
	 * @param startRow	Starting row of the grid.
	 * @param startCol	Starting column of the grid.
	 * @param value		True if value is within grid; False otherwise.
	 * @return
	 */
	private boolean foundInGrid(int startRow, int startCol, int value) {
		int numRows = sudokuProperties.get(NUM_GRID_ROWS);
		int numCols = sudokuProperties.get(NUM_GRID_COLS);
		
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (solutionPuzzle[row + startRow][col + startCol] == value)
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Determines if placing a specific value at the specified location
	 * does not violate any Sudoku rules.
	 * 
	 * @param row		Row index to be searched
	 * @param col		Column index to be searched
	 * @param value		Value to be checked for valid placement.
	 * @return			True for a valid placement; False otherwise.
	 *
	 */
	private boolean isLegalPlacement(int row, int col, int value) {
		
		int gridRows = sudokuProperties.get(NUM_GRID_ROWS);
		int gridCols = sudokuProperties.get(NUM_GRID_COLS);
		
		if (!foundInCol(col, value) && 
				!foundInRow(row, value) &&
				!foundInGrid(row -(row % gridRows), col - (col % gridCols), value)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Determines whether there is a slot in the sudoku puzzle that
	 * does not yet have a proper value.
	 * 
	 * @return		True if there is an unassigned slot; False otherwise.
	 */
	private boolean foundUnassignedSpot() {
		int numRows = sudokuProperties.get(NUM_ROWS);
		int numCols = sudokuProperties.get(NUM_COLS);
		
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (solutionPuzzle[row][col] == UNASSIGNED) {
					sudokuProperties.put(UNASSIGNED_ROW_INDEX, row);
					sudokuProperties.put(UNASSIGNED_COL_INDEX, col);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean isPerfectSquare(int value) {
		double sqrt = Math.sqrt(value);
		int intOfSqrt = (int) sqrt;
		
		if (Math.pow(sqrt, 2) == Math.pow(intOfSqrt, 2)) 
			return true;
		
		return false;
	}
	
	/**
	 * Solves the Sudoku puzzle.
	 * 
	 * @return		The solution to the Sudoku puzzle
	 */
	public boolean solvedSudoku() {
		
		int row;
		int col;
		
		boolean hasOpenSlot = foundUnassignedSpot();
		
		if (!hasOpenSlot) {
			return true;
		}
		
		row = sudokuProperties.get(UNASSIGNED_ROW_INDEX);
		col = sudokuProperties.get(UNASSIGNED_COL_INDEX);
		
		int numRows = sudokuProperties.get(NUM_ROWS);
		
		for (int digit = 1; digit <= numRows; digit++) {
			
			if (isLegalPlacement(row, col, digit)) {
				solutionPuzzle[row][col] = digit;
				
				if (solvedSudoku()) {
					return true;
				}
				
				solutionPuzzle[row][col] = 0;
			}
		}
		
		return false;
	}
	
	public int[][] getSudokuPuzzle() {
		return solutionPuzzle;
	}
	
	public void setItem() {
		solutionPuzzle[0][0] = -1;
	}
	
}
