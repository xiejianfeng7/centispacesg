package cn.nci.hutool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.nci.main.Main;

import java.util.Date;

/**
 * @program: centispacesg
 * @description: Convert类可以说是一个工具方法类，里面封装了针对Java常见类型的转换，用于简化类型转换。Convert类中大部分方法为toXXX，参数为Object，可以实现将任意可能的类型转换为指定类型。同时支持第二个参数defaultValue用于在转换失败时返回一个默认值。
 * @author: xiejianfeng
 * @create: 2020-08-11 16:44
 */
public class ConvertTest {
    public static void main(String[] args) {
        Date date = Convert.toDate("2020-07-19");
        Date date1 = Convert.toDate("2020-08-11");
        long between = DateUtil.between(date1, date, DateUnit.DAY);

        String s = Convert.numberToChinese(1122554, true);
        Main.logger.info(s);
    }
}
