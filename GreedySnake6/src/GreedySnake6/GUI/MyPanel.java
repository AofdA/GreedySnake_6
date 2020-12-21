package GreedySnake6.GUI;
import GreedySnake6.Main;
import GreedySnake6.support.Get;

import java.awt.*;

//绘制 地图 蛇 食物
public class MyPanel extends Panel {
    //步数
    private Integer Step = 0;
    //分数
    private Integer Point = 0;
    //食物的坐标
    private int foodX = 5;
    //食物的Y坐标X
    private int foodY = 5;
    //是否需要重新获取foodX/Y
    private boolean b1 = false;
    {
        //设置背景颜色为黑色
        this.setBackground(Color.BLACK);
    }
    @Override
    public void paint(Graphics g) {
        //打印地图
        paintMap(g);
        //打印蛇
        paintSnake(g);
        //打印食物
        paintFood(g);
        //打印分数
        paintPoint(g);
        //打印步数
        paintStep(g);
    }

    public void paintMap(Graphics g){
        //设置画笔的颜色
        g.setColor(Color.GRAY);
        //打印棋盘的线
        for (int k = 0; k < 20; k++) {
            for (int i = 0; i < 20; i++) {
                g.drawRect(i * 20, k * 20, 20, 20);
            }
        }
    }
    public void paintSnake(Graphics g){
        //设置画笔的颜色
        g.setColor(Color.WHITE);
        for(int i = 0; i< Main.getSnake().list.size(); i++){
            if(i == 0){
                //打印头
                g.fillRect((Main.getSnake().list.get(i).getX() * 20) + 3,
                        (Main.getSnake().list.get(i).getY() * 20) + 3, 14, 14);
            }
            //打印身体
            g.fillRect((Main.getSnake().list.get(i).getX() * 20) + 4,
                    (Main.getSnake().list.get(i).getY() * 20) + 4, 12, 12);
        }
    }
    public void paintFood(Graphics g){
        //设置画笔的颜色
        g.setColor(Color.RED);
        //是否需要生成食物坐标
        if(b1){
            Get.getFoodXY();
            setB1(false);
        }
        g.fillRect((foodX * 20) + 5, (foodY * 20) + 5, 10, 10);
    }
    //打印步数
    public void paintStep(Graphics g){
        g.setColor(Color.PINK);
        g.drawString("步数",430,15);
        g.drawString(getStepStr(),435,35);
        g.drawLine(400,45,485,45);
    }
    //打印分数
    public void paintPoint(Graphics g){
        g.setColor(Color.PINK);
        g.drawString("分数",430,65);
        g.drawString(getPointStr(),435,85);
        g.drawLine(400,95,485,95);
    }


    //@getter setter
    //增加分数 +1
    public void addStep(){
        Step++;
    }
    //获取字串符的Step
    public String getStepStr(){
        return Step.toString();
    }
    //增加分数 +1
    public void addPoint(){
        Point++;
    }
    //获取字串符的Point
    public String getPointStr(){
        return Point.toString();
    }

    public Integer getStep() {
        return Step;
    }

    public void setStep(Integer step) {
        Step = step;
    }

    public Integer getPoint() {
        return Point;
    }

    public void setPoint(Integer point) {
        Point = point;
    }

    public int getFoodX() {
        return foodX;
    }

    public void setFoodX(int foodX) {
        this.foodX = foodX;
    }

    public int getFoodY() {
        return foodY;
    }

    public void setFoodY(int foodY) {
        this.foodY = foodY;
    }

    public boolean isB1() {
        return b1;
    }

    public void setB1(boolean b1) {
        this.b1 = b1;
    }
}
