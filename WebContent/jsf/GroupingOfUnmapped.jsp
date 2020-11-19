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
													value="#{groupingOfUnmappedAction.hidden}"></h:inputHidden>
												<h2>
													<h:outputText value="  Group Permit For Unmapped Data  "
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
													value="#{groupingOfUnmappedAction.distillery_nm}"
													style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>
										</tr>
										<tr>
											<TD><rich:spacer height="5px"></rich:spacer></TD>
										</tr>

										<TR>
											<TD align="center" colspan="2"><h:outputLabel
													value="#{groupingOfUnmappedAction.distillery_adrs}"
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
							<TD><rich:dataTable columnClasses="columnClass1"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2" styleClass="DataTable"
									id="table2" rows="10" width="100%"
									value="#{groupingOfUnmappedAction.showDataTableList}"
									var="list">
									<rich:column sortBy="#{list.showDataTable_Date}"
										filterBy="#{list.showDataTable_Date}">
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
											<h:outputText value="Permit No. ">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list.permitnoD}"
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
											<h:outputText value="Brand">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list.showDataTable_Brand}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Capacity">
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
										<h:outputText value="#{list.showDataTable_PlanNoOfBottling}"
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
											<h:outputText value="Group">
											</h:outputText>
										</f:facet>
										<h:selectBooleanCheckbox value="#{list.group}"
											id="chkRememberMe3" />
									</rich:column>

								
									<f:facet name="footer">
										<rich:datascroller for="table2"></rich:datascroller>
									</f:facet>
								</rich:dataTable></TD>
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
														action="#{groupingOfUnmappedAction.checkList}"
														value="Save"
														onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;">
													</h:commandButton>

													<rich:spacer width="10px"></rich:spacer>
													<h:commandButton class="btn btn-default"
														action="#{groupingOfUnmappedAction.reset}" value="Reset"></h:commandButton>
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
							<rich:spacer height="30px"></rich:spacer>
						</tr>

						<TR>
							<TD><rich:dataTable columnClasses="columnClass1"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2" styleClass="DataTable"
									id="table21" rows="10" width="100%"
									value="#{groupingOfUnmappedAction.savedDataTableList}"
									var="list11">
									<rich:column sortBy="#{list11.showDataTable_Date}"
										filterBy="#{list11.showDataTable_Date}">
										<f:facet name="header">
											<h:outputText value="Plan Date">
											</h:outputText>
											
										</f:facet>
										<h:outputText value="#{list11.planDateDt}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									<rich:column sortBy="#{list11.finalize_Date}"
										filterBy="#{list11.finalize_Date}">
										<f:facet name="header">
											<h:outputText value="Finalize Date">
											</h:outputText>
											
										</f:facet>
										<h:outputText value="#{list11.finalize_Date}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									
									<rich:column sortBy="#{list11.gtinno}"
										filterBy="#{list11.gtinno}">
										<f:facet name="header">
											<h:outputText value="Etin No">
											</h:outputText>
											
										</f:facet>
										<h:outputText value="#{list11.gtinno}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									
									

									<rich:column>
										<f:facet name="header">
											<h:outputText value=" Group Id ">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.groupIdDt}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>

									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Liquor Type">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.liquorTypeDt}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Licence Type">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.licenseTypeDt}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Brand">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.brandNmDt}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Capacity">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.pckgNmDt}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Quantity">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.quantityDt}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>


									<rich:column>
										<f:facet name="header">
											<h:outputText value="Plan No. Of Bottling">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.nmbrOfBottlesDt}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>


									<rich:column>
										<f:facet name="header">
											<h:outputText value="No. Of Cases">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.nmbrOfBoxesDt}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Plan No. Of Bottling">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.nmbrOfBottlesDt}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>


									<rich:column>
										<f:facet name="header">
											<h:outputText value="No. Of Cases">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.nmbrOfBoxesDt}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									
									
									
								<!-- 	<rich:column>
										<f:facet name="header">
											<h:outputText value="AssignedCaseSeq.">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.provided_case_seq}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									
									
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="AssignedBottleSeq.">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.provided_bottling_seq}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									
									 -->
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="TotalRecivedCases">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.recived_cases}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									
									
										<rich:column>
										<f:facet name="header">
											<h:outputText value="Finalize">
											</h:outputText>
										</f:facet>
										<h:commandButton
											action="#{groupingOfUnmappedAction.finalizeData}"
											value="Finalize Data" styleClass="btn btn-sm btn-danger"
											disabled="#{list11.finalizedFlag eq 'F' or list11.finalizedFlag eq 'N'}">
											<f:setPropertyActionListener value="#{list11}"
												target="#{groupingOfUnmappedAction.bopd}" />
										</h:commandButton>

										<!-- <h:outputLink target="_blank"
											value="/doc/ExciseUp/Excel/#{list.gtinno}#{list.finalizedDateString}#{list.seq}.xls">
											<h:outputText value="Download Excel"
												rendered="#{list11.finalizedFlag eq 'F'}" />

										</h:outputLink> -->
									</rich:column>

									<f:facet name="footer">
										<rich:datascroller for="table21"></rich:datascroller>
									</f:facet>
								</rich:dataTable></TD>
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
