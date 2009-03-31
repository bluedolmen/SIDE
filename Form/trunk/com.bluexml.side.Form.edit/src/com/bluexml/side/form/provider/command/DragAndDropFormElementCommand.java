package com.bluexml.side.form.provider.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

import com.bluexml.side.form.Field;
import com.bluexml.side.form.FormClass;
import com.bluexml.side.form.FormElement;
import com.bluexml.side.form.FormGroup;
import com.bluexml.side.form.Reference;
import com.bluexml.side.form.VirtualField;
import com.bluexml.side.form.formFactory;
import com.bluexml.side.form.formPackage;
import com.bluexml.side.form.utils.FieldTransformation;
import com.bluexml.side.form.utils.FormDiagramUtils;
import com.bluexml.side.form.utils.InternalModification;

public class DragAndDropFormElementCommand extends DragAndDropCommand {

	protected FormClass fcTarget;
	protected FormClass fcOrigin;

	public DragAndDropFormElementCommand(EditingDomain domain, Object owner,
			float location, int operations, int operation,
			Collection<?> collection) {
		super(domain, owner, location, operations, operation, collection);
	}

	public boolean validate(Object owner, float location, int operations,
			int operation, Collection<?> collection) {
		if (this.owner != owner) {
			fcTarget = FormDiagramUtils.getParentFormClass((FormElement)owner);
		}
		return super.validate(owner, location, operations, operation,
				collection);
	}

	protected void reset() {
		super.reset();
		fcTarget = null;
		fcOrigin = null;
	}

	public boolean canExecute() {
		InternalModification.dontMoveToDisabled();
		super.canExecute();
		initializeFormClassTarget();
		FormClass previousFc = null;
		logCanExecute("owner", owner);
		logCanExecute("fcTarget", fcTarget);
		logCanExecute("fcOrigin", fcOrigin);
		for (Object o : collection) {
			logCanExecute("o", o);
			// If we move a field we must check :
			if (o instanceof Field){
				if (owner instanceof FormClass) {
					isExecutable &= false;
					logCanExecute("owner instanceof FormClass");
				} else {
					Field f = (Field) o;
					// Is it a move to the same FormClass? Move to same FormClass is
					// authorized
					FormClass fcOrigin = FormDiagramUtils.getParentFormClass(f);
					// To simplify : we don't authorized drag & drop
					// from different formclass
					if (previousFc == null) {
						previousFc = fcOrigin;
					}
					logCanExecute("f", f);
					logCanExecute("previousFc", previousFc);
					logCanExecute("fcOrigin", fcOrigin);
					// Move of virtual field between form class isn't authorized
					if (fcTarget != fcOrigin && (f instanceof VirtualField)){
						isExecutable &= false;
						logCanExecute("fcTarget != fcOrigin && (f instanceof VirtualField)");
					} else {
						// Field already virtualized can't be drag & drop
						if (fcTarget != fcOrigin && FormDiagramUtils.IsVirtualized(f)) {
								isExecutable &= false;
								logCanExecute("fcTarget != fcOrigin && FormDiagramUtils.IsVirtualized(f)");
						} else {
							if (previousFc != fcOrigin) {
								isExecutable &= false;
								logCanExecute("previousFc != fcOrigin");
							} else {
								if (fcOrigin != null && fcTarget != null) {
									logCanExecute("fcOrigin != null && fcTarget != null");
									if (fcTarget == fcOrigin) {
										isExecutable &= true;
										logCanExecute("fcTarget == fcOrigin");
									} else {
										// Does the target formClass have a Reference to the
										// origin formclass?
										if (FormDiagramUtils.haveReferenceTo(fcOrigin,
												(FormGroup) fcTarget)) {
											isExecutable = true;
											logCanExecute("FormDiagramUtils.haveReferenceTo(fcOrigin, (FormGroup) fcTarget)");
										} else {
											isExecutable &= false;
											logCanExecute("NOT FormDiagramUtils.haveReferenceTo(fcOrigin, (FormGroup) fcTarget)");
										}
									}
								} else {
									isExecutable &= false;
									logCanExecute("NOT FormDiagramUtils.haveReferenceTo(fcOrigin, (FormGroup) fcTarget)");
								}
							}
						}
					}
				}
			}
		}
		InternalModification.moveToDisabled();
		return isExecutable;
	}

	private void logCanExecute(Object... logs) {
	/*
		StringBuffer sb = new StringBuffer();
		sb.append("isExecutable :");
		sb.append(isExecutable);
		sb.append(" - ");
		for (Object log : logs) {
			if (log == null) {
				sb.append("null");
			} else {
				sb.append(log);
			}
			sb.append(" ");
		}
		System.out.println(sb.toString());
	*/
	}

	public void execute() {
		// We will check if the drag & drop use a reference with max cardinality equals to -1
		boolean doWork = true;
		if (fcTarget != fcOrigin) {
			List<Reference> listRef = FormDiagramUtils.getReferenceBetween(fcOrigin, fcTarget);
			// We seek if there is a reference with a >1 max cardinality
			for(Reference ref : listRef) {
				if (ref.getMax_bound() == -1) {
					doWork = false;
				}
			}
			// If we have found one, alert user
			if (!doWork) {
				showProblem();
			}
		}
		if (doWork) {
			super.execute();
		}
	}

	protected static void showProblem() {
		int style = 0;
		style |= SWT.OK;
		MessageBox mb = new MessageBox(Display.getCurrent().getActiveShell(),
				style);
		mb.setText("Cardinality must be changed");
		mb.setMessage("You try to drag & drop an attribute using an association with max cardinality set to *. This cardinality must be set to an integer.");
		mb.open();
	}
	
	/**
	 * This attempts to prepare a drop insert operation.
	 */
	protected boolean prepareDropInsert() {
		
		initializeFormClassTarget();
		boolean result = false;
		if (fcTarget != null) { 
			if (collection.size() > 0) {
				fcOrigin = FormDiagramUtils.getParentFormClass((FormElement)collection.toArray()[0]);
				if (fcTarget != fcOrigin) {
					CompoundCommand cc = new CompoundCommand();
					// Special case
					int j = getPos();
					
					// Create new collection of virtual field :
					Collection<Field> newCollection = new ArrayList<Field>();
					for (Object o : collection) {
						if (o instanceof Field) {
							VirtualField vf = formFactory.eINSTANCE
									.createVirtualField();
							FieldTransformation.transform((Field) o, vf);
							vf.setLink((Field)o);
							newCollection.add(vf);
						} else {
							newCollection.add((Field) o);
						}
					}
					
					EObject target = ((EObject) owner).eContainer();
					if (owner instanceof FormClass) {
						j = 0;
						target = (EObject)owner;
					}
					
					cc.append(AddCommand.create(domain, target, formPackage.eINSTANCE
							.getFormGroup_Children(), newCollection, j));
					dropCommand = cc;
				} else {
					result = super.prepareDropInsert();
				}
			}
		}

		return result;
	}

	private int getPos() {
		int i = 0;
		feedback = location < 0.5 ? FEEDBACK_INSERT_BEFORE
				: FEEDBACK_INSERT_AFTER;
		Object parent = getParent(owner);
		if (parent == null) {
			dragCommand = UnexecutableCommand.INSTANCE;
			dropCommand = UnexecutableCommand.INSTANCE;
		} else {
			// Iterate over the children to find the owner.
			//
			Collection<?> children = getChildren(parent);
			for (Object child : children) {
				// When we match the owner, we're done.
				//
				if (child == owner) {
					break;
				}
				++i;
			}

			// If the location indicates after, add one more.
			//
			if (location >= 0.5) {
				++i;
			}
		}
		return i;
	}

	private void initializeFormClassTarget() {
		if (fcTarget == null) {
			fcTarget = FormDiagramUtils.getParentFormClass((FormElement)owner);
		}
	}

}
