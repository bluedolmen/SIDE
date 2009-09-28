<html>
	<body>
		<h2>Parametrization of Random Datas Model Generator</h2>
		<table border="0">
			<form method="post" action="/alfresco/service/datas/generate/generate">
				<tr>
				 	<td align="center">Model:
						<select name="model">
						<#list qnameModels as qnameModel>
							<option value="${qnameModel}">${qnameModel}</option>
						</#list>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">Number of generated model contents:
						<input name="numOfInstances" type="text" />
					</td>
				</tr>
				<tr>
					<td align="center">Average number of output associations per content:
						<input name="numOfOutputArcs" type="text" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<input name="submit" type="Submit" value="Generate" />
					</td>
				</tr>
			</form>
		</table>
	</body>
</html>