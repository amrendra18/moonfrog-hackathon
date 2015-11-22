package in.titans.funwithdots.gameutils;

public class State {

    private boolean validMove;
    private int countBoxFormed;

    private int box1, box2;
    private Point startPointBox1;
    private Point endPointBox1;
    private Point startPointBox2;
    private Point endPointBox2;
    private boolean delete;

    public State() {
        box1 = -1;
        box2 = -1;
    }

    public State(boolean validMove, int countBoxFormed, int box1, int box2) {
        this.validMove = validMove;
        this.countBoxFormed = countBoxFormed;
        this.box1 = box1;
        this.box2 = box2;
    }

    public int getBox2() {
        return box2;
    }

    public void setBox2(int box2) {
        this.box2 = box2;
    }

    public int getBox1() {
        return box1;
    }

    public void setBox1(int box1) {
        this.box1 = box1;
    }

    public int getCountBoxFormed() {
        return countBoxFormed;
    }

    public void setCountBoxFormed(int countBoxFormed) {
        this.countBoxFormed = countBoxFormed;
    }

    public boolean getValidMove() {
        return validMove;
    }

    public void setValidMove(boolean validMove) {
        this.validMove = validMove;
    }

    public void setStartPointBox1(Point startPointBox1) {
        this.startPointBox1 = startPointBox1;
    }

    public void setEndPointBox1(Point endPointBox1) {
        this.endPointBox1 = endPointBox1;
    }

    public void setStartPointBox2(Point startPointBox2) {
        this.startPointBox2 = startPointBox2;
    }

    public void setEndPointBox2(Point endPointBox2) {
        this.endPointBox2 = endPointBox2;
    }

    public Point getStartPointBox1() {
        return startPointBox1;
    }

    public Point getEndPointBox1() {
        return endPointBox1;
    }

    public Point getStartPointBox2() {
        return startPointBox2;
    }

    public Point getEndPointBox2() {
        return endPointBox2;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean getDelete() {
        return delete;
    }

    @Override
    public String toString() {
        return "State{" +
                "validMove=" + validMove +
                ", countBoxFormed=" + countBoxFormed +
                ", box1=" + box1 +
                ", box2=" + box2 +
                ", startPointBox1=" + startPointBox1 +
                ", endPointBox1=" + endPointBox1 +
                ", startPointBox2=" + startPointBox2 +
                ", endPointBox2=" + endPointBox2 +
                ", delete=" + delete +
                '}';
    }
}
