<%@ page import="java.util.*,org.json.*"%>
<%
/*
  (c) 2006, Daniel Rubio
  relased under terms of the GNU public license 
  http://www.gnu.org/licenses/licenses.html#TOCGPL
*/
// Following code placed in JSP for simplicity, not design  

// Create addressbook data structure
SortedMap addressBook = new TreeMap();

// Create new address entries and place in Map (Address POJO below)
Address maryLebow = new Address("5 Main Street","San Diego, CA",91912,"619-332-3452","664-223-4667"); 
addressBook.put("Mary Lebow",maryLebow);
Address amySmith = new Address("25 H Street","Los Angeles, CA",95212,"660-332-3452","541-223-4667"); 
addressBook.put("Sally May",amySmith);
Address johnKim = new Address("2343 Sugarland Drive","Houston, TX",55212,"554-332-3412","461-223-4667"); 
addressBook.put("John Kim",johnKim);
Address richardThorn = new Address("14 68th Street","New York, NY",12452,"212-132-6182","161-923-4001"); 
addressBook.put("Richard Thorn",richardThorn);
Address annMichaels = new Address("P.O BOX 54534","Seattle, WA",42452,"561-832-3180","531-133-9098"); 
addressBook.put("Ann Michaels",annMichaels);
Address georgeLee = new Address("131 Peach Drive","Atlanta, GA",32452,"123-722-3783","131-733-0084"); 
addressBook.put("George Lee",georgeLee);
Address bettyCarter = new Address("53 Mullholand Drive","Miami, FL",72452,"541-322-1723","546-338-1100"); 
addressBook.put("Betty Carter",bettyCarter);
Address normanTate = new Address("P.O BOX 13231","Portland, OR",52452,"341-122-0923","146-998-1172"); 
addressBook.put("Norman Tate",normanTate);
Address dennisWong = new Address("333 Harbour Drive","Miami, FL",74452,"521-122-8623","576-229-1234"); 
addressBook.put("Dennis Wong",dennisWong);
Address jackieBennet = new Address("9 Orchard Way","Cincinnati, OH",82452,"141-717-9921","172-638-01722"); 
addressBook.put("Jackie Bennett",jackieBennet);

// Define placeholder for JSON response
String result = new String();

// Get parameter (if any) passed into application 
String from = request.getParameter("from");
String to = request.getParameter("to");

try { 
    // Check for parameters, if passed filter address book 
    if(from != null && to != null) { 
      // Filter address book by initial 
      addressBook = addressBook.subMap(from,to);
    } 

   // Prepare the convert addressBook Map to JSON array 
   // Array used to place numerous address entries 
   JSONArray jsonAddressBook = new JSONArray();
 
   // Iterate over filtered addressBook entries 
   for (Iterator iter = addressBook.entrySet().iterator(); iter.hasNext();)  { 

     // Get entry for current iteration        
     Map.Entry entry = (Map.Entry)iter.next();
     String key = (String)entry.getKey();
     Address addressValue = (Address)entry.getValue();

     // Place entry with key value assigned to "name" 
     JSONObject jsonResult = new JSONObject();
     jsonResult.put("name",key);

     // Get and create address structure corresponding to each key 
     // appending address entry in JSON format to result 
     String streetText = addressValue.getStreet();
     String cityText = addressValue.getCity();
     int zipText = addressValue.getZip();
     JSONObject jsonAddress = new JSONObject();
     jsonAddress.append("street",streetText);
     jsonAddress.append("city",cityText);
     jsonAddress.append("zip",zipText);
     jsonResult.put("address",jsonAddress);

     // Get and create telephone structure corresponding to each key 
     // appending telephone entries in JSON format to result 
     String telText = addressValue.getTel();
     String telTwoText = addressValue.getTelTwo();
     JSONArray jsonTelephones = new JSONArray();
     jsonTelephones.put(telText);
     jsonTelephones.put(telTwoText);
     jsonResult.put("phoneNumbers",jsonTelephones);


     // Place JSON address entry in global jsonAddressBook 
     jsonAddressBook.put(jsonResult);
   } // end loop over address book 

     // Assign JSON address book to result String  
     result = new JSONObject().put("addressbook",jsonAddressBook).toString();

  } catch (Exception e) { 
     // Error occurred      
  }

  // Return JSON string 
%> 

<%= result %> 

<%!
// POJO for creating address entry
// Following code placed in JSP for simplicity, not design  

public class Address { 
  private String street; 
  private String city; 
  private int zip;  
  private String tel;  
  private String telTwo;  

  public Address() { 
  }

  public Address(String street,String city,int zip,String tel,String telTwo) { 
   this.street = street;
   this.city = city; 
   this.zip = zip;
   this.tel = tel;
   this.telTwo = telTwo;
  }
  
  public void setStreet(String street) {   
     this.street = street;
  }

  public String getStreet() {   
     return street;
  }

  public void setCity(String city) {   
     this.street = street;
  }

  public String getCity() {   
     return city;
  }

  public void setZip(int zip) {   
     this.zip = zip;
  }

  public int getZip() {   
     return zip;
  }

  public void setTel(String tel) {   
     this.tel = tel;
  }

  public String getTel() {   
     return tel;
  }

  public void setTelTwo(String telTwo) {   
     this.telTwo = telTwo;
  }

  public String getTelTwo() {   
     return telTwo;
  }

}
%>
