<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="c_rt" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="/dspTaglib" prefix="dsp"%>
<dsp:page>
	<dsp:importbean bean="/atg/dynamo/droplet/Switch" />
	<dsp:importbean bean="/calculator/CalcFormHandler" />

	<html>
	<head>
	<title>Calculator result</title>
	</head>
	<body>

	<table width="700" cellpadding="8">
		<tr>

			<!-- Page Body -->
			<td valign="top"><font face="Verdana,Geneva,Arial"
				color="darkgreen" size="+1"> <dsp:droplet name="Switch">
				<dsp:param name="value" bean="CalcFormHandler.resultMoreThanZero" />
				<dsp:oparam name="true">
                Result is more than 0:  
                 <dsp:valueof bean="CalcFormHandler.resultNumber" />
				</dsp:oparam>
				<dsp:oparam name="false">
                Result is negative number: 
                <dsp:valueof bean="CalcFormHandler.resultNumber" />
				</dsp:oparam>
				<dsp:oparam name="default">
                Smth goes wrong
              </dsp:oparam>
			</dsp:droplet> </font></td>
		</tr>

		<tr>
			<td>Result in Cookie: 
			<%
				Cookie[] cookies = request.getCookies();
					if (cookies != null) {
						for (int i = 0; i < cookies.length; i++) {
							if (cookies[i].getName().equals("resultNumber")) {
								out.write(" = " + cookies[i].getValue());
							}
						}
					}
			%>
			</td>
		</tr>

		<tr>
			<td>Cookie Objects via jstl:
			<ul>
				<li>resultNumber: <c:out value='${cookie["resultNumber"]}' /></li>

			</ul>

			Cookie Values via jstl:
			<ul>
				<li>resultNumber: <c:out
					value='${cookie["resultNumber"].value}' /></li>
			</ul>
			</td>
		</tr>
	</table>
	</body>
	</html>


</dsp:page>
