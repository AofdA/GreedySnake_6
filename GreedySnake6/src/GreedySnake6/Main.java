package GreedySnake6;


import GreedySnake6.GUI.MyPanel;
import GreedySnake6.snake.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//主类
public class Main {
    //绘制 地图 蛇 食物 的实例
    private static MyPanel myPanel = new MyPanel();
    //蛇的实例
    private static Snake snake = new Snake();
    public static void main(String[] args) throws InterruptedException {
        firstF();
    }
    public static void firstF(){
        Frame f = new Frame("阴间游戏启动器");
        Button startB = new Button("开始阴间start");
        TextArea text = new TextArea(4,30);
        String str = "\n"+"\n"+"Welcome to choose this game, this game is completed independently, " +"\n"+
                "making is not easy, please take care of it! There may be some bugs " +"\n"+
                "in this game. I hope you can understand and like it!" + "\n"+
                "Play:1. Due to the characteristics of the game, The Snake must be fully "+"\n"+
                "straight to turn(can turn for the first time). " +"\n"+
                "Play:2. The keyboard controls the direction up and down, left and right."+"\n"+"\n"+
                "Version: AofdA GitHub_2020_12_21";
        text.setText("欢迎您选择此游戏,此游戏独立完成,制作不易,请多多关照！"+"\n"+"本游戏可能有些许Bug,但瑕不掩瑜;希望您能体谅,喜欢"
                +"\n"+"玩法:"+"\n"+"1.由于游戏特性,当蛇身完全伸直才能够转弯(第一次都可以转)"+"\n"+"2.小键盘上下左右控制方向"+"\n"
                +"\n"+"版本信息："+"\n"+"Version: AofdA GitHub_2020_12_21"+str);
        f.setSize(520,290);
        f.add(startB,BorderLayout.WEST);
        f.add(text,BorderLayout.CENTER);
        f.setVisible(true);
        f.setResizable(false);
        //登录游戏界面
        startB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchGame();
            }
        });
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Frame f= new Frame("您确定要关闭吗？");
                Button b1= new Button("YES");
                Button b2= new Button("NO");
                Button b3= new Button("NO");
                f.add(b1,BorderLayout.WEST);
                f.add(b2,BorderLayout.EAST);
                f.add(b3,BorderLayout.CENTER);
                f.setSize(30,75);
                f.setLocation(300,30);
                f.setVisible(true);
                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                b2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.setVisible(false);
                    }
                });
                f.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                b3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.setVisible(false);
                    }
                });
            }
        });
    }
    public static void launchGame(){
        JFrame f = new JFrame("阴间贪吃蛇-ofd-5");
        f.setSize(500,438);
        f.setLocation(100,200);
        f.add(myPanel, BorderLayout.CENTER);
        f.setVisible(true);//设置可见
        //设置关闭
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //为界面添加监听事件
        f.addKeyListener(new KeyMonitor());
        //运行移动和重绘的线程
        new Thread(new PThread()).start();
    }
    //@getter setter
    public static MyPanel getMyPanel() {
        return myPanel;
    }
    public static Snake getSnake() {
        return snake;
    }
}

class KeyMonitor extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Main.getSnake().keyPressed(e);
    }
}

class PThread implements Runnable {
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.getSnake().move();
            Main.getSnake().addCount();
            Main.getMyPanel().repaint();
        }
    }
}