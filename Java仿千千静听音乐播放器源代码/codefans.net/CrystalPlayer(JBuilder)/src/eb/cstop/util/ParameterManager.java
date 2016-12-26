package eb.cstop.util;

/**
*
* <p>Title: ����������</p>
* <p>Description: ����XML�ĵ����ά��ͼ֧��Ajax</p>
* <p>Copyright: Copyright (c) 2007</p>
* <p>Company: ��������</p>
* @author not attributable
* @version 1.0
* @download:http://www.codefans.net
*/
import java.util.ArrayList;

public class ParameterManager {
	private ArrayList contentList = new ArrayList();//�����XML�ַ���
	private String[] fileds;//�ֶ�����
	private int currentIndex = -1;//��ǰ��
	
	/**
	 * ���캯��
	 */
	public ParameterManager(){
		this(null);
	}
	
	/**
	 * ���캯��
	 * codes - ��ʼ���ֶε��������
	 */
	public ParameterManager(String[] fileds){
		this.fileds = fileds;
	}
	
	/**
	 * �����ֶ�
	 * codes - �ֶ�
	 */
	public void setField(String[] fileds){
		this.fileds = fileds;
	}
	
	/**
	 * ȡ���ֶ�
	 */
	public String[] getField(){
		return this.fileds;
	}
	
	/**
	 * ����һ��
	 */
	public void insertRow(){
		String[] values = new String[fileds.length];
		for(int i=0;i<fileds.length;i++){
			values[i] = "";
		}
		contentList.add(values);
		currentIndex++;
		this.gotoRow(currentIndex);
	}
	
	/**
	 * ɾ����ǰ��
	 */
	public Object remove(){
		return this.removeByIndex(currentIndex);
	}
	
	/**
	 * ɾ��ָ����
	 */
	public Object removeByIndex(int index){
		Object swapObject = contentList.remove(index);
		if(swapObject != null){
			currentIndex--;
			this.gotoRow(currentIndex);
		}
		swapObject = null;
		return swapObject;
	}
	
	/**
	 * ɾ�����в���
	 */
	public void removeAll(){
		if(contentList.removeAll(contentList))this.gotoRow(-1);
	}
	
	/**
	 * ת��ָ����
	 * index - ָ����
	 */
	public void gotoRow(int index){
		if(index > -1 && index < contentList.size() && contentList.size() > 0)currentIndex = index;
		else if(index >= contentList.size())currentIndex = contentList.size() - 1;
		else currentIndex = -1;
	}
	
	/**
	 * ͨ���ֶ��趨ֵ
	 * code - �ֶ�
	 * value - ֵ
	 */
	public void setValue(String code,String value){
		this.setValue(this.getIndexByCode(code),value);
	}
	
	/**
	 * ͨ���к��趨ֵ
	 * index - �к�
	 * value - ֵ
	 */
	public void setValue(int index,String value){
		if(index > -1 && currentIndex > -1){
			String[] values = (String[])contentList.get(currentIndex);
			if(value == null)value = "";
			values[index] = value;
		}
	}
	
	/**
	 * ͨ���ֶεõ�ֵ
	 */
	public String getValue(String code){
		return this.getValue(this.getIndexByCode(code));
	}
	
	/**
	 * ͨ���кŵõ�ֵ
	 */
	public String getValue(int index){
		if(index == -1)return null;
		String[] values = (String[])contentList.get(currentIndex);
		if(values == null)return null;
		else return values[index];
	}
	
	/**
	 * ͨ���ֶεõ��ֶ��к�;
	 * code - �ֶ�
	 */
	public int getIndexByCode(String code){
		if(code == null)return -1;
		int i=0;
		while(i<fileds.length){
			if(fileds[i].equals(code))return i;
			i++;
		}
		return -1;
	}
	
	/**
	 * ����XML�ַ���
	 */
	public String createDOM(){
		String xml = "<?xml version=\"1.0\" encoding=\"GB2312\"?>\t\n";
		xml += "<RootSet>\t\n";
		String[] values = null;
		for(int i=0;i<contentList.size();i++){
			xml += "  <RowSet index=\"" + i + "\">\t\n";
			values = (String[])contentList.get(i);
			for(int k=0;k<values.length;k++){
				xml += "    <ColumnSet index=\"" + k + "\" Filed=\"" + fileds[k] + "\">";
				xml += values[k]; 
				xml += "</ColumnSet>\t\n";
			}
			xml += "  </RowSet>\t\n";
		}
		for(int i=0;i<fileds.length;i++){
			xml += "  <FieldSet index=\"" + i + "\">" + fileds[i] + "</FieldSet>\t\n";
		}
		xml += "</RootSet>\t\n";
		return xml;
	}
}