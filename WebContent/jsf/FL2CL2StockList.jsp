<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

	<f:view>


		<h:form>

			<h:inputHidden value="#{licenceNameAction.hidden}" />
			<rich:separator lineType="dashed"></rich:separator>
			<div align="center">
				<h:outputText value="#{licenceNameAction.name}"
					style="FONT-STYLE: italic;
		 COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;"></h:outputText>


			</div>

			<rich:separator lineType="dashed"></rich:separator>
			<TABLE width="80%">
				<TBODY>
					<TR>
						<TD align="left"><h3>
								<h:messages errorStyle="color:red" layout="table" id="messages"
									infoStyle="color:green">
								</h:messages>
							</h3></TD>
					</TR>
				</TBODY>
			</TABLE>

			<div class="row " align="center">
				<rich:spacer height="30px"></rich:spacer>
			</div>
			<div class="row" align="center">
				<div class="col-md-12" align="center">
					<h:outputText value="Date:"
						style="FONT-SIZE:small; FONT-WEIGHT: bold;"></h:outputText>
					<rich:calendar value="#{licenceNameAction.dtDate}"
					valueChangeListener="#{licenceNameAction.getData}"
						onchanged="this.form.submit()">
					</rich:calendar>
				</div>


			</div>
			<div class="row " align="center">
				<rich:spacer height="30px"></rich:spacer>
			</div>
			<div>
				<rich:dataTable var="list"
					value="#{licenceNameAction.brandPackagingShowDataList}"
					width="100%" headerClass="TableHead" footerClass="TableHead"
					id="show" rows="10" rowClasses="TableRow1,TableRow2" styleClass="DataTable">

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Sr.No" />
						</f:facet>
						<h:outputText value="#{list.srNo }" />
					</rich:column>

					<rich:column sortBy="#{list.brandName }"
						filterBy="#{list.brandName }">
						<f:facet name="header">
							<h:outputText value="Brand_Name" />
						</f:facet>
						<h:outputText value="#{list.brandName }" />
					</rich:column>
					<rich:column sortBy="#{list.pcknm }" filterBy="#{list.pcknm }">
						<f:facet name="header">
							<h:outputText value="Package " />
						</f:facet>
						<h:outputText value="#{list.pcknm }" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Opening" />
						</f:facet>
						<h:outputText value="#{list.opening }" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Receiving" />
						</f:facet>
						<h:outputText value="#{list.recive_bottel2}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Dispatch" />
						</f:facet>
						<h:outputText value="#{list.dis_bottel}" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Closing" />
						</f:facet>
						<h:outputText value="#{list.balance}" />
					</rich:column>
					<f:facet name="footer">
						<rich:datascroller for="show"></rich:datascroller>
					</f:facet>
				</rich:dataTable>


			</div>

		</h:form>
	</f:view>
</ui:composition>