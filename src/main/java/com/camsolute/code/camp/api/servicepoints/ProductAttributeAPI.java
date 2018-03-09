/*******************************************************************************
 * Copyright (C) 2017 Christopher Campbell (campbellccc@gmail.com)
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
 * 	Christopher Campbell (campbellccc@gmail.com) - all code prior and post initial release
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

import com.camsolute.code.camp.lib.dao.rest.AttributeServicePointInterface;
import com.camsolute.code.camp.lib.data.CampRest;
import com.camsolute.code.camp.lib.models.Attribute.AttributeType;
import com.camsolute.code.camp.lib.models.AttributeDao;
import com.camsolute.code.camp.lib.models.AttributeInterface;
import com.camsolute.code.camp.lib.models.AttributeList;
import com.camsolute.code.camp.lib.models.AttributeMap;
import com.camsolute.code.camp.lib.utilities.Util;

@Path("/product")
public class ProductAttributeAPI implements AttributeServicePointInterface{

	private static final Logger LOG = LogManager.getLogger(ProductAttributeAPI.class);
	private static String fmt = "[%15s] [%s]";
	
	@Path(CampRest.AttributeDefinition.Prefix+CampRest.AttributeDefinitionDaoService.LOAD_BY_ID) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadById(@PathParam("id")int id) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadById]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadById(id, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadById completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.LOAD) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByBusinessId(@QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByBusinessId]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadByBusinessId(businessId, !Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadByBusinessId completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.LOAD_BY_KEY) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadListByBusinessKey(@QueryParam("businessKey")String businessKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadListByBusinessKey]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadListByBusinessKey(businessKey, !Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadListByBusinessKey completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.SAVE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String save(String attribute) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[save]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().save(AttributeInterface._fromJson(attribute), !Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[save completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.SAVE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String saveList(String attributeList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[saveList]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().saveList(AttributeList._fromJson(attributeList), !Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[saveList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String update(String attribute) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[update]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().update(AttributeInterface._fromJson(attribute),!Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[update completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.UPDATE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String updateList(String attributeList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[updateList]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().updateList(AttributeList._fromJson(attributeList),!Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[updateList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.LOAD_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadUpdates(@QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdates]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((AttributeList)AttributeDao.instance().loadUpdates(businessKey, target, !Util._IN_PRODUCTION)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.LOAD_UPDATES_KEY) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadUpdatesByKey(@QueryParam("businessKey")String businessKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdatesByKey]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((AttributeList)AttributeDao.instance().loadUpdatesByKey(businessKey, !Util._IN_PRODUCTION)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadUpdatesByKey completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.LOAD_UPDATES_TARGET) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadUpdatesByTarget(@QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdatesByTarget]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((AttributeList)AttributeDao.instance().loadUpdatesByTarget(target, !Util._IN_PRODUCTION)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.LOAD_UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdate(String attribute, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdate]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadUpdate(AttributeInterface._fromJson(attribute), businessKey, target, !Util._IN_PRODUCTION).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadUpdate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.ADD_UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int addToUpdates(String attribute, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addToUpdates]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().addToUpdates(AttributeInterface._fromJson(attribute), businessKey, target, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[addToUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.ADD_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int addListToUpdates(String attributeList, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addListToUpdates]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().addToUpdates(AttributeList._fromJson(attributeList), businessKey, target, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[addListToUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.DELETE_ALL_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public int deleteAllFromUpdates(@QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteAllFromUpdates]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().deleteAllFromUpdates(businessKey, target, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteAllFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.DELETE_KEY_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public int deleteFromUpdatesByKey(@QueryParam("businessKey")String businessKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdatesByKey]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().deleteFromUpdatesByKey(businessKey, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteFromUpdatesByKey completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.DELETE_TARGET_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public int deleteFromUpdatesByTarget(@QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdatesByTarget]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().deleteFromUpdatesByTarget(target, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteFromUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.DELETE_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public int deleteFromUpdates(@QueryParam("instanceId")String instanceId, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteFromUpdates]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().deleteFromUpdates(instanceId, businessKey, target, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.DaoService.DELETE_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int deleteListFromUpdates(String attributeList, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteListFromUpdates]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().deleteFromUpdates(AttributeList._fromJson(attributeList), businessKey, target, !Util._IN_PRODUCTION);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteListFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.AttributeDefinitionDaoService.DELETE_BY_ID) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public int deleteById(@PathParam("id")int id) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteById]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().delete(id);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteById completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.AttributeDefinitionDaoService.DELETE_BY_BUSINESS_ID) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public int deleteByBusinessId(@QueryParam("attributeName")String attributeName) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteByBusinessId]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().delete(attributeName);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteByBusinessId completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.AttributeDefinitionDaoService.DELETE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int deleteList(String attributeList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteList]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().deleteList(AttributeList._fromJson(attributeList));
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.AttributeDefinitionDaoService.DELETE_BY_PARENT_ID) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public int deleteList(@PathParam("rootId")int rootId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteList]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().deleteList(rootId);
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.AttributeDefinitionDaoService.LOAD_BY_TYPE) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadList(@QueryParam("attributeType")String attributeType) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadList]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadList(AttributeType.valueOf(attributeType)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Attribute.Prefix+CampRest.AttributeDaoService.LOAD_BY_OBJECT_ID) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByObjectId(@QueryParam("objectId")int objectId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByObjectId]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadByObjectId(objectId).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadByObjectId completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.AttributeDaoService.SAVE_BY_OBJECT_ID) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String saveByObjectId(@QueryParam("objectId")int objectId, String attributeMap) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[saveByObjectId]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().saveByObjectId(objectId, AttributeMap._fromJson(attributeMap)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[saveByObjectId completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Attribute.Prefix+CampRest.AttributeDaoService.LOAD_ALL) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadAttributesByObjectId(@QueryParam("objectId")int objectId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadAttributesByObjectId]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadAttributesByObjectId(objectId).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadAttributesByObjectId completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Attribute.Prefix+CampRest.AttributeDaoService.SAVE_ALL) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String saveAttributesByObjectId(@QueryParam("objectId")int objectId, String attributeList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[saveAttributesByObjectId]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().saveAttributesByObjectId(objectId, AttributeList._fromJson(attributeList)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[saveAttributesByObjectId completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.Attribute.Prefix+CampRest.AttributeDaoService.UPDATE_ALL) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int updateAttributesByObjectId(@QueryParam("objectId")int objectId, String attributeList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[updateAttributesByObjectId]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().updateAttributesByObjectId(objectId, AttributeList._fromJson(attributeList));
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[updateAttributesByObjectId completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.AttributeDefinitionDaoService.LOAD_BY_GROUP) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadGroup(@QueryParam("parentId")int parentId, @QueryParam("groupName")String groupName) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadGroup]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadGroup(parentId, groupName).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadGroup completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.AttributeDefinitionDaoService.LOAD_AFTER_POSITION) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadAfterPosition(@QueryParam("id")int id, @QueryParam("position")int position) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadAfterPosition]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadAfterPosition(id, position).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadAfterPosition completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.AttributeDefinitionDaoService.LOAD_BEFORE_POSITION) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadBeforePosition(@QueryParam("id")int id, @QueryParam("position")int position) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadBeforePosition]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadBeforePosition(id, position).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadBeforePosition completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeDefinition.Prefix+CampRest.AttributeDefinitionDaoService.LOAD_POSITION_RANGE) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadRange(@QueryParam("id")int id, @QueryParam("startPosition")int startPosition, @QueryParam("endPosition")int endPosition) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadRange]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadRange(id, startPosition, endPosition).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadRange completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.AttributeValueDaoService.SAVE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String save(@QueryParam("objectId")int objectId, String attribute) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[save]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().save(objectId, AttributeInterface._fromJson(attribute)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[save completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.AttributeValueDaoService.SAVE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String saveList(@QueryParam("objectId")int objectId, String attributeList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[saveList]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().saveList(objectId,AttributeList._fromJson(attributeList)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[saveList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.AttributeValueDaoService.UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int update(@QueryParam("objectId")int objectId, String attribute) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[update]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().update(objectId,AttributeInterface._fromJson(attribute));
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[update completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.AttributeValueDaoService.UPDATE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int updateList(@QueryParam("objectId")int objectId, String attributeList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[updateList]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().updateList(objectId,AttributeList._fromJson(attributeList));
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[updateList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.AttributeValueDaoService.DELETE_GET) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public int delete(@QueryParam("objectId")int objectId, @QueryParam("attributeId")int attributeId, @QueryParam("valueId")int valueId, @QueryParam("attributeType")String attributeType) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delete]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().delete(objectId, attributeId, valueId, AttributeType.valueOf(attributeType));
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[delete completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.AttributeValueDaoService.DELETE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int delete(@QueryParam("objectId")int objectId, String attribute) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delete]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().delete(objectId,AttributeInterface._fromJson(attribute));
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[delete completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.AttributeValueDaoService.DELETE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public int deleteList(@QueryParam("objectId")int objectId, String attributeList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[deleteList]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = AttributeDao.instance().deleteList(objectId,AttributeList._fromJson(attributeList));
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[deleteList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return retVal;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.DaoService.LOAD) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String load(@QueryParam("objectId")int objectId, String attribute) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[load]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().load(objectId,AttributeInterface._fromJson(attribute)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[load completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.AttributeValueDaoService.LOAD_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadByObjectId(String attributeList, @QueryParam("objectId")int objectId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByObjectId]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadByObjectId(AttributeList._fromJson(attributeList),objectId).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadByObjectId completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.AttributeValueDaoService.LOAD_BY_GROUP) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadGroup(String attributeList, @QueryParam("objectId")int objectId, @QueryParam("groupName")String groupName) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadGroup]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadGroup(AttributeList._fromJson(attributeList),objectId,groupName).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadGroup completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.AttributeValueDaoService.LOAD_AFTER_POSITION) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadAfterPosition(String attributeList, @QueryParam("objectId")int objectId, @QueryParam("position")int position) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadAfterPosition]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadAfterPosition(AttributeList._fromJson(attributeList),objectId,position).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadAfterPosition completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.AttributeValueDaoService.LOAD_BEFORE_POSITION) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadBeforePosition(String attributeList, @QueryParam("objectId")int objectId, @QueryParam("position")int position) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadBeforePosition]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadBeforePosition(AttributeList._fromJson(attributeList), objectId, position).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadBeforePosition completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.AttributeValueDaoService.LOAD_POSITION_RANGE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadRange(String attributeList, @QueryParam("objectId")int objectId, @QueryParam("startPosition")int startPosition, @QueryParam("endPosition")int endPosition) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadRange]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadRange(AttributeList._fromJson(attributeList),objectId,startPosition,endPosition).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadRange completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.InstanceDaoService.LOAD_FIRST) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadFirst(@QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadFirst]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadFirst(businessId).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadFirst completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.InstanceDaoService.LOAD_PREVIOUS) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadPrevious(String attribute) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadPrevious]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadPrevious(AttributeInterface._fromJson(attribute)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadPrevious completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.InstanceDaoService.LOAD_NEXT) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadNext(String attribute) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadNext]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadNext(AttributeInterface._fromJson(attribute)).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadNext completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.InstanceDaoService.LOAD_DATE_BY_BID) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadDate(@QueryParam("businessId")String businessId, @QueryParam("date")String date) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDate]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadDate(businessId,date).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadDate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.InstanceDaoService.LOAD_DATE_RANGE_BY_BID) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadDateRange(@QueryParam("businessId")String businessId, @QueryParam("startDate")String startDate, @QueryParam("endDate")String endDate) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDateRange]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadDateRange(businessId,startDate,endDate).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadDateRange completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.InstanceDaoService.LOAD_DATE) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadDate(@QueryParam("date")String date) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDate]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadDate(date).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadDate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

	@Path(CampRest.AttributeValue.Prefix+CampRest.InstanceDaoService.LOAD_DATE_RANGE) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadDateRange(@QueryParam("startDate")String startDate, @QueryParam("endDate")String endDate) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadDateRange]";
			msg = "====[  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = AttributeDao.instance().loadDateRange(startDate,endDate).toJson();
				if(!Util._IN_PRODUCTION) {
					String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
					msg = "====[loadDateRange completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
				}
		return json;
	}

}	
