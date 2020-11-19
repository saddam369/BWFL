<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

	<f:view>


		<h:form>

			
			<rich:separator lineType="dashed"></rich:separator>
			

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


			<TABLE width="80%">
				<TBODY>


					<TR>


						<TD colspan="2" align="right"><h5>
								<h:outputText value="* Date"></h:outputText>
							</h5></TD>
						<TD colspan="2"><rich:calendar
								onchanged="this.form.submit();"
								value="#{old_Stock_EntryAction.dateOfBottling}"
								inputStyle="height:25px" datePattern="dd/MM/yyyy"
								disabled="true"></rich:calendar></TD>

				


					</TR>
					
				</TBODY>
			</TABLE>


			<div class="row col-md-12" align="center"
					style="BACKGROUND-COLOR: #dee0e2; ">
					<div class="col-md-12" align="center">
						<h:selectOneRadio style="FONT-WEIGHT: bold;  font-size: 15px; "
							value="#{old_Stock_EntryAction.radio}"
							 
							onchange="this.form.submit();" >
							 
							<f:selectItem itemValue="FL2B" itemLabel="FL2B" />
						 
													
						</h:selectOneRadio><h:selectOneMenu style="width: 150px" 
						value="#{old_Stock_EntryAction.distillery_id}" 
						 
						 onchange="this.form.submit();">

							<f:selectItems value="#{old_Stock_EntryAction.disList}"  />
							
						
							
						</h:selectOneMenu>
						
						
					</div>
					

				</div>

			<rich:dataTable columnClasses="columnClass1" headerClass="TableHead"
				footerClass="TableHead" rowClasses="TableRow1,TableRow2"
				styleClass="DataTable" id="table3" rows="10" width="100%"
				value="#{old_Stock_EntryAction.brandPackagingDataList}" var="list">
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
						styleClass="dropdown-menu" onchange="this.form.submit();" style=" width : 277px;">
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
							action="#{old_Stock_EntryAction.addRowMethodPackaging}"
							image="/img/add.png" />
					</f:facet>
					<h:commandButton class="imag"
						actionListener="#{old_Stock_EntryAction.deleteRowMethodPackaging}"
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
									action="#{old_Stock_EntryAction.save}" value="Save" />

								<rich:spacer width="10px"></rich:spacer>
								<h:commandButton class="btn btn-default"
									action="#{old_Stock_EntryAction.reset}" value="Reset"></h:commandButton>
							</div>
						</div></td>

				</tr>
			</table>
			<div>
				<rich:dataTable var="list"
					value="#{old_Stock_EntryAction.brandPackagingShowDataList}"
					width="100%" headerClass="TableHead" footerClass="TableHead"
					id="show" rowClasses="TableRow1,TableRow2" styleClass="DataTable">

					<rich:column>
						<f:facet name="header">
							<h:outputText value="S.No" />
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

					
					<rich:column sortBy="#{list.brandName }" filterBy="#{list.brandName}">
						<f:facet name="header">
							<h:outputText value="BrandName" />
						</f:facet>
						<h:outputText value="#{list.brandName }" />
					</rich:column>
					
				
					<rich:column sortBy="#{list.packageName}" filterBy="#{list.packageName}">
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
						<h:outputText value="#{list.no_of_box }" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Planned No.Of Bottles" />
						</f:facet>
						<h:outputText value="#{list.no_of_planned_bottle }" />
					</rich:column>

					<f:facet name="footer">
						<rich:datascroller for="show"></rich:datascroller>
					</f:facet>




<rich:column>
														<f:facet name="header">
															<h:outputText value="Finalize">
															</h:outputText>
														</f:facet>
														<h:commandButton
															action="#{old_Stock_EntryAction.finalizeData}"
															value="Finalize Data" styleClass="btn btn-sm btn-danger"
															disabled="#{list.finalizedFlag eq 'F'}"
															onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;">
															<f:setPropertyActionListener value="#{list}"
																target="#{old_Stock_EntryAction.flsd }" />
														</h:commandButton>
 

														<h:commandButton
															action="#{old_Stock_EntryAction.print}"
															value="Generate" styleClass="btn btn-sm btn-danger"
																disabled="#{list.finalizedFlag ne 'F'}"
															onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;">
															<f:setPropertyActionListener value="#{list}"
																target="#{old_Stock_EntryAction.lsd }" />
														</h:commandButton>
														<h:outputLink target="_blank" value="/doc/ExciseUp/Excel/#{list.plan_seq}#{list.etin}#{list.finalizedDateString}.csv">
															<h:outputText value="Download CSV" rendered="#{list.finalizedFlag eq 'F'}"/>
															
															</h:outputLink>

<h:outputLink target="_blank" value="/doc/ExciseUp/Excel/#{list.plan_seq}#{list.etin}#{list.finalizedDateString}.xls">
															<h:outputText value="Download xls" rendered="#{list.finalizedFlag eq 'F'}"/>
															
															</h:outputLink>
<h:commandButton
															action="#{old_Stock_EntryAction.transfer}"
															value="Transfer" styleClass="btn btn-sm btn-danger"
																disabled="#{list.finalizedFlag ne 'F' or list.transferFlag eq 'F'}"
															onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;">
															<f:setPropertyActionListener value="#{list}"
																target="#{old_Stock_EntryAction.tsd }" />
														</h:commandButton>
														



													</rich:column>





				</rich:dataTable>


			</div>

		</h:form>
	</f:view>
</ui:composition>