package com.bluexml.side.portal.alfresco.reverse.reverser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.bluexml.side.portal.Column;
import com.bluexml.side.portal.Portal;
import com.bluexml.side.portal.PortalFactory;
import com.bluexml.side.portal.PortalLayout;
import com.bluexml.side.portal.alfresco.reverse.reverser.data.Region;
import com.bluexml.side.portal.helper.PortalHelper;

public class LayoutReverser {

	PortalLayout extractedLayout = null;
	List<?> readLines = null;
	Map<Column, Object> child_parent = new HashMap<Column, Object>();

	// workging context
	String currentLine = null;
	Column currentCol = null;
	int currentChildIndex = 0;
	int lastOpenedLineIndex = 1;
	int lastClosedLineIndex = 1;
	int currentLineIndex = -1;
	int level = 0;
	Portal p;
	File pageFile = null;
	int generatedId = 0;

	List<Region> regions = new ArrayList<Region>();

	String regexpAttributes = "(([^ =]*)=\"([^\"]*)\")";
	FreemarkerTags currentOpenedTag = null;

	public List<Region> getRegions() {
		return Collections.unmodifiableList(regions);
	}

	public LayoutReverser(File page, Portal p, String layoutName) throws IOException {
		this.pageFile = page;
		this.readLines = FileUtils.readLines(page);
		this.p = p;
		extractedLayout = PortalFactory.eINSTANCE.createPortalLayout();
		extractedLayout.setName(layoutName);
		p.getLayoutSet().add(extractedLayout);
	}

	public PortalLayout parse() throws Exception {
		System.out.println("LayoutReverser.parse() parse file :" + pageFile);
		boolean previousIsRaw = false;
		for (Object object : readLines) {
			currentLineIndex++;

			currentLine = (String) object;
			//			System.out.println("line :" + currentLine + " (" + currentLineIndex + ")");
			//			System.out.println("level " + level);
			currentLine = currentLine.trim();
			if (!currentLine.isEmpty()) {

				try {
					FreemarkerTags[] values = FreemarkerTags.values();
					boolean handledStartTag = false;
					for (FreemarkerTags freemarkerTags : values) {
						String string = freemarkerTags.start_tag;
						String string2 = freemarkerTags.end_tag;
						if (currentLine.matches(string) || currentLine.matches(string2)) {
							handledStartTag = true;
							break;
						} else {
							System.out.println("LayoutReverser.parse() no matches regexp:" + string + " line :" + currentLine);
							System.out.println("\tregexp2 :" + string2 + " line :" + currentLine);
						}
					}
					if (handledStartTag && previousIsRaw) {
						// close last raw section
						closeRawSection();
						previousIsRaw = false;
					}
					if (currentLine.matches(FreemarkerTags.include.start_tag)) {
						currentOpenedTag = FreemarkerTags.include;
						handle_include();
					} else if (currentLine.matches(FreemarkerTags.templateHeader.start_tag)) {
						currentOpenedTag = FreemarkerTags.templateHeader;
						handle_templateHeader();
					} else if (currentLine.matches(FreemarkerTags.templateBody.start_tag)) {
						currentOpenedTag = FreemarkerTags.templateBody;
						handle_templateBody();
					} else if (currentLine.matches(FreemarkerTags.div.start_tag)) {
						currentOpenedTag = FreemarkerTags.div;
						handle_div();
					} else if (currentLine.matches(FreemarkerTags.region.start_tag)) {
						currentOpenedTag = FreemarkerTags.region;
						handle_region();
					} else if (currentLine.matches(FreemarkerTags.templateFooter.start_tag)) {
						currentOpenedTag = FreemarkerTags.templateFooter;
						handle_templateFooter();
					} else if (currentLine.matches(FreemarkerTags.templateFooter_autoclose.start_tag)) {
						handle_templateFooter();
						closeColumn();
					} else if (currentLine.matches(FreemarkerTags.templateBody_autoclose.start_tag)) {
						handle_templateBody();
						closeColumn();
					} else if (currentLine.matches(FreemarkerTags.templateHeader_autoclose.start_tag)) {
						handle_templateHeader();
						closeColumn();
					} else if (currentLine.matches(FreemarkerTags.include.end_tag) && currentOpenedTag.equals(FreemarkerTags.include)) {
						// auto close
					} else if (currentLine.matches(FreemarkerTags.templateHeader.end_tag) && currentOpenedTag.equals(FreemarkerTags.templateHeader)) {
						handle_templateHeader_end();
					} else if (currentLine.matches(FreemarkerTags.templateBody.end_tag) && currentOpenedTag.equals(FreemarkerTags.templateBody)) {
						handle_templateBody_end();
					} else if (currentLine.matches(FreemarkerTags.div.end_tag) && currentOpenedTag.equals(FreemarkerTags.div)) {
						handle_div_end();
					} else if (currentLine.matches(FreemarkerTags.region.end_tag) && currentOpenedTag.equals(FreemarkerTags.region)) {
						// auto close
					} else if (currentLine.matches(FreemarkerTags.templateFooter.end_tag) && currentOpenedTag.equals(FreemarkerTags.templateFooter)) {
						handle_templateFooter_end();
					} else {
						// no matches so default behaviour using rawContent
						if (!previousIsRaw) {
							previousIsRaw = true;
							Map<String, String> properties = new HashMap<String, String>();
							createColumn("rawContent", properties);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Error on " + pageFile + "\nline :" + currentLine + " (" + currentLineIndex + ")" + "\nlevel " + level);
					throw new Exception("Error on " + pageFile + "\nline :" + currentLine + " (" + currentLineIndex + ")" + "\nlevel " + level, e.getCause());
				}
			}
		}
		return extractedLayout;
	}

	public void closeRawSection() {
		Column c = currentCol;
		closeColumn();
		extractRawContent(c);
	}

	protected void handle_region() {
		System.out.println("LayoutReverser.handle_region()");
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("tag", "");
		int groupId = 2;
		int groupValue = 3;
		Map<String, String> atts = new HashMap<String, String>();
		readAttributes(atts, regexpAttributes, groupId, groupValue);

		String regionId = atts.get("id");
		String scope = atts.get("scope");
		createColumn(regionId, properties);
		Region r = new Region(regionId, scope);
		regions.add(r);
		// auto close
		handle_region_end();
	}

	protected void handle_include() {
		System.out.println("LayoutReverser.handle_include()");
		Map<String, String> properties = new HashMap<String, String>();
		
		createColumn("includes", properties);
		// auto close
		handle_include_end();
	}

	protected void handle_templateHeader() {
		System.out.println("LayoutReverser.handle_templateHeader()");
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("tag", "@templateHeader");
		createColumn("@templateHeader", properties);
	}

	protected void handle_templateBody() {
		System.out.println("LayoutReverser.handle_templateBody()");
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("tag", "@templateBody");
		createColumn("@templateBody", properties);
	}

	protected void handle_div() {
		System.out.println("LayoutReverser.handle_div()");
		Map<String, String> properties = new HashMap<String, String>();
		int groupId = 2;
		int groupValue = 3;
		readAttributes(properties, regexpAttributes, groupId, groupValue);
		String stringId = properties.get("id");
		if (StringUtils.trimToNull(stringId) == null) {
			stringId = "generated-" + getGeneratedId();
		}
		properties.remove("id");
		createColumn(stringId, properties);
	}

	public int getGeneratedId() {
		return generatedId++;
	}

	public void readAttributes(Map<String, String> properties, String string, int groupId, int groupValue) {
		Pattern regexpDiv = Pattern.compile(string);
		Matcher matcher = regexpDiv.matcher(currentLine);

		while (matcher.find()) {
			System.out.println("MATCHS");
			String group = matcher.group();
			System.out.println("\tgroup:" + group);
			int groupCount = matcher.groupCount();
			for (int i = 0; i <= groupCount; i++) {
				String group2 = matcher.group(i);
				System.out.println("\tgroup (" + i + "):" + group2);
			}

			String attId = matcher.group(groupId);
			String attvalue = matcher.group(groupValue);

			properties.put(attId, attvalue);

		}
	}

	protected void handle_templateFooter() {
		System.out.println("LayoutReverser.handle_templateFooter()");
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("tag", "@templateFooter");
		createColumn("@templateFooter", properties);
	}

	protected void handle_region_end() {
		System.out.println("LayoutReverser.handle_region_end()");
		closeColumn();
	}

	protected void handle_include_end() {
		System.out.println("LayoutReverser.handle_include_end()");
		closeRawSection();
	}

	protected void handle_templateHeader_end() {
		System.out.println("LayoutReverser.handle_templateHeader_end()");
		//		closeRawSection();
		closeColumn();
	}

	protected void handle_templateBody_end() {
		System.out.println("LayoutReverser.handle_templateBody_end()");
		closeColumn();
	}

	protected void handle_ifTag_end() {
		System.out.println("LayoutReverser.handle_ifTag_end()");
		closeColumn();
	}

	protected void handle_div_end() {
		System.out.println("LayoutReverser.handle_div_end()");
		closeColumn();
	}

	protected void handle_templateFooter_end() {
		System.out.println("LayoutReverser.handle_templateFooter_end()");
		closeColumn();
	}

	protected Column createColumn(String name, Map<String, String> properties) {
		System.out.println("LayoutReverser.createColumn() name:" + name);
		lastOpenedLineIndex = currentLineIndex;
		Column c = PortalFactory.eINSTANCE.createColumn();
		c.setName(name);
		if (properties != null) {
			PortalHelper.createMetaInfos(properties, c, false);
		}

		// update context
		if (currentCol == null) {
			extractedLayout.getColumns().add(c);
			Object[] entry = { extractedLayout, currentChildIndex, currentOpenedTag };
			child_parent.put(c, entry);
		} else {
			Object[] entry = { currentCol, currentChildIndex, currentOpenedTag };
			child_parent.put(c, entry);
			currentCol.getSubColumns().add(c);
		}

		currentCol = c;
		level++;
		currentChildIndex++;

		System.out.println("LayoutReverser.createColumn() end");
		//		print();
		return c;
	}

	protected void closeColumn() {
		System.out.println("LayoutReverser.closeColumn()");

		Object[] object = (Object[]) child_parent.get(currentCol);

		Object parent = object[0];
		int index = (Integer) object[1];
		if (parent.equals(extractedLayout)) {
			currentCol = null;
			// need to restore the parent opened tag, so ready to be closed
			currentOpenedTag = null;
		} else {
			currentCol = (Column) parent;
			// need to restore the parent opened tag, so ready to be closed
			Object[] object_parent = (Object[]) child_parent.get(parent);
			currentOpenedTag = (FreemarkerTags) object_parent[2];
		}
		// set state
		currentChildIndex = index;

		level--;
		lastClosedLineIndex = currentLineIndex;
		System.out.println("LayoutReverser.closeColumn() end");
		//		print();
	}

	protected void extractRawContent(Column c) {
		System.out.println("LayoutReverser.extractRawContent() column :" + c.getFullName());
		String rawContent = "";
		if (lastOpenedLineIndex == lastClosedLineIndex) {
			rawContent = currentLine;
		} else {
			List<?> selection = readLines.subList(lastOpenedLineIndex, lastClosedLineIndex);

			for (Object object : selection) {
				String s = (String) object;
				rawContent += s + "\n";
			}
		}

		PortalHelper.createMetaInfo(c, "rawContent", rawContent, true);
	}

	enum FreemarkerTags {
		templateFooter("<@templateFooter>", "</@>"), region("<@region.*", "/>"), include("<#include.*", "/>"), templateHeader("<@templateHeader>", "</@>"), templateBody("<@templateBody>", "</@>"), div(".*<div.*", ".*</div>.*"), templateFooter_autoclose("<@templateFooter[ ]*/>",
				"/>"), templateHeader_autoclose("<@templateHeader[ ]*/>", "/>"), templateBody_autoclose("<@templateBody[ ]*/>", "/>");
		String start_tag;
		String end_tag;

		FreemarkerTags(String start_tag, String end_tag) {
			this.end_tag = end_tag;
			this.start_tag = start_tag;
		}
	}

}
