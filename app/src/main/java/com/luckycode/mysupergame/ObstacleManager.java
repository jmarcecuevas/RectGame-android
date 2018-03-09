package com.luckycode.mysupergame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelocuevas on 3/8/18.
 */

public class ObstacleManager {
    private List<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;
    private long startTime,initTime;
    private int score;

    public ObstacleManager(int playerGap,int obstacleGap,int obstacleHeight,int color){
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;
        this.color = color;
        startTime = initTime = System.currentTimeMillis();
        obstacles = new ArrayList<>();
        populateObstacles();
    }

    private void populateObstacles() {
        int currentY = -5 * Constants.SCREEN_HEIGHT/4;
        while(currentY < 0){
            int xStart = (int) (Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(new Obstacle(obstacleHeight,color,xStart,currentY,playerGap));
            currentY += obstacleHeight + obstacleGap;
        }
    }

    public boolean playerCollide(RectPlayer player){
        for(Obstacle ob:obstacles){
            if(ob.playerCollide(player))
                return true;
        }
        return false;
    }

    public void update(){
        int elapsedTime = (int)(System.currentTimeMillis()-startTime);
        startTime = System.currentTimeMillis();
        float speed = (float)(Math.sqrt(1 + (startTime - initTime)/1000.0))*Constants.SCREEN_HEIGHT/(10000.0f);
        for(Obstacle ob:obstacles){
            ob.incrementY(speed * elapsedTime);
        }
        if(obstacles.get(obstacles.size()-1).getRect().top >= Constants.SCREEN_HEIGHT){
            int xStart = (int)(Math.random() * (Constants.SCREEN_WIDTH-playerGap));
            obstacles.add(0,new Obstacle(obstacleHeight,color,xStart,obstacles.get(0).getRect().top
                -obstacleHeight-obstacleGap,playerGap));
            obstacles.remove(obstacles.size()-1);
            score++;
        }
    }

    public void draw(Canvas canvas){
        for(Obstacle ob:obstacles){
            ob.draw(canvas);
        }
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("" + score,50,50 + paint.descent() - paint.ascent(),paint);
    }
}
