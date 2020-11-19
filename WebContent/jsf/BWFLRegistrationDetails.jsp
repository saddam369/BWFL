
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

	<f:view>

		<head>
<style type="text/css">
.reseet {
	border-radius: 3px;
	border-style: none;
	height: 28px;
	width: 75px;
	background-color: #8a8a5c;
	color: white;
	font-weight: bold;
}

.savee {
	border: none;
	border-radius: 3px;
	border-style: none;
	height: 28px;
	width: 75px;
	padding: 8px, 16px;
	background-color: #2d8659;
	color: white;
	font-weight: bold;
	border-radius: 3px;
	background-color: #2d8659;
}

.savee:hover {
	text-decoration: blink;
	background-color: #7ec473;
}

.reseet:hover {
	text-decoration: blink;
	background-color: #837c83;
}

.textt {
	border-radius: 3px;
	background-color: #E8E8E8;
	border: 1px dotted #669999;
	height: 25px;
	box-shadow: 1px 1px 15px lightsteelblue;
	padding: 5px 5px;
	height: 25px;
	width: 10%;
}

.combo {
	border-radius: 3px;
	background-color: #E8E8E8;
	padding-top: 2px;
	padding-bottom: 2px;
	height: 25px;
	width: 75%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px dotted #669999;
	height: 25px;
}

.inputtext {
	border-radius: 3px;
	background-color: #E8E8E8;
	padding: 5px 5px;
	height: 25px;
	width: 75%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px dotted #669999;
}

.textarea1 {
	border-radius: 3px;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px dotted #669999;
	background-color: #E8E8E8;
	width: 100%;
	border: 1px dotted #669999;
}
</style>

		</head>

		<h:form style="color: black">

			<!--  heading -->

			<div style="padding-top: 10px;">
				<div style="">
					<div class="row">
						<div class="col-sm-1"></div>
						<div class="col-sm-10 wow fadeInDown">
							<h2 align="center"
								style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large">BWFL
								Registration Detail</h2>
						</div>
						<div class="col-sm-1"></div>
					</div>
				</div>
			</div>


			<a4j:outputPanel id="fullForm">
				<div class="wow fadeInDown" align="center">
					<h:outputText value="#{bWFLRegistrationDetailsAction.successMsg}"
						style="color:green;" />
					<h:messages errorStyle="color:red" layout="table" id="messages"
						infoStyle="color:green" />
				</div>
				<!-- error -->
				<div class=" wow shake" style="height: 30px;" align="center">
					<h:outputText value="#{bWFLRegistrationDetailsAction.errorMsg}"
						style="color:red;" />
					<h:inputHidden value="#{bWFLRegistrationDetailsAction.hidden}"></h:inputHidden>
				</div>

				<!--error end  -->
				<rich:spacer height="10px" />

				<div class="row" align="center">

					<h:selectOneRadio style="width: 30%"
						value="#{bWFLRegistrationDetailsAction.radio}">
						<f:selectItem itemLabel="Distillery" itemValue="Distillery" />
						<f:selectItem itemLabel="Brewery" itemValue="Brewery" />
						<f:selectItem itemLabel="Winery" itemValue="Winery" />
					</h:selectOneRadio>

				</div>
				<div class="row" align="center"
					style="margin-top: 15px; margin-bottom: 15px;">
					<div class="col-md-3"></div>
					<div class="col-md-2">
						<h:outputText value="UnitName :" style="height: 25px" />

					</div>
					<div class="col-md-4" align="left">
						<h:inputText
							disabled="#{bWFLRegistrationDetailsAction.distContactFlag == true}"
							value="#{bWFLRegistrationDetailsAction.distilleryId}"
							styleClass="inputtext"></h:inputText>
					</div>

					<div class="col-md-3">
						<h:outputText value="Unit Address"></h:outputText>
						<h:inputTextarea
							value="#{bWFLRegistrationDetailsAction.dbAddress}"
							styleClass="inputtext" style="height: 25px">

						</h:inputTextarea>


					</div>
				</div>

				<div class="row" align="center" style="margin-top: 10px;">
					<div class="col-md-1">
						<h:outputText value="License No:" />
					</div>
					<div class="col-md-2">
						<h:inputText
							value="#{bWFLRegistrationDetailsAction.licenseNumber}"
							styleClass="inputtext" style="height: 25px" disabled="true" />
					</div>

					<div class="col-md-1">
						<h:outputText value="License Type:" />
					</div>
					<div class="col-md-2">
						<h:inputText value="#{bWFLRegistrationDetailsAction.licenseType}"
							styleClass="inputtext" style="height: 25px" disabled="true" />
					</div>

					<div class="col-md-1">
						<h:outputText value="Licensee Name:" />
					</div>
					<div class="col-md-2">
						<h:inputText value="#{bWFLRegistrationDetailsAction.licenseeName}"
							styleClass="inputtext" style="height: 25px" disabled="true" />
					</div>

					<div class="col-md-1">
						<h:outputText value="Mobile:" />
					</div>
					<div class="col-md-2">
						<h:inputText value="#{bWFLRegistrationDetailsAction.mobile}"
							styleClass="inputtext" style="height: 25px" disabled="true" />
					</div>
				</div>

				<div class="row" align="center" style="margin-top: 15px;">
					<div class="col-md-1">
						<h:outputText value="Father/Husband Name:" />
					</div>
					<div class="col-md-2">
						<h:inputText value="#{bWFLRegistrationDetailsAction.fatherName}"
							styleClass="inputtext" style="height: 25px" />
					</div>

					<div class="col-md-1">
						<h:outputText value="District:" />
					</div>
					<div class="col-md-2">
						<h:inputText value="#{bWFLRegistrationDetailsAction.districtName}"
							disabled="true" styleClass="inputtext" style="height: 25px" />
					</div>

					<div class="col-md-1">
						<h:outputText value="Email:" />
					</div>
					<div class="col-md-2">
						<h:inputText value="#{bWFLRegistrationDetailsAction.email}"
							styleClass="inputtext" style="height: 25px" />
					</div>

					<div class="col-md-1">
						<h:outputText value="Firm Name:" />
					</div>
					<div class="col-md-2">
						<h:inputText value="#{bWFLRegistrationDetailsAction.firmName}"
							styleClass="inputtext" style="height: 25px" />
					</div>
				</div>

				<div class="row" align="center" style="margin-top: 15px;">
					<div class="col-md-1">
						<h:outputText value="Age:" />
					</div>
					<div class="col-md-2">
						<h:inputText value="#{bWFLRegistrationDetailsAction.age}"
							styleClass="inputtext" style="height: 25px" />
					</div>

					<div class="col-md-1">
						<h:outputText value="Gender:" />
					</div>
					<div class="col-md-2">
						<h:selectOneMenu styleClass="combo" style="height: 25px"
							value="#{bWFLRegistrationDetailsAction.sex}">
							<f:selectItem itemLabel="-Select-" itemValue="NA" />
							<f:selectItem itemLabel="Male" itemValue="male" />
							<f:selectItem itemLabel="Female" itemValue="female" />
						</h:selectOneMenu>
					</div>

					<div class="col-md-1">
						<h:outputText value="GSTIN:" />
					</div>
					<div class="col-md-2">
						<h:inputText value="#{bWFLRegistrationDetailsAction.gstin}"
							styleClass="inputtext" style="height: 25px" />
					</div>

					<div class="col-md-1">
						<h:outputText value="Pan No:" />
					</div>
					<div class="col-md-2">
						<h:inputText value="#{bWFLRegistrationDetailsAction.pan}"
							styleClass="inputtext" style="height: 25px" />
					</div>
				</div>


				<div class="row" align="center" style="margin-top: 15px;">
					<div class="col-md-1">
						<h:outputText value="Aadhar No:" />
					</div>
					<div class="col-md-2">
						<h:inputText value="#{bWFLRegistrationDetailsAction.adhar}"
							styleClass="inputtext" style="height: 25px" />
					</div>

					<div class="col-md-1">
						<h:outputText value="Unit Name:" />
					</div>
					<div class="col-md-2">
						<h:inputText value="#{bWFLRegistrationDetailsAction.unitName}"
							styleClass="inputtext" style="height: 25px" />
					</div>

					<div class="col-md-1">
						<h:outputText value="Issued Date:" />
					</div>
					<div class="col-md-2">
						<rich:calendar value="#{bWFLRegistrationDetailsAction.issueDate}"></rich:calendar>
					</div>

					<div class="col-md-1">
						<h:outputText value="Valid Upto:" />
					</div>
					<div class="col-md-2">
						<rich:calendar value="#{bWFLRegistrationDetailsAction.validUpto}"></rich:calendar>
					</div>
				</div>



				<div class="row" align="center" style="margin-top: 15px;">
					<div class="col-md-1">
						<h:outputText value="Firm Address:" />
					</div>
					<div class="col-md-11">
						<h:inputText value="#{bWFLRegistrationDetailsAction.address}"
							styleClass="textarea1" style="height: 50px;" />
					</div>

					<div class="row" align="center" style="margin-top: 20px;">
						<div class="row">
							<rich:spacer height="20px;"></rich:spacer>
						</div>
						<div class="col-md-3">
							<h:outputText value="Generate user id:" />


						</div>
						<div class="col-md-3">
							<h:inputText maxlength="10"
								value="#{bWFLRegistrationDetailsAction.contactDistillery}"
								disabled="#{bWFLRegistrationDetailsAction.distContactFlag == true}"
								styleClass="inputtext" style="height: 25px" />


						</div>
						<div class="col-md-6"></div>
					</div>

					<div class="row">
						<rich:spacer height="20px;"></rich:spacer>
					</div>
				</div>

				<div class="col-md-12" align="center">
					<h:commandButton value="Save" styleClass="savee"
						action="#{bWFLRegistrationDetailsAction.save}"></h:commandButton>
					<rich:spacer width="10px;"></rich:spacer>
					<h:commandButton value="Reset" styleClass="reseet"
						action="#{bWFLRegistrationDetailsAction.reset}"></h:commandButton>
				</div>

				<div class="row">
					<rich:spacer height="20px;"></rich:spacer>
				</div>
			</a4j:outputPanel>

		</h:form>
	</f:view>


</ui:composition>