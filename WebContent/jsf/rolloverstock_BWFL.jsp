<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

	<f:view>
		<h:form>
		   <div class="col-md-12">
		    <rich:spacer height="20px"></rich:spacer>
		       <div>
		         <h:messages errorStyle="color:red" layout="table"
					id="messages" infoStyle="color:green"
					style="FONT-SIZE: x-large;">
				</h:messages>
		       
		       </div>
		   <rich:spacer height="20px"></rich:spacer>
		   </div>
		   <div class="col-md-12">
		        <div align="center">
		        <h:outputText value="ROLLOVER STOCK FOR BWFL 2020-21" 
				 style="FONT-WEIGHT: bold; FONT-SIZE: 35px;font-family:Comic Sans MS;"></h:outputText>
		        </div>
		        <div><h:inputHidden
									value="#{rolloverstock_WFL_action.hidden}"></h:inputHidden></div>
		       <rich:spacer height="20px"></rich:spacer>
		       <rich:separator lineType="dashed"></rich:separator>
		   </div>
		   <div>
		    <rich:spacer height="20px"/>
		   </div>
		  <div >
		  
		      <div align="center">
		        <rich:dataTable value="#{rolloverstock_WFL_action.displaylist}" var="list"
		        width="90%" id="table3"  
		        
		         rendered="#{rolloverstock_WFL_action.scanflag }"
		         >
		                       <rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
										</f:facet>
										<center>
											<h:outputText styleClass="generalExciseStyle"
												value="#{list.sno}" />
										</center>
								</rich:column> 
							  <rich:column>
							       <f:facet name="header">
							               <h:outputLabel value="Brand Name" />
							       </f:facet>
							      
							              <h:outputLabel  value="#{list.brand_name}"/>
							       
							       
							  </rich:column>	
							  <rich:column>
							        <f:facet name="header">
							                <h:outputLabel value="Size" />
							        </f:facet>     
							        <h:outputLabel value="#{list.package_name}" />
							  </rich:column>
							
		                       
		                       <rich:column>
		                            <f:facet name="header">
		                                 <h:outputLabel value=" Available Stock Box" /> 
		                            </f:facet>
		                             <h:outputLabel value="#{list.avl_box}" />
		                           
		                      </rich:column>
		                      
		                      
		                       <rich:column>
		                            <f:facet name="header">
		                                 <h:outputLabel value="Rollover Boxes " /> 
		                            </f:facet>
		                             <h:inputText  value="#{list.box}"
		                              disabled ="#{list.final_flag}" >
		                          
		                           </h:inputText>
		                      </rich:column>
		                       
		                      
		                       <rich:column>
		                            <f:facet name="header">
		                                 <h:outputLabel value=" " /> 
		                            </f:facet>
		                              <h:commandButton  value="Scan And Upload"  rendered="#{!list.final_flag and !list.com_flg}"
		                                    actionListener="#{rolloverstock_WFL_action.scanAndUpload }"
		                                    disabled ="#{list.final_flag}" ></h:commandButton>
		      
		      <h:commandButton  value="Freeze And Send To DEO"  onclick="return confirm('ALERT : Stock once Freezed cannot be reverted.Do you wish to continue?');"
		                                    actionListener="#{rolloverstock_WFL_action.freez}"
		                                    rendered ="#{list.final_flag and !list.com_flg }" ></h:commandButton>
		                                    
		                           <h:outputText value="Finalised "
								rendered="#{list.com_flg }"
							style="FONT-WEIGHT: bold; font-size: 15px ;color:green "></h:outputText>
		                      </rich:column>
		                    
		        
		        </rich:dataTable>
		      </div>
		       
		  </div>
		   <rich:spacer height="20px"></rich:spacer>
		   
		   <div align="center" style="margin-left: 60px">
		      <h:panelGroup rendered="#{rolloverstock_WFL_action.tab_flag }">
		      <div align="left">
		          <h:outputLabel value="BRAND  NAME : " />
		          <rich:spacer width="15px"/>
		          <h:outputText  value="#{rolloverstock_WFL_action.brand_name }"/>
		          <rich:spacer width="20px"/>
		          <h:outputLabel value="ETIN   :" />
		          <rich:spacer width="15px"/>
		          <h:outputText  value="#{rolloverstock_WFL_action.etin_unit_id}"/>
		           <rich:spacer width="20px"/>
		          <h:outputLabel value="Rollover Boxes   :" />
		          <rich:spacer width="15px"/>
		          <h:outputText  value="#{rolloverstock_WFL_action.rolloverbox}"/>
		      </div>
		      <rich:spacer height="10px"/>
		      <div>
		        
		       
		          
		      
		         
		       
		      </div> <rich:spacer height="10px"/>
		      <div>
		       
		          <rich:spacer width="15px"/>
		         
		      </div>
		      
		    
		      <div>
		          
		      </div>
		   
		   <rich:spacer height="35px"/>
		   </h:panelGroup>
		   </div>
		   
		   
		   <div>
		      <rich:spacer height="10ps"></rich:spacer>
		   
		   
		   </div>
	 <div>
		   <h:panelGroup width="100%" rendered="#{rolloverstock_WFL_action.gatePassFlag}">
		 <table align="center" width="100%">
						<tr>
							<td colspan="6" style="color: Green;" align="left">
							<h:outputText
									rendered="#{rolloverstock_WFL_action.gatePassFlag}"
							
style="color: #ce0000;font-style: italic;font-size: large;text-decoration:blink;" />
							</td>
							
					     </tr>
					     
		 <tr align="left" width="100%">
							<td  width="100%" align="center">
							<h:outputText
									rendered="#{rolloverstock_WFL_action.gatePassFlag}"
									value="** Upload csv .:"
									style="font-style: italic;font-size: large;text-decoration:blink;" />
							 
							
						   <rich:fileUpload addControlLabel="Add File" id="link3"
									fileUploadListener="#{rolloverstock_WFL_action.uploadCsv}"
									rendered="#{rolloverstock_WFL_action.gatePassFlag}"
									maxFilesQuantity="1" listHeight="40" listWidth="250"
									clearControlLabel="clear" stopControlLabel="Stop"
									ontyperejected="if (!confirm(' ONLY .csv type file ALLOWED !!!')) return false"
									acceptedTypes="csv" clearAllControlLabel="Clear">
								</rich:fileUpload>
							 <h:commandButton value="Upload"
									styleClass="btn btn-primary"
									rendered="#{rolloverstock_WFL_action.gatePassFlag}"
									action="#{rolloverstock_WFL_action.csvSubmit}">
								</h:commandButton></td>
						</tr>	
					     
		</table>			     
		   
		 </h:panelGroup> 
		   
	</div>	 
	
		   <rich:spacer height="50px"></rich:spacer>
		      
		  <div align="center">
		       
		  
		
		       
		  
		  </div>
		   
		   <div>
		  <div align="center">
		        <rich:dataTable value="#{rolloverstock_WFL_action.displaylist1}" var="list"
		        width="90%" id="table4" rows="5" rendered="#{rolloverstock_WFL_action.tableflag and rolloverstock_WFL_action.gatePassFlag}">
		                       <rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
										</f:facet>
										<center>
											<h:outputText styleClass="generalExciseStyle"
												value="#{list.srno}" />
										</center>
								</rich:column>
								<rich:column>
							       <f:facet name="header">
							               <h:outputLabel value="ETIN Number" />
							       </f:facet>
							      
							              <h:outputLabel  value="#{list.etin}"/>
							       
							       
							  </rich:column>	
							  <rich:column>
							        <f:facet name="header">
							                <h:outputLabel value="CaseCode" />
							        </f:facet>     
							        <h:outputLabel value="#{list.casecode}" />
							  </rich:column>
							
		                      
		                    
		        <f:facet name="footer">
										<rich:datascroller for="table4" />
				</f:facet>
		        </rich:dataTable>
		      </div>
		   
		   </div>
		   <rich:spacer height="20px"/>
		   <div align="center"> 
	<h:commandButton  value="Close Uploading"
		       action="#{rolloverstock_WFL_action.back }" styleClass="btn btn-sm btn-default "
		       rendered="#{rolloverstock_WFL_action.backflag}"
		    ></h:commandButton> 
		       <rich:spacer width="20px"/>
		       <h:commandButton   rendered="#{rolloverstock_WFL_action.backflag}"
								action="#{rolloverstock_WFL_action.save}"
								styleClass="btn btn-sm btn-success " value="Save and Finalize">
							</h:commandButton>
							<rich:spacer width="20px"/>
					       <h:commandButton   rendered="#{rolloverstock_WFL_action.backflag}"
								action="#{rolloverstock_WFL_action.delete}"
								styleClass="btn btn-sm btn-danger " value="Clear Uploaded Data">
							</h:commandButton>
		       </div>
		   
		   
		   
		   
		
		

		
					
		</h:form>
	</f:view>
</ui:composition>		