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

import com.camsolute.code.camp.lib.data.CampRest;
import com.camsolute.code.camp.lib.models.customer.Customer;
import com.camsolute.code.camp.lib.models.customer.CustomerDao;
import com.camsolute.code.camp.lib.models.customer.CustomerInterface;
import com.camsolute.code.camp.lib.models.customer.CustomerList;
import com.camsolute.code.camp.lib.models.process.ProcessList;
import com.camsolute.code.camp.lib.utilities.Util;
import com.camsolute.code.camp.lib.dao.rest.CustomerServicePointInterface;

@Path(CampRest.Customer.Prefix)
public class CustomerAPI implements CustomerServicePointInterface {
	private static final Logger LOG = LogManager.getLogger(CustomerAPI.class);
	private static String fmt = "[%15s] [%s]";
	
	@Path(CampRest.DaoService.LOAD_BY_ID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadById(@PathParam("id")int id) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadById]";
			msg = "====[ customer service call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = CustomerDao.instance().loadById(id, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadById completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.LOAD) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadByBusinessId(@QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByBusinessId]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = CustomerDao.instance().loadByBusinessId(businessId,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByBusinessId completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
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
			_f = "[loadListByBusinessKey]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((CustomerList)CustomerDao.instance().loadListByBusinessKey(businessKey,!Util._IN_PRODUCTION)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadListByBusinessKey completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
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
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((CustomerList)CustomerDao.instance().loadListByGroup(group,!Util._IN_PRODUCTION)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadListByGroup completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.LOAD_BY_GROUP_VERSION) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadListByGroupVersion(@QueryParam("group")String group, @QueryParam("version")String version) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadListByGroupVersion]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((CustomerList)CustomerDao.instance().loadListByGroupVersion(group, version,!Util._IN_PRODUCTION)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadListByGroupVersion completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.SAVE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String save(String customer) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[save]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Customer c = CustomerInterface._fromJson(customer);
		String json = CustomerDao.instance().save(c,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[save completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.SAVE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String saveList(String customerList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[saveList]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		CustomerList ol = CustomerList._fromJson(customerList);
		String json = CustomerDao.instance().saveList(ol,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[saveList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String update(String customer) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[update]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Customer o = CustomerInterface._fromJson(customer);
		String json = CustomerDao.instance().update(o,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[update completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.UPDATE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String updateList(String customerList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[updateList]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		CustomerList ol = CustomerList._fromJson(customerList);
		String json = CustomerDao.instance().updateList(ol,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[updateList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
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
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((CustomerList)CustomerDao.instance().loadUpdates(businessKey, target,!Util._IN_PRODUCTION)).toJson();
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
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((CustomerList)CustomerDao.instance().loadUpdatesByKey(businessKey,!Util._IN_PRODUCTION)).toJson();
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
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((CustomerList)CustomerDao.instance().loadUpdatesByTarget(target,!Util._IN_PRODUCTION)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.LOAD_UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdate(String customer, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdate]";
			msg = "====[ Customer service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Customer c = CustomerInterface._fromJson(customer);
		String json = CustomerDao.instance().loadUpdate(c, businessKey, target,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.ADD_UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addToUpdates(String customer, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addToUpdates]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Customer o = CustomerInterface._fromJson(customer);
		int retVal = CustomerDao.instance().addToUpdates(o, businessKey, target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addToUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.DaoService.ADD_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addListToUpdates(String customerList, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addListToUpdates]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		CustomerList ol = CustomerList._fromJson(customerList);
		int retVal = CustomerDao.instance().addToUpdates(ol, businessKey, target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addListToUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.DaoService.DELETE_ALL_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteAllFromUpdates(@QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteAllFromUpdates]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = CustomerDao.instance().deleteAllFromUpdates(businessKey, target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteAllFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.DaoService.DELETE_KEY_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteFromUpdatesByKey(@QueryParam("businessKey")String businessKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdatesByKey]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = CustomerDao.instance().deleteFromUpdatesByKey(businessKey,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdatesByKey completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.DaoService.DELETE_TARGET_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteFromUpdatesByTarget(@QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdatesByTarget]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = CustomerDao.instance().deleteFromUpdatesByTarget(target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.DaoService.DELETE_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteFromUpdates(@QueryParam("businessId")String businessId, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdates]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = CustomerDao.instance().deleteFromUpdates(businessId, businessKey, target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.DaoService.DELETE_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteListFromUpdates(String customerList, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteListFromUpdates]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		CustomerList ol = CustomerList._fromJson(customerList);
		int retVal = CustomerDao.instance().deleteFromUpdates(ol, businessKey, target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteListFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.InstanceDaoService.LOAD_FIRST) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadFirst(@QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadFirst]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = CustomerDao.instance().loadFirst(businessId,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadFirst completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_PREVIOUS) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadPrevious(String customer) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadPrevious]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Customer o = CustomerInterface._fromJson(customer);
		String json = CustomerDao.instance().loadPrevious(o,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadPrevious completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_NEXT) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadNext(String customer) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadNext]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Customer o = CustomerInterface._fromJson(customer);
		String json = CustomerDao.instance().loadNext(o,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadNext completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_DATE_BY_BID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadDate(@QueryParam("businessId")String businessId, @QueryParam("date")String date) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDate]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((CustomerList)CustomerDao.instance()._loadDate(businessId, date,!Util._IN_PRODUCTION)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadDate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_DATE_RANGE_BY_BID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadDateRange(@QueryParam("businessId")String businessId, @QueryParam("startDate")String startDate, @QueryParam("endDate")String endDate) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDateRange]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((CustomerList)CustomerDao.instance().loadDateRange(businessId, startDate, endDate)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadDateRange completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_DATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadDate(@QueryParam("date")String date) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDate]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((CustomerList)CustomerDao.instance().loadDate(date)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadDate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_DATE_RANGE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadDateRange(@QueryParam("startDate")String startDate, @QueryParam("endDate")String endDate) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDateRange]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((CustomerList)CustomerDao.instance().loadDateRange(startDate, endDate)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadDateRange completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ProcessReferenceDaoService.ADD_REFERENCE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addProcessReference(@QueryParam("businessId")String businessId, @QueryParam("instanceId")String instanceId, @QueryParam("processKey")String processKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addProcessReference]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = CustomerDao.instance().addProcessReference(businessId, instanceId, processKey,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addProcessReference completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessReferenceDaoService.ADD_REFERENCES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addProcessReferences(@QueryParam("businessId")String businessId, String processList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addProcessReferences]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		ProcessList ol = ProcessList._fromJson(processList);
		int retVal = CustomerDao.instance().addProcessReferences(businessId,ol,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addProcessReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessReferenceDaoService.DEL_REFERENCE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String delProcessReference(@QueryParam("businessId")String businessId, @QueryParam("instanceId")String instanceId, @QueryParam("processKey")String processKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delProcessReference]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = CustomerDao.instance().delProcessReference(businessId, instanceId, processKey,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[delProcessReference completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessReferenceDaoService.DEL_ALL_REFERENCES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String delAllProcessReferences(@QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delAllProcessReferences]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = CustomerDao.instance().delAllProcessReferences(businessId,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[delAllProcessReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessReferenceDaoService.DEL_REFERENCES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String delProcessReferences(@QueryParam("businessId")String businessId, String processList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delProcessReferences]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		ProcessList ol = ProcessList._fromJson(processList);
		int retVal = CustomerDao.instance().delProcessReferences(businessId,ol,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[delProcessReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessReferenceDaoService.LOAD) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadProcessReferences(@QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadProcessReferences]";
			msg = "====[ CustomerAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((ProcessList)CustomerDao.instance().loadProcessReferences(businessId,!Util._IN_PRODUCTION)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadProcessReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

}
