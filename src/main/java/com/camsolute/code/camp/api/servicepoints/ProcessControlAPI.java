/*******************************************************************************
 * Copyright (C) 2018 Christopher Campbell
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 * 	Christopher Campbell - all code prior and post initial release
 ******************************************************************************/
package com.camsolute.code.camp.api.servicepoints;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import com.camsolute.code.camp.lib.contract.HasProcess;
import com.camsolute.code.camp.lib.contract.IsObjectInstance;
import com.camsolute.code.camp.lib.dao.rest.ProcessControlServicePointInterface;
import com.camsolute.code.camp.lib.dao.rest.RestInterface;
import com.camsolute.code.camp.lib.data.CampRest;
import com.camsolute.code.camp.lib.models.Attribute;
import com.camsolute.code.camp.lib.models.AttributeInterface;
import com.camsolute.code.camp.lib.models.CampInstanceDao;
import com.camsolute.code.camp.lib.models.Model;
import com.camsolute.code.camp.lib.models.ModelInterface;
import com.camsolute.code.camp.lib.models.customer.Customer;
import com.camsolute.code.camp.lib.models.customer.CustomerInterface;
import com.camsolute.code.camp.lib.models.order.Order;
import com.camsolute.code.camp.lib.models.order.OrderDao;
import com.camsolute.code.camp.lib.models.order.OrderInterface;
import com.camsolute.code.camp.lib.models.order.OrderList;
import com.camsolute.code.camp.lib.models.order.OrderPosition;
import com.camsolute.code.camp.lib.models.order.OrderPositionInterface;
import com.camsolute.code.camp.lib.models.order.OrderPositionList;
import com.camsolute.code.camp.lib.models.process.ProcessList;
import com.camsolute.code.camp.lib.models.process.Process;
import com.camsolute.code.camp.lib.models.process.ProcessDao;
import com.camsolute.code.camp.lib.models.product.Product;
import com.camsolute.code.camp.lib.models.product.ProductInterface;
import com.camsolute.code.camp.lib.models.rest.Message;
import com.camsolute.code.camp.lib.models.rest.OrderPositionProcessMessage;
import com.camsolute.code.camp.lib.models.rest.OrderPositionProcessMessage.OrderPositionMessage;
import com.camsolute.code.camp.lib.models.rest.OrderProcessMessage;
import com.camsolute.code.camp.lib.models.rest.ProductProcessMessage;
import com.camsolute.code.camp.lib.models.rest.ProductProcessMessage.ProductMessage;
import com.camsolute.code.camp.lib.models.rest.VariableValue.VariableValueType;
import com.camsolute.code.camp.lib.models.rest.Variables;
import com.camsolute.code.camp.lib.models.rest.OrderProcessMessage.CustomerOrderMessage;
import com.camsolute.code.camp.lib.models.rest.ProcessExecution;
import com.camsolute.code.camp.lib.models.rest.ProcessExecutionList;
import com.camsolute.code.camp.lib.models.rest.Request;
import com.camsolute.code.camp.lib.models.rest.Request.Principal;
import com.camsolute.code.camp.lib.models.rest.SignalPacket;
import com.camsolute.code.camp.lib.models.rest.Task;
import com.camsolute.code.camp.lib.models.rest.TaskList;
import com.camsolute.code.camp.lib.models.rest.VariableValue;
import com.camsolute.code.camp.lib.utilities.Util;

@Path(CampRest.ProcessControl.Prefix)
public class ProcessControlAPI implements ProcessControlServicePointInterface{
	
	private static final Logger LOG = LogManager.getLogger(ProcessControlAPI.class);
	private static String fmt = "[%15s] [%s]";
	
	public static String serverUrl = CampRest.PROCESS_ENGINE_API_SERVER_URL;

	public static String domainUri = CampRest.PROCESS_ENGINE_API_DOMAIN;

	@Path(CampRest.ProcessControlDaoService.START_PROCESS) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String startProcess(@QueryParam("processKey")String processKey, String request) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[startProcess]";
			msg = "====[ process control service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String prefix = CampRest.ProcessEngine.Prefix;		
		String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.START_PROCESS);
		String uri = serverUrl+domainUri+String.format(serviceUri,processKey);
		String result = RestInterface.resultPost(uri, request, !Util._IN_PRODUCTION);
		String instanceId = (new JSONObject(result)).getString("id");
		result = ProcessDao.instance().loadByInstanceId(instanceId, !Util._IN_PRODUCTION).toJson();
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[startProcess completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return result;
	}

	@Path(CampRest.ProcessControlDaoService.NOTIFY_PROCESSES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public void messageProcesses(@QueryParam("messageType")String messageType, @QueryParam("messageName")String messageName, String object) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[messageProcess]";
			msg = "====[ process control service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Message m = null;
		Message.MessageType type = Message.MessageType.valueOf(messageType);
		ProcessList pl = null;
		switch(type) {
		case OrderProcessMessage:
			Order o = OrderInterface._fromJson(object);
			pl = o.processes();
			CustomerOrderMessage message = CustomerOrderMessage.valueOf(messageName); 
			m = (Message) new OrderProcessMessage(message,o);
			break;
		case ProductProcessMessage:
			Product p = ProductInterface._fromJson(object);
			pl = p.processes();
			ProductMessage pmessage = ProductMessage.valueOf(messageName); 
			m = (Message) new ProductProcessMessage(pmessage,p);
			break;
		case OrderPositionProcessMessage:
			OrderPosition op = OrderPositionInterface._fromJson(object);
			pl = op.processInstances();
			OrderPositionMessage opmessage = OrderPositionMessage.valueOf(messageName);
			m = (Message) new OrderPositionProcessMessage(opmessage,op);
			break;
		case 	OrderOrderProcessMessage: //TODO
		case ProductionOrderProcessMessage: //TODO
		case ProductOrderProcessMessage: //TODO
		case AttributeOrderProcessMessage: //TODO
		case ModelOrderProcessMessage: //TODO
		case AttributeProcessMessage: //TODO
		case ModelProcessMessage: //TODO
		case ProductionProcessMessage: //TODO
		case CustomerProcessMessage: //TODO
			default:
				break;
		}
		if(m != null) {
			for(Process<?> p:pl) {
				m.setProcessInstanceId(p.instanceId());
				String json = m.toJson();
				String prefix = CampRest.ProcessEngine.Prefix;		
				String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.MESSAGE_PROCESSES);
				String uri = serverUrl+domainUri+serviceUri;
				String result = RestInterface.resultPost(uri, json, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION){msg = "----[SERVICE CALL RESULT: "+result+"]----";LOG.info(String.format(fmt, _f,msg));}
			}
		}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[messageProcess completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
	}

	@Path(CampRest.ProcessControlDaoService.NOTIFY_PROCESS) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public void messageProcess(@QueryParam("processInstanceId")String processInstanceId, @QueryParam("messageType")String messageType, @QueryParam("messageName")String messageName, String object) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[messageProcess]";
			msg = "====[ process control service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Message m = null;
		Message.MessageType type = Message.MessageType.valueOf(messageType);
		switch(type) {
		case OrderProcessMessage:
			Order o = OrderInterface._fromJson(object);
			CustomerOrderMessage message = CustomerOrderMessage.valueOf(messageName); 
			m = (Message) new OrderProcessMessage(message,o);
			break;
		case ProductProcessMessage:
			Product p = ProductInterface._fromJson(object);
			ProductMessage pmessage = ProductMessage.valueOf(messageName); 
			m = (Message) new ProductProcessMessage(pmessage,p);
			break;
		case OrderPositionProcessMessage:
			OrderPosition op = OrderPositionInterface._fromJson(object);
			OrderPositionMessage opmessage = OrderPositionMessage.valueOf(messageName);
			m = (Message) new OrderPositionProcessMessage(opmessage,op);
			break;
		case 	OrderOrderProcessMessage: //TODO
		case ProductionOrderProcessMessage: //TODO
		case ProductOrderProcessMessage: //TODO
		case AttributeOrderProcessMessage: //TODO
		case ModelOrderProcessMessage: //TODO
		case AttributeProcessMessage: //TODO
		case ModelProcessMessage: //TODO
		case ProductionProcessMessage: //TODO
		case CustomerProcessMessage: //TODO
			default:
				break;
		}
		if(m != null) {
			m.setProcessInstanceId(processInstanceId);
			String json = m.toJson();
			String prefix = CampRest.ProcessEngine.Prefix;		
			String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.MESSAGE_PROCESSES);
			String uri = serverUrl+domainUri+serviceUri;
			String result = RestInterface.resultPost(uri, json, !Util._IN_PRODUCTION);
			if(!Util._IN_PRODUCTION){msg = "----[SERVICE CALL RESULT: "+result+"]----";LOG.info(String.format(fmt, _f,msg));}
		}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[messageProcess completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
	}

	@Path(CampRest.ProcessControlDaoService.NOTIFY_PROCESS_GET) @PUT
	@Override
	public void messageProcess(@QueryParam("processInstanceId")String processInstanceId, @QueryParam("messageName")String messageName, @QueryParam("businessKey")String businessKey, @QueryParam("objectStatus")String objectStatus, @QueryParam("objectBusinessId")String objectBusinessId,
			@QueryParam("objectId")int objectId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[messageProcess]";
			msg = "====[ process control service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		
		Message m = new Message(messageName, businessKey, processInstanceId);
		m.setProcessInstanceId(processInstanceId);
		m.correlationKeys().variables().put("objectBusinessId", new VariableValue(objectBusinessId,VariableValueType.valueOf("String")));
		m.correlationKeys().variables().put("objectId", new VariableValue(String.valueOf(objectId),VariableValueType.valueOf("Integer")));
		m.processVariables().variables().put("objectBusinessId", new VariableValue(objectBusinessId,VariableValueType.valueOf("String")));
		m.processVariables().variables().put("objectId", new VariableValue(String.valueOf(objectId),VariableValueType.valueOf("Integer")));
		m.processVariables().variables().put("objectStatus",new VariableValue(objectStatus, VariableValueType.valueOf("String")));
		String json = m.toJson();
		
		String prefix = CampRest.ProcessEngine.Prefix;		
		String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.MESSAGE_PROCESSES);
		String uri = serverUrl+domainUri+serviceUri;
		String result = RestInterface.resultPost(uri,json, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION){msg = "----[SERVICE CALL RESULT: "+result+"]----";LOG.info(String.format(fmt, _f,msg));}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[messageProcess completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
	}

	@Path(CampRest.ProcessControlDaoService.NOTIFY_PROCESS_EVENT) @POST @Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void triggerMessageEvent(@QueryParam("processInstanceId")String processInstanceId, @QueryParam("businessKey")String businessKey, @QueryParam("messageName")String messageName, String variables) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[triggerMessageEvent]";
			msg = "====[ process control service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String prefix = CampRest.ProcessEngine.Prefix;		
		String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.GET_EXECUTIONS);
		String uri = serverUrl+domainUri+String.format(serviceUri,businessKey,processInstanceId);
		String result = RestInterface.resultGET(uri, !Util._IN_PRODUCTION);
		ProcessExecutionList el = ProcessExecutionList._fromJson(result);
		for(ProcessExecution ex:el) {
			serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.TRIGGER_MESSAGE_SUBSCRIPTION);
			uri = serverUrl+domainUri+String.format(serviceUri,ex.id(), messageName);
			result = RestInterface.resultPost(uri, variables, !Util._IN_PRODUCTION);
			if(!Util._IN_PRODUCTION){msg = "----[SERVICE CALL RESULT: "+result+"]----";LOG.info(String.format(fmt, _f,msg));}
		}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[triggerMessageEvent completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
	}

	@Path(CampRest.ProcessControlDaoService.SIGNAL_PROCESSES) @POST @Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void signalProcessList(String signalPacket) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[signalProcessList]";
			msg = "====[ process control service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		SignalPacket sp = SignalPacket._fromJson(signalPacket);
		ProcessList pl = sp.processList();
		String variables = sp.variables().toJson();
		for(Process<?> p : pl) {
			String prefix = CampRest.ProcessEngine.Prefix;
		
			String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.GET_EXECUTIONS);
			String uri = serverUrl+domainUri+String.format(serviceUri,p.businessKey(),p.instanceId());
			String result = RestInterface.resultGET(uri, !Util._IN_PRODUCTION);
			ProcessExecutionList el = ProcessExecutionList._fromJson(result);
			for(ProcessExecution ex:el) {
				serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.SIGNAL_PROCESS);
				uri = serverUrl+domainUri+String.format(serviceUri,ex.id());
				result = RestInterface.resultPost(uri, variables, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION){msg = "----[SERVICE CALL RESULT: "+result+"]----";LOG.info(String.format(fmt, _f,msg));}
			}
		}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[signalProcessList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
	}

	@Path(CampRest.ProcessControlDaoService.SIGNAL_PROCESS) @POST @Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void signalProcess(@QueryParam("processInstanceId")String processInstanceId, @QueryParam("businessKey")String businessKey, String variables) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[signalProcess]";
			msg = "====[ process control service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String prefix = CampRest.ProcessEngine.Prefix;
		String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.GET_EXECUTIONS);
		String uri = serverUrl+domainUri+String.format(serviceUri,businessKey,processInstanceId);
		String result = RestInterface.resultGET(uri, !Util._IN_PRODUCTION);
		ProcessExecutionList el = ProcessExecutionList._fromJson(result);
		for(ProcessExecution ex:el) {
			serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.SIGNAL_PROCESS);
			uri = serverUrl+domainUri+String.format(serviceUri,ex.id());
			result = RestInterface.resultPost(uri, variables, !Util._IN_PRODUCTION);
			if(!Util._IN_PRODUCTION){msg = "----[SERVICE CALL RESULT: "+result+"]----";LOG.info(String.format(fmt, _f,msg));}
		}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[signalProcess completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		
	}

	@Path(CampRest.ProcessControlDaoService.CLAIM_TASK) @PUT
	@Override
	public void claimTask(@QueryParam("taskId")String taskId, @QueryParam("userId")String userId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[claimTask]";
			msg = "====[ process control service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String prefix = CampRest.ProcessEngine.Prefix;		
		String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.CLAIM_TASK);
		String uri = serverUrl+domainUri+String.format(serviceUri,taskId, userId);
		String result = RestInterface.resultGET(uri, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION){msg = "----[SERVICE CALL RESULT: "+result+"]----";LOG.info(String.format(fmt, _f,msg));}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[claimTask completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
	}

	@Path(CampRest.ProcessControlDaoService.DELEGATE_TASK) @PUT
	@Override
	public void delegateTask(@QueryParam("taskId")String taskId, @QueryParam("userId")String userId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delegateTask]";
			msg = "====[ process control service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String prefix = CampRest.ProcessEngine.Prefix;		
		String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.DELEGATE_TASK);
		String uri = serverUrl+domainUri+String.format(serviceUri,taskId, userId);
		String result = RestInterface.resultGET(uri, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION){msg = "----[SERVICE CALL RESULT: "+result+"]----";LOG.info(String.format(fmt, _f,msg));}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[delegateTask completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
	}

	@Path(CampRest.ProcessControlDaoService.COMPLETE_CURRENT_TASK) @POST @Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void completeCurrentTask(@QueryParam("processInstanceId")String processInstanceId, @QueryParam("principal")String principal, String object) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[completeTask]";
			msg = "====[ process control service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String taskId = null;
		String variables = null;
		String prefix = CampRest.ProcessEngine.Prefix;		
		String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.GET_TASKS);
		String uri = serverUrl+domainUri+String.format(serviceUri,processInstanceId);
		String result = RestInterface.resultPost(uri, variables, !Util._IN_PRODUCTION);
		TaskList tl = TaskList._fromJson(result);
		Task t = tl.get(0);
		Variables v = null;
		Principal p = Principal.valueOf(principal);
		switch(p) {
		case Order:
			Order o = OrderInterface._fromJson(object);
			v = new Variables();
			v.add("objectId",new VariableValue(String.valueOf(o.id()), VariableValueType.Integer));
			v.add("objectBusinessId",new VariableValue(o.onlyBusinessId(), VariableValueType.String));
			v.add("objectStatus",new VariableValue(o.status().name(), VariableValueType.String));
			v.add("objectType", new VariableValue(o.getClass().getName(),VariableValueType.String));
			v.add("objectPrincipal", new VariableValue(p.name(),VariableValueType.String));
			variables = v.toJson();
			break;
		case Product:
			Product prd = ProductInterface._fromJson(object);
			v = new Variables();
			v.add("objectId",new VariableValue(String.valueOf(prd.id()), VariableValueType.Integer));
			v.add("objectBusinessId",new VariableValue(prd.onlyBusinessId(), VariableValueType.String));
			v.add("objectStatus",new VariableValue(prd.status().name(), VariableValueType.String));
			v.add("objectType", new VariableValue(prd.getClass().getName(),VariableValueType.String));
			v.add("objectPrincipal", new VariableValue(p.name(),VariableValueType.String));			
			variables = v.toJson();
			break;
		case Customer:
			Customer c = CustomerInterface._fromJson(object);
			v = new Variables();
			v.add("objectId",new VariableValue(String.valueOf(c.id()), VariableValueType.Integer));
			v.add("objectBusinessId",new VariableValue(c.onlyBusinessId(), VariableValueType.String));
			v.add("objectStatus",new VariableValue(c.status().name(), VariableValueType.String));
			v.add("objectType", new VariableValue(c.getClass().getName(),VariableValueType.String));
			v.add("objectPrincipal", new VariableValue(p.name(),VariableValueType.String));			
			variables = v.toJson();
			break;
		case Attribute:
			Attribute<?> a = AttributeInterface._fromJson(object);
			v = new Variables();
			v.add("objectId",new VariableValue(String.valueOf(a.id()), VariableValueType.Integer));
			v.add("objectBusinessId",new VariableValue(a.attributeBusinessKey(), VariableValueType.String));
			v.add("objectStatus",new VariableValue(a.status().name(), VariableValueType.String));
			v.add("objectType", new VariableValue(a.getClass().getName(),VariableValueType.String));
			v.add("objectPrincipal", new VariableValue(p.name(),VariableValueType.String));			
			variables = v.toJson();
			break;
		case Model:
			Model m = ModelInterface._fromJson(object);
			v = new Variables();
			v.add("objectId",new VariableValue(String.valueOf(m.id()), VariableValueType.Integer));
			v.add("objectBusinessId",new VariableValue(m.onlyBusinessId(), VariableValueType.String));
			v.add("objectStatus",new VariableValue(m.status().name(), VariableValueType.String));
			v.add("objectType", new VariableValue(m.getClass().getName(),VariableValueType.String));
			v.add("objectPrincipal", new VariableValue(p.name(),VariableValueType.String));			
			variables = v.toJson();
			break;
		default:
			break;
		}
		if(variables != null) {
			taskId = t.getId();	
			prefix = CampRest.ProcessEngine.Prefix;		
			serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.COMPLETE_TASK);
			uri = serverUrl+domainUri+String.format(serviceUri,taskId);
			result = RestInterface.resultPost(uri, variables, !Util._IN_PRODUCTION);
			if(!Util._IN_PRODUCTION){msg = "----[SERVICE CALL RESULT: "+result+"]----";LOG.info(String.format(fmt, _f,msg));}
		}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[completeTask completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
	}

	@Path(CampRest.ProcessControlDaoService.COMPLETE_TASK) @POST @Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void completeTask(@QueryParam("taskId")String taskId, String variables) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[completeTask]";
			msg = "====[ process control service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String prefix = CampRest.ProcessEngine.Prefix;		
		String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.COMPLETE_TASK);
		String uri = serverUrl+domainUri+String.format(serviceUri,taskId);
		String result = RestInterface.resultPost(uri, variables, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION){msg = "----[SERVICE CALL RESULT: "+result+"]----";LOG.info(String.format(fmt, _f,msg));}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[completeTask completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
	}

	@Path(CampRest.ProcessControlDaoService.GET_TASK) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String getTask(@QueryParam("taskId")String taskId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[getTask]";
			msg = "====[ process control service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String prefix = CampRest.ProcessEngine.Prefix;		
		String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.GET_TASK);
		String uri = serverUrl+domainUri+String.format(serviceUri,taskId);
		String result = RestInterface.resultGET(uri, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[getTask completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return result;
	}

	@Path(CampRest.ProcessControlDaoService.GET_TASKS_KEY) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String getTasks(@QueryParam("processInstanceId")String processInstanceId, @QueryParam("businessKey")String businessKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[getTasks]";
			msg = "====[ process control service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String prefix = CampRest.ProcessEngine.Prefix;		
		String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.GET_TASKS_KEY);
		String uri = serverUrl+domainUri+String.format(serviceUri,processInstanceId, businessKey);
		String result = RestInterface.resultGET(uri, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[getTasks completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return result;
	}

	@Path(CampRest.ProcessControlDaoService.GET_TASKS) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String getTasks(@QueryParam("processInstanceId")String processInstanceId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[getTasks]";
			msg = "====[ process control service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String prefix = CampRest.ProcessEngine.Prefix;		
		String serviceUri = CampRest.ProcessEngineDaoService.callRequest(prefix,CampRest.ProcessEngineDaoService.Request.GET_TASKS);
		String uri = serverUrl+domainUri+String.format(serviceUri,processInstanceId);
		String result = RestInterface.resultGET(uri, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[getTasks completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return result;
	}
	
	
}
