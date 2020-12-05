package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TimeFrame {
    private JFrame jf;
    private JLabel jl1;
    private JLabel jl2;
    private String title;
    private Date targetDate;

    public TimeFrame(String title, Date targetDate) {
        super();
        this.title = title;
        this.targetDate = targetDate;
        jf = new JFrame(title + "倒计时牌");
        jl1 = new JLabel("距离" + title + "还有：");
        jl2 = new JLabel("xx天xx时xx分xx秒");
    }

    private void init() {
        jf.add(jl1, BorderLayout.NORTH);
        jf.add(jl2);
    }

    private void setStyle() {
        jl1.setFont(new Font("宋体", Font.BOLD, 20));
        jl2.setFont(new Font("宋体", Font.BOLD, 40));
        jl1.setForeground(Color.BLUE);
        jl2.setForeground(Color.RED);
    }

    private void addEventHandler() {
        new Thread() {
            public void run() {
                //每1秒更新jl2上的时间
                while (true) {
                    //当前时间
                    Date now = new Date();

                    //目标时间距离当前时间差的秒
                    long time = (targetDate.getTime() - now.getTime()) / 1000;

                    if (time <= 0) {
//						jl2.setText("时间到!");


//						jf.remove(jl1);
//						jf.remove(jl2);
//						jl2=new JLabel("时间到!");
//						jf.add(jl2);
//						jf.setVisible(true);

                        jf.dispose();
                        new SuccessFrame(title, jf.getX(), jf.getY(), jf.getWidth(), jf.getHeight()).show();

                        break;
                    }

                    //根据time算出天，时，分，秒
                    int day = (int) (time / (60 * 60 * 24));
                    int hour = (int) ((time % (60 * 60 * 24)) / (60 * 60));
                    int mins = (int) ((time % (60 * 60)) / 60);
                    int second = (int) (time % 60);

                    jl2.setText(day + "天" + hour + "时" + mins + "分" + second + "秒");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void show() {
        init();
        setStyle();
        addEventHandler();
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws Exception {
        String title = JOptionPane.showInputDialog("标题");
        String dateStr = JOptionPane.showInputDialog("目标时间");
        Date targetDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dateStr);

        new TimeFrame(title, targetDate).show();
    }
}