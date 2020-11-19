<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<h:form style="background-color:whitesmoke;">

			<div class="form-group">
				<div class="row">
					<a4j:outputPanel id="msg">
						<div class="row col-md-12 wow shake" style="margin-top: 10px;">
							<h:messages errorStyle="color:red" style="font-size: 18px;"
								styleClass="generalExciseStyle" layout="table"
								infoStyle="color:green" />
						</div>
					</a4j:outputPanel>
				</div>
				<div class="row " align="center">
					<rich:spacer height="10px"></rich:spacer>
				</div>
				<div class="row">
					<div align="center">
						<h:outputText value="Godown Breakage Stock For Wholesale 2020-21"
							style="COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: 35px;font-family:Comic Sans MS;"></h:outputText>
						<h:inputHidden value="#{wholesaleBreakageStockAction.hidden}"></h:inputHidden>
					</div>
				</div>

				<hr style="border-top: 7px #D0D3D4; border-top-style: dashed;"></hr>


				<div class="row">
					<div align="center">
						<h:outputLabel value="#{wholesaleBreakageStockAction.loginUserNm}"
							style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel>
					</div>
				</div>
				<div class="row " align="center">
					<rich:spacer height="10px"></rich:spacer>
				</div>
				<div class="row">
					<div align="center">
						<h:outputLabel
							value="#{wholesaleBreakageStockAction.loginUserAdrs}"
							style="COLOR: #000000; FONT-SIZE: medium;"></h:outputLabel>
					</div>
				</div>

				<hr style="border-top: 7px #D0D3D4; border-top-style: dashed;"></hr>

				<div class="row " align="center">
					<rich:spacer height="20px"></rich:spacer>
				</div>

				<div class="row" align="center">
					<div style="overflow-x: scroll; width: 100%; height: 400px;">
						<rich:dataTable columnClasses="columnClass1"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2" styleClass="DataTable"
							id="table3" width="100%"
							value="#{wholesaleBreakageStockAction.stockList}" var="list">

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.srNo}" />
								</center>
							</rich:column>

							<rich:column sortBy="#{list.brandName_dt}"
								filterBy="#{list.brandName_dt}">
								<f:facet name="header">
									<h:outputText value="Brand"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:outputText value="#{list.brandName_dt}">
								</h:outputText>
							</rich:column>

							<rich:column sortBy="#{list.pckgName_dt}"
								filterBy="#{list.pckgName_dt}">
								<f:facet name="header">
									<h:outputText value="Packaging"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:outputText value="#{list.pckgName_dt}">
								</h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Size"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:outputText value="#{list.boxSize_dt}">
								</h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Available Bottle"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:outputText value="#{list.avlBottle_dt}">
								</h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Breakage"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:inputText value="#{list.breakage_dt}">
									<a4j:support reRender="stock_btl" event="onblur"></a4j:support>
								</h:inputText>
							</rich:column>




							<rich:column>
								<f:facet name="header">
									<h:outputText value="Stock Bottles"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<a4j:outputPanel id="stock_btl">
									<h:inputText value="#{list.stockBottle_dt}" disabled="true">
									</h:inputText>
								</a4j:outputPanel>

							</rich:column>

						</rich:dataTable>
					</div>
				</div>

				<div class="row " align="center">
					<rich:spacer height="20px"></rich:spacer>
				</div>

				<br />
				<div class="row" align="center">

					<h:commandButton value="Save" styleClass="btn btn-primary"
						action="#{wholesaleBreakageStockAction.saveMethod}">
					</h:commandButton>



					<rich:spacer width="10px;"></rich:spacer>
					<h:commandButton value="Reset" styleClass="btn btn-warning"
						action="#{wholesaleBreakageStockAction.reset}">
					</h:commandButton>
				</div>
				<div class="row" align="center">
					<rich:spacer height="20px"></rich:spacer>
				</div>
				<div class="row" align="center">
					<div class="col-md-12" align="center">
						<rich:dataTable var="list" rows="10"
							value="#{wholesaleBreakageStockAction.showDataList}"
							width="100%" headerClass="TableHead" footerClass="TableHead"
							id="show" rowClasses="TableRow1,TableRow2" styleClass="DataTable">

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Sr.No" />
								</f:facet>
								<h:outputText value="#{list.srNo }" />
							</rich:column>

							<rich:column sortBy="#{list.plan_date}">
								<f:facet name="header">
									<h:outputText value="Created Date" />
								</f:facet>
								<h:outputText value="#{list.show_crDate}">
									<f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+5:30" />
								</h:outputText>
							</rich:column>


							<rich:column>
								<f:facet name="header">
									<h:outputText value="BrandName" />
								</f:facet>
								<h:outputText value="#{list.show_brandName}" />
							</rich:column>


							<rich:column>
								<f:facet name="header">
									<h:outputText value="Packaging" />
								</f:facet>
								<h:outputText value="#{list.show_pckgName}" />
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Box Size" />
								</f:facet>
								<h:outputText value="#{list.show_boxSize}" />
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Breakage" />
								</f:facet>
								<h:outputText value="#{list.show_breakage}" />
							</rich:column>

							<f:facet name="footer">
								<rich:datascroller for="show"></rich:datascroller>
							</f:facet>

						</rich:dataTable>
					</div>
				</div>

				<div class="row " align="center">
					<rich:spacer height="20px"></rich:spacer>
				</div>
				<br />
			</div>
		</h:form>
	</f:view>
</ui:composition>