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
													<h:outputText value=" BAR CODE FOR CSD PERMIT  "
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
									<h:panelGroup rendered="#{!barCodeForCSDAction.branddata_Flag}">
										<TABLE width="70%" style="background-color: beige">
											<TBODY>
											
											
											
											
											
											
												
												<tr height="15px"><td></td></tr>
											
											
											
											
											
											
											<TR>
											
		
															<TD     ><h5>
															<h:outputText value="* Permit No."></h:outputText>
														</h5></TD>
													<TD ><h:inputText
															value="#{barCodeForCSDAction.permit_No}" inputStyle="height:25px"
															 disabled="false"></h:inputText></TD>
															
															
															
															<TD ><h5>
															<h:outputText value="*Permit Date"></h:outputText>
														</h5></TD>
													<TD><rich:calendar onchanged="this.form.submit();" 
															value="#{barCodeForCSDAction.dtdate}" inputStyle="height:25px"
															datePattern="dd/MM/yyyy" disabled="false"></rich:calendar>
														
														</TD>
															



												</TR>
											
											<tr height="10px"><td></td></tr>

												<TR>
												<TD width="150px"><h5>
															<h:outputText value="* Distillery Name" rendered="false"></h:outputText>
														</h5></TD>
													<TD><h:inputText styleClass="form-control"
															disabled="true" value="#{barCodeForCSDAction.distillery_nm}" rendered="false"></h:inputText></TD>
													<TD width="150px"><h5>
															<h:outputText value="* Distillery Address" rendered="false"></h:outputText>
														</h5></TD>
													<TD colspan="3"><h:inputTextarea
															styleClass="form-control" disabled="true"
															value="#{barCodeForCSDAction.distillery_adrs}" rendered="false"></h:inputTextarea></TD>

												</TR>
												
												<tr height="5px"><td></td></tr>
												
										
												<TR>
													<TD><h5>
															<h:outputText value="*CSD Name"></h:outputText>
														</h5></TD>
													<TD>
															 
															 <h:selectOneMenu value="#{barCodeForCSDAction.csd_Id }" style="width: 50%;" onchange="this.form.submit();" 
															 valueChangeListener="#{barCodeForCSDAction.dist_detail}"  >
           									      <f:selectItems value="#{barCodeForCSDAction.csdList}"/>										
           										     </h:selectOneMenu>
           										     
           										    
           										     </TD>
           										     
           										   
														<TD width="150px"><h5>
															<h:outputText value="* Distillery Mobile No / Email."></h:outputText>
														</h5></TD>
													<TD><h:inputTextarea
															value="#{barCodeForCSDAction.mobile_No}" 
														disabled="true"  ></h:inputTextarea>
														
														</TD>


												</TR>
												
												<tr height="15px"><td></td></tr>
												
												<TR>
											
		
															<TD colspan="1" align="left"  ><h5>
															<h:outputText value="* Enter OTP"></h:outputText>
														</h5></TD>
													<TD colspan="2"><h:inputText
															value="#{barCodeForCSDAction.opt_first}" inputStyle="height:25px"
															 disabled="false"></h:inputText> 
															 
															 	<h:commandButton class="btn btn-primary"
														action="#{barCodeForCSDAction.verify}" value="Verify"/>
															 </TD>
															
														
												</TR>
											
											<tr height="20px"><td></td></tr>
												
												
											
												
											
											
											</TBODY>
										</TABLE>
										</h:panelGroup>
										
										<table width="100%">
									<tr>
										<td width="100%"><div class="panel-footer clearfix">
												<div align="center">
												
												<h:commandButton class="btn btn-success"  rendered="#{!barCodeForCSDAction.branddata_Flag}"
														action="#{barCodeForCSDAction.getotp}" value="Generate New OTP"/>

													<rich:spacer width="10px"></rich:spacer>
													
													<h:commandButton class="btn btn-default" rendered="#{!barCodeForCSDAction.branddata_Flag}"
														action="#{barCodeForCSDAction.reset}" value="Reset"/>
													

													
													
													
												</div>
											</div></td>

									</tr>
								</table>
										
										
										
										
										<table width="80%">
										<tbody>
												<TR>
											<TD>
											<a4j:outputPanel rendered="#{barCodeForCSDAction.branddata_Flag}">
											<rich:dataTable columnClasses="columnClass1"
													headerClass="TableHead" footerClass="TableHead"
													rowClasses="TableRow1,TableRow2" styleClass="DataTable"
													id="table3" rows="10" width="100%"
													value="#{barCodeForCSDAction.brandPackagingDataList}" var="list" >
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
														<h:commandButton action="#{barCodeForCSDAction.finalizeData}" value="Finalize Data"  styleClass="btn btn-sm btn-danger" disabled="#{list.finalizedFlag eq 'F'}">
														<f:setPropertyActionListener value="#{list}" target="#{barCodeForCSDAction.bopd }" />
														</h:commandButton>
														
														
														
															<h:outputLink target="_blank" value="/doc/ExciseUp/Excel/#{list.request_id}#{list.gtinno}#{list.finalizedDateString}.xls">
															<h:outputText value="Download Excel" rendered="#{list.finalizedFlag eq 'F'}"/>
															
															</h:outputLink> 
														
														
														
													</rich:column>

													<f:facet name="footer">
														<rich:datascroller for="table3"></rich:datascroller>
													</f:facet>
												</rich:dataTable>
												
												</a4j:outputPanel>
												</TD>
										</TR>
										<TR><TD align="center" >
												<h:commandButton class="btn btn-default" rendered="#{barCodeForCSDAction.branddata_Flag}"
														action="#{barCodeForCSDAction.reset}" value="Close"/>
												</TD>
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
						
						<TR>
							<TD>
								<TABLE width="100%">
									<TBODY>
										<TR>
											<TD><rich:dataTable columnClasses="columnClass1"
													headerClass="TableHead" footerClass="TableHead"
													rowClasses="TableRow1,TableRow2" styleClass="DataTable"
													id="table2" rows="10" width="100%"
													value="#{enteryOfPermitAction.showDataTableList}" var="list" rendered="false">
													 <rich:column>
														<f:facet name="header">
															<h:outputText value="Date">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_Date}"  styleClass="generalHeaderStyleOutput" >

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="CSD Name">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_Cds_Name}"  styleClass="generalHeaderStyleOutput" >

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Permit No." >
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_Permit_No}"  styleClass="generalHeaderStyleOutput" >

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Licence No." >
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_Licence_No}"  styleClass="generalHeaderStyleOutput" >
															 
														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Contact No." >
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.showDataTable_contact_NO}"  styleClass="generalHeaderStyleOutput" >
															 
														</h:outputText>
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
