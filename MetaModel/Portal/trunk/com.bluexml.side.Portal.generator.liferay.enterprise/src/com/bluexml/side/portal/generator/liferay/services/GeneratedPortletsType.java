package com.bluexml.side.portal.generator.liferay.services;

import com.bluexml.side.clazz.Clazz;
import com.bluexml.side.form.FormCollection;
import com.bluexml.side.portal.Portlet;
import com.bluexml.side.portal.generator.liferay.LiferayGenerator;
import com.bluexml.side.view.AbstractViewOf;
import com.bluexml.side.view.DataList;
import com.bluexml.side.view.DataTable;
import com.bluexml.side.view.FacetMap;

public enum GeneratedPortletsType {

	ARRAY(DataTable.class, "BxDSNavigator_WAR_BxDSNavigatorportlet", true,
			"defaultUrl", "<alfresco.url>/"), LIST(DataList.class,
			"BxDSNavigator_WAR_BxDSNavigatorportlet", true, "defaultUrl",
			"<alfresco.url>/"), FACETMAP(FacetMap.class,
			"BxDSNavigator_WAR_BxDSNavigatorportlet", true, "defaultUrl",
			"<facetmap.url>/"), XFORM(FormCollection.class,
			"BxDSNavigator_WAR_BxDSNavigatorportlet", true, "defaultUrl",
			"<xforms.url>/");

	private Class<?> BxDSPortletType;

	private String liferayPortletType;

	private boolean instanciable;

	private String url;

	private String parameterName;

	public boolean isInstanciable() {
		return instanciable;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setInstanciable(boolean instanciable) {
		this.instanciable = instanciable;
	}

	private GeneratedPortletsType(Class<?> bxDSPortletType, String liferayType,
			boolean instanciable, String parameterName, String url) {
		setBxDSPortletType(bxDSPortletType);
		setLiferayPortletType(liferayType);
		setInstanciable(instanciable);
		setUrl(url);

		setParameterName(parameterName);
	}

	public Class<?> getBxDSPortletType() {
		return BxDSPortletType;
	}

	public void setBxDSPortletType(Class<?> bxDSPortletType) {
		BxDSPortletType = bxDSPortletType;
	}

	public String getLiferayPortletType() {
		return liferayPortletType;
	}

	public void setLiferayPortletType(String liferayPortletType) {
		this.liferayPortletType = liferayPortletType;
	}

	/**
	 * build url to call from portlet type and generator parameters
	 * 
	 * @param p
	 * @return
	 */
	public String buildUrl(Portlet p) {
		String url = getUrl();
		// replace variables

		url = url.replaceAll("<alfresco.url>", LiferayGenerator
				.getAlfrescoURL(null));
		url = url.replaceAll("<facetmap.url>", LiferayGenerator
				.getFacetMapURL(null));
		url = url
				.replaceAll("<xforms.url>", LiferayGenerator.getXFORMURL(null));

		if (p.getIsPortletInternal() != null
				&& p.getIsPortletInternal().getView() != null
				&& p.getIsPortletInternal().getView() instanceof AbstractViewOf) {
			AbstractViewOf abvo = (AbstractViewOf) p.getIsPortletInternal()
					.getView();
			String className = ((Clazz) abvo.getViewOf()).getFullName();
			String classNameUnderscore = className.replaceAll("\\.", "_");
			String viewName = "";
			if (p.getIsPortletInternal().getView() != null
					&& p.getIsPortletInternal().getView().getName() != null
					&& !p.getIsPortletInternal().getView().getName().equals("")) {
				viewName = p.getIsPortletInternal().getView().getName();
			} else {
				viewName = classNameUnderscore;
			}

			url = url.replaceAll("<datatype>", className);
			url = url.replaceAll("<viewname>", viewName);

		} else if (this.equals(XFORM)) {

		} else {
			System.out.println("oupse :" + p);
		}

		return url;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public static GeneratedPortletsType getEnum(Object c) {
		GeneratedPortletsType[] v = GeneratedPortletsType.values();
		for (int i = 0; i < v.length; i++) {
			GeneratedPortletsType current = v[i];
			Class<?> cc = current.getBxDSPortletType();
			if (cc.isInstance(c)) {
				return current;
			}
		}
		return null;
	}
}
