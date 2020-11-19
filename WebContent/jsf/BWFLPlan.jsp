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
													value="#{bWFLPlanAction.hidden}"></h:inputHidden>
												<h2>
													<h:outputText value="  Permit Entry  "
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
													<TD width="150px"><h5>
															<h:outputText value="* License Type"></h:outputText>
														</h5></TD>

													<TD>
													<h:inputText readonly="true"
															value="#{bWFLPlanAction.licenceType }"></h:inputText>
													</TD>

													<TD width="150px"><h5>
															<h:outputText value="Unit Name"></h:outputText>
														</h5></TD>
													<TD><h:inputTextarea readonly="true"
															disabled="#{bWFLPlanAction.disabledFlag}"
															value="#{bWFLPlanAction.distillery_nm}"
															onchange="this.form.submit();"
															valueChangeListener="#{bWFLPlanAction.getaddrowdata}">
														</h:inputTextarea></TD>




												</TR>

												<tr height="10px">
													<td></td>
												</tr>
												<TR>
													<TD width="150px"><h5>
															<h:outputText value="* License No."></h:outputText>
														</h5></TD>
													<TD><h:inputText readonly="true"
															value="#{bWFLPlanAction.licenceNum }"></h:inputText></TD>


													<TD><h5>
															<h:outputText value="* Permit Date"></h:outputText>
														</h5></TD>
													<TD colspan="2"><rich:calendar
															disabled="#{bWFLPlanAction.disabledFlag}"
															value="#{bWFLPlanAction.permitdt}"
															inputStyle="height:25px"></rich:calendar></TD>



												</TR>



												<TR>


													<TD width="150px"><h5>
															<h:outputText value="* Type"></h:outputText>
														</h5></TD>

													<TD><h:selectOneRadio style="width:50%;"
															onchange="this.form.submit();"
															value="#{bWFLPlanAction.bottlngType}">
															<f:selectItem itemLabel="Mapped" itemValue="M" />
															<f:selectItem itemLabel="Unmapped" itemValue="U" />

														</h:selectOneRadio></TD>


													<TD width="150px"><h5>
															<h:outputText value="*Validity Date"></h:outputText>
														</h5></TD>

													<TD><rich:calendar
															value="#{bWFLPlanAction.validityDate}"
															disabled="#{bWFLPlanAction.bottlngType eq 'U'}">
														</rich:calendar></TD>





												</TR>

												<tr>
													<TD width="150px"><h5>
															<h:outputText value="*PermitNo"></h:outputText>
														</h5></TD>

													<TD><h:inputText
															disabled="#{bWFLPlanAction.disabledFlag}"
															value="#{bWFLPlanAction.permitNoEnterd}"></h:inputText></TD>

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
															value="#{bWFLPlanAction.brandPackagingDataList}"
															var="list">


															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Brand">
																	</h:outputText>
																</f:facet>
																<h:selectOneMenu disabled="#{bWFLPlanAction.update }"
																	value="#{list.brandPackagingData_Brand}"
																	styleClass="dropdown-menu"
																	onchange="this.form.submit();">
																	<f:selectItems
																		value="#{list.brandPackagingData_BrandList}" />
																</h:selectOneMenu>
															</rich:column>
															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Capacity">
																	</h:outputText>
																</f:facet>
																<h:selectOneMenu disabled="#{bWFLPlanAction.update }"
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
																	<h:outputText value="ETIN No.">
																	</h:outputText>
																</f:facet>
																<h:outputText
																	value="#{list.brandPackagingData_etinNmbr}"
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
																		action="#{bWFLPlanAction.addRowMethodPackaging}"
																		image="/img/add.png" />
																</f:facet>
																<h:commandButton class="imag"
																	actionListener="#{bWFLPlanAction.deleteRowMethodPackaging}"
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
														action="#{bWFLPlanAction.save}" value="Save"
														onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;">
													</h:commandButton>

													<rich:spacer width="10px"></rich:spacer>
													<h:commandButton class="btn btn-default"
														action="#{bWFLPlanAction.reset}" value="Reset"></h:commandButton>
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
											<TD></TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
						</TR>



						<tr>
							<td colspan="4" align="center"></td>
						</tr>
						<tr>
							<td width="100%"><div class="panel-footer clearfix">
									<div align="right"></div>
								</div></td>

						</tr>


						<rich:dataTable align="center" styleClass="table-responsive"
							width="100%" id="rentopiccat1" rows="10"
							value="#{bWFLPlanAction.displayDataList}" var="list2"
							style="FONT-FAMILY: 'Baskerville Old Face';"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2">

							<rich:column id="column1">
								<f:facet name="header">
									<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.snothrd}" />
								</center>
							</rich:column>






							<rich:column>
								<f:facet name="header">
									<h:outputText value="License Type."
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.licenseType_dt}" />
								</center>
							</rich:column>


							<rich:column id="column3">
								<f:facet name="header">
									<h:outputText value="Unit Name" styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.unitName_dt}" />
								</center>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="License No."
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.licenseNmbr_dt}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Permit Date"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.permitDt_dt}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Permit Number"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.permitNmbr_dt}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Type" styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.mappedType_dt}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Dispatch Box/Bottles"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.dispbox_dt}" />
									/
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.dispbot_dt}" />
								</center>
							</rich:column>
							<f:facet name="footer">
								<rich:datascroller for="rentopiccat1" />
							</f:facet>
						</rich:dataTable>


					</TABLE>
				</div>
			</div>
		</h:form>

	</f:view>
</ui:composition>
