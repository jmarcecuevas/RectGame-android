package com.luckycode.mysupergame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by marcelocuevas on 3/8/18.
 */

public class Obstacle implements GameObject{
    private Rect rect;
    private Rect rect2;
    private int color;

    public Obstacle(int rectHeight,int color,int startX,int startY,int playerGap){
        this.color = color;
        this.rect = new Rect(0,startY,startX,startY+rectHeight);
        this.rect2 = new Rect(startX+playerGap,startY,Constants.SCREEN_WIDTH,startY+rectHeight);
    }

    public boolean playerCollide(RectPlayer player){
        return Rect.intersects(rect,player.getRect()) || Rect.intersects(rect2,player.getRect());
    }

    public void incrementY(float y){
        rect.top += y;
        rect.bottom += y;
        rect2.top += y;
        rect2.bottom += y;
    }

    public Rect getRect() {
        return rect;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect,paint);
        canvas.drawRect(rect2,paint);
    }

    @Override
    public void update() {

    }
}