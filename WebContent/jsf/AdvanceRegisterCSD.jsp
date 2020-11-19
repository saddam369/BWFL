
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

	<h:form>
		<f:loadBundle basename="com.mentor.nl.userMessage" var="msgBundle" />
		<f:view>
		
<head>
<style>


.line {
    border: 0;
    height: 1px;
    background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
}

.TableHead{
background-color: #d6e0f5;
}


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
	height: 40px;
	width: 30%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
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
			<div class="form-group">
				<div>
					<a4j:outputPanel id="msg">
						<h:messages errorStyle="color:red"
							style="font-size: 14px;font-weight: bold"
							styleClass="generalExciseStyle" layout="table" id="messages"
							infoStyle="color:green">
						</h:messages>
					</a4j:outputPanel>
				</div>


				<div class="row " align="center">
					<rich:spacer height="15px"></rich:spacer>
				</div>

				<div class="row " align="center">
					<div align="center" class="pghead">



						<h1>
							<h:inputHidden value="#{advanceRegisterCSDAction.hidden}" />
							<h:outputText value=" Advance Duty Register CSD"
								style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;" />
						</h1>
					</div>
				</div>
				<hr class="line"></hr>
				<rich:spacer height="10px" />
				
				<h:panelGroup rendered="#{advanceRegisterCSDAction.login_type ne 'DEO'}">
				<div class="row " align="center">

					<h:outputLabel value="#{advanceRegisterCSDAction.name}"
						style="COLOR: #000000; FONT-SIZE: x-large;" />
				</div>
				<div class="row " align="center">
					<h:outputLabel value="#{advanceRegisterCSDAction.address}"
						style="COLOR: #000000; FONT-SIZE: medium;" />

				</div>
				<rich:spacer height="10px" />
				<hr class="line"></hr>
     </h:panelGroup>




				<div class="row " align="center">
					<rich:spacer height="15px"></rich:spacer>
				</div>



				<div class=" row col-md-12">
					<rich:spacer height="20px" />
				</div>

				
				<rich:spacer height="20px" />



  <div class="col-md-3 "></div>
          <h:panelGroup rendered="#{advanceRegisterCSDAction.login_type eq 'DEO'}">
                       <div align="center">
					         <h:selectOneRadio  value="#{advanceRegisterCSDAction.unitradio}" 
					         valueChangeListener="#{advanceRegisterCSDAction.listener1}"
					         onchange="this.form.submit();"
					         >
					                    
					       
					               <f:selectItem itemValue="U" itemLabel="UNIT" />
					               <f:selectItem itemValue="D" itemLabel="DEPO" />
					               
					         </h:selectOneRadio>
					         
					    </div>

				
                <div class="row">
					<rich:spacer height="20px"></rich:spacer>
					</div>
					<div class="col-md-12">

					<div class="col-md-6 " align="right">
						<h:outputText value="Select Unit :" rendered="#{advanceRegisterCSDAction.login_type eq 'DEO'}"
							style="FONT-STYLE: italic; FONT-WEIGHT: bold ;" />
					</div>
					<div class="col-md-6 " align="left">
						<h:selectOneMenu style=" width: 250px; height: 25px;"
						rendered="#{advanceRegisterCSDAction.login_type eq 'DEO'}"
							value="#{advanceRegisterCSDAction.unit_id}"
							valueChangeListener="#{advanceRegisterCSDAction.listener3}"
							 onchange="this.form.submit();">
							<f:selectItems value="#{advanceRegisterCSDAction.unitList}" />
						</h:selectOneMenu>
					</div>
					</div>
					</h:panelGroup>
					<div class="col-md-12">

					<div class="col-md-6 " align="right">
						<h:outputText value="Select Depo :" rendered="#{advanceRegisterCSDAction.login_type eq 'UNIT'}"
							style="FONT-STYLE: italic; FONT-WEIGHT: bold ;" />
					</div>
					<div class="col-md-6 " align="left">
						<h:selectOneMenu style=" width: 250px; height: 25px;"
						rendered="#{advanceRegisterCSDAction.login_type eq 'UNIT'}"
							value="#{advanceRegisterCSDAction.depo_id}"  onchange="this.form.submit();"
							valueChangeListener="#{advanceRegisterCSDAction.listener2}">
							<f:selectItems value="#{advanceRegisterCSDAction.depoList}" />
						</h:selectOneMenu>
					</div>
                      </div>

					<div class="row">
					<rich:spacer height="20px"></rich:spacer>
					</div>
					
                       <div align="center">
					         <h:selectOneRadio  value="#{advanceRegisterCSDAction.radio}" 
					         valueChangeListener="#{advanceRegisterCSDAction.listener}"
					         onchange="this.form.submit();">
					               <f:selectItem itemValue="IF" itemLabel="Import Fee"
					               itemDisabled="#{advanceRegisterCSDAction.unitradio eq 'U' or advanceRegisterCSDAction.login_type eq 'UNIT'}"/>
					               
					               <f:selectItem itemValue="S" itemLabel="Special Fee" 
					               itemDisabled="#{advanceRegisterCSDAction.unitradio eq 'D' or advanceRegisterCSDAction.login_type eq 'FL2A'}"/>
					               
					               <f:selectItem itemValue="ED" itemLabel="Excise Duty" 
					               itemDisabled="#{advanceRegisterCSDAction.unitradio eq 'D' or advanceRegisterCSDAction.login_type eq 'FL2A'}"/>
					               
					               <f:selectItem itemValue="SF" itemLabel="Scanning Fee" 
					               itemDisabled="#{advanceRegisterCSDAction.unitradio eq 'D' or advanceRegisterCSDAction.login_type eq 'FL2A'}"/>
					              
					               <f:selectItem itemValue="SCF" itemLabel="Special Consideration Fee" 
					               itemDisabled="#{advanceRegisterCSDAction.unitradio eq 'D' or advanceRegisterCSDAction.login_type eq 'FL2A'}"/>
					         </h:selectOneRadio>
					         
					    </div>
					   

				</div>

				<div class="row" align="left">
					<rich:spacer height="10px"></rich:spacer>
				</div>


				



				<div class="row" align="left">
					<rich:spacer height="30px"></rich:spacer>
				</div>

				<div class="col-md-12" align="center">

					<rich:dataTable id="tableunit" rows="60" width="100%" var="list"
						value="#{advanceRegisterCSDAction.showData}" rendered="false"
						headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2">


						<rich:column>
							<f:facet name="header">
								<h:outputText value="Sr.No."
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:inputText value="#{list.srNo}" readonly="true">

							</h:inputText>
						</rich:column>


						<rich:column>
							<f:facet name="header">
								<h:outputText value="Date" styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:inputText value="#{list.date_Dt}" readonly="true"></h:inputText>
						</rich:column>


						<rich:column>
							<f:facet name="header">
								<h:outputText value="Opning"
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:inputText value="#{list.opningBal}" readonly="true">
								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />

							</h:inputText>
						</rich:column>



						<rich:column>
							<f:facet name="header">
								<h:outputText value="Challan Amount"
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:inputText value="#{list.produceBal}" readonly="true">

								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />
							</h:inputText>
						</rich:column>


						<rich:column>
							<f:facet name="header">
								<h:outputText value="Disptch Duty Amount"
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:inputText value="#{list.disptchBal}" readonly="true">
								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />

							</h:inputText>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Balance"
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:inputText value="#{list.balanceTot}" readonly="true">

								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />

							</h:inputText>
						</rich:column>





						<f:facet name="footer">
							<rich:datascroller for="tableunit"></rich:datascroller>
						</f:facet>

					</rich:dataTable>






					<rich:dataTable id="tableunit10" rows="15" width="99%" var="list"
						value="#{advanceRegisterCSDAction.showData}"
						headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2" styleClass="table table-hover">


						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.srno}">

							</h:outputText>
							<f:facet name="footer">
														<h:outputText value="Total"
															styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;"/>
													</f:facet>
						</rich:column>


						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Date" styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.date}"></h:outputText>
							<f:facet name="footer">
														
													</f:facet>
						</rich:column>


						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Description"
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.description}">


							</h:outputText>
							<f:facet name="footer">
														
													</f:facet>
						</rich:column>



						<rich:column align="right">
							<f:facet name="header">
								<h:outputText value="Deposite Amount"
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.deposite_amt}">

								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />
							</h:outputText>
							<f:facet name="footer">
														<h:outputText value="#{advanceRegisterCSDAction.total_deposite}"
															styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;">
															<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" /></h:outputText>
													</f:facet>
													
						</rich:column>


						<rich:column align="right">
							<f:facet name="header">
								<h:outputText value="Permit Amount"
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.permit_amt}">
								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />

							</h:outputText>
							<f:facet name="footer" >
														<h:outputText value="#{advanceRegisterCSDAction.total_permit}" 
															styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;">
															<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" /></h:outputText>
													</f:facet>
						</rich:column>




						<rich:column align="right">
							<f:facet name="header">
								<h:outputText value="Balance"
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.balance}">

								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />

							</h:outputText>
							<f:facet name="footer">
														<h:outputText value="#{advanceRegisterCSDAction.total_bal}"
															styleClass="generalHeaderOutputTable" style="FONT-WEIGHT: bold;">
															<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" /></h:outputText>
													</f:facet>
						</rich:column>





						<f:facet name="footer">
							<rich:datascroller for="tableunit10"></rich:datascroller>
						</f:facet>

					</rich:dataTable>













				</div>
				<div class="row " align="center">
					<rich:spacer height="20px"></rich:spacer>
				</div>



				<div class="col-md-12" align="center"></div>

				<div class="row " align="center">
					<rich:spacer height="40px"></rich:spacer>
				</div>



			
		</f:view>
	</h:form>



</ui:composition>