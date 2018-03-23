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
import com.camsolute.code.camp.lib.models.customer.ContactDetails;
import com.camsolute.code.camp.lib.models.customer.ContactDetailsDao;
import com.camsolute.code.camp.lib.models.customer.ContactDetailsInterface;
import com.camsolute.code.camp.lib.models.customer.ContactDetailsList;
import com.camsolute.code.camp.lib.models.customer.CustomerList;
import com.camsolute.code.camp.lib.utilities.Util;
import com.camsolute.code.camp.lib.dao.rest.ContactDetailsServicePointInterface;

//TODO:test,docs
@Path(CampRest.ContactDetails.Prefix)
public class ContactDetailsAPI implements ContactDetailsServicePointInterface {
	private static final Logger LOG = LogManager.getLogger(ContactDetailsAPI.class);
	private static String fmt = "[%15s] [%s]";
	
	@Path(CampRest.DaoService.LOAD_BY_ID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadById(@PathParam("id")int id) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadById]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ContactDetailsDao.instance().loadById(id,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadById completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ContactDetailsDaoService.LOAD_BY_EMAIL) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadByEmail(@QueryParam("emailAddress")String emailAddress) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByEmail]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ContactDetailsDao.instance().loadByEmail(emailAddress,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByEmail completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ContactDetailsDaoService.LOAD_BY_SKYPE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadBySkype(@QueryParam("skype")String skype) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadBySkype]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ContactDetailsDao.instance().loadBySkype(skype,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadBySkype completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ContactDetailsDaoService.LOAD_BY_TELEPHONE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadByPhone(@QueryParam("phoneNumber")String phoneNumber) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByPhone]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ContactDetailsDao.instance().loadByPhone(phoneNumber,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByPhone completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ContactDetailsDaoService.LOAD_BY_MOBILE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadByMobile(@QueryParam("mobileNumber")String mobileNumber) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByMobile]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ContactDetailsDao.instance().loadByMobile(mobileNumber,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByMobile completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ContactDetailsDaoService.LOAD_BY_MISC) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadByMisc(@QueryParam("misc")String misc) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByMisc]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ContactDetailsDao.instance().loadByMisc(misc,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByMisc completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.CREATE_CUSTOMER_DETAILS) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String create(@QueryParam("email")String email, @QueryParam("mobile")String mobile, @QueryParam("telephone")String telephone, @QueryParam("skype")String skype, @QueryParam("misc")String misc) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[create]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ContactDetailsDao.instance().create(email, mobile, telephone, skype, misc,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[create completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.SAVE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String save(String c) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[save]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		ContactDetails o = ContactDetailsInterface._fromJson(c);
		String json = ContactDetailsDao.instance().save(o,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[save completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.SAVE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String saveList(String cl) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[saveList]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		ContactDetailsList ol = ContactDetailsList._fromJson(cl);
		String json = ContactDetailsDao.instance().saveList(ol,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[saveList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String update(String c) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[update]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		ContactDetails o = ContactDetailsInterface._fromJson(c);
		String json = ContactDetailsDao.instance().update(o,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[update completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.UPDATE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String updateList(String cl) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[updateList]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		ContactDetailsList ol = ContactDetailsList._fromJson(cl);
		String json = ContactDetailsDao.instance().updateList(ol,!Util._IN_PRODUCTION).toJson();
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
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((ContactDetailsList)ContactDetailsDao.instance().loadUpdates(businessKey, target,!Util._IN_PRODUCTION)).toJson();
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
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((ContactDetailsList)ContactDetailsDao.instance().loadUpdatesByKey(businessKey,!Util._IN_PRODUCTION)).toJson();
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
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((ContactDetailsList)ContactDetailsDao.instance().loadUpdatesByTarget(target,!Util._IN_PRODUCTION)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.LOAD_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdate(@QueryParam("customerBusinessId")String customerBusinessId, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdate]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ContactDetailsDao.instance().loadUpdate(customerBusinessId, businessKey, target,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.ADD_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addToUpdates(@QueryParam("customerBusinessId")String customerBusinessId, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addToUpdates]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ContactDetailsDao.instance().addToUpdates(customerBusinessId, businessKey, target,!Util._IN_PRODUCTION);
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
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		CustomerList ol = CustomerList._fromJson(customerList);
		int retVal = ContactDetailsDao.instance().addListToUpdates(ol, businessKey, target,!Util._IN_PRODUCTION);
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
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ContactDetailsDao.instance().deleteAllFromUpdates(businessKey, target,!Util._IN_PRODUCTION);
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
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ContactDetailsDao.instance().deleteFromUpdatesByKey(businessKey,!Util._IN_PRODUCTION);
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
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ContactDetailsDao.instance().deleteFromUpdatesByTarget(target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.DaoService.DELETE_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteFromUpdates(@QueryParam("customerBusinessId")String customerBusinessId, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdates]";
			msg = "====[ ContactDetailsAPI service call: ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ContactDetailsDao.instance().deleteFromUpdates(customerBusinessId, businessKey, target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

}
