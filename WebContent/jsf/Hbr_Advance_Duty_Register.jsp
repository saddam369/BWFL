<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
  <f:view>
<h:form>
<div align="center">
<div>
					<a4j:outputPanel id="msg">
						<h:messages errorStyle="color:red"
							style="font-size: 14px;font-weight: bold"
							styleClass="generalExciseStyle" layout="table" id="messages"
							infoStyle="color:green">
						</h:messages>
					</a4j:outputPanel>
				</div>
	<div align="center" width="100%" class="pghead">

             <h:inputHidden value="#{hbr_Advance_Duty_RegisterAction.hidden}"/>

						<h1>
							
							<h:outputText value=" Advance Duty Register 2020"
								style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;" />
						</h1>
					</div>
					
					
</div>
<rich:separator lineType="dashed" />
<rich:spacer height="10px" />



<div class="row">
<div class="col-md-3"></div>
<div class="col-md-3" align="right">
<h:outputText value="Select HBR" styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
</div>

<div class="col-md-3">
<h:selectOneMenu value="#{hbr_Advance_Duty_RegisterAction.hbr_id}" styleClass="form-control">
<f:selectItems value="#{hbr_Advance_Duty_RegisterAction.hbr_list}"/>
</h:selectOneMenu>
</div>
<div class="col-md-3"></div>
</div>

<div align="center" class="row">
<div class="col-md-4"></div>
<div class="col-md-4">
<h:selectOneRadio value="#{hbr_Advance_Duty_RegisterAction.radio}" 
onchange="this.form.submit();" styleClass="generalHeaderOutputTable"
 style=" FONT-WEIGHT: bold;">
<f:selectItem itemLabel="IMFL" itemValue="F" />
<f:selectItem itemLabel="BEER/LAB" itemValue="B"/>
</h:selectOneRadio>
</div>
<div class="col-md-4"></div>
</div>


<div class="row col-md-12" align="center"
					style="FONT-STYLE: italic; FONT-WEIGHT: bold;">
					Between Dates :
					<rich:calendar value="#{hbr_Advance_Duty_RegisterAction.fromdate}"
						disabled="true"></rich:calendar>
					and :
					<rich:calendar value="#{hbr_Advance_Duty_RegisterAction.todate}"></rich:calendar>
				</div>
				<rich:spacer height="20px" />
				
				<div align="center" class="row">
				<div class="col-md-4"></div>
					<div class="col-md-4">
					<h:commandButton value="Show" action="#{hbr_Advance_Duty_RegisterAction.getData}" styleClass="btn btn-primary"></h:commandButton>
					<h:commandButton value="Reset" action="#{hbr_Advance_Duty_RegisterAction.reset}" styleClass="btn btn-danger"></h:commandButton>
					</div>
						<div class="col-md-4"></div>
				</div>
				<div align="center">
				
				<rich:dataTable var="list"  width="100%" value="#{hbr_Advance_Duty_RegisterAction.data_list }">
				
				<rich:column>
				<f:facet name="header">
				<h:outputText value="SrNo" styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"/>
				</f:facet>
				<h:inputText value="#{list.srNo }" disabled="true"/>
				</rich:column>
				
				
				<rich:column>
				<f:facet name="header">
				<h:outputText value="Date" styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"/>
				</f:facet>
				<h:inputText value="#{list.date }" disabled="true">
				<f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+5:30"/>  
				</h:inputText>
				</rich:column>
				
				<rich:column>
				<f:facet name="header">
				<h:outputText value="Description" styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"/>
				</f:facet>
					<h:inputText value="#{list.discription }" disabled="true"/>
				</rich:column>
				
				<rich:column>
				<f:facet name="header">
				<h:outputText value="ChallanAmount" styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"/>
				</f:facet>
					<h:inputText value="#{list.challan_amount }" disabled="true"/>
				
				</rich:column>
				
				
				<rich:column>
				<f:facet name="header">
				<h:outputText value="Dispatch Duty Amount" styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"/>
				</f:facet>
				<h:inputText value="#{list.dispatch_duty }" disabled="true"/>
				</rich:column>
				
				<rich:column>
				<f:facet name="header">
				<h:outputText value="Balance" styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"/>
				</f:facet>
					<h:inputText value="#{list.balance }" disabled="true"/>
				</rich:column>
				
				</rich:dataTable>
				
				</div>
				
				</h:form>
				</f:view>
</ui:composition>