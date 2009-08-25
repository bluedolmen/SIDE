package com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.mysql;

import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.CreateTableStatement;
import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.DefaultDialect;

public class MysqlDialect extends DefaultDialect {

	@Override
	public CreateTableStatement.Builder newCreateTableStatementBuilder(String tableName_) {
		return new MysqlCreateTableStatement.Builder(tableName_);
	}

}
