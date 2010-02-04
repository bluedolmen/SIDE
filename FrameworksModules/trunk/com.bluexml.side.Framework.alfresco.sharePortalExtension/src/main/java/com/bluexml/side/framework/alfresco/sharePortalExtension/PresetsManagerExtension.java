package com.bluexml.side.framework.alfresco.sharePortalExtension;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.tools.XMLUtil;
import org.alfresco.web.framework.PresetsManager;
import org.alfresco.web.framework.model.Component;
import org.alfresco.web.framework.model.Page;
import org.alfresco.web.framework.model.TemplateInstance;
import org.alfresco.web.scripts.ClassPathStore;
import org.alfresco.web.scripts.SearchPath;
import org.alfresco.web.scripts.Store;
import org.alfresco.web.site.Model;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class PresetsManagerExtension extends PresetsManager {
	private static Logger logger = Logger.getLogger(PresetsManagerExtension.class);
	private SearchPath searchPath;
	private List<String> files;

	private Document[] documents;

	/**
	 * @param searchPath
	 *            the SearchPath to set
	 */
	public void setSearchPath(SearchPath searchPath) {
		this.searchPath = searchPath;
	}

	/**
	 * @param files
	 *            the preset files list to set
	 */
	public void setFiles(List<String> files) {
		this.files = files;
	}

	/**
	 * Initialise the presets manager
	 */
	private void init() {
		logger.debug("=== SIDE PresetManager loaded ===");
		if (this.searchPath == null || this.files == null) {
			throw new IllegalArgumentException("SearchPath and Files list are mandatory.");
		}

		// search for our preset XML descriptor documents
		List<Document> docs = new ArrayList<Document>(4);
		for (Store store : this.searchPath.getStores()) {
			for (String file : this.files) {
				// file may contains whitecard
				if (file.indexOf("*") != -1 && store instanceof ClassPathStore) {
					file = file.replaceAll("\\.", "\\\\.");
					file = file.replaceAll("\\*", "\\.\\*");
					String path = store.toString().replaceFirst("file:", "");

					if (store.exists()) {
						File f = new File(path);
						if (f.exists()) {
							File[] l = f.listFiles(new FilenameFilter_(file));
							for (File file2 : l) {
								loadDocument(docs, store, file2.getName());
							}
						} else {
							logger.debug("File not found :" + f);
						}
					}
				} else {
					loadDocument(docs, store, file);
				}
			}
		}
		this.documents = docs.toArray(new Document[docs.size()]);
	}

	private void loadDocument(List<Document> docs, Store store, String file) {
		try {
			if (store.hasDocument(file)) {
				try {
					docs.add(XMLUtil.parse(store.getDocument(file)));
				} catch (IOException ioe) {
					throw new AlfrescoRuntimeException("Error loading presets XML file: " + file + " in store: " + store.toString(), ioe);
				} catch (DocumentException de) {
					de.printStackTrace();
					throw new AlfrescoRuntimeException("Error processing presets XML file: " + file + " in store: " + store.toString(), de);
				}
			}
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Construct the model objects for a given preset. Objects persist to the
	 * default store for the appropriate object type.
	 * 
	 * @param id
	 *            Preset ID to use
	 * @param tokens
	 *            Name value pair tokens to replace in preset definition
	 */
	public void constructPreset(Model model, String id, Map<String, String> tokens) {
		if (id == null) {
			throw new IllegalArgumentException("Preset ID is mandatory.");
		}

		// perform one time init - this cannot be perform in an app handler or
		// by the
		// framework init - as it requires the Alfresco server to be started...
		synchronized (this) {
			if (this.documents == null) {
				init();
			}
		}

		for (Document doc : this.documents) {
			for (Element preset : (List<Element>) doc.getRootElement().elements("preset")) {
				// found preset with matching id?
				if (id.equals(preset.attributeValue("id"))) {
					// any components in the preset?
					Element components = preset.element("components");
					if (components != null) {
						for (Element c : (List<Element>) components.elements("component")) {
							// apply token replacement to each value as it is
							// retrieved
							String title = replace(c.elementTextTrim(Component.PROP_TITLE), tokens);
							String titleId = replace(c.elementTextTrim(Component.PROP_TITLE_ID), tokens);
							String description = replace(c.elementTextTrim(Component.PROP_DESCRIPTION), tokens);
							String descriptionId = replace(c.elementTextTrim(Component.PROP_DESCRIPTION_ID), tokens);
							String typeId = replace(c.elementTextTrim(Component.PROP_COMPONENT_TYPE_ID), tokens);
							String scope = replace(c.elementTextTrim(Component.PROP_SCOPE), tokens);
							String regionId = replace(c.elementTextTrim(Component.PROP_REGION_ID), tokens);
							String sourceId = replace(c.elementTextTrim(Component.PROP_SOURCE_ID), tokens);
							String url = replace(c.elementTextTrim(Component.PROP_URL), tokens);
							String chrome = replace(c.elementTextTrim(Component.PROP_CHROME), tokens);

							// validate mandatory values
							if (scope == null || scope.length() == 0) {
								throw new IllegalArgumentException("Scope is a mandatory property for a component preset.");
							}
							if (regionId == null || regionId.length() == 0) {
								throw new IllegalArgumentException("RegionID is a mandatory property for a component preset.");
							}
							if (sourceId == null || sourceId.length() == 0) {
								throw new IllegalArgumentException("SourceID is a mandatory property for a component preset.");
							}

							// generate component
							Component component = model.newComponent(scope, regionId, sourceId);
							component.setComponentTypeId(typeId);
							component.setTitle(title);
							component.setTitleId(titleId);
							component.setDescription(description);
							component.setDescriptionId(descriptionId);
							component.setURL(url);
							component.setChrome(chrome);

							// apply arbituary custom properties
							if (c.element("properties") != null) {
								for (Element prop : (List<Element>) c.element("properties").elements()) {
									String propName = replace(prop.getName(), tokens);
									String propValue = replace(prop.getTextTrim(), tokens);
									component.setCustomProperty(propName, propValue);
								}
							}

							// persist the object
							model.saveObject(component);
						}
					}

					// any pages in the preset?
					Element pages = preset.element("pages");
					if (pages != null) {
						for (Element p : (List<Element>) pages.elements("page")) {
							// apply token replacement to each value as it is
							// retrieved
							String pageId = replace(p.attributeValue(Page.PROP_ID), tokens);
							String title = replace(p.elementTextTrim(Page.PROP_TITLE), tokens);
							String titleId = replace(p.elementTextTrim(Page.PROP_TITLE_ID), tokens);
							String description = replace(p.elementTextTrim(Page.PROP_DESCRIPTION), tokens);
							String descriptionId = replace(p.elementTextTrim(Page.PROP_DESCRIPTION_ID), tokens);
							String typeId = replace(p.elementTextTrim(Page.PROP_PAGE_TYPE_ID), tokens);
							String auth = replace(p.elementTextTrim(Page.PROP_AUTHENTICATION), tokens);
							String template = replace(p.elementTextTrim(Page.PROP_TEMPLATE_INSTANCE), tokens);

							// validate mandatory values
							if (pageId == null || pageId.length() == 0) {
								throw new IllegalArgumentException("ID is a mandatory attribute for a page preset.");
							}
							if (template == null || template.length() == 0) {
								throw new IllegalArgumentException("Template is a mandatory property for a page preset.");
							}

							// generate page
							Page page = model.newPage(pageId);
							page.setPageTypeId(typeId);
							page.setTitle(title);
							page.setTitleId(titleId);
							page.setDescription(description);
							page.setDescriptionId(descriptionId);
							page.setAuthentication(auth);
							page.setTemplateId(template);

							// apply arbituary custom properties
							if (p.element("properties") != null) {
								for (Element prop : (List<Element>) p.element("properties").elements()) {
									String propName = replace(prop.getName(), tokens);
									String propValue = replace(prop.getTextTrim(), tokens);
									page.setCustomProperty(propName, propValue);
								}
							}

							// persist the object
							model.saveObject(page);
						}
					}

					// any template instances in the preset?
					Element templates = preset.element("template-instances");
					if (templates != null) {
						for (Element t : (List<Element>) templates.elements("template-instance")) {
							// apply token replacement to each value as it is
							// retrieved
							String templateId = replace(t.attributeValue(TemplateInstance.PROP_ID), tokens);
							String title = replace(t.elementTextTrim(TemplateInstance.PROP_TITLE), tokens);
							String titleId = replace(t.elementTextTrim(TemplateInstance.PROP_TITLE_ID), tokens);
							String description = replace(t.elementTextTrim(TemplateInstance.PROP_DESCRIPTION), tokens);
							String descriptionId = replace(t.elementTextTrim(TemplateInstance.PROP_DESCRIPTION_ID), tokens);
							String templateType = replace(t.elementTextTrim(TemplateInstance.PROP_TEMPLATE_TYPE), tokens);

							// validate mandatory values
							if (templateId == null || templateId.length() == 0) {
								throw new IllegalArgumentException("ID is a mandatory attribute for a template-instance preset.");
							}
							if (templateType == null || templateType.length() == 0) {
								throw new IllegalArgumentException("Template is a mandatory property for a page preset.");
							}

							// generate template-instance
							TemplateInstance template = model.newTemplate(templateId);
							template.setTitle(title);
							template.setTitleId(titleId);
							template.setDescription(description);
							template.setDescriptionId(descriptionId);
							template.setTemplateType(templateType);

							// apply arbituary custom properties
							if (t.element("properties") != null) {
								for (Element prop : (List<Element>) t.element("properties").elements()) {
									String propName = replace(prop.getName(), tokens);
									String propValue = replace(prop.getTextTrim(), tokens);
									template.setCustomProperty(propName, propValue);
								}
							}

							// persist the object
							model.saveObject(template);
						}
					}

					// TODO: any chrome, associations, types, themes etc. in the
					// preset...

					// found our preset - no need to process further
					break;
				}
			}
		}
	}

	/**
	 * Replace token strings - marked by ${...} in the given string with the
	 * supplied tokens.
	 * 
	 * @param s
	 *            String to process (can be null - will return null)
	 * @param tokens
	 *            Token map (can be null - will return original string)
	 * 
	 * @return replaced string or null if input is null or original string if
	 *         tokens is null
	 */
	private static String replace(String s, Map<String, String> tokens) {
		if (s != null && tokens != null) {
			for (Entry<String, String> entry : tokens.entrySet()) {
				String key = "${" + entry.getKey() + "}";
				String value = entry.getValue();
				s = s.replace(key, value);
			}
		}

		return s;
	}

	public class FilenameFilter_ implements FilenameFilter {
		String regexp;

		FilenameFilter_(String regexp) {
			this.regexp = regexp;
		}

		public boolean accept(File dir, String name) {
			boolean check = name.matches(regexp);
			return check;
		}
	}

	public List<String> getPresetsIds() {
		List<String> ids = new ArrayList<String>();
		synchronized (this) {
			if (this.documents == null) {
				init();
			}
		}
		for (Document doc : this.documents) {
			for (Element preset : (List<Element>) doc.getRootElement().elements("preset")) {
				// found preset with matching id?
				ids.add(preset.attributeValue("id"));
			}
		}
		return ids;
	}
}
