<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	

   
  
	<f:view>
		<h:form>
		
<head>
<style>


.table 
tr:nth-child(odd){background-color: #e6e6e6;
.inputtext {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}

.dropdown-menu {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 30%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
	color: maroon;
}

.dropdown-menu1 {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 75%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
	COLOR: maroon; 
	BACKGROUND-COLOR: #0080c0;
}

.textarea1 {
	border-radius: 3px;
	border-style: none;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}
.blinking {
    animation: mymove 2s infinite alternate;
}

@-webkit-keyframes mymove {
    from {opacity:0;}
    to {opacity: 1;}
}


</style>
			</head>
	<h:inputHidden value="#{application_Csd_Action.hidden}"></h:inputHidden>
	
			

						<h:panelGroup rendered="#{application_Csd_Action.panel eq 'T'}">
							<div style="padding: 10px; border: 4px groove #2948a4;">
								<div class="row">
									<div class="col-md-12 wow shake" align="center"></div>



								</div>
								
								<div class="row">
									<div class="col-md-12 wow shake" align="center">
										<h:messages errorStyle="color:red" layout="TABLE"
											id="messages2" infoStyle="color:green"
											style="font-size:18px; background-color:#e1fcdf;">
										</h:messages>

									</div>



								</div>

								<div class="row">
									<rich:spacer height="15px;"></rich:spacer>
								</div>

								<div class="row" align="center" style="position: -webkit-sticky;position: sticky;top: 0;
								 background-color: white;">
									<h:outputText value="APPLICATION FOR CSD"
										style="FONT-SIZE: xx-large; FONT-FAMILY: 'Agency FB'; COLOR: #253f8a; TEXT-DECORATION: underline;" />
								</div>

								<div class="row">
									<rich:spacer height="15px;"></rich:spacer>
								</div>
								<div class="row" align="center">
									<h:outputLabel value="#{application_Csd_Action.name}"
										style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel>
								</div>
								<div class="row" align="center">
									<h:outputLabel value="#{application_Csd_Action.add}"
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
										
									</div>

								</div>
								
								
								<div class="row">
									<rich:spacer height="40px;"></rich:spacer>
								</div>



							</div>
							
						</h:panelGroup>




						<h:panelGroup rendered="#{application_Csd_Action.panel eq 'N'}">
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
									<h:outputLabel value="#{application_Csd_Action.name}"
										style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel>
								</div>
								<div class="row" align="center">
									<h:outputLabel value="#{application_Csd_Action.add}"
										style="COLOR: #000000; FONT-SIZE: medium;"></h:outputLabel>
								</div>

								<div class="row">
									<rich:spacer height="20px;"></rich:spacer>
								</div>
								
								<div class="row">
									<rich:spacer height="10px;"></rich:spacer>
								</div>
</div>
</h:panelGroup>
								<h:panelGroup rendered="true">

									<div class="row" align="center">
										<div class="col-md-12 col-sm-12" style="padding: 0px;">

        <rich:spacer height="20"></rich:spacer>
	 <div align="center">
					         <h:selectOneRadio  value="#{application_Csd_Action.radio}"
					                  >
					                    
					                    <a4j:support event="onchange" actionListener="#{application_Csd_Action.method}"
					                    reRender="unit,balance"></a4j:support>
					               <f:selectItem itemValue="D" itemLabel="Distillery Up" />
					               <f:selectItem itemValue="B" itemLabel="Brewery Up" />
					               <f:selectItem itemValue="O" itemLabel="OutSide Up" />
					               <f:selectItem itemValue="IU" itemLabel="Imported Unit" />
					         
					         </h:selectOneRadio>
					         
					    </div>
					    <div class="row">
												<rich:spacer height="10px;"></rich:spacer>
											</div>
											<div class="row" align="center">
												
												

												
												<div>Brand Registering
													Unit::
												
													<a4j:outputPanel id="unit"><h:selectOneMenu style="width: 300px;"
														
														value="#{application_Csd_Action.unit_id}"
														
														onchange="this.form.submit();"
														valueChangeListener="#{application_Csd_Action.importer_unit_lsnr}">
														<f:selectItems
															value="#{application_Csd_Action.unit_list}" />
													</h:selectOneMenu> </a4j:outputPanel>
												</div>
												
											</div>
											
											
										
											
											<div class="row" align="left" style="COLOR: #ff0000;">
												<rich:spacer height="10px;"></rich:spacer>
											</div>
											
											
											
											
											
											
											<div>
											<div class="row" align="center">
												
											
                                                  
											
											
											
											</div>											
											</div>
											
											<div class="row blinking" align="left">
												<h4 style="COLOR: #0000ff;">
													<b><u><i class ="blinking"> Application Permit Required For:</i></u></b>
												</h4>
											</div>

                     <div style="overflow-x: scroll; width: 98%;">
												<rich:dataTable columnClasses="columnClass1"
													headerClass="TableHead" footerClass="TableHead"
													rowClasses="TableRow1,TableRow2" styleClass="DataTable"
													id="table3" width="100%"
													value="#{application_Csd_Action.displaylist}" var="list">

                                                   <rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Serial No">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.sno}"
															styleClass="generalHeaderStyleOutput">
														</h:outputText>
														

													</rich:column>
													<rich:column align="center" width="250px">
														<f:facet name="header">
															<h:outputText value="Brand">
															</h:outputText>
														</f:facet>
														<h:selectOneMenu value="#{list.brand_id}"
															style=" white-space: normal;width : 220px; height: 30px;"
															styleClass="dropdown-menu"
															><a4j:support event="onchange" actionListener="#{application_Csd_Action.method1}"
															reRender="pack">
															</a4j:support>
															
															<f:selectItems value="#{list.brandList}" />
														</h:selectOneMenu>

													</rich:column>
													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Capacity"
																style=" white-space: normal;width : 70px;">
															</h:outputText>
														</f:facet>
														<h:selectOneMenu value="#{list.pckg_id}" id="pack"
															style=" white-space: normal;width : 75px;height: 30px;"
															onchange="this.form.submit();"
                                                              styleClass="dropdown-menu">
															
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
														<a4j:outputPanel id="qty">
															<h:outputText value="#{list.quantity}"
																styleClass="generalHeaderStyleOutput">
															</h:outputText>
														</a4j:outputPanel>

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
																reRender="box,special_fee,qty,fees,total_importfees,total_addduty,save_btn,duty,total_duty,total_scanfee,scanfees,coronafees">
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
															styleClass="generalHeaderStyleOutput" >
															<a4j:support event="onblur"
																reRender="box,special_fee,fees,qty,total_importfees,total_addduty,save_btn,duty,total_duty,total_scanfee,scanfees,coronafees,total_coronafee">
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
															<h:outputText value="Import Fee"
																style=" white-space: normal;width : 70px;"
																styleClass="generalHeaderOutputTable" />


														</f:facet>
														<a4j:outputPanel id="fees">
															<h:outputText value="#{list.cal_importFee}"
																style=" white-space: normal;width : 70px;"
																styleClass="generalHeaderStyleOutput">
																<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" />
															</h:outputText>
														</a4j:outputPanel>

													</rich:column>
													
													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value=" Duty"
																style=" white-space: normal;width : 70px;"
																styleClass="generalHeaderOutputTable" />


														</f:facet>
														<a4j:outputPanel id="duty">
															<h:outputText value="#{list.cal_duty}"
																style=" white-space: normal;width : 70px;"
																styleClass="generalHeaderStyleOutput">
																<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" />
															</h:outputText>
														</a4j:outputPanel>

													</rich:column>
													
													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Scanning Fee"
																style=" white-space: normal;width : 70px;"
																styleClass="generalHeaderOutputTable" />


														</f:facet>
														<a4j:outputPanel id="scanfees">
															<h:outputText value="#{list.cal_scanfee}"
																style=" white-space: normal;width : 70px;">
																<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" />
															</h:outputText>
														</a4j:outputPanel>

													</rich:column>
													
													
													
													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Spcl Additional Consideration Fee"
																style=" white-space: normal;width : 70px;"
																styleClass="generalHeaderOutputTable" />


														</f:facet>
														<a4j:outputPanel id="coronafees">
															<h:outputText value="#{list.cal_coronafee}"
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
																action="#{application_Csd_Action.addRowMethodPackaging}"
																image="/img/add.png" >
															
																</h:commandButton>
														</f:facet>
														<h:commandButton class="imag"
															actionListener="#{application_Csd_Action.deleteRowMethodPackaging}"
															style="background: transparent;white-space: normal;width : 30px; "
															image="/img/del.png" />
													</rich:column>

												</rich:dataTable>

											</div>
											<a4j:outputPanel id="balance">	
										<h:panelGroup  rendered="#{application_Csd_Action.radio eq 'O' or application_Csd_Action.radio eq 'IU'}">
										<div class="row" align="left" style="COLOR: #ff0000;">
												<rich:spacer height="20px;"></rich:spacer></div>
										<div align="center">
										   <table id="table" style="border-collapse: collapse;border: 1px solid black;width:40%" class="table table-hover">
										       <tr>
										       <td style="margin: 5px; border: 1px solid #dddddd;padding: 5px;width:300px;" >
										        <h:outputText value="Total Available Import Fee"></h:outputText>
										       </td>
										       <td style="margin: 5px; border: 1px solid #dddddd;padding: 5px; width:200px;">
										        <h:outputText value="#{application_Csd_Action.balance_import_fee}" style="FONT-WEIGHT: bold;"
										        ><f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" /></h:outputText>
										       </td>
										       </tr>
										        <tr>
										       <td style="margin: 5px; border: 1px solid #dddddd;padding:5px;">
										        <h:outputText value="Total Available Special Fee"></h:outputText>
										       </td>
										       <td style="margin: 5px; border: 1px solid #dddddd;padding: 5px;">
										        <h:outputText value="#{application_Csd_Action.balance_spcl_fee}" style="FONT-WEIGHT: bold;"
										        ><f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" /></h:outputText>
										       </td>
										       </tr>
										        <tr >
										       <td style="margin: 5px; border: 1px solid #dddddd;padding: 5px;" >
										        <h:outputText value="Total Available Excise Duty Fee"></h:outputText>
										       </td>
										       <td style="margin: 5px; border: 1px solid #dddddd;padding: 5px;">
										        <h:outputText value="#{application_Csd_Action.balance_duty_fee}" style="FONT-WEIGHT: bold;"
										        ><f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" /></h:outputText>
										       </td>
										       </tr>
										        <tr>
										       <td style="margin: 5px; border: 1px solid #dddddd;padding: 5px;">
										        <h:outputText value="Total Available Scanning Fee"></h:outputText>
										       </td>
										       <td style="margin: 5px; border: 1px solid #dddddd;padding: 5px;">
										        <h:outputText value="#{application_Csd_Action.balance_scanning_fee}" style="FONT-WEIGHT: bold;"
										        ><f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" /></h:outputText>
										       </td>
										       </tr>
										       
										       <tr>
										       <td style="margin: 5px; border: 1px solid #dddddd;padding: 5px;">
										        <h:outputText value="Total Available Addtional Consideration Fee"></h:outputText>
										       </td>
										       <td style="margin: 5px; border: 1px solid #dddddd;padding: 5px;">
										        <h:outputText value="#{application_Csd_Action.balance_corona_fee}" style="FONT-WEIGHT: bold;"
										        ><f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" /></h:outputText>
										       </td>
										       </tr>
										       
										      
										   </table>
										</div>
										
										</h:panelGroup>
										</a4j:outputPanel>
										
										
											<div class="row" align="left" style="COLOR: #ff0000;">
												<rich:spacer height="20px;"></rich:spacer>
											</div>
											<div class="row" align="center">

												<a4j:commandButton rendered="true" disabled=""
													styleClass="btn " id="total_importfees"
													reRender="totalpermitfee"
													value="Total Import Fee: #{application_Csd_Action.total_import_fees}">

												</a4j:commandButton>
												<a4j:commandButton rendered="true" disabled=""
													styleClass="btn" id="total_addduty"
													reRender="totalpermitfee"
													value="Total Special Fee : #{application_Csd_Action.total_special_fee}">

												</a4j:commandButton>
												<a4j:commandButton rendered="true" disabled=""
													styleClass="btn " id="total_duty"
													reRender="totalpermitfee"
													value="Total Duty : #{application_Csd_Action.total_duty}">

												</a4j:commandButton>

												
												
												<a4j:commandButton rendered="true" disabled=""
													styleClass="btn" id="total_scanfee"
													reRender="totalpermitfee"
													value="Total Scanning Fee : #{application_Csd_Action.total_scanning_fee}">

												</a4j:commandButton>
												
												<a4j:commandButton rendered="true" disabled=""
													styleClass="btn" id="total_coronafee"
													reRender="totalpermitfee"
													value="Total Spcl Additional Consideration Fee: #{application_Csd_Action.total_corona_fee}">

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
													<h:inputTextarea value="#{application_Csd_Action.route_detail}"
														style="width : 240px; height : 42px;"></h:inputTextarea>
												</div>
												<div class="col-md-6" align="left">
													<h:selectOneRadio
														value="#{application_Csd_Action.route_road_radio}">
														<f:selectItem itemLabel="By Road" itemValue="road" />
														<f:selectItem itemLabel="By Train" itemValue="train" />

													</h:selectOneRadio>
												</div>


											</div>
											<div class="row">
												<rich:spacer height="10px;"></rich:spacer>
											</div>


											<div class="row">
												<rich:spacer height="10px;"></rich:spacer>
											</div>
											


											<div class="row">
												<rich:spacer height="10px;"></rich:spacer>
											</div>

											<div>
												<a4j:outputPanel id="save_btn">
													<h:commandButton value="Save"
														onclick="if (! confirm('No. of Cases-#{application_Csd_Action.total_no_of_cases}, 
														Special Fee-#{application_Csd_Action.total_special_fee},
														 Import Fee and Duty-#{application_Csd_Action.total_import_fees}') ) 
														 { return false;}; return true;"
														action="#{application_Csd_Action.save}"
														styleClass="btn btn-success btn-sm" style="FONT-WEIGHT: bold;">
													</h:commandButton>
												</a4j:outputPanel>
												<h:commandButton value="Reset"
													action="#{application_Csd_Action.reset}"
													styleClass="btn btn-warning btn-sm" style="FONT-WEIGHT: bold;">
												</h:commandButton>
												

											</div>
											<div class="row">
												<rich:spacer height="20px;"></rich:spacer>
											</div>

										</div>

									</div>

								</h:panelGroup>
				</h:form>
	</f:view>
	
</ui:composition>																