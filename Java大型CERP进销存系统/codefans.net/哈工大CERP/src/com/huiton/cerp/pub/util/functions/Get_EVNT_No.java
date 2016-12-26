package com.huiton.cerp.pub.util.functions;

class Get_EVNT_No extends GetNo
{
	synchronized int getNo(String company_code,String no_type)
	{
		return m_getNo(company_code,no_type);
	}
}