package code.practice.leetcode;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class DiningPhilosophers {
    
    //private AtomicBoolean forkLocks[] = new AtomicBoolean[]{new AtomicBoolean(false),new AtomicBoolean(false),new AtomicBoolean(false),new AtomicBoolean(false),new AtomicBoolean(false)};
    private final Object forkLocks[] = new Object[]{new Object(),new Object(),new Object(),new Object(),new Object()};
    private final int rightForkIndex[] = new int[]{0,1,2,3,4};
    private final int leftForkIndex[] = new int[]{1,2,3,4,0};
    private final boolean isEven[] = new boolean[]{true,false,true,false,true};

    public DiningPhilosophers() {
        //forkLocks = new Semaphore[]{new Semaphore(1),new Semaphore(1),new Semaphore(1),new Semaphore(1),new Semaphore(1)};
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
                            
        int rightFork = rightForkIndex[philosopher];
        int leftFork = leftForkIndex[philosopher];

        //boolean isEvenPhil = philosopher % 2 == 0 ? true : false;

        if(isEven[philosopher]) {
            eatAlgo(rightFork, pickRightFork, putRightFork, leftFork, pickLeftFork, putLeftFork, eat);
        }
        else {
            eatAlgo(leftFork, pickLeftFork, putLeftFork, rightFork, pickRightFork, putRightFork, eat);
        }


        
    }

    public void eatAlgo(int firstFork, Runnable pickFirstFork, Runnable putFirstFork, int secondFork, Runnable pickSecondFork, Runnable putSecondFork, Runnable eat) {
        //AtomicBoolean firstLock = forkLocks[firstFork];
        //AtomicBoolean secondLock = forkLocks[secondFork];
        Object firstLock = forkLocks[firstFork];
        Object secondLock = forkLocks[secondFork];    
        //try {
        synchronized(firstLock) {
            //forkLocks[firstFork].acquire();
            //while(!firstLock.compareAndSet(false,true)) {}
            pickFirstFork.run();
            synchronized(secondLock) {
                //forkLocks[secondFork].acquire();
                //while(!secondLock.compareAndSet(false,true)) {}
                pickSecondFork.run();
                eat.run();
                putSecondFork.run();
                //secondLock.set(false);
                //forkLocks[secondFork].release();
            }
            putFirstFork.run();
            //firstLock.set(false);
            //forkLocks[firstFork].release();
        }
        //}catch(InterruptedException ie) {

        //}
    }
}