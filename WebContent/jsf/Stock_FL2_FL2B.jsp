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
							<TD><h:inputHidden value="#{fL2_2B_StockAction.hidden}"></h:inputHidden>
								<h5>
									<h:outputText value="FL2 / 2B / CL2 STOCK RECEIVING "
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
												value="#{fL2_2B_StockAction.name}"
												style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>



									</tr>
									<tr>
										<TD><rich:spacer height="5px"></rich:spacer></TD>
									</tr>
									<TR>

										<TD align="center" colspan="2"><h:outputLabel
												value="#{fL2_2B_StockAction.address}"
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
									<TR align="center">
										<TD colspan="1" align="center">
											<TABLE width="80%" align="center"
												style="background-color: #D0D3D4; padding: 5;">
												<TBODY align="center">
													<tr>
														<td><rich:spacer height="10px;"></rich:spacer></td>
													</tr>
													<tr>
														<TD colspan="4" align="center"><h:selectOneRadio
																style="width:20%;" value="#{fL2_2B_StockAction.oldnew}">
																
																<f:selectItem itemValue="N" itemLabel="Stock 2019-20" />
															</h:selectOneRadio> <h:selectOneRadio style="width:40%;"
																value="#{fL2_2B_StockAction.licenseing}"
																valueChangeListener="#{fL2_2B_StockAction.radioListiner}"
																onchange="this.form.submit();">


																<f:selectItem itemValue="D" itemLabel="Distillery" />
																<f:selectItem itemValue="B" itemLabel="Brewery"
																	itemDisabled="#{fL2_2B_StockAction.loginType eq 'CL2'}" />
																<f:selectItem itemValue="BWFL" itemLabel="BWFL"
																	itemDisabled="#{fL2_2B_StockAction.loginType eq 'CL2'}" />
																<f:selectItem itemValue="FL2D" itemLabel="FL2D"
																	itemDisabled="#{fL2_2B_StockAction.loginType eq 'CL2'}" />
															</h:selectOneRadio></TD>
													</tr>
													<tr>
														<td><rich:spacer height="10px;"></rich:spacer></td>
													</tr>
													<tr>
														<TD colspan="2" align="center">GatePass No : <h:inputText
																value="#{fL2_2B_StockAction.gatePassNo}" />

														</TD>
														<TD colspan="2" align="center">Issue Gatepass Date :
															<rich:calendar value="#{fL2_2B_StockAction.createdDate}"
																valueChangeListener="#{fL2_2B_StockAction.dateListiner}"
																onchanged="this.form.submit()" />
														</TD>
													</tr>

													<tr>
														<TD><rich:spacer height="10px"></rich:spacer></TD>
													</tr>
													<TR></TR>



													<tr align="center">

														<TD align="center" colspan="2"><h:commandButton
																action="#{fL2_2B_StockAction.check}" value="Get Details"
																styleClass="btn btn-primary" /></TD>


													</tr>









													<tr>
														<td colspan="4"
															style="padding-top: 10px; padding-bottom: 10px;"></td>
													</tr>

												</TBODY>
											</TABLE>
										</TD>
									</TR>
									<tr>
										<td colspan="4" align="center">
											<table width="90%" align="center">

											</table>
										</td>
									</tr>
									<TR>
										<TD></TD>
									</TR>
									<TR>
										<TD></TD>
									</TR>
									<tr align="center">
										<td colspan="4"></td>
									</tr>
									<tr align="center">
										<td align="center">
											<table align="center" width="80%">

												<tr>
													<td></td>
												</tr>
											</table> <rich:spacer height="30px;"></rich:spacer>
											<table>
												<tr align="center">
													<td width="100%"></td>
												</tr>
												<tr align="center">
												</tr>
											</table>
										</td>
									</tr>
								</TABLE> <rich:dataTable columnClasses="columnClass1"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2" styleClass="DataTable"
									id="table3" width="100%"
									value="#{fL2_2B_StockAction.gatePssDisplaylist}" var="list">

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
										</f:facet>
										<center>
											<h:outputText styleClass="generalExciseStyle"
												value="#{list.slno}" />
										</center>
									</rich:column>
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
											<h:outputText value="Size"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.int_package_ml}">
										</h:outputText>
									</rich:column>









									<rich:column>
										<f:facet name="header">
											<h:outputText value="Batch No."
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.batchNo}" disabled="true">
										</h:inputText>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Receive Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.boxreciv}" disabled="true">

										</h:inputText>
									</rich:column>






									<rich:column>
										<f:facet name="header">
											<h:outputText value=" Bottles"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.int_bottle_reciv}" id="bot"
											disabled="true">
										</h:inputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Breakage"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.breakage}">
											<a4j:support reRender="recv_btl" event="onblur"></a4j:support>
										</h:inputText>
									</rich:column>


									<rich:column>
										<f:facet name="header">
											<h:outputText value="Total Receive Bottles"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<a4j:outputPanel id="recv_btl">
											<h:inputText value="#{list.recivTotalBottel}" disabled="true">
											</h:inputText>
										</a4j:outputPanel>
									</rich:column>



								</rich:dataTable>
						<tr align="right">
							<td>
								<table>

									<tr></tr>
								</table>
							</td>
						</tr>
						<tr>
							<td style="text-align: center;"><h:commandButton
									styleClass="btn btn-primary"
									action="#{fL2_2B_StockAction.saveMethod}" value="Save"
									onclick="if (! confirm('Confirm That Data Once Recieved Cannot be Changed') ) { return false;}; var e=this;setTimeout(function(){e.disabled=true;},0); return true; "></h:commandButton>
								<rich:spacer width="10px"></rich:spacer> <h:commandButton
									styleClass="btn btn-warning"
									action="#{fL2_2B_StockAction.reset}" value="Reset"></h:commandButton></td>
						</tr>


						</TD>
						</TR>

						<tr>
							<td><rich:dataTable align="center" id="table55" rows="10"
									width="100%" var="list11"
									value="#{fL2_2B_StockAction.show_Data_table}"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2">

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sr.No." />
										</f:facet>
										<center>
											<h:outputText value="#{list11.sno_data_table}" />
										</center>
									</rich:column>




									<rich:column filterBy="#{list11.gatepasss}">
										<f:facet name="header">
											<h:outputLabel value="Pass No." />
										</f:facet>
										<center>
											<h:outputText value="#{list11.gatepasss}">
											</h:outputText>
										</center>
									</rich:column>
									<rich:column sortBy="#{list11.creatDate_data_table}">
										<f:facet name="header">
											<h:outputLabel value="Date Created" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.creatDate_data_table}">
												<f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+5:30" />
											</h:outputText>
										</center>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Brand" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.vch_brand_data_table}" />
										</center>
									</rich:column>



									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Bottles" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.int_bottle_reciv_data_table}" />
										</center>
									</rich:column>



									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Breakage" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.breakage_data_table}" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Total Receive Bottles" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.recivTotalBottel_data_table}" />
										</center>
									</rich:column>



									<f:facet name="footer">
										<rich:datascroller for="table55" />
									</f:facet>
								</rich:dataTable></td>
						</tr>




					</TABLE>
				</div>
			</div>
		</h:form>
	</f:view>
</ui:composition>