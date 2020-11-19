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
									value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.hidden}"></h:inputHidden>
								<h5>
									<h:outputText
										value="FL2D Manual Entry Of Gatepass Old Stock (2018-19) "
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
												value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.fl2_fl2bName}"
												style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>
									</tr>
									<tr>
										<TD><rich:spacer height="5px"></rich:spacer></TD>
									</tr>
									<TR>

										<TD align="center" colspan="2"><h:outputLabel
												value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.fl2_fl2bAdrs}"
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
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.dt_date}" />
														</TD>
														<TD colspan="2" align="center">Valid Till : <rich:calendar
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.validtilldt_date}" />
														</TD>
													</tr>
													<tr>
														<TD colspan="2" align="center">Enter Gatepass No. :
                                                            <h:inputText
																
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.gatepassNmbr}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText>
														</TD>
														<TD colspan="2" align="center"> 
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
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.vch_from}"
																valueChangeListener="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.listMethod}"
																onchange="this.form.submit()">
																<f:selectItem itemValue="FL1" itemLabel="FL1" />
																<f:selectItem itemValue="FL1A" itemLabel="FL1A" />
															</h:selectOneRadio> <h:outputText
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.vch_from}" />


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
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.vch_to}"
																onchange="this.form.submit()"
																valueChangeListener="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.listMethod}">
																<f:selectItem itemValue="FL2" itemLabel="FL2" />
																<f:selectItem itemValue="FL2B" itemLabel="FL2B" />
																<f:selectItem itemValue="HBR"
																	itemLabel="Bar/Restaurant/Club" />
																<f:selectItem itemValue="RT" itemLabel="Retailer" />


															</h:selectOneRadio> <h:selectOneMenu
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.districtLic}"
																onchange="this.form.submit();"
																style="width: 100px; height: 28px;">
																<f:selectItems
																	value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.districtList}" />
															</h:selectOneMenu></TD>


														<td align="right" style="padding-right: 10px;"><h:outputText
																value="License No."
																rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.drpdwnFlg3}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
																
																<h:outputText
																value="Shop Id."
																	rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.vch_to eq 'RT'}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText>

														</td>
														<td align="left"><h:inputText
																rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.drpdwnFlg}"
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.vch_to_lic_no}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText>
															<h:outputLabel rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.drpdwnFlg}">
															*Enter only digits w/o HBR.
															</h:outputLabel>
															
															 <h:selectOneMenu
																rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.drpdwnFlg1}"
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.brc_to_lic}"
																valueChangeListener="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.drpdownMethod}"
																onchange="this.form.submit()"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.brclicNmbrList}" />
															</h:selectOneMenu>
															
															<h:inputText
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.shopno}"
															     rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.vch_to eq 'RT'}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText>
															
															
															</td>
															
															<td align="left">
															
														<h:commandButton
																	styleClass="btn btn-primary"
																	rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.drpdwnFlg}"
																		action="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.fetch1}"
															value="Validate"></h:commandButton>
																
														<h:commandButton
																	styleClass="btn btn-primary"
																	rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.vch_to eq 'RT'}"
																		action="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.fetch}"
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
																	rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.flagshop}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText>
														
														</td>
												<td align="left">
														<h:inputText
																disabled="true"
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.shopName}"
																rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.flagshop}"
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
																rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.flagshop}"
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.licenseeName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText>
															
															<h:inputText
															    rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.flagshopS}"
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.licenceenm}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText>
															
															
															
															</td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.flagshop}"
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.licenseeAdrs}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea>
															
															
															<h:inputTextarea
															  rendered="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.flagshopS}"
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.licenceeadd}"
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
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.routeDtl}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle No."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.vehicleNo}"
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
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.vehicleDrvrName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle Agency Name And Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.vehicleAgencyNmAdrs}"
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
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.district1}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.districtList}" />
															</h:selectOneMenu></td>


														<td align="left"><h:selectOneMenu
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.district2}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.districtList}" />
															</h:selectOneMenu></td>

														<td align="left"><h:selectOneMenu
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.district3}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.districtList}" />
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
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.grossWeight}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">
																<a4j:support reRender="net-weight" event="onkeyup"></a4j:support>
															</h:inputText></TD>

														<td align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Tier Weight "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:inputText
																value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.tierWeight}"
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
																	value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.netWeight}"
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
												value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.displaylist}"
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
														valueChangeListener="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.brandListener}">
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
														<h:outputText value="No. of bottel per box"
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
														<h:outputText value="Duty Per Bottel"
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
														<h:outputText value="Add Duty Per Bottel"
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
														<h:outputText value="Calculated Duty"
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
															action="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.addRowMethodPackaging}"
															image="/img/add.png" />
													</f:facet>
													<h:commandButton class="imag"
														actionListener="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.deleteRowMethodPackaging}"
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
									<table>
						<tr align="right">
							<td>
								<table>
									<tr>

										<td align="right"><a4j:outputPanel id="totalduty">
												<h:outputText value="Total Duty : "
													style="FONT-WEIGHT: bold;" />
												<h:inputText styleClass="inputtext" readonly="true"
													value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.db_total_value}">
													<f:convertNumber maxFractionDigits="2"
														pattern="#############0.00" />
												</h:inputText>

											</a4j:outputPanel></td>
										<td align="right"><a4j:outputPanel id="totaladdduty">
												<h:outputText value="Total Additional Duty : "
													style="FONT-WEIGHT: bold;" />
												<h:inputText styleClass="inputtext" readonly="true"
													value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.db_total_add_value}">
													<f:convertNumber maxFractionDigits="2"
														pattern="#############0.00" />
												</h:inputText>

											</a4j:outputPanel></td>

									</tr>
									<tr></tr>
								</table>
							</td>
						</tr>

                 </table>
						<div class="row" align="center">
							<rich:spacer height="10px"></rich:spacer>
						</div>
						</div>
						</TD>
						</TR>
						<tr>
							<td style="text-align: center;"><h:commandButton
									styleClass="btn btn-primary"
									action="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.saveMethod}"
									value="Save"
									onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;"></h:commandButton>
								<rich:spacer width="10px"></rich:spacer> <h:commandButton
									styleClass="btn btn-warning"
									action="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.clearAll}"
									value="Reset"></h:commandButton></td>

						</tr>
						
					</TABLE>

					<div class="row">
						<rich:dataTable align="center" id="table55" rows="10" width="100%"
							var="list11"
							value="#{fL2D_Manual_Entry_Of_Gatepss_OldStock_18_19_Action.table2List }"
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