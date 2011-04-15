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

/**
 * メソッドの引数に対応する生成ソースの表現.
 * 
 * @author vvakame
 */
public class ParameterModel {
	String type;

	String simpleType;

	String name;

	/**
	 * @return the type
	 * @category accessor
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 * @category accessor
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the simpleType
	 * @category accessor
	 */
	public String getSimpleType() {
		return simpleType;
	}

	/**
	 * @param simpleType
	 *            the simpleType to set
	 * @category accessor
	 */
	public void setSimpleType(String simpleType) {
		this.simpleType = simpleType;
	}

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
}
