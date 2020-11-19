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
						<h:outputLabel value="FL2A Reciept"
							style="text-align:center;font-family:Calibri;font-weight:bold;font-size:35px;" />
						<h:inputHidden value="#{fL2A_Reciept_DetailsAction.hidden}" />
					</div>
				</div>
				<br /> <br />
				<div class="row">
					<div class="col-md-12">

						<rich:dataTable align="center" styleClass="table-responsive"
							width="100%" id="rentopiccat" rows="10"
							value="#{fL2A_Reciept_DetailsAction.bwfl_datalist}" var="list"
							style="FONT-FAMILY: 'Baskerville Old Face';"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2">

							<rich:column id="column1">
								<f:facet name="header">
									<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.sno}" />
								</center>
							</rich:column>

							<rich:column id="column2">
								<f:facet name="header">
									<h:outputText value="Permit Date"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.date}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Permit No."
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.permitno}" />
								</center>
							</rich:column>

							<rich:column id="column3">
								<f:facet name="header">
									<h:outputText value="CSD Name"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.passno}" />
								</center>
							</rich:column>



							<rich:column id="column4">
								<f:facet name="header">
									<h:outputText value="Select" styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:commandButton value="View Details"
										action="#{fL2A_Reciept_DetailsAction.view_details}"
										styleClass="btn btn-sm btn-primary">
										<f:setPropertyActionListener value="#{list}"
											target="#{fL2A_Reciept_DetailsAction.reciptDetail }" />

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
				<!-- Ravi sir Form -->

				<h:panelGroup
					rendered="#{fL2A_Reciept_DetailsAction.view_full_form_flag}">
					<rich:dataTable align="center" styleClass="table-responsive"
						width="100%" id="rento" rows="10"
						value="#{fL2A_Reciept_DetailsAction.view_formData}" var="list1"
						style="FONT-FAMILY: 'Baskerville Old Face';"
						headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2">

						<rich:column id="column1">
							<f:facet name="header">
								<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list1.slno}" />
							</center>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Brand" styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list1.brand}" />
							</center>
						</rich:column>

						<rich:column id="column2">
							<f:facet name="header">
								<h:outputText value="Quantity" styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list1.int_quantity}" />
							</center>
						</rich:column>



						<rich:column id="column3">
							<f:facet name="header">
								<h:outputText value="Dispatched Bottles"
									styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list1.int_planned_bottles}" />
							</center>
						</rich:column>

						<rich:column id="column5">
							<f:facet name="header">
								<h:outputText value="Dispatched Boxes"
									styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list1.int_boxes}" />
							</center>
						</rich:column>
						
					

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Breakage" styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:inputText styleClass="generalExciseStyle"
									value="#{list1.recivecases}">
									<a4j:support event="onblur" reRender="bot"></a4j:support>
								</h:inputText>


							</center>
						</rich:column>






						<rich:column>
							<f:facet name="header">
								<h:outputText value="Balance Bottles"
									styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<a4j:outputPanel id="bot">
									<h:inputText styleClass="generalExciseStyle"
										value="#{list1.noOfBottle}" disabled="true" />
								</a4j:outputPanel>

							</center>
						</rich:column>


						<f:facet name="footer">
							<rich:datascroller for="rento" />
						</f:facet>
					</rich:dataTable>
					<br />
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-4" align="center">
								<h:outputText value="Recieved By"
									styleClass="generalHeaderStyle" />
								<rich:spacer width="10px"/>
								<h:inputText styleClass="generalExciseStyle"
									value="#{fL2A_Reciept_DetailsAction.recievedby}" />
							</div>

							<div class="col-md-4" align="center">
								<h:outputText value="Recieved Date"
									styleClass="generalHeaderStyle" />
								<rich:spacer width="10px"/>
								<rich:calendar styleClass="generalExciseStyle"
									value="#{fL2A_Reciept_DetailsAction.receiving_date}" />
							</div>

							<div class="col-md-4" align="center">
								<h:outputText value="Remark" styleClass="generalHeaderStyle" />
								<rich:spacer width="10px"/>
								<h:inputTextarea styleClass="generalExciseStyle"
									value="#{fL2A_Reciept_DetailsAction.remarks}"
									style=" width : 387px; height : 27px;" />
							</div>
						</div>
					</div>
				</h:panelGroup>

				<!-- End Ravi Sir Form -->

				<br /> <br />

				<div class="row" align="center">
					<div class="col-md-12">
						
							<h:commandButton value="Save"
								rendered="#{fL2A_Reciept_DetailsAction.view_full_form_flag}"
								actionListener="#{fL2A_Reciept_DetailsAction.save}"
								styleClass="btn btn-sm btn-primary" />
							<rich:spacer width="30px"></rich:spacer>
							<h:commandButton value="Reset"
								action="#{fL2A_Reciept_DetailsAction.reset}"
								styleClass="btn btn-sm btn-primary" />
						
					</div>
				</div>
				<br /> <br />
				<rich:dataTable align="center" styleClass="table-responsive"
					width="97%" id="ren" rows="10"
					value="#{fL2A_Reciept_DetailsAction.reciept_list}" var="list2"
					style="FONT-FAMILY: 'Baskerville Old Face';"
					headerClass="TableHead" footerClass="TableHead"
					rowClasses="TableRow1,TableRow2">

					<rich:column>
						<f:facet name="header">
							<h:outputLabel styleClass="generalHeaderStyle" value="SrNo" />
						</f:facet>
						<center>
							<h:outputText value="#{list2.sno}" />
						</center>
					</rich:column>

					<!-- <rich:column>
						<f:facet name="header">
							<h:outputLabel styleClass="generalHeaderStyle" value="Pass No" />
						</f:facet>
						<center>
							<h:outputText value="#{list2.passno}" />
						</center>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputLabel styleClass="generalHeaderStyle"
								value="License Number" />
						</f:facet>
						<center>
							<h:outputText value="#{list2.licno}" />
						</center>
					</rich:column> -->
					
					
					<rich:column>
						<f:facet name="header">
							<h:outputLabel styleClass="generalHeaderStyle" value="Permit No." />
						</f:facet>
						<center>
							<h:outputText value="#{list2.permitnodisp}" />
						</center>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputLabel styleClass="generalHeaderStyle" value="Brand" />
						</f:facet>
						<center>
							<h:outputText value="#{list2.brand}" />
						</center>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputLabel styleClass="generalHeaderStyle"
								value="Planned Bottles" />
						</f:facet>
						<center>
							<h:outputText value="#{list2.plannedBottlesdt}" />
						</center>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputLabel styleClass="generalHeaderStyle"
								value="Recieved Bottles" />
						</f:facet>
						<center>
							<h:outputText value="#{list2.recievedBottlesdt}" />
						</center>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputLabel styleClass="generalHeaderStyle"
								value="Recieved By" />
						</f:facet>
						<center>
							<h:outputText value="#{list2.recievedby}" />
						</center>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputLabel styleClass="generalHeaderStyle"
								value="Recieving Date" />
						</f:facet>
						<center>
							<h:outputText value="#{list2.recievingdate}" />
						</center>
					</rich:column>

					<f:facet name="footer">
						<rich:datascroller for="ren" />
					</f:facet>
				</rich:dataTable>
				<br />
			</div>
		</h:form>
	</f:view>
</ui:composition>