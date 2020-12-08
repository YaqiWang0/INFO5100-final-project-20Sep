package ui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class TestSingleton {
    private JFrame frame = new JFrame("测试");
    private JPanel p = new JPanel();
    private JButton btn1 = new JButton("弹出第一个窗口");

    private JFrame f1;

    public TestSingleton(){
        init();
    }

    public void init(){
        p.add(btn1);

        frame.add(p);
        frame.setSize(222, 222);
        frame.setLocation(50, 50);
        frame.setVisible(true);

        /*
         * 给按钮添加事件监听器
         */
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f1 = Singleton1.getInstance();

                f1.setTitle("第一个窗口");
                f1.setSize(250, 180);
                f1.setLocation(270, 50);
                f1.setVisible(true);
            }
        });

    }

    /**
     * 静态内部类实现单例模式
     */
    public static class Singleton1 extends JFrame{
        /**
         * 在静态内部类中定义单例对象
         */
        public static class SingletonClass1{
            private static final Singleton1 instance = new Singleton1();
        }

        private Singleton1(){

        }

        public static Singleton1 getInstance(){
            return SingletonClass1.instance;
        }
    }

    public static void main(String[] args) {
        TestSingleton test = new TestSingleton();
    }
}
