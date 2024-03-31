package org.example.demo4.oldFiles.accountFiles.scheduler;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Scheduler {

    private long p ;

    public Scheduler(Period period) {
        calculatePeriod(period);
        Calendar date = Calendar.getInstance();
        date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), 1, 0, 0, 0);
        Timer timer = new Timer();
        timer.schedule(task, date.getTime(), p);
    }


    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            doOnTime();
        }
    };

    private void calculatePeriod(Period period){
        if (period.equals(Period.Daily))
            p = 1000L * 60 * 60 * 24;
        if (period.equals(Period.Weekly))
            p = 1000L * 60 * 60 * 24 * 7;
        if (period.equals(Period.Monthly))
            p = 1000L * 60 * 60 * 24 * 30;
        if (period.equals(Period.Yearly))
            p = 1000L * 60 * 60 * 24 * 365;
    }

    public abstract void doOnTime();
}
