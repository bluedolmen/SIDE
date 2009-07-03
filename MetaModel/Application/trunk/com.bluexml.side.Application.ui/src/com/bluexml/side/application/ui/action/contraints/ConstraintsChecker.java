package com.bluexml.side.application.ui.action.contraints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeItem;

import com.bluexml.side.application.ui.action.ApplicationDialog;
import com.bluexml.side.application.ui.action.tree.MustBechecked;
import com.bluexml.side.application.ui.action.tree.TreeElement;
import com.bluexml.side.application.ui.action.tree.TreeNode;

public class ConstraintsChecker {

	public static void applyConstraints(TreeViewer tv, TreeItem item, TreeElement el) {

		if (el instanceof TreeNode) {

			Map<String, List<TreeItem>> map = buildConstraintsMap(el);
			List<TreeItem> mustBeChecked = map.get("mustBeChecked");
			List<TreeItem> mustBeUnchecked = map.get("mustBeUnchecked");

			int apply = buildConfirmationDialog(tv, item, mustBeChecked, mustBeUnchecked);

			if (apply == SWT.YES) {
				// apply options constraints
				for (TreeItem treeItem : mustBeUnchecked) {
					TreeNode tn = (TreeNode) treeItem.getData();
					tn.setChecked(false);
					tv.update(tn, null);
				}
				for (TreeItem treeItem : mustBeChecked) {
					TreeNode tn = (TreeNode) treeItem.getData();
					tn.setChecked(true);
					tv.update(tn, null);
				}
			}
		}
	}

	public static int buildConfirmationDialog(TreeViewer tv, TreeItem item, List<TreeItem> mustBeChecked, List<TreeItem> mustBeUnChecked) {
		if ((mustBeChecked != null && mustBeChecked.size() > 0) || (mustBeUnChecked != null && mustBeUnChecked.size() > 0)) {
			String title = "Options Constraint";
			String message = "";
			String msgmChecked = "";
			if (mustBeChecked != null && mustBeChecked.size() > 0) {
				msgmChecked = "following options must be checked :\n";
				for (TreeItem treeItem : mustBeChecked) {
					msgmChecked += treeItem.getText()+"\n";
				}
			}
			String msgmUnChecked = "";

			if (mustBeUnChecked != null && mustBeUnChecked.size() > 0) {
				msgmUnChecked = "following options must be unchecked :\n";
				for (TreeItem treeItem : mustBeUnChecked) {
					msgmUnChecked += treeItem.getText()+"\n";
				}
			}
			message += msgmChecked + "\n" + msgmUnChecked + "\n";
			message += "Do you want to apply changes ? (if no this options will remain unchecked)";
			return ApplicationDialog.showConfirmation(title, message);
		}
		return SWT.NO;
	}

	private static Map<String, List<TreeItem>> buildConstraintsMap(TreeElement el) {
		Map<String, List<TreeItem>> map = new HashMap<String, List<TreeItem>>();
		TreeNode op = (TreeNode) el;
		List<MustBechecked> tocheck = op.getMustbechecked();
		List<TreeItem> mustBeChecked = new ArrayList<TreeItem>();
		map.put("mustBeChecked", mustBeChecked);
		for (MustBechecked be : tocheck) {
			List<String> ids = be.getOptionsIds();
			for (String id : ids) {
				TreeItem it = el.getRoot().getOptionTreeItemById(id);
				mustBeChecked.add(it);
			}
		}

		List<MustBechecked> toUncheck = op.getMustbeUnchecked();
		List<TreeItem> mustBeUnchecked = new ArrayList<TreeItem>();
		map.put("mustBeUnchecked", mustBeUnchecked);
		for (MustBechecked be : toUncheck) {
			List<String> ids = be.getOptionsIds();
			for (String id : ids) {
				TreeItem it = el.getRoot().getOptionTreeItemById(id);
				mustBeUnchecked.add(it);
			}
		}
		return map;
	}

}
