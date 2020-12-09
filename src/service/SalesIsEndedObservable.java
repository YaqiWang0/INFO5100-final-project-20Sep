package service;

import java.util.Date;
import java.util.Observable;

/* I created a SalesIsEndedObservable class, which would help me disable
pop-up buttons on AppUI.
 */
public class SalesIsEndedObservable extends Observable implements Runnable{
    private Thread thread;
    private Date endDate;
    private int index;

    public SalesIsEndedObservable(int index, Date endDate) {
        this.index = index;
        this.endDate = endDate;
        thread = new Thread(this::run);
        thread.start();
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void run() {
        while (true) {
            // if sales is ended, notify the observers, so observers could response.
            if (new Date().getTime() > endDate.getTime()) {
                setChanged();
                // pass true means the sales is ended.
                notifyObservers(true);
                thread = null;
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
