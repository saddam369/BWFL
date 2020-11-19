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
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD align="center"></TD>
						</TR>
						<TR>
							<TD align="center" width="100%">
								<TABLE align="center" width="100%">
									<TBODY>
										<TR align="center">

											<TD align="center" width="100%" class="pghead"><h:inputHidden
													value="#{bWFL_StockReceiveAction.hidden}"></h:inputHidden>
												<h2>
													<h:outputText value=" PERMIT ENTRY  "
														style="TEXT-DECORATION: underline; FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;">
													</h:outputText>
												</h2></TD>
										</TR>

										<tr>
											<td><rich:separator lineType="dashed"></rich:separator></td>
										</tr>

										<tr>
											<TD><rich:spacer height="30px"></rich:spacer></TD>
										</tr>

										<tr>
											<TD align="center" colspan="2"><h:outputLabel
													value="#{bWFL_StockReceiveAction.distillery_nm}"
													style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>
										</tr>
										<tr>
											<TD><rich:spacer height="5px"></rich:spacer></TD>
										</tr>

										<TR>
											<TD align="center" colspan="2"><h:outputLabel
													value="#{bWFL_StockReceiveAction.distillery_adrs}"
													style="COLOR: #000000; FONT-SIZE: medium;">
												</h:outputLabel></TD>
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
						<TR>
							<TD colspan="1" align="center">
								<div class="panel panel-default">
									<div class="panel-body">
										<TABLE width="80%">
											<TBODY>


												<TR>


													<TD><h5>
															<h:outputText value="* Date"></h:outputText>
														</h5></TD>
													<TD><rich:calendar onchanged="this.form.submit();"
															value="#{bWFL_StockReceiveAction.dateOfBottling}"
															inputStyle="height:25px" datePattern="dd/MM/yyyy"
															disabled="true"></rich:calendar></TD>
													<TD width="150px"><h5>
															<h:outputText value="* Unit Name"></h:outputText>
														</h5></TD>
													<TD><h:selectOneMenu
															disabled="#{bWFL_StockReceiveAction.updateFlg }"
															converterMessage="Unit name Required" style="width:250px;"
															value="#{bWFL_StockReceiveAction.unit_id }"
															valueChangeListener="#{bWFL_StockReceiveAction.unitListnr}"
															styleClass="dropdown-menu" onchange="this.form.submit();">

															<f:selectItems
																value="#{bWFL_StockReceiveAction.unitlist}" />
														</h:selectOneMenu></TD>



												</TR>

												<tr height="10px">
													<td></td>
												</tr>

												<TR>
													<TD width="150px"><h5>
															<h:outputText value="* Distillery Name" rendered="false"></h:outputText>
														</h5></TD>
													<TD><h:inputText styleClass="form-control"
															disabled="true"
															value="#{bWFL_StockReceiveAction.distillery_nm}"
															rendered="false"></h:inputText></TD>
													<TD width="150px"><h5>
															<h:outputText value="* Distillery Address"
																rendered="false"></h:outputText>
														</h5></TD>
													<TD colspan="3"><h:inputTextarea
															styleClass="form-control" disabled="true"
															value="#{bWFL_StockReceiveAction.distillery_adrs}"
															rendered="false"></h:inputTextarea></TD>

												</TR>

												<tr height="5px">
													<td></td>
												</tr>


												<TR>
													<TD width="150px"><h5>
															<h:outputText value="* Liquor Type"></h:outputText>
														</h5></TD>
													<TD><h:selectOneMenu
															value="#{bWFL_StockReceiveAction.liqureTypeId }"
															styleClass="dropdown-menu" onchange="this.form.submit();">

															<f:selectItems
																value="#{bWFL_StockReceiveAction.liqureTypeList}" />
														</h:selectOneMenu></TD>


													<TD width="150px"><h5>
															<h:outputText value="* License Type"></h:outputText>
														</h5></TD>
													<TD><h:selectOneMenu
															disabled="#{bWFL_StockReceiveAction.updateFlg }"
															value="#{bWFL_StockReceiveAction.licenceType }"
															rendered="#{bWFL_StockReceiveAction.liqureTypeId ne '3'}"
															styleClass="dropdown-menu"
															valueChangeListener="#{bWFL_StockReceiveAction.licencelistener}"
															onchange="this.form.submit();">
															<f:selectItem itemValue="" itemLabel="-- Select --" />
															<f:selectItem itemValue="FL2D" itemLabel="FL2D" />

														</h:selectOneMenu></TD>


												</TR>


												<tr height="15px">
													<td></td>
												</tr>


												<TR>
													<TD width="150px"><h5>
															<h:outputText value="* License No."></h:outputText>
														</h5></TD>
													<TD>
														<h:outputText value="#{bWFL_StockReceiveAction.licenceNoId }" >
																</h:outputText>
														</TD>


													<TD width="150px"><h5>
															<h:outputText value="* Permit No."></h:outputText>
														</h5></TD>
													<TD><h:inputText
															disabled="#{bWFL_StockReceiveAction.updateFlg }"
															value="#{bWFL_StockReceiveAction.permitNo }"></h:inputText>
													</TD>

												</TR>
												<TR>
													<TD width="150px"><h5>
															<h:outputText value="Brand Registering Unit"></h:outputText>
														</h5></TD>
													<TD><h:selectOneMenu style="width:250px;"
															value="#{bWFL_StockReceiveAction.other_unit_id }"
															styleClass="dropdown-menu" onchange="this.form.submit();"
														valueChangeListener="#{bWFL_StockReceiveAction.getaddrowdata}">
														<f:selectItems
																value="#{bWFL_StockReceiveAction.other_unit_list}" />
														</h:selectOneMenu></TD>
												</TR>






											</TBODY>
										</TABLE>

										<table width="80%">
											<tbody>


												<TR>
													<TD><rich:dataTable columnClasses="columnClass1"
															headerClass="TableHead" footerClass="TableHead"
															rowClasses="TableRow1,TableRow2" styleClass="DataTable"
															id="table3" rows="10" width="100%"
															value="#{bWFL_StockReceiveAction.brandPackagingDataList}"
															var="list">

															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Brand">
																	</h:outputText>
																</f:facet>
																<h:selectOneMenu
																	disabled="#{bWFL_StockReceiveAction.updateFlg }"
																	value="#{list.brandPackagingData_Brand}"
																	styleClass="dropdown-menu"
																	onchange="this.form.submit();">
																	<f:selectItems
																		value="#{list.brandPackagingData_BrandList }" />
																</h:selectOneMenu>
															</rich:column>
															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Capacity">
																	</h:outputText>
																</f:facet>
																<h:selectOneMenu
																	disabled="#{bWFL_StockReceiveAction.updateFlg }"
																	value="#{list.brandPackagingData_Packaging}"
																	styleClass="dropdown-menu"
																	onchange="this.form.submit();">
																	<f:selectItems
																		value="#{list.brandPackagingData_PackagingList }" />
																</h:selectOneMenu>
															</rich:column>


															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Quantity">
																	</h:outputText>
																</f:facet>
																<h:outputText
																	value="#{list.brandPackagingData_Quantity}"
																	styleClass="generalHeaderStyleOutput">
																</h:outputText>
															</rich:column>


															<rich:column>
																<f:facet name="header">
																	<h:outputText value="No. Of Cases">
																	</h:outputText>
																</f:facet>

																<h:inputText
																	value="#{list.brandPackagingData_NoOfBoxes}"
																	styleClass="generalHeaderStyleOutput">


																	<a4j:support event="onblur"
																		reRender="box,box1,fees,totalpermitfee">
																	</a4j:support>
																</h:inputText>



															</rich:column>
															<rich:column>
																<f:facet name="header">
																	<h:outputText value="No of bottles per cases">
																	</h:outputText>
																</f:facet>
																<a4j:outputPanel id="box1">
																	<h:inputText value="#{list.noOfBottlesPerCase}" disabled="#{list.noOfBottleFlg}"
																		styleClass="generalHeaderStyleOutput">


																		<a4j:support event="onblur" reRender="box,fees,totalpermitfee">
																		</a4j:support>
																	</h:inputText>
																</a4j:outputPanel>
															</rich:column>
															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Planned No.Of Bottles ">
																	</h:outputText>
																</f:facet>
																<a4j:outputPanel id="box">
																	<h:inputText
																		value="#{list.brandPackagingData_PlanNoOfBottling}"
																		styleClass="generalHeaderStyleOutput" disabled="true">



																	</h:inputText>
																</a4j:outputPanel>
															</rich:column>

															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Permit Fee"
																		styleClass="generalHeaderOutputTable" />
																</f:facet>
																<h:outputText value="#{list.permitFee_dt}">
																</h:outputText>
															</rich:column>

															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Calculated Permit Fee"
																		styleClass="generalHeaderOutputTable" />
																</f:facet>
																<h:outputText value="#{list.calPermitFee_dt}" id="fees">
																	<f:convertNumber maxFractionDigits="2"
																		pattern="#############0.00" />
																</h:outputText>

															</rich:column>

															<rich:column>
																<f:facet name="header">
																	<h:commandButton class="imag"
																		rendered="#{!bWFL_StockReceiveAction.updateFlg }"
																		action="#{bWFL_StockReceiveAction.addRowMethodPackaging}"
																		image="/img/add.png" />
																</f:facet>
																<h:commandButton class="imag"
																	rendered="#{!bWFL_StockReceiveAction.updateFlg }"
																	actionListener="#{bWFL_StockReceiveAction.deleteRowMethodPackaging}"
																	style="background: transparent;height:30px "
																	image="/img/del.png" />
															</rich:column>



															<f:facet name="footer">
																<rich:datascroller for="table3"></rich:datascroller>
															</f:facet>
														</rich:dataTable></TD>
												</TR>

											</tbody>
										</table>
										<div class="row" align="center">
											<rich:spacer height="10px"></rich:spacer>
										</div>

										<div class="row" align="right">
											<a4j:commandButton rendered="true" disabled="true"
												styleClass="btn btn-danger" id="totalpermitfee"
												actionListener="#{bWFL_StockReceiveAction.calculateTotalFee}"
												reRender="totalpermitfee"
												value="Total Permit Fee : #{bWFL_StockReceiveAction.db_totalFees}">
											</a4j:commandButton>
										</div>

									</div>
								</div>
							</TD>
						</TR>





						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<tr align="center">
							<td colspan="4">
								<table width="100%">
									<tr>
										<td width="100%"><div class="panel-footer clearfix">
												<div align="center">

													<h:commandButton class="btn btn-primary"
														action="#{bWFL_StockReceiveAction.save}" value="Save" />

													<rich:spacer width="10px"></rich:spacer>
													<h:commandButton class="btn btn-default"
														action="#{bWFL_StockReceiveAction.reset}" value="Reset"></h:commandButton>
												</div>
											</div></td>

									</tr>
								</table>
							</td>
						</tr>
						<TR>
							<TD>
								<TABLE width="100%">
									<TBODY>
										<TR>
											<TD><rich:dataTable columnClasses="columnClass1"
													headerClass="TableHead" footerClass="TableHead"
													rowClasses="TableRow1,TableRow2" styleClass="DataTable"
													id="table2" rows="10" width="100%"
													value="#{bWFL_StockReceiveAction.showDataTableList}"
													var="list">
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Date">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_Date}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Liquor Type">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_LiqureType}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Licence Type">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_LicenceType}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Unit Name">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.unit_name}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="
															Permit No">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_PermitNo}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>


													<rich:column>
														<f:facet name="header">
															<h:outputText value="Brand">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_Brand}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="capacity">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_Packging}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Quantity">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_Quntity}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>


													<rich:column>
														<f:facet name="header">
															<h:outputText value="Plan No. Of Bottling">
															</h:outputText>
														</f:facet>
														<h:outputText
															value="#{list.showDataTable_PlanNoOfBottling}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>


													<rich:column>
														<f:facet name="header">
															<h:outputText value="No. Of Cases">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_NoOfBoxes}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Updation">
															</h:outputText>
														</f:facet>
														<h:commandButton
															actionListener="#{bWFL_StockReceiveAction.updatelisnr}"
															value="Update" styleClass="btn btn-sm btn-info"
															disabled="#{list.finalizedFlag eq 'F'}">
														</h:commandButton>
														<h:commandButton rendered="false"
															action="#{bWFL_StockReceiveAction.finalizeData}"
															value="Finalize Data" styleClass="btn btn-sm btn-danger"
															disabled="#{list.finalizedFlag eq 'F'}">
															<f:setPropertyActionListener value="#{list}"
																target="#{bWFL_StockReceiveAction.bopd }" />
														</h:commandButton>



														<h:outputLink target="_blank" rendered="false"
															value="/doc/ExciseUp/Excel/#{list.request_id}#{list.gtinno}#{list.finalizedDateString}.xls">
															<h:outputText value="Download Excel"
																rendered="#{list.finalizedFlag eq 'F'}" />

														</h:outputLink>



													</rich:column>

													<rich:column>
														<f:facet name="header">
															<h:outputText value="CancelPermit"
																styleClass="generalHeaderStyle" />
														</f:facet>
														<h:inputTextarea value="#{list.cancel_reason}"
															rendered="#{list.cancel_flg eq 'C'  and  list.finalizedFlag eq 'F'  }" />
														<a4j:commandButton value="Cancel Permit"
															styleClass="btn btn-danger"
															disabled="#{list.cancel_order_flg}"
															oncomplete="#{rich:component('popup')}.show();"
															rendered="#{list.cancel_flg eq 'C'  and  list.finalizedFlag eq 'F'  }">

															<f:setPropertyActionListener
																target="#{bWFL_StockReceiveAction.cancelData}"
																value="#{list}" />

														</a4j:commandButton>


													</rich:column>

													<f:facet name="footer">
														<rich:datascroller for="table2"></rich:datascroller>
													</f:facet>
												</rich:dataTable></TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>

						<tr>
							<td colspan="4" align="center"></td>
						</tr>
						<tr>
							<td width="100%"><div class="panel-footer clearfix">
									<div align="right"></div>
								</div></td>

						</tr>

					</TABLE>
				</div>
			</div>
		</h:form>

	</f:view>
	
		<rich:modalPanel id="popup" width="700" height="300">
	
	
	<f:facet name="header">  
         <h:outputText value="Enter Detail Here"/>  
    </f:facet> 
	<h:form>
	
	<TABLE width="80%">
									<TBODY>
										<TR>
											<TD align="left"><h3>
											<a4j:outputPanel id="p1">
													<h:messages errorStyle="color:red" layout="table"
														id="messages1" infoStyle="color:green">
													</h:messages>
													</a4j:outputPanel>
												</h3></TD>
										</TR>
									</TBODY>
								</TABLE>
	<table align="center">
	<tr>
	<td>Cancellation Order No</td>
	<td><h:inputText value="#{bWFL_StockReceiveAction.cancel_order_no}" id="id1"></h:inputText></td>
	
	
	<td>Order Date</td>
	<td><rich:calendar value="#{bWFL_StockReceiveAction.cancel_order_date}" id="id2"></rich:calendar></td>
	
	
	</tr>
	<tr>
	
	<td>Authority Name</td>
	<td><h:inputText value="#{bWFL_StockReceiveAction.authority_name}" id="id3"></h:inputText></td>
	
	<td>Upload Permit Cancel Order</td>
	<td><rich:fileUpload listHeight="35" listWidth="250" allowFlash="false" 
									ontyperejected="if (!confirm(' ONLY .pdf type file ALLOWED !!!')) return false"
									maxFilesQuantity="1" clearControlLabel="Clear"
									clearAllControlLabel="Clear All" sizeErrorLabel=""
									addControlLabel="Add Pdf" 
									fileUploadListener="#{bWFL_StockReceiveAction.fileUpload}"
									
									
									>
									<a4j:support event="onfileuploadcomplete" reRender="messages1,renpdftrue1"></a4j:support>
									
									
									</rich:fileUpload>
									<a4j:outputPanel id="renpdftrue1">
									<h:outputLink
										rendered="#{bWFL_StockReceiveAction.doc1upload}"
										target="_blank"
										value="/doc/ExciseUp/Fl2d/cancelpermit/#{bWFL_StockReceiveAction.uploadedPdfName}">

										<h:graphicImage value="/images/download.gif" alt="view document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</h:outputLink>
								</a4j:outputPanel>
									
									
									</td>
	</tr>
	
	
	
	<tr>
	<td></td>
	<td align="center">
	<a4j:commandButton value="Cancel Order" action="#{bWFL_StockReceiveAction.cancelOrder}" reRender="messages1,id1,id2,id3,p1" styleClass="btn btn-danger"></a4j:commandButton>
	<h:commandButton value="Close" action="#{bWFL_StockReceiveAction.cancelOrderClose}" styleClass="btn btn-primary"></h:commandButton>
	</td>
	</tr>
	</table>
	</h:form>
	
	</rich:modalPanel>
	
</ui:composition>
