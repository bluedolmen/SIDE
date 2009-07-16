package com.bluexml.alfresco.modules.sql.utils;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.alfresco.service.cmr.repository.NodeRef;
import org.jdom.Document;
import org.jdom.Element;

public class ResponseBuilder {

	static String ROW_ID = "uuid";

	public static Document buildXMLResponseFromSQL(java.sql.ResultSet rs) throws Exception {
		String query = rs.getStatement().toString();
		Document response = buildXMLDocument(query);

		if (rs == null) {
			return response;
		}
		
		while (rs.next()) {
			Element row = null;
			if (rs.getObject(ROW_ID) != null) {
				row = buildXMLRow(rs.getObject(ROW_ID).toString());
			}
			int dataGridColIndex=0;
			ResultSetMetaData meta = rs.getMetaData();
			for (int column = 1; column <= meta.getColumnCount(); column++) {
				// int type = meta.getColumnType(column);
				// String className = meta.getColumnClassName(column);
				// String name = meta.getTableName(column);
				String columnName = meta.getColumnName(column);
				// do not process row.id (it's already added as row/@id bugfix
				// #205)
				if (!columnName.equals(ROW_ID)) {
					Object data = rs.getObject(column);
					if (data == null) {
						data = "";
					}
//					if ((columnName == null || columnName.equals("")) && def != null) {
//						// may occur for Action and columns build from computation like group_concat() mysql function
//						// so we use DataGrid definition to get the columnName value
//						columnName = def.getHaveCols().get(dataGridColIndex).getName();
//					}
					Element cell = buildXMLCell(columnName, null, data.toString());
					row.addContent(cell);
					dataGridColIndex++;// ROW_ID is not count
				}
			}
			response.getRootElement().addContent(row);
		}

		return response;
	}

	public static ArrayList<NodeRef> buildFreemarckerResponseFromSQL(java.sql.ResultSet rs) throws SQLException {
		ArrayList<NodeRef> records = new ArrayList<NodeRef>();
		while (rs.next()) {
			String uuid = rs.getString("uuid");
			String href = "workspace://" + "SpacesStore" + "/" + uuid;
			NodeRef nr = new NodeRef(href);
			records.add(nr);
		}
		return records;
	}

	public static Document buildXMLDocument(String query) {
		Document doc = new Document();
		Element rootElement = new Element("rows");

		Element userData = new Element("userdata");
		userData.setAttribute("name", "query");
		userData.setText(query);
		rootElement.addContent(userData);
		doc.setRootElement(rootElement);
		return doc;
	}

	public static Element buildXMLRow(String rowId) {
		Element row = new Element("row");
		row.setAttribute("id", rowId);
		return row;
	}

	public static Element buildXMLCell() {
		Element cell = new Element("cell");
		return cell;
	}

	public static Element buildXMLCell(String columnId, String columnType, String value) {
		Element cell = new Element("cell");
		if (columnId != null && !columnId.equals("")) {
			cell.setAttribute("id", columnId);
		}
		if (columnType != null && !columnType.equals("")) {
			cell.setAttribute("type", columnType);
		}
		if (value != null && !value.equals("")) {
			cell.setText(value);
		}
		return cell;
	}


}
