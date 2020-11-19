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

											<TD align="center" width="100%" class="pghead">
												<h2>
													<h:outputText value=" CSD Permit Validity Extenstion "
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
						value="#{cSD_Permit_validity_ExtAction.displayDataListExt}" var="list2"
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






						<rich:column >
							<f:facet name="header">
								<h:outputText value="License Name."
									styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list2.licenseType_dt}" />
							</center>
						</rich:column>


						
						<rich:column sortBy="#{list2.licenseNmbr_dt}">
							<f:facet name="header">
								<h:outputText value="License No."
									styleClass="generalHeaderStyle"  />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list2.licenseNmbr_dt}" />
							</center>
						</rich:column>
						
						<rich:column >
							<f:facet name="header">
								<h:outputText value="Permit Date"
									styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list2.permitDt_dt}" />
							</center>
						</rich:column>
						
						<rich:column >
							<f:facet name="header">
								<h:outputText value="Permit Number"
									styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list2.permitNmbr_dt}" />
							</center>
						</rich:column>
						
						<rich:column >
							<f:facet name="header">
								<h:outputText value="Valid Date"
									styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<rich:calendar value="#{list2.newvalidityDate}">
								</rich:calendar>
							</center>
						</rich:column>
						
						
					
						<rich:column>
							<h:commandButton class="btn btn-primary" 
							actionListener="#{cSD_Permit_validity_ExtAction.updateValidDate}" value="Update"
							onclick="submit">
							</h:commandButton>
							
					
						</rich:column>
						
						<rich:column>
						
							<h:commandButton class="btn btn-danger" rendered="#{list2.digital_sign_Flag eq false}"
							action="#{cSD_Permit_validity_ExtAction.rePrint}" value="Print And Digital Sign"
							onclick="submit">
							<f:setPropertyActionListener target="#{cSD_Permit_validity_ExtAction.csdImportDT}" value="#{list2}" />
							</h:commandButton>
							
							
							<h:outputLink
										value="https://www.upexciseonline.in/DigitalSignature/ImportPermitCSD.jsp">
											<f:param name="app_id" value="#{list2.digital_sign_appId_dt}"></f:param>
											<f:param name="bwfl_id" value="#{list2.digital_sign_fl2d_dt}"></f:param>
											<f:param name="login_type" value="#{list2.digital_sign_loginType_dt}"></f:param>
											<f:param name="permit_nbr" value="#{list2.digital_sign_permitNmbr_dt}"></f:param>
											<f:param name="domain" value="143"></f:param>
											<h:outputText value="Digital Sign"
												class="btn btn-sm btn-danger"
												
											rendered="#{list2.digital_sign_Flag}"></h:outputText>
									</h:outputLink>
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
