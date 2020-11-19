<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<h:form>
			<div>
			<rich:spacer height="30px;"></rich:spacer>
				<div class="row">
					<div class="col-md-12 wow shake" align="center">
						<h:messages errorStyle="color:red" layout="TABLE" id="messages1"
							infoStyle="color:green"
							style="FONT-SIZE: large; background-color:#e1fcdf;">
						</h:messages>
					</div>
				</div>
				<div align="center">
					<h:inputHidden
						value="#{importChallanForPermitFL2D_20_21Action.hidden}"></h:inputHidden>
					<rich:separator lineType="dashed"></rich:separator>
					<h1 style="COLOR: #0000ff;">FL2D Permit Challan Mapping</h1>
					<rich:separator lineType="dashed"></rich:separator>
				</div>
				<rich:spacer height="20px;"></rich:spacer>

				<div align="center">
					<rich:dataTable id="table" style="background: #0000"  
						width="90%" var="list"
						value="#{importChallanForPermitFL2D_20_21Action.datalist}"
						headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Application No."
									styleClass="generalHeaderOutputTable"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.app_id }" />

						</rich:column>

						<rich:column align="right">
							<f:facet name="header">
								<h:outputText value="Import Fee"
									styleClass="generalHeaderOutputTable"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.import_fee }" >
							<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" />
							</h:outputText>
						</rich:column>


						<rich:column align="right">
							<f:facet name="header">
								<h:outputText value="Special Fee"
									styleClass="generalHeaderOutputTable"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.special_fee }" >
							<f:convertNumber maxFractionDigits="2"
																	pattern="#############0.00" /></h:outputText>

						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Select"
									styleClass="generalHeaderOutputTable"
									style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							</f:facet>
							<h:selectBooleanCheckbox value="#{list.checkbox}"/>
						</rich:column>
						<f:facet name="footer">
							<rich:datascroller for="table"></rich:datascroller>
						</f:facet>

					</rich:dataTable>
				</div>
				<rich:spacer height="20px;" />
				<div class="row">
<div class="col-sm-1" ></div>
					
					
					
					
					<div class="row">
						<div class="col-md-12">

							<div class="col-md-6" align="center">
								<h:outputText value=" Map Challan For Import Fee :"
									style="FONT-WEIGHT: bold;"></h:outputText>


								<rich:dataTable columnClasses="columnClass1"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2" styleClass="DataTable"
									id="table3" rows="10" width="95%"
									value="#{importChallanForPermitFL2D_20_21Action.importChallanList}" var="list">
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Sr.No">
											</h:outputText>
										</f:facet>
										<center>
											<h:outputText value="#{list.srNo}"
												styleClass="generalHeaderStyleOutput">
											</h:outputText>
										</center>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Map Challan For Import Fee :">
											</h:outputText>
										</f:facet>
																ABK<h:inputText value="#{list.importfeeChallan}"
											style="width:200px">

										</h:inputText>
									</rich:column>








									<rich:column>
										<f:facet name="header">
											<h:commandButton class="imag"
												action="#{importChallanForPermitFL2D_20_21Action.addRowMethodPackaging}"
												image="/img/add.png"
												disabled="#{list.show_Edit_addRow_Flag}" />
										</f:facet>

										<center>
											<h:commandButton class="imag"
												actionListener="#{importChallanForPermitFL2D_20_21Action.deleteRowMethodPackaging}"
												style="background: transparent;height:30px "
												image="/img/del.png" />
										</center>
									</rich:column>



									<f:facet name="footer">
										<rich:datascroller for="table3"></rich:datascroller>
									</f:facet>
								</rich:dataTable>
							</div>
							<div class="col-md-6" align="center">
								<h:outputText value=" Map Challan For Special Fee :"
									style="FONT-WEIGHT: bold;"></h:outputText>

								<rich:dataTable columnClasses="columnClass1"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2" styleClass="DataTable"
									id="table4" rows="10" width="95%"
									value="#{importChallanForPermitFL2D_20_21Action.spclChallanList}" var="list">
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Sr.No">
											</h:outputText>
										</f:facet>
										<center>
											<h:outputText value="#{list.srNo1}"
												styleClass="generalHeaderStyleOutput">
											</h:outputText>
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Map Challan For Special Fee :">
											</h:outputText>
										</f:facet>
																
																ABK<h:inputText value="#{list.spclfeeChallan}"
											style="width:200px">

										</h:inputText>

									</rich:column>








									<rich:column>
										<f:facet name="header">
											<h:commandButton class="imag"
												action="#{importChallanForPermitFL2D_20_21Action.addRowMethodPackaging1}"
												image="/img/add.png" />
										</f:facet>
										<center>
											<h:commandButton class="imag"
												actionListener="#{importChallanForPermitFL2D_20_21Action.deleteRowMethodPackaging1}"
												style="background: transparent;height:30px "
												image="/img/del.png" />
										</center>
									</rich:column>



									<f:facet name="footer">
										<rich:datascroller for="table4"></rich:datascroller>
									</f:facet>
								</rich:dataTable>
							</div>
						</div>


					</div>
					<div class="row" align="center">
						<rich:spacer height="30px"></rich:spacer>
					</div>
					<div class="col-sm-1"  ></div>
				</div>
				<rich:spacer height="20px;"></rich:spacer>
				
				
				<div class="row" align="center">
				<h:commandButton value="Back" styleClass="btn btn-success"
						action="#{importChallanForPermitFL2D_20_21Action.navigationBack}"
						style="color:#FFFFFF; background-color: #253f8a;">
					</h:commandButton>
				
					<h:commandButton value="Save" class="btn btn-info"
						action="#{importChallanForPermitFL2D_20_21Action.save}"
						style="color:#FFFFFF; background-color: #253f8a;">
					</h:commandButton>
				</div>
				<rich:spacer height="30px;"></rich:spacer>
			</div>
		</h:form>
	</f:view>
</ui:composition>