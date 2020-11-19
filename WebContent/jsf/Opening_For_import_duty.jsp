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
				<rich:spacer height="30"></rich:spacer>
				</div>
				
				<rich:spacer height="30px;"></rich:spacer>
				<div class="row">
					<div class="col-md-12 wow shake" align="center">
						<h:messages errorStyle="color:red" layout="TABLE" id="messages1"
							infoStyle="color:green"
							style="FONT-SIZE: large; background-color:#e1fcdf;">
						</h:messages>
					</div>
				</div>
  	             <div class="row" align="center">
					<div class="col-md-12">
						<h:outputText value="Advance Import Fee For Unit"
														style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: 35px;">
													</h:outputText>
						<h:inputHidden value="#{cSD_Application_Tracking_Action.hidden}" />
					</div>
				</div>
				   <div>
                      <rich:separator lineType="dashed"></rich:separator>
                 </div>
                 <div class="row">
                   <rich:spacer height="30"></rich:spacer>
                  </div>
				  
				<div class="row">
                   <rich:spacer height="30"></rich:spacer>
                  </div>
				
				<div class="row" align="center">
												
												

												
												<div>
													Select Unit::
												
													<h:selectOneMenu style="width: 300px;"
														
														value="#{opening_For_Import_Duty_Action.unit_id}"
														
														
														>
														<f:selectItems
															value="#{opening_For_Import_Duty_Action.unit_list}" />
													</h:selectOneMenu>
												</div>
											
											</div>
												<div class="row">
				<rich:spacer height="20"></rich:spacer>
				</div>
											<div class="col-md-12" align="center">
											<div class="col-md-3" align="right">
											  <h:outputText value="Opening amount for import duty :"></h:outputText>
											</div>
											
											<div class="col-md-3" align="left">
											  <h:inputText value="#{opening_For_Import_Duty_Action.amount}"
											  styleClass="form-control"></h:inputText>
											</div>
												<div class="col-md-3" align="right">
											  <h:outputText value="Date :"></h:outputText>
											</div>
												<div class="col-md-3" align="left">
											    <rich:calendar value="#{opening_For_Import_Duty_Action.date}"></rich:calendar>
											</div>
											</div>
											
											<div class="row">
				<rich:spacer height="20"></rich:spacer>
				</div>
				
				   <div align="center">
				      <h:commandButton value="Save" 
				      styleClass="btn btn-success"
				      action="#{opening_For_Import_Duty_Action.save}"></h:commandButton>
				      <rich:spacer width="10"></rich:spacer>
				      <h:commandButton value="Reset" 
				      styleClass="btn btn-swarning"
				      action="#{opening_For_Import_Duty_Action.reset}"></h:commandButton>
				   </div>
				<div class="row">
				<rich:spacer height="10"></rich:spacer>
				</div>
				
				  
				<div class="row">
				<rich:spacer height="20"></rich:spacer>
				</div>
              
				
				 <div class="row">
                   <rich:spacer height="30"></rich:spacer>
                  </div>
  </h:form>
</f:view>
</ui:composition>