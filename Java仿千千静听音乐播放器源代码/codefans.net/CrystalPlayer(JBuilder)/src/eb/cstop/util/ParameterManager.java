package eb.cstop.util;

/**
*
* <p>Title: 参数管理器</p>
* <p>Description: 生成XML文挡或二维视图支持Ajax</p>
* <p>Copyright: Copyright (c) 2007</p>
* <p>Company: 重庆新浪</p>
* @author not attributable
* @version 1.0
* @download:http://www.codefans.net
*/
import java.util.ArrayList;

public class ParameterManager {
	private ArrayList contentList = new ArrayList();//存放主XML字符串
	private String[] fileds;//字段名称
	private int currentIndex = -1;//当前行
	
	/**
	 * 构造函数
	 */
	public ParameterManager(){
		this(null);
	}
	
	/**
	 * 构造函数
	 * codes - 初始化字段的数组对象
	 */
	public ParameterManager(String[] fileds){
		this.fileds = fileds;
	}
	
	/**
	 * 设置字段
	 * codes - 字段
	 */
	public void setField(String[] fileds){
		this.fileds = fileds;
	}
	
	/**
	 * 取得字段
	 */
	public String[] getField(){
		return this.fileds;
	}
	
	/**
	 * 插入一行
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
	 * 删除当前行
	 */
	public Object remove(){
		return this.removeByIndex(currentIndex);
	}
	
	/**
	 * 删除指定行
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
	 * 删除所有参数
	 */
	public void removeAll(){
		if(contentList.removeAll(contentList))this.gotoRow(-1);
	}
	
	/**
	 * 转到指定行
	 * index - 指定列
	 */
	public void gotoRow(int index){
		if(index > -1 && index < contentList.size() && contentList.size() > 0)currentIndex = index;
		else if(index >= contentList.size())currentIndex = contentList.size() - 1;
		else currentIndex = -1;
	}
	
	/**
	 * 通过字段设定值
	 * code - 字段
	 * value - 值
	 */
	public void setValue(String code,String value){
		this.setValue(this.getIndexByCode(code),value);
	}
	
	/**
	 * 通过列号设定值
	 * index - 列号
	 * value - 值
	 */
	public void setValue(int index,String value){
		if(index > -1 && currentIndex > -1){
			String[] values = (String[])contentList.get(currentIndex);
			if(value == null)value = "";
			values[index] = value;
		}
	}
	
	/**
	 * 通过字段得到值
	 */
	public String getValue(String code){
		return this.getValue(this.getIndexByCode(code));
	}
	
	/**
	 * 通过列号得到值
	 */
	public String getValue(int index){
		if(index == -1)return null;
		String[] values = (String[])contentList.get(currentIndex);
		if(values == null)return null;
		else return values[index];
	}
	
	/**
	 * 通过字段得到字段列号;
	 * code - 字段
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
	 * 生成XML字符串
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