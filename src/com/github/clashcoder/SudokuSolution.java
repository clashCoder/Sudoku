package com.github.clashcoder;

import org.junit.Assert;
import org.junit.Test;

public class SudokuSolution {

	public static void main(String[] args) {
		
		int grid[][] = {
				{3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
                };
		
		int grid2[][] = {
				{4, 8, 0, 0, 7, 0, 0, 0, 0},
                {0, 1, 6, 0, 0, 5, 9, 0, 0},
                {7, 0, 0, 0, 0, 2, 0, 0, 0},
                {0, 0, 5, 6, 0, 0, 8, 4, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 8, 0, 0, 1, 2, 0, 0},
                {0, 0, 0, 7, 0, 0, 0, 0, 9},
                {0, 0, 2, 9, 0, 0, 1, 8, 0},
                {0, 0, 0, 0, 5, 0, 0, 6, 3}
                };
		
		SudokuSolver ss = new SudokuSolver(grid2, 9, 9);
		SudokuSolver ss2 = new SudokuSolver(grid, 9, 9);
		
		
//		Assert.assertTrue(ss.foundInRow(0, 5));
//		Assert.assertFalse(ss.foundInRow(2, 9));
//		Assert.assertTrue(ss.foundInCol(0, 5));
//		Assert.assertFalse(ss.foundInCol(7, 4));
//		Assert.assertTrue(ss.foundInGrid(3, 4, 1));
//		Assert.assertFalse(ss.foundInGrid(6, 6, 1));
//		Assert.assertTrue(ss.isLegalPlacement(8, 7, 1));
//		Assert.assertFalse(ss.isLegalPlacement(8, 7, 4));
//		Assert.assertTrue(ss.foundUnassignedSpot());
		
		ss.solvedSudoku();
		ss2.solvedSudoku();
		
		int solution2[][] = ss.getSudokuPuzzle();
		int solution [][] = ss2.getSudokuPuzzle();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(solution2[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

}
