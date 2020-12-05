package ui;


import javax.swing.JFrame;
import javax.swing.JLabel;

public class SuccessFrame {
    private JFrame jf;
    private JLabel jl;
    private int x;
    private int y;
    private int width;
    private int height;

    public SuccessFrame(String title,int x,int y,int width,int height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        jf=new JFrame();
        jl=new JLabel(title+"时间到!");
    }

    private void init(){
        jf.add(jl);
    }

    private void addEventHandler(){
        new Thread(){
            public void run() {
                jf.setExtendedState(JFrame.NORMAL);
                jf.setAlwaysOnTop(true);

                for (int i = 0; i < 30; i++) {
                    if(i%2==0){
                        x+=15;
                        y+=15;
                    }else{
                        x-=15;
                        y-=15;
                    }
                    jf.setLocation(x, y);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void show(){
        init();
        addEventHandler();
        jf.setSize(width, height);
        jf.setLocation(x, y);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
