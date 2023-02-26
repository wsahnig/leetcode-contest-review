package 第67场双周赛;

import java.util.PriorityQueue;
import javafx.util.Pair;
/**
 * https://leetcode.cn/problems/sequentially-ordinal-rank-tracker/
 * 
 * 序列顺序查询（困难）
 * 一个观光景点由它的名字 name 和景点评分 score 组成，其中 name 是所有观光景点中 唯一 的字符串，score 是一个整数。
 * 景点按照最好到最坏排序。景点评分 越高 ，这个景点越好。如果有两个景点的评分一样，那么 字典序较小 的景点更好。
 * 你需要搭建一个系统，查询景点的排名。初始时系统里没有任何景点。这个系统支持：
 * 添加 景点，每次添加 一个 景点。
 * 查询 已经添加景点中第 i 好 的景点，其中 i 是系统目前位置查询的次数（包括当前这一次）。
 * 比方说，如果系统正在进行第 4 次查询，那么需要返回所有已经添加景点中第 4 好的。
 * 注意，测试数据保证 任意查询时刻 ，查询次数都 不超过 系统中景点的数目。
 * 请你实现 SORTracker 类：
 * SORTracker() 初始化系统。
 * void add(string name, int score) 向系统中添加一个名为 name 评分为 score 的景点。
 * string get() 查询第 i 好的景点，其中 i 是目前系统查询的次数（包括当前这次查询）。
 * 
 * 示例：
 * 输入：
 * ["SORTracker", "add", "add", "get", "add", "get", "add", "get", "add", "get", "add", "get", "get"]
 * [[], ["bradford", 2], ["branford", 3], [], ["alps", 2], [], ["orland", 2], [], ["orlando", 3], [], ["alpine", 2], [], []]
 * 输出：
 * [null, null, null, "branford", null, "alps", null, "bradford", null, "bradford", null, "bradford", "orland"]
 * 
 * name 只包含小写英文字母，且每个景点名字互不相同。
 * 1 <= name.length <= 10
 * 1 <= score <= 10^5
 * 任意时刻，调用 get 的次数都不超过调用 add 的次数。
 * 总共 调用 add 和 get 不超过 4 * 10^4
 * 
 */
/**
 * 一句话题解：
 *  使用两个堆，小根堆（从大到小排列）left和大根堆（从小到大排列）right;
 *  left堆中元素个数最多为k+1，k为已查询的次数，调用add时，添加到left，如果大小超过k+1，left堆中弹出最大元素到right;
 *  当get方法被调用时，如果left元素个数为k+1，从right中弹出最小元素到left，然后从left弹出最大元素到right（避免这种情况:left中有k个元素，调用add，add的元素比left中所有元素都大，但它不是比right中所有元素都小）;
 *  当get方法被调用时，如果left元素个数为k，从right中弹出最小元素到left;
 *  left堆顶是序列中第k+1大元素。
 * 
 * 相似思路的题：
 * 295. 数据流的中位数
 * https://leetcode.cn/problems/find-median-from-data-stream/
 * 
 */

 class SORTracker {
    PriorityQueue<Pair<String, Integer>> left;
    PriorityQueue<Pair<String, Integer>> right;
    int k;
    public SORTracker() {
        right = new PriorityQueue<>((a, b) -> {
            if(a.getValue().intValue() != b.getValue().intValue()) {
                return b.getValue().compareTo(a.getValue());
            } else {
                return a.getKey().compareTo(b.getKey());
            }
        });

        left = new PriorityQueue<>((a, b) -> {
            if(a.getValue().intValue() != b.getValue().intValue()) {
                return a.getValue().compareTo(b.getValue());
            } else {
                return b.getKey().compareTo(a.getKey());
            }
        });

        k = 0;
    }
    
    public void add(String name, int score) {
        left.offer(new Pair<>(name, score));
        if(left.size() > k + 1) {
            right.offer(left.poll());
        }
    }
    
    public String get() {
        if(!right.isEmpty()) {
            left.offer(right.poll());
            if(left.size() > k + 1)
            right.offer(left.poll());
        }
        k++;
        return left.peek().getKey();
    }
}

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker obj = new SORTracker();
 * obj.add(name,score);
 * String param_2 = obj.get();
 */