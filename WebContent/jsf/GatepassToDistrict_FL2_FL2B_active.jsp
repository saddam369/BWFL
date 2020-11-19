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
									value="#{gatepassToDistrict_FL2_FL2B_activeAction.hidden}"></h:inputHidden>
								<h5>
									<h:outputText value="GatePass To Retailer FL2 / FL2B / CL2 2020-21 "
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
												value="#{gatepassToDistrict_FL2_FL2B_activeAction.fl2_fl2bName}"
												style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>
									</tr>
									<tr>
										<TD><rich:spacer height="5px"></rich:spacer></TD>
									</tr>
									<TR>

										<TD align="center" colspan="2"><h:outputLabel
												value="#{gatepassToDistrict_FL2_FL2B_activeAction.fl2_fl2bAdrs}"
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
																disabled="true"
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.dt_date}" />
														</TD>
														<TD colspan="2" align="center">Valid Till : <rich:calendar
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.validtilldt_date}" />
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
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_from}" /></TD>

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
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to}"
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.brcFlag}"
																valueChangeListener="#{gatepassToDistrict_FL2_FL2B_activeAction.listMethod}"
																onchange="this.form.submit()">

																<f:selectItem itemValue="RT" itemLabel="Retailer" />
																<f:selectItem itemValue="BRC" itemLabel="Bar/Restaurant/Club" />
																<f:selectItem itemValue="PR" itemLabel="PR" 
																itemDisabled="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_from eq 'CL2'}"/>


															</h:selectOneRadio> <h:selectOneRadio
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to}"
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.rtFlag}"
																valueChangeListener="#{gatepassToDistrict_FL2_FL2B_activeAction.listMethod}"
																onchange="this.form.submit()">

																<f:selectItem itemValue="RT" itemLabel="Retailer" />

															</h:selectOneRadio></TD>


														<td align="right" style="padding-right: 10px;"><h:outputText
																value="HBR-"
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to eq 'BRC'}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText> <h:outputText value="Shop Id."
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to eq 'RT'}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText>
															<h:outputText value="Shop Id."
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to eq 'PR'}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText></td>
														<td align="left"><h:inputText
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to_lic_no}"
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to eq 'BRC'}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText>
															<h:outputLabel
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to eq 'BRC' }">*Enter only digits w/o HBR.</h:outputLabel>
															<h:inputText
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.shopno}"
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to eq 'RT' or gatepassToDistrict_FL2_FL2B_activeAction.vch_to  eq 'PR'}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<td align="left"><h:commandButton
																styleClass="btn btn-primary"
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to  eq 'BRC' and !gatepassToDistrict_FL2_FL2B_activeAction.disable_flag}"
																action="#{gatepassToDistrict_FL2_FL2B_activeAction.fetch1}"
																value="Validate"></h:commandButton> <h:commandButton
																styleClass="btn btn-primary"
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to  eq 'RT' or gatepassToDistrict_FL2_FL2B_activeAction.vch_to  eq 'PR' and !gatepassToDistrict_FL2_FL2B_activeAction.disable_flag}"
																action="#{gatepassToDistrict_FL2_FL2B_activeAction.fetch}"
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
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.districtLic}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrict_FL2_FL2B_activeAction.districtList}" />
															</h:selectOneMenu></td>
														<td align="right"><h:outputText value="Shop Name."
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.flagshop}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText></td>
														<td align="left"><h:inputText disabled="true"
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.shopName}"
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.flagshop}"
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
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.licenseeName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px" disabled="true">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Licensee Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.licenseeAdrs}"
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
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.routeDtl}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle No."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.vehicleNo}"
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
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.vehicleDrvrName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle Agency Name And Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.vehicleAgencyNmAdrs}"
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
																value="Major Districts In Route" rendered="false"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:selectOneMenu rendered="false"
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.district1}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrict_FL2_FL2B_activeAction.districtList}" />
															</h:selectOneMenu></td>


														<td align="left"><h:selectOneMenu rendered="false"
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.district2}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrict_FL2_FL2B_activeAction.districtList}" />
															</h:selectOneMenu></td>

														<td align="left"><h:selectOneMenu rendered="false"
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.district3}"
																style="width: 250px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToDistrict_FL2_FL2B_activeAction.districtList}" />
															</h:selectOneMenu></td>
													</tr>

													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>
													
													<tr>
														<td colspan="4"
															style="padding-top: 10px; padding-bottom: 10px;"></td>
													</tr>

													<tr style="padding-bottom: 10px; padding-top: 10px;">
														
														<TD align="left" colspan="4"
															style="FONT-WEIGHT: bold; padding-left: 15px;"><h:outputText
																value="Advance Special Duty: "
																style=" FONT-WEIGHT: bold;"
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to eq 'BRC'}"></h:outputText>
															<h:inputText
																value="#{gatepassToDistrict_FL2_FL2B_activeAction.advance_duty}"
																styleClass="generalHeaderOutputTable"
																style="width:220px"
																disabled="true"					
																rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to eq 'BRC'}">
															</h:inputText></TD>
														
														

													</tr>
													<tr>
														<td colspan="4"
															style="padding-top: 10px; padding-bottom: 10px;"></td>
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
									id="table3" width="100%" rows="10"
									value="#{gatepassToDistrict_FL2_FL2B_activeAction.displaylist}"
									var="list">

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
										</f:facet>
										<center>
											<h:outputText styleClass="generalExciseStyle"
												value="#{list.slno}" />
										</center>
									</rich:column>

									<rich:column sortBy="#{list.vch_brand}"
										filterBy="#{list.vch_brand}">
										<f:facet name="header">
											<h:outputText value="Brand"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.brandUnitName_dt}">
										</h:outputText>
									</rich:column>

									<rich:column sortBy="#{list.size}" filterBy="#{list.size}">
										<f:facet name="header">
											<h:outputText value="Size"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.packageName}">
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
											<h:outputText value="Batch No.(Max 150 characters)"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.batchNo}" id="batch">
											<a4j:support event="onkeyup"
												reRender="dispatchbox,breakage,bot,"></a4j:support>
										</h:inputText>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Dispatch Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.dispatchbox}" id="dispatchbox">
											<a4j:support event="onblur" reRender="bot,fee"></a4j:support>
										</h:inputText>
									</rich:column>



									<rich:column>
										<f:facet name="header">
											<h:outputText value="Breakage"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.breakage}" id="breakage">
											<a4j:support event="onblur" reRender="bot"></a4j:support>
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


									<f:facet name="footer">
										<rich:datascroller for="table3" />
									</f:facet>
								</rich:dataTable>
						
							
									
									
									
                          
						
						
						
						
						</TD>
						</TR>
					</TABLE>
					
					<div align="right">
					 <h:commandButton id="fee" value="Total Special fee :#{gatepassToDistrict_FL2_FL2B_activeAction.total_permit_fee}" 
					      disabled="true" styleClass="btn btn-danger"
					      rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.vch_to eq 'BRC'}">
						</h:commandButton>
					</div>
					
						
						<div align="center">
						  <h:commandButton
									styleClass="btn btn-primary"
									action="#{gatepassToDistrict_FL2_FL2B_activeAction.saveMethod}"
									value="Save"
									onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;"></h:commandButton>
								<rich:spacer width="10px"></rich:spacer> <h:commandButton
									styleClass="btn btn-warning"
									action="#{gatepassToDistrict_FL2_FL2B_activeAction.clearAll}"
									value="Reset"></h:commandButton>
						</div>
						
						<div>
						<rich:spacer height="10px"></rich:spacer></div>
					<div class="row">
						Dispatch Date :
						<rich:spacer width="5px"></rich:spacer>
						<rich:calendar
							valueChangeListener="#{gatepassToDistrict_FL2_FL2B_activeAction.listMethod1}"
							onchanged="this.form.submit();"
							value="#{gatepassToDistrict_FL2_FL2B_activeAction.table_dt}" />

					</div>
					<div class="row">
						<rich:dataTable align="center" id="table55" rows="10" width="100%"
							var="list11"
							value="#{gatepassToDistrict_FL2_FL2B_activeAction.table2List }"
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

							<rich:column 
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

							<rich:column>
								<f:facet name="header">

								</f:facet>
								<center>


									<h:commandButton styleClass="btn btn-success"
										value="PrintDraft" rendered="#{!list11.finflg}"
										actionListener="#{gatepassToDistrict_FL2_FL2B_activeAction.printDraft}"></h:commandButton>

									<h:outputLink styleClass="outputLinkEx"
										value="/doc/ExciseUp/WholeSale/pdf//#{list11.draftpdfname}"
										target="_blank">
										<h:outputText styleClass="outputText"
											value="View Draft Report" rendered="#{list11.draftprintFlag}"
											style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
									</h:outputLink>



									<h:commandButton styleClass="btn btn-success" value="Print"
										rendered="#{list11.finflg}"
										actionListener="#{gatepassToDistrict_FL2_FL2B_activeAction.printReport }"></h:commandButton>

									<h:outputLink styleClass="outputLinkEx"
										value="/doc/ExciseUp/WholeSale/pdf//#{gatepassToDistrict_FL2_FL2B_activeAction.pdfname}"
										target="_blank">
										<h:outputText styleClass="outputText" id="text223"
											value="View Report" rendered="#{list11.printFlag}"
											style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
									</h:outputLink>
									<rich:spacer width="10px"></rich:spacer>
									<h:outputLink styleClass="outputLinkEx"
										value="/doc/ExciseUp/WholeSale/pdf//#{gatepassToDistrict_FL2_FL2B_activeAction.pdfname_CaseCode}"
										target="_blank">

									</h:outputLink>

									<h:commandButton styleClass="btn btn-success"
										value="Scan and Upload" rendered="#{!list11.finflg}"
										actionListener="#{gatepassToDistrict_FL2_FL2B_activeAction.scanAndUpload}"></h:commandButton>



								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
								</f:facet>
								<center>
									<h:commandButton value="Cancel Gatepass"
										styleClass="btn btn-danger" rendered="#{!list11.finflg}"
										actionListener="#{gatepassToDistrict_FL2_FL2B_activeAction.cancelGatepass}">
									</h:commandButton>
								</center>
							</rich:column>

							<f:facet name="footer">
								<rich:datascroller for="table55" />
							</f:facet>
						</rich:dataTable>

						<table align="center">

							<tr>
								<td colspan="6" style="color: Green;" align="left"><h:outputText
										rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.scanUploadFlag}"
										value="** Please read the instructions carefully before uploading."
										style="color: #ce0000;font-style: italic;font-size: large;text-decoration:blink;" /></td>
							</tr>


							<tr align="left">
								<td style="FONT-WEIGHT: bold; color: Green;" align="left"><h:outputText
										rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.scanUploadFlag}"
										value="Uploading for Gatepass Number(Same Pass No. should be entered in the first row of excel.):"
										style="FONT-WEIGHT: bold; FONT-SIZE: medium;" /></td>
								<td style="FONT-SIZE: large; color: Red"><h:outputText
										value="#{gatepassToDistrict_FL2_FL2B_activeAction.scanGatePassNo}"
										rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.scanUploadFlag}"
										style="COLOR: #0000ff;" /></td>
								<TD><h:outputLink value="/doc/ExciseUp/ExcelFormatFCL.pdf"
										target="_blank">
										<h:graphicImage value="/img/i.png" style="width:40px;"
											rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.scanUploadFlag }"></h:graphicImage>
									</h:outputLink></TD>





								<td><rich:fileUpload addControlLabel="Add File" id="link34"
										fileUploadListener="#{gatepassToDistrict_FL2_FL2B_activeAction.uploadCsv}"
										rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.scanUploadFlag and gatepassToDistrict_FL2_FL2B_activeAction.exclcsv eq 'C'}"
										maxFilesQuantity="1" listHeight="11" listWidth="250"
										clearControlLabel="clear" stopControlLabel="Stop"
										ontyperejected="if (!confirm(' ONLY .csv type file ALLOWED !!!')) return false"
										acceptedTypes="csv" clearAllControlLabel="Clear">
									</rich:fileUpload></td>

								<td><h:commandButton value="Upload CSV"
										styleClass="btn btn-primary"
										rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.scanUploadFlag and gatepassToDistrict_FL2_FL2B_activeAction.exclcsv eq 'C'}"
										action="#{gatepassToDistrict_FL2_FL2B_activeAction.csvSubmit }">
									</h:commandButton></td>
							</tr>
						</table>
						<div class="row">
							<div class="col-md-6 col-sm-6">
								<rich:dataTable align="center" id="table57" rows="10"
									width="100%" var="listk"
									rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.scanUploadFlag}"
									value="#{gatepassToDistrict_FL2_FL2B_activeAction.getVal}"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2">



									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Valid CaseCode" />
										</f:facet>
										<center>
											<h:outputText value="#{listk.casecode}" />
										</center>
									</rich:column>




									<f:facet name="footer">
										<rich:datascroller for="table57" />
									</f:facet>
								</rich:dataTable>
							</div>
							<div class="col-md-6 col-sm-6">
								<rich:dataTable align="center" id="table58" rows="10"
									width="100%" var="listl"
									rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.scanUploadFlag  }"
									value="#{gatepassToDistrict_FL2_FL2B_activeAction.sourceList}"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2">



									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="InValid CaseCode" />
										</f:facet>
										<center>
											<h:outputText value="#{listl}" />
										</center>
									</rich:column>





									<f:facet name="footer">
										<rich:datascroller for="table58" />
									</f:facet>
								</rich:dataTable>
							</div>
						</div>
						<div align="center">
							<table align="center">
								<tr>
									<td style="color: Red;" align="center" colspan="2"><h:outputText
											rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.scanUploadFlag and gatepassToDistrict_FL2_FL2B_activeAction.fl2LicenseType eq 'CL2'}"
											value="** Cases which are present in excel and are not displayed in first table are either already dispatched by you or casecodes are invalid."
											style="FONT-STYLE: italic; COLOR: #008040; FONT-SIZE: medium;" /></td>
								</tr>
								<tr>
									<td align="center"><h:commandButton value="Finalize"
											styleClass="btn btn-primary"
											rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.scanUploadFlag}"
											action="#{gatepassToDistrict_FL2_FL2B_activeAction.finalSubmit}">

										</h:commandButton> <h:commandButton value="Clear Old Data"
											styleClass="btn btn-danger"
											rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.scanUploadFlag}"
											action="#{gatepassToDistrict_FL2_FL2B_activeAction.delete}">
										</h:commandButton></td>

								</tr>
								<tr>
									<td style="color: Red;" align="center"><h:outputText
											rendered="#{gatepassToDistrict_FL2_FL2B_activeAction.scanUploadFlag}"
											value="**Please click on the above button if you have already uploaded casecodes, before uploading a new file."
											style="FONT-WEIGHT: bold; FONT-SIZE: medium;" /></td>
								</tr>
							</table>
						</div>
					</div>
				</div>

			</div>

		</h:form>
	</f:view>
</ui:composition>