<%
metamodel http://www.eclipse.org/emf/2002/Ecore
%>

<%script type="ecore.EPackage" name="default" file="<%name%>.txt"%>


<html>
	<body>
		<h1></h1>
		<h1>TEST premier jet</h1>
		<%for (eClassifiers()){%>

	 		<h1><%toString()%></h1>
				<%for (eAnnotations()){%>
					<h1><%toString()%></h1>
					<%for (contents()){%>
					<h1><%toString()%></h1>
					<%}%>
					
					
				<%}%>
		<%}%>
	</body>
</html>


