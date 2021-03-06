/**
 * Licensed to the Apache Software Foundation (ASF) under one 
 * or more contributor license agreements. See the NOTICE file 
 * distributed with this work for additional information 
 * regarding copyright ownership. The ASF licenses this file 
 * to you under the Apache License, Version 2.0 (the 
 * "License"); you may not use this file except in compliance 
 * with the License. You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY 
 * KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations 
 * under the License. 
 * 
 * @Title: AbstractQueryFilter.java 
 * @Package sql.dhibernate.support.query.impl 
 * @Description: TODO
 * @author minjdai 
 * @date 2013-11-25
 * @version V1.0 
 */
package sql.dhibernate.support.query.impl;

import sql.dhibernate.support.ColumnAttribute;
import sql.dhibernate.support.SQLDialectUtils;
import sql.dhibernate.support.query.QueryFilter;
import sql.dhibernate.support.query.Relativization;
import sql.lang.reflect.ReflectEngine;
import utilities.Checker;

/**
 * @author minjdai
 * 
 */
public abstract class AbstractQueryFilter<E extends QueryFilter> implements
		QueryFilter, Relativization {

	private Class<?> filterClass;
	private String filterName;
	protected QueryFilter appenedFilter;

	/**
	 * @return the queryFilters
	 */
	public Class<?> getFilterClass() {
		return this.filterClass;
	}

	/*
	 * 
	 * @see
	 * sql.dhibernate.support.query.QueryFilter#append(sql.dhibernate.support
	 * .query.QueryFilter)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends QueryFilter> T append(QueryFilter filter) {
		if (this.appenedFilter == null) {
			this.appenedFilter = filter;
		} else {
			this.appenedFilter.append(filter);
		}

		return (T) this;
	}

	/**
	 * @return the filterName
	 */
	protected String getFilterName() {
		return filterName;
	}

	@SuppressWarnings("unchecked")
	public E filter(String attributeName) {
		this.filterName = attributeName;
		return (E) this;
	}

	/**
	 * @return the appenedFilter
	 */
	@Override
	public QueryFilter next() {
		return appenedFilter;
	}

	@Override
	public boolean hasNext() {
		return next() == null ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sql.dhibernate.support.query.QueryFilter#filterClass(java.lang.Class)
	 */
	QueryFilter filterClass(Class<?> clz) {
		this.filterClass = clz;
		return this;
	}

	protected StringBuffer getSQLPart() {
		String filterName = getFilterName();
		Class<?> filterClass = getFilterClass();
		Checker.require(filterName).notNull();
		Checker.require(filterClass).notNull();
		ColumnAttribute columnAttribute = ReflectEngine.parseEntityClass(
				filterClass).findByAttribute(filterName);
		StringBuffer buffer = new StringBuffer();
		String wrappedColumnName = SQLDialectUtils
				.wrapColumnName(columnAttribute.getColumnName());
		buffer.append(wrappedColumnName);
		return buffer;
	}

}
