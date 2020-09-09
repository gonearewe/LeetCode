// 5 个沉默寡言的哲学家围坐在圆桌前，每人面前一盘意面。叉子放在哲学家之间的桌面上。（5 个哲学家，5 根叉子）

// 所有的哲学家都只会在思考和进餐两种行为间交替。哲学家只有同时拿到左边和右边的叉子才能吃到面，
// 而同一根叉子在同一时间只能被一个哲学家使用。每个哲学家吃完面后都需要把叉子放回桌面以供其他哲学家吃面。
// 只要条件允许，哲学家可以拿起左边或者右边的叉子，但在没有同时拿到左右叉子时不能进食。

// 假设面的数量没有限制，哲学家也能随便吃，不需要考虑吃不吃得下。

// 设计一个进餐规则（并行算法）使得每个哲学家都不会挨饿；
// 也就是说，在没有人知道别人什么时候想吃东西或思考的情况下，每个哲学家都可以在吃饭和思考之间一直交替下去。

// 哲学家从 0 到 4 按 顺时针 编号。请实现函数 
// void wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork)：

// philosopher 哲学家的编号。
// pickLeftFork 和 pickRightFork 表示拿起左边或右边的叉子。
// eat 表示吃面。
// putLeftFork 和 putRightFork 表示放下左边或右边的叉子。
// 由于哲学家不是在吃面就是在想着啥时候吃面，所以思考这个方法没有对应的回调。
// 给你 5 个线程，每个都代表一个哲学家，请你使用类的同一个对象来模拟这个过程。在最后一次调用结束之前，可能会为同一个哲学家多次调用该函数。

// 示例：
// 输入：n = 1
// 输出：[[4,2,1],[4,1,1],[0,1,1],[2,2,1],[2,1,1],[2,0,3],[2,1,2],[2,2,2],[4,0,3],[4,1,2],[0,2,1],[4,2,2],[3,2,1],[3,1,1],[0,0,3],[0,1,2],[0,2,2],[1,2,1],[1,1,1],[3,0,3],[3,1,2],[3,2,2],[1,0,3],[1,1,2],[1,2,2]]
// 解释:
// n 表示每个哲学家需要进餐的次数。
// 输出数组描述了叉子的控制和进餐的调用，它的格式如下：
// output[i] = [a, b, c] (3个整数)
// - a 哲学家编号。
// - b 指定叉子：{1 : 左边, 2 : 右边}.
// - c 指定行为：{1 : 拿起, 2 : 放下, 3 : 吃面}。
// 如 [4,2,1] 表示 4 号哲学家拿起了右边的叉子。
//  
// 提示：
// 1 <= n <= 60


// PS：死锁的 44 个必要条件：
//
// 互斥条件：一个资源每次只能被一个进程使用，即在一段时间内某 资源仅为一个进程所占有。此时若有其他进程请求该资源，则请求进程只能等待。
// 请求与保持条件：进程已经保持了至少一个资源，但又提出了新的资源请求，而该资源 已被其他进程占有，此时请求进程被阻塞，但对自己已获得的资源保持不放。
// 不可剥夺条件:进程所获得的资源在未使用完毕之前，不能被其他进程强行夺走，即只能 由获得该资源的进程自己来释放（只能是主动释放)。
// 循环等待条件: 若干进程间形成首尾相接循环等待资源的关系。
//
// 作者：gfu
// 链接：https://leetcode-cn.com/problems/the-dining-philosophers/solution/1ge-semaphore-1ge-reentrantlockshu-zu-by-gfu/

// 避免 5 个哲学家同时先拿了同一边的叉子，可以：
// 1. 最多只允许 4 个哲学家去持有叉子
// 2. 让部分哲学家优先去获取其左边的叉子，再去获取其右边的叉子；再让剩余哲学家优先去获取其右边的叉子
import java.util.concurrent.locks.ReentrantLock;

class DiningPhilosophers {
    private final ReentrantLock[] reentrantLocks = {
        new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock()};

    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork,
     Runnable eat, Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {
        if (philosopher % 2 == 0) { // 偶数哲学家优先去获取其左边的叉子
            reentrantLocks[philosopher].lock();
            reentrantLocks[(philosopher + 1) % 5].lock();
        } else { // 奇数哲学家优先去获取其右边的叉子
            reentrantLocks[(philosopher + 1) % 5].lock();
            reentrantLocks[philosopher].lock();
        }

        pickLeftFork.run();
        pickRightFork.run();

        eat.run();

        putLeftFork.run();
        putRightFork.run();
        reentrantLocks[(philosopher + 1) % 5].unlock();
        reentrantLocks[philosopher].unlock();

    }
}
