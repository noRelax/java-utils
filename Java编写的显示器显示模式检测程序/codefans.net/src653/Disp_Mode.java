/***********************************************
   ┏━━━━━━━━━━━━━━━━━━━━━┓
   ┃             源 码 爱 好 者               ┃
   ┣━━━━━━━━━━━━━━━━━━━━━┫
   ┃                                          ┃
   ┃           提供源码发布与下载             ┃
   ┃                                          ┃
   ┃        http://www.codefans.net           ┃
   ┃                                          ┃
   ┃            互助、分享、提高              ┃
   ┗━━━━━━━━━━━━━━━━━━━━━┛
************************************************/

	import java.awt.*;
	import java.util.*;

	public class Disp_Mode
	{
		int i,j,k,m,tmpi;
		boolean tfl;
		String tstr,tsob[];
		GraphicsDevice GDev;
		DisplayMode orgDm,Dms[];
		Vector res,tmp,bits[],rrates[];
		int wid[],hei[];
		
		public Disp_Mode(GraphicsDevice dev)
		{
			GDev = dev;
			orgDm = GDev.getDisplayMode();
			Dms= GDev.getDisplayModes();
			tmp = new Vector();	
			res = new Vector();								
			wid = new int[Dms.length];
			hei = new int[Dms.length];
		}
		
		public void addMode()
		{			
			for(i=0;i<Dms.length;i++)
			{				
				wid[i] = Dms[i].getWidth();
				hei[i] = Dms[i].getHeight();
								
			}				
			for(i=0;i<hei.length;i++)
			{
				for(j=1;j<hei.length-i;j++)
				{
					if(hei[j-1]<=hei[j])
					{
						tmpi = hei[j-1];
						hei[j-1] = hei[j];
						hei[j] = tmpi;
						tmpi = wid[j-1];
						wid[j-1] = wid[j];
						wid[j] = tmpi;
					}
				}
			}	
			
			for(i=0;i<wid.length;i++)
			{
				for(j=1;j<wid.length-i;j++)
				{
					if(wid[j-1]>=wid[j])
					{
						tmpi = wid[j-1];
						wid[j-1] = wid[j];
						wid[j] = tmpi;
						tmpi = hei[j-1];
						hei[j-1] = hei[j];
						hei[j] = tmpi;
					}
				}
			}
			
			for(i=0;i<wid.length;i++)
			{
				tstr = wid[i]+" x "+hei[i];				
				tmp.add(tstr);
			}							

			res.add(tmp.get(0));
			for(i=1;i<tmp.size();i++)
			{
				tfl = true;
				for(j=0;j<res.size()&&tfl;j++)
				{
					tstr = tmp.get(i).toString();
					if(tstr.equals(res.get(j).toString()))
					{
						tfl = false;
					}					
				}
				if(tfl)
				{
					res.add(tmp.get(i));
				}
			}						
						
			bits = new Vector[res.size()];			
			for(i=0;i<res.size();i++)
			{
				bits[i] = new Vector();
			}		
			for(i=0;i<res.size();i++)
			{			
				tfl=true;	
				for(j=0;j<Dms.length&&tfl;j++)
				{
					tstr = Dms[j].getWidth()+" x "+Dms[j].getHeight();
					if(res.get(i).toString().equals(tstr))
					{						
						bits[i].add(Integer.toString(Dms[j].getBitDepth()));																														
					}					
				}				
			}				
				
			for(k=0;k<bits.length;k++)
			{
				tmp.removeAllElements();
				tmp.add(bits[k].get(0));
				for(i=1;i<bits[k].size();i++)
				{
					tfl=true;
					for(j=0;j<tmp.size()&&tfl;j++)
					{
						if(bits[k].get(i).toString().equals(tmp.get(j).toString()))
						{
							tfl = false;
						}
					}
					if(tfl)
					{
						tmp.add(bits[k].get(i));
					}
				}
				bits[k].removeAllElements();
				bits[k].addAll(tmp);			
			}
					
						
			/*for(i=0;i<bits.length;i++)
			{
				for(j=0;j<bits[i].size();j++)
				{
					System.out.println("Bits: "+bits[i].get(j).toString());
				}
			}*/			
			
			rrates = new Vector[res.size()];
			for(i=0;i<res.size();i++)
			{
				rrates[i] = new Vector();
			}		
			for(i=0;i<res.size();i++)
			{					
				for(j=0;j<Dms.length;j++)
				{
					tstr = Dms[j].getWidth()+" x "+Dms[j].getHeight();
					if(res.get(i).toString().equals(tstr))
					{						
						rrates[i].add(Integer.toString(Dms[j].getRefreshRate()));						
					}
					
				}
			}		
			
			for(k=0;k<rrates.length;k++)
			{
				tmp.removeAllElements();
				tmp.add(rrates[k].get(0));
				for(i=1;i<rrates[k].size();i++)
				{
					tfl=true;
					for(j=0;j<tmp.size()&&tfl;j++)
					{
						if(rrates[k].get(i).toString().equals(tmp.get(j).toString()))
						{
							tfl = false;
						}
					}
					if(tfl)
					{
						tmp.add(rrates[k].get(i));						
					}
				}			
				
				rrates[k].removeAllElements();				
				rrates[k].addAll(tmp);			
			}			
		}
		
		public DisplayMode getMode(int resid,int depid,int ratid)
		{
			tfl = true;
			for(i=0;i<Dms.length&&tfl;i++)
			{
				if(res.get(resid).toString().equals(Dms[i].getWidth()+" x "+Dms[i].getHeight())
				   && bits[resid].get(depid).equals(Integer.toString(Dms[i].getBitDepth()))
				   && rrates[resid].get(ratid).equals(Integer.toString(Dms[i].getRefreshRate())))
				{
					break;
				}
			}
			return Dms[i];
						
		}		
	
	}
