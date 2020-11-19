<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<h:form>
			<table align="center">
				<tr><th><h3><h:messages errorStyle="color:red" layout="TABLE" id="messages" infoStyle="color:green"/></h3></th></tr>
				<tr align="center">
					<th><h2>
							<h:outputLabel
								style="COLOR:#000000;TEXT-DECORATION:underline;font-family:calibri;font-size:25px;"
								value="FL2D Brand Registration" />
						</h2></th>
				</tr>
				<tr><td><h:inputHidden value="#{fL2DBrandRegistrationAction.hidden}"/></td></tr>
			</table>
			<div class="panel panel-default">
				<div class="panel-body">
					<TABLE width="100%" align="center">
						<TR>
							<TD colspan="2" align="center">
								<TABLE width="60%">
									<TBODY>
										<tr>
											<TD><h:outputText style="font-weight:bold;" value="* Year " /></TD>
											<TD><h:selectOneMenu
													value="#{fL2DBrandRegistrationAction.vch_year}"
													styleClass="dropdown-menu">
													<f:selectItems value="#{fL2DBrandRegistrationAction.yearList}"/>
												</h:selectOneMenu></TD>
										</tr>
										<tr height="12px"></tr>
										<TR>
											<TD><h:outputLabel style="font-weight:bold;"
													value=" * license Category  : " /></TD>
											<TD colspan="3"><h:selectOneRadio style="width:100%;"
											onchange="this.form.submit();"
													value="#{fL2DBrandRegistrationAction.licenseing}">
													<f:selectItem itemLabel="IMPORTEDBEER" itemValue="IMPORTEDBEER" />
													<f:selectItem itemLabel="IMPORTEDFL" itemValue="IMPORTEDFL" />
													<f:selectItem itemLabel="IMPORTEDWINE" itemValue="IMPORTEDWINE" />
													<f:selectItem itemLabel="IMPORTEDL LAB" itemValue="LAB" />
												</h:selectOneRadio></TD>
										</TR>
										<tr height="10px"></tr>
										<TR>

											<TD><h:outputText style="font-weight:bold;" value="*  Brand Approval Under License Type : "/></TD>
											<TD><h:selectOneRadio style="width:80%;" onchange="this.form.submit();"
													value="#{fL2DBrandRegistrationAction.license_type}">													
													<f:selectItem itemLabel="FL2D" itemValue="FL2D" />
												</h:selectOneRadio></TD>
										</TR>
										<tr height="10px"></tr>
										<TR>
											<TD><h:outputText style="font-weight:bold;" value="* License Number  : "/></TD>
											<TD><h:selectOneMenu onchange="this.form.submit();"
											valueChangeListener="#{fL2DBrandRegistrationAction.change_lic_event}"
													value="#{fL2DBrandRegistrationAction.license_no}"
													styleClass="dropdown-menu">
													<f:selectItems value="#{fL2DBrandRegistrationAction.license}"/>
												</h:selectOneMenu></TD>
										</TR>										
										<tr height="10px"></tr>
										<tr>
											<td><h:outputLabel rendered="#{fL2DBrandRegistrationAction.lic_detail_flag}" style="font-weight:bold;" value="* Licensee Details : " /></td>
											<td><h:inputText rendered="#{fL2DBrandRegistrationAction.lic_detail_flag}" styleClass="form-control" value="#{fL2DBrandRegistrationAction.license_details}"/></td>
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
														value="#{fL2DBrandRegistrationAction.addRowBranding}"
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
																<h:outputText value="* Manufacturer Details"
																	styleClass="generalHeaderOutputTable" />
															</f:facet>
															<h:inputText value="#{list.manufacturerDetails}"
																styleClass="form-control" />
														</rich:column>

														<rich:column>
															<f:facet name="header">
																<h:outputText value="* Liquor Category"
																	styleClass="generalHeaderOutputTable" />
															</f:facet>
														<h:selectOneMenu value="#{list.liquorCategory}"
																rendered="#{fL2DBrandRegistrationAction.licenseing ne 'IMPORTEDBEER' and fL2DBrandRegistrationAction.licenseing ne 'IMPORTEDWINE' }"
																styleClass="dropdown-menu">
																<f:selectItems value="#{list.liq_cat_list }" />
															</h:selectOneMenu>  

															<h:selectOneMenu value="#{list.liquorCategory}"
																rendered="#{fL2DBrandRegistrationAction.licenseing eq 'IMPORTEDWINE'}"
																styleClass="dropdown-menu">
																<f:selectItem itemLabel="---Select---"
																	itemValue="0" />
																<f:selectItem itemLabel="WINE" itemValue="15" />
															</h:selectOneMenu>

															<h:selectOneMenu value="#{list.liquorCategory}"
																rendered="#{fL2DBrandRegistrationAction.licenseing eq 'CL'}"
																styleClass="dropdown-menu">
																<f:selectItem itemLabel="----Select----"
																	itemValue="0" />
																<f:selectItem itemLabel="25% Plain" itemValue="10" />
																<f:selectItem itemLabel="25% Spiced" itemValue="11" />
																<f:selectItem itemLabel="36%" itemValue="12" />
																<f:selectItem itemLabel="42.8%" itemValue="13" />
															</h:selectOneMenu>
															<h:selectOneMenu value="#{list.liquorCategory}"
																rendered="#{fL2DBrandRegistrationAction.licenseing eq 'IMPORTEDBEER'}"
																styleClass="dropdown-menu">
																<f:selectItem itemLabel="----Select----"
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
																	action="#{fL2DBrandRegistrationAction.addRowMethodBranding}"
																	image="/img/add.png" />
															</f:facet>
															<h:commandButton class="imag"
																actionListener="#{fL2DBrandRegistrationAction.deleteRowMethodBranding}"
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
																action="#{fL2DBrandRegistrationAction.saveMethod}"
																value="Save" />
															<rich:spacer width="10px">
															</rich:spacer>
															<h:commandButton styleClass="btn btn-default"
																action="#{fL2DBrandRegistrationAction.reset1}"
																value="Reset" />
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
									value="#{fL2DBrandRegistrationAction.brands_view}"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2">

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
												actionListener="#{fL2DBrandRegistrationAction.add_Packaging_Detail}"
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
				<div id="sh-addPopup"
					style="#{fL2DBrandRegistrationAction.shPopupStyle}">
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
																<h:outputText
																	rendered="#{fL2DBrandRegistrationAction.packaging_flag}"
																	value="* Packaging Details : "
																	style="FONT-SIZE:  large; FONT-WEIGHT: bold;">
																</h:outputText>
															</h4>
														</tr>
														<TR>
															<TD width="150px"><rich:dataTable
																	rendered="#{fL2DBrandRegistrationAction.packaging_flag}"
																	align="center" id="table4" rows="10" width="100%"
																	var="list22"
																	value="#{fL2DBrandRegistrationAction.addRowPackaging }"
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

																		<h:selectOneMenu value="#{list22.package_type}">
																			<f:selectItem itemLabel="---Select---" itemValue="" />
																			<f:selectItem itemLabel="CAN" itemValue="2" />
																			<f:selectItem itemLabel="Glass Bottle" itemValue="1" />
																			<f:selectItem itemLabel="Tetra Pack" itemValue="4" />
																			<f:selectItem itemLabel="Pet Bottle" itemValue="3" />

																		</h:selectOneMenu>

																	</rich:column>
																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="* Box Size"
																				styleClass="generalHeaderOutputTable" />
																		</f:facet>
																		<h:selectOneMenu  value="#{list22.boxsize_id}">
																			
																			<f:selectItems value="#{list22.boxsize_list}" />
																			
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
																			<h:outputText value="* Export Duty"
																				styleClass="generalHeaderOutputTable" />
																		</f:facet>
																		<h:inputText value="#{list22.duty}"
																			disabled="#{list22.disableFlag}"
																			styleClass="form-control">
																			<f:convertNumber maxFractionDigits="2"
																				pattern="#############0.00" />
																		</h:inputText>
																	</rich:column>
																	
																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="* Permit Fee"
																				styleClass="generalHeaderOutputTable" />
																		</f:facet>
																		<h:inputText value="#{list22.permit}"
																			disabled="#{list22.disableFlag}"
																			styleClass="form-control">
																			<f:convertNumber maxFractionDigits="2"
																				pattern="#############0.00" />
																		</h:inputText>
																	</rich:column>

																	<rich:column>
																		<f:facet name="header">
																			<h:commandButton class="imag"
																				action="#{fL2DBrandRegistrationAction.addRowMethodPackaging}"
																				image="/img/add.png" />
																		</f:facet>
																		<h:commandButton class="imag"
																			disabled="#{list22.disableFlag}"
																			actionListener="#{fL2DBrandRegistrationAction.deleteRowMethodPackaging}"
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
																			rendered="#{fL2DBrandRegistrationAction.packaging_flag}"
																			styleClass="btn btn-primary"
																			action="#{fL2DBrandRegistrationAction.saveMethod2}"
																			value="Save" />
																		<rich:spacer width="10px" />
																		<h:commandButton
																			rendered="#{fL2DBrandRegistrationAction.packaging_flag}"
																			styleClass="btn btn-default" onclick="off()"
																			action="#{fL2DBrandRegistrationAction.reset2}"
																			value="Close" />
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
				<div id="sh-overlay"
					style="#{fL2DBrandRegistrationAction.shOverlayStyle}"></div>

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