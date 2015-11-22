package in.titans.funwithdots;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import in.titans.funwithdots.utils.Debug;
import in.titans.funwithdots.utils.GameConstants;
import in.titans.funwithdots.utils.Line;
import in.titans.funwithdots.utils.PointPx;

public class DotBoard extends View {

    private Bitmap bitmap = null;
    private Canvas canvas = null;
    private Paint whitePaint = null;
    Paint dotLinePaint = null;
    private ArrayList<Paint> rectPaintList = null;
    int canvasHeight = 1000, canvasWidth = 1000;
    private GameActivity activity;
    private int rows;
    private int cols;

    public void setActivity(GameActivity activity) {
        this.activity = activity;
    }

    public DotBoard(Context context) {
        this(context, null);
    }

    public DotBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        Debug.check();

        Debug.check();
        bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.translate(GameConstants.translate, GameConstants.translate);
        whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        whitePaint.setStrokeWidth(2);
        dotLinePaint = new Paint();
        dotLinePaint.setColor(Color.BLACK);
        dotLinePaint.setStrokeWidth(2);
        Paint paintPlayer1 = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paintPlayer1.setColor(Color.RED);
        paintPlayer1.setStyle(Paint.Style.FILL);
        Paint paintPlayer2 = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paintPlayer2.setColor(Color.BLUE);
        paintPlayer2.setStyle(Paint.Style.FILL);
        rectPaintList = new ArrayList<Paint>();
        rectPaintList.add(paintPlayer1);
        rectPaintList.add(paintPlayer2);

    }

    public DotBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Debug.check();
    }


    @Override
    protected void onDraw(Canvas viewCanvas) {
        super.onDraw(viewCanvas);
        //bitmap.eraseColor(Color.TRANSPARENT);
        Debug.check();
//        debugCanvas(viewCanvas, Color.RED, new RectF(viewCanvas.getClipBounds()));


        //viewCanvas.setBitmap(bitmap);
        //bitmap.eraseColor(Color.TRANSPARENT);
        viewCanvas.drawBitmap(bitmap, 0, 0, null);
        Debug.i("length: " + viewCanvas.getHeight() + " width: " + viewCanvas.getWidth(), false);
        canvasWidth = viewCanvas.getWidth();
        canvasHeight = viewCanvas.getHeight();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @SuppressWarnings("unused")
    private void debugCanvas(Canvas canvas, int color, RectF rect) {
        Paint debugCanvas = new Paint();
        debugCanvas.setColor(color);
        debugCanvas.setStyle(Paint.Style.STROKE);
        // Debug.i("canvas : "+canvas+" color : "+color+" rect : "+rect);
        canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, debugCanvas);
    }


    public void drawLine(PointPx start, PointPx end) {
        Debug.check();
        if(start.getX() == end.getX()) {
            canvas.drawLine(start.getX(), start.getY() + 5, end.getX(), end.getY() - 5, dotLinePaint);
        } else {
            canvas.drawLine(start.getX() + 5, start.getY(), end.getX() - 5, end.getY(), dotLinePaint);
        }
        this.invalidate();
    }

    public void drawRec(PointPx start, PointPx end, int player) {
        Debug.check();
        Debug.i("Start:" + start + " End:" + end, true);
        canvas.drawRoundRect(start.getX() + 2, start.getY() + 2, end.getX() - 2, end.getY() - 2, 5, 5, rectPaintList.get(player));
        this.invalidate();
    }


    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        Debug.check();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            PointPx p = new PointPx(event.getX()-GameConstants.translate, event.getY()-GameConstants.translate);
            Debug.i(p.toString(),false);
            Line line = getLine(GameConstants.lineWidth, p);
            Debug.i("" + line, false);
            if(!(line.getEnd().getX() > GameConstants.lineWidth*cols+GameConstants.translate || line.getEnd().getY() > GameConstants.lineWidth*rows+GameConstants.translate)) {
                drawLine(line.getStart(), line.getEnd());
                activity.move(line.getStart(), line.getEnd());
            }
        }
        return true;
    }

    public Line getLine(float lineLength, PointPx point) {
        PointPx start = new PointPx();
        PointPx end = new PointPx();
        float x = point.getX()%lineLength;
        float y = point.getY()%lineLength;
        Debug.i("x: "+x+" y:"+y,false);
        float maxDistace = 1.0f * lineLength/((float)Math.sqrt(2));
        float distance = (float)Math.sqrt(x*x+y*y);
        float distance2 = (float)Math.sqrt((lineLength-x)*(lineLength-x)+y*y);
        Debug.i("maxDistace: " + maxDistace + " distance:" + distance + " distance2:" + distance2, false);
        if(distance < maxDistace) {
            start.setX(point.getX()-x);
            start.setY(point.getY()-y);
            if(distance2 < maxDistace) {
                end.setX(point.getX()-x+lineLength);
                end.setY(point.getY()-y);
            } else {
                end.setX(point.getX()-x);
                end.setY(point.getY()-y+lineLength);
            }
        } else {
            end.setX(point.getX() - x + lineLength);
            end.setY(point.getY() - y + lineLength);
            if(distance2 < maxDistace) {
                start.setX(point.getX()-x+lineLength);
                start.setY(point.getY()-y);
            } else {
                start.setX(point.getX() - x);
                start.setY(point.getY() - y + lineLength);
            }
        }
        Line line = new Line(start, end);
        return line;
    }

    void drawBackgroudLines(int row, int col) {
        //draw horizontal lines
        float disCols = 1.0f * canvasWidth / col;
        float rowCols = 1.0f * canvasHeight / row;
        //Debug.i("");
        for (int i = 0; i <= row; i++) {
            drawLine(new PointPx(0, i * rowCols), new PointPx(canvasWidth, i * rowCols));
        }
        //draw vertical lines
        for (int i = 0; i <= col; i++) {
            drawLine(new PointPx(i * disCols, 0), new PointPx(i * disCols, canvasHeight));
        }
    }

    public void drawDot(PointPx point) {
        canvas.drawCircle(point.getX(), point.getY(), 3, dotLinePaint);
        this.invalidate();
    }

    public void drawAllDots(int row, int col) {
        this.rows = row;
        this.cols = col;
        for(int i = 0; i <= col; i++) {
            for(int j = 0; j <= row; j++) {
                drawDot(new PointPx(i*GameConstants.lineWidth, j*GameConstants.lineWidth));
            }
        }
    }

    public void drawTransparentLine(PointPx start, PointPx end) {
        if(start.getX() == end.getX()) {
            canvas.drawLine(start.getX(), start.getY() + 5, end.getX(), end.getY() - 5, whitePaint);
        } else {
            canvas.drawLine(start.getX() + 5, start.getY(), end.getX() - 5, end.getY(), whitePaint);
        }
        this.invalidate();
    }
}
