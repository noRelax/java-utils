package dboperate;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
public class writexml {
 private Document document;
 private String filename;
 private Element root;
 public writexml() throws ParserConfigurationException{
  filename="data/Not_Forget.xml";
  DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
  DocumentBuilder builder=factory.newDocumentBuilder();
  document=builder.newDocument();
  this.root = document.createElement("Not_Forget");
  document.appendChild(root);

 }
 public void toWrite(Object[][] data) {

     for(int m=0; m < data.length;m++){
         Element memoire = document.createElement(
                 "memoire");
         root.appendChild(memoire);

         Element id = document.createElement("id");
         String ID = new String(String.valueOf(data[m][0]));
         id.appendChild(document.createTextNode(ID));
         memoire.appendChild(id);

         Element title = document.createElement("title");
         String Title = new String(String.valueOf(data[m][1]));
         title.appendChild(document.createTextNode(Title));
         memoire.appendChild(title);

         Element content = document.createElement(
                 "content");
         String Content = new String(String.valueOf(data[m][2]));
         content.appendChild(document.createTextNode(
                 Content));
         memoire.appendChild(content);

         Element className = document.createElement(
                 "className");
         String ClassName = new String(String.valueOf(data[m][3]));
         className.appendChild(document.createTextNode(
                 ClassName));
         memoire.appendChild(className);

         Element time = document.createElement("time");
         String Time = new String(String.valueOf(data[m][4]));
         time.appendChild(document.createTextNode(Time));
         memoire.appendChild(time);

         Element model = document.createElement("model");
         String Model = new String(String.valueOf(data[m][5]));
         model.appendChild(document.createTextNode(Model));
         memoire.appendChild(model);

         Element confirm = document.createElement(
                 "confirm");
         String Confirm = new String(String.valueOf(data[m][6]));
         confirm.appendChild(document.createTextNode(
                 Confirm));
         memoire.appendChild(confirm);

         Element youxiao = document.createElement(
                 "youxiao");
         String Youxiao = new String(String.valueOf(data[m][7]));
         youxiao.appendChild(document.createTextNode(
                 Youxiao));
         memoire.appendChild(youxiao);

         Element auditing = document.createElement("auditing");
         String Auditing = new String(String.valueOf(data[m][8]));
         auditing.appendChild(document.createTextNode(Auditing));
         memoire.appendChild(auditing);
     }
 }

 public void toSave(){
  try{
   TransformerFactory tf=TransformerFactory.newInstance();
   Transformer transformer=tf.newTransformer();
   DOMSource source=new DOMSource(document);
   PrintWriter pw=new PrintWriter(new FileOutputStream(filename));
   transformer.setOutputProperty(OutputKeys.ENCODING,"GB2312");
   transformer.setOutputProperty(OutputKeys.INDENT, "yes");
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
  writexml xsxml = new writexml();
  Object[][] data = { {"10", "shi", "内容", "普通提醒", "1", "2007-3-24", "1",
                    "1", "1"}, {"11", "shiyi", "content", "生日提醒", "1",
                    "2007-3-23", "1", "1", "0"},
  };
  xsxml.toWrite(data);
  xsxml.toSave();
  xsxml.toWrite(data);
  xsxml.toSave();
  System.out.print("Your writing is successful.");
  }
  catch(ParserConfigurationException exp){
   exp.printStackTrace();
   System.out.print("Your writing is failed.");
  }
 }
}
