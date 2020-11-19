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
														style="font-size:18px;">
													</h:messages>
												</h3></TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
						</TR>
						<tr>
							<TD><h:inputHidden value="#{stockAdjustmentAction.hidden}"></h:inputHidden></TD>
						</tr>
						<h:panelGroup rendered="#{stockAdjustmentAction.flgtab}">
							<TR align="center" style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
								<TD>
									<h5>
										<h:outputText value="STOCK ADJUSTMENT "
											style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;"></h:outputText>
									</h5>
								</TD>
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
													value="#{stockAdjustmentAction.loginName}"
													style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>

										</tr>
										<tr>
											<TD><rich:spacer height="5px"></rich:spacer></TD>
										</tr>
										<TR>

											<TD align="center" colspan="2"><h:outputLabel
													value="#{stockAdjustmentAction.loginAdrs}"
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
									</TABLE>

									<div class="row col-md-12" align="center"
										style="BACKGROUND-COLOR: #dee0e2;">
										<div class="col-md-12" align="center">
											<h:selectOneRadio
												style="FONT-WEIGHT: bold;  font-size: 15px; width:15% "
												onclick="this.form.submit();"
												value="#{stockAdjustmentAction.radioType}"
												valueChangeListener="#{stockAdjustmentAction.radioListiner}">
												<f:selectItem itemValue="R" itemLabel="Recieved" />
												<f:selectItem itemValue="D" itemLabel="Dispatch" />

											</h:selectOneRadio>
										</div>

									</div>
									<div class="row" align="center">
										<rich:spacer height="20px"></rich:spacer>
									</div>
									<div class="row" align="center">
										<div class="col-md-12" align="center">
											<rich:dataTable columnClasses="columnClass1"
												headerClass="TableHead" footerClass="TableHead"
												rowClasses="TableRow1,TableRow2" styleClass="DataTable"
												id="table3" rows="10" width="100%"
												value="#{stockAdjustmentAction.brandPackagingDataList}"
												var="list">
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Sr.No">
														</h:outputText>
													</f:facet>
													<h:outputText value="#{list.sno}"
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
														style=" width : 277px;">
														<f:selectItems
															value="#{list.brandPackagingData_BrandList }" />
													</h:selectOneMenu>
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
															value="#{list.brandPackagingData_PackagingList }" />
													</h:selectOneMenu>
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
														<h:outputText value="No. Of Boxes">
														</h:outputText>
													</f:facet>

													<h:inputText value="#{list.brandPackagingData_NoOfBoxes}"
														styleClass="form-control">
														<a4j:support event="onblur" reRender="box">
														</a4j:support>
													</h:inputText>



												</rich:column>


												<rich:column>
													<f:facet name="header">
														<h:outputText value="Breakage">
														</h:outputText>
													</f:facet>

													<h:inputText value="#{list.brandPackagingData_Breakage}"
														styleClass="form-control">
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
															styleClass="form-control" disabled="true">
														</h:inputText>
													</a4j:outputPanel>
												</rich:column>

												<rich:column>
													<f:facet name="header">
														<h:outputText value="Current Stock(in bottles) ">
														</h:outputText>
													</f:facet>
													<a4j:outputPanel id="cur_stock">
														<h:inputText
															value="#{list.current_stock}"
															styleClass="form-control" disabled="true">
														</h:inputText>
													</a4j:outputPanel>
												</rich:column>


												<rich:column>
													<f:facet name="header">
														<h:commandButton class="imag"
															action="#{stockAdjustmentAction.addRowMethodPackaging}"
															image="/img/add.png" />
													</f:facet>
													<h:commandButton class="imag"
														actionListener="#{stockAdjustmentAction.deleteRowMethodPackaging}"
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
										<rich:spacer height="20px"></rich:spacer>
									</div>
									<div class="row" align="center">
										<h:commandButton styleClass="btn btn-primary"
											action="#{stockAdjustmentAction.save}" value="Save"
											onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;"></h:commandButton>
										<rich:spacer width="10px"></rich:spacer>
										<h:commandButton styleClass="btn btn-warning"
											action="#{stockAdjustmentAction.reset}" value="Reset"></h:commandButton>
									</div>
									<div class="row" align="center">
										<rich:spacer height="20px"></rich:spacer>
									</div>
									<div class="row" align="center">
										<div class="col-md-12" align="center">
											<rich:dataTable var="list" rows="10"
												value="#{stockAdjustmentAction.brandPackagingShowDataList}"
												width="100%" headerClass="TableHead" footerClass="TableHead"
												id="show" rowClasses="TableRow1,TableRow2"
												styleClass="DataTable">

												<rich:column>
													<f:facet name="header">
														<h:outputText value="Sr.No" />
													</f:facet>
													<h:outputText value="#{list.srNo }" />
												</rich:column>

												<rich:column sortBy="#{list.plan_date }">
													<f:facet name="header">
														<h:outputText value="Date" />
													</f:facet>
													<h:outputText value="#{list.plan_date }">
														<f:convertDateTime pattern="dd/MM/yyyy"
															timeZone="GMT+5:30" />
													</h:outputText>
												</rich:column>


												<rich:column sortBy="#{list.brandName }"
													filterBy="#{list.brandName}">
													<f:facet name="header">
														<h:outputText value="BrandName" />
													</f:facet>
													<h:outputText value="#{list.brandName }" />
												</rich:column>


												<rich:column sortBy="#{list.packageName}"
													filterBy="#{list.packageName}">
													<f:facet name="header">
														<h:outputText value="Packaging" />
													</f:facet>
													<h:outputText value="#{list.packageName }" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Quantity" />
													</f:facet>
													<h:outputText value="#{list.quantity }" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="No.Of Boxes" />
													</f:facet>
													<h:inputText value="#{list.no_of_box }" disabled="true" />
												</rich:column>
												<rich:column>
													<f:facet name="header">
														<h:outputText value="Planned No.Of Bottles" />
													</f:facet>
													<h:inputText value="#{list.no_of_planned_bottle }"
														disabled="true" />
												</rich:column>

												<f:facet name="footer">
													<rich:datascroller for="show"></rich:datascroller>
												</f:facet>

											</rich:dataTable>
										</div>
									</div>
								</TD>
							</TR>

						</h:panelGroup>
					</TABLE>

				</div>
			</div>

		</h:form>
	</f:view>
</ui:composition>