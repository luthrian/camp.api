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
import com.camsolute.code.camp.lib.models.customer.Address;
import com.camsolute.code.camp.lib.models.customer.AddressList;
import com.camsolute.code.camp.lib.models.customer.AddressDao;
import com.camsolute.code.camp.lib.models.customer.AddressInterface;
import com.camsolute.code.camp.lib.utilities.Util;
import com.camsolute.code.camp.lib.dao.rest.AddressServicePointInterface;

//TODO:test,docs
@Path(CampRest.Address.Prefix)
public class AddressAPI implements AddressServicePointInterface {
	private static final Logger LOG = LogManager.getLogger(AddressAPI.class);
	private static String fmt = "[%15s] [%s]";
	
	@Path(CampRest.DaoService.LOAD_BY_ID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadById(@PathParam("id")int id) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadById]";
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AddressDao.instance().loadById(id,!Util._IN_PRODUCTION).toJson();
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AddressDao.instance().loadByBusinessId(businessId,!Util._IN_PRODUCTION).toJson();
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((AddressList)AddressDao.instance().loadListByBusinessKey(businessKey,!Util._IN_PRODUCTION)).toJson();
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((AddressList)AddressDao.instance().loadListByGroup(group,!Util._IN_PRODUCTION)).toJson();
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((AddressList)AddressDao.instance().loadListByGroupVersion(group, version,!Util._IN_PRODUCTION)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadListByGroupVersion completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.SAVE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String save(String address) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[save]";
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Address o = AddressInterface._fromJson(address);
		String json = AddressDao.instance().save(o,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[save completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.SAVE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String saveList(String addressList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[saveList]";
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		AddressList ol = AddressList._fromJson(addressList);
		String json = AddressDao.instance().saveList(ol,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[saveList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String update(String address) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[update]";
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Address o = AddressInterface._fromJson(address);
		String json = AddressDao.instance().update(o,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[update completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.UPDATE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String updateList(String addressList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[updateList]";
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		AddressList ol = AddressList._fromJson(addressList);
		String json = AddressDao.instance().updateList(ol,!Util._IN_PRODUCTION).toJson();
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((AddressList)AddressDao.instance().loadUpdates(businessKey, target,!Util._IN_PRODUCTION)).toJson();
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((AddressList)AddressDao.instance().loadUpdatesByKey(businessKey,!Util._IN_PRODUCTION)).toJson();
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((AddressList)AddressDao.instance().loadUpdatesByTarget(target,!Util._IN_PRODUCTION)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.LOAD_UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdate(String address, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdate]";
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Address o = AddressInterface._fromJson(address);
		String json = AddressDao.instance().loadUpdate(o, businessKey, target,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.ADD_UPDATE_POST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addToUpdates(String address, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addToUpdates]";
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Address o = AddressInterface._fromJson(address);
		int retVal = AddressDao.instance().addToUpdates(o, businessKey, target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addToUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.DaoService.ADD_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addListToUpdates(String addressList, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addListToUpdates]";
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		AddressList ol = AddressList._fromJson(addressList);
		int retVal = AddressDao.instance().addToUpdates(ol, businessKey, target,!Util._IN_PRODUCTION);
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AddressDao.instance().deleteAllFromUpdates(businessKey, target,!Util._IN_PRODUCTION);
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AddressDao.instance().deleteFromUpdatesByKey(businessKey,!Util._IN_PRODUCTION);
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AddressDao.instance().deleteFromUpdatesByTarget(target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.DaoService.DELETE_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteFromUpdates(@QueryParam("addressId")String addressId, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdates]";
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AddressDao.instance().deleteFromUpdates(addressId, businessKey, target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.DaoService.DELETE_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteListFromUpdates(String addressList, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteListFromUpdates]";
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		AddressList ol = AddressList._fromJson(addressList);
		int retVal = AddressDao.instance().deleteFromUpdates(ol, businessKey, target,!Util._IN_PRODUCTION);
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AddressDao.instance().loadFirst(businessId).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadFirst completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_PREVIOUS) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadPrevious(String address) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadPrevious]";
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Address o = AddressInterface._fromJson(address);
		String json = AddressDao._loadPrevious(o,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadPrevious completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.InstanceDaoService.LOAD_NEXT) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadNext(String address) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadNext]";
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Address o = AddressInterface._fromJson(address);
		String json = AddressDao._loadNext(o,!Util._IN_PRODUCTION).toJson();
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((AddressList)AddressDao.instance().loadDate(businessId, date)).toJson();
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((AddressList)AddressDao.instance().loadDateRange(businessId, startDate, endDate)).toJson();
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((AddressList)AddressDao.instance().loadDate(date)).toJson();
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
			msg = "====[ AddressAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((AddressList)AddressDao.instance().loadDateRange(startDate, endDate)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadDateRange completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

}
