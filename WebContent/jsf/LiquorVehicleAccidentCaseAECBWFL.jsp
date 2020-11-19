 <ui:composition
       xmlns="http://www.w3.org/1999/xhtml"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:a4j="http://richfaces.org/a4j"
	  xmlns:rich="http://richfaces.org/rich">

   
   <f:view>
   <h:form>
   <style>
    .table1 TD{ width:200px;}
    
    .dropdown-menu {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
	
}
    
    
    textArea{ border-radius: 3px;
	border-style: none;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
	height:30px;
     }
     .main{ margin: 20px;
     background-color:#f2f2f2;
     padding:20px;
     border-radius:10px;
     shadow: 5px #888888;
     box-shadow: 5px 4px 10px grey;}
   
   </style>
   
   
   
     <div class="row">
       <rich:spacer height="30"></rich:spacer>
       
     </div>
     
      <h:messages errorStyle="color:red" layout="table"
														id="messages" infoStyle="color:green">
													</h:messages>
       <rich:spacer  height="30"></rich:spacer>
       <h:inputHidden value="#{liquorVehicleAccidentCaseAECBWFLAction.hidden}" />
                            <div align="center"><h:outputText
										value="Liquor Vehicle Accidental Case Verification BWFL"
										
										style="TEXT-DECORATION: underline;FONT-FAMILY: Copperplate Gothic Light;COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: xx-large;"></h:outputText>
										</div>
							
							  <rich:spacer  height="30"></rich:spacer>
							 <rich:separator lineType="dashed"></rich:separator>
							 
							 
							 <rich:spacer  height="30"></rich:spacer>
							 		<div class="row" align="center"
								style="BACKGROUND-COLOR: #ffcc66;">
								<div class="col-md-12" align="center">
									<h:selectOneRadio style="width:25%"
										value="#{liquorVehicleAccidentCaseAECBWFLAction.radioType}"
										valueChangeListener="#{liquorVehicleAccidentCaseAECBWFLAction.changelist}"
										onchange="this.form.submit();">
										<f:selectItem itemValue="N" itemLabel="New" />
										<f:selectItem itemValue="A" itemLabel="Verify/Reject" />


									</h:selectOneRadio>
								</div>
							</div>
							<div class="row">
								<rich:spacer height="20px"></rich:spacer>
							</div>
							 <h:panelGroup rendered="#{liquorVehicleAccidentCaseAECBWFLAction.viewflag == false}">
							 <div align="center">
					<rich:dataTable id="table22" rows="15" var="list"
						value="#{liquorVehicleAccidentCaseAECBWFLAction.display_list}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2" >

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.srno}">
							</h:outputText>
						</rich:column>
						
					
				
					
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Gatepass no. "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.gatepass_no}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Gatepass Date "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.gatepass_date}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="BWFL"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.distillery_name}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center"  rendered="#{liquorVehicleAccidentCaseAECBWFLAction.radioType eq 'A'}">
							<f:facet name="header">
								<h:outputText value="Status"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.status}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Action"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:commandButton value="View" styleClass="btn btn-info btn-sm" actionListener="#{liquorVehicleAccidentCaseAECBWFLAction.view}"></h:commandButton>
							
						</rich:column>
						
							
							<rich:column align="center" rendered="#{liquorVehicleAccidentCaseAECBWFLAction.radioType eq 'A'}">
								<f:facet name="header">
									<h:outputText value="Forward Gatepass"
										styleClass="generalHeaderOutputTable"
										></h:outputText>
								</f:facet>
										<h:outputLink
								value="/doc/ExciseUp/WholeSale/pdf/#{list.gatepass_no}_new.pdf"
								target="_blank">

								<h:outputText value="View Forward Gatepass "
									style="FONT-SIZE: large; TEXT-DECORATION: underline; FONT-FAMILY: 'Agency FB'; COLOR: #0000ff;"></h:outputText>
            
							</h:outputLink>
							


							
							</rich:column>
							<rich:column align="center" rendered="#{liquorVehicleAccidentCaseAECBWFLAction.radioType eq 'A'}">
								<f:facet name="header">
									<h:outputText value="Return Gatepass"
										styleClass="generalHeaderOutputTable"
										></h:outputText>
								</f:facet>
										<h:outputLink
								value="/doc/ExciseUp/WholeSale/pdf/#{list.gatepass_no}_return.pdf"
								target="_blank">

								<h:outputText value="View Return Gatepass "
									style="FONT-SIZE: large;TEXT-DECORATION: underline;  FONT-FAMILY: 'Agency FB';  COLOR: #0000ff;"></h:outputText>
            
							</h:outputLink>
							


							
							</rich:column>
							<rich:column align="center" rendered="#{liquorVehicleAccidentCaseAECBWFLAction.radioType eq 'A'}">
							<f:facet name="header">
								<h:outputText value="Receive"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:commandButton value="Receive" styleClass="btn btn-success btn-sm" actionListener="#{liquorVehicleAccidentCaseAECBWFLAction.genrateBarcode}"></h:commandButton>
							
						</rich:column>
						
						
						
					   <f:facet name="footer">
							<rich:datascroller for="table22"></rich:datascroller>
						</f:facet> 

					</rich:dataTable>
					</div>
					</h:panelGroup>
						<h:panelGroup rendered="#{liquorVehicleAccidentCaseAECBWFLAction.viewflag}">	
							 <rich:spacer  height="30"></rich:spacer>
							 <div class="main">
						<div>
<table class="table table-hover table-striped table-bordered"  style="width: 100%;">
											<tr>
												<td style="width: 150px; padding: 10px;"><h:outputText
														value="Gatepass type : " /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.gatepass_type}" /></td>
														<td style="width: 150px; padding: 10px;"><h:outputText
														value="Gatepass No. " /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.gatepass_no}" /></td>
															<td style="width: 150px; padding: 10px;"><h:outputText
														value="Gatepass Date . " /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.gatepass_date}" /></td>
													
											</tr>
											<tr>
												<td style="width: 150px; padding: 10px;"><h:outputText
														value="Accident Location Address :" /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.accident_address}" /></td>
														<td style="width: 150px; padding: 10px;"><h:outputText
														value="Accident Location District:" /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.accident_district_name}" /></td>
															<td style="width: 150px; padding: 10px;"><h:outputText
														value="Accident  Date :" /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.accident_date}" /></td>
							
											</tr>

											<tr>
												<td style="width: 150px; padding: 10px;"><h:outputText
														value="License No: " /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.licenseNo}" />
												</td>
												
													<td style="width: 150px; padding: 10px;"><h:outputText
														value="License Name:" /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.licenseNm}" />
												</td>
												
													<td style="width: 150px; padding: 10px;"><h:outputText
														value="Driver Name: " /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.driverNm}" />
												</td>
				
											</tr>
											<tr>
												<td style="width: 150px; padding: 10px;"><h:outputText
														value="License Address : " /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.licenseAddrss}" /></td>
														<td style="width: 150px; padding: 10px;"><h:outputText
														value="License Root details : " /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.licenserootDetails}" /></td>
															<td style="width: 150px; padding: 10px;"><h:outputText
														value="Inpection Report : " /></td>
												<td style="width: 300px; text-align: left;"><h:outputLink
														value="/doc/ExciseUp/WholeSale/pdf/#{liquorVehicleAccidentCaseAECBWFLAction.gatepass_no}_new.pdf"
														target="_blank">

														<h:outputText
															value="Inpection Report"
															style="TEXT-DECORATION: underline; FONT-STYLE: italic; COLOR: #0000ff;"></h:outputText>

													</h:outputLink></td>
														
														
											</tr>
											<tr>
												<td style="width: 150px; padding: 10px;"><h:outputText
														value="Vehicle No : " /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.vehcleNo}" /></td>
																<td style="width: 150px; padding: 10px;"><h:outputText
														value="Agency Address: " /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.vehicaleagecyAdd}"/></td>
														
														
														
														
							
											</tr>
											
											<rich:spacer height="20px"></rich:spacer>
										</table>
										</div>
				 <div class="row">   
				  <rich:spacer height="30"></rich:spacer>
                 </div>
							
							 
							 </div>
							 
							 <rich:spacer height="10px"></rich:spacer>
							 	<div class="row" align="center">
										<table class="table table-hover table-striped table-bordered"  style="width: 80%;">
											<tr>
												<td style="width: 150px; padding: 10px;"><h:outputText
														value="Date Created: " /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.date_list}" /></td>
													
	<td style="width: 150px; padding: 10px;"><h:outputText
														value="License Type : " /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.licensetype_list}" /></td>

											</tr>
										

											<tr>
												<td style="width: 150px; padding: 10px;"><h:outputText
														value="Gatapass No: " /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.gatapassNo_list}" />
												</td>
												<td style="width: 150px; padding: 10px;"><h:outputText
														value="License No : " /></td>
												<td style="width: 300px; text-align: left;"><h:outputText
														value="#{liquorVehicleAccidentCaseAECBWFLAction.licenseno_list}" /></td>
												
											</tr>
										
											
											<rich:spacer height="20px"></rich:spacer>
										</table>

										
									</div>
							 
					
							 
							 
					<div align="center">
					<rich:dataTable id="table23"  var="list22"
						value="#{liquorVehicleAccidentCaseAECBWFLAction.brand_list}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2"  style="width:95%;">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.sr2}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Brand Name "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.brand_name}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Size "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.size}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Box Size "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.box_size}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Actual Dipatched Box "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.dispatch_box}">
							</h:outputText>
						</rich:column>
						<!--<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Total No. of Bottles "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.no_of_bottles}">
							</h:outputText>
						</rich:column>-->
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Actual Dispatched Bottles "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.dispatch_bottle}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center" >
							<f:facet name="header">
								<h:outputText value=" Total Damage Box "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.damage_box}" style="width: 90px;">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Total Damage Bottles "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.damage_bottle}" style="width: 90px;">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Total return Box "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.return_box}" style="width: 90px;">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Total return Bottles "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.return_bottle}" style="width: 90px;">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Procced To Dispatch Box"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.prcced_dispatch_box}" style="width: 90px;">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Procced To Dispatch Bottle"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list22.prcced_dispatch_bottle}" style="width: 90px;">
							</h:outputText>
						</rich:column>
						
						
						
					   <f:facet name="footer">
							<rich:datascroller for="table23"></rich:datascroller>
						</f:facet> 

					</rich:dataTable>
					</div>
					 <rich:spacer height="20px"></rich:spacer>
					 
					 <div align="center">
					
					</div>
					
					<div align="center">
							   <h:commandButton value="Accept" action="#{liquorVehicleAccidentCaseAECBWFLAction.verfy}" 
							   disabled="#{liquorVehicleAccidentCaseAECBWFLAction.radioType eq 'A'}" style="margin:0px 5px;"
							   styleClass="btn btn-success btn-sm"></h:commandButton>
							      <h:commandButton value="Reject" action="#{liquorVehicleAccidentCaseAECBWFLAction.reject}" 
							      disabled="#{liquorVehicleAccidentCaseAECBWFLAction.radioType eq 'A'}" style="margin:0px 5px;"
							   styleClass="btn btn-success btn-sm"></h:commandButton>
							   <h:commandButton value="Back" action="#{liquorVehicleAccidentCaseAECBWFLAction.back}" style="margin:0px 5px;"
							   styleClass="btn btn-warning btn-sm"></h:commandButton>
							 </div>
							 </h:panelGroup>
							 		 <rich:spacer height="20px"></rich:spacer>
							 <h:panelGroup rendered="#{liquorVehicleAccidentCaseAECBWFLAction.barcode_flag}">
							    <div align="left"><h:outputText
										value="You Can genrate One Time QR/BAR Code of SKU in a Gatepass"
										
										style="FONT-SIZE: xx-large; FONT-FAMILY: 'Agency FB';  TEXT-DECORATION: underline;"></h:outputText>
										</div>
												 <rich:spacer height="20px"></rich:spacer>
							 <div >
							 <rich:dataTable columnClasses="columnClass1"
													headerClass="TableHead" footerClass="TableHead"
													rowClasses="TableRow1,TableRow2" styleClass="DataTable"
													id="table2" rows="10" width="100%"
													value="#{liquorVehicleAccidentCaseAECBWFLAction.showDataTableList}" var="list11" >
													<rich:column  sortBy="#{list11.planid}">
														<f:facet name="header">
															<h:outputText value="Plan Id.">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list11.plan_id}"  styleClass="generalHeaderStyleOutput" >

														</h:outputText>
													</rich:column>
													 <rich:column  sortBy="#{list11.showDataTable_Date}">
														<f:facet name="header">
															<h:outputText value="Date" width="60px">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list11.showDataTable_Date}"  styleClass="generalHeaderStyleOutput" >

														</h:outputText>
													</rich:column>
													<rich:column width="60px">
														<f:facet name="header">
															<h:outputText value="Liquor Type">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list11.showDataTable_LiqureType}"  styleClass="generalHeaderStyleOutput" >

														</h:outputText>
													</rich:column>
													<rich:column width="60px">
														<f:facet name="header">
															<h:outputText value="Licence Type" >
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list11.showDataTable_LicenceType}"  styleClass="generalHeaderStyleOutput" >

														</h:outputText>
													</rich:column>
													<rich:column sortBy="#{list11.showDataTable_Brand}">
														<f:facet name="header">
															<h:outputText value="Brand" >
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list11.showDataTable_Brand}"  styleClass="generalHeaderStyleOutput" >
															 
														</h:outputText>
													</rich:column>
													<rich:column sortBy="#{list11.showDataTable_Packging}" width="60px">
														<f:facet name="header">
															<h:outputText value="capacity" >
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list11.showDataTable_Packging}"  styleClass="generalHeaderStyleOutput" >
															 
														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Quantity" width="60px" >
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list11.showDataTable_Quntity}"  styleClass="generalHeaderStyleOutput" >
															 
														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Box Size" width="60px" >
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list11.box_size}"  styleClass="generalHeaderStyleOutput" >
															 
														</h:outputText>
													</rich:column>
													
													
													<rich:column>
														<f:facet name="header">
															<h:outputText style="white-space: normal" value="Plan No. Of Bottling" width="60px">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list11.showDataTable_PlanNoOfBottling}"  styleClass="generalHeaderStyleOutput" >
															 
														</h:outputText>
													</rich:column>
													
													
													<rich:column>
														<f:facet name="header">
															<h:outputText value="No. Of Cases" width="60px">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list11.showDataTable_NoOfBoxes}"  styleClass="generalHeaderStyleOutput" >
															 
														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Breakage" width="60px">
															</h:outputText>
														</f:facet>
														<h:inputText value="#{list11.showDataTable_breakgae}" disabled="#{list11.receive_flg eq 'F'}" >
															 
														</h:inputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Receive Date" width="60px">
															</h:outputText>
														</f:facet>
														
														
														<rich:calendar disabled="#{list11.receive_flg eq 'F'}"
																	value="#{list11.receive_date}">
																</rich:calendar>
													</rich:column>
														<rich:column>
														<f:facet name="header">
															<h:outputText value="No Of Bottle For Barcode" width="60px">
															</h:outputText>
														</f:facet>
														<h:inputText value="#{list11.showDataTabl_barcode_bottle}" disabled="#{list11.finalized_falg eq 'F' or list11.finalized_falg eq 'N'}" 
														rendered="#{list11.receive_flg eq 'F'}" >
															 
														</h:inputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Finalize" >
															</h:outputText>
														</f:facet>
														<h:commandButton actionListener="#{liquorVehicleAccidentCaseAECBWFLAction.finalizeData}" 
														value="Finalize Data"  styleClass="btn btn-sm btn-danger" disabled="#{list11.finalized_falg eq 'F' or list11.finalized_falg eq 'N'}" 
															onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;"
															rendered="#{list11.receive_flg eq 'F'}">
														
														</h:commandButton>
														<h:commandButton actionListener="#{liquorVehicleAccidentCaseAECBWFLAction.receive}" 
														value="Receive"  styleClass="btn btn-sm btn-success"
															rendered="#{list11.receive_flg eq 'N'}">
														
														</h:commandButton>
														
														
														
														<h:outputLink target="_blank" value="/doc/ExciseUp/Excel/#{list11.plan_id}#{list11.code_generate_through}#{list11.finalizedDateString}.xls">
															<h:outputText value="Download Excel" rendered="#{list11.finalized_falg eq 'F' and list11.maped_unmaped_flag eq 'M'}"/>
															
															</h:outputLink> 
														
														
															<h:outputLink target="_blank" value="/doc/ExciseUp/Excel/#{list11.plan_id}#{list.code_generate_through}#{list11.finalizedDateString}.csv">
															 
															</h:outputLink> 
														
														
														
														 
														
														
														
													</rich:column>
													
													
													

													<f:facet name="footer">
														<rich:datascroller for="table2"></rich:datascroller>
													</f:facet>
												</rich:dataTable>
							 </div>
							 <div style="height: 30px"></div>
							   <div align="center">
							    <h:commandButton value="Back" action="#{liquorVehicleAccidentCaseAECBWFLAction.back}" style="margin:0px 5px;"
							   styleClass="btn btn-warning btn-sm"></h:commandButton>
							   </div>
							 </h:panelGroup>
							 
							 
							   <div style="height: 30px"></div>
   </h:form>
   </f:view>
</ui:composition>