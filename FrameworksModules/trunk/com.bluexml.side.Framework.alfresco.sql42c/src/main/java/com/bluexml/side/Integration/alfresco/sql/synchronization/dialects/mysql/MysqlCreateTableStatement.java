package com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.mysql;

import com.bluexml.side.Integration.alfresco.sql.synchronization.dialects.CreateTableStatement;

public class MysqlCreateTableStatement extends CreateTableStatement {

	public static class Builder extends CreateTableStatement.Builder {

		public Builder(String tableName_) {
			super(tableName_);
		}
		
		@Override
		public CreateTableStatement build() {
			return new MysqlCreateTableStatement(this);
		}
	}
	
	private MysqlCreateTableStatement(Builder builder_) {
		super(builder_);
	}
	
	@Override
	public String toSQLString() {
		return super.toSQLString() + " TYPE = InnoDB";
	}
}
