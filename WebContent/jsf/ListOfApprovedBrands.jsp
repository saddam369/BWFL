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
					<td><h:messages errorStyle="color:red" layout="TABLE"
							id="messages" infoStyle="color:green" /></td>
				</tr>
			</table>

			<br />
			<br />

			<div class="container-fluid">

				<div class="row">
					<div class="col-md-12" align="center">
						<h2>
							<h:outputLabel
								style="COLOR: #000000; TEXT-DECORATION: underline;font-family: calibri;font-size:25px;"
								value="List of Approved Brands" />
						</h2>
					</div>
				</div>

				<br /> <br />

				<div class="row">
					<div class="col-md-1">
						<h:outputLabel style="font-weight:bold;"
							value=" * license Category  : " />
					</div>
					<div class="col-md-10">
						<h:selectOneRadio style="width:60%%;"
							value="#{listOfApprovedBrandsAction.licenseing}"
							onchange="this.form.submit();">
							<f:selectItem itemLabel="CL" itemValue="CL" />
							<f:selectItem itemLabel="IMFL" itemValue="IMFL" />
							<f:selectItem itemLabel="WINE" itemValue="WINE" />
							<f:selectItem itemLabel="BEER" itemValue="BEER" />
							<f:selectItem itemLabel="IMPORTEDFL" itemValue="IMPORTEDFL" />
							<f:selectItem itemLabel="IMPORTEDBEER" itemValue="IMPORTEDBEER" />
							<f:selectItem itemLabel="LAB" itemValue="LAB" />
						</h:selectOneRadio>
					</div>
				</div>

				<br /> <br /> <br />

				<div class="row">
					<div class="col-md-12">

						<rich:dataTable align="center" id="table5" rows="10" width="100%"
							var="list" headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2"
							value="#{listOfApprovedBrandsAction.listdetails}">

							<rich:column>
								<f:facet name="header">
									<h:outputText value="* Registring Authority "
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:inputText disabled="true" value="#{list.registring}"
									styleClass="form-control" />
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="* Brand Name "
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:inputText disabled="true" value="#{list.brand}" styleClass="form-control" />
							</rich:column>



							<rich:column>
								<f:facet name="header">
									<h:outputText value="* Package Type "
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:inputText disabled="true" value="#{list.package1}" styleClass="form-control" />
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="* Size(ML)"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:inputText disabled="true" value="#{list.quantity}" styleClass="form-control" />

							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="* ETIN "
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:inputText disabled="true" value="#{list.etin}" styleClass="form-control" />
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="* MRP "
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:inputText disabled="true" value="#{list.mrp}" styleClass="form-control" />
							</rich:column>

							

							<f:facet name="footer">
								<rich:datascroller for="table5" />
							</f:facet>
						</rich:dataTable>
					</div>
				</div>

				<tr>
					<td width="100%" colspan="2">
						<div class="panel-footer clearfix">
							<div align="center">
								
								<h:commandButton styleClass="btn btn-default"
									action="#{listOfApprovedBrandsAction.reset}" value="Reset" />
							  <h:commandButton styleClass="btn btn-default"
							  action="#{listOfApprovedBrandsAction.print}" value="Print" />
								<h:outputLink styleClass="outputLinkEx"
									value="/doc/ExciseUp/reports/pdf//#{listOfApprovedBrandsAction.pdfname}"
									target="_blank">
									<h:outputText styleClass="outputText" id="text223"
										value="View Report"
										rendered="#{listOfApprovedBrandsAction.printFlag==true}"
										style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
								</h:outputLink>
							</div>
						</div>
					</td>
				</tr>
			</div>
		</h:form>
	</f:view>
</ui:composition>