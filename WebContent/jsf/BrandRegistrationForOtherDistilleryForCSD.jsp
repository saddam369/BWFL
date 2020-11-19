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
								value="Brand Registration For Other Distillery For CSD" />
						</h2></th>
				</tr>
			</table>
			<br />
			<br />
			<br />
			<div class="panel panel-default">
				<div class="panel-body" style="background-color: whitesmoke;">
					<TABLE width="100%" align="center">
						<TR>
							<TD colspan="2" align="center">
								<TABLE width="50%" align="center"
									style="background-color: whitesmoke;">
									<TBODY>
										<tr height="20px"></tr>
										<tr height="20px"></tr>
										<tr>
											<TD align="right"><h:outputText
													style="font-weight:bold;" value=" * Year : " />
												<rich:spacer width="20px" /></TD>
											<TD><h:selectOneMenu
													value="#{brandRegistrationForOtherDistilleryForCSDAction.vch_year}"
													styleClass="dropdown-menu">
													<f:selectItems
														value="#{brandRegistrationForOtherDistilleryForCSDAction.yearList}" />
												</h:selectOneMenu></TD>
										</tr>
										<tr height="20px"></tr>
										<TR>
											<TD align="right"><h:outputLabel
													style="font-weight:bold;" value=" * license Category  : " />
												<rich:spacer width="20px" /></TD>
											<TD colspan="3"><h:selectOneRadio style="width:100%;"
													valueChangeListener="#{brandRegistrationForOtherDistilleryForCSDAction.license_change_event}"
													value="#{brandRegistrationForOtherDistilleryForCSDAction.licenseing}"
													onchange="this.form.submit();">
													<f:selectItem itemLabel="CL" itemValue="CL" />
													<f:selectItem itemLabel="IMFL" itemValue="IMFL" />
													<f:selectItem itemLabel="WINE" itemValue="WINE" />
													<f:selectItem itemLabel="BEER" itemValue="BEER" />
													<f:selectItem itemLabel="IMPORTEDFL" itemValue="IMPORTEDFL" />
													<f:selectItem itemLabel="LAB" itemValue="LAB" />
												</h:selectOneRadio></TD>
										</TR>
										<tr height="20px"></tr>
										<TR>
											<TD align="right"><h:outputText
													style="font-weight:bold;"
													value="* Brand Approval Under License Type : " />
												<rich:spacer width="20px" /></TD>
											<TD><h:selectOneRadio style="width:80%;"													
													value="#{brandRegistrationForOtherDistilleryForCSDAction.license_type}">
													<f:selectItem itemLabel="FL2A" itemValue="FL2A" />													
												</h:selectOneRadio></TD>
										</TR>
										<tr height="20px"></tr>
										<tr>
											<td align="right"><h:outputLabel
													style="font-weight:bold;" value=" * License Type : " />
												<rich:spacer width="20px" /></td>
											<td><h:selectOneMenu
													value="#{brandRegistrationForOtherDistilleryForCSDAction.lic_type_int_id}"
													styleClass="dropdown-menu">
													<f:selectItems
														value="#{brandRegistrationForOtherDistilleryForCSDAction.fl2_2b_2d_list}" />
												</h:selectOneMenu></td>
										</tr>
										<tr height="20px"></tr>
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
														value="#{brandRegistrationForOtherDistilleryForCSDAction.addRowBranding}"
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
															<h:selectOneMenu value="#{list.liquorCategory}"
																rendered="#{brandRegistrationForOtherDistilleryForCSDAction.licenseing ne 'CL' and brandRegistrationForOtherDistilleryForCSDAction.licenseing ne 'BEER' and brandRegistrationForOtherDistilleryForCSDAction.licenseing ne 'LAB'}"
																styleClass="dropdown-menu">
																<f:selectItems value="#{list.liq_cat_list }" />
															</h:selectOneMenu>
															<h:selectOneMenu value="#{list.liquorCategory}"
																rendered="#{brandRegistrationForOtherDistilleryForCSDAction.licenseing eq 'CL'}"
																styleClass="dropdown-menu">
																<f:selectItem itemLabel="---Select---" itemValue="0" />
																<f:selectItem itemLabel="25% Plain" itemValue="10" />
																<f:selectItem itemLabel="25% Spiced" itemValue="11" />
																<f:selectItem itemLabel="36%" itemValue="12" />
																<f:selectItem itemLabel="42.8%" itemValue="13" />
															</h:selectOneMenu>
															<h:selectOneMenu value="#{list.liquorCategory}"
																rendered="#{brandRegistrationForOtherDistilleryForCSDAction.licenseing eq 'BEER' or brandRegistrationForOtherDistilleryForCSDAction.licenseing eq 'LAB'}"
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
																	action="#{brandRegistrationForOtherDistilleryForCSDAction.addRowMethodBranding}"
																	image="/img/add.png" />
															</f:facet>
															<h:commandButton class="imag"
																actionListener="#{brandRegistrationForOtherDistilleryForCSDAction.deleteRowMethodBranding}"
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
																action="#{brandRegistrationForOtherDistilleryForCSDAction.saveMethod}"
																value="Save" />
															<rich:spacer width="10px">
															</rich:spacer>
															<h:commandButton styleClass="btn btn-default"
																action="#{brandRegistrationForOtherDistilleryForCSDAction.reset1}"
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
									value="#{brandRegistrationForOtherDistilleryForCSDAction.brands_view}"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2">

									<rich:column>
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
												actionListener="#{brandRegistrationForOtherDistilleryForCSDAction.add_Packaging_Detail}"
												styleClass="btn btn-primary btn-sm" />
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
					style="#{brandRegistrationForOtherDistilleryForCSDAction.shPopupStyle}">
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
																	rendered="#{brandRegistrationForOtherDistilleryForCSDAction.packaging_flag}"
																	value="* Packaging Details : "
																	style="FONT-SIZE:  large; FONT-WEIGHT: bold;" />
															</h4>
														</tr>
														<TR>
															<TD width="150px"><rich:dataTable
																	rendered="#{brandRegistrationForOtherDistilleryForCSDAction.packaging_flag}"
																	align="center" id="table4" rows="10" width="100%"
																	var="list22"
																	value="#{brandRegistrationForOtherDistilleryForCSDAction.addRowPackaging }"
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
																			styleClass="form-control">
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
																			styleClass="form-control">
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
																			styleClass="form-control">
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
																				action="#{brandRegistrationForOtherDistilleryForCSDAction.addRowMethodPackaging}"
																				image="/img/add.png" />
																		</f:facet>
																		<h:commandButton class="imag"
																			disabled="#{list22.disableFlag}"
																			actionListener="#{brandRegistrationForOtherDistilleryForCSDAction.deleteRowMethodPackaging}"
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
																			rendered="#{brandRegistrationForOtherDistilleryForCSDAction.packaging_flag}"
																			styleClass="btn btn-primary"
																			action="#{brandRegistrationForOtherDistilleryForCSDAction.saveMethod2}"
																			value="Save" />
																		<rich:spacer width="10px" />
																		<h:commandButton
																			rendered="#{brandRegistrationForOtherDistilleryForCSDAction.packaging_flag}"
																			styleClass="btn btn-default" onclick="off()"
																			action="#{brandRegistrationForOtherDistilleryForCSDAction.reset2}"
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
					style="#{brandRegistrationForOtherDistilleryForCSDAction.shOverlayStyle}"></div>

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