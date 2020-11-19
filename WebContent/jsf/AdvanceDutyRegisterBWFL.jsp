
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

	<h:form>
		<f:loadBundle basename="com.mentor.nl.userMessage" var="msgBundle" />
		<f:view>
		
<style>
.table{
box-shadow: 1px 1px 15px grey;

}
.TableHead{

background-color: #689fb1;
height:20px;
color:white;
font-size: 12px;
}
.table{
box-shadow: 1px 1px 15px grey;

}



.TableFooter{background-color: white; }

</style>		
			<div class="form-group">
			<div>
			 <rich:spacer height="20px"></rich:spacer>
			</div>
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
							<h:inputHidden value="#{advanceDutyRegisterAction.hidden }" />
							<h:outputText value=" Advance Duty Register"
								style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;" />
						</h1>
					</div>
				</div>
				<rich:separator lineType="dashed" />
				<rich:spacer height="10px" />
				<div class="row " align="center">

					<h:outputLabel value="#{advanceDutyRegisterAction.name}"
						style="COLOR: #000000; FONT-SIZE: x-large;" />
				</div>
				<div class="row " align="center">
					<h:outputLabel value="#{advanceDutyRegisterAction.address}"
						style="COLOR: #000000; FONT-SIZE: medium;" />

				</div>
				<rich:spacer height="10px" />
				<rich:separator lineType="dashed" />





				<div class="row " align="center">
					<rich:spacer height="15px"></rich:spacer>
				</div>



				<div class=" row col-md-12">
					<rich:spacer height="20px" />
				</div>

				<div class="row col-md-12" align="center"
					style="FONT-STYLE: italic; FONT-WEIGHT: bold;">
					Between Dates :
					<rich:calendar value="#{advanceDutyRegisterAction.fromdate}"
						disabled="true"></rich:calendar>
					and :
					<rich:calendar value="#{advanceDutyRegisterAction.todate}"></rich:calendar>
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
							value="#{advanceDutyRegisterAction.type}">
							<f:selectItems value="#{advanceDutyRegisterAction.typeList}" />
						</h:selectOneMenu>
					</div>


					<div class="col-md-1 "></div>
					<div class="col-md-3 "></div>


				</div>

				<div class="row" align="left">
					<rich:spacer height="10px"></rich:spacer>
				</div>


				<div class="col-md-12" align="center">

					<h:commandButton action="#{advanceDutyRegisterAction.getdata}"
						value="View Register" styleClass="btn btn-info btn-sm" />

					<rich:spacer width="20px;" />

					<h:commandButton action="#{advanceDutyRegisterAction.print}"
						value="Print Report" styleClass="btn btn-info btn-sm" />

					<h:outputLink styleClass="outputLinkEx"
						value="/doc/ExciseUp/WholeSale/pdf//#{advanceDutyRegisterAction.pdfname}"
						target="_blank">
						<h:outputText styleClass="outputText" id="text223"
							value="View Report"
							rendered="#{advanceDutyRegisterAction.printFlag==true}"
							style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
					</h:outputLink>

					<rich:spacer width="20px;" />

					<h:commandButton action="#{advanceDutyRegisterAction.reset}"
						value="Reset" styleClass="btn btn-default btn-sm" />

				</div>



				<div class="row" align="left">
					<rich:spacer height="30px"></rich:spacer>
				</div>

				<div class="col-md-12" align="center">

					<rich:dataTable id="tableunit" rows="60" width="100%" var="list"
						value="#{advanceDutyRegisterAction.showData}" rendered="false"
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
						value="#{advanceDutyRegisterAction.showData}"
						headerClass="TableHead" footerClass="TableFooter"
						rowClasses="TableRow1,TableRow2" styleClass="table table-hover">


						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.srNo}">

							</h:outputText>
						</rich:column>


						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Date" styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.date_Dt}"></h:outputText>
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



						<rich:column align="right">
							<f:facet name="header">
								<h:outputText value="Challan Amount"
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.produceBal}" >

								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />
							</h:outputText>
						</rich:column>


						<rich:column align="right">
							<f:facet name="header">
								<h:outputText value="Disptch Duty Amount"
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.disptchBal}" >
								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />

							</h:outputText>
						</rich:column>




						<rich:column align="right">
							<f:facet name="header">
								<h:outputText value="Balance"
									styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.balanceTot}" >

								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />

							</h:outputText>
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