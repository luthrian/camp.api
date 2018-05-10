package com.camsolute.code.camp.api.servicepoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.catalina.tribes.group.interceptors.OrderInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.camsolute.code.camp.lib.dao.LoggerDaoInterface.RangeTarget;
import com.camsolute.code.camp.lib.dao.rest.LoggerServicePointInterface;
import com.camsolute.code.camp.lib.data.CampRest;
import com.camsolute.code.camp.lib.models.customer.Customer;
import com.camsolute.code.camp.lib.models.customer.CustomerInterface;
import com.camsolute.code.camp.lib.models.customer.CustomerList;
import com.camsolute.code.camp.lib.models.order.Order;
import com.camsolute.code.camp.lib.models.order.OrderInterface;
import com.camsolute.code.camp.lib.models.order.OrderList;
import com.camsolute.code.camp.lib.models.product.Product;
import com.camsolute.code.camp.lib.models.product.ProductInterface;
import com.camsolute.code.camp.lib.models.product.ProductList;
import com.camsolute.code.camp.lib.utilities.LogEntryInterface;
import com.camsolute.code.camp.lib.utilities.LogEntryInterface.LogObjects;
import com.camsolute.code.camp.lib.utilities.LoggerDao;
import com.camsolute.code.camp.lib.utilities.Util;

@Path(CampRest.Logging.Prefix)
public class LoggerAPI implements LoggerServicePointInterface {

	private static final Logger LOG = LogManager.getLogger(LoggerAPI.class);
	private static String fmt = "[%15s] [%s]";
	
	public static String serverUrl = CampRest.LOGGING_API_SERVER_URL;

	public static String domainUri = CampRest.LOGGING_API_DOMAIN;

	@Path(CampRest.LoggingService.LOG) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String log(String object, @QueryParam("objectType")String objectType) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[log]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		
		String v = "{}";
		switch(objectType) {
		case "Order":
			Order o = OrderInterface._fromJson(object);
			v = LoggerDao.instance().log(o,LogObjects.valueOf(objectType),!Util._IN_PRODUCTION).toJson();
			break;
		case "Customer":
			Customer c = CustomerInterface._fromJson(object);
			v = LoggerDao.instance().log(c,LogObjects.valueOf(objectType),!Util._IN_PRODUCTION).toJson();
			break;
		case "Product":
			Product p = ProductInterface._fromJson(object);
			v = LoggerDao.instance().log(p,LogObjects.valueOf(objectType),!Util._IN_PRODUCTION).toJson();
			break;
			default:
				break;
		}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[log completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return v;
	}

	@Path(CampRest.LoggingService.LOG_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String logList(String objects, @QueryParam("objectType")String objectType) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[logList]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
		String vl = "{}";
		switch(objectType) {
		case "Order":
			OrderList ol = OrderList._fromJson(objects);
			vl = LoggerDao.instance().log(ol,LogObjects.valueOf(objectType),!Util._IN_PRODUCTION).toJson();
			break;
		case "Customer":
			CustomerList cl = CustomerList._fromJson(objects);
			vl = LoggerDao.instance().log(cl,LogObjects.valueOf(objectType),!Util._IN_PRODUCTION).toJson();
			break;
		case "Product":
			ProductList pl = ProductList._fromJson(objects);
			vl = LoggerDao.instance().log(pl,LogObjects.valueOf(objectType),!Util._IN_PRODUCTION).toJson();
			break;
			default:
				break;
		}
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[logList completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_TYPE) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByType(@QueryParam("objectType")String objectType) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByType(objectType, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_TYPE_GROUP) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByTypeGroup(@QueryParam("objectType")String objectType, @QueryParam("group")String group) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByTypeGroup(objectType, group, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_TYPE_VERSION) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByTypeVersion(@QueryParam("objectType")String objectType, @QueryParam("version")String version) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByTypeVersion(objectType, version, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_TYPE_DATE) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByTypeDate(@QueryParam("objectType")String objectType, @QueryParam("date")String date) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByTypeDate(objectType, date, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_TYPE_END_OF_LIFE) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByTypeEndOfLife(@QueryParam("objectType")String objectType, @QueryParam("endOfLife")String endOfLife) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByTypeEndOfLife(objectType, endOfLife, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_TYPE_DATE_RANGE) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByTypeDateRange(@QueryParam("businessId")String businessId, @QueryParam("startDate")String startDate, @QueryParam("endDate")String endDate, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByTypeDateRange(businessId, startDate, endDate, RangeTarget.valueOf(target), !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_TYPE_TIMESTAMP) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByTypeTimestamp(@QueryParam("businessId")String businessId, @QueryParam("timestamp")String timestamp) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByTypeTimestamp(businessId, timestamp, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_TYPE_LOG_TIMESTAMP) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByTypeLogTimestamp(@QueryParam("businessId")String businessId, @QueryParam("timestamp")String timestamp) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByTypeLogTimestamp(businessId, timestamp, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_BUSINESS_ID) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByBusinessId(@QueryParam("businessId")String businessId) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByBusinessId(businessId, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_GROUP) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByGroup(@QueryParam("businessId")String businessId, @QueryParam("group")String group) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByGroup(businessId, group, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_VERSION) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByVersion(@QueryParam("businessId")String businessId, @QueryParam("version")String version) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByVersion(businessId, version, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_DATE) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByDate(@QueryParam("businessId")String businessId, @QueryParam("date")String date) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByDate(businessId, date, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_END_OF_LIFE) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByEndOfLife(@QueryParam("objectType")String objectType, @QueryParam("endOfLife")String endOfLife) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByEndOfLife(objectType, endOfLife, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_DATE_RANGE) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByDateRange(@QueryParam("businessId")String businessId, @QueryParam("startDate")String startDate, @QueryParam("endDate")String endDate, @QueryParam("target")String target) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByDateRange(businessId, startDate, endDate, RangeTarget.valueOf(target), !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_TIMESTAMP) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByTimestamp(@QueryParam("businessId")String businessId, @QueryParam("timestamp")String timestamp) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByTimestamp(businessId, timestamp, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}

	@Path(CampRest.LoggingService.LOAD_BY_LOG_TIMESTAMP) @GET @Produces(MediaType.APPLICATION_JSON)	
	@Override
	public String loadByLogTimestamp(@QueryParam("businessId")String businessId, @QueryParam("timestamp")String timestamp) {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[loadByType]";
			msg = "====[  Logger service point call:  ]====";LOG.traceEntry(String.format(fmt,_f,msg));
		}
		String vl = LoggerDao.instance().loadByLogTimestamp(businessId, timestamp, !Util._IN_PRODUCTION).toJson();
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[loadByType completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		return vl;
	}
	
	
}
