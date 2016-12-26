//实现Student的本地Home接口
package enroll.ejb;
//download by http://www.codefans.net
import java.util.Collection;
import java.rmi.RemoteException;
import javax.ejb.*;

public interface StudentHome extends EJBHome{
   
  public Student create(
    String student_id, String name) 
    throws DuplicateKeyException, 
      CreateException, RemoteException;
             
  public Student findByPrimaryKey(String student_id)
    throws ObjectNotFoundException, 
      FinderException, RemoteException;
    
  public Collection findAll() 
    throws FinderException, RemoteException;
}	
