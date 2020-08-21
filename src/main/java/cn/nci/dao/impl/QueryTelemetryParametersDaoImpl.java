package cn.nci.dao.impl;

import cn.nci.dao.QueryTelemetryParametersDao;
import cn.nci.domain.QueryTelemetryParameters;
import cn.nci.util.JDBCUtilsOpt;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @program: centispacesg
 * @description: 遥测参数入库实现类
 * @author: xiejianfeng
 * @create: 2020-07-24 14:21
 */
public class QueryTelemetryParametersDaoImpl implements QueryTelemetryParametersDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtilsOpt.getDataSource());

    @Override
    public List<QueryTelemetryParameters> findAll(QueryTelemetryParameters q) {
        String sql = "select * from pack_503_100b_s";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<QueryTelemetryParameters>(QueryTelemetryParameters.class));
    }
}
