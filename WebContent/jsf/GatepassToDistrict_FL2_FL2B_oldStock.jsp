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
														id="messages" infoStyle="color:green"
														style="FONT-SIZE: xx-large;">
													</h:messages>
												</h3></TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
						</TR>

						<TR align="center" style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
							<TD><h:inputHidden
									value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.hidden}"></h:inputHidden>
								<h5>
									<h:outputText
										value="GatePass To Retailer FL2 / FL2B / CL2 OldStock (2018-19) "
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
												value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.fl2_fl2bName}"
												style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>
									</tr>
									<tr>
										<TD><rich:spacer height="5px"></rich:spacer></TD>
									</tr>
									<TR>

										<TD align="center" colspan="2"><h:outputLabel
												value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.fl2_fl2bAdrs}"
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

									</tr>
									<TR>
										<td><table>

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
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.dt_date}" />
														</TD>
														<TD colspan="2" align="center">Valid Till : <rich:calendar
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.validtilldt_date}" />
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
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vch_from}" /></TD>

														<TD colspan="2" align="center">Gatepass Number : <h:inputText
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.gatepassNmbr}"
																style="width:250px" />
														</TD>
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
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vch_to}"
																rendered="#{gatepassToDistrict_FL2_FL2B_oldStockAction.brcFlag}"
																valueChangeListener="#{gatepassToDistrict_FL2_FL2B_oldStockAction.listMethod}"
																onchange="this.form.submit()">

																<f:selectItem itemValue="RT" itemLabel="Retailer" />

																<f:selectItem itemValue="BRC"
																	itemLabel="Bar/Restaurant/Club" />


															</h:selectOneRadio> <h:selectOneRadio
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vch_to}"
																rendered="#{gatepassToDistrict_FL2_FL2B_oldStockAction.rtFlag}"
																valueChangeListener="#{gatepassToDistrict_FL2_FL2B_oldStockAction.listMethod}"
																onchange="this.form.submit()">

																<f:selectItem itemValue="RT" itemLabel="Retailer" />

															</h:selectOneRadio></TD>


														<td align="right" style="padding-right: 10px;"><h:outputText
																value="HBR-"
																rendered="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vch_to eq 'BRC'}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText> <h:outputText value="Shop Id."
																rendered="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vch_to eq 'RT'}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText></td>
														<td align="left"><h:inputText
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vch_to_lic_no}"
																rendered="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vch_to eq 'BRC' }"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText> <h:outputLabel
																rendered="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vch_to eq 'BRC' }">*Enter only digits w/o HBR.</h:outputLabel>
															<h:inputText
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.shopno}"
																rendered="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vch_to eq 'RT'}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<td align="left"><h:commandButton
																styleClass="btn btn-primary"
																rendered="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vch_to  eq 'BRC' and !gatepassToDistrict_FL2_FL2B_oldStockAction.disable_flag}"
																action="#{gatepassToDistrict_FL2_FL2B_oldStockAction.fetch1}"
																value="Validate"></h:commandButton> <h:commandButton
																styleClass="btn btn-primary"
																rendered="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vch_to  eq 'RT' and !gatepassToDistrict_FL2_FL2B_oldStockAction.disable_flag}"
																action="#{gatepassToDistrict_FL2_FL2B_oldStockAction.fetch}"
																value="Validate"></h:commandButton></td>

													</tr>
													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>
													<tr style="padding-bottom: 10px;">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Licensee District" rendered="false"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:selectOneMenu rendered="false"
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.districtLic}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.districtList}" />
															</h:selectOneMenu></td>
														<td align="right"><h:outputText value="Shop Name."
																rendered="#{gatepassToDistrict_FL2_FL2B_oldStockAction.flagshop}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText></td>
														<td align="left"><h:inputText disabled="true"
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.shopName}"
																rendered="#{gatepassToDistrict_FL2_FL2B_oldStockAction.flagshop}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>

													</tr>

													<tr>


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
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.licenseeName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px" disabled="true">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Licensee Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.licenseeAdrs}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;" disabled="true">

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
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.routeDtl}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle No."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vehicleNo}"
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
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vehicleDrvrName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle Agency Name And Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.vehicleAgencyNmAdrs}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea></td>
													</tr>
													<tr align="center">
														<td style="padding-top: 10px;"></td>
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
								</TABLE>
								<div class="row">

									<div class="col-md-12">
										<h:outputLabel value="BRAND DETAILS : "
											style="FONT-WEIGHT: bold; COLOR: #0000ff; FONT-SIZE: x-large; FONT-STYLE: italic;"></h:outputLabel>
									</div>
									<div>
										<rich:spacer height="10px" />
									</div>
									<div class="row">
										<div class="col-md-12">
											<rich:dataTable columnClasses="columnClass1"
												headerClass="TableHead" footerClass="TableHead"
												rowClasses="TableRow1,TableRow2" styleClass="DataTable"
												id="table3" rows="10" width="100%"
												value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.displaylist}"
												var="list">
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Sr.No">
														</h:outputText>
													</f:facet>
													<h:outputText value="#{list.srNo}"
														styleClass="generalHeaderStyleOutput">
													</h:outputText>
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Brand">
														</h:outputText>
													</f:facet>
													<h:selectOneMenu value="#{list.brandPackagingData_Brand}"
														styleClass="dropdown-menu" onchange="this.form.submit();"
														style=" width : 277px;"
														valueChangeListener="#{gatepassToDistrict_FL2_FL2B_oldStockAction.brandListener}">
														<f:selectItems
															value="#{list.brandPackagingData_BrandList}" />
														<a4j:support event="onblur" reRender="brand">
														</a4j:support>
													</h:selectOneMenu>
													<a4j:outputPanel id="brand">
														<h:inputText value="#{list.vch_brand}"
															rendered="#{list.brandFlg}" styleClass="form-control">
														</h:inputText>
													</a4j:outputPanel>
												</rich:column>

												<rich:column>
													<f:facet name="header">
														<h:outputText value="Packaging">
														</h:outputText>
													</f:facet>
													<h:selectOneMenu
														value="#{list.brandPackagingData_Packaging}"
														styleClass="dropdown-menu" onchange="this.form.submit();">
														<f:selectItems
															value="#{list.brandPackagingData_PackagingList}" />
													</h:selectOneMenu>
													<h:inputText value="#{list.packageName}"
														rendered="#{list.brandFlg}" styleClass="form-control">
													</h:inputText>
												</rich:column>


												<rich:column>
													<f:facet name="header">
														<h:outputText value="Quantity">
														</h:outputText>
													</f:facet>
													<h:outputText value="#{list.brandPackagingData_Quantity}"
														styleClass="generalHeaderStyleOutput">
													</h:outputText>
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Batch No." />
													</f:facet>
													<h:inputText value="#{list.batchNo}"
														styleClass="generalHeaderStyleOutput">
													</h:inputText>
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Bottles Per Case"
															styleClass="generalHeaderOutputTable" />
													</f:facet>
													<h:inputText value="#{list.size}"
														styleClass="generalHeaderStyleOutput">
														<a4j:support event="onblur" reRender="box">
														</a4j:support>
													</h:inputText>
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="No. Of Boxes">
														</h:outputText>
													</f:facet>

													<h:inputText value="#{list.dispatchbox}"
														styleClass="generalHeaderStyleOutput">

														<a4j:support event="onblur"
															reRender="box,dutyy,addduty,totalduty,totaladdduty">
														</a4j:support>
													</h:inputText>
												</rich:column>

												<rich:column>
													<f:facet name="header">
														<h:outputText value="No.Of Bottles ">
														</h:outputText>
													</f:facet>
													<a4j:outputPanel id="box">
														<h:inputText value="#{list.dispatchbottls}"
															styleClass="generalHeaderStyleOutput" disabled="true">
														</h:inputText>
													</a4j:outputPanel>
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Duty Per Bottle"
															styleClass="generalHeaderOutputTable" />
													</f:facet>
													<h:inputText value="#{list.db_duty}">
														<a4j:support event="onblur"
															reRender="dutyy,addduty,totalduty,totaladdduty">
														</a4j:support>
													</h:inputText>
												</rich:column>


												<rich:column>
													<f:facet name="header">
														<h:outputText value="Add Duty Per Bottle"
															styleClass="generalHeaderOutputTable" />
													</f:facet>
													<h:inputText value="#{list.db_add_duty}">
														<a4j:support event="onblur"
															reRender="dutyy,addduty,totalduty,totaladdduty">
														</a4j:support>
													</h:inputText>
												</rich:column>

												<rich:column>
													<f:facet name="header">
														<h:outputText value="Calculated Duty "
															style=" white-space: normal;width : 53px;"
															styleClass="generalHeaderOutputTable" />
													</f:facet>
													<h:outputText value="#{list.calculated_duty}" id="dutyy">
														<f:convertNumber maxFractionDigits="2"
															pattern="#############0.00" />
													</h:outputText>
												</rich:column>


												<rich:column>
													<f:facet name="header">
														<h:outputText value="Calculated Add. Duty"
															style=" white-space: normal;width : 53px;"
															styleClass="generalHeaderOutputTable" />
													</f:facet>
													<h:outputText value="#{list.calculated_add_duty}"
														id="addduty">
														<f:convertNumber maxFractionDigits="2"
															pattern="#############0.00" />
													</h:outputText>
												</rich:column>

												<rich:column>
													<f:facet name="header">
														<h:commandButton class="imag"
															action="#{gatepassToDistrict_FL2_FL2B_oldStockAction.addRowMethodPackaging}"
															image="/img/add.png" />
													</f:facet>
													<h:commandButton class="imag"
														actionListener="#{gatepassToDistrict_FL2_FL2B_oldStockAction.deleteRowMethodPackaging}"
														style="background: transparent;height:30px "
														image="/img/del.png" />
												</rich:column>

												<f:facet name="footer">
													<rich:datascroller for="table3"></rich:datascroller>
												</f:facet>
											</rich:dataTable>
										</div>
									</div>
									<div class="row" align="center">
										<rich:spacer height="10px"></rich:spacer>
									</div>
						<tr align="right">
							<td>
								<table>
									<tr>

										<td align="right"><a4j:outputPanel id="totalduty">
												<h:outputText value="Total Duty : "
													style="FONT-WEIGHT: bold;" />
												<h:inputText styleClass="inputtext" readonly="true"
													value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.db_total_value}">
													<f:convertNumber maxFractionDigits="2"
														pattern="#############0.00" />
												</h:inputText>

											</a4j:outputPanel></td>
										<td align="right"><a4j:outputPanel id="totaladdduty">
												<h:outputText value="Total Additional Duty : "
													style="FONT-WEIGHT: bold;" />
												<h:inputText styleClass="inputtext" readonly="true"
													value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.db_total_add_value}">
													<f:convertNumber maxFractionDigits="2"
														pattern="#############0.00" />
												</h:inputText>

											</a4j:outputPanel></td>

									</tr>
									<tr></tr>
								</table>
							</td>
						</tr>

						<div class="row" align="center">
							<rich:spacer height="10px"></rich:spacer>
						</div>
						</div>
						<tr>
							<td style="text-align: center;"><h:commandButton
									styleClass="btn btn-primary"
									action="#{gatepassToDistrict_FL2_FL2B_oldStockAction.saveMethod}"
									value="Save"
									onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;"></h:commandButton>
								<rich:spacer width="10px"></rich:spacer> <h:commandButton
									styleClass="btn btn-warning"
									action="#{gatepassToDistrict_FL2_FL2B_oldStockAction.clearAll}"
									value="Reset"></h:commandButton></td>

						</tr>
						</TD>
						</TR>
					</TABLE>

					<div class="row">
						<rich:dataTable align="center" id="table55" rows="10" width="100%"
							var="list11"
							value="#{gatepassToDistrict_FL2_FL2B_oldStockAction.table2List }"
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




							<rich:column sortBy="#{list11.currentDate}">
								<f:facet name="header">
									<h:outputLabel value="Date Created">
									</h:outputLabel>
								</f:facet>
								<center>
									<h:outputText value="#{list11.currentDate}">
										<f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+5:30" />
									</h:outputText>
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

							<rich:column filterBy="#{list11.gatepassNo}"
								sortBy="#{list11.gatepassNo}">
								<f:facet name="header">
									<h:outputLabel value="FL2 Gatepass No." />
								</f:facet>
								<center>
									<h:outputText value="#{list11.gatepassNo}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="FL2 License No./ HBR ID" />
								</f:facet>
								<center>
									<h:outputText value="#{list11.licenseNo}" />
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