<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">


	<f:view>
		<h:form>
			<style>


.dropdown-menu {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}

.form-control {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 200px;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}

textArea {
	border-radius: 3px;
	border-style: none;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
	height: 30px;
}

.main {
	margin: 20px;
	background-color: #f2f2f2;
	padding: 20px;
	border-radius: 10px;
	shadow: 5px #888888;
	box-shadow: 5px 4px 10px grey;
}
</style>



			<div class="row">
				<rich:spacer height="30"></rich:spacer>

			</div>

			<h:messages errorStyle="color:red" layout="table" id="messages"
				infoStyle="color:green">
			</h:messages>
			<rich:spacer height="30"></rich:spacer>
			<h:inputHidden
				value="#{requestForAccidentalCaseBWFLAction.hidden}" />
			<div align="center">
				<h:outputText value="Request For Accidental Case"
					style="TEXT-DECORATION: underline; FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;"></h:outputText>
			</div>
			<rich:spacer height="30"></rich:spacer>

			<div align="center">
				<h:outputText value="#{requestForAccidentalCaseBWFLAction.name}"
					style="FONT-SIZE: large;"></h:outputText>
			</div>
			<rich:spacer height="10"></rich:spacer>
			<div align="center">
				<h:outputText
					value="#{requestForAccidentalCaseBWFLAction.address}"
					style="FONT-SIZE: medium;"></h:outputText>
			</div>
			<rich:spacer height="30"></rich:spacer>
			<rich:separator lineType="dashed"></rich:separator>


			<rich:spacer height="30"></rich:spacer>


			<rich:spacer height="30"></rich:spacer>
			<div class="main">
				<div>
						<table   style="width: 90%;">
						<TR style="margin: 5px;">
							<TD style="text-align: right"><h:outputText
									value="Gatepass type :"></h:outputText></TD>
							<TD><h:selectOneMenu
									value="#{requestForAccidentalCaseBWFLAction.gatepass_type}"
									styleClass="dropdown-menu">
									<f:selectItem itemValue="" itemLabel="--Select--" />
									<f:selectItem itemValue="FL36" itemLabel="FL36" />
								</h:selectOneMenu></TD>
							<TD style="text-align: right"><h:outputText
									value="Gatepass No.:"></h:outputText></TD>
							<TD><h:inputText styleClass="form-control"
									value="#{requestForAccidentalCaseBWFLAction.gatepass_no}">
									

								</h:inputText></TD>
							<TD style="text-align: right"><h:outputText
									value="Gatepass Date :"></h:outputText></TD>
							<TD style="text-align: left; width:100px;"><rich:calendar
									value="#{requestForAccidentalCaseBWFLAction.gatepass_date}" ></rich:calendar>
							</TD>
						</TR>
					
						<TR style="height: 20px;"></TR>

						<TR style="margin-top: 10px;">
							<TD style="text-align: right"><h:outputText
									value="Accident Location Address :"></h:outputText></TD>
							<TD><h:inputTextarea
									value="#{requestForAccidentalCaseBWFLAction.accident_address}"></h:inputTextarea>
							</TD>
							<TD style="text-align: right"><h:outputText
									value="Accident Location District:"></h:outputText></TD>
							<TD><h:selectOneMenu
									value="#{requestForAccidentalCaseBWFLAction.accident_district_id}"
									styleClass="dropdown-menu">
									<f:selectItems
										value="#{requestForAccidentalCaseBWFLAction.accident_district_list}" />
								</h:selectOneMenu></TD>
							<TD style="text-align: right"><h:outputText
									value="Accident  Date :"></h:outputText></TD>
							<TD><rich:calendar
									value="#{requestForAccidentalCaseBWFLAction.accident_date}"></rich:calendar>
							</TD>
						</TR>
					</table>
				</div>
				<div class="row">
					<rich:spacer height="30"></rich:spacer>
				</div>
				<div align="center">
					<h:commandButton value="Save"
						action="#{requestForAccidentalCaseBWFLAction.save}"
						style="margin:0px 5px;" styleClass="btn btn-success btn-sm"></h:commandButton>
					<h:commandButton value="Reset"
						action="#{requestForAccidentalCaseBWFLAction.reset}"
						style="margin:0px 5px;" styleClass="btn btn-warning btn-sm"></h:commandButton>
				</div>

			</div>

			<rich:spacer height="10px"></rich:spacer>
			<div align="center">
				<rich:dataTable id="table22" rows="15" var="list"
					value="#{requestForAccidentalCaseBWFLAction.display_list}"
					styleClass="table table-hover" width="100%" headerClass="TableHead"
					footerClass="TableHead" rowClasses="TableRow1,TableRow2"
					style="width:95%;">

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
							<h:outputText value="Request Id "
								styleClass="generalHeaderOutputTable"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.req_id}">


						</h:outputText>
					</rich:column>
					<rich:column align="center">
						<f:facet name="header">
							<h:outputText value="Request Date "
								styleClass="generalHeaderOutputTable"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.req_dt}">


						</h:outputText>
					</rich:column>
					<rich:column align="center">
						<f:facet name="header">
							<h:outputText value=" Gatepass Type "
								styleClass="generalHeaderOutputTable"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.gatepass_type}">
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
							<h:outputText value=" Gatepass Date "
								styleClass="generalHeaderOutputTable"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.gatepass_date}">
						</h:outputText>
					</rich:column>
					<rich:column align="center">
						<f:facet name="header">
							<h:outputText value="Accident Location Address "
								styleClass="generalHeaderOutputTable"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.accident_address}">
						</h:outputText>
					</rich:column>
					<rich:column align="center">
						<f:facet name="header">
							<h:outputText value="Accident Location District "
								styleClass="generalHeaderOutputTable"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.accident_district_name}">
						</h:outputText>
					</rich:column>

					<rich:column align="center">
						<f:facet name="header">
							<h:outputText value="Accident Date "
								styleClass="generalHeaderOutputTable"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.accident_date}">
						</h:outputText>
					</rich:column>





					<f:facet name="footer">
						<rich:datascroller for="table22"></rich:datascroller>
					</f:facet>

				</rich:dataTable>
			</div>
			<rich:spacer height="20px"></rich:spacer>
		</h:form>
	</f:view>
</ui:composition>