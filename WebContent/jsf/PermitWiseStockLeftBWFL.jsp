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
													value="#{permitWiseStockLeftBWFLAction.hidden}"></h:inputHidden>
												<h2>
													<h:outputText value="  Permit Wise Stock Left (Against Manual Receiving From Distillery / Brewery)  "
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
													value="#{permitWiseStockLeftBWFLAction.distillery_nm}"
													style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>
										</tr>
										<tr>
											<TD><rich:spacer height="5px"></rich:spacer></TD>
										</tr>

										<TR>
											<TD align="center" colspan="2"><h:outputLabel
													value="#{permitWiseStockLeftBWFLAction.distillery_adrs}"
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
						
						<TR align="center">
							<TD><h:outputText value=" Permit No. :"/>
							
							<h:selectOneMenu
											value="#{permitWiseStockLeftBWFLAction.permit_Id}"
											style=" width: 250px; height: 25px;"
											valueChangeListener="#{permitWiseStockLeftBWFLAction.permitListnr}"
											onchange="this.form.submit();">
											<f:selectItems
												value="#{permitWiseStockLeftBWFLAction.permit_List}" />
										</h:selectOneMenu>
							</TD>
						</TR>
						
						<tr height="20px;">
						
						</tr>
						
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD><rich:dataTable columnClasses="columnClass1"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2" styleClass="DataTable"
									id="table200" rows="10" width="100%"
									value="#{permitWiseStockLeftBWFLAction.permitWiseBrandPackShow}" var="list">

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Sr No.">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list.per_Seq}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>




									<rich:column>
										<f:facet name="header">
											<h:outputText value="Permit No. ">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list.per_PermitNo}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Brand And Packing">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list.per_Brand_Pack_Name}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Size">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list.per_Size}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									
									
									
									
									
									
									
									
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Available Current Box">
											</h:outputText>
										</f:facet>
										<h:inputText value="#{list.per_box_Case}"
											styleClass="generalHeaderStyleOutput">
											<a4j:support event="onblur" reRender="lftbtl"></a4j:support>

										</h:inputText>
									</rich:column>
									
									
									
									
									

									<rich:column>
										<f:facet name="header">
											<h:outputText value=" Bottle Left">
											</h:outputText>
										</f:facet>
										<h:inputText id="lftbtl" value="#{list.per_Left_Qty}"
											styleClass="generalHeaderStyleOutput" readonly="true">

										</h:inputText>
									</rich:column>



									<f:facet name="footer">
										<rich:datascroller for="table200"></rich:datascroller>
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
														action="#{permitWiseStockLeftBWFLAction.savePermitData}"
														value="Save"
														onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;">
													</h:commandButton>

													<rich:spacer width="10px"></rich:spacer>
													<h:commandButton class="btn btn-default"
														action="#{permitWiseStockLeftBWFLAction.reset}" value="Reset"></h:commandButton>
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
									id="table222" rows="10" width="100%"
									value="#{permitWiseStockLeftBWFLAction.showData_PermitWiseSave}"
									var="list11">
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Sr No.">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.sav_Seq}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									
									
									<rich:column 
										>
										<f:facet name="header">
											<h:outputText value="Date">
											</h:outputText>
											
										</f:facet>
										<h:outputText value="#{list11.sav_Created_Date}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									
									
									<rich:column sortBy="#{list11.sav_etin_No}"
										>
										<f:facet name="header">
											<h:outputText value="Etin No">
											</h:outputText>
											
										</f:facet>
										<h:outputText value="#{list11.sav_etin_No}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									
									

									<rich:column>
										<f:facet name="header">
											<h:outputText value=" Permit No">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.sav_PermitNo}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>

									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Brand Name">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.sav_Brand_Pack_Name}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									
									
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Size">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.sav_Size}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
									
									
									
									
									
									
									
									
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Box">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.sav_box_Case}"
											styleClass="generalHeaderStyleOutput">
											

										</h:outputText>
									</rich:column>
									
									
									
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Bottle Left ">
											</h:outputText>
										</f:facet>
										<h:outputText value="#{list11.sav_Left_Qty}"
											styleClass="generalHeaderStyleOutput">

										</h:outputText>
									</rich:column>
									
										<rich:column >
										<f:facet name="header">
											<h:outputText value="Scan And Upload">
											</h:outputText>
										</f:facet>
										<h:commandButton styleClass="btn btn-success"
										value="Scan and Upload"  disabled="#{list11.sav_finlize_Flag eq 'F'}"
										actionListener="#{permitWiseStockLeftBWFLAction.scanAndUpload}">
									</h:commandButton>

										
										
									</rich:column>

									<f:facet name="footer">
										<rich:datascroller for="table222"></rich:datascroller>
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
					
					<div class="row">
						<rich:spacer height="10px"></rich:spacer>
					</div>
					
					
					
					<table align="center">
						<tr>
							<td colspan="6" style="color: Green;" align="left"><h:outputText
							rendered="#{permitWiseStockLeftBWFLAction.gatePassFlag}"
							 value="** Please read the instructions carefully before uploading."
									style="color: #ce0000;font-style: italic;font-size: large;text-decoration:blink;" /></td>
						</tr>
						<tr align="left">
							<td style="FONT-WEIGHT: bold; color: Green; width: 348px;"
								align="left"><h:outputText
								rendered="#{permitWiseStockLeftBWFLAction.gatePassFlag}"
									value="Upload csv for Permit Number(Same Permit No. should be entered in the first row of csv.):"
									style="FONT-SIZE: medium;" /></td>
							<td style="FONT-SIZE: large; color: Red"><h:outputText
							rendered="#{permitWiseStockLeftBWFLAction.gatePassFlag}"
									value="#{permitWiseStockLeftBWFLAction.scan_PermitNo}"
									style="COLOR: #0000ff;" /></td>
							<TD><h:outputLink value="/doc/ExciseUp/ExcelFormatBWFL.pdf"
									rendered="#{permitWiseStockLeftBWFLAction.gatePassFlag}" target="_blank">
									<h:graphicImage value="/img/i.png" style="width:40px;"></h:graphicImage>
								</h:outputLink></TD>

							<td><rich:fileUpload addControlLabel="Add File" id="link3"
									fileUploadListener="#{permitWiseStockLeftBWFLAction.uploadCsv}"
									rendered="#{permitWiseStockLeftBWFLAction.gatePassFlag}"
									maxFilesQuantity="1" listHeight="40" listWidth="250"
									clearControlLabel="clear" stopControlLabel="Stop"
									ontyperejected="if (!confirm(' ONLY .csv type file ALLOWED !!!')) return false"
									acceptedTypes="csv" clearAllControlLabel="Clear">
								</rich:fileUpload></td>

							<td><h:commandButton value="Upload"
									styleClass="btn btn-primary"
									rendered="#{permitWiseStockLeftBWFLAction.gatePassFlag}"
									action="#{permitWiseStockLeftBWFLAction.csvSubmit}">
								</h:commandButton></td>

						</tr>
					</table>
					
					<div class="row" >
					<rich:spacer height="20px;"/>
					</div>
					
					
					<div>
						<rich:dataTable align="center" id="table57" rows="10" width="100%"
							var="listk" value="#{permitWiseStockLeftBWFLAction.getVal}"
							rendered="#{permitWiseStockLeftBWFLAction.gatePassFlag}"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2">

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="Permit No." />
								</f:facet>
								<center>
									<h:outputText value="#{listk.gatepass}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="CaseCode" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.casecode}" />
								</center>
							</rich:column>

							<f:facet name="footer">
								<rich:datascroller for="table57" />
							</f:facet>
						</rich:dataTable>
					</div>
					
					
					<div class="row">
							<rich:spacer height="20px"></rich:spacer>
						</div>

						<table align="center">
							<tr>
								<td><h:commandButton value="Finalize"
										styleClass="btn btn-primary"
										rendered="#{permitWiseStockLeftBWFLAction.gatePassFlag}"
										action="#{permitWiseStockLeftBWFLAction.finalSubmit}">

									</h:commandButton> 
									<rich:spacer width="10px"></rich:spacer>
									
									<h:commandButton value="Cancel" styleClass="btn btn-danger"
										rendered="#{permitWiseStockLeftBWFLAction.gatePassFlag}"
										action="#{permitWiseStockLeftBWFLAction.delete}">
									</h:commandButton>
									
									<rich:spacer width="10px"></rich:spacer>
													<h:commandButton class="btn btn-default"
													rendered="#{permitWiseStockLeftBWFLAction.gatePassFlag}"
														action="#{permitWiseStockLeftBWFLAction.reset}" value="Reset"></h:commandButton>
												
									</td>

							</tr>
						</table>

					<div class="row">
							<rich:spacer height="20px"></rich:spacer>
						</div>
					
					
					
					
					
					
					
					
					
						
					
					
				</div>
			</div>
		</h:form>

	</f:view>
</ui:composition>
