package com.gsoft.workflow.msgsender;

public class MsgQueue extends java.util.Vector {

	public MsgQueue() {
		super();
	}
		
	//取出队列元素
	public synchronized Object deq() {
		/* 队列若为空，引发EmptyQueueException异常 */
		if( this.empty() )
			throw new EmptyQueueException();
		Object x = super.elementAt(0);
		super.removeElementAt(0);
		return x;
	} 

	
	//写入队列元素
	public synchronized void enq(Object x) {
		super.addElement(x);
	} 

	//读取队列第一个元素，不删除
	public synchronized Object front() {
		if( this.empty() )
			throw new EmptyQueueException();
		return super.elementAt(0);
	} 
	
	public boolean empty() {
		return super.isEmpty();
	}

	public synchronized void clear() {
		super.removeAllElements();
		} 

	public int search(Object x) {
		return super.indexOf(x);
	} 
}
