/**   
 *  Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
* @Title: SwingMouseDoubleClickActionSupport.java 
* @Package binder.action.support.swing 
* @Description: TODO
* @author dailey  
* @date 2012-3-20
* @version V1.0   
*/
package binder.support.swing.action;

import java.awt.event.MouseEvent;

import binder.action.support.ActionSupport;

/**
 * @author dailey
 *
 */
public class SwingMouseDoubleClickActionSupport extends SwingAbstractMouseActionSupport {

	/**
	 * @param actionComponent
	 */
	public SwingMouseDoubleClickActionSupport(Object actionComponent) {
		super(actionComponent);
	}

	/**
	 * @param wrappedActionSupport
	 */
	public SwingMouseDoubleClickActionSupport(ActionSupport wrappedActionSupport) {
		super(wrappedActionSupport);
	}

	/* (non-Javadoc)
	 * @see binder.action.support.swing.SwingAbstractMouseActionSupport#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() != 2)
			return;
		setChanged();
		notifyObservers(e);
	}

}
