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

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ecore.EcoreEvaluationEnvironment;

import com.bluexml.side.Util.ecore.EClassUtils;

class KerblueEvaluationEnvironment extends EcoreEvaluationEnvironment {

	public KerblueEvaluationEnvironment() {
		super();
	}

	public KerblueEvaluationEnvironment(EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> parent) {
		super(parent);
	}

	/**
	 * Overides OCL enviroment evaluation method We overide this method because
	 * the operation return is always the oepration from the top superType of
	 * the context from wich is called the operation In this method, we will
	 * find the nearest corresponding method from the call context and forward
	 * this this operation to the former method
	 * 
	 * @param operation
	 *            the operation to resolve
	 * @param receiver
	 *            the call context
	 */
	@Override
	protected Method getJavaMethodFor(EOperation operation, Object receiver) {
		Method result = null;
		EOperation resolved = null;
		EObject eo = (EObject) receiver;
		if (eo != null) {

			EClass eoClass = eo.eClass();
			resolved = EClassUtils.getEOperationFor(operation, eoClass);
		}
		if (resolved == null) {
			result = super.getJavaMethodFor(operation, receiver);
		} else {
			result = super.getJavaMethodFor(resolved, receiver);
		}
		return result;
	}

	public Object callOperation(EOperation operation, int opcode, Object source, Object[] args) {
		if (operation.getEAnnotation(KerblueEnvironment.KERBLUEENV) == null) {
			// not our custom regex operation
//			System.out.println("KerblueEvaluationEnvironment.callOperation() :");
//			System.out.println("\top :" + operation);
//			System.out.println("\topcode :" + opcode);
//			System.out.println("\tsource :" + source);
//			System.out.println("\targs :" + args);
			return super.callOperation(operation, opcode, source, args);
		}

		if (KerblueEnvironment.OP_REGEXMATCH.equals(operation.getName())) {
			Pattern pattern = Pattern.compile((String) args[0]);
			//Pattern pattern = Pattern.compile("\\w*");
			Matcher matcher = pattern.matcher((String) source);
			//Matcher matcher = pattern.matcher("Start");

			return matcher.matches();
		}

		if (KerblueEnvironment.OP_GETCONTENER.equals(operation.getName())) {
			if (source instanceof EObject) {
				return (EObject) ((EObject) source).eContainer();
			}
		}

		throw new UnsupportedOperationException(); // unknown operation
	}
}
