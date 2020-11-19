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
					<h:outputText
						value="RollOver Of Non-Renewed Brand Stock For FL2 /  FL2B"
						style="font-weight: bold;font-size: 35px;font-family: cursive sans MS; ">
					</h:outputText>
					<h:inputHidden value="#{rollOverOfNonRenewalBrandAction.hidden}">

					</h:inputHidden>
				</div>
			</div>

			<hr style="border-top: 6px #D0D3D4; border-top-style: dashed;" />

			<h:panelGroup
				rendered="#{rollOverOfNonRenewalBrandAction.viewflg eq 'F'}">
				<div>
					<rich:dataTable columnClasses="columnClass1" rows="10"
						headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2" styleClass="DataTable"
						id="table1" width="100%"
						value="#{rollOverOfNonRenewalBrandAction.displaylist}" var="list">
						<rich:column>
							<f:facet name="header">
								<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText value="#{list.slno}" />
							</center>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Brand"
									styleClass="generalHeaderOutputTable" />
							</f:facet>
							<h:outputText value="#{list.brand}">
							</h:outputText>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Size" styleClass="generalHeaderOutputTable" />
							</f:facet>
							<h:outputText value="#{list.size}">
							</h:outputText>
						</rich:column>




						<rich:column>
							<f:facet name="header">
								<h:outputText value="Stock Box"
									styleClass="generalHeaderOutputTable" />
							</f:facet>
							<h:outputText value="#{list.stockbottle}">
							</h:outputText>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Rollover Boxes "
									styleClass="generalHeaderOutputTable" />
							</f:facet>
							<h:inputText value="#{list.rolloverbox}"
								disabled="#{list.finl_flg}">


							</h:inputText>
						</rich:column>


						<rich:column>
							<f:facet name="header">
								<h:outputText value="Enter New Etin"
									styleClass="generalHeaderOutputTable" />
							</f:facet>
							
							<h:selectOneMenu value="#{list.etin_new}">
							<f:selectItems value="#{list.list }"/>
							</h:selectOneMenu>
							
							
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Upload and Scan"
									styleClass="generalHeaderOutputTable" />
							</f:facet>

							<center>

								<h:commandButton value="Scan And Upload"
									rendered="#{list.btflag and !list.com_flg and !list.finl_flg}"
									action="#{rollOverOfNonRenewalBrandAction.upload}"
									styleClass="btn btn-sm btn-success">
									<f:setPropertyActionListener value="#{list}"
										target="#{rollOverOfNonRenewalBrandAction.prt}" />
								</h:commandButton>
								<h:commandButton value="Freeze And Send To DEO"
									rendered="#{list.finl_flg and !list.com_flg}"
									action="#{rollOverOfNonRenewalBrandAction.freez}"
									styleClass="btn btn-sm btn-success"
									onclick="return confirm('ALERT : Stock once Freezed cannot be reverted.Do you wish to continue?');">
									<f:setPropertyActionListener value="#{list}"
										target="#{rollOverOfNonRenewalBrandAction.prt}" />
								</h:commandButton>


								<h:outputText value="Finalised"
									rendered="#{list.finl_flg and list.com_flg}"
									style="FONT-WEIGHT: bold; color: Green;" align="left"></h:outputText>
							</center>

						</rich:column>


						<f:facet name="footer">
							<rich:datascroller for="table1">
							</rich:datascroller>
						</f:facet>
					</rich:dataTable>

				</div>
			</h:panelGroup>
			<h:panelGroup
				rendered="#{rollOverOfNonRenewalBrandAction.viewflg eq 'T'}">


				<div class="col-md-12" align="center">

					<div class="col-md-3" align="right">
						<h:outputText value="Brand Name::  "
							style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
					</div>
					<div class="col-md-9" align="left">
						<h:inputText value="#{rollOverOfNonRenewalBrandAction.brand_nm}"
							style="COLOR: #0000ff;" readonly="true" styleClass="form-control"></h:inputText>

					</div>
				</div>



				<div class="col-md-12" align="center">

					<div class="col-md-3" align="right">
						<h:outputText value="ETN::"
							style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
					</div>
					<div class="col-md-3" align="left">
						<h:inputText value="#{rollOverOfNonRenewalBrandAction.etn}"
							style="COLOR: #0000ff;" readonly="true" styleClass="form-control"></h:inputText>

					</div>
					<div class="col-md-3" align="right">
						<h:outputText value="RollOver Box::"
							style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
					</div>
					<div class="col-md-3" align="left">
						<h:inputText
							value="#{rollOverOfNonRenewalBrandAction.rollerboxses}"
							style="COLOR: #0000ff;" readonly="true" styleClass="form-control"></h:inputText>

					</div>
				</div>
<div class="row">
<div class="col-md-12" align="center">

					<div class="col-md-3" align="right">
						<h:outputText value="New Etin::"
							style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
					</div>
					<div class="col-md-3" align="left">
						<h:inputText value="#{rollOverOfNonRenewalBrandAction.new_etin}"
							style="COLOR: #0000ff;" readonly="true" styleClass="form-control"></h:inputText>

					</div>
					</div>
<div class="col-md-3" align="right">
						<h:outputText value="New Brand Name::  "
							style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
					</div>
					<div class="col-md-9" align="left">
						<h:inputText value="#{rollOverOfNonRenewalBrandAction.new_brand_name}"
							style="COLOR: #0000ff;" readonly="true" styleClass="form-control"></h:inputText>

					</div>
</div>

				<div class="col-md-12" align="center">
					<div class="col-md-2" align="right">
						<h:outputText value="Upload CSV:: "
							style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
					</div>
					<div class="col-md-4" align="left">
						<rich:fileUpload addControlLabel="Add File" id="link34"
							fileUploadListener="#{rollOverOfNonRenewalBrandAction.uploadCsv}"
							maxFilesQuantity="1" listHeight="40" listWidth="250"
							clearControlLabel="clear" stopControlLabel="Stop"
							ontyperejected="if (!confirm(' ONLY .csv type file ALLOWED !!!')) return false"
							acceptedTypes="csv" clearAllControlLabel="Clear">
						</rich:fileUpload>
					</div>

					<div class="col-md-4" align="right">
						<h:commandButton value="Upload CSV" styleClass="btn btn-primary"
							action="#{rollOverOfNonRenewalBrandAction.csvSubmit}">
						</h:commandButton>

					</div>
				</div>
				<div>
					<rich:spacer height="20px" />
				</div>


				<div class="row">
					<rich:spacer height="20px">
					</rich:spacer>
				</div>

				<div>
					<rich:dataTable columnClasses="columnClass1" rows="10"
						headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2" styleClass="DataTable"
						id="table30" width="100%"
						value="#{rollOverOfNonRenewalBrandAction.csvdetail}" var="list">

						<rich:column>
							<f:facet name="header">
								<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
							</f:facet>
							<center>
								<h:outputText value="#{list.slno_csv}" />
							</center>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="ETIN" styleClass="generalHeaderOutputTable" />
							</f:facet>
							<h:outputText value="#{list.etin_csv}">
							</h:outputText>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="CASECODE"
									styleClass="generalHeaderOutputTable" />
							</f:facet>
							<h:outputText value="#{list.casecose_csv}">
							</h:outputText>
						</rich:column>

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Status"
									styleClass="generalHeaderOutputTable" />
							</f:facet>
							<h:outputText value="#{list.validInvalid}">
							</h:outputText>
						</rich:column>









						<f:facet name="footer">
							<rich:datascroller for="table30"></rich:datascroller>
						</f:facet>
					</rich:dataTable>
					<div align="center">
						<h:commandButton action="#{rollOverOfNonRenewalBrandAction.reset}"
							styleClass="btn btn-sm btn-default " value="Close Uploading">
						</h:commandButton>
						<rich:spacer width="20px" />
						<h:commandButton action="#{rollOverOfNonRenewalBrandAction.save}"
							disabled="#{rollOverOfNonRenewalBrandAction.save_disable}"
							styleClass="btn btn-sm btn-primary " value="Save and Finalize">
						</h:commandButton>
						<rich:spacer width="20px" />
						<h:commandButton
							action="#{rollOverOfNonRenewalBrandAction.delete}"
							styleClass="btn btn-sm btn-danger " value="Reset Uploaded Data">
						</h:commandButton>
					</div>

				</div>
			</h:panelGroup>





		</h:form>
	</f:view>
</ui:composition>
