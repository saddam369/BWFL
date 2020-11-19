<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<h:form>
			<table align="center">
				<tr>
					<th><h3>
							<h:messages errorStyle="color:red" layout="TABLE" id="messages"
								infoStyle="color:green" />
						</h3></th>
				</tr>
				<tr align="center">
					<th><h2>
							<h:outputLabel
								style="COLOR: #000000; TEXT-DECORATION: underline;font-family: calibri;font-size:25px;"
								value="BWFL Brand Registration" />
						</h2></th>
				</tr>
				<tr>
					<td><h:inputHidden value="#{bWFLBrandRegistrationAction.hidden}" /></td>
				</tr>
			</table>
			<div class="panel panel-default">
				<div class="panel-body">
					<TABLE width="100%" align="center">
						<TR>
							<TD colspan="2" align="center">
								<TABLE width="60%">
									<TBODY>
										<tr>
											<TD><h:outputText style="font-weight:bold;"
													value="* Year " /></TD>
											<TD><h:selectOneMenu value="#{bWFLBrandRegistrationAction.vch_year}"
													styleClass="dropdown-menu">
													<f:selectItems value="#{bWFLBrandRegistrationAction.yearList}" />
												</h:selectOneMenu></TD>
										</tr>
										<tr height="12px"></tr>
										<TR>
											<TD><h:outputLabel style="font-weight:bold;"
													value=" *license Category  : " /></TD>
											<TD colspan="3"><h:selectOneRadio style="width:100%;"
													valueChangeListener="#{bWFLBrandRegistrationAction.license_change_event}"
													value="#{bWFLBrandRegistrationAction.licenseing}"
													onchange="this.form.submit();">
													<f:selectItem itemLabel="CL" itemValue="CL" itemDisabled="#{bWFLBrandRegistrationAction.brewery_flag}"/>
													<f:selectItem itemLabel="IMFL" itemValue="IMFL" />
													<f:selectItem itemLabel="WINE" itemValue="WINE" />
													<f:selectItem itemLabel="BEER" itemValue="BEER" />
													<f:selectItem itemLabel="LAB" itemValue="LAB" />
												</h:selectOneRadio></TD>
										</TR>
										<tr height="10px"></tr>
										<TR>
											<TD><h:outputText style="font-weight:bold;"
													rendered="#{bWFLBrandRegistrationAction.type_flag}"
													value="*Brand Approval Under License Type : " /></TD>
											<TD>
											
											<h:selectOneRadio style="width:80%;"
													rendered="#{bWFLBrandRegistrationAction.imfl_beer}"
													value="#{bWFLBrandRegistrationAction.license_type}"
													onchange="this.form.submit();">
													<f:selectItem itemLabel="FL3" itemValue="FL3" itemDisabled="#{bWFLBrandRegistrationAction.bwfl_flag_bwfl_case}"/>
													<f:selectItem itemLabel="FL3A" itemValue="FL3A" itemDisabled="#{bWFLBrandRegistrationAction.bwfl_flag_bwfl_case}"/>
													<f:selectItem itemLabel="BWFL2B" itemValue="BWFL2B" itemDisabled="#{bWFLBrandRegistrationAction.bwfl_flag}"/>
												</h:selectOneRadio>
												<h:selectOneRadio style="width:80%;"
													rendered="#{bWFLBrandRegistrationAction.imfl_flag}"
													value="#{bWFLBrandRegistrationAction.license_type}"
													onchange="this.form.submit();">
													<f:selectItem itemLabel="FL3" itemValue="FL3" itemDisabled="#{bWFLBrandRegistrationAction.bwfl_flag_bwfl_case}"/>
													<f:selectItem itemLabel="FL3A" itemValue="FL3A" itemDisabled="#{bWFLBrandRegistrationAction.bwfl_flag_bwfl_case}"/>
													<f:selectItem itemLabel="BWFL2A" itemValue="BWFL2A" itemDisabled="#{bWFLBrandRegistrationAction.bwfl_flag}"/>
												</h:selectOneRadio>
												<h:selectOneRadio style="width:55%;"
													rendered="#{bWFLBrandRegistrationAction.wine_importedfl}"
													value="#{bWFLBrandRegistrationAction.license_type}"
													onchange="this.form.submit();">
													<f:selectItem itemLabel="FL2D" itemValue="FL2D" itemDisabled="#{bWFLBrandRegistrationAction.bwfl_flag_bwfl_case or bWFLBrandRegistrationAction.licenseing eq 'LAB'}"/>
													<f:selectItem itemLabel="BWFL2C" itemValue="BWFL2C" itemDisabled="#{bWFLBrandRegistrationAction.bwfl_flag or bWFLBrandRegistrationAction.licenseing eq 'LAB'}"/>
													<f:selectItem itemLabel="BWFL2D" itemValue="BWFL2D" itemDisabled="#{bWFLBrandRegistrationAction.bwfl_flag or bWFLBrandRegistrationAction.licenseing ne 'LAB'}"/>
												</h:selectOneRadio></TD>
										</TR>
										<tr height="10px"></tr>
										<TR>
											<TD><h:outputText style="font-weight:bold;"
													value="* License Number  : "
													rendered="#{bWFLBrandRegistrationAction.cl_flag}" /></TD>
											<TD><h:selectOneMenu rendered="#{bWFLBrandRegistrationAction.cl_flag}"
													onchange="this.form.submit();"
													value="#{bWFLBrandRegistrationAction.license_no}"
													styleClass="dropdown-menu">
													<f:selectItems value="#{bWFLBrandRegistrationAction.license_list}" />
												</h:selectOneMenu></TD>
										</TR>
										<tr height="10px"></tr>
										<TR>
											<TD><h:outputText style="font-weight:bold;"
													value="* Distillery  : " rendered="#{bWFLBrandRegistrationAction.cl_flag2}" /></TD>
											<TD colspan="2"><h:selectOneMenu
													onchange="this.form.submit();"
													style="width:60%;padding:5px;"
													rendered="#{bWFLBrandRegistrationAction.cl_flag2}"
													value="#{bWFLBrandRegistrationAction.dis_license_no}">
													<f:selectItems value="#{bWFLBrandRegistrationAction.getDisName}" />
												</h:selectOneMenu></TD>
										</TR>
										<tr height="10px"></tr>

										<tr>
											<td><h:outputLabel style="font-weight:bold;"
													rendered="#{bWFLBrandRegistrationAction.cl_flag}"
													value="* Licensee Details : " /></td>
											<td><h:inputText styleClass="form-control"
													value="#{bWFLBrandRegistrationAction.license_details}"
													rendered="#{bWFLBrandRegistrationAction.cl_flag}" /></td>
										</tr>
										<tr height="20px"></tr>
									</TBODY>
								</TABLE>

							</TD>
						</TR>
						<tr>
							<td align="center" width="100%">
								<div class="panel panel-default">
									<div class="panel-body">
										<table width="90%" align="center">
											<tr bgcolor="#ffffff">
												<h4>
													<h:outputText value="* Add Brand Details : "
														style="FONT-SIZE: large; FONT-WEIGHT: bold;" />
												</h4>
											</tr>
											<TR>
												<TD width="150px"><rich:dataTable align="center"
														id="table5" rows="10" width="100%" var="list"
														value="#{bWFLBrandRegistrationAction.addRowBranding}"
														headerClass="TableHead" footerClass="TableHead"
														rowClasses="TableRow1,TableRow2">

														<rich:column>
															<f:facet name="header">
																<h:outputText value="* Sr.No. "
																	styleClass="generalHeaderOutputTable" />
															</f:facet>
															<h:inputText disabled="true" value="#{list.sno}"
																styleClass="form-control" />
														</rich:column>

														<rich:column>
															<f:facet name="header">
																<h:outputText value="* Brand Name "
																	styleClass="generalHeaderOutputTable" />
															</f:facet>
															<h:inputText value="#{list.brandName}"
																styleClass="form-control" />
														</rich:column>



														<rich:column>
															<f:facet name="header">
																<h:outputText value="* Liquor Category"
																	styleClass="generalHeaderOutputTable" />
															</f:facet>
															<h:selectOneMenu value="#{list.liquorCategory}" rendered="#{bWFLBrandRegistrationAction.licenseing ne 'CL' and bWFLBrandRegistrationAction.licenseing ne 'BEER' and bWFLBrandRegistrationAction.licenseing ne 'WINE'  and bWFLBrandRegistrationAction.licenseing ne 'LAB'}"
																styleClass="dropdown-menu">
																<f:selectItems value="#{list.liq_cat_list }" />
															</h:selectOneMenu>
															
															<h:selectOneMenu value="#{list.liquorCategory}" rendered="#{bWFLBrandRegistrationAction.licenseing eq 'WINE'}"
																styleClass="dropdown-menu">
																<f:selectItem itemLabel="------Select-------"
																				itemValue="0" />
																			<f:selectItem itemLabel="WINE" itemValue="16" />
															</h:selectOneMenu>
															
															<h:selectOneMenu value="#{list.liquorCategory}" rendered="#{bWFLBrandRegistrationAction.licenseing eq 'CL'}"
																styleClass="dropdown-menu">
																<f:selectItem itemLabel="------Select-------"
																				itemValue="0" />
																			<f:selectItem itemLabel="25% Plain" itemValue="10" />
																				<f:selectItem itemLabel="25% Spiced" itemValue="11" />
																			<f:selectItem itemLabel="36%" itemValue="12" />
																				<f:selectItem itemLabel="42.8%" itemValue="13" />
															</h:selectOneMenu>
															<h:selectOneMenu value="#{list.liquorCategory}" rendered="#{bWFLBrandRegistrationAction.licenseing eq 'BEER' or bWFLBrandRegistrationAction.licenseing eq 'LAB'}"
																styleClass="dropdown-menu">
																<f:selectItem itemLabel="------Select-------"
																				itemValue="0" />
																			<f:selectItem itemLabel="Mild" itemValue="8" />
																			<f:selectItem itemLabel="Strong" itemValue="9" />
																				 
															</h:selectOneMenu>
														</rich:column>

														<rich:column>
															<f:facet name="header">
																<h:outputText value="* Strength "
																	styleClass="generalHeaderOutputTable" />
															</f:facet>
															<h:inputText value="#{list.strength_addrow}"
																styleClass="form-control" />
																
														</rich:column>

														<rich:column>
															<f:facet name="header">
																<h:commandButton class="imag"
																	action="#{bWFLBrandRegistrationAction.addRowMethodBranding}"
																	image="/img/add.png" />
															</f:facet>
															<h:commandButton class="imag"
																actionListener="#{bWFLBrandRegistrationAction.deleteRowMethodBranding}"
																style="background: transparent;height:30px "
																image="/img/del.png" />
														</rich:column>
														<f:facet name="footer">
															<rich:datascroller for="table4" />
														</f:facet>
													</rich:dataTable></TD>
											</TR>
											<tr>
												<td width="100%" colspan="2">
													<div class="panel-footer clearfix">
														<div align="center">
															<h:commandButton styleClass="btn btn-primary"
																action="#{bWFLBrandRegistrationAction.saveMethod}" value="Save" />
															<rich:spacer width="10px">
															</rich:spacer>
															<h:commandButton styleClass="btn btn-default"
																action="#{bWFLBrandRegistrationAction.reset1}" value="Reset" />
														</div>
													</div>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td align="center"><rich:dataTable align="center"
									id="table55" rows="10" width="100%" var="list11"
									value="#{bWFLBrandRegistrationAction.brands_view}" headerClass="TableHead"
									footerClass="TableHead" rowClasses="TableRow1,TableRow2">

									<rich:column filterBy="#{list11.srno}" sortBy="#{list11.srno}">
										<f:facet name="header">
											<h:outputLabel value="Sr.No." />
										</f:facet>
										<center>
											<h:outputText value="#{list11.srno}" />
										</center>
									</rich:column>

									<rich:column filterBy="#{list11.brand_name}"
										sortBy="#{list11.brand_name}">
										<f:facet name="header">
											<h:outputLabel value="Brand Name" />
										</f:facet>
										<h:outputText value="#{list11.brand_name}" />
									</rich:column>









									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Liquor Category" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.liqCatDisplay}" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Strength" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.strength}" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="License Type" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.vch_license_type}" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="License Number" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.license_number}" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Select" />
										</f:facet>
										<center>
											<a4j:commandLink reRender="shPopupA4j"
												value="Add Packaging Detail"
												actionListener="#{bWFLBrandRegistrationAction.add_Packaging_Detail}"
												styleClass="btn btn-primary btn-sm">


											</a4j:commandLink>

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
		<h:form>




			<a4j:outputPanel id="shPopupA4j">
				<div id="sh-addPopup" style="#{bWFLBrandRegistrationAction.shPopupStyle}">
					<div class="row">

						<div class="col-md-12">
							<div
								style="animation-name: sh-entry; animation-duration: 0.3s; background-color: #f2f2f2; border-radius: 15px; padding: 25px;">
								<h:graphicImage onclick="off()" value="/img/cross.png"
									width="15px" height="15px"
									style="position: absolute; right: 25px; top: 10px; cursor: pointer;">
								</h:graphicImage>
								<TABLE width="100%" align="center">
									<tr>
										<td align="center" width="100%">
											<div class="panel panel-default">
												<div class="panel-body">
													<table width="90%" align="center">
														<tr bgcolor="#ffffff">
															<h4>
																<h:outputText rendered="#{bWFLBrandRegistrationAction.packaging_flag}"
																	value="* Packaging Details : "
																	style="FONT-SIZE:  large; FONT-WEIGHT: bold;">
																</h:outputText>
															</h4>
														</tr>
														<TR>
															<TD width="150px"><rich:dataTable
																	rendered="#{bWFLBrandRegistrationAction.packaging_flag}" align="center"
																	id="table4" rows="10" width="100%" var="list22"
																	value="#{bWFLBrandRegistrationAction.addRowPackaging }"
																	headerClass="TableHead" footerClass="TableHead"
																	rowClasses="TableRow1,TableRow2">
																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="* Sr.No. "
																				styleClass="generalHeaderOutputTable" />
																		</f:facet>
																		<h:inputText disabled="true" value="#{list22.sno}"
																			styleClass="form-control" />
																	</rich:column>
																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="* Package Name "
																				styleClass="generalHeaderOutputTable" />
																		</f:facet>
																		<h:inputText value="#{list22.package_name}"
																			disabled="#{list22.disableFlag}"
																			styleClass="form-control" />
																	</rich:column>

																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="* Quantity (ML)"
																				styleClass="generalHeaderOutputTable" />
																		</f:facet>
																		<h:selectOneMenu disabled="#{list22.disableFlag}"
																			value="#{list22.quantity}">

																			<f:selectItems value="#{list22.quantityList}" />
																			<a4j:support event="onkeyup" reRender="qt" />

																		</h:selectOneMenu>
																	</rich:column>


																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="* Package Type"
																				styleClass="generalHeaderOutputTable" />
																		</f:facet>

																		<h:selectOneMenu 
																 
																 
																value="#{list22.package_type}">
																<f:selectItem itemLabel="------Select-------"
																	itemValue="" />
																 
																<f:selectItem itemLabel="CAN" itemValue="2" />
															 
																<f:selectItem itemLabel="Glass Bottle"
																	itemValue="1" />
																<f:selectItem itemLabel="Tetra Pack"
																	itemValue="4" />
															 
																<f:selectItem itemLabel="Pet Bottle"
																	itemValue="3" />
															 
															</h:selectOneMenu>

																	</rich:column>
																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="* MRP (INR))"
																				styleClass="generalHeaderOutputTable" />
																		</f:facet>
																		<h:inputText value="#{list22.mrp1}"
																			disabled="#{list22.disableFlag}"
																			styleClass="form-control">
																			<f:convertNumber maxFractionDigits="2"
																				pattern="#############0.00" />
																		</h:inputText>
																	</rich:column>
																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="* EDP"
																				styleClass="generalHeaderOutputTable" />
																		</f:facet>
																		<h:inputText value="#{list22.edp1}"
																			disabled="#{list22.disableFlag}"
																			styleClass="form-control" >
																			<f:convertNumber maxFractionDigits="2"
																				pattern="#############0.00" />
																			</h:inputText>
																	</rich:column>
																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="* Duty"
																				styleClass="generalHeaderOutputTable" />
																		</f:facet>
																		<h:inputText value="#{list22.duty1}"
																			disabled="#{list22.disableFlag}"
																			styleClass="form-control">
																			<f:convertNumber maxFractionDigits="2"
																				pattern="#############0.00" />
																			</h:inputText>
																	</rich:column>
																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="* Add Duty"
																				styleClass="generalHeaderOutputTable" />
																		</f:facet>
																		<h:inputText value="#{list22.addDuty1}"
																			disabled="#{list22.disableFlag}"
																			styleClass="form-control" >
																			<f:convertNumber maxFractionDigits="2"
																				pattern="#############0.00" />
																			
																			</h:inputText>
																	</rich:column>
																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="* WS Margin"
																				styleClass="generalHeaderOutputTable" />
																		</f:facet>
																		<h:inputText value="#{list22.wsMargin1}"
																			disabled="#{list22.disableFlag}"
																			styleClass="form-control" >
																			<f:convertNumber maxFractionDigits="2"
																				pattern="#############0.00" />
																			</h:inputText>
																	</rich:column>
																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="* Ret Margin"
																				styleClass="generalHeaderOutputTable" />
																		</f:facet>
																		<h:inputText value="#{list22.retMargin1}"
																			disabled="#{list22.disableFlag}"
																			styleClass="form-control">
																			<f:convertNumber maxFractionDigits="2"
																				pattern="#############0.00" />
																			</h:inputText>
																	</rich:column>

																	<rich:column>
																		<f:facet name="header">
																			<h:commandButton class="imag"
																				action="#{bWFLBrandRegistrationAction.addRowMethodPackaging}"
																				image="/img/add.png" />
																		</f:facet>
																		<h:commandButton class="imag"
																			disabled="#{list22.disableFlag}"
																			actionListener="#{bWFLBrandRegistrationAction.deleteRowMethodPackaging}"
																			style="background: transparent;height:30px "
																			image="/img/del.png" />
																	</rich:column>

																	<f:facet name="footer">
																		<rich:datascroller for="table4" />
																	</f:facet>
																</rich:dataTable></TD>
														</TR>
														<tr>
															<td width="100%" colspan="2">
																<div class="panel-footer clearfix">
																	<div align="center">
																		<h:commandButton
																			rendered="#{bWFLBrandRegistrationAction.packaging_flag}"
																			styleClass="btn btn-primary"
																			action="#{bWFLBrandRegistrationAction.saveMethod2}" value="Save" />
																		<rich:spacer width="10px" />
																		<h:commandButton
																			rendered="#{bWFLBrandRegistrationAction.packaging_flag}"
																			styleClass="btn btn-default" onclick="off()"
																			action="#{bWFLBrandRegistrationAction.reset2}" value="Close" />
																	</div>
																</div>
															</td>
														</tr>
													</table>
												</div>
											</div>
										</td>
									</tr>
								</TABLE>
							</div>
						</div>
					</div>
				</div>
				<div id="sh-overlay" style="#{bWFLBrandRegistrationAction.shOverlayStyle}"></div>

			</a4j:outputPanel>

		</h:form>
	</f:view>

	<script>
		function off() {
			document.getElementById("sh-overlay").style.display = "none";
			document.getElementById("sh-addPopup").style.display = "none";
		}
	</script>
</ui:composition>