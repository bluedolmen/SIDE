/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.bluexml.side.util.metaModel.validate.OCLextension;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCLExpression;

public class OCLEvaluator {
	private static final OCL OCL_ENV = KerblueOCL.newInstance();

	public static <T> EList<T> eval(Object context, String body) throws Exception {
		OCL.Helper helper = OCL_ENV.createOCLHelper();

		helper.setInstanceContext(context);
		OCLExpression queryOCLExpression = helper.createQuery(body);

		Query<EClassifier, ?, ?> query = OCL_ENV.createQuery(queryOCLExpression);
		Collection<T> result = null;
		Object evaluate = query.evaluate(context);
		if (evaluate instanceof Collection<?>) {
			result = (Collection<T>) evaluate;
		} else {
			result = new ArrayList<T>();
			result.add((T) evaluate);
		}

		return new BasicEList.UnmodifiableEList<T>(result.size(), result.toArray());
	}
}
