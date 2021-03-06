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
 * @Title: IMessageContext.java 
 * @Package i18n 
 * @Description: TODO
 * @author dailey_dai 
 * @date Apr 16, 2012
 * @version V1.0 
 */
package i18n;

/**
 * Context for {@link I18n}.
 * @author dailey_dai
 *
 */
public interface IMessageContext {
	/**
	 * get message pack manager
	 * @return MessagePackManager
	 */
	public MessagePackManager getPackManager();
	
	/**
	 * get message pack by message type
	 * @param messageType IMessageType
	 * @return IMessagePack
	 */
	public IMessagePack getMessagePack(IMessageType messageType);
}
