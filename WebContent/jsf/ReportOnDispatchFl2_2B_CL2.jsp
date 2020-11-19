<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">


	<h:form>



		<f:view>
			<div class="form-group">
				<div class="row " align="center">
					<rich:spacer height="30px"></rich:spacer>
				</div>
				<div>
					<a4j:outputPanel id="msg">
						<h:messages errorStyle="color:red"
							style="font-size: 14px;font-weight: bold"
							styleClass="generalExciseStyle" layout="table" id="messages"
							infoStyle="color:green">
						</h:messages>
					</a4j:outputPanel>
				</div>

				<div class="row " align="center">
					<div>
						<h2>
							<h:outputText value="Report Of FL2/FL2B/CL2 Dispatches  "
								styleClass="generalHeaderOutputTable"
								style="font-weight: bold; font-size: 40px;text-decoration: underline;margin-top: 2px;font-family:Monotype Corsiva;font-size:40px;">
							</h:outputText>
						</h2>
					</div>
				</div>
<div class="row " align="center">
					<rich:spacer height="30px"></rich:spacer>
				</div>
<div class="col-md-12" align="center">
						<h:outputText value="Select Year :"
							style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
							<rich:spacer width="20px"></rich:spacer>
							<h:selectOneMenu
						value="#{reportOnDispatchFl2_2B_CL2Action.year}"
						style="height: 25px; width : 150px;">
						<f:selectItems
							value="#{reportOnDispatchFl2_2B_CL2Action.getAll_List}"/>
					</h:selectOneMenu>
					</div>
					
					<rich:spacer height="20px"></rich:spacer>
				</div>
				<rich:spacer height="30px"></rich:spacer>
				<div class="form-group newsdiv">
					 
					
					<div class="row col-md-12" align="center">
						<h:selectOneRadio
							style="FONT-WEIGHT: bold;  font-size: 18px; width: 20% "
							onclick="this.form.submit();"
							value="#{reportOnDispatchFl2_2B_CL2Action.radioCLandFL}"
							valueChangeListener="#{reportOnDispatchFl2_2B_CL2Action.radioListiner}">

							<f:selectItem itemValue="FL2" itemLabel="FL2" />
							<f:selectItem itemValue="FL2B" itemLabel="FL2B" />
							<f:selectItem itemValue="CL2" itemLabel="CL2" />
						</h:selectOneRadio>
					</div>

					<div class="row ">
						<rich:spacer height="20px" />
					</div>

					<div class="row col-md-12" align="center">

						<h:selectOneRadio
							style="FONT-WEIGHT: bold;  font-size: 18px;  "
							onchange="this.form.submit();"
							value="#{reportOnDispatchFl2_2B_CL2Action.radioVal}"
							valueChangeListener="#{reportOnDispatchFl2_2B_CL2Action.radioListiner}">

							<f:selectItem itemValue="A" itemLabel="All" />
							<f:selectItem itemValue="DW" itemLabel="District And Shop Wise" />

						</h:selectOneRadio>

					</div>
						<div class=" row col-md-12">
						<rich:spacer height="20px" />
					</div>
					<div align="center">
					<h:outputText value="District Name:- " rendered="#{reportOnDispatchFl2_2B_CL2Action.radioVal eq 'DW'}">
					
					</h:outputText>
					
					<h:selectOneMenu
					 valueChangeListener="#{reportOnDispatchFl2_2B_CL2Action.shopname}" 
							rendered="#{reportOnDispatchFl2_2B_CL2Action.radioVal eq 'DW'}"
							onchange="this.form.submit();"
							value="#{reportOnDispatchFl2_2B_CL2Action.district}"							
							style="width: 100px; height: 28px;">
							<f:selectItems
								value="#{reportOnDispatchFl2_2B_CL2Action.districtList}" />
						</h:selectOneMenu>
					
					</div>

					<div class=" row col-md-12">
						<rich:spacer height="20px" />
					</div>
					<div align="center">
				<h:outputText rendered="#{reportOnDispatchFl2_2B_CL2Action.radioVal eq 'DW'}"
				value="Sector/Circle*"
						styleClass="generalExciseStyle" ></h:outputText>
						
						
					<h:selectOneMenu 
						style="width: 250px; height: 28px;"
						rendered="#{reportOnDispatchFl2_2B_CL2Action.radioVal eq 'DW'}"
						onchange="this.form.submit();"
						value="#{reportOnDispatchFl2_2B_CL2Action.sectorId}" 
						 valueChangeListener="#{reportOnDispatchFl2_2B_CL2Action.radioListenerSector}"
						 
						>

						<f:selectItems value="#{reportOnDispatchFl2_2B_CL2Action.sectorList}" />
					</h:selectOneMenu>
					
					
					
					
					</div>
						<div class=" row col-md-12">
						<rich:spacer height="20px" />
					</div>
					
					<div align="center">
					<h:outputText value="Shop Name:- " rendered="#{reportOnDispatchFl2_2B_CL2Action.radioVal eq 'DW'}">
					
					</h:outputText>
					
					
					<h:selectOneMenu
							rendered="#{reportOnDispatchFl2_2B_CL2Action.radioVal eq 'DW'}"
							onchange="this.form.submit();"
							
							value="#{reportOnDispatchFl2_2B_CL2Action.shop}"							
							style="width: 100px; height: 28px;">
							<f:selectItems
								value="#{reportOnDispatchFl2_2B_CL2Action.shopList}" />
						</h:selectOneMenu>
					
					</div>
					
					
					<div class=" row col-md-12">
						<rich:spacer height="20px" />
					</div>
					
					
					 

					<div class="row col-md-12" align="center">
						Between Dates :
						<rich:calendar
							value="#{reportOnDispatchFl2_2B_CL2Action.fromdate}"></rich:calendar>
						and :
						<rich:calendar value="#{reportOnDispatchFl2_2B_CL2Action.todate}"></rich:calendar>
					</div>
					<rich:spacer height="20px" />

					


					<div class="col-md-12" align="center">


						<h:commandButton
							action="#{reportOnDispatchFl2_2B_CL2Action.print}"
							value="Print Report"
							style="background-color:#C5C5C5; font-size: large;" />

						<h:outputLink styleClass="outputLinkEx"
							value="/doc/ExciseUp/MIS/pdf/#{reportOnDispatchFl2_2B_CL2Action.pdfname}"
							target="_blank">
							<h:outputText styleClass="outputText" id="text223"
								value="View Report"
								rendered="#{reportOnDispatchFl2_2B_CL2Action.printFlag==true}"
								style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
						</h:outputLink>



					</div>
					<div class="row " align="center">
						<rich:spacer height="30px"></rich:spacer>
					</div>
					<div class="col-md-12" align="center">


						<h:commandButton
							action="#{reportOnDispatchFl2_2B_CL2Action.excel}"
							value="Generate Excel"
							style="background-color:#C5C5C5; font-size: large;"
							rendered="true" />

						<h:outputLink styleClass="outputLinkEx"
							value="/doc/ExciseUp//MIS//Excel//#{reportOnDispatchFl2_2B_CL2Action.exlname}.xlsx"
							target="_blank">
							<h:outputText styleClass="outputText" value="Download Excel"
								rendered="#{reportOnDispatchFl2_2B_CL2Action.excelFlag==true}"
								style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
						</h:outputLink>



					</div>
					<div class="row " align="center">
						<rich:spacer height="30px"></rich:spacer>
					</div>
					<div class="col-md-12" align="center">





						<h:commandButton
							action="#{reportOnDispatchFl2_2B_CL2Action.reset}" value="Reset"
							style="background-color:#C5C5C5; font-size: large; width: 110px;" />

					</div>
					<rich:spacer height="20px" />
				</div>
		</f:view>
	</h:form>
</ui:composition>