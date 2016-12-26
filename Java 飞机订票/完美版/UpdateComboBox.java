package flight.assist;

import flight.manage.*;
import flight.assist.*;
import flight.query.*;

import java.util.*;
import java.sql.*; 

public class UpdateComboBox extends Thread
{
	private SqlBean sqlBean = new SqlBean();
	private HashSet flight = new HashSet();
	private HashSet airFirm = new HashSet();
	private HashSet place = new HashSet();
	public final static int INSERT_INFO = 1;
	public final static int DELETE_INFO = 2;
	
	public UpdateComboBox()
	{
	   
	       //Start the thread
	    this.start();
	}
	
	public void run()
	{
		try
		{
			while (true)
			{
				updateInsert();
				updateDelete();
				
				   //Stop the thread every 2 seconds
				this.sleep(2000);		
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}	
	
	public void updateInsert()
	{
		String sqlString = "select flight,airfirm,start from flight";
		String newFlightNum = "";
		String newAirFirm = "";
		String newPlace = "";		
	
		try
		{
			ResultSet rs = sqlBean.executeQuery(sqlString);
			
			while (rs.next())
			{
				newFlightNum = rs.getString("flight");
				newAirFirm = rs.getString("airfirm");
				newPlace = rs.getString("start");
				
				    //Check that if the old ones has the new ones
				if ( !flight.contains(newFlightNum))
                {
                	flight.add(newFlightNum);
                	updateFlightComboBox(newFlightNum,this.INSERT_INFO);
                }					
				   
				   
				if ( !airFirm.contains(newAirFirm))
				{
					airFirm.add(newAirFirm);
					updateAirFirmComboBox(newAirFirm,this.INSERT_INFO);
				}
				   
				
				if ( !place.contains(newPlace))	
				{
					place.add(newPlace);
					updatePlaceComboBox(newPlace,this.INSERT_INFO);	
				}					   				
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	public void updateDelete()
	{
		try
		{
		    String sqlString = "select flight,airfirm,start from flight";
		    ResultSet rs = sqlBean.executeQuery(sqlString);
		    
		    HashSet fli = new HashSet();
	        HashSet firm = new HashSet();
	        HashSet pla = new HashSet();
		    
		    while (rs.next())
		    {	    
		    	fli.add(rs.getString("flight"));
		    	
		    	firm.add(rs.getString("airfirm"));
		    	
		    	pla.add(rs.getString("start")); 
		    }								
		    
		    Iterator flightIt = flight.iterator(); 
		    Iterator airFirmIt = airFirm.iterator();
		    Iterator placeIt = place.iterator();
		    		   
		       //Check that which flightNum has been delete
		    String flightNum = "";		   
		    while (flightIt.hasNext())
		    {
		       flightNum = (String)flightIt.next();	
		       if ( !fli.contains(flightNum))
		       {		           	       	  
		       	   updateFlightComboBox(flightNum,this.DELETE_INFO);	
		       }		          
		    }
		    flight = (HashSet)fli.clone();
		    
		       //Check that which airFirm has been delete
		    String air = "";
		    while (airFirmIt.hasNext())
		    {
		       air = (String)airFirmIt.next();	
		       if ( !firm.contains(air))
		       {		       	   
		       	   updateAirFirmComboBox(air,this.DELETE_INFO);	
		       }		          
		    }
		    airFirm = (HashSet)firm.clone();
		    
		       //Check that which city has been delete
		    String location = "";
		    while (placeIt.hasNext())
		    {
		       location = (String)placeIt.next();	
		       if ( !pla.contains(location))
		       {		       	  
		       	   updatePlaceComboBox(location,this.DELETE_INFO);
		       }		          	
		    }
		    place = (HashSet)pla.clone();
		    		    	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	    //Update the flght combobox in the other panels
	private void updateFlightComboBox(String newFlightNum,int insertOrDelete )
	{
		CommonQuery.updateFlightComboBox(newFlightNum,insertOrDelete);
		
		DeletePanel.updateFlightComboBox(newFlightNum,insertOrDelete);	
	}
	
	   //Update the airfirm combobox in the other panels
	private void updateAirFirmComboBox(String newAirFirm,int insertOrDelete)
	{
		CommonQuery.updateAirFirmComboBox(newAirFirm,insertOrDelete);		
	
		ComprehenQuery.updateAirFirmComboBox(newAirFirm,insertOrDelete);		
	}
	
	   //Update the city combobox in the other panels
	private void updatePlaceComboBox(String newPlace,int insertOrDelete)
	{
		CommonQuery.updatePlaceComboBox(newPlace,insertOrDelete);

		ComprehenQuery.updatePlaceComboBox(newPlace,insertOrDelete);
		
		DeletePanel.updateCityComboBox(newPlace,insertOrDelete);
	}
}