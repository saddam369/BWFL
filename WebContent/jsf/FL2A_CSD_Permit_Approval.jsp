 <ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<head>
<style>
.inputtext {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}

.dropdown-menu {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 30%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}

.dropdown-menu1 {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 75%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}

.textarea1 {
	border-radius: 3px;
	border-style: none;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}
</style>
		</head>
		<h:form>
			<div class="container">
				<div class="row">
					<rich:spacer height="20px"></rich:spacer>
				</div>
				<div class="row">
					<div class="col-md-12 wow shake" align="center">
						<h:messages errorStyle="color:red" layout="TABLE" id="messages1"
							infoStyle="color:green"
							style="font-size:18px; background-color:#e1fcdf; font-weight: bold">
						</h:messages>

					</div>
				</div>

				<div class="row" align="center">
					<TABLE width="100%" align="center">
						<TR>
							<TD align="center" width="100%">
								<TABLE align="center" width="100%">
									<TBODY>

										<tr>
											<td><rich:separator lineType="dashed"></rich:separator>
											</td>
										</tr>

										<tr>
											<TD align="center" colspan="2"><h2>
													<h:outputText value="FL2A (CSD) Permit Approval"
														style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: 35px;">
													</h:outputText>
													<h:inputHidden value="#{fL2A_CSD_Permit_Approval_Action.hidden}" />
												</h2></TD>
										</tr>

										<tr>
											<td><rich:separator lineType="dashed"></rich:separator>
											</td>
										</tr>
									</TBODY>
								</TABLE>
							</TD>
						</TR>

					</TABLE>
				</div>
				<br />

				<h:panelGroup
					rendered="#{fL2A_CSD_Permit_Approval_Action.hideDataTable and !fL2A_CSD_Permit_Approval_Action.showMainPanel_flg}">
					<div class="row" align="center" style="BACKGROUND-COLOR: #dee0e2;">
						<div class="col-md-12" align="center">
							<h:selectOneRadio style="width:25%"
								value="#{fL2A_CSD_Permit_Approval_Action.radioType}"
								valueChangeListener="#{fL2A_CSD_Permit_Approval_Action.radioListener}"
								onchange="this.form.submit();">
								<f:selectItem itemValue="N" itemLabel="Pending" />
								<f:selectItem itemValue="A" itemLabel="Approved" />
								<f:selectItem itemValue="R" itemLabel="Rejected" />
							</h:selectOneRadio>
						</div>
					</div>
					<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<div class="row" align="center">
						<div class="col-md-12">
							<rich:dataTable id="table22" rows="10" width="100%"
								value="#{fL2A_CSD_Permit_Approval_Action.displayRegUsers}" var="list"
								headerClass="TableHead" footerClass="TableHead"
								styleClass="DataTable" rowClasses="TableRow1,TableRow2">


								<rich:column>
									<f:facet name="header">
										<h:outputText value="Sr.No">
										</h:outputText>
									</f:facet>
									<h:outputText style="margin-left: 20px;" value="#{list.srNo}"></h:outputText>
								</rich:column>

								<rich:column>
									<f:facet name="header">
										<h:outputText value="Application ID" />
									</f:facet>
									<center>
										<h:outputText styleClass="generalExciseStyle"
											value="#{list.appId_dt}" />
									</center>
								</rich:column>

								<rich:column>
									<f:facet name="header">
										<h:outputText value="Application Date">
										</h:outputText>
									</f:facet>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.appDate_dt}">
									</h:outputText>
								</rich:column>

								<rich:column>
									<f:facet name="header">
										<h:outputText value="Applicant Name" />
									</f:facet>
									<center>
										<h:outputText styleClass="generalExciseStyle"
											value="#{list.bwflName_dt}" />
									</center>
								</rich:column>

								<rich:column id="column3">
									<f:facet name="header">
										<h:outputText value="License Type">
										</h:outputText>
									</f:facet>

									<h:outputText value="#{list.licenseType_dt}"></h:outputText>
								</rich:column>

								<rich:column>
									<f:facet name="header">
										<h:outputText value="No.Of Boxes" />
									</f:facet>
									<center>
										<h:outputText styleClass="generalExciseStyle"
											value="#{list.totalBoxes_dt}" />
									</center>
								</rich:column>
								
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Unit Type" />
									</f:facet>
									<center>
										<h:outputText styleClass="generalExciseStyle"
											value="#{list.unitName_dt}" />
									</center>
								</rich:column>

								<rich:column rendered="#{fL2A_CSD_Permit_Approval_Action.radioType ne 'R'}">
									<f:facet name="header">
										<h:outputText value="ImportFee" />
									</f:facet>
									<center>
										<h:outputText styleClass="generalExciseStyle"
											value="#{list.import_fee}" />
									</center>
								</rich:column>

								<rich:column rendered="#{fL2A_CSD_Permit_Approval_Action.radioType ne 'R'}">
									<f:facet name="header">
										<h:outputText value="SpecialFee" />
									</f:facet>
									<center>
										<h:outputText styleClass="generalExciseStyle"
											value="#{list.special_fee}" />
									</center>
								</rich:column>
								
								<rich:column rendered="#{fL2A_CSD_Permit_Approval_Action.radioType ne 'R'}">
									<f:facet name="header">
										<h:outputText value="Excise Duty" />
									</f:facet>
									<center>
										<h:outputText styleClass="generalExciseStyle"
											value="#{list.excise_duty}" />
									</center>
								</rich:column>


	<rich:column rendered="#{fL2A_CSD_Permit_Approval_Action.radioType ne 'R'}">
									<f:facet name="header">
										<h:outputText value="Scanning Fee" />
									</f:facet>
									<center>
										<h:outputText styleClass="generalExciseStyle"
											value="#{list.scanning_fee}" />
									</center>
								</rich:column>
<rich:column rendered="#{fL2A_CSD_Permit_Approval_Action.radioType ne 'R'}">
									<f:facet name="header">
										<h:outputText value="Consideration Fee" />
									</f:facet>
									<center>
										<h:outputText styleClass="generalExciseStyle"
											value="#{list.consideration_fee}" />
									</center>
								</rich:column>

								<rich:column style="text-align: center">
									<f:facet name="header">
										<h:outputText value="Status" />
									</f:facet>
									<h:outputText value="#{list.status_dt}" />
								</rich:column>

								<rich:column>
									<f:facet name="header">
										<h:outputText value="View Application" />
									</f:facet>
									<center>
										<h:commandButton value="View Details"
											actionListener="#{fL2A_CSD_Permit_Approval_Action.viewDetails}"
											styleClass="btn btn-sm btn-primary">
										</h:commandButton>
									</center>
								</rich:column>
								
								

								<rich:column rendered="#{fL2A_CSD_Permit_Approval_Action.radioType eq 'A'}">
									<f:facet name="header">
										<h:outputText value="Permit" />
									</f:facet>
									<center>
										<h:commandButton value="Generate"
											rendered="#{list.permitDate_dt eq null}"
											actionListener="#{fL2A_CSD_Permit_Approval_Action.printReport}"
											styleClass="btn btn-sm btn-info">
										</h:commandButton>
										
										<h:outputLink styleClass="outputLinkEx"
											rendered="#{list.permitDate_dt ne null}"
											value="/doc/ExciseUp/Bond/FL2Apermit//#{list.permitNmbr_dt}.pdf"
											target="_blank">
											<h:outputText styleClass="outputText" id="textF"
												value="View Permit"
												style="color: blue; font-family: serif; font-size: 10pt"></h:outputText>
										</h:outputLink>
									</center>
								</rich:column>

								<rich:column rendered="#{fL2A_CSD_Permit_Approval_Action.radioType eq 'A'}">
									<f:facet name="header">
										<h:outputText value="Permit"/>
									</f:facet>
									<center>

										<h:outputLink
											value="https://www.upexciseonline.in/DigitalSignature/ImportPermitCSD.jsp">
											<f:param name="app_id" value="#{list.appId_dt}"></f:param>
											<f:param name="bwfl_id" value="#{list.bwflID_dt}"></f:param>
											<f:param name="login_type" value="#{list.loginType_dt}"></f:param>
											<f:param name="permit_nbr" value="#{list.permitNmbr_dt}"></f:param>
											<f:param name="domain" value="143"></f:param>
											<h:outputText value="Digital Sign"
												class="btn btn-sm btn-danger"
												rendered="#{list.permitDate_dt ne null and list.digitalSignPdfNm_dt eq 'NA'}"></h:outputText>
										</h:outputLink>


										<h:outputLink styleClass="outputLinkEx"
											rendered="#{list.digitalSignPdfNm_dt ne 'NA'}"
											value="/doc/ExciseUp/Bond/FL2Apermit//#{list.permitNmbr_dt}_esign.pdf"
											target="_blank">
											<h:outputText styleClass="outputText" id="textD1"
												value="View Digitally Sign Permit"
												style="color: blue; font-family: serif; font-size: 10pt"></h:outputText>
										</h:outputLink>
										
									</center>
								</rich:column>
								<f:facet name="footer">
									<rich:datascroller for="table22"></rich:datascroller>
									
								</f:facet>
							</rich:dataTable>

						</div>
					</div>
				</h:panelGroup>
				<br />
				<div class="container-fuild">
					<h:panelGroup rendered="#{fL2A_CSD_Permit_Approval_Action.viewPanelFlg}">
						<div class="row" align="center" style="background-color: #e6ffff">
							<div class="col-md-12">
								<h:outputText
									value="For Application ID:#{fL2A_CSD_Permit_Approval_Action.appId}"
									style="COLOR: #2952a3; font-weight:bold; FONT-SIZE: x-large; font-family:Times New Roman" />
							</div>
						</div>
						<div class="row" align="center">

							<rich:spacer height="20px" />
						</div>
						<div
							style="border: 1px solid black; padding-bottom: 10px; padding-top: 10px; padding-left: 10px; padding-right: 10px; margin-left: 5px; margin-right: 5px; border-radius: 4px;">
							<div class="row">
								<table align="center" width="100%;">
									<tr style="width: 100%">
										<td colspan="4"><h:outputLabel value="Basic Detail : "
												style="FONT-WEIGHT: bold; COLOR: #0000ff; FONT-SIZE: large; FONT-STYLE: italic;"></h:outputLabel>
										</td>
									</tr>

									<tr>
										<td style="width: 100px; padding: 8px 16px;"><h:outputText
												value="License Number :" style="FONT-WEIGHT: bold;" /></td>
										<td style="width: 200px; padding: 8px 16px;"><h:inputText
												disabled="true" styleClass="inputtext"
												value="#{fL2A_CSD_Permit_Approval_Action.licenseNmbr}">
											</h:inputText></td>
										<td
											style="width: 110px; text-align: center; padding: 8px 16px;"><h:outputText
												value="Transport:" style="FONT-WEIGHT: bold;" /></td>
										<td style="width: 200px; padding: 8px 16px;"><h:selectOneRadio
												style="width:80%" disabled="true"
												value="#{fL2A_CSD_Permit_Approval_Action.byRoad_byRoute}">
												<f:selectItem itemValue="train" itemLabel="By Train" />
												<f:selectItem itemValue="road" itemLabel="By Road" />
											</h:selectOneRadio></td>
										<td
											style="width: 110px; text-align: center; padding: 8px 16px;"><h:outputText
												value="Route Details :" style="FONT-WEIGHT: bold;" /></td>
										<td style="width: 200px; padding: 8px 16px;"><h:inputTextarea
												styleClass="textarea1" disabled="true"
												value="#{fL2A_CSD_Permit_Approval_Action.routeDetail}">
											</h:inputTextarea></td>

									</tr>
									<tr>
										<rich:spacer height="5px;"></rich:spacer>
									</tr>

									<tr>



										<td style="width: 100px; padding: 8px 16px;"><h:outputText
												value="Parent Unit Name:" style="FONT-WEIGHT: bold;"
												rendered="#{!fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}" /></td>
										<td style="width: 200px; padding: 8px 16px;"><h:inputTextarea
												styleClass="textarea1" disabled="true"
												value="#{fL2A_CSD_Permit_Approval_Action.parentUnitNm}"
												rendered="#{!fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}">
											</h:inputTextarea></td>

										<td
											style="width: 110px; text-align: center; padding: 8px 16px;"><h:outputText
												value="Parent Unit Address:" style="FONT-WEIGHT: bold;"
												rendered="#{!fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}" /></td>

										<td style="width: 200px; padding: 8px 16px;"><h:inputTextarea
												styleClass="textarea1" disabled="true"
												value="#{fL2A_CSD_Permit_Approval_Action.parentUnitAdrs}"
												rendered="#{!fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}">

											</h:inputTextarea></td>

										<td
											style="width: 110px; text-align: center; padding: 8px 16px;"><h:outputText
												value="Parent Unit State:" style="FONT-WEIGHT: bold;"
												rendered="#{!fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}" /></td>

										<td style="width: 200px; padding: 8px 16px;"><h:inputText
												disabled="true" styleClass="inputtext"
												value="#{fL2A_CSD_Permit_Approval_Action.parentUnitState}"
												rendered="#{!fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}"></h:inputText></td>


									</tr>

									<tr>
										<rich:spacer height="5px;"></rich:spacer>
									</tr>

									<tr>
										<td colspan="2" style="padding: 8px 16px;"><h:outputText
												value="State of Unit from where Import is to be done :"
												style="FONT-WEIGHT: bold;"
												rendered="#{fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}" /></td>
										<td style="width: 200px; padding: 8px 16px;"><h:inputText
												disabled="true" styleClass="inputtext"
												value="#{fL2A_CSD_Permit_Approval_Action.parentUnitState}"
												rendered="#{fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}">
											</h:inputText></td>
										<td style="width: 100px; padding: 8px 16px;"><h:outputText
												value="Name and Address of Unit from where Import is to be done :"
												style="FONT-WEIGHT: bold;"
												rendered="#{fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}" /></td>
										<td colspan="2" style="padding: 8px 16px;"><h:inputTextarea
												styleClass="textarea1" disabled="true"
												value="#{fL2A_CSD_Permit_Approval_Action.consignorNmAdrs}"
												rendered="#{fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}" /></td>

									</tr>

									<tr>
										<rich:spacer height="5px;"></rich:spacer>
									</tr>

									<tr>
										<td colspan="2" style="padding: 8px 16px;"><h:outputText
												value="QR Code / Bar Code Requested As :"
												rendered="#{!fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}"
												style="FONT-WEIGHT: bold;" /></td>
										<td style="width: 200px; padding: 8px 16px;"><h:selectOneRadio
												style="width:80%" disabled="true"
												rendered="#{!fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}"
												value="#{fL2A_CSD_Permit_Approval_Action.mapped_unmapped}">
												<f:selectItem itemValue="M" itemLabel="Mapped" />
												<f:selectItem itemValue="U" itemLabel="UnMapped" />
											</h:selectOneRadio></td>

									</tr>
									<tr>
										<rich:spacer height="5px;"></rich:spacer>
									</tr>

								</table>
								<div class="row" align="center">
									<rich:spacer height="10px"></rich:spacer>
								</div>
								<div class="row">
									<div class="col-md-12">
										<h:outputLabel value="Permit Required For : "
											style="FONT-WEIGHT: bold; COLOR: #0000ff; FONT-SIZE: large; FONT-STYLE: italic;"></h:outputLabel>
									</div>

									<div class="row" align="center">
										<div class="col-md-12">
											<rich:dataTable id="table2p" width="100%"
												value="#{fL2A_CSD_Permit_Approval_Action.displayBrandDetails}"
												var="list" headerClass="TableHead" footerClass="TableHead"
												styleClass="DataTable" rowClasses="TableRow1,TableRow2">


												<rich:column>
													<f:facet name="header">
														<h:outputText value="Sr.No">
														</h:outputText>
													</f:facet>
													<h:outputText style="margin-left: 20px;"
														value="#{list.srNo}"></h:outputText>
													<f:facet name="footer">
														<h:outputText value="Total"
															styleClass="generalHeaderOutputTable" />
													</f:facet>
												</rich:column>

												<rich:column>
													<f:facet name="header">
														<h:outputText value="Brand Name" />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.brandName_dt}" />
													</center>
													<f:facet name="footer">
														<h:outputText value=""
															styleClass="generalHeaderOutputTable" />
													</f:facet>
												</rich:column>



												<rich:column>
													<f:facet name="header">
														<h:outputText value="Package Name" />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.pckgType_dt}" />
													</center>
													<f:facet name="footer">
														<h:outputText value=""
															styleClass="generalHeaderOutputTable" />
													</f:facet>
												</rich:column>

												<rich:column>
													<f:facet name="header">
														<h:outputText value="ETIN">
														</h:outputText>
													</f:facet>
													<h:outputText styleClass="generalExciseStyle"
														value="#{list.etinNo_dt}">
													</h:outputText>
													<f:facet name="footer">
														<h:outputText value=""
															styleClass="generalHeaderOutputTable" />
													</f:facet>
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="No.of Bottles Per case" />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.size_dt}" />
													</center>
													<f:facet name="footer">
														<h:outputText value=""
															styleClass="generalHeaderOutputTable" />
													</f:facet>
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="No.of Boxes" />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.nmbrOfBox_dt}" />
													</center>
													<f:facet name="footer">
														<h:outputText value=""
															styleClass="generalHeaderOutputTable" />
													</f:facet>
												</rich:column>



												<rich:column>
													<f:facet name="header">
														<h:outputText value="No.of Bottles" />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.nmbrOfBtl_dt}" />
													</center>
													<f:facet name="footer">
														<h:outputText value=""
															styleClass="generalHeaderOutputTable" />
													</f:facet>
												</rich:column>

												<rich:column rendered="#{!fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}">
													<f:facet name="header">
														<h:outputText value="Duty" />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.duty_dt}">
															<f:convertNumber maxFractionDigits="2"
																pattern="#############0.00" />
														</h:outputText>
													</center>
													<f:facet name="footer">
														<h:outputText id="totalduty"
															value="#{fL2A_CSD_Permit_Approval_Action.totalDuty}"
															styleClass="generalHeaderOutputTable">
															<f:convertNumber maxFractionDigits="2"
																pattern="#############0.00" />
														</h:outputText>
													</f:facet>
												</rich:column>



												<rich:column>
													<f:facet name="header">
														<h:outputText value="Import Fee" />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.importFees_dt}">
															<f:convertNumber maxFractionDigits="2"
																pattern="#############0.00" />
														</h:outputText>
													</center>
													<f:facet name="footer">
														<h:outputText id="totalfees"
															value="#{fL2A_CSD_Permit_Approval_Action.totalImportFee}"
															styleClass="generalHeaderOutputTable">
															<f:convertNumber maxFractionDigits="2"
																pattern="#############0.00" />
														</h:outputText>
													</f:facet>
												</rich:column>

												<rich:column>
													<f:facet name="header">
														<h:outputText value="Special Fee" />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.specialfees_dt}">
															<f:convertNumber maxFractionDigits="2"
																pattern="#############0.00" />
														</h:outputText>
													</center>
													<f:facet name="footer">
														<h:outputText id="totspclfees"
															value="#{fL2A_CSD_Permit_Approval_Action.totalSpecialFee}"
															styleClass="generalHeaderOutputTable">
															<f:convertNumber maxFractionDigits="2"
																pattern="#############0.00" />
														</h:outputText>
													</f:facet>
												</rich:column>
												

											</rich:dataTable>

										</div>
									</div>

								</div>
								<div class="row">
									<rich:spacer height="10px"></rich:spacer>
								</div>



							</div>
						</div>
					</h:panelGroup>

				</div>
				<div class="row" align="center">
					<rich:spacer height="10px"></rich:spacer>
				</div>



				<h:panelGroup
					rendered="#{fL2A_CSD_Permit_Approval_Action.radioType ne 'R' and fL2A_CSD_Permit_Approval_Action.viewPanelFlg}">

					<div class="row" style="BACKGROUND-COLOR: #e6ffff;">

						<div class="row" align="center">
							<rich:spacer height="10px"></rich:spacer>
						</div>
						<div class="col-md-12" align="center">
							<h:outputLabel value="Advance Duty : "
								style="FONT-WEIGHT: bold; COLOR: #003399; FONT-SIZE: large; FONT-STYLE: italic;"></h:outputLabel>
						</div>
						<div class="row" align="center">
							<rich:spacer height="10px"></rich:spacer>
						</div>


<div align="center">
<div class="row">
<div class="col-md-12">

</div>
</div>

</div>









						<div class="row" align="center">
							<div class="col-md-12">
								<rich:dataTable id="table3" width="100%"
									value="#{fL2A_CSD_Permit_Approval_Action.displayChalanDetails}" var="list"
									headerClass="TableHead" footerClass="TableHead"
									styleClass="DataTable" rowClasses="TableRow1,TableRow2">


									<rich:column>
										<f:facet name="header">
											<h:outputText value="Sr.No">
											</h:outputText>
										</f:facet>
										<h:outputText style="margin-left: 20px;" value="#{list.srNo}"></h:outputText>
										<f:facet name="footer">
											<h:outputText value="" styleClass="generalHeaderOutputTable" />
										</f:facet>
									</rich:column>
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Head Name" />
										</f:facet>
										<center>
											<h:outputText styleClass="generalExciseStyle"
												value="#{list.challanName_dt}" />
										</center>
										<f:facet name="footer">
											<h:outputText value="" styleClass="generalHeaderOutputTable" />
										</f:facet>
									</rich:column>

									
	                                <rich:column>
										<f:facet name="header">
											<h:outputText value="Balance Before Approval Of This Permit" />
										</f:facet>
										<center>
											<h:outputText styleClass="generalExciseStyle"
												value="#{list.balance}" />
										</center>
										<f:facet name="footer">
											<h:outputText value="" styleClass="generalHeaderOutputTable" />
										</f:facet>
									</rich:column>


                                             <rich:column>
										<f:facet name="header">
											<h:outputText value="Permit Fee Required" />
										</f:facet>
										<center>
											<h:outputText styleClass="generalExciseStyle"
												value="#{list.permit_balance}" />
										</center>
										<f:facet name="footer">
											<h:outputText value="" styleClass="generalHeaderOutputTable" />
										</f:facet>
									</rich:column>
									
									
									     <rich:column>
										<f:facet name="header">
											<h:outputText value="Remark" />
										</f:facet>
										<center>
											<h:outputText styleClass="generalExciseStyle"
												value="#{list.required_remark}" />
										</center>
										<f:facet name="footer">
											<h:outputText value="" styleClass="generalHeaderOutputTable" />
										</f:facet>
									</rich:column>
					

								</rich:dataTable>

                  <h:outputText value="No Deduction Shall Be Made From Advance Duty Register For Distillery And Brewery In UP " 
                   rendered="#{fL2A_CSD_Permit_Approval_Action.flag}" style="COLOR: #ff0000;"> </h:outputText>
							</div>
						</div>

						<div class="col-md-12" style="height: 15px"></div>
					</div>
				</h:panelGroup>
				<div class="row" align="center">
					<rich:spacer height="10px"></rich:spacer>
				</div>
				
				
				<h:panelGroup rendered="#{fL2A_CSD_Permit_Approval_Action.registerFlag}">
          
          <div class="row" align="center">
							<rich:spacer height="10px"></rich:spacer>
						</div>
						<div class="col-md-12" align="center">
							<h:outputLabel value="Advance Register Details : "
								style="FONT-WEIGHT: bold; COLOR: #003399; FONT-SIZE: large; FONT-STYLE: italic;"></h:outputLabel>
						</div>
						<div class="row" align="center">
							<rich:spacer height="10px"></rich:spacer>
						</div>
           <div class="row" align="center">
										<div class="col-md-12">
											<rich:dataTable id="table2r" width="100%"
												value="#{fL2A_CSD_Permit_Approval_Action.registerList}"
												var="list" headerClass="TableHead" footerClass="TableHead"
												styleClass="DataTable" rowClasses="TableRow1,TableRow2">


												<rich:column>
													<f:facet name="header">
														<h:outputText value="Sr.No">
														</h:outputText>
													</f:facet>
													<h:outputText style="margin-left: 20px;"
														value="#{list.srNo}"></h:outputText>
													<f:facet name="footer">
														<h:outputText value="Total"
															styleClass="generalHeaderOutputTable" />
													</f:facet>
												</rich:column>

												<rich:column>
													<f:facet name="header">
														<h:outputText value="Unit Name" />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.unitName_dt}" />
													</center>
													<f:facet name="footer">
														<h:outputText value=""
															styleClass="generalHeaderOutputTable" />
													</f:facet>
												</rich:column>



												<rich:column>
													<f:facet name="header">
														<h:outputText value="Challan/Permit No." />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.challan_permit_no}" />
													</center>
													<f:facet name="footer">
														<h:outputText value=""
															styleClass="generalHeaderOutputTable" />
													</f:facet>
												</rich:column>

												<rich:column>
													<f:facet name="header">
														<h:outputText value="Challan Date">
														</h:outputText>
													</f:facet>
													<h:outputText styleClass="generalExciseStyle"
														value="#{list.challanDate}">
													</h:outputText>
													<f:facet name="footer">
														<h:outputText value=""
															styleClass="generalHeaderOutputTable" />
													</f:facet>
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Permit Date" />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.permitDate}" />
													</center>
													<f:facet name="footer">
														<h:outputText value=""
															styleClass="generalHeaderOutputTable" />
													</f:facet>
												</rich:column>
												


												

												<rich:column rendered="#{!fL2A_CSD_Permit_Approval_Action.fl2dDutyFlg}">
													<f:facet name="header">
														<h:outputText value="Deposite Amount" />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.deposite_amt}">
															<f:convertNumber maxFractionDigits="2"
																pattern="#############0.00" />
														</h:outputText>
													</center>
													<f:facet name="footer">
														<h:outputText id="totalduty"
															value="#{fL2A_CSD_Permit_Approval_Action.total_deposit_amt}"
															styleClass="generalHeaderOutputTable">
															<f:convertNumber maxFractionDigits="2"
																pattern="#############0.00" />
														</h:outputText>
													</f:facet>
												</rich:column>



												<rich:column>
													<f:facet name="header">
														<h:outputText value="permit Amount" />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.permit_amt}">
															<f:convertNumber maxFractionDigits="2"
																pattern="#############0.00" />
														</h:outputText>
													</center>
													<f:facet name="footer">
														<h:outputText id="totalfees"
															value="#{fL2A_CSD_Permit_Approval_Action.total_permit_amt}"
															styleClass="generalHeaderOutputTable">
															<f:convertNumber maxFractionDigits="2"
																pattern="#############0.00" />
														</h:outputText>
													</f:facet>
												</rich:column>

												<rich:column>
													<f:facet name="header">
														<h:outputText value="Advance Amount" />
													</f:facet>
													
													<f:facet name="footer">
														<h:outputText id="totspclfees"
															value="#{fL2A_CSD_Permit_Approval_Action.total_diff}"
															styleClass="generalHeaderOutputTable">
															<f:convertNumber maxFractionDigits="2"
																pattern="#############0.00" />
														</h:outputText>
													</f:facet>
												</rich:column>
												

											</rich:dataTable>

										</div>
									</div>
          
          </h:panelGroup>
          
          <div class="row">
          <rich:spacer height="20"></rich:spacer>
          </div>
				<h:panelGroup rendered="#{fL2A_CSD_Permit_Approval_Action.viewPanelFlg}">
					<div class="row" align="center" style="BACKGROUND-COLOR: #c5cfe5;">
						<div class="row" align="center">
							<rich:spacer height="10px"></rich:spacer>
						</div>
						<div class="col-md-12" align="center">
							<h:outputLabel value="Users Remark : "
								style="FONT-WEIGHT: bold; COLOR: #061f56; FONT-SIZE: large; FONT-STYLE: italic;"></h:outputLabel>
						</div>



						<div class="col-md-12">
							<div class="col-md-3" align="right">
								<b><h:outputText
										rendered="#{fL2A_CSD_Permit_Approval_Action.radioType ne 'N'}"
										value="Excise-DEO #{fL2A_CSD_Permit_Approval_Action.districtName} Remark:" /></b>
							</div>
							<div class="col-md-5" align="left">
								<h:inputTextarea value="#{fL2A_CSD_Permit_Approval_Action.deoRemrks}"
									styleClass="form-control"
									rendered="#{fL2A_CSD_Permit_Approval_Action.radioType ne 'N'}"
									style="FONT-STYLE: italic;width: 100%;" readonly="true"></h:inputTextarea>
							</div>
							<div class="col-md-2" align="right">
								<b><h:outputText value="Valid Upto::"
										rendered="#{fL2A_CSD_Permit_Approval_Action.radioType ne 'N'}" /></b>

							</div>
							<div class="col-md-2" align="left">
								<h:inputText styleClass="form-control" readonly="true"
									rendered="#{fL2A_CSD_Permit_Approval_Action.radioType ne 'N'}"
									value="#{fL2A_CSD_Permit_Approval_Action.validUptoDt}">
								</h:inputText>

							</div>


						</div>



						<div class="col-md-12">
							<div class="col-md-3" align="right">
								<b><h:outputText
										rendered="#{fL2A_CSD_Permit_Approval_Action.radioType ne 'R'}"
										value="* Remarks::" /></b>

							</div>
							<div class="col-md-5" align="left">
								<h:inputText style="width: 100%; height : 70px;"
									styleClass="form-control"
									rendered="#{fL2A_CSD_Permit_Approval_Action.radioType ne 'R'}"
									value="#{fL2A_CSD_Permit_Approval_Action.fillRemrks}">
								</h:inputText>
							</div>
							<div class="col-md-2" align="right">
								<b><h:outputText
										rendered="#{fL2A_CSD_Permit_Approval_Action.radioType eq 'N'}"
										value="* Valid Upto::" /></b>

							</div>
							<div class="col-md-2" align="left">
								<rich:calendar
									rendered="#{fL2A_CSD_Permit_Approval_Action.radioType eq 'N'}"
									value="#{fL2A_CSD_Permit_Approval_Action.fillValidDt}">
								</rich:calendar>

							</div>

						</div>
						<div class="row" align="center">
							<rich:spacer height="10px"></rich:spacer>
						</div>
						<div class="col-md-12">
							<div class="col-md-3" align="right"></div>
							<div class="col-md-7" align="left">
								<h:selectBooleanCheckbox
									rendered="false"
									value="#{fL2A_CSD_Permit_Approval_Action.checkBox}" id="chkRememberMe" />
								<h:outputText value="Challan Verified and Certified."
									rendered="false"
									styleClass="generalExciseStyle" style="FONT-WEIGHT: bold;"></h:outputText>

							</div>

						</div>

						<div class="col-md-12" style="height: 15px"></div>
					</div>


					<div class="row" align="center">
						<rich:spacer height="10px"></rich:spacer>
					</div>

					<div class="row" align="center">


						<h:commandButton value="Close" styleClass="btn btn-default btn-sm"
							action="#{fL2A_CSD_Permit_Approval_Action.closeApplication}" />
						<rich:spacer width="10px"></rich:spacer>
						<h:commandButton value="Approve"
							onclick="return confirm('ALERT : The application will be approved. Do you wish to continue?');"
							styleClass="btn btn-success btn-sm" disabled="#{fL2A_CSD_Permit_Approval_Action.approveFlag}"
							rendered="#{fL2A_CSD_Permit_Approval_Action.radioType eq 'N'}"
							action="#{fL2A_CSD_Permit_Approval_Action.approveApplication}" />
						<rich:spacer width="10px"></rich:spacer>

						<h:commandButton value="Reject"
							onclick="return confirm('ALERT : The application will be rejected. Do you wish to continue?');"
							action="#{fL2A_CSD_Permit_Approval_Action.rejectApplication}"
							styleClass="btn btn-danger btn-sm"
							rendered="#{fL2A_CSD_Permit_Approval_Action.radioType ne 'R'}">
						</h:commandButton>
					</div>
					<div class="row" align="center">
						<rich:spacer height="10px"></rich:spacer>
					</div>

				</h:panelGroup>

			</div>


			<h:panelGroup rendered="#{fL2A_CSD_Permit_Approval_Action.showMainPanel_flg}">
				<div style="margin: 10px;">
					<div class="row">
						<div class="col-md-3"></div>
						<div class="col-md-6" align="center">
							<div
								style="padding: 20px; width: 100%; box-shadow: 0 0 4px 1px rgba(0, 0, 0, 0.3);"
								align="center">
								<h2 style="COLOR: #000080;">Application Approved
									Successfully.Your Application Id is
									#{fL2A_CSD_Permit_Approval_Action.popupPermitNmbr}</h2>
							</div>
						</div>
						<div class="col-md-3"></div>

					</div>
					<div class="row" align="center" style="margin-top: 15px;">
						<h:commandButton value="Close" class="btn btn-info"
							action="#{fL2A_CSD_Permit_Approval_Action.closePopup}"></h:commandButton>
					</div>
				</div>
			</h:panelGroup>


          
		</h:form>


	</f:view>
</ui:composition>