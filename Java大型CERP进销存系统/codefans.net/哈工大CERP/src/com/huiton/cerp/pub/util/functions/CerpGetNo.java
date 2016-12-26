package com.huiton.cerp.pub.util.functions;

public class CerpGetNo
{
	public static int getNo(String company_code,String table)
	{
		if (company_code==null || company_code.length()<1
			|| table==null || table.length()<1)
			return -1;


		TypeAndClass tac = (new SwitchTable()).getType(table);
		if (tac==null)
			return -1;

		String type_name = tac.m_type_name;
		String class_name = tac.m_class_name;
		String pkg_path = "com.huiton.cerp.pub.util.functions.";

		try
		{
			GetNo ob = (GetNo) (Class.forName(pkg_path+class_name).newInstance());
			return ob.getNo(company_code,type_name);
		}catch(Exception e)
		{
			return -1;
		}
	}
}

class TypeAndClass
{
	String m_type_name = null;
	String m_class_name = null;
	TypeAndClass(String type_name,String class_name)
	{
		m_type_name = type_name;
		m_class_name = class_name;
	}
}

class SwitchTable
{
	private String [] table_name = {"sam_user",
				"sam_access_log",
				"sam_prog_log",
				"sam_drop_user_log",
				"sam_msg_log",
				"sam_user_event",
				"sam_report_apply",
				"sam_report_log",
				"sam_schedule"
			};

	private TypeAndClass [] type_and_class =
			{
				new TypeAndClass("USER","Get_USER_No"),
				new TypeAndClass("ACSL","Get_ACSL_No"),
				new TypeAndClass("PRGL","Get_PRGL_No"),
				new TypeAndClass("DRPL","Get_DRPL_No"),
				new TypeAndClass("MSGL","Get_MSGL_No"),
				new TypeAndClass("EVNT","Get_EVNT_No"),
				new TypeAndClass("RPTA","Get_RPTA_No"),
				new TypeAndClass("RPTL","Get_RPTL_No"),
				new TypeAndClass("SCHD","Get_SCHD_No")
			};

	SwitchTable()
	{

	}

	private boolean len_check()
	{
		return (table_name.length==type_and_class.length);
	}

	public TypeAndClass getType(String table)
	{
		if (!len_check()) return null;

		int m_len = table_name.length;
		for (int i=0;i<m_len;i++)
		{
			if (table_name[i].equalsIgnoreCase(table))
			{
				return type_and_class[i];
			}
		}
		return null;
	}
}
