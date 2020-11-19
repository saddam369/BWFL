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
						<TR align="center" style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
							<TD><h:inputHidden
									value="#{adjustmentGatepassAction.hidden}"></h:inputHidden>
								<h5>
									<h:outputText value="Adjustment Of Offline gatepass New"
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
												value="#{adjustmentGatepassAction.name}"
												style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>



									</tr>
									<tr>
										<TD><rich:spacer height="5px"></rich:spacer></TD>
									</tr>
									<TR>

										<TD align="center" colspan="2"><h:outputLabel
												value="#{adjustmentGatepassAction.address}"
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
								
									
									<TR>
										<TD></TD>
									</TR>
									
									
									
									
									<tr align="center">
										<td colspan="4"></td>
									</tr>
									
								</TABLE> 
								<rich:dataTable columnClasses="columnClass1"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2" styleClass="DataTable"
									id="table3" rows="10" width="100%"
									value="#{adjustmentGatepassAction.displaylist}"
									var="list">
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Brand"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.vch_brand}">
										</h:outputText>
									</rich:column>
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Permit"
												 />
										</f:facet>
										<h:outputText value="#{list.permit}">
										</h:outputText>
									</rich:column>
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="plan Date"
												 />
										</f:facet>
										<h:outputText value="#{list.plan_dt}">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Size"
												 />
										</f:facet>
										<h:outputText value="#{list.size}">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Portal Stock Bottle"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.int_bottle_avaliable}">
										</h:outputText>
									</rich:column>
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Portal Stock Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.boxAvailable}">
										</h:outputText>
									</rich:column>
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Actual Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.actboxe}">
										<a4j:support reRender="duty1" event="onblur"></a4j:support>
										</h:inputText>
									</rich:column>
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Actual Bottle"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.int_bottle_avaliableact}" id="duty1">
										</h:outputText>
									</rich:column>

									

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Breakage Bottle"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.breakage}">
										<a4j:support reRender="duty" event="onblur"></a4j:support>
										</h:inputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Balance Bottle"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.balance}" id="duty">
										</h:outputText>
									</rich:column>

									
									

									

									<rich:column>
										<f:facet name="header">
											<h:outputText value=""/>
										</f:facet>
										<h:commandButton value="Update"
												styleClass="btn btn-danger"
												actionListener="#{adjustmentGatepassAction.update}">
											</h:commandButton>
										
									</rich:column>

									<f:facet name="footer">
										<rich:datascroller for="table3"></rich:datascroller>
									</f:facet>
								</rich:dataTable>
						</TD>
						</TR>
						





<tr>
										<TD><rich:spacer height="20px"></rich:spacer></TD>
									</tr>

						
								
<tr>
							<td><rich:dataTable align="center" id="table55" rows="10"
									width="100%" var="list11"
									value="#{adjustmentGatepassAction.updatelist}"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2">

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sr.No." />
										</f:facet>
										<center>
											<h:outputText value="#{list11.sno}" />
										</center>
									</rich:column>




									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Brand Name" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.brandname }" />
										</center>
									</rich:column>
									
									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Size" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.upsize }" />
										</center>
									</rich:column>
									
									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Permit No" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.uppermit }" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Update Date" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.update }" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Update Box" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.upbox }" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Update Bottle" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.upbottle }" />
										</center>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Breakage Bottle" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.brbottle }" />
										</center>
									</rich:column>

									
	

									<f:facet name="footer">
										<rich:datascroller for="table55" />
									</f:facet>
								</rich:dataTable></td>
						</tr>
								
						


						
						
					</TABLE>
					
					
					<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>
					
					<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>

				</div>
			</div>
		</h:form>
	</f:view>
</ui:composition>