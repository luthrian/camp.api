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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.logging.LoggingFeature;

import com.camsolute.code.camp.lib.dao.rest.OrderServicePointInterface;
import com.camsolute.code.camp.lib.data.CampRest;
import com.camsolute.code.camp.lib.models.CampInstanceDao;
import com.camsolute.code.camp.lib.models.order.Order;
import com.camsolute.code.camp.lib.models.order.Order.UpdateAttribute;
import com.camsolute.code.camp.lib.models.order.OrderDao;
import com.camsolute.code.camp.lib.models.order.OrderInterface;
import com.camsolute.code.camp.lib.models.order.OrderList;
import com.camsolute.code.camp.lib.models.order.OrderPositionInterface;
import com.camsolute.code.camp.lib.models.order.OrderPositionList;
import com.camsolute.code.camp.lib.models.order.OrderRest;
import com.camsolute.code.camp.lib.models.process.ProcessList;
import com.camsolute.code.camp.lib.utilities.Util;


@Path(CampRest.Order.Prefix)
public class OrderAPI implements OrderServicePointInterface {
	private static final Logger LOG = LogManager.getLogger(OrderAPI.class);
	private static String fmt = "[%15s] [%s]";
	
	
	public static String serverUrl = CampRest.ORDER_API_SERVER_URL;

	public static String domainUri = CampRest.ORDER_API_DOMAIN;
	
	@Path(CampRest.DaoService.CREATE_ORDER) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String create(@QueryParam("businessId")String businessId, @QueryParam("businessKey")String businessKey, @QueryParam("date")String date, @QueryParam("byDate")String byDate) {
		boolean log = true;
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[create]";
			msg = "====[ order service api: create order ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		Order o = new Order(businessId, businessKey, byDate);
		o.setByDate(Util.Time.timestamp(byDate));
		
		o = OrderDao.instance().save(o,!Util._IN_PRODUCTION);
		
		String json = o.toJson();
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[ service call executed.]====";LOG.traceExit(String.format(fmt,_f,msg+time));
		}
		
		return json;
	}

	@Path(CampRest.DaoService.SAVE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String save(String order) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[save]";
			msg = "====[ save order ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		
		Order o = OrderInterface._fromJson(order);
		o = OrderDao.instance().save(o,!Util._IN_PRODUCTION);
			
		String json = o.toJson();
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[ service call executed.]====";LOG.traceExit(String.format(fmt,_f,msg+time));
		}
		
		return json;
	}
	
	@Path(CampRest.DaoService.SAVE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String saveList(String orderList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[_saveList]";
			msg = "====[ save order list ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		OrderList ol = OrderList._fromJson(orderList);
		
		ol = OrderDao.instance().saveList(ol, !Util._IN_PRODUCTION);
		
		String json = ol.toJson();
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[ service call executed.]====";LOG.traceExit(String.format(fmt,_f,msg+time));
		}
		
		return json;
}
			
	@Path(CampRest.DaoService.UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String update(String order){
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[update]";
			msg = "====[ update order ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		Order o = OrderInterface._fromJson(order);
		
		o = OrderDao.instance().update(o, !Util._IN_PRODUCTION);
		
		String json = o.toJson();
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[ service call executed.]====";LOG.traceExit(String.format(fmt,_f,msg+time));
		}
		
		return json;
	}
	
	@Path(CampRest.DaoService.UPDATE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String updateList(String orderList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[updateList]";
			msg = "====[ order service api: update list call ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		OrderList ol = OrderList._fromJson(orderList);
		ol = OrderDao.instance().updateList(ol, !Util._IN_PRODUCTION);
		
		String json = ol.toJson();
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[updateList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.UPDATE_ATTRIBUTE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String updateAttribute(@QueryParam("attributeType")String attributeType, @QueryParam("businessId")String businessId, @QueryParam("attributeValue")String attributeValue) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[updateAttribute]";
			msg = "====[ order service api: update order attribute ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = OrderDao.instance().updateAttribute(UpdateAttribute.valueOf(attributeType), businessId, attributeValue, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[updateAttribute completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}
	
	@Path(CampRest.DaoService.LOAD_BY_ID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadById(@PathParam("id")int id) {
			long startTime = System.currentTimeMillis();
			String _f = null;
			String msg = null;
			if(!Util._IN_PRODUCTION) {
				_f = "[load]";
				msg = "====[ order service api: load order by id '"+id+"' ]====";LOG.traceEntry(String.format(fmt,_f,msg));
			}
			
			String json = OrderDao.instance().loadById(id, !Util._IN_PRODUCTION).toJson();
			
			if(!Util._IN_PRODUCTION) {
				String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
				msg = "====[ service call executed.]====";LOG.traceExit(String.format(fmt,_f,msg+time));
			}
			
			return json;
	}

	@Path(CampRest.Order.Prefix + CampRest.DaoService.LOAD) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadByBusinessId(@QueryParam("businessId")String businessId) {
			long startTime = System.currentTimeMillis();
			String _f = null;
			String msg = null;
			if(!Util._IN_PRODUCTION) {
				_f = "[load]";
				msg = "====[ order service api: load order by '"+businessId+"' ]====";LOG.traceEntry(String.format(fmt,_f,msg));
			}
			
			String json = OrderDao.instance().loadByBusinessId(businessId, !Util._IN_PRODUCTION).toJson();
			
			if(!Util._IN_PRODUCTION) {
				String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
				msg = "====[ service call executed.]====";LOG.traceExit(String.format(fmt,_f,msg+time));
			}
			
			return json;
	}

	@Path(CampRest.DaoService.LOAD_BY_KEY) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadListByBusinessKey(@QueryParam("businessKey")String businessKey) {
			long startTime = System.currentTimeMillis();
			String _f = null;
			String msg = null;
			if(!Util._IN_PRODUCTION) {
				_f = "[loadListByBusinessId]";
				msg = "====[ order service api: load list of orders by business key'"+businessKey+"' ]====";LOG.traceEntry(String.format(fmt,_f,msg));
			}
			
			String json = OrderDao.instance().loadListByBusinessKey(businessKey, !Util._IN_PRODUCTION).toJson();
			
			if(!Util._IN_PRODUCTION) {
				String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
				msg = "====[ service call executed.]====";LOG.traceExit(String.format(fmt,_f,msg+time));
			}
			
			return json;
	}

	@Path(CampRest.DaoService.LOAD_BY_GROUP) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadListByGroup(@QueryParam("group")String group) {
			long startTime = System.currentTimeMillis();
			String _f = null;
			String msg = null;
			if(!Util._IN_PRODUCTION) {
				_f = "[loadListByGroup]";
				msg = "====[ order service api: load list of orders by group '"+group+"' ]====";LOG.traceEntry(String.format(fmt,_f,msg));
			}
			
			String json = OrderDao.instance().loadListByGroup(group, !Util._IN_PRODUCTION).toJson();
			
			if(!Util._IN_PRODUCTION) {
				String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
				msg = "====[loadListByGroup completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
			}
			return json;
	}

	@Path(CampRest.DaoService.LOAD_BY_GROUP_VERSION) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadListByGroupVersion(@QueryParam("group")String group,@QueryParam("version")String version) {
			long startTime = System.currentTimeMillis();
			String _f = null;
			String msg = null;
			if(!Util._IN_PRODUCTION) {
				_f = "[loadListByGroupVersion]";
				msg = "====[ order service api: load list of orders by group '"+group+"' and version '"+version+"']====";LOG.traceEntry(String.format(fmt,_f,msg));
			}
			
			String json = OrderDao.instance().loadListByGroupVersion(group, version, !Util._IN_PRODUCTION).toJson();
			
			if(!Util._IN_PRODUCTION) {
				String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
				msg = "====[loadListByGroupVersion completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
			}
			return json;
	}

	@Path(CampRest.DaoService.LOAD_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdates(@QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdates]";
			msg = "====[ order service call: load order object registered in the updates tables that share common businessKey and target identifiers ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((OrderList)OrderDao.instance().loadUpdates(businessKey, target, !Util._IN_PRODUCTION)).toJson();
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.LOAD_UPDATES_KEY) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdatesByKey(@QueryParam("businessKey")String businessKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdatesByKey]";
			msg = "====[ order service call: load list of orders registered in updates that share a common businessKey ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		
		String json = ((OrderList)OrderDao.instance().loadUpdatesByKey(businessKey, !Util._IN_PRODUCTION)).toJson();
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdatesByKey completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.LOAD_UPDATES_TARGET) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdatesByTarget(@QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdatesByTarget]";
			msg = "====[ order service call: load list of orders registered in updates that share a common target identifier ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((OrderList)OrderDao.instance().loadUpdatesByTarget(target, !Util._IN_PRODUCTION)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.LOAD_UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdate(String order, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdate]";
			msg = "====[ order service call: load an order that is registered in updates ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		
		String json = OrderDao.instance().loadUpdate(OrderInterface._fromJson(order), businessKey, target, !Util._IN_PRODUCTION).toJson();
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.ADD_UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int addToUpdates(String order, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addToUpdates]";
			msg = "====[ order service call: register an order in the updates table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().addToUpdates(OrderInterface._fromJson(order), businessKey, target, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addToUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.DaoService.ADD_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int addListToUpdates(String orderList, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addListToUpdates]";
			msg = "====[ order service call: register a list of order object instances in the updates table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().addToUpdates(OrderList._fromJson(orderList), businessKey, target, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addListToUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.DaoService.DELETE_ALL_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int deleteAllFromUpdates(@QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteAllFromUpdates]";
			msg = "====[ order service call: deregisterd all order entries in updates that share the common businessKey and target identifiers  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().deleteAllFromUpdates(businessKey, target, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteAllFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.DaoService.DELETE_KEY_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int deleteFromUpdatesByKey(@QueryParam("businessKey")String businessKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdatesByKey]";
			msg = "====[ order service call: deregister order entries in updates that share the common businessKey  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().deleteFromUpdatesByKey(businessKey, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdatesByKey completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}


	@Path(CampRest.DaoService.DELETE_TARGET_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int deleteFromUpdatesByTarget(@QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdatesByTarget]";
			msg = "====[ order service call: deregisterd all order entries in updates that share the common businessKey and target identifiers  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().deleteFromUpdatesByTarget(target, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.DaoService.DELETE_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int deleteFromUpdates(@QueryParam("businessId")String businessId, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdates]";
			msg = "====[ order service call: deregister an order entry from updates ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().deleteFromUpdates(businessId, businessKey, target, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.DaoService.DELETE_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int deleteListFromUpdates(String orderList, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteListFromUpdates]";
			msg = "====[ order service call: deregister a list of order entries from updates ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().deleteFromUpdates(OrderList._fromJson(orderList), businessKey, target, !Util._IN_PRODUCTION); 
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteListFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.ProcessReferenceDaoService.ADD_REFERENCE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int addProcessReference(@QueryParam("businessId")String businessId, @QueryParam("instanceId")String instanceId, @QueryParam("processKey")String processKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addProcessReference]";
			msg = "====[ order service call: register an association to a process in the reference table  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().addProcessReference(businessId, instanceId, processKey, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addProcessReference completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.ProcessReferenceDaoService.ADD_REFERENCES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int addProcessReferences(@QueryParam("businessId")String businessId, @QueryParam("processList")String processList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addProcessReferences]";
			msg = "====[ order service call: register association with a list of processes in the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().addProcessReferences(businessId, ProcessList._fromJson(processList), !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addProcessReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.ProcessReferenceDaoService.DEL_REFERENCE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int delProcessReference(@QueryParam("businessId")String businessId, @QueryParam("instanceId")String instanceId, @QueryParam("processKey")String processKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delProcessReference]";
			msg = "====[ order service call: deregister an associate with a process from the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().delProcessReference(businessId, instanceId, processKey, !Util._IN_PRODUCTION);
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[delProcessReference completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.ProcessReferenceDaoService.DEL_ALL_REFERENCES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int delAllProcessReferences(@QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delAllProcessReferences]";
			msg = "====[ order service call: deregister all processes associated with order from the reference table  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().delAllProcessReferences(businessId, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[delAllProcessReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.ProcessReferenceDaoService.DEL_REFERENCES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int delProcessReferences(@QueryParam("businessId")String businessId, String processList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delProcessReferences]";
			msg = "====[ order service call: deregister a list of processes associated with order from the reference table  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		ProcessList pl = ProcessList._fromJson(processList);
		int retVal = OrderDao.instance().delProcessReferences(businessId,pl, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[delProcessReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.ProcessReferenceDaoService.LOAD) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadProcessReferences(@QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadProcessReferences]";
			msg = "====[ order service call: load all associated processes registered in the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((ProcessList)OrderDao.instance().loadProcessReferences(businessId, !Util._IN_PRODUCTION)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadProcessReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.OrderPositionReferenceDaoService.Prefix+CampRest.ReferenceDaoService.ADD_REFERENCE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int addOrderPositionReference(@QueryParam("parentBusinessId")String orderBusinessId, @QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addOrderPositionReference]";
			msg = "====[ order service call: register order position association in reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().addOrderPositionReference(orderBusinessId, businessId, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addOrderPositionReference completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.OrderPositionReferenceDaoService.Prefix+CampRest.ReferenceDaoService.ADD_REFERENCES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int addOrderPositionReferences(@QueryParam("parentBusinessId")String orderBusinessId, @QueryParam("orderPositionList")String orderPositionList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addOrderPositionReferences]";
			msg = "====[ order service call: register a list of associated order position objects in the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().addOrderPositionReferences(orderBusinessId, OrderPositionList._fromJson(orderPositionList), !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addOrderPositionReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.OrderPositionReferenceDaoService.Prefix+CampRest.ReferenceDaoService.DEL_REFERENCE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int delOrderPositionReference(@QueryParam("parentBusinessId")String orderBusinessId, @QueryParam("buisinessId")String buisinessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delOrderPositionReference]";
			msg = "====[ order service call: deregister an order position association from the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().delOrderPositionReference(orderBusinessId, buisinessId, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[delOrderPositionReference completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.OrderPositionReferenceDaoService.Prefix+CampRest.ReferenceDaoService.DEL_REFERENCES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int delOrderPositionReferences(@QueryParam("orderBusinessId")String orderBusinessId, String orderPositionList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delOrderPositionReferences]";
			msg = "====[ order service call: deregister a list of associated order position objects from the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = OrderDao.instance().delOrderPositionReferences(orderBusinessId, OrderPositionList._fromJson(orderPositionList), !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[delOrderPositionReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}

	@Path(CampRest.OrderPositionReferenceDaoService.Prefix+CampRest.ReferenceDaoService.LOAD) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadOrderPositions(@QueryParam("orderBusinessId")String orderBusinessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadOrderPositions]";
			msg = "====[ order service call: load all associated order position objects registered in the refrence table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = OrderDao.instance().loadOrderPositions(orderBusinessId, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadOrderPositions completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_FIRST) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadFirst(@QueryParam("businessId")String businessId, @QueryParam("primary")boolean primary) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadFirst]";
			msg = "====[ order service call: load first order object instance from history ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((Order)CampInstanceDao.instance().loadFirst(businessId, OrderDao.instance(), primary)).toJson();
		} catch (SQLException e) {
			if(!Util._IN_PRODUCTION){msg = "----[SQL ERROR! loadFirst FAILED]----";LOG.info(String.format(fmt, _f,msg));}
			e.printStackTrace();
		}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadFirst completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_NEXT) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadNext(String order, @QueryParam("primary")boolean primary) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadNext]";
			msg = "====[ order service call: load next order object instance from history ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((Order)CampInstanceDao.instance().loadNext(OrderInterface._fromJson(order), OrderDao.instance(), primary)).toJson();
		} catch (SQLException e) {
			if(!Util._IN_PRODUCTION){msg = "----[SQL ERROR! loadFirst FAILED]----";LOG.info(String.format(fmt, _f,msg));}
			e.printStackTrace();
		}		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadNext completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_PREVIOUS) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadPrevious(String order, @QueryParam("primary")boolean primary) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadPrevious]";
			msg = "====[ order service call: load previous order object instance from history ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((Order)CampInstanceDao.instance().loadPrevious(OrderInterface._fromJson(order), OrderDao.instance(), primary)).toJson();
		} catch (SQLException e) {
			if(!Util._IN_PRODUCTION){msg = "----[SQL ERROR! loadFirst FAILED]----";LOG.info(String.format(fmt, _f,msg));}
			e.printStackTrace();
		}		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadPrevious completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_DATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadDate(@QueryParam("date")String date, @QueryParam("primary")boolean primary) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDate]";
			msg = "====[ order service call: load list of current order instances from history based on their timestamp date ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((OrderList)CampInstanceDao.instance().loadDate(date, OrderDao.instance(), primary)).toJson();
		} catch (SQLException e) {
			if(!Util._IN_PRODUCTION){msg = "----[SQL ERROR! loadFirst FAILED]----";LOG.info(String.format(fmt, _f,msg));}
			e.printStackTrace();
		}		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadDate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_DATE_RANGE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadDateRange(@QueryParam("startDate")String startDate, @QueryParam("endDate")String endDate, @QueryParam("primary")boolean primary) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDateRange]";
			msg = "====[ order service call: load a list of current order instances from history if their timestamp lies within the date range ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((OrderList)CampInstanceDao.instance().loadDateRange(startDate, endDate, OrderDao.instance(), primary)).toJson();
		} catch (SQLException e) {
			if(!Util._IN_PRODUCTION){msg = "----[SQL ERROR! loadFirst FAILED]----";LOG.info(String.format(fmt, _f,msg));}
			e.printStackTrace();
		}			
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadDateRange completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_DATE_BY_BID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadDate(@QueryParam("businessId")String businessId, @QueryParam("date")String date, @QueryParam("primary")boolean primary) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDate]";
			msg = "====[ order service call: load a list of order object historical instances based on their timestamp dates and the businessId  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((OrderList)CampInstanceDao.instance().loadDate(businessId,date, OrderDao.instance(), primary)).toJson();
		} catch (SQLException e) {
			if(!Util._IN_PRODUCTION){msg = "----[SQL ERROR! loadFirst FAILED]----";LOG.info(String.format(fmt, _f,msg));}
			e.printStackTrace();
		}		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadDate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_DATE_RANGE_BY_BID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadDateRange(@QueryParam("businessId")String businessId, @QueryParam("startDate")String startDate, @QueryParam("endDate")String endDate, @QueryParam("primary")boolean primary) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDateRange]";
			msg = "====[ order service call: load a list of order object historical instances based on their the businessId and if their timestamp lies within the date range ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((OrderList)CampInstanceDao.instance().loadDateRange(businessId, startDate, endDate, OrderDao.instance(), primary)).toJson();
		} catch (SQLException e) {
			if(!Util._IN_PRODUCTION){msg = "----[SQL ERROR! loadFirst FAILED]----";LOG.info(String.format(fmt, _f,msg));}
			e.printStackTrace();
		}		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadDateRange completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}


	@Path(CampRest.InstanceDaoService.ADD_INSTANCE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int addInstance(String orderPosition, @QueryParam("useObjectId")boolean useObjectId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addInstance]";
			msg = "====[ order service call: add current order object instance to history ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = 0;
		try {
			retVal = CampInstanceDao.instance().addInstance(OrderPositionInterface._fromJson(orderPosition),useObjectId);
		} catch (SQLException e) {
			if(!Util._IN_PRODUCTION){msg = "----[SQL ERROR! loadFirst FAILED]----";LOG.info(String.format(fmt, _f,msg));}
			e.printStackTrace();
		}		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addInstance completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return retVal;
	}


}
