/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

/**
 *
 * @author user
 */


public class SudokuSolver {

	public static void main(String[] args) {
	
            int marks[][]=
                {
                    {0,0,0,0},
                    {0,0,1,3},
                    {0,0,0,0},
                    {0,0,0,4}
                };
            
		Sudoku s = new Sudoku(marks); // you can also pass incomplete sudoku as a parameter
		s.displaySudoku();
		
		if(s.solveSudoku(0))
		{
			s.displaySudoku();
		}
		else
		{
			System.out.println("Unsuccessful");
		}

	}

}


class Sudoku
{
	private int[][] sudoku;
	private static final int UNASSIGNED = 0;
	
	public Sudoku()
	{
		sudoku = new int[4][4];
	}
	
	public Sudoku(int sudoku[][])
	{
		this.sudoku= sudoku;
	}
	
	public boolean solveSudoku(int step)
	{                        
            int row=0;            
            int col=0;
            int number=1;
            
            try
            {                
            	for(row=0;row<4;row++)
		{
			for(col=0;col<4;col++)
			{       
				if(sudoku[row][col]==UNASSIGNED)
				{
					for(number=1;number<=4;number++)
					{
                                            if(step == 3 && row == 0 && col == 3 && number ==3)
                                            {                                		
                                                System.out.println("degug\n");
                                            }
                                                                        
						if(isAllowed(row, col, number))
						{
							sudoku[row][col] = number;
							if(solveSudoku(++step))
							{
								return true;
							}
							else
							{
								sudoku[row][col] = UNASSIGNED;
							}
						}
					}
					return false;
				}
			}
		}
                
		return true;
                
            }catch(Exception ex)
            {
                throw ex;
            }
            
	}
        
	
	private boolean containsInRow(int row,int number)
	{
		for(int i=0;i<4;i++)
		{
			if(sudoku[row][i]==number)
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean containsInCol(int col,int number)
	{
		for(int i=0;i<4;i++)
		{
			if(sudoku[i][col]==number)
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean containsInBox(int row, int col,int number)
	{
		int r = row - row%2;
		int c = col - col%2;
		for(int i=r;i<r+2;i++)
		{
			for(int j=c;j<c+2;j++)
			{
				if(sudoku[i][j]==number)
				{
					return true;
				}
			}
			
		}
		return false;
	}
	
	private boolean isAllowed(int row, int col,int number)
	{
		return !(containsInRow(row, number) || containsInCol(col, number) || containsInBox(row, col, number));
	}
	
	public void displaySudoku()
	{
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<4;j++)
                {
                    System.out.print(" " + sudoku[i][j] + " ");
                }
                System.out.println();
            }
		
	}
	
}