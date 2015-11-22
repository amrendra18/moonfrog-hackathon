package in.titans.funwithdots.gameutils;

public class Grid {
	private int length;
	Record grid[];
	Grid(int length) {
		this.length = length;
		grid = new Record[length*length];
		int size = length*length;
		for(int count=0;count<size;count++) {
				int x = count/length;
				int y = count % length;
				grid[count] = new Record(count,new Point(x,y), new Point(x+1,y+1));

		}
		
	}
	
	public int getGridLength() {
		return length;
	}
	public State playTurn(Point stPoint, Point enPoint, boolean lastDeleted) { // line points

		boolean result = false;
		State state = new State();
        state.setDelete(false);

		int size = length * length;
		int box1 = stPoint.getStartBoxNum();
        int box2 = enPoint.getEndBoxNum();

		if (box1 >= 0 && box1 <= size - 1) {

			if (isHorizontal(stPoint, enPoint)) {
				if (!grid[box1].getBox().hasUp()) {
					grid[box1].getBox().markUp(true);
					result = true;
					grid[box1].lineInc();
					if(grid[box1].getLineCount() == 4) {
						state.setBox1(box1);
                        state.setStartPointBox1(grid[box1].getStPoint());
                        state.setEndPointBox1(grid[box1].getEndPoint());
					}
				} else {
                    if(lastDeleted ==false && grid[box1].getLineCount() < 4) {
                        if (box2 >= 0 && box2 <= size - 1 && grid[box2].getLineCount() < 4){
                            grid[box1].getBox().markUp(false);
                            grid[box1].lineDec();
                            grid[box2].getBox().markDown(false);
                            grid[box2].lineDec();
                            state.setDelete(true);
                        } else if (box2 >= 0 && box2 <= size - 1 && grid[box2].getLineCount() == 4) {

                        } else {
                            grid[box1].getBox().markUp(false);
                            grid[box1].lineDec();
                            state.setDelete(true);
                        }
                    }
                }
			} else {
                if (!grid[box1].getBox().hasLeft()) {
                    grid[box1].getBox().markLeft(true);
					result = true;
					grid[box1].lineInc();
					if(grid[box1].getLineCount() == 4) {
						state.setBox1(box1);
                        state.setStartPointBox1(grid[box1].getStPoint());
                        state.setEndPointBox1(grid[box1].getEndPoint());
					}
				} else {
                    if(lastDeleted ==false && grid[box1].getLineCount() < 4) {
                        if (box2 >= 0 && box2 <= size - 1 && grid[box2].getLineCount() < 4){
                            grid[box1].getBox().markLeft(false);
                            grid[box1].lineDec();
                            grid[box2].getBox().markRight(false);
                            grid[box2].lineDec();
                            state.setDelete(true);
                        } else if(box2 >= 0 && box2 <= size - 1 && grid[box2].getLineCount() == 4) {
                        } else {
                            grid[box1].getBox().markLeft(false);
                            grid[box1].lineDec();
                            state.setDelete(true);
                        }
                    }
                }
			}

		}

		if (state.getDelete() == false && box2 >= 0 && box2 <= size - 1) {

			if (isHorizontal(stPoint, enPoint)) {
				if (!grid[box2].getBox().hasDown()) {
					grid[box2].getBox().markDown(true);
					result = true;
					grid[box2].lineInc();
                    if (grid[box2].getLineCount() == 4) {
                        state.setBox2(box2);
                        state.setStartPointBox2(grid[box2].getStPoint());
                        state.setEndPointBox2(grid[box2].getEndPoint());
                    }
				} else {
                    if(lastDeleted ==false && grid[box2].getLineCount() < 4) {
                        if (box1 >= 0 && box1 <= size - 1) {
                        } else {
                            grid[box2].getBox().markDown(false);
                            grid[box2].lineDec();
                            state.setDelete(true);
                        }
                    }
                }
			} else {
				if (!grid[box2].getBox().hasRight()) {
					grid[box2].getBox().markRight(true);
					result = true;
					grid[box2].lineInc();
                    if (grid[box2].getLineCount() == 4) {
                        state.setBox2(box2);
                        state.setStartPointBox2(grid[box2].getStPoint());
                        state.setEndPointBox2(grid[box2].getEndPoint());
                    }
				} else {
                    if(lastDeleted ==false && grid[box2].getLineCount() < 4) {
                        if (box1 >= 0 && box1 <= size - 1) {
                        } else {
                            grid[box2].getBox().markRight(false);
                            grid[box2].lineDec();
                            state.setDelete(true);
                        }
                    }
                }
			}
		}
		state.setValidMove(result);

		return state;
	}
	
	public boolean isHorizontal(Point stPoint, Point enPoint) {
		
		boolean result =false;
		
		if(stPoint.getX() == enPoint.getX())
		{
			result = true;
		}
		return result;
	}
	
	public boolean isLeft(Point stPoint, Point enPoint,Record record) {
		boolean result = false;
		
		
		
		return result;
	}
	
	public void showRecords() {
		int size = length*length;
		for(int count=0; count<size;count++) {
			System.out.println(grid[count].toString());
		}
	}
}
