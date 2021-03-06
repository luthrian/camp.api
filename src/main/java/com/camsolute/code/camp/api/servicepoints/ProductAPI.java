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
import java.sql.Timestamp;

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

import com.camsolute.code.camp.lib.dao.rest.ProductServicePointInterface;
import com.camsolute.code.camp.lib.data.CampRest;
import com.camsolute.code.camp.lib.models.AttributeMap;
import com.camsolute.code.camp.lib.models.CampInstanceDao;
import com.camsolute.code.camp.lib.models.Group;
import com.camsolute.code.camp.lib.models.ModelList;
import com.camsolute.code.camp.lib.models.Version;
import com.camsolute.code.camp.lib.models.product.Product;
import com.camsolute.code.camp.lib.models.product.ProductDao;
import com.camsolute.code.camp.lib.models.product.ProductInterface;
import com.camsolute.code.camp.lib.models.product.ProductList;
import com.camsolute.code.camp.lib.models.process.ProcessList;
import com.camsolute.code.camp.lib.utilities.Util;

@Path(CampRest.Product.Prefix)
public class ProductAPI implements ProductServicePointInterface {
	private static final Logger LOG = LogManager.getLogger(ProductAPI.class);
	private static String fmt = "[%15s] [%s]";
	
	
	public static String serverUrl = CampRest.PRODUCT_API_SERVER_URL;

	public static String domainUri = CampRest.PRODUCT_API_DOMAIN;
	
	@Path(CampRest.DaoService.CREATE_PRODUCT) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String create(@QueryParam("businessId")String businessId, @QueryParam("businessKey")String businessKey, @QueryParam("date")String date, @QueryParam("endOfLife")String endOfLife, @QueryParam("group")String group, @QueryParam("version")String version) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[create]";
			msg = "====[ product service call: create product a product object instance and persist it to the database ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		Timestamp d = Util.Time.timestamp();
		Timestamp eol = Util.Time.timestamp(Util.Time.nowPlus(1000, Util.Time.formatDateTime));
		try {
			d =  Util.Time.timestamp(date);
		} catch(Exception e) {
			if(!Util._IN_PRODUCTION){msg = "----[Exception! Date has wrong format. ("+date+"). Using current date and time instead.]----";LOG.info(String.format(fmt, _f,msg));}
			e.printStackTrace();
		}
		try {
			eol =  Util.Time.timestamp(endOfLife);
		} catch(Exception e) {
			if(!Util._IN_PRODUCTION){msg = "----[Exception! endOfLife date has wrong format. ("+endOfLife+"). Using current date and time + 1000days instead.]----";LOG.info(String.format(fmt, _f,msg));}
			e.printStackTrace();
		}
		Product p = new Product(businessId, businessKey, new Group(group), new Version(version),d);
		p.history().endOfLife(eol);
		if(!Util._IN_PRODUCTION){msg = "----[Created new Product("+businessId+")]----";LOG.info(String.format(fmt, _f,msg));}
		p = ProductDao.instance().save(p,!Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION){msg = "----[Persisted Product instance]----";LOG.info(String.format(fmt, _f,msg));}
			
		String json = "{}";
		try {
			json = p.toJson();
		} catch (Exception e) {
			if(!Util._IN_PRODUCTION){msg = "----[JSON Exception! failed to transform service call result to JSON String.]----";LOG.info(String.format(fmt, _f,msg));}
			e.printStackTrace();
		}
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[ service call executed.]====";LOG.traceExit(String.format(fmt,_f,msg+time));
		}
		
		return json;
	}
	
	@Path(CampRest.DaoService.SAVE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String save(String product) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[save]";
			msg = "====[ save product ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		
		Product p = null;
		
		try {
			p = ProductInterface._fromJson(product);
		} catch(Exception e) {
			if(!Util._IN_PRODUCTION){msg = "----[ JSON EXCEPTION! transform from FAILED.]----";LOG.info(String.format(fmt,_f,msg));}
			e.printStackTrace();
		}
		
		p = ProductDao.instance().save(p,!Util._IN_PRODUCTION);
			
		String json = null;
		try {	
			json = p.toJson();
		} catch (Exception e) {
			if(!Util._IN_PRODUCTION){msg = "----[ JSON EXCEPTION! transform to FAILED.]----";LOG.info(String.format(fmt,_f,msg));}
			e.printStackTrace();
		}
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[ service call executed.]====";LOG.traceExit(String.format(fmt,_f,msg+time));
		}
		
		return json;
	}
	
	@Path(CampRest.DaoService.SAVE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String saveList(String productList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[_saveList]";
			msg = "====[ save product list ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		ProductList pl = new ProductList();
		if(!Util._IN_PRODUCTION){msg = "----[Product list size("+pl.size()+")]----";LOG.info(String.format(fmt, _f,msg));}
		try {
			pl = ProductList._fromJson(productList);
		} catch(Exception e) {
			if(!Util._IN_PRODUCTION){msg = "----[ JSON EXCEPTION! transform FAILED.]----";LOG.info(String.format(fmt,_f,msg));}
			e.printStackTrace();
			return "[]";
		}
		
		pl = ProductDao.instance().saveList(pl, !Util._IN_PRODUCTION);
		
		String json = pl.toJson();
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[ service call executed.]====";LOG.traceExit(String.format(fmt,_f,msg+time));
		}
		
		return json;
}
			
	@Path(CampRest.DaoService.UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String update(String product){
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[update]";
			msg = "====[ update product ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		Product p = ProductInterface._fromJson(product);
		
		p = ProductDao.instance().update(p, !Util._IN_PRODUCTION);
		
		String json = p.toJson();
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[ service call executed.]====";LOG.traceExit(String.format(fmt,_f,msg+time));
		}
		
		return json;
	}
	
	@Path(CampRest.DaoService.UPDATE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String updateList(String productList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[updateList]";
			msg = "====[ product service api: update list call ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		ProductList pl = ProductList._fromJson(productList);
		pl = ProductDao.instance().updateList(pl, !Util._IN_PRODUCTION);
		
		String json = pl.toJson();
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[updateList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
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
				msg = "====[ product service api: load product by id '"+id+"' ]====";LOG.traceEntry(String.format(fmt,_f,msg));
			}
			
			String json = ProductDao.instance().loadById(id, !Util._IN_PRODUCTION).toJson();
			
			if(!Util._IN_PRODUCTION) {
				String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
				msg = "====[ service call executed.]====";LOG.traceExit(String.format(fmt,_f,msg+time));
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
				_f = "[load]";
				msg = "====[ product service api: load product by '"+businessId+"' ]====";LOG.traceEntry(String.format(fmt,_f,msg));
			}
			
			String json = ProductDao.instance().loadByBusinessId(businessId, !Util._IN_PRODUCTION).toJson();
			
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
				_f = "[load]";
				msg = "====[ product service api: load list of orders by business key'"+businessKey+"' ]====";LOG.traceEntry(String.format(fmt,_f,msg));
			}
			
			String json = ((ProductList)ProductDao.instance().loadListByBusinessKey(businessKey, !Util._IN_PRODUCTION)).toJson();
			
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
				msg = "====[ product service api: load list of orders by group '"+group+"' ]====";LOG.traceEntry(String.format(fmt,_f,msg));
			}
			
			String json = ((ProductList)ProductDao.instance().loadListByGroup(group, !Util._IN_PRODUCTION)).toJson();
			
			if(!Util._IN_PRODUCTION) {
				String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
				msg = "====[loadListByGroup completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
			}
			return json;
	}
	
	@Path(CampRest.DaoService.LOAD_LIST) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadList() {
			long startTime = System.currentTimeMillis();
			String _f = null;
			String msg = null;
			if(!Util._IN_PRODUCTION) {
				_f = "[loadList]";
				msg = "====[ product service api: load all current products ]====";LOG.traceEntry(String.format(fmt,_f,msg));
			}
			
			String json = ((ProductList)ProductDao.instance().loadList(!Util._IN_PRODUCTION)).toJson();
			
			if(!Util._IN_PRODUCTION) {
				String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
				msg = "====[loadList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
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
				msg = "====[ product service api: load list of orders by group '"+group+"' and version '"+version+"']====";LOG.traceEntry(String.format(fmt,_f,msg));
			}
			
			String json = "[]";
			try {
				json = ((ProductList)ProductDao.instance().loadListByGroupVersion(group, version, !Util._IN_PRODUCTION)).toJson();
				if(!Util._IN_PRODUCTION){msg = "----[product service call: result JSON("+json+")]----";LOG.info(String.format(fmt, _f,msg));}
			} catch (Exception e) {
				if(!Util._IN_PRODUCTION){msg = "----[ JSON EXCEPTION! transform FAILED.]----";LOG.info(String.format(fmt,_f,msg));}
				e.printStackTrace();
			}
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
			msg = "====[ product service call: load product object registered in the updates tables that share common businessKey and target identifiers ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((ProductList)ProductDao.instance().loadUpdates(businessKey, target, !Util._IN_PRODUCTION)).toJson();
		
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
			msg = "====[ product service call: load list of orders registered in updates that share a common businessKey ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		
		String json = ((ProductList)ProductDao.instance().loadUpdatesByKey(businessKey, !Util._IN_PRODUCTION)).toJson();
		
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
			msg = "====[ product service call: load list of orders registered in updates that share a common target identifier ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ((ProductList)ProductDao.instance().loadUpdatesByTarget(target, !Util._IN_PRODUCTION)).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.LOAD_UPDATE_BY_BUSINESSID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdateByBusinessId(@QueryParam("businessId")String businessId, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdate]";
			msg = "====[ product service call: load an product that is registered in updates ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String[] ids = businessId.split(Util.DB._VS);
		if(ids.length <2) {
			if(!Util._IN_PRODUCTION){msg = "----[PARAMETER FORMAT ERROR! business id has wrong format.Format must be <product.businessId>"+Util.DB._VS+"<modelId> ]----";LOG.info(String.format(fmt, _f,msg));}
			return "";
		}
		String json = "{}";
		try{
			json = ProductDao.instance().loadUpdate(ids[0], Integer.valueOf(ids[1]), businessKey, target, !Util._IN_PRODUCTION).toJson();
		} catch (Exception e) {
			if(!Util._IN_PRODUCTION){msg = "----[ JSON EXCEPTION! transform product to json FAILED.]----";LOG.info(String.format(fmt,_f,msg));}
			e.printStackTrace();
		}
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.LOAD_UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdate(String product, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadUpdate]";
			msg = "====[ product service call: load an product that is registered in updates ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		
		String json = "{}";
		try { 
			json = ProductDao.instance().loadUpdate(ProductInterface._fromJson(product), businessKey, target, !Util._IN_PRODUCTION).toJson();
		} catch (Exception e) {
			if(!Util._IN_PRODUCTION){msg = "----[ JSON EXCEPTION! transform FAILED.]----";LOG.info(String.format(fmt,_f,msg));}
			e.printStackTrace();
		}
		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadUpdate completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.DaoService.ADD_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addToUpdates(@QueryParam("businessId")String businessId, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addToUpdates]";
			msg = "====[ product service call: register an product in the updates table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String[] ids = businessId.split(Util.DB._VS);
		if(ids.length <2) {
			if(!Util._IN_PRODUCTION){msg = "----[ERROR! business id format error! Format must be <product.businessId>"+Util.DB._VS+"<model.Id> ]----";LOG.info(String.format(fmt, _f,msg));}
			return "0";
		}
		int retVal = ProductDao.instance().addToUpdates(ids[0], Integer.valueOf(ids[1]), businessKey, target, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addToUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.DaoService.ADD_UPDATE_POST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addToUpdatesPost(String product, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addToUpdates]";
			msg = "====[ product service call: register an product in the updates table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().addToUpdates(ProductInterface._fromJson(product), businessKey, target, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addToUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.DaoService.ADD_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addListToUpdates(String productList, @QueryParam("businessKey")String businessKey, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addListToUpdates]";
			msg = "====[ product service call: register a list of product object instances in the updates table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().addToUpdates(ProductList._fromJson(productList), businessKey, target, !Util._IN_PRODUCTION);
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
			msg = "====[ product service call: deregisterd all product entries in updates that share the common businessKey and target identifiers  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().deleteAllFromUpdates(businessKey, target, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteAllFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
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
			msg = "====[ product service call: deregister an product entry from updates ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().deleteFromUpdates(businessId, businessKey, target, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdates completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ProcessReferenceDaoService.ADD_REFERENCE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addProcessReference(@QueryParam("businessId")String businessId, @QueryParam("instanceId")String instanceId, @QueryParam("processKey")String processKey) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addProcessReference]";
			msg = "====[ product service call: register an association to a process in the reference table  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().addProcessReference(businessId, instanceId, processKey, !Util._IN_PRODUCTION);
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
			msg = "====[ product service call: register association with a list of processes in the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		if(!Util._IN_PRODUCTION){msg = "----[product service call: add process references JSON("+processList+")]----";LOG.info(String.format(fmt, _f,msg));}
		int retVal = 0;
		try {
			retVal = ProductDao.instance().addProcessReferences(businessId, ProcessList._fromJson(processList), !Util._IN_PRODUCTION);
		} catch(Exception e) {
			if(!Util._IN_PRODUCTION){msg = "----[ JSON EXCEPTION! transform FAILED.]----";LOG.info(String.format(fmt,_f,msg));}
			e.printStackTrace();
		}
			
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
			msg = "====[ product service call: deregister an associate with a process from the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().delProcessReference(businessId, instanceId, processKey, !Util._IN_PRODUCTION);
		
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
			msg = "====[ product service call: deregister all processes associated with product from the reference table  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().delAllProcessReferences(businessId, !Util._IN_PRODUCTION);
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
			msg = "====[ product service call: deregister a list processes associated with product from the reference table  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		ProcessList pl = ProcessList._fromJson(processList);
		int retVal = ProductDao.instance().delProcessReferences(businessId,pl, !Util._IN_PRODUCTION);
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
			msg = "====[ product service call: load all associated processes registered in the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProductDao.instance().loadProcessReferences(businessId, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION){msg = "----[product service call: loadProcessReferences.JSON("+json+")]----";LOG.info(String.format(fmt, _f,msg));}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadProcessReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
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
			msg = "====[ product service call: load first product object instance from history ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((Product)CampInstanceDao.instance().loadFirst(businessId, ProductDao.instance(), primary)).toJson();
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
	public String loadNext(String product, @QueryParam("primary")boolean primary) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadNext]";
			msg = "====[ product service call: load next product object instance from history ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((Product)CampInstanceDao.instance().loadNext(ProductInterface._fromJson(product), ProductDao.instance(), primary)).toJson();
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
	public String loadPrevious(String product, @QueryParam("primary")boolean primary) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadPrevious]";
			msg = "====[ product service call: load previous product object instance from history ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((Product)CampInstanceDao.instance().loadPrevious(ProductInterface._fromJson(product), ProductDao.instance(), primary)).toJson();
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
			msg = "====[ product service call: load list of current product instances from history based on their timestamp date ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((ProductList)CampInstanceDao.instance().loadDate(date, ProductDao.instance(), primary)).toJson();
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
			msg = "====[ product service call: load a list of current product instances from history if their timestamp lies within the date range ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((ProductList)CampInstanceDao.instance().loadDateRange(startDate, endDate, ProductDao.instance(), primary)).toJson();
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
			msg = "====[ product service call: load a list of product object historical instances based on their timestamp dates and the businessId  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((ProductList)CampInstanceDao.instance().loadDate(businessId,date, ProductDao.instance(), primary)).toJson();
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
			msg = "====[ product service call: load a list of product object historical instances based on their the businessId and if their timestamp lies within the date range ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = null;
		try {
			json = ((ProductList)CampInstanceDao.instance().loadDateRange(businessId, startDate, endDate, ProductDao.instance(), primary)).toJson();
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
	public String addInstance(String product, @QueryParam("useObjectId")boolean useObjectId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addInstance]";
			msg = "====[ product service call: save current product object instance to history ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = 0;
		try {
			retVal = CampInstanceDao.instance().addInstance(ProductInterface._fromJson(product),useObjectId);
		} catch (SQLException e) {
			if(!Util._IN_PRODUCTION){msg = "----[SQL ERROR! loadFirst FAILED]----";LOG.info(String.format(fmt, _f,msg));}
			e.printStackTrace();
		}		
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addInstance completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
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
			msg = "====[ product service call: deregister product object instances from the updates table that have the common business key identifier aspect ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().deleteFromUpdatesByKey(businessKey, !Util._IN_PRODUCTION);
		
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
			msg = "====[ product service call: deregister product object instances from the updates table that share the common target identifier aspect ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().deleteFromUpdatesByTarget(target, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[deleteFromUpdatesByTarget completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ModelReferenceDaoService.Prefix+CampRest.ReferenceDaoService.ADD_REFERENCE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addModelReference(@QueryParam("parentBusinessId")String businessId, @QueryParam("businessId")int modelId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addModelReference]";
			msg = "====[ product service call: register an associated product model in the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().addModelReference(businessId, modelId, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addModelReference completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ModelReferenceDaoService.Prefix+CampRest.ReferenceDaoService.ADD_REFERENCES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addModelReferences(@QueryParam("parentBusinessId")String businessId, String modelList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[addModelReferences]";
			msg = "====[ product service call: register associated model object instances in the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().addModelReferences(businessId, ModelList._fromJson(modelList), !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[addModelReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ModelReferenceDaoService.Prefix+CampRest.ReferenceDaoService.DEL_REFERENCE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String delModelReference(@QueryParam("parentBusinessId")String businessId, @QueryParam("businessId")int modelId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delModelReference]";
			msg = "====[ product service call: deregister an associated product model object instance from the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().delModelReference(businessId, modelId, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[delModelReference completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ModelReferenceDaoService.Prefix+CampRest.ReferenceDaoService.DEL_ALL_REFERENCES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String delAllModelReferences(@QueryParam("parentBusinessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delAllModelReferences]";
			msg = "====[ product service call: deregister all associated product model instances from the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().delAllModelReferences(businessId, !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[delAllModelReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ModelReferenceDaoService.Prefix+CampRest.ReferenceDaoService.DEL_REFERENCES) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String delModelReferences(@QueryParam("parentBusinessId")String businessId, String modelList) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[delModelReferences]";
			msg = "====[ product service call: deregister a list of associated product model instances from the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		int retVal = ProductDao.instance().delModelReferences(businessId, ModelList._fromJson(modelList), !Util._IN_PRODUCTION);
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[delModelReferences completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return String.valueOf(retVal);
	}

	@Path(CampRest.ModelReferenceDaoService.Prefix+CampRest.ReferenceDaoService.LOAD) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadModels(@QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadModels]";
			msg = "====[ product service call: load associated product models that are registered in the reference table ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProductDao.instance().loadModels(businessId, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadModels completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.AttributeDaoService.SAVE_BY_OBJECT_ID) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String saveAttributes(@QueryParam("objectId")int productId, String attributeMap) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[saveAttributes]";
			msg = "====[ product service call: persist all current product attribute instances ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProductDao.instance().saveAttributes(productId, AttributeMap._fromJson(attributeMap), !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[saveAttributes completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.AttributeDaoService.UPDATE_BY_OBJECT_ID) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String updateAttributes(@QueryParam("objectId")int productId, String attributeMap) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[updateAttributes]";
			msg = "====[ product service call: update persisted product attribute instances ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProductDao.instance().updateAttributes(productId, AttributeMap._fromJson(attributeMap), !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[updateAttributes completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

	@Path(CampRest.AttributeDaoService.LOAD_BY_OBJECT_ID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadAttributes(@QueryParam("objectId")int productId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadAttributes]";
			msg = "====[ product service call: load all product attributes ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String json = ProductDao.instance().loadAttributes(productId, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadAttributes completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return json;
	}

}
