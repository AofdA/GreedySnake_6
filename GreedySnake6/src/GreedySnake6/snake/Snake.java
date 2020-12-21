package GreedySnake6.snake;


import GreedySnake6.Main;
import GreedySnake6.support.Direction;
import GreedySnake6.support.LogicException;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Snake {
    //用来存储蛇身 索引为0的是头
    public ArrayList<Node> list = new ArrayList<>();
    //计算动了几次 是否直
    int count = 0;
    //第一次为true
    boolean b2 = true;
    //初始化四个蛇身
    {
        list.add(new Node(3, 0, Direction.RIGHT));
        list.add(new Node(2, 0, Direction.RIGHT));
        list.add(new Node(1, 0, Direction.RIGHT));
        list.add(new Node(0, 0, Direction.RIGHT));
    }
    public Node prev = null;
    private boolean b1 = true;
    //往蛇身list的后面加一个节点
    public void addNode() {
        Node first = list.get(0);
        Node last = list.get(list.size()-1);
        switch (first.getD()) {
            case UP :
                list.add(new Node(last.getX(), last.getY()+1, last.getD()));
                break;
            case DOWN:
                list.add(new Node(last.getX(), last.getY()-1, last.getD()));
                break;
            case LEFT:
                list.add(new Node(last.getX()+1, last.getY(), last.getD()));
                break;
            case RIGHT:
                list.add(new Node(last.getX()-1, last.getY(), last.getD()));
                break;
        }
    }
    //移动
    public void move() {
        if (b1) {
            prev = list.get(list.indexOf(prev) + 1);
            b1 = false;
        }
        //移动每个节点
        //第一个节点要判断是否撞墙 是否装身体是否撞到食物
        // 需调用加分MyPanel中addPoint();和MyPanel中setB1(true);
        Node n = Main.getSnake().list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                doWorkF(n);
            }else{
                n = Main.getSnake().list.get(i);
                doWork(n);
            }
        }
        if (list.size()-1 <= list.indexOf(prev)) {
            //prev = list.get(list.indexOf(0));
            prev = list.get(0);
        } else if (prev.d != list.get(list.indexOf(prev) + 1).getD()) {
            list.get(list.indexOf(prev) + 1).setD(prev.getD());
            prev = list.get(list.indexOf(prev) + 1);
        }
        Main.getMyPanel().addStep();
    }
    public void doWorkF(Node n) {
        //当前的位置信息
        Direction d = n.d;
        //右
        switch (d) {
            case UP:
                n.addY(-1);
                //判断x，y位置是否撞墙 和撞身体 食物这儿不负责
                isCollisionThrowException(n.getX(),n.getY());
                //判断撞食物
                if(n.getY() == Main.getMyPanel().getFoodY() && n.getX() == Main.getMyPanel().getFoodX()){
                    //获取新的food x，y
                    getNewF();
                    //加一个身体
                    addNode();
                }
                break;
            case DOWN:
                n.addY(1);
                //判断x，y位置是否撞墙 和撞身体 食物这儿不负责
                isCollisionThrowException(n.getX(),n.getY());
                //判断撞食物
                if(n.getY() == Main.getMyPanel().getFoodY() && n.getX() == Main.getMyPanel().getFoodX()){
                    //获取新的food x，y
                    getNewF();
                    //加一个身体
                    addNode();
                }
                break;
            case LEFT:
                n.addX(-1);
                //判断x，y位置是否撞墙 和撞身体 食物这儿不负责
                isCollisionThrowException(n.getX(),n.getY());
                //判断撞食物
                if(n.getX() == Main.getMyPanel().getFoodX() && n.getY() == Main.getMyPanel().getFoodY()){
                    //获取新的food x，y
                    getNewF();
                    //加一个身体
                    addNode();
                }
                break;
            case RIGHT:
                n.addX(1);
                //判断x，y位置是否撞墙 和撞身体 食物这儿不负责
                isCollisionThrowException(n.getX(),n.getY());
                //判断撞食物
                if(n.getX()+1 == Main.getMyPanel().getFoodX() && n.getY() == Main.getMyPanel().getFoodY()){
                    //获取新的food x，y
                    getNewF();
                    //加一个身体
                    addNode();
                }
                break;
        }
    }
    //头
    public void doWork(Node n) {
        //当前的位置信息
        Direction d = n.d;
        //右
        switch (d) {
            case UP:
                //碰撞判断 装墙 撞身 装食物  未做
                n.addY(-1);
                break;
            case DOWN:
                //碰撞判断 装墙 撞身 装食物  未做
                n.addY(1);
                break;
            case LEFT:
                //碰撞判断 装墙 撞身 装食物  未做
                n.addX(-1);
                break;
            case RIGHT:
                //碰撞判断 装墙 撞身 装食物  未做
                n.addX(1);
                break;
        }
    }

    public void getNewF(){
        Main.getMyPanel().setB1(true);
        Main.getMyPanel().addPoint();
    }
    //判断x，y位置是否撞墙 和撞身体 食物这儿不负责
    public void isCollisionThrowException(int x,int y){
        //判断撞墙
        if(x>19 || y>19){
            gameOver();
            throw new LogicException("您已撞墙 游戏结束！");
        }
        if(x<0 || y<0){
            gameOver();
            throw new LogicException("您已撞墙 游戏结束！");
        }
        //撞身体这不不需要。。。 不完全转弯都不能动
        //如果要做，需要换算法 move改坐标 --> 删除尾 增加头
    }
    public void gameOver(){
        Frame f= new Frame("您已撞墙 游戏结束!");
        f.setSize(50,75);
        f.setLocation(100,200);
        Button b = new Button("OK");
        f.add(b);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    //@getter setter
    public void setFirstD(Direction d) {
        list.get(0).setD(d);
    }
    public int getCount(){
        return count;
    }
    public void addCount(){
        count++;
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(b2){
            switch(key){
                case KeyEvent.VK_LEFT:
                    if(list.get(0).getD() != Direction.LEFT){
                        Main.getSnake().setFirstD(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(list.get(0).getD() != Direction.UP){
                        Main.getSnake().setFirstD(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(list.get(0).getD() != Direction.RIGHT){
                        Main.getSnake().setFirstD(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(list.get(0).getD() != Direction.DOWN){
                        Main.getSnake().setFirstD(Direction.DOWN);
                    }
                    break;
            }
            b2= false;
            return;
        }
        switch(key){
            case KeyEvent.VK_LEFT:
                if((list.get(0).getD() != Direction.LEFT) && getCount() >= list.size()-1){
                    Main.getSnake().setFirstD(Direction.LEFT);
                    count = 0;
                }
                break;
            case KeyEvent.VK_UP:
                if((list.get(0).getD() != Direction.UP) && getCount() >= list.size()-1){
                    Main.getSnake().setFirstD(Direction.UP);
                    count = 0;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if((list.get(0).getD() != Direction.RIGHT) && getCount() >= list.size()-1){
                    Main.getSnake().setFirstD(Direction.RIGHT);
                    count = 0;
                }
                break;
            case KeyEvent.VK_DOWN:
                if((list.get(0).getD() != Direction.DOWN) && getCount() >= list.size()-1){
                    Main.getSnake().setFirstD(Direction.DOWN);
                    count = 0;
                }
                break;
        }
    }
}
