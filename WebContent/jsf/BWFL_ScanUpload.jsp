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
						<h:messages errorStyle="color:red" layout="TABLE" id="messages1"
							infoStyle="color:green"
							style="font-size:16px; font-weight: bold">
						</h:messages>
						
					</div>
				</div>

				<div class="row" align="center">
					<div class="col-md-12">
						<h:outputLabel value="BWFL Scan And Upload"
							style="text-align:center;font-family:Calibri;font-weight:bold;font-size:35px;" />

						<h:inputHidden value="#{bWFL_ScanUploadAction.hidden}" />
					</div>
				</div>
				<br />

				<table align="center" width="80%">
					<TBODY align="center">
						<tr>
							<td><rich:spacer height="10px;"></rich:spacer></td>
						</tr>

						<tr>
							<TD><rich:spacer height="10px"></rich:spacer></TD>
						</tr>

						<tr align="center">
							<TD align="right" style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
									value="FROM" style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</TD>
							<TD align="left"><h:inputText
									value="#{bWFL_ScanUploadAction.distillery_name}"
									styleClass="generalHeaderOutputTable" style="width:250px"
									disabled="true">
								</h:inputText></TD>

							<td align="right" style="padding-right: 10px;"><h:outputText
									value="License No."
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText></td>
							<td align="left">
								<h:selectOneMenu
									valueChangeListener="#{bWFL_ScanUploadAction.licChangeLsnr}"
									value="#{bWFL_ScanUploadAction.select_lic_no}"
									onchange="this.form.submit();">
									<f:selectItems value="#{bWFL_ScanUploadAction.licno}" />
								</h:selectOneMenu>
							</td>
						</tr>
						<tr align="center">
							<td style="padding-top: 10px;"></td>
						</tr>
						<tr align="center">
							<TD align="right" style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
									value="TO" style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</TD>
							<TD align="left"><h:selectOneRadio
									value="#{bWFL_ScanUploadAction.license_type}" disabled="true">
									<f:selectItem itemValue="1" itemLabel="BWFL2A" />
									<f:selectItem itemValue="2" itemLabel="BWFL2B" />
									<f:selectItem itemValue="3" itemLabel="BWFL2C" />
									<f:selectItem itemValue="4" itemLabel="BWFL2D" />
								</h:selectOneRadio></TD>


							<td align="right" style="padding-right: 10px;"><h:outputText
									value="Licensee Name ."
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText></td>
							<td align="left"><h:inputText
									value="#{bWFL_ScanUploadAction.licensee_name}"
									styleClass="generalHeaderOutputTable" style="width:250px"
									disabled="true">

								</h:inputText></td>





						</tr>
						<tr align="center">
							<td style="padding-top: 10px;"></td>
						</tr>
						<tr style="padding-bottom: 10px;">
							<TD align="right" style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
									value="Address" style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</TD>
							<td align="left"><h:inputTextarea
									value="#{bWFL_ScanUploadAction.firm_address}"
									styleClass="generalHeaderOutputTable" style="width: 250px;"
									disabled="true">

								</h:inputTextarea></td>
							<TD align="right" style="padding-right: 10px;"></TD>
							<td align="left"></td>
						</tr>
						<tr align="center">
							<td style="padding-top: 10px;"></td>
						</tr>
						<tr style="padding-bottom: 10px;">
							<TD align="right" style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
									value=" * Route Details"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText></TD>
							<td align="left"><h:inputTextarea
									value="#{bWFL_ScanUploadAction.routeDetails}"
									styleClass="generalHeaderOutputTable" style="width: 250px;">

								</h:inputTextarea></td>
							<TD align="right" style="padding-right: 10px;"><h:outputText
									value="* Vehicle No."
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText></TD>
							<td align="left"><h:inputText
									value="#{bWFL_ScanUploadAction.transVehicleNo}"
									styleClass="generalHeaderOutputTable" style="width:250px">

								</h:inputText></td>
						</tr>
						<tr align="center">
							<td style="padding-top: 10px;"></td>
						</tr>
						<tr style="padding-bottom: 10px;">
							<TD align="right" style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
									value="* Export Order No.:"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText></TD>
							<td align="left"><h:inputText
									value="#{bWFL_ScanUploadAction.expOrderNo}"
									styleClass="generalHeaderOutputTable" style="width:250px">

								</h:inputText></td>
							<TD align="right" style="padding-right: 10px;"><h:outputText
									value="* Export Order Date"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText></TD>
							<td align="left"><rich:calendar
									styleClass="generalExciseStyle"
									value="#{bWFL_ScanUploadAction.expOrderDt}" /></td>
						</tr>
						<tr align="center">
							<td style="padding-top: 10px;"></td>
						</tr>
					</TBODY>
				</table>

				<br />



				<rich:spacer height="30px;"></rich:spacer>



				<br /> <br />
				<!-- Ravi sir Form -->

				<h:panelGroup>
					<rich:dataTable align="center" styleClass="table-responsive"
						width="100%" id="rento" rows="10"
						value="#{bWFL_ScanUploadAction.datalist}" var="list1"
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
								<h:outputText value="Permit Date"
									styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list1.datefrst}" />
							</center>
						</rich:column>
						<rich:column>
							<f:facet name="header">
								<h:outputText value="Permit No" styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list1.permitno}" />
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
								<h:outputText value="Available Bottles"
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
									value="#{list1.breakage}">
									<a4j:support event="onblur" reRender="dispatched"></a4j:support>
								</h:inputText>
							</center>
						</rich:column>




						<rich:column id="column8">
							<f:facet name="header">
								<h:outputText value="Dispatched Bottles"
									styleClass="generalHeaderStyle" />
							</f:facet>
							<center>


								<h:inputText styleClass="generalExciseStyle"
									value="#{list1.dispatched_bottles}" id="dispatched"
									disabled="true">
								</h:inputText>
							</center>
						</rich:column>
						<rich:column>
							<f:facet name="header">

							</f:facet>
							<center>


								<h:commandButton value="Save"
									onclick="return confirm('ALERT : After saving cannot be changed. Do you Agree ?');"
									actionListener="#{bWFL_ScanUploadAction.updateData}"
									styleClass="btn btn-primary" />
							</center>
						</rich:column>
						<f:facet name="footer">
							<rich:datascroller for="rento" />
						</f:facet>
					</rich:dataTable>
					<div class="row">
						<div class="col-md-12">
							<center>


								<h:commandButton value="Reset" styleClass="btn btn-danger"
									action="#{bWFL_ScanUploadAction.reset}" />


							</center>
						</div>
					</div>
					<br />
					<rich:dataTable align="center" styleClass="table-responsive"
						width="100%" id="rentopiccat1" rows="10"
						value="#{bWFL_ScanUploadAction.reciept_list}" var="list2"
						style="FONT-FAMILY: 'Baskerville Old Face';"
						headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2">

						<rich:column id="column1">
							<f:facet name="header">
								<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list2.snothrd}" />
							</center>
						</rich:column>






						<rich:column filterBy="#{list2.passnothrd}"
							sortBy="#{list2.passnothrd}">
							<f:facet name="header">
								<h:outputText value="Gatepass No."
									styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list2.passnothrd}" />
							</center>
						</rich:column>

						<rich:column id="column3" sortBy="#{list2.permitNmbr_dt}">
							<f:facet name="header">
								<h:outputText value="Permit No." styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list2.permitNmbr_dt}" />
							</center>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Brand Name" styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list2.brandNm_dt}" />
							</center>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Package Name"
									styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list2.pckgNm_dt}" />
							</center>
						</rich:column>
						<rich:column sortBy="#{list2.dispatch_date}">
							<f:facet name="header">
								<h:outputText value="Dispatch Date"
									styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list2.dispatch_date}" />
							</center>
						</rich:column>
						<rich:column>
							<f:facet name="header">
								<h:outputText value="Dispatch Box/Bottles"
									styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list2.dispbox}" />
								/
								<h:outputText styleClass="generalExciseStyle"
									value="#{list2.dispbot}" />
							</center>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Action" styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:commandButton styleClass="btn btn-success" value="Print"
									disabled="false" rendered="#{list2.printBtnFlag == false}"
									actionListener="#{bWFL_ScanUploadAction.printreport}" />

								<h:outputLink styleClass="outputLinkEx"
									value="/doc/ExciseUp/WholeSale/pdf//#{bWFL_ScanUploadAction.pdfName}"
									target="_blank">
									<h:outputText styleClass="outputText" id="text223"
										value="View Report" rendered="#{list2.printFlag}"
										style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
								</h:outputLink>
								<h:commandButton styleClass="btn btn-success"
									value="Scan and Upload"
									rendered="#{list2.printBtnFlag == true }"
									actionListener="#{bWFL_ScanUploadAction.scanAndUpload}">
								</h:commandButton>


							</center>
						</rich:column>
						<f:facet name="footer">
							<rich:datascroller for="rentopiccat1" />
						</f:facet>
					</rich:dataTable>
					<br />
					<div class="row">
						<rich:spacer height="10px"></rich:spacer>
					</div>

					<table align="center">
						<tr>
							<td colspan="6" style="color: Green;" align="left"><h:outputText
									rendered="#{bWFL_ScanUploadAction.gatePassFlag}"
									value="** Please read the instructions carefully before uploading."
									style="color: #ce0000;font-style: italic;font-size: large;text-decoration:blink;" /></td>
						</tr>
						<tr align="left">
							<td style="FONT-WEIGHT: bold; color: Green; width: 348px;"
								align="left"><h:outputText
									rendered="#{bWFL_ScanUploadAction.gatePassFlag}"
									value="Upload csv for Gatepass Number(Same Pass No. should be entered in the first row of csv.):"
									style="FONT-SIZE: medium;" /></td>
							<td style="FONT-SIZE: large; color: Red"><h:outputText
									rendered="#{bWFL_ScanUploadAction.gatePassFlag}"
									value="#{bWFL_ScanUploadAction.gatepassForCsv}"
									style="COLOR: #0000ff;" /></td>
							<TD><h:outputLink value="/doc/ExciseUp/ExcelFormatBWFL.pdf"
									rendered="#{bWFL_ScanUploadAction.gatePassFlag}"
									target="_blank">
									<h:graphicImage value="/img/i.png" style="width:40px;"></h:graphicImage>
								</h:outputLink></TD>

							<td><rich:fileUpload addControlLabel="Add File" id="link3"
									fileUploadListener="#{bWFL_ScanUploadAction.uploadCsv}"
									rendered="#{bWFL_ScanUploadAction.gatePassFlag}"
									maxFilesQuantity="1" listHeight="40" listWidth="250"
									clearControlLabel="clear" stopControlLabel="Stop"
									ontyperejected="if (!confirm(' ONLY .csv type file ALLOWED !!!')) return false"
									acceptedTypes="csv" clearAllControlLabel="Clear">
								</rich:fileUpload></td>

							<td><h:commandButton value="Upload"
									styleClass="btn btn-primary"
									rendered="#{bWFL_ScanUploadAction.gatePassFlag}"
									action="#{bWFL_ScanUploadAction.csvSubmit}">
								</h:commandButton></td>



						</tr>
					</table>
					<div>
						<rich:dataTable align="center" id="table57" rows="10" width="100%"
							var="listk" value="#{bWFL_ScanUploadAction.getVal}"
							rendered="#{bWFL_ScanUploadAction.tableFlag}"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2">

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="GatePass.No" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.gatepass}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="CaseCode" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.casecode}" />
								</center>
							</rich:column>
							<rich:column filterBy="#{listk.cascodeMatching}"
								sortBy="#{listk.cascodeMatching}">
								<f:facet name="header">
									<h:outputLabel value="CaseCodeMatching" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.cascodeMatching}" />
								</center>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="Description" />
								</f:facet>
								<center>
									<h:outputText
										value="#{listk.brand_name}-#{listk.casecoseBrandSize}-#{listk.date_plan}" />
								</center>
							</rich:column>
							<f:facet name="footer">
								<rich:datascroller for="table57" />
							</f:facet>
						</rich:dataTable>
					</div>

				</h:panelGroup>

				<!-- End Ravi Sir Form -->

				<br /> <br />

				<div class="row">
					<div class="col-md-12">
						<center>
							<h:commandButton value="Finalize"
								onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;"
								rendered="#{bWFL_ScanUploadAction.tableFlag}"
								action="#{bWFL_ScanUploadAction.finalSubmit}"
								disabled="#{bWFL_ScanUploadAction.bottlingNotDoneFlag }"
								styleClass="btn btn-primary" />
							<rich:spacer width="30px"></rich:spacer>

							<h:commandButton value="Cancel" styleClass="btn btn-danger"
								rendered="#{bWFL_ScanUploadAction.tableFlag}"
								action="#{bWFL_ScanUploadAction.delete}">
							</h:commandButton>


						</center>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<center>
							<h:commandButton value="Close"
								rendered="#{bWFL_ScanUploadAction.tableFlag}"
								action="#{bWFL_ScanUploadAction.reset}"
								styleClass="btn btn-primary" />
						</center>
					</div>
				</div>
				<br /> <br />

			</div>
		</h:form>
	</f:view>
</ui:composition>