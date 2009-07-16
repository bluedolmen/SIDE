package com.bluexml.alfresco.modules.sql.searcher;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;

import net.sf.acegisecurity.AccessDeniedException;
import net.sf.acegisecurity.Authentication;
import net.sf.acegisecurity.ConfigAttribute;
import net.sf.acegisecurity.ConfigAttributeDefinition;
import net.sf.acegisecurity.afterinvocation.AfterInvocationProvider;

public class ACLEntryAfterInvocationProvider implements AfterInvocationProvider, InitializingBean {

    private static final String AFTER_ACL_NODE = "AFTER_ACL_NODE";
    private static final String AFTER_ACL_PARENT = "AFTER_ACL_PARENT";

	
	public Object decide(Authentication arg0, Object arg1,
			ConfigAttributeDefinition arg2, Object arg3)
			throws AccessDeniedException {
		System.out.println("decide called on object " + arg3.toString());
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(ConfigAttribute attribute) {
        if ((attribute.getAttribute() != null) && (attribute.getAttribute().startsWith(AFTER_ACL_NODE) || attribute.getAttribute().startsWith(AFTER_ACL_PARENT)))
        {
        	System.out.println("supports " + attribute);
            return true;
        }
        else
        {
        	System.out.println("do not support " + attribute);
            return false;
        }
	}

	public boolean supports(Class clazz) {
		System.out.println("support of class \"" + clazz.toString() + "\" : " + MethodInvocation.class.isAssignableFrom(clazz));
        return (MethodInvocation.class.isAssignableFrom(clazz));

	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
