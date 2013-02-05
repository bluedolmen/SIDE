SIDE README

In WEB-INF/web.xml

   ...
   
   <filter-mapping>
      <filter-name>UrlRewriteFilter</filter-name>
      <url-pattern>/page/dologin/*</url-pattern>
   </filter-mapping>
   
   ...
   
In WEB-INF/urlrewrite.xml

<urlrewrite use-query-string="true">

   <!--  SIDE-Labs Auto Connection Configuration -->
   <rule>
          <condition type="parameter" name="auto" operator="equal">(true)</condition>
          <condition type="parameter" name="try" operator="notequal">(true)</condition>
          <condition type="parameter" name="username" operator="equal">(^anonymous$)</condition>
      <from>/page/dologin</from>
      <to type="forward">/page/dologin?try=true&amp;username=admin&amp;password=admin&amp;a</to>
   </rule>
   
   ...