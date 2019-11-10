package code.practice.leetcode;

public class DiningPhilosophers {
    
    private Object forkLocks[];

    public DiningPhilosophers() {
        forkLocks = new Object[]{new Object(),new Object(),new Object(),new Object(),new Object()};
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
                            
        int rightForkIndex = philosopher;
        int leftForkIndex = (philosopher < (forkLocks.length-1)) ? philosopher + 1 : 0;

        boolean isEvenPhil = philosopher % 2 == 0 ? true : false;

        if(isEvenPhil) {
            eatAlgo(rightForkIndex, pickRightFork, putRightFork, leftForkIndex, pickLeftFork, putLeftFork, eat);
        }
        else {
            eatAlgo(leftForkIndex, pickLeftFork, putLeftFork, rightForkIndex, pickRightFork, putRightFork, eat);
        }


        
    }

    public void eatAlgo(int firstFork, Runnable pickFirstFork, Runnable putFirstFork, int secondFork, Runnable pickSecondFork, Runnable putSecondFork, Runnable eat) {
        synchronized(forkLocks[firstFork]) {
            pickFirstFork.run();
            synchronized(forkLocks[secondFork]) {
                pickSecondFork.run();
                eat.run();
                putSecondFork.run();
            }
            putFirstFork.run();
        }
    }
}