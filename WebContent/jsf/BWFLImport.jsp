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
													value="#{bWFLImportAction.hidden}"></h:inputHidden>
												<h2>
													<h:outputText value="  Permit Entry  "
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
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD colspan="1" align="center">
								<div class="panel panel-default">
									<div class="panel-body">
										<TABLE width="80%">
											<TBODY>


												<TR>
													<TD width="150px"><h5>
															<h:outputText value="* License Type"></h:outputText>
														</h5></TD>

													<TD><h:selectOneMenu
															disabled="#{bWFLImportAction.disabledFlag}"
															value="#{bWFLImportAction.licenceType }"
															styleClass="dropdown-menu" onchange="this.form.submit();"
															valueChangeListener="#{bWFLImportAction.getunit}">
															<f:selectItem itemLabel="------Select-------"
																itemValue="0" />
															<f:selectItem itemValue="1" itemLabel="BWFL2A" />
															<f:selectItem itemValue="2" itemLabel="BWFL2B" />
															<f:selectItem itemValue="3" itemLabel="BWFL2C" />
															<f:selectItem itemValue="4" itemLabel="BWFL2D" />
														</h:selectOneMenu></TD>

													<TD width="150px"><h5>
															<h:outputText value="Unit Name"></h:outputText>
														</h5></TD>
													<TD><h:selectOneMenu styleClass="dropdown-menu" style="width:250px;"
															disabled="#{bWFLImportAction.disabledFlag}"
															value="#{bWFLImportAction.distillery_id }"
															  onchange="this.form.submit();"
															valueChangeListener="#{bWFLImportAction.getaddrowdata}">
															<f:selectItems value="#{bWFLImportAction.unitlist}" />
														</h:selectOneMenu></TD>




												</TR>

												<tr height="10px">
													<td></td>
												</tr>
												<TR>
													


													<TD><h5>
															<h:outputText value="* License No."></h:outputText>

														</h5></TD>
													<TD colspan="2"><h:outputText 
															value="#{bWFLImportAction.licenceNum }"></h:outputText></TD>



												</TR>
												<!--  1-->

												<TR>
													<TD width="150px"><h5>
															<h:outputText value="* Permit Date"></h:outputText>
														</h5></TD>
													<TD><rich:calendar
															disabled="#{bWFLImportAction.disabledFlag}"
															value="#{bWFLImportAction.permitdt}"
															inputStyle="height:25px"></rich:calendar></TD>
												</TR>

												<!--  2-->
												<TR>


													<TD width="150px"><h5>
															<h:outputText value="* Type"></h:outputText>
														</h5></TD>

													<TD><h:selectOneRadio style="width:50%;"
															onchange="this.form.submit();"
															disabled="#{bWFLImportAction.disabledFlag}"
															value="#{bWFLImportAction.bottlngType}">
															<f:selectItem itemLabel="Mapped" itemValue="M" />
															<f:selectItem itemLabel="Unmapped" itemValue="U" />

														</h:selectOneRadio></TD>


													<TD width="150px"><h5>
															<h:outputText value="*Validity Date"></h:outputText>
														</h5></TD>

													<TD><rich:calendar
															value="#{bWFLImportAction.validityDate}"
															disabled="#{bWFLImportAction.bottlngType eq 'U' }">
														</rich:calendar></TD>





												</TR>

												<tr>
													<TD width="150px"><h5>
															<h:outputText value="*PermitNo"></h:outputText>
														</h5></TD>

													<TD><h:inputText
															disabled="#{bWFLImportAction.disabledFlag}"
															value="#{bWFLImportAction.permitNoEnterd}"></h:inputText>
													</TD>

												</tr>

											</TBODY>
										</TABLE>

										<table width="80%">
											<tbody>


												<TR>
													<TD><rich:dataTable columnClasses="columnClass1"
															headerClass="TableHead" footerClass="TableHead"
															rowClasses="TableRow1,TableRow2" styleClass="DataTable"
															id="table3" rows="10" width="100%"
															value="#{bWFLImportAction.brandPackagingDataList}"
															var="list">


															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Brand">
																	</h:outputText>
																</f:facet>
																<h:selectOneMenu disabled="#{bWFLImportAction.update}"
																	value="#{list.brandPackagingData_Brand}"
																	styleClass="dropdown-menu"
																	onchange="this.form.submit();">
																	<f:selectItems
																		value="#{list.brandPackagingData_BrandList}" />
																</h:selectOneMenu>

															</rich:column>
															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Capacity">
																	</h:outputText>
																</f:facet>
																<h:selectOneMenu  disabled="#{bWFLImportAction.update }"
																	value="#{list.brandPackagingData_Packaging}"
																	styleClass="dropdown-menu"
																	onchange="this.form.submit();">
																	<f:selectItems
																		value="#{list.brandPackagingData_PackagingList }" />
																	<a4j:support event="onchange" reRender="bb,box">
																	</a4j:support>
																</h:selectOneMenu>

															</rich:column>


															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Quantity">
																	</h:outputText>
																</f:facet>
																<h:outputText
																	value="#{list.brandPackagingData_Quantity}"
																	styleClass="generalHeaderStyleOutput">
																</h:outputText>

															</rich:column>

															<rich:column>
																<f:facet name="header">
																	<h:outputText value="ETIN No.">
																	</h:outputText>
																</f:facet>
																<h:outputText
																	value="#{list.brandPackagingData_etinNmbr}"
																	styleClass="generalHeaderStyleOutput">
																</h:outputText>

															</rich:column>

															<rich:column>
																<f:facet name="header">
																	<h:outputText value="No. Of Cases">
																	</h:outputText>
																</f:facet>

																<h:inputText
																	value="#{list.brandPackagingData_NoOfBoxes}"
																	styleClass="generalHeaderStyleOutput">

																	<a4j:support event="onblur"
																		reRender="box,totalpermitfee,fees">
																	</a4j:support>
																</h:inputText>



															</rich:column>






															
															<rich:column
																 
																id="bb">
																<f:facet name="header">
																	<h:outputText value="No. Of Bottle  Per Case">
																	</h:outputText>
																</f:facet>
																<h:inputText value="#{list.entry_no_of_bottle_per_case}" disabled="#{list.noOfBottleFlg}"
																	styleClass="generalHeaderStyleOutput">


																	<a4j:support event="onblur" reRender="box,fees,totalpermitfee">
																	</a4j:support>
																</h:inputText>

															</rich:column>

															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Planned No.Of Bottles ">
																	</h:outputText>
																</f:facet>
																<a4j:outputPanel id="box">
																	<h:inputText
																		value="#{list.brandPackagingData_PlanNoOfBottling}"
																		styleClass="generalHeaderStyleOutput" disabled="true">

																	</h:inputText>
																</a4j:outputPanel>

															</rich:column>

															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Permit Fee"
																		styleClass="generalHeaderOutputTable" />
																</f:facet>
																<h:outputText value="#{list.permitFee_dt}">
																</h:outputText>

															</rich:column>

															<rich:column>
																<f:facet name="header">
																	<h:outputText value="Calculated Permit Fee"
																		styleClass="generalHeaderOutputTable" />
																</f:facet>
																<h:outputText value="#{list.calPermitFee_dt}" id="fees">
																	<f:convertNumber maxFractionDigits="2"
																		pattern="#############0.00" />
																</h:outputText>

															</rich:column>

															<rich:column>
																<f:facet name="header">
																	<h:commandButton class="imag"
																		rendered="#{!bWFLImportAction.update}"
																		action="#{bWFLImportAction.addRowMethodPackaging}"
																		image="/img/add.png" />
																</f:facet>
																<h:commandButton class="imag"
																	rendered="#{!bWFLImportAction.update}"
																	actionListener="#{bWFLImportAction.deleteRowMethodPackaging}"
																	style="background: transparent;height:30px "
																	image="/img/del.png" />
															</rich:column>
															<f:facet name="footer">
																<rich:datascroller for="table3"></rich:datascroller>
															</f:facet>
														</rich:dataTable></TD>
												</TR>

											</tbody>
										</table>
										<div class="row" align="center">
											<rich:spacer height="10px"></rich:spacer>
										</div>

										<div class="row" align="right">
											<a4j:commandButton rendered="true" disabled="true"
												styleClass="btn btn-danger" id="totalpermitfee"
												actionListener="#{bWFLImportAction.calculateTotalFee}"
												reRender="totalpermitfee"
												value="Total Permit Fee : #{bWFLImportAction.db_totalFees}">
											</a4j:commandButton>
										</div>


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
						<tr align="center">
							<td colspan="4">
								<table width="100%">
									<tr>
										<td width="100%"><div class="panel-footer clearfix">
												<div align="center">

													<h:commandButton class="btn btn-primary"
														action="#{bWFLImportAction.save}" value="Save"
														onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;">
													</h:commandButton>

													<rich:spacer width="10px"></rich:spacer>
													<h:commandButton class="btn btn-default"
														action="#{bWFLImportAction.reset}" value="Reset"></h:commandButton>
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
							<td colspan="4" align="center"></td>
						</tr>
						<tr>
							<td width="100%"><div class="panel-footer clearfix">
									<div align="right"></div>
								</div></td>

						</tr>


						<rich:dataTable align="center" styleClass="table-responsive"
							width="100%" id="rentopiccat1" rows="10"
							value="#{bWFLImportAction.displayDataList}" var="list2"
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






							<rich:column>
								<f:facet name="header">
									<h:outputText value="License Type."
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.licenseType_dt}" />
								</center>
							</rich:column>


							<rich:column id="column3">
								<f:facet name="header">
									<h:outputText value="Unit Name" styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.unitName_dt}" />
								</center>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="License No."
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.licenseNmbr_dt}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Permit Date"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.permitDt_dt}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Permit Number"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.permitNmbr_dt}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Type" styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.mappedType_dt}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Dispatch Box/Bottles"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.dispbox_dt}" />
									/
									<h:outputText styleClass="generalExciseStyle"
										value="#{list2.dispbot_dt}" />
								</center>
							</rich:column>

							<rich:column>
							<f:facet name="header">
                           
                           </f:facet>
								<h:commandButton class="btn btn-primary"
									disabled="#{list2.updateDisabled}"
									actionListener="#{bWFLImportAction.updateData}" value="Update"
									onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;">
								</h:commandButton>
								<h:commandButton  class="btn btn-danger"
									disabled="#{list2.updateDisabled}"
									onclick="if (! confirm('Record will be deleted permanently.Do you wish to continue ?') ) { return false;}; return true; "
									actionListener="#{bWFLImportAction.deleteData}" value="Delete">
								</h:commandButton>
							</rich:column>

					 <rich:column>
                           <f:facet name="header">
                           <h:outputText value="CancelPermit" styleClass="generalHeaderStyle"/>
                           </f:facet>
                           <h:outputText value="#{list2.cancel_reason}" rendered="#{list2.cancel_flg eq 'C'  and  list2.updateDisabled  }"/>
                           <a4j:commandButton value="Cancel Permit" styleClass="btn btn-danger" disabled="#{list2.cancel_order_flg}" oncomplete="#{rich:component('popup')}.show();" rendered="#{list2.cancel_flg eq 'C'  and  list2.updateDisabled  }">
                           
                           <f:setPropertyActionListener target="#{bWFLImportAction.cancelData}" value="#{list2}" />
                           
                           </a4j:commandButton>
                           
                           
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
	
	<rich:modalPanel id="popup" width="700" height="300">
	
	
	<f:facet name="header">  
         <h:outputText value="Enter Detail Here"/>  
    </f:facet> 
	<h:form>
	
	<TABLE width="80%">
									<TBODY>
										<TR>
											<TD align="left"><h3>
											<a4j:outputPanel id="p1">
													<h:messages errorStyle="color:red" layout="table"
														id="messages1" infoStyle="color:green">
													</h:messages>
													</a4j:outputPanel>
												</h3></TD>
										</TR>
									</TBODY>
								</TABLE>
	<table align="center">
	<tr>
	<td>Cancellation Order No</td>
	<td><h:inputText value="#{bWFLImportAction.cancel_order_no}" id="id1"></h:inputText></td>
	
	
	<td>Order Date</td>
	<td><rich:calendar value="#{bWFLImportAction.cancel_order_date}" id="id2"></rich:calendar></td>
	
	
	</tr>
	<tr>
	
	<td>Authority Name</td>
	<td><h:inputText value="#{bWFLImportAction.authority_name}" id="id3"></h:inputText></td>
	
	<td>Upload Permit Cancel Order</td>
	<td><rich:fileUpload listHeight="35" listWidth="250" allowFlash="false" 
									ontyperejected="if (!confirm(' ONLY .pdf type file ALLOWED !!!')) return false"
									maxFilesQuantity="1" clearControlLabel="Clear"
									clearAllControlLabel="Clear All" sizeErrorLabel=""
									addControlLabel="Add Pdf" 
									fileUploadListener="#{bWFLImportAction.fileUpload}"
									
									
									>
									<a4j:support event="onfileuploadcomplete" reRender="messages1,renpdftrue1"></a4j:support>
									
									
									</rich:fileUpload>
									<a4j:outputPanel id="renpdftrue1">
									<h:outputLink
										rendered="#{bWFLImportAction.doc1upload}"
										target="_blank"
										value="/doc/ExciseUp/Bwfl/cancelpermit/#{bWFLImportAction.uploadedPdfName}">

										<h:graphicImage value="/images/download.gif" alt="view document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</h:outputLink>
								</a4j:outputPanel>
									
									
									</td>
	</tr>
	
	
	
	<tr>
	<td></td>
	<td align="center">
	<a4j:commandButton value="Cancel Order" action="#{bWFLImportAction.cancelOrder}" reRender="messages1,id1,id2,id3,p1" styleClass="btn btn-danger"></a4j:commandButton>
	<h:commandButton value="Close" action="#{bWFLImportAction.cancelOrderClose}" styleClass="btn btn-primary"></h:commandButton>
	</td>
	</tr>
	</table>
	</h:form>
	
	</rich:modalPanel>
</ui:composition>
