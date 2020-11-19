 <ui:composition
       xmlns="http://www.w3.org/1999/xhtml"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:a4j="http://richfaces.org/a4j"
      xmlns:rich="http://richfaces.org/rich">
<f:view>

   <h:form>
   
   <div class="row">
									<rich:spacer height="35px;"></rich:spacer>
								</div>
   
       <div class="row">
									<div class="col-md-12 wow shake" align="center">
										<h:messages errorStyle="color:red" layout="TABLE"
											id="messages3" infoStyle="color:green"
											style="font-size:18px; background-color:#e1fcdf;">
										</h:messages>

									</div>



								</div>
                                  <div  style="border-style: groove; border-color: blue; margin:22px;">
								<div class="row">
									<rich:spacer height="15px;"></rich:spacer>
								</div>

								<div class="row" align="center">
									<h:outputText value="DEO Verification of Shop Stock"
										style="FONT-SIZE: xx-large; FONT-FAMILY: 'Agency FB'; COLOR: #253f8a; TEXT-DECORATION: underline;" />
								</div>
								
								<div class="row" align="center"><h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/Shop.pdf"
												target="_blank">
									<h:outputText  
													value="Download Retail Shop List For Mobile No."  
													style="color: blue; font-family: serif; font-size: 12pt"></h:outputText></h:outputLink>
								</div>
								
								
								<div class="col-md-12">
								    <div class="col-md-3" align="right">
								       <h:outputText value="Shop Id :">
								       
								       </h:outputText>
								    </div>
								    
								    <div class="col-md-3" align="left">
								        <h:inputText styleClass="form-control"
								        value="#{shopStockVerificationAction.shop_id}">
								          
								        </h:inputText>
								    </div>
								    
								    <div class="cl-md-3" align="left">
								          <h:commandButton value="Display" styleClass="btn btn-sm btn-primary"
								          action="#{shopStockVerificationAction.display }">
								          
								          </h:commandButton>
								    </div>
								    
								    
								
								</div>
								
								<div class="row">
									<rich:spacer height="30px;"></rich:spacer>
								</div>
								
								</div>
								
								<h:panelGroup rendered="#{shopStockVerificationAction.flag}">
								
								<div class="row">
									<rich:spacer height="30px;"></rich:spacer>
								</div>
								<div class="col-md-12">
								    <div class="col-md-4" align="right">
								       <h:outputText value="Shop Name :"></h:outputText>
								    </div>
								    
								    <div class="col-md-3" align="left">
								       <h:inputText styleClass="form-control"
								       value="#{shopStockVerificationAction.shop_name}"></h:inputText>
								    </div>
								</div>
								<div class="row">
									<rich:spacer height="10px;"></rich:spacer>
								</div>
								<div class="col-md-12">
								    <div class="col-md-4" align="right">
								       <h:outputText value="Licensee Name :"></h:outputText>
								    </div>
								    
								    <div class="col-md-3" align="left">
								       <h:inputText styleClass="form-control"
								       value="#{shopStockVerificationAction.licensee_name}"/>
								    </div>
								</div>
								 <div class="row">
									<rich:spacer height="10px;"></rich:spacer>
								</div>
								<div class="col-md-12">
								    <div class="col-md-4" align="right">
								       <h:outputText value="Shop Type :"></h:outputText>
								    </div>
								    
								    <div class="col-md-3" align="left">
								       <h:inputText styleClass="form-control"
								       value="#{shopStockVerificationAction.shop_type}"/>
								    </div>
								</div>
								
								<div class="row">
									<rich:spacer height="30px;"></rich:spacer>
								</div>
								<div class="row" align="left">
												<h4 style="COLOR: #0000ff;">
													<b><u><i> Stock Declared By Shop:</i></u></b>
												</h4>
											</div>

                         <div style="overflow-x: scroll; width: 100%;">
												<rich:dataTable columnClasses="columnClass1"
													headerClass="TableHead" footerClass="TableHead"
													rowClasses="TableRow1,TableRow2" styleClass="DataTable"
													id="table3" width="100%"
													value="#{shopStockVerificationAction.stockList}" var="list">

                                                   <rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Serial No">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.srNo}"
															styleClass="generalHeaderStyleOutput">
														</h:outputText>
														

													</rich:column>
													<rich:column align="center" width="250px">
														<f:facet name="header">
															<h:outputText value="Brand">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.brand}" />

													</rich:column>
													
													<rich:column align="center" width="250px">
														<f:facet name="header">
															<h:outputText value="FKU No.">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.etin}" />

													</rich:column>
													

													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Size">
															</h:outputText>
														</f:facet>
														
															<h:outputText value="#{list.size}"
																styleClass="generalHeaderStyleOutput">
															</h:outputText>
														

													</rich:column>


													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="No. Of Box"
																style=" white-space: normal;width : 70px;">
															</h:outputText>
														</f:facet>

														<h:outputText value="#{list.no_of_box}"
															style=" white-space: normal;width : 70px;"
															styleClass="generalHeaderStyleOutput">

															
														</h:outputText>



													</rich:column>







													

													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Total No.Of Bottles "
																style=" white-space: normal;width : 100px;">
															</h:outputText>
														</f:facet>
														
															<h:outputText value="#{list.total_no_of_bottles}"
																style=" white-space: normal;width : 100px;"
																styleClass="generalHeaderStyleOutput" >

															</h:outputText>
														

													</rich:column>

													

												</rich:dataTable>

											</div>
											
											
											 <div class="row">
									<rich:spacer height="30px;"></rich:spacer>
								</div>
											
											<div class="col-md-12">
											   <div class="col-md-4" align="right">
											      <h:outputText value="Total Rollover Fees :"></h:outputText>
											   </div>
											   
											   <div class="col-md-3" align="left">
											     <h:inputText styleClass="form-control"
											     value="#{shopStockVerificationAction.rolloverFee}" >
											     <f:convertNumber maxFractionDigits="2"
													pattern="#############0.00" />
											     </h:inputText>
											     
											   </div>
											</div>
											
											<div class="col-md-12">
											   <div class="col-md-4" align="right">
											      <h:outputText value="Total Diffrential Duty :"></h:outputText>
											   </div>
											   
											   <div class="col-md-3" align="left">
											     <h:inputText styleClass="form-control"
											     
											     value="#{shopStockVerificationAction.diffrential_duty}">
											     <f:convertNumber maxFractionDigits="2"
													pattern="#############0.00" />
											     </h:inputText>
											     
											   </div>
											</div>
											
											<div class="row">
									<rich:spacer height="30px;"></rich:spacer>
								</div>
								
								<div class="row" align="left">
												<h4 style="COLOR: #0000ff;">
													<b><u><i> Diposite:</i></u></b>
												</h4>
											</div>

<div style="overflow-x: scroll; width: 100%;">
												<rich:dataTable columnClasses="columnClass1"
													headerClass="TableHead" footerClass="TableHead"
													rowClasses="TableRow1,TableRow2" styleClass="DataTable"
													id="table4" width="100%"
													value="#{shopStockVerificationAction.table}" var="list">

                                                   <rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Serial No">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.srNo1}"
															styleClass="generalHeaderStyleOutput">
														</h:outputText>
														

													</rich:column>
													<rich:column align="center" width="250px">
														<f:facet name="header">
														
															<h:outputText value="Challan No.">
															</h:outputText>
														</f:facet>
														<h:outputText value="ABK">
															</h:outputText>
															<h:inputText value="#{list.challan_no}" style=" width : 149px; height : 22px;"/>

													</rich:column>
													

													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Challan Date">
															</h:outputText>
														</f:facet>
														<rich:calendar value="#{list.challan_date }"></rich:calendar>

													</rich:column>
													
													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Challan Type">
															</h:outputText>
														</f:facet>
														
														<h:selectOneMenu value = "#{list.fees_type}"> 
														   <f:selectItem itemValue = "" itemLabel = "--Select--" /> 
														   <f:selectItem itemValue = "R" itemLabel = "Rollover Fee" /> 
														   <f:selectItem itemValue = "D" itemLabel = "Diffrential Duty" /> 
														</h:selectOneMenu> 
														
													

													</rich:column>


													

													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Amount "
																style=" white-space: normal;width : 100px;">
															</h:outputText>
														</f:facet>
														
															<h:inputText value="#{list.amount}"
																style=" white-space: normal;width : 100px;"
																styleClass="generalHeaderStyleOutput" >

															</h:inputText>
														
													</rich:column>
													
													<rich:column>
														<f:facet name="header">
															<h:commandButton class="imag"
																style=" white-space: normal;width : 30px;"
																action="#{shopStockVerificationAction.addRowMethodPackaging}"
																image="/img/add.png" />
														</f:facet>
														<h:commandButton class="imag"
															actionListener="#{shopStockVerificationAction.deleteRowMethodPackaging}"
															style="background: transparent;white-space: normal;width : 30px; "
															image="/img/del.png" />
													</rich:column>

													

												</rich:dataTable>

											</div>
											
											<div class="row">
									<rich:spacer height="30px;"></rich:spacer>
								</div>
								
								<div align="center">
								    <h:commandButton value="Save" styleClass="btn btn-sm btn-success"
								    action="#{shopStockVerificationAction.save}"></h:commandButton>
								    
									<rich:spacer width="10px;"></rich:spacer>
								
								    <h:commandButton value="Genrate Affidavite"  styleClass="btn btn-sm btn-primary"
								    action="#{shopStockVerificationAction.generate}"></h:commandButton>
								</div>
								
								<div class="row">
									<rich:spacer height="30px;"></rich:spacer>
								</div>
								
							</h:panelGroup>	
   
   </h:form>

</f:view>
</ui:composition>