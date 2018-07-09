package com.github.clashcoder;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class SudokuSolverTest {
	
	private int gridOne[][] = {
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
	
	private int gridTwo[][] = {
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
	
	private int gridThree[][] = {
			{3,4,5},
			{2,3}
	};
	
	private int gridOneSol[][] = {
			{3, 1, 6, 5, 7, 8, 4, 9, 2},
			{5, 2, 9, 1, 3, 4, 7, 6, 8},
			{4, 8, 7, 6, 2, 9, 5, 3, 1},
			{2, 6, 3, 4, 1, 5, 9, 8, 7},
			{9, 7, 4, 8, 6, 3, 1, 2, 5},
			{8, 5, 1, 7, 9, 2, 6, 4, 3},
			{1, 3, 8, 9, 4, 7, 2, 5, 6},
			{6, 9, 2, 3, 5, 1, 8, 7, 4},
			{7, 4, 5, 2, 8, 6, 3, 1, 9}
	};
	
	private int gridTwoSol[][] = {
			{4, 8, 9, 1, 7, 6, 3, 5, 2},
			{2, 1, 6, 3, 4, 5, 9, 7, 8},
			{7, 5, 3, 8, 9, 2, 4, 1, 6},
			{9, 3, 5, 6, 2, 7, 8, 4, 1},
			{1, 2, 7, 4, 8, 9, 6, 3, 5},
			{6, 4, 8, 5, 3, 1, 2, 9, 7},
			{3, 6, 4, 7, 1, 8, 5, 2, 9},
			{5, 7, 2, 9, 6, 3, 1, 8, 4},
			{8, 9, 1, 2, 5, 4, 7, 6, 3}
	};
	
	private SudokuSolver solverOne;
	private SudokuSolver solverTwo;
	private SudokuSolver solverThree;
	
	@Before
	public void setup() {
		solverOne = new SudokuSolver(gridOne, gridOne[0].length, gridOne.length);
		solverTwo = new SudokuSolver(gridTwo, gridTwo[0].length, gridTwo.length);
		solverThree = new SudokuSolver(gridThree, gridThree[0].length, gridThree.length);
	}

	@Test
	public void testIsPerfectSquare() {
		Assert.assertTrue(solverOne.isPerfectSquare(1));
		Assert.assertTrue(solverOne.isPerfectSquare(4));
		Assert.assertTrue(solverOne.isPerfectSquare(9));
		Assert.assertFalse(solverOne.isPerfectSquare(15));
		Assert.assertFalse(solverOne.isPerfectSquare(2));
	}

	@Test
	public void testSolvedSudoku() {
		assertTrue(solverOne.solvedSudoku());
		assertTrue(solverTwo.solvedSudoku());
		assertTrue(solverThree.solvedSudoku());
		assertArrayEquals(solverThree.getSudokuPuzzle(), null);
		
		assertArrayEquals(solverOne.getSudokuPuzzle(), gridOneSol);
		assertArrayEquals(solverTwo.getSudokuPuzzle(), gridTwoSol);
	}

}
