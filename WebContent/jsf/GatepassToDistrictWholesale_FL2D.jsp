<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<h:form>
			<div class="panel panel-default">
				<div class="panel-body">
					<TABLE width="100%" align="center">
						<TR>
							<TD>
								<TABLE width="80%">
									<TBODY>
										<TR>
											<TD align="left"><h3>
													<h:messages errorStyle="color:red" layout="table"
														id="messages" infoStyle="color:green">
													</h:messages>
												</h3></TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
						</TR>
						<TR align="center" style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
							<TD><h:inputHidden
									value="#{gatepassToDistrictWholesale_FL2DAction.hidden}"></h:inputHidden>
								<h5>
									<h:outputText value="GatePass From FL-2D To District WholeSale 2019-20"
										style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;"></h:outputText>
								</h5></TD>
						</TR>
						<TR align="center" style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
							<TD><rich:separator lineType="dashed"></rich:separator></TD>
						</TR>
						<TR align="center">

							<TD align="center">
								<TABLE width="100%" align="center">

									<tr>
										<TD><rich:spacer height="10px"></rich:spacer></TD>
									</tr>
									<tr>
										<TD align="center" colspan="2"><h:outputLabel
												value="#{gatepassToDistrictWholesale_FL2DAction.name}"
												style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>



									</tr>
									<tr>
										<TD><rich:spacer height="5px"></rich:spacer></TD>
									</tr>
									<TR>

										<TD align="center" colspan="2"><h:outputLabel
												value="#{gatepassToDistrictWholesale_FL2DAction.address}"
												style="COLOR: #000000; FONT-SIZE: medium;"></h:outputLabel></TD>

									</TR>
									<tr>
										<TD><rich:spacer height="10px"></rich:spacer></TD>
									</tr>
									<TR align="center"
										style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
										<TD><rich:separator lineType="dashed"></rich:separator></TD>
									</TR>
									<tr>
										<TD><rich:spacer height="30px"></rich:spacer></TD>
									</tr>
									<TR>
										<td><table>
												<tr>
													<td></td>
												</tr>
											</table></td>
									</TR>
									<TR>
									</TR>
									<TR>
									</TR>
									<tr>
										<td></td>
										<td></td>
									</tr>
									<TR>
										<TD align="center" width="100%">
											<TABLE align="center" width="100%">
												<TBODY>
													<TR align="center">



													</TR>
												</TBODY>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD></TD>
									</TR>
									<TR>
										<TD></TD>
									</TR>
									<TR align="center">
										<TD colspan="1" align="center">
											<TABLE width="80%" align="center"
												style="background-color: #D0D3D4; padding: 5;">
												<TBODY align="center">
													<tr>
														<td><rich:spacer height="10px;"></rich:spacer></td>
													</tr>
													<tr>
														<TD colspan="2" align="center">Date : <rich:calendar
																value="#{gatepassToDistrictWholesale_FL2DAction.dt_date}" />
														</TD>
														<TD colspan="2" align="center">Valid Till : <rich:calendar
																value="#{gatepassToDistrictWholesale_FL2DAction.validtilldt_date}" />
														</TD>
													</tr>
													<tr>
														<TD><rich:spacer height="10px"></rich:spacer></TD>
													</tr>
													<TR></TR>


													<tr align="center">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="FROM :"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<TD align="left"><h:selectOneRadio rendered="false"
																value="#{gatepassToDistrictWholesale_FL2DAction.vch_from}"
																valueChangeListener="#{gatepassToDistrictWholesale_FL2DAction.listMethod}"
																onchange="this.form.submit()">
																<f:selectItem itemValue="FL1" itemLabel="FL1" />
																<f:selectItem itemValue="FL1A" itemLabel="FL1A" />
															</h:selectOneRadio> <h:outputText
																value="#{gatepassToDistrictWholesale_FL2DAction.vch_from}" />


														</TD>

														<td align="right" style="padding-right: 10px;"></td>
														<td align="left"></td>
													</tr>
													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>
													<tr align="center">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="TO" style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<TD align="left"><h:selectOneRadio
																value="#{gatepassToDistrictWholesale_FL2DAction.vch_to}"
																onchange="this.form.submit()"
																valueChangeListener="#{gatepassToDistrictWholesale_FL2DAction.listMethod}">
																<f:selectItem itemValue="FL2" itemLabel="FL2" />
																<f:selectItem itemValue="FL2B" itemLabel="FL2B" />
																<f:selectItem itemValue="HBR"
																	itemLabel="Bar/Restaurant/Club" />
																<f:selectItem itemValue="RT" itemLabel="Retailer" />


															</h:selectOneRadio> <h:selectOneMenu
																value="#{gatepassToDistrictWholesale_FL2DAction.districtLic}"
																onchange="this.form.submit();"
																style="width: 100px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrictWholesale_FL2DAction.districtList}" />
															</h:selectOneMenu></TD>


														<td align="right" style="padding-right: 10px;"><h:outputText
																value="License No."
																rendered="#{gatepassToDistrictWholesale_FL2DAction.drpdwnFlg3}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
																
																<h:outputText
																value="Shop Id."
																	rendered="#{gatepassToDistrictWholesale_FL2DAction.vch_to eq 'RT'}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText>

														</td>
														<td align="left"><h:inputText
																rendered="#{gatepassToDistrictWholesale_FL2DAction.drpdwnFlg}"
																value="#{gatepassToDistrictWholesale_FL2DAction.vch_to_lic_no}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText>
															<h:outputLabel rendered="#{gatepassToDistrictWholesale_FL2DAction.drpdwnFlg}">
															*Enter only digits w/o HBR.
															</h:outputLabel>
															
															 <h:selectOneMenu
																rendered="#{gatepassToDistrictWholesale_FL2DAction.drpdwnFlg1}"
																value="#{gatepassToDistrictWholesale_FL2DAction.brc_to_lic}"
																valueChangeListener="#{gatepassToDistrictWholesale_FL2DAction.drpdownMethod}"
																onchange="this.form.submit()"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrictWholesale_FL2DAction.brclicNmbrList}" />
															</h:selectOneMenu>
															
															<h:inputText
																value="#{gatepassToDistrictWholesale_FL2DAction.shopno}"
															     rendered="#{gatepassToDistrictWholesale_FL2DAction.vch_to eq 'RT'}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText>
															
															
															</td>
															
															<td align="left">
															
														<h:commandButton
																	styleClass="btn btn-primary"
																	rendered="#{gatepassToDistrictWholesale_FL2DAction.drpdwnFlg}"
																		action="#{gatepassToDistrictWholesale_FL2DAction.fetch1}"
															value="Validate"></h:commandButton>
																
														<h:commandButton
																	styleClass="btn btn-primary"
																	rendered="#{gatepassToDistrictWholesale_FL2DAction.vch_to eq 'RT'}"
																		action="#{gatepassToDistrictWholesale_FL2DAction.fetch}"
															value="Validate"></h:commandButton>
														</td>
														
															
														


													</tr>


													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>
													
													
													
													
												<tr>
												<td align="right">
														
														<h:outputText
																value="Shop Name."
																	rendered="#{gatepassToDistrictWholesale_FL2DAction.flagshop}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText>
														
														</td>
												<td align="left">
														<h:inputText
																disabled="true"
																value="#{gatepassToDistrictWholesale_FL2DAction.shopName}"
																rendered="#{gatepassToDistrictWholesale_FL2DAction.flagshop}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText>
												</td>
												
												
												</tr>	
													
													
													
													
													
													
													
													
													
													<tr style="padding-bottom: 10px;">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Firm Name"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																rendered="#{gatepassToDistrictWholesale_FL2DAction.flagshop}"
																value="#{gatepassToDistrictWholesale_FL2DAction.licenseeName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText>
															
															<h:inputText
															    rendered="#{gatepassToDistrictWholesale_FL2DAction.flagshopS}"
																value="#{gatepassToDistrictWholesale_FL2DAction.licenceenm}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText>
															
															
															
															</td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																rendered="#{gatepassToDistrictWholesale_FL2DAction.flagshop}"
																value="#{gatepassToDistrictWholesale_FL2DAction.licenseeAdrs}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea>
															
															
															<h:inputTextarea
															  rendered="#{gatepassToDistrictWholesale_FL2DAction.flagshopS}"
																value="#{gatepassToDistrictWholesale_FL2DAction.licenceeadd}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea>
															
															</td>
													</tr>

													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>

													<tr style="padding-bottom: 10px;">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Route Details"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{gatepassToDistrictWholesale_FL2DAction.routeDtl}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle No."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{gatepassToDistrictWholesale_FL2DAction.vehicleNo}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
													</tr>

													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>

													<tr style="padding-bottom: 10px;">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Vehicle Driver Name"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{gatepassToDistrictWholesale_FL2DAction.vehicleDrvrName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle Agency Name And Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{gatepassToDistrictWholesale_FL2DAction.vehicleAgencyNmAdrs}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea></td>
													</tr>

													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>

													<tr style="padding-bottom: 10px;">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Major Districts In Route"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:selectOneMenu
																value="#{gatepassToDistrictWholesale_FL2DAction.district1}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrictWholesale_FL2DAction.districtList}" />
															</h:selectOneMenu></td>


														<td align="left"><h:selectOneMenu
																value="#{gatepassToDistrictWholesale_FL2DAction.district2}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrictWholesale_FL2DAction.districtList}" />
															</h:selectOneMenu></td>

														<td align="left"><h:selectOneMenu
																value="#{gatepassToDistrictWholesale_FL2DAction.district3}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrictWholesale_FL2DAction.districtList}" />
															</h:selectOneMenu></td>
													</tr>

													<tr>
														<td colspan="4"
															style="padding-top: 10px; padding-bottom: 10px;"></td>
													</tr>

													<tr style="padding-bottom: 10px; padding-top: 10px;">
														<td></td>
														<TD align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Gross Weight "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:inputText
																value="#{gatepassToDistrictWholesale_FL2DAction.grossWeight}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">
																<a4j:support reRender="net-weight" event="onkeyup"></a4j:support>
															</h:inputText></TD>

														<td align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Tier Weight "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:inputText
																value="#{gatepassToDistrictWholesale_FL2DAction.tierWeight}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">
																<a4j:support reRender="net-weight" event="onkeyup"></a4j:support>
															</h:inputText></td>
														<td align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Net Weight "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<a4j:outputPanel id="net-weight">
																<h:inputText
																	value="#{gatepassToDistrictWholesale_FL2DAction.netWeight}"
																	disabled="true" styleClass="generalHeaderOutputTable"
																	style="width:250px">
																</h:inputText>
															</a4j:outputPanel></td>
													</tr>
													<tr>
														<td colspan="4"
															style="padding-top: 10px; padding-bottom: 10px;"></td>
													</tr>

												</TBODY>
											</TABLE>
										</TD>
									</TR>
									<tr>
										<td colspan="4" align="center">
											<table width="90%" align="center">

											</table>
										</td>
									</tr>
									<TR>
										<TD></TD>
									</TR>
									<TR>
										<TD></TD>
									</TR>
									<tr align="center">
										<td colspan="4"></td>
									</tr>
									<tr align="center">
										<td align="center">
											<table align="center" width="80%">

												<tr>
													<td></td>
												</tr>
											</table> <rich:spacer height="30px;"></rich:spacer>
											<table>
												<tr align="center">
													<td width="100%"></td>
												</tr>
												<tr align="center">
												</tr>
											</table>
										</td>
									</tr>
								</TABLE> <rich:dataTable columnClasses="columnClass1"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2" styleClass="DataTable"
									id="table3" rows="10" width="100%"
									value="#{gatepassToDistrictWholesale_FL2DAction.displaylist}"
									var="list">
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Brand"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.vch_brand}">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Size"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.size}">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Available Bottle"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.int_bottle_avaliable}">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Available Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.boxAvailable}">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Breakage"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.breakage}">
											<a4j:support reRender="duty" event="onblur"></a4j:support>
										</h:inputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Balance Bottle"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.balance}" id="duty">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Batch No."
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.batchNo}">
										</h:inputText>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Dispatch Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.dispatchbox}">
											<a4j:support event="onblur"
												reRender="bot,duty1,addduty,disbtl,totalduty,totaladdduty"></a4j:support>

										</h:inputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Dispatch Bottles"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.dispatchbottls}" id="bot"
											disabled="true">
										</h:inputText>
									</rich:column>



									<rich:column rendered="true">
										<f:facet name="header">
											<h:outputText value="Permit Fee"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.db_duty}">
										</h:outputText>
									</rich:column>

									<rich:column rendered="false">
										<f:facet name="header">
											<h:outputText value="Add Duty"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.db_add_duty}">
										</h:outputText>
									</rich:column>


									<rich:column rendered="true">
										<f:facet name="header">
											<h:outputText value="Calculated Permit Fee"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.calculated_duty}" id="duty1">
											<f:convertNumber maxFractionDigits="2"
												pattern="#############0.00" />
										</h:outputText>
									</rich:column>

									<rich:column rendered="false">
										<f:facet name="header">
											<h:outputText value="Calculated Additional Duty"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.calculated_add_duty}" id="addduty">
											<f:convertNumber maxFractionDigits="2"
												pattern="#############0.00" />
										</h:outputText>
									</rich:column>

									<f:facet name="footer">
										<rich:datascroller for="table3"></rich:datascroller>
									</f:facet>
								</rich:dataTable>
						<tr align="right">
							<td>
								<table>
									<tr>

										<td align="right"><a4j:commandButton rendered="true"
												disabled="true" styleClass="btn btn-danger" id="totalduty"
												actionListener="#{gatepassToDistrictWholesale_FL2DAction.calculateTotalDuty}"
												reRender="totalduty"
												value="Total Permit Fee : #{gatepassToDistrictWholesale_FL2DAction.db_total_value}">
											</a4j:commandButton></td>
										<td align="right"><a4j:commandButton rendered="false"
												disabled="true" styleClass="btn btn-danger"
												id="totaladdduty"
												actionListener="#{gatepassToDistrictWholesale_FL2DAction.calculateTotalAddDuty}"
												reRender="totalduty"
												value="Total Additional Duty : #{gatepassToDistrictWholesale_FL2DAction.db_total_add_value}">
											</a4j:commandButton></td>
									</tr>
									<tr></tr>
								</table>
							</td>
						</tr>







						<tr align="right">
							<td>
								<table>

									<tr></tr>
								</table>
							</td>
						</tr>
						<tr>
							<td style="text-align: center;"><h:commandButton
									styleClass="btn btn-primary"
									action="#{gatepassToDistrictWholesale_FL2DAction.saveMethod}"
									value="Save"
									onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;"></h:commandButton>
								<rich:spacer width="10px"></rich:spacer> <h:commandButton
									styleClass="btn btn-warning"
									action="#{gatepassToDistrictWholesale_FL2DAction.clearAll}"
									value="Reset"></h:commandButton></td>
						</tr>


						</TD>
						</TR>
						<tr>
							<td><rich:dataTable align="center" id="table55" rows="10"
									width="100%" var="list11"
									value="#{gatepassToDistrictWholesale_FL2DAction.getListWholesale}"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2">

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sr.No." />
										</f:facet>
										<center>
											<h:outputText value="#{list11.sno}" />
										</center>
									</rich:column>




									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Date Created" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.dt_date}" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="License Type" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.vch_to}" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="FL2D Gatepass No." />
										</f:facet>
										<center>
											<h:outputText value="#{list11.gatepassNo}" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="FL2 License No." />
										</f:facet>
										<center>
											<h:outputText value="#{list11.vch_to_lic_no}" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">

										</f:facet>
										<center>

											<h:commandButton styleClass="btn btn-success"
												value="Print Draft" rendered="#{list11.printDraft}"
												actionListener="#{gatepassToDistrictWholesale_FL2DAction.printDraftreport}"></h:commandButton>

											<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{gatepassToDistrictWholesale_FL2DAction.draftpdfname}"
												target="_blank">
												<h:outputText styleClass="outputText" id="text224"
													value="View Draft Report"
													rendered="#{list11.draftprintFlag}"
													style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
											</h:outputLink>

											<h:commandButton styleClass="btn btn-success" value="Print"
												disabled="false"
												actionListener="#{gatepassToDistrictWholesale_FL2DAction.printreport}"
												rendered="#{list11.finalizePrint}"></h:commandButton>

											<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{gatepassToDistrictWholesale_FL2DAction.pdfname}"
												target="_blank">
												<h:outputText styleClass="outputText" id="text223"
													value="View Report" rendered="#{list11.printFlag}"
													style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
											</h:outputLink>

											<h:commandButton styleClass="btn btn-success"
												value="Scan and Upload" rendered="#{list11.draftprintFlag}"
												actionListener="#{gatepassToDistrictWholesale_FL2DAction.scanAndUpload}">
											</h:commandButton>

										</center>
									</rich:column>
	<rich:column>
										<f:facet name="header">
										</f:facet>
										<center>
											<h:commandButton value="Cancel Gatepass"
												styleClass="btn btn-danger" rendered="#{list11.printDraft}"
												actionListener="#{gatepassToDistrictWholesale_FL2DAction.cancelGatepass}">
											</h:commandButton>
										</center>
									</rich:column>

									<f:facet name="footer">
										<rich:datascroller for="table55" />
									</f:facet>
								</rich:dataTable></td>
						</tr>
					</TABLE>
					<div class="row">
						<rich:spacer height="10px"></rich:spacer>
					</div>
					<table align="center">
						<tr>
							<TD align="center"
								style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
									value="" style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</TD>

							<TD align="center"><h:selectOneRadio
									value="#{gatepassToDistrictWholesale_FL2DAction.exclcsv}"
									rendered="#{gatepassToDistrictWholesale_FL2DAction.gatePassFlag}"
									onchange="this.form.submit()">
									
									<f:selectItem itemValue="C" itemLabel="Upload CSV" />

								</h:selectOneRadio></TD>
						</tr>
						<tr align="left">
							<td style="FONT-WEIGHT: bold; color: Green;" align="left"><h:outputText
									value="Uploading for Gatepass Number::"
									rendered="#{gatepassToDistrictWholesale_FL2DAction.gatePassFlag}"
									style="FONT-SIZE: medium;" /></td>
							<td style="FONT-SIZE: large; color: Red"><h:outputText
									value="#{gatepassToDistrictWholesale_FL2DAction.scanGatePassNo}"
									rendered="#{gatepassToDistrictWholesale_FL2DAction.gatePassFlag}" /></td>
							<TD><h:outputLink value="/doc/ExciseUp/ExcelFormat.pdf"
									rendered="#{gatepassToDistrictWholesale_FL2DAction.gatePassFlag}"
									target="_blank">
									<h:graphicImage value="/img/i.png" style="width:40px;"></h:graphicImage>
								</h:outputLink></TD>

							<td><rich:fileUpload addControlLabel="Add File" id="link3"
									fileUploadListener="#{gatepassToDistrictWholesale_FL2DAction.uploadExcel}"
									rendered="#{gatepassToDistrictWholesale_FL2DAction.gatePassFlag and gatepassToDistrictWholesale_FL2DAction.exclcsv eq 'E'}"
									maxFilesQuantity="1" listHeight="40" listWidth="250"
									clearControlLabel="clear" stopControlLabel="Stop"
									ontyperejected="if (!confirm(' ONLY .xlsx type file ALLOWED !!!')) return false"
									acceptedTypes="xlsx" clearAllControlLabel="Clear">
								</rich:fileUpload></td>

							<td><h:commandButton value="Upload"
									rendered="#{gatepassToDistrictWholesale_FL2DAction.gatePassFlag and gatepassToDistrictWholesale_FL2DAction.exclcsv eq 'E'}"
									styleClass="btn btn-primary"
									action="#{gatepassToDistrictWholesale_FL2DAction.importExcel}">
								</h:commandButton></td>

							<td><rich:fileUpload addControlLabel="Add File" id="link34"
									fileUploadListener="#{gatepassToDistrictWholesale_FL2DAction.uploadCsv}"
									rendered="#{gatepassToDistrictWholesale_FL2DAction.gatePassFlag and gatepassToDistrictWholesale_FL2DAction.exclcsv eq 'C'}"
									maxFilesQuantity="1" listHeight="40" listWidth="250"
									clearControlLabel="clear" stopControlLabel="Stop"
									ontyperejected="if (!confirm(' ONLY .csv type file ALLOWED !!!')) return false"
									acceptedTypes="csv" clearAllControlLabel="Clear">
								</rich:fileUpload></td>

							<td><h:commandButton value="Upload CSV"
									styleClass="btn btn-primary"
									rendered="#{gatepassToDistrictWholesale_FL2DAction.gatePassFlag and gatepassToDistrictWholesale_FL2DAction.exclcsv eq 'C'}"
									action="#{gatepassToDistrictWholesale_FL2DAction.csvSubmit }">
								</h:commandButton></td>

						</tr>
					</table>
					<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<div>
						<rich:dataTable align="center" id="table57" rows="10" width="100%"
							var="listk"
							rendered="#{gatepassToDistrictWholesale_FL2DAction.tableFlag}"
							value="#{gatepassToDistrictWholesale_FL2DAction.getVal }"
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
							
							
								<rich:column sortBy="#{listk.cascodeMatching}" filterBy="#{listk.cascodeMatching}">
								<f:facet name="header">
									<h:outputLabel value="Status" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.cascodeMatching}" />
								</center>
							</rich:column>		

							<rich:column>
								<f:facet name="header">

								</f:facet>
							</rich:column>

							<f:facet name="footer">
								<rich:datascroller for="table57" />
							</f:facet>
						</rich:dataTable>
					</div>

					<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>

					<table align="center">
						<tr>
							<td><h:commandButton value="Finalize"
									styleClass="btn btn-primary"
									rendered="#{gatepassToDistrictWholesale_FL2DAction.tableFlag}"
									disabled="#{gatepassToDistrictWholesale_FL2DAction.finalSubmitFlag}"
									action="#{gatepassToDistrictWholesale_FL2DAction.finalSubmit}">

								</h:commandButton> <h:commandButton value="Cancel" styleClass="btn btn-danger"
									rendered="#{gatepassToDistrictWholesale_FL2DAction.tableFlag}"
									action="#{gatepassToDistrictWholesale_FL2DAction.delete}">
								</h:commandButton></td>

						</tr>
					</table>

				</div>
			</div>
		</h:form>
	</f:view>
</ui:composition>