package com.wcp.gdufo2o.dao.split;

import java.util.Locale;
import java.util.Properties;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
	@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,RowBounds.class,ResultHandler.class})
	})
public class DynamicDataSourceIntercepter implements Interceptor{
	private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceIntercepter.class);
	private static final String REGEX = ".*insert\\u0020.*.*|.*delete\\u0020.*|.*update\\u0020";

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		boolean synchronizationActive =  TransactionSynchronizationManager.isActualTransactionActive();
		Object[] objects = invocation.getArgs();
		MappedStatement ms = (MappedStatement) objects[0];
		String lookupKey = DynamicDataSourceHolder.DB_MASTER;
		if(synchronizationActive != true) {
			// 读方法
			if(ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
				// selectKey为自增查询主键(SELECT LAST_INSERT_ID())方法,使用主库
				if(ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
					lookupKey = DynamicDataSourceHolder.DB_MASTER;
				}else {
					BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
					String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]","");
					if(sql.matches(REGEX)) {
						// 增删改使用主库
						lookupKey = DynamicDataSourceHolder.DB_MASTER;
					}else {
						// 查使用从库
						lookupKey = DynamicDataSourceHolder.DB_SLAVE;
					}
				}
			}
		}else{
			// 事务使用主库
			lookupKey = DynamicDataSourceHolder.DB_MASTER;
		}
		logger.debug("设置方法[{}] use [{}] Stragegy, SqlCommanType[{}]...", ms.getId(), lookupKey,
				ms.getSqlCommandType().name());
		DynamicDataSourceHolder.setDbType(lookupKey);
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// Executor 增删改查一系列动作
		if(target instanceof Executor) {
			return Plugin.wrap(target, this);
		}
		return null;
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
		
	}

}
