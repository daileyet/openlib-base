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
 * @Title: TestSession.java 
 * @Package sql.dhibernate.support.test 
 * @Description: TODO
 * @author minjdai 
 * @date 2013-12-2
 * @version V1.0 
 */
package sql.dhibernate.support.test;

import java.sql.Connection;
import java.sql.SQLException;

import sql.dao.BaseDao;
import sql.dao.impl.BaseDaoImpl;
import sql.dhibernate.support.AbstractSession;

/**
 * @author minjdai
 * 
 */
public class TestSession extends AbstractSession {

	/*
	 * (non-Javadoc)
	 * 
	 * @see sql.dhibernate.support.AbstractSession#getBaseDao()
	 */
	@Override
	public BaseDao getBaseDao() {

		return new BaseDaoImpl() {

			@Override
			public void setConn(Connection connection) {

			}

			@Override
			public Connection getConn() throws ClassNotFoundException,
					SQLException {
				return null;
			}

		};
	}

}
