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
													value="#{bWFLImportAction_20_21.mappedhidden}"></h:inputHidden>
												<h2>
													<h:outputText value="  Generate Mapped Barcode  "
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
													value="#{bWFLImportAction_20_21.distillery_nm}"
													style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>
										</tr>
										<tr>
											<TD><rich:spacer height="5px"></rich:spacer></TD>
										</tr>

										<TR>
											<TD align="center" colspan="2"><h:outputLabel
													value="#{bWFLImportAction_20_21.distillery_adrs}"
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
						<tr align="center">
							<td><h:outputText value="License No"></h:outputText> <h:selectOneMenu
									value="#{bWFLImportAction_20_21.select_lic_no}"
									onchange="this.form.submit();">
									<f:selectItems value="#{bWFLImportAction_20_21.licno}" />
								</h:selectOneMenu></td>
						</tr>
						<TR>
							<TD><rich:dataTable columnClasses="columnClass1"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2" styleClass="DataTable"
									id="table2" rows="10" width="100%"
									value="#{bWFLImportAction_20_21.showDataTableList}" var="list">
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
											<h:outputText value="Finalize">
											</h:outputText>
										</f:facet>
										<h:commandButton action="#{bWFLImportAction_20_21.finalizeData}"
											onclick="return alert('Are you sure you want to Finalize this?');"
											value="Finalize Data" styleClass="btn btn-sm btn-danger"
											rendered="#{ list.cancel_req_dt_time eq '0'}"
											disabled="#{list.finalizedFlag eq 'F' or list.finalizedFlag eq 'N'}">
											<f:setPropertyActionListener value="#{list}"
												target="#{bWFLImportAction_20_21.bopd}" />
										</h:commandButton>

										<h:outputLink target="_blank"
											value="/doc/ExciseUp/Excel/#{list.gtinno}#{list.finalizedDateString}#{list.groupseq}.xls">
											<h:outputText value="Download Excel"
												rendered="#{list.finalizedFlag eq 'F' and list.cancel_req_dt_time eq '0'}" />

										</h:outputLink>

									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Cancel Request">
											</h:outputText>
										</f:facet>
										<a4j:commandButton value="Cancel"  rendered="#{list.finalizedFlag eq 'F' and list.cancel_req_dt_time eq '0'}"
										action="#{bWFLImportAction_20_21.showPopUpData}"
										oncomplete="#{rich:component('popup2')}.show()" reRender="messages,messages1,msg2,sv"
										styleClass="btn btn-sm btn-success" >
										<f:setPropertyActionListener target="#{bWFLImportAction_20_21.datatable }" value="#{list}" />
										</a4j:commandButton>
									 <h:outputText value="Request Submitted on #{list.cancel_req_dt_time }" rendered="#{list.finalizedFlag eq 'F'  and list.cancel_req_dt_time ne '0'}"/>
									</rich:column>
									<f:facet name="footer">
										<rich:datascroller for="table2"></rich:datascroller>
									</f:facet>
								</rich:dataTable></TD>
						</TR>

						<TR>
							<TD></TD>
						</TR>

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

					</TABLE>
				</div>
			</div>
		</h:form>
<rich:modalPanel id="popup2" minWidth="600" autosized="true">
			<f:facet name="header">
				<h:outputText value="Request for Cancellation" />
			</f:facet>
			<h:form>
			
			<TABLE width="80%">
									<TBODY>
										<TR>
											<TD align="left"><h3>
											<a4j:outputPanel id="msg2">
													<h:messages errorStyle="color:red" layout="table"
														id="messages1" infoStyle="color:green">
														
													</h:messages></a4j:outputPanel>
												</h3></TD>
										</TR>
									</TBODY>
								</TABLE>
				<div class="col-md-12">
					<div class="col-md-3">
						<b>Reason</b>
					</div>
					<div class="col-md-7">
						<h:inputTextarea
							value="#{bWFLImportAction_20_21.remark}"
							styleClass="form-control" style="FONT-STYLE: italic;width: 100%;"></h:inputTextarea>
					</div>
				</div>

				<div class="col-md-12">
					<h:commandButton value="Save" styleClass="btn btn-success btn-sm" id="sv"
						 action="#{bWFLImportAction_20_21.cancelRequest}" disabled="#{bWFLImportAction_20_21.popup eq 'S' }"></h:commandButton>

					<a4j:commandButton value="Close"
						styleClass="btn btn-warning btn-sm"
						oncomplete="#{rich:component('popup2')}.hide()" />
				</div>

			</h:form>
		</rich:modalPanel>
	</f:view>
</ui:composition>
