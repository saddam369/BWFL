<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<h:form>
			<div>
			<rich:spacer height="30px;"></rich:spacer>
				<div class="row">
					<div class="col-md-12 wow shake" align="center">
						<h:messages errorStyle="color:red" layout="TABLE" id="messages1"
							infoStyle="color:green"
							style="FONT-SIZE: large; background-color:#e1fcdf;">
						</h:messages>
					</div>
				</div>
				<div align="center">
					<h:inputHidden
						value="#{importChallanForPermitBWFL_FL2DAction_20_21.hidden}"></h:inputHidden>
					<rich:separator lineType="dashed"></rich:separator>
					<h1 style="COLOR: #0000ff;">Import Permit Challan </h1>
					<rich:separator lineType="dashed"></rich:separator>
				</div>
				<rich:spacer height="20px;"></rich:spacer>

				<div align="center">
					<rich:dataTable id="table" style="background: #0000"  
						width="90%" var="list"
						value="#{importChallanForPermitBWFL_FL2DAction_20_21.datalist}"
						headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Application No."
									styleClass="generalHeaderOutputTable"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.app_id }" />

						</rich:column>

						<rich:column align="right">
							<f:facet name="header">
								<h:outputText value="Import Fee"
									styleClass="generalHeaderOutputTable"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.import_fee }" >
							<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" />
							</h:outputText>
						</rich:column>


						<rich:column align="right">
							<f:facet name="header">
								<h:outputText value="Special Fee"
									styleClass="generalHeaderOutputTable"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.special_fee }" >
							<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" /></h:outputText>

						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Select"
									styleClass="generalHeaderOutputTable"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:selectBooleanCheckbox value="#{list.checkbox}"/>
						</rich:column>
						<f:facet name="footer">
							<rich:datascroller for="table"></rich:datascroller>
						</f:facet>

					</rich:dataTable>
				</div>
				<rich:spacer height="20px;" />
				<div class="row">
<div class="col-sm-1" ></div>
					<div class="col-sm-5" align="left">
						<h:outputText value="Import Fee Challan No:"
							style="FONT-WEIGHT: bold;" />

						<h:selectOneMenu
							value="#{importChallanForPermitBWFL_FL2DAction_20_21.import_fee_challan_no}"
							style=" white-space: normal;width : 200px; height: 21px;">
							<f:selectItems
								value="#{importChallanForPermitBWFL_FL2DAction_20_21.importChallanList}" />
						</h:selectOneMenu>
					</div>
					<div class="col-sm-5" align="left">
						<h:outputText value="Special Fee Challan No: "
							style="FONT-WEIGHT: bold;" />
						<h:selectOneMenu
							value="#{importChallanForPermitBWFL_FL2DAction_20_21.spcl_fee_challan_no}"
							style=" white-space: normal;width : 200px; height: 21px;">
							<f:selectItems
								value="#{importChallanForPermitBWFL_FL2DAction_20_21.spclChallanList}" />
						</h:selectOneMenu>
					</div>
					<div class="col-sm-1"  ></div>
				</div>
				<rich:spacer height="20px;"></rich:spacer>
				<div class="row" align="center">
				<h:commandButton value="Back" styleClass="btn btn-success"
						action="#{importChallanForPermitBWFL_FL2DAction_20_21.navigationBack}"
						style="color:#FFFFFF; background-color: #253f8a;">
					</h:commandButton>
				
					<h:commandButton value="Save" class="btn btn-info"
						action="#{importChallanForPermitBWFL_FL2DAction_20_21.save}"
						style="color:#FFFFFF; background-color: #253f8a;">
					</h:commandButton>
				</div>
				<rich:spacer height="30px;"></rich:spacer>
			</div>
		</h:form>
	</f:view>
</ui:composition>