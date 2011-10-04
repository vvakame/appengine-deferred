/*
 * Copyright 2011 vvakame <vvakame@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.vvakame.appengine.deferred.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * メソッドに対応する生成ソースの表現.
 * 
 * @author vvakame
 */
public class MethodModel {

	String name;

	List<ParameterModel> params = new ArrayList<ParameterModel>();

	String returnType;

	List<String> throwsTypes = new ArrayList<String>();

	String extendsClass = "";

	String queueName;

	/**
	 * @return the name
	 * @category accessor
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 * @category accessor
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the params
	 * @category accessor
	 */
	public List<ParameterModel> getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 * @category accessor
	 */
	public void setParams(List<ParameterModel> params) {
		this.params = params;
	}

	/**
	 * @return the returnType
	 * @category accessor
	 */
	public String getReturnType() {
		return returnType;
	}

	/**
	 * @param returnType
	 *            the returnType to set
	 * @category accessor
	 */
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	/**
	 * @return the throwsTypes
	 * @category accessor
	 */
	public List<String> getThrowsTypes() {
		return throwsTypes;
	}

	/**
	 * @param throwsTypes
	 *            the throwsTypes to set
	 * @category accessor
	 */
	public void setThrowsTypes(List<String> throwsTypes) {
		this.throwsTypes = throwsTypes;
	}

	/**
	 * @return the extendsClass
	 * @category accessor
	 */
	public String getExtendsClass() {
		return extendsClass;
	}

	/**
	 * @param extendsClass
	 *            the extendsClass to set
	 * @category accessor
	 */
	public void setExtendsClass(String extendsClass) {
		this.extendsClass = extendsClass;
	}

	/**
	 * @return the queueName
	 * @category accessor
	 */
	public String getQueueName() {
		return queueName;
	}

	/**
	 * @param queueName
	 *            the queueName to set
	 * @category accessor
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
}
