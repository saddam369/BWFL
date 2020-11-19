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
							<TD><h:inputHidden value="#{imflOldStockFL36.hidden}"></h:inputHidden>
								<h5>
									<h:outputText
										value="Gatepass to Manufacture WholseSale for 2017-18 "
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
												value="#{imflOldStockFL36.vch_bwfl_name}"
												style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>



									</tr>
									<tr>
										<TD><rich:spacer height="5px"></rich:spacer></TD>
									</tr>
									<TR>

										<TD align="center" colspan="2"><h:outputLabel
												value="#{imflOldStockFL36.vch_bwfl_address}"
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
																value="#{imflOldStockFL36.dt_date}" />
														</TD>
														<TD colspan="2" align="center">Valid Till : <rich:calendar
																value="#{imflOldStockFL36.validtill_date}" />
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
																value="#{imflOldStockFL36.vch_from}" /></TD>

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
																value="#{imflOldStockFL36.vch_to}"
																valueChangeListener="#{imflOldStockFL36.listMethod}"
																onchange="this.form.submit()">

																<f:selectItem itemValue="DW"
																	itemLabel="District Warehouse" />
																<f:selectItem itemValue="BRC"
																	itemLabel="Bar/Restaurant/Club" />


															</h:selectOneRadio></TD>


														<td align="right" style="padding-right: 10px;"><h:outputText
																value="FL2"
																rendered="#{imflOldStockFL36.licNoflg}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText> <h:outputText value="FL2B"
																rendered="#{imflOldStockFL36.licNoflg1}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:outputText value="FL2/FL2B"
																rendered="#{imflOldStockFL36.licNoflg2}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>

														</td>
														<td align="left"><h:selectOneMenu
																rendered="#{imflOldStockFL36.drpdwnFlg}"
																value="#{imflOldStockFL36.vch_to_lic_no}"
																valueChangeListener="#{imflOldStockFL36.waredrpMethod}"
																onchange="this.form.submit()"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{imflOldStockFL36.licNmbrList}" />
															</h:selectOneMenu> <h:selectOneMenu
																rendered="#{imflOldStockFL36.drpdwnFlg1}"
																value="#{imflOldStockFL36.brc_to_lic}"
																valueChangeListener="#{imflOldStockFL36.drpdownMethod}"
																onchange="this.form.submit()"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{imflOldStockFL36.brclicNmbrList}" />

															</h:selectOneMenu></td>


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
														<td align="left"><h:inputText disabled="true"
																value="#{imflOldStockFL36.licenseeDistrict}"
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
																value="Licensee Name"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText disabled="true"
																value="#{imflOldStockFL36.licenseeName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Licensee Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea disabled="true"
																value="#{imflOldStockFL36.licenseeAdrs}"
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
																value="#{imflOldStockFL36.routeDtl}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle No."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{imflOldStockFL36.vehicleNo}"
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
																value="#{imflOldStockFL36.vehicleDrvrName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle Agency Name And Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{imflOldStockFL36.vehicleAgencyNmAdrs}"
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
																value="#{imflOldStockFL36.district1}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{imflOldStockFL36.districtList}" />
															</h:selectOneMenu></td>


														<td align="left"><h:selectOneMenu
																value="#{imflOldStockFL36.district2}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{imflOldStockFL36.districtList}" />
															</h:selectOneMenu></td>

														<td align="left"><h:selectOneMenu
																value="#{imflOldStockFL36.district3}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{imflOldStockFL36.districtList}" />
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
																value="#{imflOldStockFL36.grossWeight}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">
																<a4j:support reRender="net-weight" event="onkeyup"></a4j:support>
															</h:inputText></TD>

														<td align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Tier Weight "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:inputText
																value="#{imflOldStockFL36.tierWeight}"
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
																	value="#{imflOldStockFL36.netWeight}"
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
									id="table3" width="100%"
									value="#{imflOldStockFL36.displaylist}" var="list">
									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sr.No" styleClass="generalHeaderStyle" />
										</f:facet>
										<h:outputText value="#{list.slno}" />
									</rich:column>

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
											<a4j:support reRender="duty1" event="onblur"></a4j:support>
										</h:inputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Balance Bottle"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.balance}" id="duty1">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Dispatch Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.dispatchBoxes}">
											<a4j:support
												reRender="bot,disbtl"
												event="onblur"></a4j:support>
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
											<h:outputText value="Batch No."
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.batchNo}">
										</h:inputText>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Duty"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.db_duty}">
										<a4j:support
												reRender=" totalduty,totaladdduty,addduty,duty"
												event="onblur"></a4j:support>
										</h:inputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Add Duty"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.db_add_duty}">
										<a4j:support
												reRender=" totalduty,totaladdduty,addduty,duty"
												event="onblur"></a4j:support>
										</h:inputText>
									</rich:column>


									<rich:column>
										<f:facet name="header">
											<h:outputText value="Calculated Duty"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.calculated_duty}"  id="duty">
											 
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Calculated Additional Duty"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.calculated_add_duty}"  id="addduty">
											 
										</h:outputText>
									</rich:column>

									<f:facet name="footer">

									</f:facet>
								</rich:dataTable>
						<tr align="right">
							<td>
								<table>
									<tr>

										<td align="right"><a4j:commandButton
												styleClass="btn btn-danger" id="totalduty"
												actionListener="#{imflOldStockFL36.calculateTotalDuty}"
												reRender="totalduty"
												value="Total Duty : #{imflOldStockFL36.db_total_value}">


											</a4j:commandButton></td>
										<td align="right"><a4j:commandButton
												styleClass="btn btn-danger" id="totaladdduty"
												actionListener="#{imflOldStockFL36.calculateTotalAddDuty}"
												reRender="totalduty"
												value="Total Additional Duty : #{imflOldStockFL36.db_total_add_value}">

											</a4j:commandButton></td>
									</tr>
									<tr></tr>
								</table>
							</td>
						</tr>
						<tr>
							<td style="text-align: center;">
								<div>
									<div>
										<h:commandButton styleClass="btn btn-primary"
											action="#{imflOldStockFL36.saveMethod}" value="Save"
											onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;"></h:commandButton>
										<rich:spacer width="10px"></rich:spacer>
										<h:commandButton styleClass="btn btn-warning"
											action="#{imflOldStockFL36.clearAll}" value="Reset"></h:commandButton>



									</div>
								</div>
							</td>
						</tr>

						</TD>
						</TR>
						<tr>
							<td><rich:dataTable align="center" id="table55" rows="20"
									width="100%" var="list11"
									value="#{imflOldStockFL36.getListWholesale}"
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


									<rich:column sortBy="#{list11.vch_gatepass_no}"
										filterBy="#{list11.vch_gatepass_no}">
										<f:facet name="header">
											<h:outputLabel value="Gate Pass No" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.vch_gatepass_no}" />
										</center>
									</rich:column>

									<rich:column sortBy="#{list11.dt_date}">
										<f:facet name="header">
											<h:outputLabel value="Date " />
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
											<h:outputLabel value="License No." />
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
												actionListener="#{imflOldStockFL36.printDraftReport}">
											</h:commandButton>
											<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{imflOldStockFL36.draftpdfname}"
												target="_blank">
												<h:outputText styleClass="outputText" id="text224"
													value="View Draft Report"
													rendered="#{list11.draftprintFlag}"
													style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
											</h:outputLink>

											<h:commandButton value="Print" styleClass="btn btn-success"
													rendered="#{list11.finalizePrint}"
												actionListener="#{imflOldStockFL36.printReport}">
											</h:commandButton>

											<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{imflOldStockFL36.pdfName}"
												target="_blank">
												<h:outputText styleClass="outputText" id="text223"
													value="View Report" rendered="#{list11.myFlagDt}"
													style="color: blue; font-family: serif; font-size: 12pt" />
											</h:outputLink>

											<h:commandButton styleClass="btn btn-success"
												value="Scan and Upload" rendered="#{list11.printDraft}"
												actionListener="#{imflOldStockFL36.scanAndUpload}">
											</h:commandButton>
										</center>
									</rich:column>

									<f:facet name="footer">
										<rich:datascroller for="table55" />
									</f:facet>
								</rich:dataTable></td>
						</tr>
					</TABLE>

					<table align="center">

						<tr align="left">
							<td style="FONT-WEIGHT: bold; color: Green;" align="left"><h:outputText
									value="Upload CSV for Gatepass Number::"
									rendered="#{imflOldStockFL36.gatePassFlag}"
									style="FONT-SIZE: medium;" /></td>
							<td style="FONT-SIZE: large; color: Red"><h:outputText
									value="#{imflOldStockFL36.scanGatePassNo}"
									rendered="#{imflOldStockFL36.gatePassFlag}" /></td>
							<TD><h:outputLink value="/doc/ExciseUp/ExcelFormat.pdf"
									target="_blank">
									<h:graphicImage value="/img/i.png" style="width:40px;"
										rendered="#{imflOldStockFL36.gatePassFlag}"></h:graphicImage>
								</h:outputLink></TD>

							<td><rich:fileUpload addControlLabel="Add File" id="link34"
									fileUploadListener="#{imflOldStockFL36.uploadCsv}"
									rendered="#{imflOldStockFL36.gatePassFlag}"
									maxFilesQuantity="1" listHeight="40" listWidth="250"
									clearControlLabel="clear" stopControlLabel="Stop"
									ontyperejected="if (!confirm(' ONLY .csv type file ALLOWED !!!')) return false"
									acceptedTypes="csv" clearAllControlLabel="Clear">
								</rich:fileUpload></td>

							<td><h:commandButton value="Upload CSV"
									styleClass="btn btn-primary"
									rendered="#{imflOldStockFL36.gatePassFlag }"
									action="#{imflOldStockFL36.csvSubmit}">
								</h:commandButton></td>

						</tr>
					</table>

					<div>
						<rich:dataTable align="center" id="table57" rows="10" width="100%"
							var="listk" rendered="#{imflOldStockFL36.gatePassFlag}"
							value="#{imflOldStockFL36.getVal}"
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

								</f:facet>
							</rich:column>

							<f:facet name="footer">
								<rich:datascroller for="table57" />
							</f:facet>
						</rich:dataTable>
						<table align="center">
						<tr>
							<td><h:commandButton value="Finalise"
									styleClass="btn btn-primary"
									rendered="#{imflOldStockFL36.gatePassFlag}"
									disabled="#{imflOldStockFL36.bottlingNotDoneFlag }"
									action="#{imflOldStockFL36.finalSubmit}">

								</h:commandButton> <h:commandButton value="Cancel" styleClass="btn btn-danger"
									rendered="#{imflOldStockFL36.gatePassFlag}"
									action="#{imflOldStockFL36.delete}">
								</h:commandButton></td>

						</tr>
					</table>
					</div>

					

				</div>
			</div>
		</h:form>
	</f:view>
</ui:composition>