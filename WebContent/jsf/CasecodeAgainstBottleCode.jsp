 <ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

   <f:view>
   <h:form>
   <TABLE width="80%">
									<TBODY>
										<TR>
											<TD align="left"><h3>
													<h:messages errorStyle="color:red" layout="table"
														id="messages" infoStyle="color:green">
													</h:messages>
												</h3></TD>
										</TR>
									</TBODY>
								</TABLE>
								
								
								
							<TABLE align="center" width="100%">
									<TBODY>
										<TR align="center">

											<TD align="center" width="100%" class="pghead"><h:inputHidden
													value="#{bWFLImportAction.hidden}"></h:inputHidden>
												<h2>
													<h:outputText value="CaseCode Against Bottle Code "
														style="TEXT-DECORATION: underline; FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;">
													</h:outputText>
												</h2></TD>
										</TR>

										<tr>
											<td><rich:separator lineType="dashed"></rich:separator></td>
										</tr>

										<tr>
											<TD><rich:spacer height="30px"></rich:spacer></TD>
										</tr>










									</TBODY>
								</TABLE>		
							<div align="center">
							<h:outputText value="Upload Bottle Code Excel"></h:outputText>
							<rich:separator lineType="dashed"></rich:separator>
							</div>	
								
						<div align="center">
						<rich:fileUpload listHeight="35" listWidth="250"
									fileUploadListener="#{casecodeAgainstBottleCodeAction.excelUploadForWholesale}"
									maxFilesQuantity="1" clearControlLabel="Clear"
									clearAllControlLabel="Clear All" sizeErrorLabel=""
									addControlLabel="Add">
  
  </rich:fileUpload>
						</div>	
						
						<div align="center">
						<h:commandButton value="Download Casecode Excel" styleClass="btn btn-primary"
						action="#{casecodeAgainstBottleCodeAction.getUploadedExcelDataForCasecode}"
						>
						
						</h:commandButton>
						 <h:outputLink target="_blank" rendered="#{casecodeAgainstBottleCodeAction.renderFlag}"
									value="/doc/ExciseUp/casecode/#{casecodeAgainstBottleCodeAction.excelPath}">
									<h:graphicImage value="/img/download.gif" alt="view document"
										style="width : 60px; height : 35px;"></h:graphicImage>
									
									</h:outputLink>
						</div>	
								
								
								
   </h:form>
   </f:view>
</ui:composition>