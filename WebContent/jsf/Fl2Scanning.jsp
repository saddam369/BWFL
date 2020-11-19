<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

	<f:view>


		<h:form>
	<rich:spacer height="15px;"/>
			<h:inputHidden value="#{fl2ScanningAction.hidden}" />
			<rich:separator lineType="dashed"></rich:separator>
			<div align="center">
				<h:outputText value="#{fl2ScanningAction.name}"
					style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;"></h:outputText>


			</div>

			<rich:separator lineType="dashed"></rich:separator>
			<TABLE width="80%">
				<TBODY>
					<TR>
						<TD align="left"><h3>
								<h:messages errorStyle="color:red" layout="table" id="messages"
									infoStyle="color:green">
								</h:messages>
							</h3></TD>
					</TR>
				</TBODY>
			</TABLE>

			<h:panelGroup rendered="#{!fl2ScanningAction.panelFlg }">
				<TABLE width="80%">
					<TBODY>
						<TR>
							<TD colspan="2" align="right"><h5>
									<h:outputText value="* Date"></h:outputText>
								</h5></TD>
							<TD colspan="2"><rich:calendar
									onchanged="this.form.submit();"
									value="#{fl2ScanningAction.dateOfBottling}" disabled="true"></rich:calendar></TD>




						</TR>
					</TBODY>
				</TABLE>




				<rich:dataTable columnClasses="columnClass1" headerClass="TableHead"
					footerClass="TableHead" rowClasses="TableRow1,TableRow2"
					styleClass="DataTable" id="table3" rows="10" width="100%"
					value="#{fl2ScanningAction.brandPackagingDataList}" var="list">
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Sr.No">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.sno}"
							styleClass="generalHeaderStyleOutput">
						</h:outputText>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Brand">
							</h:outputText>
						</f:facet>
						<h:selectOneMenu value="#{list.brandPackagingData_Brand}"
							styleClass="dropdown-menu" onchange="this.form.submit();"
							style=" width : 277px;">
							<f:selectItems value="#{list.brandPackagingData_BrandList }" />
						</h:selectOneMenu>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Packaging">
							</h:outputText>
						</f:facet>
						<h:selectOneMenu value="#{list.brandPackagingData_Packaging}"
							styleClass="dropdown-menu" onchange="this.form.submit();">
							<f:selectItems value="#{list.brandPackagingData_PackagingList }" />
						</h:selectOneMenu>
					</rich:column>


					<rich:column>
						<f:facet name="header">
							<h:outputText value="Quantity">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.brandPackagingData_Quantity}"
							styleClass="generalHeaderStyleOutput">
						</h:outputText>
					</rich:column>


					<rich:column>
						<f:facet name="header">
							<h:outputText value="No. Of Boxes">
							</h:outputText>
						</f:facet>

						<h:inputText value="#{list.brandPackagingData_NoOfBoxes}"
							styleClass="generalHeaderStyleOutput">


							<a4j:support event="onblur" reRender="box">
							</a4j:support>
						</h:inputText>



					</rich:column>


					<rich:column>
						<f:facet name="header">
							<h:outputText value="Breakage">
							</h:outputText>
						</f:facet>

						<h:inputText value="#{list.brandPackagingData_Breakage}"
							styleClass="generalHeaderStyleOutput">
							<a4j:support event="onblur" reRender="box">
							</a4j:support>
						</h:inputText>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Planned No.Of Bottles ">
							</h:outputText>
						</f:facet>
						<a4j:outputPanel id="box">
							<h:inputText value="#{list.brandPackagingData_PlanNoOfBottling}"
								styleClass="generalHeaderStyleOutput" disabled="true">



							</h:inputText>
						</a4j:outputPanel>
					</rich:column>


					<rich:column>
						<f:facet name="header">
							<h:commandButton class="imag"
								action="#{fl2ScanningAction.addRowMethodPackaging}"
								image="/img/add.png" />
						</f:facet>
						<h:commandButton class="imag"
							actionListener="#{fl2ScanningAction.deleteRowMethodPackaging}"
							style="background: transparent;height:30px " image="/img/del.png" />
					</rich:column>



					<f:facet name="footer">
						<rich:datascroller for="table3"></rich:datascroller>
					</f:facet>
				</rich:dataTable>



				<table width="100%">
					<tr>
						<td width="100%"><div class="panel-footer clearfix">
								<div align="center">

									<h:commandButton class="btn btn-primary"
										onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;"
										action="#{fl2ScanningAction.save}" value="Save"
										disabled="#{fl2ScanningAction.finalizedLast eq 'F' }" />

									<rich:spacer width="10px"></rich:spacer>
									<h:commandButton class="btn btn-default"
										action="#{fl2ScanningAction.reset}" value="Reset"></h:commandButton>
								</div>
							</div></td>

					</tr>
				</table>
				<div>
					<rich:dataTable var="list" rows="10"
						value="#{fl2ScanningAction.brandPackagingShowDataList}"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						id="show" rowClasses="TableRow1,TableRow2" styleClass="DataTable">

						<rich:column>
							<f:facet name="header">
								<h:outputText value="Sr.No" />
							</f:facet>
							<h:outputText value="#{list.srNo }" />
						</rich:column>

						<rich:column sortBy="#{list.plan_date }">
							<f:facet name="header">
								<h:outputText value="Date" />
							</f:facet>
							<h:outputText value="#{list.plan_date }">
								<f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+5:30" />
							</h:outputText>
						</rich:column>


						<rich:column sortBy="#{list.brandName }"
							filterBy="#{list.brandName}">
							<f:facet name="header">
								<h:outputText value="BrandName" />
							</f:facet>
							<h:outputText value="#{list.brandName }" />
						</rich:column>


						<rich:column sortBy="#{list.packageName}"
							filterBy="#{list.packageName}">
							<f:facet name="header">
								<h:outputText value="Packaging" />
							</f:facet>
							<h:outputText value="#{list.packageName }" />
						</rich:column>
						<rich:column>
							<f:facet name="header">
								<h:outputText value="Quantity" />
							</f:facet>
							<h:outputText value="#{list.quantity }" />
						</rich:column>
						<rich:column>
							<f:facet name="header">
								<h:outputText value="No.Of Boxes" />
							</f:facet>
							<h:inputText value="#{list.no_of_box }"
								disabled="#{fl2ScanningAction.finalizedLast eq 'F' }" />
						</rich:column>
						<rich:column>
							<f:facet name="header">
								<h:outputText value="Planned No.Of Bottles" />
							</f:facet>
							<h:inputText value="#{list.no_of_planned_bottle }"
								disabled="#{fl2ScanningAction.finalizedLast eq 'F' }" />
						</rich:column>

						<rich:column>
							<h:commandButton class="btn btn-primary"
								actionListener="#{fl2ScanningAction.update}" value="Update"
								disabled="#{fl2ScanningAction.finalizedLast eq 'F' or fl2ScanningAction.loginType ne 'CL2'}" />
						</rich:column>



						<f:facet name="footer">
							<rich:datascroller for="show"></rich:datascroller>
						</f:facet>

					</rich:dataTable>
					<div align="center " style="COLOR: #ff0000;">
						<b>***Note : After finalizing the data, you cannot make any
							changes in the opening stock. Before clicking on finalize button,
							make sure that your stock is displayed correctly in the above
							table. </b>
					</div>
					<div align="center">
						<h:commandButton value="Print Report" styleClass="btn btn-success"
							action="#{fl2ScanningAction.print}"
							rendered="#{fl2ScanningAction.loginType eq 'CL2'}">
						</h:commandButton>

						<h:outputLink styleClass="outputLinkEx"
							value="/doc/ExciseUp/MIS/pdf/#{fl2ScanningAction.pdfname}"
							target="_blank">
							<h:outputText styleClass="outputText" id="text223"
								value="View Report"
								rendered="#{fl2ScanningAction.printFlag==true}"
								style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
						</h:outputLink>
						<h:commandButton class="btn btn-danger"
							action="#{fl2ScanningAction.finalize}"
							onclick="if (! confirm('Your stock once finalised, cannot be altered. Do you consent to proceed ?')) { return false;}; return true; "
							disabled="#{fl2ScanningAction.finalizedLast eq 'F' or fl2ScanningAction.loginType ne 'CL2'}"
							value="Finalize" />
					</div>
				</div>
			</h:panelGroup>
			<h:panelGroup rendered="#{fl2ScanningAction.panelFlg}">
			<h1 align="center" style="COLOR: #ff0000; FONT-FAMILY: 'Arial Black';">Kindly go to stock adjustment Screen for Updating offline stock</h1>
				<rich:spacer height="40px;"/>
			</h:panelGroup>
		</h:form>
	</f:view>
</ui:composition>