package com.gsoft.workflow.msgsender;

public class MsgQueue extends java.util.Vector {

	public MsgQueue() {
		super();
	}
		
	//ȡ������Ԫ��
	public synchronized Object deq() {
		/* ������Ϊ�գ�����EmptyQueueException�쳣 */
		if( this.empty() )
			throw new EmptyQueueException();
		Object x = super.elementAt(0);
		super.removeElementAt(0);
		return x;
	} 

	
	//д�����Ԫ��
	public synchronized void enq(Object x) {
		super.addElement(x);
	} 

	//��ȡ���е�һ��Ԫ�أ���ɾ��
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
