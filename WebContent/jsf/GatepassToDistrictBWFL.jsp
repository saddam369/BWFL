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
									value="#{gatepassToDistrictBWFLAction.hidden}"></h:inputHidden>
								<h5>
									<h:outputText
										value="GatePass From Distillery To District WholeSale"
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
												value="#{gatepassToDistrictBWFLAction.bwflName}"
												style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>
									</tr>
									<tr>
										<TD><rich:spacer height="5px"></rich:spacer></TD>
									</tr>
									<TR>

										<TD align="center" colspan="2"><h:outputLabel
												value="#{gatepassToDistrictBWFLAction.bwflAdrs}"
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
																value="#{gatepassToDistrictBWFLAction.dt_date}" />
														</TD>
														<TD colspan="2" align="center">Valid Till : <rich:calendar
																value="#{gatepassToDistrictBWFLAction.validtilldt_date}" />
														</TD>
													</tr>
													<tr>
														<TD><rich:spacer height="10px"></rich:spacer></TD>
													</tr>
													<TR></TR>
													<tr align="center">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="FROM"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<TD align="left"><h:outputText
																value="#{gatepassToDistrictBWFLAction.vch_from}" /></TD>

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
																value="#{gatepassToDistrictBWFLAction.vch_to}"
																valueChangeListener="#{gatepassToDistrictBWFLAction.listMethod}"
																onchange="this.form.submit()">

																<f:selectItem itemValue="DW"
																	itemLabel="District Warehouse" />
																<f:selectItem itemValue="BRC"
																	itemLabel="Bar/Restaurant/Club" />


															</h:selectOneRadio> </TD>


														<td align="right" style="padding-right: 10px;">
														<h:outputText value="FL2"
														rendered="#{gatepassToDistrictBWFLAction.licNoflg}"
																 style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText> 
															<h:outputText value="FL2B" 
															rendered="#{gatepassToDistrictBWFLAction.licNoflg1}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:outputText value="FL2/FL2B" 
															rendered="#{gatepassToDistrictBWFLAction.licNoflg2}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>

														</td>
														<td align="left">
															<h:selectOneMenu rendered="#{gatepassToDistrictBWFLAction.drpdwnFlg}"
																value="#{gatepassToDistrictBWFLAction.vch_to_lic_no}"
																valueChangeListener="#{gatepassToDistrictBWFLAction.waredrpMethod}"
																onchange="this.form.submit()"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrictBWFLAction.licNmbrList}" />
															</h:selectOneMenu>
															
															<h:selectOneMenu rendered="#{gatepassToDistrictBWFLAction.drpdwnFlg1}"
																value="#{gatepassToDistrictBWFLAction.brc_to_lic}"
																valueChangeListener="#{gatepassToDistrictBWFLAction.drpdownMethod}"
																onchange="this.form.submit()"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrictBWFLAction.brclicNmbrList}" />
															</h:selectOneMenu>
														</td>


													</tr>
													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>
													<tr style="padding-bottom: 10px;">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Licensee District"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:selectOneMenu
																value="#{gatepassToDistrictBWFLAction.districtLic}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrictBWFLAction.districtList}" />
															</h:selectOneMenu></td>
														
														
													</tr>
													
													
													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>
													<tr style="padding-bottom: 10px;">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Licensee Name"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{gatepassToDistrictBWFLAction.licenseeName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Licensee Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{gatepassToDistrictBWFLAction.licenseeAdrs}"
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
																value="Route Details"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{gatepassToDistrictBWFLAction.routeDtl}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle No."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{gatepassToDistrictBWFLAction.vehicleNo}"
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
																value="#{gatepassToDistrictBWFLAction.vehicleDrvrName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle Agency Name And Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{gatepassToDistrictBWFLAction.vehicleAgencyNmAdrs}"
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
																value="#{gatepassToDistrictBWFLAction.district1}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrictBWFLAction.districtList}" />
															</h:selectOneMenu></td>


														<td align="left"><h:selectOneMenu
																value="#{gatepassToDistrictBWFLAction.district2}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrictBWFLAction.districtList}" />
															</h:selectOneMenu></td>

														<td align="left"><h:selectOneMenu
																value="#{gatepassToDistrictBWFLAction.district3}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrictBWFLAction.districtList}" />
															</h:selectOneMenu></td>
													</tr>

													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>

													<tr style="padding-bottom: 10px; padding-top: 10px;">
														<td></td>
														<TD align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Gross Weight "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:inputText
																value="#{gatepassToDistrictBWFLAction.grossWeight}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">
																<a4j:support reRender="net-weight" event="onkeyup"></a4j:support>
															</h:inputText></TD>

														<td align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Tier Weight "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:inputText
																value="#{gatepassToDistrictBWFLAction.tierWeight}"
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
																	value="#{gatepassToDistrictBWFLAction.netWeight}"
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
									value="#{gatepassToDistrictBWFLAction.displaylist}" var="list">
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
											<a4j:support event="onblur" reRender="bot,dutyy,totalduty"></a4j:support>
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


<rich:column>
										<f:facet name="header">
											<h:outputText value="Duty"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.db_duty}">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Calculated Duty"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.calculated_duty}" id="dutyy">
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

										<td align="right"><a4j:commandButton disabled="true"
												styleClass="btn btn-danger" id="totalduty"
												actionListener="#{gatepassToDistrictBWFLAction.calculateTotalDuty}"
												reRender="totalduty"
												value="Total Duty : #{gatepassToDistrictBWFLAction.db_total_value}">
											</a4j:commandButton></td>
										
									</tr>
									<tr></tr>
								</table>
							</td>
						</tr>
						<tr>
							<td style="text-align: center;"><h:commandButton
									styleClass="btn btn-primary"
									action="#{gatepassToDistrictBWFLAction.saveMethod}"
									value="Save"
									onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;"></h:commandButton>
								<rich:spacer width="10px"></rich:spacer> <h:commandButton
									styleClass="btn btn-warning"
									action="#{gatepassToDistrictBWFLAction.clearAll}" value="Reset"></h:commandButton></td>

						</tr>
						</TD>
						</TR>
					</TABLE>

					<div class="row">
						<rich:dataTable align="center" id="table55" rows="10" width="100%"
							var="list11" value="#{gatepassToDistrictBWFLAction.table2List }"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2">

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="Sr.No." />
								</f:facet>
								<center>
									<h:outputText value="#{list11.serialNo}" />
								</center>
							</rich:column>




							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="Date Created" />
								</f:facet>
								<center>
									<h:outputText value="#{list11.currentDate}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="License Type" />
								</f:facet>
								<center>
									<h:outputText value="#{list11.vchTO}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="FL2 Gatepass No." />
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
									<h:outputText value="#{list11.licenseNo}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">

								</f:facet>
								<center>
									<h:commandButton styleClass="btn btn-success" value="Print"
										actionListener="#{gatepassToDistrictBWFLAction.printReport }"></h:commandButton>

									<h:outputLink styleClass="outputLinkEx"
										value="/doc/ExciseUp/WholeSale/pdf//#{gatepassToDistrictBWFLAction.pdfname}"
										target="_blank">
										<h:outputText styleClass="outputText" id="text223"
											value="View Report" rendered="#{list11.printFlag}"
											style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
									</h:outputLink>




								</center>
							</rich:column>

							<f:facet name="footer">
								<rich:datascroller for="table55" />
							</f:facet>
						</rich:dataTable>
					</div>

				</div>
			</div>
		</h:form>
	</f:view>
</ui:composition>