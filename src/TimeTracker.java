/**
 * Created by andreydelany on 12/12/2016.
 */
public class TimeTracker {
    long start;
    long end;
    public TimeTracker(){
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public void end() {
        end = System.currentTimeMillis();
    }

    public void print(){
        System.out.println("needed " + (end - start) + " ms");
    }

    public long get() {
        return end - start;
    }
}
