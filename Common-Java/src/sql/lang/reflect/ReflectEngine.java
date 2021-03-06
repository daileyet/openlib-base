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
 * @Title: ReflectEngine.java 
 * @Package sql.lang.reflect 
 * @Description: TODO
 * @author dailey 
 * @date 2012-11-5
 * @version V1.0 
 */
package sql.lang.reflect;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import sql.dhibernate.support.ColumnAttributeMapping;
import sql.dhibernate.support.FilterTemplate;
import sql.dhibernate.support.StandardSQLTemplate;
import sql.dhibernate.support.Template;
import sql.dhibernate.support.query.QueryFilter;
import sql.dhibernate.support.query.impl.FilterSQLTemplate;
import sql.entity.Entity;
import sql.entity.jpa.EntityReflectHandler;
import sql.entity.jpa.IReflectHandler;
import sql.entity.jpa.JPAReflectHandler;

/**
 * @author dailey
 * 
 */
public abstract class ReflectEngine {

	private static Map<Class<?>, IReflectHandler> map = new ConcurrentHashMap<Class<?>, IReflectHandler>();
	static {
		register(Entity.class, new EntityReflectHandler());
		register(Object.class, new JPAReflectHandler());
	}

	public static void register(Class<?> entityClass, IReflectHandler hander) {
		map.put(entityClass, hander);
	}

	public static <T> boolean propertyReflect(T entity, String columnName,
			Object columnValue) {
		boolean isSuccess = false;
		for (Entry<Class<?>, IReflectHandler> entry : map.entrySet()) {
			Class<?> clzz = entry.getKey();
			if (clzz.isInstance(entity)) {
				try {
					isSuccess = entry.getValue().handColumnField(entity,
							columnName, columnValue);
				} catch (Exception e) {
					continue;
				}
				if (isSuccess)
					break;
			}
		}
		return isSuccess;
	}

	public static <T> String getEntityTable(Class<T> entityClazz) {
		String tableName = null;
		for (Entry<Class<?>, IReflectHandler> entry : map.entrySet()) {
			Class<?> clzz = entry.getKey();
			if (clzz.isAssignableFrom(entityClazz)) {
				try {
					tableName = entry.getValue()
							.getEntityTableName(entityClazz);
				} catch (Exception e) {
					continue;
				}
				if (tableName != null)
					break;
			}
		}
		return tableName;
	}

	public static <T> String getEntityID(Class<T> entityClazz) {
		String idName = null;
		for (Entry<Class<?>, IReflectHandler> entry : map.entrySet()) {
			Class<?> clzz = entry.getKey();
			if (clzz.isAssignableFrom(entityClazz)) {
				try {
					idName = entry.getValue().getEntityIDName(entityClazz);
				} catch (Exception e) {
					continue;
				}
				if (idName != null)
					break;
			}
		}
		return idName;
	}

	public static <T> ColumnAttributeMapping parseEntityClass(
			Class<T> entityClass) {
		ColumnAttributeMapping columnAttributeMapping = null;
		for (Entry<Class<?>, IReflectHandler> entry : map.entrySet()) {
			Class<?> clzz = entry.getKey();
			if (clzz.isAssignableFrom(entityClass)) {
				columnAttributeMapping = entry.getValue().parseEntityClass(
						entityClass);
			}
		}
		return columnAttributeMapping;
	}

	/**
	 * create template for standard sql by entity class<BR>
	 * 1. JPA annotation on param entityClass<BR>
	 * 2. param entityClass is subclass from {@link Entity}
	 * 
	 * @param entityClass
	 *            Class<T>
	 * @return Template
	 */
	public static <T> Template createSQLTemplate(Class<T> entityClass) {
		Template template = null;
		ColumnAttributeMapping columnAttributeMapping = parseEntityClass(entityClass);
		template = new StandardSQLTemplate(columnAttributeMapping, entityClass);
		return template;
	}

	public static <T> FilterTemplate createSQLTemplate(Class<T> entityClass,
			QueryFilter filter) {
		FilterTemplate template = null;
		ColumnAttributeMapping columnAttributeMapping = parseEntityClass(entityClass);
		template = new FilterSQLTemplate(columnAttributeMapping, entityClass);
		template.setFilter(filter);
		return template;
	}
}
