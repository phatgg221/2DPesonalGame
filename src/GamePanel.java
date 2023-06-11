import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Screen setting
    final int originalTitleSize = 16;
    final int scale= 3;


    final int tileSize = originalTitleSize * scale;// 48x48 pixels
    final int maxScreenX= 16;
    final int maxScreenY= 12;
    final int screenWidth= tileSize *maxScreenX;// 768 pixels
    final int screenHeight = tileSize* maxScreenY;// 576 pixels

    //Fps
    int fps= 60;
    KeyHandler keyHandler= new KeyHandler();
    Thread gameThread;

    //set player's default position
    int playerx =100;
    int playerY= 100;
    int speed = 4;
    public GamePanel(){
        System.out.println(screenWidth);
        System.out.println(screenHeight);
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread= new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval= 1000000000/ fps;
        double nextDrawTime= System.nanoTime()+ drawInterval;

        while(gameThread != null){

           // update infomations of the game
            update();
            // draw the screen with updated infomations
            repaint();



            try {
                double remainingTime= nextDrawTime- System.nanoTime();
                remainingTime= remainingTime/1000000;
                if(remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){
        if(keyHandler.upPressed){
            playerY-= speed;
        }
        else if(keyHandler.downPressed){
            playerY+= speed;
        }
        else if(keyHandler.leftPressed){
            playerx-= speed;
        }
        else if(keyHandler.rightPressed){
            playerx+= speed;
        }

    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D= (Graphics2D) graphics;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(playerx,playerY,tileSize,tileSize);
        graphics2D.dispose();
    }
}
