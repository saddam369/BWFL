<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<h:form>
			<table align="center">
				<tr>
					<td><h:messages errorStyle="color:red" layout="table"
							id="messages" infoStyle="color:green" /></td>
				</tr>
			</table>

			<br />

			<div class="conatiner-fluid">

				<div class="row" align="center">
					<div class="col-md-12">
						<h:outputLabel value="Stock Report"
							style="font-weight:bold;font-family:calibri;font-size: 30px;" />
					</div>
				</div>

				<br /> <br /> <br /> <br />

				<div class="row" align="center">
					<div class="col-md-12">
						<h:selectOneRadio value="#{stock_ReportAction.radio}"
							valueChangeListener="#{stock_ReportAction.radioChange}"
							onchange="this.form.submit();"
							style="width:40%;margin-left:190px;">
							<f:selectItem itemLabel="Distillery" itemValue="D" />
							<f:selectItem itemLabel="Brewery" itemValue="B" />
						</h:selectOneRadio>
					</div>
				</div>
				<br /> <br />
				<div class="row">
					<div class="col-md-12"></div>
				</div>

				<div class="row">
					<div class="col-md-12"></div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<h:panelGroup rendered="#{stock_ReportAction.radioChangeFlag}">
							<rich:dataTable columnClasses="columnClass1"
								headerClass="TableHead" footerClass="TableHead"
								rowClasses="TableRow1,TableRow2" styleClass="DataTable"
								id="table2" rows="10" width="100%"
								value="#{stock_ReportAction.showDataTableList}" var="list">

								<rich:column>
									<f:facet name="header">
										<h:outputText value="Sr. No." />
									</f:facet>
									<center>
										<h:outputText value="#{list.sno}"
											styleClass="generalHeaderStyleOutput" />
									</center>
								</rich:column>

								<rich:column>
									<f:facet name="header">
										<h:outputText value="Distillery/Brewery" />
									</f:facet>
									<center>
										<h:outputText value="#{list.name}"
											styleClass="generalHeaderStyleOutput" />
									</center>
								</rich:column>

								<rich:column sortBy="#{list.brand_name}"
									filterBy="#{list.brand_name}">
									<f:facet name="header">
										<h:outputText value="Brand Name" />
									</f:facet>
									<h:outputText value="#{list.brand_name}"
										styleClass="generalHeaderStyleOutput" />
								</rich:column>

								<rich:column sortBy="#{list.package_name}"
									filterBy="#{list.package_name}">
									<f:facet name="header">
										<h:outputText value="Package Name" />
									</f:facet>
									<h:outputText value="#{list.package_name}"
										styleClass="generalHeaderStyleOutput" />
								</rich:column>

								<rich:column>
									<f:facet name="header">
										<h:outputText value="Size (ML)" />
									</f:facet>
									<center>
										<h:outputText value="#{list.size}"
											styleClass="generalHeaderStyleOutput" />
									</center>
								</rich:column>

								<rich:column>
									<f:facet name="header">
										<h:outputText value="Current Stock" />
									</f:facet>
									<center>
										<h:outputText value="#{list.current_stock}"
											styleClass="generalHeaderStyleOutput" />
									</center>
								</rich:column>

								<f:facet name="footer">
									<rich:datascroller for="table2" />
								</f:facet>

							</rich:dataTable>
						</h:panelGroup>
					</div>
				</div>
				<br />
				<div class="row" align="center">
					<div class="col-md-12">
						<h:commandButton rendered="#{stock_ReportAction.radioChangeFlag}"
							actionListener="#{stock_ReportAction.print}"
							styleClass="btn btn-info btn-sm" value="Print" />
						<h:outputLink styleClass="outputLinkEx"
							value="/doc/ExciseUp/Stock_Report/pdf//#{stock_ReportAction.pdfname}"
							target="_blank">
							<rich:spacer width="7px" />
							<h:outputText styleClass="outputText" id="text223"
								value="View Report" rendered="#{stock_ReportAction.printFlag}"
								style="color: blue;font-family:serif;font-size:12pt;text-decoration:none;" />
						</h:outputLink>
					</div>
				</div>

				<br />
			</div>
		</h:form>
	</f:view>
</ui:composition>