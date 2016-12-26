package lgcsgwxt;

import java.util.Vector;

public interface LgcsInterface {
    public Vector select_merch_Name(String merch_Name);//按商品名称查询，返回一个二维数组
    public Vector select_merch_number(String merch_number);//按商品编号查询，返回一个二维数组
    public Vector select_merch_supply(String merch_supply);//按商品名供应商查询，返回一个二维数组
    public Vector select_merch_producing(String merch_producing);//按商品产地查询，返回一个二维数组
    public Vector select_sort_producing(String merch_sort);//按商品类别查询，返回一个二维数组
    //登录的验证方法,返回一个boolen值
    //入库保存到入库表,返回一个int值
    //出库保存到出库表,返回一个int值
    //退货保存到退货表,返回一个int值
    //获取当前时间的方法，格式2007-09-01 08:05:01
    //查询所有会员信息的方法,返回一个二维数组的结果集
    //根据用户名 查询单个会员信息的方法,返回一个二维数组的结果集
    //修改会员信息的方法,返回一个int值
    //注册会员的方法,,返回一个int值
    //当月收入计算方法，返回一个二维数组的结果集，含商品种类，当月销售金额，总金额
    //每次销售需更新销售表,并更新库存表
}
