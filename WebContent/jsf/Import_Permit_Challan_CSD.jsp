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
						value="#{import_Permit_Challan_CSD_Action.hidden}"></h:inputHidden>
					<rich:separator lineType="dashed"></rich:separator>
					<h1 style="COLOR: #0000ff;">Import Permit Challan (CSD)</h1>
					<rich:separator lineType="dashed"></rich:separator>
				</div>
				<rich:spacer height="20px;"></rich:spacer>

				<div align="center">
					<rich:dataTable id="table" style="background: #0000"  
						width="90%" var="list"
						value="#{import_Permit_Challan_CSD_Action.datalist}"
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
						
						<rich:column align="right">
							<f:facet name="header">
								<h:outputText value="Excise Duty"
									styleClass="generalHeaderOutputTable"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.excise_duty}" >
							<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" /></h:outputText>

						</rich:column>
						
						<rich:column align="right">
							<f:facet name="header">
								<h:outputText value="Scanning Fee"
									styleClass="generalHeaderOutputTable"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.scannFee}" >
							<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" /></h:outputText>

						</rich:column>
						
						<rich:column align="right">
							<f:facet name="header">
								<h:outputText value="Corona Fee"
									styleClass="generalHeaderOutputTable"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.coronaFee}" >
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
           <div class="col-sm-12">
					<div class="col-sm-4" align="left">
						<h:outputText value="Import Fee Challan No:"
							style="FONT-WEIGHT: bold;" />

						<h:selectOneMenu
							value="#{import_Permit_Challan_CSD_Action.import_fee_challan_no}"
							style=" white-space: normal;width : 200px; height: 21px;">
							<f:selectItems
								value="#{import_Permit_Challan_CSD_Action.importChallanList}" />
						</h:selectOneMenu>
					</div>
					<div class="col-sm-4" align="left">
						<h:outputText value="Special Fee Challan No: "
							style="FONT-WEIGHT: bold;" />
						<h:selectOneMenu
							value="#{import_Permit_Challan_CSD_Action.spcl_fee_challan_no}"
							style=" white-space: normal;width : 200px; height: 21px;">
							<f:selectItems
								value="#{import_Permit_Challan_CSD_Action.spclChallanList}" />
						</h:selectOneMenu>
					</div>
					
					<div class="col-sm-4" align="left">
						<h:outputText value="Excise Duty Challan No: "
							style="FONT-WEIGHT: bold;" />
						<h:selectOneMenu
							value="#{import_Permit_Challan_CSD_Action.excise_duty}"
							style=" white-space: normal;width : 200px; height: 21px;">
							<f:selectItems
								value="#{import_Permit_Challan_CSD_Action.excise_duty_list}" />
						</h:selectOneMenu>
					</div>
				</div>
				
				
					<div class="col-sm-1"  ></div>
					<div class="col-sm-12" >
					<div class="col-sm-4" align="left">
						<h:outputText value="Scanning Fee Challan No: "
							style="FONT-WEIGHT: bold;" />
						<h:selectOneMenu
							value="#{import_Permit_Challan_CSD_Action.scanning_fee}"
							style=" white-space: normal;width : 200px; height: 21px;">
							<f:selectItems
								value="#{import_Permit_Challan_CSD_Action.scanning_fee_list}" />
						</h:selectOneMenu>
					</div>
					<div class="col-sm-4" align="left">
						<h:outputText value="Corona Fee Challan No: "
							style="FONT-WEIGHT: bold;" />
						<h:selectOneMenu
							value="#{import_Permit_Challan_CSD_Action.corona_fee}"
							style=" white-space: normal;width : 200px; height: 21px;">
							<f:selectItems
								value="#{import_Permit_Challan_CSD_Action.corona_fee_list}" />
						</h:selectOneMenu>
					</div>
					</div>
					<div class="col-sm-1"  ></div>
				</div>
				<rich:spacer height="20px;"></rich:spacer>
				<div class="row" align="center">
				<h:commandButton value="Reset" styleClass="btn btn-success"
						action="#{import_Permit_Challan_CSD_Action.navigationBack}"
						style="color:#FFFFFF; background-color: #253f8a;">
					</h:commandButton>
				
					<h:commandButton value="Save" class="btn btn-info"
						actionListener="#{import_Permit_Challan_CSD_Action.save}"
						style="color:#FFFFFF; background-color: #253f8a;">
					</h:commandButton>
				</div>
				<rich:spacer height="30px;"></rich:spacer>
			</div>
		</h:form>
	</f:view>
</ui:composition>