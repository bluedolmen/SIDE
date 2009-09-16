/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package com.bluexml.side.Requirements.modeler.processDiagram.commands;

import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gef.EditDomain;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.utils.Utils;

import com.bluexml.side.requirements.Goal;
import com.bluexml.side.requirements.GoalStep;
import com.bluexml.side.requirements.Process;
import com.bluexml.side.requirements.RequirementsPackage;

/**
 * GoalStep edge creation command
 *
 * @generated
 */
public class GoalStepEdgeCreationCommand extends CreateTypedEdgeCommand {

	private CompoundCommand ccmd;
	private MixedEditDomain medomain;
	private EObject elementToDelete;
	private EObject replacingElement;

	/**
	 * @param domain the edit domain
	 * @param newObj the graph edge of the new connection
	 * @param src the graph element of the source
	 * @generated
	 */
	public GoalStepEdgeCreationCommand(EditDomain domain, GraphEdge newObj,
			GraphElement src) {
		this(domain, newObj, src, true);
	}

	/**
	 * @param domain the edit domain
	 * @param newObj the graph edge of the new connection
	 * @param src the graph element of the source
	 * @param needModelUpdate set it to true if the model need to be updated
	 * @_generated
	 */
	public GoalStepEdgeCreationCommand(EditDomain domain, GraphEdge newObj,
			GraphElement src, boolean needModelUpdate) {
		super(domain, newObj, src, needModelUpdate);

		if (needModelUpdate) {
			ccmd = new CompoundCommand();

			EObject edgeObj = Utils.getElement(newObj);
			if (edgeObj instanceof GoalStep) {
				GoalStep gStep = (GoalStep) edgeObj;
				if (gStep.getProcess() == null) {

					GraphElement ge = src.getContainer();
					EObject diagramObj = Utils.getElement(ge);
					if (diagramObj instanceof Process) {
						medomain = (MixedEditDomain) domain;
						Process p = (Process) diagramObj;

						//Set the process in function of diagram
						Command cmd = new SetCommand(medomain
								.getEMFEditingDomain(), gStep,
								RequirementsPackage.eINSTANCE
										.getGoalStep_Process(), p);
						ccmd.append(cmd);
					}

				}
			}
		}
	}

	/**
	 * @generated
	 */
	protected void redoModel() {
		//TODO add specific code if super method is not sufficient
		super.redoModel();
	}

	/**
	 * @generated
	 */
	protected void undoModel() {
		//TODO add specific code if super method is not sufficient
		super.undoModel();
	}

	@Override
	public void setTarget(GraphElement target) {
		super.setTarget(target);

		if (isUpdateModel()) {
			GraphElement src = getSource();
			GoalStep gStep = (GoalStep) Utils.getElement(getEdge());
			elementToDelete = null;

			GraphElement ge = src.getContainer();
			EObject diagramObj = Utils.getElement(ge);
			if (diagramObj instanceof Process) {
				Process p = (Process) diagramObj;

				//Check if the source eObject has already a goalStep linked to this process
				EObject goalObj = Utils.getElement(src);
				if (goalObj instanceof Goal) {
					Goal goal = (Goal) goalObj;
					GoalStep searchedGoalStep = null;
					for (GoalStep gs : goal.getStep()) {
						if (gs.getProcess().equals(p))
							searchedGoalStep = gs;
					}

					Command cmd;
					if (searchedGoalStep != null) {
						cmd = new AddCommand(medomain.getEMFEditingDomain(),
								searchedGoalStep.getNextGoals(), Utils
										.getElement(target));
						ccmd.append(cmd);

						elementToDelete = gStep;
						replacingElement = searchedGoalStep;
					}
				}
			}
		}
	}

	@Override
	public void execute() {
		super.execute();
		if (elementToDelete != null
				&& Utils.getElement(getEdge()).equals(elementToDelete)) {
			if (replacingElement != null)
				((EMFSemanticModelBridge) getEdge().getSemanticModel())
						.setElement(replacingElement);
		}

		if (isUpdateModel()) {
			if (elementToDelete != null) {
				Command cmd = new DeleteCommand(medomain.getEMFEditingDomain(),
						Collections.singleton(elementToDelete));
				if (ccmd != null)
					ccmd.append(cmd);
			}

			if (ccmd != null)
				medomain.getEMFEditingDomain().getCommandStack().execute(ccmd);
		}
	}

}