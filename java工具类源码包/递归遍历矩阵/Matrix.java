package test;

class Line {
}

class Cell {
	private int x;
	private int y;
	private Line topLine;
	private Line bottomLine;
	private Line leftLine;
	private Line rightLine;
	private Cell rightCell;//
	private Cell bottomCell;
	private boolean visited;//是否被访问，缺省为false
	
	public Cell(int y, int x) {
		this.x = x;
		this.y = y;
		if (x == 0) {
			leftLine = new Line();
		};
		if (y == 0) {
			topLine = new Line();
		};
		rightLine = new Line();
		bottomLine = new Line();
	}
	
	public void visit() {
		if (visited) {//判断是否被访问过
			return;
		}
		String strTemp = String.format("Cell[%d][%d] ", y+1, x+1);
		
		if (leftLine != null) {
			strTemp += " Left"; 
		}
		if (topLine != null) {
			strTemp += " Top";
		}
		if (rightLine != null) {
			strTemp += " Right";
		}
		if (bottomLine != null) {
			strTemp += " Bottom";
		}
		System.out.println(strTemp);
		visited = true;//访问过的线标识为true
	}
	
	public Cell getRightCell() {
		return rightCell;
	}
	public void setRightCell(Cell rightCell) {
		this.rightCell = rightCell;
	}
	public Cell getBottomCell() {
		return bottomCell;
	}
	public void setBottomCell(Cell bottomCell) {
		this.bottomCell = bottomCell;
	}
	
}

class Matrix {
	private Cell cells[][];
	public Matrix(int m, int n) {
		cells = new Cell[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				cells[i][j] = new Cell(i, j);
				//当下一行格子存在时， 设置上一行格子的bottomCell
				if (i > 0) {
					cells[i-1][j].setBottomCell(cells[i][j]); 
				}
				//当前一列格子存在时， 设置前一列格子的rightCell
				if (j > 0) {
					cells[i][j-1].setRightCell(cells[i][j]);
				}
			}
		}
	}
	public void visit() {
		visit(cells[0][0]);
	}
	private void visit(Cell firstCell){
		firstCell.visit(); //从第一个格子开始递归
		if (firstCell.getBottomCell() != null) {
			visit(firstCell.getBottomCell());
		}
		if (firstCell.getRightCell() != null) {
			visit(firstCell.getRightCell());
		}
	}
	public static void main(String[] Args) {
		Matrix matrix = new Matrix(3, 4);
		matrix.visit();
	}
}
