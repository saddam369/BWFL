<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<head>
<style type="text/css">
.reseet {
	border-radius: 3px;
	border-style: none;
	height: 28px;
	width: 75px;
	background-color: #8a8a5c;
	color: white;
	font-weight: bold;
}

.savee {
	border: none;
	border-radius: 3px;
	border-style: none;
	height: 28px;
	width: 75px;
	padding: 8px, 16px;
	background-color: #2d8659;
	color: white;
	font-weight: bold;
	border-radius: 3px;
	background-color: #2d8659;
}

.savee:hover {
	text-decoration: blink;
	background-color: #7ec473;
}

.reseet:hover {
	text-decoration: blink;
	background-color: #837c83;
}

.textt {
	border-radius: 3px;
	background-color: #E8E8E8;
	border: 1px dotted #669999;
	height: 25px;
	box-shadow: 1px 1px 15px lightsteelblue;
	padding: 5px 5px;
	height: 25px;
	width: 10%;
}

.combo {
	border-radius: 3px;
	background-color: #E8E8E8;
	padding-top: 2px;
	padding-bottom: 2px;
	height: 25px;
	width: 75%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px dotted #669999;
	height: 25px;
}

.inputtext {
	border-radius: 3px;
	background-color: #E8E8E8;
	padding: 5px 5px;
	height: 25px;
	width: 75%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px dotted #669999;
}

.textarea1 {
	border-radius: 3px;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px dotted #669999;
	background-color: #E8E8E8;
	width: 100%;
	border: 1px dotted #669999;
}
</style>
		</head>
		<h:form>
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<br />
						<h:messages errorStyle="color:red" layout="table" id="messages"
							infoStyle="color:green" />
					</div>
				</div>

				<div class="row" align="center">
					<div class="col-md-12">
						<h:outputLabel value="Adjustment Offline Pass (BWFL)"
							style="text-align:center;font-family:Calibri;font-weight:bold;font-size:35px;COLOR: #0000a0;" />

						<h:inputHidden value="#{bWFLAdjustmentOfflinePassAction.hidden}" />
					</div>
				</div>
				<br /> 
				<TABLE width="100%" align="center">
					<TR align="center" style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
						<TD><rich:separator lineType="dashed"></rich:separator></TD>
					</TR>
					<tr>
						<TD><rich:spacer height="10px"></rich:spacer></TD>
					</tr>
					<tr>
						<TD align="center" colspan="2"><h:outputLabel
								value="#{bWFLAdjustmentOfflinePassAction.userName}"
								style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>

					</tr>
					<tr>
						<TD><rich:spacer height="5px"></rich:spacer></TD>
					</tr>
					<TR>

						<TD align="center" colspan="2"><h:outputLabel
								value="#{bWFLAdjustmentOfflinePassAction.userAdrs}"
								style="COLOR: #000000; FONT-SIZE: medium;"></h:outputLabel></TD>

					</TR>
					<tr>
						<TD><rich:spacer height="10px"></rich:spacer></TD>
					</tr>
					<TR align="center" style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
						<TD><rich:separator lineType="dashed"></rich:separator></TD>
					</TR>

				</TABLE>
				<br /> <br />
				<div class="row">
					<div class="col-md-12">

						<rich:dataTable align="center" styleClass="table-responsive"
							width="100%" id="rentopiccat" rows="10"
							value="#{bWFLAdjustmentOfflinePassAction.availableStockList}"
							var="list" style="FONT-FAMILY: 'Baskerville Old Face';"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2">

							<rich:column id="column1">
								<f:facet name="header">
									<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.srNo}" />
								</center>
							</rich:column>
							
							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="Permit No." styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.permitNmber_dt}" />
								</center>
							</rich:column>

							<rich:column id="column2">
								<f:facet name="header">
									<h:outputText value="Brand" styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.brandName_dt}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Size" styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.size_dt}" />
								</center>
							</rich:column>


							<rich:column>
								<f:facet name="header">
									<h:outputText value="Available Bottle"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.availableBottle_dt}" />
								</center>
							</rich:column>


							<rich:column id="column3">
								<f:facet name="header">
									<h:outputText value="Available Box"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.availableBox_dt}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Actual Box"
										styleClass="generalHeaderOutputTable" />
								</f:facet>

								<h:inputText value="#{list.dispatchBox_dt}"
									styleClass="generalExciseStyle">
									<a4j:support event="onblur" reRender="bottl">
									</a4j:support>
								</h:inputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Breakage"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:inputText value="#{list.breakage_dt}"
									styleClass="generalExciseStyle">
									<a4j:support event="onblur" reRender="bottl">
									</a4j:support>
								</h:inputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Actual Bottles"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:inputText value="#{list.dispatchBottls_dt}" id="bottl"
									styleClass="generalExciseStyle" disabled="true">
								</h:inputText>
							</rich:column>


							<rich:column id="column4">
								<f:facet name="header">
									<h:outputText value="" styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:commandButton value="Save"
										actionListener="#{bWFLAdjustmentOfflinePassAction.saveDetail}"
										styleClass="btn btn-sm btn-primary">
									</h:commandButton>
								</center>
							</rich:column>
							<f:facet name="footer">
								<rich:datascroller for="rentopiccat" />
							</f:facet>
						</rich:dataTable>
					</div>
				</div>

				<br /> <br />
				<div class="row">
					<div class="col-md-12">
						<rich:dataTable align="center" styleClass="table-responsive"
							width="100%" id="ren" rows="10"
							value="#{bWFLAdjustmentOfflinePassAction.displayStockList}"
							var="list2" style="FONT-FAMILY: 'Baskerville Old Face';"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2">

							<rich:column>
								<f:facet name="header">
									<h:outputLabel styleClass="generalHeaderStyle" value="SrNo" />
								</f:facet>
								<center>
									<h:outputText value="#{list2.srNo}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputLabel styleClass="generalHeaderStyle"
										value="Created Date" />
								</f:facet>
								<center>
									<h:outputText value="#{list2.show_crtdDate}" />
								</center>
							</rich:column>
							
							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="Permit No." styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.show_permitNmbr}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputLabel styleClass="generalHeaderStyle"
										value="Brand Name" />
								</f:facet>
								<center>
									<h:outputText value="#{list2.show_brandName}" />
								</center>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputLabel styleClass="generalHeaderStyle"
										value="Package Name" />
								</f:facet>
								<center>
									<h:outputText value="#{list2.show_pckgName}" />
								</center>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Actual Box/Bottles"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.show_dispatchBox}" />
									/
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.show_dispatchBottls}" />
								</center>
							</rich:column>

							<f:facet name="footer">
								<rich:datascroller for="ren" />
							</f:facet>
						</rich:dataTable>
					</div>
				</div>
				<br />
			</div>
		</h:form>
	</f:view>
</ui:composition>