<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">


	<h:form>
		<f:view>

			<div class="form-group">
				<div>
					<rich:spacer height="20px"></rich:spacer>

				</div>
				<div class="row">
					<a4j:outputPanel id="msg">
						<div class="row col-md-12 wow shake" style="margin-top: 10px;">
							<h:messages errorStyle="color:red"
								style="font-size: 14px;font-weight: bold"
								styleClass="generalStyle" layout="table" infoStyle="color:green">
							</h:messages>
						</div>
					</a4j:outputPanel>
				</div>

				<div class="row " align="center">
					<div align="center" width="100%" class="pghead">

						<h:inputHidden value="#{rollover_Stock_BWFL_18_19Action.hidden}"></h:inputHidden>
						<h2>
							<h:outputText value="Declaration of 2018-19 Stock"
								style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: xx-large;" />
						</h2>
					</div>
				</div>

				<div class="row">
					<rich:spacer height="20px"></rich:spacer>
				</div>
				<TABLE width="100%" align="center">
					<TR>
						<TD align="center" width="100%">
							<TABLE align="center" width="100%">
								<TBODY>

									<tr>
										<td><rich:separator lineType="dashed"></rich:separator></td>
									</tr>
									<tr>
										<TD><rich:spacer height="10px"></rich:spacer></TD>
									</tr>
									<tr>
										<TD align="center" colspan="2"><h:outputLabel
												value="#{rollover_Stock_BWFL_18_19Action.unit_name}"
												style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>

									</tr>
									<tr>
										<TD><rich:spacer height="5px"></rich:spacer></TD>
									</tr>
									<TR>

										<TD align="center" colspan="2"><h:outputLabel
												value="#{rollover_Stock_BWFL_18_19Action.unit_address}"
												style="COLOR: #000000; FONT-SIZE: medium;"></h:outputLabel></TD>

									</TR>
									<tr>
										<TD><rich:spacer height="10px"></rich:spacer></TD>
									</tr>
									<tr>
										<td><rich:separator lineType="dashed"></rich:separator></td>
									</tr>
								</TBODY>
							</TABLE>
						</TD>
					</TR>

				</TABLE>

				<div>
					<rich:spacer height="20px"></rich:spacer>

				</div>


				<div class="row">
					<div class="col-md-12" align="center">
						<rich:dataTable columnClasses="columnClass1"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2" styleClass="DataTable"
							id="table3" rows="10" width="90%"
							value="#{rollover_Stock_BWFL_18_19Action.displaylist}" var="list">
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
									<h:outputText value="Brand Name(2020-21)"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:selectOneMenu value="#{list.brandPackagingData_Brand}"
									onchange="this.form.submit();" style=" width : 279px;">
									<f:selectItems value="#{list.brandPackagingData_BrandList }" />
								</h:selectOneMenu>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Package"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:selectOneMenu value="#{list.brandPackagingData_Packaging}"
									styleClass="dropdown-menu" onchange="this.form.submit();">
									<f:selectItems
										value="#{list.brandPackagingData_PackagingList }" />


								</h:selectOneMenu>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Size"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:outputText value="#{list.brandPackagingData_Quantity}"
									styleClass="form-control">
								</h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Box" styleClass="generalHeaderOutputTable" />
								</f:facet>
								<a4j:outputPanel>
									<h:inputText value="#{list.box}" styleClass="form-control">
										<a4j:support reRender="box" event="onkeyup"></a4j:support>
									</h:inputText>
								</a4j:outputPanel>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="MRP 2018-19"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<a4j:outputPanel>
									<h:inputText value="#{list.mrp}" styleClass="form-control">
										<a4j:support reRender="mrp" event="onkeyup"></a4j:support>
										<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
									</h:inputText>
								</a4j:outputPanel>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Rollover fees"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<a4j:outputPanel>
									<h:inputText value="#{list.rollover_fee}"
										styleClass="form-control">
										<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
										<a4j:support reRender="rlovfee" event="onkeyup"></a4j:support>
									</h:inputText>
								</a4j:outputPanel>
							</rich:column>

							
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Cowcess fees"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<a4j:outputPanel>
									<h:inputText value="#{list.cowcess}"
										styleClass="form-control">
										<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
										<a4j:support reRender="cowcessfee" event="onkeyup"></a4j:support>
									</h:inputText>
								</a4j:outputPanel>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:commandButton class="img"
										action="#{rollover_Stock_BWFL_18_19Action.addRowMethod}"
										value=" ADD" image="/img/add.png" style=" width : 43px;" />
								</f:facet>
								<h:commandButton class="img"
									actionListener="#{rollover_Stock_BWFL_18_19Action.deleteRowMethod}"
									style="background: transparent;height:30px ; width : 36px;"
									image="/img/del.png" />
							</rich:column>





							<f:facet name="footer">
								<rich:datascroller for="table3"></rich:datascroller>
							</f:facet>
						</rich:dataTable>
						<div>
					<rich:spacer height="10px"></rich:spacer>

				</div>
						<div class="row">
							<div class="col-md-12" align="center">
								<div class="col-md-1" align="right">
									<h:outputText value="Total Box : "
										style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
								</div>
								<div class="col-md-2" align="left">

									<h:outputText disabled="true"
										value="#{rollover_Stock_BWFL_18_19Action.totalbox}" id="box"
										styleClass="generalHeaderOutputTable">
										<f:convertNumber maxFractionDigits="2"
											pattern="#############0" />
									</h:outputText>
								</div>
								<div class="col-md-1" align="right">
									<h:outputText value="Total MRP : "
										style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
								</div>
								<div class="col-md-2" align="left">

									<h:outputText disabled="true"
										value="#{rollover_Stock_BWFL_18_19Action.totalmrp}" id="mrp"
										styleClass="generalHeaderOutputTable">
										<f:convertNumber maxFractionDigits="2"
											pattern="#############0.00" />
									</h:outputText>
								</div>
								<div class="col-md-1" align="right">
									<h:outputText value="Total Rollover fees : "
										style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
								</div>
								<div class="col-md-2" align="left">

									<h:outputText disabled="true"
										value="#{rollover_Stock_BWFL_18_19Action.totalrolloverfees}"
										id="rlovfee" styleClass="generalHeaderOutputTable">
										<f:convertNumber maxFractionDigits="2"
											pattern="#############0.00" />
									</h:outputText>
								</div>
								<div class="col-md-1" align="right">
									<h:outputText value="Total Cowcess : "
										style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
								</div>
								<div class="col-md-2" align="left">

									<h:outputText disabled="true"
										value="#{rollover_Stock_BWFL_18_19Action.totalcowcess}"
										id="cowcessfee" styleClass="generalHeaderOutputTable">
										<f:convertNumber maxFractionDigits="4"
											pattern="#############0.0000" />
									</h:outputText>
								</div>
							</div>
						</div>






					</div>

				</div>

				<div>
					<rich:spacer height="20px"></rich:spacer>

				</div>
				<div class="panel-footer clearfix" align="center">
					<h:commandButton
					
						styleClass="btn btn-primary"
						action="#{rollover_Stock_BWFL_18_19Action.saveMethod}" value="Save">
					</h:commandButton>
					<rich:spacer width="10px"></rich:spacer>
					<h:commandButton styleClass="btn btn-warning"
						action="#{rollover_Stock_BWFL_18_19Action.reset}" value="Reset"></h:commandButton>
				</div>

				<div>
					<rich:spacer height="20px"></rich:spacer>

				</div>

				<div class="row">
					<div class="col-md-12" align="center">
						<rich:dataTable columnClasses="columnClass1"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2" styleClass="DataTable"
							id="table4" rows="10" width="100%"
							value="#{rollover_Stock_BWFL_18_19Action.displaylist1}" var="list">
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Sr.No">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.s_no}"
									styleClass="generalHeaderStyleOutput">
								</h:outputText>
							</rich:column>
							<rich:column width="350px">
								<f:facet name="header">
									<h:outputText value="Brand Name(2020-21)"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:outputText value="#{list.brand_name}" >
								</h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Package"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:outputText value="#{list.packg}" >
								</h:outputText>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Size"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:outputText value="#{list.size}"  >
								</h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Box" styleClass="generalHeaderOutputTable" />
								</f:facet>

								<h:outputText value="#{list.s_box}"  >

								</h:outputText>

							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="MRP 2018-19"
										styleClass="generalHeaderOutputTable" />
								</f:facet>

								<h:outputText value="#{list.s_mrp}"  >
<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
								</h:outputText>

							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Rollover fees"
										styleClass="generalHeaderOutputTable" />
								</f:facet>

								<h:outputText value="#{list.s_rollover_fee}" >
<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
								</h:outputText>

							</rich:column>

							
							<rich:column width="200px">
								<f:facet name="header">
									<h:outputText value="Cowcess fees"
										styleClass="generalHeaderOutputTable" />
								</f:facet>

								<h:outputText value="#{list.s_cowcess}" >
<f:convertNumber maxFractionDigits="2"
										pattern="#############0.00" />
								</h:outputText>

							</rich:column>
							
							
							<rich:column width="200px">
								<f:facet name="header">
									<h:outputText value=""
										styleClass="generalHeaderOutputTable" />
								</f:facet>
<center>
								<h:commandButton styleClass="btn btn-primary"
						rendered="#{list.buttn_flg ne 'T' }"
						onclick="return confirm('ALERT : These Brands will be send to DEO. Do you wish to continue?');"
						action="#{rollover_Stock_BWFL_18_19Action.finaliz}"
						value="Finalize and send to DEO">
						<f:setPropertyActionListener value="#{list}"
										target="#{rollover_Stock_BWFL_18_19Action.prt}" />
					</h:commandButton>
					
					
					
					
					<h:outputText value="Finalised "
								rendered="#{list.buttn_flg eq 'T'}"
							style="FONT-WEIGHT: bold; font-size: 15px ;color:green "></h:outputText>
							
							
							<h:commandButton action="#{rollover_Stock_BWFL_18_19Action.finalizeData}" value="Finalize Data"  styleClass="btn btn-sm btn-danger" 
disabled="#{list.excel_flg_dis eq 'T'}"  rendered="#{list.excel_flg eq 'T'}"
									onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;">
								<f:setPropertyActionListener value="#{list}" target="#{rollover_Stock_BWFL_18_19Action.prt }" />
								</h:commandButton>                                                            
								<h:outputLink target="_blank" value="/doc/ExciseUp/RolloverStock/Excel/#{list.gtin_no}#{list.finalizedDateString}#{list.planid}.xls">
									<h:outputText value="Download Excel" rendered="#{list.excel_flg_dis eq 'T'}"/>
									
									</h:outputLink>
</center>
							</rich:column>







							<f:facet name="footer">
								<rich:datascroller for="table4"></rich:datascroller>
							</f:facet>
						</rich:dataTable>
					</div>
				</div>

				<div>
					<rich:spacer height="20px"></rich:spacer>

				</div>
				
				

				<div>
					<rich:spacer height="20px"></rich:spacer>

				</div>







			</div>

		</f:view>

	</h:form>
</ui:composition>