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

import com.camsolute.code.camp.lib.dao.rest.ProcessServicePointInterface;
import com.camsolute.code.camp.lib.data.CampRest;
import com.camsolute.code.camp.lib.models.process.Process.ProcessType;
import com.camsolute.code.camp.lib.models.process.Process;
import com.camsolute.code.camp.lib.models.process.ProcessDao;
import com.camsolute.code.camp.lib.models.process.ProcessInterface;
import com.camsolute.code.camp.lib.models.process.ProcessList;
import com.camsolute.code.camp.lib.utilities.Util;

@Path(CampRest.Process.Prefix)
public class ProcessAPI implements ProcessServicePointInterface {

	private static final Logger LOG = LogManager.getLogger(ProcessAPI.class);
	private static String fmt = "[%15s] [%s]";
	
	@Path(CampRest.ProcessDaoService.LOAD_BY_ID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadById(@PathParam("id")int id) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadById]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProcessDao.instance().loadById(id,!Util._IN_PRODUCTION).toJson(); 
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadById completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ProcessDaoService.LOAD_BY_INSTANCE_ID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadByInstanceId(@QueryParam("instanceId")String instanceId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByInstanceId]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProcessDao.instance().loadByInstanceId(instanceId,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByInstanceId completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ProcessDaoService.LOAD_BY_BUSINESS_ID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadListByBusinessId(@QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadListByBusinessId]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProcessDao.instance().loadListByBusinessId(businessId,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadListByBusinessId completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}
	
	@Path(CampRest.ProcessDaoService.LOAD_BY_KEY) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadListByKey(@QueryParam("businessKey")String businessKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadListByKey]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProcessDao.instance().loadListByKey(businessKey,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadListByKey completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}
	
	@Path(CampRest.ProcessDaoService.LOAD_LIST) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadList() {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadList]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProcessDao.instance().loadList(!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ProcessDaoService.CREATE_EID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String create(@QueryParam("businessId")String businessId, @QueryParam("executionId")String executionId, @QueryParam("instanceId")String instanceId, @QueryParam("businessKey")String businessKey, @QueryParam("processName")String processName, @QueryParam("definitionId")String definitionId, @QueryParam("tenantId")String tenantId, @QueryParam("caseInstanceId")String caseInstanceId, @QueryParam("ended")boolean ended, @QueryParam("suspended")boolean suspended, @QueryParam("type")String type) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[create]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Process<?> p = ProcessInterface._process(businessId,executionId,instanceId,businessKey,processName,definitionId,tenantId,caseInstanceId,ended,suspended,ProcessType.valueOf(type));
		String json = ProcessDao.instance().save(p,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[create completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ProcessDaoService.CREATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String create(@QueryParam("businessId")String businessId, @QueryParam("instanceId")String instanceId, @QueryParam("businessKey")String businessKey, @QueryParam("processName")String processName, @QueryParam("definitionId")String definitionId, @QueryParam("tenantId")String tenantId, @QueryParam("caseInstanceId")String caseInstanceId, @QueryParam("ended")boolean ended, @QueryParam("suspended")boolean suspended, @QueryParam("type")String type) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[create]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Process<?> p = ProcessInterface._process(businessId,instanceId,businessKey,processName,definitionId,tenantId,caseInstanceId,ended,suspended,ProcessType.valueOf(type));
		String json = ProcessDao.instance().save(p,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[create completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ProcessDaoService.SAVE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String save(String process) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[save]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Process<?> p = ProcessInterface._fromJson(process);
		String json = ProcessDao.instance().save(p,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[save completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ProcessDaoService.SAVE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String saveList(String processList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[saveList]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		ProcessList pl = ProcessList._fromJson(processList);
		String json = ProcessDao.instance().saveList(pl,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[saveList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ProcessDaoService.UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String update(String process) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[update]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		Process<?> p = ProcessInterface._fromJson(process);
		int retVal = ProcessDao.instance().update(p,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[update completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessDaoService.UPDATE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String updateList(String processList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[updateList]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		ProcessList pl = ProcessList._fromJson(processList);
		int retVal = ProcessDao.instance().updateList(pl,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[updateList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessDaoService.LOAD_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdates(@QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdates]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProcessDao.instance().loadUpdates(businessKey,target,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ProcessDaoService.LOAD_UPDATES_KEY) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdatesByKey(@QueryParam("businessKey")String businessKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdatesByKey]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProcessDao.instance().loadUpdatesByKey(businessKey,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdatesByKey completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ProcessDaoService.LOAD_UPDATES_TARGET) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdatesByTarget(@QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdatesByTarget]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProcessDao.instance().loadUpdatesByTarget(target,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ProcessDaoService.LOAD_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdate(@QueryParam("instanceId")String instanceId, @QueryParam("businessId")String businessId, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdate]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProcessDao.instance().loadUpdate(instanceId,businessId,businessKey,target,!Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.ProcessDaoService.ADD_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addToUpdates(@QueryParam("instanceId")String instanceId, @QueryParam("businessId")String businessId, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addToUpdates]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProcessDao.instance().addToUpdates(instanceId,businessId,businessKey,target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addToUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessDaoService.ADD_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addToUpdates(String processList, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addToUpdates]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		ProcessList pl = ProcessList._fromJson(processList);
		int retVal = ProcessDao.instance().addToUpdates(pl,businessKey,target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addToUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessDaoService.DELETE_ALL_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteAllFromUpdates(@QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteAllFromUpdates]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProcessDao.instance().deleteAllFromUpdates(businessKey,target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteAllFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessDaoService.DELETE_KEY_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteFromUpdatesByKey(@QueryParam("businessKey")String businessKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdatesByKey]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProcessDao.instance().deleteFromUpdatesByKey(businessKey,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdatesByKey completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessDaoService.DELETE_TARGET_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteFromUpdatesByTarget(@QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdatesByTarget]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProcessDao.instance().deleteFromUpdatesByTarget(target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessDaoService.DELETE_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteFromUpdates(@QueryParam("instanceId")String instanceId, @QueryParam("businessId")String businessId, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdates]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProcessDao.instance().deleteFromUpdates(instanceId,businessId,businessKey,target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessDaoService.DELETE_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteFromUpdates(String processList, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdates]";
			msg = "====[ process service api : ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		ProcessList pl = ProcessList._fromJson(processList);
		int retVal = ProcessDao.instance().deleteFromUpdates(pl,businessKey,target,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

}
