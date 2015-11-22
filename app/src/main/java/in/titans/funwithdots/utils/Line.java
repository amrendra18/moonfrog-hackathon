package in.titans.funwithdots.utils;

public class Line {

    PointPx start;
    PointPx end;

    public Line() {
    }

    public Line(PointPx start, PointPx end) {
        this.start = start;
        this.end = end;
    }

    public PointPx getStart() {
        return start;
    }

    public void setStart(PointPx start) {
        this.start = start;
    }

    public PointPx getEnd() {
        return end;
    }

    public void setEnd(PointPx end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
