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

import com.camsolute.code.camp.lib.dao.rest.ModelServicePointInterface;
import com.camsolute.code.camp.lib.models.ModelDao;
import com.camsolute.code.camp.lib.models.ModelInterface;
import com.camsolute.code.camp.lib.models.ModelList;
import com.camsolute.code.camp.lib.utilities.Util;

public class ModelAPI implements ModelServicePointInterface {
	private static final Logger LOG = LogManager.getLogger(ModelAPI.class);
	private static String fmt = "[%15s] [%s]";
	
	@Path(CampRest.Model.Prefix+CampRest.DaoService.LOAD_BY_ID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadById(@PathParam("id")int id) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadById]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().loadById(id, !Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadById completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.LOAD) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadByBusinessId(@QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByBusinessId]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().loadByBusinessId(businessId, !Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadByBusinessId completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.LOAD_BY_KEY) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadListByBusinessKey(@QueryParam("businessKey")String businessKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadListByBusinessKey]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((ModelList)ModelDao.instance().loadListByBusinessKey(businessKey, !Util._IN_PRODUCTION)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadListByBusinessKey completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.LOAD_BY_GROUP) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadListByGroup(@QueryParam("group")String group) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadListByGroup]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((ModelList)ModelDao.instance().loadListByGroup(group, !Util._IN_PRODUCTION)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadListByGroup completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.LOAD_BY_GROUP_VERSION) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadListByGroupVersion(@QueryParam("group")String group, @QueryParam("version")String version) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadListByGroupVersion]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((ModelList)ModelDao.instance().loadListByGroupVersion(group, version, !Util._IN_PRODUCTION)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadListByGroupVersion completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.SAVE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String save(String model) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[save]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().save(ModelInterface._fromJson(model),!Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[save completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.SAVE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String saveList(String modelList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[saveList]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().saveList(ModelList._fromJson(modelList),!Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[saveList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String update(String model) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[update]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().update(ModelInterface._fromJson(model),!Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[update completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.UPDATE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String updateList(String modelList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[updateList]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().updateList(ModelList._fromJson(modelList),!Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[updateList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.LOAD_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdates(@QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdates]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((ModelList)ModelDao.instance().loadUpdates(businessKey, target, !Util._IN_PRODUCTION)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.LOAD_UPDATES_KEY) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdatesByKey(@QueryParam("businessKey")String businessKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdatesByKey]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((ModelList)ModelDao.instance().loadUpdatesByKey(businessKey, !Util._IN_PRODUCTION)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadUpdatesByKey completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.LOAD_UPDATES_TARGET) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdatesByTarget(@QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdatesByTarget]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((ModelList)ModelDao.instance().loadUpdatesByTarget(target, !Util._IN_PRODUCTION)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.LOAD_UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdate(String model, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdate]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().loadUpdate(ModelInterface._fromJson(model), businessKey, target,!Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadUpdate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.ADD_UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int addToUpdates(String model, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addToUpdates]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ModelDao.instance().addToUpdates(ModelInterface._fromJson(model), businessKey, target, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[addToUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.ADD_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int addListToUpdates(String modelList, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addListToUpdates]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ModelDao.instance().addToUpdates(ModelList._fromJson(modelList), businessKey, target, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[addListToUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.DELETE_ALL_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int deleteAllFromUpdates(@QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteAllFromUpdates]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ModelDao.instance().deleteAllFromUpdates(businessKey, target, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteAllFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.DELETE_KEY_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int deleteFromUpdatesByKey(@QueryParam("businessKey")String businessKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdatesByKey]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ModelDao.instance().deleteFromUpdatesByKey(businessKey, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteFromUpdatesByKey completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.DELETE_TARGET_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int deleteFromUpdatesByTarget(@QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdatesByTarget]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ModelDao.instance().deleteFromUpdatesByTarget(target, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteFromUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.DELETE_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int deleteFromUpdates(@QueryParam("businessId")String businessId, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdates]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ModelDao.instance().deleteFromUpdates(businessId, businessKey, target, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.Model.Prefix+CampRest.DaoService.DELETE_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int deleteListFromUpdates(String modelList, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteListFromUpdates]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ModelDao.instance().deleteFromUpdates(ModelList._fromJson(modelList), businessKey, target, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteListFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.Model.Prefix+CampRest.InstanceDaoService.LOAD_FIRST) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadFirst(@QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadFirst]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().loadFirst(businessId).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadFirst completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.InstanceDaoService.LOAD_PREVIOUS) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadPrevious(String model) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadPrevious]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().loadPrevious(ModelInterface._fromJson(model)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadPrevious completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.InstanceDaoService.LOAD_NEXT) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadNext(String model) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadNext]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().loadNext(ModelInterface._fromJson(model)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadNext completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.InstanceDaoService.LOAD_DATE_BY_BID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadDate(@QueryParam("businessId")String businessId, @QueryParam("date")String date) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDate]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().loadDate(businessId, date).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadDate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.InstanceDaoService.LOAD_DATE_RANGE_BY_BID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadDateRange(@QueryParam("businessId")String businessId, @QueryParam("startDate")String startDate, @QueryParam("endDate")String endDate) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDateRange]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().loadDateRange(businessId, startDate, endDate).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadDateRange completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.InstanceDaoService.LOAD_DATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadDate(@QueryParam("date")String date) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDate]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().loadDate(date).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadDate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Model.Prefix+CampRest.InstanceDaoService.LOAD_DATE_RANGE_BY_BID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadDateRange(@QueryParam("startDate")String startDate, @QueryParam("endDate")String endDate) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDateRange]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ModelDao.instance().loadDateRange(startDate, endDate).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadDateRange completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

}
