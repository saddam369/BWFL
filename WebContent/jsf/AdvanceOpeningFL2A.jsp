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
hr.style-one {
	border: 0;
	height: 1px;
	background: #333;
	background-image: -webkit-linear-gradient(left, #ccc, #333, #ccc);
	background-image: -moz-linear-gradient(left, #ccc, #333, #ccc);
	background-image: -ms-linear-gradient(left, #ccc, #333, #ccc);
	background-image: -o-linear-gradient(left, #ccc, #333, #ccc);
}
</style>


			</head>
			<div class="row">
				<rich:spacer height="20"></rich:spacer>
			</div>
			<div class="row">
				<div class="col-md-12 wow shake" align="center">
					<h:messages errorStyle="color:red" layout="TABLE" id="messages1"
						infoStyle="color:green"
						style="FONT-SIZE: large; background-color:#e1fcdf;">
					</h:messages>
				</div>
			</div>


			<hr class="style-one" />
			<rich:separator lineType="beveled"></rich:separator>
			<div class="row" align="center">
				<div class="col-md-12">
					<h:outputText value="Advance Import Fee For Unit"
						style="FONT-FAMILY: 'Corbel Light'; FONT-WEIGHT: bold; COLOR: #0000a0; FONT-SIZE: 35px;">
					</h:outputText>
				</div>
			</div>
			<div>
				<rich:separator lineType="beveled"></rich:separator>
				<hr class="style-one" />


			</div>
			<div class="row">
				<rich:spacer height="10"></rich:spacer>
			</div>

			<div class="row" align="center">

				<div class="col-md-12 row">
				<div class="col-md-2" align="right">
									<h:outputText value="Type : "
										style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
								</div>
								<div class="col-md-2" align="left">
									<h:selectOneRadio
										value="#{advanceOpeningFL2AAction.radio}"
										valueChangeListener="#{advanceOpeningFL2AAction.radioVal}"
										onchange="this.form.submit();">
							

										<f:selectItem itemValue="DEPO" itemLabel=" DEPO " />
										<f:selectItem itemValue="UNIT" itemLabel=" UNIT " />
										
									</h:selectOneRadio>
								</div>
					<div class="col-md-2" align="right">
						<h:outputText value="Select Unit::"
							style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
					</div>
					<div class="col-md-6" align="left">

							<h:selectOneMenu onchange="this.form.submit();" 
									styleClass="form-control" style="COLOR: #0000ff;"
									value="#{advanceOpeningFL2AAction.unit_id}"
						>
							<f:selectItems value="#{advanceOpeningFL2AAction.unit_list}" />

						</h:selectOneMenu>
					</div>

					
				</div>




			</div>
			<div class="row">
				<rich:spacer height="20"></rich:spacer>
			</div>
			<div class="col-md-12 row">
			
				<div class="col-md-2" align="right">
					<h:outputText value="Fee Type :"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-2" align="left">
					<h:selectOneMenu styleClass="dropdown-menu" style="COLOR: #0000ff;" 
					    rendered="#{advanceOpeningFL2AAction.radio ne 'UNIT'}"
						value="#{advanceOpeningFL2AAction.unit_type}">
						<f:selectItem itemValue="0" itemLabel="--Select--"></f:selectItem>
						<f:selectItem itemValue="IMPORT FEE"  itemLabel="IMPORT FEE"></f:selectItem>
						
					</h:selectOneMenu>
				</div>
				<div class="col-md-2" align="left">
					<h:selectOneMenu styleClass="dropdown-menu" style="COLOR: #0000ff;" 
					     rendered="#{advanceOpeningFL2AAction.radio ne 'DEPO'}"
						value="#{advanceOpeningFL2AAction.unit_type}">
						<f:selectItem itemValue="EXCISE DUTY" itemLabel="EXCISE DUTY"></f:selectItem>
						<f:selectItem itemValue="SPECIAL FEE" itemLabel="SPECIAL FEE"></f:selectItem>
						<f:selectItem itemValue="SCANING FEE" itemLabel="SCANING FEE"></f:selectItem>
						<f:selectItem itemValue="SPECIAL CONSIDERATION FEE" itemLabel="SPECIAL CONSIDERATION FEE"></f:selectItem>

					</h:selectOneMenu>
				</div>
				

				
			</div>
			
				<div class="row">
				<rich:spacer height="20"></rich:spacer>
			</div>
            	<div class="col-md-12 row">
            	<div class="col-md-2" align="right">
					<h:outputText value="Amount ::"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-2" align="left">
					<h:inputText value="#{advanceOpeningFL2AAction.amount}"
						styleClass="form-control"></h:inputText>
				</div>
				<div class="col-md-2" align="right">
						<h:outputText value="Opening Date::"
							style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
					</div>
					<div class="col-md-2" align="left">

						<h:inputText value="#{advanceOpeningFL2AAction.open_date}"
							disabled="true" styleClass="form-control"></h:inputText>
					</div>
            	</div>

			<div class="row">
				<rich:spacer height="20"></rich:spacer>
			</div>

			<div align="center">
				<h:commandButton value="Save" styleClass="btn btn-sm btn-success"
					action="#{advanceOpeningFL2AAction.save}"></h:commandButton>
				<rich:spacer width="10"></rich:spacer>
				<h:commandButton value="Reset" styleClass="btn btn-sm btn-warning"
					action="#{advanceOpeningFL2AAction.reset}"></h:commandButton>
			</div>
			<div class="row">
				<rich:spacer height="10"></rich:spacer>
			</div>
			<div class="col-md-12">
				<div class="col-md-4" align="right">
					<h:outputText value="Show Save Advance Import Detail :-"
						style="FONT-FAMILY: 'Corbel Light'; FONT-WEIGHT: bold; COLOR: #0000a0;text-decoration:underline; FONT-SIZE: 15px;">
					</h:outputText>
				</div>
				<div class="col-md-2" align="left"></div>
			</div>
			<rich:spacer height="10"></rich:spacer>
			<div align="center">
				<rich:dataTable width="90%" id="table" rows="15" var="list"
					value="#{advanceOpeningFL2AAction.getshowdata}"
					headerClass="TableHead" footerClass="TableHead"
					rowClasses="TableRow1,TableRow2">

					<rich:column align="center">
						<f:facet name="header">
							<h:outputText value="Sr.No."
								styleClass="generalHeaderOutputTable"
								style=" FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.sr_no}" readonly="true">

						</h:outputText>
					</rich:column>





					<rich:column align="center">
						<f:facet name="header">
							<h:outputText value="Unit Type"
								styleClass="generalHeaderOutputTable"
								style=" FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.unit_typ}" readonly="true"></h:outputText>
					</rich:column>
					<rich:column align="center">
						<f:facet name="header">
							<h:outputText value="Unit Name"
								styleClass="generalHeaderOutputTable"
								style=" FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.unit_nm}" readonly="true"></h:outputText>
					</rich:column>


					<rich:column align="center">
						<f:facet name="header">
							<h:outputText value="Fee Type"
								styleClass="generalHeaderOutputTable"
								style=" FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.fee_typ}">


						</h:outputText>
					</rich:column>

					<rich:column align="center">
						<f:facet name="header">
							<h:outputText value="Open Date"
								styleClass="generalHeaderOutputTable"
								style=" FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.opn_dt}">


						</h:outputText>
					</rich:column>

					<rich:column align="center">
						<f:facet name="header">
							<h:outputText value="Amount"
								styleClass="generalHeaderOutputTable"
								style=" FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.amount}">


						</h:outputText>
					</rich:column>
					<f:facet name="footer">
						<rich:datascroller for="table"></rich:datascroller>
					</f:facet>

				</rich:dataTable>

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