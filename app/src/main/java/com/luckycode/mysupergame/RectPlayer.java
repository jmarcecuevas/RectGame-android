package com.luckycode.mysupergame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by marcelocuevas on 3/8/18.
 */

public class RectPlayer implements GameObject {
    private Rect rect;
    private int color;

    public RectPlayer(Rect rect,int color){
        this.rect = rect;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect,paint);
    }

    @Override
    public void update() {

    }

    public void update(Point point){
        rect.set(point.x - rect.width()/2 , point.y - rect.height()/2 , point.x + rect.width()/2,
                point.y + rect.height()/2);
    }

    public Rect getRect() {
        return rect;
    }
}
