 <ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
	<a4j:outputPanel ajaxRendered="true">
    <script type="text/javascript">
    
        setTimeout("Richfaces.showModalPanel('xxx');", 900000);
        
    </script>
	</a4j:outputPanel>

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
													value="#{fl2d_old_stockAction.hidden}"></h:inputHidden>
												<h2>
													<h:outputText value="  Bottling Plan Of Old Stock "
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
													value="#{fl2d_old_stockAction.distillery_nm}"
													style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>
										</tr>
										<tr>
											<TD><rich:spacer height="5px"></rich:spacer></TD>
										</tr>

										<TR>
											<TD align="center" colspan="2"><h:outputLabel
													value="#{fl2d_old_stockAction.distillery_adrs}"
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
										<tr>
											<TD style="COLOR: #ff0000; FONT-STYLE: italic;"><h:outputText
													value="NOTE : You can now submit multiple plans on same date. A single plan should not exceed 5 Lakh bottles." /></TD>
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
													<TD colspan="2"><rich:calendar
															onchanged="this.form.submit();"
															value="#{fl2d_old_stockAction.dateOfBottling}"
															inputStyle="height:25px" datePattern="dd/MM/yyyy"
															disabled="true"></rich:calendar></TD>




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
															value="#{fl2d_old_stockAction.distillery_nm}"
															rendered="false"></h:inputText></TD>
													<TD width="150px"><h5>
															<h:outputText value="* Distillery Address"
																rendered="false"></h:outputText>
														</h5></TD>
													<TD colspan="3"><h:inputTextarea
															styleClass="form-control" disabled="true"
															value="#{fl2d_old_stockAction.distillery_adrs}"
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
															value="#{fl2d_old_stockAction.liqureTypeId }"
															styleClass="dropdown-menu" onchange="this.form.submit();">

															<f:selectItems
																value="#{fl2d_old_stockAction.liqureTypeList}" />
														</h:selectOneMenu></TD>


													<TD width="150px"><h5>
															<h:outputText value="* License Type"></h:outputText>
														</h5></TD>
													<TD><h:selectOneMenu value="#{fl2d_old_stockAction.licenceType }" 
													styleClass="dropdown-menu"   valueChangeListener="#{fl2d_old_stockAction.licencelistener}" 
													 onchange="this.form.submit();" 
													 >
 															 <f:selectItem itemValue="" itemLabel="-- Select --" />
 															   <f:selectItem itemValue="FL2D" itemLabel="FL2D" />
 	 															</h:selectOneMenu> </TD>


												</TR>

												<tr height="15px">
													<td></td>
												</tr>


												<TR>
													<TD width="150px"><h5>
															<h:outputText value="* License No."></h:outputText>
														</h5></TD>
													<TD><h:selectOneMenu
															value="#{fl2d_old_stockAction.licenceNoId }"
															styleClass="dropdown-menu" onchange="this.form.submit();"
															valueChangeListener="#{fl2d_old_stockAction.getaddrowdata}">

															<f:selectItems
																value="#{fl2d_old_stockAction.licenceNoList}" />
														</h:selectOneMenu></TD>


													<TD width="150px"><h5>
															<h:outputText value=""></h:outputText>
														</h5></TD>
													<TD></TD>


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
															value="#{fl2d_old_stockAction.brandPackagingDataList}"
															var="list">
															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Plan Id">
																	</h:outputText>
																</f:facet>
																<h:outputText value="#{list.seq}"
																	styleClass="generalHeaderStyleOutput">
																</h:outputText>
															</rich:column>
															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Brand">
																	</h:outputText>
																</f:facet>
																<h:selectOneMenu value="#{list.brandPackagingData_Brand}"
																styleClass="dropdown-menu" onchange="this.form.submit();">
																<f:selectItems value="#{list.brandPackagingData_BrandList }" />
															</h:selectOneMenu>
															</rich:column>
															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Capacity">
																	</h:outputText>
																</f:facet>
																<h:selectOneMenu
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


																	<a4j:support event="onblur" reRender="box">
																	</a4j:support>
																</h:inputText>



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
																<h:commandButton class="imag"
																	action="#{fl2d_old_stockAction.addRowMethodPackaging}"
																	image="/img/add.png"/>
															</f:facet>
															<h:commandButton class="imag"
																actionListener="#{fl2d_old_stockAction.deleteRowMethodPackaging}"
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
														action="#{fl2d_old_stockAction.save}" value="Save" />

													<rich:spacer width="10px"></rich:spacer>
													<h:commandButton class="btn btn-default"
														action="#{fl2d_old_stockAction.reset}" value="Reset"></h:commandButton>
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
													value="#{fl2d_old_stockAction.showDataTableList}"
													var="list">
													<rich:column sortBy="#{list.planid}">
														<f:facet name="header">
															<h:outputText value="Plan Id.">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.planid}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>
													<rich:column sortBy="#{list.showDataTable_Date}">
														<f:facet name="header">
															<h:outputText value="Date">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_Date}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>
													<rich:column filterBy="#{list.showDataTable_LiqureType}"
														sortBy="#{list.showDataTable_LiqureType}">
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
													<rich:column filterBy="#{list.showDataTable_Brand}"
														sortBy="#{list.showDataTable_Brand}">
														<f:facet name="header">
															<h:outputText value="Brand">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_Brand}"
															styleClass="generalHeaderStyleOutput">

														</h:outputText>
													</rich:column>
													<rich:column filterBy="#{list.showDataTable_Packging}"
														sortBy="#{list.showDataTable_Packging}">
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
															<h:outputText value="Finalize">
															</h:outputText>
														</f:facet>
														<h:commandButton
															action="#{fl2d_old_stockAction.finalizeData}"
															value="Finalize Data" styleClass="btn btn-sm btn-danger"
															disabled="#{list.finalizedFlag eq 'F' or list.finalizedFlag eq 'N'}"
															onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;">
															<f:setPropertyActionListener value="#{list}"
																target="#{fl2d_old_stockAction.bopd }" />
														</h:commandButton>



														<h:outputLink target="_blank"
															value="/doc/ExciseUp/Excel/FL2D#{list.planid}#{list.gtinno}#{list.finalizedDateString}.xls">
															<h:outputText value="Download Excel"
																rendered="#{list.finalizedFlag eq 'F' }" />

														</h:outputLink>



														



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
</ui:composition>
