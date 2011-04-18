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
 * クラス全体に対応する生成ソースの表現.
 * 
 * @author vvakame
 */
public class GeneratingModel {
	String packageName;

	String className;

	List<MethodModel> methods = new ArrayList<MethodModel>();

	/**
	 * @return the packageName
	 * @category accessor
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName
	 *            the packageName to set
	 * @category accessor
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return the className
	 * @category accessor
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 * @category accessor
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the methods
	 * @category accessor
	 */
	public List<MethodModel> getMethods() {
		return methods;
	}

	/**
	 * @param methods
	 *            the methods to set
	 * @category accessor
	 */
	public void setMethods(List<MethodModel> methods) {
		this.methods = methods;
	}
}
