/**
 * https://leetcode.cn/problems/walking-robot-simulation-ii/
 * 
 * 模拟行走机器人 II(中等)
 * 给你一个在 XY 平面上的 width x height 的网格图，左下角 的格子为 (0, 0) ，右上角 的格子为 (width - 1, height - 1) 。
 * 网格图中相邻格子为四个基本方向之一（"North"，"East"，"South" 和 "West"）。
 * 一个机器人 初始 在格子 (0, 0) ，方向为 "East" 。
 * 
 * 机器人可以根据指令移动指定的 步数 。每一步，它可以执行以下操作。
 * 沿着当前方向尝试 往前一步 。
 * 如果机器人下一步将到达的格子 超出了边界 ，机器人会 逆时针 转 90 度，然后再尝试往前一步。
 * 如果机器人完成了指令要求的移动步数，它将停止移动并等待下一个指令。
 * 
 * 请你实现 Robot 类：
 * Robot(int width, int height) 初始化一个 width x height 的网格图，机器人初始在 (0, 0) ，方向朝 "East" 。
 * void move(int num) 给机器人下达前进 num 步的指令。
 * int[] getPos() 返回机器人当前所处的格子位置，用一个长度为 2 的数组 [x, y] 表示。
 * String getDir() 返回当前机器人的朝向，为 "North" ，"East" ，"South" 或者 "West" 。
 * 
 * 示例：
 * 输入：
 * ["Robot", "move", "move", "getPos", "getDir", "move", "move", "move", "getPos", "getDir"]
 * [[6, 3], [2], [2], [], [], [2], [1], [4], [], []]
 * 输出：
 * [null, null, null, [4, 0], "East", null, null, null, [1, 2], "West"]
 */

/**
 * 一句话题解：
 * 机器人的路径有规律性，因此记录当前总步数，绕圈过程对此取模;
 * 分类讨论四条边上步数的范围，步数能推出坐标及方向;
 * （0,0）的方向需特判，如果还未移动，这个位置的方向是"East"，否则回到起点，这个位置方向是"South"。
 * 
 */

class Robot {
    int total;
    int width;
    int height;
    int cycle;
    boolean isMoved;

    public Robot(int width, int height) {
        total = 0;
        this.width = width;
        this.height = height;
        this.cycle = (width+height-2) * 2;
    }
    
    public void step(int num) {
        total += num;
        total %= cycle;
        if(!isMoved) {
            isMoved = true;
        }
    }
    
    public int[] getPos() {
        if(total < width) {
            return new int[]{total, 0};
        } else if(total < width + height - 1) {
            return new int[]{width - 1, total - width + 1};
        } else if(total < 2 * width + height - 2) {
            return new int[]{2 * width + height - 2 - total - 1, height - 1};
        } else {
            return new int[]{0, cycle - total};
        }
    }
    
    public String getDir() {
        if(total == 0) {
            return !isMoved ? "East" : "South";
        } else if(total < width) {
            return "East";
        } else if(total < width + height - 1) {
            return "North";
        } else if(total < 2 * width + height - 2) {
            return "West";
        } else {
            return "South";
        }
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */
