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
  	             <div class="row" align="center">
					<div class="col-md-12">
						<h:outputLabel value="CSD Application Tracking"
							style="TEXT-DECORATION: underline; COLOR: #6f7de3; text-align:center;font-family:Calibri;font-weight:bold;font-size:35px;" />

						<h:inputHidden value="#{cSD_Application_Tracking_Action.hidden}" />
					</div>
				</div>
				   <div>
                      <rich:separator lineType="dashed"></rich:separator>
                 </div>
                 <div class="row">
                   <rich:spacer height="30"></rich:spacer>
                  </div>
				   <div class="row" align="center">
					<div class="col-md-12">
						<h:outputText value="#{cSD_Application_Tracking_Action.name}"
						style="text-align:center;font-family:Calibri;font-weight:bold;font-size:30px;"></h:outputText>

					
					</div>
				</div>
				
				<div class="row">
				<rich:spacer height="10"></rich:spacer>
				</div>
				
				  
				<div class="row">
				<rich:spacer height="20"></rich:spacer>
				</div>
               <div class="row">
					<div class="col-md-12">

						<rich:dataTable align="center" styleClass="table-responsive"
							width="100%" id="rentopiccat" rows="10"
							value="#{cSD_Application_Tracking_Action.datalist}" var="list"
							style="FONT-FAMILY: 'Baskerville Old Face';"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2">

							<rich:column id="column1">
								<f:facet name="header">
									<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
									 value="#{list.srNo}" />
								</center>
							</rich:column>

							<rich:column id="column2" >
								<f:facet name="header">
									<h:outputText value="Application No." styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.app_id}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Application Date"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.app_date}" />
								</center>
							</rich:column>
							
							
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Permit No."
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.permit_no}" />
								</center>
							</rich:column>

							
							<rich:column id="column3"
								>
								<f:facet name="header">
									<h:outputText value="Status"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.status}" />
								</center>
							</rich:column>

							

							<rich:column id="column4"
								>
								<f:facet name="header">
									<h:outputText value="Permit"
										styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputLink styleClass="outputLinkEx"
											rendered="#{list.flag}"
											value="/doc/ExciseUp/Bond/FL2Apermit//#{list.permit_no}_esign.pdf"
											target="_blank">
											<h:outputText styleClass="outputText" id="textF"
												value="View Permit"
												style="color: blue; font-family: serif; font-size: 10pt"></h:outputText>
									</h:outputLink>
								</center>
							</rich:column>
							<f:facet name="footer">
								<rich:datascroller for="rentopiccat" />
							</f:facet>
						</rich:dataTable>
					</div>
				</div>
				
				 <div class="row">
                   <rich:spacer height="30"></rich:spacer>
                  </div>
  </h:form>
</f:view>
</ui:composition>