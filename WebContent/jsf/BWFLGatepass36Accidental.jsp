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
									value="#{bWFLGatepass36AccidentalAction.hidden}"></h:inputHidden>
								<h5>
									<h:outputText
										value="GatePass From Distillery To District WholeSale 2020-21"
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
												value="#{bWFLGatepass36AccidentalAction.bwflName}"
												style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>
									</tr>
									<tr>
										<TD><rich:spacer height="5px"></rich:spacer></TD>
									</tr>
									<TR>

										<TD align="center" colspan="2"><h:outputLabel
												value="#{bWFLGatepass36AccidentalAction.bwflAdrs}"
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
																value="#{bWFLGatepass36AccidentalAction.dt_date}" />
														</TD>
														<TD colspan="2" align="center">Valid Till : <rich:calendar
																value="#{bWFLGatepass36AccidentalAction.validtilldt_date}" />
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
																value="#{bWFLGatepass36AccidentalAction.vch_from}" /></TD>

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
																value="#{bWFLGatepass36AccidentalAction.vch_to}"
																valueChangeListener="#{bWFLGatepass36AccidentalAction.listMethod}"
																onchange="this.form.submit()">

																<f:selectItem itemValue="DW"
																	itemLabel="District Warehouse" />
																<f:selectItem itemValue="BRC"
																	itemLabel="Bar/Restaurant/Club" />


															</h:selectOneRadio></TD>


													<td align="right" style="padding-right: 10px;">
														<h:outputText value="FL2"
														rendered="#{bWFLGatepass36AccidentalAction.licNoflg}"
																 style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText> 
															<h:outputText value="FL2B" 
															rendered="#{bWFLGatepass36AccidentalAction.licNoflg1}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:outputText value="FL2/FL2B" 
															rendered="#{bWFLGatepass36AccidentalAction.licNoflg2}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>

														</td>
														<td align="left">
															<h:selectOneMenu rendered="#{bWFLGatepass36AccidentalAction.drpdwnFlg}"
																value="#{bWFLGatepass36AccidentalAction.vch_to_lic_no}"
																valueChangeListener="#{bWFLGatepass36AccidentalAction.waredrpMethod}"
																onchange="this.form.submit()"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{bWFLGatepass36AccidentalAction.licNmbrList}" />
															</h:selectOneMenu>
															
														
															
															<h:outputText
																value="HBR-"
																	rendered="#{bWFLGatepass36AccidentalAction.drpdwnFlg1}"

																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText>
															
															
																<h:inputText
																value="#{bWFLGatepass36AccidentalAction.brc_to_lic}"
																rendered="#{bWFLGatepass36AccidentalAction.drpdwnFlg1}"
																
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText>
															
														
															
															
															
															
															
															
															<h:outputLabel 
															rendered="#{bWFLGatepass36AccidentalAction.drpdwnFlg1}"
>
															*Enter only digits w/o HBR.
															</h:outputLabel>
															<h:commandButton
																	styleClass="btn btn-primary"
																	rendered="#{bWFLGatepass36AccidentalAction.drpdwnFlg1}"

																		action="#{bWFLGatepass36AccidentalAction.fetch1}"
															value="Validate">
															</h:commandButton>
															
															
															
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
														<td align="left"><h:inputText disabled="true"
																value="#{bWFLGatepass36AccidentalAction.licenseeDistrict}"
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
																value="#{bWFLGatepass36AccidentalAction.licenseeName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Licensee Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea disabled="true"
																value="#{bWFLGatepass36AccidentalAction.licenseeAdrs}"
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
																value="#{bWFLGatepass36AccidentalAction.routeDtl}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle No."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{bWFLGatepass36AccidentalAction.vehicleNo}"
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
																value="#{bWFLGatepass36AccidentalAction.vehicleDrvrName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle Agency Name And Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{bWFLGatepass36AccidentalAction.vehicleAgencyNmAdrs}"
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
																value="#{bWFLGatepass36AccidentalAction.district1}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{bWFLGatepass36AccidentalAction.districtList}" />
															</h:selectOneMenu></td>


														<td align="left"><h:selectOneMenu
																value="#{bWFLGatepass36AccidentalAction.district2}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{bWFLGatepass36AccidentalAction.districtList}" />
															</h:selectOneMenu></td>

														<td align="left"><h:selectOneMenu
																value="#{bWFLGatepass36AccidentalAction.district3}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{bWFLGatepass36AccidentalAction.districtList}" />
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
																value="#{bWFLGatepass36AccidentalAction.grossWeight}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">
																<a4j:support reRender="net-weight" event="onkeyup"></a4j:support>
															</h:inputText></TD>

														<td align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Tier Weight "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:inputText
																value="#{bWFLGatepass36AccidentalAction.tierWeight}"
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
																	value="#{bWFLGatepass36AccidentalAction.netWeight}"
																	disabled="true" styleClass="generalHeaderOutputTable"
																	style="width:250px">
																</h:inputText>
															</a4j:outputPanel>
															</td>
													</tr>
													
												<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>
													<tr style="padding-bottom: 10px; padding-top: 10px;">
														
														
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;">
															<h:outputText rendered="#{bWFLGatepass36AccidentalAction.vch_to eq 'BRC'}"
																value="Challan no."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText></TD>
															<td align="left">
															<h:inputText rendered="#{bWFLGatepass36AccidentalAction.vch_to eq 'BRC'}"
																value="#{bWFLGatepass36AccidentalAction.challanNo}"
																styleClass="generalHeaderOutputTable"
																style="width:100px">
																
															</h:inputText>
															</td>
															
											<TD  align="left">
											<h:outputText style="FONT-SIZE: small; FONT-WEIGHT: bold;"
											rendered="#{bWFLGatepass36AccidentalAction.vch_to eq 'BRC'}"
											 >Date : </h:outputText>
											 
											
											<rich:calendar 
											rendered="#{bWFLGatepass36AccidentalAction.vch_to eq 'BRC'}"
										   value="#{bWFLGatepass36AccidentalAction.challanDate}" />
														</TD>
														<td align="left"
														style="FONT-WEIGHT: bold; padding-right: 10px;">
															<h:outputText
															rendered="#{bWFLGatepass36AccidentalAction.vch_to eq 'BRC'}"
																value="Challan Amount "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															
																<h:inputText
																rendered="#{bWFLGatepass36AccidentalAction.vch_to eq 'BRC'}"
																	value="#{bWFLGatepass36AccidentalAction.challanAmount}"
																	 styleClass="generalHeaderOutputTable" style="width:150px"
																	>
																</h:inputText>
															
															</td>
													</tr>
													<tr align="center">
														<td style="padding-top: 10px;"></td>
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
								
								
								
								
								
								<rich:dataTable columnClasses="columnClass1"
								rendered="#{bWFLGatepass36AccidentalAction.vch_to eq 'DW'}"
													headerClass="TableHead" footerClass="TableHead"
													rowClasses="TableRow1,TableRow2" styleClass="DataTable"
													id="table555" width="100%" rows="10"
													value="#{bWFLGatepass36AccidentalAction.displaylist1}" var="list"
												>

													<rich:column>
														<f:facet name="header">
															<h:outputLabel value="Sno"
																styleClass="generalHeaderStyle" />
														</f:facet>
														<center>
															<h:outputText styleClass="generalExciseStyle"
																value="#{list.snoIndent}" />
														</center>
													</rich:column>

													<rich:column sortBy="#{list.indentNo}"
														filterBy="#{list.indentNo}">
														<f:facet name="header">
															<h:outputText value="Indent No"
																styleClass="generalHeaderOutputTable" />
														</f:facet>
														<h:outputText value="#{list.indentNo}">
														</h:outputText>
													</rich:column>

													<rich:column sortBy="#{list.indentDate}" filterBy="#{list.indentDate}">
														<f:facet name="header">
															<h:outputText value="Indent Date"
																styleClass="generalHeaderOutputTable" />
														</f:facet>
														<h:outputText value="#{list.indentDate}">
														</h:outputText>
													</rich:column>
												
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Brand Name"
																styleClass="generalHeaderOutputTable" />
														</f:facet>
														<h:outputText value="#{list.brandName}">
														</h:outputText>
													</rich:column>


													<rich:column>
														<f:facet name="header">
															<h:outputText value="Size"
																styleClass="generalHeaderOutputTable" />
														</f:facet>
														<h:outputText value="#{list.packageSize}">
														</h:outputText>
													</rich:column>










													<rich:column>
														<f:facet name="header">
															<h:outputText value="No of cases"
																styleClass="generalHeaderOutputTable" />
														</f:facet>
														<h:outputText 
														value="#{list.dispatchboxIndent}" 
															>
															
														</h:outputText>
													</rich:column>










													<f:facet name="footer">
														<rich:datascroller for="table555" />
													</f:facet>
												</rich:dataTable>
										
										<table>
										<tr><td>	</td></tr>
										</table>
									
										
											<rich:spacer height="30px"></rich:spacer>
								
								
								 <rich:dataTable columnClasses="columnClass1"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2" styleClass="DataTable"
									id="table3" rows="10" width="100%"
									value="#{bWFLGatepass36AccidentalAction.displaylist}" var="list">

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
										</f:facet>
										<center>
											<h:outputText styleClass="generalExciseStyle"
												value="#{list.slno}" />
										</center>
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
								<rich:column rendered="#{bWFLGatepass36AccidentalAction.vch_to eq 'DW'}">
										<f:facet name="header">
											<h:outputText value="Indent No" />
										</f:facet>
										
										<h:inputText value="#{list.indentNumber}" 	>
										</h:inputText>
										
									</rich:column>
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Available Bottle" style="white-space: normal;"  />
										</f:facet>
										<h:outputText value="#{list.int_bottle_avaliable}"  >
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Available Box" style="white-space: normal;"  />
										</f:facet>
										<h:outputText value="#{list.boxAvailable}"  >
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Breakage"  />
										</f:facet>
										<h:inputText value="#{list.breakage}" style=" width : 53px;">
											<a4j:support reRender="duty" event="onblur"></a4j:support>
										</h:inputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Balance Bottle" style="white-space: normal;"  />
										</f:facet>
										<h:outputText value="#{list.balance}" id="duty">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Batch No."  />
										</f:facet>
										<h:inputText value="#{list.batchNo}" style=" width : 83px;">
										</h:inputText>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Dispatch Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.dispatchbox}" style=" width : 83px;">
											<a4j:support event="onblur"
												reRender="bot,dutyy,totalduty,totaladdduty,addduty,cesh,totalcesh"></a4j:support>
										</h:inputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Dispatch Bottles" style="white-space: normal;"  />
										</f:facet>
										<h:outputText value="#{list.dispatchbottls}" id="bot"
											  disabled="true">
										</h:outputText>
									</rich:column>

									<f:facet name="footer">
										<rich:datascroller for="table3"></rich:datascroller>
									</f:facet>
								</rich:dataTable>
			
						<tr>
							<td style="text-align: center;"><h:commandButton
									styleClass="btn btn-primary"
									action="#{bWFLGatepass36AccidentalAction.saveMethod}"
									value="Save"
									onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;"></h:commandButton>
								<rich:spacer width="10px"></rich:spacer> <h:commandButton
									styleClass="btn btn-warning"
									action="#{bWFLGatepass36AccidentalAction.clearAll}" value="Reset"></h:commandButton></td>

						</tr>
						</TD>
						</TR>
					</TABLE>

					<div class="row">
						<rich:dataTable align="center" id="table55" rows="10" width="100%"
							var="list11" value="#{bWFLGatepass36AccidentalAction.table2List }"
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
									<h:outputLabel value="Date Created" />
								</f:facet>
								<center>
									<h:outputText value="#{list11.currentDate}" />
								</center>
							</rich:column>

							<rich:column sortBy="#{list11.vchTO}">
								<f:facet name="header">
									<h:outputLabel value="License Type" />
								</f:facet>
								<center>
									<h:outputText value="#{list11.vchTO}" />
								</center>
							</rich:column>

							<rich:column sortBy="#{list11.gatepassNo}"
								filterBy="#{list11.gatepassNo}">
								<f:facet name="header">
									<h:outputLabel value="FL2 Gatepass No." />
								</f:facet>
								<center>
									<h:outputText value="#{list11.gatepassNo}" />
								</center>
							</rich:column>

							<rich:column sortBy="#{list11.licenseNo}"
								filterBy="#{list11.licenseNo}">
								<f:facet name="header">
									<h:outputLabel value="License No." />
								</f:facet>
								<center>
									<h:outputText value="#{list11.licenseNo}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">

								</f:facet>
								<center>

									<h:commandButton styleClass="btn btn-success"
										value="Print Draft" rendered="#{list11.printDraft}"
										actionListener="#{bWFLGatepass36AccidentalAction.printDraftreport}"></h:commandButton>

									<h:outputLink styleClass="outputLinkEx"
										value="/doc/ExciseUp/WholeSale/pdf//#{bWFLGatepass36AccidentalAction.draftpdfname}"
										target="_blank">
										<h:outputText styleClass="outputText" id="text224"
											value="View Draft Report" rendered="#{list11.draftprintFlag}"
											style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
									</h:outputLink>






									<h:commandButton styleClass="btn btn-success" value="Print"
										actionListener="#{bWFLGatepass36AccidentalAction.printReport }"
										rendered="#{list11.finalizePrint}"></h:commandButton>

									<h:outputLink styleClass="outputLinkEx"
										value="/doc/ExciseUp/WholeSale/pdf//#{bWFLGatepass36AccidentalAction.pdfname}"
										target="_blank">
										<h:outputText styleClass="outputText" id="text223"
											value="View Report" rendered="#{list11.printFlag}"
											style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
									</h:outputLink>

									<h:commandButton styleClass="btn btn-success"
										value="Scan and Upload" rendered="#{list11.draftprintFlag}"
										actionListener="#{bWFLGatepass36AccidentalAction.scanAndUpload}">
									</h:commandButton>


								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="Cancel Request." />
								</f:facet>

								<h:commandButton styleClass="btn btn-danger"
									value="Cancel Gatepass" rendered="#{list11.printDraft}"
									action="#{bWFLGatepass36AccidentalAction.cancel}">
									<f:setPropertyActionListener
										target="#{bWFLGatepass36AccidentalAction.edidata}"
										value="#{list11}" />
								</h:commandButton>

							</rich:column>


							<f:facet name="footer">
								<rich:datascroller for="table55" />
							</f:facet>
						</rich:dataTable>

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
										value="#{bWFLGatepass36AccidentalAction.exclcsv}"
										rendered="#{bWFLGatepass36AccidentalAction.gatePassFlag}"
										onchange="this.form.submit()"> 
										<f:selectItem itemValue="C" itemLabel="Upload CSV" />

									</h:selectOneRadio></TD>
							</tr>


							<tr align="left">
								<tr  ><td colspan="6" style="color: Green;" align="left">
								<h:outputText rendered="#{bWFLGatepass36AccidentalAction.gatePassFlag}"
									value="** Please read the instructions carefully before uploading."
									style="color: #ce0000;font-style: italic;font-size: large;text-decoration:blink;" /></td></tr>
								<td style="FONT-WEIGHT: bold; color: Green;" align="left"><h:outputText
										value="Uploading for Gatepass Number::"
										rendered="#{bWFLGatepass36AccidentalAction.gatePassFlag}"
										style="FONT-SIZE: medium;" /></td>
								<td style="FONT-SIZE: large; color: Red"><h:outputText
										value="#{bWFLGatepass36AccidentalAction.scanGatePassNo}"
										rendered="#{bWFLGatepass36AccidentalAction.exclcsv eq 'E' or bWFLGatepass36AccidentalAction.exclcsv eq 'C'}" /></td>
								<TD><h:outputLink value="/doc/ExciseUp/ExcelFormatBWFL.pdf"
										rendered="#{bWFLGatepass36AccidentalAction.gatePassFlag}"
										target="_blank">
										<h:graphicImage value="/img/i.png" style="width:40px;"
											rendered="#{bWFLGatepass36AccidentalAction.exclcsv eq 'E' or bWFLGatepass36AccidentalAction.exclcsv eq 'C'}">
										</h:graphicImage>
									</h:outputLink></TD>

								<td><rich:fileUpload addControlLabel="Add File" id="link3"
										fileUploadListener="#{bWFLGatepass36AccidentalAction.uploadExcel}"
										rendered="#{bWFLGatepass36AccidentalAction.gatePassFlag and bWFLGatepass36AccidentalAction.exclcsv eq 'E'}"
										maxFilesQuantity="1" listHeight="40" listWidth="250"
										clearControlLabel="clear" stopControlLabel="Stop"
										ontyperejected="if (!confirm(' ONLY .xlsx type file ALLOWED !!!')) return false"
										acceptedTypes="xlsx" clearAllControlLabel="Clear">
									</rich:fileUpload></td>

								<td><h:commandButton value="Upload"
										rendered="#{bWFLGatepass36AccidentalAction.gatePassFlag and bWFLGatepass36AccidentalAction.exclcsv eq 'E'}"
										styleClass="btn btn-primary"
										action="#{bWFLGatepass36AccidentalAction.importExcel}">
									</h:commandButton></td>


								<td><rich:fileUpload addControlLabel="Add File" id="link34"
										fileUploadListener="#{bWFLGatepass36AccidentalAction.uploadCsv}"
										rendered="#{bWFLGatepass36AccidentalAction.gatePassFlag and bWFLGatepass36AccidentalAction.exclcsv eq 'C'}"
										maxFilesQuantity="1" listHeight="40" listWidth="250"
										clearControlLabel="clear" stopControlLabel="Stop"
										ontyperejected="if (!confirm(' ONLY .csv type file ALLOWED !!!')) return false"
										acceptedTypes="csv" clearAllControlLabel="Clear">
									</rich:fileUpload></td>

								<td><h:commandButton value="upload CSV"
										styleClass="btn btn-primary"
										rendered="#{bWFLGatepass36AccidentalAction.gatePassFlag and bWFLGatepass36AccidentalAction.exclcsv eq 'C'}"
										action="#{bWFLGatepass36AccidentalAction.csvSubmit }">
									</h:commandButton></td>


							</tr>
						</table>
						<div class="row">
							<rich:spacer height="20px"></rich:spacer>
						</div>
						<div>
							<rich:dataTable align="center" id="table57" rows="10"
								width="100%" var="listk"
								rendered="#{bWFLGatepass36AccidentalAction.tableFlag}"
								value="#{bWFLGatepass36AccidentalAction.getVal }"
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

								   <rich:column filterBy="#{listk.cascodeMatching}" sortBy="#{listk.cascodeMatching}">
								<f:facet name="header">
									<h:outputLabel value="CaseCodeMatching" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.cascodeMatching}" />
								</center>
							</rich:column>
							  <rich:column >
								<f:facet name="header">
									<h:outputLabel value="Description" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.brand_name}-#{listk.casecoseBrandSize}-#{listk.date_plan}" />
								</center>
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
								<td><h:commandButton value="Finalise"
										styleClass="btn btn-primary"
										rendered="#{bWFLGatepass36AccidentalAction.tableFlag}" disabled="#{bWFLGatepass36AccidentalAction.bottlingNotDoneFlag }"
										action="#{bWFLGatepass36AccidentalAction.finalSubmit}">


									</h:commandButton> <h:commandButton value="Cancel" styleClass="btn btn-danger"
										rendered="#{bWFLGatepass36AccidentalAction.tableFlag}"
										action="#{bWFLGatepass36AccidentalAction.delete}">
									</h:commandButton></td>

							</tr>
						</table>


					</div>

				</div>
			</div>
		</h:form>
	</f:view>
</ui:composition>