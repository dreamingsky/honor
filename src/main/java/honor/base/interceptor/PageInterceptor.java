package honor.base.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import honor.common.Page;
@Intercepts({@Signature(type =StatementHandler.class, method = "prepare", args ={Connection.class})})
public class PageInterceptor implements Interceptor{
	public final static Logger logger = LoggerFactory.getLogger(PageInterceptor.class);
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static  String dialect = null;
	private static  String pageSqlId = null;
	private static  String defaultDialect = "mysql";
	private static  String defaultPageSqlId = ".*Page$";
	public Object intercept(Invocation invocation) throws Throwable {
		
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();  
	     MetaObject metaStatementHandler = MetaObject.forObject(statementHandler,DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);  
	     // ������������(����Ŀ������ܱ�������������أ��Ӷ��γɶ�δ���ͨ�����������ѭ��  
	     // ���Է������ԭʼ�ĵ�Ŀ����)  
	     while (metaStatementHandler.hasGetter("h")) {  
	         Object object = metaStatementHandler.getValue("h");  
	         metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,DEFAULT_OBJECT_WRAPPER_FACTORY);  
	     }  
	     // �������һ����������Ŀ����  
	     while (metaStatementHandler.hasGetter("target")) {  
	         Object object = metaStatementHandler.getValue("target");  
	         metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,DEFAULT_OBJECT_WRAPPER_FACTORY);  
	     }  
	     Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");  
	     dialect = configuration.getVariables().getProperty("dialect");  
	     if (null == dialect || "".equals(dialect)) {  
	         logger.warn("Property dialect is not setted,use default 'mysql' ");  
	         dialect = defaultDialect;  
	     }  
	     pageSqlId = configuration.getVariables().getProperty("pageSqlId");  
	     if (null == pageSqlId || "".equals(pageSqlId)) {  
	         logger.warn("Property pageSqlId is not setted,use default '.*Page$' ");  
	         pageSqlId = defaultPageSqlId;  
	     }  
	     MappedStatement mappedStatement = (MappedStatement)metaStatementHandler.getValue("delegate.mappedStatement");  
	     // ֻ��д��Ҫ��ҳ��sql��䡣ͨ��MappedStatement��IDƥ�䣬Ĭ����д��Page��β��  
	     //  MappedStatement��sql  
	     if (mappedStatement.getId().matches(pageSqlId)) {  
	         BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");  
	         Object parameterObject = boundSql.getParameterObject();  
	         if (parameterObject == null) {  
	             throw new NullPointerException("parameterObject is null!");  
	         } else {  
	             // ��ҳ������Ϊ��������parameterObject��һ������  
	             Page page = (Page) metaStatementHandler  
	                     .getValue("delegate.boundSql.parameterObject.page");  
	             String sql = boundSql.getSql();  
	             // ��дsql  
	             String pageSql = buildPageSql(sql, page);  
	             metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);  
	             // ���������ҳ�󣬾Ͳ���Ҫmybatis���ڴ��ҳ�ˣ����������������������  
	             metaStatementHandler.setValue("delegate.rowBounds.offset",RowBounds.NO_ROW_OFFSET);  
	             metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);  
	             Connection connection = (Connection) invocation.getArgs()[0];  
	             // �����ҳ���������ҳ����  
	             setPageParameter(sql, connection, mappedStatement, boundSql, page);  
	         }  
	     }  
	     // ��ִ��Ȩ������һ��������  
	     return invocation.proceed();
	}

	public Object plugin(Object target) {
		// ��Ŀ������StatementHandler����ʱ���Ű�װĿ���࣬����ֱ�ӷ���Ŀ�걾��,����Ŀ�걻�����  
	    if (target instanceof StatementHandler) {  
	        return Plugin.wrap(target, this);  
	    } else {  
	        return target;  
	    }
	}

	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	private String buildPageSql(String sql, Page page) {  
	    if (page != null) {  
	        StringBuilder pageSql = new StringBuilder();  
	        if ("mysql".equals(dialect)) {  
	            pageSql = buildPageSqlForMysql(sql, page);  
	        } else if ("oracle".equals(dialect)) {  
	            pageSql = buildPageSqlForOracle(sql, page);  
	        } else {  
	            return sql;  
	        }  
	        return pageSql.toString();  
	    } else {  
	        return sql;  
	    }  
	}
	
	
	public StringBuilder buildPageSqlForMysql(String sql, Page page) {  
	    StringBuilder pageSql = new StringBuilder(100);  
	    String beginrow = String.valueOf((page.getCurrentPage() - 1) * page.getPageSize());  
	    pageSql.append(sql);  
	    pageSql.append(" limit " + beginrow + "," + page.getPageSize());  
	    return pageSql;  
	}
	
	
	public StringBuilder buildPageSqlForOracle(String sql, Page page) {  
	    StringBuilder pageSql = new StringBuilder(100);  
	    String beginrow = String.valueOf((page.getCurrentPage() - 1) * page.getPageSize());  
	    String endrow = String.valueOf(page.getCurrentPage() * page.getPageSize());  
	    pageSql.append("select * from ( select temp.*, rownum row_id from ( ");  
	    pageSql.append(sql);  
	    pageSql.append(" ) temp where rownum <= ").append(endrow);  
	    pageSql.append(") where row_id > ").append(beginrow);  
	    return pageSql;  
	}
	
	
	private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement,BoundSql boundSql, Page page) {  
	    // ��¼�ܼ�¼��  
	    String countSql = "select count(0) from (" + sql + ") as total";  
	    PreparedStatement countStmt = null;  
	    ResultSet rs = null;  
	    try {  
	        countStmt = connection.prepareStatement(countSql);  
	        BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,  
	                boundSql.getParameterMappings(), boundSql.getParameterObject());  
	        setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());  
	        rs = countStmt.executeQuery();  
	        int totalCount = 0;  
	        if (rs.next()) {  
	            totalCount = rs.getInt(1);  
	        }  
	        page.setTotalRows(totalCount);  
	        int totalPage = totalCount / page.getPageSize() + ((totalCount % page.getPageSize() == 0) ? 0 : 1);  
	        page.setTotalPages(totalPage);  
	    } catch (SQLException e) {  
	        logger.error("Ignore this exception", e);  
	    } finally {  
	        try {  
	            rs.close();  
	        } catch (SQLException e) {  
	            logger.error("Ignore this exception", e);  
	        }  
	        try {  
	            countStmt.close();  
	        } catch (SQLException e) {  
	            logger.error("Ignore this exception", e);  
	        }  
	    }  
	}  
	  
	/** 
	 * ��SQL����(?)��ֵ 
	 *  
	 * @param ps 
	 * @param mappedStatement 
	 * @param boundSql 
	 * @param parameterObject 
	 * @throws SQLException 
	 */  
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,  
	        Object parameterObject) throws SQLException {  
	    ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);  
	    parameterHandler.setParameters(ps);  
	}  

}
