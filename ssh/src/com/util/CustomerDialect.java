package com.util;

import java.sql.Types;

import org.hibernate.dialect.MySQL5Dialect;

public class CustomerDialect extends MySQL5Dialect {
	public CustomerDialect() {
		super();
		registerHibernateType(Types.LONGVARCHAR, 65535, "text");
	}
}