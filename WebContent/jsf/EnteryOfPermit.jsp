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
													value="#{enteryOfPermitAction.hidden}"></h:inputHidden>
												<h2>
													<h:outputText value=" Entry Of Permit  "
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
													value="#{enteryOfPermitAction.distillery_nm}"
													style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>
										</tr>
										<tr>
											<TD><rich:spacer height="5px"></rich:spacer></TD>
										</tr>

										<TR>
											<TD align="center" colspan="2"><h:outputLabel
													value="#{enteryOfPermitAction.distillery_adrs}"
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
													<TD colspan="4" align="center"><h:selectOneRadio
															disabled="#{enteryOfPermitAction.disableFlg}"
															style="width: 40%"
															value="#{enteryOfPermitAction.radioButton}"
															onchange="this.form.submit()"
															valueChangeListener="#{enteryOfPermitAction.listMethod}">
															<f:selectItem itemLabel="From UP Distillery"
																itemValue="FUPD" />
															<f:selectItem itemLabel="From UP Brewery"
																itemValue="FUPB" />
															<f:selectItem itemLabel="From Out Side UP Distillery"
																itemValue="FOD" />
															<f:selectItem itemLabel="From Out Side UP Brewery"
																itemValue="FOB" />
														</h:selectOneRadio></TD>

												</TR>

												<tr height="15px">
													<td></td>
												</tr>

												<TR>
													<TD width="150px"><h5>
															<h:outputText value="* CSD Name" rendered="false"></h:outputText>
														</h5></TD>
													<TD><h:inputHidden
															value="#{enteryOfPermitAction.cdsName}"
															inputStyle="height:25px" disabled="true"></h:inputHidden>

													</TD>


													<TD width="150px"><h5>
															<h:outputText value="* CSD Address" rendered="false"></h:outputText>
														</h5></TD>
													<TD><h:inputHidden
															value="#{enteryOfPermitAction.cdsAddress}"
															inputStyle="height:25px" disabled="true"></h:inputHidden>

													</TD>


												</TR>

												<tr height="15px">
													<td></td>
												</tr>

												<TR>


													<TD><h5>
															<h:outputText value="* Permit No."></h:outputText>
														</h5></TD>
													<TD><h:inputText
															disabled="#{enteryOfPermitAction.disableFlg}"
															value="#{enteryOfPermitAction.permitNo}"
															inputStyle="height:25px"></h:inputText></TD>



													<TD width="150px"><h5>
															<h:outputText value="* Issue Date"></h:outputText>
														</h5></TD>
													<TD><rich:calendar
															disabled="#{enteryOfPermitAction.disableFlg}"
															value="#{enteryOfPermitAction.issueDate}"
															inputStyle="height:25px" datePattern="dd/MM/yyyy"></rich:calendar></TD>




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
															<h:outputText value="* Distillery / Brewery Name"></h:outputText>
														</h5></TD>
													<TD><h:inputText
															value="#{enteryOfPermitAction.distilleryBreweryName}"
															inputStyle="height:25px" disabled="false"
															rendered="#{enteryOfPermitAction.distilleryBreweryNameText_Flag}"></h:inputText>

														<h:selectOneMenu
															value="#{enteryOfPermitAction.distilleryId }"
															style="width: 30%;" onchange="this.form.submit();"
															valueChangeListener="#{enteryOfPermitAction.dist_detail}"
															rendered="#{enteryOfPermitAction.distillerylist_Flag }">
															<f:selectItems
																value="#{enteryOfPermitAction.distillerylist}" />
														</h:selectOneMenu> <h:selectOneMenu
															value="#{enteryOfPermitAction.breweryId }"
															style="width: 30%;" onchange="this.form.submit();"
															valueChangeListener="#{enteryOfPermitAction.brewery_detail}"
															rendered="#{enteryOfPermitAction.brewerylist_Flag}">
															<f:selectItems
																value="#{enteryOfPermitAction.brewerylist}" />
														</h:selectOneMenu></TD>


													<TD width="150px"><h5>
															<h:outputText value="* Distillery / Brewery Address"></h:outputText>
														</h5></TD>
													<TD><h:inputTextarea
															value="#{enteryOfPermitAction.distilleryBreweryAddress}"
															inputStyle="height:25px" disabled="false"></h:inputTextarea>

													</TD>


												</TR>

												<tr height="15px">
													<td></td>
												</tr>


												<TR>
													<TD width="150px"><h5>
															<h:outputText value="* Email Id"></h:outputText>
														</h5></TD>
													<TD><h:inputText
															value="#{enteryOfPermitAction.email_id}"
															inputStyle="height:25px" disabled="false"></h:inputText>

													</TD>


													<TD width="150px"><h5>
															<h:outputText value="* Contact No."></h:outputText>
														</h5></TD>
													<TD><h:inputText
															value="#{enteryOfPermitAction.contactNo }" maxlength="10"></h:inputText>
													</TD>


												</TR>

												<tr height="15px">
													<td></td>
												</tr>


												<TR>
													<TD width="150px"><h5>
															<h:outputText value="* Email Id" rendered="false"></h:outputText>
														</h5></TD>
													<TD><h:inputText
															value="#{enteryOfPermitAction.email_id}"
															inputStyle="height:25px" rendered="false"></h:inputText>

													</TD>


													<TD width="150px"><h5>
															<h:outputText value="* Contact No." rendered="false"></h:outputText>
														</h5></TD>
													<TD><h:inputText value="#" maxlength="10"
															rendered="false"></h:inputText></TD>


												</TR>


												<tr height="15px">
													<td></td>
												</tr>



											</TBODY>
										</TABLE>

										<table width="80%">
											<tbody>


												<TR>
													<TD><rich:dataTable columnClasses="columnClass1"
															headerClass="TableHead" footerClass="TableHead"
															rowClasses="TableRow1,TableRow2" styleClass="DataTable"
															id="table3" rows="10" width="100%"
															value="#{enteryOfPermitAction.brandPackagingDataList}"
															var="list">

															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Brand">
																	</h:outputText>
																</f:facet>
																<h:selectOneMenu
																	value="#{list.brandPackagingData_Brand}"
																	styleClass="dropdown-menu"  
																	onchange="this.form.submit();">
																	<f:selectItems
																		value="#{list.brandPackagingData_BrandList}" />
																</h:selectOneMenu>
															</rich:column>



															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Package ">
																	</h:outputText>
																</f:facet>
																<h:selectOneMenu
																	value="#{list.brandPackagingData_Capacity_id}"
																	styleClass="dropdown-menu"
																	onchange="this.form.submit();">
																	<f:selectItems
																		value="#{list.brandPackagingData_CapacityList }" />

																	<a4j:support event="onblur" reRender="size1">
																	</a4j:support>
																</h:selectOneMenu>
															</rich:column>






															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Quantity (ml)">
																	</h:outputText>
																</f:facet>
																<a4j:outputPanel id="size1">
																	<h:selectOneMenu
																		value="#{list.brandPackagingData_Packaging}"
																		styleClass="dropdown-menu"
																		onchange="this.form.submit();">
																		<f:selectItems
																			value="#{list.brandPackagingData_PackagingList }" />
																	</h:selectOneMenu>
																</a4j:outputPanel>
															</rich:column>

															<rich:column>
																<f:facet name="header">
																	<h:outputText value="No. Of Cases">
																	</h:outputText>
																</f:facet>

																<h:inputText
																	value="#{list.brandPackagingData_NoOfBoxes}"
																	styleClass="generalHeaderStyleOutput">


																	<a4j:support event="onblur" reRender="box">
																	</a4j:support>
																</h:inputText>



															</rich:column>


															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Case Size">
																	</h:outputText>
																</f:facet>

																<h:inputText value="#{list.cases}"
																	styleClass="generalHeaderStyleOutput">


																	<a4j:support event="onblur" reRender="box">
																	</a4j:support>
																</h:inputText>



															</rich:column>







															<rich:column>
																<f:facet name="header">
																	<h:outputText value=" No.Of Bottles ">
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
																	<h:commandButton class="imag"
																		action="#{enteryOfPermitAction.addRowMethodPackaging}"
																		image="/img/add.png" />
																</f:facet>
																<h:commandButton class="imag"
																	actionListener="#{enteryOfPermitAction.deleteRowMethodPackaging}"
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
														action="#{enteryOfPermitAction.save}" value="Save" />

													<rich:spacer width="10px"></rich:spacer>
													<h:commandButton class="btn btn-default"
														action="#{enteryOfPermitAction.reset}" value="Reset"></h:commandButton>
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
													value="#{enteryOfPermitAction.showDataTableList}"
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
															<h:outputText value="CSD Name">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_Cds_Name}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Permit No.">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_Permit_No}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Email Id">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_Licence_No}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Contact No.">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_contact_NO}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>

													<rich:column>
														<f:facet name="header">
														</f:facet>
														<center>

															<h:commandButton styleClass="btn btn-success"
																actionListener="#{enteryOfPermitAction.updtDetails}"
																value="Update"></h:commandButton>
														</center>

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
			
			<div style="overflow-y:scroll;width:100%; height:500px;">
			<rich:dataTable rendered="false" columnClasses="columnClass1"
													headerClass="TableHead" footerClass="TableHead"
													rowClasses="TableRow1,TableRow2" styleClass="DataTable"
													id="table4"  width="100%"
													value="#{enteryOfPermitAction.brandPackagingDataList1}" var="list" >
													 <rich:column>
														<f:facet name="header">
															<h:outputText value="Permit No.">
															</h:outputText>
														</f:facet>
														<h:outputText  value="#{list.licencenoo}" 
														 styleClass="generalHeaderStyleOutput" ></h:outputText>
													</rich:column>
													 
													 <rich:column>
														<f:facet name="header">
															<h:outputText value="Brand">
															</h:outputText>
														</f:facet>
														<h:outputText  value="#{list.brandPackagingData_Brand_Name}" 
														 styleClass="generalHeaderStyleOutput" ></h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Packaging(ml)">
															</h:outputText>
														</f:facet>
														<h:outputText  value="#{list.brandPackagingData_Brand_Size}" 
														 styleClass="generalHeaderStyleOutput" ></h:outputText>
													</rich:column>
													
													
													
													
													
													<rich:column>
														<f:facet name="header">
															<h:outputText value=" No.Of Bottles " >
															</h:outputText>
														</f:facet>
													<a4j:outputPanel id="box">	<h:outputText  value="#{list.brandPackagingData_Brand_No_OF_Bottels}" 
														 styleClass="generalHeaderStyleOutput" >
															 
															 
															 
														</h:outputText></a4j:outputPanel>
													</rich:column>
													
													
													<rich:column >
														<f:facet name="header">
															<h:outputText value="Finalize" >
															</h:outputText>
														</f:facet>
														<h:commandButton action="#{enteryOfPermitAction.finalizeData}" value="Finalize Data"  styleClass="btn btn-sm btn-danger" disabled="#{list.finalizedFlag eq 'F'}">
														<f:setPropertyActionListener value="#{list}" target="#{enteryOfPermitAction.bopd }" />
														</h:commandButton>
														
														
														
															<h:outputLink target="_blank" value="/doc/ExciseUp/Excel/#{list.request_id}#{list.gtinno}#{list.finalizedDateString}.xls">
															<h:outputText value="Download Excel" rendered="#{list.finalizedFlag eq 'F'}"/>
															
															</h:outputLink> 
														
														
														
													</rich:column>

												</rich:dataTable>
			</div>
			
			
		</h:form>

	</f:view>
</ui:composition>
