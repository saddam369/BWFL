
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

	<h:form>
		<f:loadBundle basename="com.mentor.nl.userMessage" var="msgBundle" />
		<f:view>
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
					<div align="center" width="100%" class="pghead">



						<h1>
							<h:inputHidden value="#{advanceDutyRegisterBWFL_FL2D_DEOAction.hidden }" />
							<h:outputText value=" Advance Duty Register"
								style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;" />
						</h1>
					</div>
				</div>
				<rich:separator lineType="dashed" />
				<rich:spacer height="10px" />
				<div class="row " align="center">

					<h:outputLabel value="#{advanceDutyRegisterBWFL_FL2D_DEOAction.name}"
						style="COLOR: #000000; FONT-SIZE: x-large;" />
				</div>
				<div class="row " align="center">
					<h:outputLabel value="#{advanceDutyRegisterBWFL_FL2D_DEOAction.address}"
						style="COLOR: #000000; FONT-SIZE: medium;" />

				</div>
				<rich:spacer height="10px" />
				<rich:separator lineType="dashed" />





				<div class="row " align="center">
					<rich:spacer height="15px"></rich:spacer>
				</div>
				<div align="center">
                <h:selectOneRadio value="#{advanceDutyRegisterBWFL_FL2D_DEOAction.unit_type}"
                onclick="this.form.submit();"
                valueChangeListener="#{advanceDutyRegisterBWFL_FL2D_DEOAction.radioListener}">
                <f:selectItem itemValue="BWFL" itemLabel="BWFL"/>
                <f:selectItem itemValue="FL2D" itemLabel="FL2D"/>
                
                </h:selectOneRadio>
                  </div> 
                  <div class=" row col-md-12">
					<rich:spacer height="20px" />
				</div>
                 <div align="center">
						<h:selectOneMenu style=" width: 250px; height: 25px;"
							value="#{advanceDutyRegisterBWFL_FL2D_DEOAction.unit_id }">
							<f:selectItems value="#{advanceDutyRegisterBWFL_FL2D_DEOAction.unitLsit}" />
						</h:selectOneMenu>
					</div>
				<div class=" row col-md-12">
					<rich:spacer height="20px" />
				</div>

				<div class="row col-md-12" align="center"
					style="FONT-STYLE: italic; FONT-WEIGHT: bold;">
					Between Dates :
					<rich:calendar value="#{advanceDutyRegisterBWFL_FL2D_DEOAction.fromdate}"
						disabled="true"></rich:calendar>
					and :
					<rich:calendar value="#{advanceDutyRegisterBWFL_FL2D_DEOAction.todate}"></rich:calendar>
				</div>
				<rich:spacer height="20px" />





				<div class="row" align="center">

					<div class="col-md-3"></div>

					<div class="col-md-1 " align="right">
						<h:outputText value="Type"
							style="FONT-STYLE: italic; FONT-WEIGHT: bold ;" />
					</div>
					<div class="col-md-4 " align="left">
						<h:selectOneMenu style=" width: 250px; height: 25px;"
							value="#{advanceDutyRegisterBWFL_FL2D_DEOAction.type }">
							<f:selectItems value="#{advanceDutyRegisterBWFL_FL2D_DEOAction.typeList}" />
						</h:selectOneMenu>
					</div>


					<div class="col-md-1 "></div>
					<div class="col-md-3 "></div>


				</div>

				<div class="row" align="left">
					<rich:spacer height="10px"></rich:spacer>
				</div>


				<div class="col-md-12" align="center">

					<h:commandButton action="#{advanceDutyRegisterBWFL_FL2D_DEOAction.getdata}"
						value="View Register" styleClass="btn btn-info btn-sm" />

					<rich:spacer width="20px;" />

					<h:commandButton action="#{advanceDutyRegisterBWFL_FL2D_DEOAction.print}"
						value="Print Report" styleClass="btn btn-info btn-sm" />

					<h:outputLink styleClass="outputLinkEx"
						value="/doc/ExciseUp/WholeSale/pdf//#{advanceDutyRegisterBWFL_FL2D_DEOAction.pdfname}"
						target="_blank">
						<h:outputText styleClass="outputText" id="text223"
							value="View Report"
							rendered="#{advanceDutyRegisterBWFL_FL2D_DEOAction.printFlag==true}"
							style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
					</h:outputLink>

					<rich:spacer width="20px;" />

					<h:commandButton action="#{advanceDutyRegisterBWFL_FL2D_DEOAction.reset}"
						value="Reset" styleClass="btn btn-default btn-sm" />

				</div>



				<div class="row" align="left">
					<rich:spacer height="30px"></rich:spacer>
				</div>

				<div class="col-md-12" align="center">

					<rich:dataTable id="tableunit" rows="60" width="100%" var="list"
						value="#{advanceDutyRegisterBWFL_FL2D_DEOAction.showData}" rendered="false"
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






					<rich:dataTable id="tableunit10" rows="15" width="100%" var="list"
						value="#{advanceDutyRegisterBWFL_FL2D_DEOAction.showData}"
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
								<h:outputText value="Description"
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.description}">


							</h:outputText>
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



			</div>
		</f:view>
	</h:form>



</ui:composition>