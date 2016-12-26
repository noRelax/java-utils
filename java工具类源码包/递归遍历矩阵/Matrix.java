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
	private boolean visited;//�Ƿ񱻷��ʣ�ȱʡΪfalse
	
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
		if (visited) {//�ж��Ƿ񱻷��ʹ�
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
		visited = true;//���ʹ����߱�ʶΪtrue
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
				//����һ�и��Ӵ���ʱ�� ������һ�и��ӵ�bottomCell
				if (i > 0) {
					cells[i-1][j].setBottomCell(cells[i][j]); 
				}
				//��ǰһ�и��Ӵ���ʱ�� ����ǰһ�и��ӵ�rightCell
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
		firstCell.visit(); //�ӵ�һ�����ӿ�ʼ�ݹ�
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
