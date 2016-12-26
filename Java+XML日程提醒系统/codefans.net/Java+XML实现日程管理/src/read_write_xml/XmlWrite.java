package read_write_xml;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
public class XmlWrite {
private Document document;
private String filename;

public XmlWrite(String name) throws ParserConfigurationException{
filename=name;
DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
DocumentBuilder builder=factory.newDocumentBuilder();
document=builder.newDocument();
}
public void toWrite(String mytitle,String mycontent){
Element root=document.createElement("WorkShop");
document.appendChild(root);
Element title=document.createElement("Title");
title.appendChild(document.createTextNode(mytitle));
root.appendChild(title);
Element content=document.createElement("Content");
content.appendChild(document.createTextNode(mycontent));
root.appendChild(content);
}
public void toSave(){
try{
TransformerFactory tf=TransformerFactory.newInstance();
Transformer transformer=tf.newTransformer();
DOMSource source=new DOMSource(document);
transformer.setOutputProperty(OutputKeys.ENCODING,"GB2312");
transformer.setOutputProperty(OutputKeys.INDENT,"yes");
PrintWriter pw=new PrintWriter(new FileOutputStream(filename));
StreamResult result=new StreamResult(pw);
transformer.transform(source,result);
}
catch(TransformerException mye){
mye.printStackTrace();
}
catch(IOException exp){
exp.printStackTrace();
}
}
public static void main(String args[]){
try{
XmlWrite myxml=new XmlWrite("9.xml");
myxml.toWrite("中文题目","中文内容");
myxml.toSave();
System.out.print("Your writing is successful.");
}
catch(ParserConfigurationException exp){
exp.printStackTrace();
System.out.print("Your writing is failed.");
}
}
}


