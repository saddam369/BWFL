<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

	<f:view>
		<h:form>
			<div>
				<rich:spacer height="20px;"></rich:spacer>
			</div>


			<div class="row">
				<div class="col-md-12 wow shake " align="center">
					<h:messages errorStyle="color:red" layout=" TABLE" id="messages"
						style="font-size:20px;background-color: #e1fcdf ;font-weight: bold;">
					</h:messages>
				</div>
			</div>

			<div class="row">
				<div align="center">
					<h:outputText value="RollOver Stock For Non-Renewed Brand FL2D "
						style="font-weight: bold;font-size: 35px;font-family: cursive sans MS; ">
					</h:outputText>
					<h:inputHidden value="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.hidden}">

					</h:inputHidden>
				</div>
			</div>

			<hr style="border-top: 6px #D0D3D4; border-top-style: dashed;" />

			<h:panelGroup>
				<div>
				<div class="col-md-12" align="center">
							
						</div>
					<div class="row" align="center">
						<div class="col-md-12" align="center">
							<div class="col-md-6" align="right">
								<h:outputText value=" Unit Name :"></h:outputText>
							</div>
							<div class="col-md-3" align="left">
								<h:selectOneMenu 
									value="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.unitID}"
									style=" width : 350px;"
									valueChangeListener="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.unitChngListener}"
									onchange="this.form.submit()">
									<f:selectItems
										value="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.unitList}" />
								</h:selectOneMenu>
							</div>

						</div>
					</div>
					<div class="row" align="center">
						<rich:spacer height="20px"></rich:spacer>
					</div>


					<rich:dataTable columnClasses="columnClass1"  align="center"
						headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2" styleClass="DataTable"
						id="table1" width="95%"
						value="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.displaylist}"
						var="list">
						<rich:column>
							<f:facet name="header">
								<h:outputLabel value="Sr.No" styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText value="#{list.srno}" />
							</center>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Brand"
									styleClass="generalHeaderOutputTable" />
							</f:facet>
							<h:outputText value="#{list.brand_name}">
							</h:outputText>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="New Brand Name"
									styleClass="generalHeaderOutputTable" />
							</f:facet>
							<h:outputText value="#{list.new_brand_name}">
							</h:outputText>
						</rich:column>
						






						<rich:column>
							<f:facet name="header">
								<h:outputText value="Rollover Boxes Without Breakage Indent"
									styleClass="generalHeaderOutputTable" />
							</f:facet>
							<h:outputText value="#{list.rolloverbox}">


							</h:outputText>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="RollOver Bottles"
									styleClass="generalHeaderOutputTable" />
							</f:facet>


							<h:outputText value="#{list.rolloverbottles}">

							</h:outputText>


						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Old Duty"
									styleClass="generalHeaderOutputTable" />
							</f:facet>


							<h:outputText value="#{list.old_duty}">
								<f:convertNumber maxFractionDigits="2"
									pattern="#############0.00" />
							</h:outputText>


						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Old Additional Duty"
									styleClass="generalHeaderOutputTable" />
							</f:facet>

							
								<h:outputText value="#{list.old_adduty}">
									<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
								</h:outputText>
							

						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="New Duty"
									styleClass="generalHeaderOutputTable" />
							</f:facet>

							
								<h:outputText value="#{list.new_duty}">
									<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
								</h:outputText>
							

						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="New Additional Duty"
									styleClass="generalHeaderOutputTable" />
							</f:facet>

							
								<h:outputText value="#{list.new_adduty}">
									<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
								</h:outputText>
							

						</rich:column>

						<rich:column rendered="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.radio_type ne 'BWFL' }">
							<f:facet name="header">
								<h:outputText value="Differencial Duty "
									styleClass="generalHeaderOutputTable" />
							</f:facet>

							
								<h:outputText value="#{list.diff_duty}">
									<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
								</h:outputText>
							

						</rich:column>
						<rich:column>
							<f:facet name="header">
								<h:outputText value="Rollover Fees"
									styleClass="generalHeaderOutputTable" />
							</f:facet>

							
								<h:outputText value="#{list.mrp}">
									<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
								</h:outputText>
							

						</rich:column>
						<rich:column align="center" >
						<f:facet name="header">
							<h:outputText value="" styleClass="generalHeaderOutputTable"
								style="FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="Challan Submited"
									rendered="#{list.checkbox_flg eq 'T'}"
									style="FONT-WEIGHT: bold; color: Green;" align="left"></h:outputText>
						<h:selectBooleanCheckbox value="#{list.checkbox}" rendered="#{list.checkbox_flg ne 'T'}"
						disabled="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.viewflg eq 'T' }"/>
						
						<h:commandButton value="Generate Excel" action="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.generateExcel }"  rendered="#{list.checkbox_flg eq 'T'}" 
						disabled="#{list.excelFlag eq 'T' }" onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;" styleClass="btn btn-danger">
						<f:setPropertyActionListener target="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.deoDt}" value="#{list}" />
						</h:commandButton>
						<h:outputLink value="https://www.upexciseonline.in/#{list.excelPath }" target="_blank">
						<h:outputText value="Download Excel" rendered="#{list.excelFlag eq 'T' }"/>
						</h:outputLink>
						
					</rich:column>





						
					</rich:dataTable>

				</div>
				<div class="row" align="center">
				<rich:spacer height="20px"></rich:spacer>
			</div>
			<div align="right">
				<h:commandButton value="Calculate Fees"
						styleClass="btn btn-sm  btn-success" disabled="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.viewflg eq 'T' }"
						action="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.calculate_fees}">
					</h:commandButton>
					</div>
			</h:panelGroup>
			<div class="row" align="center">
				<rich:spacer height="20px"></rich:spacer>
			</div>
			<h:panelGroup rendered="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.viewflg eq 'T' }">
				<div class="col-md-12">

					<div class="col-md-2" align="right">
						<h:outputText value="Total  Rollover fees" style="FONT-WEIGHT: bold;"></h:outputText>
					</div>
					<div class="col-md-3" align="left">
						<h:inputText styleClass="inputtext" disabled="true"
							value="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.total_mrp}" >
							<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
						</h:inputText>


					</div>

					<div class="col-md-2" align="right">
						<h:outputLabel rendered="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.radio_type ne 'BWFL' }"
						value="Total Duty:" style="FONT-WEIGHT: bold;"></h:outputLabel>
					</div>

					<div class="col-md-3" align="left">
						<h:inputText styleClass="inputtext" disabled="true" rendered="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.radio_type ne 'BWFL' }"
							value="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.total_duty}">
							<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
						</h:inputText>
					</div>
					<div class="col-md-1"></div>
					<div class="col-md-1"></div>
				</div>
				<div class="row" align="center">
				<rich:spacer height="20px"></rich:spacer>
			</div>
				
				<div class="row">
					<div class="col-md-12">

						
						<div class="col-md-12" align="center">
						

							<rich:dataTable columnClasses="columnClass1"
								headerClass="TableHead" footerClass="TableHead"
								rowClasses="TableRow1,TableRow2" styleClass="DataTable"
								id="table4" rows="10" width="95%"
								value="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.challantable}" var="list">
								<rich:column>
									<f:facet name="header">
										<h:outputText value="Sr.No">
										</h:outputText>
									</f:facet>
									<center>
										<h:outputText value="#{list.srNo_challan}"
											styleClass="generalHeaderStyleOutput">
										</h:outputText>
									</center>
								</rich:column>

								<rich:column>
									<f:facet name="header">
										<h:outputText value="Challan No.">
										</h:outputText>
									</f:facet>
																
																ABK<h:inputText value="#{list.challanname}"
										style="width:200px">

									</h:inputText>

								</rich:column>
<rich:column>
									<f:facet name="header">
										<h:outputText value="Challan Date">
										</h:outputText>
									</f:facet>
									<center>
										<rich:calendar value="#{list.challan_date}">
										</rich:calendar>
									</center>
								</rich:column>







								<rich:column>
									<f:facet name="header">
										<h:commandButton class="imag"
											action="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.addRowMethod}"
											image="/img/add.png" />
									</f:facet>
									<center>
										<h:commandButton class="imag"
											actionListener="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.deleteRowMethodlabel}"
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



				<div align="center">
				<h:commandButton value="Reject" disabled="true"
						styleClass="btn btn-sm  btn-success" 
						action="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.dfinalize}">
					</h:commandButton>
					<rich:spacer width="20px" />
					<h:commandButton value="Approve"
						styleClass="btn btn-sm  btn-success" 
						action="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.finalizeData}">
					</h:commandButton>
					<rich:spacer width="20px" />
					<h:commandButton action="#{rollOverStock_fl2d_NonRenewedBrand_DEOAction.reset}"
							styleClass="btn btn-sm btn-default " value="Close">
						</h:commandButton>


				</div>
				
				</h:panelGroup>
<div class="row" align="center">
				<rich:spacer height="20px"></rich:spacer>
			</div>
			



		</h:form>
	</f:view>
</ui:composition>
