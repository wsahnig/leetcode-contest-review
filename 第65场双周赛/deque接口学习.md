接口分析
双向队列操作
插入元素
addFirst(): 向队头插入元素，如果元素为空，则发生NPE

addLast(): 向队尾插入元素，如果为空，则发生NPE

offerFirst(): 向队头插入元素，如果插入成功返回true，否则返回false

offerLast(): 向队尾插入元素，如果插入成功返回true，否则返回false

移除元素
removeFirst(): 返回并移除队头元素，如果该元素是null，则发生NoSuchElementException

removeLast(): 返回并移除队尾元素，如果该元素是null，则发生NoSuchElementException

pollFirst(): 返回并移除队头元素，如果队列无元素，则返回null

pollLast(): 返回并移除队尾元素，如果队列无元素，则返回null

获取元素
getFirst(): 获取队头元素但不移除，如果队列无元素，则发生NoSuchElementException

getLast(): 获取队尾元素但不移除，如果队列无元素，则发生NoSuchElementException

peekFirst(): 获取队头元素但不移除，如果队列无元素，则返回null

peekLast(): 获取队尾元素但不移除，如果队列无元素，则返回null

栈操作
pop(): 弹出栈中元素，也就是返回并移除队头元素，等价于removeFirst()，如果队列无元素，则发生NoSuchElementException

push(): 向栈中压入元素，也就是向队头增加元素，等价于addFirst()，如果元素为null，则发生NPE，如果栈空间受到限制，则发生IllegalStateException

应用场景
满足FIFO场景时
满足LIFO场景时，曾经在解析XML按标签时使用过栈这种数据结构，但是却选择Stack类，如果在进行栈选型时，更推荐使用Deque类，应为Stack是线程同步
主要实现
ArrayDeque: 基于数组实现的线性双向队列
LinkedList: 基于链表实现的链式双向队列