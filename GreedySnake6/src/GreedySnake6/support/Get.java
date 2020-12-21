package GreedySnake6.support;


import GreedySnake6.Main;

import java.util.concurrent.ThreadLocalRandom;

//所有需要支持的get方法在此
public class Get {
    //生成正确的food的X Y坐标
    //不与蛇重叠 不越界
    public static void getFoodXY(){
        int maxX = 0;
        int maxY = 0;
        for(int i = 0; i< Main.getSnake().list.size(); i++){
            maxX =  Main.getSnake().list.get(i).getX();
            maxY = Main.getSnake().list.get(i).getY();
        }
        if(maxX >= 20){
            throw new LogicException("未写maxX为20时如何让获取food坐标");
        }
        if(maxY >= 20){
            throw new LogicException("未写maxY为20时如何让获取food坐标");
        }
        int x = getRandomByNum(maxX,20);
        int y = getRandomByNum(maxX,20);
        Main.getMyPanel().setFoodX(x);
        Main.getMyPanel().setFoodY(y) ;
    }
    //含bound
    public static int getRandomByNum(int bound,int i){
        return ThreadLocalRandom.current().nextInt(bound,i+1);
    }
}
