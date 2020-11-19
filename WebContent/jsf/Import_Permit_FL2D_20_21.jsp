
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">





	<f:view>
		<h:form>
			<h:inputHidden value="#{import_Permit_FL2D_20_21Action.hidden}"></h:inputHidden>
 


			<h:panelGroup>
				<div class="row" style="background-color: #8585ad;">
					<div class="col-md-2">
						<div class="row">
							<rich:spacer height="70px;"></rich:spacer>
						</div>
						<div class="row" align="center">

							<div class="row">
								<rich:spacer height="40px;"></rich:spacer>
							</div>
							<h:commandButton value="New Import Permit"
								action="#{import_Permit_FL2D_20_21Action.new_indent}"
								styleClass="form-control"
								style="color:#FFFFFF;  background-color: #8585ad; width: 100%;font-size: 1em">
							</h:commandButton>
						</div>
						 
						<div class="row" align="center">
							<h:commandButton value="Import Permit Status"
								action="#{import_Permit_FL2D_20_21Action.his_indent}"
								styleClass="form-control"
								style="color:#FFFFFF;  background-color: #8585ad; width: 100%;font-size: 1em">

							</h:commandButton>
						</div>
					</div>
					<div class="col-md-10" style="background-color: #ffffff;">
						<div class="row">
							<rich:spacer height="30px;"></rich:spacer>
						</div>

						<h:panelGroup rendered="#{import_Permit_FL2D_20_21Action.panel eq 'T'}">
							<div style="padding: 10px; border: 4px groove #2948a4;">
								<div class="row">
									<div class="col-md-12 wow shake" align="center"></div>



								</div>

								<div class="row">
									<rich:spacer height="15px;"></rich:spacer>
								</div>

								<div class="row" align="center">
									<h:outputText value="Import Permit"
										style="FONT-SIZE: xx-large; FONT-FAMILY: 'Agency FB'; COLOR: #253f8a; TEXT-DECORATION: underline;" />
								</div>

								<div class="row">
									<rich:spacer height="15px;"></rich:spacer>
								</div>
								<div class="row" align="center">
									<h:outputLabel value="#{import_Permit_FL2D_20_21Action.vch_firm_name}"
										style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel>
								</div>
								<div class="row" align="center">
									<h:outputLabel value="#{import_Permit_FL2D_20_21Action.vch_firm_add}"
										style="COLOR: #000000; FONT-SIZE: medium;"></h:outputLabel>
								</div>

								<div class="row">
									<rich:spacer height="10px;"></rich:spacer>
								</div>
								<div class="row" align="center">
									<div class="col-md-1 col-sm-12"></div>
									<div class="col-md-11 col-sm-12">
										<div class="row">
											<rich:spacer height="10px;"></rich:spacer>
										</div>
										<div class="row" style="FONT-SIZE: large;">

											<div class="col-md-6" align="left"
												style="margin-bottom: 5px;">
												<b style="COLOR: #000080;">Licence No.: </b>
												${import_Permit_FL2D_20_21Action.vch_license_number }
											</div>

											<div class="col-md-6" align="left"
												style="margin-bottom: 5px;">
												<b style="COLOR: #000080;">District: </b>${import_Permit_FL2D_20_21Action.vch_firm_district_name
												}
											</div>
											<div class="col-md-6" align="left"
												style="margin-bottom: 5px;">
												<b style="COLOR: #000080;">Current Date :</b>
												${import_Permit_FL2D_20_21Action.cr_date }
											</div>


										</div>
									</div>

								</div>
								<div class="row">
									<rich:spacer height="40px;"></rich:spacer>
								</div>



							</div>
							<!-- 123 -->
						</h:panelGroup>




						<h:panelGroup rendered="#{import_Permit_FL2D_20_21Action.panel eq 'N'}">
							<div style="padding: 10px; border: 4px groove #2948a4;">
								<div class="row">
									<div class="col-md-12 wow shake" align="center">
										<h:messages errorStyle="color:red" layout="TABLE"
											id="messages1" infoStyle="color:green"
											style="font-size:18px; background-color:#e1fcdf;">
										</h:messages>

									</div>



								</div>
								<div class="row">
									<rich:spacer height="10px;"></rich:spacer>
								</div>
								<div class="row" align="center">
									<h:outputLabel value="#{import_Permit_FL2D_20_21Action.vch_firm_name}"
										style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel>
								</div>
								<div class="row" align="center">
									<h:outputLabel value="#{import_Permit_FL2D_20_21Action.vch_firm_add}"
										style="COLOR: #000000; FONT-SIZE: medium;"></h:outputLabel>
								</div>

								<div class="row">
									<rich:spacer height="20px;"></rich:spacer>
								</div>
								<div class="row">

									<div class="col-md-6" align="left" style="margin-bottom: 5px;">
										<b style="COLOR: #000080;">Licence No.: </b>
										${import_Permit_FL2D_20_21Action.vch_license_number }
									</div>

									<div class="col-md-6" align="left" style="margin-bottom: 5px;">
										<b style="COLOR: #000080;">District: </b>${import_Permit_FL2D_20_21Action.vch_firm_district_name
										}
									</div>
									<div class="col-md-6" align="left" style="margin-bottom: 5px;">
										<b style="COLOR: #000080;">Current Date :</b>
										${import_Permit_FL2D_20_21Action.cr_date }
									</div>


								</div>
								<div class="row">
									<rich:spacer height="10px;"></rich:spacer>
								</div>

								<h:panelGroup rendered="true">

									<div class="row" align="center">
										<div class="col-md-12 col-sm-12" style="padding: 0px;">


											<div class="row" align="center">
												<div class="col-sm-3"></div>
												<div class="col-sm-3" align="right">Importing
													Unit::</div>
												<div class="col-sm-3">
													<h:selectOneMenu style="width: 300px;"
														class=" form-control"
														value="#{import_Permit_FL2D_20_21Action.importer_unit_id }"
														onchange="this.form.submit();"
														valueChangeListener="#{import_Permit_FL2D_20_21Action.importer_unit_lsnr}">
														<f:selectItems
															value="#{import_Permit_FL2D_20_21Action.importer_unit_list}" />
													</h:selectOneMenu>
												</div>
											</div>
											<div class="row" align="center">
												<div class="col-sm-3"></div>
												<div class="col-sm-3" align="right">Custom Bond::</div>
												<div class="col-sm-3">
													<h:selectOneMenu style="width: 300px;"
														class=" form-control"
														value="#{import_Permit_FL2D_20_21Action.custom_unit_id }"
														onchange="this.form.submit();"
														valueChangeListener="#{import_Permit_FL2D_20_21Action.custom_unit}">
														<f:selectItems
															value="#{import_Permit_FL2D_20_21Action.custom_unit_list}" />
													</h:selectOneMenu>
												</div>
											</div>
											
											<div class="row" align="left" style="COLOR: #ff0000;">
												<rich:spacer height="10px;"></rich:spacer>
											</div>
											<div class="row" align="center">
												<div class="col-sm-3"></div>
												<div class="col-sm-3" align="right">Name and Address
													of Unit from where Import is to be done:</div>

												<div class="col-sm-3">
												
												
													<h:inputTextarea class=" form-control"
													  
														value="#{import_Permit_FL2D_20_21Action.consignor_nm_adrs }">
														
													</h:inputTextarea>
												</div>

											</div>
											<div class="row">
												<rich:spacer height="30px;"></rich:spacer>
											</div>
											<div class="row">

									<div class="col-md-6" align="left" style="margin-bottom: 5px;">
										<b style="COLOR: #000080;">Total Advance Import Fee  :: </b>
										${import_Permit_FL2D_20_21Action.st_total_advance_import_fees }
									</div>

									<div class="col-md-6" align="left" style="margin-bottom: 5px;">
										 <h:outputText value="."></h:outputText>
									</div>
									<div class="col-md-6" align="left" style="margin-bottom: 5px;">
										<b style="COLOR: #000080;">Total Advance Special Fee ::</b>
										${import_Permit_FL2D_20_21Action.st_total_advance_special_fee }
									</div>


								</div>
											 
											<div class="row">
												<rich:spacer height="10px;"></rich:spacer>
											</div>
											
											<div class="row" align="left">
												<h4 style="COLOR: #0000ff;">
													<b><u><i>Permit Required For:</i></u></b>
												</h4>
											</div>

											<div style="overflow-x: scroll; width: 100%;">
												<rich:dataTable columnClasses="columnClass1"
													headerClass="TableHead" footerClass="TableHead"
													rowClasses="TableRow1,TableRow2" styleClass="DataTable"
													id="table3" width="100%"
													value="#{import_Permit_FL2D_20_21Action.displaylist}" var="list">


													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Brand">
															</h:outputText>
														</f:facet>
														<h:selectOneMenu value="#{list.brand_id}"
															style=" white-space: normal;width : 140px; height: 21px;"
															onchange="this.form.submit();">
															<f:selectItems value="#{list.brandList}" />
														</h:selectOneMenu>

													</rich:column>
													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Capacity"
																style=" white-space: normal;width : 70px;">
															</h:outputText>
														</f:facet>
														<h:selectOneMenu value="#{list.pckg_id}"
															style=" white-space: normal;width : 75px;height: 21px;"
															onchange="this.form.submit();">
															<f:selectItems value="#{list.packagingList}" />
															<a4j:support event="onchange" reRender="bb,box">
															</a4j:support>
														</h:selectOneMenu>

													</rich:column>


													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Quantity">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.quantity}"
															styleClass="generalHeaderStyleOutput">
														</h:outputText>

													</rich:column>
													
													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Current Stock">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.current_stock}"
															styleClass="generalHeaderStyleOutput">
														</h:outputText>

													</rich:column>

													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="ETIN No.">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.etin}"
															styleClass="generalHeaderStyleOutput">
														</h:outputText>

													</rich:column>

													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="No. Of Cases"
																style=" white-space: normal;width : 70px;">
															</h:outputText>
														</f:facet>

														<h:inputText value="#{list.no_of_cases}"
															style=" white-space: normal;width : 70px;"
															styleClass="generalHeaderStyleOutput">

															<a4j:support event="onblur"
																reRender="box,fees,total_importfees,total_addduty,save_btn,special_fee,advance_import_fees,advance_special_fee">
															</a4j:support>
														</h:inputText>
                                              </rich:column>







													<rich:column id="bb" align="center">
														<f:facet name="header">
															<h:outputText value="No. Of Bottle  Per Case"
																style=" white-space: normal;width : 55px;">
															</h:outputText>
														</f:facet>
														<h:inputText value="#{list.no_of_bottle_per_case}"
															style=" white-space: normal;width : 55px;"
															styleClass="generalHeaderStyleOutput" disabled="#{list.ren_flg}">
															<a4j:support event="onblur"
																reRender="box,fees,total_importfees,total_addduty,save_btn,special_fee">
															</a4j:support>
														</h:inputText>
                                                        
													</rich:column>

													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Planned No.Of Bottles "
																style=" white-space: normal;width : 100px;">
															</h:outputText>
														</f:facet>
														<a4j:outputPanel id="box">
															<h:outputText value="#{list.pland_no_of_bottles}"
																style=" white-space: normal;width : 100px;"
																styleClass="generalHeaderStyleOutput" >

															</h:outputText>
														</a4j:outputPanel>

													</rich:column>

													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Special Fee"
																styleClass="generalHeaderOutputTable" />
														</f:facet>
														<a4j:outputPanel id="special_fee">
															<h:outputText value="#{list.special_fee}"
																style=" white-space: normal;width : 70px;"
																styleClass="generalHeaderStyleOutput">
																<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" />
															</h:outputText>
														</a4j:outputPanel>
													</rich:column>



													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Import Fee and Duty"
																style=" white-space: normal;width : 70px;"
																styleClass="generalHeaderOutputTable" />


														</f:facet>
														<a4j:outputPanel id="fees">
															<h:outputText value="#{list.cal_importFee}"
																style=" white-space: normal;width : 70px;">
																<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" />
															</h:outputText>
														</a4j:outputPanel>

													</rich:column>
													
													





													<rich:column>
														<f:facet name="header">
															<h:commandButton class="imag"
																style=" white-space: normal;width : 30px;"
																action="#{import_Permit_FL2D_20_21Action.addRowMethodPackaging}"
																image="/img/add.png" />
														</f:facet>
														<h:commandButton class="imag"
															actionListener="#{import_Permit_FL2D_20_21Action.deleteRowMethodPackaging}"
															style="background: transparent;white-space: normal;width : 30px; "
															image="/img/del.png" />
													</rich:column>

												</rich:dataTable>

											</div>
											<div class="row" align="left" style="COLOR: #ff0000;">
												<rich:spacer height="20px;"></rich:spacer>
											</div>
											<div class="row" align="center">

												<a4j:commandButton rendered="true" disabled=""
													styleClass="btn " id="total_importfees"
													reRender="totalpermitfee"
													value="Total Import Fee and Duty : #{import_Permit_FL2D_20_21Action.total_import_fees}">

												</a4j:commandButton>

												<a4j:commandButton rendered="true" disabled=""
													styleClass="btn" id="total_addduty"
													reRender="totalpermitfee"
													value="Total Special Fee : #{import_Permit_FL2D_20_21Action.total_special_fee}">

												</a4j:commandButton>
												 

												 
												
												
											</div>
											<div class="row">
												<rich:spacer height="10px;"></rich:spacer>
											</div>


											<div class="row">
												<rich:spacer height="10px;"></rich:spacer>
											</div>
											<div class="row">
												<div class="col-md-6" align="right">
													<b>Route Detail:</b>
													<h:inputTextarea 
														value="#{import_Permit_FL2D_20_21Action.route_detail}"
														style="width : 240px; height : 42px;"></h:inputTextarea>
												</div>
												<div class="col-md-6" align="left">
													<h:selectOneRadio
														value="#{import_Permit_FL2D_20_21Action.route_road_radio}">
														<f:selectItem itemLabel="By Road" itemValue="road" />
														<f:selectItem itemLabel="By Train" itemValue="route" />

													</h:selectOneRadio>
												</div>


											</div>


											<div class="row">
												<rich:spacer height="10px;"></rich:spacer>
											</div>

											<div>
												<a4j:outputPanel id="save_btn">
													<h:commandButton value="Save"
														onclick="if (! confirm('No. of Cases-#{import_Permit_FL2D_20_21Action.total_no_of_cases}, Special Fee-#{import_Permit_FL2D_20_21Action.total_special_fee}, Import Fee and Duty-#{import_Permit_FL2D_20_21Action.total_import_fees}') ) { return false;}; return true; "
														action="#{import_Permit_FL2D_20_21Action.save}"
														style="color:#FFFFFF; background-color: #253f8a;">
													</h:commandButton>
												</a4j:outputPanel>
												<h:commandButton value="Reset"
													action="#{import_Permit_FL2D_20_21Action.reset}"
													style="color:#FFFFFF; background-color: #253f8a;">
												</h:commandButton>
												<h:commandButton value="Back"
													action="#{import_Permit_FL2D_20_21Action.home}"
													style="color:#FFFFFF; background-color: #253f8a;">
												</h:commandButton>

											</div>

										</div>

									</div>

								</h:panelGroup>
								<div>
									<rich:spacer height="20px;"></rich:spacer>
								</div>
							</div>
							<div>
								<rich:spacer height="20px;"></rich:spacer>
							</div>
						</h:panelGroup>


						<h:panelGroup rendered="#{import_Permit_FL2D_20_21Action.panel eq 'H'}">

							<div style="padding: 10px; border: 4px groove #2948a4;">

								<div class="row"></div>
								<div class="row">
									<rich:spacer height="10px;"></rich:spacer>
								</div>
								<div class="row">
									<div class="col-md-12 wow shake" align="center">
										<h:messages errorStyle="color:red" layout="TABLE" id="message"
											infoStyle="color:green"
											style="font-size:18px; background-color:#e1fcdf;">
										</h:messages>

									</div>



								</div>


								<div class="row" align="center">

									<div class="col-md-12 col-sm-12">



										<div class="row">
											<rich:spacer height="10px;"></rich:spacer>
										</div>


										<div class="row">
											<rich:spacer height="10px;"></rich:spacer>
										</div>

										<div class="row">
											<div class="col-sm-12">


												<div class="col-sm-2"></div>
											</div>
										</div>
										<div class="row">
											<rich:dataTable columnClasses="columnClass1"
												headerClass="TableHead" footerClass="TableHead"
												rowClasses="TableRow1,TableRow2" styleClass="DataTable"
												id="table4" width="100%" rows="5"
												value="#{import_Permit_FL2D_20_21Action.hislist}" var="list">

												<rich:column align="center">
													<f:facet name="header">
														<h:outputLabel value="Application No."
															styleClass="generalHeaderStyle" />
													</f:facet>
													<center>
														<h:outputText styleClass="generalExciseStyle"
															value="#{list.app_id}" />
													</center>
												</rich:column>
												<rich:column width="150px;">
													<f:facet name="header">
														<h:outputText style=" white-space: normal;width : 150px;"
															value="Name and Address of Unit from
													where Import is to be done"
															styleClass="generalHeaderOutputTable" />
													</f:facet>
													<h:outputText value="#{list.consignor_nm_adrs}">

													</h:outputText>
												</rich:column>

												<rich:column align="center">
													<f:facet name="header">
														<h:outputText value="Valid Upto"
															styleClass="generalHeaderOutputTable" />
													</f:facet>
													<h:outputText value="#{list.validUpto}">

													</h:outputText>
												</rich:column>
												<rich:column align="right">
													<f:facet name="header">
														<h:outputText value="Import Fee"
															styleClass="generalHeaderOutputTable" />
													</f:facet>
													<h:outputText value="#{list.dt_import_fee}">
														<f:convertNumber maxFractionDigits="2"
															pattern="#############0.00" />
													</h:outputText>
												</rich:column>




												<rich:column align="right">
													<f:facet name="header">
														<h:outputText value="Special Fee"
															styleClass="generalHeaderOutputTable" />
													</f:facet>
													<h:outputText value="#{list.dt_special_fee}">
														<f:convertNumber maxFractionDigits="2"
															pattern="#############0.00" />
													</h:outputText>
												</rich:column>
												
												
												 
												 
												
												










												<rich:column align="center">
													<f:facet name="header">
														<h:outputText value="Status"
															styleClass="generalHeaderOutputTable" />
													</f:facet>
													<h:outputText value="#{list.dt_vch_status}">

													</h:outputText>
												</rich:column>




												<rich:column>
													<f:facet name="header">
														<h:outputText value="View" />
													</f:facet>
													<center>
														<h:commandButton value="View Detail"
															styleClass="btn btn-primary btn-sm"
															actionListener="#{import_Permit_FL2D_20_21Action.view}">

														</h:commandButton>
														<h:commandButton value="Delete"
															rendered="#{list.dt_vch_status eq 'Pending' }"
															styleClass="btn btn-danger btn-sm"
															actionListener="#{import_Permit_FL2D_20_21Action.deletelisnr}">

														</h:commandButton>
														<h:outputLink styleClass="outputLinkEx"
															rendered="#{list.digital_sign_pdf_name ne 'NUL' }"
															value="/doc/ExciseUp/Bond/FL2Dpermit/#{list.digital_sign_pdf_name}"
															target="_blank">
															<h:outputText styleClass="outputText"
																value="Download Permit"
																rendered="#{list.digital_sign_pdf_name ne 'NUL' }"
																style="color: blue; font-family: serif; font-size: 10pt"></h:outputText>
														</h:outputLink>
														<rich:spacer width="10px"></rich:spacer>



													</center>
												</rich:column>










												<f:facet name="footer">
													<rich:datascroller for="table4" />
												</f:facet>
											</rich:dataTable>

										</div>


										<div class="row">
											<rich:spacer height="20px;"></rich:spacer>
										</div>
										
										

										<h:panelGroup rendered="#{import_Permit_FL2D_20_21Action.flag ne 'F'}">
										<h:outputText value="For Application No. : #{import_Permit_FL2D_20_21Action.app_id }" 
										style="FONT-WEIGHT: bold; FONT-FAMILY: 'Times New Roman'; FONT-STYLE: italic; COLOR: #1f8bdc; FONT-SIZE: x-large;"></h:outputText>
											<div class="row">


												<div style="overflow-x: scroll; width: 100%;">
													<rich:dataTable columnClasses="columnClass1"
														headerClass="TableHead" footerClass="TableHead"
														rowClasses="TableRow1,TableRow2" styleClass="DataTable"
														id="table5" width="100%"
														value="#{import_Permit_FL2D_20_21Action.brand_pack_list}"
														var="list10" style="FONT-FAMILY: 'Arial Black';">
														<rich:column width="150px;">
															<f:facet name="header">
																<h:outputText value="Brand">
																</h:outputText>
															</f:facet>
															<h:outputText value="#{list10.brand_name }"></h:outputText>
														</rich:column>
														<rich:column width="50px;" align="center">
															<f:facet name="header">
																<h:outputText value="Package Name"
																	style=" white-space: normal;width : 70px;">
																</h:outputText>
															</f:facet>
															<h:outputText styleClass="generalExciseStyle"
																value="#{list10.packageName }" />
														</rich:column>





														<rich:column align="center">
															<f:facet name="header">
																<h:outputText value="No. Of Cases"
																	style=" white-space: normal;width : 70px;">
																</h:outputText>
															</f:facet>

															<h:outputText styleClass="generalExciseStyle"
																value="#{list10.no_of_cases }" />



														</rich:column>







														<rich:column width="70px;" align="center">
															<f:facet name="header">
																<h:outputText value="No. Of Bottle  Per Case"
																	style=" white-space: normal;width : 50px;">
																</h:outputText>
															</f:facet>
															<h:outputText styleClass="generalExciseStyle"
																value="#{list10.no_of_bottle_per_case }" />

														</rich:column>

														<rich:column align="center">
															<f:facet name="header">
																<h:outputText value="Planned No.Of Bottles "
																	style=" white-space: normal;width : 80px;">
																</h:outputText>
															</f:facet>
															<h:outputText styleClass="generalExciseStyle"
																value="#{list10.pland_no_of_bottles }" />

														</rich:column>
														<rich:column align="right">
															<f:facet name="header">
																<h:outputText value="Special Fee"
																	styleClass="generalHeaderOutputTable" />
															</f:facet>
															<h:outputText styleClass="generalExciseStyle"
																value="#{list10.dt_special_fee }">
																<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" />
															</h:outputText>
														</rich:column>
														<rich:column align="right">
															<f:facet name="header">
																<h:outputText value="Import Fee"
																	style=" white-space: normal;width : 10px;"
																	styleClass="generalHeaderOutputTable" />
															</f:facet>
															<h:outputText value="#{list10.importFee}">
																<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" />
															</h:outputText>

														</rich:column>
														
														











													</rich:dataTable>

												</div>
											</div>
											<div class="row" align="center">
												<rich:spacer height="10px"></rich:spacer>
											</div>






											<div>
												<h:commandButton value="Close"
													action="#{import_Permit_FL2D_20_21Action.close}"
													styleClass="btn btn-default btn-sm">
													
													</h:commandButton>




											</div>
										</h:panelGroup>
										<div class="row" align="center">
											<h:commandButton value="Back"
												action="#{import_Permit_FL2D_20_21Action.home}"
												styleClass="btn btn-warning btn-sm">

											</h:commandButton>
										</div>











									</div>

								</div>

								<div class="row">
									<rich:spacer height="30px;"></rich:spacer>
								</div>

								<div class="row" align="center">
									<div class="col-md-12" align="center"></div>
								</div>
							</div>
						</h:panelGroup>
						<div class="row">
							<rich:spacer height="20px;"></rich:spacer>
						</div>

					</div>

				</div>


			</h:panelGroup>
		</h:form>
	</f:view>
</ui:composition>