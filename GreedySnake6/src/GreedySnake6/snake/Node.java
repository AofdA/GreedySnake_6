package GreedySnake6.snake;

import GreedySnake6.support.Direction;

public class Node {
    int x;
    int y;
    Direction d;
    public Node(int x, int y, Direction d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
    public int getX() {
        return x;
    }
    public void addX(int i) {
        this.x = this.x+i;
    }
    public int getY() {
        return y;
    }
    public void addY(int i) {
        this.y = this.y+i;
    }
    public Direction getD() {
        return d;
    }
    public void setD(Direction d) {
        this.d = d;
    }
    //@toString
    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", d=" + d +
                '}';
    }
}
