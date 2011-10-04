Alfresco language pack brings translations for MMI.

English is default language but it's due to the JVM configuration.
If the JVM that launches Alfresco doesn't specify english as language,
you can't display Share MMI back to english language...

So make sure your alfresco.sh file to specify language and localization.
Add this to JAVA_OPTS :
-Duser.language=en -Duser.region=US

===========================================================================

To activate the new DispatcherServlet, please modify webapps/share/WEB-INF/web.xml 
and specify com.bluexml.side.Framework.share.languagePicker.CustomDispatcherServlet as pageRendererServlet :

<!-- The Web Framework Dispatcher Servlet -->
   <servlet>
      <servlet-name>pageRendererServlet</servlet-name>
      <servlet-class>com.bluexml.side.Framework.alfresco.shareLanguagePicker.CustomDispatcherServlet</servlet-class>
      <load-on-startup>1</load-on-startup>
   </servlet>
   
   <servlet>
      <servlet-name>apiServlet</servlet-name>
      <servlet-class>com.bluexml.side.Framework.alfresco.shareLanguagePicker.CustomWebScriptServlet</servlet-class>
      <init-param>
         <param-name>container</param-name>
         <param-value>webframework.webscripts.container</param-value>
      </init-param>
      <!--
      <init-param>
         <param-name>authenticator</param-name>
         <param-value>webscripts.authenticator.basic</param-value>
      </init-param>
      -->
   </servlet>
===========================================================================

ADD or REMOVE available languages

If you want to customize languages list dashlet, please edit webapps/share/WEB-INF/classes/alfresco/site-webscripts/org/alfresco/components/dashlets/languagePicker.get.html.ftl file.

Use locale ID to name your images and put it in webapps/share/components/dashlets/images....