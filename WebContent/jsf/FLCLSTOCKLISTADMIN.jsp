<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

	<f:view>


		<h:form>

			
			

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
			
			
			<TABLE width="60%" align="center">
			<tr>
								<TD align="center"
									style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
										value="" style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
								</TD>

								<TD align="center"><h:selectOneRadio
										value="#{adminAction.brand_whole}"
										
										onchange="this.form.submit()">
										<f:selectItem itemValue="B" itemLabel="Brand Wise" />
										<f:selectItem itemValue="W" itemLabel="Whole Sale Wise" />

									</h:selectOneRadio></TD>
							</tr>
				<TBODY >
					<tr align="center" >
						<TD align="center"><h:outputText style="font-weight:bold;" value="Brand Name:" 
						rendered="#{ adminAction.brand_whole eq 'B'}"/></TD>
						<TD ><h:selectOneMenu value="#{adminAction.brand_name}"
								rendered="#{adminAction.brand_whole eq 'B'}"
								valueChangeListener="#{adminAction.changeMethod}"
								onchange="this.form.submit();" styleClass="dropdown-menu">
								<f:selectItems value="#{adminAction.brand_list}" />
							</h:selectOneMenu></TD>
					</tr>
				</TBODY>
				
				<TBODY >
					<tr align="center">
						<TD align="center"><h:outputText style="font-weight:bold;" value="WholeSale:" 
						rendered="#{adminAction.brand_whole eq 'W'}"/></TD>
						<TD ><h:selectOneMenu value="#{adminAction.wholeSalename}"
							rendered="#{adminAction.brand_whole eq 'W'}"
								valueChangeListener="#{adminAction.changeMethod1}"
								onchange="this.form.submit();" styleClass="dropdown-menu">
								<f:selectItems value="#{adminAction.whole_list}" />
							</h:selectOneMenu></TD>
					</tr>
				</TBODY>
			</TABLE>
			<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>

			<div>
				<rich:dataTable var="list" rendered="#{adminAction.tableflag and adminAction.brand_whole eq 'B'}"
					value="#{adminAction.brandPackagingShowDataList}" width="100%"
					headerClass="TableHead" footerClass="TableHead" id="show"
					rowClasses="TableRow1,TableRow2" styleClass="DataTable">

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Sr.No" />
						</f:facet>
						<h:outputText value="#{list.srNo }" />
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Brand_Name" />
						</f:facet>
						<h:outputText value="#{list.brandName }" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Applicant_name" />
						</f:facet>
						<h:outputText value="#{list.applicant_name}" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Package Name" />
						</f:facet>
						<h:outputText value="#{list.pack_name }" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Opening" />
						</f:facet>
						<h:outputText value="#{list.recive_bottel }" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Receiving" />
						</f:facet>
						<h:outputText value="#{list.recive_bottel2 }" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Dispatch" />
						</f:facet>
						<h:outputText value="#{list.dis_bottel }" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Balance" style="color: blue; font-weight:bold;"/>
						</f:facet>
						<h:outputText value="#{list.balance }" />
					</rich:column>
					<f:facet name="footer">
						<rich:datascroller for="show"></rich:datascroller>
						<rich:columnGroup>
                <rich:column></rich:column>
                <rich:column>
                   
                </rich:column>
                <rich:column>
                    
                </rich:column>
                <rich:column>
                    Total :
                </rich:column>
                <rich:column>
                    <h:outputText value="#{adminAction.open1}">
                        <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
                    </h:outputText>
                </rich:column>
                <rich:column>
                    <h:outputText value="#{adminAction.reci1}">
                        <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
                    </h:outputText>
                </rich:column>
                <rich:column>
                    <h:outputText value="#{adminAction.disp1}">
                        <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
                    </h:outputText>
                </rich:column>
                <rich:column>
                    <h:outputText value="#{adminAction.bale1}">
                        <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
                    </h:outputText>
                </rich:column>
               
                
                 
            </rich:columnGroup>
					</f:facet>
					
					
					
					
				</rich:dataTable>


			</div>
			
			
			
			<div>
				<rich:dataTable var="list1" rendered="#{adminAction.tableflag1 and adminAction.brand_whole eq 'W'}"
					value="#{adminAction.brandPackagingShowDataList1}" width="100%"
					headerClass="TableHead" footerClass="TableHead" id="show1"
					rowClasses="TableRow1,TableRow2" styleClass="DataTable">

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Sr.No" />
						</f:facet>
						<h:outputText value="#{list1.srNo }" />
					</rich:column>
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Applicant_name" />
						</f:facet>
						<h:outputText value="#{list1.applicant_name}" />
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Brand_Name" />
						</f:facet>
						<h:outputText value="#{list1.brandName }" />
					</rich:column>
					
					
					
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Package Name" />
						</f:facet>
						<h:outputText value="#{list1.pack_name }" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Opening" />
						</f:facet>
						<h:outputText value="#{list1.recive_bottel }" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Receiving" />
						</f:facet>
						<h:outputText value="#{list1.recive_bottel2 }" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Dispatch" />
						</f:facet>
						<h:outputText value="#{list1.dis_bottel }" />
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Balance" style="color: blue; font-weight:bold;"/>
						</f:facet>
						<h:outputText value="#{list1.balance }" />
					</rich:column>
					<f:facet name="footer">
						<rich:columnGroup>
                <rich:column></rich:column>
                <rich:column>
                   
                </rich:column>
                <rich:column>
                    
                </rich:column>
                <rich:column>
                    Total :
                </rich:column>
                <rich:column>
                    <h:outputText value="#{adminAction.open}">
                        <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
                    </h:outputText>
                </rich:column>
                <rich:column>
                    <h:outputText value="#{adminAction.reci}">
                        <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
                    </h:outputText>
                </rich:column>
                <rich:column>
                    <h:outputText value="#{adminAction.disp}">
                        <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
                    </h:outputText>
                </rich:column>
                <rich:column>
                    <h:outputText value="#{adminAction.bale}">
                        <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
                    </h:outputText>
                </rich:column>
               
                
                 
            </rich:columnGroup>
					</f:facet>

				</rich:dataTable>


			</div>

		</h:form>
	</f:view>
</ui:composition>